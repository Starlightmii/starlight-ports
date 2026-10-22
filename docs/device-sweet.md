# Device: Xiaomi Redmi Note 10 Pro Max (sweetin)

## Verified Device Facts (from base firmware inspection)

| Field | Value | Source |
|-------|-------|--------|
| Device codename | **sweetin** (India variant) | `updater-script`, metadata |
| Product path | `Redmi/sweetin/sweetin` | `post-build` |
| SoC | Qualcomm Snapdragon 732G (SM7150) | community |
| Base firmware on hand | `miui_SWEETININGlobal_V14.0.1.0.TKFINXM` | `base-firmware/sweetin/` |
| Base Android | **13** | `post-sdk-level=33` |
| Base build ID | `TKQ1.221013.002` | `post-build` |
| Base patches | 2023-03-01 | `post-security-patch-level` |
| OTA type | **BLOCK** (block-based) | `ota-type=BLOCK` |

> Repo copy lives at `base-firmware/sweetin/miui_SWEETININGlobal_V14.0.1.0.TKFINXM_022ce09a45_13.0.zip` (3.6 GB, gitignored).

## Verified Partition Layout

Dynamic partition group: **`qti_dynamic_partitions`**, max size **9,122,611,200 bytes (~9.1 GB)**.

| Logical partition | Size (bytes) | Approx |
|-------------------|-------------|--------|
| system | 1,310,167,040 | ~1.22 GiB |
| vendor | 1,387,966,464 | ~1.29 GiB |
| product | 4,507,086,848 | ~4.20 GiB |
| odm | 1,499,136 | ~1.43 MiB |
| system_ext | 806,785,024 | ~769 MiB |
| mi_ext | 282,624 | ~276 KiB |

Source: `dynamic_partitions_op_list` inside the OTA zip. This is the real, verified layout — not an assumption.

## Verified Physical Images in OTA

| Image | Notes |
|-------|-------|
| `boot.img` | 134,217,728 bytes (128 MiB) — flashed to `by-name/boot` |
| `firmware-update/dtbo.img` | 33,554,432 bytes (32 MiB) |
| `firmware-update/logo.img` | 31,133,696 bytes |
| `firmware-update/vbmeta.img` | 8,192 bytes |
| `firmware-update/vbmeta_system.img` | 4,096 bytes |

Radio/firmware blobs also present: `abl.elf`, `aop.mbn`, `BTFM.bin`, `NON-HLOS.bin`, `cmnlib.mbn`, `cmnlib64.mbn`, `devcfg.mbn`, `dspso.bin`, `hyp.mbn`, `imagefv.elf`, `km41.mbn`, `qupv3fw.elf`, `storsec.mbn`, `tz.mbn`, `uefi_sec.mbn`, `xbl.elf`, `xbl_config.elf`.

## Dynamic Partition Section Images

OTA ships block-based deltas: `<part>.new.dat.br` (Brotli) + `<part>.transfer.list`.

| Partition | Compressed size |
|-----------|----------------|
| product | 2,294,561,658 bytes (~2.14 GiB) |
| vendor | 571,756,852 bytes |
| system | 517,624,090 bytes |
| system_ext | 351,652,539 bytes |
| odm | 384,605 bytes |
| mi_ext | 6,237 bytes |

## Flashing Workflow (from updater-script)

1. `update_dynamic_partitions(dynamic_partitions_op_list)` — removes all groups, recreates `qti_dynamic_partitions`, adds + resizes all 6 logical partitions.
2. `block_image_update(map_partition("<part>"), <part>.transfer.list, <part>.new.dat.br, <part>.patch.dat)` for each logical partition.
3. `package_extract_file("boot.img", by-name/boot)`
4. Firmware images flashed directly to their `by-name/` targets.

**Implication for porting:** the target ROM's `dynamic_partitions_op_list` must fit within the 9.1 GB group limit. Cross-ROM merges that change partition sizes must be validated against this.

## Reported (UNVERIFIED) Target-side Claims

- HyperOS 4 Chinese port, Android 17, kernel `4.14.357-reforge`, build `OS4.0.0.9.XPCCNXM`
- **These have NOT been verified.** No target ROM metadata has been inspected yet.

## Known Reported Issues

- IMS crash (prior session, unverified):
  ```
  UnsatisfiedLinkError: JNI_ERR returned from JNI_OnLoad in
  "/system_ext/priv-app/ims/lib/arm64/libimsmedia_jni.so"
  ```
  Requires actual crash log + vendor/firmware context.
