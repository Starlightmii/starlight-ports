#!/system/bin/sh
# Run ON THE DEVICE as root to roll back the STRONG experiment.
# Removes the tricky_store module through KernelSU, re-enables Oh My Keymint,
# and reminds you to reboot. Does not restore the old (invalid) keybox.
set -u
/data/adb/ksud module uninstall tricky_store 2>/dev/null
rm -f /data/adb/modules/oh_my_keymint/disable 2>/dev/null
echo "tricky_store uninstall requested; Oh My Keymint re-enabled."
echo "Reboot, then run:"
echo "  sh /data/adb/modules/starlight_integrity_kit/action.sh apply"
