#!/system/bin/sh
for p in /proc/[0-9]*; do c=$(tr "\\0" " " < $p/cmdline 2>/dev/null); case "$c" in app_process64*com.starlight.engstub.EngStub*) echo PID=${p#/proc/}; cat $p/cgroup; echo OOM=$(cat $p/oom_score_adj);; esac; done
echo SECURE=$(getprop ro.adb.secure)
echo ZYGOTE=$(pidof zygote64)
echo SYSTEM=$(pidof system_server)
