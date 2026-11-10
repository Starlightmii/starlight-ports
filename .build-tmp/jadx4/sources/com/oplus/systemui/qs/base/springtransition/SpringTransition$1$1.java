package com.oplus.systemui.qs.base.springtransition;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpringTransition$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ SpringTransition this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        SpringTransition springTransition = this.this$0;
        float f3 = springTransition.startValue;
        if (f3 != Float.MIN_VALUE) {
            float f4 = springTransition.endValue;
            if (f4 != Float.MIN_VALUE && f4 != f3) {
                f = (f - f3) / (f4 - f3);
            }
        }
        if (springTransition._allTargetProperties.size() == 0) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "[updateFrame] now is empty animation ", springTransition.getTransitionName(), LogUtil.TAG_COMMON, springTransition.getTag());
        } else {
            Iterator<TransitionProperty> allTargetProperties = springTransition.getAllTargetProperties();
            while (allTargetProperties.hasNext()) {
                allTargetProperties.next().setProgress(f);
            }
        }
    }
}
