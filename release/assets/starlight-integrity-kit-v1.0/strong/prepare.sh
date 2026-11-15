#!/system/bin/sh
# Run ON THE DEVICE as root BEFORE flashing the STRONG zip.
# Backs up the Tricky Store config and disables Oh My Keymint (both hook
# keystore; running both is unsupported). Reversible with rollback.sh.
set -u
BAK=/data/local/tmp/tricky-strong-backup-$(date +%Y%m%d-%H%M%S)
mkdir -p "$BAK"
cp -a /data/adb/tricky_store "$BAK/" 2>/dev/null
cp -a /data/misc/keystore/omk "$BAK/" 2>/dev/null
touch /data/adb/modules/oh_my_keymint/disable 2>/dev/null
echo "backup: $BAK"
echo "Oh My Keymint disabled."
echo "Next: reboot, then flash Starlight-TrickyStore-STRONG-1.4.1.zip in KernelSU,"
echo "then reboot again and run the checker unlocked."
