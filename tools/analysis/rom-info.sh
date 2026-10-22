#!/usr/bin/env bash
# rom-info.sh - Inspect any Android OTA/fastboot ROM zip and report verified facts.
# Usage: tools/analysis/rom-info.sh <rom.zip>
# Output: human-readable report; pass --json for machine-readable.
set -euo pipefail

ZIP="${1:-}"
JSON=0
[[ "${2:-}" == "--json" ]] && JSON=1

if [[ -z "$ZIP" || ! -f "$ZIP" ]]; then
  echo "usage: $0 <rom.zip> [--json]" >&2
  exit 1
fi

meta=$(unzip -p "$ZIP" META-INF/com/android/metadata 2>/dev/null || true)
oplist=$(unzip -p "$ZIP" dynamic_partitions_op_list 2>/dev/null || true)
script=$(unzip -p "$ZIP" META-INF/com/google/android/updater-script 2>/dev/null || true)

get() { echo "$meta" | grep -m1 "^$1=" | cut -d= -f2- || true; }

device=$(get pre-device)
post_build=$(get post-build)
sdk=$(get post-sdk-level)
patch=$(get post-security-patch-level)
ota_type=$(get ota-type)

if (( JSON )); then
  printf '{\n'
  printf '  "zip": "%s",\n' "$ZIP"
  printf '  "device": "%s",\n' "$device"
  printf '  "post_build": "%s",\n' "$post_build"
  printf '  "sdk_level": "%s",\n' "$sdk"
  printf '  "security_patch": "%s",\n' "$patch"
  printf '  "ota_type": "%s",\n' "$ota_type"
  printf '  "physical_images": ['
  unzip -l "$ZIP" 2>/dev/null | awk '/\.img$/ {printf "%s\"%s\"", (n++?", ":""), $4}'
  printf '],\n'
  printf '  "logical_partitions": ['
  echo "$oplist" | awk '/^resize /{printf "%s\"%s:%s\"", (n++?", ":""), $2, $3}'
  printf ']\n}\n'
  exit 0
fi

echo "=== ROM: $ZIP ==="
echo "device          : $device"
echo "post-build      : $post_build"
echo "sdk level       : $sdk"
echo "security patch  : $patch"
echo "ota type        : $ota_type"
echo
echo "--- logical partitions (resize entries) ---"
echo "$oplist" | grep '^resize ' | awk '{printf "  %-12s %s bytes\n", $2, $3}' || echo "  (none)"
echo
echo "--- physical .img entries ---"
unzip -l "$ZIP" 2>/dev/null | awk '/\.img$/ {printf "  %-40s %s\n", $4, $1}' || true
echo
echo "--- dynamic group ---"
echo "$oplist" | grep '^add_group ' | sed 's/^/  /' || echo "  (none)"
