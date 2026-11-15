#!/system/bin/sh
# Starlight Integrity Kit - uninstall.
# Restores the original build identity and the real (orange / unlocked)
# lock-state properties. It deliberately does NOT put the Google packages
# back into Oh My Keymint's scoop: leaving them out is the safety fix that
# keeps the Play Store licensing alive.
MODDIR=${0%/*}
[ "$MODDIR" = "$0" ] && MODDIR=.
. "$MODDIR/common.sh"

say "--- uninstall $(date) ---"
new_backup
getprop > "$BAK/getprop.before-uninstall.txt" 2>/dev/null
apply_real_identity

# Real boot facts (kernel cmdline): unlocked / orange / permissive.
resetprop -n ro.boot.flash.locked 0 2>/dev/null
resetprop -n ro.boot.verifiedbootstate orange 2>/dev/null
resetprop -n vendor.boot.verifiedbootstate orange 2>/dev/null
resetprop -n ro.boot.vbmeta.device_state unlocked 2>/dev/null
resetprop -n vendor.boot.vbmeta.device_state unlocked 2>/dev/null
resetprop -n ro.secureboot.lockstate unlocked 2>/dev/null
resetprop -n sys.oem_unlock_allowed 1 2>/dev/null
resetprop -n ro.boot.selinux permissive 2>/dev/null
say "restored the real lock-state props (unlocked / orange / permissive)"

# The old global-spoof module is disabled, not deleted.
disable_old_spoof

printf 'off\n' > "$MODEFILE" 2>/dev/null
rm -f "$MODEFILE" 2>/dev/null
say "removed the identity-mode flag"
say "kept OMK's Google exclusion (safety)"
say "backups remain in $BAK_ROOT/pi-fix-backup-*"
say "reboot to fully unload the module"
exit 0
