# Starlight Integrity Kit v1.0

Everything needed to set up, verify, and (if you choose) experiment with Play
Integrity on the Starlight ColorOS 17 port for the Redmi Note 10 Pro Max
(sweetin, CP2A.260605.016).

## Start here

1. Flash **`Starlight-Integrity-Kit-v1.0.zip`** once in KernelSU
   (Modules -> Install from storage). Reboot once.
2. Install the checker with Play Store attribution:
   **`play/install.sh`** (or the exact `adb install-multiple -i com.android.vending`
   command in `INSTALL.md`).
3. Open the Play Store once, sign in yourself if it asks, then unlock the
   phone and run Play Integrity API Checker.

Expected: `PLAY_RECOGNIZED`, `MEETS_BASIC_INTEGRITY`, `LICENSED`,
`NO_ISSUES`.

The Play Store will still say **"lock the bootloader"**. That is the real
hardware fact (`/proc/cmdline: androidboot.verifiedbootstate=orange`), not a
property bug. BASIC is the ceiling on this unlocked, custom-signed port.

## What is in this folder

| Path | Contents |
|---|---|
| `Starlight-Integrity-Kit-v1.0.zip` | The single KernelSU module. See `docs/` inside it. |
| `play/` | Play Integrity API Checker base APK + splits, `install.sh`, sha256s. |
| `keyboxes/` | `Yurikey70.xml` (valid until 2026-11-25) and `evokerrkey49.xml` (invalid). |
| `props/` | Copy of the stale `gms_certified_props.json` (reference only). |
| `upstream/` | OhMyKeymint, PIF inject-s, Duck ToolBox zips used during diagnosis. |
| `strong/` | Prepared Tricky Store 1.4.1 experiment. **Not flashed, not supported.** |
| `docs/` | Updated `FINDINGS.md`, `RECOVERY.md`, `LIMITS.md`. |
| `INSTALL.md` | Exact ordered steps. |
| `MANIFEST.md` | Every file, purpose, sha256. |
| `SHA256SUMS` | Hashes of every file in this folder. |

## The module

One module, id `starlight_integrity_kit`:

- `post-fs-data.sh` / `service.sh` apply the lock-state compatibility props and
  re-assert them late.
- `action.sh` opens a dark-terminal-friendly menu:
  status, apply baseline, enable/disable certified identity, install keybox,
  repair OMK, clean stale GMS props, re-license the store.
- The certified-identity mode is **OFF by default** and reads its identity from
  OMK's own `pif_fingerprint.json`, so the props and OMK never diverge. It
  warns loudly that it breaks the Play Store on this ROM.
- OMK is **never** allowed to serve `com.android.vending`,
  `com.google.android.gms`, `com.google.android.gms.unstable` or
  `com.google.android.gsf`. This is enforced on every boot and before every
  OMK restart.
- Every write is backed up to `/data/local/tmp/pi-fix-backup-<timestamp>/`.
  The module never reboots, never wipes data, never signs accounts out, and
  never runs `keymint --webui-clear-gms-cache`.

## Deprecated files

The older `Starlight-Integrity-Baseline-v1.0.zip`,
`Starlight-PI-Global-Spoof-v1.0.zip` and `Starlight-Kaorios-Remover-v1.0.zip`
are kept for provenance only. The consolidated kit replaces the baseline and
spoof modules; do not flash them alongside it.

## Honest limits

`MEETS_BASIC_INTEGRITY` is the realistic ceiling here. DEVICE/STRONG require a
locked bootloader with a certified image, or a viable attestation-keybox route
that this ROM does not currently have. The Tricky Store experiment in `strong/`
is the only remaining candidate and it needs two reboots, disabling OMK, and a
keybox that expires 2026-11-25. Read `strong/SETUP.md` before approving it.

See `docs/LIMITS.md` and `docs/FINDINGS.md` for the evidence.
