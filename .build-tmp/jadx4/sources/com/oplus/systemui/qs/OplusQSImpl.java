package com.oplus.systemui.qs;

import android.content.res.Configuration;
import android.hardware.biometrics.IBiometricContextListener;
import android.hardware.fingerprint.IUdfpsRefreshRateRequestCallback;
import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.app.animation.Interpolators;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.panelanimation.qs.QSPanelOverScrollAnimationEx;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.qs.IQSContainerImplController;
import com.android.systemui.qs.ISysUiCustom;
import com.android.systemui.qs.QSDisableFlagsLogger;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.animator.IQSAnimator;
import com.android.systemui.qs.brightness.MirrorController;
import com.android.systemui.qs.deprecated.QSImpl;
import com.android.systemui.qs.deprecated.QSPanelController;
import com.android.systemui.qs.deprecated.QuickQSPanelController;
import com.android.systemui.qs.deprecated.dagger.QSComponent;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.shade.transition.LargeScreenShadeInterpolator;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.policy.RemoteInputQuickSettingsDisabler;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.customize.OplusQSCustomizer;
import com.oplus.systemui.qs.customize.OplusQSCustomizerController;
import com.oplus.systemui.qs.dagger.OplusQSFragmentComponent;
import com.oplus.systemui.qs.fake.OplusQSFakeStatusController;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.mydevice.MyDevicePanel;
import com.oplus.systemui.qs.mydevice.MyDevicePanelControllerImpl;
import com.oplus.systemui.qs.res.domain.util.StdQSTileViewBinder;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSImpl extends QSImpl {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSImpl";
    private final CentralSurfacesImpl centralSurfacesImpl;
    private Boolean isDarkMode;
    private Boolean isLargeFoldWithOpened;
    public OplusQSBottomViewController mBottomController;
    public OplusQuickStatusBarHeaderController mHeaderController;
    private OplusQSTileMediaContainerController mMediaContainerController;
    private MyDevicePanelControllerImpl mMyDeviceController;
    public OplusQSPanelContainerController mQsPanelContainerController;
    private final OplusQsImplHelper observerHelper;
    public OplusQSFakeStatusController oplusQSFakeStatusController;
    private final QSPanelOverScrollAnimationEx overScrollAnimationEx;
    private final PanelAnimationEx panelAnimationEx;
    private final QSHost qsHost;
    private final SysUiStateControllerImpl sysUiStateController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSImpl(RemoteInputQuickSettingsDisabler remoteInputQuickSettingsDisabler, SysuiStatusBarStateController sysuiStatusBarStateController, CommandQueue commandQueue, MediaHost mediaHost, MediaHost mediaHost2, KeyguardBypassController keyguardBypassController, QSDisableFlagsLogger qSDisableFlagsLogger, DumpManager dumpManager, QSLogger qSLogger, LargeScreenShadeInterpolator largeScreenShadeInterpolator, ISysUiCustom iSysUiCustom, AmbientState ambientState, QSHost qSHost, PanelAnimationEx panelAnimationEx, QSPanelOverScrollAnimationEx qSPanelOverScrollAnimationEx, SysUiStateControllerImpl sysUiStateControllerImpl, CentralSurfacesImpl centralSurfacesImpl, OplusQsImplHelper oplusQsImplHelper) {
        super(remoteInputQuickSettingsDisabler, sysuiStatusBarStateController, commandQueue, mediaHost, mediaHost2, keyguardBypassController, qSDisableFlagsLogger, dumpManager, qSLogger, largeScreenShadeInterpolator, panelAnimationEx, qSPanelOverScrollAnimationEx);
        this.qsHost = qSHost;
        this.panelAnimationEx = panelAnimationEx;
        this.overScrollAnimationEx = qSPanelOverScrollAnimationEx;
        this.sysUiStateController = sysUiStateControllerImpl;
        this.centralSurfacesImpl = centralSurfacesImpl;
        this.observerHelper = oplusQsImplHelper;
        ((QSImpl) this).mSySUiCustom = iSysUiCustom;
        ((QSImpl) this).mAmbientState = ambientState;
    }

    private final void setDarkMode(Boolean bool) {
        Boolean bool2 = this.isDarkMode;
        if (bool2 == null) {
            this.isDarkMode = bool;
            return;
        }
        if (Intrinsics.areEqual(bool, bool2) || bool == null) {
            return;
        }
        this.isDarkMode = bool;
        OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController = this.mHeaderController;
        if (oplusQuickStatusBarHeaderController == null) {
            oplusQuickStatusBarHeaderController = null;
        }
        oplusQuickStatusBarHeaderController.updateColor();
        OplusQSFooterViewController oplusQSFooterViewController = ((QSImpl) this).mFooter;
        if (oplusQSFooterViewController == null) {
            oplusQSFooterViewController = null;
        }
        if (oplusQSFooterViewController != null) {
            OplusQSFooterImpl oplusQSFooterImpl = (OplusQSFooterImpl) ((ViewController) oplusQSFooterViewController).mView;
            oplusQSFooterImpl.mMoreIcon.setImageResource(2131237158);
            oplusQSFooterImpl.mSettingsButtonIcon.setImageResource(2131237159);
            oplusQSFooterImpl.updateTextColor(QsColorUtil.getQsColorState());
        }
        OplusQSTileMediaContainerController oplusQSTileMediaContainerController = this.mMediaContainerController;
        if (oplusQSTileMediaContainerController != null) {
            oplusQSTileMediaContainerController.updateColor();
        }
        OplusQSPanelContainerController oplusQSPanelContainerController = this.mQsPanelContainerController;
        if (oplusQSPanelContainerController == null) {
            oplusQSPanelContainerController = null;
        }
        ((OplusQSPanelContainer) ((ViewController) oplusQSPanelContainerController).mView).updateColor();
        OplusQSBottomViewController oplusQSBottomViewController = this.mBottomController;
        OplusQSBottomViewController oplusQSBottomViewController2 = oplusQSBottomViewController != null ? oplusQSBottomViewController : null;
        oplusQSBottomViewController2.getClass();
        oplusQSBottomViewController2.onQsColorStateChanged(QsColorUtil.getQsColorState());
    }

    private final void setDeviceListening(boolean z) {
        MyDevicePanelControllerImpl myDevicePanelControllerImpl = this.mMyDeviceController;
        if (myDevicePanelControllerImpl != null) {
            myDevicePanelControllerImpl.setDeviceListening(z);
        }
    }

    private final void setLargeFoldWithOpened(Boolean bool) {
        Boolean bool2 = this.isLargeFoldWithOpened;
        if (bool2 == null) {
            this.isLargeFoldWithOpened = bool;
        } else {
            if (Intrinsics.areEqual(bool, bool2) || bool == null) {
                return;
            }
            this.isLargeFoldWithOpened = bool;
            QSFragmentHelper.getInstance().onQsClosingBegin();
            QSFragmentHelper.getInstance().onQsClosingEnd();
        }
    }

    public final void animateHeaderSlidingOut() {
        QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
        OplusQSContainerImpl oplusQSContainerImpl = qSFragmentHelper.mContainer;
        float f = (oplusQSContainerImpl == null || oplusQSContainerImpl.getController() == null) ? 0 : -((int) qSFragmentHelper.mContainer.getController().calcExpansionHeight(qSFragmentHelper.mLastQSExpansion));
        if (qSFragmentHelper.mQs.getView().getY() == f) {
            return;
        }
        qSFragmentHelper.mQs.setHeaderListening(false);
        LogUtil.d(LogUtil.TAG_QS, "QSFragmentHelper", "animateHeaderSlidingOut setHeaderListening false");
        OplusQSImpl oplusQSImpl = qSFragmentHelper.mQs;
        ((QSImpl) oplusQSImpl).mHeaderAnimating = true;
        oplusQSImpl.getView().animate().y(f).setStartDelay(0L).setDuration(360L).setInterpolator(Interpolators.FAST_OUT_SLOW_IN).setListener(new QSFragmentHelper.1(qSFragmentHelper)).start();
    }

    public Boolean forbidQsExpansion() {
        return Boolean.valueOf(((QSImpl) this).mPanelAnimationEx.getUseLayeredAnimation() ? ((QSImpl) this).mOverScrollAnimationEx.forbidQsExpansion() : QSFragmentHelper.getInstance().forbidQsExpansion());
    }

    public int getQsMinExpansionHeight() {
        ISysUiCustom iSysUiCustom = ((QSImpl) this).mSySUiCustom;
        if (iSysUiCustom == null || !iSysUiCustom.usingSysUiCustom()) {
            return super.getQsMinExpansionHeight();
        }
        boolean z = ((QSImpl) this).mInSplitShade;
        IQSContainerImplController iQSContainerImplController = ((QSImpl) this).mQSContainerImplController;
        return (int) (z ? ((OplusQSContainerImplController) iQSContainerImplController).calcExpansionHeight(1.0f) : ((OplusQSContainerImplController) iQSContainerImplController).calcExpansionHeight(0.0f));
    }

    public final void hideImmediately() {
        float translationYValue;
        ISysUiCustom iSysUiCustom = ((QSImpl) this).mSySUiCustom;
        if (iSysUiCustom == null || !iSysUiCustom.usingSysUiCustom()) {
            return;
        }
        getView().animate().cancel();
        View view = getView();
        if (this.panelAnimationEx.getUseLayeredAnimation()) {
            translationYValue = this.panelAnimationEx.getTranslationYValue().getTranslationYValue();
        } else {
            translationYValue = -((OplusQSContainerImplController) ((QSImpl) this).mQSContainerImplController).calcExpansionHeight(((QSImpl) this).mLastQSExpansion);
        }
        view.setY(translationYValue);
    }

    public final void notifyCustomizeChanged() {
        ISysUiCustom iSysUiCustom = ((QSImpl) this).mSySUiCustom;
        if (iSysUiCustom == null || !iSysUiCustom.usingSysUiCustom()) {
            super.notifyCustomizeChanged();
            return;
        }
        ((QSImpl) this).mQSContainerImplController.setCustomizing(isCustomizing());
        QS.HeightListener heightListener = ((QSImpl) this).mPanelView;
        if (heightListener != null) {
            heightListener.onQsHeightChanged();
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isCustom:", ((OplusQSCustomizerController) ((QSImpl) this).mQSCustomizerController).isCustomizing()), null, 8);
    }

    public void onComponentCreated(QSComponent qSComponent, Bundle bundle) {
        View viewInflate;
        QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
        View rootView = qSComponent.getRootView();
        qSFragmentHelper.mQs = this;
        qSFragmentHelper.mContainer = (OplusQSContainerImpl) rootView.findViewById(2131364820);
        qSFragmentHelper.mQSBottom = (OplusQSBottomImpl) rootView.findViewById(2131364711);
        qSFragmentHelper.mFooter = (OplusQSFooterImpl) rootView.findViewById(2131364763);
        qSFragmentHelper.mTileMediaContainer = (OplusQSTileMediaContainer) rootView.findViewById(2131364407);
        qSFragmentHelper.mQSPanel = rootView.findViewById(2131364821);
        qSFragmentHelper.mQSCustomizer = (OplusQSCustomizer) rootView.findViewById(2131364724);
        qSFragmentHelper.mQSPanelScrollView = (OplusQSPanelContainer) rootView.findViewById(2131363092);
        qSFragmentHelper.mHeader = (OplusQuickStatusBarHeader) rootView.findViewById(2131363334);
        qSFragmentHelper.mHeaderPanel = rootView.findViewById(2131364817);
        qSFragmentHelper.mBackground = rootView.findViewById(2131364819);
        qSFragmentHelper.mBrightness = rootView.findViewById(2131364713);
        qSFragmentHelper.mTouchSlop = ViewConfiguration.get(rootView.getContext()).getScaledTouchSlop();
        qSFragmentHelper.mOverscrollDistance = rootView.getContext().getResources().getDisplayMetrics().heightPixels;
        qSFragmentHelper.mMaximumVelocity = ViewConfiguration.get(rootView.getContext()).getScaledMaximumFlingVelocity();
        qSFragmentHelper.mHlTileOneHolder = rootView.findViewById(2131363363);
        qSFragmentHelper.mHlTileTwoHolder = rootView.findViewById(2131363364);
        qSFragmentHelper.mQsMediaContainer = rootView.findViewById(2131363897);
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSupportVolumeSeekBar()) {
            qSFragmentHelper.mSeekbarContainer = rootView.findViewById(2131365122);
        }
        qSFragmentHelper.mMoreButton = rootView.findViewById(2131364003);
        qSFragmentHelper.mQsFooterClock = rootView.findViewById(2131364766);
        qSFragmentHelper.mQsFooterCarrier = rootView.findViewById(2131364765);
        qSFragmentHelper.mSettingButton = rootView.findViewById(2131365156);
        qSFragmentHelper.mOplusDate = rootView.findViewById(2131364311);
        qSFragmentHelper.resetSpringBack();
        qSFragmentHelper.abortAnimation();
        qSFragmentHelper.mDeltaY = 0.0f;
        qSFragmentHelper.applyAccentColor(rootView.getContext());
        qSFragmentHelper.applyDynamicFontVariationConfig(rootView.getResources().getConfiguration());
        super.onComponentCreated(qSComponent, bundle);
        this.observerHelper.registerObserver(getContext());
        boolean z = qSComponent instanceof OplusQSFragmentComponent;
        SysuiStatusBarStateController sysuiStatusBarStateController = ((QSImpl) this).mStatusBarStateController;
        if (z) {
            setDarkMode(Boolean.valueOf(OplusDarkModeUtil.isNightMode(getContext())));
            FeatureOption.INSTANCE.getClass();
            setLargeFoldWithOpened(Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled() && DeviceUtil.shouldUseLargeFoldConfig(getContext().getResources())));
            OplusQSFragmentComponent oplusQSFragmentComponent = (OplusQSFragmentComponent) qSComponent;
            OplusQuickStatusBarHeaderController qSHeaderController = oplusQSFragmentComponent.getQSHeaderController();
            this.mHeaderController = qSHeaderController;
            if (qSHeaderController == null) {
                qSHeaderController = null;
            }
            qSHeaderController.init();
            IQSContainerImplController iQSContainerImplController = ((QSImpl) this).mQSContainerImplController;
            OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController = this.mHeaderController;
            if (oplusQuickStatusBarHeaderController == null) {
                oplusQuickStatusBarHeaderController = null;
            }
            iQSContainerImplController.attchQsNode(oplusQuickStatusBarHeaderController);
            OplusQSFooterViewController oplusQSFooterViewController = ((QSImpl) this).mFooter;
            if (oplusQSFooterViewController != null) {
                ((QSImpl) this).mQSContainerImplController.attchQsNode(oplusQSFooterViewController);
            }
            this.oplusQSFakeStatusController = oplusQSFragmentComponent.getQSFakeStatusController();
            OplusQSTileMediaContainerController qSTileMediaContainerController = oplusQSFragmentComponent.getQSTileMediaContainerController();
            this.mMediaContainerController = qSTileMediaContainerController;
            if (qSTileMediaContainerController != null) {
                qSTileMediaContainerController.init();
            }
            OplusQSTileMediaContainerController oplusQSTileMediaContainerController = this.mMediaContainerController;
            if (oplusQSTileMediaContainerController != null) {
                ((QSImpl) this).mQSContainerImplController.attchQsNode(oplusQSTileMediaContainerController);
            }
            OplusQSPanelContainerController qsPanelContainerController = oplusQSFragmentComponent.getQsPanelContainerController();
            this.mQsPanelContainerController = qsPanelContainerController;
            if (qsPanelContainerController == null) {
                qsPanelContainerController = null;
            }
            qsPanelContainerController.init();
            IQSContainerImplController iQSContainerImplController2 = ((QSImpl) this).mQSContainerImplController;
            OplusQSPanelContainerController oplusQSPanelContainerController = this.mQsPanelContainerController;
            if (oplusQSPanelContainerController == null) {
                oplusQSPanelContainerController = null;
            }
            iQSContainerImplController2.attchQsNode(oplusQSPanelContainerController);
            OplusQSBottomViewController qsBottomController = oplusQSFragmentComponent.getQsBottomController();
            this.mBottomController = qsBottomController;
            if (qsBottomController == null) {
                qsBottomController = null;
            }
            qsBottomController.init();
            OplusQSBottomViewController oplusQSBottomViewController = this.mBottomController;
            if (oplusQSBottomViewController == null) {
                oplusQSBottomViewController = null;
            }
            oplusQSBottomViewController.attachPagedLayout(getQSPanelController().getTileLayout());
            IQSContainerImplController iQSContainerImplController3 = ((QSImpl) this).mQSContainerImplController;
            OplusQSBottomViewController oplusQSBottomViewController2 = this.mBottomController;
            if (oplusQSBottomViewController2 == null) {
                oplusQSBottomViewController2 = null;
            }
            iQSContainerImplController3.attchQsNode(oplusQSBottomViewController2);
            ViewGroup viewGroup = ((QSImpl) this).mContainer;
            if (viewGroup instanceof ViewGroup) {
                ViewGroup viewGroup2 = viewGroup;
                if (FeatureOption.isSupportMyDevice()) {
                    OplusNotificationLogger.logDevicePanel(TAG, "initMyDevicePanel");
                    ViewStub viewStub = (ViewStub) viewGroup2.findViewById(2131364116);
                    if (viewStub != null && (viewInflate = viewStub.inflate()) != null) {
                        MyDevicePanel myDevicePanel = (MyDevicePanel) viewInflate;
                        MyDevicePanelControllerImpl myDevicePanelControllerImpl = new MyDevicePanelControllerImpl(myDevicePanel);
                        myDevicePanelControllerImpl.init();
                        QSFragmentHelper.getInstance().setmMyDevicePanel(myDevicePanel);
                        ((QSImpl) this).mQSContainerImplController.attchQsNode(myDevicePanelControllerImpl);
                        this.mMyDeviceController = myDevicePanelControllerImpl;
                    }
                }
            }
            QSFragmentHelper.getInstance().setHost(this.qsHost);
            IQSAnimator iQSAnimator = qSComponent.getIQSAnimator();
            if (iQSAnimator instanceof OplusQSAnimator) {
                ((OplusQSAnimator) iQSAnimator).updateAnimators();
            }
            if (bundle != null) {
                setQsVisible(bundle.getBoolean("visible"));
                setExpanded(bundle.getBoolean("expanded"));
                setListening(bundle.getBoolean("listening"));
                setEditLocation(getView());
                ((QSImpl) this).mQSCustomizerController.restoreInstanceState(bundle);
                if (((QSImpl) this).mQsExpanded) {
                    ((QSImpl) this).mQSPanelController.getTileLayout().restoreInstanceState(bundle);
                }
            }
            if (sysuiStatusBarStateController.getState() == 0) {
                ShadeViewController shadeViewController = this.centralSurfacesImpl.getShadeViewController();
                NotificationPanelViewController notificationPanelViewController = shadeViewController instanceof NotificationPanelViewController ? (NotificationPanelViewController) shadeViewController : null;
                if (notificationPanelViewController != null) {
                    notificationPanelViewController.collapseNotifPanelWithoutAnimate();
                }
            }
            QSPanelController qSPanelController = ((QSImpl) this).mQSPanelController;
            QuickQSPanelController quickQSPanelController = ((QSImpl) this).mQuickQSPanelController;
            QSImpl.ListeningAndVisibilityLifecycleOwner listeningAndVisibilityLifecycleOwner = ((QSImpl) this).mListeningAndVisibilityLifecycleOwner;
            StdQSTileViewBinder.collectTileViewRes(qSPanelController, quickQSPanelController, listeningAndVisibilityLifecycleOwner);
            StdQSTileViewBinder.collectMediaPanelRes(this.mMediaContainerController, listeningAndVisibilityLifecycleOwner);
            StdQSTileViewBinder.collectFooterButton(((QSImpl) this).mFooter, listeningAndVisibilityLifecycleOwner);
        }
        onStateChanged(sysuiStatusBarStateController.getState());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setDarkMode(Boolean.valueOf(OplusDarkModeUtil.isNightMode(getContext())));
        FeatureOption.INSTANCE.getClass();
        setLargeFoldWithOpened(Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled() && DeviceUtil.shouldUseLargeFoldConfig(getContext().getResources())));
    }

    public final void onDestroy() {
        super.onDestroy();
        if (getView() != null) {
            getView().removeOnLayoutChangeListener(((QSImpl) this).mOnLayoutChangeListener);
        }
        QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
        OplusQSPanelContainer oplusQSPanelContainer = qSFragmentHelper.mQSPanelScrollView;
        if (oplusQSPanelContainer != null) {
            oplusQSPanelContainer.setOnScrollChangeListener(null);
        }
        qSFragmentHelper.hideCouiPopup();
        qSFragmentHelper.mContainer = null;
        this.observerHelper.unRegisterObserver(getContext());
    }

    public final void resetSpringBack() {
        QSFragmentHelper.getInstance().resetSpringBack();
        QSFragmentHelper.getInstance().abortAnimation();
    }

    public /* bridge */ /* synthetic */ void setBiometricContextListener(IBiometricContextListener iBiometricContextListener) {
        super/*com.android.systemui.statusbar.CommandQueue.Callbacks*/.setBiometricContextListener(iBiometricContextListener);
    }

    public void setBrightnessMirrorController(MirrorController mirrorController) {
        ISysUiCustom iSysUiCustom = ((QSImpl) this).mSySUiCustom;
        if (iSysUiCustom == null || !iSysUiCustom.usingSysUiCustom()) {
            super.setBrightnessMirrorController(mirrorController);
            return;
        }
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSupportVolumeSeekBar()) {
            OplusQSTileMediaContainerController oplusQSTileMediaContainerController = this.mMediaContainerController;
            if (oplusQSTileMediaContainerController != null) {
                oplusQSTileMediaContainerController.setBrightnessMirror(mirrorController);
                return;
            }
            return;
        }
        OplusQSBottomViewController oplusQSBottomViewController = this.mBottomController;
        if (oplusQSBottomViewController == null) {
            oplusQSBottomViewController = null;
        }
        oplusQSBottomViewController.setBrightnessMirror(mirrorController);
    }

    public void setEditLocation(View view) {
        View viewFindViewById = view.findViewById(2131364003);
        if (viewFindViewById == null) {
            return;
        }
        int[] locationOnScreen = viewFindViewById.getLocationOnScreen();
        int i = locationOnScreen[0];
        viewFindViewById.getWidth();
        int i2 = locationOnScreen[1];
        viewFindViewById.getHeight();
        ((OplusQSCustomizer) ((ViewController) ((OplusQSCustomizerController) ((QSImpl) this).mQSCustomizerController)).mView).getClass();
    }

    public void setHeaderListening(boolean z) {
        ISysUiCustom iSysUiCustom = ((QSImpl) this).mSySUiCustom;
        if (iSysUiCustom == null || !iSysUiCustom.usingSysUiCustom()) {
            super.setHeaderListening(z);
        }
        if ((((QSImpl) this).mListening && ((QSImpl) this).mQsVisible) != (z && ((QSImpl) this).mQsVisible) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusQSImpl-->", "SystemUi--QuickSettings", TextFieldImplKt$.ExternalSyntheticOutline0.m("setHeaderListening ", " listening=", " mQsVisible=", z && ((QSImpl) this).mQsVisible, z), ((QSImpl) this).mQsVisible);
        }
        OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController = this.mHeaderController;
        if (oplusQuickStatusBarHeaderController == null) {
            oplusQuickStatusBarHeaderController = null;
        }
        oplusQuickStatusBarHeaderController.setListening(z && ((QSImpl) this).mQsVisible);
        OplusQSFooterViewController oplusQSFooterViewController = ((QSImpl) this).mFooter;
        if (oplusQSFooterViewController != null) {
            oplusQSFooterViewController.setListening(z && ((QSImpl) this).mQsVisible);
        }
        OplusQSTileMediaContainerController oplusQSTileMediaContainerController = this.mMediaContainerController;
        if (oplusQSTileMediaContainerController != null) {
            oplusQSTileMediaContainerController.setListening(z && ((QSImpl) this).mQsVisible);
        }
        ((QSImpl) this).mQuickQSPanelController.setListening(z && ((QSImpl) this).mQsVisible);
        OplusQSBottomViewController oplusQSBottomViewController = this.mBottomController;
        (oplusQSBottomViewController != null ? oplusQSBottomViewController : null).setListening(z && ((QSImpl) this).mQsVisible);
        setDeviceListening(z && ((QSImpl) this).mQsVisible);
    }

    public void setHeightOverride(int i) {
        ISysUiCustom iSysUiCustom = ((QSImpl) this).mSySUiCustom;
        if (iSysUiCustom == null || !iSysUiCustom.usingSysUiCustom()) {
            super.setHeightOverride(i);
        }
    }

    public void setKeyguardShowingInner(boolean z) {
        QSFragmentHelper.getInstance().setKeyguardShowing(z);
    }

    public /* bridge */ /* synthetic */ void setLastQSExpansion(Float f) {
        setLastQSExpansion(f.floatValue());
    }

    public void setListening(boolean z) {
        ISysUiCustom iSysUiCustom = ((QSImpl) this).mSySUiCustom;
        if (iSysUiCustom == null || !iSysUiCustom.usingSysUiCustom()) {
            super.setListening(z);
        }
        boolean z2 = ((QSImpl) this).mQsVisible;
        if (z2 && ((QSImpl) this).mListening != z2 && z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("setListening: listening=true mQsVisible=", "OplusQSImpl-->", "SystemUi--QuickSettings", ((QSImpl) this).mQsVisible);
        }
        super.setListening(z);
        QSFragmentHelper.getInstance().setListening(z);
    }

    public void setQsDeviceExpansion(float f) {
        MyDevicePanelControllerImpl myDevicePanelControllerImpl = this.mMyDeviceController;
        if (myDevicePanelControllerImpl != null) {
            myDevicePanelControllerImpl.setQsDeviceExpansion(f);
        }
    }

    public /* bridge */ /* synthetic */ void setQsTiles(String[] strArr) {
        super/*com.android.systemui.statusbar.CommandQueue.Callbacks*/.setQsTiles(strArr);
    }

    public void setQsVisible(boolean z) {
        if (((QSImpl) this).mQsVisible != z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusQSImpl-->", "SystemUi--QuickSettings", TextFieldImplKt$.ExternalSyntheticOutline0.m("setQsVisible ", " mListening=", " mQsVisible=", z, ((QSImpl) this).mListening), ((QSImpl) this).mQsVisible);
        }
        boolean z2 = ((QSImpl) this).mQsVisible;
        super.setQsVisible(z);
        NotificationPanelViewController shadeViewController = this.centralSurfacesImpl.getShadeViewController();
        NotificationPanelViewController notificationPanelViewController = shadeViewController instanceof NotificationPanelViewController ? shadeViewController : null;
        boolean z3 = false;
        boolean z4 = (notificationPanelViewController != null ? notificationPanelViewController.getExpandedFraction() : 0.0f) > 0.0f;
        if (z2 != ((QSImpl) this).mQsVisible) {
            setHeaderListening(z);
        }
        SysUiStateControllerImpl sysUiStateControllerImpl = this.sysUiStateController;
        if (z && z4) {
            z3 = true;
        }
        OplusQSFakeStatusController oplusQSFakeStatusController = this.oplusQSFakeStatusController;
        sysUiStateControllerImpl.onExpandedChanged(z3, (oplusQSFakeStatusController != null ? oplusQSFakeStatusController : null).getView());
        if (z) {
            return;
        }
        if (isKeyguardState() || !z4) {
            QSFragmentHelper.getInstance().onQsClosingEnd();
        }
    }

    public /* bridge */ /* synthetic */ void setSplitscreenFocus(boolean z) {
        super/*com.android.systemui.statusbar.CommandQueue.Callbacks*/.setSplitscreenFocus(z);
    }

    public /* bridge */ /* synthetic */ void setTopAppHidesStatusBar(boolean z) {
        super/*com.android.systemui.statusbar.CommandQueue.Callbacks*/.setTopAppHidesStatusBar(z);
    }

    public /* bridge */ /* synthetic */ void setUdfpsRefreshRateCallback(IUdfpsRefreshRateRequestCallback iUdfpsRefreshRateRequestCallback) {
        super/*com.android.systemui.statusbar.CommandQueue.Callbacks*/.setUdfpsRefreshRateCallback(iUdfpsRefreshRateRequestCallback);
    }

    public final void updateQsState() {
        boolean z = true;
        boolean z2 = ((QSImpl) this).mQsExpanded || ((QSImpl) this).mInSplitShade;
        boolean z3 = z2 || ((QSImpl) this).mStackScrollerOverscrolling || ((QSImpl) this).mHeaderAnimating;
        ((QSImpl) this).mQSPanelController.setExpanded(z2);
        boolean z4 = isKeyguardState() || ((QSImpl) this).mStatusBarStateController.getCurrentOrUpcomingState() == 2;
        ((QSImpl) this).mQuickQSPanelController.setExpanded(!(!z4 || ((QSImpl) this).mHeaderAnimating || ((QSImpl) this).mShowCollapsedOnKeyguard) || (z2 && !((QSImpl) this).mStackScrollerOverscrolling));
        ((QSImpl) this).mFooter.setExpanded(!(!z4 || ((QSImpl) this).mHeaderAnimating || ((QSImpl) this).mShowCollapsedOnKeyguard) || (z2 && !((QSImpl) this).mStackScrollerOverscrolling));
        OplusQSTileMediaContainerController oplusQSTileMediaContainerController = this.mMediaContainerController;
        if (oplusQSTileMediaContainerController != null) {
            oplusQSTileMediaContainerController.setExpanded(!(!z4 || ((QSImpl) this).mHeaderAnimating || ((QSImpl) this).mShowCollapsedOnKeyguard) || (z2 && !((QSImpl) this).mStackScrollerOverscrolling));
        }
        ((QSImpl) this).mQSPanelController.setVisibility(z3 ? 0 : 4);
        QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
        boolean z5 = ((QSImpl) this).mQsExpanded;
        boolean z6 = ((QSImpl) this).mHeaderAnimating;
        boolean z7 = ((QSImpl) this).mQsDisabled;
        qSFragmentHelper.mIsExpanded = z5;
        if (z7 || (!z5 && z4 && !z6)) {
            z = false;
        }
        OplusQSContainerImpl oplusQSContainerImpl = qSFragmentHelper.mContainer;
        if (oplusQSContainerImpl != null) {
            oplusQSContainerImpl.setVisibility(z ? 0 : 4);
        }
        qSFragmentHelper.hideCouiPopup();
    }

    public void setLastQSExpansion(float f) {
        QSFragmentHelper.getInstance().setLastQSExpansion(f);
    }
}
