package com.oplus.systemui.qs.slider;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.widget.BrightnessHighPowerConsumptionTextView;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlavorOneQsBrightnessSliderController$textViewChangedListener$1 implements View.OnLayoutChangeListener {
    public /* synthetic */ FlavorOneQsBrightnessSliderController this$0;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (new Rect(i, i2, i3, i4).equals(new Rect(i5, i6, i7, i8))) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "FlavorOneQsBrightnessSliderController-->updateTextViewMargin");
        }
        BrightnessHighPowerConsumptionTextView brightnessHighPowerConsumptionTextView = this.this$0.brightnessHighPowerConsumptionTextView;
        if (brightnessHighPowerConsumptionTextView != null) {
            FlavorOneQsBrightnessSliderController flavorOneQsBrightnessSliderController = this.this$0;
            boolean z = false;
            if (QSFragmentHelper.getInstance().getQsTileMediaContainer() != null) {
                if (QSFragmentHelper.getInstance().getQsTileMediaContainer().getMediaMode() && DeviceUtil.isLandscape(flavorOneQsBrightnessSliderController.getContext())) {
                    z = true;
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "FlavorOneQsBrightnessSliderController-->qsTileMediaContainer is null");
            }
            brightnessHighPowerConsumptionTextView.updateTextViewMargin(z);
        }
    }
}
