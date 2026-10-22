# ColorOS Port

**Target:** ColorOS → Xiaomi Redmi Note 10 Pro Max (sweet/sweetin)
**Reported Android:** TBD
**Reported Kernel:** TBD
**Reported Build:** TBD

> All identifiers are reported, not yet verified against actual ROM metadata.

## Status

Planning stage. See `../../PROJECT.md` for the full roadmap.

## Directory Layout

```
coloros/
├── base/          # Original base ROM files (preserved untouched)
├── target/        # Target ColorOS ROM files
├── merged/        # Working merge directory (gitignored)
├── build/         # Rebuilt images and flashable output (gitignored)
├── fixes/         # ROM-specific patches and native fixes
│   └── _template/ # Template for new fixes
├── overlays/      # RRO/resource overlays specific to ColorOS
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

