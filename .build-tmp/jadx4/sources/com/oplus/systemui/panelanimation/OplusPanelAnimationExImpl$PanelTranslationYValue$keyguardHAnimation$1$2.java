package com.oplus.systemui.panelanimation;

import com.android.internal.dynamicanimation.animation.DynamicAnimation;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$2 implements DynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusPanelAnimationExImpl.PanelTranslationYValue this$0;

    public /* synthetic */ OplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$2(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("keyguardHAnimation onAnimationEnd, value=", f, "PanelAnimationEx-->", "SystemUi--Notification");
                }
                this.this$0.setMblock(null);
                this.this$0.isExpandAnimationRunning = false;
                break;
            default:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("translationYAnimation onAnimationEnd, value=", f, "PanelAnimationEx-->", "SystemUi--Notification");
                }
                this.this$0.isExpandAnimationRunning = false;
                break;
        }
    }
}
