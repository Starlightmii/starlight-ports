package com.oplus.systemui.keyguard.wallpaper.pictorial.factory;

import android.content.Context;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialFunctionContainer;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialGuideEntity;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMessageView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface PictorialBottomViewHelper {
    PictorialMessageView createPictorialMessageView(Context context);

    default void destory() {
    }

    default void onPictorialGuideInfoChanged(Context context, PictorialGuideEntity pictorialGuideEntity, PictorialFunctionContainer pictorialFunctionContainer) {
    }

    default void updatePictorialGuideView(Context context) {
    }

    default void updatePictorialGuideView(Context context, PictorialFunctionContainer pictorialFunctionContainer) {
    }
}
