# Starlight Ports

AI-agent-driven Android ROM porting, modification & release engineering for **Xiaomi Redmi Note 10 Pro Max** (sweet/sweetin).

> This repository is designed for AI agents (pi, Hermes, OpenCode) to autonomously port, fix, debloat, and package ROMs. Humans can use it too — but the structure, manifests, and skills are optimized for machine consumption.

## Release / Showcase

**[Starlight — ColorOS 17 for Redmi Note 10 Pro Max →](https://github.com/Starlightmii/starlight-ports/blob/release-showcase/release/README.md)**

Public release page for the current build: highlights, on-device screenshots, demo video, SHA-256 checksums, and recovery/sideload install instructions. The showcase lives in [`release/`](release/README.md).

## Repository Structure

```
├── AGENTS.md                 # Instructions for AI agents entering this repo
├── PROJECT.md                # Master 14-step development roadmap
├── README.md                 # You are here
├── .gitignore                # Excludes large binaries, build artifacts, logs
│
├── roms/
│   ├── hyperos/              # HyperOS 4 Chinese port (Android 17)
│   │   ├── base/             # Untouched base ROM reference files
│   │   ├── target/           # Target ROM files to port from
│   │   ├── merged/           # Working merge directory (gitignored)
│   │   ├── build/            # Rebuilt images & flashable output (gitignored)
│   │   ├── fixes/            # ROM-specific patches (IMS, camera, etc.)
│   │   │   └── _template/    # Fix template for new patches
│   │   ├── overlays/         # RRO/resource overlays
│   │   ├── logs/             # Test results & debug logs (gitignored)
│   │   ├── manifest.yaml     # Machine-readable file replacement manifest
│   │   ├── debloat.yaml      # Packages to remove + safety classification
│   │   └── README.md         # ROM metadata, status, identifiers
│   │
│   └── coloros/              # ColorOS port (same structure)
│
├── tools/                    # Shared automation scripts (cross-ROM)
│   └── README.md             # Tool index with descriptions
│
├── skills/                   # Agent-consumable SKILL.md procedures
│   ├── port-rom/             # End-to-end ROM porting workflow
│   ├── fix-component/        # How to diagnose & fix hardware components
│   ├── debloat-safely/       # Safe package removal procedure
│   └── repack-images/        # Image rebuild & dynamic partition handling
│
└── docs/                     # Reference documentation
    ├── device-sweet.md       # Device specs, partition map, firmware notes
    └── references.md         # Upstream links, AOSP docs, community guides
```

## Active Ports

| ROM | Target | Android | Kernel | Status |
|-----|--------|---------|--------|--------|
| HyperOS 4 | Chinese → sweetin | 17 (reported) | 4.14.357-reforge (reported) | Pre-merge |
| ColorOS | TBD | TBD | TBD | Planning |

## For AI Agents

Read `AGENTS.md` first. It contains your operating instructions, conventions, and workflow rules for this repository. Every task you perform should follow the skills in `skills/`, use tools from `tools/`, and record changes in the appropriate ROM's `manifest.yaml`.

## For Humans

See `PROJECT.md` for the full development roadmap. Each ROM folder contains its own README with status and metadata. Fixes are self-contained under each ROM's `fixes/` directory with manifests explaining what changed and why.

