package com.oplus.systemui.separate;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.OplusBaseConfigurationAddon;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.qs.OplusQsColorManagerEx;
import com.android.systemui.qs.deprecated.BaseQuickStatusBarHeader;
import com.android.systemui.separate.OplusSimpleQSManager;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.SeparateModeHelper;
import com.oplus.systemui.blur.OplusQsColorManager;
import com.oplus.systemui.qs.OplusQuickStatusBarHeaderController;
import com.oplus.systemui.qs.widget.OplusQSDateView;
import com.oplus.systemui.seamless.FakeType;
import com.oplus.systemui.seamless.OplusFakeViewProvider;
import com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManager;
import com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1;
import com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManagerImpl;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.DisposableHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSimpleQSManagerImpl extends OplusSimpleQSManager implements ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusSimpleQSManagerImpl";
    private final NotificationPanelChildAppearAnimationManager appearAnimationManager = NotificationPanelChildAppearAnimationManager.Companion.create(true);
    private ViewGroup buttonContainer;
    private final Lazy centralSurfacesImpl;
    private final ConfigurationController configurationController;
    private final Context context;
    private final PanelAnimationEx.ExpandedFractionChangeListener fractionChangeListener;
    private ViewGroup headerContainer;
    private OplusQuickStatusBarHeaderController headerController;
    public int lastFlipFont;
    private final Lazy notificationEffectRepository;
    private final OplusFakeViewProvider oplusFakeViewProvider;
    private OplusQSSimpleHeader oplusQSSimpleHeader;
    private final OplusQsColorManager oplusQsColorManager;
    private final OplusSimpleQSFakeController oplusSimpleQSFakeController;
    private final PanelAnimationEx panelAnimationEx;
    private final PanelAnimationEx.PanelTranslationYChangeListener panelTranslationYChangeListener;
    private ViewGroup qSContaier;
    private final OplusQsColorManagerEx.QsColorStateObserver qsColorStateObserver;
    public boolean qsVisible;
    private View rootView;
    public boolean shouldShowFirstElement;
    private final QSHeaderFactory simpleHeaderFactory;
    private ViewGroup simpleQSContainer;
    private final SysuiStatusBarStateController statusBarStateController;
    private DisposableHandle strokeEnableDisposable;
    private final SysUiStateControllerImpl sysUiStateController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$FNWPUYLRXm6tny-JRkQTpbzh-yY, reason: not valid java name */
    public static Unit m364$r8$lambda$FNWPUYLRXm6tnyJRkQTpbzhyY(OplusSimpleQSManagerImpl oplusSimpleQSManagerImpl, float f) {
        ImageView settingsButton;
        OplusQSSimpleHeader oplusQSSimpleHeader = oplusSimpleQSManagerImpl.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null && (settingsButton = oplusQSSimpleHeader.getSettingsButton()) != null) {
            settingsButton.setTransitionAlpha(f);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$XuTJvBY7BsfNd3iHC5b_8jOn9wA(OplusSimpleQSManagerImpl oplusSimpleQSManagerImpl, float f) {
        OplusQSDateView dateView;
        ViewGroup nITZContainer;
        Clock clockView;
        ViewGroup statusIconsContainer;
        OplusQSSimpleHeader oplusQSSimpleHeader = oplusSimpleQSManagerImpl.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null && (statusIconsContainer = oplusQSSimpleHeader.getStatusIconsContainer()) != null) {
            statusIconsContainer.setTransitionAlpha(f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader2 = oplusSimpleQSManagerImpl.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader2 != null && (clockView = oplusQSSimpleHeader2.getClockView()) != null) {
            clockView.setTransitionAlpha(f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader3 = oplusSimpleQSManagerImpl.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader3 != null && (nITZContainer = oplusQSSimpleHeader3.getNITZContainer()) != null) {
            nITZContainer.setTransitionAlpha(f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader4 = oplusSimpleQSManagerImpl.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader4 != null && (dateView = oplusQSSimpleHeader4.getDateView()) != null) {
            dateView.setTransitionAlpha(f);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$hgOSARyIsE8i8JSaIPTM3r_aRoE(OplusSimpleQSManagerImpl oplusSimpleQSManagerImpl, float f) {
        ViewGroup nITZContainer;
        ImageView settingsButton;
        OplusQSDateView dateView;
        Clock clockView;
        ViewGroup statusIconsContainer;
        OplusQSSimpleHeader oplusQSSimpleHeader = oplusSimpleQSManagerImpl.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null && (statusIconsContainer = oplusQSSimpleHeader.getStatusIconsContainer()) != null) {
            statusIconsContainer.setTransitionAlpha(f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader2 = oplusSimpleQSManagerImpl.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader2 != null && (clockView = oplusQSSimpleHeader2.getClockView()) != null) {
            clockView.setTransitionAlpha(f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader3 = oplusSimpleQSManagerImpl.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader3 != null && (dateView = oplusQSSimpleHeader3.getDateView()) != null) {
            dateView.setTransitionAlpha(f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader4 = oplusSimpleQSManagerImpl.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader4 != null && (settingsButton = oplusQSSimpleHeader4.getSettingsButton()) != null) {
            settingsButton.setTransitionAlpha(f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader5 = oplusSimpleQSManagerImpl.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader5 != null && (nITZContainer = oplusQSSimpleHeader5.getNITZContainer()) != null) {
            nITZContainer.setTransitionAlpha(f);
        }
        return Unit.INSTANCE;
    }

    public OplusSimpleQSManagerImpl(Context context, QSHeaderFactory qSHeaderFactory, PanelAnimationEx panelAnimationEx, OplusQsColorManager oplusQsColorManager, SysuiStatusBarStateController sysuiStatusBarStateController, ConfigurationController configurationController, SysUiStateControllerImpl sysUiStateControllerImpl, Lazy lazy, OplusSimpleQSFakeController oplusSimpleQSFakeController, OplusFakeViewProvider oplusFakeViewProvider, Lazy lazy2) {
        this.context = context;
        this.simpleHeaderFactory = qSHeaderFactory;
        this.panelAnimationEx = panelAnimationEx;
        this.oplusQsColorManager = oplusQsColorManager;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.configurationController = configurationController;
        this.sysUiStateController = sysUiStateControllerImpl;
        this.centralSurfacesImpl = lazy;
        this.oplusSimpleQSFakeController = oplusSimpleQSFakeController;
        this.oplusFakeViewProvider = oplusFakeViewProvider;
        this.notificationEffectRepository = lazy2;
        OplusSimpleQSManagerImpl$qsColorStateObserver$1 oplusSimpleQSManagerImpl$qsColorStateObserver$1 = new OplusSimpleQSManagerImpl$qsColorStateObserver$1();
        oplusSimpleQSManagerImpl$qsColorStateObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsColorStateObserver = oplusSimpleQSManagerImpl$qsColorStateObserver$1;
        OplusSimpleQSManagerImpl$panelTranslationYChangeListener$1 oplusSimpleQSManagerImpl$panelTranslationYChangeListener$1 = new OplusSimpleQSManagerImpl$panelTranslationYChangeListener$1();
        oplusSimpleQSManagerImpl$panelTranslationYChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.panelTranslationYChangeListener = oplusSimpleQSManagerImpl$panelTranslationYChangeListener$1;
        OplusSimpleQSManagerImpl$fractionChangeListener$1 oplusSimpleQSManagerImpl$fractionChangeListener$1 = new OplusSimpleQSManagerImpl$fractionChangeListener$1();
        oplusSimpleQSManagerImpl$fractionChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.fractionChangeListener = oplusSimpleQSManagerImpl$fractionChangeListener$1;
        configurationController.addCallback(this);
    }

    public static final boolean access$isShadeLockState(OplusSimpleQSManagerImpl oplusSimpleQSManagerImpl) {
        return oplusSimpleQSManagerImpl.statusBarStateController.getCurrentOrUpcomingState() == 2;
    }

    public static final void access$updatePanelFraction(OplusSimpleQSManagerImpl oplusSimpleQSManagerImpl) {
        Clock clockView;
        ImageView settingsButton;
        int i = 0;
        int i2 = 1;
        boolean z = oplusSimpleQSManagerImpl.statusBarStateController.getState() == 1;
        boolean z2 = oplusSimpleQSManagerImpl.shouldShowFirstElement;
        boolean z3 = oplusSimpleQSManagerImpl.panelAnimationEx.isFirstElementShowing() && !z;
        oplusSimpleQSManagerImpl.shouldShowFirstElement = z3;
        if (z2 != z3) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("updatePanelFraction, shouldShowFirstElement: ", z3), null, 8);
            OplusQSSimpleHeader oplusQSSimpleHeader = oplusSimpleQSManagerImpl.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader == null || (clockView = oplusQSSimpleHeader.getClockView()) == null) {
                return;
            }
            boolean z4 = oplusSimpleQSManagerImpl.shouldShowFirstElement;
            NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager = oplusSimpleQSManagerImpl.appearAnimationManager;
            if (z4) {
                OplusSimpleQSManagerImpl$$ExternalSyntheticLambda0 oplusSimpleQSManagerImpl$$ExternalSyntheticLambda0 = new OplusSimpleQSManagerImpl$$ExternalSyntheticLambda0(i);
                oplusSimpleQSManagerImpl$$ExternalSyntheticLambda0.f$0 = oplusSimpleQSManagerImpl;
                VarHandle.storeStoreFence();
                ((NotificationPanelChildAppearAnimationManagerImpl) notificationPanelChildAppearAnimationManager).show(clockView, true, 0L, 0, (120 & 16) != 0 ? false : true, (120 & 32) != 0 ? 0.0f : 0.0f, new NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1(), oplusSimpleQSManagerImpl$$ExternalSyntheticLambda0);
                return;
            }
            OplusSimpleQSManagerImpl$$ExternalSyntheticLambda0 oplusSimpleQSManagerImpl$$ExternalSyntheticLambda1 = new OplusSimpleQSManagerImpl$$ExternalSyntheticLambda0(i2);
            oplusSimpleQSManagerImpl$$ExternalSyntheticLambda1.f$0 = oplusSimpleQSManagerImpl;
            VarHandle.storeStoreFence();
            NotificationPanelChildAppearAnimationManager.hide$default(notificationPanelChildAppearAnimationManager, clockView, true, 0.0f, oplusSimpleQSManagerImpl$$ExternalSyntheticLambda1, 56);
            OplusQSSimpleHeader oplusQSSimpleHeader2 = oplusSimpleQSManagerImpl.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader2 == null || (settingsButton = oplusQSSimpleHeader2.getSettingsButton()) == null) {
                return;
            }
            NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager2 = oplusSimpleQSManagerImpl.appearAnimationManager;
            OplusSimpleQSManagerImpl$$ExternalSyntheticLambda0 oplusSimpleQSManagerImpl$$ExternalSyntheticLambda2 = new OplusSimpleQSManagerImpl$$ExternalSyntheticLambda0(2);
            oplusSimpleQSManagerImpl$$ExternalSyntheticLambda2.f$0 = oplusSimpleQSManagerImpl;
            VarHandle.storeStoreFence();
            NotificationPanelChildAppearAnimationManager.hide$default(notificationPanelChildAppearAnimationManager2, settingsButton, true, 0.0f, oplusSimpleQSManagerImpl$$ExternalSyntheticLambda2, 52);
        }
    }

    public boolean getFirstElementShowingStatus() {
        return this.shouldShowFirstElement;
    }

    public ViewGroup getHeader() {
        return this.oplusQSSimpleHeader;
    }

    public final float getHeaderContainerToStatusbarMargin(int i) {
        ViewGroup viewGroup = this.headerContainer;
        int height = viewGroup != null ? viewGroup.getHeight() : 0;
        int i2 = height / 2;
        int i3 = i2 + i;
        OplusFakeViewProvider oplusFakeViewProvider = this.oplusFakeViewProvider;
        FakeType fakeType = FakeType.FAKE_SYSTEM_ICON;
        float realStatusElementLocY = oplusFakeViewProvider.getSimpleFakeView(fakeType).getRealStatusElementLocY();
        int iIntValue = ((Number) this.oplusFakeViewProvider.getSimpleFakeView(fakeType).getRealStatusElementParams().getSecond()).intValue() / 2;
        float f = iIntValue + realStatusElementLocY;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
            Float fValueOf = oplusQSSimpleHeader != null ? Float.valueOf(oplusQSSimpleHeader.getTranslationY()) : null;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("qSContaierMarginTop:", i, height, ",headerHeight:", ", headerMidHeight:");
            RectList$.ExternalSyntheticOutline0.m(i2, i3, ",getHeaderToStatusbarMargin headerToTopMargin ", ",statusbarLocationY：", sbM);
            ChildHelper$.ExternalSyntheticOutline0.m(realStatusElementLocY, iIntValue, ",statusbarHeight:", ",statusbarTopMargin :", sbM);
            sbM.append(f);
            sbM.append(", oplusQSSimpleHeader translationY: ");
            sbM.append(fValueOf);
            Log.d("SystemUi--Notification", "OplusSimpleQSManagerImpl-->".concat(sbM.toString()));
        }
        return i3 - f;
    }

    public ViewGroup getQSContainer() {
        return this.qSContaier;
    }

    public float getQSOffsetHeight() {
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        float simpleQSHeight = oplusQSSimpleHeader != null ? oplusQSSimpleHeader.getSimpleQSHeight() : 0.0f;
        ViewGroup viewGroup = this.simpleQSContainer;
        return simpleQSHeight + (viewGroup != null ? viewGroup.getTranslationY() : 0.0f);
    }

    public ViewGroup getStatusBarContainer() {
        return this.headerContainer;
    }

    public float getTopMargin() {
        return 0.0f;
    }

    public final void onAZoneHeightChanged(int i) {
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null) {
            oplusQSSimpleHeader.onAZoneHeightChanged(i);
        }
    }

    public void onConfigChanged(Configuration configuration) {
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null) {
            oplusQSSimpleHeader.updateSimpleQsContainerPadding();
        }
    }

    public final void onDensityOrFontScaleChanged() {
        if (SeparateModeHelper.INSTANCE.getSeparateMode()) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onDensityOrFontScaleChanged, reInflateHeader", null, 8);
            reInflateHeader();
        }
    }

    public final void onDestroy() {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onDestroy", null, 8);
        DisposableHandle disposableHandle = this.strokeEnableDisposable;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        this.strokeEnableDisposable = null;
        OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController = this.headerController;
        if (oplusQuickStatusBarHeaderController != null) {
            oplusQuickStatusBarHeaderController.destroy();
        }
        this.headerController = null;
        this.oplusQsColorManager.removeQsColorStateObserver(this.qsColorStateObserver);
        if (this.panelAnimationEx.getUseLayeredAnimation()) {
            this.panelAnimationEx.getTranslationYValue().removeCallback(this.panelTranslationYChangeListener);
            this.panelAnimationEx.getPanelExpandFraction().removeCallback(this.fractionChangeListener);
        }
        this.oplusSimpleQSFakeController.onDestroy();
        this.rootView = null;
    }

    public void onFontChanged(Configuration configuration) {
        int flipFont;
        if (SeparateModeHelper.INSTANCE.getSeparateMode() && (flipFont = OplusBaseConfigurationAddon.getFlipFont(configuration)) != this.lastFlipFont) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onFontChanged reInflateHeader", null, 8);
            this.lastFlipFont = flipFont;
            reInflateHeader();
        }
    }

    public final void onFontWeightChanged() {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onFontWeightChanged, reInflateButtonContainer", null, 8);
        ViewGroup viewGroup = this.buttonContainer;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        View viewInflate = LayoutInflater.from(this.context).inflate(2131559370, (ViewGroup) null, false);
        ViewGroup viewGroup2 = this.buttonContainer;
        if (viewGroup2 != null) {
            viewGroup2.addView(viewInflate);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null) {
            oplusQSSimpleHeader.onInit();
        }
        View view = this.rootView;
        if (view != null) {
            this.oplusSimpleQSFakeController.initView(view);
        }
    }

    public final void onOrientationChanged(int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "onOrientationChanged, orientation ", "OplusSimpleQSManagerImpl-->", "SystemUi--Notification");
        }
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null) {
            OplusQSSimpleHeader.Companion companion = OplusQSSimpleHeader.Companion;
            oplusQSSimpleHeader.updateMarginTop("onOrientationChanged", null);
        }
        this.oplusSimpleQSFakeController.updateFakeMarginTop("onOrientationChanged", null);
    }

    public final void onOverlayChanged() {
        if (SeparateModeHelper.INSTANCE.getSeparateMode()) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onOverlayChanged, reInflateHeader", null, 8);
            reInflateHeader();
        }
    }

    public final void onUiModeChanged() {
        OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController = this.headerController;
        if (oplusQuickStatusBarHeaderController != null) {
            oplusQuickStatusBarHeaderController.updateColor();
        }
    }

    public void onViewCreated(View view) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onViewCreated", null, 8);
        this.rootView = view;
        BaseQuickStatusBarHeader baseQuickStatusBarHeaderRequireViewById = view.requireViewById(2131363334);
        this.qSContaier = (ViewGroup) view.requireViewById(2131365224);
        this.headerContainer = (ViewGroup) view.requireViewById(2131363336);
        this.buttonContainer = (ViewGroup) view.requireViewById(2131362434);
        this.simpleQSContainer = (ViewGroup) view.requireViewById(2131365224);
        this.oplusQSSimpleHeader = (OplusQSSimpleHeader) view.requireViewById(2131365229);
        OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderControllerCreate = this.simpleHeaderFactory.create(baseQuickStatusBarHeaderRequireViewById);
        this.headerController = oplusQuickStatusBarHeaderControllerCreate;
        if (oplusQuickStatusBarHeaderControllerCreate != null) {
            oplusQuickStatusBarHeaderControllerCreate.init();
        }
        this.oplusQsColorManager.addQsColorStateObserver(this.qsColorStateObserver);
        refreshColor();
        View view2 = this.rootView;
        if (view2 != null) {
            this.oplusSimpleQSFakeController.onViewCreated(view2);
        }
        if (this.panelAnimationEx.getUseLayeredAnimation()) {
            this.panelAnimationEx.getTranslationYValue().addCallback(this.panelTranslationYChangeListener);
            this.panelAnimationEx.getPanelExpandFraction().addCallback(this.fractionChangeListener);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader == null) {
            return;
        }
        DisposableHandle disposableHandle = this.strokeEnableDisposable;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        this.strokeEnableDisposable = RepeatWhenAttachedKt.repeatWhenAttached$default(oplusQSSimpleHeader, (CoroutineContext) null, new OplusSimpleQSManagerImpl$bindSettingsStrokeEnableObserver$1(this, oplusQSSimpleHeader, null), 3);
    }

    public final void reInflateHeader() {
        ViewGroup viewGroup = this.headerContainer;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController = this.headerController;
        if (oplusQuickStatusBarHeaderController != null) {
            oplusQuickStatusBarHeaderController.destroy();
        }
        OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderControllerCreate = this.simpleHeaderFactory.create(null);
        this.headerController = oplusQuickStatusBarHeaderControllerCreate;
        if (oplusQuickStatusBarHeaderControllerCreate != null) {
            oplusQuickStatusBarHeaderControllerCreate.init();
        }
        OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController2 = this.headerController;
        View view = oplusQuickStatusBarHeaderController2 != null ? oplusQuickStatusBarHeaderController2.getView() : null;
        ViewGroup viewGroup2 = this.headerContainer;
        if (viewGroup2 != null) {
            viewGroup2.addView(view);
        }
        ViewGroup viewGroup3 = this.buttonContainer;
        if (viewGroup3 != null) {
            viewGroup3.removeAllViews();
        }
        View viewInflate = LayoutInflater.from(this.context).inflate(2131559370, (ViewGroup) null, false);
        ViewGroup viewGroup4 = this.buttonContainer;
        if (viewGroup4 != null) {
            viewGroup4.addView(viewInflate);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null) {
            oplusQSSimpleHeader.onInit();
        }
        View view2 = this.rootView;
        if (view2 != null) {
            this.oplusSimpleQSFakeController.initView(view2);
        }
    }

    public final void refreshColor() {
        OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController = this.headerController;
        if (oplusQuickStatusBarHeaderController != null) {
            oplusQuickStatusBarHeaderController.updateColor();
        }
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null) {
            oplusQSSimpleHeader.updateTextColor();
        }
    }

    public void setListening(boolean z) {
        OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController = this.headerController;
        if (oplusQuickStatusBarHeaderController != null) {
            oplusQuickStatusBarHeaderController.setListening(z);
        }
    }

    public void setQsVisible(boolean z) {
        if (this.qsVisible != z) {
            this.qsVisible = z;
            CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) this.centralSurfacesImpl.get();
            ShadeViewController shadeViewController = centralSurfacesImpl != null ? centralSurfacesImpl.getShadeViewController() : null;
            NotificationPanelViewController notificationPanelViewController = shadeViewController instanceof NotificationPanelViewController ? (NotificationPanelViewController) shadeViewController : null;
            boolean z2 = false;
            boolean z3 = (notificationPanelViewController != null ? notificationPanelViewController.getExpandedFraction() : 0.0f) > 0.0f;
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("setQsVisible: ", ", ", z, z3), null, 8);
            SysUiStateControllerImpl sysUiStateControllerImpl = this.sysUiStateController;
            if (z && z3) {
                z2 = true;
            }
            sysUiStateControllerImpl.onSeparateNtExpand(z2, this.oplusSimpleQSFakeController.getQSFakeContainer());
        }
    }

    public final void updateFakeLocation() {
        this.oplusSimpleQSFakeController.updateFakeElementLoc();
        this.oplusSimpleQSFakeController.initFakeContainLocation();
        this.oplusSimpleQSFakeController.updateClockPivot$1();
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null) {
            oplusQSSimpleHeader.setTranslationY(0.0f);
        }
    }

    public void updateTopMargin(String str) {
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null) {
            OplusQSSimpleHeader.Companion companion = OplusQSSimpleHeader.Companion;
            oplusQSSimpleHeader.updateMarginTop(str, null);
        }
        this.oplusSimpleQSFakeController.updateFakeMarginTop(str, null);
    }
}
