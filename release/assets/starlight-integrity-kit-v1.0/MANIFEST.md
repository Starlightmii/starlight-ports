# MANIFEST — Starlight Integrity Kit v1.0

Generated 2026-11-14. Every file below is covered by `SHA256SUMS` (which
excludes itself). `size` is bytes.

| File | Size | sha256 | Purpose |
|---|---:|---|---|
| `Starlight-Integrity-Kit-v1.0.zip` | 13571 | `2e80fec920625732b25bc30b67944d5b69c6deb904bedfc7b1d469f2dc8ba5fe` | The single KernelSU module. |
| `README.md` | 3448 | `29f2575fb0a6a38d22b941005896d0a632cff4597a2143e89034fe5d9eb56b30` | Kit overview and quick start. |
| `INSTALL.md` | 3675 | `6e3e82e67bd9f3cf03981705d857f45edad02b1ae63fdf1f0daf10b92a9b6e9f` | Exact ordered install/verify/rollback steps. |
| `FINDINGS.md` | 15617 | `b74e82517da798f4eccc551cab7dfc65bc350d20acf3cbb5b571b3335219824c` | Diagnosed root causes and evidence. |
| `RECOVERY.md` | 9080 | `8f24a887783e9a7ae5d197a58d67f942efada2cfe47910fafede9d287e65b0c9` | Recovery / undo guide. |
| `10-restore-baseline.sh` | 7238 | `dbe660b9412758b87b453670253c82aa63d763d41ed50e8b7866f91273d7d37f` | Standalone recovery of the BASIC state. |
| `20-licensing-recovery.sh` | 4840 | `4f20f09ffd8173971a52a919e9b6a766e449f4ca01f262756ceba42b3310924a` | Standalone Play Store licensing recovery. |
| `docs/FINDINGS.md` | 15617 | `b74e82517da798f4eccc551cab7dfc65bc350d20acf3cbb5b571b3335219824c` | Copy of FINDINGS for the docs index. |
| `docs/RECOVERY.md` | 9080 | `8f24a887783e9a7ae5d197a58d67f942efada2cfe47910fafede9d287e65b0c9` | Copy of RECOVERY for the docs index. |
| `docs/LIMITS.md` | 3045 | `e7d35357a76867793b72db741fc6bfd46646e14b24c15a4b00c92a658cc53957` | Honest hardware/route limits. |
| `play/PlayIntegrityAPIChecker.apk` | 2306944 | `ee209793ebd4c7c8215f2202b5b18ced412e451cbae82a9d01291c0d824a13a9` | Checker base APK pulled from the device. |
| `play/splits/split_config.en.apk` | 41369 | `79933a5688545419e5c447cbe21945fcdaefb300d0938b62d2084ab3f8015673` | English resources split. |
| `play/splits/split_config.xxhdpi.apk` | 61928 | `bba68d197eaf24342cc550cd944afebec699916d9b0065677ccd9d1760300ac8` | xxhdpi resources split. |
| `play/install.sh` | 397 | `21a9f354c94266d1353ac5845d003314f08f3225a166c42cdc7aedc30e2833e5` | Reinstall with `-i com.android.vending`. |
| `keyboxes/Yurikey70.xml` | 5590 | `f005433f37832860a9a595627e19d04511c6378b2725b7f88fda912f648c59eb` | Valid keybox (ECDSA, leaf 2026-11-25). |
| `keyboxes/evokerrkey49.xml` | 4710 | `2a4cd97a0359e020a5d305e159b8ca02da61f2a4ae9e47488360b1762fd7aa52` | Invalid keybox; reference only. |
| `keyboxes/README.md` | 1011 | `8c02843c47dfdcde99c5571cca0fd21854a08ebfb6e94f18cf20ea48b1683767` | Validation results. |
| `props/gms_certified_props.json` | 417 | `5ca1f625afa197f1c495d288e5458eacbbc7e81c8cb6edd4cad7eb84865d10f7` | Stale Pixel 6a identity; reference only. |
| `props/README.md` | 586 | `bfa5c9a6ff3d1d69bed270c4bcdb428d796133ee265bffce11f824ab4aecc5cf` | Why it is not applied. |
| `upstream/OhMyKeymint-1.3.5-243-fe712e2-release.zip` | 10717607 | `1ab9a19f0926a2816efc5a722a6c5ed226941bb1442a33af75c2d37408c24384` | Active OMK module (rollback/reference). |
| `upstream/PlayIntegrityFix_v4.9-inject-s.zip` | 692513 | `26761af705e24a3de55a0c1b9f88028fe239b935d5e4211f593c4b3d7b4e9483` | PIF; extracted but not loaded. |
| `upstream/duck-toolbox-0.2.2-Stable.zip` | 11455471 | `c95549fd561349407d9eb6ec32a29595199fe9039a0ebc11ba1bbfe7277240d2` | Duck ToolBox / duckd source. |
| `upstream/README.md` | 1106 | `425d469cc1fd092423fbb0ef29843f6c5e03fc002c1d24117751324c2bd01f13` | Provenance and notes. |
| `strong/Starlight-TrickyStore-STRONG-1.4.1.zip` | 2847528 | `2c06918212a332b5a0531551d0c1c67c499a4edbae2ff9ef6622d2d136affec5` | Tricky Store 1.4.1 + STRONG profile (not flashed). |
| `strong/Tricky-Store-v1.4.1-245-72b2e84-release.zip` | 2840600 | `2f5e73fcba0e4e43b6e96b38f333cbe394873e3a81cf8fe1b831c2fbd6c46ea9` | Unmodified upstream release. |
| `strong/SETUP.md` | 6964 | `0411f5ce0a16d1fcd72bf2a4ed35e414dbf72e949d5b6e114053c392ad91807d` | Viability, approval list, rollback. |
| `strong/prepare.sh` | 655 | `2ec096c59fb4a22d1e60a1edba521cf44fc0ea58093a0b3d4ec0b3ea5acb0da2` | Device-side preflight: backup + disable OMK. |
| `strong/rollback.sh` | 515 | `3c417fa393bc88e7e93c278145b985fc45d8b3a0ef89300c7265d6cfbb5a1274` | Device-side rollback: uninstall TS + re-enable OMK. |
| `Starlight-Integrity-Baseline-v1.0.zip` | 2518 | `2bafec2ed69ddbc8817acc11ae9d943b6786444fce7bd93a178c6856662380bf` | Deprecated lock-state module. |
| `Starlight-PI-Global-Spoof-v1.0.zip` | 3299 | `f5530824964592941027eae77ddb28eaac31f457f250f9913df2ef1c006995f7` | Deprecated global spoof. |
| `Starlight-Kaorios-Remover-v1.0.zip` | 7159 | `ab6d9346b5e3ffc601e93ad01b38d5eb19ed822f614e4c0ea72cc9ddd500e35d` | Kaorios removal tool (unchanged). |
