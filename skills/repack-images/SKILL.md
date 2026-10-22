---
name: repack-images
description: Unpack, modify, and rebuild Android system images correctly for dynamic-partition devices like sweet/sweetin. Use when editing files inside system/vendor/product/system_ext images, rebuilding from an extracted folder, or handling super partition metadata, EROFS/ext4, sparse images, and AVB.
---

# Repack Images

## When to Use
- Editing files inside an extracted system/vendor/product image
- Rebuilding an image from a modified folder
- Handling super partition metadata, sparse images, or AVB after modification

## Procedure
1. **Identify source format.** ext4 or EROFS; raw or sparse. Record it.
2. **Unpack or mount correctly** using the matching tool (not a generic archive extractor).
3. **Make tracked modifications only** — update `manifest.yaml`.
4. **Preserve metadata:** permissions, ownership, SELinux labels, symlinks, capabilities.
5. **Rebuild in the same format** and size constraints as the source.
6. **Validate** the rebuilt image mounts and matches expected size limits.
7. **Handle verification:** re-sign / update vbmeta as required by the boot chain.
8. **Respect dynamic partition metadata** if the partitions live in `super`.
9. **Package** for the actual installer mechanism, then test on device.

## Pitfalls
- A folder of modified files compressed into a ZIP is NOT a bootable image.
- Losing SELinux labels causes silent service failures and bootloops.
- Resizing logical partitions without updating super metadata bricks install.
- Sparse vs raw mismatch breaks flashing.
- Modified images invalidate existing AVB metadata.

## Verification
- Rebuilt image mounts on host and on device.
- Device boots and services start.
- AVB/vbmeta state is intentional and documented.
