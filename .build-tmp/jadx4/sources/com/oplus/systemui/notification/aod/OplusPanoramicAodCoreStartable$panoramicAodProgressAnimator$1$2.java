package com.oplus.systemui.notification.aod;

import androidx.core.view.insets.Protection$;
import com.android.internal.dynamicanimation.animation.DynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanoramicAodCoreStartable$panoramicAodProgressAnimator$1$2 implements DynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ OplusPanoramicAodCoreStartable this$0;

    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusPanoramicAodCoreStartable", Protection$.ExternalSyntheticOutline0.m(f, "fractionAnimation onAnimationEnd, value="), null, 8);
        this.this$0.setPanoramicAodProgress(f);
    }
}
