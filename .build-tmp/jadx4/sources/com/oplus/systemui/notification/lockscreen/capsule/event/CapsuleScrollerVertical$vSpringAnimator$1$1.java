package com.oplus.systemui.notification.lockscreen.capsule.event;

import android.util.Log;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.config.CapsuleDebug;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleScrollerVertical$vSpringAnimator$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ CapsuleScrollerVertical this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        CapsuleScrollerVertical capsuleScrollerVertical = this.this$0;
        capsuleScrollerVertical.lastVSpringVelocity = f2;
        if (CapsuleDebug.INSTANCE.getLogSwitchItemViewUpdate() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleScrollerVertical-->".concat("V_SPRING," + capsuleScrollerVertical.isDragger + " NewValue=" + f));
        }
        if (!capsuleScrollerVertical.isDragger) {
            CapsuleConfig.INSTANCE.getClass();
            if (!CapsuleConfig.isLightOs || !capsuleScrollerVertical.isLightOsFling) {
                capsuleScrollerVertical.capsuleContainerController.customOverScrollToY(-f);
                return;
            }
        }
        CapsuleScrollerVertical.access$scrollToDiff(capsuleScrollerVertical, f);
    }
}
