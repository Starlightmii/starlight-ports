# Starlight Ports

AI-agent-driven Android ROM porting, modification & release engineering for **Xiaomi Redmi Note 10 Pro Max** (sweet/sweetin).

> **Current project goal:** Port ColorOS 17 (Android 17) to `sweetin`, make it boot, and deliver it as a recovery- or sideload-flashable package. **Do not use fastboot.** HOS4 files are reference only and must not be flashed. See `PROJECT.md` for the canonical goal, constraints, current state, and handoff plan.

> This repository is designed for AI agents (pi, Hermes, OpenCode) to autonomously port, fix, debloat, and package ROMs. Humans can use it too — but the structure, manifests, and skills are optimized for machine consumption.

## Release / Showcase

**[Starlight — ColorOS 17 for Redmi Note 10 Pro Max →](README.md)**

The repository front page (`README.md`) is the public release page for the current build: highlights, on-device screenshots, demo video, SHA-256 checksums, and recovery/sideload install instructions. Release media lives in [`assets/`](assets/), covered by [`CHECKSUMS.sha256`](CHECKSUMS.sha256).

## Repository Structure

```
├── AGENTS.md                 # Instructions for AI agents entering this repo
├── PROJECT.md                # Canonical goal, current state, constraints, and handoff plan
├── agents/                   # File-based agent mailbox, task board, and status
│   └── README.md             # Coordination protocol and CLI usage
├── README.md                 # Public release page (repository front page)
├── README.developer.md       # This file — developer / agent workspace overview
├── assets/                   # Release banner, screenshots, poster and demo video
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
├── agents/                   # Agent task board, mailbox, status, and profiles
│   ├── README.md             # Coordination protocol
│   ├── BOOTSTRAP.md          # Self-scan and connect prompts
│   └── profiles/             # Pi and OpenCode self-models
│
├── tools/                    # Shared automation scripts (cross-ROM)
│   ├── agents/board.py       # File-based agent task board and mailbox
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

| ROM | Target | Android | Device layer | Status |
|-----|--------|---------|--------------|--------|
| **ColorOS 17** | OPPO/OnePlus → sweetin | 17 (API 37) | HOS4 kernel/vendor as reference; recovery-only | Built-unverified; boot testing |
| HyperOS 4 | Chinese → sweetin | 17 (reported) | 4.14.357-reforge (reported) | Paused / reference |

> Current work is **ColorOS 17 only**. HOS4 is a working `sweetin` reference used to build a compatible device layer; it is not the deliverable and must not be flashed.

## For AI Agents

Read `AGENTS.md` and the canonical goal in `PROJECT.md` first. `AGENTS.md` contains the operating instructions, conventions, and workflow rules. `PROJECT.md` records the current mission, non-negotiable constraints, verified device state, and handoff plan. Use `agents/` for task claims, handoffs, messages, and status; see `agents/README.md`. Read your own profile in `agents/profiles/`, scan via `agents/BOOTSTRAP.md`, and send the other agent an introduction before claiming work. Every task should follow the skills in `skills/`, use tools from `tools/`, and record changes in the appropriate ROM's `manifest.yaml`.

## For Humans

See `PROJECT.md` for the current goal, constraints, verified state, and handoff plan. Each ROM folder contains its own README with status and metadata. Fixes are self-contained under each ROM's `fixes/` directory with manifests explaining what changed and why.

