#!/system/bin/sh
# Restore the ROM default (la_lyric_mode=1) when the module is removed.
# Best-effort: uninstall can also run from recovery, where `settings` is absent.

SETTINGS_BIN="${SETTINGS_BIN:-/system/bin/settings}"
FLAG_FILE="${FLAG_FILE:-/data/adb/starlight_livelyrics_off}"
[ -x "$SETTINGS_BIN" ] || exit 0

"$SETTINGS_BIN" --user 0 put system la_lyric_mode 1 2>/dev/null || \
"$SETTINGS_BIN" put system la_lyric_mode 1 2>/dev/null

rm -f "$FLAG_FILE"
exit 0
