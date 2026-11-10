#!/system/bin/sh
echo ---LSPD---
grep -i assistrestore /data/adb/lspd/log/verbose_*.log 2>/dev/null | tail -15
echo ---LOG---
logcat -d -v brief -s AssistRestore:* 2>/dev/null | tail -20
echo ---NAV---
settings get secure navigation_mode
dumpsys window 2>/dev/null | grep -iE "NavigationBar|mNavigationBar" | head -6
