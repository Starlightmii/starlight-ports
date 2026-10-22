---
name: debloat-safely
description: Safely remove unwanted preinstalled apps from a ported ROM without breaking system services. Use when building a debloat list, removing bloat APKs, or when a removal caused boot/permission failures.
---

# Debloat Safely

## When to Use
- Building or applying a package-removal list
- Deciding whether an unfamiliar system APK is safe to remove
- Recovering from a debloat that broke boot or permissions

## Procedure
1. **Inventory packages** across system, product, system_ext.
2. **Classify each** into: unwanted-user-app, optional-vendor-app, essential-service, permission/config provider, dependency-of-other-package, recovery/setup-required.
3. **Check dependencies** (use `tools/scan-deps.sh`) — packages, services, permission grants.
4. **Record in `debloat.yaml`** with classification, `safe_to_remove`, dependencies, notes.
5. **Remove in a dev build only.** One batch at a time.
6. **Test** boot, setup, permissions, and affected hardware after each batch.
7. **Keep a restore record** so any removal can be reverted.

## Pitfalls
- Unfamiliar APK does NOT mean bloat.
- Removing a permission provider can break unrelated apps.
- Some "store" or "service" APKs are framework dependencies.
- Never batch-delete without testing between batches.

## Verification
- Device boots to launcher after removal.
- Permission dialogs and install flow still work.
- `debloat.yaml` has a classification for every removed package.
