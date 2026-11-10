package com.oplus.systemui.notification.lockscreen.lightos;

import android.util.Log;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleLightOSManager$flingFadeoutAnimator$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ CapsuleLightOSManager this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        CapsuleLightOSManager capsuleLightOSManager = this.this$0;
        capsuleLightOSManager.stackStartAppearProgress = capsuleLightOSManager.capsuleProgress;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("flingFadeoutAnimator: EndValue=", f, ", stackStartAppearProgress:", capsuleLightOSManager.stackStartAppearProgress, "CapsuleListOSManager-->"));
        }
    }
}
