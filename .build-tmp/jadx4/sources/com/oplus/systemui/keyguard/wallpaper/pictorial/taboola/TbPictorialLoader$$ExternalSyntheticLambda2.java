package com.oplus.systemui.keyguard.wallpaper.pictorial.taboola;

import android.graphics.drawable.Drawable;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperLoader;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class TbPictorialLoader$$ExternalSyntheticLambda2 implements PictorialWallpaperLoader.WallpaperLoadListener {
    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperLoader.WallpaperLoadListener
    public final void onLoadWallpaperFinished(PictorialWallpaperLoader.WallpaperLoadRequest wallpaperLoadRequest, Drawable drawable) {
        if (drawable != null) {
            KgdLogUtil.i("TbPictorialLoader", "asyncWelcomeUpdateWallpaper");
            return;
        }
        KgdLogUtil.i("TbPictorialLoader", "asyncWelcomeUpdateWallpaper result is nul, key is " + wallpaperLoadRequest.getCacheKey());
    }
}
