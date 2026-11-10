package com.oplus.systemui.keyguard.wallpaper.pictorial.haokan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperInfo;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppData;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus.OplusPictorialPreloadHelper;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HKPictorialPreloadHelper extends OplusPictorialPreloadHelper {
    public boolean mClickEntryClicked;

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus.OplusPictorialPreloadHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public final boolean needUpdatePreloadViewAtFirst(PictorialMediator pictorialMediator) {
        return true;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public final void onClickEntry() {
        this.mClickEntryClicked = true;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus.OplusPictorialPreloadHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public final PictorialWallpaperInfo onWallpaperChange(Context context, ImageView imageView, PictorialWallpaperHelper pictorialWallpaperHelper) {
        Drawable preloadWallpaper = pictorialWallpaperHelper.getPreloadWallpaper();
        StringBuilder sb = new StringBuilder("onWallpaperChange drawable = ");
        sb.append(preloadWallpaper != null);
        KgdLogUtil.d("PictorialPreloadHelper(HK)", sb.toString());
        if (preloadWallpaper != null) {
            imageView.setImageDrawable(preloadWallpaper);
        } else {
            imageView.setImageDrawable(context.getDrawable(2131237579));
        }
        this.mPreloadViewLoadSuccessed = preloadWallpaper != null;
        return pictorialWallpaperHelper.getPreloadWallpaperInfo();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus.OplusPictorialPreloadHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper
    public final void updatePictorialSlidePageIntent(Intent intent, Context context, PictorialWallpaperInfo pictorialWallpaperInfo) {
        if (this.mClickEntryClicked) {
            intent.setData(Uri.parse("hkmagazine://home"));
        } else {
            PictorialAppData pictorialAppData = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialAppData();
            if (pictorialAppData != null) {
                intent.setClassName(pictorialAppData.getPictorialAppPackage(), pictorialAppData.getPictorialSlideClassName());
                intent.setAction(pictorialAppData.getPictorialSlideAction());
            }
            if (pictorialWallpaperInfo != null) {
                intent.putExtra("imageId", pictorialWallpaperInfo.getImageId());
            }
        }
        this.mClickEntryClicked = false;
        KgdLogUtil.d("PictorialPreloadHelper(HK)", "resetTipsTime");
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("kg_pictorial_hk_tips", 0).edit();
        editorEdit.putLong("kg_hk_pretime", System.currentTimeMillis());
        editorEdit.apply();
    }
}
