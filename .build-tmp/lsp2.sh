#!/system/bin/sh
cat /data/adb/modules/zygisk_lsposed/module.prop 2>&1
echo ---
cat /data/adb/modules/zygisksu/module.prop 2>&1
echo ---LOGTAIL---
ls -la /data/adb/lspd/log 2>&1
for f in /data/adb/lspd/log/*; do echo "== $f"; tail -5 "$f"; done 2>/dev/null | head -40
