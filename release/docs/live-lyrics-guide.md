# Live Lyrics on the Lock Screen — Starlight ColorOS 17

Get the native ColorOS lock-screen lyric page working on the Redmi Note 10 Pro
Max (`sweetin`) running the **Starlight ColorOS 17** build.

Works with **ColorOS Live Lyrics Bridge 4.5.3** + **Provider-Metrolist 1.2.1**
and **Metrolist 13.6.3**.

LSPosed is a separate prerequisite; it is **not** bundled in this release.

---

## Two ways to enable it

| Method | Needs a PC / adb? | Use when |
|---|---|---|
| **Flash the module (recommended)** | **No** | You install APKs and flash zips in KernelSU / Magisk |
| adb command | Yes | You already have adb and a root shell |

The module in this release is **`Starlight-LiveLyrics-Enable-v1.0.zip`**. It sets
`la_lyric_mode=2` at boot. That setting is what turns the lyric page on for this
ROM.

> The Bridge app's **Settings** screen only styles the page (preset, brightness,
> size, spacing, translation, AOD). It has **no toggle that enables the page**.
> Use the module, not the Bridge settings, to switch lyrics on.

---

## What you need

| Item | Requirement |
|---|---|
| ROM | Starlight ColorOS 17 (Android 17, `CP2A.260605.016` or newer) |
| Root | KernelSU / KernelSU Next (already included in the Starlight build) |
| LSPosed | LSPosed with **libxposed API 102** support, Zygisk enabled (**not bundled**) |
| Metrolist | **13.6.3** (matched to Provider-Metrolist 1.2.1) |
| Modules | Bridge **4.5.3** + Provider-Metrolist **1.2.1** |
| Enabler | **`Starlight-LiveLyrics-Enable-v1.0.zip`** (this release) |

The release asset named `ColorOS-Live-Lyrics-Provider-Metrolist-v4.5.3.apk`
contains app version **1.2.1**; that is the correct provider for Bridge 4.5.3.

---

## Install — app-only / flash-only (recommended)

No adb and no termux are needed for this path.

1. **Install LSPosed** (skip if it is already working).
   - Download LSPosed from the LSPosed project's release page; it is not
     bundled in this release.
   - In **KernelSU / Magisk → Modules → Install from storage**, flash the
     LSPosed Zygisk module zip.
   - Install the **LSPosed Manager** APK with your file manager.
   - Reboot once.

2. **Install the three APKs** with your file manager. Allow "Install unknown
   apps" for the file manager if Android asks.
   - `Metrolist-13.6.3.apk`
   - `ColorOS-Live-Lyrics-Provider-Metrolist-v4.5.3.apk`
   - `ColorOS-Live-Lyrics-Bridge-v4.5.3.apk`

3. **Enable the modules in LSPosed Manager**, with exactly these scopes:

   | Module | Scope |
   |---|---|
   | ColorOS Live Lyrics Bridge | **System UI** (`com.android.systemui`) + **System** (`android`) |
   | Provider-Metrolist | **Metrolist** (`com.metrolist.music`) |

4. **Reboot once** after installing or changing scopes.

5. **Flash the enabler module.**
   - Open **KernelSU → Modules → Install from storage**.
   - Select **`Starlight-LiveLyrics-Enable-v1.0.zip`**.
   - Reboot once.

   On boot the module runs `settings put system la_lyric_mode 2` and verifies
   the value. It re-applies the setting on every boot, so it survives reboots.

6. **Play a song with lyrics** in Metrolist, then lock the phone.

7. **Tap the lyric entrance icon** at the top-right of the lock-screen media
   card (the small icon with lines). The native lyric page opens and follows
   the track.

8. **Optional:** open LSPosed → ColorOS Live Lyrics Bridge → **Settings** to
   choose a preset and tune brightness, size, spacing, translation, AOD
   behaviour, etc. Tap **Apply and save**. This does not enable the page; the
   module does.

9. **Optional:** the page also works on the always-on display. Keep-screen-awake
   can be set in Bridge settings if you want lyrics to stay visible.

---

## Install — adb method (optional alternative)

Use this only if you already have adb. It does the same thing as the module.

1. Install the three APKs:
   ```bash
   adb install -r Metrolist-13.6.3.apk
   adb install -r ColorOS-Live-Lyrics-Provider-Metrolist-v4.5.3.apk
   adb install -r ColorOS-Live-Lyrics-Bridge-v4.5.3.apk
   ```

2. Enable the LSPosed scopes from the table above, then reboot.

3. Turn on the lyric page:
   ```bash
   adb shell su -c 'settings put system la_lyric_mode 2'
   adb shell settings get system la_lyric_mode   # must print 2
   ```

4. Play a song, lock the phone, and tap the top-right lyric icon.

You can skip the KernelSU module entirely if you use this method.

---

## Verify

**Without a PC:** play a track in Metrolist, lock the phone, and confirm the
lyric icon appears on the media card. Tap it; lyrics should render and follow
the song.

You can also open **KernelSU → Modules → Starlight LiveLyrics Enabler →
Action**. The action re-applies the setting and prints the current value
(`2` = enabled). It is not a toggle, so it cannot turn lyrics off by accident.

**With adb (advanced):**
```bash
adb shell settings get system la_lyric_mode
# 2

adb shell dumpsys activity service com.android.systemui | grep -o 'isLyricSupported=[a-z]*'
# isLyricSupported=true
```

---

## Troubleshooting

| Symptom | Fix |
|---|---|
| No lyric icon on the media card | Confirm the module is installed **and enabled** in KernelSU, then reboot. Press the module's **Action** button to re-apply. Also confirm the Bridge scopes are `System UI` + `System`. |
| Cannot find an "enable lyrics" switch in Bridge Settings | There is none. Bridge Settings only styles the page. The module (or the adb command) enables it. |
| Lyrics worked, then turned off | The module re-applies `la_lyric_mode=2` on the next boot, or press **Action** in KernelSU to re-apply immediately. |
| Icon appears but tap does nothing | Tap the small top-right icon, not the artwork; wake/lock once and retry. |
| "No lyrics" for a track | The track has no lyrics from the selected source, or Bridge/Provider/Metrolist versions do not match. Use the same release family. |
| Lyrics one track behind | Force stop Metrolist, restart SystemUI; keep Bridge and Provider from the same release. |
| Breaks after a ROM update | Update Bridge/Provider to a build that matches the new SystemUI. Reflash the enabler module if `/data` was wiped. |

---

## Revert

**App-only:** uninstall **Starlight LiveLyrics Enabler** in KernelSU → Modules.
The module's `uninstall.sh` sets `la_lyric_mode=1`, which returns the lyric page
to the ROM default (off).

**With adb (optional):**
```bash
adb shell su -c 'settings put system la_lyric_mode 1'
# or remove the setting entirely:
adb shell su -c 'settings delete system la_lyric_mode'
```

Removing the module stops it from re-applying the setting.

---

## Notes

- No fastboot, no data wipe, no image repacking.
- No core app is modified. The module only writes the normal System setting
  `la_lyric_mode=2`; the Bridge, Provider, Metrolist, SystemUI and the ROM
  images are untouched.
- `la_lyric_mode=2` is a normal user setting; it survives reboots and is fully
  reversible.
- Bridge reads lyrics from the player's MediaSession and styles them in the
  system UI. It does not modify music files or upload lyrics. Online lyric
  fetching is handled by Metrolist / its Provider.

---

## Credits

- **ColorOS Live Lyrics Bridge** — the LSPosed module that renders lyrics on the
  lockscreen, by **AndreAltB**
  ([github.com/AndreAltB/lockscreen-lyrics](https://github.com/AndreAltB/lockscreen-lyrics)).
- **Metrolist** — the open-source music player this guide assumes, by the
  Metrolist community
  ([github.com/MetrolistGroup/Metrolist](https://github.com/MetrolistGroup/Metrolist),
  [t.me/metrolistapp](https://t.me/metrolistapp)).
