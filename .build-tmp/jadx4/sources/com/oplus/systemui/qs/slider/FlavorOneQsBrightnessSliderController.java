package com.oplus.systemui.qs.slider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.OplusBrightnessControllerEx;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.settings.brightness.ui.BrightnessWarningToast;
import com.android.systemui.util.ViewController;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.qs.widget.BrightnessHighPowerConsumptionTextView;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlavorOneQsBrightnessSliderController extends OplusQsBrightnessSliderController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "FlavorOneQsBrightnessSliderController";
    private OplusBrightnessControllerEx brightnessControllerEx;
    private BrightnessHighPowerConsumptionTextView brightnessHighPowerConsumptionTextView;
    private final COUIVerticalSeekBar.OnSeekBarChangeListener highBrightnessSeekListener;
    private final View.OnLayoutChangeListener textViewChangedListener;
    private final SysUIViewDialogInteractor viewDialogInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public FlavorOneQsBrightnessSliderController(OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout, FalsingManager falsingManager, ActivityStarter activityStarter, BrightnessWarningToast brightnessWarningToast, QSLogger qSLogger, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, SysUIViewDialogInteractor sysUIViewDialogInteractor) {
        super(oplusQsBaseToggleSliderLayout, falsingManager, activityStarter, brightnessWarningToast, qSLogger, oplusSeparateNotificationAndQSState, sysUIViewDialogInteractor);
        this.viewDialogInteractor = sysUIViewDialogInteractor;
        this.brightnessControllerEx = ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getBrightnessControllerEx();
        FlavorOneQsBrightnessSliderController$textViewChangedListener$1 flavorOneQsBrightnessSliderController$textViewChangedListener$1 = new FlavorOneQsBrightnessSliderController$textViewChangedListener$1();
        flavorOneQsBrightnessSliderController$textViewChangedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.textViewChangedListener = flavorOneQsBrightnessSliderController$textViewChangedListener$1;
        FlavorOneQsBrightnessSliderController$highBrightnessSeekListener$1 flavorOneQsBrightnessSliderController$highBrightnessSeekListener$1 = new FlavorOneQsBrightnessSliderController$highBrightnessSeekListener$1();
        flavorOneQsBrightnessSliderController$highBrightnessSeekListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.highBrightnessSeekListener = flavorOneQsBrightnessSliderController$highBrightnessSeekListener$1;
    }

    @Override // com.oplus.systemui.qs.slider.OplusQsBrightnessSliderController
    public final void onViewAttached() {
        super.onViewAttached();
        if (FlavorOneFeatureOption.INSTANCE.isBrightnessSecMaxSupported()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "FlavorOneQsBrightnessSliderController-->onViewAttached");
            }
            BrightnessHighPowerConsumptionTextView brightnessHighPowerConsumptionTextView = null;
            if (((ViewController) this).mView instanceof FrameLayout) {
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -1);
                layoutParams.bottomMargin = getResources().getDimensionPixelSize(2131172225);
                layoutParams.topMargin = getResources().getDimensionPixelSize(2131172227);
                layoutParams.gravity = 81;
                brightnessHighPowerConsumptionTextView = (BrightnessHighPowerConsumptionTextView) LayoutInflater.from(getContext()).inflate(2131558517, (ViewGroup) null);
                brightnessHighPowerConsumptionTextView.setLayoutParams(layoutParams);
            } else {
                LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "parent view is not frame layout, check this error!");
            }
            this.brightnessHighPowerConsumptionTextView = brightnessHighPowerConsumptionTextView;
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).addView(brightnessHighPowerConsumptionTextView);
            addOnSeekBarChangeListener(this.highBrightnessSeekListener);
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).addOnLayoutChangeListener(this.textViewChangedListener);
        }
    }

    @Override // com.oplus.systemui.qs.slider.OplusQsBrightnessSliderController
    public final void onViewDetached() {
        super.onViewDetached();
        if (FlavorOneFeatureOption.INSTANCE.isBrightnessSecMaxSupported()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "FlavorOneQsBrightnessSliderController-->onViewDeAttached");
            }
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).removeOnLayoutChangeListener(this.textViewChangedListener);
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).removeView(this.brightnessHighPowerConsumptionTextView);
            this.brightnessHighPowerConsumptionTextView = null;
            removeOnSeekBarChangeListener(this.highBrightnessSeekListener);
        }
    }

    @Override // com.oplus.systemui.qs.slider.OplusQsBrightnessSliderController
    public void setListening(boolean z) {
        super.setListening(z);
        if (z && FlavorOneFeatureOption.INSTANCE.isBrightnessSecMaxSupported()) {
            int maxBrightness = this.brightnessControllerEx.getMaxBrightness() - this.brightnessControllerEx.getBrightnessMin();
            if (getMax() != maxBrightness) {
                setMax(maxBrightness);
            }
            int brightness = this.brightnessControllerEx.getBrightness() - this.brightnessControllerEx.getBrightnessMin();
            setValue(brightness - 1, 0.0f, 0, 0L);
            setValue(brightness, 0.0f, 0, 0L);
        }
    }
}
