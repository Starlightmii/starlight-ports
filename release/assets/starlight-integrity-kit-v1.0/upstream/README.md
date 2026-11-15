# Upstream modules

Collected from `/sdcard/Download/Nekogram/integrity/` on 2026-11-14. These are
the exact files used during the diagnosis; keep them for rollback/reference.

| File | Version | sha256 | Notes |
|---|---|---|---|
| `OhMyKeymint-1.3.5-243-fe712e2-release.zip` | 1.3.5 (243-fe712e2-release) | `1ab9a19f0926a2816efc5a722a6c5ed226941bb1442a33af75c2d37408c24384` | Active keystore injector. Its routing of the Play Store caused the licensing collapse; the kit removes Google packages from `scoop`. |
| `PlayIntegrityFix_v4.9-inject-s.zip` | v4.9-inject-s | `26761af705e24a3de55a0c1b9f88028fe239b935d5e4211f593c4b3d7b4e9483` | Extracted but **not hot-loaded** (`znctl znmod` loads nothing). Not required for BASIC. |
| `duck-toolbox-0.2.2-Stable.zip` | 0.2.2 Stable | `c95549fd561349407d9eb6ec32a29595199fe9039a0ebc11ba1bbfe7277240d2` | Provides `duckd` (keybox validation, RKP diagnostics). Its Play Integrity engine is disabled by the kit. |

Do not flash PIF while the kit is active: it is a Zygisk in-process module and
the device's Zygisk is not loading it. The kit does not depend on it.
