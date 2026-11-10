package com.oplus.systemui.keyguard.wallpaper.pictorial.factory;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperInfo;
import com.oplus.systemui.keyguard.wallpaper.pictorial.haokan.HKPictorialPreloadHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface PictorialPreloadHelper {
    void dispatchPictorialPreloadViewLoad();

    default boolean isOnlyShowWallpaperOnLockScreen() {
        return this instanceof HKPictorialPreloadHelper;
    }

    boolean needUpdatePreloadViewAtFirst(PictorialMediator pictorialMediator);

    default void onClickEntry() {
    }

    PictorialWallpaperInfo onWallpaperChange(Context context, ImageView imageView, PictorialWallpaperHelper pictorialWallpaperHelper);

    void updatePictorialSlidePageIntent(Intent intent, Context context, PictorialWallpaperInfo pictorialWallpaperInfo);
}
