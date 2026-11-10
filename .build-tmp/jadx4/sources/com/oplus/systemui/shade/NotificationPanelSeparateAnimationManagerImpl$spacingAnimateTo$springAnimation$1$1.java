package com.oplus.systemui.shade;

import android.util.MathUtils;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ float $endSpacing;
    public /* synthetic */ float $startSpacing;
    public /* synthetic */ Function1 $update;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        float fLerp = MathUtils.lerp(this.$startSpacing, this.$endSpacing, f);
        Function1 function1 = this.$update;
        if (function1 != null) {
            function1.invoke(Float.valueOf(fLerp));
        }
    }
}
