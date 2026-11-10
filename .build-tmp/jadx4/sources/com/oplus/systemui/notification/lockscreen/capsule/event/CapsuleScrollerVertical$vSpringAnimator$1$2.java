package com.oplus.systemui.notification.lockscreen.capsule.event;

import android.util.Log;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleScrollerVertical$vSpringAnimator$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ CapsuleScrollerVertical this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        CapsuleScrollerVertical capsuleScrollerVertical = this.this$0;
        capsuleScrollerVertical.lastVSpringVelocity = f2;
        if (capsuleScrollerVertical.isDragger) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("V_SPRING, cancelled:", z, ", EndValue=", f, " velocity=");
            sbM.append(f2);
            sbM.append(" ");
            Log.d("SystemUi--Notification", "CapsuleScrollerVertical-->".concat(sbM.toString()));
        }
        capsuleScrollerVertical.onVerticalFlingEnd();
    }
}
