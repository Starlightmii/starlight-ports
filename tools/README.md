# Tools

Shared automation for ROM porting. Cross-ROM utilities only — ROM-specific patches belong in `roms/<name>/fixes/`.

## Rule

If a manual task is performed twice, write a tool for it. Every tool must:
- Accept `--help` (or a usage line) and fail loudly on bad input
- Be idempotent where possible
- Emit machine-readable output (YAML/JSON) when producing reports
- Never modify `roms/*/base/` or `base-firmware/`

## Available

### analysis/
| Tool | Purpose |
|------|---------|
| `rom-info.sh <rom.zip> [--json]` | Report verified device/build/SDK/patch/OTA type + logical partition sizes from any OTA zip |
| `unpack-dat.sh <rom.zip> <outdir> [part...]` | Convert block-OTA `.new.dat.br` payloads into raw `<part>.img` |
| `sdat2img.py <transfer.list> <new.dat> <out.img>` | Lower-level transfer → raw image converter |

### Build host requirements
Detected on this machine:
- `simg2img`, `img2simg` (sparse images)
- `brotli`, `zstd`
- `lpunpack`, `lpmake` (dynamic partitions)
- `mkbootimg`, `avbtool`
- `7z`, `debugfs`, `e2fsck`, `java`

Missing (install if needed):
- `unpackbootimg` — boot image unpacking
- `erofsfuse` / `fsck.erofs` — EROFS support
- `apktool` — APK decompiling

## Planned

| Tool | Purpose |
|------|---------|
| `image/unpack.sh` | Mount/extract ext4 or EROFS images |
| `image/repack.sh` | Rebuild images preserving SELinux labels, permissions, symlinks |
| `analysis/diff-trees.sh` | Diff base vs target trees, emit `manifest.yaml` entries |
| `analysis/scan-deps.sh` | APK/service dependency scan before debloating |
| `build/make-super.sh` | Assemble super image within the 9.1 GB group limit |
| `build/verify-avb.sh` | Inspect AVB/vbmeta state of built images |
| `build/package.sh` | Assemble final flashable package |
