#!/system/bin/sh
pm dump io.github.andrea_lyz.assistrestore 2>/dev/null | grep -E "dataDir|userId|codePath" | head
echo ---LS---
ls -la /data/data/ 2>/dev/null | grep -i assist
ls -la /data/user/0/ 2>/dev/null | grep -i assist
ls -la /data/user_de/0/ 2>/dev/null | grep -i assist
echo ---FIND---
find /data -maxdepth 4 -iname "*assistrestore*" 2>/dev/null | head -20
