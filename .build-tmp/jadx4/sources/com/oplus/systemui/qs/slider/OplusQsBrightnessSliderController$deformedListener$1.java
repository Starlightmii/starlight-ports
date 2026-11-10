package com.oplus.systemui.qs.slider;

import android.view.View;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.qs.brightness.ToggleSlider;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.util.ViewController;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.coui.appcompat.seekbar.DeformedValueBean;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBrightnessSliderController$deformedListener$1 implements COUIVerticalSeekBar.OnDeformedListener {
    public /* synthetic */ OplusQsBrightnessSliderController this$0;

    public final void onHeightDeformedChanged(float f, float f2) {
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController = this.this$0;
        if (f == oplusQsBrightnessSliderController.topDeformed || f2 == oplusQsBrightnessSliderController.bottomDeformed) {
            return;
        }
        oplusQsBrightnessSliderController.topDeformed = f;
        oplusQsBrightnessSliderController.bottomDeformed = f2;
        oplusQsBrightnessSliderController.setDeformedTranslation(f, f2);
        ToggleSlider toggleSlider = oplusQsBrightnessSliderController.mMirror;
        if (toggleSlider != null) {
            toggleSlider.setDeformedTranslation(f, f2);
        }
    }

    public final void onScaleChanged(DeformedValueBean deformedValueBean) {
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController = this.this$0;
        float heightBottomDeformedUpValue = deformedValueBean.getHeightBottomDeformedUpValue();
        DeformedValueBean deformedValueBean2 = oplusQsBrightnessSliderController.deformedValueBean;
        if (Intrinsics.areEqual(heightBottomDeformedUpValue, deformedValueBean2 != null ? Float.valueOf(deformedValueBean2.getHeightBottomDeformedUpValue()) : null)) {
            float heightTopDeformedUpValue = deformedValueBean.getHeightTopDeformedUpValue();
            DeformedValueBean deformedValueBean3 = oplusQsBrightnessSliderController.deformedValueBean;
            if (Intrinsics.areEqual(heightTopDeformedUpValue, deformedValueBean3 != null ? Float.valueOf(deformedValueBean3.getHeightTopDeformedUpValue()) : null)) {
                float heightBottomDeformedDownValue = deformedValueBean.getHeightBottomDeformedDownValue();
                DeformedValueBean deformedValueBean4 = oplusQsBrightnessSliderController.deformedValueBean;
                if (Intrinsics.areEqual(heightBottomDeformedDownValue, deformedValueBean4 != null ? Float.valueOf(deformedValueBean4.getHeightBottomDeformedDownValue()) : null)) {
                    float heightTopDeformedDownValue = deformedValueBean.getHeightTopDeformedDownValue();
                    DeformedValueBean deformedValueBean5 = oplusQsBrightnessSliderController.deformedValueBean;
                    if (Intrinsics.areEqual(heightTopDeformedDownValue, deformedValueBean5 != null ? Float.valueOf(deformedValueBean5.getHeightTopDeformedDownValue()) : null)) {
                        return;
                    }
                }
            }
        }
        oplusQsBrightnessSliderController.deformedValueBean = deformedValueBean;
        QSLogger qSLogger = oplusQsBrightnessSliderController.qsLogger;
        String str = oplusQsBrightnessSliderController.tag;
        View view = ((ViewController) oplusQsBrightnessSliderController).mView;
        float scale = deformedValueBean.getScale();
        float heightBottomDeformedUpValue2 = deformedValueBean.getHeightBottomDeformedUpValue();
        float heightTopDeformedUpValue2 = deformedValueBean.getHeightTopDeformedUpValue();
        float heightBottomDeformedDownValue2 = deformedValueBean.getHeightBottomDeformedDownValue();
        float heightTopDeformedDownValue2 = deformedValueBean.getHeightTopDeformedDownValue();
        StringBuilder sb = new StringBuilder("onScaleChanged mView: ");
        sb.append(view);
        sb.append(" scale: ");
        sb.append(scale);
        sb.append(" heightBottomDeformedUpValue: ");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, heightBottomDeformedUpValue2, " heightTopDeformedUpValue: ", heightTopDeformedUpValue2, " heightBottomDeformedDownValue: ");
        sb.append(heightBottomDeformedDownValue2);
        sb.append(" heightTopDeformedDownValue: ");
        sb.append(heightTopDeformedDownValue2);
        qSLogger.d(str, sb.toString());
        ToggleSlider toggleSlider = oplusQsBrightnessSliderController.mMirror;
        if (toggleSlider != null) {
            toggleSlider.setDeformedParams(deformedValueBean);
        }
    }
}
