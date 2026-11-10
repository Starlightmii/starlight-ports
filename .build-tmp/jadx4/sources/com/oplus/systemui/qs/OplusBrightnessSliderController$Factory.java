package com.oplus.systemui.qs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.brightness.BrightnessSliderController;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.brightness.ui.BrightnessWarningToast;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.util.time.SystemClock;
import com.google.android.msdl.domain.MSDLPlayer;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.qs.slider.FlavorOneQsBrightnessSliderController;
import com.oplus.systemui.qs.slider.OplusQsBrightnessSliderController;
import com.oplus.systemui.qs.slider.OplusQsDetailBrightnessSliderController;
import com.oplus.systemui.qs.slider.OplusQsDetailToggleSliderLayout;
import com.oplus.systemui.qs.widget.OplusQsToggleSliderLayout;
import com.oplus.systemui.qs.widget.OplusToggleSliderView;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusBrightnessSliderController$Factory extends BrightnessSliderController.BrightnessSliderControllerFactory {
    private final ActivityStarter activityStarter;
    public final boolean isSupportVolumeSeekBar;
    private final SystemClock mClock;
    private final FalsingManager mFalsingManager;
    private final UiEventLogger mUiEventLogger;
    private final UserTracker mUserTracker;
    private final VibratorHelper mVibratorHelper;
    private final MSDLPlayer msdlPlayer;
    private final QSLogger qsLogger;
    private final OplusSeparateNotificationAndQSState separateStateManager;
    private final SysUIViewDialogInteractor viewDialogInteractor;
    private final BrightnessWarningToast warningToast;

    public OplusBrightnessSliderController$Factory(FalsingManager falsingManager, UiEventLogger uiEventLogger, VibratorHelper vibratorHelper, MSDLPlayer mSDLPlayer, SystemClock systemClock, ActivityStarter activityStarter, BrightnessWarningToast brightnessWarningToast, UserTracker userTracker, QSLogger qSLogger, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, SysUIViewDialogInteractor sysUIViewDialogInteractor) {
        super(falsingManager, uiEventLogger, vibratorHelper, mSDLPlayer, systemClock, activityStarter, brightnessWarningToast);
        this.mFalsingManager = falsingManager;
        this.mUiEventLogger = uiEventLogger;
        this.mVibratorHelper = vibratorHelper;
        this.msdlPlayer = mSDLPlayer;
        this.mClock = systemClock;
        this.activityStarter = activityStarter;
        this.warningToast = brightnessWarningToast;
        this.mUserTracker = userTracker;
        this.qsLogger = qSLogger;
        this.separateStateManager = oplusSeparateNotificationAndQSState;
        this.viewDialogInteractor = sysUIViewDialogInteractor;
        QSFeatureOption.INSTANCE.getClass();
        this.isSupportVolumeSeekBar = QSFeatureOption.isSupportVolumeSeekBar();
    }

    public IBrightnessSliderController create(Context context, ViewGroup viewGroup) {
        if (viewGroup instanceof OplusQsDetailToggleSliderLayout) {
            return new OplusQsDetailBrightnessSliderController((OplusQsDetailToggleSliderLayout) viewGroup, this.mFalsingManager, this.activityStarter, this.warningToast, this.qsLogger, this.separateStateManager, this.viewDialogInteractor);
        }
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
            return createForFlavorOne(context, viewGroup);
        }
        boolean zEquals = "brightness_dialog".equals(viewGroup != null ? viewGroup.getTag() : null);
        int layout = getLayout(zEquals);
        if (viewGroup instanceof OplusToggleSliderView) {
            return new OplusBrightnessSliderController((OplusToggleSliderView) viewGroup, this.mFalsingManager, this.mUserTracker, this.warningToast, this.separateStateManager);
        }
        if (viewGroup instanceof OplusQsToggleSliderLayout) {
            return new OplusQsBrightnessSliderController((OplusQsBaseToggleSliderLayout) viewGroup, this.mFalsingManager, this.activityStarter, this.warningToast, this.qsLogger, this.separateStateManager, this.viewDialogInteractor);
        }
        View viewInflate = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        if (((Boolean) this.separateStateManager.getEnableSeparateNotificationAndQS().getValue()).booleanValue() && (viewInflate instanceof OplusQsBaseToggleSliderLayout)) {
            return new OplusQsBrightnessSliderController((OplusQsBaseToggleSliderLayout) viewInflate, this.mFalsingManager, this.activityStarter, this.warningToast, this.qsLogger, this.separateStateManager, this.viewDialogInteractor);
        }
        return (!this.isSupportVolumeSeekBar || zEquals) ? new OplusBrightnessSliderController((OplusToggleSliderView) viewInflate.findViewById(2131362351), this.mFalsingManager, this.mUserTracker, this.warningToast, this.separateStateManager) : new OplusQsBrightnessSliderController((OplusQsBaseToggleSliderLayout) viewInflate.findViewById(2131362351), this.mFalsingManager, this.activityStarter, this.warningToast, this.qsLogger, this.separateStateManager, this.viewDialogInteractor);
    }

    public final IBrightnessSliderController createForFlavorOne(Context context, ViewGroup viewGroup) {
        boolean zEquals = "brightness_dialog".equals(viewGroup != null ? viewGroup.getTag() : null);
        int layout = getLayout(zEquals);
        if (viewGroup instanceof OplusToggleSliderView) {
            return new OplusBrightnessSliderController((OplusToggleSliderView) viewGroup, this.mFalsingManager, this.mUserTracker, this.warningToast, this.separateStateManager);
        }
        if (viewGroup instanceof OplusQsToggleSliderLayout) {
            return new FlavorOneQsBrightnessSliderController((OplusQsBaseToggleSliderLayout) viewGroup, this.mFalsingManager, this.activityStarter, this.warningToast, this.qsLogger, this.separateStateManager, this.viewDialogInteractor);
        }
        View viewInflate = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        if (((Boolean) this.separateStateManager.getEnableSeparateNotificationAndQS().getValue()).booleanValue() && (viewInflate instanceof OplusQsBaseToggleSliderLayout)) {
            return new FlavorOneQsBrightnessSliderController((OplusQsBaseToggleSliderLayout) viewInflate, this.mFalsingManager, this.activityStarter, this.warningToast, this.qsLogger, this.separateStateManager, this.viewDialogInteractor);
        }
        return (!this.isSupportVolumeSeekBar || zEquals) ? new OplusBrightnessSliderController((OplusToggleSliderView) viewInflate.findViewById(2131362351), this.mFalsingManager, this.mUserTracker, this.warningToast, this.separateStateManager) : new FlavorOneQsBrightnessSliderController((OplusQsBaseToggleSliderLayout) viewInflate.findViewById(2131362351), this.mFalsingManager, this.activityStarter, this.warningToast, this.qsLogger, this.separateStateManager, this.viewDialogInteractor);
    }

    public final int getLayout(boolean z) {
        if (!((Boolean) this.separateStateManager.getEnableSeparateNotificationAndQS().getValue()).booleanValue() || z) {
            return (!this.isSupportVolumeSeekBar || z) ? 2131559385 : 2131559329;
        }
        Integer brightnessMirrorLayout = ((OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class)).getBrightnessMirrorLayout();
        if (brightnessMirrorLayout != null) {
            return brightnessMirrorLayout.intValue();
        }
        return 2131559329;
    }
}
