package com.oplus.systemui.qs.base.springtransition;

import androidx.dynamicanimation.animation.FloatPropertyCompat;
import com.oplus.systemui.volume.OplusVolumeTipsControler;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class DrawableAlphaPropertyKt {
    private static final FloatPropertyCompat DRAWABLE_ALPHA = new DrawableAlphaPropertyKt$DRAWABLE_ALPHA$1(OplusVolumeTipsControler.PROPERTY_NAME_ALPHA);

    public static final FloatPropertyCompat getDRAWABLE_ALPHA() {
        return DRAWABLE_ALPHA;
    }
}
