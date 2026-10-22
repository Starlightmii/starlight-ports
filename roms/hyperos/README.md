# HyperOS Port

**Target:** HyperOS 4 Chinese → Xiaomi Redmi Note 10 Pro Max (sweet/sweetin)
**Reported Android:** 17
**Reported Kernel:** 4.14.357-reforge
**Reported Build:** OS4.0.0.9.XPCCNXM

> All identifiers are reported, not yet verified against actual ROM metadata.

## Status

Pre-merge / file-copy stage. See `../../PROJECT.md` for the full roadmap.

## Directory Layout

```
hyperos/
├── base/          # Original base ROM files (preserved untouched)
├── target/        # Target HyperOS ROM files
├── merged/        # Working merge directory (gitignored)
├── build/         # Rebuilt images and flashable output (gitignored)
├── fixes/         # ROM-specific patches and native fixes
│   └── _template/ # Template for new fixes
├── overlays/      # RRO/resource overlays specific to HyperOS
├── logs/          # Test results and debug logs (gitignored)
├── manifest.yaml  # Machine-readable file replacement manifest
├── debloat.yaml   # Packages to remove + safety classification
└── README.md      # This file
```

## Fixes

All native fixes for this ROM live under `fixes/`. Each fix is a self-contained folder with:
- `manifest.yaml` — what changed, affected files, rollback instructions
- Patch files, scripts, or replacement binaries
- `README.md` — human-readable explanation

Example: `fixes/ims-fix/` for IMS-related crashes on HyperOS 4.

