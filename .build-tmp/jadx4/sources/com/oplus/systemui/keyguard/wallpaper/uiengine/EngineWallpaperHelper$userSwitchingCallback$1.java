package com.oplus.systemui.keyguard.wallpaper.uiengine;

import com.android.keyguard.KeyguardUpdateMonitorCallback;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EngineWallpaperHelper$userSwitchingCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ EngineWallpaperHelper this$0;

    public final void onUserSwitchComplete(int i) {
        this.this$0.isUserSwtitching = false;
    }

    public final void onUserSwitching(int i) {
        this.this$0.isUserSwtitching = true;
    }
}
