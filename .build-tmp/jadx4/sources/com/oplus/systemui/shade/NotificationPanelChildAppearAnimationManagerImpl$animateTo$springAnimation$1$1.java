package com.oplus.systemui.shade;

import android.util.MathUtils;
import android.view.View;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ float $endAlpha;
    public /* synthetic */ float $endScale;
    public /* synthetic */ float $startAlpha;
    public /* synthetic */ float $startScale;
    public /* synthetic */ Function2 $update;
    public /* synthetic */ View $view;
    public /* synthetic */ NotificationPanelChildAppearAnimationManagerImpl this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        float fLerp = MathUtils.lerp(this.$startAlpha, this.$endAlpha, f);
        float fLerp2 = MathUtils.lerp(this.$startScale, this.$endScale, f);
        Function2 function2 = this.$update;
        if (function2 != null) {
            function2.invoke(Float.valueOf(fLerp), Float.valueOf(fLerp2));
            return;
        }
        View view = this.$view;
        view.setScaleX(fLerp2);
        view.setScaleY(fLerp2);
        view.setTransitionAlpha(fLerp);
    }
}
