package com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus;

import android.graphics.drawable.Drawable;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialPreloadView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperInfo;
import com.oplus.systemui.utils.OplusImageUtils;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusPictorialLoader$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ OplusPictorialLoader$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        Drawable drawable;
        switch (this.$r8$classId) {
            case 0:
                PictorialWallpaperInfo pictorialWallpaperInfo = (PictorialWallpaperInfo) obj;
                Drawable drawable2 = pictorialWallpaperInfo.mWallpaperDrawable;
                if (drawable2 != null) {
                    OplusImageUtils.recycleBitmap(drawable2);
                    pictorialWallpaperInfo.mWallpaperDrawable = null;
                }
                PictorialWallpaperInfo preloadWallpaper = pictorialWallpaperInfo.getPreloadWallpaper();
                if (preloadWallpaper != null && (drawable = preloadWallpaper.mWallpaperDrawable) != null) {
                    OplusImageUtils.recycleBitmap(drawable);
                    preloadWallpaper.mWallpaperDrawable = null;
                    break;
                }
                break;
            default:
                ((PictorialPreloadView) ((PictorialWallpaperHelper.IPictorialPreloadListener) obj)).onWallpaperChange();
                break;
        }
    }
}
