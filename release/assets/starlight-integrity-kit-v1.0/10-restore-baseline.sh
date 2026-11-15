#!/system/bin/sh
# =============================================================================
# Starlight PI — restore the known-good Play Integrity baseline
#
# Target: sweetin / Starlight ColorOS 17 (CP2A.260605.016)
# Run as root from adb shell, or from a recovery shell:
#     adb shell sh /data/local/tmp/10-restore-baseline.sh
#
# WHAT THIS DOES (all reversible, no reboot needed):
#   1. Restores the ROM's real build identity (no global fingerprint spoof).
#   2. Re-applies the OEM lock-state **compatibility props**
#      (ro.boot.flash.locked=1, verifiedbootstate=green, vbmeta.device_state=
#      locked, veritymode=enforcing, secureboot.lockstate=locked,
#      sys.oem_unlock_allowed=0, ro.boot.selinux=enforcing).
#      These are property-store values only. Play Integrity's DEVICE verdict
#      reads the real hardware (/proc/cmdline: androidboot.verifiedbootstate=
#      orange) and the TEE attestation; these props cannot make an unlocked
#      bootloader pass DEVICE. They still help property-based legacy checks.
#   3. Makes sure Oh My Keymint does NOT route Google Play Services or the
#      Play Store through its software keystore (that breaks the Play Store's
#      Keystore-backed account tokens -> licensing fails -> every Play
#      Integrity verdict becomes UNEVALUATED).
#   4. Makes sure Duck ToolBox's Play Integrity engine is disabled (it
#      conflicts with a standalone PIF module and its "full" mode cannot work
#      on KernelSU-Next without a metamodule).
#   5. Removes the global fingerprint-spoof module if present.
#
# BACKUP: everything it touches is copied to
#         /data/local/tmp/pi-fix-backup-<timestamp>/
# =============================================================================
# NOTE 2026-11-14: the shipped kit is now ONE KernelSU module:
#   release/starlight-integrity-kit-v1.0/Starlight-Integrity-Kit-v1.0.zip
#   (id starlight_integrity_kit, source roms/coloros/fixes/starlight-integrity-kit/)
# This standalone script is kept as a recovery path; it performs the same
# safe baseline and never relies on the module being installed.

set -u
TS="$(date +%Y%m%d-%H%M%S)"
BAK="/data/local/tmp/pi-fix-backup-$TS"
mkdir -p "$BAK"
export PATH=/data/adb/ksu/bin:/data/adb/ap/bin:/data/adb/magisk:/system/bin:$PATH

say() { echo "[baseline] $*"; }
say "backup dir: $BAK"

# --- 1. back up what we are about to touch -------------------------------
for f in /data/misc/keystore/omk/config.toml /data/misc/keystore/omk/injector.toml \
         /data/misc/keystore/omk/keybox.xml /data/adb/play-integrity/custom.pif.json; do
    [ -e "$f" ] && cp -a "$f" "$BAK/$(basename "$f").bak" 2>/dev/null
done
getprop > "$BAK/getprop.before.txt" 2>/dev/null

# --- 2. real build identity ----------------------------------------------
say "restoring the ROM build identity"
resetprop -n ro.build.fingerprint "Redmi/sweetin/sweetin:17/CP2A.260605.016/Starlight-test:user/release-keys"
resetprop -n ro.build.id "CP2A.260605.016"
resetprop -n ro.build.version.incremental "Starlight-test"
resetprop -n ro.build.version.release "17"
resetprop -n ro.build.version.sdk "37"
resetprop -n ro.build.version.security_patch "2026-09-01"
resetprop -n ro.vendor.build.security_patch "2026-09-01"
resetprop -n ro.product.brand "Redmi"
resetprop -n ro.product.device "sweetin"
resetprop -n ro.product.manufacturer "Xiaomi"
resetprop -n ro.product.model "M2101K6I"
resetprop -n ro.product.name "sweetin"

# --- 3. OEM lock-state properties ----------------------------------------
say "re-applying the OEM lock-state properties"
resetprop -n ro.boot.selinux enforcing
resetprop -n ro.boot.flash.locked 1
resetprop -n ro.boot.verifiedbootstate green
resetprop -n vendor.boot.verifiedbootstate green
resetprop -n ro.boot.vbmeta.device_state locked
resetprop -n vendor.boot.vbmeta.device_state locked
resetprop -n ro.boot.veritymode enforcing
resetprop -n ro.secureboot.lockstate locked
resetprop -n sys.oem_unlock_allowed 0
resetprop -n ro.debuggable 0
resetprop -n ro.force.debuggable 0
resetprop -n ro.secure 1
resetprop -n ro.adb.secure 1
resetprop -n ro.build.type user
resetprop -n ro.build.tags release-keys

# --- 4. keep OMK away from Google Play Services / Play Store --------------
OMK=/data/misc/keystore/omk
if [ -d "$OMK" ]; then
    say "removing google/play-store from OMK's routing list"
    cp -a "$OMK/injector.toml" "$BAK/injector.toml.bak2" 2>/dev/null
    sed -i '/"com.google.android.gms"/d; /"com.google.android.gsf"/d; /"com.android.vending"/d; /"com.google.android.gms.unstable"/d' \
        "$OMK/injector.toml" 2>/dev/null
    chmod 0600 "$OMK/injector.toml" 2>/dev/null
    chown 1017:1017 "$OMK/injector.toml" 2>/dev/null
    touch /data/adb/omk/restart.all 2>/dev/null
fi

# --- 5. disable Duck's Play Integrity engine (conflicts with PIF) --------
if [ -x /data/adb/modules/duck-toolbox/play-integrity/pif-engine ]; then
    say "disabling Duck ToolBox's Play Integrity engine"
    echo '{"enabled":false}' | /data/adb/modules/duck-toolbox/play-integrity/pif-engine request options >/dev/null 2>&1
fi

# --- 6. remove the global fingerprint-spoof module -----------------------
if [ -d /data/adb/modules/starlight_pi_spoof ]; then
    say "removing the global fingerprint-spoof module"
    touch /data/adb/modules/starlight_pi_spoof/disable 2>/dev/null
    for p in $(ls /proc 2>/dev/null | grep -E "^[0-9]+$"); do
        c=$(cat /proc/$p/cmdline 2>/dev/null | tr "\0" " ")
        case "$c" in *starlight_pi_spoof*) kill -9 "$p" 2>/dev/null ;; esac
    done
fi

# --- 7. restart the Google stack so it re-reads the identity -------------
say "restarting Play Services / Play Store"
am force-stop com.google.android.gms 2>/dev/null
am force-stop com.google.android.gms.unstable 2>/dev/null
am force-stop com.android.vending 2>/dev/null

# --- report ---------------------------------------------------------------
say "---- resulting state ----"
for p in ro.build.fingerprint ro.product.model ro.product.manufacturer \
         ro.build.version.security_patch ro.boot.selinux ro.boot.flash.locked \
         ro.boot.verifiedbootstate ro.boot.vbmeta.device_state ro.boot.veritymode \
         ro.secureboot.lockstate sys.oem_unlock_allowed; do
    printf "  %-34s = %s\n" "$p" "$(getprop "$p")"
done
say "OMK routing (should be empty for google packages):"
grep -A8 'scoop' "$OMK/injector.toml" 2>/dev/null | sed 's/^/    /'
# --- warnings -------------------------------------------------------------
if ! pm list packages -i 2>/dev/null | grep -qE 'gr\.nikolasspyr\.integritycheck[[:space:]]+installer=com\.android\.vending'; then
    say "WARNING: the checker is not Play-attributed (installer != com.android.vending)."
    say "         See RECOVERY.md 3a; reinstall with -i com.android.vending."
fi
if [ -f /data/system/gms_certified_props.json ] && \
   ! grep -q 'Redmi/sweetin' /data/system/gms_certified_props.json 2>/dev/null; then
    say "NOTE: stale gms_certified_props.json holds a foreign identity; see RECOVERY.md 3c."
fi

say "done. Now open the Play Store once so it re-licenses, then run the"
say "Play Integrity API Checker with the phone UNLOCKED."
say "Do NOT run 'keymint --webui-clear-gms-cache' - it destroys store licensing state."
exit 0
