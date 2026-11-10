#!/system/bin/sh
D=/data/data/io.github.andrea_lyz.assistrestore
ls -lad "$D" 2>&1
ls -laR "$D" 2>&1 | head -50
