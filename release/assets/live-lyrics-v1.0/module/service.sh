#!/system/bin/sh
# Starlight LiveLyrics Enabler
# Runs from KernelSU/Magisk late_start service on every boot.
# Idempotent: writing the same value repeatedly is safe.

MODDIR=${0%/*}
SETTINGS_BIN="${SETTINGS_BIN:-/system/bin/settings}"
GETPROP_BIN="${GETPROP_BIN:-/system/bin/getprop}"
FLAG_FILE="${FLAG_FILE:-/data/adb/starlight_livelyrics_off}"

# Optional clean opt-out for advanced users. Removing the module is the normal
# way to stop this script; this flag just lets a user pause it without removal.
[ -f "$FLAG_FILE" ] && exit 0

# Wait for Android to finish booting (max about 120 s). service.sh runs off the
# critical path, so this does not delay or block the boot.
i=0
while [ "$("$GETPROP_BIN" sys.boot_completed 2>/dev/null)" != "1" ] && [ "$i" -lt 60 ]; do
    sleep 2
    i=$((i + 1))
done

# Let SettingsProvider settle on the first boot after installation.
sleep 3

set_mode() {
    "$SETTINGS_BIN" --user 0 put system la_lyric_mode 2 2>/dev/null || \
    "$SETTINGS_BIN" put system la_lyric_mode 2 2>/dev/null
}

get_mode() {
    "$SETTINGS_BIN" --user 0 get system la_lyric_mode 2>/dev/null || \
    "$SETTINGS_BIN" get system la_lyric_mode 2>/dev/null
}

# Retry briefly: on the first boot the settings provider may not be ready yet.
n=0
while [ "$n" -lt 15 ]; do
    set_mode
    [ "$(get_mode)" = "2" ] && break
    n=$((n + 1))
    sleep 2
done

exit 0
