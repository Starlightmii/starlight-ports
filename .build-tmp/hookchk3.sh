#!/system/bin/sh
for f in /data/adb/lspd/log/verbose_*.log; do echo "== $f"; grep -i "assistrestore" "$f" 2>/dev/null | grep -E "\(android\)|systemServer=true|cts_|ContextualSearchManagerService|SystemServer|hook_installed target=com.android.server" | tail -20; done
