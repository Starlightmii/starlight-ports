import json
import os

out = "/home/starlight/tmp/release_fixes/playstore-arm64-bundle"
os.chdir(out)
files = sorted(f for f in os.listdir(".") if f.endswith(".apk"))
info = {
    "packageName": "com.android.vending",
    "versionName": "53.9.23-24 [0] [PR] 1012244613",
    "versionCode": 85392300,
    "minSdkVersion": 32,
    "targetSdkVersion": 37,
    "arch": "arm64-v8a",
    "bundleType": "split-apk-bundle",
    "baseApk": "base.apk",
    "splitApks": [f for f in files if f != "base.apk"],
    "install": "adb install-multiple -r base.apk split_*.apk",
    "signerSha256": "7ce83c1b71f3d572fed04c8d40c5cb10ff75e6d87d9df6fbd53f0468c2905053",
}
with open("info.json", "w") as fh:
    json.dump(info, fh, indent=2)
    fh.write("\n")
print("wrote info.json with", len(info["splitApks"]), "splits")
