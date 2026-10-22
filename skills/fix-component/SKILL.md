---
name: fix-component
description: Diagnose and fix a broken ROM component (IMS, camera, audio, fingerprint, sensors, Wi-Fi) on sweet/sweetin. Use when a port has a specific failing feature, a JNI/native library crash, or a service that fails to start.
---

# Fix Component

## When to Use
- A specific hardware feature is broken after porting
- A native library fails to load (e.g. `UnsatisfiedLinkError ... JNI_OnLoad`)
- A system service crashes or fails to register

## Procedure
1. **Identify the layer.** Read the actual log: logcat, kernel log, init log, SELinux denials. Do not guess.
2. **Create the fix folder.** `cp -r roms/<rom>/fixes/_template roms/<rom>/fixes/<fix-name>`.
3. **Fill the manifest.** Symptom, exact error text, reproduction steps, affected files.
4. **Diagnose root cause.** Change ONE variable at a time. Preserve before/after logs.
5. **Apply the minimal fix.** Prefer replacing a compatible library/file over broad patches.
6. **Record rollback.** Every fix must state how to revert.
7. **Verify.** Reboot and confirm the symptom is gone; check for new denials or crashes.
8. **Log results** to `roms/<rom>/logs/` and update the ROM manifest.
9. **Mark status** in the fix manifest: draft → testing → verified.

## Pitfalls
- Do not stack multiple fixes before testing each.
- A library from a different Android version is usually not drop-in compatible.
- SELinux denials often masquerade as hardware failures — check `avc:` lines first.
- Missing vendor/firmware context can make a correct fix appear to fail.

## Verification
- Exact symptom no longer reproduces.
- No new crashes/denials introduced.
- Fix manifest marked `verified` with date.
