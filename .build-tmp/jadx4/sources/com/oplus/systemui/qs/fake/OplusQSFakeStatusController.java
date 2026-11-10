package com.oplus.systemui.qs.fake;

import android.content.res.Resources;
import android.util.Log;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable$;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.OneShotPreDrawListener;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.qs.ExpandableNode;
import com.android.systemui.qs.QSNode;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.shade.NotificationsQSContainerController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.LargeScreenUtils;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.qs.OplusQSFooterViewController;
import com.oplus.systemui.qs.OplusQuickStatusBarHeaderController;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplus.systemui.qs.fake.view.QsFakeLayout;
import com.oplus.systemui.qs.helper.OplusQSFontHelper;
import com.oplus.systemui.qs.widget.OplusSecondCarrierText;
import com.oplus.systemui.seamless.FakeType;
import com.oplus.systemui.seamless.OplusFakeViewProvider;
import com.oplus.systemui.seamless.widget.StatusBarFakeFrame;
import com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManager;
import com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1;
import com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManagerImpl;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.function.Consumer;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFakeStatusController extends ViewController implements QSNode, ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusQSFakeStatusController";
    public static float default_setting_translationy;
    private final NotificationPanelChildAppearAnimationManager appearAnimationManager;
    public float carrierContainerTranslationX;
    public float carrierContainerTranslationY;
    public float clockBaseLine;
    public float clockTranslationX;
    private final OplusQSFakeStatusController$clockViewGlobalLayoutListener$1 clockViewGlobalLayoutListener;
    private final ConfigurationController configurationController;
    public float differenceTranslationY;
    private final OplusQSFakeStatusController$elementShowStateChangeListener$1 elementShowStateChangeListener;
    private FrameLayout fakeCarrierContainer;
    private StatusBarFakeFrame fakeClock;
    private FrameLayout fakeClockContainer;
    private StatusBarFakeFrame fakeKeyguardCarriertext;
    private StatusBarFakeFrame fakeKeyguardSystemIcon;
    private ConstraintLayout fakeLeft;
    private StatusBarFakeFrame fakeNotification;
    private FrameLayout fakeNotificationIconContainer;
    private StatusBarFakeFrame fakeOperator;
    private StatusBarFakeFrame fakePrivacy;
    private FrameLayout fakePrivacyIconContainer;
    private ConstraintLayout fakeRight;
    private FrameLayout fakeStatusIconContainer;
    private StatusBarFakeFrame fakeSystemIcon;
    private final View header;
    public boolean isFakeClockGone;
    private FrameLayout keyguardFakeCarrierContainer;
    private FrameLayout keyguardFakeStatusIconContainer;
    public float keyguardFraction;
    public boolean keyguardNeedUpdate;
    private final View.OnLayoutChangeListener layoutChangedListener;
    public boolean needUpdatePosition;
    public float notificationIconTranslationX;
    public float notificationIconTranslationY;
    private final NotificationsQSContainerController notificationsQSContainerController;
    private final OplusFakeViewProvider oplusFakeViewProvider;
    private final OplusQSFooterViewController oplusQSFooterViewController;
    private final PanelAnimationEx panelAnimationEx;
    private final OplusQSFakeStatusController$panelTranslationYChangeListener$1 panelTranslationYChangeListener;
    private ConstraintLayout qsCarrierLeft;
    public float qsExpandedY;
    private final OplusQSFakeStatusController$qsPanelExpandFractionListener$1 qsPanelExpandFractionListener;
    private ViewGroup quickStatus;
    public float rawFraction;
    private OplusSecondCarrierText splitCarrier;
    private final OplusQuickStatusBarHeaderController statusBarHeader;
    private final SysuiStatusBarStateController statusBarStateController;
    private final OplusQSFakeStatusController$statusBarStateListener$1 statusBarStateListener;
    public float statusIconTranslationX;
    public float statusIconTranslationY;
    private final KeyguardUpdateMonitor updateMonitor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final float getDefault_setting_translationy() {
            return OplusQSFakeStatusController.default_setting_translationy;
        }

        public final void setDefault_setting_translationy(float f) {
            OplusQSFakeStatusController.default_setting_translationy = f;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.fake.OplusQSFakeStatusController$onViewAttached$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Consumer {
        public /* synthetic */ OplusQSFakeStatusController this$0;

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            OplusQSFakeStatusController oplusQSFakeStatusController = this.this$0;
            View view = oplusQSFakeStatusController.mView;
            OplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1 oplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1 = new OplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1(1);
            oplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1.this$0 = oplusQSFakeStatusController;
            VarHandle.storeStoreFence();
            OneShotPreDrawListener.add(view, oplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1);
        }
    }

    public static Unit $r8$lambda$7PPGmTsJ_hG_OugNsDOEFCp51LQ(OplusQSFakeStatusController oplusQSFakeStatusController, float f, float f2) {
        float f3 = 1.0f - f;
        oplusQSFakeStatusController.header.setTransitionAlpha(f3);
        oplusQSFakeStatusController.statusBarHeader.updateViewState(f3, 1.0f - f2);
        ViewGroup mClockContainer = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
        if (mClockContainer != null) {
            mClockContainer.setTransitionAlpha(f3);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$QnR89D8-3L0mP1NH5Qpsm0Syjas, reason: not valid java name */
    public static Unit m268$r8$lambda$QnR89D83L0mP1NH5Qpsm0Syjas(OplusQSFakeStatusController oplusQSFakeStatusController, float f, float f2) {
        oplusQSFakeStatusController.oplusQSFooterViewController.updateViewState(f, f2);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$QrWM4TuSOaZxMeHZalxfLGJGiYM(OplusQSFakeStatusController oplusQSFakeStatusController, float f, float f2) {
        float f3 = 1.0f - f;
        oplusQSFakeStatusController.header.setTransitionAlpha(f3);
        float f4 = 1.0f - f2;
        oplusQSFakeStatusController.statusBarHeader.updateViewState(f3, f4);
        oplusQSFakeStatusController.oplusQSFooterViewController.updateViewState(f3, f4);
        ViewGroup mClockContainer = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
        if (mClockContainer != null) {
            mClockContainer.setTransitionAlpha(f3);
        }
        FrameLayout frameLayout = oplusQSFakeStatusController.fakeClockContainer;
        if (frameLayout != null) {
            frameLayout.setTransitionAlpha(f);
        }
        FrameLayout frameLayout2 = oplusQSFakeStatusController.fakePrivacyIconContainer;
        if (frameLayout2 != null) {
            frameLayout2.setTransitionAlpha(f);
        }
        FrameLayout frameLayout3 = oplusQSFakeStatusController.fakeStatusIconContainer;
        if (frameLayout3 != null) {
            frameLayout3.setTransitionAlpha(f);
        }
        oplusQSFakeStatusController.fakeKeyguardSystemIcon.setTransitionAlpha(f);
        FrameLayout frameLayout4 = oplusQSFakeStatusController.keyguardFakeCarrierContainer;
        if (frameLayout4 != null) {
            frameLayout4.setTransitionAlpha(f);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$T2FcQXgTF3-9dswZW475VGyXXpQ, reason: not valid java name */
    public static Unit m269$r8$lambda$T2FcQXgTF39dswZW475VGyXXpQ(OplusQSFakeStatusController oplusQSFakeStatusController, float f) {
        FrameLayout frameLayout = oplusQSFakeStatusController.fakeCarrierContainer;
        if (frameLayout != null) {
            frameLayout.setTransitionAlpha(f);
        }
        FrameLayout frameLayout2 = oplusQSFakeStatusController.keyguardFakeCarrierContainer;
        if (frameLayout2 != null) {
            frameLayout2.setTransitionAlpha(f);
        }
        FrameLayout frameLayout3 = oplusQSFakeStatusController.fakeClockContainer;
        if (frameLayout3 != null) {
            frameLayout3.setTransitionAlpha(f);
        }
        FrameLayout frameLayout4 = oplusQSFakeStatusController.fakeNotificationIconContainer;
        if (frameLayout4 != null) {
            frameLayout4.setTransitionAlpha(f);
        }
        FrameLayout frameLayout5 = oplusQSFakeStatusController.fakeStatusIconContainer;
        if (frameLayout5 != null) {
            frameLayout5.setTransitionAlpha(f);
        }
        FrameLayout frameLayout6 = oplusQSFakeStatusController.fakePrivacyIconContainer;
        if (frameLayout6 != null) {
            frameLayout6.setTransitionAlpha(f);
        }
        oplusQSFakeStatusController.fakeKeyguardSystemIcon.setTransitionAlpha(f);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$V0uGvebCLBv_1v4DWZLYk57JaJY(OplusQSFakeStatusController oplusQSFakeStatusController, float f) {
        FrameLayout frameLayout = oplusQSFakeStatusController.fakeCarrierContainer;
        if (frameLayout != null) {
            frameLayout.setTransitionAlpha(f);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$VCL4f1iriyntP3b-0JzmXr4ecXk, reason: not valid java name */
    public static Unit m270$r8$lambda$VCL4f1iriyntP3b0JzmXr4ecXk(OplusQSFakeStatusController oplusQSFakeStatusController, float f, float f2) {
        float f3 = 1.0f - f;
        oplusQSFakeStatusController.header.setTransitionAlpha(f3);
        oplusQSFakeStatusController.statusBarHeader.updateViewState(f3, 1.0f - f2);
        ViewGroup mClockContainer = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
        if (mClockContainer != null) {
            mClockContainer.setTransitionAlpha(f3);
        }
        FrameLayout frameLayout = oplusQSFakeStatusController.fakePrivacyIconContainer;
        if (frameLayout != null) {
            frameLayout.setTransitionAlpha(f);
        }
        FrameLayout frameLayout2 = oplusQSFakeStatusController.fakeStatusIconContainer;
        if (frameLayout2 != null) {
            frameLayout2.setTransitionAlpha(f);
        }
        oplusQSFakeStatusController.fakeKeyguardSystemIcon.setTransitionAlpha(f);
        FrameLayout frameLayout3 = oplusQSFakeStatusController.keyguardFakeCarrierContainer;
        if (frameLayout3 != null) {
            frameLayout3.setTransitionAlpha(f);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$WVq31py5KiZeEIAJd4z1phCKtdI(OplusQSFakeStatusController oplusQSFakeStatusController, float f, float f2) {
        float f3 = 1.0f - f;
        oplusQSFakeStatusController.header.setTransitionAlpha(f3);
        oplusQSFakeStatusController.statusBarHeader.updateViewState(f3, 1.0f - f2);
        ViewGroup mClockContainer = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
        if (mClockContainer != null) {
            mClockContainer.setTransitionAlpha(f3);
        }
        FrameLayout frameLayout = oplusQSFakeStatusController.fakeClockContainer;
        if (frameLayout != null) {
            frameLayout.setTransitionAlpha(f);
        }
        FrameLayout frameLayout2 = oplusQSFakeStatusController.fakeNotificationIconContainer;
        if (frameLayout2 != null) {
            frameLayout2.setTransitionAlpha(f);
        }
        FrameLayout frameLayout3 = oplusQSFakeStatusController.fakePrivacyIconContainer;
        if (frameLayout3 != null) {
            frameLayout3.setTransitionAlpha(f);
        }
        FrameLayout frameLayout4 = oplusQSFakeStatusController.fakeStatusIconContainer;
        if (frameLayout4 != null) {
            frameLayout4.setTransitionAlpha(f);
        }
        oplusQSFakeStatusController.fakeKeyguardSystemIcon.setTransitionAlpha(f);
        FrameLayout frameLayout5 = oplusQSFakeStatusController.keyguardFakeCarrierContainer;
        if (frameLayout5 != null) {
            frameLayout5.setTransitionAlpha(f);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$ZRIJSCM52JpYFtYGmHHMdYeyj5o(OplusQSFakeStatusController oplusQSFakeStatusController, float f, float f2) {
        float f3 = 1.0f - f;
        oplusQSFakeStatusController.header.setTransitionAlpha(f3);
        float f4 = 1.0f - f2;
        oplusQSFakeStatusController.statusBarHeader.updateViewState(f3, f4);
        oplusQSFakeStatusController.oplusQSFooterViewController.updateViewState(f3, f4);
        ViewGroup mClockContainer = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
        if (mClockContainer != null) {
            mClockContainer.setTransitionAlpha(f3);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$lX_cZletCtThocgO5lBs9YKkLBA(OplusQSFakeStatusController oplusQSFakeStatusController, float f, float f2) {
        float f3 = 1.0f - f;
        oplusQSFakeStatusController.header.setTransitionAlpha(f3);
        oplusQSFakeStatusController.statusBarHeader.updateViewState(f3, 1.0f - f2);
        ViewGroup mClockContainer = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
        if (mClockContainer != null) {
            mClockContainer.setTransitionAlpha(f3);
        }
        FrameLayout frameLayout = oplusQSFakeStatusController.fakeClockContainer;
        if (frameLayout != null) {
            frameLayout.setTransitionAlpha(f);
        }
        FrameLayout frameLayout2 = oplusQSFakeStatusController.fakePrivacyIconContainer;
        if (frameLayout2 != null) {
            frameLayout2.setTransitionAlpha(f);
        }
        FrameLayout frameLayout3 = oplusQSFakeStatusController.fakeStatusIconContainer;
        if (frameLayout3 != null) {
            frameLayout3.setTransitionAlpha(f);
        }
        oplusQSFakeStatusController.fakeKeyguardSystemIcon.setTransitionAlpha(f);
        FrameLayout frameLayout4 = oplusQSFakeStatusController.keyguardFakeCarrierContainer;
        if (frameLayout4 != null) {
            frameLayout4.setTransitionAlpha(f);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$ntxHaEwaoI2mGQ87uLCD5xEAPqA(OplusQSFakeStatusController oplusQSFakeStatusController, float f) {
        FrameLayout frameLayout = oplusQSFakeStatusController.fakeCarrierContainer;
        if (frameLayout != null) {
            frameLayout.setTransitionAlpha(f);
        }
        FrameLayout frameLayout2 = oplusQSFakeStatusController.keyguardFakeCarrierContainer;
        if (frameLayout2 != null) {
            frameLayout2.setTransitionAlpha(f);
        }
        FrameLayout frameLayout3 = oplusQSFakeStatusController.fakeClockContainer;
        if (frameLayout3 != null) {
            frameLayout3.setTransitionAlpha(f);
        }
        FrameLayout frameLayout4 = oplusQSFakeStatusController.fakeNotificationIconContainer;
        if (frameLayout4 != null) {
            frameLayout4.setTransitionAlpha(f);
        }
        FrameLayout frameLayout5 = oplusQSFakeStatusController.fakeStatusIconContainer;
        if (frameLayout5 != null) {
            frameLayout5.setTransitionAlpha(f);
        }
        FrameLayout frameLayout6 = oplusQSFakeStatusController.fakePrivacyIconContainer;
        if (frameLayout6 != null) {
            frameLayout6.setTransitionAlpha(f);
        }
        oplusQSFakeStatusController.fakeKeyguardSystemIcon.setTransitionAlpha(f);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$x70NeZHCQzGpVmCcG1L1zwptd_U(OplusQSFakeStatusController oplusQSFakeStatusController, float f) {
        FrameLayout frameLayout = oplusQSFakeStatusController.fakeCarrierContainer;
        if (frameLayout != null) {
            frameLayout.setTransitionAlpha(f);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSFakeStatusController(QsFakeLayout qsFakeLayout, View view, PanelAnimationEx panelAnimationEx, OplusQSFooterViewController oplusQSFooterViewController, OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController, KeyguardUpdateMonitor keyguardUpdateMonitor, SysuiStatusBarStateController sysuiStatusBarStateController, ConfigurationController configurationController, NotificationsQSContainerController notificationsQSContainerController, OplusFakeViewProvider oplusFakeViewProvider) {
        super(qsFakeLayout);
        this.header = view;
        this.panelAnimationEx = panelAnimationEx;
        this.oplusQSFooterViewController = oplusQSFooterViewController;
        this.statusBarHeader = oplusQuickStatusBarHeaderController;
        this.updateMonitor = keyguardUpdateMonitor;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.configurationController = configurationController;
        this.notificationsQSContainerController = notificationsQSContainerController;
        this.oplusFakeViewProvider = oplusFakeViewProvider;
        this.fakeClock = oplusFakeViewProvider.getQsFakeView(FakeType.FAKE_CLOCK);
        this.fakeNotification = oplusFakeViewProvider.getQsFakeView(FakeType.FAKE_NOTIFICATION);
        this.fakeSystemIcon = oplusFakeViewProvider.getQsFakeView(FakeType.FAKE_SYSTEM_ICON);
        this.fakePrivacy = oplusFakeViewProvider.getQsFakeView(FakeType.FAKE_PRIVACY);
        this.fakeOperator = oplusFakeViewProvider.getQsFakeView(FakeType.FAKE_CARRIER);
        this.fakeKeyguardCarriertext = oplusFakeViewProvider.getQsFakeView(FakeType.KEYGUARD_FAKE_CARRIER);
        this.fakeKeyguardSystemIcon = oplusFakeViewProvider.getQsFakeView(FakeType.KEYGUARD_FAKE_SYSTEM_ICON);
        this.needUpdatePosition = true;
        this.keyguardNeedUpdate = true;
        this.appearAnimationManager = NotificationPanelChildAppearAnimationManager.Companion.create(true);
        getContext().getResources().getDimension(2131171867);
        getContext().getResources().getDimension(2131171870);
        OplusQSFakeStatusController$layoutChangedListener$1 oplusQSFakeStatusController$layoutChangedListener$1 = new OplusQSFakeStatusController$layoutChangedListener$1();
        oplusQSFakeStatusController$layoutChangedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.layoutChangedListener = oplusQSFakeStatusController$layoutChangedListener$1;
        OplusQSFakeStatusController$elementShowStateChangeListener$1 oplusQSFakeStatusController$elementShowStateChangeListener$1 = new OplusQSFakeStatusController$elementShowStateChangeListener$1();
        oplusQSFakeStatusController$elementShowStateChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.elementShowStateChangeListener = oplusQSFakeStatusController$elementShowStateChangeListener$1;
        OplusQSFakeStatusController$statusBarStateListener$1 oplusQSFakeStatusController$statusBarStateListener$1 = new OplusQSFakeStatusController$statusBarStateListener$1();
        oplusQSFakeStatusController$statusBarStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusBarStateListener = oplusQSFakeStatusController$statusBarStateListener$1;
        OplusQSFakeStatusController$panelTranslationYChangeListener$1 oplusQSFakeStatusController$panelTranslationYChangeListener$1 = new OplusQSFakeStatusController$panelTranslationYChangeListener$1();
        oplusQSFakeStatusController$panelTranslationYChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.panelTranslationYChangeListener = oplusQSFakeStatusController$panelTranslationYChangeListener$1;
        OplusQSFakeStatusController$qsPanelExpandFractionListener$1 oplusQSFakeStatusController$qsPanelExpandFractionListener$1 = new OplusQSFakeStatusController$qsPanelExpandFractionListener$1();
        oplusQSFakeStatusController$qsPanelExpandFractionListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsPanelExpandFractionListener = oplusQSFakeStatusController$qsPanelExpandFractionListener$1;
        OplusQSFakeStatusController$clockViewGlobalLayoutListener$1 oplusQSFakeStatusController$clockViewGlobalLayoutListener$1 = new OplusQSFakeStatusController$clockViewGlobalLayoutListener$1();
        oplusQSFakeStatusController$clockViewGlobalLayoutListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.clockViewGlobalLayoutListener = oplusQSFakeStatusController$clockViewGlobalLayoutListener$1;
    }

    public static final void access$showOrHideCarrierForKeyguard(OplusQSFakeStatusController oplusQSFakeStatusController) {
        if (oplusQSFakeStatusController.updateMonitor.isKeyguardVisible$1()) {
            if (!oplusQSFakeStatusController.panelAnimationEx.isElementShowing()) {
                oplusQSFakeStatusController.showOrHideCarrier(oplusQSFakeStatusController.keyguardFraction > 0.05f);
            } else {
                if ((!oplusQSFakeStatusController.panelAnimationEx.isElementShowing() || oplusQSFakeStatusController.keyguardFraction > 0.05f) && oplusQSFakeStatusController.keyguardFraction <= 0.05f) {
                    return;
                }
                oplusQSFakeStatusController.showOrHideCarrier(true);
            }
        }
    }

    public static final void access$showOrHideView(OplusQSFakeStatusController oplusQSFakeStatusController, boolean z) {
        int i = 1;
        if (!z) {
            FrameLayout frameLayout = oplusQSFakeStatusController.fakeNotificationIconContainer;
            if (frameLayout != null) {
                ((NotificationPanelChildAppearAnimationManagerImpl) oplusQSFakeStatusController.appearAnimationManager).cancelViewAnimation(frameLayout, false, "fake show");
            }
            if (oplusQSFakeStatusController.isTabletLandscape()) {
                NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager = oplusQSFakeStatusController.appearAnimationManager;
                FrameLayout frameLayout2 = oplusQSFakeStatusController.fakeClockContainer;
                OplusQSFakeStatusController$$ExternalSyntheticLambda0 oplusQSFakeStatusController$$ExternalSyntheticLambda0 = new OplusQSFakeStatusController$$ExternalSyntheticLambda0(9);
                oplusQSFakeStatusController$$ExternalSyntheticLambda0.f$0 = oplusQSFakeStatusController;
                VarHandle.storeStoreFence();
                ((NotificationPanelChildAppearAnimationManagerImpl) notificationPanelChildAppearAnimationManager).show(frameLayout2, true, 0L, 0, (120 & 16) != 0 ? false : false, (120 & 32) != 0 ? 0.0f : 0.0f, new NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1(), oplusQSFakeStatusController$$ExternalSyntheticLambda0);
            } else {
                boolean zIsQsexpanded = oplusQSFakeStatusController.isQsexpanded();
                NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager2 = oplusQSFakeStatusController.appearAnimationManager;
                FrameLayout frameLayout3 = oplusQSFakeStatusController.fakeClockContainer;
                if (zIsQsexpanded) {
                    OplusQSFakeStatusController$$ExternalSyntheticLambda0 oplusQSFakeStatusController$$ExternalSyntheticLambda1 = new OplusQSFakeStatusController$$ExternalSyntheticLambda0(10);
                    oplusQSFakeStatusController$$ExternalSyntheticLambda1.f$0 = oplusQSFakeStatusController;
                    VarHandle.storeStoreFence();
                    ((NotificationPanelChildAppearAnimationManagerImpl) notificationPanelChildAppearAnimationManager2).show(frameLayout3, true, 0L, 0, (120 & 16) != 0 ? false : false, (120 & 32) != 0 ? 0.0f : 0.0f, new NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1(), oplusQSFakeStatusController$$ExternalSyntheticLambda1);
                } else {
                    OplusQSFakeStatusController$$ExternalSyntheticLambda0 oplusQSFakeStatusController$$ExternalSyntheticLambda2 = new OplusQSFakeStatusController$$ExternalSyntheticLambda0(i);
                    oplusQSFakeStatusController$$ExternalSyntheticLambda2.f$0 = oplusQSFakeStatusController;
                    VarHandle.storeStoreFence();
                    ((NotificationPanelChildAppearAnimationManagerImpl) notificationPanelChildAppearAnimationManager2).show(frameLayout3, true, 0L, 0, (120 & 16) != 0 ? false : false, (120 & 32) != 0 ? 0.0f : 0.0f, new NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1(), oplusQSFakeStatusController$$ExternalSyntheticLambda2);
                }
            }
            if (oplusQSFakeStatusController.isQsexpanded()) {
                return;
            }
            NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager3 = oplusQSFakeStatusController.appearAnimationManager;
            View clockView = oplusQSFakeStatusController.oplusQSFooterViewController.getClockView();
            OplusQSFakeStatusController$$ExternalSyntheticLambda0 oplusQSFakeStatusController$$ExternalSyntheticLambda3 = new OplusQSFakeStatusController$$ExternalSyntheticLambda0(2);
            oplusQSFakeStatusController$$ExternalSyntheticLambda3.f$0 = oplusQSFakeStatusController;
            VarHandle.storeStoreFence();
            NotificationPanelChildAppearAnimationManager.hide$default(notificationPanelChildAppearAnimationManager3, clockView, true, 0.0f, oplusQSFakeStatusController$$ExternalSyntheticLambda3, 52);
            return;
        }
        View clockView2 = oplusQSFakeStatusController.oplusQSFooterViewController.getClockView();
        if (clockView2 != null) {
            ((NotificationPanelChildAppearAnimationManagerImpl) oplusQSFakeStatusController.appearAnimationManager).cancelViewAnimation(clockView2, false, "fake hide");
        }
        if (oplusQSFakeStatusController.panelAnimationEx.getPanelExpandFraction().getRawFraction() == 1.0f && !oplusQSFakeStatusController.updateMonitor.isKeyguardVisible$1()) {
            FrameLayout frameLayout4 = oplusQSFakeStatusController.fakeClockContainer;
            if (frameLayout4 != null) {
                ((NotificationPanelChildAppearAnimationManagerImpl) oplusQSFakeStatusController.appearAnimationManager).cancelViewAnimation(frameLayout4, true, "fakeClock");
            }
            FrameLayout frameLayout5 = oplusQSFakeStatusController.fakeNotificationIconContainer;
            if (frameLayout5 != null) {
                ((NotificationPanelChildAppearAnimationManagerImpl) oplusQSFakeStatusController.appearAnimationManager).cancelViewAnimation(frameLayout5, false, "fakeNotification");
            }
            View clockView3 = oplusQSFakeStatusController.oplusQSFooterViewController.getClockView();
            if (clockView3 != null) {
                ((NotificationPanelChildAppearAnimationManagerImpl) oplusQSFakeStatusController.appearAnimationManager).cancelViewAnimation(clockView3, false, "clockView");
            }
            oplusQSFakeStatusController.resetTransitionAlpha(oplusQSFakeStatusController.panelAnimationEx.getPanelExpandFraction().getRawFraction());
            return;
        }
        if (oplusQSFakeStatusController.panelAnimationEx.getPanelExpandFraction().getRawFraction() != 1.0f && oplusQSFakeStatusController.isQsexpanded() && !oplusQSFakeStatusController.updateMonitor.isKeyguardVisible$1()) {
            FrameLayout frameLayout6 = oplusQSFakeStatusController.fakeClockContainer;
            if (frameLayout6 != null) {
                frameLayout6.setTransitionAlpha(0.0f);
            }
            FrameLayout frameLayout7 = oplusQSFakeStatusController.fakeNotificationIconContainer;
            if (frameLayout7 != null) {
                frameLayout7.setTransitionAlpha(0.0f);
            }
        }
        if (oplusQSFakeStatusController.isTabletLandscape()) {
            NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager4 = oplusQSFakeStatusController.appearAnimationManager;
            FrameLayout frameLayout8 = oplusQSFakeStatusController.fakeClockContainer;
            OplusQSFakeStatusController$$ExternalSyntheticLambda0 oplusQSFakeStatusController$$ExternalSyntheticLambda4 = new OplusQSFakeStatusController$$ExternalSyntheticLambda0(6);
            oplusQSFakeStatusController$$ExternalSyntheticLambda4.f$0 = oplusQSFakeStatusController;
            VarHandle.storeStoreFence();
            NotificationPanelChildAppearAnimationManager.hide$default(notificationPanelChildAppearAnimationManager4, frameLayout8, true, 0.0f, oplusQSFakeStatusController$$ExternalSyntheticLambda4, 56);
        } else {
            boolean zIsQsexpanded2 = oplusQSFakeStatusController.isQsexpanded();
            NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager5 = oplusQSFakeStatusController.appearAnimationManager;
            FrameLayout frameLayout9 = oplusQSFakeStatusController.fakeClockContainer;
            if (zIsQsexpanded2) {
                OplusQSFakeStatusController$$ExternalSyntheticLambda0 oplusQSFakeStatusController$$ExternalSyntheticLambda5 = new OplusQSFakeStatusController$$ExternalSyntheticLambda0(7);
                oplusQSFakeStatusController$$ExternalSyntheticLambda5.f$0 = oplusQSFakeStatusController;
                VarHandle.storeStoreFence();
                NotificationPanelChildAppearAnimationManager.hide$default(notificationPanelChildAppearAnimationManager5, frameLayout9, true, 0.0f, oplusQSFakeStatusController$$ExternalSyntheticLambda5, 56);
            } else {
                OplusQSFakeStatusController$$ExternalSyntheticLambda0 oplusQSFakeStatusController$$ExternalSyntheticLambda6 = new OplusQSFakeStatusController$$ExternalSyntheticLambda0(8);
                oplusQSFakeStatusController$$ExternalSyntheticLambda6.f$0 = oplusQSFakeStatusController;
                VarHandle.storeStoreFence();
                NotificationPanelChildAppearAnimationManager.hide$default(notificationPanelChildAppearAnimationManager5, frameLayout9, true, 0.0f, oplusQSFakeStatusController$$ExternalSyntheticLambda6, 56);
            }
        }
        FrameLayout frameLayout10 = oplusQSFakeStatusController.fakeNotificationIconContainer;
        if (frameLayout10 != null) {
            NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager6 = oplusQSFakeStatusController.appearAnimationManager;
            OplusQSFakeStatusController$$ExternalSyntheticLambda0 oplusQSFakeStatusController$$ExternalSyntheticLambda7 = new OplusQSFakeStatusController$$ExternalSyntheticLambda0(11);
            oplusQSFakeStatusController$$ExternalSyntheticLambda7.f$0 = frameLayout10;
            VarHandle.storeStoreFence();
            NotificationPanelChildAppearAnimationManager.hide$default(notificationPanelChildAppearAnimationManager6, frameLayout10, true, 0.0f, oplusQSFakeStatusController$$ExternalSyntheticLambda7, 52);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final void access$updateClockScale(OplusQSFakeStatusController oplusQSFakeStatusController, float f) {
        float fontScale = (((oplusQSFakeStatusController.getFontScale() + Math.min(Resources.getSystem().getConfiguration().fontScale, 1.6f)) - 1.0f) * f) + 1.0f;
        View clockView = oplusQSFakeStatusController.oplusQSFooterViewController.getClockView();
        if (clockView != null) {
            clockView.setScaleX(fontScale);
            clockView.setScaleY(fontScale);
        }
        FrameLayout frameLayout = oplusQSFakeStatusController.fakeClockContainer;
        if (frameLayout != null) {
            frameLayout.setScaleX(fontScale);
            frameLayout.setScaleY(fontScale);
        }
        float f2 = 1.0f / fontScale;
        float fM = DrawerArrowDrawable$.ExternalSyntheticOutline0.m(1.0f, f2, f, f2);
        AppCompatTextView appCompatTextView = oplusQSFakeStatusController.splitCarrier;
        if (appCompatTextView != null) {
            appCompatTextView.setScaleX(fM);
            appCompatTextView.setScaleY(fM);
        }
        ViewGroup mClockContainer = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
        if (mClockContainer != null) {
            mClockContainer.setScaleX(fM);
            mClockContainer.setScaleY(fM);
        }
    }

    private final float getFakeCarrierContainerTranslationX() {
        float dimension = getContext().getResources().getDimension(2131170982);
        if (DeviceUtil.isRtl()) {
            float fFloatValue = ((Number) getFakeCarrierView().getRealStatusElementParams().getFirst()).floatValue() + getFakeCarrierLocX();
            float x = this.notificationsQSContainerController.getQsFrame().getX();
            ViewGroup mClockContainer = this.statusBarHeader.getMClockContainer();
            float left = x + (mClockContainer != null ? mClockContainer.getLeft() : 0);
            AppCompatTextView mQsHeaderCarrierText = this.statusBarHeader.getMQsHeaderCarrierText();
            float left2 = left + (mQsHeaderCarrierText != null ? mQsHeaderCarrierText.getLeft() : 0);
            AppCompatTextView mQsHeaderCarrierText2 = this.statusBarHeader.getMQsHeaderCarrierText();
            return ((left2 + (mQsHeaderCarrierText2 != null ? mQsHeaderCarrierText2.getWidth() : 0)) - fFloatValue) + dimension;
        }
        float fakeCarrierLocX = getFakeCarrierLocX();
        float x2 = this.notificationsQSContainerController.getQsFrame().getX();
        ViewGroup mClockContainer2 = this.statusBarHeader.getMClockContainer();
        float left3 = x2 + (mClockContainer2 != null ? mClockContainer2.getLeft() : 0);
        AppCompatTextView mQsHeaderCarrierText3 = this.statusBarHeader.getMQsHeaderCarrierText();
        float left4 = left3 + (mQsHeaderCarrierText3 != null ? mQsHeaderCarrierText3.getLeft() : 0);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sb = new StringBuilder("OplusQSFakeStatusController-->");
            float x3 = this.notificationsQSContainerController.getQsFrame().getX();
            ViewGroup mClockContainer3 = this.statusBarHeader.getMClockContainer();
            int left5 = mClockContainer3 != null ? mClockContainer3.getLeft() : 0;
            AppCompatTextView mQsHeaderCarrierText4 = this.statusBarHeader.getMQsHeaderCarrierText();
            int left6 = mQsHeaderCarrierText4 != null ? mQsHeaderCarrierText4.getLeft() : 0;
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("qsCarrierRightLoc = ", left4, ", notificationsQSContainerController.getQsFrame().x = ", x3, ", (statusBarHeader.mClockContainer?.left ?: 0) = ");
            RectList$.ExternalSyntheticOutline0.m(left5, left6, ", (statusBarHeader.mQsHeaderCarrierText?.left ?: 0) = ", "carrierRightLoc = ", sbM);
            sbM.append(fakeCarrierLocX);
            sbM.append(" , ");
            sb.append(sbM.toString());
            Log.d("SystemUi--QuickSettings", sb.toString());
        }
        return (left4 - fakeCarrierLocX) - dimension;
    }

    private final float getFakeCarrierLocX() {
        return this.updateMonitor.isKeyguardVisible$1() ? this.fakeKeyguardCarriertext.getRealStatusElementLocX() : this.fakeOperator.getRealStatusElementLocX();
    }

    private final float getFakeCarrierLocY() {
        return this.updateMonitor.isKeyguardVisible$1() ? this.fakeKeyguardCarriertext.getRealStatusElementLocY() : this.fakeOperator.getRealStatusElementLocY();
    }

    private final StatusBarFakeFrame getFakeCarrierView() {
        return this.updateMonitor.isKeyguardVisible$1() ? this.fakeKeyguardCarriertext : this.fakeOperator;
    }

    private final float getFakeClockScaY() {
        OplusQSFooterViewController oplusQSFooterViewController = this.oplusQSFooterViewController;
        int iIntValue = ((Number) this.fakeClock.getRealStatusElementParams().getFirst()).intValue();
        int width = oplusQSFooterViewController.getClockView().getWidth();
        if (width == 0 || iIntValue == 0) {
            return 1.0f;
        }
        return width / iIntValue;
    }

    private final float getFakeClockTranslationX() {
        float dimension = getContext().getResources().getDimension(2131173051);
        OplusQSFooterViewController oplusQSFooterViewController = this.oplusQSFooterViewController;
        boolean zIsRtl = DeviceUtil.isRtl();
        StatusBarFakeFrame statusBarFakeFrame = this.fakeClock;
        if (!zIsRtl) {
            return (((oplusQSFooterViewController.getView().getX() + this.notificationsQSContainerController.getQsFrame().getX()) + oplusQSFooterViewController.getClockView().getLeft()) - statusBarFakeFrame.getRealStatusElementLocX()) - dimension;
        }
        return ((((oplusQSFooterViewController.getView().getX() + this.notificationsQSContainerController.getQsFrame().getX()) + oplusQSFooterViewController.getClockView().getLeft()) + oplusQSFooterViewController.getClockView().getWidth()) - (((Number) this.fakeClock.getRealStatusElementParams().getFirst()).floatValue() + statusBarFakeFrame.getRealStatusElementLocX())) + dimension;
    }

    private final float getFakeClockTranslationY() {
        OplusQSFooterViewController oplusQSFooterViewController = this.oplusQSFooterViewController;
        return (((oplusQSFooterViewController.getView().getY() + this.notificationsQSContainerController.getQsFrame().getY()) + (oplusQSFooterViewController.getClockView().getHeight() / 2)) + oplusQSFooterViewController.getClockView().getTop()) - (this.fakeClock.getRealStatusElementLocY() + (((Number) this.fakeClock.getRealStatusElementParams().getSecond()).intValue() / 2));
    }

    private final float getFakeNotifiIconTranslationX() {
        OplusQSFooterViewController oplusQSFooterViewController = this.oplusQSFooterViewController;
        if (!DeviceUtil.isRtl()) {
            return (this.notificationsQSContainerController.getQsFrame().getX() + (oplusQSFooterViewController.getView().getX() + oplusQSFooterViewController.getDateView().getLeft())) - this.fakeNotification.getRealStatusElementLocX();
        }
        return (this.notificationsQSContainerController.getQsFrame().getX() + ((oplusQSFooterViewController.getView().getX() + oplusQSFooterViewController.getDateView().getLeft()) + oplusQSFooterViewController.getDateView().getWidth())) - (((Number) this.fakeNotification.getRealStatusElementParams().getFirst()).floatValue() + this.fakeNotification.getRealStatusElementLocX());
    }

    private final float getFakeNotifiIconTranslationY() {
        OplusQSFooterViewController oplusQSFooterViewController = this.oplusQSFooterViewController;
        return (((oplusQSFooterViewController.getView().getY() + this.notificationsQSContainerController.getQsFrame().getY()) + (oplusQSFooterViewController.getDateView().getHeight() / 2)) + oplusQSFooterViewController.getDateView().getTop()) - (this.fakeClock.getRealStatusElementLocY() + (this.fakeClock.getHeight() / 2));
    }

    private final float getFakeStatusIconTranslationX() {
        if (DeviceUtil.isRtl()) {
            float realStatusIconLocX = getRealStatusIconLocX();
            float x = this.notificationsQSContainerController.getQsFrame().getX();
            ViewGroup mStatusIconsView = this.statusBarHeader.getMStatusIconsView();
            return (x + (mStatusIconsView != null ? mStatusIconsView.getLeft() : 0)) - realStatusIconLocX;
        }
        float fFloatValue = ((Number) getFakeStatusIconView().getRealStatusElementParams().getFirst()).floatValue() + getRealStatusIconLocX();
        float x2 = this.notificationsQSContainerController.getQsFrame().getX();
        ViewGroup mStatusIconsView2 = this.statusBarHeader.getMStatusIconsView();
        float left = x2 + (mStatusIconsView2 != null ? mStatusIconsView2.getLeft() : 0);
        ViewGroup mStatusIconsView3 = this.statusBarHeader.getMStatusIconsView();
        return (left + (mStatusIconsView3 != null ? mStatusIconsView3.getWidth() : 0)) - fFloatValue;
    }

    private final StatusBarFakeFrame getFakeStatusIconView() {
        return this.updateMonitor.isKeyguardVisible$1() ? this.fakeKeyguardSystemIcon : this.fakeSystemIcon;
    }

    private final float getFontScale() {
        return (OplusQSFontHelper.Companion.getInstance(getContext()).getAdapterTextSizeSp(2) / 13.0f) - 1.0f;
    }

    private final float getRealStatusIconLocX() {
        return this.updateMonitor.isKeyguardVisible$1() ? this.fakeKeyguardSystemIcon.getRealStatusElementLocX() : this.fakeSystemIcon.getRealStatusElementLocX();
    }

    private final float getRealStatusIconLocY() {
        return this.updateMonitor.isKeyguardVisible$1() ? this.fakeKeyguardSystemIcon.getRealStatusElementLocY() : this.fakeSystemIcon.getRealStatusElementLocY();
    }

    public static void setMarginEnd(View view, int i) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) (view != null ? view.getLayoutParams() : null);
        if (layoutParams.getMarginEnd() != i) {
            layoutParams.setMarginEnd(i);
            view.setLayoutParams(layoutParams);
        }
    }

    public static void setMarginStart(int i, View view) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) (view != null ? view.getLayoutParams() : null);
        if (layoutParams.getMarginStart() != i) {
            layoutParams.setMarginStart(i);
            view.setLayoutParams(layoutParams);
        }
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("OplusQSFakeStatusController: ");
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    default_setting_translationy: ", default_setting_translationy, printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    clockTranslationX: ", this.clockTranslationX, printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    notificationIconTranslationX: ", this.notificationIconTranslationX, printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    notificationIconTranslationY: ", this.notificationIconTranslationY, printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    statusIconTranslationX: ", this.statusIconTranslationX, printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    statusIconTranslationY: ", this.statusIconTranslationY, printWriter);
    }

    public /* bridge */ /* synthetic */ View getAnimatorView() {
        return super.getAnimatorView();
    }

    public int getCollapseHeight() {
        ConstraintLayout constraintLayout = (QsFakeLayout) this.mView;
        if (constraintLayout != null) {
            return constraintLayout.getMeasuredHeight();
        }
        return 0;
    }

    public /* bridge */ /* synthetic */ int getExpandHeight() {
        return super/*com.android.systemui.qs.ExpandableNode*/.getExpandHeight();
    }

    public /* bridge */ /* synthetic */ int getFloorCount() {
        return super.getFloorCount();
    }

    public View getView() {
        return this.mView;
    }

    public final boolean isQsexpanded() {
        return !LargeScreenUtils.shouldUseSplitNotificationShade(getResources()) && this.oplusQSFooterViewController.getExpanded();
    }

    public final boolean isTabletLandscape() {
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isTablet() && DeviceUtil.isLandscape(getContext());
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onInit() {
        ViewTreeObserver viewTreeObserver;
        this.fakeClockContainer = (FrameLayout) ((QsFakeLayout) this.mView).requireViewById(2131364747);
        this.fakeCarrierContainer = (FrameLayout) ((QsFakeLayout) this.mView).requireViewById(2131364746);
        this.keyguardFakeCarrierContainer = (FrameLayout) ((QsFakeLayout) this.mView).requireViewById(2131364776);
        this.fakeNotificationIconContainer = (FrameLayout) ((QsFakeLayout) this.mView).requireViewById(2131364750);
        this.fakeStatusIconContainer = (FrameLayout) ((QsFakeLayout) this.mView).requireViewById(2131364754);
        this.keyguardFakeStatusIconContainer = (FrameLayout) ((QsFakeLayout) this.mView).requireViewById(2131364777);
        this.fakePrivacyIconContainer = (FrameLayout) ((QsFakeLayout) this.mView).requireViewById(2131364751);
        this.fakeLeft = ((QsFakeLayout) this.mView).requireViewById(2131364749);
        this.fakeRight = ((QsFakeLayout) this.mView).requireViewById(2131364752);
        this.qsCarrierLeft = ((QsFakeLayout) this.mView).requireViewById(2131364715);
        ViewParent parent = this.fakeClock.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(this.fakeClock);
        }
        ViewParent parent2 = this.fakeNotification.getParent();
        ViewGroup viewGroup2 = parent2 instanceof ViewGroup ? (ViewGroup) parent2 : null;
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.fakeNotification);
        }
        ViewParent parent3 = this.fakePrivacy.getParent();
        ViewGroup viewGroup3 = parent3 instanceof ViewGroup ? (ViewGroup) parent3 : null;
        if (viewGroup3 != null) {
            viewGroup3.removeView(this.fakePrivacy);
        }
        ViewParent parent4 = this.fakeKeyguardCarriertext.getParent();
        ViewGroup viewGroup4 = parent4 instanceof ViewGroup ? (ViewGroup) parent4 : null;
        if (viewGroup4 != null) {
            viewGroup4.removeView(this.fakeKeyguardCarriertext);
        }
        ViewParent parent5 = this.fakeOperator.getParent();
        ViewGroup viewGroup5 = parent5 instanceof ViewGroup ? (ViewGroup) parent5 : null;
        if (viewGroup5 != null) {
            viewGroup5.removeView(this.fakeOperator);
        }
        ViewParent parent6 = this.fakeKeyguardSystemIcon.getParent();
        ViewGroup viewGroup6 = parent6 instanceof ViewGroup ? (ViewGroup) parent6 : null;
        if (viewGroup6 != null) {
            viewGroup6.removeView(this.fakeKeyguardSystemIcon);
        }
        ViewParent parent7 = this.fakeSystemIcon.getParent();
        ViewGroup viewGroup7 = parent7 instanceof ViewGroup ? (ViewGroup) parent7 : null;
        if (viewGroup7 != null) {
            viewGroup7.removeView(this.fakeSystemIcon);
        }
        FrameLayout frameLayout = this.fakeClockContainer;
        if (frameLayout != null) {
            frameLayout.addView(this.fakeClock);
        }
        FrameLayout frameLayout2 = this.fakeCarrierContainer;
        if (frameLayout2 != null) {
            frameLayout2.addView(this.fakeOperator);
        }
        FrameLayout frameLayout3 = this.keyguardFakeCarrierContainer;
        if (frameLayout3 != null) {
            frameLayout3.addView(this.fakeKeyguardCarriertext);
        }
        FrameLayout frameLayout4 = this.fakeNotificationIconContainer;
        if (frameLayout4 != null) {
            frameLayout4.addView(this.fakeNotification);
        }
        FrameLayout frameLayout5 = this.keyguardFakeStatusIconContainer;
        if (frameLayout5 != null) {
            frameLayout5.addView(this.fakeKeyguardSystemIcon);
        }
        FrameLayout frameLayout6 = this.fakeStatusIconContainer;
        if (frameLayout6 != null) {
            frameLayout6.addView(this.fakeSystemIcon);
        }
        FrameLayout frameLayout7 = this.fakePrivacyIconContainer;
        if (frameLayout7 != null) {
            frameLayout7.addView(this.fakePrivacy);
        }
        View clockView = this.oplusQSFooterViewController.getClockView();
        if (clockView != null && (viewTreeObserver = clockView.getViewTreeObserver()) != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this.clockViewGlobalLayoutListener);
        }
        this.rawFraction = this.panelAnimationEx.getPanelExpandFraction().getRawFraction();
        resetTransitionAlpha(this.panelAnimationEx.getPanelExpandFraction().getRawFraction());
    }

    public final void onOrientationChanged(int i) {
        if (isTabletLandscape()) {
            FrameLayout frameLayout = this.fakeClockContainer;
            if (frameLayout != null) {
                frameLayout.setScaleX(1.0f);
            }
            FrameLayout frameLayout2 = this.fakeClockContainer;
            if (frameLayout2 != null) {
                frameLayout2.setScaleY(1.0f);
            }
            FrameLayout frameLayout3 = this.fakeClockContainer;
            if (frameLayout3 != null) {
                frameLayout3.setTranslationX(0.0f);
            }
            FrameLayout frameLayout4 = this.fakeCarrierContainer;
            if (frameLayout4 != null) {
                frameLayout4.setTranslationX(0.0f);
            }
            FrameLayout frameLayout5 = this.fakeNotificationIconContainer;
            if (frameLayout5 != null) {
                frameLayout5.setTranslationX(0.0f);
            }
            FrameLayout frameLayout6 = this.fakeStatusIconContainer;
            if (frameLayout6 != null) {
                frameLayout6.setTranslationX(0.0f);
            }
            FrameLayout frameLayout7 = this.fakeStatusIconContainer;
            if (frameLayout7 != null) {
                frameLayout7.setTranslationY(0.0f);
            }
            FrameLayout frameLayout8 = this.fakePrivacyIconContainer;
            if (frameLayout8 != null) {
                frameLayout8.setTranslationX(0.0f);
            }
            FrameLayout frameLayout9 = this.fakePrivacyIconContainer;
            if (frameLayout9 != null) {
                frameLayout9.setTranslationY(0.0f);
            }
            FrameLayout frameLayout10 = this.keyguardFakeCarrierContainer;
            if (frameLayout10 != null) {
                frameLayout10.setTranslationX(0.0f);
            }
            FrameLayout frameLayout11 = this.keyguardFakeStatusIconContainer;
            if (frameLayout11 != null) {
                frameLayout11.setTranslationX(0.0f);
            }
            FrameLayout frameLayout12 = this.keyguardFakeStatusIconContainer;
            if (frameLayout12 != null) {
                frameLayout12.setTranslationY(0.0f);
            }
            ConstraintLayout constraintLayout = this.fakeLeft;
            if (constraintLayout != null) {
                constraintLayout.setTranslationY(0.0f);
            }
            ConstraintLayout constraintLayout2 = this.qsCarrierLeft;
            if (constraintLayout2 != null) {
                constraintLayout2.setTranslationY(0.0f);
            }
            ConstraintLayout constraintLayout3 = this.fakeRight;
            if (constraintLayout3 != null) {
                constraintLayout3.setTranslationY(0.0f);
            }
            View dateView = this.oplusQSFooterViewController.getDateView();
            if (dateView != null) {
                dateView.setTranslationX(0.0f);
            }
            View clockView = this.oplusQSFooterViewController.getClockView();
            if (clockView != null) {
                clockView.setTranslationX(0.0f);
            }
            ViewGroup mStatusIconsView = this.statusBarHeader.getMStatusIconsView();
            if (mStatusIconsView != null) {
                mStatusIconsView.setTranslationX(0.0f);
            }
            ViewGroup mStatusIconsView2 = this.statusBarHeader.getMStatusIconsView();
            if (mStatusIconsView2 != null) {
                mStatusIconsView2.setTranslationY(0.0f);
            }
            ViewGroup mClockContainer = this.statusBarHeader.getMClockContainer();
            if (mClockContainer != null) {
                mClockContainer.setTranslationX(0.0f);
            }
            ViewGroup mClockContainer2 = this.statusBarHeader.getMClockContainer();
            if (mClockContainer2 != null) {
                mClockContainer2.setTranslationY(0.0f);
            }
            ViewGroup viewGroup = this.quickStatus;
            if (viewGroup != null) {
                viewGroup.setTranslationX(0.0f);
            }
            ViewGroup viewGroup2 = this.quickStatus;
            if (viewGroup2 != null) {
                viewGroup2.setTranslationY(0.0f);
            }
        }
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewAttached() {
        this.configurationController.addCallback(this);
        this.panelAnimationEx.getTranslationYValue().addCallback(this.panelTranslationYChangeListener);
        this.statusBarStateController.addCallback(this.statusBarStateListener);
        this.panelAnimationEx.getElementShowState().addCallback(this.elementShowStateChangeListener);
        this.panelAnimationEx.getPanelExpandFraction().addCallback(this.qsPanelExpandFractionListener);
        this.notificationsQSContainerController.getQsFrame().addOnLayoutChangeListener(this.layoutChangedListener);
        ViewGroup mStatusIconsView = this.statusBarHeader.getMStatusIconsView();
        if (mStatusIconsView != null) {
            mStatusIconsView.addOnLayoutChangeListener(this.layoutChangedListener);
        }
        updateElementTranslation();
        QsFakeLayout qsFakeLayout = (QsFakeLayout) this.mView;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        qsFakeLayout.setConfigurationChangedListener(anonymousClass1);
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewDetached() {
        ViewTreeObserver viewTreeObserver;
        this.configurationController.removeCallback(this);
        this.panelAnimationEx.getTranslationYValue().removeCallback(this.panelTranslationYChangeListener);
        this.statusBarStateController.removeCallback(this.statusBarStateListener);
        this.panelAnimationEx.getElementShowState().removeCallback(this.elementShowStateChangeListener);
        this.panelAnimationEx.getPanelExpandFraction().removeCallback(this.qsPanelExpandFractionListener);
        this.notificationsQSContainerController.getQsFrame().removeOnLayoutChangeListener(this.layoutChangedListener);
        ViewGroup mStatusIconsView = this.statusBarHeader.getMStatusIconsView();
        if (mStatusIconsView != null) {
            mStatusIconsView.removeOnLayoutChangeListener(this.layoutChangedListener);
        }
        View clockView = this.oplusQSFooterViewController.getClockView();
        if (clockView == null || (viewTreeObserver = clockView.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.removeOnGlobalLayoutListener(this.clockViewGlobalLayoutListener);
    }

    public final void resetTransitionAlpha(float f) {
        if (this.updateMonitor.isKeyguardVisible$1()) {
            float f2 = this.panelAnimationEx.isElementShowing() ? 0.0f : 1.0f;
            this.fakeKeyguardSystemIcon.setTransitionAlpha(f2);
            FrameLayout frameLayout = this.keyguardFakeCarrierContainer;
            if (frameLayout != null) {
                frameLayout.setTransitionAlpha(f2);
                return;
            }
            return;
        }
        if (f == 0.0f) {
            FrameLayout frameLayout2 = this.fakeClockContainer;
            if (frameLayout2 != null) {
                frameLayout2.setTransitionAlpha(1.0f);
            }
            FrameLayout frameLayout3 = this.fakeNotificationIconContainer;
            if (frameLayout3 != null) {
                frameLayout3.setTransitionAlpha(1.0f);
            }
            FrameLayout frameLayout4 = this.fakePrivacyIconContainer;
            if (frameLayout4 != null) {
                frameLayout4.setTransitionAlpha(1.0f);
            }
            FrameLayout frameLayout5 = this.fakeStatusIconContainer;
            if (frameLayout5 != null) {
                frameLayout5.setTransitionAlpha(1.0f);
            }
            this.fakeKeyguardSystemIcon.setTransitionAlpha(1.0f);
            FrameLayout frameLayout6 = this.fakeCarrierContainer;
            if (frameLayout6 != null) {
                frameLayout6.setTransitionAlpha(1.0f);
            }
            this.header.setTransitionAlpha(0.0f);
            ViewGroup mClockContainer = this.statusBarHeader.getMClockContainer();
            if (mClockContainer != null) {
                mClockContainer.setTransitionAlpha(0.0f);
            }
            this.statusBarHeader.updateViewState(0.0f, 0.0f);
            this.oplusQSFooterViewController.updateViewState(0.0f, 0.0f);
            return;
        }
        if (f == 1.0f) {
            FrameLayout frameLayout7 = this.fakeClockContainer;
            if (frameLayout7 != null) {
                frameLayout7.setTransitionAlpha(0.0f);
            }
            FrameLayout frameLayout8 = this.fakeNotificationIconContainer;
            if (frameLayout8 != null) {
                frameLayout8.setTransitionAlpha(0.0f);
            }
            FrameLayout frameLayout9 = this.fakePrivacyIconContainer;
            if (frameLayout9 != null) {
                frameLayout9.setTransitionAlpha(0.0f);
            }
            FrameLayout frameLayout10 = this.fakeStatusIconContainer;
            if (frameLayout10 != null) {
                frameLayout10.setTransitionAlpha(0.0f);
            }
            this.fakeKeyguardSystemIcon.setTransitionAlpha(0.0f);
            FrameLayout frameLayout11 = this.fakeCarrierContainer;
            if (frameLayout11 != null) {
                frameLayout11.setTransitionAlpha(0.0f);
            }
            this.header.setTransitionAlpha(1.0f);
            ViewGroup mClockContainer2 = this.statusBarHeader.getMClockContainer();
            if (mClockContainer2 != null) {
                mClockContainer2.setTransitionAlpha(1.0f);
            }
            this.statusBarHeader.updateViewState(1.0f, 1.0f);
            this.oplusQSFooterViewController.updateViewState(1.0f, 1.0f);
        }
    }

    public final void reverseTranslationY(float f) {
        if (isTabletLandscape()) {
            FrameLayout frameLayout = this.keyguardFakeStatusIconContainer;
            if (frameLayout != null) {
                frameLayout.setTranslationY(-(default_setting_translationy + f));
            }
            FrameLayout frameLayout2 = this.fakeStatusIconContainer;
            if (frameLayout2 != null) {
                frameLayout2.setTranslationY(-(default_setting_translationy + f));
            }
            FrameLayout frameLayout3 = this.fakeClockContainer;
            if (frameLayout3 != null) {
                frameLayout3.setTranslationY(-(default_setting_translationy + f));
            }
            FrameLayout frameLayout4 = this.fakeNotificationIconContainer;
            if (frameLayout4 != null) {
                frameLayout4.setTranslationY(-(default_setting_translationy + f));
            }
        }
        FrameLayout frameLayout5 = this.fakeCarrierContainer;
        if (frameLayout5 != null) {
            frameLayout5.setTranslationY(-(default_setting_translationy + f));
        }
    }

    public /* bridge */ /* synthetic */ void setContainer(ExpandableNode.OnStateListener onStateListener) {
        super.setContainer(onStateListener);
    }

    public /* bridge */ /* synthetic */ void setCustomizing(boolean z) {
        super.setCustomizing(z);
    }

    public /* bridge */ /* synthetic */ void setExpanded(boolean z) {
        super.setExpanded(z);
    }

    public /* bridge */ /* synthetic */ void setExpansion(float f) {
        super.setExpansion(f);
    }

    public final void setLeftTranslationY(float f, float f2) {
        float fStatusIconsCenterY;
        if (isTabletLandscape()) {
            return;
        }
        OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController = this.statusBarHeader;
        float fLerp = (oplusQuickStatusBarHeaderController.mExpandTopGap * f2) + ((int) MathUtils.lerp(oplusQuickStatusBarHeaderController.getCollapseHeight(), oplusQuickStatusBarHeaderController.getExpandHeight(), f2));
        this.statusBarHeader.getClass();
        float f3 = fLerp + 0.0f + this.oplusQSFooterViewController.mGapTop;
        if (LargeScreenUtils.shouldUseSplitNotificationShade(getResources())) {
            fStatusIconsCenterY = (getRealStatusIconLocY() + (((Number) getFakeStatusIconView().getRealStatusElementParams().getSecond()).intValue() / 2)) - this.notificationsQSContainerController.getQsFrame().getY();
        } else if (this.updateMonitor.isKeyguardVisible$1()) {
            fStatusIconsCenterY = statusIconsCenterY() + (this.statusBarHeader.mExpandTopGap * f2);
        } else {
            fStatusIconsCenterY = statusIconsCenterY();
        }
        this.notificationIconTranslationY = (((this.oplusQSFooterViewController.getClockView().getHeight() / 2) + f3) + this.oplusQSFooterViewController.getClockView().getTop()) - fStatusIconsCenterY;
        this.differenceTranslationY = f - f3;
        if (this.updateMonitor.isKeyguardVisible$1() || isQsexpanded()) {
            return;
        }
        ConstraintLayout constraintLayout = this.fakeLeft;
        if (constraintLayout != null) {
            constraintLayout.setTranslationY((this.differenceTranslationY + this.notificationIconTranslationY) * this.rawFraction);
        }
        if (this.rawFraction == 1.0f) {
            return;
        }
        View dateView = this.oplusQSFooterViewController.getDateView();
        if (dateView != null) {
            dateView.setTranslationY((this.differenceTranslationY + this.notificationIconTranslationY) * (this.rawFraction - 1.0f));
        }
        View clockView = this.oplusQSFooterViewController.getClockView();
        if (clockView != null) {
            clockView.setTranslationY((this.differenceTranslationY + this.notificationIconTranslationY) * (this.rawFraction - 1.0f));
        }
        ViewGroup mClockContainer = this.statusBarHeader.getMClockContainer();
        if (mClockContainer != null) {
            mClockContainer.setTranslationY((this.differenceTranslationY + this.notificationIconTranslationY) * (this.rawFraction - 1.0f));
        }
    }

    public /* bridge */ /* synthetic */ void setListening(boolean z) {
        super.setListening(z);
    }

    public float setTranslationY(float f, int i, Function1 function1) {
        if (isTabletLandscape()) {
            this.statusBarHeader.getView().setTranslationY(f);
            return 0.0f;
        }
        if (isQsexpanded()) {
            this.qsExpandedY = this.statusBarHeader.mExpandTopGap * 1.0f;
        }
        if (!LargeScreenUtils.shouldUseSplitNotificationShade(getResources())) {
            ConstraintLayout constraintLayout = this.fakeRight;
            if (constraintLayout != null) {
                constraintLayout.setTranslationY(this.rawFraction * f);
            }
            ConstraintLayout constraintLayout2 = this.qsCarrierLeft;
            if (constraintLayout2 != null) {
                constraintLayout2.setTranslationY(this.rawFraction * f);
            }
            this.statusBarHeader.getView().setTranslationY(this.rawFraction * f);
        }
        return 0.0f;
    }

    public final void showOrHideCarrier(boolean z) {
        if ((this.updateMonitor.isKeyguardVisible$1() ? this.fakeKeyguardCarriertext.shouldAnimation() : this.fakeOperator.shouldAnimation()) || isTabletLandscape()) {
            if (z) {
                boolean zIsTabletLandscape = isTabletLandscape();
                NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager = this.appearAnimationManager;
                FrameLayout frameLayout = this.fakeCarrierContainer;
                if (zIsTabletLandscape) {
                    OplusQSFakeStatusController$$ExternalSyntheticLambda0 oplusQSFakeStatusController$$ExternalSyntheticLambda0 = new OplusQSFakeStatusController$$ExternalSyntheticLambda0(0);
                    oplusQSFakeStatusController$$ExternalSyntheticLambda0.f$0 = this;
                    VarHandle.storeStoreFence();
                    NotificationPanelChildAppearAnimationManager.hide$default(notificationPanelChildAppearAnimationManager, frameLayout, true, 0.0f, oplusQSFakeStatusController$$ExternalSyntheticLambda0, 56);
                    return;
                }
                OplusQSFakeStatusController$$ExternalSyntheticLambda0 oplusQSFakeStatusController$$ExternalSyntheticLambda1 = new OplusQSFakeStatusController$$ExternalSyntheticLambda0(3);
                oplusQSFakeStatusController$$ExternalSyntheticLambda1.f$0 = this;
                VarHandle.storeStoreFence();
                NotificationPanelChildAppearAnimationManager.hide$default(notificationPanelChildAppearAnimationManager, frameLayout, true, 0.0f, oplusQSFakeStatusController$$ExternalSyntheticLambda1, 56);
                return;
            }
            boolean zIsTabletLandscape2 = isTabletLandscape();
            NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager2 = this.appearAnimationManager;
            FrameLayout frameLayout2 = this.fakeCarrierContainer;
            if (zIsTabletLandscape2) {
                OplusQSFakeStatusController$$ExternalSyntheticLambda0 oplusQSFakeStatusController$$ExternalSyntheticLambda2 = new OplusQSFakeStatusController$$ExternalSyntheticLambda0(4);
                oplusQSFakeStatusController$$ExternalSyntheticLambda2.f$0 = this;
                VarHandle.storeStoreFence();
                ((NotificationPanelChildAppearAnimationManagerImpl) notificationPanelChildAppearAnimationManager2).show(frameLayout2, true, 0L, 0, (120 & 16) != 0 ? false : false, (120 & 32) != 0 ? 0.0f : 0.0f, new NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1(), oplusQSFakeStatusController$$ExternalSyntheticLambda2);
                return;
            }
            OplusQSFakeStatusController$$ExternalSyntheticLambda0 oplusQSFakeStatusController$$ExternalSyntheticLambda3 = new OplusQSFakeStatusController$$ExternalSyntheticLambda0(5);
            oplusQSFakeStatusController$$ExternalSyntheticLambda3.f$0 = this;
            VarHandle.storeStoreFence();
            ((NotificationPanelChildAppearAnimationManagerImpl) notificationPanelChildAppearAnimationManager2).show(frameLayout2, true, 0L, 0, (120 & 16) != 0 ? false : false, (120 & 32) != 0 ? 0.0f : 0.0f, new NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1(), oplusQSFakeStatusController$$ExternalSyntheticLambda3);
        }
    }

    public final float statusIconsCenterY() {
        ViewGroup mStatusIconsView = this.statusBarHeader.getMStatusIconsView();
        return ((mStatusIconsView != null ? mStatusIconsView.getHeight() : 0) / 2) + (mStatusIconsView != null ? mStatusIconsView.getTop() : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void updateElementTranslation() {
        float realStatusElementLocX;
        float x;
        float fakeCarrierLocX;
        float x2;
        float fakeCarrierLocX2;
        float x3;
        float x4;
        float fMin;
        float x5;
        float fMin2;
        float x6;
        float fFloatValue;
        float height;
        float y;
        float f;
        float baseline;
        float fFloatValue2;
        Float fValueOf = Float.valueOf(0.0f);
        boolean zIsKeyguardVisible$1 = this.updateMonitor.isKeyguardVisible$1();
        FrameLayout frameLayout = this.fakeClockContainer;
        if (zIsKeyguardVisible$1) {
            if (frameLayout != null) {
                frameLayout.setVisibility(4);
            }
            FrameLayout frameLayout2 = this.fakeCarrierContainer;
            if (frameLayout2 != null) {
                frameLayout2.setVisibility(4);
            }
            FrameLayout frameLayout3 = this.keyguardFakeCarrierContainer;
            if (frameLayout3 != null) {
                frameLayout3.setVisibility(0);
            }
            FrameLayout frameLayout4 = this.keyguardFakeStatusIconContainer;
            if (frameLayout4 != null) {
                frameLayout4.setVisibility(0);
            }
            FrameLayout frameLayout5 = this.fakeStatusIconContainer;
            if (frameLayout5 != null) {
                frameLayout5.setVisibility(4);
            }
        } else {
            if (frameLayout != null) {
                frameLayout.setVisibility(0);
            }
            FrameLayout frameLayout6 = this.fakeCarrierContainer;
            if (frameLayout6 != null) {
                frameLayout6.setVisibility(0);
            }
            FrameLayout frameLayout7 = this.keyguardFakeCarrierContainer;
            if (frameLayout7 != null) {
                frameLayout7.setVisibility(4);
            }
            FrameLayout frameLayout8 = this.keyguardFakeStatusIconContainer;
            if (frameLayout8 != null) {
                frameLayout8.setVisibility(4);
            }
            FrameLayout frameLayout9 = this.fakeStatusIconContainer;
            if (frameLayout9 != null) {
                frameLayout9.setVisibility(0);
            }
            resetTransitionAlpha(this.panelAnimationEx.getPanelExpandFraction().getRawFraction());
        }
        if (!isQsexpanded()) {
            this.oplusQSFooterViewController.getView().setScaleX(1.0f);
            this.oplusQSFooterViewController.getView().setScaleY(1.0f);
        }
        if (DeviceUtil.isRtl()) {
            realStatusElementLocX = (this.notificationsQSContainerController.getQsFrame().getX() + this.notificationsQSContainerController.getQsFrame().getWidth()) - this.fakeClock.getRealStatusElementLocX();
            x = ((Number) this.fakeClock.getRealStatusElementParams().getFirst()).floatValue();
        } else {
            realStatusElementLocX = (int) this.fakeClock.getRealStatusElementLocX();
            x = this.notificationsQSContainerController.getQsFrame().getX();
        }
        setMarginStart((int) (realStatusElementLocX - x), this.fakeClockContainer);
        if (DeviceUtil.isRtl()) {
            fakeCarrierLocX = (this.notificationsQSContainerController.getQsFrame().getX() + this.notificationsQSContainerController.getQsFrame().getWidth()) - getFakeCarrierLocX();
            x2 = ((Number) getFakeCarrierView().getRealStatusElementParams().getFirst()).floatValue();
        } else {
            fakeCarrierLocX = getFakeCarrierLocX();
            x2 = this.notificationsQSContainerController.getQsFrame().getX();
        }
        setMarginStart((int) (fakeCarrierLocX - x2), this.fakeCarrierContainer);
        if (DeviceUtil.isRtl()) {
            fakeCarrierLocX2 = (this.notificationsQSContainerController.getQsFrame().getX() + this.notificationsQSContainerController.getQsFrame().getWidth()) - getFakeCarrierLocX();
            x3 = ((Number) getFakeCarrierView().getRealStatusElementParams().getFirst()).floatValue();
        } else {
            fakeCarrierLocX2 = getFakeCarrierLocX();
            x3 = this.notificationsQSContainerController.getQsFrame().getX();
        }
        setMarginStart((int) (fakeCarrierLocX2 - x3), this.keyguardFakeCarrierContainer);
        setMarginStart(DeviceUtil.isRtl() ? (int) ((((this.notificationsQSContainerController.getQsFrame().getX() + this.notificationsQSContainerController.getQsFrame().getWidth()) - this.fakeNotification.getRealStatusElementLocX()) - ((Number) this.fakeNotification.getRealStatusElementParams().getFirst()).floatValue()) - this.fakeNotification.getOffsetX()) : ((int) (((int) this.fakeNotification.getRealStatusElementLocX()) - this.notificationsQSContainerController.getQsFrame().getX())) - this.fakeNotification.getOffsetX(), this.fakeNotificationIconContainer);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            float x7 = this.notificationsQSContainerController.getQsFrame().getX();
            int width = this.notificationsQSContainerController.getQsFrame().getWidth();
            float realStatusElementLocX2 = this.fakeNotification.getRealStatusElementLocX();
            Object first = this.fakeNotification.getRealStatusElementParams().getFirst();
            StringBuilder sbM = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(x7, width, "qsFrameX = ", "qsFrameW = ", "fakeNotificationIconX = ");
            sbM.append(realStatusElementLocX2);
            sbM.append("fakeNotificationIconW = ");
            sbM.append(first);
            Log.d("SystemUi--QuickSettings", "OplusQSFakeStatusController-->".concat(sbM.toString()));
        }
        if (DeviceUtil.isRtl()) {
            x4 = (int) getRealStatusIconLocX();
            fMin = this.notificationsQSContainerController.getQsFrame().getX();
        } else {
            x4 = (this.notificationsQSContainerController.getQsFrame().getX() + this.notificationsQSContainerController.getQsFrame().getWidth()) - getRealStatusIconLocX();
            fMin = Math.min(((Number) getFakeStatusIconView().getRealStatusElementParams().getFirst()).intValue(), this.notificationsQSContainerController.getQsFrame().getWidth());
        }
        setMarginEnd(this.fakeStatusIconContainer, (int) (x4 - fMin));
        if (DeviceUtil.isRtl()) {
            x5 = (int) getRealStatusIconLocX();
            fMin2 = this.notificationsQSContainerController.getQsFrame().getX();
        } else {
            x5 = (this.notificationsQSContainerController.getQsFrame().getX() + this.notificationsQSContainerController.getQsFrame().getWidth()) - getRealStatusIconLocX();
            fMin2 = Math.min(((Number) getFakeStatusIconView().getRealStatusElementParams().getFirst()).intValue(), this.notificationsQSContainerController.getQsFrame().getWidth());
        }
        setMarginEnd(this.keyguardFakeStatusIconContainer, (int) (x5 - fMin2));
        if (DeviceUtil.isRtl()) {
            x6 = this.fakePrivacy.getRealStatusElementLocX();
            fFloatValue = this.notificationsQSContainerController.getQsFrame().getX();
        } else {
            x6 = (this.notificationsQSContainerController.getQsFrame().getX() + this.notificationsQSContainerController.getQsFrame().getWidth()) - this.fakePrivacy.getRealStatusElementLocX();
            fFloatValue = ((Number) this.fakePrivacy.getRealStatusElementParams().getFirst()).floatValue();
        }
        setMarginEnd(this.fakePrivacyIconContainer, (int) (x6 - fFloatValue));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            float x8 = this.notificationsQSContainerController.getQsFrame().getX();
            int width2 = this.notificationsQSContainerController.getQsFrame().getWidth();
            float realStatusIconLocX = getRealStatusIconLocX();
            Object first2 = getFakeStatusIconView().getRealStatusElementParams().getFirst();
            StringBuilder sbM2 = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(x8, width2, "qsFrameX = ", "qsFrameW = ", "fakeStatusIconX = ");
            sbM2.append(realStatusIconLocX);
            sbM2.append("fakeStatusIconW = ");
            sbM2.append(first2);
            Log.d("SystemUi--QuickSettings", "OplusQSFakeStatusController-->".concat(sbM2.toString()));
        }
        float realStatusIconLocY = LargeScreenUtils.shouldUseSplitNotificationShade(getResources()) ? (getRealStatusIconLocY() + (((Number) getFakeStatusIconView().getRealStatusElementParams().getSecond()).intValue() / 2)) - this.notificationsQSContainerController.getQsFrame().getY() : statusIconsCenterY();
        if (LargeScreenUtils.shouldUseSplitNotificationShade(getResources())) {
            height = (getRealStatusIconLocY() + (((Number) getFakeStatusIconView().getRealStatusElementParams().getSecond()).intValue() / 2)) - this.notificationsQSContainerController.getQsFrame().getY();
        } else {
            ViewGroup mClockContainer = this.statusBarHeader.getMClockContainer();
            height = ((mClockContainer != null ? mClockContainer.getHeight() : 0) / 2) + this.statusBarHeader.getView().getTop() + (mClockContainer != null ? mClockContainer.getTop() : 0);
        }
        float height2 = realStatusIconLocY - (((QsFakeLayout) this.mView).getHeight() / 2);
        ConstraintLayout constraintLayout = (QsFakeLayout) this.mView;
        ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.topMargin = (int) height2;
        }
        constraintLayout.setLayoutParams(constraintLayout.getLayoutParams());
        boolean zIsKeyguardVisible$2 = this.updateMonitor.isKeyguardVisible$1();
        NotificationsQSContainerController notificationsQSContainerController = this.notificationsQSContainerController;
        if (zIsKeyguardVisible$2) {
            float topGap = this.statusBarHeader.getTopGap() + ((notificationsQSContainerController.getQsFrame().getY() + realStatusIconLocY) - (getRealStatusIconLocY() + (((Number) getFakeStatusIconView().getRealStatusElementParams().getSecond()).intValue() / 2)));
            y = topGap != 0.0f ? topGap : 1.0f;
            if (default_setting_translationy != y) {
                default_setting_translationy = y;
                this.panelAnimationEx.updateKeyguardTranslationY();
            }
        } else {
            y = (notificationsQSContainerController.getQsFrame().getY() + realStatusIconLocY) - (getRealStatusIconLocY() + (((Number) getFakeStatusIconView().getRealStatusElementParams().getSecond()).intValue() / 2));
        }
        default_setting_translationy = y;
        this.panelAnimationEx.setKeyguardMiniChange(this.statusBarHeader.getTopGap() == 0.0f);
        this.carrierContainerTranslationY = height - realStatusIconLocY;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            float realStatusIconLocY2 = getRealStatusIconLocY();
            int height3 = getFakeStatusIconView().getHeight();
            Object second = getFakeStatusIconView().getRealStatusElementParams().getSecond();
            float y2 = this.notificationsQSContainerController.getQsFrame().getY();
            float f2 = default_setting_translationy;
            float f3 = this.carrierContainerTranslationY;
            float fFloatValue3 = ((Number) getFakeCarrierView().getRealStatusElementParams().getSecond()).floatValue() + getFakeCarrierLocY();
            float f4 = this.carrierContainerTranslationY;
            StringBuilder sbM3 = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(realStatusIconLocY2, height3, "realY = ", ", realH = ", ",realHsecond = ");
            sbM3.append(second);
            sbM3.append("qsFrameY = ");
            sbM3.append(y2);
            sbM3.append("statusBarHeaderh = ");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM3, realStatusIconLocY, " topMargin = ", height2, " default_setting_translationy = ");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM3, f2, "carrierContainerTranslationY = ", f3, "carrierHeaderh = ");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM3, height, "carrfakeh = ", fFloatValue3, "carrierContainerTranslationY = ");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM3, f4, "OplusQSFakeStatusController-->", "SystemUi--QuickSettings");
        }
        FrameLayout frameLayout10 = this.fakeClockContainer;
        float height4 = frameLayout10 != null ? frameLayout10.getHeight() : 0.0f;
        View clockView = this.oplusQSFooterViewController.getClockView();
        float height5 = (height4 - (clockView != null ? clockView.getHeight() : 0.0f)) / 2.0f;
        View clockView2 = this.oplusQSFooterViewController.getClockView();
        if (clockView2 != null) {
            clockView2.setPivotX(DeviceUtil.isRtl() ? clockView2.getWidth() : 0.0f);
            f = this.clockBaseLine;
            clockView2.setPivotY(f);
        } else {
            f = 0.0f;
        }
        FrameLayout frameLayout11 = this.fakeClockContainer;
        if (frameLayout11 != null) {
            float dimension = getContext().getResources().getDimension(2131173051);
            if (DeviceUtil.isRtl()) {
                dimension = frameLayout11.getWidth() - dimension;
            }
            frameLayout11.setPivotX(dimension);
            frameLayout11.setPivotY(f + height5);
        }
        AppCompatTextView mQsHeaderCarrierText = this.statusBarHeader.getMQsHeaderCarrierText();
        if (mQsHeaderCarrierText != null) {
            mQsHeaderCarrierText.setPivotX(DeviceUtil.isRtl() ? mQsHeaderCarrierText.getWidth() : 0.0f);
            baseline = mQsHeaderCarrierText.getBaseline();
            mQsHeaderCarrierText.setPivotY(baseline);
        } else {
            baseline = 0.0f;
        }
        FrameLayout frameLayout12 = this.keyguardFakeCarrierContainer;
        float height6 = frameLayout12 != null ? frameLayout12.getHeight() : 0.0f;
        AppCompatTextView mQsHeaderCarrierText2 = this.statusBarHeader.getMQsHeaderCarrierText();
        float height7 = (height6 - (mQsHeaderCarrierText2 != null ? mQsHeaderCarrierText2.getHeight() : 0.0f)) / 2.0f;
        FrameLayout frameLayout13 = this.keyguardFakeCarrierContainer;
        if (frameLayout13 != null) {
            float dimension2 = getContext().getResources().getDimension(2131170982);
            if (DeviceUtil.isRtl()) {
                dimension2 = frameLayout13.getWidth() - dimension2;
            }
            frameLayout13.setPivotX(dimension2);
            frameLayout13.setPivotY(baseline + height7);
        }
        AppCompatTextView appCompatTextView = this.splitCarrier;
        if (appCompatTextView != null) {
            appCompatTextView.setPivotX(DeviceUtil.isRtl() ? appCompatTextView.getWidth() : 0.0f);
            appCompatTextView.setPivotY(appCompatTextView.getHeight());
        }
        ViewGroup mClockContainer2 = this.statusBarHeader.getMClockContainer();
        if (mClockContainer2 != null) {
            mClockContainer2.setPivotX(DeviceUtil.isRtl() ? mClockContainer2.getWidth() : 0.0f);
            mClockContainer2.setPivotY(mClockContainer2.getHeight());
        }
        getFakeClockScaY();
        this.oplusQSFooterViewController.getClockView().getWidth();
        this.clockTranslationX = getFakeClockTranslationX();
        this.notificationIconTranslationX = getFakeNotifiIconTranslationX();
        this.carrierContainerTranslationX = getFakeCarrierContainerTranslationX();
        this.oplusQSFooterViewController.getClockView().getWidth();
        ViewGroup viewGroup = (ViewGroup) this.header;
        int left = ((ViewGroup) viewGroup.requireViewById(2131364908)).getLeft();
        this.quickStatus = (ViewGroup) viewGroup.requireViewById(2131364818);
        this.splitCarrier = (OplusSecondCarrierText) viewGroup.requireViewById(2131364716);
        if (LargeScreenUtils.shouldUseSplitNotificationShade(getResources())) {
            ConstraintLayout constraintLayout2 = this.fakeRight;
            if (constraintLayout2 != null) {
                constraintLayout2.setTranslationY(0.0f);
            }
            ConstraintLayout constraintLayout3 = this.qsCarrierLeft;
            if (constraintLayout3 != null) {
                constraintLayout3.setTranslationY(0.0f);
            }
            if (DeviceUtil.isRtl()) {
                float left2 = viewGroup.getLeft() + left;
                ViewGroup viewGroup2 = this.quickStatus;
                fFloatValue2 = ((viewGroup2 != null ? Integer.valueOf(viewGroup2.getLeft()) : fValueOf).floatValue() + left2) - getRealStatusIconLocX();
            } else {
                float fFloatValue4 = ((Number) getFakeStatusIconView().getRealStatusElementParams().getFirst()).floatValue() + getRealStatusIconLocX();
                float f5 = left;
                ViewGroup viewGroup3 = this.quickStatus;
                float fFloatValue5 = (viewGroup3 != null ? Integer.valueOf(viewGroup3.getLeft()) : fValueOf).floatValue() + f5;
                ViewGroup viewGroup4 = this.quickStatus;
                fFloatValue2 = ((viewGroup4 != null ? Integer.valueOf(viewGroup4.getWidth()) : fValueOf).floatValue() + fFloatValue5) - fFloatValue4;
            }
            this.statusIconTranslationX = fFloatValue2;
            float realStatusIconLocY3 = getRealStatusIconLocY() + (((Number) getFakeStatusIconView().getRealStatusElementParams().getSecond()).intValue() / 2);
            ViewGroup viewGroup5 = this.quickStatus;
            float fFloatValue6 = (viewGroup5 != null ? Integer.valueOf(viewGroup5.getTop()) : fValueOf).floatValue();
            ViewGroup viewGroup6 = this.quickStatus;
            Float fValueOf2 = fValueOf;
            if (viewGroup6 != null) {
                fValueOf2 = Integer.valueOf(viewGroup6.getHeight());
            }
            this.statusIconTranslationY = ((fValueOf2.floatValue() / 2.0f) + fFloatValue6) - realStatusIconLocY3;
        } else {
            this.statusIconTranslationX = getFakeStatusIconTranslationX();
            this.statusIconTranslationY = 0.0f;
        }
        this.isFakeClockGone = this.fakeClock.getRealStatusElementLocX() == this.fakeNotification.getRealStatusElementLocX();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            boolean z = this.isFakeClockGone;
            float f6 = this.clockTranslationX;
            float f7 = this.notificationIconTranslationX;
            float f8 = this.notificationIconTranslationY;
            float f9 = this.statusIconTranslationX;
            float f10 = this.statusIconTranslationY;
            float f11 = this.carrierContainerTranslationX;
            StringBuilder sbM4 = NotificationPanelViewController$.ExternalSyntheticOutline0.m("isFakeClockGone = ", z, ", clockTranslationX = ", f6, ", notificationIconTranslationX = ");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM4, f7, ", notificationIconTranslationY = ", f8, "statusIconTranslationX = ");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM4, f9, " , statusIconTranslationY = ", f10, "carrierContainerTranslationX = ");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM4, f11, "OplusQSFakeStatusController-->", "SystemUi--QuickSettings");
        }
    }

    public final void updateViewState(float f, float f2) {
        if (isQsexpanded()) {
            if (this.updateMonitor.isKeyguardVisible$1() || this.panelAnimationEx.getPanelExpandFraction().getRawFraction() != 1.0f) {
                this.oplusQSFooterViewController.getView().setScaleX(f2);
                this.oplusQSFooterViewController.getView().setScaleY(f2);
                this.oplusQSFooterViewController.updateViewState(f, f2);
            } else {
                this.oplusQSFooterViewController.getView().setScaleX(1.0f);
                this.oplusQSFooterViewController.getView().setScaleY(1.0f);
                this.oplusQSFooterViewController.updateViewState(1.0f, 1.0f);
            }
        }
    }
}
