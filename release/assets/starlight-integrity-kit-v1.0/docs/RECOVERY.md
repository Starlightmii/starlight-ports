# Recovery / undo guide — Play Integrity work on 2026-11-14

Everything below is reversible. Nothing here flashes a partition or wipes data.

## Where the backups are

Every script writes its own backup to:

```
/data/local/tmp/pi-fix-backup-<YYYYmmdd-HHMMSS>/
```

On the PC the original evidence is in `/tmp/opencode/evidence/` and the
Kaorios removal backup is `/home/starlight/tmp/kaorios-removed-2026-11-14.tar.gz`.

## 1. Undo the identity / lock-state changes

```sh
adb shell sh /data/local/tmp/10-restore-baseline.sh
```

That is the **baseline** script, so it also re-applies the good state. To get
back to the ROM's raw properties instead:

```sh
adb shell 'export PATH=/data/adb/ksu/bin:$PATH
resetprop -n ro.build.fingerprint "Redmi/sweetin/sweetin:17/CP2A.260605.016/Starlight-test:user/release-keys"
resetprop -n ro.product.model "M2101K6I"; resetprop -n ro.product.manufacturer "Xiaomi"
resetprop -n ro.product.device "sweetin"; resetprop -n ro.product.brand "Redmi"
resetprop -n ro.build.version.security_patch "2026-09-01"'
```

## 2. Remove the modules this work installed

```sh
adb shell '/data/adb/ksud module uninstall starlight_integrity_kit'  # consolidated kit
adb shell '/data/adb/ksud module uninstall starlight_pi_spoof'       # old global spoof
adb shell '/data/adb/ksud module uninstall starlight_pi_baseline'    # old lock-state props
adb shell '/data/adb/ksud module uninstall oh_my_keymint'            # only if you want OMK gone
adb shell '/data/adb/ksud module uninstall duck-toolbox'             # only if you want Duck gone
```

The consolidated kit's `uninstall.sh` restores the ROM identity and the real
(`orange`/unlocked/permissive) lock-state props, removes the identity flag, and
keeps OMK's Google exclusion in place. Reboot afterwards.

The old `starlight_pi_spoof` module, if present, is disabled by the kit (its
`disable` file is touched) rather than deleted.

`oh_my_keymint` and `duck_toolbox` were installed from the user's own kit
(`/sdcard/Download/Nekogram/integrity/`) on 2026-11-14 morning; the standalone
`playintegrityfix` module was re-installed live from the same zip.

## 3. Put the Play Store back if it misbehaves

```sh
adb shell sh /data/local/tmp/20-licensing-recovery.sh
```

Then on the phone: accept the Terms of Service if it appears, sign in if asked,
and open the app's store page once so the store re-fetches its license.

**If Play Integrity still reports UNEVALUATED for everything after that**,
remove and re-add the Google account in Settings → Accounts. That is the only
step that needs the account password, so it has to be done by hand.

## 3a. If the checker still will not license (installer attribution)

Check how the checker was installed:

```sh
adb shell pm list packages -i | grep integritycheck
```

It must say `installer=com.android.vending` (or another Play installer). If it
says `installer=null`, Play cannot return `PLAY_RECOGNIZED` / `LICENSED` on
Android 13+. Reinstall the **same** APKs with Play attribution (`-r` keeps
data), using the base + split APKs pulled from the device:

```sh
adb install-multiple -r -i com.android.vending base.apk split_config.en.apk split_config.xxhdpi.apk
adb shell pm list packages -i | grep integritycheck   # installer=com.android.vending
```

Then clear only the Play Store's *cache* (never its data) and open the app's
store page (`market://details?id=gr.nikolasspyr.integritycheck`).

## 3b. "Lock the bootloader" is expected and cannot be fixed by props

The Play Protect certification screen reads the real bootloader state:

```sh
adb shell cat /proc/cmdline | tr ' ' '\n' | grep androidboot
# androidboot.selinux=permissive
# androidboot.verifiedbootstate=orange
# androidboot.secureboot=1
```

The `starlight_pi_baseline` props (`ro.boot.flash.locked=1`,
`ro.boot.verifiedbootstate=green`, …) are only property-store values. They do
not change `/proc/cmdline` or the TEE attestation, so the store still sees an
unlocked bootloader and offers "lock the bootloader". **Do not** try to fix
this with more props. The only real fixes are a locked bootloader with a
certified image (not possible on this port) or a keybox/HAL spoof (fragile,
currently blocked by the Keymaster 4.1 `km_compat` backend — see FINDINGS.md §4).

## 3c. Clean the stale `gms_certified_props.json`

The file still contains the old Pixel 6a Canary spoof even though the global
props were reverted. Back it up and move it away (reversible):

```sh
adb shell 'cp -a /data/system/gms_certified_props.json /data/local/tmp/pi-fix-backup-$(date +%s)/ 2>/dev/null || true'
adb shell 'mkdir -p /data/local/tmp/pi-fix-backup-manual && cp -a /data/system/gms_certified_props.json /data/local/tmp/pi-fix-backup-manual/ && mv /data/system/gms_certified_props.json /data/local/tmp/pi-fix-backup-manual/gms_certified_props.json.removed'
```

To restore: move it back and reboot is **not** required for this file.

## 4. Known-good state to return to

| Item | Value |
|---|---|
| `ro.build.fingerprint` | `Redmi/sweetin/sweetin:17/CP2A.260605.016/Starlight-test:user/release-keys` |
| `ro.product.model` | `M2101K6I` (Xiaomi, sweetin) |
| `ro.boot.flash.locked` | `1` |
| `ro.boot.verifiedbootstate` | `green` |
| `ro.boot.vbmeta.device_state` | `locked` |
| `ro.boot.veritymode` | `enforcing` |
| `ro.secureboot.lockstate` | `locked` |
| `sys.oem_unlock_allowed` | `0` |
| OMK `scoop` | **must not** contain `com.android.vending`, `com.google.android.gms`, `com.google.android.gms.unstable`, `com.google.android.gsf` |
| OMK `[trust]` | `device_locked = true`, `verified_boot_state = true` (false/false makes keymaster die with `-33`) |
| Duck PI engine | `enabled = false` while a standalone PIF module is installed |

## 5. Things that are easy to get wrong again

* **The phone must be UNLOCKED** when a Play Integrity check runs — the
  attestation needs a fresh user-auth token, otherwise it fails with
  `ErrorCode(-26)` / `ErrorCode(-30)` and every verdict reads UNEVALUATED.
* **Do not clear GMS/Play Store caches** while testing — it wipes the Play
  Store's ToS acceptance and license records and reproduces the failure.
* **Do not spoof the build identity globally** while the Play Store holds a
  device registration for the real model — the store loses its registration and
  every verdict collapses.
* **Never let OMK serve the Play Store's Keystore traffic** — its software
  keystore cannot read the store's Keystore-backed account tokens.
* A **new zygisk module needs a reboot** to be picked up; `znctl znmod` only
  toggles modules that are already loaded.
* `Yurikey70` keybox **expires 2026-11-25** — get a fresh one before then.
* **Never run `keymint --webui-clear-gms-cache` again.** It deletes GMS cache
  state that includes the Play Store's accepted ToS and license records; the
  store then asks for a fresh sign-in and every verdict collapses.
* The lock-state props (`ro.boot.flash.locked=1`, `verifiedbootstate=green`, …)
  do **not** affect Play Integrity's hardware check. The real state is in
  `/proc/cmdline` (`androidboot.verifiedbootstate=orange`) and in the TEE.
* The checker must be Play-installed (`installer=com.android.vending`). If it is
  sideloaded (`installer=null`), `PLAY_RECOGNIZED`/`LICENSED` stay UNEVALUATED
  regardless of attestation; reinstall with `-i com.android.vending` as shown
  in §3a.
* Do not leave a stale `gms_certified_props.json` from an old identity spoof;
  see §3c.

## 6. Consolidated kit (2026-11-14, later)

One module replaces the split baseline/spoof pair:

```
release/starlight-integrity-kit-v1.0/Starlight-Integrity-Kit-v1.0.zip
```

Useful one-shot actions (also available as a KernelSU module action menu):

```sh
adb shell su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh status'
adb shell su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh apply'
adb shell su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh repair-omk'
adb shell su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh clean-gms-props'
adb shell su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh relicense'
adb shell su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh identity-off'
```

Every action backs up what it touches to
`/data/local/tmp/pi-fix-backup-<timestamp>/`. The module never reboots, never
wipes data, and never runs `keymint --webui-clear-gms-cache`.

### 6a. STRONG experiment rollback

The prepared Tricky Store zip is in
`release/starlight-integrity-kit-v1.0/strong/`. It has **not** been flashed.
If you do flash it and need to undo:

1. KernelSU -> Modules -> uninstall `tricky_store`.
2. `rm -f /data/adb/modules/oh_my_keymint/disable` (re-enable OMK).
3. Reboot.
4. `adb shell su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh apply'`
5. Open the Play Store once and re-run the checker.

The installer backs up an existing `/data/adb/tricky_store/keybox.xml` as
`keybox.xml.bak.<timestamp>` before replacing it. The current
`/data/adb/tricky_store/keybox.xml` (5054 B) is invalid and should not be
restored.
