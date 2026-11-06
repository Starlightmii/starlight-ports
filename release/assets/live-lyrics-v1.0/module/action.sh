#!/system/bin/sh
# KernelSU "Action" button: re-apply the setting and print the current value.
# This is intentionally not a toggle, so an accidental tap cannot disable lyrics.

SETTINGS_BIN="${SETTINGS_BIN:-/system/bin/settings}"
FLAG_FILE="${FLAG_FILE:-/data/adb/starlight_livelyrics_off}"

# Clear a pause flag, if one exists, then apply the setting again.
rm -f "$FLAG_FILE"

"$SETTINGS_BIN" --user 0 put system la_lyric_mode 2 2>/dev/null || \
"$SETTINGS_BIN" put system la_lyric_mode 2 2>/dev/null

CURRENT="$("$SETTINGS_BIN" --user 0 get system la_lyric_mode 2>/dev/null || \
           "$SETTINGS_BIN" get system la_lyric_mode 2>/dev/null)"

echo "Starlight LiveLyrics Enabler"
echo "la_lyric_mode=${CURRENT:-unknown} (2 = enabled, 1 = disabled)"
echo ""
echo "If it is 2, play a song and tap the lyric icon at the top-right of the"
echo "lock-screen media card. To remove the setting, uninstall this module."
