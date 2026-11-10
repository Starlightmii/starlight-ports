package com.oplus.systemui.qs.slider;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.animation.view.LaunchableFrameLayout;
import com.android.systemui.plugins.qs.QSVolume;
import com.android.systemui.settings.UserTracker;
import com.oplus.systemui.qs.base.seek.OplusQsVolumeSliderController;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.slider.detail.data.OplusQsSliderDetailTileRepository;
import com.oplus.systemui.qs.slider.detail.data.OplusQsVolumeDetailTileController;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSVolumeDialogController extends OplusQSBrightnessVolumeDialogController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSVolumeDialogController";
    private final Handler bgHandler;
    private OplusQsVolumeSliderController fromSliderController;
    public QSVolume qsVolume;
    private OplusQsDetailVolumeRootViewController secondaryViewController;
    private final OplusQsSliderDetailTileRepository sliderDetailTileRepository;
    private final userContextProvider.1 userContextProvider;
    private final UserTracker userTracker;
    private final OplusQsVolumeDetailTileController volumeDetailTileController;

    public OplusQSVolumeDialogController(SysUIViewDialogInteractor sysUIViewDialogInteractor, Handler handler, UserTracker userTracker, OplusQsSliderDetailTileRepository oplusQsSliderDetailTileRepository, OplusQsVolumeDetailTileController oplusQsVolumeDetailTileController) {
        super(sysUIViewDialogInteractor);
        this.bgHandler = handler;
        this.userTracker = userTracker;
        this.sliderDetailTileRepository = oplusQsSliderDetailTileRepository;
        this.volumeDetailTileController = oplusQsVolumeDetailTileController;
        userContextProvider.1 r1 = new userContextProvider.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userContextProvider = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.view.View, com.android.systemui.animation.view.LaunchableFrameLayout, com.oplus.systemui.qs.slider.OplusQsDetailRootView] */
    @Override // com.oplus.systemui.qs.slider.OplusQSBrightnessVolumeDialogController, com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public View createDialogView(Context context) {
        LaunchableFrameLayout secondaryView = getSecondaryView();
        if (secondaryView != null) {
            return secondaryView;
        }
        ?? r4 = (OplusQsDetailRootView) LayoutInflater.from(QsColorUtil.createDarkColorContext(context, QsColorUtil.isNeedDarkThemeColor(context, false))).inflate(getSecondaryViewLayout(), (ViewGroup) null, false);
        r4.setVisibility(4);
        View expandableView = getExpandableView();
        OplusQsDetailVolumeRootViewController oplusQsDetailVolumeRootViewController = new OplusQsDetailVolumeRootViewController((OplusQsDetailRootView) r4, this.sliderDetailTileRepository, expandableView != null ? expandableView.getHeight() : 0, getQsVolume(), this.bgHandler, this.userContextProvider, this.volumeDetailTileController);
        oplusQsDetailVolumeRootViewController.init();
        setSecondaryView(r4);
        this.secondaryViewController = oplusQsDetailVolumeRootViewController;
        return r4;
    }

    public final OplusQsVolumeSliderController getFromSliderController() {
        return this.fromSliderController;
    }

    @Override // com.oplus.systemui.qs.slider.OplusQSBrightnessVolumeDialogController, com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public /* bridge */ /* synthetic */ int getHideContentViewId() {
        return super.getHideContentViewId();
    }

    public final QSVolume getQsVolume() {
        QSVolume qSVolume = this.qsVolume;
        if (qSVolume != null) {
            return qSVolume;
        }
        return null;
    }

    @Override // com.oplus.systemui.qs.slider.OplusQSBrightnessVolumeDialogController, com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public String getViewTag() {
        return TAG;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager.AnimationCallBack
    public final void onAnimationEnd(boolean z, boolean z2) {
        OplusQsDetailVolumeSliderController detailVolumeSliderController;
        OplusQsDetailVolumeRootViewController oplusQsDetailVolumeRootViewController = this.secondaryViewController;
        if (oplusQsDetailVolumeRootViewController == null || (detailVolumeSliderController = oplusQsDetailVolumeRootViewController.getDetailVolumeSliderController()) == null) {
            return;
        }
        detailVolumeSliderController.setTouchFeedbackEnable(true);
    }

    @Override // com.oplus.systemui.qs.slider.OplusQSBrightnessVolumeDialogController, com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager.AnimationCallBack
    public final void onAnimationStart(boolean z) {
        OplusQsDetailVolumeSliderController detailVolumeSliderController;
        super.onAnimationStart(z);
        OplusQsDetailVolumeRootViewController oplusQsDetailVolumeRootViewController = this.secondaryViewController;
        if (oplusQsDetailVolumeRootViewController == null || (detailVolumeSliderController = oplusQsDetailVolumeRootViewController.getDetailVolumeSliderController()) == null) {
            return;
        }
        detailVolumeSliderController.setTouchFeedbackEnable(false);
    }

    @Override // com.oplus.systemui.qs.slider.OplusQSBrightnessVolumeDialogController, com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final void onDismiss() {
        super.onDismiss();
        OplusQsVolumeSliderController oplusQsVolumeSliderController = this.fromSliderController;
        if (oplusQsVolumeSliderController != null) {
            oplusQsVolumeSliderController.setAllowProgressUpdate(1000L);
        }
        this.fromSliderController = null;
        OplusQsDetailVolumeRootViewController oplusQsDetailVolumeRootViewController = this.secondaryViewController;
        if (oplusQsDetailVolumeRootViewController != null) {
            oplusQsDetailVolumeRootViewController.destroy();
        }
        this.secondaryViewController = null;
    }

    public final void setFromSliderController(OplusQsVolumeSliderController oplusQsVolumeSliderController) {
        this.fromSliderController = oplusQsVolumeSliderController;
    }

    public final void setQsVolume(QSVolume qSVolume) {
        this.qsVolume = qSVolume;
    }
}
