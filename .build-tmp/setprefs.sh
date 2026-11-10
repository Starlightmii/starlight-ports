#!/system/bin/sh
D=/data/data/io.github.andrea_lyz.assistrestore
mkdir -p "$D/shared_prefs"
cat > "$D/shared_prefs/assist_restore.xml" <<'EOF'
<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
<map>
    <boolean name="enabled" value="true" />
    <string name="handle_mode">cts</string>
    <string name="power_mode">default</string>
    <boolean name="skip_ocr_preload" value="true" />
    <boolean name="spoof_google_build" value="true" />
    <boolean name="handle_when_bar_hidden" value="true" />
    <boolean name="unblock_page_flags" value="true" />
    <boolean name="double_press_wallet" value="false" />
</map>
EOF
chown u0_a353:u0_a353 "$D/shared_prefs/assist_restore.xml"
chmod 660 "$D/shared_prefs/assist_restore.xml"
chcon --reference="$D/files" "$D/shared_prefs/assist_restore.xml" 2>/dev/null
ls -laZ "$D/shared_prefs/assist_restore.xml"
cat "$D/shared_prefs/assist_restore.xml"
