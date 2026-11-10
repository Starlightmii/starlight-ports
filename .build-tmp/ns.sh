#!/system/bin/sh
echo self=$(readlink /proc/self/ns/mnt) init=$(readlink /proc/1/ns/mnt)
which nsenter
nsenter -t 1 -m -- ls -lad /data/data/io.github.andrea_lyz.assistrestore 2>&1
nsenter -t 1 -m -- ls -laR /data/data/io.github.andrea_lyz.assistrestore 2>&1 | head -30
