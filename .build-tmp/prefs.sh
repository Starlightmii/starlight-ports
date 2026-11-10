#!/system/bin/sh
ls -la /data/data/io.github.andrea_lyz.assistrestore/shared_prefs 2>&1
for f in /data/data/io.github.andrea_lyz.assistrestore/shared_prefs/*; do echo "== $f"; cat "$f"; echo; done 2>/dev/null
