package com.oplus.systemui.keyguard.wallpaper.uiengine;

import com.android.keyguard.wallpaper.KeyguardWallpaperInfo;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EngineWallpaperInfo extends KeyguardWallpaperInfo {
    public static final Companion Companion = new Companion();
    private static final String DEFINE_ENGINE_PATH = "engine";
    private String filePath;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public EngineWallpaperInfo() {
        setMType(2);
        setMColor(-1);
        this.filePath = DEFINE_ENGINE_PATH;
    }

    public String getFilePath() {
        return DEFINE_ENGINE_PATH;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }
}
