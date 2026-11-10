package com.oplus.systemui.wallpapers;

import com.oplus.content.IOplusFeatureConfigManager;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplusos.systemui.common.utils.CustomizeWallpaperUtilsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DefaultCanvasEngineExImpl$customizeWallpaperObserver$1 implements OplusFeatureConfigManager.OnFeatureActionObserver {
    public /* synthetic */ DefaultCanvasEngineExImpl this$0;

    public final void onFeaturesActionUpdate(String str, String str2, IOplusFeatureConfigManager.FeatureID featureID) {
        if ("cotaMounted".equals(str) && str2 != null && CustomizeWallpaperUtilsKt.shouldUpdWallpaper(1)) {
            int i = ImageWallpaperExt.$r8$clinit;
            if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wallpaper.separate_wallpaper_for_multi_display")) {
                return;
            }
            this.this$0.getEngineWrapper().drawFrame$1();
        }
    }
}
