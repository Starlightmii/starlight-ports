# Play Integrity on Starlight ColorOS 17 (sweetin) — findings & fix kit

**Date:** 2026-11-14 · **Device:** dd89fda1 (Redmi Note 10 Pro Max / sweetin, SM7150, 6/128)
**ROM:** Starlight build = `CP2A.260605.016` (ColorOS 17 CN donor ported to sweetin, X9U donor + sweet EEA vendor)
**Bootloader:** actually unlocked. `/proc/cmdline` contains
`androidboot.verifiedbootstate=orange` and `androidboot.selinux=permissive`.
The `ro.boot.*` props are currently spoofed to locked/green, but that does not
change the kernel command line or what the real TEE signs.
**Root:** KernelSU-Next + Zygisk Next 1.5.2 + LSPosed · **SELinux: really permissive** (`/sys/fs/selinux/enforce = 0`)

---

## 1. The symptom, and why "everything UNEVALUATED" is NOT an attestation problem

Bad state (everything withheld):

```json
"appIntegrity":      { "appRecognitionVerdict": "UNEVALUATED" },
"deviceIntegrity":   { "recentDeviceActivity": { "deviceActivityLevel": "UNEVALUATED" } },
"accountDetails":    { "appLicensingVerdict": "UNEVALUATED" },
"environmentDetails":{ "playProtectVerdict": "UNEVALUATED", "appAccessRiskVerdict": {} }
```
→ **no `deviceRecognitionVerdict` field at all.**

Good state (measured 2026-11-14 09:49, before any change):

```json
"appRecognitionVerdict": "PLAY_RECOGNIZED",
"deviceRecognitionVerdict": [ "MEETS_BASIC_INTEGRITY" ],
"appLicensingVerdict": "LICENSED",
"playProtectVerdict": "NO_ISSUES",
"appsDetected": [ "KNOWN_INSTALLED", "KNOWN_CONTROLLING", "UNKNOWN_INSTALLED" ]
```

Per Google's docs, `UNEVALUATED` on **appIntegrity** means *"a necessary requirement
was missed, such as the device not being trustworthy enough"*, and a blank
`deviceRecognitionVerdict` means *"the device has signs of attack … or system
compromise"*.

**The decisive clue:** `appRecognitionVerdict` and `appLicensingVerdict` are
*app-level* verdicts. They have nothing to do with the hardware attestation.
When **they** also read UNEVALUATED, the Play Store has stopped licensing apps —
and Google then withholds every other verdict as well. **The whole failure was a
Play Store / licensing failure, not an attestation failure.**

Note the baseline still had `appsDetected = [KNOWN_INSTALLED, KNOWN_CONTROLLING,
UNKNOWN_INSTALLED]` (KSU manager, LSPosed, VPN apps, …) and still got BASIC +
PLAY_RECOGNIZED. Those flags alone are **not** what blocks the verdict.

## 2. Root causes found (in the order they bit)

### 2.1 `--webui-clear-gms-cache` wiped the Play Store's licensing state
`keymint --webui-clear-gms-cache` deleted 8 cache dirs / 59 entries from GMS.
That removed the Play Store's accepted Terms of Service and its cached license
records. Symptom: the Play Store shows a **Terms of Service** dialog for
`pankajbagariya800@gmail.com` and licenses nothing → every verdict UNEVALUATED.
Fix: open the Play Store, tap **Accept**, let it re-sync.

### 2.2 Oh My Keymint was routing the Play Store through its software keystore
OMK's injector log (live evidence):
```
inject::hook::rewrite::reply: event=reply OMK getKeyEntry failed for uid=10209
    pid=13599: ServiceSpecific(7): .../service.rs:326 while trying to load key info
```
`uid=10209` is **com.android.vending** (the Play Store). The Play Store keeps
its account tokens in the Android Keystore; OMK's *software* keystore could not
read them → the store lost its credentials → licensing failed.
Fix: remove `com.android.vending` (and the gms packages) from OMK's `scoop`, then
`touch /data/adb/omk/restart.all`. Verified: routing now shows `route=System`.

### 2.3 The build identity was spoofed globally as another device
A global `resetprop -n` spoof (`ro.product.model=Pixel 6a`,
`ro.product.manufacturer=Google`, `ro.product.device=bluejay`) changes the
identity the **Play Store** sees. A store that suddenly belongs to a different
model/account combination loses its device registration. It also conflicts with
OMK's own attestation identity if the two disagree.
Fix: keep the real ROM identity. Only spoof if you know the store accepts it.

### 2.4 A locked phone makes every request fail
OMK's keystore log:
```
Error::Km(ErrorCode(-26))  ... matching auth token ... is expired (71s old > timeout=10s)
Error::Km(ErrorCode(-26))  ... No suitable auth token for SID count 1 type Some(HardwareAuthenticatorType(3))
Error::Km(ErrorCode(-30))  boringssl\src\aes.rs:276 Hal(VerificationFailed, "failed to finalize")
```
The attestation needs a **fresh user-auth token**. With the keyguard up there
is no fresh token → the operation fails → the request is unanswered.
Fix: **always unlock the phone before running the checker.** (`-26`/`-30`
disappear the moment the keyguard is dismissed.)

### 2.5 OMK's `[trust]` policy landmine
`device_locked = false` + `verified_boot_state = false` makes OMK's keymaster
fail fatally with `ErrorCode(-33)`. Keep both **true**.

### 2.6 The shipped `injector.toml` is invalid TOML
OhMyKeymint 1.3.5 ships a `scoop = [...]` array whose elements are neither
comma-separated nor quoted. OMK's own parser tolerates it, Duck's strict TOML
parser does not (`missing comma between array elements` → `invalid float`).
Correct form:
```toml
scoop = [
  "com.google.android.gms",
  "com.android.vending",
]
```

### 2.7 The Play Store's "lock the bootloader" message is the hardware fact
The Play Store's Play Protect certification check now says **"lock the
bootloader"**. That is not caused by an `ro.*` property: the current props say
`ro.boot.flash.locked=1` and `ro.boot.verifiedbootstate=green`, yet the check
still fails. It reads the real hardware/bootloader state:

```
$ cat /proc/cmdline | tr ' ' '\n' | grep androidboot
androidboot.selinux=permissive
androidboot.verifiedbootstate=orange
androidboot.secureboot=1
```

The bootloader is unlocked and SELinux is really permissive. The property
spoofs are only property-store values; they do not change `/proc/cmdline` or
the TEE attestation. GMS/DroidGuard's device-integrity path therefore still
sees an unlocked bootloader, and the device verdict stops at
`MEETS_BASIC_INTEGRITY`; the store reports the device as uncertified and offers
"lock the bootloader". The ROM's build is also `.../Starlight-test:...`, i.e.
not a certified manufacturer image, so even a locked bootloader would not make
this port "certified".

### 2.8 The app's installer attribution matters
Before this session the checker's package manager entry was
`installerPackageName=null`, `installerPackageUid=-1`, `installReason=0` — i.e.
sideloaded. On Android 13+ Google only returns `PLAY_RECOGNIZED` / `LICENSED`
for apps installed or updated through Play. Reinstalling the same APKs with
installer attribution restored the app-level verdicts:

```sh
adb install-multiple -r -i com.android.vending base.apk split_config.en.apk split_config.xxhdpi.apk
# pm list packages -i | grep integritycheck -> installer=com.android.vending
```

This does not wipe app data (`-r`) and is reversible. If licensing regresses,
check the installer first, before clearing any store data.

## 3. What was actually verified about the keybox

* `evokerrkey49.xml` → **corrupt**: Duck's verifier rejects it
  (*"expected one complete keybox XML with matching counts, PEM keys and
  certificate chains"*); a base64 fragment is injected into one certificate so
  the chain signature no longer verifies.
* `Yurikey70.xml` → **valid**: leaf `O=TEE, CN=88749805f81059c50debc74e1b56a789`,
  chain leaf → `Droid CA3` → `Droid CA2` → `Key Attestation CA1` (self-signed
  Google root), `source = google_remote`, `level = tee`,
  `certificate_signatures_valid = true`, revocation status **`not_listed`**
  (i.e. not revoked as of 2026-11-14). Leaf validity 2026-11-11 → **2026-11-25**
  — it expires in under two weeks, so plan a fresh keybox.

## 4. Why DEVICE / STRONG is out of reach on this device

* Google (May 2025 change, Android 13+): `MEETS_DEVICE_INTEGRITY` requires
  **hardware-backed proof of a locked bootloader** and a **certified manufacturer
  image**. `MEETS_STRONG_INTEGRITY` additionally requires a security update
  within the last year **on every partition**.
* This phone's bootloader is unlocked and Xiaomi does not allow relocking a
  custom ROM. Community sources are explicit: *"On Android 13 and newer, Google
  requires hardware-backed proof of a locked bootloader … a Play Integrity Fix
  module alone cannot give an unlocked, rooted phone that verdict"* and
  *"Basic integrity is the realistic target for a rooted phone"*.
* The fingerprint alone cannot help: a spoofed fingerprint with an unlocked
  bootloader is exactly what Google checks for.
* Extra blockers on this ROM specifically:
  * **SELinux is really permissive** (kernel cmdline `androidboot.selinux=permissive`;
    the `ro.boot.selinux=enforcing` property is only a prop lie). ~500 `avc:
    denied` records are logged. Making the ROM enforcing needs the missing
    allow rules (a KernelSU `sepolicy.rule` module is the fastest route).
  * **KernelSU-Next here has no metamodule** (`ksud module metamodule` →
    `Unsupported`), so a module's `system/` folder is never overlaid. Any tool
    that needs to replace a system APK (Duck's PI engine "full" mode) cannot
    work, and the same limitation blocks the classic TrickyStore-style overlay
    tricks.
  * **Correction:** the GMS here is the normal global package
    (`26.43.30 (260400-1006944126)`), not a CN-only build with different
    licensing behaviour. The ROM is CN ColorOS, but `PLAY_RECOGNIZED`,
    `LICENSED` and `NO_ISSUES` all return once the store is signed in, so the
    GMS build is **not** the blocker.
  * The current BASIC pass does **not** use PIF: `znctl` shows only
    lsposed/duck/OMK loaded; the extracted `playintegrityfix` module is not
    hot-loaded. Any claim that "the main GMS process gets no zygisk library"
    is a hypothesis and is not required to explain the current result.
  * `pm list features` shows **no** `android.hardware.strongbox_keystore`, and
    OMK's diagnostics report `tee: unavailable`, `strongbox: unavailable`,
    `rkp_tee: unavailable` — a software keystore cannot produce a genuinely
    hardware-backed attestation.
  * `duckd device-ids provision` fails (`QSEECom_start_app failed with status -1`).
  * `duckd rkp-device status` reports `prerequisites_met: true`, but
    `rkp-device provision` also fails at `QSEECom_start_app -1`, and
    `cmd remote_provisioning dump` is empty. A local RKP server cannot help:
    RKP certificates must chain to Google's RKP root, which a local server
    cannot mint. `duckd rkp` is an experimental diagnostic path, not a bypass.

**Conclusion: MEETS_BASIC_INTEGRITY is the realistic ceiling for this rooted,
unlocked-bootloader port.** The only remaining path to DEVICE is a
keybox/attestation-chain spoof. TEESimulator is a dead end here: upstream issue
#216 states that the Android 12+ interceptor never runs when `keystore2` wraps
a legacy HIDL Keymaster (this device logs `Add emulation wrapper around
Keymaster device` and exposes `keymaster@4.0`/`4.1` HIDL).

Classic TrickyStore is different: it hooks the keystore/attestation path rather
than the vendor HAL, so it can plausibly handle Keymaster 4.1. Public XDA
reports show closed-source TS 1.3+ detecting Keymaster 4.0/4.1 on an
A11-launch device and using it. But it needs a **reboot**, conflicts with Oh My
Keymint (both hook keystore), is officially tested only through Android 16
while this ROM is API 37 (and TrickyStoreOSS issue #120 reports Android 17
`keystore2` injection failure), and its keybox (Yurikey70) expires
2026-11-25. A prepared, **not flashed** experiment is in
`release/starlight-integrity-kit-v1.0/strong/`; do not promise DEVICE/STRONG
from it. Note also that TrickyStore does not inject a key into the TEE — it
intercepts/re-signs the certificate chain, so a pass would be a spoof, not a
genuine hardware-backed proof.

The `rkp-device` path is also not a fix: `prerequisites_met` is true but
provisioning fails at the KeyMint TA (`QSEECom_start_app -1`) and there is no
`remote_provisioning` HAL to talk to.

## 5. Fix kit contents

The kit is now consolidated into **one** KernelSU module:
`release/starlight-integrity-kit-v1.0/Starlight-Integrity-Kit-v1.0.zip`
(id `starlight_integrity_kit`). It replaces the separate baseline and spoof
modules. Source: `roms/coloros/fixes/starlight-integrity-kit/`.

| File | What it is |
|---|---|
| `Starlight-Integrity-Kit-v1.0.zip` | One module: lock-state props, OMK Google-exclusion + trust repair, keybox validation/install, opt-in identity mode (off by default), stale GMS props cleanup, store re-license helper, status/action menu. |
| `10-restore-baseline.sh` | Standalone recovery: restores the known-good BASIC state (real identity, lock-state props, OMK off Google packages, Duck PI engine off). Reversible, no reboot. |
| `20-licensing-recovery.sh` | Standalone recovery for a broken Play Store / licensing state (the UNEVALUATED-everything failure). |
| `Starlight-Kaorios-Remover-v1.0.zip` | Recovery-flashable uninstaller for the Kaorios Toolbox (already verified in OrangeFox). |
| `Starlight-PI-Global-Spoof-v1.0.zip` | **Deprecated** optional global fingerprint spoof. The kit's opt-in identity mode replaces it and is off by default because it breaks the Play Store if enabled while the store holds another identity. |
| `Starlight-Integrity-Baseline-v1.0.zip` | **Deprecated** lock-state-only module; the consolidated kit replaces it. |
| `FINDINGS.md` | This document. |
| `RECOVERY.md` | How to undo everything. |

### 5.1 The consolidated module's hard safety rule

`post-fs-data.sh`, `service.sh` and `action.sh` all call
`omk_clean_scoop` before any OMK restart. That function rewrites `scoop` and
removes `com.android.vending`, `com.google.android.gms`,
`com.google.android.gms.unstable` and `com.google.android.gsf`. If the rewrite
cannot be validated, it installs a conservative `injector.toml` with
`scoop = []`. OMK is **never** allowed to restart while a Google package is in
the routing list.

### 5.2 The STRONG experiment (prepared, not flashed)

`release/starlight-integrity-kit-v1.0/strong/` contains Tricky Store 1.4.1
repacked with the valid Yurikey70 keybox, a clean `target.txt`, and the
`security_patch.txt` profile. It is **not** part of the normal install.

Correction to the earlier wording: Tricky Store does not inject a key into the
Qualcomm TEE. It intercepts the keystore/attestation certificate chain. `!`
forces generation from the keybox (software key material); `?`/auto tries to
hack the TEE leaf and falls back. It also conflicts with OMK (both hook
keystore), needs a reboot, officially supports up to Android 16 (this ROM is
API 37), and the keybox expires 2026-11-25. Treat it as one controlled
experiment with a documented rollback, not a supported fix.

## 6. Measured facts worth keeping

* TEE is alive: `android.hardware.keymaster@4.1-service-qti`, `qseecomd`,
  `vendor.qti.hardware.qseecom@1.0-service`, `vendor.qti.hardware.qteeconnector@1.0-service`.
* SELinux really permissive: `/sys/fs/selinux/enforce = 0`; the property is spoofed.
* GMS = CN build `26.43.30 (260400-1006944126)`, versionCode 264330035.
* `Yurikey70` keybox sha256 `f005433f37832860a9a595627e19d04511c6378b2725b7f88fda912f648c59eb`,
  valid until 2026-11-25, revocation `not_listed`.
* Duck `duckd rkp-device status` → `prerequisites_met: true`, `rkp_only`.
* The device needs **one controlled reboot** for a freshly installed zygisk
  module to be picked up (Zygisk Next does not hot-load a new module; `znctl
  znmod` only toggles already-loaded modules).
