package com.oplus.systemui.qs.slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.animation.view.LaunchableFrameLayout;
import com.android.systemui.qs.brightness.BrightnessSliderController;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.android.systemui.qs.deprecated.brightness.BrightnessController;
import com.oplus.systemui.qs.base.seek.ClipBrightnessView;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.slider.detail.data.OplusQsSliderDetailTileRepository;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSBrightnessDialogController extends OplusQSBrightnessVolumeDialogController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSBrightnessDialogController";
    private final BrightnessController.Factory brightnessFactory;
    private final BrightnessSliderController.Factory brightnessSliderFactory;
    private IBrightnessSliderController fromSliderController;
    private OplusQsDetailBrightnessRootViewController secondaryViewController;
    private final seekBarChangeListener.1 seekBarChangeListener;
    private final OplusQsSliderDetailTileRepository sliderDetailTileRepository;

    public OplusQSBrightnessDialogController(SysUIViewDialogInteractor sysUIViewDialogInteractor, BrightnessSliderController.Factory factory, BrightnessController.Factory factory2, OplusQsSliderDetailTileRepository oplusQsSliderDetailTileRepository) {
        super(sysUIViewDialogInteractor);
        this.brightnessSliderFactory = factory;
        this.brightnessFactory = factory2;
        this.sliderDetailTileRepository = oplusQsSliderDetailTileRepository;
        seekBarChangeListener.1 r1 = new seekBarChangeListener.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        this.seekBarChangeListener = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.view.View, com.android.systemui.animation.view.LaunchableFrameLayout, com.oplus.systemui.qs.slider.OplusQsDetailRootView] */
    @Override // com.oplus.systemui.qs.slider.OplusQSBrightnessVolumeDialogController, com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public View createDialogView(Context context) {
        LaunchableFrameLayout secondaryView = getSecondaryView();
        if (secondaryView != null) {
            return secondaryView;
        }
        ?? r3 = (OplusQsDetailRootView) LayoutInflater.from(QsColorUtil.createDarkColorContext(context, QsColorUtil.isNeedDarkThemeColor(context, false))).inflate(getSecondaryViewLayout(), (ViewGroup) null, false);
        r3.setVisibility(4);
        OplusQsDetailBrightnessRootViewController oplusQsDetailBrightnessRootViewController = new OplusQsDetailBrightnessRootViewController((OplusQsDetailRootView) r3, this.sliderDetailTileRepository, this.brightnessSliderFactory, this.brightnessFactory, this.fromSliderController);
        oplusQsDetailBrightnessRootViewController.init();
        r3.getSlider().addOnSeekBarChangeListener(this.seekBarChangeListener);
        View expandableView = getExpandableView();
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = expandableView instanceof OplusQsBaseToggleSliderLayout ? (OplusQsBaseToggleSliderLayout) expandableView : null;
        View toggleView = oplusQsBaseToggleSliderLayout != null ? oplusQsBaseToggleSliderLayout.getToggleView() : null;
        ClipBrightnessView clipBrightnessView = toggleView instanceof ClipBrightnessView ? (ClipBrightnessView) toggleView : null;
        View toggleView2 = r3.getSlider().getToggleView();
        ClipBrightnessView clipBrightnessView2 = toggleView2 instanceof ClipBrightnessView ? (ClipBrightnessView) toggleView2 : null;
        if (clipBrightnessView2 != null && clipBrightnessView != null) {
            clipBrightnessView2.updateRotation(clipBrightnessView.getDegree());
        }
        setSecondaryView(r3);
        this.secondaryViewController = oplusQsDetailBrightnessRootViewController;
        return r3;
    }

    public final IBrightnessSliderController getFromSliderController() {
        return this.fromSliderController;
    }

    @Override // com.oplus.systemui.qs.slider.OplusQSBrightnessVolumeDialogController, com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public /* bridge */ /* synthetic */ int getHideContentViewId() {
        return super.getHideContentViewId();
    }

    @Override // com.oplus.systemui.qs.slider.OplusQSBrightnessVolumeDialogController, com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public String getViewTag() {
        return TAG;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager.AnimationCallBack
    public final void onAnimationEnd(boolean z, boolean z2) {
        OplusQsDetailBrightnessRootViewController.QSDetailBrightnessImp qsBrightness;
        OplusQsDetailBrightnessRootViewController oplusQsDetailBrightnessRootViewController = this.secondaryViewController;
        if (oplusQsDetailBrightnessRootViewController == null || (qsBrightness = oplusQsDetailBrightnessRootViewController.getQsBrightness()) == null) {
            return;
        }
        OplusQsDetailBrightnessRootViewController.QSDetailBrightnessImp qSDetailBrightnessImp = qsBrightness instanceof OplusQsDetailBrightnessRootViewController.QSDetailBrightnessImp ? qsBrightness : null;
        if (qSDetailBrightnessImp != null) {
            IBrightnessSliderController qsBrightnessSliderController = qSDetailBrightnessImp.getQsBrightnessSliderController();
            OplusQsDetailBrightnessSliderController oplusQsDetailBrightnessSliderController = qsBrightnessSliderController instanceof OplusQsDetailBrightnessSliderController ? (OplusQsDetailBrightnessSliderController) qsBrightnessSliderController : null;
            if (oplusQsDetailBrightnessSliderController != null) {
                oplusQsDetailBrightnessSliderController.setTouchFeedbackEnable(true);
            }
        }
    }

    @Override // com.oplus.systemui.qs.slider.OplusQSBrightnessVolumeDialogController, com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager.AnimationCallBack
    public final void onAnimationStart(boolean z) {
        OplusQsDetailBrightnessRootViewController.QSDetailBrightnessImp qsBrightness;
        super.onAnimationStart(z);
        OplusQsDetailBrightnessRootViewController oplusQsDetailBrightnessRootViewController = this.secondaryViewController;
        if (oplusQsDetailBrightnessRootViewController == null || (qsBrightness = oplusQsDetailBrightnessRootViewController.getQsBrightness()) == null) {
            return;
        }
        OplusQsDetailBrightnessRootViewController.QSDetailBrightnessImp qSDetailBrightnessImp = qsBrightness instanceof OplusQsDetailBrightnessRootViewController.QSDetailBrightnessImp ? qsBrightness : null;
        if (qSDetailBrightnessImp != null) {
            IBrightnessSliderController qsBrightnessSliderController = qSDetailBrightnessImp.getQsBrightnessSliderController();
            OplusQsDetailBrightnessSliderController oplusQsDetailBrightnessSliderController = qsBrightnessSliderController instanceof OplusQsDetailBrightnessSliderController ? (OplusQsDetailBrightnessSliderController) qsBrightnessSliderController : null;
            if (oplusQsDetailBrightnessSliderController != null) {
                oplusQsDetailBrightnessSliderController.setTouchFeedbackEnable(false);
            }
        }
    }

    @Override // com.oplus.systemui.qs.slider.OplusQSBrightnessVolumeDialogController, com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final void onDismiss() {
        OplusQsDetailToggleSliderLayout slider;
        OplusQsDetailRootView secondaryView = getSecondaryView();
        if (secondaryView != null && (slider = secondaryView.getSlider()) != null) {
            slider.removeOnSeekBarChangeListener(this.seekBarChangeListener);
        }
        super.onDismiss();
        OplusQsDetailBrightnessRootViewController oplusQsDetailBrightnessRootViewController = this.secondaryViewController;
        if (oplusQsDetailBrightnessRootViewController != null) {
            oplusQsDetailBrightnessRootViewController.destroy();
        }
        this.secondaryViewController = null;
        this.fromSliderController = null;
    }

    public final void setFromSliderController(IBrightnessSliderController iBrightnessSliderController) {
        this.fromSliderController = iBrightnessSliderController;
    }
}
