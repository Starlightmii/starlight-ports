#!/system/bin/sh
# Starlight Integrity Kit - KernelSU module action.
# Interactive menu when a terminal is attached; one-shot subcommands otherwise:
#   action.sh status | apply | identity-on | identity-off | keybox <path>
#   action.sh repair-omk | clean-gms-props | relicense
MODDIR=${0%/*}
[ "$MODDIR" = "$0" ] && MODDIR=.
. "$MODDIR/common.sh"

pause() {
    [ -t 0 ] || return 0
    printf '\nPress Enter to continue...'
    read -r _pause || true
}

menu() {
    while :; do
        printf '\n'
        printf '==============================================\n'
        printf ' Starlight Integrity Kit v1.0\n'
        printf '==============================================\n'
        printf ' 1) Status report\n'
        printf ' 2) Apply baseline now\n'
        printf ' 3) Enable certified identity (WARNING: breaks Play Store)\n'
        printf ' 4) Disable certified identity (restore ROM identity)\n'
        printf ' 5) Install / replace keybox from a file path\n'
        printf ' 6) Repair Oh My Keymint (trust + remove Google from scoop)\n'
        printf ' 7) Clean stale gms_certified_props.json (backup first)\n'
        printf ' 8) Re-license the Play Store (store cache only)\n'
        printf ' 9) Help / honest limits\n'
        printf ' 0) Exit\n'
        printf 'Choose: '
        read -r _c || exit 0
        case "$_c" in
            1) status ;;
            2) apply_baseline ;;
            3) identity_on ;;
            4) identity_off ;;
            5)
                printf 'Keybox path [/data/local/tmp/keybox.xml]: '
                read -r _p || _p=""
                [ -n "$_p" ] || _p=/data/local/tmp/keybox.xml
                keybox_install "$_p"
                ;;
            6) omk_trust_fix; omk_clean_scoop; omk_safe_restart ;;
            7) clean_gms_props ;;
            8) relicense_store ;;
            9)
                printf '\nHonest limits:\n'
                printf '  - MEETS_BASIC_INTEGRITY is the ceiling on this unlocked port.\n'
                printf '  - DEVICE/STRONG need a locked bootloader and a certified image.\n'
                printf '  - The ro.boot.* props are compatibility values, not hardware proof.\n'
                printf '  - The store will still say "lock the bootloader"; that is expected.\n'
                printf '  - Never clear GMS data or run keymint --webui-clear-gms-cache.\n'
                ;;
            0|q|Q) exit 0 ;;
            *) printf 'Unknown choice.\n' ;;
        esac
        pause
    done
}

case "${1:-}" in
    status)        status ;;
    apply)         apply_baseline ;;
    identity-on)   identity_on --yes ;;
    identity-off)  identity_off ;;
    keybox)        shift; keybox_install "${1:-}" ;;
    repair-omk)    omk_trust_fix; omk_clean_scoop; omk_safe_restart ;;
    clean-gms-props) clean_gms_props ;;
    relicense)     relicense_store ;;
    *)
        if [ -t 0 ]; then
            menu
        else
            status
            printf '\nNon-interactive: run action.sh with status|apply|identity-on|identity-off|keybox <path>|repair-omk|clean-gms-props|relicense\n'
        fi
        ;;
esac
exit 0
