---
name: port-rom
description: End-to-end Android ROM porting workflow for sweet/sweetin. Use when starting a new ROM port, merging base and target ROM files, or resuming a pre-merge port. Covers inventory, partition verification, baseline testing, and repack ordering.
---

# Port ROM

## When to Use
- Starting a new port (HyperOS, ColorOS, or another base)
- Resuming the file-copy / pre-merge stage
- Deciding what to replace from base vs target

## Procedure
1. **Preserve originals.** Keep untouched copies of base ROM, target ROM, and every image. Never edit `base/`.
2. **Inventory.** List every file copied/replaced. Write entries into `roms/<rom>/manifest.yaml` under `replacements`, `additions`, `removals`.
3. **Verify partition layout.** Determine whether the device uses dynamic partitions (super). Record in `manifest.yaml:partition_layout`.
4. **Verify identifiers.** Confirm Android version, kernel, build ID from actual ROM metadata — not from memory or notes.
5. **Complete the initial port.** Ensure target system and sweet hardware can coexist.
6. **Test a clean baseline.** Boot, setup wizard, calls, mobile data, Wi-Fi, Bluetooth, audio, camera, sensors.
7. **Only then** proceed to debloat / UI / installer / kernel stages.

## Pitfalls
- Copying files is NOT a correct port. Each replacement needs a compatibility reason.
- Do not assume Android version compatibility between base and target.
- Do not repack without respecting dynamic partition metadata.
- Unstable before modding = unmanageable debugging after.

## Verification
- `manifest.yaml` reflects every change actually made.
- Fresh install boots to launcher.
- Core hardware functions verified and logged in `roms/<rom>/logs/`.
