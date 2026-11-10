#!/system/bin/sh
echo lspd_pid=$(pidof lspd)
readlink /proc/$(pidof lspd)/exe 2>&1
ls -la /data/adb/modules/zygisk_lsposed/ 2>&1 | head
rm -rf /data/local/tmp/lspdb2; mkdir -p /data/local/tmp/lspdb2
cp /data/adb/lspd/config/modules_config.db* /data/local/tmp/lspdb2/ 2>/dev/null
chmod 644 /data/local/tmp/lspdb2/*
ls -la /data/local/tmp/lspdb2
