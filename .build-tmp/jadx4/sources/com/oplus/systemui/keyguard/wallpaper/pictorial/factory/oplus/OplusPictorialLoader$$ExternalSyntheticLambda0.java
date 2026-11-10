package com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus;

import android.graphics.drawable.Drawable;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperLoader;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusPictorialLoader$$ExternalSyntheticLambda0 implements PictorialWallpaperLoader.WallpaperLoadListener {
    public /* synthetic */ OplusPictorialLoader f$0;
    public /* synthetic */ boolean f$1;

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperLoader.WallpaperLoadListener
    public final void onLoadWallpaperFinished(PictorialWallpaperLoader.WallpaperLoadRequest wallpaperLoadRequest, Drawable drawable) {
        OplusPictorialLoader oplusPictorialLoader = this.f$0;
        boolean z = this.f$1;
        if (drawable == null) {
            LogUtil.w(LogUtil.TAG_KEYGUARD, "PictorialLoader-oplus", "asyncUpdateWallpaper result is nul, key is " + wallpaperLoadRequest.getCacheKey());
            return;
        }
        List list = oplusPictorialLoader.mPreloadChangeListeners;
        PictorialLoader.IPictorialLoaderCallback iPictorialLoaderCallback = oplusPictorialLoader.mCallback;
        OplusPictorialLoader.SetWallpaperRunnable setWallpaperRunnable = new OplusPictorialLoader.SetWallpaperRunnable();
        setWallpaperRunnable.mRequest = wallpaperLoadRequest;
        setWallpaperRunnable.mIsLockScreen = z;
        setWallpaperRunnable.mPictorialLoader = oplusPictorialLoader;
        setWallpaperRunnable.mPreloadChangeListeners = list;
        setWallpaperRunnable.mResult = drawable;
        setWallpaperRunnable.mCallback = iPictorialLoaderCallback;
        SystemUIThreadUtil.runOnUiThreadAtFront(setWallpaperRunnable);
    }
}
