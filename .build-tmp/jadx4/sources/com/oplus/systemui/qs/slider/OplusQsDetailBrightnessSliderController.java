package com.oplus.systemui.qs.slider;

import android.view.View;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.settings.brightness.ui.BrightnessWarningToast;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsDetailBrightnessSliderController extends OplusQsBrightnessSliderController {
    private final OplusQsDetailHookClipBrightnessView hookBrightnessIcon;
    public boolean touchFeedbackEnable;

    public OplusQsDetailBrightnessSliderController(OplusQsDetailToggleSliderLayout oplusQsDetailToggleSliderLayout, FalsingManager falsingManager, ActivityStarter activityStarter, BrightnessWarningToast brightnessWarningToast, QSLogger qSLogger, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, SysUIViewDialogInteractor sysUIViewDialogInteractor) {
        super(oplusQsDetailToggleSliderLayout, falsingManager, activityStarter, brightnessWarningToast, qSLogger, oplusSeparateNotificationAndQSState, sysUIViewDialogInteractor);
        View hookToggleView = oplusQsDetailToggleSliderLayout != null ? oplusQsDetailToggleSliderLayout.getHookToggleView() : null;
        this.hookBrightnessIcon = hookToggleView instanceof OplusQsDetailHookClipBrightnessView ? (OplusQsDetailHookClipBrightnessView) hookToggleView : null;
        this.touchFeedbackEnable = true;
    }

    public final boolean getTouchFeedbackEnable() {
        return this.touchFeedbackEnable;
    }

    @Override // com.oplus.systemui.qs.slider.OplusQsBrightnessSliderController
    public final boolean isNeedtouchFeekback() {
        return this.isListening && this.touchFeedbackEnable;
    }

    public final void setTouchFeedbackEnable(boolean z) {
        this.touchFeedbackEnable = z;
    }

    @Override // com.oplus.systemui.qs.slider.OplusQsBrightnessSliderController
    public final void updateIconRotation(float f, int i) {
        OplusQsDetailHookClipBrightnessView oplusQsDetailHookClipBrightnessView = this.hookBrightnessIcon;
        if (oplusQsDetailHookClipBrightnessView != null) {
            oplusQsDetailHookClipBrightnessView.updateRotation(f);
        }
    }

    @Override // com.oplus.systemui.qs.slider.OplusQsBrightnessSliderController
    public final void updateIconRotationWithAnimation(float f, float f2, int i) {
        OplusQsDetailHookClipBrightnessView oplusQsDetailHookClipBrightnessView = this.hookBrightnessIcon;
        if (oplusQsDetailHookClipBrightnessView != null) {
            oplusQsDetailHookClipBrightnessView.updateRotationAnimation(f, f2, i);
        }
    }
}
