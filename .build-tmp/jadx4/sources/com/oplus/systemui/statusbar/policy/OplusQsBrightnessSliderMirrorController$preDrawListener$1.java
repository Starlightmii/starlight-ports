package com.oplus.systemui.statusbar.policy;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import com.android.systemui.qs.brightness.BrightnessMirrorController;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBrightnessSliderMirrorController$preDrawListener$1 implements ViewTreeObserver.OnPreDrawListener {
    public /* synthetic */ OplusQsBrightnessSliderMirrorController this$0;

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        Rect backgroundRect;
        Float backgroundRadius;
        IBrightnessSliderController iBrightnessSliderController = ((BrightnessMirrorController) this.this$0).mToggleSliderController;
        View view = iBrightnessSliderController != null ? iBrightnessSliderController.getView() : null;
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = view instanceof OplusQsBaseToggleSliderLayout ? (OplusQsBaseToggleSliderLayout) view : null;
        if (oplusQsBaseToggleSliderLayout != null && (backgroundRect = oplusQsBaseToggleSliderLayout.getBackgroundRect()) != null && (backgroundRadius = oplusQsBaseToggleSliderLayout.getBackgroundRadius()) != null) {
            float fFloatValue = backgroundRadius.floatValue();
            Float backgroundCornerWeight = oplusQsBaseToggleSliderLayout.getBackgroundCornerWeight();
            if (backgroundCornerWeight != null) {
                float fFloatValue2 = backgroundCornerWeight.floatValue();
                if (!Intrinsics.areEqual(this.this$0.getShadowConfig().getSliderBackgroundRect(), backgroundRect) || this.this$0.getShadowConfig().getRadius() != fFloatValue || this.this$0.getShadowConfig().getWeight() != fFloatValue2) {
                    this.this$0.getShadowConfig().getSliderBackgroundRect().set(backgroundRect);
                    this.this$0.getShadowConfig().setRadius(fFloatValue);
                    this.this$0.getShadowConfig().setWeight(fFloatValue2);
                    int dimension = (int) ((BrightnessMirrorController) this.this$0).mBrightnessMirror.getContext().getResources().getDimension(2131165742);
                    this.this$0.getShadowConfig().getRect().set(backgroundRect.left, backgroundRect.top + dimension, backgroundRect.right, dimension + backgroundRect.bottom);
                    ((BrightnessMirrorController) this.this$0).mBrightnessMirror.invalidateOutline();
                }
            }
        }
        return true;
    }
}
