package com.oplus.systemui.keyguard.wallpaper.pictorial.taboola;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
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
public final class TbPictorialPreloadHelper implements PictorialPreloadHelper {
    public boolean mPreloadViewLoadSuccessed;

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public final void dispatchPictorialPreloadViewLoad() {
        PictorialStateWatcher.getInstance().dispatchPictorialPreloadViewLoad(this.mPreloadViewLoadSuccessed);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public final boolean isOnlyShowWallpaperOnLockScreen() {
        return true;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public final boolean needUpdatePreloadViewAtFirst(PictorialMediator pictorialMediator) {
        return !pictorialMediator.isMainSwitchOn();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public final void onClickEntry() {
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public final PictorialWallpaperInfo onWallpaperChange(Context context, ImageView imageView, PictorialWallpaperHelper pictorialWallpaperHelper) {
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        if (!pictorialMediator.isLHSwitchOn()) {
            this.mPreloadViewLoadSuccessed = false;
            return null;
        }
        if (!pictorialMediator.isLHSwitchOn()) {
            KgdLogUtil.w("TbPictorialPreloadHelper", "onWallpaperChange activate drawable");
            this.mPreloadViewLoadSuccessed = false;
            return null;
        }
        PictorialWallpaperInfo preloadWallpaperInfo = pictorialWallpaperHelper.getPreloadWallpaperInfo();
        Drawable preloadWallpaper = pictorialWallpaperHelper.getPreloadWallpaper();
        imageView.setImageDrawable(preloadWallpaper);
        StringBuilder sb = new StringBuilder("onWallpaperChange drawable = ");
        sb.append(preloadWallpaper != null);
        KgdLogUtil.w("TbPictorialPreloadHelper", sb.toString());
        this.mPreloadViewLoadSuccessed = preloadWallpaper != null;
        return preloadWallpaperInfo;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public final void updatePictorialSlidePageIntent(Intent intent, Context context, PictorialWallpaperInfo pictorialWallpaperInfo) {
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        PictorialAppData pictorialAppData = pictorialMediator.getPictorialAppData();
        if (pictorialAppData != null) {
            if (!pictorialMediator.isMainSwitchStatusOn()) {
                intent.setAction(pictorialAppData.getPictorialActivateAction());
                intent.setClassName(pictorialAppData.getPictorialAppPackage(), pictorialAppData.getPictorialActivateClassName());
                return;
            }
            intent.setClassName(pictorialAppData.getPictorialAppPackage(), pictorialAppData.getPictorialSlideClassName());
            intent.setAction(pictorialAppData.getPictorialSlideAction());
            if (pictorialWallpaperInfo == null || TextUtils.isEmpty(pictorialWallpaperInfo.getImageId())) {
                return;
            }
            intent.putExtra("imageId", pictorialWallpaperInfo.getImageId());
            if (pictorialWallpaperInfo.getImageId().contains("BuiltInItem_")) {
                ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialFactory().getPictorialBuriedHelper().uploadActiveEvent(context, "taboola_active_0");
            } else {
                ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialFactory().getPictorialBuriedHelper().uploadActiveEvent(context, "taboola_active_2");
            }
        }
    }
}
