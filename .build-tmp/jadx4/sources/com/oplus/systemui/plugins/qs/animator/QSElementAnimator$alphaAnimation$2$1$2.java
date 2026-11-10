package com.oplus.systemui.plugins.qs.animator;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSElementAnimator$alphaAnimation$2$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ QSElementAnimator this$0;

    public /* synthetic */ QSElementAnimator$alphaAnimation$2$1$2(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                LogUtil.i$default(LogUtil.TAG_QS, this.this$0.getTag(), "alphaAnimation end, isCanceled=" + z + ", currValue=" + f, null, 8);
                break;
            case 1:
                QSElementAnimator qSElementAnimator = this.this$0;
                LogUtil.i$default(LogUtil.TAG_QS, qSElementAnimator.getTag(), "negativeOffsetYAnimation end, isCanceled=" + z + ", currValue=" + f, null, 8);
                Function0 function0 = qSElementAnimator.negativeOffsetYEndListener;
                if (function0 != null) {
                    function0.invoke();
                }
                qSElementAnimator.negativeOffsetYEndListener = null;
                break;
            case 2:
                LogUtil.i$default(LogUtil.TAG_QS, this.this$0.getTag(), "scaleAnimation end, isCanceled=" + z + ", currValue=" + f, null, 8);
                break;
            default:
                QSElementAnimator qSElementAnimator2 = this.this$0;
                LogUtil.i$default(LogUtil.TAG_QS, qSElementAnimator2.getTag(), "updateAnimation end, isCanceled=" + z + ", currValue=" + f, null, 8);
                Function1 function1 = qSElementAnimator2.updateAnimationEndListener;
                if (function1 != null) {
                    function1.invoke(Boolean.valueOf(z));
                }
                qSElementAnimator2.updateAnimationEndListener = null;
                break;
        }
    }
}
