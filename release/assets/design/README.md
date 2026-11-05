# Design assets

Self-contained SVG artwork for the Starlight release page. Every gradient,
filter, star and icon is inline — no external images, fonts or `xlink`
references.

| File | Canvas | Purpose |
|---|---|---|
| `banner-hero.svg` | 1200 × 420 | README hero: starfield, gold star mark, gradient wordmark and a glowing phone silhouette |
| `logo-star.svg` | 256 × 256 | Standalone star badge for avatars, README footer and social previews |
| `feature-strip.svg` | 1200 × 240 | Six feature cards: Dolby Atmos, GCam, performance, custom apps, safe install and Circle to Search |

## Palette

| Token | Hex |
|---|---|
| Deep space | `#05060f` → `#0a1028` → `#1a0a2e` |
| Cyan | `#22d3ee` / `#67e8f9` |
| Indigo | `#6366f1` / `#818cf8` |
| Violet | `#a78bfa` / `#c084fc` |
| Gold | `#fbbf24` / `#fcd34d` / `#f59e0b` |
| Text | `#e8eeff` / `#c7d2fe` |
| Muted | `#8b97c4` |

## Notes

- Text uses an Arial / Helvetica / sans-serif fallback stack.
- The wordmark, icons and star marks are vector paths and text, so they scale
  cleanly.
- The three files share the same deep-space gradients, orbit rings and gold
  accent for a cohesive release page.
