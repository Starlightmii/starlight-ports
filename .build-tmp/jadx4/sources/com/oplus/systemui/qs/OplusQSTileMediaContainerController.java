package com.oplus.systemui.qs;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.plugins.qs.QSVolume;
import com.android.systemui.qs.ExpandableNode;
import com.android.systemui.qs.QSNode;
import com.android.systemui.qs.brightness.BrightnessMirrorHandler;
import com.android.systemui.qs.brightness.BrightnessSliderController;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.android.systemui.qs.brightness.MirrorController;
import com.android.systemui.qs.deprecated.brightness.BrightnessController;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.qs.base.seek.OplusQsVolumeSliderController;
import com.oplus.systemui.qs.base.widget.QsViewBackgroundKt;
import com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView;
import com.oplus.systemui.qs.media.OplusQsMediaPanelViewController;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.qs.slider.OplusQSVolumeDialogController;
import com.oplus.systemui.qs.slider.OplusQsBrightnessSliderController;
import com.oplus.systemui.qs.slider.OplusQsVolumeController;
import com.oplus.systemui.qs.utils.OplusQSDisplayModeUtils;
import com.oplus.systemui.qs.utils.SpringOverScroller;
import com.oplus.systemui.qs.widget.OplusQsToggleSliderLayout;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTileMediaContainerController extends ViewController implements QSNode {
    public static final Companion Companion = new Companion();
    private static final String QS_SHOW_BRIGHTNESS = "qs_show_brightness";
    private final Lazy brightnessDialogController;
    private final BrightnessController.Factory brightnessFactory;
    private final View.OnClickListener brightnessLayoutClickListener;
    private final View.OnLongClickListener brightnessLayoutLongClickListener;
    private final BrightnessSliderController.Factory brightnessSliderFactory;
    public int mBottomGap;
    private ViewGroup mBrightnessContainer;
    private BrightnessController mBrightnessController;
    private BrightnessMirrorHandler mBrightnessMirrorHandler;
    private IBrightnessSliderController mBrightnessSliderController;
    public boolean mCustomizing;
    public boolean mIsMediaMode;
    private final OplusQSTileMediaContainerController$mSuperPowerObserver$1 mSuperPowerObserver;
    public int mTopGap;
    private final TunerService mTunerService;
    private QSVolume mVolumeController;
    private OplusQsVolumeSliderController mVolumeSliderController;
    private final OplusQsMediaPanelViewController mediaPanelViewController;
    private final TunerService.Tunable tunable;
    private final Lazy volumeDialogController;
    private final View.OnClickListener volumeLayoutClickListener;
    private final View.OnLongClickListener volumeLayoutLongClickListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusQsVolumeSliderControllerFactory {
        private final Looper bgLooper;
        private final Handler mainHandler;
        private final UserContextProvider userContextProvider;

        public OplusQsVolumeSliderControllerFactory(Looper looper, Handler handler, UserContextProvider userContextProvider) {
            this.bgLooper = looper;
            this.mainHandler = handler;
            this.userContextProvider = userContextProvider;
        }

        public final OplusQsVolumeSliderController create(OplusQsToggleSliderLayout oplusQsToggleSliderLayout, QSVolume qSVolume) {
            Handler handler = this.mainHandler;
            Handler handler2 = new Handler(this.bgLooper);
            OplusQSTileMediaContainerController$OplusQsVolumeSliderControllerFactory$create$1 oplusQSTileMediaContainerController$OplusQsVolumeSliderControllerFactory$create$1 = new OplusQSTileMediaContainerController$OplusQsVolumeSliderControllerFactory$create$1();
            oplusQSTileMediaContainerController$OplusQsVolumeSliderControllerFactory$create$1.this$0 = this;
            VarHandle.storeStoreFence();
            return new OplusQsVolumeSliderController(oplusQsToggleSliderLayout, qSVolume, handler, handler2, oplusQSTileMediaContainerController$OplusQsVolumeSliderControllerFactory$create$1);
        }

        public final UserContextProvider getUserContextProvider() {
            return this.userContextProvider;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSTileMediaContainerController$onInit$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ViewController.OnConfigurationChangedListener {
        public /* synthetic */ OplusQSTileMediaContainerController this$0;

        public final void onConfigurationChange(Configuration configuration) {
            OplusQSTileMediaContainerController oplusQSTileMediaContainerController = this.this$0;
            oplusQSTileMediaContainerController.updateResources(oplusQSTileMediaContainerController.getResources());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSTileMediaContainerController(OplusQSTileMediaContainer oplusQSTileMediaContainer, OplusQsVolumeController.Factory factory, OplusQsVolumeSliderControllerFactory oplusQsVolumeSliderControllerFactory, BrightnessController.Factory factory2, BrightnessSliderController.Factory factory3, TunerService tunerService, OplusQsMediaPanelViewController oplusQsMediaPanelViewController, Lazy lazy, Lazy lazy2) {
        super(oplusQSTileMediaContainer);
        this.brightnessFactory = factory2;
        this.brightnessSliderFactory = factory3;
        this.mTunerService = tunerService;
        this.mediaPanelViewController = oplusQsMediaPanelViewController;
        this.brightnessDialogController = lazy;
        this.volumeDialogController = lazy2;
        int i = 1;
        OplusQSTileMediaContainerController$volumeLayoutClickListener$1 oplusQSTileMediaContainerController$volumeLayoutClickListener$1 = new OplusQSTileMediaContainerController$volumeLayoutClickListener$1(i);
        oplusQSTileMediaContainerController$volumeLayoutClickListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.brightnessLayoutClickListener = oplusQSTileMediaContainerController$volumeLayoutClickListener$1;
        OplusQSTileMediaContainerController$volumeLayoutLongClickListener$1 oplusQSTileMediaContainerController$volumeLayoutLongClickListener$1 = new OplusQSTileMediaContainerController$volumeLayoutLongClickListener$1(i);
        oplusQSTileMediaContainerController$volumeLayoutLongClickListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.brightnessLayoutLongClickListener = oplusQSTileMediaContainerController$volumeLayoutLongClickListener$1;
        int i2 = 0;
        OplusQSTileMediaContainerController$volumeLayoutClickListener$1 oplusQSTileMediaContainerController$volumeLayoutClickListener$2 = new OplusQSTileMediaContainerController$volumeLayoutClickListener$1(i2);
        oplusQSTileMediaContainerController$volumeLayoutClickListener$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.volumeLayoutClickListener = oplusQSTileMediaContainerController$volumeLayoutClickListener$2;
        OplusQSTileMediaContainerController$volumeLayoutLongClickListener$1 oplusQSTileMediaContainerController$volumeLayoutLongClickListener$2 = new OplusQSTileMediaContainerController$volumeLayoutLongClickListener$1(i2);
        oplusQSTileMediaContainerController$volumeLayoutLongClickListener$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.volumeLayoutLongClickListener = oplusQSTileMediaContainerController$volumeLayoutLongClickListener$2;
        OplusQSTileMediaContainerController$tunable$1 oplusQSTileMediaContainerController$tunable$1 = new OplusQSTileMediaContainerController$tunable$1();
        oplusQSTileMediaContainerController$tunable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.tunable = oplusQSTileMediaContainerController$tunable$1;
        OplusQSTileMediaContainerController$mSuperPowerObserver$1 oplusQSTileMediaContainerController$mSuperPowerObserver$1 = new OplusQSTileMediaContainerController$mSuperPowerObserver$1();
        oplusQSTileMediaContainerController$mSuperPowerObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSuperPowerObserver = oplusQSTileMediaContainerController$mSuperPowerObserver$1;
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSupportVolumeSeekBar()) {
            this.mBrightnessContainer = oplusQSTileMediaContainer != 0 ? (ViewGroup) oplusQSTileMediaContainer.findViewById(2131362350) : null;
            IBrightnessSliderController iBrightnessSliderControllerCreate = factory3.create(getContext(), this.mBrightnessContainer);
            this.mBrightnessSliderController = iBrightnessSliderControllerCreate;
            BrightnessController brightnessControllerCreate = factory2.create(iBrightnessSliderControllerCreate);
            this.mBrightnessController = brightnessControllerCreate;
            this.mBrightnessMirrorHandler = new BrightnessMirrorHandler(brightnessControllerCreate);
            IBrightnessSliderController iBrightnessSliderController = this.mBrightnessSliderController;
            OplusQsBrightnessSliderController oplusQsBrightnessSliderController = iBrightnessSliderController instanceof OplusQsBrightnessSliderController ? (OplusQsBrightnessSliderController) iBrightnessSliderController : null;
            if (oplusQsBrightnessSliderController != null) {
                oplusQsBrightnessSliderController.setOnLayoutClickListener(oplusQSTileMediaContainerController$volumeLayoutClickListener$1);
                oplusQsBrightnessSliderController.setLongClickListener(oplusQSTileMediaContainerController$volumeLayoutLongClickListener$1);
            }
            QSVolume qSVolumeCreate = factory.create();
            OplusQsVolumeSliderController oplusQsVolumeSliderControllerCreate = oplusQsVolumeSliderControllerFactory.create(oplusQSTileMediaContainer != 0 ? (OplusQsToggleSliderLayout) oplusQSTileMediaContainer.findViewById(2131366042) : null, qSVolumeCreate);
            this.mVolumeController = qSVolumeCreate;
            this.mVolumeSliderController = oplusQsVolumeSliderControllerCreate;
            if (oplusQsVolumeSliderControllerCreate != null) {
                oplusQsVolumeSliderControllerCreate.setOnLayoutClickListener(oplusQSTileMediaContainerController$volumeLayoutClickListener$2);
            }
            OplusQsVolumeSliderController oplusQsVolumeSliderController = this.mVolumeSliderController;
            if (oplusQsVolumeSliderController != null) {
                oplusQsVolumeSliderController.setLongClickListener(oplusQSTileMediaContainerController$volumeLayoutLongClickListener$2);
            }
        }
    }

    public static final void access$showVolumeDetailDialog(OplusQSTileMediaContainerController oplusQSTileMediaContainerController) {
        QSVolume qSVolume = oplusQSTileMediaContainerController.mVolumeController;
        if (qSVolume != null) {
            ((OplusQSVolumeDialogController) oplusQSTileMediaContainerController.volumeDialogController.get()).setQsVolume(qSVolume);
        }
        ((OplusQSVolumeDialogController) oplusQSTileMediaContainerController.volumeDialogController.get()).setFromSliderController(oplusQSTileMediaContainerController.mVolumeSliderController);
        OplusQSVolumeDialogController oplusQSVolumeDialogController = (OplusQSVolumeDialogController) oplusQSTileMediaContainerController.volumeDialogController.get();
        OplusQsVolumeSliderController oplusQsVolumeSliderController = oplusQSTileMediaContainerController.mVolumeSliderController;
        oplusQSVolumeDialogController.showDialog(oplusQsVolumeSliderController != null ? oplusQsVolumeSliderController.getExpandableView() : null);
    }

    public static final void access$updateViewVisibilityForTuningValue(OplusQSTileMediaContainerController oplusQSTileMediaContainerController, String str, String str2) {
        ViewGroup viewGroup;
        if (!QS_SHOW_BRIGHTNESS.equals(str) || (viewGroup = oplusQSTileMediaContainerController.mBrightnessContainer) == null) {
            return;
        }
        viewGroup.setVisibility(TunerService.parseIntegerSwitch(str2, true) ? 0 : 8);
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [android.view.ViewGroup, com.oplus.systemui.qs.OplusQSTileMediaContainer] */
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("QSTileMediaContainerController: ");
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "    mIsMediaMode: ", this.mIsMediaMode);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "    mCustomizing: ", this.mCustomizing);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    mTopGap: ", this.mTopGap, printWriter);
        printWriter.println("    mBottomGap: " + this.mBottomGap);
        printWriter.println("    mCollapseHeight: " + getCollapseHeight());
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    mExpandHeight: ", getExpandHeight(), printWriter);
        ?? r2 = (OplusQSTileMediaContainer) ((ViewController) this).mView;
        if (r2 != 0) {
            printWriter.println("    translationY: " + r2.getTranslationY());
            SpringOverScroller springOverScroller = r2.getSpringOverScroller();
            printWriter.println("    couiCurrY: " + (springOverScroller != null ? Integer.valueOf(springOverScroller.getCOUICurrY()) : null));
        }
    }

    public View getAnimatorView() {
        OplusQSTileMediaContainer oplusQSTileMediaContainer = (OplusQSTileMediaContainer) ((ViewController) this).mView;
        if (oplusQSTileMediaContainer != null) {
            return oplusQSTileMediaContainer.getAnimatorView();
        }
        return null;
    }

    public final float getBottomGap() {
        return this.mBottomGap;
    }

    public int getCollapseHeight() {
        ConstraintLayout constraintLayout = (OplusQSTileMediaContainer) ((ViewController) this).mView;
        if (constraintLayout != null) {
            return constraintLayout.getHeight();
        }
        return 0;
    }

    public /* bridge */ /* synthetic */ int getExpandHeight() {
        return super/*com.android.systemui.qs.ExpandableNode*/.getExpandHeight();
    }

    public int getFloorCount() {
        if (!this.mIsMediaMode) {
            return 1;
        }
        QSFeatureOption.INSTANCE.getClass();
        if (!QSFeatureOption.isSupportVolumeSeekBar()) {
            return 1;
        }
        FeatureOption.INSTANCE.getClass();
        return ((FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled() && DeviceStateController.getInstance().isOpenOrHalfOpen()) || FeatureOption.isTablet()) ? 1 : 2;
    }

    public final float getTopGap(float f) {
        return this.mTopGap;
    }

    public View getView() {
        ConstraintLayout constraintLayout = (OplusQSTileMediaContainer) ((ViewController) this).mView;
        if (constraintLayout != null) {
            return constraintLayout;
        }
        Path$.ExternalSyntheticBUOutline0.m$1("View is null");
        return null;
    }

    public final void onInit() {
        updateResources(getResources());
        OplusQSTileMediaContainer oplusQSTileMediaContainer = (OplusQSTileMediaContainer) ((ViewController) this).mView;
        if (oplusQSTileMediaContainer != null) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            oplusQSTileMediaContainer.setConfigurationChangeListener(anonymousClass1);
        }
        OplusQsVolumeSliderController oplusQsVolumeSliderController = this.mVolumeSliderController;
        if (oplusQsVolumeSliderController != null) {
            oplusQsVolumeSliderController.init();
        }
        OplusQsVolumeSliderController oplusQsVolumeSliderController2 = this.mVolumeSliderController;
        if (oplusQsVolumeSliderController2 != null) {
            OplusQsVolumeSliderController.updateSeekBarColor$default(oplusQsVolumeSliderController2, QsViewBackgroundKt.isMixColorSupport(getContext(), GaussBlurUtils.isQSGaussBlurDisabled()), false, null, 6);
        }
        IBrightnessSliderController iBrightnessSliderController = this.mBrightnessSliderController;
        if (iBrightnessSliderController != null) {
            iBrightnessSliderController.doInit();
        }
        this.mediaPanelViewController.init();
    }

    public final void onViewAttached() {
        BrightnessMirrorHandler brightnessMirrorHandler = this.mBrightnessMirrorHandler;
        if (brightnessMirrorHandler != null) {
            brightnessMirrorHandler.onQsPanelAttached();
        }
        BrightnessController brightnessController = this.mBrightnessController;
        if (brightnessController != null) {
            brightnessController.registerBrightnessObserver();
        }
        this.mTunerService.addTunable(this.tunable, new String[]{QS_SHOW_BRIGHTNESS});
        QSVolume qSVolume = this.mVolumeController;
        if (qSVolume != null) {
            qSVolume.registerCallbacks();
        }
        SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(this.mSuperPowerObserver);
    }

    public final void onViewDetached() {
        BrightnessMirrorHandler brightnessMirrorHandler = this.mBrightnessMirrorHandler;
        if (brightnessMirrorHandler != null) {
            brightnessMirrorHandler.onQsPanelDettached();
        }
        BrightnessController brightnessController = this.mBrightnessController;
        if (brightnessController != null) {
            brightnessController.unregisterBrightnessObserver();
        }
        this.mTunerService.removeTunable(this.tunable);
        setListening(false);
        QSVolume qSVolume = this.mVolumeController;
        if (qSVolume != null) {
            qSVolume.unregisterCallbacks();
        }
        QSVolume qSVolume2 = this.mVolumeController;
        if (qSVolume2 != null) {
            qSVolume2.disableShowVolumeDialog(false);
        }
        SuperPowerSaveSettingsObserver.Companion.getInstance().removeListener(this.mSuperPowerObserver);
    }

    public final void setBrightnessMirror(MirrorController mirrorController) {
        BrightnessMirrorHandler brightnessMirrorHandler = this.mBrightnessMirrorHandler;
        if (brightnessMirrorHandler != null) {
            brightnessMirrorHandler.setController(mirrorController);
        }
    }

    public /* bridge */ /* synthetic */ void setContainer(ExpandableNode.OnStateListener onStateListener) {
        super.setContainer(onStateListener);
    }

    public void setCustomizing(boolean z) {
        this.mCustomizing = z;
        ConstraintLayout constraintLayout = (OplusQSTileMediaContainer) ((ViewController) this).mView;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(z ? 4 : 0);
        }
    }

    public void setExpanded(boolean z) {
        OplusQSTileMediaContainer oplusQSTileMediaContainer = (OplusQSTileMediaContainer) ((ViewController) this).mView;
        if (oplusQSTileMediaContainer != null) {
            oplusQSTileMediaContainer.setExpanded(z);
        }
    }

    public void setExpansion(float f) {
        OplusQSTileMediaContainer oplusQSTileMediaContainer = (OplusQSTileMediaContainer) ((ViewController) this).mView;
        if (oplusQSTileMediaContainer != null) {
            oplusQSTileMediaContainer.setExpansion(f);
        }
    }

    public void setListening(boolean z) {
        OplusQSTileMediaContainer oplusQSTileMediaContainer = (OplusQSTileMediaContainer) ((ViewController) this).mView;
        if (oplusQSTileMediaContainer != null) {
            oplusQSTileMediaContainer.setListening(z);
        }
        OplusQsVolumeSliderController oplusQsVolumeSliderController = this.mVolumeSliderController;
        if (oplusQsVolumeSliderController != null) {
            oplusQsVolumeSliderController.setListening(z);
        }
        IBrightnessSliderController iBrightnessSliderController = this.mBrightnessSliderController;
        if (iBrightnessSliderController != null) {
            iBrightnessSliderController.setListening(z);
        }
        BrightnessController brightnessController = this.mBrightnessController;
        if (!z) {
            if (brightnessController != null) {
                brightnessController.unregisterCallbacks();
            }
        } else {
            if (brightnessController != null) {
                brightnessController.checkRestrictionAndSetEnabled();
            }
            BrightnessController brightnessController2 = this.mBrightnessController;
            if (brightnessController2 != null) {
                brightnessController2.registerCallbacks();
            }
        }
    }

    public final void setQsMediaPanelShown(boolean z) {
        this.mIsMediaMode = z;
        OplusQSTileMediaContainer oplusQSTileMediaContainer = (OplusQSTileMediaContainer) ((ViewController) this).mView;
        if (oplusQSTileMediaContainer != null) {
            oplusQSTileMediaContainer.setMediaMode(z);
        }
        updateResources(getResources());
    }

    public float setTranslationY(float f, int i, Function1 function1) {
        super.setTranslationY(f, i, function1);
        int floorCount = getFloorCount();
        Object obj = ((ViewController) this).mView;
        if (floorCount <= 1) {
            OplusQSTileMediaContainer oplusQSTileMediaContainer = (OplusQSTileMediaContainer) obj;
            if (oplusQSTileMediaContainer != null) {
                return oplusQSTileMediaContainer.updateSubTranslationY(f, i, function1, true);
            }
            return 0.0f;
        }
        OplusQSTileMediaContainer oplusQSTileMediaContainer2 = (OplusQSTileMediaContainer) obj;
        if (oplusQSTileMediaContainer2 == null) {
            return 0.0f;
        }
        OplusQSTileMediaContainer.Companion companion = OplusQSTileMediaContainer.Companion;
        return oplusQSTileMediaContainer2.updateSubTranslationY(f, i, function1, false);
    }

    public final void updateColor() {
        ((OplusQsBaseMediaPanelView) ((ViewController) this.mediaPanelViewController).mView).updateColor();
        OplusQsVolumeSliderController oplusQsVolumeSliderController = this.mVolumeSliderController;
        if (oplusQsVolumeSliderController != null) {
            oplusQsVolumeSliderController.updateColor(QsViewBackgroundKt.isMixColorSupport(getContext(), GaussBlurUtils.isQSGaussBlurDisabled()), null);
        }
        IBrightnessSliderController iBrightnessSliderController = this.mBrightnessSliderController;
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController = iBrightnessSliderController instanceof OplusQsBrightnessSliderController ? (OplusQsBrightnessSliderController) iBrightnessSliderController : null;
        if (oplusQsBrightnessSliderController != null) {
            oplusQsBrightnessSliderController.updateColor(QSSpecialModeUpdateMonitor.ColorType.UI_MODE);
        }
        OplusQSTileMediaContainer oplusQSTileMediaContainer = (OplusQSTileMediaContainer) ((ViewController) this).mView;
        if (oplusQSTileMediaContainer != null) {
            oplusQSTileMediaContainer.updateTileColor();
        }
    }

    public final void updateMediaPanelDrawable() {
        ((OplusQsBaseMediaPanelView) ((ViewController) this.mediaPanelViewController).mView).onDrawableUpdate(true);
    }

    public final void updateMediaPanelOutline() {
        ((OplusQsBaseMediaPanelView) ((ViewController) this.mediaPanelViewController).mView).onOutlineUpdate();
    }

    public final void updateResources(Resources resources) {
        boolean z;
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSupportVolumeSeekBar()) {
            z = OplusQSSmallSpaceController.Companion.isSmallSpace(getContext(), false) && OplusQSDisplayModeUtils.isBigDisplayMode(getContext());
            if (this.mIsMediaMode) {
                this.mTopGap = resources.getDimensionPixelSize(z ? 2131172204 : 2131172205);
                this.mBottomGap = resources.getDimensionPixelSize(z ? 2131172195 : 2131172196);
                return;
            } else {
                this.mTopGap = resources.getDimensionPixelSize(z ? 2131172201 : 2131172206);
                this.mBottomGap = resources.getDimensionPixelSize(z ? 2131172193 : 2131172197);
                return;
            }
        }
        z = OplusQSSmallSpaceController.Companion.isSmallSpace(getContext(), false) && OplusQSDisplayModeUtils.isBigDisplayMode(getContext());
        if (this.mIsMediaMode) {
            this.mTopGap = resources.getDimensionPixelSize(z ? 2131172203 : 2131172202);
            this.mBottomGap = resources.getDimensionPixelSize(2131172194);
        } else {
            this.mTopGap = resources.getDimensionPixelSize(z ? 2131172200 : 2131172199);
            this.mBottomGap = resources.getDimensionPixelSize(z ? 2131172192 : 2131172191);
        }
    }

    public final void updateSliderCornerRadius() {
        OplusQsVolumeSliderController oplusQsVolumeSliderController = this.mVolumeSliderController;
        if (oplusQsVolumeSliderController != null) {
            ((OplusQsBaseToggleSliderLayout) oplusQsVolumeSliderController.mView).updateCornerRadius();
        }
        IBrightnessSliderController iBrightnessSliderController = this.mBrightnessSliderController;
        if (iBrightnessSliderController != null) {
            iBrightnessSliderController.updateSliderCornerRadius();
        }
    }

    public final void updateViewState(float f, float f2) {
        OplusQSTileMediaContainer oplusQSTileMediaContainer = (OplusQSTileMediaContainer) ((ViewController) this).mView;
        if (oplusQSTileMediaContainer != null) {
            oplusQSTileMediaContainer.updateViewState(f, f2);
        }
    }
}
