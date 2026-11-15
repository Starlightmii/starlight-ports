#!/system/bin/sh
# Starlight Integrity Kit - early boot pass.
# Applies the lock-state compatibility props, keeps Oh My Keymint away from
# Google packages, and applies the opt-in certified identity if enabled.
MODDIR=${0%/*}
[ "$MODDIR" = "$0" ] && MODDIR=.
. "$MODDIR/common.sh"

say "--- post-fs-data $(date) ---"
apply_lock_props early
if identity_mode; then
    apply_profile_identity
fi
disable_old_spoof
omk_trust_fix
omk_clean_scoop
omk_safe_restart
exit 0
