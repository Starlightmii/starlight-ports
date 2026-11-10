package com.oplus.systemui.separate;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMapBuilderLinksIterator$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.core.view.insets.Protection$;
import androidx.fragment.app.Fragment$5$;
import com.android.keyguard.OplusLockIconView;
import com.android.keyguard.OplusLockIconView$;
import com.android.launcher3.icons.IconProvider$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.DependencyEx;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.shade.NotificationShadeWindowViewController;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.data.repository.HomeStatusBarComponentsRepository;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.phone.PhoneStatusBarView;
import com.android.systemui.statusbar.phone.fragment.dagger.HomeStatusBarComponent;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.blur.OplusNotificationTiltShiftBlurContainer;
import com.oplus.systemui.notification.blur.OplusNotificationTiltShiftBlurController;
import com.oplus.systemui.qs.res.domain.util.ResourcesUtilKt;
import com.oplus.systemui.qs.widget.OplusQSDateView;
import com.oplus.systemui.seamless.FakeType;
import com.oplus.systemui.seamless.OplusFakeViewProvider;
import com.oplus.systemui.seamless.widget.StatusBarFakeFrame;
import com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManager;
import com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1;
import com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManagerImpl;
import com.oplus.systemui.statusbar.seeding.IScaledAncestorView;
import com.oplus.systemui.statusbar.seeding.ScaleChangedListener;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Map;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSimpleQSFakeController implements ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    private static final String SETTINGS_KEY = "display_density_index_manual";
    private static final String TAG = "OplusSimpleQSFakeController";
    public float clockScaleFactor;
    private final ConfigurationController configurationController;
    private final Context context;
    private final OplusSimpleQSFakeController$elementShowStateChangeListener$1 elementShowStateChangeListener;
    public float extraSimpleQSFootTYFractionMapping;
    private FrameLayout fakeCarrierContainer;
    private FrameLayout fakeClockViewContainer;
    private FrameLayout fakeDynamicIconsContainer;
    private FrameLayout fakeNotificationContainer;
    private FrameLayout fakeStatusBarIconsContainer;
    public float fakeToClockTranslationX;
    public float fakeToClockTranslationY;
    public float fakeToDateViewTranslationX;
    public float fakeToDateViewTranslationY;
    public float fakeToEndStatusBarTranslationX;
    public float fakeToEndStatusBarTranslationY;
    public float fakeToNotificationTranslationX;
    private final PanelAnimationEx.ExpandedFractionChangeListener fractionChangeListener;
    private final HomeStatusBarComponentsRepository homeStatusBarComponentsRepository;
    public boolean isFakeClockGone;
    public boolean isLargeDisplay;
    private final View.OnLayoutChangeListener layoutChangedListener;
    private final LogBuffer logBuffer;
    public float nitzTranslationX;
    private final Lazy notificationShadeWindowViewController;
    private ViewGroup oplusButtonContainer;
    private ViewGroup oplusButtonContainerParent;
    private final OplusFakeViewProvider oplusFakeViewProvider;
    private ViewGroup oplusHeaderContainer;
    private ViewGroup oplusQSFakeContainer;
    private ViewGroup oplusQSFakeFixedContainer;
    private View oplusQSFrame;
    private OplusQSSimpleHeader oplusQSSimpleHeader;
    private ViewGroup oplusSimpleQsContainer;
    private ViewGroup oplusStatusBarIconContainer;
    private ViewGroup oplusSubHeader;
    private final PanelAnimationEx panelAnimationEx;
    public float panelThreshold;
    private final PanelAnimationEx.PanelTranslationYChangeListener panelTranslationYChangeListener;
    public float qsSettingsButtonViewTranslationX;
    public float qsSettingsButtonViewTranslationY;
    private final Lazy separateNotificationAndQSEx;
    public boolean shouldShowFirstElement;
    public boolean shouldShowFixedElement;
    private final SysuiStatusBarStateController statusBarStateController;
    private final View.OnLayoutChangeListener statusIconsLayoutListener;
    public float statusbarClockPadding;
    public float systemFontScale;
    private final ScaleChangedListener systemIconsScaleChangedListener;
    private final Lazy tiltShiftBlurController;
    private final NotificationPanelChildAppearAnimationManager appearAnimationManager = NotificationPanelChildAppearAnimationManager.Companion.create(true);
    public boolean isUpdateFakeViewLocation = true;
    public boolean updateExtraSimpleQSFootTYFraction = true;
    public float lastExtraFootFractionTemp = Float.NaN;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Unit $r8$lambda$1xfwNsF4p8YYMupnamCa8Cgizmg(OplusSimpleQSFakeController oplusSimpleQSFakeController, float f) {
        FrameLayout frameLayout = oplusSimpleQSFakeController.fakeClockViewContainer;
        if (frameLayout != null) {
            frameLayout.setTransitionAlpha(f);
        }
        FrameLayout frameLayout2 = oplusSimpleQSFakeController.fakeStatusBarIconsContainer;
        if (frameLayout2 != null) {
            frameLayout2.setTransitionAlpha(f);
        }
        FrameLayout frameLayout3 = oplusSimpleQSFakeController.fakeDynamicIconsContainer;
        if (frameLayout3 != null) {
            frameLayout3.setTransitionAlpha(f);
        }
        FrameLayout frameLayout4 = oplusSimpleQSFakeController.fakeNotificationContainer;
        if (frameLayout4 != null) {
            frameLayout4.setTransitionAlpha(f);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$ME_AiNyFMpdfsPQVV9YmpTXHR2w(OplusSimpleQSFakeController oplusSimpleQSFakeController, float f) {
        FrameLayout frameLayout;
        if (oplusSimpleQSFakeController.oplusFakeViewProvider.getSimpleFakeView(FakeType.FAKE_CARRIER).shouldAnimation() && (frameLayout = oplusSimpleQSFakeController.fakeCarrierContainer) != null) {
            frameLayout.setTransitionAlpha(f);
        }
        if (oplusSimpleQSFakeController.isNotSupportSeamlessTransfer()) {
            FrameLayout frameLayout2 = oplusSimpleQSFakeController.fakeClockViewContainer;
            if (frameLayout2 != null) {
                frameLayout2.setTransitionAlpha(f);
            }
            FrameLayout frameLayout3 = oplusSimpleQSFakeController.fakeStatusBarIconsContainer;
            if (frameLayout3 != null) {
                frameLayout3.setTransitionAlpha(f);
            }
            FrameLayout frameLayout4 = oplusSimpleQSFakeController.fakeDynamicIconsContainer;
            if (frameLayout4 != null) {
                frameLayout4.setTransitionAlpha(f);
            }
            FrameLayout frameLayout5 = oplusSimpleQSFakeController.fakeNotificationContainer;
            if (frameLayout5 != null) {
                frameLayout5.setTransitionAlpha(f);
            }
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$O2f1BykaRjBeM_NuJCmRgYOj2SY(OplusSimpleQSFakeController oplusSimpleQSFakeController, float f) {
        FrameLayout frameLayout;
        if (oplusSimpleQSFakeController.oplusFakeViewProvider.getSimpleFakeView(FakeType.FAKE_CARRIER).shouldAnimation() && (frameLayout = oplusSimpleQSFakeController.fakeCarrierContainer) != null) {
            frameLayout.setTransitionAlpha(f);
        }
        if (oplusSimpleQSFakeController.isNotSupportSeamlessTransfer()) {
            FrameLayout frameLayout2 = oplusSimpleQSFakeController.fakeClockViewContainer;
            if (frameLayout2 != null) {
                frameLayout2.setTransitionAlpha(f);
            }
            FrameLayout frameLayout3 = oplusSimpleQSFakeController.fakeStatusBarIconsContainer;
            if (frameLayout3 != null) {
                frameLayout3.setTransitionAlpha(f);
            }
            FrameLayout frameLayout4 = oplusSimpleQSFakeController.fakeDynamicIconsContainer;
            if (frameLayout4 != null) {
                frameLayout4.setTransitionAlpha(f);
            }
            FrameLayout frameLayout5 = oplusSimpleQSFakeController.fakeNotificationContainer;
            if (frameLayout5 != null) {
                frameLayout5.setTransitionAlpha(f);
            }
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$WmYaoVf8c5ZNLJtbdtxQpsd7jbE(OplusSimpleQSFakeController oplusSimpleQSFakeController, float f) {
        FrameLayout frameLayout = oplusSimpleQSFakeController.fakeClockViewContainer;
        if (frameLayout != null) {
            frameLayout.setTransitionAlpha(f);
        }
        FrameLayout frameLayout2 = oplusSimpleQSFakeController.fakeStatusBarIconsContainer;
        if (frameLayout2 != null) {
            frameLayout2.setTransitionAlpha(f);
        }
        FrameLayout frameLayout3 = oplusSimpleQSFakeController.fakeDynamicIconsContainer;
        if (frameLayout3 != null) {
            frameLayout3.setTransitionAlpha(f);
        }
        FrameLayout frameLayout4 = oplusSimpleQSFakeController.fakeNotificationContainer;
        if (frameLayout4 != null) {
            frameLayout4.setTransitionAlpha(f);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$iBfVNmV1JR6Nqi55jaCBN9T_XR8(OplusSimpleQSFakeController oplusSimpleQSFakeController, boolean z) {
        View view = oplusSimpleQSFakeController.oplusQSFrame;
        if (z) {
            if (view != null) {
                view.setTranslationZ(1.0f);
            }
        } else if (view != null) {
            view.setTranslationZ(0.0f);
        }
        return Unit.INSTANCE;
    }

    public OplusSimpleQSFakeController(Context context, PanelAnimationEx panelAnimationEx, SysuiStatusBarStateController sysuiStatusBarStateController, HomeStatusBarComponentsRepository homeStatusBarComponentsRepository, OplusFakeViewProvider oplusFakeViewProvider, ConfigurationController configurationController, Lazy lazy, Lazy lazy2, Lazy lazy3, LogBuffer logBuffer) {
        this.context = context;
        this.panelAnimationEx = panelAnimationEx;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.homeStatusBarComponentsRepository = homeStatusBarComponentsRepository;
        this.oplusFakeViewProvider = oplusFakeViewProvider;
        this.configurationController = configurationController;
        this.separateNotificationAndQSEx = lazy;
        this.notificationShadeWindowViewController = lazy2;
        this.tiltShiftBlurController = lazy3;
        this.logBuffer = logBuffer;
        OplusSimpleQSFakeController$panelTranslationYChangeListener$1 oplusSimpleQSFakeController$panelTranslationYChangeListener$1 = new OplusSimpleQSFakeController$panelTranslationYChangeListener$1();
        oplusSimpleQSFakeController$panelTranslationYChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.panelTranslationYChangeListener = oplusSimpleQSFakeController$panelTranslationYChangeListener$1;
        OplusSimpleQSFakeController$layoutChangedListener$1 oplusSimpleQSFakeController$layoutChangedListener$1 = new OplusSimpleQSFakeController$layoutChangedListener$1(0);
        oplusSimpleQSFakeController$layoutChangedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.layoutChangedListener = oplusSimpleQSFakeController$layoutChangedListener$1;
        OplusSimpleQSFakeController$fractionChangeListener$1 oplusSimpleQSFakeController$fractionChangeListener$1 = new OplusSimpleQSFakeController$fractionChangeListener$1();
        oplusSimpleQSFakeController$fractionChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.fractionChangeListener = oplusSimpleQSFakeController$fractionChangeListener$1;
        OplusSimpleQSFakeController$elementShowStateChangeListener$1 oplusSimpleQSFakeController$elementShowStateChangeListener$1 = new OplusSimpleQSFakeController$elementShowStateChangeListener$1();
        oplusSimpleQSFakeController$elementShowStateChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.elementShowStateChangeListener = oplusSimpleQSFakeController$elementShowStateChangeListener$1;
        OplusSimpleQSFakeController$systemIconsScaleChangedListener$1 oplusSimpleQSFakeController$systemIconsScaleChangedListener$1 = new OplusSimpleQSFakeController$systemIconsScaleChangedListener$1();
        oplusSimpleQSFakeController$systemIconsScaleChangedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.systemIconsScaleChangedListener = oplusSimpleQSFakeController$systemIconsScaleChangedListener$1;
        OplusSimpleQSFakeController$layoutChangedListener$1 oplusSimpleQSFakeController$layoutChangedListener$2 = new OplusSimpleQSFakeController$layoutChangedListener$1(1);
        oplusSimpleQSFakeController$layoutChangedListener$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusIconsLayoutListener = oplusSimpleQSFakeController$layoutChangedListener$2;
        configurationController.addCallback(this);
    }

    public static final boolean access$isShadeLockState(OplusSimpleQSFakeController oplusSimpleQSFakeController) {
        return oplusSimpleQSFakeController.statusBarStateController.getCurrentOrUpcomingState() == 2;
    }

    public static final void access$resetQsViewsExpandLocation(OplusSimpleQSFakeController oplusSimpleQSFakeController) {
        ViewGroup nITZContainer;
        ViewGroup nITZContainer2;
        ImageView settingsButton;
        ImageView settingsButton2;
        OplusQSDateView dateView;
        OplusQSDateView dateView2;
        Clock clockView;
        Clock clockView2;
        OplusQSSimpleHeader oplusQSSimpleHeader = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null && (clockView2 = oplusQSSimpleHeader.getClockView()) != null) {
            clockView2.setTranslationX(0.0f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader2 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader2 != null && (clockView = oplusQSSimpleHeader2.getClockView()) != null) {
            clockView.setTranslationY(0.0f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader3 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader3 != null && (dateView2 = oplusQSSimpleHeader3.getDateView()) != null) {
            dateView2.setTranslationX(0.0f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader4 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader4 != null && (dateView = oplusQSSimpleHeader4.getDateView()) != null) {
            dateView.setTranslationY(0.0f);
        }
        ViewGroup viewGroup = oplusSimpleQSFakeController.oplusStatusBarIconContainer;
        if (viewGroup != null) {
            viewGroup.setTranslationX(0.0f);
        }
        ViewGroup viewGroup2 = oplusSimpleQSFakeController.oplusStatusBarIconContainer;
        if (viewGroup2 != null) {
            viewGroup2.setTranslationY(0.0f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader5 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader5 != null && (settingsButton2 = oplusQSSimpleHeader5.getSettingsButton()) != null) {
            settingsButton2.setTranslationX(0.0f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader6 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader6 != null && (settingsButton = oplusQSSimpleHeader6.getSettingsButton()) != null) {
            settingsButton.setTranslationY(0.0f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader7 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader7 != null && (nITZContainer2 = oplusQSSimpleHeader7.getNITZContainer()) != null) {
            nITZContainer2.setTranslationX(0.0f);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader8 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader8 != null && (nITZContainer = oplusQSSimpleHeader8.getNITZContainer()) != null) {
            nITZContainer.setTranslationY(0.0f);
        }
        oplusSimpleQSFakeController.updateQSClockScale(1.0f);
    }

    public static final void access$resetfakeViewsStartLocation(OplusSimpleQSFakeController oplusSimpleQSFakeController) {
        FrameLayout frameLayout = oplusSimpleQSFakeController.fakeClockViewContainer;
        if (frameLayout != null) {
            frameLayout.setTranslationX(0.0f);
        }
        FrameLayout frameLayout2 = oplusSimpleQSFakeController.fakeClockViewContainer;
        if (frameLayout2 != null) {
            frameLayout2.setTranslationY(0.0f);
        }
        FrameLayout frameLayout3 = oplusSimpleQSFakeController.fakeNotificationContainer;
        if (frameLayout3 != null) {
            frameLayout3.setTranslationX(0.0f);
        }
        FrameLayout frameLayout4 = oplusSimpleQSFakeController.fakeNotificationContainer;
        if (frameLayout4 != null) {
            frameLayout4.setTranslationY(0.0f);
        }
        FrameLayout frameLayout5 = oplusSimpleQSFakeController.fakeDynamicIconsContainer;
        if (frameLayout5 != null) {
            frameLayout5.setTranslationX(0.0f);
        }
        FrameLayout frameLayout6 = oplusSimpleQSFakeController.fakeStatusBarIconsContainer;
        if (frameLayout6 != null) {
            frameLayout6.setTranslationX(0.0f);
        }
        FrameLayout frameLayout7 = oplusSimpleQSFakeController.fakeStatusBarIconsContainer;
        if (frameLayout7 != null) {
            frameLayout7.setTranslationY(0.0f);
        }
        oplusSimpleQSFakeController.updateFakeClockScale(0.0f);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0048  */
    /* JADX WARN: Code duplicated, block: B:24:0x004c  */
    /* JADX WARN: Code duplicated, block: B:27:0x0053  */
    /* JADX WARN: Code duplicated, block: B:31:0x005d  */
    /* JADX WARN: Code duplicated, block: B:33:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:34:? A[RETURN, SYNTHETIC] */
    public static final void access$updateExtraSimpleQSFootTranslationY(OplusSimpleQSFakeController oplusSimpleQSFakeController, float f) {
        boolean z;
        OplusQSSimpleHeader oplusQSSimpleHeader;
        if (!oplusSimpleQSFakeController.panelAnimationEx.isFirstElementShowing()) {
            SeparatePanelAnimation.Companion companion = SeparatePanelAnimation.Companion;
            if (companion.getExtraSimpleQSFootTranslationY() <= 0.0f || Float.isNaN(oplusSimpleQSFakeController.lastExtraFootFractionTemp) || f <= oplusSimpleQSFakeController.lastExtraFootFractionTemp + 0.004f) {
                if (companion.getExtraSimpleQSFootTranslationY() > 0.0f) {
                    z = true;
                }
                oplusSimpleQSFakeController.lastExtraFootFractionTemp = f;
                if (z) {
                    if (oplusSimpleQSFakeController.updateExtraSimpleQSFootTYFraction) {
                        oplusSimpleQSFakeController.updateExtraSimpleQSFootTYFraction = false;
                        oplusSimpleQSFakeController.extraSimpleQSFootTYFractionMapping = f != 0.0f ? 1.0f / f : 0.0f;
                    }
                    oplusQSSimpleHeader = oplusSimpleQSFakeController.oplusQSSimpleHeader;
                    if (oplusQSSimpleHeader != null) {
                        oplusQSSimpleHeader.setTranslationY(SeparatePanelAnimation.Companion.getExtraSimpleQSFootTranslationY() * f * oplusSimpleQSFakeController.extraSimpleQSFootTYFractionMapping);
                    }
                }
                return;
            }
            oplusSimpleQSFakeController.resetExtraTakeOverCondition();
        } else if (SeparatePanelAnimation.Companion.getExtraSimpleQSFootTranslationY() > 0.0f) {
            oplusSimpleQSFakeController.resetExtraTakeOverCondition();
        }
        z = false;
        oplusSimpleQSFakeController.lastExtraFootFractionTemp = f;
        if (z) {
            return;
        }
        if (oplusSimpleQSFakeController.updateExtraSimpleQSFootTYFraction) {
            oplusSimpleQSFakeController.updateExtraSimpleQSFootTYFraction = false;
            oplusSimpleQSFakeController.extraSimpleQSFootTYFractionMapping = f != 0.0f ? 1.0f / f : 0.0f;
        }
        oplusQSSimpleHeader = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null) {
            oplusQSSimpleHeader.setTranslationY(SeparatePanelAnimation.Companion.getExtraSimpleQSFootTranslationY() * f * oplusSimpleQSFakeController.extraSimpleQSFootTYFractionMapping);
        }
    }

    public static final void access$updateFakeFraction(OplusSimpleQSFakeController oplusSimpleQSFakeController) {
        int i = 0;
        int i2 = 1;
        boolean z = oplusSimpleQSFakeController.statusBarStateController.getState() == 1;
        boolean z2 = oplusSimpleQSFakeController.shouldShowFirstElement;
        boolean z3 = oplusSimpleQSFakeController.shouldShowFixedElement;
        oplusSimpleQSFakeController.shouldShowFirstElement = oplusSimpleQSFakeController.panelAnimationEx.isFirstElementShowing() && !z;
        oplusSimpleQSFakeController.shouldShowFixedElement = oplusSimpleQSFakeController.panelAnimationEx.isFixedElementShowing() && !z;
        if (z2 != oplusSimpleQSFakeController.shouldShowFirstElement) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("updateFakeFraction, shouldShowFirstElement: ", "OplusSimpleQSFakeController-->", "SystemUi--Notification", oplusSimpleQSFakeController.shouldShowFirstElement);
            }
            FrameLayout frameLayout = oplusSimpleQSFakeController.fakeClockViewContainer;
            if (frameLayout != null) {
                boolean z4 = oplusSimpleQSFakeController.shouldShowFirstElement;
                if (!oplusSimpleQSFakeController.isNotSupportSeamlessTransfer()) {
                    if (z4) {
                        oplusSimpleQSFakeController.resetExtraTakeOverCondition();
                        NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager = oplusSimpleQSFakeController.appearAnimationManager;
                        OplusSimpleQSFakeController$$ExternalSyntheticLambda1 oplusSimpleQSFakeController$$ExternalSyntheticLambda1 = new OplusSimpleQSFakeController$$ExternalSyntheticLambda1(3);
                        oplusSimpleQSFakeController$$ExternalSyntheticLambda1.f$0 = oplusSimpleQSFakeController;
                        VarHandle.storeStoreFence();
                        NotificationPanelChildAppearAnimationManager.hide$default(notificationPanelChildAppearAnimationManager, frameLayout, true, 0.0f, oplusSimpleQSFakeController$$ExternalSyntheticLambda1, 56);
                    } else {
                        SeparatePanelAnimation.Companion companion = SeparatePanelAnimation.Companion;
                        OplusQSSimpleHeader oplusQSSimpleHeader = oplusSimpleQSFakeController.oplusQSSimpleHeader;
                        companion.setExtraSimpleQSFootTranslationY(oplusQSSimpleHeader != null ? oplusQSSimpleHeader.getTranslationY() : 0.0f);
                        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, Protection$.ExternalSyntheticOutline0.m(companion.getExtraSimpleQSFootTranslationY(), "show fake view, set extraSimpleQSFootTranslationY: "), null, 8);
                        NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager2 = oplusSimpleQSFakeController.appearAnimationManager;
                        OplusSimpleQSFakeController$$ExternalSyntheticLambda1 oplusSimpleQSFakeController$$ExternalSyntheticLambda2 = new OplusSimpleQSFakeController$$ExternalSyntheticLambda1(2);
                        oplusSimpleQSFakeController$$ExternalSyntheticLambda2.f$0 = oplusSimpleQSFakeController;
                        VarHandle.storeStoreFence();
                        ((NotificationPanelChildAppearAnimationManagerImpl) notificationPanelChildAppearAnimationManager2).show(frameLayout, true, 0L, 0, (120 & 16) != 0 ? false : true, (120 & 32) != 0 ? 0.0f : 0.0f, new NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1(), oplusSimpleQSFakeController$$ExternalSyntheticLambda2);
                    }
                }
            }
        }
        if (z3 != oplusSimpleQSFakeController.shouldShowFixedElement) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("updateFakeFraction, shouldShowFixedElement: ", "OplusSimpleQSFakeController-->", "SystemUi--Notification", oplusSimpleQSFakeController.shouldShowFixedElement);
            }
            FrameLayout frameLayout2 = oplusSimpleQSFakeController.fakeCarrierContainer;
            if (frameLayout2 != null) {
                boolean z5 = oplusSimpleQSFakeController.shouldShowFixedElement;
                if (oplusSimpleQSFakeController.isNotSupportSeamlessTransfer() || oplusSimpleQSFakeController.oplusFakeViewProvider.getSimpleFakeView(FakeType.FAKE_CARRIER).shouldAnimation()) {
                    NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager3 = oplusSimpleQSFakeController.appearAnimationManager;
                    if (z5) {
                        OplusSimpleQSFakeController$$ExternalSyntheticLambda1 oplusSimpleQSFakeController$$ExternalSyntheticLambda3 = new OplusSimpleQSFakeController$$ExternalSyntheticLambda1(i2);
                        oplusSimpleQSFakeController$$ExternalSyntheticLambda3.f$0 = oplusSimpleQSFakeController;
                        VarHandle.storeStoreFence();
                        NotificationPanelChildAppearAnimationManager.hide$default(notificationPanelChildAppearAnimationManager3, frameLayout2, true, 0.0f, oplusSimpleQSFakeController$$ExternalSyntheticLambda3, 56);
                        return;
                    }
                    OplusSimpleQSFakeController$$ExternalSyntheticLambda1 oplusSimpleQSFakeController$$ExternalSyntheticLambda4 = new OplusSimpleQSFakeController$$ExternalSyntheticLambda1(i);
                    oplusSimpleQSFakeController$$ExternalSyntheticLambda4.f$0 = oplusSimpleQSFakeController;
                    VarHandle.storeStoreFence();
                    ((NotificationPanelChildAppearAnimationManagerImpl) notificationPanelChildAppearAnimationManager3).show(frameLayout2, true, 0L, 0, (120 & 16) != 0 ? false : true, (120 & 32) != 0 ? 0.0f : 0.0f, new NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1(), oplusSimpleQSFakeController$$ExternalSyntheticLambda4);
                }
            }
        }
    }

    public static final void access$updateFakeSimpleQSTranslation(OplusSimpleQSFakeController oplusSimpleQSFakeController, float f) {
        FrameLayout frameLayout = oplusSimpleQSFakeController.fakeClockViewContainer;
        if (frameLayout != null) {
            frameLayout.setTranslationX((-oplusSimpleQSFakeController.fakeToClockTranslationX) * f);
        }
        FrameLayout frameLayout2 = oplusSimpleQSFakeController.fakeClockViewContainer;
        if (frameLayout2 != null) {
            frameLayout2.setTranslationY(oplusSimpleQSFakeController.fakeToClockTranslationY * f);
        }
        FrameLayout frameLayout3 = oplusSimpleQSFakeController.fakeNotificationContainer;
        if (frameLayout3 != null) {
            frameLayout3.setTranslationX((-oplusSimpleQSFakeController.fakeToNotificationTranslationX) * f);
        }
        FrameLayout frameLayout4 = oplusSimpleQSFakeController.fakeNotificationContainer;
        if (frameLayout4 != null) {
            frameLayout4.setTranslationY(oplusSimpleQSFakeController.fakeToClockTranslationY * f);
        }
        FrameLayout frameLayout5 = oplusSimpleQSFakeController.fakeDynamicIconsContainer;
        if (frameLayout5 != null) {
            frameLayout5.setTranslationX((-oplusSimpleQSFakeController.fakeToEndStatusBarTranslationX) * f);
        }
        FrameLayout frameLayout6 = oplusSimpleQSFakeController.fakeStatusBarIconsContainer;
        if (frameLayout6 != null) {
            frameLayout6.setTranslationX((-oplusSimpleQSFakeController.fakeToEndStatusBarTranslationX) * f);
        }
        FrameLayout frameLayout7 = oplusSimpleQSFakeController.fakeStatusBarIconsContainer;
        if (frameLayout7 != null) {
            frameLayout7.setTranslationY(f * oplusSimpleQSFakeController.fakeToEndStatusBarTranslationY);
        }
    }

    public static final void access$updateFakeToStatusBarTranslation(OplusSimpleQSFakeController oplusSimpleQSFakeController) {
        float left;
        String str;
        float left2;
        String str2;
        float left3;
        float left4;
        String str3;
        float left5;
        float left6;
        float left7;
        float left8;
        ImageView settingsButton;
        Clock clockView;
        ViewGroup nITZContainer;
        Clock clockView2;
        OplusQSDateView dateView;
        Clock clockView3;
        oplusSimpleQSFakeController.updateFakeElementLoc();
        oplusSimpleQSFakeController.updateClockPivot$1();
        StatusBarFakeFrame simpleFakeView = oplusSimpleQSFakeController.oplusFakeViewProvider.getSimpleFakeView(FakeType.FAKE_CLOCK);
        StatusBarFakeFrame simpleFakeView2 = oplusSimpleQSFakeController.oplusFakeViewProvider.getSimpleFakeView(FakeType.FAKE_SYSTEM_ICON);
        StatusBarFakeFrame simpleFakeView3 = oplusSimpleQSFakeController.oplusFakeViewProvider.getSimpleFakeView(FakeType.FAKE_NOTIFICATION);
        float fFloatValue = DeviceUtil.isRtl() ? (((Number) simpleFakeView.getRealStatusElementParams().getFirst()).floatValue() + simpleFakeView.getRealStatusElementLocX()) - oplusSimpleQSFakeController.statusbarClockPadding : oplusSimpleQSFakeController.statusbarClockPadding + simpleFakeView.getRealStatusElementLocX();
        ViewGroup viewGroup = oplusSimpleQSFakeController.oplusButtonContainer;
        float left9 = viewGroup != null ? viewGroup.getLeft() : 0.0f;
        ViewGroup viewGroup2 = oplusSimpleQSFakeController.oplusButtonContainerParent;
        float left10 = viewGroup2 != null ? viewGroup2.getLeft() : 0.0f;
        ViewGroup viewGroup3 = oplusSimpleQSFakeController.oplusSimpleQsContainer;
        float left11 = viewGroup3 != null ? viewGroup3.getLeft() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        float left12 = oplusQSSimpleHeader != null ? oplusQSSimpleHeader.getLeft() : 0.0f;
        View view = oplusSimpleQSFakeController.oplusQSFrame;
        float left13 = view != null ? view.getLeft() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader2 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader2 == null || (clockView3 = oplusQSSimpleHeader2.getClockView()) == null) {
            left = 0.0f;
        } else {
            left = DeviceUtil.isRtl() ? clockView3.getLeft() + left9 + left10 + left11 + left12 + left13 + clockView3.getWidth() : clockView3.getLeft() + left9 + left10 + left11 + left12 + left13;
        }
        float f = left12;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("statusBarClockXLocation :", fFloatValue, " ,statusbarClockPadding :", oplusSimpleQSFakeController.statusbarClockPadding, " ,qsClockXLocation :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, left, " ,settingButtonContainerLeft :", left9, " ,oplusButtonContainerParentLeft :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, left10, " ,qsContainerLeft :", left11, " ,qsSimpleHeaderLeft :");
            str = "SystemUi--Notification";
            Log.d(str, VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM, f, " ,qsFrameLeft :", left13, "OplusSimpleQSFakeController-->"));
        } else {
            str = "SystemUi--Notification";
        }
        oplusSimpleQSFakeController.fakeToClockTranslationX = fFloatValue - left;
        oplusSimpleQSFakeController.fakeToClockTranslationY = oplusSimpleQSFakeController.getClockTranslationY();
        float fFloatValue2 = DeviceUtil.isRtl() ? (((Number) simpleFakeView.getRealStatusElementParams().getFirst()).floatValue() + simpleFakeView.getRealStatusElementLocX()) - oplusSimpleQSFakeController.statusbarClockPadding : oplusSimpleQSFakeController.statusbarClockPadding + simpleFakeView.getRealStatusElementLocX();
        ViewGroup viewGroup4 = oplusSimpleQSFakeController.oplusButtonContainer;
        float left14 = viewGroup4 != null ? viewGroup4.getLeft() : 0.0f;
        ViewGroup viewGroup5 = oplusSimpleQSFakeController.oplusButtonContainerParent;
        float left15 = viewGroup5 != null ? viewGroup5.getLeft() : 0.0f;
        ViewGroup viewGroup6 = oplusSimpleQSFakeController.oplusSimpleQsContainer;
        float left16 = viewGroup6 != null ? viewGroup6.getLeft() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader3 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        float left17 = oplusQSSimpleHeader3 != null ? oplusQSSimpleHeader3.getLeft() : 0.0f;
        View view2 = oplusSimpleQSFakeController.oplusQSFrame;
        float left18 = view2 != null ? view2.getLeft() : 0.0f;
        String str4 = str;
        OplusQSSimpleHeader oplusQSSimpleHeader4 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader4 == null || (dateView = oplusQSSimpleHeader4.getDateView()) == null) {
            left2 = 0.0f;
        } else {
            left2 = DeviceUtil.isRtl() ? dateView.getLeft() + left14 + left15 + left16 + left17 + left18 + dateView.getWidth() : dateView.getLeft() + left14 + left15 + left16 + left17 + left18;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM2 = MonoSpline$.ExternalSyntheticOutline0.m("statusBarClockXLocation :", fFloatValue2, " ,statusbarClockPadding :", oplusSimpleQSFakeController.statusbarClockPadding, " ,dateViewXLocation :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM2, left2, " ,settingButtonContainerLeft :", left14, " ,oplusButtonContainerParentLeft :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM2, left15, " ,qsContainerLeft :", left16, " ,qsSimpleHeaderLeft :");
            str2 = str4;
            Log.d(str2, VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM2, left17, " ,qsFrameLeft :", left18, "OplusSimpleQSFakeController-->"));
        } else {
            str2 = str4;
        }
        oplusSimpleQSFakeController.fakeToDateViewTranslationX = fFloatValue2 - left2;
        oplusSimpleQSFakeController.fakeToDateViewTranslationY = oplusSimpleQSFakeController.getDateViewTranslationY();
        float fFloatValue3 = DeviceUtil.isRtl() ? ((Number) simpleFakeView3.getRealStatusElementParams().getFirst()).floatValue() + simpleFakeView3.getRealStatusElementLocX() : simpleFakeView3.getRealStatusElementLocX();
        ViewGroup viewGroup7 = oplusSimpleQSFakeController.oplusButtonContainer;
        float left19 = viewGroup7 != null ? viewGroup7.getLeft() : 0.0f;
        ViewGroup viewGroup8 = oplusSimpleQSFakeController.oplusButtonContainerParent;
        float left20 = viewGroup8 != null ? viewGroup8.getLeft() : 0.0f;
        ViewGroup viewGroup9 = oplusSimpleQSFakeController.oplusSimpleQsContainer;
        float left21 = viewGroup9 != null ? viewGroup9.getLeft() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader5 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        float left22 = oplusQSSimpleHeader5 != null ? oplusQSSimpleHeader5.getLeft() : 0.0f;
        View view3 = oplusSimpleQSFakeController.oplusQSFrame;
        float left23 = view3 != null ? view3.getLeft() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader6 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader6 == null || (clockView2 = oplusQSSimpleHeader6.getClockView()) == null) {
            left3 = 0.0f;
        } else {
            left3 = (DeviceUtil.isRtl() ? clockView2.getLeft() : clockView2.getWidth() + clockView2.getLeft()) + left19 + left20 + left21 + left22 + left23;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM3 = MonoSpline$.ExternalSyntheticOutline0.m("startStatusBarXLocation ", fFloatValue3, ",clockViewXLocation :", left3, ",settingButtonContainerLeft:");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM3, left19, ",oplusButtonContainerParentLeft:", left20, ",qsContainerLeft:");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM3, left21, ",qsSimpleHeaderLeft:", left22, ",qsFrameLeft:");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM3, left23, "OplusSimpleQSFakeController-->", str2);
        }
        oplusSimpleQSFakeController.fakeToNotificationTranslationX = fFloatValue3 - left3;
        float realStatusElementLocX = com.oplus.systemui.qs.base.util.DeviceUtil.isRtl() ? simpleFakeView2.getRealStatusElementLocX() : simpleFakeView2.getRealStatusElementLocX() + ((Number) simpleFakeView2.getRealStatusElementParams().getFirst()).floatValue();
        ViewGroup viewGroup10 = oplusSimpleQSFakeController.oplusSubHeader;
        float left24 = viewGroup10 != null ? viewGroup10.getLeft() : 0.0f;
        ViewGroup viewGroup11 = oplusSimpleQSFakeController.oplusHeaderContainer;
        float left25 = viewGroup11 != null ? viewGroup11.getLeft() : 0.0f;
        ViewGroup viewGroup12 = oplusSimpleQSFakeController.oplusSimpleQsContainer;
        float left26 = viewGroup12 != null ? viewGroup12.getLeft() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader7 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        float left27 = oplusQSSimpleHeader7 != null ? oplusQSSimpleHeader7.getLeft() : 0.0f;
        View view4 = oplusSimpleQSFakeController.oplusQSFrame;
        float left28 = view4 != null ? view4.getLeft() : 0.0f;
        ViewGroup viewGroup13 = oplusSimpleQSFakeController.oplusStatusBarIconContainer;
        if (viewGroup13 != null) {
            left4 = com.oplus.systemui.qs.base.util.DeviceUtil.isRtl() ? viewGroup13.getLeft() + left24 + left25 + left26 + left27 + left28 : viewGroup13.getWidth() + viewGroup13.getLeft() + left24 + left25 + left26 + left27 + left28;
        } else {
            left4 = 0.0f;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String str5 = str2;
            StringBuilder sbM4 = MonoSpline$.ExternalSyntheticOutline0.m("endStatusBarIconsXLocation :", realStatusElementLocX, ",qsEndStatusbarIconsXLocation :", left4, "subHeaderLeft :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM4, left24, "headerContainerLeft :", left25, "qsContainerLeft :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM4, left26, "qsSimpleHeaderLeft :", left27, "qsFrameLeft :");
            str3 = str5;
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM4, left28, "OplusSimpleQSFakeController-->", str3);
        } else {
            str3 = str2;
        }
        oplusSimpleQSFakeController.fakeToEndStatusBarTranslationX = realStatusElementLocX - left4;
        oplusSimpleQSFakeController.fakeToEndStatusBarTranslationY = oplusSimpleQSFakeController.getEndStatusBarTranslationY();
        float fFloatValue4 = DeviceUtil.isRtl() ? (((Number) simpleFakeView.getRealStatusElementParams().getFirst()).floatValue() + simpleFakeView.getRealStatusElementLocX()) - oplusSimpleQSFakeController.statusbarClockPadding : oplusSimpleQSFakeController.statusbarClockPadding + simpleFakeView.getRealStatusElementLocX();
        ViewGroup viewGroup14 = oplusSimpleQSFakeController.oplusButtonContainer;
        float left29 = viewGroup14 != null ? viewGroup14.getLeft() : 0.0f;
        ViewGroup viewGroup15 = oplusSimpleQSFakeController.oplusButtonContainerParent;
        float left30 = viewGroup15 != null ? viewGroup15.getLeft() : 0.0f;
        ViewGroup viewGroup16 = oplusSimpleQSFakeController.oplusSimpleQsContainer;
        float left31 = viewGroup16 != null ? viewGroup16.getLeft() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader8 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        float left32 = oplusQSSimpleHeader8 != null ? oplusQSSimpleHeader8.getLeft() : 0.0f;
        View view5 = oplusSimpleQSFakeController.oplusQSFrame;
        float left33 = view5 != null ? view5.getLeft() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader9 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader9 == null || (nITZContainer = oplusQSSimpleHeader9.getNITZContainer()) == null) {
            left5 = 0.0f;
        } else {
            left5 = DeviceUtil.isRtl() ? nITZContainer.getLeft() + left29 + left30 + left31 + left32 + left33 + nITZContainer.getWidth() : nITZContainer.getLeft() + left29 + left30 + left31 + left32 + left33;
        }
        OplusQSSimpleHeader oplusQSSimpleHeader10 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader10 == null || (clockView = oplusQSSimpleHeader10.getClockView()) == null) {
            left6 = 0.0f;
        } else {
            left6 = DeviceUtil.isRtl() ? clockView.getLeft() + left29 + left30 + left31 + left32 + left33 + clockView.getWidth() : clockView.getLeft() + left29 + left30 + left31 + left32 + left33;
        }
        float f2 = left6 - left5;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM5 = MonoSpline$.ExternalSyntheticOutline0.m("statusBarClockXLocation :", fFloatValue4, " ,statusbarClockPadding :", oplusSimpleQSFakeController.statusbarClockPadding, " ,qsNITZXLocation :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM5, left5, " ,clockXLocation :", left6, " ,settingButtonContainerLeft :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM5, left29, " ,oplusButtonContainerParentLeft :", left30, " ,qsContainerLeft :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM5, left31, " ,qsSimpleHeaderLeft :", left32, " ,qsFrameLeft :");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM5, left33, "OplusSimpleQSFakeController-->", str3);
        }
        oplusSimpleQSFakeController.nitzTranslationX = (fFloatValue4 - left5) - f2;
        float realStatusElementLocX2 = com.oplus.systemui.qs.base.util.DeviceUtil.isRtl() ? simpleFakeView2.getRealStatusElementLocX() : simpleFakeView2.getRealStatusElementLocX() + ((Number) simpleFakeView2.getRealStatusElementParams().getFirst()).floatValue();
        ViewGroup viewGroup17 = oplusSimpleQSFakeController.oplusSubHeader;
        float left34 = viewGroup17 != null ? viewGroup17.getLeft() : 0.0f;
        ViewGroup viewGroup18 = oplusSimpleQSFakeController.oplusHeaderContainer;
        float left35 = viewGroup18 != null ? viewGroup18.getLeft() : 0.0f;
        ViewGroup viewGroup19 = oplusSimpleQSFakeController.oplusSimpleQsContainer;
        float left36 = viewGroup19 != null ? viewGroup19.getLeft() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader11 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        float left37 = oplusQSSimpleHeader11 != null ? oplusQSSimpleHeader11.getLeft() : 0.0f;
        View view6 = oplusSimpleQSFakeController.oplusQSFrame;
        float left38 = view6 != null ? view6.getLeft() : 0.0f;
        ViewGroup viewGroup20 = oplusSimpleQSFakeController.oplusStatusBarIconContainer;
        if (viewGroup20 != null) {
            left7 = com.oplus.systemui.qs.base.util.DeviceUtil.isRtl() ? viewGroup20.getLeft() + left34 + left35 + left36 + left37 + left38 : viewGroup20.getWidth() + viewGroup20.getLeft() + left34 + left35 + left36 + left37 + left38;
        } else {
            left7 = 0.0f;
        }
        OplusQSSimpleHeader oplusQSSimpleHeader12 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader12 == null || (settingsButton = oplusQSSimpleHeader12.getSettingsButton()) == null) {
            left8 = 0.0f;
        } else {
            left8 = com.oplus.systemui.qs.base.util.DeviceUtil.isRtl() ? settingsButton.getLeft() + left34 + left35 + left36 + left37 + left38 : settingsButton.getLeft() + left34 + left35 + left36 + left37 + left38 + settingsButton.getWidth();
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM6 = MonoSpline$.ExternalSyntheticOutline0.m("endStatusBarIconsXLocation :", realStatusElementLocX2, ",qsEndStatusbarIconsXLocation :", left7, "subHeaderLeft :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM6, left34, "headerContainerLeft :", left35, "qsContainerLeft :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM6, left36, "qsSimpleHeaderLeft :", left37, "qsFrameLeft :");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM6, left38, "OplusSimpleQSFakeController-->", str3);
        }
        oplusSimpleQSFakeController.qsSettingsButtonViewTranslationX = realStatusElementLocX2 - left8;
        oplusSimpleQSFakeController.qsSettingsButtonViewTranslationY = oplusSimpleQSFakeController.getSettingsButtonViewTranslationY();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            float f3 = oplusSimpleQSFakeController.fakeToClockTranslationX;
            float f4 = oplusSimpleQSFakeController.fakeToClockTranslationY;
            float f5 = oplusSimpleQSFakeController.fakeToDateViewTranslationX;
            float f6 = oplusSimpleQSFakeController.fakeToDateViewTranslationY;
            float f7 = oplusSimpleQSFakeController.fakeToNotificationTranslationX;
            float f8 = oplusSimpleQSFakeController.fakeToEndStatusBarTranslationX;
            float f9 = oplusSimpleQSFakeController.fakeToEndStatusBarTranslationY;
            float f10 = oplusSimpleQSFakeController.qsSettingsButtonViewTranslationX;
            float f11 = oplusSimpleQSFakeController.qsSettingsButtonViewTranslationY;
            StringBuilder sbM7 = MonoSpline$.ExternalSyntheticOutline0.m("updateFakeToStatusBarTranslation fakeToClockTranslationX: ", f3, ", fakeToClockTranslationY: ", f4, ", fakeToDateViewTranslationX: ");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM7, f5, ", fakeToDateViewTranslationY: ", f6, ", fakeToNotificationTranslationX: ");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM7, f7, ", fakeToEndStatusBarTranslationX:", f8, ", fakeToEndStatusBarTranslationY:");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM7, f9, ", qsSettingsButtonViewTranslationX:", f10, ", qsSettingsButtonViewTranslationY:");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM7, f11, "OplusSimpleQSFakeController-->", str3);
        }
    }

    public static final void access$updateSimpleQSTranslation(OplusSimpleQSFakeController oplusSimpleQSFakeController, float f) {
        ImageView settingsButton;
        ImageView settingsButton2;
        ViewGroup nITZContainer;
        ViewGroup nITZContainer2;
        OplusQSDateView dateView;
        OplusQSDateView dateView2;
        Clock clockView;
        Clock clockView2;
        OplusQSSimpleHeader oplusQSSimpleHeader = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader != null && (clockView2 = oplusQSSimpleHeader.getClockView()) != null) {
            clockView2.setTranslationX((1.0f - f) * oplusSimpleQSFakeController.fakeToClockTranslationX);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader2 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader2 != null && (clockView = oplusQSSimpleHeader2.getClockView()) != null) {
            clockView.setTranslationY((1.0f - f) * (-oplusSimpleQSFakeController.fakeToClockTranslationY));
        }
        OplusQSSimpleHeader oplusQSSimpleHeader3 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader3 != null && (dateView2 = oplusQSSimpleHeader3.getDateView()) != null) {
            dateView2.setTranslationX((1.0f - f) * oplusSimpleQSFakeController.fakeToDateViewTranslationX);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader4 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader4 != null && (dateView = oplusQSSimpleHeader4.getDateView()) != null) {
            dateView.setTranslationY((1.0f - f) * (-oplusSimpleQSFakeController.fakeToDateViewTranslationY));
        }
        ViewGroup viewGroup = oplusSimpleQSFakeController.oplusStatusBarIconContainer;
        if (viewGroup != null) {
            viewGroup.setTranslationX((1.0f - f) * oplusSimpleQSFakeController.fakeToEndStatusBarTranslationX);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader5 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader5 != null && (nITZContainer2 = oplusQSSimpleHeader5.getNITZContainer()) != null) {
            nITZContainer2.setTranslationY((1.0f - f) * (-60.0f));
        }
        OplusQSSimpleHeader oplusQSSimpleHeader6 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader6 != null && (nITZContainer = oplusQSSimpleHeader6.getNITZContainer()) != null) {
            nITZContainer.setTranslationX((1.0f - f) * oplusSimpleQSFakeController.nitzTranslationX);
        }
        ViewGroup viewGroup2 = oplusSimpleQSFakeController.oplusStatusBarIconContainer;
        if (viewGroup2 != null) {
            viewGroup2.setTranslationY((1.0f - f) * (-oplusSimpleQSFakeController.fakeToEndStatusBarTranslationY));
        }
        OplusQSSimpleHeader oplusQSSimpleHeader7 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader7 != null && (settingsButton2 = oplusQSSimpleHeader7.getSettingsButton()) != null) {
            settingsButton2.setTranslationX((1.0f - f) * oplusSimpleQSFakeController.qsSettingsButtonViewTranslationX);
        }
        OplusQSSimpleHeader oplusQSSimpleHeader8 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader8 == null || (settingsButton = oplusQSSimpleHeader8.getSettingsButton()) == null) {
            return;
        }
        settingsButton.setTranslationY((1.0f - f) * 2.0f * (-oplusSimpleQSFakeController.qsSettingsButtonViewTranslationY));
    }

    private final float getClockTranslationY() {
        Clock clockView;
        Clock clockView2;
        ViewGroup viewGroup = this.oplusSimpleQsContainer;
        float top = viewGroup != null ? viewGroup.getTop() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        float top2 = oplusQSSimpleHeader != null ? oplusQSSimpleHeader.getTop() : 0.0f;
        View view = this.oplusQSFrame;
        float top3 = view != null ? view.getTop() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader2 = this.oplusQSSimpleHeader;
        float top4 = (oplusQSSimpleHeader2 == null || (clockView2 = oplusQSSimpleHeader2.getClockView()) == null) ? 0.0f : clockView2.getTop();
        ViewGroup viewGroup2 = this.oplusButtonContainer;
        float top5 = viewGroup2 != null ? viewGroup2.getTop() : 0.0f;
        ViewGroup viewGroup3 = this.oplusButtonContainerParent;
        float top6 = viewGroup3 != null ? viewGroup3.getTop() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader3 = this.oplusQSSimpleHeader;
        int height = (oplusQSSimpleHeader3 == null || (clockView = oplusQSSimpleHeader3.getClockView()) == null) ? 0 : clockView.getHeight() / 2;
        float f = height + top4 + top5 + top6 + top + top2 + top3;
        float statusBarMidToQsPanelTopHeight = getStatusBarMidToQsPanelTopHeight();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("qsClockMidToTop ", f, ",qsStatusBarMidToTop :", statusBarMidToQsPanelTopHeight, "qsClockMidHeight :");
            IconProvider$.ExternalSyntheticOutline0.m(top4, height, "qsClockTop :", "settingButtonContainerTop :", sbM);
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, top5, "oplusButtonContainerParentTop :", top6, "qsContainerTop :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, top, "qsSimpleHeaderTop :", top2, "qsFrameTop :");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM, top3, "OplusSimpleQSFakeController-->", "SystemUi--Notification");
        }
        return f - statusBarMidToQsPanelTopHeight;
    }

    private final float getDateViewTranslationY() {
        OplusQSDateView dateView;
        OplusQSDateView dateView2;
        ViewGroup viewGroup = this.oplusSimpleQsContainer;
        float top = viewGroup != null ? viewGroup.getTop() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        float top2 = oplusQSSimpleHeader != null ? oplusQSSimpleHeader.getTop() : 0.0f;
        View view = this.oplusQSFrame;
        float top3 = view != null ? view.getTop() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader2 = this.oplusQSSimpleHeader;
        float top4 = (oplusQSSimpleHeader2 == null || (dateView2 = oplusQSSimpleHeader2.getDateView()) == null) ? 0.0f : dateView2.getTop();
        OplusQSSimpleHeader oplusQSSimpleHeader3 = this.oplusQSSimpleHeader;
        int height = (oplusQSSimpleHeader3 == null || (dateView = oplusQSSimpleHeader3.getDateView()) == null) ? 0 : dateView.getHeight() / 2;
        ViewGroup viewGroup2 = this.oplusButtonContainer;
        float top5 = viewGroup2 != null ? viewGroup2.getTop() : 0.0f;
        ViewGroup viewGroup3 = this.oplusButtonContainerParent;
        float top6 = viewGroup3 != null ? viewGroup3.getTop() : 0.0f;
        ViewGroup viewGroup4 = this.oplusStatusBarIconContainer;
        int height2 = viewGroup4 != null ? viewGroup4.getHeight() : 0;
        float f = height + top4 + top5 + top6 + top + top2 + top3;
        float statusBarMidToQsPanelTopHeight = getStatusBarMidToQsPanelTopHeight();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("qsClockMidToTop ", f, ",qsStatusBarMidToTop :", statusBarMidToQsPanelTopHeight, "qsDateViewMidHeight :");
            IconProvider$.ExternalSyntheticOutline0.m(top4, height, "qsDateViewTop :", "settingButtonContainerTop :", sbM);
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, top5, "oplusButtonContainerParentTop :", top6, "qsContainerTop :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, top, "qsSimpleHeaderTop :", top2, "qsFrameTop :");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM, top3, "OplusSimpleQSFakeController-->", "SystemUi--Notification");
        }
        return (f - statusBarMidToQsPanelTopHeight) + height2;
    }

    private final float getEndStatusBarTranslationY() {
        ViewGroup viewGroup = this.oplusButtonContainerParent;
        float f = 0.0f;
        float height = viewGroup != null ? viewGroup.getHeight() : 0.0f;
        ViewGroup viewGroup2 = this.oplusButtonContainerParent;
        float top = viewGroup2 != null ? viewGroup2.getTop() : 0.0f;
        ViewGroup viewGroup3 = this.oplusButtonContainerParent;
        if (viewGroup3 != null) {
            ViewGroup.LayoutParams layoutParams = viewGroup3.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
            f = marginLayoutParams != null ? marginLayoutParams.bottomMargin : 0;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("buttonContainerParentHeight: ", height, "OplusSimpleQSFakeController-->", "SystemUi--Notification");
        }
        return height + top + f;
    }

    private final float getSettingsButtonViewTranslationY() {
        ImageView settingsButton;
        ImageView settingsButton2;
        ViewGroup viewGroup = this.oplusSimpleQsContainer;
        float top = viewGroup != null ? viewGroup.getTop() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        float top2 = oplusQSSimpleHeader != null ? oplusQSSimpleHeader.getTop() : 0.0f;
        View view = this.oplusQSFrame;
        float top3 = view != null ? view.getTop() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader2 = this.oplusQSSimpleHeader;
        float top4 = (oplusQSSimpleHeader2 == null || (settingsButton2 = oplusQSSimpleHeader2.getSettingsButton()) == null) ? 0.0f : settingsButton2.getTop();
        OplusQSSimpleHeader oplusQSSimpleHeader3 = this.oplusQSSimpleHeader;
        int height = (oplusQSSimpleHeader3 == null || (settingsButton = oplusQSSimpleHeader3.getSettingsButton()) == null) ? 0 : settingsButton.getHeight() / 2;
        ViewGroup viewGroup2 = this.oplusButtonContainer;
        float top5 = viewGroup2 != null ? viewGroup2.getTop() : 0.0f;
        ViewGroup viewGroup3 = this.oplusButtonContainerParent;
        float top6 = viewGroup3 != null ? viewGroup3.getTop() : 0.0f;
        ViewGroup viewGroup4 = this.oplusStatusBarIconContainer;
        int height2 = viewGroup4 != null ? viewGroup4.getHeight() : 0;
        float f = height + top4 + top5 + top6 + top + top2 + top3;
        float statusBarMidToQsPanelTopHeight = getStatusBarMidToQsPanelTopHeight();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("qsClockMidToTop ", f, ",qsStatusBarMidToTop :", statusBarMidToQsPanelTopHeight, "qsSettingsButtonViewMidHeight :");
            IconProvider$.ExternalSyntheticOutline0.m(top4, height, "qsSettingsButtonViewTop :", "settingButtonContainerTop :", sbM);
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, top5, "oplusButtonContainerParentTop :", top6, "qsContainerTop :");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, top, "qsSimpleHeaderTop :", top2, "qsFrameTop :");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM, top3, "OplusSimpleQSFakeController-->", "SystemUi--Notification");
        }
        return (f - statusBarMidToQsPanelTopHeight) + height2;
    }

    private final float getStatusBarMidToQsPanelTopHeight() {
        ViewGroup viewGroup = this.oplusSimpleQsContainer;
        float top = viewGroup != null ? viewGroup.getTop() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        float top2 = oplusQSSimpleHeader != null ? oplusQSSimpleHeader.getTop() : 0.0f;
        View view = this.oplusQSFrame;
        float top3 = view != null ? view.getTop() : 0.0f;
        ViewGroup viewGroup2 = this.oplusStatusBarIconContainer;
        int height = viewGroup2 != null ? viewGroup2.getHeight() / 2 : 0;
        ViewGroup viewGroup3 = this.oplusStatusBarIconContainer;
        float top4 = viewGroup3 != null ? viewGroup3.getTop() : 0.0f;
        ViewGroup viewGroup4 = this.oplusSubHeader;
        return height + top4 + (viewGroup4 != null ? viewGroup4.getTop() : 0.0f) + top + top2 + top3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFakeViewLocationUpdateFlag(boolean z) {
        this.isUpdateFakeViewLocation = z;
    }

    public static void updateContainerMarginAndHeight(View view, int i, int i2, String str) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 == null) {
            return;
        }
        int i3 = layoutParams2.topMargin;
        int i4 = layoutParams2.height;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, Fragment$5$.ExternalSyntheticOutline0.m(ConstraintSet$.ExternalSyntheticOutline0.m(i, "fake updateMarginTop, reason: ", str, ", newMarginTop: ", " currentMarginTop: "), i3, i2, ", headerHeight: "), null, 8);
        if (i3 == i && i4 == i2) {
            return;
        }
        layoutParams2.topMargin = i;
        layoutParams2.height = i2;
        view.setLayoutParams(layoutParams2);
    }

    public final LogBuffer getLogBuffer() {
        return this.logBuffer;
    }

    public final ViewGroup getQSFakeContainer() {
        return this.oplusQSFakeContainer;
    }

    public final View getStatusIcons() {
        return this.oplusStatusBarIconContainer;
    }

    public final void hideQsFakeContainer(String str, boolean z) {
        ViewGroup viewGroup;
        if (z && (viewGroup = this.oplusQSFakeContainer) != null) {
            viewGroup.setVisibility(4);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusSimpleQSFakeController-->".concat("updateQsFakeContainerVisible -- hide:" + z + " from " + str));
        }
    }

    public final void initFakeContainLocation() {
        boolean z;
        float f = -this.panelAnimationEx.getThresholdForTranslationY();
        this.panelThreshold = f;
        ViewGroup viewGroup = this.oplusQSFakeFixedContainer;
        if (viewGroup != null) {
            viewGroup.setTranslationY(f);
        }
        ViewGroup viewGroup2 = this.oplusQSFakeContainer;
        if (viewGroup2 != null) {
            viewGroup2.setTranslationY(this.panelThreshold);
        }
        StatusBarFakeFrame simpleFakeView = this.oplusFakeViewProvider.getSimpleFakeView(FakeType.FAKE_CLOCK);
        StatusBarFakeFrame simpleFakeView2 = this.oplusFakeViewProvider.getSimpleFakeView(FakeType.FAKE_NOTIFICATION);
        float realStatusElementLocX = simpleFakeView.getRealStatusElementLocX();
        float realStatusElementLocX2 = simpleFakeView2.getRealStatusElementLocX();
        if (!com.oplus.systemui.qs.base.util.DeviceUtil.isRtl()) {
            z = simpleFakeView2.getRealStatusElementLocX() == simpleFakeView.getRealStatusElementLocX();
            this.isFakeClockGone = z;
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("initFakeContainLocation, isFakeViewGone: ", z, " fakeClockLocX: ", realStatusElementLocX, ", fakeNotificationLocX: ");
            sbM.append(realStatusElementLocX2);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
            return;
        }
        z = ((Number) simpleFakeView2.getRealStatusElementParams().getFirst()).floatValue() + realStatusElementLocX2 == ((Number) simpleFakeView.getRealStatusElementParams().getFirst()).floatValue() + realStatusElementLocX;
        this.isFakeClockGone = z;
        Object first = simpleFakeView.getRealStatusElementParams().getFirst();
        Object first2 = simpleFakeView2.getRealStatusElementParams().getFirst();
        StringBuilder sbM2 = NotificationPanelViewController$.ExternalSyntheticOutline0.m("initFakeContainLocation, is rtl, isFakeViewGone: ", z, " fakeClockLocX: ", realStatusElementLocX, ", fakeClockWidth: ");
        sbM2.append(first);
        sbM2.append(", fakeNotificationLocX: ");
        sbM2.append(realStatusElementLocX2);
        sbM2.append(",fakeNotificationWidth: ");
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, PersistentOrderedMapBuilderLinksIterator$.ExternalSyntheticOutline0.m(sbM2, first2, " "), null, 8);
    }

    public final void initView(View view) {
        OplusNotificationTiltShiftBlurController oplusNotificationTiltShiftBlurController;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusSimpleQSFakeController-->qsFake initView");
        }
        this.systemFontScale = Resources.getSystem().getConfiguration().fontScale;
        this.clockScaleFactor = this.context.getResources().getFloat(2131171153);
        refreshLargeDisplayState();
        this.statusbarClockPadding = this.context.getResources().getDimension(2131173051);
        this.oplusQSFrame = ((NotificationShadeWindowViewController) this.notificationShadeWindowViewController.get()).getView().findViewById(2131364768);
        this.oplusQSSimpleHeader = (OplusQSSimpleHeader) view.requireViewById(2131365229);
        this.oplusSimpleQsContainer = (ViewGroup) view.requireViewById(2131365224);
        this.oplusHeaderContainer = (ViewGroup) view.requireViewById(2131363336);
        this.oplusSubHeader = (ViewGroup) view.requireViewById(2131363334);
        this.oplusStatusBarIconContainer = (ViewGroup) view.requireViewById(2131364818);
        this.oplusButtonContainer = (ViewGroup) view.requireViewById(2131365157);
        this.oplusButtonContainerParent = (ViewGroup) view.requireViewById(2131362434);
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        this.oplusQSFakeContainer = oplusQSSimpleHeader != null ? (ViewGroup) oplusQSSimpleHeader.requireViewById(2131365226) : null;
        OplusQSSimpleHeader oplusQSSimpleHeader2 = this.oplusQSSimpleHeader;
        this.oplusQSFakeFixedContainer = oplusQSSimpleHeader2 != null ? (ViewGroup) oplusQSSimpleHeader2.requireViewById(2131365228) : null;
        ViewGroup viewGroup = this.oplusQSFakeContainer;
        this.fakeStatusBarIconsContainer = viewGroup != null ? (FrameLayout) viewGroup.requireViewById(2131365227) : null;
        ViewGroup viewGroup2 = this.oplusQSFakeContainer;
        this.fakeClockViewContainer = viewGroup2 != null ? (FrameLayout) viewGroup2.requireViewById(2131364317) : null;
        ViewGroup viewGroup3 = this.oplusQSFakeContainer;
        this.fakeNotificationContainer = viewGroup3 != null ? (FrameLayout) viewGroup3.requireViewById(2131364318) : null;
        ViewGroup viewGroup4 = this.oplusQSFakeContainer;
        this.fakeDynamicIconsContainer = viewGroup4 != null ? (FrameLayout) viewGroup4.requireViewById(2131365225) : null;
        ViewGroup viewGroup5 = this.oplusQSFakeFixedContainer;
        this.fakeCarrierContainer = viewGroup5 != null ? (FrameLayout) viewGroup5.requireViewById(2131364316) : null;
        OplusFakeViewProvider oplusFakeViewProvider = this.oplusFakeViewProvider;
        FakeType fakeType = FakeType.FAKE_SYSTEM_ICON;
        ViewParent parent = oplusFakeViewProvider.getSimpleFakeView(fakeType).getParent();
        ViewGroup viewGroup6 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup6 != null) {
            viewGroup6.removeView(this.oplusFakeViewProvider.getSimpleFakeView(fakeType));
        }
        OplusFakeViewProvider oplusFakeViewProvider2 = this.oplusFakeViewProvider;
        FakeType fakeType2 = FakeType.FAKE_PRIVACY;
        ViewParent parent2 = oplusFakeViewProvider2.getSimpleFakeView(fakeType2).getParent();
        ViewGroup viewGroup7 = parent2 instanceof ViewGroup ? (ViewGroup) parent2 : null;
        if (viewGroup7 != null) {
            viewGroup7.removeView(this.oplusFakeViewProvider.getSimpleFakeView(fakeType2));
        }
        OplusFakeViewProvider oplusFakeViewProvider3 = this.oplusFakeViewProvider;
        FakeType fakeType3 = FakeType.FAKE_CLOCK;
        ViewParent parent3 = oplusFakeViewProvider3.getSimpleFakeView(fakeType3).getParent();
        ViewGroup viewGroup8 = parent3 instanceof ViewGroup ? (ViewGroup) parent3 : null;
        if (viewGroup8 != null) {
            viewGroup8.removeView(this.oplusFakeViewProvider.getSimpleFakeView(fakeType3));
        }
        OplusFakeViewProvider oplusFakeViewProvider4 = this.oplusFakeViewProvider;
        FakeType fakeType4 = FakeType.FAKE_NOTIFICATION;
        ViewParent parent4 = oplusFakeViewProvider4.getSimpleFakeView(fakeType4).getParent();
        ViewGroup viewGroup9 = parent4 instanceof ViewGroup ? (ViewGroup) parent4 : null;
        if (viewGroup9 != null) {
            viewGroup9.removeView(this.oplusFakeViewProvider.getSimpleFakeView(fakeType4));
        }
        OplusFakeViewProvider oplusFakeViewProvider5 = this.oplusFakeViewProvider;
        FakeType fakeType5 = FakeType.FAKE_CARRIER;
        ViewParent parent5 = oplusFakeViewProvider5.getSimpleFakeView(fakeType5).getParent();
        ViewGroup viewGroup10 = parent5 instanceof ViewGroup ? (ViewGroup) parent5 : null;
        if (viewGroup10 != null) {
            viewGroup10.removeView(this.oplusFakeViewProvider.getSimpleFakeView(fakeType5));
        }
        FrameLayout frameLayout = this.fakeCarrierContainer;
        if (frameLayout != null) {
            frameLayout.addView(this.oplusFakeViewProvider.getSimpleFakeView(fakeType5));
        }
        FrameLayout frameLayout2 = this.fakeDynamicIconsContainer;
        if (frameLayout2 != null) {
            frameLayout2.addView(this.oplusFakeViewProvider.getSimpleFakeView(fakeType2));
        }
        FrameLayout frameLayout3 = this.fakeNotificationContainer;
        if (frameLayout3 != null) {
            frameLayout3.addView(this.oplusFakeViewProvider.getSimpleFakeView(fakeType4));
        }
        FrameLayout frameLayout4 = this.fakeStatusBarIconsContainer;
        if (frameLayout4 != null) {
            frameLayout4.addView(this.oplusFakeViewProvider.getSimpleFakeView(fakeType));
        }
        FrameLayout frameLayout5 = this.fakeClockViewContainer;
        if (frameLayout5 != null) {
            frameLayout5.addView(this.oplusFakeViewProvider.getSimpleFakeView(fakeType3));
        }
        setFakeViewLocationUpdateFlag(true);
        OplusNotificationTiltShiftBlurContainer oplusNotificationTiltShiftBlurContainer = (OplusNotificationTiltShiftBlurContainer) ((NotificationShadeWindowViewController) this.notificationShadeWindowViewController.get()).getView().findViewById(2131365668);
        OplusLockIconView oplusLockIconViewFindViewById = ((NotificationShadeWindowViewController) this.notificationShadeWindowViewController.get()).getView().findViewById(2131363783);
        if (oplusNotificationTiltShiftBlurContainer == null || oplusLockIconViewFindViewById == null || (oplusNotificationTiltShiftBlurController = (OplusNotificationTiltShiftBlurController) this.tiltShiftBlurController.get()) == null) {
            return;
        }
        OplusSimpleQSFakeController$$ExternalSyntheticLambda0 oplusSimpleQSFakeController$$ExternalSyntheticLambda0 = new OplusSimpleQSFakeController$$ExternalSyntheticLambda0();
        oplusSimpleQSFakeController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        oplusNotificationTiltShiftBlurController.bind(oplusNotificationTiltShiftBlurContainer, oplusLockIconViewFindViewById, oplusSimpleQSFakeController$$ExternalSyntheticLambda0);
    }

    public final boolean isNotSupportSeamlessTransfer() {
        if (this.context.getResources().getConfiguration().orientation != 2) {
            return false;
        }
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isTablet();
    }

    public void onConfigChanged(Configuration configuration) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusSimpleQSFakeController-->onConfigChanged isUpdateFakeViewLocation:true");
        }
        refreshLargeDisplayState();
        setFakeViewLocationUpdateFlag(true);
    }

    public final void onDestroy() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusSimpleQSFakeController-->onDestroy");
        }
        if (this.panelAnimationEx.getUseLayeredAnimation()) {
            this.panelAnimationEx.getTranslationYValue().removeCallback(this.panelTranslationYChangeListener);
            this.panelAnimationEx.getPanelExpandFraction().removeCallback(this.fractionChangeListener);
            this.panelAnimationEx.getElementShowState().removeCallback(this.elementShowStateChangeListener);
        }
        View statusIcons = getStatusIcons();
        if (statusIcons != null) {
            statusIcons.removeOnLayoutChangeListener(this.statusIconsLayoutListener);
        }
        HomeStatusBarComponent homeStatusBarComponent = (HomeStatusBarComponent) ((Map) this.homeStatusBarComponentsRepository.getComponentsByDisplayId().getValue()).get(0);
        PhoneStatusBarView phoneStatusBarView = homeStatusBarComponent != null ? homeStatusBarComponent.getPhoneStatusBarView() : null;
        View viewFindViewById = phoneStatusBarView != null ? phoneStatusBarView.findViewById(2131365367) : null;
        IScaledAncestorView iScaledAncestorView = viewFindViewById instanceof IScaledAncestorView ? (IScaledAncestorView) viewFindViewById : null;
        if (iScaledAncestorView != null) {
            iScaledAncestorView.removeScaleChangedListener(this.systemIconsScaleChangedListener);
        }
        ViewGroup simpleQsFrame = ((NotificationShadeWindowViewController) this.notificationShadeWindowViewController.get()).getSimpleQsFrame();
        if (simpleQsFrame != null) {
            simpleQsFrame.removeOnLayoutChangeListener(this.layoutChangedListener);
        }
        ((OplusNotificationTiltShiftBlurController) this.tiltShiftBlurController.get()).unbind();
        View view = this.oplusQSFrame;
        if (view != null) {
            view.setTranslationZ(0.0f);
        }
    }

    public final void onViewCreated(View view) {
        initView(view);
        if (this.panelAnimationEx.getUseLayeredAnimation()) {
            this.panelAnimationEx.getTranslationYValue().addCallback(this.panelTranslationYChangeListener);
            this.panelAnimationEx.getPanelExpandFraction().addCallback(this.fractionChangeListener);
            this.panelAnimationEx.getElementShowState().addCallback(this.elementShowStateChangeListener);
        }
        View statusIcons = getStatusIcons();
        if (statusIcons != null) {
            statusIcons.addOnLayoutChangeListener(this.statusIconsLayoutListener);
        }
        HomeStatusBarComponent homeStatusBarComponent = (HomeStatusBarComponent) ((Map) this.homeStatusBarComponentsRepository.getComponentsByDisplayId().getValue()).get(0);
        PhoneStatusBarView phoneStatusBarView = homeStatusBarComponent != null ? homeStatusBarComponent.getPhoneStatusBarView() : null;
        View viewFindViewById = phoneStatusBarView != null ? phoneStatusBarView.findViewById(2131365367) : null;
        IScaledAncestorView iScaledAncestorView = viewFindViewById instanceof IScaledAncestorView ? (IScaledAncestorView) viewFindViewById : null;
        if (iScaledAncestorView != null) {
            iScaledAncestorView.addScaleChangedListener(this.systemIconsScaleChangedListener);
        }
        ViewGroup simpleQsFrame = ((NotificationShadeWindowViewController) this.notificationShadeWindowViewController.get()).getSimpleQsFrame();
        if (simpleQsFrame != null) {
            simpleQsFrame.addOnLayoutChangeListener(this.layoutChangedListener);
        }
        hideQsFakeContainer("onViewCreated", true);
    }

    public final void refreshLargeDisplayState() {
        int i = Settings.System.getInt(this.context.getContentResolver(), SETTINGS_KEY, -1);
        KeyguardFeatureOption.INSTANCE.getClass();
        boolean z = false;
        if (!KeyguardFeatureOption.isScreenResolutionSwitchSupport() ? i >= 2 : i >= 4) {
            z = true;
        }
        this.isLargeDisplay = z;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", MobileInputLogger$.ExternalSyntheticOutline0.m("refreshLargeDisplayState index=", i, ", isLargeDisplay=", "OplusSimpleQSFakeController-->", this.isLargeDisplay));
        }
    }

    public final void resetAllViewsTransitionAlpha(float f) {
        OplusQSDateView dateView;
        ViewGroup nITZContainer;
        ViewGroup statusIconsContainer;
        ImageView settingsButton;
        OplusQSDateView dateView2;
        Clock clockView;
        OplusQSDateView dateView3;
        ViewGroup nITZContainer2;
        ViewGroup statusIconsContainer2;
        ImageView settingsButton2;
        OplusQSDateView dateView4;
        Clock clockView2;
        if (f == 1.0f) {
            OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader != null && (clockView2 = oplusQSSimpleHeader.getClockView()) != null) {
                clockView2.setTransitionAlpha(1.0f);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader2 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader2 != null && (dateView4 = oplusQSSimpleHeader2.getDateView()) != null) {
                dateView4.setTransitionAlpha(1.0f);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader3 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader3 != null && (settingsButton2 = oplusQSSimpleHeader3.getSettingsButton()) != null) {
                settingsButton2.setTransitionAlpha(1.0f);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader4 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader4 != null && (statusIconsContainer2 = oplusQSSimpleHeader4.getStatusIconsContainer()) != null) {
                statusIconsContainer2.setTransitionAlpha(1.0f);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader5 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader5 != null && (nITZContainer2 = oplusQSSimpleHeader5.getNITZContainer()) != null) {
                nITZContainer2.setTransitionAlpha(1.0f);
            }
            FrameLayout frameLayout = this.fakeStatusBarIconsContainer;
            if (frameLayout != null) {
                frameLayout.setTransitionAlpha(0.0f);
            }
            FrameLayout frameLayout2 = this.fakeClockViewContainer;
            if (frameLayout2 != null) {
                frameLayout2.setTransitionAlpha(0.0f);
            }
            FrameLayout frameLayout3 = this.fakeCarrierContainer;
            if (frameLayout3 != null) {
                frameLayout3.setTransitionAlpha(0.0f);
            }
            FrameLayout frameLayout4 = this.fakeNotificationContainer;
            if (frameLayout4 != null) {
                frameLayout4.setTransitionAlpha(0.0f);
            }
            FrameLayout frameLayout5 = this.fakeDynamicIconsContainer;
            if (frameLayout5 != null) {
                frameLayout5.setTransitionAlpha(0.0f);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader6 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader6 == null || (dateView3 = oplusQSSimpleHeader6.getDateView()) == null) {
                return;
            }
            dateView3.setSelected(true);
            return;
        }
        if (f == 0.0f) {
            OplusQSSimpleHeader oplusQSSimpleHeader7 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader7 != null && (clockView = oplusQSSimpleHeader7.getClockView()) != null) {
                clockView.setTransitionAlpha(0.0f);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader8 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader8 != null && (dateView2 = oplusQSSimpleHeader8.getDateView()) != null) {
                dateView2.setTransitionAlpha(0.0f);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader9 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader9 != null && (settingsButton = oplusQSSimpleHeader9.getSettingsButton()) != null) {
                settingsButton.setTransitionAlpha(0.0f);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader10 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader10 != null && (statusIconsContainer = oplusQSSimpleHeader10.getStatusIconsContainer()) != null) {
                statusIconsContainer.setTransitionAlpha(0.0f);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader11 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader11 != null && (nITZContainer = oplusQSSimpleHeader11.getNITZContainer()) != null) {
                nITZContainer.setTransitionAlpha(0.0f);
            }
            FrameLayout frameLayout6 = this.fakeStatusBarIconsContainer;
            if (frameLayout6 != null) {
                frameLayout6.setTransitionAlpha(1.0f);
            }
            FrameLayout frameLayout7 = this.fakeClockViewContainer;
            if (frameLayout7 != null) {
                frameLayout7.setTransitionAlpha(1.0f);
            }
            FrameLayout frameLayout8 = this.fakeCarrierContainer;
            if (frameLayout8 != null) {
                frameLayout8.setTransitionAlpha(1.0f);
            }
            FrameLayout frameLayout9 = this.fakeNotificationContainer;
            if (frameLayout9 != null) {
                frameLayout9.setTransitionAlpha(1.0f);
            }
            FrameLayout frameLayout10 = this.fakeDynamicIconsContainer;
            if (frameLayout10 != null) {
                frameLayout10.setTransitionAlpha(1.0f);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader12 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader12 == null || (dateView = oplusQSSimpleHeader12.getDateView()) == null) {
                return;
            }
            dateView.setSelected(false);
        }
    }

    public final void resetExtraTakeOverCondition() {
        this.updateExtraSimpleQSFootTYFraction = true;
        SeparatePanelAnimation.Companion.setExtraSimpleQSFootTranslationY(0.0f);
    }

    public final void updateClockPivot$1() {
        Clock clockView;
        Clock clockView2;
        Clock clockView3;
        Clock clockView4;
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        float baseline = (oplusQSSimpleHeader == null || (clockView4 = oplusQSSimpleHeader.getClockView()) == null) ? 0.0f : clockView4.getBaseline();
        if (((int) baseline) == -1) {
            return;
        }
        FrameLayout frameLayout = this.fakeClockViewContainer;
        float height = frameLayout != null ? frameLayout.getHeight() : 0.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader2 = this.oplusQSSimpleHeader;
        float height2 = (oplusQSSimpleHeader2 == null || (clockView3 = oplusQSSimpleHeader2.getClockView()) == null) ? 0.0f : clockView3.getHeight();
        float f = (height - height2) / 2.0f;
        OplusQSSimpleHeader oplusQSSimpleHeader3 = this.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader3 != null && (clockView2 = oplusQSSimpleHeader3.getClockView()) != null) {
            clockView2.setPivotX(DeviceUtil.isRtl() ? clockView2.getWidth() : 0.0f);
            clockView2.setPivotY(baseline);
        }
        FrameLayout frameLayout2 = this.fakeClockViewContainer;
        if (frameLayout2 != null) {
            frameLayout2.setPivotX(DeviceUtil.isRtl() ? frameLayout2.getWidth() - this.statusbarClockPadding : this.statusbarClockPadding);
            frameLayout2.setPivotY(baseline + f);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sb = new StringBuilder("OplusSimpleQSFakeController-->");
            OplusQSSimpleHeader oplusQSSimpleHeader4 = this.oplusQSSimpleHeader;
            Float fValueOf = (oplusQSSimpleHeader4 == null || (clockView = oplusQSSimpleHeader4.getClockView()) == null) ? null : Float.valueOf(clockView.getPivotX());
            FrameLayout frameLayout3 = this.fakeClockViewContainer;
            Float fValueOf2 = frameLayout3 != null ? Float.valueOf(frameLayout3.getPivotX()) : null;
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("updateClockPivot qsClockPivotY: ", baseline, ", qsClockPivotOffset: ", f, ", qsClockHeight: ");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, height2, ", statusBarClockHeight: ", height, "qsClock pivotX: ");
            sbM.append(fValueOf);
            sbM.append(", fakeClock pivotX: ");
            sbM.append(fValueOf2);
            sb.append(sbM.toString());
            Log.d("SystemUi--Notification", sb.toString());
        }
    }

    public final void updateFakeClockScale(float f) {
        float f2 = (((this.clockScaleFactor + this.systemFontScale) - 1.0f) * f) + 1.0f;
        if (this.isLargeDisplay && f2 > 3.0f) {
            f2 = 3.0f;
        }
        FrameLayout frameLayout = this.fakeClockViewContainer;
        if (frameLayout != null) {
            frameLayout.setScaleX(f2);
            frameLayout.setScaleY(f2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:116:0x021b A[PHI: r7
      0x021b: PHI (r7v23 int) = (r7v22 int), (r7v35 int) binds: [B:123:0x024d, B:114:0x0214] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:15:0x0058 A[PHI: r3
      0x0058: PHI (r3v6 int) = (r3v5 int), (r3v21 int) binds: [B:22:0x0073, B:13:0x0051] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:40:0x00ce A[PHI: r4
      0x00ce: PHI (r4v15 int) = (r4v14 int), (r4v29 int) binds: [B:47:0x00e9, B:38:0x00c7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:92:0x01a7 A[PHI: r7
      0x01a7: PHI (r7v12 int) = (r7v11 int), (r7v40 int) binds: [B:99:0x01d9, B:90:0x01a0] A[DONT_GENERATE, DONT_INLINE]] */
    public final void updateFakeElementLoc() {
        int left;
        int left2;
        int left3;
        int left4;
        int left5;
        int left6;
        int left7;
        int left8;
        int left9;
        int i = this.context.getResources().getDisplayMetrics().widthPixels;
        FrameLayout frameLayout = this.fakeClockViewContainer;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) (frameLayout != null ? frameLayout.getLayoutParams() : null);
        boolean zIsRtl = com.oplus.systemui.qs.base.util.DeviceUtil.isRtl();
        OplusFakeViewProvider oplusFakeViewProvider = this.oplusFakeViewProvider;
        if (zIsRtl) {
            FakeType fakeType = FakeType.FAKE_CLOCK;
            int realStatusElementLocX = (int) ((i - oplusFakeViewProvider.getSimpleFakeView(fakeType).getRealStatusElementLocX()) - ((Number) this.oplusFakeViewProvider.getSimpleFakeView(fakeType).getRealStatusElementParams().getFirst()).floatValue());
            View view = this.oplusQSFrame;
            left = realStatusElementLocX - (view != null ? view.getLeft() : 0);
            OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader != null) {
                left2 = oplusQSSimpleHeader.getLeft();
            } else {
                left2 = 0;
            }
        } else {
            int realStatusElementLocX2 = (int) oplusFakeViewProvider.getSimpleFakeView(FakeType.FAKE_CLOCK).getRealStatusElementLocX();
            View view2 = this.oplusQSFrame;
            left = realStatusElementLocX2 - (view2 != null ? view2.getLeft() : 0);
            OplusQSSimpleHeader oplusQSSimpleHeader2 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader2 != null) {
                left2 = oplusQSSimpleHeader2.getLeft();
            } else {
                left2 = 0;
            }
        }
        layoutParams.setMarginStart(left - left2);
        FrameLayout frameLayout2 = this.fakeClockViewContainer;
        if (frameLayout2 != null) {
            frameLayout2.setLayoutParams(layoutParams);
        }
        FrameLayout frameLayout3 = this.fakeCarrierContainer;
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) (frameLayout3 != null ? frameLayout3.getLayoutParams() : null);
        boolean zIsRtl2 = com.oplus.systemui.qs.base.util.DeviceUtil.isRtl();
        OplusFakeViewProvider oplusFakeViewProvider2 = this.oplusFakeViewProvider;
        if (zIsRtl2) {
            FakeType fakeType2 = FakeType.FAKE_CARRIER;
            int realStatusElementLocX3 = (int) ((i - oplusFakeViewProvider2.getSimpleFakeView(fakeType2).getRealStatusElementLocX()) - ((Number) this.oplusFakeViewProvider.getSimpleFakeView(fakeType2).getRealStatusElementParams().getFirst()).floatValue());
            View view3 = this.oplusQSFrame;
            left3 = realStatusElementLocX3 - (view3 != null ? view3.getLeft() : 0);
            OplusQSSimpleHeader oplusQSSimpleHeader3 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader3 != null) {
                left4 = oplusQSSimpleHeader3.getLeft();
            } else {
                left4 = 0;
            }
        } else {
            int realStatusElementLocX4 = (int) oplusFakeViewProvider2.getSimpleFakeView(FakeType.FAKE_CARRIER).getRealStatusElementLocX();
            View view4 = this.oplusQSFrame;
            left3 = realStatusElementLocX4 - (view4 != null ? view4.getLeft() : 0);
            OplusQSSimpleHeader oplusQSSimpleHeader4 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader4 != null) {
                left4 = oplusQSSimpleHeader4.getLeft();
            } else {
                left4 = 0;
            }
        }
        layoutParams2.setMarginStart(left3 - left4);
        FrameLayout frameLayout4 = this.fakeCarrierContainer;
        if (frameLayout4 != null) {
            frameLayout4.setLayoutParams(layoutParams2);
        }
        FrameLayout frameLayout5 = this.fakeNotificationContainer;
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) (frameLayout5 != null ? frameLayout5.getLayoutParams() : null);
        StatusBarFakeFrame simpleFakeView = this.oplusFakeViewProvider.getSimpleFakeView(FakeType.FAKE_NOTIFICATION);
        if (com.oplus.systemui.qs.base.util.DeviceUtil.isRtl()) {
            float realStatusElementLocX5 = (i - simpleFakeView.getRealStatusElementLocX()) - ((Number) simpleFakeView.getRealStatusElementParams().getFirst()).floatValue();
            View view5 = this.oplusQSFrame;
            float left10 = realStatusElementLocX5 - (view5 != null ? view5.getLeft() : 0);
            OplusQSSimpleHeader oplusQSSimpleHeader5 = this.oplusQSSimpleHeader;
            left5 = (int) ((left10 - (oplusQSSimpleHeader5 != null ? oplusQSSimpleHeader5.getLeft() : 0)) - simpleFakeView.getOffsetX());
        } else {
            int realStatusElementLocX6 = (int) simpleFakeView.getRealStatusElementLocX();
            View view6 = this.oplusQSFrame;
            int left11 = realStatusElementLocX6 - (view6 != null ? view6.getLeft() : 0);
            OplusQSSimpleHeader oplusQSSimpleHeader6 = this.oplusQSSimpleHeader;
            left5 = (left11 - (oplusQSSimpleHeader6 != null ? oplusQSSimpleHeader6.getLeft() : 0)) - simpleFakeView.getOffsetX();
        }
        layoutParams3.setMarginStart(left5);
        FrameLayout frameLayout6 = this.fakeNotificationContainer;
        if (frameLayout6 != null) {
            frameLayout6.setLayoutParams(layoutParams3);
        }
        FrameLayout frameLayout7 = this.fakeStatusBarIconsContainer;
        FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) (frameLayout7 != null ? frameLayout7.getLayoutParams() : null);
        boolean zIsRtl3 = com.oplus.systemui.qs.base.util.DeviceUtil.isRtl();
        OplusFakeViewProvider oplusFakeViewProvider3 = this.oplusFakeViewProvider;
        if (zIsRtl3) {
            int realStatusElementLocX7 = (int) oplusFakeViewProvider3.getSimpleFakeView(FakeType.FAKE_SYSTEM_ICON).getRealStatusElementLocX();
            View view7 = this.oplusQSFrame;
            left6 = realStatusElementLocX7 - (view7 != null ? view7.getLeft() : 0);
            OplusQSSimpleHeader oplusQSSimpleHeader7 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader7 != null) {
                left7 = oplusQSSimpleHeader7.getLeft();
            } else {
                left7 = 0;
            }
        } else {
            FakeType fakeType3 = FakeType.FAKE_SYSTEM_ICON;
            int realStatusElementLocX8 = (int) ((i - oplusFakeViewProvider3.getSimpleFakeView(fakeType3).getRealStatusElementLocX()) - ((Number) this.oplusFakeViewProvider.getSimpleFakeView(fakeType3).getRealStatusElementParams().getFirst()).floatValue());
            View view8 = this.oplusQSFrame;
            left6 = realStatusElementLocX8 - (view8 != null ? view8.getLeft() : 0);
            OplusQSSimpleHeader oplusQSSimpleHeader8 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader8 != null) {
                left7 = oplusQSSimpleHeader8.getLeft();
            } else {
                left7 = 0;
            }
        }
        layoutParams4.setMarginEnd(left6 - left7);
        FrameLayout frameLayout8 = this.fakeStatusBarIconsContainer;
        if (frameLayout8 != null) {
            frameLayout8.setLayoutParams(layoutParams4);
        }
        FrameLayout frameLayout9 = this.fakeDynamicIconsContainer;
        FrameLayout.LayoutParams layoutParams5 = (FrameLayout.LayoutParams) (frameLayout9 != null ? frameLayout9.getLayoutParams() : null);
        boolean zIsRtl4 = com.oplus.systemui.qs.base.util.DeviceUtil.isRtl();
        OplusFakeViewProvider oplusFakeViewProvider4 = this.oplusFakeViewProvider;
        if (zIsRtl4) {
            int realStatusElementLocX9 = (int) oplusFakeViewProvider4.getSimpleFakeView(FakeType.FAKE_PRIVACY).getRealStatusElementLocX();
            View view9 = this.oplusQSFrame;
            left8 = realStatusElementLocX9 - (view9 != null ? view9.getLeft() : 0);
            OplusQSSimpleHeader oplusQSSimpleHeader9 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader9 != null) {
                left9 = oplusQSSimpleHeader9.getLeft();
            } else {
                left9 = 0;
            }
        } else {
            FakeType fakeType4 = FakeType.FAKE_PRIVACY;
            int realStatusElementLocX10 = (int) ((i - oplusFakeViewProvider4.getSimpleFakeView(fakeType4).getRealStatusElementLocX()) - ((Number) this.oplusFakeViewProvider.getSimpleFakeView(fakeType4).getRealStatusElementParams().getFirst()).floatValue());
            View view10 = this.oplusQSFrame;
            left8 = realStatusElementLocX10 - (view10 != null ? view10.getLeft() : 0);
            OplusQSSimpleHeader oplusQSSimpleHeader10 = this.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader10 != null) {
                left9 = oplusQSSimpleHeader10.getLeft();
            } else {
                left9 = 0;
            }
        }
        layoutParams5.setMarginEnd(left8 - left9);
        FrameLayout frameLayout10 = this.fakeDynamicIconsContainer;
        if (frameLayout10 != null) {
            frameLayout10.setLayoutParams(layoutParams5);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sb = new StringBuilder("OplusSimpleQSFakeController-->");
            int marginStart = layoutParams.getMarginStart();
            int marginStart2 = layoutParams2.getMarginStart();
            int marginStart3 = layoutParams3.getMarginStart();
            int marginEnd = layoutParams4.getMarginEnd();
            int marginEnd2 = layoutParams5.getMarginEnd();
            View view11 = this.oplusQSFrame;
            int left12 = view11 != null ? view11.getLeft() : 0;
            OplusQSSimpleHeader oplusQSSimpleHeader11 = this.oplusQSSimpleHeader;
            int left13 = oplusQSSimpleHeader11 != null ? oplusQSSimpleHeader11.getLeft() : 0;
            float f = this.panelThreshold;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("screenWidth ", i, marginStart, ",fakeClockLayoutParam.marginStart:", ",fakeCarrierLayoutParam.marginStart:");
            RectList$.ExternalSyntheticOutline0.m(marginStart2, marginStart3, ",fakeNotificationIconLayoutParam.marginStart:", ",fakeStatusIconLayoutParam.marginEnd:", sbM);
            RectList$.ExternalSyntheticOutline0.m(marginEnd, marginEnd2, ",fakePrivacyLayoutParam.marginEnd:", "oplusQSFrame.left :", sbM);
            RectList$.ExternalSyntheticOutline0.m(left12, left13, "oplusQSSimpleHeader.left :", "panelThreshold :", sbM);
            sbM.append(f);
            sb.append(sbM.toString());
            Log.d("SystemUi--Notification", sb.toString());
        }
    }

    /* JADX WARN: Code duplicated, block: B:3:0x0002 A[PHI: r4
      0x0002: PHI (r4v11 java.lang.Integer) = (r4v0 java.lang.Integer), (r4v5 java.lang.Integer) binds: [B:2:0x0000, B:8:0x0019] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:967)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:130)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    public final void updateFakeMarginTop(String str, Integer num) {
        int dimensionPixelSize;
        if (num == null) {
            OplusSeparateNotificationAndQSEx oplusSeparateNotificationAndQSEx = (OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class);
            num = oplusSeparateNotificationAndQSEx != null ? oplusSeparateNotificationAndQSEx.getStatusBarTopSpacing() : null;
            if (num != null) {
                dimensionPixelSize = num.intValue();
            } else {
                dimensionPixelSize = this.context.getResources().getDimensionPixelSize(2131170599);
            }
        } else {
            dimensionPixelSize = num.intValue();
        }
        int iLockDpi = ResourcesUtilKt.lockDpi(2131170596, this.context);
        updateContainerMarginAndHeight(this.oplusQSFakeContainer, dimensionPixelSize, iLockDpi, str);
        updateContainerMarginAndHeight(this.oplusQSFakeFixedContainer, dimensionPixelSize, iLockDpi, str);
    }

    public final void updateQSClockScale(float f) {
        Clock clockView;
        float f2 = (((this.clockScaleFactor + this.systemFontScale) - 1.0f) * f) + 1.0f;
        if (this.isLargeDisplay && f2 > 3.0f) {
            f2 = 3.0f;
        }
        OplusQSSimpleHeader oplusQSSimpleHeader = this.oplusQSSimpleHeader;
        if (oplusQSSimpleHeader == null || (clockView = oplusQSSimpleHeader.getClockView()) == null) {
            return;
        }
        clockView.setScaleX(f2);
        clockView.setScaleY(f2);
    }

    public final void updateQSFakeFixedContainerTy(float f) {
        ViewGroup viewGroup;
        ViewGroup viewGroup2 = this.oplusQSFakeFixedContainer;
        if (viewGroup2 != null) {
            viewGroup2.setTranslationY(this.panelThreshold - f);
        }
        if (!isNotSupportSeamlessTransfer() || (viewGroup = this.oplusQSFakeContainer) == null) {
            return;
        }
        viewGroup.setTranslationY(this.panelThreshold - f);
    }

    public final void updateQsSimpleHeaderHeight(int i) {
        ((OplusNotificationTiltShiftBlurController) this.tiltShiftBlurController.get()).updateQsSimpleHeaderHeight(i);
    }
}
