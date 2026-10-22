#!/usr/bin/env python3
"""sdat2img.py - convert Android .transfer.list + .new.dat into a raw image.

Usage: sdat2img.py <transfer.list> <new.dat> <output.img>
Adapted from the well-known sdat2img concept; block-based OTA payloads.
"""

import os
import sys

BLOCK_SIZE = 4096


def read_transfer_list(path):
    """Read and parse a transfer.list, returning (version, total_blocks, commands)."""
    try:
        with open(path, encoding="utf-8") as handle:
            lines = [line.strip() for line in handle if line.strip()]
    except OSError as exc:
        raise SystemExit(f"cannot read transfer list {path}: {exc}") from exc
    if not lines:
        raise SystemExit(f"empty transfer list: {path}")
    try:
        version = int(lines[0])
        total_blocks = int(lines[1])
    except ValueError as exc:
        raise SystemExit(f"bad transfer list header in {path}: {exc}") from exc
    return version, total_blocks, lines[2:]


def parse_range(command):
    """Parse a command line into (op, start_block, block_count)."""
    parts = command.split()
    if len(parts) != 2 or "," not in parts[1]:
        raise SystemExit(f"malformed transfer command: {command!r}")
    try:
        start, count = (int(value) for value in parts[1].split(","))
    except ValueError as exc:
        raise SystemExit(f"malformed block range in {command!r}: {exc}") from exc
    return parts[0], start, count


def write_zeros(handle, start, count):
    handle.seek(start * BLOCK_SIZE)
    handle.write(b"\x00" * (count * BLOCK_SIZE))


def write_image(dat, out, commands, total_blocks, dat_path):
    """Write the raw image body from the data handle into the output handle."""
    out.truncate(total_blocks * BLOCK_SIZE)
    for command in commands:
        op, start, count = parse_range(command)
        if op == "new":
            size = count * BLOCK_SIZE
            out.seek(start * BLOCK_SIZE)
            chunk = dat.read(size)
            if len(chunk) != size:
                raise SystemExit(
                    f"short read in {dat_path}: wanted {size}, got {len(chunk)}"
                )
            out.write(chunk)
        elif op in ("erase", "zero"):
            write_zeros(out, start, count)
        # "move" commands belong to incremental OTAs and are skipped.


def convert(tl_path, dat_path, out_path):
    version, total_blocks, commands = read_transfer_list(tl_path)
    try:
        with open(dat_path, "rb") as dat, open(out_path, "wb") as out:
            write_image(dat, out, commands, total_blocks, dat_path)
    except OSError as exc:
        raise SystemExit(f"image conversion failed: {exc}") from exc

    print(
        f"wrote {out_path} ({os.path.getsize(out_path)} bytes, "
        f"transfer version {version})"
    )


def main():
    if len(sys.argv) != 4:
        print(__doc__, file=sys.stderr)
        return 1
    convert(sys.argv[1], sys.argv[2], sys.argv[3])
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
