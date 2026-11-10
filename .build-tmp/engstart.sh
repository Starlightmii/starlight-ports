#!/system/bin/sh
# Start exactly one engineer-stub process (live test / module helper).
export PATH=/data/adb/ksu/bin:/system/bin:/vendor/bin:$PATH
JAR=${JAR:-/data/local/tmp/engstub.jar}
LOG=${LOG:-/data/local/tmp/engstub.log}
NAME=com.starlight.engstub.EngStub

# Kill stale instances by command line (never kill all app_process).
for p in /proc/[0-9]*; do
  if tr '\0' ' ' < "$p/cmdline" 2>/dev/null | grep -q "$NAME"; then
    kill "${p#/proc/}" 2>/dev/null
  fi
done
sleep 1

# Derive the runtime boot classpath from a live process.
BC=""
for src in $(pidof zygote64 2>/dev/null) $(pidof system_server 2>/dev/null) 1; do
  [ -r "/proc/$src/environ" ] || continue
  BC=$(tr '\0' '\n' < "/proc/$src/environ" 2>/dev/null | sed -n 's/^BOOTCLASSPATH=//p' | head -1)
  [ -n "$BC" ] && break
done
if [ -z "$BC" ]; then
  echo "no BOOTCLASSPATH source"
  exit 1
fi
export ANDROID_ROOT=/system ANDROID_DATA=/data ANDROID_ART_ROOT=/apex/com.android.art
export BOOTCLASSPATH="$BC"

: > "$LOG"
CLASSPATH="$JAR" nohup setsid app_process64 /system/bin "$NAME" </dev/null >>"$LOG" 2>&1 &
sleep 3
echo "log:"; cat "$LOG"
echo "proc:"; ps -A -o pid,ppid,args 2>/dev/null | grep "$NAME" | grep -v grep
echo "svc:"; timeout 5 service list 2>/dev/null | grep -i "engineer.IEngineer"
