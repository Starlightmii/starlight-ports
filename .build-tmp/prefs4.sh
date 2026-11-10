#!/system/bin/sh
D=/data/user/0/io.github.andrea_lyz.assistrestore
ls -lad "$D" 2>&1
find "$D" -maxdepth 3 2>&1 | head -40
echo ---UID---
ls -lad /data/user/0 2>&1
ls /data/user/0 2>/dev/null | grep -i assist
