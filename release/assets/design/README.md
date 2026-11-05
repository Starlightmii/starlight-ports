# Design assets

Self-contained SVG artwork for the Starlight showcase. No external images,
fonts, or `xlink` references — every gradient, filter and shape is inline.

| File | Canvas | Purpose |
|---|---|---|
| `banner-hero.svg` | 1200 × 420 | README hero banner: starfield, four-point star mark, gradient wordmark, build tag |
| `logo-star.svg` | 256 × 256 | Standalone star logo badge for avatars, README footer and social previews |
| `feature-strip.svg` | 1200 × 240 | Six feature cards: Dolby Atmos, GCam, Performance, Custom apps, Safe install, Circle to Search |

## Palette

| Token | Hex |
|---|---|
| Deep space | `#05060f` → `#0b1030` → `#1b0b33` |
| Cyan | `#67e8f9` / `#38bdf8` |
| Indigo | `#818cf8` |
| Violet | `#a78bfa` / `#c084fc` |
| Text | `#e2e8f0` / `#c7d2fe` |
| Muted | `#7f8db8` |

## Notes

- Text uses a system sans-serif stack (`Segoe UI`, `Helvetica Neue`, `Arial`).
- The wordmark and icons are vector paths/text, so they scale cleanly.
- Rendered previews: `rsvg-convert -w 1200 banner-hero.svg -o banner.png`.
