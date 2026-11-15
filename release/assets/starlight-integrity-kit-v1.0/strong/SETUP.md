# STRONG attempt — Tricky Store 1.4.1 (prepared, NOT flashed)

This folder is a **prepared experiment**, not a supported fix. Read it fully
before approving anything. Nothing here has been flashed or rebooted.

## What is in this folder

| File | sha256 | Purpose |
|---|---|---|
| `Starlight-TrickyStore-STRONG-1.4.1.zip` | see `SHA256SUMS` | Official Tricky Store 1.4.1 repacked with the Starlight STRONG profile |
| `Tricky-Store-v1.4.1-245-72b2e84-release.zip` | `2f5e73fcba0e4e43b6e96b38f333cbe394873e3a81cf8fe1b831c2fbd6c46ea9` | Unmodified upstream release, provenance |

The repack adds only `starlight-config/` and a short block at the end of
`customize.sh` that copies the config into `/data/adb/tricky_store/` during
install. `customize.sh.sha256` was regenerated so the upstream `verify.sh`
still passes.

The STRONG profile is:

```
/data/adb/tricky_store/keybox.xml          <- keyboxes/Yurikey70.xml (ECDSA, 4 certs, leaf 2026-11-25)
/data/adb/tricky_store/target.txt          <- vending, gms, gsf, checker, keyattestation (auto mode)
/data/adb/tricky_store/security_patch.txt  <- system/vendor/boot = 2026-11-05
```

## First: what the existing `/data/adb/tricky_store/` contains

Inspected on 2026-11-14:

| File | Size | Finding |
|---|---:|---|
| `keybox.xml` | 5054 B | **Invalid.** `duckd tee-attestation keybox` returns `invalid_keybox` (`expected one complete keybox XML with matching counts, PEM keys and certificate chains`). Same failure as `evokerrkey49.xml`. Not usable. |
| `target.txt` | 163 B | First line is an error string (`cmd: Failure calling service package: Broken pipe (32)`), then `com.android.vending!`, `com.google.android.apps.nbu.paisa.user`, `com.google.android.gms!`, `com.google.android.gsf!`. The `!` suffix forces **keybox certificate generation**, not TEE leaf hacking. Replace it. |
| `security_patch.txt` | 52 B | `system=2026-11-05`, `vendor=2026-11-05`, `boot=2026-11-05`. Format is valid; the STRONG profile keeps the same dates. |
| `hbk` | 32 B | Random bytes (`38 3e be 92 16 6e 7d 10 ...`). Tricky Store 1.4.1 does not document or use an `hbk` file. Treat it as an unrelated leftover; do not rely on it. |
| `pif.prop` | 343 B | Play Integrity Fix profile (Pixel 6a Canary). Tricky Store does not read it. Leftover. |

## Compatibility: Keymaster 4.1 HIDL / qseecom / API 37

- This device advertises legacy **Keymaster 4.0/4.1 HIDL** (not AIDL KeyMint)
  and uses Qualcomm `qseecom`.
- Tricky Store does not talk to the vendor HAL directly. Its daemon hooks the
  Android **keystore** path and modifies the attestation certificate chain.
  That is why it can work across HIDL/AIDL on Android 10+.
- Upstream README: "Android 10 or above". Changelog 1.3.0: "Compatibility with
  Android 16". **This ROM is API 37 (Android 17), which is untested upstream.**
- A public XDA report shows the closed-source Tricky Store 1.3+ on an
  A11-launch device detecting **Keymaster 4.0/4.1 HAL** and using those values
  for attestation, so the legacy HIDL backend is not disqualifying.
- Counter-evidence for Android 17: TrickyStoreOSS issue #120 (2026-11-09)
  reports that the open-source fork cannot inject into `keystore2` on a
  Pixel 10 Pro XL running Android 17. The closed-source 1.4.1 release notes
  only claim Android 16. **API 37 is the single largest risk in this
  experiment**; a failed injection would leave the device root-only with no
  BASIC improvement and would need the rollback.

## Important correction: it is not "injecting the keybox into the TEE"

Tricky Store is a **certificate-chain interceptor**. It does not install a
private key into the Qualcomm TEE and it cannot make the TEE report a locked
bootloader.

- `!` suffix: generate a certificate chain from `keybox.xml` (software key
  material; bypasses TEE leaf retrieval).
- `?` suffix: force TEE leaf hack (requires a working TEE).
- no suffix: auto — try TEE leaf hack, fall back to generation.

The keybox is leaked hardware attestation material, not this device's TEE key.
A pass would be a **spoofed chain**, not a genuine hardware-backed proof.

## Conflict with Oh My Keymint

Both Tricky Store and OMK hook keystore. Running both at once is not supported.
The prepared flow disables OMK first:

```sh
su -c 'touch /data/adb/modules/oh_my_keymint/disable'
```

Then reboot, flash the STRONG zip, reboot again. Without disabling OMK, the
result is unpredictable.

## Exact approval list (what the user must approve)

1. **Two reboots** (one to unload OMK, one to load Tricky Store).
2. Disabling the `oh_my_keymint` module (reversible: remove the `disable` file).
3. Flashing a **closed-source third-party module** (`tricky_store` 1.4.1).
4. Using the Yurikey70 keybox, which expires **2026-11-25** and may be revoked
   by Google at any time. `duckd` says `trust_verified:false`.
5. Accepting that if it fails, the phone may lose BASIC until rollback.

Do not approve this if the phone must stay usable without a reboot window.

Convenience scripts (review before running):

```sh
adb push strong/prepare.sh /data/local/tmp/prepare.sh
adb shell su -c 'sh /data/local/tmp/prepare.sh'
# reboot, flash the STRONG zip, reboot, test
adb push strong/rollback.sh /data/local/tmp/rollback.sh
adb shell su -c 'sh /data/local/tmp/rollback.sh'
# reboot, then re-run the kit baseline
```

## Expected outcome

| Scenario | Result |
|---|---|
| TEE leaf path works (auto/`?`) | Best case: a keybox-signed chain with TEE key operations; checker may show DEVICE, possibly STRONG. |
| TEE path fails, generation works (`!`) | Software keybox chain; PI may accept it if the keybox is unrevoked; not hardware-backed. |
| API 37 incompatibility / revoked keybox / OMK conflict | No better than BASIC; checker may show errors or `UNEVALUATED`. |

There is **no guarantee**. The honest probability is "worth one controlled
experiment, not a fix".

## Exact rollback

1. KernelSU -> Modules -> uninstall `tricky_store` (or its uninstall action).
2. Restore the backed-up `keybox.xml` / `target.txt` / `security_patch.txt` if
   you want the old files back:
   ```sh
   su -c 'ls -la /data/adb/tricky_store/'
   # backups created by the installer are keybox.xml.bak.<timestamp>
   ```
3. Re-enable OMK:
   ```sh
   su -c 'rm -f /data/adb/modules/oh_my_keymint/disable'
   ```
4. Reboot.
5. Re-run the kit baseline:
   ```sh
   su -c 'sh /data/adb/modules/starlight_integrity_kit/action.sh apply'
   ```
6. If the store still fails, open it once and check the checker; the baseline
   (BASIC) should return.

## Why BASIC may still be the ceiling

- The bootloader is genuinely unlocked (`/proc/cmdline:
  androidboot.verifiedbootstate=orange`).
- The build is a custom `Starlight-test` image, not a certified manufacturer
  image.
- The keybox is not Google-trusted (`trust_verified:false`) and is close to
  expiry.
- Android 17 is newer than the module's tested range.
- Any DEVICE/STRONG result would be a spoof and can be revoked by Google.
