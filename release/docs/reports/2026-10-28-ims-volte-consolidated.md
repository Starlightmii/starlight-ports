# IMS/VoLTE consolidated report — 2026-10-28

## Summary

Two separate IMS failures were fixed on the sweetin port:

1. **HyperOS 4 JNI crash-loop** — `libimsmedia_jni.so` referenced legacy JNI
   class names. Nine exact-length string edits restored IMS on the HyperOS 4
   port. Archived at `roms/hyperos/fixes/ims-volte-jni/`.
2. **ColorOS 17 AIDL/HIDL mismatch** — ColorOS ships an AIDL-only IMS APK while
   the retained MIUI 14 vendor exposes only HIDL IMS radio 1.0–1.6. A hybrid
   ColorOS/HOS4/HIDL APK bound inside the zygote64 namespace restored real
   IMS/VoLTE registration on both SIMs. The current module is v1.3 at
   `roms/coloros/fixes/ims-radio-hidl/`.

Incoming calls and the HD status-bar icon are confirmed working. Outgoing
VoLTE call setup, SMS over IMS, reboot persistence, and an actual
TWRP/OrangeFox flash remain to be tested.

## ColorOS 17 fix

### Root cause

- ColorOS `org.codeaurora.ims` is AIDL-only: `ImsRadioHalFactory` can return
  `ImsRadioAidl` or `ImsRadioNotSupportedHal`, with no HIDL fallback.
- The retained MIUI 14 vendor provides only
  `vendor.qti.hardware.radio.ims@1.0`–`@1.6` (`imsradio0/1`), not the AIDL
  `IImsRadio` service, so the APK selected `ImsRadioNotSupportedHal`.
- HOS4 has an `ImsRadioHidl` fallback, but replacing the ColorOS APK directly
  fails: PackageManager expects `org.codeaurora.ims.ImsApp`, `sharedUserId
  android.uid.qtiphone`, and the OPPO signature. v1.0 did exactly that and
  bootlooped at the OPPO logo.

### Fix design

- Hybrid APK:
  - `classes.dex`: HOS4 A17 IMS classes (HIDL fallback);
  - `classes2.dex`: ColorOS 17 classes (`ImsApp`, package glue);
  - `classes3.dex` / `classes4.dex`: HIDL manager/base jars;
  - `classes5.dex`: `TelephonyBaseUtilsStub` for the missing MIUI class.
- `post-fs-data.sh` only logs. `service.sh` waits for boot completion, stages
  the APK and patched lib in `/data/local/tmp/sweetin_ims_hidl` with mode 644,
  bind-mounts them inside the zygote64 namespace, verifies md5 through that
  namespace, and restarts `com.qti.phone`.
- The original `/system_ext` files remain untouched; PackageManager keeps the
  ColorOS metadata/signature.
- v1.1 failed because it bound in the root-shell namespace, which zygote64 does
  not see. v1.0 is permanently deprecated.

### Verification

Focused test 08:56:52–08:57:57 on 2026-10-28:

```
ImsServiceSub : [0] VOLTE ims registered
ImsServiceSub : [1] VOLTE ims registered
ImsRegistrationImplBase: onRegistered
ImsGuardUnit[0/1] isRegistered:2
SystemUi--Statusbar: imsRegistered=true, voiceCapable=true, videoCapable=true
ImsRadioHidl: imsRadioV10–V16 availability: available
```

Live module activation at 09:01:37:

```
GLOBAL=6a104aa936179b1b965594e0414ead0d
NS=1297a69a578e522ff0b915b1b7a51152
PID=29917
```

The user then confirmed incoming calls and the HD icon. v1.3 is installed in
`/data/adb/modules_update` with the Starlight banner, which the user confirmed is
showing in KSU Next. The user will perform the reboot; the running process still
uses the v1.2 bind until then.

### v1.3 banner and recovery

- `module/banner.png`: 864×576 Starlight banner; md5
  `31b14edcb8707bb43dae1b1a9c41615b`.
- `module.prop` carries `banner=banner.png`; `ksud module list` reports it.
- Recovery ZIP: stages the module in `/data/adb/modules_update`, copies
  `module.prop`/`banner.png` into the active module directory, touches `update`,
  and never writes `/system_ext`. Requires KSU Next already installed.
- Host smoke test passed; no recovery boot has been performed yet.

## HyperOS 4 JNI fix (archived)

- Nine `.rodata` edits replace `ImsVtRequestJni`/`ImsVtCallBackJni` with
  `ImsMedia`, fixing `JNI_OnLoad`/`UnsatisfiedLinkError`.
- The patched lib is reused by the ColorOS hybrid module.
- Original report: `roms/hyperos/fixes/ims-volte-jni/README.md`.
- Artifacts: v1.2 KSU/Magisk zip and recovery zip, plus v1.0/v1.1 archives.

## Artifacts

| Artifact | sha256 |
|---|---|
| `Sweetin-ColorOS17-IMS-Radio-HIDL-v1.3.zip` | `88fac6d91979406943c6b3e5c8edadfe20576aef3e015d87afea1c99c8656f2e` |
| `Sweetin-ColorOS17-IMS-Radio-HIDL-recovery-v1.3.zip` | `2d15eb834ff4d93a78057e86ec0c80114c4b7e6c9133bd5db7a5a4878f76705b` |
| `Sweetin-IMS-VoLTE-Fix-v1.2.zip` | `b4f4e2b8cd23cab1a92385ea353d023bcf4e622b8774372277d2c56a09532b1c` |
| `Sweetin-IMS-VoLTE-Fix-recovery-v1.2.zip` | `5318fb1cb3ff29eacf1c8abbd31c4ac06d41c485e18677439e8206d3808a9777` |

## Repository map

- `roms/coloros/fixes/ims-radio-hidl/` — current ColorOS 17 hybrid fix,
  source artifacts, banner generator, recovery installer, v1.0–v1.3 ZIPs.
- `roms/coloros/logs/2026-10-27-ims-radio-hidl.md` — diagnosis.
- `roms/coloros/logs/2026-10-28-ims-radio-hidl-v1.2.md` — live registration.
- `roms/coloros/logs/2026-10-28-ims-radio-hidl-v1.3.md` — banner/recovery.
- `roms/hyperos/fixes/ims-volte-jni/` — archived HyperOS 4 JNI fix, source,
  recovery, and old versions.
- `roms/hyperos/logs/2026-09-09-ims-volte-jni.md` — archived HyperOS report.
- `docs/reports/` — this consolidated report.

## Open items

1. Reboot (user will do it) and confirm v1.3 auto-activates, the banner renders,
   and IMS re-registers.
2. Test outgoing VoLTE and SMS over IMS.
3. Optionally flash the recovery ZIP in TWRP/OrangeFox and confirm promotion.
4. Keep the unrelated `vendor.atfwd` SIGSYS/init abort loop separate.
