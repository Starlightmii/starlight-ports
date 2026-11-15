# Starlight Integrity Kit v1.0

One KernelSU module for the Play Integrity stack on the Starlight ColorOS 17
port (sweetin, CP2A.260605.016). It replaces the old split baseline/spoof
modules with a single, safe, idempotent module.

## What it does

- **Lock-state compatibility props** (`ro.boot.flash.locked=1`,
  `verifiedbootstate=green`, `vbmeta.device_state=locked`, `veritymode`,
  `secureboot.lockstate`, `oem_unlock_allowed=0`) in `post-fs-data.sh` and
  `service.sh`. These are property-store values only. The real bootloader is
  still unlocked (`/proc/cmdline: androidboot.verifiedbootstate=orange`), so
  they do **not** turn BASIC into DEVICE.
- **Oh My Keymint safety.** Removes `com.android.vending`,
  `com.google.android.gms`, `com.google.android.gms.unstable` and
  `com.google.android.gsf` from OMK's `scoop`, keeps the TOML valid, and
  forces `[trust] device_locked=true` / `verified_boot_state=true`.
  This is the single most important rule: OMK routing the Play Store is what
  destroyed the Play Store licensing on this ROM.
- **Keybox install** with `duckd tee-attestation keybox <path>` validation
  (structural fallback if duckd is missing), backup, and hot OMK restart.
- **Certified-identity mode**, OFF by default. When explicitly enabled it
  reads the identity from `/data/misc/keystore/omk/data/pif_fingerprint.json`
  and applies the global props, keeping OMK's profile and the props in sync.
  A loud warning says it breaks the Play Store on this ROM.
- **Stale `gms_certified_props.json` cleanup**, always with a backup.
- **Play Store re-license**: force-stops the store and removes only its
  `cache/` and `code_cache/`. Never clears GMS data and never runs
  `keymint --webui-clear-gms-cache`.
- **Status report**: mode, props, real cmdline, OMK scoop/trust, keybox
  validity/expiry, checker installer attribution, last copied verdict.

## Install

1. KernelSU app -> Modules -> Install from storage -> this zip.
2. Reboot once (the installer applies the safe baseline immediately; the
   boot scripts need the reboot).
3. Open the Play Store once so it re-licenses.
4. Unlock the phone, then run Play Integrity API Checker.

The checker must be Play-attributed:
`pm list packages -i | grep integritycheck` must show
`installer=com.android.vending`. If it says `null`, reinstall with:

```sh
adb install-multiple -r -i com.android.vending base.apk \
    split_config.en.apk split_config.xxhdpi.apk
```

## Action menu

KernelSU module action opens `action.sh`:

```
1) Status report
2) Apply baseline now
3) Enable certified identity (WARNING: breaks Play Store)
4) Disable certified identity (restore ROM identity)
5) Install / replace keybox from a file path
6) Repair Oh My Keymint (trust + remove Google from scoop)
7) Clean stale gms_certified_props.json (backup first)
8) Re-license the Play Store (store cache only)
9) Help / honest limits
0) Exit
```

One-shot form (useful from adb):

```sh
su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh status'
su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh apply'
su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh identity-off'
su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh keybox /data/local/tmp/keybox.xml'
```

Every write is backed up to `/data/local/tmp/pi-fix-backup-<timestamp>/`.
The module never reboots the device and never wipes data.

## Identity mode

Off by default. Enabling it writes `on` to
`/data/adb/starlight_integrity_kit/identity_mode`; `post-fs-data.sh` and
`service.sh` re-apply the profile on every boot. Disabling it writes `off` and
restores the ROM identity (`Redmi/sweetin/sweetin:17/.../Starlight-test`).
The profile file is OMK's own, so the props and the attestation identity never
diverge while the mode is on.

**Warning:** on this ROM the global identity spoof breaks the Play Store's
device registration and makes Play Integrity read `UNEVALUATED`. Leave it off
unless you are deliberately testing.

## Uninstall

The module's `uninstall.sh` restores the ROM identity and the real
(`orange` / unlocked / permissive) lock-state props, removes the identity
flag, and keeps OMK's Google exclusion in place. Backups are kept. Reboot to
fully unload the module.

## Honest limits

See `docs/LIMITS.md`. Short version: `MEETS_BASIC_INTEGRITY` is the realistic
ceiling on this unlocked, custom-signed port. The Play Store's
"lock the bootloader" message is the hardware fact, not a prop bug. DEVICE and
STRONG require a locked bootloader with a certified image, or a viable
attestation-keybox route that this ROM does not currently have.
