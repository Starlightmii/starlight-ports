#!/bin/sh
# Reinstall the Play Integrity API Checker with Play Store attribution.
# Run from a PC with the phone connected and USB debugging enabled.
set -eu
cd "$(dirname "$0")"
adb install-multiple -r -i com.android.vending \
    PlayIntegrityAPIChecker.apk \
    splits/split_config.en.apk \
    splits/split_config.xxhdpi.apk
adb shell pm list packages -i | grep gr.nikolasspyr.integritycheck
