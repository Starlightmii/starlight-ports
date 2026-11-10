#!/system/bin/sh
rm -rf /data/local/tmp/lspdb
mkdir -p /data/local/tmp/lspdb
cp /data/adb/lspd/config/modules_config.db* /data/local/tmp/lspdb/ 2>/dev/null
chmod 644 /data/local/tmp/lspdb/*
ls -la /data/local/tmp/lspdb
