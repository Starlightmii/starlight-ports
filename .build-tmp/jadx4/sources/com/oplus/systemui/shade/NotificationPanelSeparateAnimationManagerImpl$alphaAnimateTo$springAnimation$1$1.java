package com.oplus.systemui.shade;

import android.util.MathUtils;
import android.view.View;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ float $endAlpha;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ float $startAlpha;
    public /* synthetic */ Function1 $update;
    public /* synthetic */ View $view;
    public /* synthetic */ NotificationPanelSeparateAnimationManagerImpl this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                float fLerp = MathUtils.lerp(this.$startAlpha, this.$endAlpha, f);
                Function1 function1 = this.$update;
                if (function1 != null) {
                    function1.invoke(Float.valueOf(fLerp));
                } else {
                    this.$view.setTransitionAlpha(fLerp);
                }
                break;
            default:
                float fLerp2 = MathUtils.lerp(this.$startAlpha, this.$endAlpha, f);
                Function1 function2 = this.$update;
                if (function2 != null) {
                    function2.invoke(Float.valueOf(fLerp2));
                } else {
                    View view = this.$view;
                    view.setScaleX(fLerp2);
                    view.setScaleY(fLerp2);
                }
                break;
        }
    }
}
