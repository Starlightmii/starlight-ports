## Live Lyrics on the Lock Screen — Complete Setup Bundle (v1.0)

All the ROM-side files needed to enable the **native ColorOS lock-screen lyric
page** on the **Starlight ColorOS 17** ROM (Redmi Note 10 Pro Max / sweetin).
LSPosed is a separate prerequisite and is not bundled here.

This bundle now includes a **KernelSU / Magisk module**, so you do **not** need
adb or termux to enable lyrics. Install the APKs, flash the module in the
KernelSU app, reboot, and play a song.

---

### What's in this release

| File | What it is |
|---|---|
| **Starlight-LiveLyrics-Enable-v1.0.zip** | **KernelSU / Magisk module** — sets `la_lyric_mode=2` at boot. No adb needed. |
| **ColorOS-Live-Lyrics-Bridge-v4.5.3.apk** | The core LSPosed module that renders lyrics on the lockscreen |
| **ColorOS-Live-Lyrics-Provider-Metrolist-v4.5.3.apk** | Lyrics source provider for Metrolist (app version 1.2.1) |
| **Metrolist-13.6.3.apk** | The Metrolist music app version matched to the provider |
| **LIVE-LYRICS-SETUP-GUIDE.md** | Full step-by-step install + enable guide |

---

### Requirements

- **ROM:** Starlight ColorOS 17 (Android 17, `CP2A.260605.016` or newer)
- **Root:** KernelSU / KernelSU Next (already included in the Starlight build)
- **LSPosed** with libxposed API 102 + Zygisk enabled (not bundled; install separately)
- **Metrolist 13.6.3** (this release's APK) — matched to the provider

---

### App-only / flash-only install (recommended — no adb)

1. Install LSPosed the usual way: flash the LSPosed Zygisk zip in
   **KernelSU / Magisk → Modules**, install the LSPosed Manager APK, reboot.
2. Install the three APKs from this release with a file manager.
3. In **LSPosed Manager**, set the scopes:
   - **Bridge** → `System UI` (`com.android.systemui`) + `System` (`android`)
   - **Provider-Metrolist** → `Metrolist` (`com.metrolist.music`)
4. Reboot once.
5. In **KernelSU → Modules → Install from storage**, flash
   **Starlight-LiveLyrics-Enable-v1.0.zip**, then reboot.
   The module runs `settings put system la_lyric_mode 2` at boot and re-applies
   it on every boot.
6. Play a song in Metrolist, lock the phone, and tap the **lyric entrance icon**
   at the top-right of the lockscreen media card.

The Bridge app's Settings screen only styles the page. It has no switch that
enables the lyric page; the module does that.

---

### Optional adb method

If you already have adb and prefer it, you can skip the module and run:

```
adb shell su -c 'settings put system la_lyric_mode 2'
```

Full details, verification, troubleshooting and revert steps are in
**LIVE-LYRICS-SETUP-GUIDE.md**.

---

### Notes

- Uses the **native** ColorOS lyric page (not a floating overlay).
- No core app is modified. The module only writes the normal System setting
  `la_lyric_mode=2`; Bridge, Provider, Metrolist and the ROM are untouched.
- Revert: uninstall the module in KernelSU (its `uninstall.sh` sets
  `la_lyric_mode=1`), or use the adb revert in the guide.
- Tested on Starlight ColorOS 17, sweetin.

---

### Credits

- **ColorOS Live Lyrics Bridge** — the LSPosed module that renders lyrics on the
  lockscreen, by **AndreAltB**
  ([github.com/AndreAltB/lockscreen-lyrics](https://github.com/AndreAltB/lockscreen-lyrics)).
- **Metrolist** — the open-source music player this works with, by the Metrolist
  community ([github.com/MetrolistGroup/Metrolist](https://github.com/MetrolistGroup/Metrolist),
  [t.me/metrolistapp](https://t.me/metrolistapp)).

---

### Credits

- **ColorOS Live Lyrics Bridge** — the LSPosed module that renders lyrics on the
  lockscreen, by **AndreAltB**
  ([github.com/AndreAltB/lockscreen-lyrics](https://github.com/AndreAltB/lockscreen-lyrics)).
- **Metrolist** — the open-source music player this works with, by the Metrolist
  community ([github.com/MetrolistGroup/Metrolist](https://github.com/MetrolistGroup/Metrolist),
  [t.me/metrolistapp](https://t.me/metrolistapp)).
