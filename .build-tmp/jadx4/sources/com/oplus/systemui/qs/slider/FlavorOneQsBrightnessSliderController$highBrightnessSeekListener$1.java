package com.oplus.systemui.qs.slider;

import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.util.ViewController;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.oplus.systemui.qs.base.seek.ClipBrightnessView;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.qs.widget.BrightnessHighPowerConsumptionTextView;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlavorOneQsBrightnessSliderController$highBrightnessSeekListener$1 implements COUIVerticalSeekBar.OnSeekBarChangeListener {
    public int lastProgress;
    public /* synthetic */ FlavorOneQsBrightnessSliderController this$0;

    public final void onProgressChanged(COUIVerticalSeekBar cOUIVerticalSeekBar, int i, boolean z) {
        BrightnessHighPowerConsumptionTextView brightnessHighPowerConsumptionTextView;
        FlavorOneQsBrightnessSliderController flavorOneQsBrightnessSliderController = this.this$0;
        boolean zIsHighBrightness = flavorOneQsBrightnessSliderController.brightnessControllerEx.isHighBrightness(i);
        if ((z || flavorOneQsBrightnessSliderController.getMIsMirror()) && (brightnessHighPowerConsumptionTextView = flavorOneQsBrightnessSliderController.brightnessHighPowerConsumptionTextView) != null) {
            brightnessHighPowerConsumptionTextView.updateText(zIsHighBrightness && i >= this.lastProgress);
        }
        ClipBrightnessView brightnessIcon = flavorOneQsBrightnessSliderController.getBrightnessIcon();
        if (brightnessIcon != null) {
            brightnessIcon.updateHighBrightnessState(zIsHighBrightness);
        }
        this.lastProgress = i;
    }

    public final void onStartTrackingTouch(COUIVerticalSeekBar cOUIVerticalSeekBar) {
        FlavorOneQsBrightnessSliderController flavorOneQsBrightnessSliderController = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(((OplusQsBaseToggleSliderLayout) ((ViewController) flavorOneQsBrightnessSliderController).mView).getProgress(), "onStartTrackingTouch,last progress=", "FlavorOneQsBrightnessSliderController-->", "SystemUi--QuickSettings");
        }
    }

    public final void onStopTrackingTouch(COUIVerticalSeekBar cOUIVerticalSeekBar) {
        FlavorOneQsBrightnessSliderController flavorOneQsBrightnessSliderController = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(((OplusQsBaseToggleSliderLayout) ((ViewController) flavorOneQsBrightnessSliderController).mView).getProgress(), "onStopTrackingTouch, final progress=", "FlavorOneQsBrightnessSliderController-->", "SystemUi--QuickSettings");
        }
        BrightnessHighPowerConsumptionTextView brightnessHighPowerConsumptionTextView = flavorOneQsBrightnessSliderController.brightnessHighPowerConsumptionTextView;
        if (brightnessHighPowerConsumptionTextView != null) {
            brightnessHighPowerConsumptionTextView.updateText(false);
        }
    }
}
