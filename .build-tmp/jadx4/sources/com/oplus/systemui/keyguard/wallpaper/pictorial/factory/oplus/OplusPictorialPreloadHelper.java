package com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperInfo;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppData;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper;
import com.oplus.systemui.keyguard.watcher.PictorialStateWatcher;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusPictorialPreloadHelper implements PictorialPreloadHelper {
    public boolean mPreloadViewLoadSuccessed = false;

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public final void dispatchPictorialPreloadViewLoad() {
        PictorialStateWatcher.getInstance().dispatchPictorialPreloadViewLoad(this.mPreloadViewLoadSuccessed);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public boolean needUpdatePreloadViewAtFirst(PictorialMediator pictorialMediator) {
        if (pictorialMediator.isMainSwitchOffAndCanLeftSwipe()) {
            return !pictorialMediator.isLHSwitchOn();
        }
        return false;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public PictorialWallpaperInfo onWallpaperChange(Context context, ImageView imageView, PictorialWallpaperHelper pictorialWallpaperHelper) {
        PictorialWallpaperInfo preloadWallpaperInfo = pictorialWallpaperHelper.getPreloadWallpaperInfo();
        Drawable preloadWallpaper = pictorialWallpaperHelper.getPreloadWallpaper();
        StringBuilder sb = new StringBuilder("onWallpaperChange drawable = ");
        sb.append(preloadWallpaper != null);
        KgdLogUtil.i("PictorialPreloadHelper(oplus)", sb.toString());
        imageView.setImageDrawable(preloadWallpaper);
        this.mPreloadViewLoadSuccessed = preloadWallpaper != null;
        return preloadWallpaperInfo;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public void updatePictorialSlidePageIntent(Intent intent, Context context, PictorialWallpaperInfo pictorialWallpaperInfo) {
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        PictorialAppData pictorialAppData = pictorialMediator.getPictorialAppData();
        if (pictorialMediator.isMainSwitchOffAndCanLeftSwipe() && !pictorialMediator.isMainSwitchStatusOn()) {
            if (pictorialAppData != null) {
                intent.setAction(pictorialAppData.getPictorialActivateAction());
                intent.setClassName(pictorialAppData.getPictorialAppPackage(), pictorialAppData.getPictorialActivateClassName());
                intent.putExtra("enterReferer", 0);
                return;
            }
            return;
        }
        if (pictorialAppData != null) {
            intent.setClassName(pictorialAppData.getPictorialAppPackage(), pictorialAppData.getPictorialSlideClassName());
            intent.setAction(pictorialAppData.getPictorialSlideAction());
        }
        if (pictorialWallpaperInfo != null) {
            intent.putExtra("imageId", pictorialWallpaperInfo.getImageId());
        }
    }
}
