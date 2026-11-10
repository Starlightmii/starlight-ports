package com.oplus.systemui.notification.lockscreen.lightos;

import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleLightOSManager$flingFadeoutAnimator$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ CapsuleLightOSManager this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("flingFadeoutAnimator: NewValue=", f, "CapsuleListOSManager-->", "SystemUi--Notification");
        }
        this.this$0.updateCapsuleNSSLAlpha(f);
    }
}
