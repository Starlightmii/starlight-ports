package com.oplus.systemui.statusbar.notification.row;

import androidx.core.view.insets.Protection$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusExpandableViewExImpl$touchScaleAnimator$2$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ COUISpringAnimation $this_apply;
    public /* synthetic */ OplusExpandableViewExImpl this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        COUISpringAnimation cOUISpringAnimation = this.$this_apply;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "ExpandableViewExtImpl", Protection$.ExternalSyntheticOutline0.m(cOUISpringAnimation.getSpring().getFinalPosition(), "tryStartScaleAnimation end, finalPosition="), null, 8);
        if (cOUISpringAnimation.getSpring().getFinalPosition() == 1.0f) {
            OplusExpandableViewExImpl.access$runAnimationFinishedRunnables(this.this$0);
        }
    }
}
