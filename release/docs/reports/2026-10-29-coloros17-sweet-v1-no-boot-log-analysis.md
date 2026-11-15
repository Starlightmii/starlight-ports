# ColorOS 17 sweet v1 — No-Boot Log Analysis

- **Date:** 2026-10-29
- **Device:** Redmi Note 10 Pro / Note 10 Pro Max (`sweet` / `sweetin`), SM7150
- **Artifact flashed:** `/usb_otg/cos 17 by konfigure/ColorOS-17-Sweet-V1.zip`
- **Recovery:** OrangeFox R12.0_0
- **Evidence:** `recovery.log`, `dmesg.log`, `logcat.log` from `Collected logs from my cos 17 port not booted/`
- **Mode:** read-only analysis; no flashing, reboot, wipe, or device modification performed

## Question

Why did the ColorOS 17 port stop booting after flashing, and what does the recovery “unmount image” error mean?

## Short Answer

The recovery errors did not cause the no-boot.

The collected `dmesg.log` and `logcat.log` are from **OrangeFox recovery**, not from a failed Android boot. They cannot show where the normal boot stops. The strongest differences between the previously working device and the post-flash state are:

1. the ZIP replaced the known-working **Vortex 1.3** boot image with the packaged **Vortex 1.0** boot image;
2. the raw `super` / `boot` writes are unverified and can silently be incomplete;
3. `/data` was formatted after flashing with F2FS features that the packaged kernel may not support;
4. the port is a fragile hybrid: Android 17 system + Android 13-era vendor/ODM + Android 14-era boot image.

## Evidence

### 1. These are recovery logs, not Android boot logs

`dmesg.log:1725`:

```text
init: First stage mount skipped (recovery mode)
```

`logcat.log:1840+` shows recovery SELinux context:

```text
scontext=u:r:recovery:s0
```

Therefore the logs describe the recovery session that flashed the ZIP. They do not contain the failed normal boot.

### 2. The ZIP reported success, but the success message is unreliable

`recovery.log:1453-1494`:

```text
Installing ZIP file '/usb_otg/cos 17 by konfigure/ColorOS-17-Sweet-V1.zip'
...
 - Flashing boot.img to boot partition
 - Flashing dtbo.img to dtbo partition
 - Flashing vbmeta.img to vbmeta partition
 - Flashing vbmeta_system.img to vbmeta_system partition
 - Flashing cust.img.zst to cust partition
 - Flashing super.img.zst to super partition
...
 - Flashed Successfully!
I:Process ended with no errors.
I:Install took 197 second(s).
I:flash_zip: installer code = 0
```

However, the script continues after failed commands and does not abort, so `installer code = 0` does not prove every step worked.

### 3. `run_program: command not found` affected recovery-side mount/wipe/cleanup steps

`recovery.log:1469-1488`:

```text
/tmp/updater: line 34: run_program: command not found
/tmp/updater: line 37: run_program: command not found
/tmp/updater: line 38: run_program: command not found
/tmp/updater: line 41: run_program: command not found
/tmp/updater: line 42: run_program: command not found
/tmp/updater: line 55: run_program: command not found
/tmp/updater: line 56: run_program: command not found
```

These correspond to the busybox flash, mount `/cache`, mount `/cust`, wipe `/cust`, wipe `/data/dalvik-cache`, and cleanup. The actual image writes use `package_extract_file` / `package_extract_zstd`, so the images were probably written despite these errors.

The skipped `/cust` wipe can leave stale cust/overlay data, but it is unlikely to prevent boot by itself. The skipped dalvik wipe is irrelevant after `/data` was formatted.

### 4. The remembered “unmount image” error is recovery-side and benign

`recovery.log:1508-1509`:

```text
I:OrangeFox: bind-unmounting /sdcard before f2fs data format...
umount: /sdcard: Invalid argument
```

This happens immediately before OrangeFox formats `/data` as F2FS. It is a recovery mount-namespace quirk, not a system boot failure. The normal `Unmounting main partitions...` message also appears earlier in recovery startup.

### 5. Recovery could not map the dynamic partitions

`dmesg.log:1760-1763`:

```text
init: DM_DEV_STATUS failed for system_ext: No such device or address
init: Could not update logical partition
init: DM_DEV_STATUS failed for product: No such device or address
init: Could not update logical partition
```

Recovery also reports `/super ... Size: 0MB` in `recovery.log`. This can be normal when OrangeFox does not map super logical partitions, but it is also consistent with damaged or incomplete super metadata. It is not conclusive from recovery alone.

### 6. Keymaster 4.0 errors are a recovery mismatch

`dmesg.log:1872+` repeatedly reports:

```text
Could not find 'android.hardware.keymaster@4.0::IKeymasterDevice/default'
```

OrangeFox recovery asks for keymaster 4.0 while the target vendor provides 4.1. This is a recovery/userspace mismatch and does not prove the normal ColorOS boot fails for the same reason.

### 7. The ZIP downgraded the boot image

The port report records:

- live kernel: `4.14.357-Vortex-Hybrid-1.3-PORT-gb90ad463cac0`, built 2026-10-02, with KSU;
- packaged kernel: `4.14.357-Vortex-Hybrid-1.0-PORT`, built 2026-09-15;
- live `boot` does not match the packaged `boot.img` at 24,898,672 byte positions.

The ZIP always flashes the packaged 1.0 boot image. Reflashing therefore removed the known-working 1.3 boot image and replaced it with the older 1.0 image.

### 8. The package has no payload verification

The port report records that `update-binary`:

- writes raw images directly to block devices;
- performs no hash or size verification;
- does not flash `vbmeta_vendor`, modem, DSP, firmware, or a `super_empty`;
- assumes the target `super` is already large enough.

A `zstd | dd` write can return success even if decompression or the USB read fails partway, leaving a truncated `super` or boot image.

### 9. `/data` was formatted with F2FS compression after flashing

`recovery.log:1507`:

```text
I:mkfs_f2fs command: /system/bin/make_f2fs -g android -O project_quota,extra_attr -O compression,extra_attr /dev/block/sda34 28068855 && sload_f2fs -t /data /dev/block/sda34
```

If the packaged 1.0 kernel does not support the chosen F2FS feature set, or if the hybrid ColorOS 17 system cannot initialize encryption with the Android 13-era vendor/keymaster stack, `/data` mount can fail and the device can loop.

## Most Likely Causes, Ranked

### High

1. **Boot image regression from 1.3 to 1.0.**  
   The working device was running 1.3; the ZIP flashed 1.0. This is the clearest difference between the working state and the no-boot state.

2. **Incomplete or corrupted raw `super` / `boot` write.**  
   The package reports success without verifying payloads. A truncated super image or boot image would prevent boot while still producing installer code 0.

### Medium

3. **Fresh-data first boot failure.**  
   `/data` was formatted after flashing with F2FS compression. A kernel/filesystem or FBE/keymaster mismatch can prevent first boot.

4. **Damaged or incompatible super metadata.**  
   Recovery could not map `system_ext`/`product`, though this may be recovery-only behavior.

### Low

5. **Skipped `/cust` wipe.**  
   Can cause stale overlays/apps, but is unlikely to be the primary no-boot cause.

6. **The `umount: /sdcard` and keymaster 4.0 errors.**  
   These are recovery-side symptoms, not the cause of the Android boot failure.

## Missing Evidence

The current logs do not contain the failed normal boot. To identify the exact failure, collect the previous boot’s persistent log from recovery:

```text
/sys/fs/pstore/console-ramoops-0
/proc/last_kmsg
```

That log should show whether the boot stops at `init`, `vold`, `apexd`, `system_server`, or before userspace.

## Verification Steps

1. Compare SHA-256 of the flashed `super`, `boot`, `dtbo`, and `cust` block devices against the ZIP images.
2. Decompress the ZIP’s `super.img.zst` and `boot.img` and verify their hashes before flashing.
3. Test the known-working 1.3 boot image with the same `super` and `/data`.
4. Reformat `/data` without F2FS compression if the 1.0 kernel lacks support.
5. Capture `pstore` / `last_kmsg` after the next failed boot before rebooting again.

## Bottom Line

The recovery log shows a successful-looking flash with several skipped recovery commands and a benign `/sdcard` unmount error. It does not show the Android boot failure. The no-boot is most likely caused by the ZIP’s downgrade to the 1.0 boot image, an unverified/truncated raw write, or a first-boot failure after `/data` was reformatted.
