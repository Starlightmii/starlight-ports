# Starlight LiveLyrics Enabler

KernelSU / Magisk module for the Starlight ColorOS 17 build on the Redmi Note
10 Pro Max (`sweetin`).

## What it does

Runs at boot and sets the System setting:

```
la_lyric_mode = 2
```

That setting is the gate for the native ColorOS lock-screen lyric page. The
ROM already contains the lyric page; this module only flips the gate on.

## Install

1. Open **KernelSU** (or Magisk) → **Modules**.
2. **Install from storage** → select
   `Starlight-LiveLyrics-Enable-v1.0.zip`.
3. Reboot once.
4. Play a song in Metrolist and lock the phone.
5. Tap the **lyric entrance icon** at the top-right of the lock-screen media
   card.

No adb, no termux, no PC required.

## Notes

- The value is re-applied on every boot, so it survives reboots and ROM
  updates that preserve `/data`.
- The setting is a normal user setting; it does not modify the ROM, SystemUI,
  or any app.
- **Action** button in KernelSU: re-applies the setting and prints the current
  value. It is not a toggle.
- **Revert:** uninstall the module in KernelSU. Its `uninstall.sh` sets
  `la_lyric_mode=1`. The lyric page then returns to the ROM default (off).
- Advanced opt-out: create `/data/adb/starlight_livelyrics_off` as root; the
  boot script will skip while that file exists.
