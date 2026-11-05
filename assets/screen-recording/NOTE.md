# Screen recording

`starlight-demo.mp4` — a 22-second on-device walkthrough of the Starlight
ColorOS 17 release build.

| Property | Value |
|---|---|
| Duration | 22 s |
| Resolution | 720 × 1600 (portrait) |
| Codec | H.264 High / yuv420p, 30 fps, faststart |
| Audio | AAC, 48 kHz stereo (64 kbps) |
| Size | ~2.1 MB |
| Poster | `poster.png` — 720 × 1600 still with the Starlight frame and play badge |

## What it shows

1. The custom Starlight app drawer
2. App search and Settings
3. The Starlight **About device** banner
4. Build identity and version details

The poster is taken from the About screen at **0:09** and composited with
[`../design/video-frame.svg`](../design/video-frame.svg) plus a play badge.

## Notes

- A 22-second cut from the full 80-second on-device screen recording.
- Re-encoded for web playback with `+faststart`; keep it under ~5 MB so the
  showcase stays light.
- The root README embeds the demo the way GitHub supports inline video: a bare
  `https://github.com/user-attachments/assets/<uuid>` URL on its own line. GitHub
  rewrites that to a `<video controls muted>` player at render time. Authored
  `<video>` tags are stripped by GitHub's README sanitizer, so do not replace the
  bare URL with one. The committed MP4 remains the source/download and
  `poster.png` stays as the no-player fallback.
