# shellcheck shell=ash
# Starlight Integrity Kit - installer hook (KernelSU / APatch / Magisk).
# The module is safe by default: it never enables the certified-identity spoof.

ui_print " "
ui_print "=============================================="
ui_print " Starlight Integrity Kit v1.0"
ui_print "=============================================="
ui_print " One module for the whole Play Integrity stack."
ui_print " Default mode: real ROM identity (spoof OFF)."

if [ "$KSU" = "true" ]; then
    ui_print "- Root manager: KernelSU $KSU_VER ($KSU_VER_CODE)"
elif [ "$APATCH" = "true" ]; then
    ui_print "- Root manager: APatch $APATCH_VER"
elif [ -n "$MAGISK_VER_CODE" ]; then
    ui_print "- Root manager: Magisk $MAGISK_VER"
else
    abort "! Install from KernelSU, APatch or Magisk, not from recovery."
fi

if [ -z "$API" ] || [ "$API" -lt 30 ]; then
    abort "! Android 11 (API 30) or newer is required."
fi
ui_print "- Device API: $API"

set_perm_recursive "$MODPATH" 0 0 0755 0644
for f in post-fs-data.sh service.sh action.sh uninstall.sh common.sh; do
    [ -f "$MODPATH/$f" ] && set_perm "$MODPATH/$f" 0 0 0755
done

mkdir -p /data/adb/starlight_integrity_kit
chmod 0755 /data/adb/starlight_integrity_kit 2>/dev/null

ui_print "- Applying the safe baseline now (props + OMK hygiene)"
if sh "$MODPATH/action.sh" apply >/dev/null 2>&1; then
    ui_print "- Baseline applied. Log: /data/adb/starlight_integrity_kit/apply.log"
else
    ui_print "! Baseline action returned an error; it will retry on boot."
fi

ui_print "- Reboot once to activate post-fs-data.sh and service.sh."
ui_print "- Then open the Play Store once so it re-licenses."
ui_print "- Finally unlock the phone and run Play Integrity API Checker."
ui_print "- Module action (KernelSU) opens the menu: status, keybox, OMK repair."
ui_print "- Never run keymint --webui-clear-gms-cache."
