package com.oplus.systemui.wallpaperblur;

import android.app.WallpaperColors;
import android.app.WallpaperManager;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WallpaperBlurManager$colorChangeListener$1 implements WallpaperManager.OnColorsChangedListener {
    public /* synthetic */ WallpaperBlurManager this$0;

    @Override // android.app.WallpaperManager.OnColorsChangedListener
    public final void onColorsChanged(WallpaperColors wallpaperColors, int i) {
        LogUtil.i$default(LogUtil.TAG_COMMON, "WallpaperBlurManager", "onColorsChanged", null, 8);
        this.this$0.refreshCurrentWallpaperBlurDrawable();
    }
}
