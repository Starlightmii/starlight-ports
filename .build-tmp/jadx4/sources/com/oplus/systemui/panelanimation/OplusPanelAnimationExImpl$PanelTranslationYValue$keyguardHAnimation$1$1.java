package com.oplus.systemui.panelanimation;

import com.android.internal.dynamicanimation.animation.DynamicAnimation;
import com.android.internal.dynamicanimation.animation.SpringAnimation;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$1 implements DynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusPanelAnimationExImpl.PanelTranslationYValue this$0;

    public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                Function2 mblock = this.this$0.getMblock();
                if (mblock != null) {
                    mblock.invoke(Float.valueOf(f), (SpringAnimation) dynamicAnimation);
                }
                break;
            default:
                this.this$0.setTranslationYValue(f);
                break;
        }
    }
}
