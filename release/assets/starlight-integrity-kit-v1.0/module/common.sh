#!/system/bin/sh
# Starlight Integrity Kit - shared functions
# Sourced by post-fs-data.sh, service.sh, action.sh and uninstall.sh.
# POSIX sh only. No bashisms.

KIT_ID=starlight_integrity_kit
KIT_DIR=${MODDIR:-/data/adb/modules/$KIT_ID}
KIT_STATE=/data/adb/$KIT_ID
MODEFILE=$KIT_STATE/identity_mode
LOG=$KIT_STATE/apply.log
OMK=/data/misc/keystore/omk
OMK_STATE=/data/adb/omk
TRICKY=/data/adb/tricky_store
PROFILE=$OMK/data/pif_fingerprint.json
DUCKD=/data/adb/modules/duck-toolbox/bin/duckd
BAK_ROOT=/data/local/tmp

export PATH=/data/adb/ksu/bin:/data/adb/ap/bin:/data/adb/magisk:/system/bin:$PATH

mkdir -p "$KIT_STATE" 2>/dev/null
: >> "$LOG" 2>/dev/null

say() {
    printf '%s\n' "$*"
    printf '%s\n' "$*" >> "$LOG" 2>/dev/null
}
warn() { say "WARNING: $*"; }
err()  { say "ERROR: $*"; }

new_backup() {
    BAK="$BAK_ROOT/pi-fix-backup-$(date +%Y%m%d-%H%M%S)"
    mkdir -p "$BAK" 2>/dev/null
    say "backup: $BAK"
}

backup_file() {
    [ -e "$1" ] || return 0
    [ -n "${BAK:-}" ] || new_backup
    cp -a "$1" "$BAK/$(basename "$1").bak" 2>/dev/null && \
        say "  backed up $1"
}

# ---------------------------------------------------------------------------
# Properties
# ---------------------------------------------------------------------------
REAL_FP="Redmi/sweetin/sweetin:17/CP2A.260605.016/Starlight-test:user/release-keys"

prop_set() {
    [ -n "$2" ] || return 0
    [ "$(resetprop "$1" 2>/dev/null)" = "$2" ] && return 0
    resetprop -n "$1" "$2" 2>/dev/null
}

apply_lock_props() {
    _pass=${1:-now}
    say "lock-state props ($_pass)"
    prop_set ro.boot.selinux enforcing
    prop_set ro.boot.flash.locked 1
    prop_set ro.boot.verifiedbootstate green
    prop_set vendor.boot.verifiedbootstate green
    prop_set ro.boot.vbmeta.device_state locked
    prop_set vendor.boot.vbmeta.device_state locked
    prop_set ro.boot.veritymode enforcing
    prop_set ro.secureboot.lockstate locked
    prop_set sys.oem_unlock_allowed 0
    prop_set ro.debuggable 0
    prop_set ro.force.debuggable 0
    prop_set ro.secure 1
    prop_set ro.adb.secure 1
    prop_set ro.build.type user
    prop_set ro.build.tags release-keys
}

apply_real_identity() {
    say "restoring the ROM build identity"
    prop_set ro.build.fingerprint "$REAL_FP"
    prop_set ro.build.id "CP2A.260605.016"
    prop_set ro.build.version.incremental "Starlight-test"
    prop_set ro.build.version.release "17"
    prop_set ro.build.version.security_patch "2026-09-01"
    prop_set ro.vendor.build.security_patch "2026-09-01"
    prop_set ro.product.brand "Redmi"
    prop_set ro.product.device "sweetin"
    prop_set ro.product.manufacturer "Xiaomi"
    prop_set ro.product.model "M2101K6I"
    prop_set ro.product.name "sweetin"
    prop_set ro.build.type user
    prop_set ro.build.tags release-keys
}

# Read a field from the canonical one-line OMK PIF profile.
pval() {
    [ -f "$PROFILE" ] || return 1
    tr ',' '\n' < "$PROFILE" | \
        sed -n "s/.*\"$1\":\"\\([^\"]*\\)\".*/\\1/p" | head -1
}

apply_profile_identity() {
    _fp=$(pval fingerprint)
    [ -n "$_fp" ] || { warn "no OMK PIF profile at $PROFILE"; return 1; }
    say "applying certified identity from $PROFILE"
    prop_set ro.build.fingerprint            "$_fp"
    prop_set ro.build.id                     "$(pval id)"
    prop_set ro.build.version.incremental    "$(pval incremental)"
    prop_set ro.build.version.security_patch "$(pval security_patch)"
    prop_set ro.product.brand                "$(pval brand)"
    prop_set ro.product.device               "$(pval device)"
    prop_set ro.product.manufacturer         "$(pval manufacturer)"
    prop_set ro.product.model                "$(pval model)"
    prop_set ro.product.name                 "$(pval product)"
}

identity_mode() {
    [ -f "$MODEFILE" ] && grep -qx on "$MODEFILE" 2>/dev/null
}

identity_on() {
    [ "${1:-}" = "--yes" ] || {
        printf 'Type YES to enable the certified-identity spoof: '
        read -r _a || return 1
        [ "$_a" = YES ] || { say "cancelled"; return 0; }
    }
    [ -f "$PROFILE" ] || { err "no OMK PIF profile at $PROFILE"; return 1; }
    new_backup
    getprop > "$BAK/getprop.before-identity.txt" 2>/dev/null
    apply_profile_identity || return 1
    printf 'on\n' > "$MODEFILE"
    warn "identity mode is ON. This breaks the Play Store on this ROM."
    warn "Use 'disable certified identity' to restore the ROM identity."
    say "reboot recommended so GMS starts with one consistent identity"
}

identity_off() {
    new_backup
    getprop > "$BAK/getprop.before-identity.txt" 2>/dev/null
    apply_real_identity
    printf 'off\n' > "$MODEFILE"
    say "identity mode is OFF; the ROM identity is restored"
}

# ---------------------------------------------------------------------------
# Oh My Keymint safety
# ---------------------------------------------------------------------------
omk_trust_fix() {
    _c="$OMK/config.toml"
    [ -f "$_c" ] || { warn "OMK config.toml not present; skipping trust fix"; return 0; }
    if grep -q '^device_locked = true' "$_c" 2>/dev/null && \
       grep -q '^verified_boot_state = true' "$_c" 2>/dev/null && \
       grep -q '^boot_patchlevel = "auto"' "$_c" 2>/dev/null && \
       grep -q '^vendor_patchlevel = "auto"' "$_c" 2>/dev/null && \
       grep -q '^os_patchlevel = "auto"' "$_c" 2>/dev/null && \
       grep -q '^security_patch = "auto"' "$_c" 2>/dev/null && \
       grep -q '^os_version = "auto"' "$_c" 2>/dev/null && \
       grep -q '^vb_hash = "auto"' "$_c" 2>/dev/null && \
       grep -q '^vb_key = "auto"' "$_c" 2>/dev/null; then
        return 0
    fi
    backup_file "$_c"
    _owner=$(stat -c '%u:%g' "$_c" 2>/dev/null || echo 1017:1017)
    cat > "$BAK/trust.block" <<'EOF'
[trust]
boot_patchlevel = "auto"
device_locked = true
os_patchlevel = "auto"
os_version = "auto"
security_patch = "auto"
vb_hash = "auto"
vb_key = "auto"
vendor_patchlevel = "auto"
verified_boot_state = true
EOF
    awk -v blockfile="$BAK/trust.block" '
        BEGIN { while ((getline line < blockfile) > 0) block = block line "\n"; seen=0 }
        /^[[:space:]]*\[trust\][[:space:]]*$/ {
            printf "%s", block
            seen=1; skip=1; next
        }
        skip {
            if ($0 ~ /^[[:space:]]*\[/) { skip=0; print }
            next
        }
        { print }
        END { if (!seen) printf "\n%s", block }
    ' "$_c" > "$BAK/config.toml.new"
    cp -f "$BAK/config.toml.new" "$_c"
    chmod 600 "$_c" 2>/dev/null
    chown "$_owner" "$_c" 2>/dev/null
    say "OMK [trust] device_locked=true verified_boot_state=true"
}

# Remove every Google/Play package from OMK's routing list and keep the TOML
# valid. Never leave a single Google package in `scoop`: OMK serving vending or
# GMS is what destroyed the Play Store licensing on this ROM.
omk_clean_scoop() {
    _f="$OMK/injector.toml"
    [ -f "$_f" ] || { warn "OMK injector.toml not present; skipping scoop fix"; return 0; }
    if ! grep -qE '"(com\.android\.vending|com\.google\.android\.gms|com\.google\.android\.gms\.unstable|com\.google\.android\.gsf)"' "$_f" 2>/dev/null; then
        return 0
    fi
    backup_file "$_f"
    _owner=$(stat -c '%u:%g' "$_f" 2>/dev/null || echo 1017:1017)

    _entries=$(awk '
        /^[[:space:]]*scoop[[:space:]]*=/ {
            if ($0 ~ /\]/) { print $0; inb=0; next }
            inb=1; next
        }
        inb {
            if ($0 ~ /^[[:space:]]*\]/) { inb=0; next }
            print
        }
    ' "$_f" | sed -n 's/.*"\([^"]*\)".*/\1/p')

    _keep=""
    for _e in $_entries; do
        case "$_e" in
            com.android.vending|com.google.android.gms|com.google.android.gms.unstable|com.google.android.gsf)
                continue ;;
        esac
        _keep="$_keep$_e
"
    done

    {
        printf 'scoop = [\n'
        printf '%s' "$_keep" | while IFS= read -r _e; do
            [ -n "$_e" ] && printf '  "%s",\n' "$_e"
        done
        printf ']\n'
    } > "$BAK/scoop.block"

    awk -v blockfile="$BAK/scoop.block" '
        BEGIN { while ((getline line < blockfile) > 0) block = block line "\n" }
        /^[[:space:]]*scoop[[:space:]]*=/ {
            printf "%s", block
            if ($0 ~ /\]/) next
            inb=1; next
        }
        inb { if ($0 ~ /^[[:space:]]*\]/) inb=0; next }
        { print }
    ' "$_f" > "$BAK/injector.toml.new"

    if ! grep -q '^[[:space:]]*scoop[[:space:]]*=' "$BAK/injector.toml.new"; then
        printf '\nscoop = []\n' >> "$BAK/injector.toml.new"
    fi

    if awk '/^[[:space:]]*scoop[[:space:]]*=/{inb=1} inb{print} inb&&/\]/{exit}' \
            "$BAK/injector.toml.new" | \
            grep -qE '"(com\.android\.vending|com\.google\.android\.gms|com\.google\.android\.gsf)"'; then
        warn "scoop rewrite failed; installing a conservative injector.toml"
        cat > "$BAK/injector.toml.new" <<'EOF'
version = 1
scoop = []
[main]
enabled = true
log_level = "info"
[filter]
enabled = true
deny_packages = []
block_android_package = true
allow_unknown_package = false
[intercept]
get_security_level = true
get_key_entry = true
update_subcomponent = true
list_entries = true
delete_key = true
grant = true
ungrant = true
get_number_of_entries = true
list_entries_batched = true
get_supplementary_attestation_info = true
EOF
    fi

    cp -f "$BAK/injector.toml.new" "$_f"
    chmod 600 "$_f" 2>/dev/null
    chown "$_owner" "$_f" 2>/dev/null
    say "OMK scoop cleaned (Google packages are never routed)"
}

omk_safe_restart() {
    _f="$OMK/injector.toml"
    if [ -f "$_f" ] && grep -qE '"(com\.android\.vending|com\.google\.android\.gms|com\.google\.android\.gms\.unstable|com\.google\.android\.gsf)"' "$_f"; then
        warn "refusing to restart OMK: a Google package is still in injector.toml"
        return 1
    fi
    touch "$OMK_STATE/restart.all" 2>/dev/null && say "OMK restart requested"
}

disable_old_spoof() {
    for _m in /data/adb/modules/starlight_pi_spoof /data/adb/modules_update/starlight_pi_spoof; do
        [ -d "$_m" ] && touch "$_m/disable" 2>/dev/null && \
            say "disabled old global-spoof module at $_m"
    done
}

disable_duck_pi() {
    _e=/data/adb/modules/duck-toolbox/play-integrity/pif-engine
    if [ -x "$_e" ]; then
        echo '{"enabled":false}' | "$_e" request options >/dev/null 2>&1 && \
            say "Duck ToolBox Play Integrity engine disabled"
    fi
}

# ---------------------------------------------------------------------------
# Keybox
# ---------------------------------------------------------------------------
keybox_validate() {
    _p="$1"
    [ -f "$_p" ] || { err "keybox not found: $_p"; return 1; }
    if [ -x "$DUCKD" ]; then
        _o=$("$DUCKD" tee-attestation keybox "$_p" 2>/dev/null)
        if printf '%s' "$_o" | grep -q '"ok":true'; then
            say "duckd: keybox is structurally valid (trust_verified may still be false)"
            return 0
        fi
        _msg=$(printf '%s' "$_o" | sed -n 's/.*"message":"\([^"]*\)".*/\1/p')
        err "duckd rejected keybox: ${_msg:-unknown error}"
        return 1
    fi
    if grep -q '<AndroidAttestation>' "$_p" 2>/dev/null && \
       grep -q '<Keybox ' "$_p" 2>/dev/null && \
       grep -q 'BEGIN .*PRIVATE KEY' "$_p" 2>/dev/null && \
       grep -q 'BEGIN CERTIFICATE' "$_p" 2>/dev/null; then
        say "structural keybox check passed (duckd not installed)"
        return 0
    fi
    err "not a keybox XML"
    return 1
}

keybox_expiry() {
    _sha=$(sha256sum "$1" 2>/dev/null | awk '{print $1}')
    case "$_sha" in
        f005433f37832860a9a595627e19d04511c6378b2725b7f88fda912f648c59eb)
            echo "2026-11-25" ;;
        *)  echo "unknown (openssl is not present on the device)" ;;
    esac
}

keybox_install() {
    _src="$1"
    [ -n "$_src" ] || { err "usage: keybox <path>"; return 1; }
    keybox_validate "$_src" || return 1
    new_backup
    _omk_owner=$(stat -c '%u:%g' "$OMK/keybox.xml" 2>/dev/null || echo 1017:1017)
    backup_file "$OMK/keybox.xml"
    [ -d "$TRICKY" ] && backup_file "$TRICKY/keybox.xml"
    cp -f "$_src" "$OMK/keybox.xml" || { err "copy to OMK failed"; return 1; }
    chmod 600 "$OMK/keybox.xml" 2>/dev/null
    chown "$_omk_owner" "$OMK/keybox.xml" 2>/dev/null
    if [ -d "$TRICKY" ]; then
        cp -f "$_src" "$TRICKY/keybox.xml" 2>/dev/null && \
            chmod 600 "$TRICKY/keybox.xml" 2>/dev/null && \
            chown 0:0 "$TRICKY/keybox.xml" 2>/dev/null && \
            say "also installed into $TRICKY/keybox.xml"
    fi
    say "keybox sha256: $(sha256sum "$_src" | awk '{print $1}')"
    say "keybox leaf expiry (known table): $(keybox_expiry "$_src")"
    omk_safe_restart
}

# ---------------------------------------------------------------------------
# Cleanup / re-license
# ---------------------------------------------------------------------------
clean_gms_props() {
    _f=/data/system/gms_certified_props.json
    [ -e "$_f" ] || { say "no stale gms_certified_props.json"; return 0; }
    new_backup
    backup_file "$_f"
    mv "$_f" "$BAK/gms_certified_props.json.moved" 2>/dev/null && \
        say "moved stale gms_certified_props.json out of the way"
}

relicense_store() {
    say "restarting the Play Store (data and account are NOT touched)"
    am force-stop com.android.vending 2>/dev/null
    rm -rf /data/data/com.android.vending/cache/* 2>/dev/null
    rm -rf /data/data/com.android.vending/code_cache/* 2>/dev/null
    am start -n com.android.vending/com.google.android.finsky.activities.MainActivity \
        >/dev/null 2>&1 || \
        monkey -p com.android.vending -c android.intent.category.LAUNCHER 1 \
        >/dev/null 2>&1
    say "Store cache cleared. Open the store; sign in yourself only if it asks."
    say "Never run: keymint --webui-clear-gms-cache (it destroys licensing state)."
}

# ---------------------------------------------------------------------------
# Baseline / status
# ---------------------------------------------------------------------------
apply_baseline() {
    new_backup
    getprop > "$BAK/getprop.before.txt" 2>/dev/null
    if identity_mode; then
        apply_profile_identity
    else
        apply_real_identity
    fi
    apply_lock_props early
    disable_old_spoof
    omk_trust_fix
    omk_clean_scoop
    omk_safe_restart
    disable_duck_pi
    am force-stop com.google.android.gms 2>/dev/null
    am force-stop com.android.vending 2>/dev/null
    say "baseline applied. Open the Play Store once, then run the checker unlocked."
}

status() {
    say "=== Starlight Integrity Kit status ==="
    say "mode: $(identity_mode && echo 'certified identity ON (spoof)' || echo 'OFF (ROM identity)')"
    say "fingerprint: $(getprop ro.build.fingerprint)"
    say "model: $(getprop ro.product.model) / $(getprop ro.product.manufacturer)"
    say "lock props: flash.locked=$(getprop ro.boot.flash.locked) verified=$(getprop ro.boot.verifiedbootstate) vbmeta=$(getprop ro.boot.vbmeta.device_state) verity=$(getprop ro.boot.veritymode)"
    say "real cmdline: $(tr ' ' '\n' < /proc/cmdline | grep -E 'verifiedbootstate|selinux|flash.locked' | tr '\n' ' ')"

    say "--- OMK ---"
    if [ -f "$OMK/injector.toml" ]; then
        awk '/^[[:space:]]*scoop[[:space:]]*=/{inb=1} inb{print} inb&&/\]/{exit}' \
            "$OMK/injector.toml" | sed 's/^/  /'
        if grep -qE '"(com\.android\.vending|com\.google\.android\.gms|com\.google\.android\.gms\.unstable|com\.google\.android\.gsf)"' "$OMK/injector.toml"; then
            say "  !! GOOGLE PACKAGE PRESENT IN SCOOP - run 'repair OMK' now"
        else
            say "  Google packages: not routed (good)"
        fi
    else
        say "  injector.toml not present"
    fi
    if [ -f "$OMK/config.toml" ]; then
        awk '/^\[trust\]/{inb=1} inb{print} inb&&/^\[/&&!/^\[trust\]/{exit}' \
            "$OMK/config.toml" | sed 's/^/  /'
    fi

    say "--- keybox ---"
    for _k in "$OMK/keybox.xml" "$TRICKY/keybox.xml"; do
        [ -f "$_k" ] || continue
        say "  $_k"
        say "    sha256: $(sha256sum "$_k" | awk '{print $1}')"
        say "    expiry: $(keybox_expiry "$_k")"
        if [ -x "$DUCKD" ]; then
            _o=$("$DUCKD" tee-attestation keybox "$_k" 2>/dev/null)
            if printf '%s' "$_o" | grep -q '"ok":true'; then
                say "    duckd: ok (trust_verified=$(printf '%s' "$_o" | sed -n 's/.*"trust_verified":\([a-z]*\).*/\1/p'))"
            else
                say "    duckd: INVALID"
            fi
        fi
    done

    say "--- checker ---"
    _ci=$(pm list packages -i 2>/dev/null | grep 'gr.nikolasspyr.integritycheck')
    say "  ${_ci:-not installed}"
    printf '%s' "$_ci" | grep -q 'installer=com.android.vending' || \
        say "  !! not Play-attributed: reinstall with -i com.android.vending"

    say "--- last copied verdict (may be stale) ---"
    if [ -f /sdcard/Download/Nekogram/integrity/clipboard.txt ]; then
        _flat=$(tr '\n' ' ' < /sdcard/Download/Nekogram/integrity/clipboard.txt | tr -s ' ')
        printf '%s' "$_flat" | grep -o '"appRecognitionVerdict": "[^"]*"' | sed 's/^/  /'
        printf '%s' "$_flat" | grep -o '"appLicensingVerdict": "[^"]*"' | sed 's/^/  /'
        printf '%s' "$_flat" | grep -o '"playProtectVerdict": "[^"]*"' | sed 's/^/  /'
        printf '%s' "$_flat" | grep -o '"deviceRecognitionVerdict": \[[^]]*\]' | sed 's/^/  /'
        printf '%s' "$_flat" | grep -o '"appsDetected": \[[^]]*\]' | sed 's/^/  /'
        printf '%s' "$_flat" | grep -o '"timestampMillis": "[0-9]*"' | sed 's/^/  /'
        say "  (open Play Integrity API Checker for a fresh result)"
    else
        say "  no clipboard.txt; open Play Integrity API Checker"
    fi

    if [ -f /data/system/gms_certified_props.json ] && \
       ! grep -q 'Redmi/sweetin' /data/system/gms_certified_props.json 2>/dev/null; then
        say "--- stale gms_certified_props.json (foreign identity) ---"
        say "  run 'clean stale GMS props' to back it up and move it away"
    fi
}
