# Install — exact order

Do these in order. No fastboot, no recovery, no data wipe.

## 0. Back up first

```sh
adb shell 'mkdir -p /data/local/tmp/pi-manual-backup && cp -a /data/misc/keystore/omk /data/adb/tricky_store /data/system/gms_certified_props.json /data/local/tmp/pi-manual-backup/ 2>/dev/null'
```

The module also writes its own backups to
`/data/local/tmp/pi-fix-backup-<timestamp>/`.

## 1. Flash the one module

KernelSU app -> Modules -> Install from storage ->
`Starlight-Integrity-Kit-v1.0.zip`.

The installer applies the safe baseline immediately (props + OMK hygiene).
**Reboot once** so `post-fs-data.sh` and `service.sh` are active.

After reboot, check it:

```sh
adb shell su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh status'
```

Expect: mode OFF, real `Redmi/sweetin/...` fingerprint, OMK scoop without any
Google package, keybox `duckd: ok`.

## 2. Install the checker with Play attribution

From this folder on a PC:

```sh
./play/install.sh
```

Or manually:

```sh
adb install-multiple -r -i com.android.vending \
    play/PlayIntegrityAPIChecker.apk \
    play/splits/split_config.en.apk \
    play/splits/split_config.xxhdpi.apk
```

Verify:

```sh
adb shell pm list packages -i | grep integritycheck
# package:gr.nikolasspyr.integritycheck  installer=com.android.vending
```

If it says `installer=null`, the app will not return
`PLAY_RECOGNIZED`/`LICENSED` even on a perfect device. Reinstall with the
command above. Do not use `pm set-installer`; it fails on this build.

## 3. Re-license the Play Store

Open the Play Store. If it asks you to sign in again, do it yourself. The kit
never touches your password or signs you out.

If the store is stuck, use the module action:

```sh
adb shell su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh relicense'
```

This clears only the store's `cache/` and `code_cache/`. It never clears GMS
data and never runs `keymint --webui-clear-gms-cache`.

## 4. Run the checker

Unlock the phone (the checker misbehaves on the lock screen), open Play
Integrity API Checker, tap CHECK.

Expected:

```
appRecognitionVerdict: PLAY_RECOGNIZED
appLicensingVerdict:   LICENSED
playProtectVerdict:    NO_ISSUES
deviceRecognitionVerdict: [MEETS_BASIC_INTEGRITY]
```

If it says `UNEVALUATED`, check the checker installer attribution (step 2) and
whether the Play Store is signed in and licensed.

## 5. Optional actions

```sh
# Install a validated keybox (default: keyboxes/Yurikey70.xml)
adb push keyboxes/Yurikey70.xml /data/local/tmp/keybox.xml
adb shell su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh keybox /data/local/tmp/keybox.xml'

# Back up + move the stale Pixel 6a gms_certified_props.json
adb shell su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh clean-gms-props'

# Repair OMK after any manual edit
adb shell su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh repair-omk'
```

The certified-identity mode is **off by default** and breaks the Play Store on
this ROM. Only enable it deliberately:

```sh
adb shell su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh identity-on'
```

## 6. STRONG experiment (optional, NOT part of normal install)

Read `strong/SETUP.md` fully. It requires two reboots, disabling Oh My Keymint,
and flashing a closed-source third-party module. Do not do it on a phone you
need working immediately.

## 7. Rollback

KernelSU -> Modules -> uninstall `starlight_integrity_kit`. Its `uninstall.sh`
restores the ROM identity and the real (`orange`/unlocked) lock-state props,
removes the identity flag, and keeps OMK's Google exclusion. Reboot.

Backups are never deleted automatically.
