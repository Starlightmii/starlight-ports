package com.oplus.systemui.plugins.qs.animator;

import android.util.MathUtils;
import android.view.View;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSElementAnimator$alphaAnimation$2$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ QSElementAnimator this$0;

    public /* synthetic */ QSElementAnimator$alphaAnimation$2$1$1(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                QSElementAnimator qSElementAnimator = this.this$0;
                if (qSElementAnimator.alphaUpdateProxy != null) {
                    Function1 function1 = qSElementAnimator.alphaUpdateProxy;
                    if (function1 != null) {
                        function1.invoke(Float.valueOf(f));
                    }
                } else {
                    QSElementAnimator.Companion companion = QSElementAnimator.Companion;
                    View view = qSElementAnimator.hostView;
                    companion.getClass();
                    view.setTransitionAlpha(f);
                }
                break;
            case 1:
                Function1 function2 = this.this$0.negativeOffsetYUpdateProxy;
                if (function2 != null) {
                    function2.invoke(Float.valueOf(f));
                }
                break;
            case 2:
                QSElementAnimator qSElementAnimator2 = this.this$0;
                if (qSElementAnimator2.scaleUpdateProxy != null) {
                    Function1 function3 = qSElementAnimator2.scaleUpdateProxy;
                    if (function3 != null) {
                        function3.invoke(Float.valueOf(f));
                    }
                } else {
                    QSElementAnimator.Companion companion2 = QSElementAnimator.Companion;
                    View view2 = qSElementAnimator2.hostView;
                    companion2.getClass();
                    view2.setScaleX(f);
                    view2.setScaleY(f);
                }
                break;
            default:
                float fLerp = MathUtils.lerp(0.8f, 1.0f, f);
                QSElementAnimator qSElementAnimator3 = this.this$0;
                if (qSElementAnimator3.updateProxy != null) {
                    Function2 function4 = qSElementAnimator3.updateProxy;
                    if (function4 != null) {
                        function4.invoke(Float.valueOf(f), Float.valueOf(fLerp));
                    }
                } else {
                    QSElementAnimator.Companion companion3 = QSElementAnimator.Companion;
                    View view3 = qSElementAnimator3.hostView;
                    companion3.getClass();
                    view3.setScaleX(fLerp);
                    view3.setScaleY(fLerp);
                    view3.setTransitionAlpha(f);
                }
                break;
        }
    }
}
