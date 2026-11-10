package com.oplus.systemui.notification.lockscreen.capsule.event;

import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleScrollerHorizontal$springAnimator$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ CapsuleScrollerHorizontal this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        CapsuleScrollerHorizontal capsuleScrollerHorizontal = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("springAnimator hScrollSpring, NewValue=", f, "CapsuleScrollerHorizontal-->", "SystemUi--Notification");
        }
        if (Math.abs(f) >= CapsuleConfig.INSTANCE.getItemWidthMultiCard()) {
            capsuleScrollerHorizontal.getHostLayoutHelper().getAmbientState().setHorizontalBackFling(true);
        }
        int i = (int) f;
        if (i != ((int) capsuleScrollerHorizontal.finalXAmount)) {
            capsuleScrollerHorizontal.capsuleContainer.customOverScrollToX(i);
        }
    }
}
