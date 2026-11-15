#!/system/bin/sh
# =============================================================================
# Starlight PI — Play Store / licensing recovery
#
# Use when every Play Integrity verdict reads UNEVALUATED (appRecognitionVerdict,
# appLicensingVerdict, playProtectVerdict and deviceRecognitionVerdict all
# missing). That combination is NOT an attestation problem - it means the Play
# Store stopped licensing apps, so Google withholds every verdict.
#
# Causes seen on this device:
#   * the Play Store's data was cleared -> the account has to accept the ToS
#     again and the store has to re-fetch every license;
#   * Oh My Keymint was routing the Play Store's Keystore traffic into its own
#     software keystore, which broke the Play Store's Keystore-backed account
#     tokens ("getKeyEntry failed ... while trying to load key info");
#   * the build identity was temporarily spoofed as another device, which
#     invalidates the account's Play Store device registration;
#   * the checker was sideloaded (installerPackageName=null). Android 13+ only
#     returns PLAY_RECOGNIZED/LICENSED for Play-installed apps; reinstall with
#     `adb install-multiple -r -i com.android.vending ...` (see RECOVERY.md 3a).
#
# Usage (adb shell, phone unlocked):
#     adb shell sh /data/local/tmp/20-licensing-recovery.sh
#
# The script CANNOT enter your Google password. If the store asks you to sign
# in again, do that by hand, then re-run the Play Integrity checker.
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
say() { echo "[licensing] $*"; }

say "backup: $BAK"

# 1. make sure nothing steals the Play Store's keystore traffic
say "stopping Oh My Keymint from serving the Play Store"
OMK=/data/misc/keystore/omk
if [ -d "$OMK" ]; then
    cp -a "$OMK/injector.toml" "$BAK/injector.toml.bak" 2>/dev/null
    sed -i '/"com.android.vending"/d; /"com.google.android.gms"/d; \
            /"com.google.android.gms.unstable"/d; /"com.google.android.gsf"/d' \
        "$OMK/injector.toml" 2>/dev/null
    touch /data/adb/omk/restart.all 2>/dev/null
fi

# 2. Do NOT clear app data. Clearing com.android.vending / com.google.android.gms
#    data wipes the accepted Terms of Service and the store's cached licence
#    records - that is what caused the "everything UNEVALUATED" failure in the
#    first place. Cache-only clears are not consistently supported across ROMs,
#    so this script deliberately skips clearing entirely.
say "skipping Play Store / Play Services data clearing (it destroys licensing state)"

# 3. restart the stack
say "restarting Play Services + Play Store"
am force-stop com.google.android.gms >/dev/null 2>&1
am force-stop com.google.android.gms.unstable >/dev/null 2>&1
am force-stop com.android.vending >/dev/null 2>&1
sleep 3

# 4. open the store so it re-licenses in the background
say "opening the Play Store (accept the ToS if it appears)"
am start -n com.android.vending/com.google.android.finsky.activities.MainActivity >/dev/null 2>&1

cat <<'EOF'

------------------------------------------------------------------
 NEXT STEPS (manual, ~2 minutes)
------------------------------------------------------------------
 1. On the phone: dismiss the "Shortcuts to your favorites" tip.
 2. If the Play Store shows "Terms of Service" -> tap ACCEPT.
 3. If it shows a sign-in prompt, sign in with the Google account that owns
    the installed apps (the store needs a valid token to license them).
 4. Open the Play Store page of the app you want to test, e.g.
        market://details?id=gr.nikolasspyr.integritycheck
    so the store fetches that app's license.
 5. Keep the phone UNLOCKED and open the Play Integrity API Checker, tap
    CHECK. A locked phone makes the request fail with UNEVALUATED because
    the attestation needs a fresh user-auth token.
 6. If it is still UNEVALUATED, check the app's installer attribution:
        adb shell pm list packages -i | grep integritycheck
    It must be installer=com.android.vending. If it is null, reinstall the
    same APKs with:
        adb install-multiple -r -i com.android.vending base.apk split_config.en.apk split_config.xxhdpi.apk
 7. Only if both of the above fail: remove and re-add the Google account
    (Settings > Accounts). That is the only step that needs your password.
 ------------------------------------------------------------------
EOF
exit 0
