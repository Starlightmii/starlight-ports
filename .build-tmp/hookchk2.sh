#!/system/bin/sh
for f in /data/adb/lspd/log/verbose_*.log; do echo "== $f"; grep -i "assistrestore" "$f" 2>/dev/null | grep -iE "process=android|process=com.android.systemui|systemServer=true|hook_installed" | tail -12; done
