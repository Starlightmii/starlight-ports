package com.oplus.systemui.qs.slider;

import android.util.Log;
import android.view.View;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.statusbar.policy.OplusQsBrightnessSliderMirrorController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBrightnessSliderController$layoutChangedListener$1 implements View.OnLayoutChangeListener {
    public /* synthetic */ OplusQsBrightnessSliderController this$0;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i == i5 && i2 == i6 && i3 == i7 && i4 == i8) {
            return;
        }
        OplusQsBrightnessSliderMirrorController oplusQsBrightnessSliderMirrorController = this.this$0.mMirrorController;
        OplusQsBrightnessSliderMirrorController oplusQsBrightnessSliderMirrorController2 = oplusQsBrightnessSliderMirrorController instanceof OplusQsBrightnessSliderMirrorController ? oplusQsBrightnessSliderMirrorController : null;
        if (oplusQsBrightnessSliderMirrorController2 != null) {
            int measuredWidth = ((OplusQsBaseToggleSliderLayout) ((ViewController) this.this$0).mView).getMeasuredWidth();
            int measuredHeight = ((OplusQsBaseToggleSliderLayout) ((ViewController) this.this$0).mView).getMeasuredHeight();
            oplusQsBrightnessSliderMirrorController2.originalWidth = measuredWidth;
            oplusQsBrightnessSliderMirrorController2.originalHeight = measuredHeight;
            oplusQsBrightnessSliderMirrorController2.updateResources();
        }
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController = this.this$0;
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) ((ViewController) oplusQsBrightnessSliderController).mView;
        int value = oplusQsBrightnessSliderController.getValue();
        OplusQsBaseToggleSliderLayout.Companion companion = OplusQsBaseToggleSliderLayout.Companion;
        oplusQsBrightnessSliderController.distanceFromBottom = oplusQsBaseToggleSliderLayout.getDistanceFromBottom(value, -1);
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController2 = this.this$0;
        String str = oplusQsBrightnessSliderController2.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            IQSSpotLightConfig slider = ((OplusQsBaseToggleSliderLayout) ((ViewController) oplusQsBrightnessSliderController2).mView).getSlider();
            Integer numValueOf = slider != null ? Integer.valueOf(slider.getHeight()) : null;
            sbM.append("layoutChangedListener, height: " + numValueOf + ", distanceFromBottom:" + oplusQsBrightnessSliderController2.distanceFromBottom);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController3 = this.this$0;
        oplusQsBrightnessSliderController3.setBrightnessIconAnimator(oplusQsBrightnessSliderController3.getValue(), this.this$0.distanceFromBottom);
    }
}
