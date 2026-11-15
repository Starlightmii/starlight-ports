# Honest limits

## The message is the hardware

`/proc/cmdline` on this device contains:

```
androidboot.selinux=permissive
androidboot.verifiedbootstate=orange
androidboot.secureboot=1
```

There is no `androidboot.flash.locked=1`. The `ro.boot.*` properties are
already spoofed to `1` / `green` / `locked`, so a consumer that read only those
props would see a locked device. The Play Store still says **"lock the
bootloader"**, which means the certification/device-integrity path is reading
the kernel command line and/or TEE attestation. No `ro.*` property can change
that.

The build is also `.../Starlight-test:user/release-keys`, a custom image. Even
a genuinely locked bootloader would not make it a certified manufacturer
image.

## BASIC is the ceiling

- **BASIC**: works on this ROM (verified: `PLAY_RECOGNIZED`,
  `MEETS_BASIC_INTEGRITY`, `LICENSED`, `NO_ISSUES`).
- **DEVICE**: requires hardware-backed proof of a locked bootloader and a
  certified image. Not available here.
- **STRONG**: additionally requires a recent security patch and a
  hardware-backed keystore with StrongBox/RKP-grade evidence. Not available.

## What does NOT work

| Route | Why not |
|---|---|
| Locking the bootloader | Custom uncertified `Starlight-test` image; needs a certified stock-compatible image and AVB chain; brick risk; no fastboot. |
| `ro.*` prop spoofing | The check reads cmdline/TEE, not the property store. |
| Global identity spoof | Breaks the Play Store's device registration; Play Integrity reads `UNEVALUATED`. |
| OMK serving Google apps | Routes the whole UID through the software keystore; Google apps lose real-TEE keys; licensing collapses. |
| `duckd rkp-device provision` | TA unreachable (`QSEECom_start_app -1`), no `remote_provisioning` HAL; a local RKP server cannot mint Google-trusted certs. |
| TEESimulator | On this legacy HIDL `keymaster@4.1` / `km_compat` path the Android 12+ interceptor does not handle it (upstream #216). |
| OMK software keymint for Google apps | Whole-UID routing; breaks real-TEE-backed app keys and licensing. |
| StrongBox | No `android.hardware.strongbox_keystore`; OMK reports tee/strongbox/rkp unavailable. |

## Keybox caveats

A valid keybox (`duckd` `ok:true`) is not the same as a Google-trusted keybox
(`trust_verified:true`). The Yurikey70 keybox is structurally valid but
`trust_verified:false`; its leaf expires **2026-11-25**. Revocation is not
checked offline. Google can revoke it at any time. A keybox is leaked
attestation material, not this device's TEE key.

## Tricky Store

Tricky Store hooks the Android keystore/attestation path. It does **not**
install a key into the Qualcomm TEE. In `!` mode it generates a certificate
chain from the keybox (software key material); in `?`/auto mode it tries to
hack the TEE leaf and falls back to generation. The module requires a reboot to
load, conflicts with OMK (both hook keystore), and 1.4.1 officially supports up
to Android 16 while this ROM is API 37. Treat it as an experiment with a
rollback, not a supported fix.
