# Fix Template

Copy this folder to create a new fix:

```bash
cp -r roms/<rom>/fixes/_template roms/<rom>/fixes/<fix-name>
```

## Required Contents

1. **`manifest.yaml`** — filled in completely (see the template fields). No fix is valid without it.
2. **Patch files / scripts / replacement files** — whatever the fix actually applies.
3. **`README.md`** — this file, updated to describe the specific fix.

## Rules

- One fix = one component. Do not bundle unrelated changes.
- Record the exact symptom and reproduction steps.
- Record how to roll back. Every fix must be reversible.
- Update `roms/<rom>/manifest.yaml` to reference the new fix.
- Log verification results to `roms/<rom>/logs/`.
- Mark `status: verified` only after a full boot + functional test passes.
