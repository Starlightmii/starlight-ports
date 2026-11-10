package com.oplus.systemui.panelanimation;

import com.android.internal.dynamicanimation.animation.DynamicAnimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$1 implements DynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusPanelAnimationExImpl.PanelExpandedFraction this$0;

    public /* synthetic */ OplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$1(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.setRawFraction(f);
                break;
            default:
                OplusPanelAnimationExImpl.PanelExpandedFraction panelExpandedFraction = this.this$0;
                panelExpandedFraction.setBlurFraction(OplusPanelAnimationExImpl.PanelExpandedFraction.access$getBlurFraction(panelExpandedFraction, f));
                break;
        }
    }
}
