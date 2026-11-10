#!/system/bin/sh
D=/data/data/io.github.andrea_lyz.assistrestore
ls -laR "$D/shared_prefs" "$D/files" 2>&1 | head -40
echo ---PREFS---
for f in "$D"/shared_prefs/* "$D"/files/*; do [ -f "$f" ] || continue; echo "== $f"; cat "$f"; echo; done
