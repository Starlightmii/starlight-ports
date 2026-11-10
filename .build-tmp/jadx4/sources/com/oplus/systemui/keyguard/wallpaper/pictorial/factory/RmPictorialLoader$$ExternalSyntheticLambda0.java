package com.oplus.systemui.keyguard.wallpaper.pictorial.factory;

import android.widget.ImageView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialPreloadView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class RmPictorialLoader$$ExternalSyntheticLambda0 implements Consumer {
    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        PictorialPreloadView pictorialPreloadView = (PictorialPreloadView) ((PictorialWallpaperHelper.IPictorialPreloadListener) obj);
        ImageView imageView = pictorialPreloadView.mIvPreloadPictoralImage;
        if (imageView != null) {
            imageView.setImageDrawable(null);
        }
        pictorialPreloadView.mWallpaperInfo = null;
    }
}
