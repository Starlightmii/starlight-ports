#!/system/bin/sh
D=/data/data/io.github.andrea_lyz.assistrestore
ls -laR "$D" 2>/dev/null | head -60
echo ---STRINGS---
find "$D" -type f 2>/dev/null | while read f; do echo "== $f"; strings "$f" 2>/dev/null | grep -iE "target|cts|circle|gesture|assist|enabled|mode" | head -8; done
