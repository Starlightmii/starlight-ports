# Starlight Ports — Agent Instructions

You are an AI agent working in the **Starlight Ports** repository. This repo exists for AI agents (pi, Hermes, OpenCode) to port, modify, debug, and release Android ROMs for the Xiaomi Redmi Note 10 Pro Max (sweet/sweetin).

## Core Principles

1. **ROM-specific fixes live under that ROM's folder.** An IMS fix for HyperOS goes in `roms/hyperos/fixes/ims-fix/`. Never put ROM-specific patches in shared `tools/` or `skills/`.
2. **Tools are shared automation.** Scripts in `tools/` replace manual work across all ROMs (image unpacking, manifest generation, debloat scanning, partition verification). Every tool must accept `--help` and produce machine-readable output where possible.
3. **Skills teach agents procedures.** SKILL.md files in `skills/` are step-by-step workflows agents follow. They reference tools and ROM paths but contain no ROM-specific data.
4. **One change at a time.** Port first → validate baseline → debloat → customize → integrate kernel → package. Never stack untested changes.
5. **Manifests over assumptions.** Every file replacement, fix, or modification must be recorded in YAML manifests. Agents read manifests to understand state without guessing.
6. **Preserve originals.** Never overwrite base ROM files. Work in `merged/` or `build/`.

## Repository Structure

```
├── AGENTS.md                  # THIS FILE — agent operating instructions
├── README.md                  # Human-facing project overview
├── PROJECT.md                 # Master 14-step development roadmap
├── .gitignore                 # Excludes ROM binaries, build artifacts, extracted images
│
├── roms/
│   ├── hyperos/               # HyperOS 4 Chinese port
│   │   ├── README.md          # ROM metadata, status, identifiers
│   │   ├── manifest.yaml      # Machine-readable file replacement log
│   │   ├── debloat.yaml       # Packages to remove with dependency notes
│   │   ├── base/              # Untouched base ROM reference (DO NOT MODIFY)
│   │   ├── target/            # Target ROM files to port from
│   │   ├── merged/            # Working merge directory
│   │   ├── build/             # Rebuilt images and flashable output
│   │   ├── fixes/             # ROM-specific patches (each fix = subfolder)
│   │   │   └── _template/     # Copy this when creating a new fix
│   │   ├── overlays/          # RRO/resource overlays for this ROM
│   │   └── logs/              # Test results, boot logs, crash dumps
│   │
│   └── coloros/               # ColorOS port (same internal structure)
│
├── tools/                     # Shared automation scripts
│   └── README.md              # Tool index with usage examples
│
├── skills/                    # Agent-consumable procedure files
│   ├── port-rom/SKILL.md      # End-to-end porting workflow
│   ├── fix-component/SKILL.md # How to create and apply a ROM fix
│   ├── debloat-safely/SKILL.md# Safe debloating procedure
│   └── repack-images/SKILL.md # Image rebuild workflow
│
└── docs/                      # Reference material
    ├── device-sweet.md        # Partition map, hardware specs, VINTF info
    └── references.md          # Upstream links, AOSP docs, community guides
```

## Workflow for Agents

### Starting a task
1. Read `PROJECT.md` for current milestone status.
2. Read the target ROM's `README.md` and `manifest.yaml`.
3. Check `roms/<name>/logs/` for recent test results.
4. Load the relevant skill from `skills/` for the procedure you're executing.

### Creating a fix
1. Copy `roms/<name>/fixes/_template/` to `roms/<name>/fixes/<fix-name>/`.
2. Fill in `manifest.yaml` with affected files, reason, and verification steps.
3. Place patch files/scripts in the fix folder.
4. Update the ROM-level `roms/<name>/manifest.yaml` to reference the fix.
5. Log test results to `roms/<name>/logs/`.

### Using tools
- All tools live in `tools/` and are callable via `bash tools/<script>.sh [args]`.
- Tools must be idempotent where possible.
- Tools output structured data (YAML/JSON) when generating manifests or reports.
- If a manual task is repeated twice, write a tool for it.

### Git conventions
- Commit messages: `[rom/<name>] <action>: <description>` (e.g., `[rom/hyperos] fix: resolve IMS JNI crash`)
- One logical change per commit.
- Never commit extracted ROM binaries or build artifacts (see `.gitignore`).
- Tag releases: `<rom>-<version>-<date>`.

## Device Context

| Field | Value |
|-------|-------|
| Device | Xiaomi Redmi Note 10 Pro Max |
| Codename | sweet / sweetin |
| Platform | SM7150 (Snapdragon 732G) |
| Partition | Dynamic (super partition) |

## Important Warnings

- All reported ROM/kernel/build identifiers are UNVERIFIED until confirmed against actual device metadata.
- Do not assume compatibility between Android versions.
- SELinux labels, filesystem permissions, and AVB metadata must be preserved during image rebuilds.
- A folder of modified files is NOT a valid system.img — use proper repacking tools.

