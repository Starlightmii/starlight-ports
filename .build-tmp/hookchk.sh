#!/system/bin/sh
for f in /data/adb/lspd/log/verbose_*.log; do echo "== $f"; grep -i "assistrestore" "$f" 2>/dev/null | grep -iE "systemui|systemServer=true|system_server" | tail -8; done
echo ---SUI_PID---
pidof com.android.systemui
echo ---FRAME---
dumpsys window windows 2>/dev/null | grep -A25 "Window #.*NavigationBar_displayId_0" | grep -E "mFrame|frame=|mTouchableInsets|mGivenContentInsets|mAttrs" | head -10
