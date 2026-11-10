package com.oplus.systemui.panelanimation;

import androidx.compose.animation.core.MonoSpline$;
import com.android.internal.dynamicanimation.animation.DynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$2 implements DynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusPanelAnimationExImpl.PanelExpandedFraction this$0;

    public /* synthetic */ OplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$2(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("fractionAnimation onAnimationEnd, value=", f, " rawFraction=", this.this$0.getRawFraction(), " ");
                sbM.append(z);
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "PanelAnimationEx", sbM.toString(), null, 8);
                OplusPanelAnimationExImpl.PanelExpandedFraction.access$notifyFractionEnd(this.this$0, f);
                break;
            default:
                StringBuilder sbM2 = MonoSpline$.ExternalSyntheticOutline0.m("blurFactionAnimation onAnimationEnd, value=", f, " blurFraction=", this.this$0.getBlurFraction(), " ");
                sbM2.append(z);
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "PanelAnimationEx", sbM2.toString(), null, 8);
                break;
        }
    }
}
