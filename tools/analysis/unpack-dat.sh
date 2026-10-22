#!/usr/bin/env bash
# unpack-dat.sh - Convert Android block-OTA .new.dat.br payloads into raw images.
# Requires: brotli, python3 (uses tools/analysis/sdat2img.py)
# Usage: tools/analysis/unpack-dat.sh <rom.zip> <outdir> [partition ...]
set -euo pipefail

ZIP="${1:?usage: $0 <rom.zip> <outdir> [partition ...]}"
OUT="${2:?usage: $0 <rom.zip> <outdir> [partition ...]}"
shift 2 || true
PARTS=("$@")

HERE="$(cd "$(dirname "$0")" && pwd)"
SDAT="$HERE/sdat2img.py"

[[ -f "$SDAT" ]] || { echo "missing $SDAT" >&2; exit 1; }
mkdir -p "$OUT"

# Discover partitions if none given
if [[ ${#PARTS[@]} -eq 0 ]]; then
  mapfile -t PARTS < <(unzip -l "$ZIP" | awk '/\.transfer\.list$/ {sub(/\.transfer\.list$/,"",$4); print $4}')
fi

[[ ${#PARTS[@]} -gt 0 ]] || { echo "no .transfer.list entries found in $ZIP" >&2; exit 1; }

for p in "${PARTS[@]}"; do
  echo ">>> $p"
  tmp="$(mktemp -d)"
  unzip -p "$ZIP" "$p.new.dat.br" > "$tmp/$p.new.dat.br"
  unzip -p "$ZIP" "$p.transfer.list" > "$tmp/$p.transfer.list"
  brotli -d -f "$tmp/$p.new.dat.br" -o "$tmp/$p.new.dat"
  python3 "$SDAT" "$tmp/$p.transfer.list" "$tmp/$p.new.dat" "$OUT/$p.img"
  rm -rf "$tmp"
  echo "    -> $OUT/$p.img"
done
echo "done."
