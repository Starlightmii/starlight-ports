# Advanced Android ROM Porting, Modification & Release Engineering

## Device Context

| Field | Value |
|-------|-------|
| Device | Xiaomi Redmi Note 10 Pro Max |
| Codename | sweet / sweetin |
| Target ROM | HyperOS 4 Chinese port |
| Android Version | 17 (reported, unverified) |
| Kernel | 4.14.357-reforge (reported, unverified) |
| Build ID | OS4.0.0.9.XPCCNXM (reported, unverified) |

> **WARNING:** ROM, Android version, kernel, and build identifiers are reported — not independently verified. Treat as unconfirmed until validated against actual device/ROM metadata.

---

## 1. Project Goal

Create a complete, advanced, customized ROM package that users flash once. Not merely a bootable port — a fully engineered release.

### Final ROM Must Include:
- Ported ROM UI and features
- Debloated (unwanted preinstalled apps removed)
- Useful customizations and optimizations
- Customized Package Installer
- Proper permissions and system-app configuration
- Integrated custom kernel (if supported)
- Compatible kernel manager app
- Correct ROM integrity and boot verification config
- Stable, tested modifications
- Clean, consistent flashable release package
- Installation instructions, known issues, troubleshooting notes

Users should **not** need to flash ten separate ZIPs. Optional modules may be provided separately later.

---

## 2. Current Status

**Stage:** File-copy / pre-merge (Step 2 of roadmap)

### What Has Been Done:
1. Base ROM obtained
2. Target ROM obtained
3. Files copied from base ROM into target ROM
4. Preparing to merge/repack — **NOT finalized**

### What Has NOT Been Verified:
- Exact image list required for this HyperOS 4 port
- Boot image format and partition layout of the actual build
- Whether reported build ID corresponds to stated Android 17
- Whether reported kernel is compatible with target vendor
- Exact files copied from base ROM
- Whether the copied ROM is complete or able to boot
- Final installer implementation or compatible source branch
- AVB configuration changes needed
- Functional testing status
- Play Integrity verdict feasibility

**No ROM image was built, no files repacked, no device modified. This is a technical roadmap, not a validated release.**

---

## 3. Core Concepts

### Porting vs Modding
- **Porting:** Making the OS compatible with target device hardware/software
- **Modding:** Changing behavior/appearance of an already-compatible ROM
- **Advanced Development:** Repeatable build process covering port → modify → validate → package → test → release

### ROM Port Components

**Target ROM components:**
- Android framework and system apps
- System resources and UI
- Framework libraries
- Product/system configuration
- Source ROM features

**Device-specific components:**
- Vendor components and hardware interfaces
- Compatible kernel and boot components
- Required firmware
- Device tree and hardware config
- Init config and fstab
- VINTF manifests
- Hardware-specific libraries and services

> Do NOT blindly replace boot.img, vendor.img, or every device-specific file. Each replacement needs a compatibility reason.

### Dynamic Partitions
Logical partitions managed within a physical `super` container. May include: system, vendor, product, system_ext. Repacking without respecting metadata breaks installation.

Reference: https://source.android.com/docs/core/ota/dynamic_partitions/implement

### Kernel & Boot Architecture
Boot-related components may include: boot.img, vendor_boot.img, init_boot.img, dtbo.img, vbmeta.img. Not every device has all of these. A kernel manager APK does NOT provide a kernel — it only exposes controls for features the installed kernel supports.

### Integrity (Two Separate Topics)

**A. ROM/Boot Integrity (AVB):**
Verified Boot, vbmeta, dm-verity, bootloader state, rollback protection, image signing, SELinux labels. Modified images invalidate existing verification metadata.

Reference: https://source.android.com/docs/security/features/verifiedboot/avb

**B. Google Play Integrity:**
Separate service for app/device integrity signals. Not fixable by adding a single file. Legitimate engineering helps; guaranteed verdicts do not exist for custom ROMs.

Reference: https://developer.android.google.cn/google/play/integrity/overview?hl=en

### Delivery Formats
- **Complete Flashable ROM:** Images + install metadata in expected format
- **Optional Mod ZIP:** Additional modification to existing ROM
- **Root Module:** Systemless changes via Magisk/etc.
- **Installer UI:** Script/app guiding installation workflow

These are NOT interchangeable.

---

## 4. Modification Levels

### Debloating
Classify packages before removal:
- Unwanted user-facing apps
- Optional vendor apps
- Apps used by essential system services
- Apps required for permissions/config
- Apps with dependencies on other packages
- Apps needed for recovery/setup

Before removing: determine function → check dependencies → check consumers → remove in dev build → test → keep restore record.

### UI Customization
- **Level 1:** Theme/resources (colors, icons, overlays) — use RROs where possible
- **Level 2:** System app customization (Settings, SystemUI)
- **Level 3:** Framework modification (JAR/service changes) — substantially more complex

### Custom Package Installer
Two approaches:
- **A:** Modify existing compatible installer source
- **B:** Build custom installer from AOSP source

Key components: PackageInstaller, PackageManager, PermissionController, install-source handling, signature verification, runtime permissions, update/failure handling.

Reference: https://github.com/hi-dhl/AOSP-PackageInstaller (study reference, NOT verified Android 17 drop-in)

**Required test cases:** normal APK install, update, incompatible APK, split APKs, signature mismatch, runtime permissions, unknown sources, failures/cancellation, post-reboot, permission manager interaction.

---

## 5. Development Roadmap (14 Steps)

| Step | Action |
|------|--------|
| 1 | Preserve originals (untouched copies of base ROM, target ROM, all images) |
| 2 | Document file replacements (change manifest) |
| 3 | Verify partition layout (dynamic? super image?) |
| 4 | Complete initial port (system + hardware coexist correctly) |
| 5 | Test clean baseline (boot, setup, calls, data, Wi-Fi, BT, audio, camera, sensors) |
| 6 | Create repeatable modification workflow (scripts, not manual edits) |
| 7 | Debloat (reviewed removal list, retest) |
| 8 | Add UI modifications (isolated changes, RROs preferred) |
| 9 | Customize Package Installer (compatible implementation, test thoroughly) |
| 10 | Integrate custom kernel (after baseline works) |
| 11 | Validate integrity/security (AVB, signing, device config) |
| 12 | Rebuild and package (correct images, compatible installer) |
| 13 | Test complete release (fresh install, reboot, operation, upgrade, recovery) |
| 14 | Document release (version, variants, firmware prereqs, mods, bugs, checksums, recovery) |

---

## 6. Image Repacking Pipeline

```
Original compatible image
  → unpack/mount correctly
  → make tracked modifications
  → preserve required metadata (permissions, SELinux, symlinks, capabilities)
  → rebuild image in correct format (ext4/EROFS, sparse/raw)
  → validate image and partition constraints
  → handle verification/signing config
  → package for supported installer
  → test on target device
```

A folder of modified files ≠ valid system.img. Format must match source.

---

## 7. Debugging Approach

Identify the failing layer, don't patch randomly.

**Sources:** logcat, kernel logs, boot/recovery logs, crash reports, init/framework config, VINTF compat, SELinux denials, app crash traces.

**Known reported issue (prior session, not current diagnosis):**
```
UnsatisfiedLinkError: JNI_ERR returned from JNI_OnLoad in
"/system_ext/priv-app/ims/lib/arm64/libimsmedia_jni.so"
```
This requires actual crash log, process/library details, and vendor/firmware context to diagnose properly.

**Principle:** Change one variable at a time. Preserve logs before and after each change.

---

## 8. Reference Links

| Resource | URL | Notes |
|----------|-----|-------|
| Dynamic Partitions | https://source.android.com/docs/core/ota/dynamic_partitions/implement | Super/logical partitions |
| Verified Boot (AVB) | https://source.android.com/docs/security/features/verifiedboot/avb | Boot verification |
| Play Integrity | https://developer.android.google.cn/google/play/integrity/overview?hl=en | Integrity signals |
| Magisk Guides | https://topjohnwu.github.io/Magisk/guides.html | Systemless modification concepts |
| Sweet Device Tree | https://github.com/xiaomi-sweet/android_device_xiaomi_sweet | Community reference — verify variant/branch/version |
| Image Patching Guide | https://github.com/1shin-7/aosp-porting-guide/blob/master/en/05-device/05-17-image-patching-system.md | General unpack/modify/repack — verify against actual layout |
| AOSP PackageInstaller | https://github.com/hi-dhl/AOSP-PackageInstaller | Study reference — confirm Android version compatibility |

> All links referenced during planning. Verify branch/version compatibility before using in release.

---

## 9. Next Milestone

Establish exactly what has been copied, what remains in each ROM, and what image/partition format the target package expects. Then proceed through the 14-step roadmap sequentially.

