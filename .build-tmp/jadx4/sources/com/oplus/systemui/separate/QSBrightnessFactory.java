package com.oplus.systemui.separate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSBrightness;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.qs.brightness.BrightnessMirrorController;
import com.android.systemui.qs.brightness.BrightnessMirrorHandler;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.android.systemui.qs.brightness.MirrorController;
import com.android.systemui.qs.deprecated.brightness.BrightnessController;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.settings.brightness.ui.BrightnessWarningToast;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.qs.slider.FlavorOneQsBrightnessSliderController;
import com.oplus.systemui.qs.slider.OplusQsBrightnessSliderController;
import com.oplus.systemui.qs.slider.OplusQsDetailBrightnessSliderController;
import com.oplus.systemui.qs.slider.OplusQsDetailToggleSliderLayout;
import com.oplus.systemui.statusbar.policy.OplusBrightnessMirrorController;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSBrightnessFactory {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QSBrightnessFactory";
    private BrightnessMirrorController brightnessMirrorController;
    public boolean brightnessMirrorVisible;
    private final Context context;
    private final BrightnessController.Factory qsBrightnessControllerFactory;
    private final QSBrightnessSliderControllerFactory qsBrightnessSliderControllerFactory;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class QSBrightnessImpl implements QSBrightness {
        private final BrightnessController brightnessController;
        private final BrightnessMirrorHandler brightnessMirrorHandler;
        private final View.OnClickListener clickListener;
        private final Context context;
        private final View.OnLongClickListener longClickListener;
        private final OplusQsBrightnessSliderController qsBrightnessSliderController;
        private final OplusQsBaseToggleSliderLayout sliderLayout;
        private final ViewGroup viewGroup;

        public QSBrightnessImpl(Context context, ViewGroup viewGroup, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
            this.context = context;
            this.viewGroup = viewGroup;
            this.clickListener = onClickListener;
            this.longClickListener = onLongClickListener;
            OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = viewGroup instanceof OplusQsBaseToggleSliderLayout ? (OplusQsBaseToggleSliderLayout) viewGroup : null;
            this.sliderLayout = oplusQsBaseToggleSliderLayout;
            OplusQsBrightnessSliderController oplusQsBrightnessSliderControllerCreate = QSBrightnessFactory.this.qsBrightnessSliderControllerFactory.create(oplusQsBaseToggleSliderLayout);
            this.qsBrightnessSliderController = oplusQsBrightnessSliderControllerCreate;
            BrightnessController brightnessControllerCreate = QSBrightnessFactory.this.qsBrightnessControllerFactory.create(oplusQsBrightnessSliderControllerCreate);
            this.brightnessController = brightnessControllerCreate;
            this.brightnessMirrorHandler = new BrightnessMirrorHandler(brightnessControllerCreate);
        }

        public final void checkRestrictionAndSetEnabled() {
            this.brightnessController.checkRestrictionAndSetEnabled();
        }

        public final void doDestroy() {
            this.qsBrightnessSliderController.destroy();
        }

        public final void doInit() {
            this.qsBrightnessSliderController.init();
            this.qsBrightnessSliderController.setOnLayoutClickListener(this.clickListener);
            this.qsBrightnessSliderController.setLongClickListener(this.longClickListener);
            LogUtil.i$default(LogUtil.TAG_QS, QSBrightnessFactory.TAG, "doInit sliderLayout:" + this.sliderLayout + " qsBrightnessSliderController:" + this.qsBrightnessSliderController + " brightnessController:" + this.brightnessController + " brightnessMirrorHandler:" + this.brightnessMirrorHandler + " ", null, 8);
        }

        public final View.OnClickListener getClickListener() {
            return this.clickListener;
        }

        public final Context getContext() {
            return this.context;
        }

        public final View.OnLongClickListener getLongClickListener() {
            return this.longClickListener;
        }

        public final OplusQsBrightnessSliderController getQsBrightnessSliderController() {
            return this.qsBrightnessSliderController;
        }

        public final ViewGroup getViewGroup() {
            return this.viewGroup;
        }

        public final void onQsPanelAttached() {
            this.brightnessMirrorHandler.setController(QSBrightnessFactory.this.brightnessMirrorController);
            this.brightnessMirrorHandler.onQsPanelAttached();
        }

        public final void onQsPanelDetached() {
            this.brightnessMirrorHandler.setController((MirrorController) null);
            this.brightnessMirrorHandler.onQsPanelDettached();
        }

        public final void refreshEnableState() {
            this.qsBrightnessSliderController.refreshEnableState();
        }

        public final void registerBrightnessObserver() {
            this.brightnessController.registerBrightnessObserver();
        }

        public final void registerCallbacks() {
            this.brightnessController.registerCallbacks();
        }

        public void setIsEditable(boolean z) {
            this.qsBrightnessSliderController.setIsEditable(z);
        }

        public void setListening(boolean z) {
            MirrorController mirrorController;
            LogUtil.i$default(LogUtil.TAG_QS, QSBrightnessFactory.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setListening listening:", z), null, 8);
            this.qsBrightnessSliderController.setListening(z);
            if (z || !QSBrightnessFactory.this.brightnessMirrorVisible || (mirrorController = this.brightnessMirrorHandler.getMirrorController()) == null) {
                return;
            }
            mirrorController.hideMirrorDirect();
        }

        public void setShowMirrorListener(QSBrightness.ShowMirrorListener showMirrorListener) {
            QSBrightnessFactory qSBrightnessFactory = QSBrightnessFactory.this;
            if (qSBrightnessFactory.brightnessMirrorController instanceof OplusBrightnessMirrorController) {
                ((OplusBrightnessMirrorController) qSBrightnessFactory.brightnessMirrorController).setShowMirrorListener(showMirrorListener);
            }
        }

        public final void unregisterBrightnessObserver() {
            this.brightnessController.unregisterBrightnessObserver();
        }

        public final void unregisterCallbacks() {
            this.brightnessController.unregisterCallbacks();
        }

        public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
            this.qsBrightnessSliderController.updateColor(colorType);
        }

        public final void updateSliderColor(boolean z) {
            IBrightnessSliderController.updateSliderColor$default(this.qsBrightnessSliderController, z, (QSSpecialModeUpdateMonitor.ColorType) null, 2);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class QSBrightnessSliderControllerFactory {
        private final ActivityStarter activityStarter;
        private final FalsingManager falsingManager;
        private final QSLogger qSLogger;
        private final OplusSeparateNotificationAndQSState separateStateManager;
        private final SysUIViewDialogInteractor viewDialogInteractor;
        private final BrightnessWarningToast warningToast;

        public QSBrightnessSliderControllerFactory(FalsingManager falsingManager, ActivityStarter activityStarter, BrightnessWarningToast brightnessWarningToast, QSLogger qSLogger, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, SysUIViewDialogInteractor sysUIViewDialogInteractor) {
            this.falsingManager = falsingManager;
            this.activityStarter = activityStarter;
            this.warningToast = brightnessWarningToast;
            this.qSLogger = qSLogger;
            this.separateStateManager = oplusSeparateNotificationAndQSState;
            this.viewDialogInteractor = sysUIViewDialogInteractor;
        }

        public final OplusQsBrightnessSliderController create(OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout) {
            if (oplusQsBaseToggleSliderLayout instanceof OplusQsDetailToggleSliderLayout) {
                return new OplusQsDetailBrightnessSliderController((OplusQsDetailToggleSliderLayout) oplusQsBaseToggleSliderLayout, this.falsingManager, this.activityStarter, this.warningToast, this.qSLogger, this.separateStateManager, this.viewDialogInteractor);
            }
            FlavorOneFeatureOption.INSTANCE.getClass();
            boolean zIsFlavorOneDevice = FlavorOneFeatureOption.isFlavorOneDevice();
            FalsingManager falsingManager = this.falsingManager;
            ActivityStarter activityStarter = this.activityStarter;
            return zIsFlavorOneDevice ? new FlavorOneQsBrightnessSliderController(oplusQsBaseToggleSliderLayout, falsingManager, activityStarter, this.warningToast, this.qSLogger, this.separateStateManager, this.viewDialogInteractor) : new OplusQsBrightnessSliderController(oplusQsBaseToggleSliderLayout, falsingManager, activityStarter, this.warningToast, this.qSLogger, this.separateStateManager, this.viewDialogInteractor);
        }
    }

    public QSBrightnessFactory(Context context, QSBrightnessSliderControllerFactory qSBrightnessSliderControllerFactory, BrightnessController.Factory factory) {
        this.context = context;
        this.qsBrightnessSliderControllerFactory = qSBrightnessSliderControllerFactory;
        this.qsBrightnessControllerFactory = factory;
    }

    public final QSBrightness bindQSBrightness(ViewGroup viewGroup, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "bindQSBrightness viewGroup:" + viewGroup, null, 8);
        return new QSBrightnessImpl(this.context, viewGroup, onClickListener, onLongClickListener);
    }

    public final void setBrightnessMirrorController(BrightnessMirrorController brightnessMirrorController) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "setBrightnessMirrorController: " + brightnessMirrorController, null, 8);
        BrightnessMirrorController brightnessMirrorController2 = this.brightnessMirrorController;
        if (brightnessMirrorController2 instanceof OplusBrightnessMirrorController) {
            ((OplusBrightnessMirrorController) brightnessMirrorController2).setShowMirrorListener(null);
        }
        this.brightnessMirrorController = brightnessMirrorController;
    }

    public final void setBrightnessMirrorVisible(boolean z) {
        this.brightnessMirrorVisible = z;
    }
}
