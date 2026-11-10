package com.oplus.systemui.qs.widget;

import androidx.appcompat.widget.AppCompatTextView;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class AnimateChangeTextView$$ExternalSyntheticLambda1 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ AnimateChangeTextView f$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        AppCompatTextView appCompatTextView = this.f$0;
        int i = AnimateChangeTextView.$r8$clinit;
        appCompatTextView.setScaleX(1.0f);
        appCompatTextView.setScaleY(1.0f);
    }
}
