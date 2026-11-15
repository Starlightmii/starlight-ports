#!/system/bin/sh
# Starlight Integrity Kit - late boot pass.
# A few vendor/oplus services rewrite ro.boot.* after post-fs-data; re-assert.
MODDIR=${0%/*}
[ "$MODDIR" = "$0" ] && MODDIR=.
. "$MODDIR/common.sh"

say "--- service $(date) ---"
_i=0
while [ "$_i" -lt 8 ]; do
    sleep 15
    apply_lock_props late
    if identity_mode; then
        apply_profile_identity
    fi
    if [ "$_i" -eq 0 ]; then
        omk_trust_fix
        omk_clean_scoop
        omk_safe_restart
    fi
    if [ "$(resetprop ro.boot.flash.locked 2>/dev/null)" = "1" ] && \
       [ "$(resetprop ro.boot.verifiedbootstate 2>/dev/null)" = "green" ]; then
        break
    fi
    _i=$((_i + 1))
done
exit 0
