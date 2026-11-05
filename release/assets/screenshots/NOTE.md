# Screenshots

Real on-device captures from the Starlight ColorOS 17 build.

| File | Shows |
|---|---|
| `01-home.png` | Home screen, status bar and dock |
| `02-about-starlight.png` | About device — Starlight banner, `developer: starlight ⭐` GitHub row, `CP2A.260605.016 Starlight build` |
| `03-quick-settings.png` | Expanded Quick Settings / status bar |
| `04-sound-effects.png` | ColorOS sound effect settings (OReality / Dolby audio page) |
| `05-lunaris-dolby.png` | LunarisDolby — Starlight app |
| `06-app-drawer.png` | App drawer with custom `cust.img` apps (Weather, Documents, Translate, Notes, IR Remote, InstallerX, LunarisDolby, Metrolist, FrostKeys) |

## Capture details

- Device: `dd89fda1` — Redmi Note 10 Pro Max (`sweetin`, `M2101K6I`)
- Build: `CP2A.260605.016 Starlight build`
- Resolution: 1080 × 2400
- Method: `adb exec-out screencap -p`
- Date: 2026-11-05

Screenshots are PNG, unedited except for cropping the raw capture. All text is
legible at the resolution shown.

To recapture:

```bash
adb exec-out screencap -p > assets/screenshots/01-home.png
```
