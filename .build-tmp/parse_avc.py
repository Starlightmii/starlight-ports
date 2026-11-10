import re, collections, sys

p = sys.argv[1] if len(sys.argv) > 1 else '/home/starlight/tmp/selinux/live/live.log'
lo = sys.argv[2] if len(sys.argv) > 2 else '12:34:30'
hi = sys.argv[3] if len(sys.argv) > 3 else '12:35:30'
pat = re.compile(r'avc:\s+denied\s+\{([^}]*)\}\s+for\s+(.*?)\s*scontext=(\S+)\s+tcontext=(\S+)\s+tclass=(\S+)\s+permissive=0')
tpat = re.compile(r'^(\d\d-\d\d \d\d:\d\d:\d\d\.\d+)')
rows = collections.Counter()
for line in open(p, errors='replace'):
    m = tpat.match(line)
    if not m:
        continue
    ts = m.group(1)[6:]
    if not (lo <= ts <= hi):
        continue
    mm = pat.search(line)
    if not mm:
        continue
    perms = ' '.join(mm.group(1).split())
    extra = mm.group(2)
    name = ''
    for k in ('interface', 'name', 'path'):
        n = re.search(k + r'=(\S+)', extra)
        if n:
            name += k + '=' + n.group(1) + ' '
    rows[(mm.group(3), name.strip(), mm.group(4), mm.group(5), perms)] += 1
for k, v in rows.most_common(80):
    print(v, '|', k[0], '->', k[2], '|', k[3], '|', k[4], '|', k[1])
print('total unique', len(rows))
