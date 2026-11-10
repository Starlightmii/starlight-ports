package com.oplus.systemui.plugins.qs.animator;

import com.android.systemui.shade.NotificationPanelViewController$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSScenarioPullAnimator$pullToScenarioAnimation$2$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ QSScenarioPullAnimator this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        QSScenarioPullAnimator qSScenarioPullAnimator = this.this$0;
        if ((f == 0.0f || f == 1.0f) && !qSScenarioPullAnimator.isTracking) {
            qSScenarioPullAnimator.notifyScenarioAnimEnd(f);
        }
        boolean z2 = qSScenarioPullAnimator.isTracking;
        StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("pullToScenarioAnimation end, isCanceled=", z, ", currValue=", f, " isTracking=");
        sbM.append(z2);
        LogUtil.i$default(LogUtil.TAG_QS, "QSScenarioPullAnimator", sbM.toString(), null, 8);
    }
}
