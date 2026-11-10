#!/system/bin/sh
echo "BUILD=$(getprop ro.build.display.id) SDK=$(getprop ro.build.version.sdk)"
echo "---CTS-STATE---"
timeout 5 service list 2>/dev/null | grep -i contextual_search
echo "setting_pkg=$(settings get secure contextual_search_package)"
echo "setting_enabled=$(settings get secure contextual_search_enabled)"
echo "assistant=$(settings get secure assistant)"
echo "vis=$(settings get secure voice_interaction_service)"
echo "touch_gesture=$(settings get secure assist_touch_gesture_enabled)"
echo "---OVERLAY---"
cmd overlay lookup android android:string/config_defaultContextualSearchPackageName 2>&1 | tail -4
echo "---SUI-PERM---"
dumpsys package com.android.systemui 2>/dev/null | grep -i -m6 ACCESS_CONTEXTUAL_SEARCH
echo "---MODULES---"
ls /data/adb/modules
echo "---LSPD---"
ls -d /data/adb/lspd 2>&1
ls -d /data/adb/modules/zygisksu 2>&1
ls -d /data/adb/modules/*lsposed* 2>&1
ls /data/adb/modules/zygisksu/bin 2>&1
echo "---PROPS---"
getprop 2>/dev/null | grep -iE 'zygisk|lsposed' | head -10
echo "---SUI-APK---"
pm path com.android.systemui
dumpsys package com.android.systemui 2>/dev/null | grep -m2 -E 'versionName|versionCode'
echo "---LAUNCHER---"
pm path com.android.launcher 2>&1
