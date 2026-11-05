# Screen recording

`starlight-demo.mp4` — a 20-second on-device walkthrough of the Starlight
ColorOS 17 build.

| Property | Value |
|---|---|
| Duration | ~20 s |
| Resolution | 720 × 1600 |
| Codec | H.264 / yuv420p, faststart |
| Audio | none (screenrecord) |
| Size | ~1 MB |
| Source | `adb shell screenrecord` on `sweetin` |
| Poster | `poster.png` (frame at 00:02) |

## What it shows

1. Home screen and status bar
2. App drawer with the custom `cust.img` apps
3. Quick Settings panel
4. About device — Starlight banner, GitHub row, `CP2A.260605.016 Starlight build`

## Re-record

```bash
adb shell screenrecord --size 720x1600 --bit-rate 4M --time-limit 25 /sdcard/starlight-demo.mp4
adb pull /sdcard/starlight-demo.mp4 .
ffmpeg -i starlight-demo.mp4 -c:v libx264 -preset slow -crf 30 \
  -pix_fmt yuv420p -movflags +faststart -an starlight-demo.mp4
```

If a longer or higher-quality recording is added later, keep it under ~20 MB so
the showcase branch stays light.
