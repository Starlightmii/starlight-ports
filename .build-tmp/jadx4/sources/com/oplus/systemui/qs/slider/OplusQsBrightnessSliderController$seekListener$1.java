package com.oplus.systemui.qs.slider;

import android.util.Log;
import android.view.View;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.qs.brightness.MirrorController;
import com.android.systemui.qs.brightness.ToggleSlider;
import com.android.systemui.qs.deprecated.brightness.BrightnessController;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.util.ViewController;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.oplus.systemui.qs.base.seek.ClipBrightnessView;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.utils.Util;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBrightnessSliderController$seekListener$1 implements COUIVerticalSeekBar.OnSeekBarChangeListener {
    public /* synthetic */ OplusQsBrightnessSliderController this$0;

    public final void onProgressChanged(COUIVerticalSeekBar cOUIVerticalSeekBar, int i, boolean z) {
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController = this.this$0;
        QSLogger qSLogger = oplusQsBrightnessSliderController.qsLogger;
        int mLastProgress = oplusQsBrightnessSliderController.getMLastProgress();
        boolean mTracking = oplusQsBrightnessSliderController.getMTracking();
        View view = ((ViewController) oplusQsBrightnessSliderController).mView;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("OplusQsBrightnessSliderController: onProgressChanged: progress=", i, mLastProgress, ", mLastProgress=", " fromUser=");
        KeyguardLogger$.ExternalSyntheticOutline0.m(", mTracking=", ", mView=", sbM, z, mTracking);
        sbM.append(view);
        sbM.append(" ");
        qSLogger.d(sbM.toString());
        if (oplusQsBrightnessSliderController.getMLastProgress() == i) {
            return;
        }
        ToggleSlider toggleSlider = oplusQsBrightnessSliderController.mMirror;
        if (toggleSlider != null) {
            toggleSlider.setValue(oplusQsBrightnessSliderController.getValue(), 0.0f, 0, 0L);
        }
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) ((ViewController) oplusQsBrightnessSliderController).mView;
        OplusQsBaseToggleSliderLayout.Companion companion = OplusQsBaseToggleSliderLayout.Companion;
        int distanceFromBottom = oplusQsBaseToggleSliderLayout.getDistanceFromBottom(i, -1);
        oplusQsBrightnessSliderController.distanceFromBottom = distanceFromBottom;
        if (((OplusQsBaseToggleSliderLayout) ((ViewController) oplusQsBrightnessSliderController).mView).isTouchDelegate) {
            oplusQsBrightnessSliderController.setBrightnessIconAnimator(i, distanceFromBottom);
        }
        if (z) {
            oplusQsBrightnessSliderController.setBrightnessIconAnimator(i, oplusQsBrightnessSliderController.distanceFromBottom);
            oplusQsBrightnessSliderController.setLatestBrightnessRequest(i);
            BrightnessController brightnessController = oplusQsBrightnessSliderController.mListener;
            if (brightnessController != null) {
                brightnessController.onChanged(i, oplusQsBrightnessSliderController.getMTracking(), false);
            }
        } else {
            ClipBrightnessView brightnessIcon = oplusQsBrightnessSliderController.getBrightnessIcon();
            if (brightnessIcon != null) {
                brightnessIcon.updateIconColorWidthMarginBottom(oplusQsBrightnessSliderController.distanceFromBottom);
            }
        }
        oplusQsBrightnessSliderController.setMLastProgress(i);
        for (COUIVerticalSeekBar.OnSeekBarChangeListener onSeekBarChangeListener : oplusQsBrightnessSliderController.seekBarListener) {
            if (onSeekBarChangeListener != null) {
                onSeekBarChangeListener.onProgressChanged(cOUIVerticalSeekBar, i, z);
            }
        }
    }

    public final void onStartTrackingTouch(COUIVerticalSeekBar cOUIVerticalSeekBar) {
        MirrorController mirrorController;
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController = this.this$0;
        String str = oplusQsBrightnessSliderController.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append(" onStartTrackingTouch, mTracking=" + oplusQsBrightnessSliderController.getMTracking() + ", mListener=" + oplusQsBrightnessSliderController.mListener + ", mMirrorController=" + oplusQsBrightnessSliderController.mMirrorController);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        oplusQsBrightnessSliderController.setMTracking(true);
        oplusQsBrightnessSliderController.setNeedResetLastAnimation(true);
        oplusQsBrightnessSliderController.latestUpdateBrightness = -1;
        BrightnessController brightnessController = oplusQsBrightnessSliderController.mListener;
        if (brightnessController != null) {
            brightnessController.onChanged(oplusQsBrightnessSliderController.getValue(), true, false);
        }
        if (!((OplusQsBaseToggleSliderLayout) ((ViewController) oplusQsBrightnessSliderController).mView).isTouchDelegate && (mirrorController = oplusQsBrightnessSliderController.mMirrorController) != null) {
            mirrorController.showMirror(((ViewController) oplusQsBrightnessSliderController).mView);
        }
        oplusQsBrightnessSliderController.mStartBrightnessValue = String.valueOf(oplusQsBrightnessSliderController.getValue());
        for (COUIVerticalSeekBar.OnSeekBarChangeListener onSeekBarChangeListener : oplusQsBrightnessSliderController.seekBarListener) {
            if (onSeekBarChangeListener != null) {
                onSeekBarChangeListener.onStartTrackingTouch(cOUIVerticalSeekBar);
            }
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isBrightnessUxSupport()) {
            Util.setBrightnessUx(oplusQsBrightnessSliderController.getContext().getContentResolver(), true);
        }
    }

    public final void onStopTrackingTouch(COUIVerticalSeekBar cOUIVerticalSeekBar) {
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController = this.this$0;
        String str = oplusQsBrightnessSliderController.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("onStopTrackingTouch, mTracking = " + oplusQsBrightnessSliderController.getMTracking() + ", mView=" + ((ViewController) oplusQsBrightnessSliderController).mView);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        if (oplusQsBrightnessSliderController.getMTracking()) {
            oplusQsBrightnessSliderController.setMTracking(false);
            BrightnessController brightnessController = oplusQsBrightnessSliderController.mListener;
            if (brightnessController != null) {
                brightnessController.onChanged(oplusQsBrightnessSliderController.getValue(), false, true);
            }
            MirrorController mirrorController = oplusQsBrightnessSliderController.mMirrorController;
            if (mirrorController != null) {
                mirrorController.hideMirror();
            }
            QsStatisticUtil.collectBrightnessChangeTypeEvent(oplusQsBrightnessSliderController.getContext(), "brightness_change_click", oplusQsBrightnessSliderController.mStartBrightnessValue, String.valueOf(oplusQsBrightnessSliderController.getValue()), ((Boolean) oplusQsBrightnessSliderController.separateStateManager.getEnableSeparateNotificationAndQS().getValue()).booleanValue());
            for (COUIVerticalSeekBar.OnSeekBarChangeListener onSeekBarChangeListener : oplusQsBrightnessSliderController.seekBarListener) {
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onStopTrackingTouch(cOUIVerticalSeekBar);
                }
            }
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isBrightnessUxSupport()) {
                Util.setBrightnessUx(oplusQsBrightnessSliderController.getContext().getContentResolver(), false);
            }
        }
    }
}
