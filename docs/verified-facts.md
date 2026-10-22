# Verified Facts Log

Every entry here was confirmed by inspecting real artifacts — not memory, not assumption.
Update this file whenever a new fact is verified.

## 2026 — Base Firmware Inspection

**Artifact:** `base-firmware/sweetin/miui_SWEETININGlobal_V14.0.1.0.TKFINXM_022ce09a45_13.0.zip`

| Fact | Value | Where verified |
|------|-------|----------------|
| Device | sweetin | `updater-script` device assert |
| Base Android | 13 (SDK 33) | `META-INF/com/android/metadata` |
| Build ID | V14.0.1.0.TKFINXM | `post-build` |
| Build fingerprint | Redmi/sweetin/sweetin:13/TKQ1.221013.002/V14.0.1.0.TKFINXM:user/release-keys | `post-build` |
| Security patch | 2023-03-01 | `post-security-patch-level` |
| OTA type | BLOCK | `ota-type=BLOCK` |
| Dynamic group | qti_dynamic_partitions, max 9,122,611,200 B | `dynamic_partitions_op_list` |
| Logical partitions | system, vendor, product, odm, system_ext, mi_ext | `dynamic_partitions_op_list` |
| boot.img size | 134,217,728 B | zip listing |
| Firmware-update images | dtbo, logo, vbmeta, vbmeta_system + radio blobs | zip listing |

**Still NOT verified:**
- HyperOS 4 target ROM metadata (Android version, kernel, build ID, partition op list)
- Whether the target ROM's partition sizes fit the 9.1 GB group limit
- Exact files copied from base into target
- Whether the merged ROM boots
- AVB/signing configuration for the ported build
