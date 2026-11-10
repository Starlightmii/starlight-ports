package com.oplus.systemui.statusbar.phone;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import android.util.MathUtils;
import android.util.Pair;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.internal.colorextraction.ColorExtractor;
import com.android.internal.graphics.ColorUtils;
import com.android.keyguard.BouncerPanelExpansionCalculator;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.ShadeInterpolation;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.broadcast.ActionReceiver$;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.shared.model.ScrimAlpha;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.android.systemui.scrim.ScrimView;
import com.android.systemui.scrim.ScrimViewEx;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationShadeWindowViewController;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.shade.PanelType;
import com.android.systemui.statusbar.OlockStateUtil;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.android.systemui.statusbar.phone.ScrimState;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.charge.viewmodel.OplusChargeAnimController$;
import com.oplus.keyguard.utils.KeyguardRapidUnlockUtils;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.keyguard.utils.OplusSwitchUserUtil;
import com.oplus.systemui.aod.aodclock.constant.AodData;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.aod.scene.PanoramicAodGestureController;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.keyguard.anim.transition.OplusKgdTransitionAnimationControllerImpl;
import com.oplus.systemui.keyguard.clockstyle.KeyguardStyleClockControllerImpl;
import com.oplus.systemui.keyguard.ui.interactor.OplusKgdMaskInteractor;
import com.oplus.systemui.navigationbar.observer.SystemFoldModeObserver;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.scrim.NotificationBarDrawable;
import com.oplus.systemui.statusbar.notification.util.OpNotificationUtils;
import com.oplus.systemui.wallpaperblur.WallpaperBlurDrawable;
import com.oplus.systemui.wallpaperblur.WallpaperBlurManager;
import com.oplus.zoom.common.ZoomParameterHelper;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.DebugTool;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.WindowBlurConfig;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.blurability.drawable.MaskDrawable;
import com.oplusos.systemui.common.blurability.screenshot.OplusScreenshotHelper;
import com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.NotifiAndQsPlatformBlurExKt;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScrimControllerExImp extends ScrimControllerEx implements IObserverListener, ConfigurationController.ConfigurationListener {
    private static final String TAG = "ScrimControllerExImp";
    public static StaticBlurManager frontScrimBlurManager;
    public static StaticBlurManager notifiAndQsBlurManager;
    private final Context context;
    private final CoroutineScope coroutineScope;
    private final ScrimControllerExImp$deviceStateListener$1 deviceStateListener;
    public boolean hasHandleUnlockState;
    private final Lazy immersiveCardControl;
    private final kotlin.Lazy isBAnimEnableBouncerWallpaperBlur$delegate;
    public boolean isEditMode;
    public boolean isEnteringBouncer;
    public boolean isStartUnlockHintAnimationRunning;
    private final Lazy keyguardStateController;
    private final Lazy keyguardViewMediator;
    private final Lazy kgdTransitionAnimationController;
    private final LogBuffer logBuffer;
    private final Lazy notificationShadeWindowViewControllerLazy;
    private final ScrimControllerExImp$olockStateCallback$1 olockStateCallback;
    private final Lazy oplusKgdMaskInteractor;
    private final OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionController;
    private final PanelAnimationEx panelAnimationEx;
    private final Lazy panelViewControllerLazy;
    private final Lazy platformBlurManager;
    public boolean runnerUnlock;
    private final Lazy screenOffAnimationController;
    public float scrimBehindAlphaForCustomBlur;
    private final Lazy scrimBlurStateUtils;
    private final ScrimGlobalThemeTransitionHelper scrimTransitionHelper;
    private final Lazy separateNotificationAndQSEx;
    private final SysuiStatusBarStateController statusBarStateController;
    private final Lazy switchUserUtilLazy;
    private final ViewBlurManager viewBlurManager;
    private final Lazy wallpaperBlurManager;
    public static final Companion Companion = new Companion();
    public static boolean isBouncerPlatformBlurAllEnabled = SystemProperties.getBoolean("debug.lockscreen.blur.all.platform", true);
    public final boolean oplusLogic = true;
    public float scrimAlpha = -1.0f;
    public float lastCustomBlurRatio = -1.0f;
    private ScrimControllerEx.PanelScrimParams qsPanelScrimParams = new ScrimControllerEx.PanelScrimParams(PanelType.QS_PANEL, 0.0f, 0);
    private ScrimControllerEx.PanelScrimParams notificationPanelScrimParams = new ScrimControllerEx.PanelScrimParams(PanelType.NOTIFICATION_PANEL, 0.0f, 0);

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.ScrimControllerExImp$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Dumpable {
        public /* synthetic */ ScrimControllerExImp this$0;

        public final void dump(PrintWriter printWriter, String[] strArr) {
            this.this$0.getLogBuffer().dump(printWriter, 0);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final StaticBlurManager getNotifiAndQsBlurManager(Context context) {
            if (ScrimControllerExImp.notifiAndQsBlurManager == null) {
                synchronized (StaticBlurManager.Companion) {
                    if (ScrimControllerExImp.notifiAndQsBlurManager == null) {
                        ScrimControllerExImp.notifiAndQsBlurManager = new StaticBlurManager(context.getApplicationContext(), new StaticBlurManager.StaticBlurParams(0.05f, 540));
                    }
                }
            }
            StaticBlurManager staticBlurManager = ScrimControllerExImp.notifiAndQsBlurManager;
            if (staticBlurManager == null) {
                return null;
            }
            return staticBlurManager;
        }

        public final StaticBlurManager getPwdPanelScrimBlurManager(Context context) {
            if (ScrimControllerExImp.frontScrimBlurManager == null) {
                synchronized (StaticBlurManager.Companion) {
                    if (ScrimControllerExImp.frontScrimBlurManager == null) {
                        ScrimControllerExImp.frontScrimBlurManager = new StaticBlurManager(context.getApplicationContext(), new StaticBlurManager.StaticBlurParams(0.05f, 540));
                    }
                }
            }
            StaticBlurManager staticBlurManager = ScrimControllerExImp.frontScrimBlurManager;
            if (staticBlurManager == null) {
                return null;
            }
            return staticBlurManager;
        }

        public final void setBouncerPlatformBlurAllEnabled(boolean z) {
            ScrimControllerExImp.isBouncerPlatformBlurAllEnabled = z;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.ScrimControllerExImp$bindController$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02991 extends SuspendLambda implements Function2 {
        int label;

        public C02991(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return ScrimControllerExImp.this.new C02991(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ScrimControllerExImp scrimControllerExImp = ScrimControllerExImp.this;
                this.label = 1;
                if (ScrimControllerExImp.access$collectUseMotionBlur(scrimControllerExImp, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.ScrimControllerExImp$doBrightnessFade$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {
        public /* synthetic */ float $alphaEnd;
        public /* synthetic */ float $alphaStart;
        public /* synthetic */ ValueAnimator $anim;
        public /* synthetic */ float $fractionEnd;
        public /* synthetic */ float $fractionStart;
        public /* synthetic */ int $initialScrimTint;
        public /* synthetic */ ScrimView $scrim;
        public /* synthetic */ boolean $syncBlurFraction;
        public /* synthetic */ ScrimControllerExImp this$0;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            float fLerp = MathUtils.lerp(this.$alphaStart, this.$alphaEnd, fFloatValue);
            ScrimControllerExImp scrimControllerExImp = this.this$0;
            Companion companion = ScrimControllerExImp.Companion;
            this.this$0.getScrimController().updateScrimColor(this.$scrim, this.$initialScrimTint, ScrimControllerExImp.isNeedScrimFade(this.this$0.getScrimController().getPanelExpansionFraction(), scrimControllerExImp.getScrimController().getQsBottomVisible()) ? MathUtils.constrain(fLerp, 0.0f, 1.0f) : 0.0f);
            if (this.$scrim == this.this$0.getScrimController().getScrimBehind()) {
                NotificationFeatureOption.INSTANCE.getClass();
                if (!NotificationFeatureOption.isPanViewBlurDisabled) {
                    float fConstrain = ScrimControllerExImp.isNeedScrimFade(this.this$0.getScrimController().getPanelExpansionFraction(), this.this$0.getScrimController().getQsBottomVisible()) ? MathUtils.constrain(MathUtils.lerp(this.$fractionStart, this.$fractionEnd, fFloatValue), 0.0f, 1.0f) : 0.0f;
                    this.$scrim.getExt().setScaleAmount(fConstrain, "doBrightnessFade");
                    this.$scrim.getExt().setBlurAmount(fConstrain, "doBrightnessFade");
                    if (this.$syncBlurFraction) {
                        if (((OplusSeparateNotificationAndQSEx) this.this$0.separateNotificationAndQSEx.get()).isBlurFractionAnimationRunning()) {
                            LogUtil.i$default(LogUtil.TAG_STATUSBAR, ScrimControllerExImp.TAG, "blurFraction anim running, cancel brightness fade", null, 8);
                            this.this$0.cancelBrightnessFadeAnimation("isBlurFractionAnimationRunning");
                            this.$anim.cancel();
                            return;
                        }
                        ((OplusSeparateNotificationAndQSEx) this.this$0.separateNotificationAndQSEx.get()).syncBlurFractionFromExternal(fConstrain);
                    }
                }
            }
            if (ScrimControllerExImp.isNeedScrimFade(this.this$0.getScrimController().getPanelExpansionFraction(), this.this$0.getScrimController().getQsBottomVisible())) {
                return;
            }
            this.$anim.cancel();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.ScrimControllerExImp$handleGaussBlur$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements Choreographer.FrameCallback {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object $scrimFrontProxy;

        public /* synthetic */ AnonymousClass4(int i) {
            this.$r8$classId = i;
        }

        @Override // android.view.Choreographer.FrameCallback
        public final void doFrame(long j) {
            switch (this.$r8$classId) {
                case 0:
                    ViewBlurProxy viewBlurProxy = (ViewBlurProxy) this.$scrimFrontProxy;
                    if (viewBlurProxy != null) {
                        viewBlurProxy.setNeedSyncWindowBlur(false);
                    }
                    break;
                default:
                    ((ScrimControllerExImp$handleGaussBlur$runnable$1) this.$scrimFrontProxy).run();
                    break;
            }
        }
    }

    public ScrimControllerExImp(Context context, SysuiColorExtractor sysuiColorExtractor, ViewBlurManager viewBlurManager, Lazy lazy, Lazy lazy2, PanelAnimationEx panelAnimationEx, SysuiStatusBarStateController sysuiStatusBarStateController, ConfigurationController configurationController, LogBuffer logBuffer, DumpManager dumpManager, Lazy lazy3, Lazy lazy4, CoroutineScope coroutineScope, OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx, Lazy lazy5, Lazy lazy6, Lazy lazy7, ScrimGlobalThemeTransitionHelper scrimGlobalThemeTransitionHelper, Lazy lazy8, Lazy lazy9, Lazy lazy10, Lazy lazy11, Lazy lazy12, Lazy lazy13) {
        this.context = context;
        this.viewBlurManager = viewBlurManager;
        this.keyguardStateController = lazy;
        this.screenOffAnimationController = lazy2;
        this.panelAnimationEx = panelAnimationEx;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.logBuffer = logBuffer;
        this.separateNotificationAndQSEx = lazy3;
        this.platformBlurManager = lazy4;
        this.coroutineScope = coroutineScope;
        this.oplusLockscreenShadeTransitionController = oplusLockscreenShadeTransitionControllerEx;
        this.switchUserUtilLazy = lazy5;
        this.keyguardViewMediator = lazy6;
        this.oplusKgdMaskInteractor = lazy7;
        this.scrimTransitionHelper = scrimGlobalThemeTransitionHelper;
        this.notificationShadeWindowViewControllerLazy = lazy8;
        this.panelViewControllerLazy = lazy9;
        this.scrimBlurStateUtils = lazy10;
        this.wallpaperBlurManager = lazy11;
        this.immersiveCardControl = lazy12;
        this.kgdTransitionAnimationController = lazy13;
        ScrimControllerExImp$deviceStateListener$1 scrimControllerExImp$deviceStateListener$1 = new ScrimControllerExImp$deviceStateListener$1();
        this.deviceStateListener = scrimControllerExImp$deviceStateListener$1;
        ScrimControllerExImp$olockStateCallback$1 scrimControllerExImp$olockStateCallback$1 = new ScrimControllerExImp$olockStateCallback$1();
        scrimControllerExImp$olockStateCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.olockStateCallback = scrimControllerExImp$olockStateCallback$1;
        this.isBAnimEnableBouncerWallpaperBlur$delegate = LazyKt__LazyJVMKt.lazy(new ScrimControllerExImp$$ExternalSyntheticLambda0(0));
        setColorExtractor(sysuiColorExtractor);
        SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(this);
        configurationController.addCallback(this);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        dumpManager.registerNormalDumpable("OplusBlurLog", anonymousClass1);
        DeviceStateController.getInstance().addCallback(scrimControllerExImp$deviceStateListener$1);
        OlockStateUtil.registerCallback(scrimControllerExImp$olockStateCallback$1);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$collectUseMotionBlur(ScrimControllerExImp scrimControllerExImp, ContinuationImpl continuationImpl) {
        ScrimControllerExImp$collectUseMotionBlur$1 scrimControllerExImp$collectUseMotionBlur$1;
        scrimControllerExImp.getClass();
        if (continuationImpl instanceof ScrimControllerExImp$collectUseMotionBlur$1) {
            scrimControllerExImp$collectUseMotionBlur$1 = (ScrimControllerExImp$collectUseMotionBlur$1) continuationImpl;
            int i = scrimControllerExImp$collectUseMotionBlur$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                scrimControllerExImp$collectUseMotionBlur$1.label = i - Integer.MIN_VALUE;
            } else {
                scrimControllerExImp$collectUseMotionBlur$1 = new ScrimControllerExImp$collectUseMotionBlur$1(scrimControllerExImp, continuationImpl);
            }
        } else {
            scrimControllerExImp$collectUseMotionBlur$1 = new ScrimControllerExImp$collectUseMotionBlur$1(scrimControllerExImp, continuationImpl);
        }
        Object obj = scrimControllerExImp$collectUseMotionBlur$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = scrimControllerExImp$collectUseMotionBlur$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            StateFlow useMotionBlur = ((PlatformBlurManager) scrimControllerExImp.platformBlurManager.get()).getUseMotionBlur();
            ScrimControllerExImp$collectUseMotionBlur$2 scrimControllerExImp$collectUseMotionBlur$2 = new ScrimControllerExImp$collectUseMotionBlur$2(0);
            scrimControllerExImp$collectUseMotionBlur$2.this$0 = scrimControllerExImp;
            VarHandle.storeStoreFence();
            scrimControllerExImp$collectUseMotionBlur$1.label = 1;
            if (useMotionBlur.collect(scrimControllerExImp$collectUseMotionBlur$2, scrimControllerExImp$collectUseMotionBlur$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        Regex$.ExternalSyntheticBUOutline0.m$1();
        return null;
    }

    private final int getDisplayId() {
        FeatureOption.INSTANCE.getClass();
        return (FeatureOption.isFoldDevice() && SystemFoldModeObserver.Companion.getInstance().isFolding()) ? 1 : 0;
    }

    private final NotificationBarDrawable getNotificationBarDrawable() {
        ScrimView notificationsScrim = getScrimController().getNotificationsScrim();
        Drawable drawable = notificationsScrim != null ? notificationsScrim.getDrawable() : null;
        if (drawable instanceof NotificationBarDrawable) {
            return (NotificationBarDrawable) drawable;
        }
        return null;
    }

    private final BlurMixConfig getPanelPlatformMixConfig() {
        boolean zBooleanValue = ((Boolean) ((PlatformBlurManager) this.platformBlurManager.get()).getUseMotionBlur().getValue()).booleanValue();
        boolean z = this.isEnteringBouncer || getScrimController().getState() == ScrimState.BOUNCER;
        ScrimControllerEx.Companion.getClass();
        if (ScrimControllerEx.isThirdTheme && !this.isEditMode) {
            BlurMixConfig.OverlayColor overlayColor = BlurMixConfig.OverlayColor.INSTANCE;
            overlayColor.setAlphaWithBlurAmount(!zBooleanValue);
            return overlayColor;
        }
        if (z) {
            return NotifiAndQsPlatformBlurExKt.panelBouncerMixConfig(zBooleanValue);
        }
        Context context = this.context;
        String str = NotifiAndQsPlatformBlurExKt.TAG;
        return NotifiAndQsPlatformBlurExKt.panelPlatformMixConfig(context, zBooleanValue, context.getResources().getConfiguration().isNightModeActive());
    }

    public static boolean isNeedScrimFade(float f, boolean z) {
        return z && f > 0.0f;
    }

    public static void showBehindScrim$default(ScrimControllerExImp scrimControllerExImp, float f) {
        ScrimViewEx ext;
        ScrimViewEx ext2;
        ScrimViewEx ext3;
        ViewBlurProxy viewBlurProxy;
        ScrimController scrimController = scrimControllerExImp.getScrimController();
        ScrimView scrimBehind = scrimController.getScrimBehind();
        if (scrimBehind != null && (ext3 = scrimBehind.getExt()) != null && (viewBlurProxy = ext3.getViewBlurProxy()) != null) {
            viewBlurProxy.setStaticBlurManager(Companion.getNotifiAndQsBlurManager(scrimControllerExImp.context));
        }
        scrimControllerExImp.adjudgeScrimBehindBlurType();
        scrimControllerExImp.shouldShowBehindScrim("showBehindScrim");
        ScrimView scrimBehind2 = scrimController.getScrimBehind();
        if (scrimBehind2 != null && (ext2 = scrimBehind2.getExt()) != null) {
            ext2.setScaleAmount(f, "showBehindScrim");
        }
        ScrimView scrimBehind3 = scrimController.getScrimBehind();
        if (scrimBehind3 == null || (ext = scrimBehind3.getExt()) == null) {
            return;
        }
        ext.setBlurAmount(f, "showBehindScrim");
    }

    public final void adjudgeScrimBehindBlurType() {
        ScrimViewEx ext;
        ViewBlurProxy viewBlurProxy;
        ViewBlurProxy.BlurType blurType;
        refreshBehindDrawable();
        updateBehindMixConfig();
        ScrimView scrimBehind = getScrimController().getScrimBehind();
        if (scrimBehind == null || (ext = scrimBehind.getExt()) == null || (viewBlurProxy = ext.getViewBlurProxy()) == null) {
            return;
        }
        ImmersiveCardControl immersiveCardControl = (ImmersiveCardControl) this.immersiveCardControl.get();
        Integer numCurrImmersiveBgType = immersiveCardControl.currImmersiveBgType((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) immersiveCardControl.lockScreenExtStateController.get()).getExtStateEvent().getValue());
        ScrimState scrimState = ScrimState.BOUNCER;
        if (numCurrImmersiveBgType != null && (this.isEnteringBouncer || getScrimController().getState() == scrimState)) {
            viewBlurProxy.setBlurType(ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE);
            return;
        }
        if ((ScrimUtil.isLowGaussianLevel(this.context) && !ScrimUtil.isLightOsGaussianLevel()) || GaussBlurUtils.isGaussBlurDisabled()) {
            blurType = ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE;
        } else if (this.isEnteringBouncer || getScrimController().getState() == scrimState) {
            KeyguardFeatureOption.INSTANCE.getClass();
            if (KeyguardFeatureOption.isDisableBouncerBlurScrim) {
                blurType = ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE;
            } else {
                blurType = isBouncerPlatformBlurAllEnabled ? ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE : ViewBlurProxy.BlurType.BlurTypeStatic.INSTANCE;
            }
        } else {
            blurType = ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE;
        }
        viewBlurProxy.setBlurType(blurType);
    }

    public final boolean applyStateOverride() {
        SmoothTransitionController companion;
        SmoothTransitionController.SmoothTransitionUiState lastUiState;
        SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState;
        SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState2;
        SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState3;
        SmoothTransitionController.SmoothTransitionUiState currentUiState;
        ScrimState scrimState = ScrimState.PULSING;
        ScrimState scrimState2 = ScrimState.DREAMING;
        ScrimState scrimState3 = ScrimState.SHADE_LOCKED;
        ScrimController scrimController = getScrimController();
        scrimController.mInFrontTint = scrimController.getState().getFrontTint();
        scrimController.mBehindTint = scrimController.getState().getBehindTint();
        scrimController.mNotificationsTint = scrimController.getState().getNotifTint();
        scrimController.mInFrontAlpha = scrimController.getState().getFrontAlpha();
        scrimController.mBehindAlpha = scrimController.getState().getBehindAlpha();
        scrimController.mNotificationsAlpha = scrimController.getState().getNotifAlpha();
        this.scrimAlpha = scrimController.getState().getExt().getScrimAlpha();
        this.scrimBehindAlphaForCustomBlur = scrimController.getInterpolatedFraction();
        scrimController.assertAlphasValid();
        if (!scrimController.mExpansionAffectsAlpha) {
            return true;
        }
        ScrimState state = scrimController.getState();
        ScrimState scrimState4 = ScrimState.UNLOCKED;
        if (state == scrimState4 || scrimController.getState() == scrimState2) {
            boolean z = scrimController.mOccludeAnimationPlaying || scrimController.getState().mLaunchingAffordanceWithPreview;
            if (!((ScreenOffAnimationController) this.screenOffAnimationController.get()).shouldExpandNotifications() && !scrimController.mAnimatingPanelExpansionOnUnlock && !z) {
                if (scrimController.mTransparentScrimBackground) {
                    scrimController.mBehindAlpha = 0.0f;
                    scrimController.mNotificationsAlpha = 0.0f;
                } else if (scrimController.getClipQsScrim()) {
                    float fPow = ((float) Math.pow(scrimController.getInterpolatedFraction(), 0.8d)) * this.scrimAlpha;
                    scrimController.mBehindAlpha = fPow;
                    scrimController.mNotificationsAlpha = fPow;
                } else {
                    float fConstrainedMap = MathUtils.constrainedMap(0.0f, 1.0f, 0.0f, 0.3f, scrimController.getPanelExpansionFraction());
                    scrimController.getDefaultScrimAlpha();
                    scrimController.mBehindAlpha = this.scrimAlpha * fConstrainedMap;
                    MathUtils.constrainedMap(0.0f, 1.0f, 0.3f, 0.75f, scrimController.getPanelExpansionFraction());
                    scrimController.mNotificationsAlpha = fConstrainedMap * this.scrimAlpha;
                }
                scrimController.mInFrontAlpha = 0.0f;
            }
            if (((OplusKgdTransitionAnimationControllerImpl) this.kgdTransitionAnimationController.get()).spruceOccludeAnim && (getOldState() == ScrimState.AOD || getOldState() == scrimState || this.statusBarStateController.isDozing() || this.statusBarStateController.getDozeAmount() > 0.0f || AodData.getInstance(this.context).mAodIsInShow || (lastUiState = (companion = SmoothTransitionController.Companion.getInstance(this.context)).getLastUiState()) == (smoothTransitionUiState = SmoothTransitionController.SmoothTransitionUiState.WORKSHOP_AOD) || lastUiState == (smoothTransitionUiState2 = SmoothTransitionController.SmoothTransitionUiState.NORMAL_AOD) || lastUiState == (smoothTransitionUiState3 = SmoothTransitionController.SmoothTransitionUiState.PANORAMIC_AOD) || (currentUiState = companion.getCurrentUiState()) == smoothTransitionUiState || currentUiState == smoothTransitionUiState2 || currentUiState == smoothTransitionUiState3)) {
                scrimController.mBehindAlpha = 0.0f;
                scrimController.mNotificationsAlpha = 0.0f;
            }
            if (scrimController.getState() == scrimState2) {
                float f = scrimController.mBouncerHiddenFraction;
                if (f != 1.0f) {
                    float fAboutToShowBouncerProgress = BouncerPanelExpansionCalculator.aboutToShowBouncerProgress(f);
                    scrimController.mBehindAlpha = MathUtils.lerp(scrimController.getDefaultScrimAlpha(), scrimController.mBehindAlpha, fAboutToShowBouncerProgress);
                    scrimController.mBehindTint = ColorUtils.blendARGB(ScrimState.BOUNCER.getBehindTint(), scrimController.mBehindTint, fAboutToShowBouncerProgress);
                }
            }
        } else {
            ScrimState state2 = scrimController.getState();
            ScrimState scrimState5 = ScrimState.KEYGUARD;
            if (state2 == scrimState5 || scrimController.getState() == scrimState3 || scrimController.getState() == scrimState) {
                Pair pairCalculateBackStateForState = calculateBackStateForState(scrimController.getState());
                Integer numValueOf = (Integer) pairCalculateBackStateForState.first;
                Float fValueOf = (Float) pairCalculateBackStateForState.second;
                if (scrimController.mTransitionToFullShadeProgress > 0.0f) {
                    Pair pairCalculateBackStateForState2 = calculateBackStateForState(scrimState3);
                    fValueOf.getClass();
                    fValueOf = Float.valueOf(MathUtils.lerp(fValueOf.floatValue(), ((Number) pairCalculateBackStateForState2.second).floatValue(), scrimController.mTransitionToFullShadeProgress));
                    numValueOf.getClass();
                    numValueOf = Integer.valueOf(ColorUtils.blendARGB(numValueOf.intValue(), ((Number) pairCalculateBackStateForState2.first).intValue(), scrimController.mTransitionToFullShadeProgress));
                }
                scrimController.mInFrontAlpha = scrimController.getState().getFrontAlpha();
                if (scrimController.getClipQsScrim()) {
                    scrimController.mNotificationsAlpha = 0.0f;
                    numValueOf.getClass();
                    scrimController.mNotificationsTint = numValueOf.intValue();
                    if (scrimController.getState() == scrimState5) {
                        scrimController.mBehindAlpha = ((float) Math.pow(ShadeInterpolation.getNotificationScrimAlpha(scrimController.getQsExpansion()), 0.8d)) * this.scrimAlpha;
                    } else if (scrimController.getState() == scrimState3) {
                        float fPow2 = (float) Math.pow(ShadeInterpolation.getNotificationScrimAlpha(scrimController.getPanelExpansionFraction()), 0.8d);
                        scrimController.mBehindAlpha = this.scrimAlpha * fPow2;
                        scrimController.mNotificationsAlpha = scrimController.getDefaultScrimAlpha() * fPow2;
                    } else {
                        scrimController.mBehindAlpha = this.scrimAlpha;
                    }
                    scrimController.mBehindTint = numValueOf.intValue();
                } else {
                    if (isKgNotificationCenter()) {
                        scrimController.mNotificationsAlpha = 0.0f;
                        scrimController.mBehindAlpha = 0.0f;
                    } else {
                        float interpolatedFraction = 1.0f - scrimController.getInterpolatedFraction();
                        float qsExpansion = scrimController.getQsExpansion();
                        if (interpolatedFraction < qsExpansion) {
                            interpolatedFraction = qsExpansion;
                        }
                        scrimController.mNotificationsAlpha = interpolatedFraction;
                        fValueOf.getClass();
                        scrimController.mBehindAlpha = fValueOf.floatValue();
                    }
                    scrimController.mNotificationsTint = scrimController.getState().getNotifTint();
                    numValueOf.getClass();
                    scrimController.mBehindTint = numValueOf.intValue();
                }
                boolean z2 = scrimController.getState() == scrimState5 && scrimController.mTransitionToFullShadeProgress == 0.0f && scrimController.getQsExpansion() == 0.0f && !scrimController.getClipQsScrim();
                if (scrimController.mKeyguardOccluded || z2) {
                    scrimController.mNotificationsAlpha = 0.0f;
                }
            }
        }
        if (scrimController.getState() != scrimState4) {
            scrimController.mAnimatingPanelExpansionOnUnlock = false;
        }
        boolean z3 = (ScrimUtil.isLowGaussianLevel(this.context) && !ScrimUtil.isLightOsGaussianLevel()) || GaussBlurUtils.isGaussBlurDisabled();
        boolean z4 = ((ScrimControllerEx) this).isQsVisible && scrimController.getState() == scrimState4 && getOldState() == ScrimState.BOUNCER_SCRIMMED;
        if (z3 && z4 && !((KeyguardStateController) this.keyguardStateController.get()).mKeyguardGoingAway) {
            scrimController.mBehindTint = this.context.getColor(2131102930);
            scrimController.mBehindAlpha = 1.0f;
        }
        scrimController.assertAlphasValid();
        handleGaussBlur(scrimController.getState(), false, 0.0f);
        return true;
    }

    public void bindController(ScrimController scrimController) {
        super.bindController(scrimController);
        if (this.panelAnimationEx.getUseLayeredAnimation()) {
            PanelAnimationEx.ExpandedFraction panelExpandFraction = this.panelAnimationEx.getPanelExpandFraction();
            ScrimControllerExImp$addFractionCallback$1 scrimControllerExImp$addFractionCallback$1 = new ScrimControllerExImp$addFractionCallback$1();
            scrimControllerExImp$addFractionCallback$1.this$0 = this;
            VarHandle.storeStoreFence();
            panelExpandFraction.addCallback(scrimControllerExImp$addFractionCallback$1);
        }
        this.scrimTransitionHelper.bindScrimController(scrimController, this);
        BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C02991(null), 3);
    }

    public final Pair calculateBackStateForState(ScrimState scrimState) {
        int iBlendARGB;
        float interpolatedFraction = getScrimController().getInterpolatedFraction();
        float fLerp = MathUtils.lerp(0.0f, getScrimController().getClipQsScrim() ? scrimState.getNotifAlpha() : scrimState.getBehindAlpha(), interpolatedFraction);
        boolean clipQsScrim = getScrimController().getClipQsScrim();
        ScrimState scrimState2 = ScrimState.KEYGUARD;
        ScrimState scrimState3 = ScrimState.BOUNCER;
        if (clipQsScrim) {
            iBlendARGB = ColorUtils.blendARGB(scrimState3.getNotifTint(), scrimState.getNotifTint(), interpolatedFraction);
        } else if (scrimState != ScrimState.SHADE_LOCKED || ((!ScrimUtil.isLowGaussianLevel(this.context) || ScrimUtil.isLightOsGaussianLevel()) && !GaussBlurUtils.isGaussBlurDisabled())) {
            iBlendARGB = ColorUtils.blendARGB(scrimState3.getBehindTint(), scrimState.getBehindTint(), interpolatedFraction);
        } else {
            iBlendARGB = ColorUtils.blendARGB(scrimState2.getBehindTint(), scrimState.getBehindTint(), ((Number) this.oplusLockscreenShadeTransitionController.getNotificationCenterAnimFraction().getValue()).floatValue());
        }
        if (scrimState == scrimState2) {
            fLerp = MathUtils.lerp(0.0f, scrimState.getExt().getScrimAlpha(), getScrimController().getQsExpansion());
            iBlendARGB = scrimState.getBehindTint();
            if (getScrimController().mStatusBarKeyguardViewManager.mPrimaryBouncerInteractor.isInTransit()) {
                if (getScrimController().getQsExpansion() == 0.0f) {
                    fLerp = MathUtils.lerp(scrimState3.getBehindAlpha(), scrimState.getBehindAlpha(), ShadeInterpolation.getNotificationScrimAlpha(getScrimController().getPanelExpansionFraction()));
                    iBlendARGB = scrimState3.getBehindTint();
                } else if (getScrimController().getPanelExpansionFraction() > 0.5f) {
                    fLerp = MathUtils.lerp(0.0f, scrimState.getExt().getScrimAlpha(), (getScrimController().getPanelExpansionFraction() * 2.0f) - 1.0f);
                } else {
                    fLerp = MathUtils.lerp(scrimState3.getBehindAlpha(), scrimState.getBehindAlpha(), getScrimController().getPanelExpansionFraction() * 2.0f);
                    iBlendARGB = scrimState3.getBehindTint();
                }
            }
        }
        return new Pair(Integer.valueOf(iBlendARGB), Float.valueOf(((KeyguardStateController) this.keyguardStateController.get()).mKeyguardGoingAway ? 0.0f : fLerp));
    }

    public void cancelBrightnessFadeAnimation(String str) {
        if (((ScrimControllerEx) this).isBrightnessAnimating) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, FileProvider$.ExternalSyntheticOutline0.m("cancelBrightnessFadeAnimation reason=", str), null, 8);
            ScrimView scrimBehind = getScrimController().getScrimBehind();
            Object tag = scrimBehind != null ? scrimBehind.getTag(ScrimController.TAG_KEY_ANIM) : null;
            ValueAnimator valueAnimator = tag instanceof ValueAnimator ? (ValueAnimator) tag : null;
            if (valueAnimator != null) {
                valueAnimator.cancel();
                scrimBehind.setTag(ScrimController.TAG_KEY_ANIM, null);
            }
            ScrimView notificationsScrim = getScrimController().getNotificationsScrim();
            Object tag2 = notificationsScrim != null ? notificationsScrim.getTag(ScrimController.TAG_KEY_ANIM) : null;
            ValueAnimator valueAnimator2 = tag2 instanceof ValueAnimator ? (ValueAnimator) tag2 : null;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
                notificationsScrim.setTag(ScrimController.TAG_KEY_ANIM, null);
            }
            setBrightnessAnimating(false);
        }
    }

    public boolean consumeScrimAlphaOverride(ScrimAlpha scrimAlpha) {
        float behindAlpha;
        float frontAlpha;
        ScrimController scrimController = getScrimController();
        if (scrimController.getState() != ScrimState.UNLOCKED) {
            KgdLogUtil.i(TAG, "consumeScrimAlphaOverride return false,state:" + scrimController.getState());
            return false;
        }
        scrimController.mNotificationsAlpha = scrimAlpha.getNotificationsAlpha();
        boolean z = ((KeyguardStateController) this.keyguardStateController.get()).mKeyguardGoingAway;
        ScrimState oldState = getOldState();
        ScrimState scrimState = ScrimState.BOUNCER;
        if (oldState == scrimState) {
            float behindAlpha2 = (scrimAlpha.getLeaveOpenOnKeyguard() || z) ? scrimState.getBehindAlpha() : 0.0f;
            scrimController.mBehindAlpha = behindAlpha2;
            scrimController.mInFrontAlpha = scrimState.getFrontAlpha() * scrimAlpha.getFrontAlpha();
        } else {
            ScrimState oldState2 = getOldState();
            ScrimState scrimState2 = ScrimState.BOUNCER_SCRIMMED;
            if (oldState2 == scrimState2) {
                scrimController.mBehindAlpha = scrimAlpha.getLeaveOpenOnKeyguard() ? scrimController.getState().getExt().getScrimAlphaWhenLeaveOpenOnKeyguard(((ScrimControllerEx) this).isQsVisible) : scrimAlpha.getBehindAlpha() * scrimState2.getBehindAlpha();
                if (scrimAlpha.getLeaveOpenOnKeyguard()) {
                    behindAlpha = scrimAlpha.getFrontAlpha();
                    frontAlpha = scrimState2.getFrontAlpha();
                } else {
                    behindAlpha = scrimAlpha.getBehindAlpha();
                    frontAlpha = scrimState2.getFrontAlpha();
                }
                scrimController.mInFrontAlpha = frontAlpha * behindAlpha;
            }
        }
        boolean z2 = scrimController.mBehindAlpha == 1.0f || scrimController.mInFrontAlpha == 1.0f || scrimController.mNotificationsAlpha == 1.0f;
        ScrimControllerExImp$$ExternalSyntheticLambda1 scrimControllerExImp$$ExternalSyntheticLambda1 = new ScrimControllerExImp$$ExternalSyntheticLambda1();
        scrimControllerExImp$$ExternalSyntheticLambda1.f$0 = this;
        scrimControllerExImp$$ExternalSyntheticLambda1.f$1 = scrimAlpha;
        scrimControllerExImp$$ExternalSyntheticLambda1.f$2 = scrimController;
        scrimControllerExImp$$ExternalSyntheticLambda1.f$3 = z;
        VarHandle.storeStoreFence();
        KgdLogUtil.d$default(TAG, "alpha_consume", 0, z2, scrimControllerExImp$$ExternalSyntheticLambda1, 4);
        scrimController.getScrimInFront().setViewAlpha(scrimController.mInFrontAlpha);
        scrimController.getNotificationsScrim().setViewAlpha(scrimController.mNotificationsAlpha);
        scrimController.getScrimBehind().setViewAlpha(scrimController.mBehindAlpha);
        return true;
    }

    public final void doBrightnessFade(ScrimView scrimView, float f, float f2, float f3, float f4, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            boolean z2 = scrimView == getScrimController().getScrimBehind();
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("do brightness fade: ", f, "-", f2, "-");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f3, "-", f4, " isbehind:");
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" syncBlurFraction:", "ScrimControllerExImp-->", sbM, z2, z));
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        int tint = scrimView.getTint();
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$alphaStart = f;
        anonymousClass2.$alphaEnd = f2;
        anonymousClass2.this$0 = this;
        anonymousClass2.$scrim = scrimView;
        anonymousClass2.$initialScrimTint = tint;
        anonymousClass2.$fractionStart = f3;
        anonymousClass2.$fractionEnd = f4;
        anonymousClass2.$syncBlurFraction = z;
        anonymousClass2.$anim = valueAnimatorOfFloat;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(anonymousClass2);
        valueAnimatorOfFloat.addListener(new doBrightnessFade.3(this, scrimView));
        valueAnimatorOfFloat.setInterpolator(ScrimControllerEx.PANEL_TRANSITION_INTERPOLATOR);
        valueAnimatorOfFloat.setDuration(200L);
        scrimView.setTag(ScrimController.TAG_START_ALPHA, Float.valueOf(f));
        scrimView.setTag(ScrimController.TAG_END_ALPHA, Float.valueOf(f2));
        valueAnimatorOfFloat.start();
        scrimView.setTag(ScrimController.TAG_KEY_ANIM, valueAnimatorOfFloat);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        ScrimViewEx ext;
        ViewBlurProxy viewBlurProxy;
        ScrimViewEx ext2;
        ViewBlurProxy viewBlurProxy2;
        ScrimView scrimBehind = getScrimController().getScrimBehind();
        if (scrimBehind != null && (ext2 = scrimBehind.getExt()) != null && (viewBlurProxy2 = ext2.getViewBlurProxy()) != null) {
            printWriter.println(viewBlurProxy2);
        }
        ScrimView scrimInFront = getScrimController().getScrimInFront();
        if (scrimInFront != null && (ext = scrimInFront.getExt()) != null && (viewBlurProxy = ext.getViewBlurProxy()) != null) {
            printWriter.println(viewBlurProxy);
        }
        this.viewBlurManager.dump(printWriter, strArr);
        OlockStateUtil.INSTANCE.dump(printWriter, strArr);
        ((ScrimBlurStateUtilsExImpl) this.scrimBlurStateUtils.get()).dump(printWriter, strArr);
        ActionReceiver$.ExternalSyntheticOutline1.m("scrim bg color: ", Integer.toHexString(this.context.getColor(2131102929)), printWriter);
    }

    public final void forceResetScrimVisibility() {
        ScrimController scrimController = getScrimController();
        scrimController.mIsBouncerToGoneTransitionRunning = false;
        boolean z = true;
        scrimController.setExpansionAffectsAlpha(true);
        if (!scrimController.getQsBottomVisible() && scrimController.getQsExpansion() == 0.0f) {
            z = false;
        }
        if (z) {
            scrimController.setQsPosition(0.0f, 0);
        }
        if (scrimController.getPanelExpansionFraction() > 0.0f) {
            scrimController.setRawPanelExpansionFraction(0.0f);
        } else {
            if (z) {
                return;
            }
            scrimController.applyAndDispatchState();
        }
    }

    public boolean getAnimateChange(ScrimState scrimState) {
        if ((scrimState == ScrimState.UNLOCKED && KeyguardRapidUnlockUtils.Companion.getInstance().unlockWithoutTransition()) || KeyguardUtils.Companion.isSystemFbeMode(this.context)) {
            return false;
        }
        return scrimState.getAnimateChange();
    }

    public long getAnimationDuration(ScrimState scrimState) {
        if (scrimState == ScrimState.UNLOCKED && ((KeyguardStateController) this.keyguardStateController.get()).mKeyguardGoingAway) {
            return scrimState.getAnimationDuration();
        }
        if (scrimState == ScrimState.AOD || scrimState == ScrimState.KEYGUARD || scrimState == ScrimState.BOUNCER_SCRIMMED) {
            return scrimState.getAnimationDuration();
        }
        return 0L;
    }

    public final LogBuffer getLogBuffer() {
        return this.logBuffer;
    }

    public int getNotificationScrimBgColor(Context context) {
        int color = context.getColor(2131102929);
        updateWhenThemeChange();
        if (color != -16777216 || (!ScrimUtil.isLowGaussianLevel(context) && !GaussBlurUtils.isGaussBlurDisabled())) {
            return color;
        }
        int color2 = context.getColor(2131102930);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[getNotificationScrimBgColor] other ", Integer.toHexString(color2), "ScrimControllerExImp-->", "SystemUi--Notification");
        }
        return color2;
    }

    public boolean getOplusLogic() {
        return this.oplusLogic;
    }

    public final boolean getRunnerUnlock() {
        return this.runnerUnlock;
    }

    public float getScrimBehindBlurAmount() {
        ViewBlurProxy viewBlurProxy = getScrimController().getScrimBehind().getExt().getViewBlurProxy();
        if (viewBlurProxy != null) {
            return viewBlurProxy.getBlurAmount();
        }
        return 0.0f;
    }

    public float getScrimFrontAlpha() {
        return getScrimController().getScrimInFront().getViewAlpha();
    }

    public float getScrimFrontBlurAmount() {
        ViewBlurProxy viewBlurProxy = getScrimController().getScrimInFront().getExt().getViewBlurProxy();
        if (viewBlurProxy != null) {
            return viewBlurProxy.getBlurAmount();
        }
        return 0.0f;
    }

    /* JADX WARN: Code duplicated, block: B:40:0x011c  */
    public void handleGaussBlur(ScrimState scrimState, boolean z, float f) {
        ScrimViewEx ext;
        ScrimViewEx ext2;
        ViewBlurProxy viewBlurProxy;
        ScrimViewEx ext3;
        ScrimViewEx ext4;
        ScrimViewEx ext5;
        ViewBlurProxy viewBlurProxy2;
        ScrimViewEx ext6;
        ScrimState scrimState2 = ScrimState.BRIGHTNESS_MIRROR;
        ScrimState scrimState3 = ScrimState.UNLOCKED;
        ScrimState scrimState4 = ScrimState.BOUNCER_SCRIMMED;
        Ref.FloatRef floatRef = new Ref.FloatRef();
        floatRef.element = getScrimController().getInterpolatedFraction();
        if (z) {
            floatRef.element = f;
        }
        ScrimControllerExImp$$ExternalSyntheticLambda2 scrimControllerExImp$$ExternalSyntheticLambda2 = new ScrimControllerExImp$$ExternalSyntheticLambda2();
        scrimControllerExImp$$ExternalSyntheticLambda2.f$0 = z;
        scrimControllerExImp$$ExternalSyntheticLambda2.f$1 = floatRef;
        VarHandle.storeStoreFence();
        KgdLogUtil.d(TAG, TAG, 8, false, scrimControllerExImp$$ExternalSyntheticLambda2);
        this.isEnteringBouncer = false;
        getScrimController().getScrimInFront().setBackgroundColor(0);
        float fFloatValue = ((Number) this.oplusLockscreenShadeTransitionController.getNotificationCenterAnimFraction().getValue()).floatValue();
        ScrimState scrimState5 = ScrimState.KEYGUARD;
        KeyguardStyleClockControllerImpl viewBlurProxy3 = null;
        if (scrimState != scrimState5 && scrimState != ScrimState.PULSING) {
            ScrimState scrimState6 = ScrimState.SHADE_LOCKED;
            if (scrimState != scrimState6) {
                ScrimState scrimState7 = ScrimState.BOUNCER;
                if (scrimState == scrimState7) {
                    if (((OplusSwitchUserUtil) this.switchUserUtilLazy.get()).mMultiUserBeginSwitch && getOldState() == scrimState4) {
                        long switchUserStartTime = ((OplusSwitchUserUtil) this.switchUserUtilLazy.get()).getSwitchUserStartTime();
                        if (switchUserStartTime == -1 || SystemClock.elapsedRealtime() - switchUserStartTime >= 200) {
                            if (LogUtil.getNormalDebug()) {
                                Log.d("SystemUi--Keyguard", "ScrimControllerExImp-->blur State to STATIC_BLUR_BOUNCER");
                            }
                            ((ScrimBlurStateUtilsExImpl) this.scrimBlurStateUtils.get()).setFrontScrimBlurAmount(1.0f, "entered-Bouncer");
                        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                            Log.d("SystemUi--Keyguard", "ScrimControllerExImp-->not blur State from BOUNCER_SCRIMMED to STATIC_BLUR_BOUNCER when switch user");
                        }
                    } else {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                            Log.d("SystemUi--Keyguard", "ScrimControllerExImp-->blur State to STATIC_BLUR_BOUNCER");
                        }
                        ((ScrimBlurStateUtilsExImpl) this.scrimBlurStateUtils.get()).setFrontScrimBlurAmount(1.0f, "entered-Bouncer");
                    }
                } else if (scrimState == scrimState4) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                        Log.d("SystemUi--Keyguard", "ScrimControllerExImp-->blur State to BOUNCER_SCRIMMED");
                    }
                    if (!z && getOldState() != scrimState5) {
                        floatRef.element = 1.0f;
                    }
                    if (getOldState() == scrimState6 && (this.statusBarStateController.getState() == 1 || this.oplusLockscreenShadeTransitionController.isAnimRunning())) {
                        getScrimController().mBehindAlpha = 0.0f;
                        ScrimView scrimBehind = getScrimController().getScrimBehind();
                        if (scrimBehind != null && (ext6 = scrimBehind.getExt()) != null) {
                            ext6.setBlurAmount(0.0f, "resetBehindBlurFromShadeLockToBouncer");
                        }
                    }
                    if (KeyguardUtils.Companion.isSystemFbeMode(this.context)) {
                        floatRef.element = 1.0f;
                        ((ScrimBlurStateUtilsExImpl) this.scrimBlurStateUtils.get()).setFrontScrimBlurAmount(floatRef.element, "entered-Bouncer-SystemFbeMode");
                        KeyguardStyleClockControllerImpl keyguardStyleClockController = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getKeyguardStyleClockController();
                        KeyguardStyleClockControllerImpl keyguardStyleClockControllerImpl = keyguardStyleClockController instanceof KeyguardStyleClockControllerImpl ? keyguardStyleClockController : null;
                        if (keyguardStyleClockControllerImpl != null) {
                            keyguardStyleClockControllerImpl.hideStyleClockAlphaIfNeeded("entered-Bouncer-SystemFbeMode");
                        }
                    }
                    if (OlockStateUtil.INSTANCE.getSafeModeEnable()) {
                        ScrimController scrimController = getScrimController();
                        ScrimView scrimInFront = scrimController.getScrimInFront();
                        if (scrimInFront != null) {
                            scrimInFront.setViewAlpha(1.0f);
                        }
                        if (OpNotificationUtils.isNormalScreen()) {
                            ScrimView scrimInFront2 = scrimController.getScrimInFront();
                            if (scrimInFront2 != null) {
                                scrimInFront2.setBackgroundResource(2131235748);
                            }
                        } else {
                            ScrimView scrimInFront3 = scrimController.getScrimInFront();
                            if (scrimInFront3 != null) {
                                scrimInFront3.setBackgroundResource(2131235749);
                            }
                        }
                    }
                } else if (scrimState == scrimState3) {
                    boolean z2 = ((KeyguardStateController) this.keyguardStateController.get()).mKeyguardGoingAway;
                    boolean z3 = getScrimController().mOccludeAnimationPlaying;
                    boolean z4 = getOldState() == scrimState7;
                    boolean z5 = getOldState() == scrimState4;
                    if (getOldState() != scrimState3) {
                        ScrimControllerExImp$$ExternalSyntheticLambda4 scrimControllerExImp$$ExternalSyntheticLambda4 = new ScrimControllerExImp$$ExternalSyntheticLambda4();
                        scrimControllerExImp$$ExternalSyntheticLambda4.f$0 = z4;
                        scrimControllerExImp$$ExternalSyntheticLambda4.f$1 = z5;
                        scrimControllerExImp$$ExternalSyntheticLambda4.f$2 = this;
                        scrimControllerExImp$$ExternalSyntheticLambda4.f$3 = z2;
                        scrimControllerExImp$$ExternalSyntheticLambda4.f$4 = z3;
                        VarHandle.storeStoreFence();
                        KgdLogUtil.d(TAG, TAG, 8, false, scrimControllerExImp$$ExternalSyntheticLambda4);
                    }
                    if (isNotNeedClearAlpha()) {
                        return;
                    }
                    if ((z4 || (z5 && !this.runnerUnlock)) && z2) {
                        return;
                    }
                    if (((z4 || z5) && z3) || this.hasHandleUnlockState) {
                        return;
                    }
                    if (getOldState() != scrimState2) {
                        this.hasHandleUnlockState = true;
                        adjudgeScrimBehindBlurType();
                        ScrimView scrimBehind2 = getScrimController().getScrimBehind();
                        if (scrimBehind2 != null && (ext5 = scrimBehind2.getExt()) != null && (viewBlurProxy2 = ext5.getViewBlurProxy()) != null) {
                            viewBlurProxy2.setStaticBlurManager(Companion.getNotifiAndQsBlurManager(this.context));
                        }
                        if (this.statusBarStateController.leaveOpenOnKeyguardHide()) {
                            ScrimView scrimBehind3 = getScrimController().getScrimBehind();
                            if (scrimBehind3 != null && (ext4 = scrimBehind3.getExt()) != null) {
                                ext4.setBlurAmount(1.0f, "leaveOpenOnKeyguard");
                            }
                        } else if (getScrimController().getQsExpansion() != 1.0f && !((NotificationPanelViewController) this.panelViewControllerLazy.get()).isExpanded()) {
                            ScrimView scrimBehind4 = getScrimController().getScrimBehind();
                            if (scrimBehind4 != null && (ext3 = scrimBehind4.getExt()) != null) {
                                ext3.setBlurAmount(0.0f, "clearBehindScrimWhenUnlock");
                            }
                            ((ScrimBlurStateUtilsExImpl) this.scrimBlurStateUtils.get()).setFrontScrimBlurAmount(0.0f, "clearFrontScrimWhenUnlock");
                        }
                        ScrimControllerExImp$handleGaussBlur$runnable$1 scrimControllerExImp$handleGaussBlur$runnable$1 = new ScrimControllerExImp$handleGaussBlur$runnable$1(0);
                        scrimControllerExImp$handleGaussBlur$runnable$1.this$0 = this;
                        VarHandle.storeStoreFence();
                        if (z5 && !z2 && ((KeyguardStateController) this.keyguardStateController.get()).mOccluded) {
                            scrimControllerExImp$handleGaussBlur$runnable$1.run();
                        } else {
                            ((ScrimBlurStateUtilsExImpl) this.scrimBlurStateUtils.get()).setNeedClearScrimAlphaWhenUnlock(true);
                            Choreographer choreographer = Choreographer.getInstance();
                            AnonymousClass4 anonymousClass4 = new AnonymousClass4(1);
                            anonymousClass4.$scrimFrontProxy = scrimControllerExImp$handleGaussBlur$runnable$1;
                            VarHandle.storeStoreFence();
                            choreographer.postFrameCallback(anonymousClass4);
                        }
                    }
                } else if (scrimState == ScrimState.AOD) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                        OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("blur State to AOD", getScrimController().getQsExpansion(), "ScrimControllerExImp-->", "SystemUi--Keyguard");
                    }
                    if (getScrimController().getQsExpansion() == 0.0f) {
                        showBehindScrim$default(this, 0.0f);
                        Companion.getPwdPanelScrimBlurManager(this.context).stopScreenShotBlur();
                    }
                }
            } else if (fFloatValue <= 0.0f || fFloatValue >= 1.0f || getScrimController().getQsExpansion() != 0.0f || !this.oplusLockscreenShadeTransitionController.isAnimRunning()) {
                boolean z6 = getScrimController().mOccludeAnimationPlaying;
                OplusChargeAnimController$.ExternalSyntheticOutline0.m(TAG, MonoSpline$.ExternalSyntheticOutline0.m("blur State to STATIC_BLUR_SHADE_LOCK: shadeLock--mQsExpansion: ", getScrimController().getQsExpansion(), " , interpolatedFract = ", floatRef.element, ",isOccludeAnimationPlaying="), z6);
                if (z6) {
                    showBehindScrim$default(this, 0.0f);
                } else {
                    showBehindScrim$default(this, 0.0f);
                }
            } else {
                KgdLogUtil.i(TAG, "blur State to KEYGUARD FROM SHADE_LOCK: shadeLock--mQsExpansion: " + getScrimController().getQsExpansion() + " , fraction = " + fFloatValue);
                showBehindScrim$default(this, 0.0f);
            }
        } else if (fFloatValue > 0.0f && fFloatValue < 1.0f && getScrimController().getQsExpansion() == 0.0f && this.oplusLockscreenShadeTransitionController.isAnimRunning()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("blur State to KEYGUARD_NOTIFICATION_CENTER: slide up to notificaiton center in KG,  fraction = ", fFloatValue, "ScrimControllerExImp-->", "SystemUi--Keyguard");
            }
            showBehindScrim$default(this, 0.0f);
        } else if (floatRef.element != 1.0f || getScrimController().getQsExpansion() <= 0.0f) {
            float f2 = floatRef.element;
            if (f2 <= 0.0f || f2 >= 1.0f || getScrimController().getQsExpansion() <= 0.0f) {
                float f3 = floatRef.element;
                if (f3 > 0.0f && f3 < 1.0f && getScrimController().getQsExpansion() == 0.0f && !this.isStartUnlockHintAnimationRunning && !getScrimController().getKeyguardUpdateMonitor().canSkipBouncer(KeyguardUpdateMonitor.getCurrentUser())) {
                    float f4 = 1.0f - floatRef.element;
                    KgdLogUtil.i(TAG, "blur State to STATIC_BLUR_QS_FRACTION: pull up Bouncer in KG, reform fraction = " + f4);
                    this.isEnteringBouncer = true;
                    ((ScrimBlurStateUtilsExImpl) this.scrimBlurStateUtils.get()).setFrontScrimBlurAmount(f4, "entering-Bouncer");
                } else if (floatRef.element == 1.0f && getScrimController().getQsExpansion() == 0.0f) {
                    KgdLogUtil.d(TAG, TAG, 8, false, new ScrimControllerExImp$$ExternalSyntheticLambda0(1));
                    showBehindScrim$default(this, 0.0f);
                    Companion.getPwdPanelScrimBlurManager(this.context).stopScreenShotBlur();
                }
            } else {
                KgdLogUtil.i(TAG, "blur State to STATIC_BLUR_QS_FRACTION: pull up Bouncer in KG, while QS has pulled down, reform fraction = 1");
                showBehindScrim$default(this, 1.0f);
            }
        } else {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("blur State to STATIC_BLUR_QS_FRACTION: pull down/up QS in KG, mQsExpansion = ", getScrimController().getQsExpansion(), "ScrimControllerExImp-->", "SystemUi--Keyguard");
            }
            if (getOldState() != scrimState2 || !((ScrimControllerEx) this).isBrightnessAnimating) {
                if (getOldState() == scrimState4) {
                    ScrimView scrimInFront4 = getScrimController().getScrimInFront();
                    if (Intrinsics.areEqual((scrimInFront4 == null || (ext2 = scrimInFront4.getExt()) == null || (viewBlurProxy = ext2.getViewBlurProxy()) == null) ? null : viewBlurProxy.getBlurType(), ViewBlurProxy.BlurType.BlurTypeWindowBg.INSTANCE)) {
                        ScrimView scrimInFront5 = getScrimController().getScrimInFront();
                        if (scrimInFront5 != null && (ext = scrimInFront5.getExt()) != null) {
                            viewBlurProxy3 = ext.getViewBlurProxy();
                        }
                        KeyguardStyleClockControllerImpl keyguardStyleClockControllerImpl2 = viewBlurProxy3;
                        if (keyguardStyleClockControllerImpl2 != null) {
                            keyguardStyleClockControllerImpl2.setNeedSyncWindowBlur(true);
                        }
                        Choreographer choreographer2 = Choreographer.getInstance();
                        AnonymousClass4 anonymousClass5 = new AnonymousClass4(0);
                        anonymousClass5.$scrimFrontProxy = keyguardStyleClockControllerImpl2;
                        VarHandle.storeStoreFence();
                        choreographer2.postFrameCallback(anonymousClass5);
                    }
                }
                showBehindScrim$default(this, getScrimController().getQsExpansion());
            }
        }
        ((PlatformBlurManager) this.platformBlurManager.get()).updatePlatformBlurState("handleGaussBlur");
        if (scrimState != scrimState3) {
            this.hasHandleUnlockState = false;
        }
    }

    public ColorExtractor.GradientColors initColors() {
        SysuiColorExtractor colorExtractor = getColorExtractor();
        colorExtractor.getClass();
        return colorExtractor.getNeutralColors();
    }

    public final boolean isDisableBouncerBlur() {
        if (GaussBlurUtils.isGaussBlurDisabled()) {
            return true;
        }
        KeyguardFeatureOption.INSTANCE.getClass();
        return KeyguardFeatureOption.isDisableBouncerBlurScrim;
    }

    public final boolean isEnteringBouncer() {
        return this.isEnteringBouncer;
    }

    public final boolean isKgNotificationCenter() {
        return (getScrimController().getState() == ScrimState.SHADE_LOCKED) || (getScrimController().getState() == ScrimState.KEYGUARD && (getScrimController().getQsExpansion() > 0.0f ? 1 : (getScrimController().getQsExpansion() == 0.0f ? 0 : -1)) == 0 && this.oplusLockscreenShadeTransitionController.isAnimatingToExpand());
    }

    public final boolean isNotNeedClearAlpha() {
        ViewGroup viewGroup;
        ImmersiveCardControl immersiveCardControl = (ImmersiveCardControl) this.immersiveCardControl.get();
        if (immersiveCardControl.currImmersiveBgType((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) immersiveCardControl.lockScreenExtStateController.get()).getExtStateEvent().getValue()) != null && (getScrimController().getState() == ScrimState.BOUNCER || this.isEnteringBouncer || (getScrimController().getState() != ScrimState.BOUNCER_SCRIMMED && ((KeyguardStateController) this.keyguardStateController.get()).mPrimaryBouncerShowing))) {
            return true;
        }
        boolean z = ((OplusSwitchUserUtil) this.switchUserUtilLazy.get()).mMultiUserBeginSwitch;
        boolean z2 = ((KeyguardViewMediator) this.keyguardViewMediator.get()).mHideAnimationRunning;
        boolean z3 = false;
        if (!z || (((viewGroup = ((NotificationShadeWindowViewController) this.notificationShadeWindowViewControllerLazy.get()).mBouncerParentView) == null || viewGroup.getVisibility() != 0) && !z2)) {
            return false;
        }
        ViewGroup viewGroup2 = ((NotificationShadeWindowViewController) this.notificationShadeWindowViewControllerLazy.get()).mBouncerParentView;
        if (viewGroup2 != null && viewGroup2.getVisibility() == 0) {
            z3 = true;
        }
        OplusChargeAnimController$.ExternalSyntheticOutline0.m(TAG, TextFieldImplKt$.ExternalSyntheticOutline0.m("switchUserUtilLazyIsMultiUserBeginSwitch： ", "keyguardSecurityView?.visibility： ", "hideAnimationRunning: ", z, z3), z2);
        return true;
    }

    public final boolean isUseWallpaperBlurWhenLeaveOpen() {
        ScrimView scrimBehind = getScrimController().getScrimBehind();
        boolean z = (scrimBehind != null ? scrimBehind.getDrawable() : null) instanceof WallpaperBlurDrawable;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("isUseWallpaperBlurWhenLeaveOpen:", " , ", z, this.statusBarStateController.leaveOpenOnKeyguardHide()), null, 8);
        return z && this.statusBarStateController.leaveOpenOnKeyguardHide();
    }

    public final void logBufferAlpha(float f, float f2) {
        if (LogUtil.panicEnabled) {
            this.logBuffer.log(TAG, LogLevel.DEBUG, ListPopupWindow$.ExternalSyntheticOutline0.m("frontAlpha:", f, " behindAlpha:", f2, " clipsQsScrim:false"), (Throwable) null);
        }
    }

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("[onChange] superSaveMode is ", ",qsBottomVisible=", "ScrimControllerExImp-->", SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState, getScrimController().getQsBottomVisible()));
        }
        refreshBehindDrawable();
        ((PlatformBlurManager) this.platformBlurManager.get()).updatePlatformBlurState("onSuperSaveModeChange");
        adjudgeScrimBehindBlurType();
        if (GaussBlurUtils.isGaussBlurDisabled()) {
            ScrimController scrimController = getScrimController();
            if (!scrimController.getQsBottomVisible() || scrimController.getState() == ScrimState.UNINITIALIZED) {
                return;
            }
            scrimController.getState().prepare(getOldState());
            scrimController.applyAndDispatchState();
        }
    }

    public final void onOccludeAnimationPlayingChanged(boolean z) {
        ((OplusKgdTransitionAnimationControllerImpl) this.kgdTransitionAnimationController.get()).onOccludeAnimationPlayingChanged(z);
    }

    public final void onQsBottomVisibleChanged(boolean z) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[onQsBottomVisibleChanged] qsBottomVisible=", z), null, 8);
        ((PlatformBlurManager) this.platformBlurManager.get()).updatePlatformBlurState("updatePanelBlurState");
        ((PlatformBlurManager) this.platformBlurManager.get()).forceStopMotionBlurRefresh(false);
        ((PlatformBlurManager) this.platformBlurManager.get()).handleTimeoutPauseBlur(z);
        adjudgeScrimBehindBlurType();
    }

    public void onStateChanged(ScrimState scrimState, ScrimState scrimState2) {
        super.onStateChanged(scrimState, scrimState2);
        if (scrimState != scrimState2 && scrimState2 == ScrimState.UNLOCKED) {
            OplusScreenshotHelper.INSTANCE.needCheckPureBlack(500L);
        }
        ((OplusKgdMaskInteractor) this.oplusKgdMaskInteractor.get()).onScrimStateChanged(scrimState, scrimState2);
    }

    public final void onThemeChanged() {
        updateWhenThemeChange();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            ScrimControllerEx.Companion.getClass();
            OplusLockIconView$.ExternalSyntheticOutline0.m("[onThemeChanged] isThirdTheme = ", "ScrimControllerExImp-->", "SystemUi--Notification", ScrimControllerEx.isThirdTheme);
        }
    }

    public final void onUiModeChanged() {
        ScrimController scrimController = getScrimController();
        if (!scrimController.getQsBottomVisible() || scrimController.getState() == ScrimState.UNINITIALIZED) {
            return;
        }
        scrimController.getState().prepare(getOldState());
        scrimController.applyAndDispatchState();
    }

    public final void refreshBehindDrawable() {
        View scrimBehind = getScrimController().getScrimBehind();
        if (scrimBehind != null) {
            boolean zBooleanValue = false;
            if (!ScrimUtil.isWallpaperBlurDisable() && ScrimUtil.isLowGaussianLevel(this.context)) {
                ScrimControllerEx.Companion.getClass();
                if (!ScrimControllerEx.isThirdTheme && !SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState) {
                    zBooleanValue = (this.isEnteringBouncer || getScrimController().getState() == ScrimState.BOUNCER || getScrimController().getState() == ScrimState.BOUNCER_SCRIMMED) ? ((Boolean) this.isBAnimEnableBouncerWallpaperBlur$delegate.getValue()).booleanValue() : true;
                }
            }
            if (zBooleanValue) {
                if (scrimBehind.getDrawable() instanceof WallpaperBlurDrawable) {
                    return;
                } else {
                    scrimBehind.setDrawable(((WallpaperBlurManager) this.wallpaperBlurManager.get()).requireWallpaperBlurDrawableForView(scrimBehind, true));
                }
            } else {
                if (scrimBehind.getDrawable() instanceof AutoBlurDrawable) {
                    return;
                }
                if (ScrimUtil.isLowGaussianLevel(this.context) && !ScrimUtil.isWallpaperBlurDisable()) {
                    ((WallpaperBlurManager) this.wallpaperBlurManager.get()).releaseWallpaperBlurDrawableForView(scrimBehind);
                }
                ViewBlurProxy viewBlurProxy = new ViewBlurProxy(scrimBehind, new BlurConfig(NotifiAndQsPlatformBlurExKt.panelBlurRadius(this.context), 0, null, null, null, null, true, getPanelPlatformMixConfig(), null, 0, null, 33456126), Companion.getNotifiAndQsBlurManager(this.context), 24);
                scrimBehind.setDrawable(new AutoBlurDrawable(viewBlurProxy, new MaskDrawable(viewBlurProxy.getBlurConfig())));
            }
            boolean zIsBAnimLevel = ScrimUtil.isBAnimLevel();
            ScrimControllerEx.Companion.getClass();
            boolean z = ScrimControllerEx.isThirdTheme;
            boolean z2 = SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState;
            Drawable drawable = scrimBehind.getDrawable();
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("refreshBehindDrawable isBAnimLevel: ", ", isThirdTheme: ", ", isSuperPowerSaveState: ", zIsBAnimLevel, z);
            sbM.append(z2);
            sbM.append(", drawable: ");
            sbM.append(drawable);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:33:0x00dd  */
    public final void scrimViewsInitted() {
        ViewBlurProxy.BlurType blurType;
        DebugTool.INSTANCE.setLogBuffer(this.logBuffer);
        ScrimController scrimController = getScrimController();
        ScrimView scrimBehind = scrimController.getScrimBehind();
        StaticBlurManager staticBlurManager = null;
        if (scrimBehind != null) {
            refreshBehindDrawable();
            ScrimViewEx ext = scrimBehind.getExt();
            if (ext != null) {
                ext.setLogBuffer(this.logBuffer);
            }
            ScrimViewEx ext2 = scrimBehind.getExt();
            if (ext2 != null) {
                ext2.setBehind(true);
            }
            adjudgeScrimBehindBlurType();
            RepeatWhenAttachedKt.repeatWhenAttached$default(scrimBehind, (CoroutineContext) null, new ScrimControllerExImp$collectLockscreenNtfCenterAnimFractionFlow$1(this, null), 3);
        }
        View scrimInFront = scrimController.getScrimInFront();
        if (scrimInFront != null) {
            ViewBlurProxy viewBlurProxy = new ViewBlurProxy(scrimInFront, new BlurConfig(540, 0, null, null, null, null, true, null, null, 0, new WindowBlurConfig(this.context.getResources().getDimensionPixelSize(2131170215), ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP, this.context.getColor(2131099943)), 31424510), staticBlurManager, 28);
            if (!ScrimUtil.isWallpaperBlurDisable() && ScrimUtil.isBAnimLevel()) {
                ScrimControllerEx.Companion.getClass();
                if (ScrimControllerEx.isThirdTheme || SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState || !((Boolean) this.isBAnimEnableBouncerWallpaperBlur$delegate.getValue()).booleanValue()) {
                    scrimInFront.setDrawable(new AutoBlurDrawable(viewBlurProxy, new MaskDrawable(viewBlurProxy.getBlurConfig())));
                } else {
                    scrimInFront.setDrawable(((WallpaperBlurManager) this.wallpaperBlurManager.get()).requireWallpaperBlurDrawableForView(scrimInFront, true));
                }
            } else {
                scrimInFront.setDrawable(new AutoBlurDrawable(viewBlurProxy, new MaskDrawable(viewBlurProxy.getBlurConfig())));
            }
            NotificationFeatureOption.INSTANCE.getClass();
            if (NotificationFeatureOption.isGaussBlurDisabled) {
                blurType = ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE;
            } else {
                KeyguardFeatureOption.INSTANCE.getClass();
                if (KeyguardFeatureOption.isDisableBouncerBlurScrim) {
                    blurType = ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE;
                } else {
                    blurType = ViewBlurProxy.BlurType.BlurTypeWindowBg.INSTANCE;
                }
            }
            viewBlurProxy.setBlurType(blurType);
            ScrimViewEx ext3 = scrimInFront.getExt();
            if (ext3 != null) {
                ext3.setLogBuffer(this.logBuffer);
            }
        }
        ScrimView notificationsScrim = scrimController.getNotificationsScrim();
        if (notificationsScrim != null) {
            notificationsScrim.setDrawable(new NotificationBarDrawable(notificationsScrim));
            ScrimViewEx ext4 = notificationsScrim.getExt();
            if (ext4 != null) {
                ext4.setLogBuffer(this.logBuffer);
            }
        }
    }

    public void setBrightnessMirrorVisible(boolean z) {
        NotificationBarDrawable notificationBarDrawable = getNotificationBarDrawable();
        if (notificationBarDrawable != null) {
            notificationBarDrawable.setBrightnessMirrorVisible(z);
        }
    }

    public final void setExtraScrimState() {
        float blurFraction;
        ScrimViewEx ext;
        if (this.statusBarStateController.getState() != 0 || (((ScrimControllerEx) this).isBrightnessAnimating || getScrimController().getState().equals(ScrimState.BRIGHTNESS_MIRROR)) || AodData.getInstance(this.context).mAodIsInShow || isNotNeedClearAlpha()) {
            return;
        }
        ScrimController scrimController = getScrimController();
        if (this.panelAnimationEx.getUseLayeredAnimation()) {
            if (LogUtil.panicEnabled) {
                this.logBuffer.log(TAG, LogLevel.DEBUG, "qsBlurFraction:" + ((OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSEx.get()).getBlurFraction() + " panelBlurFraction:" + this.panelAnimationEx.getPanelExpandFraction().getBlurFraction(), (Throwable) null);
            }
            blurFraction = this.panelAnimationEx.getPanelExpandFraction().getBlurFraction();
            float blurFraction2 = ((OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSEx.get()).getBlurFraction();
            if (blurFraction < blurFraction2) {
                blurFraction = blurFraction2;
            }
            getScrimController().mBehindAlpha = this.scrimAlpha * blurFraction;
        } else {
            blurFraction = this.scrimBehindAlphaForCustomBlur;
        }
        if (((Boolean) ((PlatformBlurManager) this.platformBlurManager.get()).getUseMotionBlur().getValue()).booleanValue() && this.lastCustomBlurRatio != blurFraction) {
            ((PlatformBlurManager) this.platformBlurManager.get()).updateMotionFrequency(blurFraction > 0.0f && blurFraction < 1.0f);
        }
        if (this.lastCustomBlurRatio != blurFraction || blurFraction == 0.0f || blurFraction == 1.0f) {
            ScrimView scrimBehind = scrimController.getScrimBehind();
            if (scrimBehind != null && (ext = scrimBehind.getExt()) != null) {
                ext.setBlurAndScaleAmount(blurFraction, "ScrimControllerExImp-setExtraScrimState");
            }
            this.lastCustomBlurRatio = blurFraction;
        }
    }

    public void setNotificationsAlpha(float f) {
        getScrimController().getNotificationsScrim().setAlpha(f);
    }

    public void setQsDialogVisible(boolean z) {
        NotificationBarDrawable notificationBarDrawable = getNotificationBarDrawable();
        if (notificationBarDrawable != null) {
            notificationBarDrawable.setQsDialogVisible(z);
        }
    }

    public void setQsFullyExpanded(boolean z) {
        NotificationBarDrawable notificationBarDrawable = getNotificationBarDrawable();
        if (notificationBarDrawable != null) {
            notificationBarDrawable.setQsFullyExpanded(z);
        }
    }

    public void setQsPosition(float f, int i, PanelType panelType, Function2 function2) {
        if (Float.isNaN(f)) {
            return;
        }
        int iOrdinal = panelType.ordinal();
        ScrimControllerEx.PanelScrimParams panelScrimParams = (iOrdinal == 0 || iOrdinal != 1) ? this.notificationPanelScrimParams : this.qsPanelScrimParams;
        panelScrimParams.setFraction(f);
        int iOrdinal2 = panelType.ordinal();
        ScrimControllerEx.PanelScrimParams panelScrimParams2 = (iOrdinal2 == 0 || iOrdinal2 != 1) ? this.notificationPanelScrimParams : this.qsPanelScrimParams;
        panelScrimParams2.setPanelBottom(i);
        float fraction = this.notificationPanelScrimParams.getFraction();
        float fraction2 = this.qsPanelScrimParams.getFraction();
        if (fraction < fraction2) {
            fraction = fraction2;
        }
        Float fValueOf = Float.valueOf(fraction);
        int panelBottom = this.notificationPanelScrimParams.getPanelBottom();
        int panelBottom2 = this.qsPanelScrimParams.getPanelBottom();
        if (panelBottom < panelBottom2) {
            panelBottom = panelBottom2;
        }
        function2.invoke(fValueOf, Integer.valueOf(panelBottom));
    }

    public final void setRunnerUnlock(boolean z) {
        this.runnerUnlock = z;
    }

    public void setScrimFrontAlpha(float f) {
        getScrimController().getScrimInFront().setViewAlpha(f);
    }

    public void setSplitEnableForNotificationScrim(boolean z) {
        NotificationBarDrawable notificationBarDrawable = getNotificationBarDrawable();
        if (notificationBarDrawable != null) {
            notificationBarDrawable.setSplitShadeEnabled(z);
        }
    }

    public void setStartUnlockHintAnimationRunning(boolean z) {
        this.isStartUnlockHintAnimationRunning = z;
    }

    public boolean shouldForceFinish(ScrimState scrimState, ScrimState scrimState2, boolean z) {
        ScrimState scrimState3 = ScrimState.UNLOCKED;
        if (z && scrimState == scrimState3 && ((scrimState2 == ScrimState.KEYGUARD || scrimState2 == ScrimState.BOUNCER || scrimState2 == ScrimState.BOUNCER_SCRIMMED) && !((KeyguardStateController) this.keyguardStateController.get()).mShowing)) {
            return true;
        }
        return scrimState == scrimState3 && scrimState2 == ScrimState.BRIGHTNESS_MIRROR && z;
    }

    public final boolean shouldForceUpdate(boolean z) {
        if (z || !getLastRecordKeyGuardGoingAway()) {
            setLastRecordKeyGuardGoingAway(z);
            return false;
        }
        setLastRecordKeyGuardGoingAway(z);
        return true;
    }

    public final void shouldShowBehindScrim(String str) {
        PanoramicAodGestureController.Companion companion = PanoramicAodGestureController.Companion;
        if (companion.getRequestKeyguardByPanoramicSwipeUp().getAndSet(false)) {
            companion.setBlurGone(false);
        } else {
            ((ScrimBlurStateUtilsExImpl) this.scrimBlurStateUtils.get()).setFrontScrimBlurAmount(0.0f, str);
            companion.setBlurGone(true);
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0024  */
    public boolean shouldStartBrightnessFadeAnimation(ScrimState scrimState) {
        boolean z;
        ScrimState scrimState2 = ScrimState.BRIGHTNESS_MIRROR;
        if (scrimState == scrimState2 || getOldState() == scrimState2) {
            if (isNeedScrimFade(getScrimController().getPanelExpansionFraction(), getScrimController().getQsBottomVisible())) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        if (z && ((OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSEx.get()).isFractionAnimationRunning()) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "skip brightness fade, fractionAnimation already running, state=" + scrimState, null, 8);
            return false;
        }
        if (z) {
            if (scrimState == scrimState2) {
                doBrightnessFade(getScrimController().getNotificationsScrim(), getScrimController().getNotificationsScrim().getViewAlpha(), getScrimController().getCurrentScrimAlpha(getScrimController().getNotificationsScrim()), 1.0f, 0.0f, false);
                doBrightnessFade(getScrimController().getScrimBehind(), getScrimController().getScrimBehind().getViewAlpha(), getScrimController().getCurrentScrimAlpha(getScrimController().getScrimBehind()), 1.0f, 0.0f, true);
                return z;
            }
            ScrimView scrimBehind = getScrimController().getScrimBehind();
            int i = ScrimController.TAG_KEY_ANIM;
            float fConstrain = 0.0f;
            if (scrimBehind.getTag(i) != null) {
                ValueAnimator valueAnimator = (ValueAnimator) getScrimController().getScrimBehind().getTag(i);
                fConstrain = MathUtils.constrain(MathUtils.lerp(1.0f, 0.0f, ((Float) valueAnimator.getAnimatedValue()).floatValue()), 0.0f, 1.0f);
                valueAnimator.cancel();
            }
            float f = fConstrain;
            if (getScrimController().getNotificationsScrim().getTag(i) != null) {
                ((ValueAnimator) getScrimController().getNotificationsScrim().getTag(i)).cancel();
            }
            doBrightnessFade(getScrimController().getNotificationsScrim(), getScrimController().getNotificationsScrim().getViewAlpha(), getScrimController().getCurrentScrimAlpha(getScrimController().getNotificationsScrim()), f, 1.0f, false);
            doBrightnessFade(getScrimController().getScrimBehind(), getScrimController().getScrimBehind().getViewAlpha(), getScrimController().getCurrentScrimAlpha(getScrimController().getScrimBehind()), f, 1.0f, true);
        }
        return z;
    }

    public final void updateBehindMixConfig() {
        ScrimViewEx ext;
        ViewBlurProxy viewBlurProxy;
        ScrimView scrimBehind = getScrimController().getScrimBehind();
        if (scrimBehind == null || (ext = scrimBehind.getExt()) == null || (viewBlurProxy = ext.getViewBlurProxy()) == null) {
            return;
        }
        ScrimControllerEx.Companion.getClass();
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("updateBehindMixConfig: isThirdTheme=", ScrimControllerEx.isThirdTheme), null, 8);
        viewBlurProxy.getBlurConfig().setPlatformMixConfig(getPanelPlatformMixConfig());
        viewBlurProxy.getBlurConfig().setBlurRadius(NotifiAndQsPlatformBlurExKt.panelBlurRadius(this.context));
        viewBlurProxy.applyBlurConfig();
    }

    public final void updateEditMode(boolean z) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("updateEditMode() ", "=>", this.isEditMode, z), null, 8);
        this.isEditMode = z;
        this.scrimTransitionHelper.updateEditMode(z);
    }

    public final void updateWhenThemeChange() {
        ScrimControllerEx.Companion companion = ScrimControllerEx.Companion;
        companion.getClass();
        boolean z = ScrimControllerEx.isThirdTheme;
        companion.setThirdTheme(QsColorUtil.isGlobalThemeApplied(this.context));
        companion.getClass();
        if (ScrimControllerEx.isThirdTheme != z) {
            refreshBehindDrawable();
            updateBehindMixConfig();
        }
    }
}
