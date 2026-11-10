#!/system/bin/sh
echo LSPD_PROC:
ps -A -o PID,NAME 2>/dev/null | grep -iE "lspd|zygisk"
echo MANAGER:
pm path org.lsposed.manager 2>&1
dumpsys package org.lsposed.manager 2>/dev/null | grep -m3 -E "versionName|versionCode"
echo MODULES:
pm list packages 2>/dev/null | grep -iE "lsposed|lunar|rove|assistant"
echo LSPD_CFG:
ls -la /data/adb/lspd 2>&1
ls -la /data/adb/lspd/config 2>&1
echo LOG:
logcat -d -v brief 2>/dev/null | grep -iE "lsposed|zygisk" | tail -15
