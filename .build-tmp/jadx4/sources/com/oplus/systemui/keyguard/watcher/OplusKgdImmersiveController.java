package com.oplus.systemui.keyguard.watcher;

import android.animation.ValueAnimator;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.collection.FloatList$;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.android.systemui.keyguard.OplusKgdImmersiveControllerEx;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.shared.model.ClockSize;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.coui.appcompat.animation.COUIEaseInterpolator;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.app.OplusWindowInfo;
import com.oplus.keyguard.OplusKeyguardUnlockAnimationControllerExImpl;
import com.oplus.keyguard.security.verify.OplusKeyguardVerifyManagerExImpl;
import com.oplus.systemui.aod.aodclock.constant.AodData;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.keyguard.OplusKgdImmersiveThemeController;
import com.oplus.systemui.keyguard.anim.OplusKgdAnimParams;
import com.oplus.systemui.keyguard.anim.OplusWallpaperAnimControllerImpl;
import com.oplus.systemui.keyguard.clockstyle.KeyguardStyleServiceCallbackImpl;
import com.oplus.systemui.keyguard.ui.viewmodel.OplusKeyguardClockViewModel;
import com.oplus.systemui.keyguard.view.OplusKgdImmersiveLockMaskView;
import com.oplus.systemui.keyguard.view.OplusKgdImmersiveMaskView;
import com.oplus.systemui.keyguard.wallpaper.OplusLockScreenWallpaperController;
import com.oplus.systemui.keyguard.wallpaper.OplusWallpaperBitmapUtil;
import com.oplus.systemui.keyguard.wallpaper.OplusWallpaperColorManager;
import com.oplus.systemui.keyguard.wallpaper.observer.DefaultKeyguardInSettingsObserver;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveViewManager;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateManager;
import com.oplus.systemui.volume.utils.ScreenUtils;
import com.oplusos.keyguard.utils.KeyguardThreadUtil;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.Deprecated;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKgdImmersiveController extends OplusKgdImmersiveControllerEx implements ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    private static final String FILE_NAME = "wallpaper_service_interact_info";
    private static final String KEY_SURFACE_SHOWING = "immersive_surface_showing";
    private static final String TAG = "OplusKgdImmersiveController";
    private final ConfigurationController configurationController;
    private final Context context;
    private final CoroutineScope coroutineScope;
    private volatile Bitmap cropBitmap;
    private ImmersiveRenderMode crossfadeInMode;
    private Function0 crossfadeOutCleanup;
    private ImmersiveRenderMode crossfadeOutMode;
    private volatile Integer currentImmersiveType;
    private final Lazy defaultKeyguardInSettingsObserver;
    public boolean delayHideSurface;
    private OplusKgdImmersiveController$handler$1 handler;
    private final kotlin.Lazy immersiveAlphaAnimation$delegate;
    private final Lazy immersiveCardControl;
    public volatile boolean isImmersiveWallpaper;
    public boolean isImmersiveWallpaperAod;
    public volatile int isImmersiveWallpaperState;
    public boolean isLockImmersive;
    public boolean isPipelineCrossfading;
    private final Lazy keyguardClockViewModel;
    private final Lazy keyguardStateController;
    private final OplusKgdImmersiveController$keyguardStyleServiceCallback$1 keyguardStyleServiceCallback;
    private final Lazy keyguardUnlockAnimationController;
    private final Lazy keyguardUpdateMonitor;
    private SurfaceControl lastAttachedWallpaperSurfaceControl;
    public boolean lastSwitchedFromMusicToBitmap;
    private final Lazy lockScreenExtStateController;
    private final Lazy lockScreenNotificationExtStateManager;
    private View musicArtBackgroundView;
    private final kotlin.Lazy musicArtHostContainer$delegate;
    private ValueAnimator musicBlackMaskAnimator;
    public int musicBlackMaskAnimatorToken;
    public float musicBlackMaskRatio;
    private final kotlin.Lazy musicBlackMaskView$delegate;
    private ValueAnimator musicContentFadeAnimator;
    public int musicContentFadeToken;
    private final kotlin.Lazy musicDesktopWallpaperBgView$delegate;
    private Bitmap musicDesktopWallpaperBitmap;
    public int musicDesktopWallpaperLoadGeneration;
    public boolean musicExitShouldClearState;
    private SurfaceControlViewHost musicSurfaceControlViewHost;
    private final Lazy nswv;
    private Runnable onUiStateChangedRunnable;
    private final Lazy oplusImmersiveViewManager;
    private final Lazy oplusKeyguardVerifyManager;
    private final Lazy oplusKgdImmersiveLockMaskView;
    private final Lazy oplusKgdImmersiveMaskView;
    private final Lazy oplusKgdImmersiveThemeController;
    private final Lazy oplusLockScreenWallpaperController;
    public volatile boolean pendingBitmapEnterMessage;
    public boolean pendingBitmapSurfaceAttach;
    public boolean pendingBitmapToMusicCrossfade;
    public boolean pendingMusicScreenOffEnter;
    public boolean pendingMusicSurfaceAttach;
    public boolean pendingMusicToBitmapCrossfade;
    private final kotlin.Lazy pipelineCrossfadeAnimation$delegate;
    private volatile Runnable restoreMusicAfterWorkShopRunnable;
    private SharedPreferences sharedPrefs;
    private final Lazy statusBarStateControllerImpl;
    private SurfaceControlViewHost surfaceControlViewHost;
    public volatile int typeChangeLoadGeneration;
    private final UncaughtExceptionPreHandlerManager uncaughtExceptionPreHandlerManager;
    private final KeyguardUpdateMonitorCallback updateMonitorCallback;
    public volatile boolean useDesktopWallpaperUnderlayForLockEnter;
    private final UserFileManager userFileManager;
    private final UserTracker userTracker;
    private final WakefulnessLifecycle.Observer wakeUpObserver;
    private final Lazy wakefulnessLifecycle;
    private final Lazy wallpaperAnimController;
    private final OplusKgdImmersiveController$wallpaperChangeCallback$1 wallpaperChangeCallback;
    public float wallpaperHScale;
    private Point wallpaperOffset;
    public int wallpaperOrientation;
    public float wallpaperVScale;
    public float surfaceAlpha = 1.0f;
    private SmoothTransitionController.SmoothTransitionUiState lastUiState = SmoothTransitionController.SmoothTransitionUiState.KEYGUARD;
    private volatile ImmersiveRenderMode immersiveRenderMode = ImmersiveRenderMode.NONE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class ImmersiveRenderMode {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ ImmersiveRenderMode[] $VALUES;
        public static final ImmersiveRenderMode BITMAP;
        public static final ImmersiveRenderMode MUSIC_VIEW;
        public static final ImmersiveRenderMode NONE;

        static {
            ImmersiveRenderMode immersiveRenderMode = new ImmersiveRenderMode("NONE", 0);
            NONE = immersiveRenderMode;
            ImmersiveRenderMode immersiveRenderMode2 = new ImmersiveRenderMode("BITMAP", 1);
            BITMAP = immersiveRenderMode2;
            ImmersiveRenderMode immersiveRenderMode3 = new ImmersiveRenderMode("MUSIC_VIEW", 2);
            MUSIC_VIEW = immersiveRenderMode3;
            ImmersiveRenderMode[] immersiveRenderModeArr = {immersiveRenderMode, immersiveRenderMode2, immersiveRenderMode3};
            $VALUES = immersiveRenderModeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(immersiveRenderModeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static ImmersiveRenderMode valueOf(String str) {
            return (ImmersiveRenderMode) Enum.valueOf(ImmersiveRenderMode.class, str);
        }

        public static ImmersiveRenderMode[] values() {
            return (ImmersiveRenderMode[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ImmersiveRenderMode.values().length];
            try {
                ImmersiveRenderMode immersiveRenderMode = ImmersiveRenderMode.NONE;
                iArr[2] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                ImmersiveRenderMode immersiveRenderMode2 = ImmersiveRenderMode.NONE;
                iArr[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                ImmersiveRenderMode immersiveRenderMode3 = ImmersiveRenderMode.NONE;
                iArr[0] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$init$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00021 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ OplusKgdImmersiveController this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$init$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            public final class C00031 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public /* synthetic */ OplusKgdImmersiveController this$0;

                public /* synthetic */ C00031(int i) {
                    this.$r8$classId = i;
                }

                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            OplusKeyguardClockViewModel.ClockSizeState clockSizeState = (OplusKeyguardClockViewModel.ClockSizeState) obj;
                            OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
                            ImmersiveCardControl immersiveCardControl = (ImmersiveCardControl) oplusKgdImmersiveController.immersiveCardControl.get();
                            OplusKgdImmersiveController.access$handleImmersiveTypeChange(oplusKgdImmersiveController, immersiveCardControl.currImmersiveBgType((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) immersiveCardControl.lockScreenExtStateController.get()).getExtStateEvent().getValue()));
                            if (!((DefaultKeyguardInSettingsObserver) oplusKgdImmersiveController.defaultKeyguardInSettingsObserver.get()).isDefaultKeyguard) {
                                ((OplusKgdImmersiveThemeController) oplusKgdImmersiveController.oplusKgdImmersiveThemeController.get()).setClockSize(clockSizeState.getClockSize());
                            }
                            break;
                        default:
                            OplusKgdImmersiveController.access$onLockScreenWallpaperInfoChanged(this.this$0, (OplusWindowInfo) obj);
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00021(OplusKgdImmersiveController oplusKgdImmersiveController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusKgdImmersiveController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00021(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow clockSizeStateFlow = ((OplusKeyguardClockViewModel) this.this$0.keyguardClockViewModel.get()).getClockSizeStateFlow();
                    OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
                    C00031 c00031 = new C00031(0);
                    c00031.this$0 = oplusKgdImmersiveController;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (clockSizeStateFlow.collect(c00031, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                Regex$.ExternalSyntheticBUOutline0.m$1();
                return null;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = OplusKgdImmersiveController.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            CoroutineTracingKt.launchTraced$default(coroutineScope, "OplusKgdImmersiveController-launch-clockSizeFlow", (CoroutineContext) null, (CoroutineStart) null, new C00021(OplusKgdImmersiveController.this, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$init$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$init$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ OplusKgdImmersiveController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(OplusKgdImmersiveController oplusKgdImmersiveController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusKgdImmersiveController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                int i2 = 1;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow lockScreenWallpaperWindowInfoFlow = ((OplusWallpaperAnimControllerImpl) this.this$0.wallpaperAnimController.get()).getLockScreenWallpaperWindowInfoFlow();
                    OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
                    AnonymousClass1.C00021.C00031 c00031 = new AnonymousClass1.C00021.C00031(i2);
                    c00031.this$0 = oplusKgdImmersiveController;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (lockScreenWallpaperWindowInfoFlow.collect(c00031, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                Regex$.ExternalSyntheticBUOutline0.m$1();
                return null;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = OplusKgdImmersiveController.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            CoroutineTracingKt.launchTraced$default(coroutineScope, "OplusKgdImmersiveController-wallpaperInfoFlow", (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(OplusKgdImmersiveController.this, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$init$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements Thread.UncaughtExceptionHandler {
        public /* synthetic */ OplusKgdImmersiveController this$0;

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public final void uncaughtException(Thread thread, Throwable th) {
            if (Intrinsics.areEqual(thread, Looper.getMainLooper().getThread())) {
                KgdLogUtil.i(OplusKgdImmersiveController.TAG, "systemui process exiting");
                this.this$0.safeReleaseActiveImmersiveSurface();
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$saveSurfaceStateToSp$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC01781 implements Runnable {
        public /* synthetic */ boolean $showing;
        public /* synthetic */ OplusKgdImmersiveController this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.sharedPrefs.edit().putBoolean(OplusKgdImmersiveController.KEY_SURFACE_SHOWING, this.$showing).apply();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$updateUiSate$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC01792 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object $uiState;
        public /* synthetic */ OplusKgdImmersiveController this$0;

        public /* synthetic */ RunnableC01792(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.updateUiStateInner((SmoothTransitionController.SmoothTransitionUiState) this.$uiState);
                    break;
                case 1:
                    post((OplusKgdImmersiveController$changeBitmapForImmersiveType$1.AnonymousClass1) this.$uiState);
                    break;
                case 2:
                    this.this$0.updateMusicArtBg((View) this.$uiState);
                    break;
                default:
                    KgdLogUtil.i(OplusKgdImmersiveController.TAG, "onWallpaperBitmapChanged");
                    Integer num = this.this$0.currentImmersiveType;
                    if (!((OplusKeyguardVerifyManagerExImpl) this.this$0.oplusKeyguardVerifyManager.get()).isSystemReboot()) {
                        if ((num != null && num.intValue() == -1) || (num != null && num.intValue() == 1)) {
                            Bitmap bitmapScaleAndBlurBitmap = OplusWallpaperBitmapUtil.scaleAndBlurBitmap((Bitmap) this.$uiState, 4);
                            OplusKgdImmersiveController$handler$1 oplusKgdImmersiveController$handler$1 = this.this$0.handler;
                            OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
                            updateImmersiveLockScreenWallpaper.1.1 r4 = new updateImmersiveLockScreenWallpaper.1.1(1);
                            r4.$bitmap = bitmapScaleAndBlurBitmap;
                            r4.this$0 = oplusKgdImmersiveController;
                            r4.$effectiveType = num;
                            VarHandle.storeStoreFence();
                            oplusKgdImmersiveController$handler$1.post(r4);
                        }
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$-AiPaDSWlkXjWauBt9aAUN3LJ6w, reason: not valid java name */
    public static FrameLayout m16$r8$lambda$AiPaDSWlkXjWauBt9aAUN3LJ6w(OplusKgdImmersiveController oplusKgdImmersiveController) {
        FrameLayout frameLayout = new FrameLayout(oplusKgdImmersiveController.context);
        frameLayout.setVisibility(8);
        frameLayout.setBackgroundColor(-16777216);
        return frameLayout;
    }

    public static Unit $r8$lambda$8sLo_a7lFtj6ZNiyrZ7rTp82JA0(OplusKgdImmersiveController oplusKgdImmersiveController, OplusKgdImmersiveMaskView oplusKgdImmersiveMaskView, Bitmap bitmap, Bitmap bitmap2) {
        if (!oplusKgdImmersiveController.isBitmapLockEnterValid()) {
            return Unit.INSTANCE;
        }
        KgdLogUtil.i(TAG, "startBitmapOpaqueLockEnter desktop->immersive transition");
        oplusKgdImmersiveMaskView.setImageBitmap(bitmap2);
        OplusKgdImmersiveController$.ExternalSyntheticLambda12 externalSyntheticLambda12 = new OplusKgdImmersiveController$.ExternalSyntheticLambda12();
        externalSyntheticLambda12.f$0 = oplusKgdImmersiveMaskView;
        externalSyntheticLambda12.f$1 = bitmap2;
        externalSyntheticLambda12.f$2 = bitmap;
        externalSyntheticLambda12.f$3 = oplusKgdImmersiveController;
        VarHandle.storeStoreFence();
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        int i = 1;
        OplusKgdImmersiveController$changeBitmapForImmersiveType$1.AnonymousClass1 anonymousClass1 = new OplusKgdImmersiveController$changeBitmapForImmersiveType$1.AnonymousClass1(i);
        anonymousClass1.$from = booleanRef;
        anonymousClass1.this$0 = oplusKgdImmersiveController;
        anonymousClass1.$to = externalSyntheticLambda12;
        VarHandle.storeStoreFence();
        ViewTreeObserver viewTreeObserver = oplusKgdImmersiveMaskView.getViewTreeObserver();
        RunnableC01792 runnableC01792 = new RunnableC01792(i);
        runnableC01792.this$0 = oplusKgdImmersiveController;
        runnableC01792.$uiState = anonymousClass1;
        VarHandle.storeStoreFence();
        viewTreeObserver.registerFrameCommitCallback(runnableC01792);
        oplusKgdImmersiveController.handler.postDelayed(anonymousClass1, 100L);
        return Unit.INSTANCE;
    }

    public static ImageView $r8$lambda$NB0MVb1abYZCHPTyj_3l7wrTDf4(OplusKgdImmersiveController oplusKgdImmersiveController) {
        ImageView imageView = new ImageView(oplusKgdImmersiveController.context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImportantForAccessibility(2);
        return imageView;
    }

    public static Unit $r8$lambda$ey8XmF9XxFoUZSI7bnkwOuXwK2Y(OplusKgdImmersiveMaskView oplusKgdImmersiveMaskView, Bitmap bitmap, OplusKgdImmersiveController oplusKgdImmersiveController) {
        oplusKgdImmersiveMaskView.setImageBitmap(bitmap);
        OplusKgdImmersiveMaskView oplusKgdImmersiveMaskView2 = (OplusKgdImmersiveMaskView) oplusKgdImmersiveController.oplusKgdImmersiveMaskView.get();
        oplusKgdImmersiveController.cancelMusicContentFade();
        oplusKgdImmersiveController.getImmersiveAlphaAnimation().cancel();
        oplusKgdImmersiveController.setSurfaceAlpha(1.0f);
        oplusKgdImmersiveMaskView2.setImmersiveContentAlpha(0.0f);
        int i = oplusKgdImmersiveController.musicContentFadeToken;
        int i2 = i >= 2147483646 ? 0 : i + 1;
        oplusKgdImmersiveController.musicContentFadeToken = i2;
        KgdLogUtil.i(TAG, "startBitmapOpaqueContentFadeIn");
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(300L);
        valueAnimatorOfFloat.setInterpolator(new COUIEaseInterpolator());
        startMusicOpaqueContentFadeIn.1.1 r2 = new startMusicOpaqueContentFadeIn.1.1(1);
        r2.$myToken = i2;
        r2.this$0 = oplusKgdImmersiveController;
        r2.$artView = oplusKgdImmersiveMaskView2;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(r2);
        valueAnimatorOfFloat.addListener(new startMusicOpaqueContentFadeIn.1.2(i2, oplusKgdImmersiveController, oplusKgdImmersiveMaskView2));
        oplusKgdImmersiveController.musicContentFadeAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.start();
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$nUZlDKmyAzv5F1krLT3_3S01AO8(OplusKgdImmersiveController oplusKgdImmersiveController, int i, int i2, int i3, int i4) {
        oplusKgdImmersiveController.getMusicArtHostContainer().measure(i3, i4);
        oplusKgdImmersiveController.getMusicArtHostContainer().layout(0, 0, i, i2);
        return Unit.INSTANCE;
    }

    public static View $r8$lambda$spPdYTcY2xXC5Jk0VHi4X1tAjXQ(OplusKgdImmersiveController oplusKgdImmersiveController) {
        View view = new View(oplusKgdImmersiveController.context);
        view.setBackgroundColor(-16777216);
        view.setAlpha(0.0f);
        return view;
    }

    /* JADX INFO: renamed from: $r8$lambda$wwL2-I2-cbRsDZsY0UD0h6H29M8, reason: not valid java name */
    public static Unit m17$r8$lambda$wwL2I2cbRsDZsY0UD0h6H29M8(OplusKgdImmersiveController oplusKgdImmersiveController, int i, int i2, int i3, int i4) {
        ((OplusKgdImmersiveMaskView) oplusKgdImmersiveController.oplusKgdImmersiveMaskView.get()).measure(i3, i4);
        ((OplusKgdImmersiveMaskView) oplusKgdImmersiveController.oplusKgdImmersiveMaskView.get()).layout(0, 0, i, i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r1v22, types: [com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$keyguardStyleServiceCallback$1] */
    /* JADX WARN: Type inference failed for: r2v12, types: [com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$handler$1] */
    public OplusKgdImmersiveController(Context context, Lazy lazy, Lazy lazy2, Lazy lazy3, UserFileManager userFileManager, UserTracker userTracker, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, Lazy lazy11, Lazy lazy12, ConfigurationController configurationController, Lazy lazy13, Lazy lazy14, Lazy lazy15, UncaughtExceptionPreHandlerManager uncaughtExceptionPreHandlerManager, Lazy lazy16, Lazy lazy17, CoroutineScope coroutineScope, Lazy lazy18) {
        this.context = context;
        this.oplusKgdImmersiveMaskView = lazy;
        this.oplusKgdImmersiveLockMaskView = lazy2;
        this.oplusLockScreenWallpaperController = lazy3;
        this.userFileManager = userFileManager;
        this.userTracker = userTracker;
        this.oplusKgdImmersiveThemeController = lazy4;
        this.keyguardClockViewModel = lazy5;
        this.defaultKeyguardInSettingsObserver = lazy6;
        this.nswv = lazy7;
        this.keyguardUpdateMonitor = lazy8;
        this.keyguardStateController = lazy9;
        this.lockScreenExtStateController = lazy10;
        this.wallpaperAnimController = lazy11;
        this.wakefulnessLifecycle = lazy12;
        this.configurationController = configurationController;
        this.lockScreenNotificationExtStateManager = lazy13;
        this.immersiveCardControl = lazy14;
        this.statusBarStateControllerImpl = lazy15;
        this.uncaughtExceptionPreHandlerManager = uncaughtExceptionPreHandlerManager;
        this.keyguardUnlockAnimationController = lazy16;
        this.oplusImmersiveViewManager = lazy17;
        this.coroutineScope = coroutineScope;
        this.oplusKeyguardVerifyManager = lazy18;
        this.sharedPrefs = userFileManager.getSharedPreferences(FILE_NAME, 0, userTracker.getUserId());
        OplusKgdImmersiveController$$ExternalSyntheticLambda0 oplusKgdImmersiveController$$ExternalSyntheticLambda0 = new OplusKgdImmersiveController$$ExternalSyntheticLambda0(0);
        oplusKgdImmersiveController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.musicArtHostContainer$delegate = LazyKt__LazyJVMKt.lazy(oplusKgdImmersiveController$$ExternalSyntheticLambda0);
        OplusKgdImmersiveController$$ExternalSyntheticLambda0 oplusKgdImmersiveController$$ExternalSyntheticLambda1 = new OplusKgdImmersiveController$$ExternalSyntheticLambda0(2);
        oplusKgdImmersiveController$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.musicDesktopWallpaperBgView$delegate = LazyKt__LazyJVMKt.lazy(oplusKgdImmersiveController$$ExternalSyntheticLambda1);
        OplusKgdImmersiveController$$ExternalSyntheticLambda0 oplusKgdImmersiveController$$ExternalSyntheticLambda2 = new OplusKgdImmersiveController$$ExternalSyntheticLambda0(3);
        oplusKgdImmersiveController$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.musicBlackMaskView$delegate = LazyKt__LazyJVMKt.lazy(oplusKgdImmersiveController$$ExternalSyntheticLambda2);
        this.currentImmersiveType = 0;
        this.wallpaperOrientation = -1;
        this.wallpaperHScale = 1.0f;
        this.wallpaperVScale = 1.0f;
        this.wallpaperOffset = new Point(0, 0);
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$handler$1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i = message.what;
                if (i != 101) {
                    if (i != 103) {
                        return;
                    }
                    this.this$0.updateImmersiveLockScreenWallpaper(null, 3, null);
                    return;
                }
                this.this$0.pendingBitmapEnterMessage = false;
                if (!this.this$0.isImmersiveWallpaper) {
                    KgdLogUtil.w("OplusKgdImmersiveController", "MSG_ENTER_WALLPAPER return,not set isImmersiveWallpaper");
                    this.this$0.clearCropBitmap(false);
                    return;
                }
                Bitmap bitmap = this.this$0.cropBitmap;
                if (bitmap != null) {
                    OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
                    Object obj = message.obj;
                    boolean zAreEqual = Intrinsics.areEqual(obj instanceof Boolean ? (Boolean) obj : null, Boolean.TRUE);
                    if (zAreEqual) {
                        oplusKgdImmersiveController.getImmersiveAlphaAnimation().cancel();
                    }
                    oplusKgdImmersiveController.enterImmersiveLockScreenWallpaper(bitmap, zAreEqual);
                }
            }
        };
        OplusKgdImmersiveController$wallpaperChangeCallback$1 oplusKgdImmersiveController$wallpaperChangeCallback$1 = new OplusKgdImmersiveController$wallpaperChangeCallback$1();
        oplusKgdImmersiveController$wallpaperChangeCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.wallpaperChangeCallback = oplusKgdImmersiveController$wallpaperChangeCallback$1;
        OplusKgdImmersiveController$updateMonitorCallback$1 oplusKgdImmersiveController$updateMonitorCallback$1 = new OplusKgdImmersiveController$updateMonitorCallback$1();
        oplusKgdImmersiveController$updateMonitorCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.updateMonitorCallback = oplusKgdImmersiveController$updateMonitorCallback$1;
        OplusKgdImmersiveController$wakeUpObserver$1 oplusKgdImmersiveController$wakeUpObserver$1 = new OplusKgdImmersiveController$wakeUpObserver$1();
        oplusKgdImmersiveController$wakeUpObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.wakeUpObserver = oplusKgdImmersiveController$wakeUpObserver$1;
        OplusKgdImmersiveController$$ExternalSyntheticLambda0 oplusKgdImmersiveController$$ExternalSyntheticLambda3 = new OplusKgdImmersiveController$$ExternalSyntheticLambda0(4);
        oplusKgdImmersiveController$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.immersiveAlphaAnimation$delegate = LazyKt__LazyJVMKt.lazy(oplusKgdImmersiveController$$ExternalSyntheticLambda3);
        OplusKgdImmersiveController$$ExternalSyntheticLambda0 oplusKgdImmersiveController$$ExternalSyntheticLambda4 = new OplusKgdImmersiveController$$ExternalSyntheticLambda0(5);
        oplusKgdImmersiveController$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.pipelineCrossfadeAnimation$delegate = LazyKt__LazyJVMKt.lazy(oplusKgdImmersiveController$$ExternalSyntheticLambda4);
        this.keyguardStyleServiceCallback = new KeyguardStyleServiceCallbackImpl() { // from class: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$keyguardStyleServiceCallback$1
            public void onUiStateChanged(SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState, boolean z) {
                this.this$0.updateUiSate(smoothTransitionUiState);
            }
        };
    }

    public static final void access$applyPipelineCrossfadeProgress(OplusKgdImmersiveController oplusKgdImmersiveController, float f) {
        ImmersiveRenderMode immersiveRenderMode = oplusKgdImmersiveController.crossfadeInMode;
        int i = immersiveRenderMode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[immersiveRenderMode.ordinal()];
        if (i != 1) {
            f = i != 2 ? 1.0f : 1.0f - f;
        }
        setHostSurfaceAlpha(oplusKgdImmersiveController.hostForRenderMode(ImmersiveRenderMode.MUSIC_VIEW), f);
    }

    public static final void access$ensureBitmapImmersiveSurfaceVisible(OplusKgdImmersiveController oplusKgdImmersiveController) {
        if (!oplusKgdImmersiveController.isImmersiveWallpaper || oplusKgdImmersiveController.isImmersiveWallpaperAod) {
            return;
        }
        oplusKgdImmersiveController.immersiveRenderMode = ImmersiveRenderMode.BITMAP;
        boolean z = oplusKgdImmersiveController.isLockImmersive;
        if (oplusKgdImmersiveController.surfaceControlViewHost == null) {
            Bitmap bitmap = oplusKgdImmersiveController.cropBitmap;
            if (bitmap != null) {
                ((OplusKgdImmersiveMaskView) oplusKgdImmersiveController.oplusKgdImmersiveMaskView.get()).setImageBitmap(bitmap);
            }
            if (!setMaskViewToSurface$default(oplusKgdImmersiveController, z, 2)) {
                return;
            }
        } else {
            ((OplusKgdImmersiveMaskView) oplusKgdImmersiveController.oplusKgdImmersiveMaskView.get()).setVisibility(0);
            oplusKgdImmersiveController.attachHostSurfaceToWallpaper(oplusKgdImmersiveController.surfaceControlViewHost, oplusKgdImmersiveController.isWallpaperMatrixValid(), true);
        }
        if (oplusKgdImmersiveController.surfaceAlpha >= 1.0f) {
            oplusKgdImmersiveController.makeSureSurfaceVisible();
        } else {
            oplusKgdImmersiveController.getImmersiveAlphaAnimation().cancel();
            oplusKgdImmersiveController.immersiveAnimateTo(oplusKgdImmersiveController.surfaceAlpha, 1.0f, false);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0021, code lost:
    
        if (r0.intValue() != 2) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x003d, code lost:
    
        if (r0.intValue() != 1) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void access$finishPipelineCrossfade(com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController r8) {
        /*
            com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$ImmersiveRenderMode r0 = r8.crossfadeInMode
            java.lang.String r1 = ",in="
            java.lang.String r2 = "OplusKgdImmersiveController"
            r3 = 0
            if (r0 != 0) goto Lb
            goto L97
        Lb:
            int r0 = r0.ordinal()
            if (r0 == 0) goto L97
            r4 = 1
            if (r0 == r4) goto L28
            r4 = 2
            if (r0 != r4) goto L24
            java.lang.Integer r0 = r8.currentImmersiveType
            if (r0 != 0) goto L1d
            goto L97
        L1d:
            int r0 = r0.intValue()
            if (r0 != r4) goto L97
            goto L3f
        L24:
            kotlin.LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m()
            return
        L28:
            java.lang.Integer r0 = r8.currentImmersiveType
            if (r0 != 0) goto L2d
            goto L34
        L2d:
            int r0 = r0.intValue()
            r5 = -1
            if (r0 == r5) goto L3f
        L34:
            java.lang.Integer r0 = r8.currentImmersiveType
            if (r0 != 0) goto L39
            goto L97
        L39:
            int r0 = r0.intValue()
            if (r0 != r4) goto L97
        L3f:
            com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$ImmersiveRenderMode r0 = r8.crossfadeOutMode
            com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$ImmersiveRenderMode r4 = r8.crossfadeInMode
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "finishPipelineCrossfade out="
            r5.<init>(r6)
            r5.append(r0)
            r5.append(r1)
            r5.append(r4)
            java.lang.String r0 = r5.toString()
            com.oplusos.keyguard.utils.KgdLogUtil.i(r2, r0)
            kotlin.jvm.functions.Function0 r0 = r8.crossfadeOutCleanup
            if (r0 == 0) goto L61
            r0.invoke()
        L61:
            r8.crossfadeOutCleanup = r3
            com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$ImmersiveRenderMode r0 = r8.crossfadeInMode
            if (r0 != 0) goto L69
            com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$ImmersiveRenderMode r0 = com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController.ImmersiveRenderMode.NONE
        L69:
            r8.crossfadeOutMode = r3
            r8.crossfadeInMode = r3
            r1 = 0
            r8.isPipelineCrossfading = r1
            r8.pendingMusicToBitmapCrossfade = r1
            r8.pendingBitmapToMusicCrossfade = r1
            r8.immersiveRenderMode = r0
            r2 = 1065353216(0x3f800000, float:1.0)
            r8.surfaceAlpha = r2
            android.view.SurfaceControlViewHost r3 = r8.hostForRenderMode(r0)
            setHostSurfaceAlpha(r3, r2)
            int r2 = r8.isImmersiveWallpaperState
            r3 = 3
            if (r2 != r3) goto L96
            r8.isLockImmersive = r1
            r1 = 4
            r8.setImmersiveWallpaperState(r1)
            r8.dismissMusicScreenshotOverlay()
            com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$ImmersiveRenderMode r1 = com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController.ImmersiveRenderMode.BITMAP
            if (r0 != r1) goto L96
            r8.applyBitmapBlackMaskForCurrentUiState()
        L96:
            return
        L97:
            com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$ImmersiveRenderMode r0 = r8.crossfadeOutMode
            com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$ImmersiveRenderMode r4 = r8.crossfadeInMode
            java.lang.Integer r5 = r8.currentImmersiveType
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "finishPipelineCrossfade stale, abort instead out="
            r6.<init>(r7)
            r6.append(r0)
            r6.append(r1)
            r6.append(r4)
            java.lang.String r0 = ",currentType="
            r6.append(r0)
            r6.append(r5)
            java.lang.String r0 = r6.toString()
            com.oplusos.keyguard.utils.KgdLogUtil.w(r2, r0)
            r8.abortPipelineCrossfade(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController.access$finishPipelineCrossfade(com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController):void");
    }

    public static final void access$handleImmersiveTypeChange(OplusKgdImmersiveController oplusKgdImmersiveController, Integer num) {
        KgdLogUtil.i(TAG, "immersive type changed：from " + oplusKgdImmersiveController.currentImmersiveType + " to " + num);
        if (Intrinsics.areEqual(oplusKgdImmersiveController.currentImmersiveType, num)) {
            return;
        }
        if (oplusKgdImmersiveController.isPipelineCrossfading && num != null) {
            oplusKgdImmersiveController.abortPipelineCrossfade(resolveRenderModeForImmersiveType(num));
        }
        boolean z = oplusKgdImmersiveController.immersiveRenderMode == ImmersiveRenderMode.MUSIC_VIEW;
        oplusKgdImmersiveController.currentImmersiveType = num;
        if ((num != null && num.intValue() == -1) || (num != null && num.intValue() == 1)) {
            if (z && oplusKgdImmersiveController.musicSurfaceControlViewHost != null) {
                oplusKgdImmersiveController.pendingMusicToBitmapCrossfade = true;
                oplusKgdImmersiveController.lastSwitchedFromMusicToBitmap = true;
                if (oplusKgdImmersiveController.isImmersiveWallpaperState >= 3) {
                    oplusKgdImmersiveController.setImmersiveWallpaperState(2);
                }
            } else if (z) {
                oplusKgdImmersiveController.stopMusicImmersiveWallpaper(true);
                oplusKgdImmersiveController.lastSwitchedFromMusicToBitmap = true;
                if (oplusKgdImmersiveController.isImmersiveWallpaperState >= 3) {
                    oplusKgdImmersiveController.setImmersiveWallpaperState(2);
                }
            }
        }
        if ((num != null && num.intValue() == -1) || (num != null && num.intValue() == 1)) {
            oplusKgdImmersiveController.removeMessage();
            int i = oplusKgdImmersiveController.typeChangeLoadGeneration;
            KeyguardThreadUtil.Companion companion = KeyguardThreadUtil.Companion;
            OplusKgdImmersiveController$handleImmersiveTypeChange$1 oplusKgdImmersiveController$handleImmersiveTypeChange$1 = new OplusKgdImmersiveController$handleImmersiveTypeChange$1();
            oplusKgdImmersiveController$handleImmersiveTypeChange$1.this$0 = oplusKgdImmersiveController;
            oplusKgdImmersiveController$handleImmersiveTypeChange$1.$loadGeneration = i;
            VarHandle.storeStoreFence();
            companion.runOnWorkThread(oplusKgdImmersiveController$handleImmersiveTypeChange$1);
        } else if (num != null && num.intValue() == 2) {
            oplusKgdImmersiveController.removeMessage();
            ImmersiveRenderMode immersiveRenderMode = oplusKgdImmersiveController.immersiveRenderMode;
            ImmersiveRenderMode immersiveRenderMode2 = ImmersiveRenderMode.BITMAP;
            if (immersiveRenderMode == immersiveRenderMode2 && oplusKgdImmersiveController.surfaceControlViewHost != null) {
                oplusKgdImmersiveController.pendingBitmapToMusicCrossfade = true;
            } else if (oplusKgdImmersiveController.immersiveRenderMode == immersiveRenderMode2) {
                oplusKgdImmersiveController.releaseMaskViewSurface();
                oplusKgdImmersiveController.immersiveRenderMode = ImmersiveRenderMode.NONE;
            }
        } else if (num == null) {
            oplusKgdImmersiveController.removeMessage();
            Message messageObtain = Message.obtain();
            messageObtain.what = 103;
            oplusKgdImmersiveController.handler.sendMessage(messageObtain);
        }
        if (num == null || num.intValue() == 2) {
            return;
        }
        oplusKgdImmersiveController.replaceAOD(oplusKgdImmersiveController.lastUiState);
    }

    public static final void access$onLockScreenWallpaperInfoChanged(OplusKgdImmersiveController oplusKgdImmersiveController, OplusWindowInfo oplusWindowInfo) {
        Point point;
        if (oplusKgdImmersiveController.surfaceControlViewHost == null && oplusKgdImmersiveController.musicSurfaceControlViewHost == null) {
            return;
        }
        if ((oplusWindowInfo != null ? oplusWindowInfo.mSurfaceControl : null) == null) {
            return;
        }
        if (Intrinsics.areEqual(oplusWindowInfo.mSurfaceControl, oplusKgdImmersiveController.lastAttachedWallpaperSurfaceControl)) {
            OplusWindowInfo oplusWindowInfo2 = (OplusWindowInfo) ((OplusWallpaperAnimControllerImpl) oplusKgdImmersiveController.wallpaperAnimController.get()).getLockScreenWallpaperWindowInfoFlow().getValue();
            Bundle bundle = oplusWindowInfo2 != null ? oplusWindowInfo2.extension : null;
            float f = bundle != null ? bundle.getFloat("HScale", 1.0f) : 1.0f;
            float f2 = bundle != null ? bundle.getFloat("VScale", 1.0f) : 1.0f;
            if ((oplusKgdImmersiveController.surfaceControlViewHost == null && oplusKgdImmersiveController.musicSurfaceControlViewHost == null) || (f == oplusKgdImmersiveController.wallpaperHScale && f2 == oplusKgdImmersiveController.wallpaperVScale)) {
                OplusWindowInfo oplusWindowInfo3 = (OplusWindowInfo) ((OplusWallpaperAnimControllerImpl) oplusKgdImmersiveController.wallpaperAnimController.get()).getLockScreenWallpaperWindowInfoFlow().getValue();
                Bundle bundle2 = oplusWindowInfo3 != null ? oplusWindowInfo3.extension : null;
                if (bundle2 == null || (point = (Point) bundle2.getParcelable("offset", Point.class)) == null) {
                    point = new Point(0, 0);
                }
                if (oplusKgdImmersiveController.surfaceControlViewHost == null && oplusKgdImmersiveController.musicSurfaceControlViewHost == null) {
                    return;
                }
                if (oplusKgdImmersiveController.shouldSwapWallpaperExt(point, oplusKgdImmersiveController.context.getResources().getConfiguration().orientation)) {
                    point = new Point(point.y, point.x);
                }
                int i = point.x;
                Point point2 = oplusKgdImmersiveController.wallpaperOffset;
                if (i == point2.x && point.y == point2.y) {
                    return;
                }
            }
        }
        KgdLogUtil.i(TAG, "wallpaper component changed, re-attach");
        if (oplusKgdImmersiveController.immersiveRenderMode == ImmersiveRenderMode.MUSIC_VIEW && oplusKgdImmersiveController.musicSurfaceControlViewHost != null) {
            oplusKgdImmersiveController.attachHostSurfaceToWallpaper(oplusKgdImmersiveController.musicSurfaceControlViewHost, oplusKgdImmersiveController.isWallpaperMatrixValid(), true);
        } else if (oplusKgdImmersiveController.surfaceControlViewHost != null) {
            oplusKgdImmersiveController.attachHostSurfaceToWallpaper(oplusKgdImmersiveController.surfaceControlViewHost, oplusKgdImmersiveController.isWallpaperMatrixValid(), true);
        }
    }

    public static final void access$resetWallpaperComponent(OplusKgdImmersiveController oplusKgdImmersiveController) {
        Object systemService = oplusKgdImmersiveController.context.getSystemService("wallpaper");
        WallpaperManager wallpaperManager = systemService instanceof WallpaperManager ? (WallpaperManager) systemService : null;
        if (wallpaperManager == null) {
            KgdLogUtil.e(TAG, "wallpaperManager is null");
            return;
        }
        boolean zBooleanValue = ((Boolean) ((OplusLockScreenWallpaperController) oplusKgdImmersiveController.oplusLockScreenWallpaperController.get()).isLockScreenWallpaperEnableFlow().getValue()).booleanValue();
        WallpaperInfo wallpaperInfo = wallpaperManager.getWallpaperInfo(zBooleanValue ? 2 : 1, oplusKgdImmersiveController.userTracker.getUserId());
        KgdLogUtil.i(TAG, "lockscreenWallpaperInfo:" + wallpaperInfo);
        ComponentName component = wallpaperInfo != null ? wallpaperInfo.getComponent() : null;
        if (component == null) {
            KgdLogUtil.e(TAG, "lsWpComponentName is null");
            return;
        }
        int i = zBooleanValue ? 2 : 3;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusKgdImmersiveController-->set wallpaper");
        }
        wallpaperManager.setWallpaperComponentWithFlags(new ComponentName("com.android.systemui", "com.android.systemui.wallpapers.ImageWallpaper"), i);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusKgdImmersiveController-->restore wallpaper");
        }
        wallpaperManager.setWallpaperComponentWithFlags(component, i, oplusKgdImmersiveController.userTracker.getUserId());
    }

    public static final void access$scheduleRestoreMusicImmersiveAfterWorkShopAod(OplusKgdImmersiveController oplusKgdImmersiveController) {
        Runnable runnable = oplusKgdImmersiveController.restoreMusicAfterWorkShopRunnable;
        if (runnable != null) {
            oplusKgdImmersiveController.handler.removeCallbacks(runnable);
        }
        oplusKgdImmersiveController.restoreMusicAfterWorkShopRunnable = null;
        long notificationWorkShopFadeInStartDelay = OplusKgdAnimParams.INSTANCE.getNotificationWorkShopFadeInStartDelay(oplusKgdImmersiveController.context);
        KgdLogUtil.i(TAG, "scheduleRestoreMusicImmersiveAfterWorkShopAod delayMs=" + notificationWorkShopFadeInStartDelay);
        OplusKgdImmersiveController$exitWorkShopAod$1 oplusKgdImmersiveController$exitWorkShopAod$1 = new OplusKgdImmersiveController$exitWorkShopAod$1(1);
        oplusKgdImmersiveController$exitWorkShopAod$1.this$0 = oplusKgdImmersiveController;
        VarHandle.storeStoreFence();
        oplusKgdImmersiveController.restoreMusicAfterWorkShopRunnable = oplusKgdImmersiveController$exitWorkShopAod$1;
        oplusKgdImmersiveController.handler.postDelayed(oplusKgdImmersiveController$exitWorkShopAod$1, notificationWorkShopFadeInStartDelay);
    }

    public static final boolean access$shouldDelayHideSurface(OplusKgdImmersiveController oplusKgdImmersiveController) {
        SmoothTransitionController.SmoothTransitionUiState currentUiState;
        if (oplusKgdImmersiveController.isImmersiveWallpaper) {
            return (((KeyguardUpdateMonitor) oplusKgdImmersiveController.keyguardUpdateMonitor.get()).mGoingToSleep || (currentUiState = SmoothTransitionController.Companion.getInstance(oplusKgdImmersiveController.context).getCurrentUiState()) == SmoothTransitionController.SmoothTransitionUiState.PANORAMIC_AOD || currentUiState == SmoothTransitionController.SmoothTransitionUiState.WORKSHOP_AOD) ? false : true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final COUISpringAnimation getImmersiveAlphaAnimation() {
        return (COUISpringAnimation) this.immersiveAlphaAnimation$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FrameLayout getMusicArtHostContainer() {
        return (FrameLayout) this.musicArtHostContainer$delegate.getValue();
    }

    private final View getMusicBlackMaskView() {
        return (View) this.musicBlackMaskView$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageView getMusicDesktopWallpaperBgView() {
        return (ImageView) this.musicDesktopWallpaperBgView$delegate.getValue();
    }

    private final COUISpringAnimation getPipelineCrossfadeAnimation() {
        return (COUISpringAnimation) this.pipelineCrossfadeAnimation$delegate.getValue();
    }

    public static boolean isScreenOffUiReadyForMusicEnterInner(SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState) {
        return smoothTransitionUiState == SmoothTransitionController.SmoothTransitionUiState.WORKSHOP_AOD || smoothTransitionUiState == SmoothTransitionController.SmoothTransitionUiState.PANORAMIC_AOD || smoothTransitionUiState == SmoothTransitionController.SmoothTransitionUiState.NORMAL_AOD || smoothTransitionUiState == SmoothTransitionController.SmoothTransitionUiState.AOD_OFF || smoothTransitionUiState == SmoothTransitionController.SmoothTransitionUiState.KEYGUARD;
    }

    public static void recycleBitmap$1(Bitmap bitmap) {
        if (bitmap != null) {
            if (bitmap.isRecycled()) {
                bitmap = null;
            }
            if (bitmap != null) {
                bitmap.recycle();
            }
        }
    }

    public static ImmersiveRenderMode resolveRenderModeForImmersiveType(Integer num) {
        if (num != null && num.intValue() == 2) {
            return ImmersiveRenderMode.MUSIC_VIEW;
        }
        return ((num != null && num.intValue() == -1) || (num != null && num.intValue() == 1)) ? ImmersiveRenderMode.BITMAP : ImmersiveRenderMode.NONE;
    }

    public static void sendEnterImmersiveLockScreenWallpaperMsg$default(OplusKgdImmersiveController oplusKgdImmersiveController) {
        oplusKgdImmersiveController.removeMessage();
        oplusKgdImmersiveController.pendingBitmapEnterMessage = true;
        Message messageObtain = Message.obtain();
        messageObtain.what = 101;
        messageObtain.obj = false;
        oplusKgdImmersiveController.handler.sendMessage(messageObtain);
    }

    public static void setHostSurfaceAlpha(SurfaceControlViewHost surfaceControlViewHost, float f) {
        SurfaceControlViewHost.SurfacePackage surfacePackage;
        SurfaceControl surfaceControl;
        if (surfaceControlViewHost == null || (surfacePackage = surfaceControlViewHost.getSurfacePackage()) == null || (surfaceControl = surfacePackage.getSurfaceControl()) == null || !surfaceControl.isValid()) {
            return;
        }
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        try {
            transaction.setAlpha(surfaceControl, RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f)).apply();
            CloseableKt.closeFinally(transaction, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(transaction, th);
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setImmersiveWallpaperState(int i) {
        OplusLockIconView$.ExternalSyntheticOutline0.m(i, "set isImmersiveWallpaperState:", TAG);
        this.isImmersiveWallpaperState = i;
    }

    public static /* synthetic */ boolean setMaskViewToSurface$default(OplusKgdImmersiveController oplusKgdImmersiveController, boolean z, int i) {
        if ((i & 1) != 0) {
            z = false;
        }
        return oplusKgdImmersiveController.setMaskViewToSurface(z, true);
    }

    public static /* synthetic */ boolean setMusicArtViewToSurface$default(OplusKgdImmersiveController oplusKgdImmersiveController, boolean z, int i) {
        if ((i & 1) != 0) {
            z = false;
        }
        return oplusKgdImmersiveController.setMusicArtViewToSurface(z, (i & 2) != 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setMusicBlackMaskRatio(float f) {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        if (Math.abs(fCoerceIn - this.musicBlackMaskRatio) < 0.001f) {
            updateMusicBlackMaskAlpha(fCoerceIn);
            return;
        }
        float f2 = this.musicBlackMaskRatio;
        int i = this.musicBlackMaskAnimatorToken;
        int i2 = i >= 2147483646 ? 0 : i + 1;
        this.musicBlackMaskAnimatorToken = i2;
        ValueAnimator valueAnimator = this.musicBlackMaskAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        KgdLogUtil.i(TAG, "setMusicBlackMaskRatio ratio=" + fCoerceIn + ",curr=" + f2);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f2, fCoerceIn);
        valueAnimatorOfFloat.setDuration(300L);
        valueAnimatorOfFloat.setInterpolator(new COUIEaseInterpolator());
        setMusicBlackMaskRatio.1.1 r2 = new setMusicBlackMaskRatio.1.1();
        r2.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(r2);
        valueAnimatorOfFloat.addListener(new setMusicBlackMaskRatio.1.2(i2, this, fCoerceIn));
        this.musicBlackMaskAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:16:0x0023  */
    public final void setSurfaceAlpha(float f) {
        SurfaceControl surfaceControl;
        SurfaceControlViewHost.SurfacePackage surfacePackage;
        SurfaceControlViewHost.SurfacePackage surfacePackage2;
        if (this.isPipelineCrossfading) {
            return;
        }
        int iOrdinal = this.immersiveRenderMode.ordinal();
        if (iOrdinal == 0) {
            surfaceControl = null;
        } else if (iOrdinal == 1) {
            SurfaceControlViewHost surfaceControlViewHost = this.surfaceControlViewHost;
            if (surfaceControlViewHost == null || (surfacePackage = surfaceControlViewHost.getSurfacePackage()) == null) {
                surfaceControl = null;
            } else {
                surfaceControl = surfacePackage.getSurfaceControl();
            }
        } else {
            if (iOrdinal != 2) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return;
            }
            SurfaceControlViewHost surfaceControlViewHost2 = this.musicSurfaceControlViewHost;
            if (surfaceControlViewHost2 == null || (surfacePackage2 = surfaceControlViewHost2.getSurfacePackage()) == null) {
                surfaceControl = null;
            } else {
                surfaceControl = surfacePackage2.getSurfaceControl();
            }
        }
        if (surfaceControl != null) {
            if (!surfaceControl.isValid()) {
                KgdLogUtil.i(TAG, "setSurfaceAlpha return, invalid surfaceControl");
                return;
            }
            this.surfaceAlpha = f;
            SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
            try {
                transaction.setAlpha(surfaceControl, f).apply();
                CloseableKt.closeFinally(transaction, (Throwable) null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(transaction, th);
                    throw th2;
                }
            }
        }
    }

    public static /* synthetic */ void updateImmersiveLockScreenWallpaper$default(OplusKgdImmersiveController oplusKgdImmersiveController, Bitmap bitmap, Integer num, int i) {
        int i2 = (i & 2) != 0 ? 1 : 305;
        if ((i & 4) != 0) {
            num = null;
        }
        oplusKgdImmersiveController.updateImmersiveLockScreenWallpaper(bitmap, i2, num);
    }

    public final void abortPipelineCrossfade(ImmersiveRenderMode immersiveRenderMode) {
        if (!this.isPipelineCrossfading && !((COUIDynamicAnimation) getPipelineCrossfadeAnimation()).mRunning) {
            if (immersiveRenderMode != null) {
                this.immersiveRenderMode = immersiveRenderMode;
                return;
            }
            return;
        }
        KgdLogUtil.i(TAG, "abortPipelineCrossfade preferred=" + immersiveRenderMode + ",out=" + this.crossfadeOutMode + ",in=" + this.crossfadeInMode + ",currentType=" + this.currentImmersiveType);
        getPipelineCrossfadeAnimation().cancel();
        this.crossfadeOutCleanup = null;
        this.crossfadeOutMode = null;
        this.crossfadeInMode = null;
        this.isPipelineCrossfading = false;
        this.pendingMusicToBitmapCrossfade = false;
        this.pendingBitmapToMusicCrossfade = false;
        if (immersiveRenderMode == null) {
            immersiveRenderMode = resolveRenderModeForImmersiveType(this.currentImmersiveType);
        }
        this.immersiveRenderMode = immersiveRenderMode;
        this.surfaceAlpha = 1.0f;
        int iOrdinal = immersiveRenderMode.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                setHostSurfaceAlpha(hostForRenderMode(ImmersiveRenderMode.MUSIC_VIEW), 0.0f);
            } else if (iOrdinal == 2) {
                setHostSurfaceAlpha(hostForRenderMode(ImmersiveRenderMode.MUSIC_VIEW), 1.0f);
            } else {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
            }
        }
    }

    public final void applyBitmapBlackMaskForCurrentUiState() {
        ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).updateBlackMaskAlpha(resolveBitmapMaskRatio(this.lastUiState));
    }

    public final void attachHostSurfaceToWallpaper(SurfaceControlViewHost surfaceControlViewHost, boolean z, boolean z2) {
        Point point;
        if (surfaceControlViewHost != null) {
            SurfaceControlViewHost.SurfacePackage surfacePackage = surfaceControlViewHost.getSurfacePackage();
            SurfaceControl surfaceControl = surfacePackage != null ? surfacePackage.getSurfaceControl() : null;
            OplusWindowInfo oplusWindowInfo = (OplusWindowInfo) ((OplusWallpaperAnimControllerImpl) this.wallpaperAnimController.get()).getLockScreenWallpaperWindowInfoFlow().getValue();
            SurfaceControl surfaceControl2 = oplusWindowInfo != null ? oplusWindowInfo.mSurfaceControl : null;
            if (surfaceControl == null || !surfaceControl.isValid() || surfaceControl2 == null || !surfaceControl2.isValid()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    Log.d("SystemUi--Keyguard", "OplusKgdImmersiveController-->".concat("attachHostSurfaceToWallpaper sc invalid, wallpaperSc:" + surfaceControl2));
                }
                if (surfaceControlViewHost == this.musicSurfaceControlViewHost) {
                    releaseMusicSurface();
                    return;
                } else {
                    releaseMaskViewSurface();
                    return;
                }
            }
            Bundle bundle = oplusWindowInfo.extension;
            float f = bundle != null ? bundle.getFloat("HScale", 1.0f) : 1.0f;
            float f2 = bundle != null ? bundle.getFloat("VScale", 1.0f) : 1.0f;
            if (bundle == null || (point = (Point) bundle.getParcelable("offset", Point.class)) == null) {
                point = new Point(0, 0);
            }
            resolveWallpaperTransformState(this.context.getResources().getConfiguration().orientation, f, f2, point);
            Matrix matrixBuildWallpaperInverseMatrix = z ? buildWallpaperInverseMatrix() : new Matrix();
            new SurfaceControl.Transaction().reparent(surfaceControl, surfaceControl2).setRelativeLayer(surfaceControl, surfaceControl2, surfaceControlViewHost == this.musicSurfaceControlViewHost ? 2 : 1).setMatrix(surfaceControl, matrixBuildWallpaperInverseMatrix, new float[9]).show(surfaceControl).apply();
            this.lastAttachedWallpaperSurfaceControl = surfaceControl2;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "OplusKgdImmersiveController-->".concat("show the SurfaceControl below, needTransform:" + z + " matrix:" + matrixBuildWallpaperInverseMatrix));
            }
            saveSurfaceStateToSp(true);
            if (z2) {
                makeSureSurfaceVisible();
            }
        }
    }

    public final void attachMusicImmersiveOnLockSurface(boolean z) {
        if (setMusicArtViewToSurface(false, false)) {
            setMusicBlackMaskRatio(resolveMusicMaskRatio(this.lastUiState));
            if (!z || !shouldUseOpaqueContentFadeForLockEnter()) {
                float fCoerceIn = z ? 0.0f : RangesKt___RangesKt.coerceIn(this.surfaceAlpha, 0.0f, 1.0f);
                setSurfaceAlpha(fCoerceIn);
                if (immersiveAnimateTo(fCoerceIn, 1.0f, false)) {
                    return;
                }
                makeSureSurfaceVisible();
                dismissMusicScreenshotOverlay();
                return;
            }
            View view = this.musicArtBackgroundView;
            if (view == null) {
                makeSureSurfaceVisible();
                onMusicLockEnterAnimationFinished();
                return;
            }
            cancelMusicContentFade();
            getImmersiveAlphaAnimation().cancel();
            setSurfaceAlpha(1.0f);
            view.setAlpha(0.0f);
            int i = this.musicContentFadeToken;
            int i2 = i >= 2147483646 ? 0 : i + 1;
            this.musicContentFadeToken = i2;
            KgdLogUtil.i(TAG, "startMusicOpaqueContentFadeIn");
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat.setDuration(300L);
            valueAnimatorOfFloat.setInterpolator(new COUIEaseInterpolator());
            startMusicOpaqueContentFadeIn.1.1 r3 = new startMusicOpaqueContentFadeIn.1.1(0);
            r3.$myToken = i2;
            r3.this$0 = this;
            r3.$artView = view;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addUpdateListener(r3);
            valueAnimatorOfFloat.addListener(new startMusicOpaqueContentFadeIn.1.2(i2, this, view));
            this.musicContentFadeAnimator = valueAnimatorOfFloat;
            valueAnimatorOfFloat.start();
        }
    }

    public final void bindMusicArtView(View view) {
        cancelMusicContentFade();
        if (!this.useDesktopWallpaperUnderlayForLockEnter) {
            clearDesktopWallpaperBackground();
        }
        this.musicArtBackgroundView = view;
        view.setAlpha(1.0f);
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        getMusicArtHostContainer().removeAllViews();
        getMusicArtHostContainer().addView(getMusicDesktopWallpaperBgView(), new FrameLayout.LayoutParams(-1, -1));
        getMusicArtHostContainer().addView(view, new FrameLayout.LayoutParams(-1, -1));
        getMusicArtHostContainer().addView(getMusicBlackMaskView(), new FrameLayout.LayoutParams(-1, -1));
        updateMusicBlackMaskAlpha(this.musicBlackMaskRatio);
    }

    public final Matrix buildWallpaperInverseMatrix() {
        Matrix matrix = new Matrix();
        if (this.wallpaperHScale == 0.0f) {
            this.wallpaperHScale = 1.0f;
        }
        if (this.wallpaperVScale == 0.0f) {
            this.wallpaperVScale = 1.0f;
        }
        matrix.preScale(1.0f / this.wallpaperHScale, 1.0f / this.wallpaperVScale);
        Point point = this.wallpaperOffset;
        matrix.preTranslate(-point.x, -point.y);
        return matrix;
    }

    public final void cancelMusicContentFade() {
        ValueAnimator valueAnimator = this.musicContentFadeAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.musicContentFadeAnimator = null;
    }

    public final void cancelPipelineCrossfade(boolean z) {
        if (!this.isPipelineCrossfading && !((COUIDynamicAnimation) getPipelineCrossfadeAnimation()).mRunning) {
            if (z) {
                this.pendingMusicToBitmapCrossfade = false;
                this.pendingBitmapToMusicCrossfade = false;
                return;
            }
            return;
        }
        getPipelineCrossfadeAnimation().cancel();
        this.isPipelineCrossfading = false;
        this.crossfadeOutMode = null;
        this.crossfadeInMode = null;
        this.crossfadeOutCleanup = null;
        if (z) {
            this.pendingMusicToBitmapCrossfade = false;
            this.pendingBitmapToMusicCrossfade = false;
        }
    }

    public final void checkMatrix(Configuration configuration) {
        Point point;
        SurfaceControlViewHost.SurfacePackage surfacePackage;
        SurfaceControl surfaceControl;
        SurfaceControlViewHost.SurfacePackage surfacePackage2;
        SurfaceControl surfaceControl2;
        if (configuration.orientation != this.wallpaperOrientation) {
            OplusWindowInfo oplusWindowInfo = (OplusWindowInfo) ((OplusWallpaperAnimControllerImpl) this.wallpaperAnimController.get()).getLockScreenWallpaperWindowInfoFlow().getValue();
            Bundle bundle = oplusWindowInfo != null ? oplusWindowInfo.extension : null;
            float f = bundle != null ? bundle.getFloat("HScale", 1.0f) : 1.0f;
            float f2 = bundle != null ? bundle.getFloat("VScale", 1.0f) : 1.0f;
            if (bundle == null || (point = (Point) bundle.getParcelable("offset", Point.class)) == null) {
                point = new Point(0, 0);
            }
            resolveWallpaperTransformState(configuration.orientation, f, f2, point);
            Matrix matrixBuildWallpaperInverseMatrix = isWallpaperMatrixValid() ? buildWallpaperInverseMatrix() : new Matrix();
            SurfaceControlViewHost surfaceControlViewHost = this.surfaceControlViewHost;
            if (surfaceControlViewHost != null && (surfacePackage2 = surfaceControlViewHost.getSurfacePackage()) != null && (surfaceControl2 = surfacePackage2.getSurfaceControl()) != null) {
                new SurfaceControl.Transaction().setMatrix(surfaceControl2, matrixBuildWallpaperInverseMatrix, new float[9]).apply();
            }
            SurfaceControlViewHost surfaceControlViewHost2 = this.musicSurfaceControlViewHost;
            if (surfaceControlViewHost2 != null && (surfacePackage = surfaceControlViewHost2.getSurfacePackage()) != null && (surfaceControl = surfacePackage.getSurfaceControl()) != null) {
                new SurfaceControl.Transaction().setMatrix(surfaceControl, matrixBuildWallpaperInverseMatrix, new float[9]).apply();
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "OplusKgdImmersiveController-->".concat("apply matrix:" + matrixBuildWallpaperInverseMatrix));
            }
        }
    }

    public final boolean checkWallpaperImmersive(Bitmap bitmap, Integer num) {
        if (bitmap != null && ((ILockScreenExtStateController.ExtStateEvent) ((LockScreenNotificationExtStateManager) this.lockScreenNotificationExtStateManager.get()).getExtStateEvent().getValue()).getNewState() != 2) {
            KgdLogUtil.w(TAG, "checkWallpaperImmersive return false,not immersive,bitmap=" + bitmap);
            return false;
        }
        if (this.currentImmersiveType == null || Intrinsics.areEqual(num, this.currentImmersiveType)) {
            return true;
        }
        KgdLogUtil.w(TAG, "checkWallpaperImmersive return false,type invalid");
        return false;
    }

    public final void clearCropBitmap(boolean z) {
        if (z) {
            recycleBitmap$1(this.cropBitmap);
        }
        this.cropBitmap = null;
    }

    public final void clearDesktopWallpaperBackground() {
        this.musicDesktopWallpaperLoadGeneration++;
        getMusicDesktopWallpaperBgView().setImageDrawable(null);
        Bitmap bitmap = this.musicDesktopWallpaperBitmap;
        this.musicDesktopWallpaperBitmap = null;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    public final void clearMusicImmersiveState() {
        cancelMusicContentFade();
        int i = this.musicBlackMaskAnimatorToken;
        this.musicBlackMaskAnimatorToken = i >= 2147483646 ? 0 : i + 1;
        ValueAnimator valueAnimator = this.musicBlackMaskAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.musicBlackMaskAnimator = null;
        clearDesktopWallpaperBackground();
        this.useDesktopWallpaperUnderlayForLockEnter = false;
        this.immersiveRenderMode = ImmersiveRenderMode.NONE;
        getMusicArtHostContainer().setVisibility(8);
        View view = this.musicArtBackgroundView;
        if (view != null) {
            view.setAlpha(1.0f);
        }
        this.musicArtBackgroundView = null;
    }

    public final void clearStaleMusicImmersiveState(boolean z) {
        if (this.musicArtBackgroundView == null && this.musicSurfaceControlViewHost == null && this.immersiveRenderMode != ImmersiveRenderMode.MUSIC_VIEW) {
            return;
        }
        KgdLogUtil.i(TAG, "clearStaleMusicImmersiveState keepBitmapPipeline=" + z + ",renderMode=" + this.immersiveRenderMode);
        if (this.isPipelineCrossfading && this.crossfadeInMode == ImmersiveRenderMode.MUSIC_VIEW) {
            abortPipelineCrossfade(z ? ImmersiveRenderMode.BITMAP : ImmersiveRenderMode.NONE);
        }
        this.musicExitShouldClearState = false;
        if (((COUIDynamicAnimation) getImmersiveAlphaAnimation()).mRunning && this.immersiveRenderMode == ImmersiveRenderMode.MUSIC_VIEW) {
            getImmersiveAlphaAnimation().cancel();
        }
        cancelMusicContentFade();
        int i = this.musicBlackMaskAnimatorToken;
        this.musicBlackMaskAnimatorToken = i >= 2147483646 ? 0 : i + 1;
        ValueAnimator valueAnimator = this.musicBlackMaskAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.musicBlackMaskAnimator = null;
        releaseMusicSurface();
        getMusicArtHostContainer().setVisibility(8);
        View view = this.musicArtBackgroundView;
        if (view != null) {
            view.setAlpha(1.0f);
        }
        this.musicArtBackgroundView = null;
        if (z) {
            return;
        }
        clearDesktopWallpaperBackground();
        this.useDesktopWallpaperUnderlayForLockEnter = false;
        if (this.immersiveRenderMode == ImmersiveRenderMode.MUSIC_VIEW) {
            this.immersiveRenderMode = ImmersiveRenderMode.NONE;
        }
    }

    public final void dismissMusicScreenshotOverlay() {
        OplusKgdImmersiveLockMaskView oplusKgdImmersiveLockMaskView = (OplusKgdImmersiveLockMaskView) this.oplusKgdImmersiveLockMaskView.get();
        OplusKgdImmersiveLockMaskView.Companion companion = OplusKgdImmersiveLockMaskView.Companion;
        oplusKgdImmersiveLockMaskView.dismissScreenshotOverlay(true, (Function0) null);
    }

    public final void enterImmersiveLockScreenWallpaper(Bitmap bitmap, boolean z) {
        if (this.isImmersiveWallpaperAod || !this.isImmersiveWallpaper) {
            return;
        }
        boolean z2 = z || this.isLockImmersive;
        if (this.pendingMusicToBitmapCrossfade && this.musicSurfaceControlViewHost != null) {
            ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).setImageBitmap(bitmap);
            applyBitmapBlackMaskForCurrentUiState();
            ImmersiveRenderMode immersiveRenderMode = ImmersiveRenderMode.BITMAP;
            this.immersiveRenderMode = immersiveRenderMode;
            if (!setMaskViewToSurface(z2, false)) {
                return;
            }
            ImmersiveRenderMode immersiveRenderMode2 = ImmersiveRenderMode.MUSIC_VIEW;
            OplusKgdImmersiveController$$ExternalSyntheticLambda0 oplusKgdImmersiveController$$ExternalSyntheticLambda0 = new OplusKgdImmersiveController$$ExternalSyntheticLambda0(7);
            oplusKgdImmersiveController$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            if (runPipelineCrossfade(immersiveRenderMode2, immersiveRenderMode, oplusKgdImmersiveController$$ExternalSyntheticLambda0)) {
                this.pendingMusicToBitmapCrossfade = false;
                this.lastSwitchedFromMusicToBitmap = false;
                return;
            } else {
                this.pendingMusicToBitmapCrossfade = false;
                clearStaleMusicImmersiveState(true);
            }
        }
        this.immersiveRenderMode = ImmersiveRenderMode.BITMAP;
        applyBitmapBlackMaskForCurrentUiState();
        if (this.isImmersiveWallpaperState <= 2) {
            setImmersiveWallpaperState(3);
            boolean zShouldUseOpaqueContentFadeForLockEnter = shouldUseOpaqueContentFadeForLockEnter();
            if (zShouldUseOpaqueContentFadeForLockEnter) {
                seedDesktopWallpaperBeforeAttach();
            }
            if (!setMaskViewToSurface(z2, false)) {
                ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).setImageBitmap(bitmap);
                return;
            } else if (zShouldUseOpaqueContentFadeForLockEnter) {
                startBitmapOpaqueLockEnter(bitmap);
                return;
            } else {
                ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).setImageBitmap(bitmap);
                immersiveAnimateTo(0.0f, 1.0f, false);
                return;
            }
        }
        if (this.isImmersiveWallpaperState >= 3) {
            if (shouldUseOpaqueContentFadeForLockEnter()) {
                seedDesktopWallpaperBeforeAttach();
                if (setMaskViewToSurface(z2, false)) {
                    startBitmapOpaqueLockEnter(bitmap);
                    return;
                } else {
                    ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).setImageBitmap(bitmap);
                    return;
                }
            }
            ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).setImageBitmap(bitmap);
            if (!setMaskViewToSurface$default(this, z2, 2) || this.surfaceAlpha >= 1.0f) {
                makeSureSurfaceVisible();
            } else {
                getImmersiveAlphaAnimation().cancel();
                immersiveAnimateTo(this.surfaceAlpha, 1.0f, false);
            }
        }
    }

    public final void enterMusicImmersiveLockScreenWallpaper(boolean z) {
        if (this.isImmersiveWallpaperAod) {
            return;
        }
        ImmersiveRenderMode immersiveRenderMode = this.immersiveRenderMode;
        ImmersiveRenderMode immersiveRenderMode2 = ImmersiveRenderMode.MUSIC_VIEW;
        if (immersiveRenderMode != immersiveRenderMode2) {
            return;
        }
        if (this.isLockImmersive) {
            enterMusicImmersiveOnLock(z);
            return;
        }
        getMusicArtHostContainer().setVisibility(0);
        if (!z && this.isImmersiveWallpaperState > 2 && this.musicSurfaceControlViewHost != null && this.surfaceAlpha >= 1.0f) {
            if (setMusicArtViewToSurface$default(this, false, 3)) {
                makeSureSurfaceVisible();
                return;
            }
            return;
        }
        setImmersiveWallpaperState(3);
        if (this.pendingBitmapToMusicCrossfade && this.surfaceControlViewHost != null) {
            if (!setMusicArtViewToSurface$default(this, false, 1)) {
                return;
            }
            ImmersiveRenderMode immersiveRenderMode3 = ImmersiveRenderMode.BITMAP;
            OplusKgdImmersiveController$$ExternalSyntheticLambda0 oplusKgdImmersiveController$$ExternalSyntheticLambda0 = new OplusKgdImmersiveController$$ExternalSyntheticLambda0(6);
            oplusKgdImmersiveController$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            if (runPipelineCrossfade(immersiveRenderMode3, immersiveRenderMode2, oplusKgdImmersiveController$$ExternalSyntheticLambda0)) {
                this.pendingBitmapToMusicCrossfade = false;
                return;
            } else {
                this.pendingBitmapToMusicCrossfade = false;
                releaseMaskViewSurface();
                clearCropBitmap(false);
            }
        }
        if (setMusicArtViewToSurface$default(this, false, 1)) {
            float fCoerceIn = z ? 0.0f : RangesKt___RangesKt.coerceIn(this.surfaceAlpha, 0.0f, 1.0f);
            setSurfaceAlpha(fCoerceIn);
            if (immersiveAnimateTo(fCoerceIn, 1.0f, false)) {
                return;
            }
            makeSureSurfaceVisible();
        }
    }

    public final void enterMusicImmersiveOnLock(boolean z) {
        if (!((KeyguardStateController) this.keyguardStateController.get()).mShowing) {
            this.pendingMusicSurfaceAttach = true;
            getMusicArtHostContainer().setVisibility(0);
            if (this.isImmersiveWallpaperState <= 0) {
                setImmersiveWallpaperState(2);
            }
            KgdLogUtil.i(TAG, "enterMusicImmersiveOnLock defer, keyguard not show");
            return;
        }
        this.pendingMusicSurfaceAttach = false;
        getImmersiveAlphaAnimation().cancel();
        cancelMusicContentFade();
        setImmersiveWallpaperState(3);
        getMusicArtHostContainer().setVisibility(0);
        if (isScreenOffUiReadyForMusicEnter()) {
            this.pendingMusicScreenOffEnter = false;
            attachMusicImmersiveOnLockSurface(z);
        } else {
            this.pendingMusicScreenOffEnter = true;
            KgdLogUtil.i(TAG, "defer music lock enter until screen-off ui ready");
        }
    }

    public final void exitImmersiveLockScreenWallpaper() {
        if (this.isImmersiveWallpaperState == 1) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "OplusKgdImmersiveController-->exitImmersiveLockScreenWallpaper skip, already exiting");
                return;
            }
            return;
        }
        ImmersiveRenderMode immersiveRenderMode = this.crossfadeInMode;
        boolean z = this.isPipelineCrossfading;
        if (!z) {
            immersiveRenderMode = null;
        }
        if (z) {
            cancelPipelineCrossfade(true);
            if (immersiveRenderMode == null) {
                releaseMusicSurface();
                releaseMaskViewSurface();
                this.immersiveRenderMode = ImmersiveRenderMode.NONE;
            }
        }
        removeMessage();
        dismissMusicScreenshotOverlay();
        if (!this.isImmersiveWallpaper && this.isImmersiveWallpaperState == 2) {
            KgdLogUtil.i(TAG, "exitImmersiveLockScreenWallpaper,PRE_ENTERING");
            setImmersiveWallpaperState(0);
            if (this.immersiveRenderMode == ImmersiveRenderMode.BITMAP) {
                clearCropBitmap(false);
            }
            if (this.immersiveRenderMode == ImmersiveRenderMode.MUSIC_VIEW || this.musicSurfaceControlViewHost != null) {
                releaseMusicSurface();
                getMusicArtHostContainer().setVisibility(8);
                this.musicArtBackgroundView = null;
                this.immersiveRenderMode = ImmersiveRenderMode.NONE;
                return;
            }
            return;
        }
        if (this.isImmersiveWallpaperState < 3) {
            runExitAnimAndReleaseResource();
            return;
        }
        setImmersiveWallpaperState(1);
        if (((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).mKeyguardGoingAway || ((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).mKeyguardOccluded) {
            KgdLogUtil.i(TAG, "exitImmersiveLockScreenWallpaper,isKeyguardGoingAway");
            safeReleaseActiveImmersiveSurface();
            runExitAnimAndReleaseResource();
            return;
        }
        if (immersiveRenderMode != null) {
            int iOrdinal = immersiveRenderMode.ordinal();
            if (iOrdinal == 1) {
                runBitmapExitAnimation();
                return;
            } else if (iOrdinal != 2) {
                runExitAnimAndReleaseResource();
                return;
            } else {
                runMusicExitForContext();
                return;
            }
        }
        int iOrdinal2 = this.immersiveRenderMode.ordinal();
        if (iOrdinal2 != 0) {
            if (iOrdinal2 == 1) {
                runBitmapExitAnimation();
                return;
            } else if (iOrdinal2 == 2) {
                runMusicExitForContext();
                return;
            } else {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return;
            }
        }
        if (this.surfaceControlViewHost != null) {
            runBitmapExitAnimation();
        } else if (this.musicSurfaceControlViewHost != null || this.musicArtBackgroundView != null) {
            runMusicExitForContext();
        } else {
            setMaskViewToSurface$default(this, false, 3);
            runExitAnimAndReleaseResource();
        }
    }

    public final void finishMusicImmersiveExit() {
        this.isImmersiveWallpaper = false;
        setImmersiveWallpaperState(0);
        this.musicExitShouldClearState = false;
        releaseMusicSurface();
        clearMusicImmersiveState();
        if (this.surfaceControlViewHost != null) {
            releaseMaskViewSurface();
            clearCropBitmap(true);
        }
    }

    public final void hideSpecialSurface() {
        ((OplusImmersiveViewManager) this.oplusImmersiveViewManager.get()).notifyHideSurfaceView();
    }

    public final SurfaceControlViewHost hostForRenderMode(ImmersiveRenderMode immersiveRenderMode) {
        int iOrdinal = immersiveRenderMode.ordinal();
        if (iOrdinal == 0) {
            return null;
        }
        if (iOrdinal == 1) {
            return this.surfaceControlViewHost;
        }
        if (iOrdinal == 2) {
            return this.musicSurfaceControlViewHost;
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0061  */
    /* JADX WARN: Code duplicated, block: B:26:0x0079  */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0045, code lost:
    
        if (r5.isImmersiveWallpaperState == 1) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005c, code lost:
    
        if (r5.pendingBitmapSurfaceAttach == false) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean immersiveAnimateTo(float r6, float r7, boolean r8) {
        /*
            r5 = this;
            com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$ImmersiveRenderMode r0 = r5.immersiveRenderMode
            int r1 = r5.isImmersiveWallpaperState
            java.lang.String r2 = ",endValue="
            java.lang.String r3 = ",renderMode="
            java.lang.String r4 = "immersiveAnimateTo startValue="
            java.lang.StringBuilder r2 = androidx.compose.animation.core.MonoSpline$.ExternalSyntheticOutline0.m(r4, r6, r2, r7, r3)
            r2.append(r0)
            java.lang.String r0 = ",state="
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = r2.toString()
            java.lang.String r1 = "OplusKgdImmersiveController"
            com.oplusos.keyguard.utils.KgdLogUtil.i(r1, r0)
            com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$ImmersiveRenderMode r0 = r5.immersiveRenderMode
            int r0 = r0.ordinal()
            r2 = 0
            if (r0 == 0) goto La6
            r3 = 1
            if (r0 == r3) goto L4c
            r4 = 2
            if (r0 != r4) goto L48
            android.view.View r0 = r5.musicArtBackgroundView
            if (r0 == 0) goto La6
            android.widget.FrameLayout r0 = r5.getMusicArtHostContainer()
            int r0 = r0.getVisibility()
            if (r0 == 0) goto L5e
            boolean r0 = r5.pendingMusicSurfaceAttach
            if (r0 != 0) goto L5e
            int r0 = r5.isImmersiveWallpaperState
            if (r0 != r3) goto La6
            goto L5e
        L48:
            kotlin.LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m()
            return r2
        L4c:
            dagger.Lazy r0 = r5.oplusKgdImmersiveMaskView
            java.lang.Object r0 = r0.get()
            com.oplus.systemui.keyguard.view.OplusKgdImmersiveMaskView r0 = (com.oplus.systemui.keyguard.view.OplusKgdImmersiveMaskView) r0
            int r0 = r0.getVisibility()
            if (r0 == 0) goto L5e
            boolean r0 = r5.pendingBitmapSurfaceAttach
            if (r0 == 0) goto La6
        L5e:
            r0 = 0
            if (r8 == 0) goto L79
            com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation r8 = r5.getImmersiveAlphaAnimation()
            com.coui.appcompat.animation.dynamicanimation.COUISpringForce r1 = new com.coui.appcompat.animation.dynamicanimation.COUISpringForce
            r1.<init>()
            com.coui.appcompat.animation.dynamicanimation.COUISpringForce r0 = r1.setBounce(r0)
            r1 = 1060320051(0x3f333333, float:0.7)
            com.coui.appcompat.animation.dynamicanimation.COUISpringForce r0 = r0.setResponse(r1)
            r8.setSpring(r0)
            goto L90
        L79:
            com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation r8 = r5.getImmersiveAlphaAnimation()
            com.coui.appcompat.animation.dynamicanimation.COUISpringForce r1 = new com.coui.appcompat.animation.dynamicanimation.COUISpringForce
            r1.<init>()
            com.coui.appcompat.animation.dynamicanimation.COUISpringForce r0 = r1.setBounce(r0)
            r1 = 1050253722(0x3e99999a, float:0.3)
            com.coui.appcompat.animation.dynamicanimation.COUISpringForce r0 = r0.setResponse(r1)
            r8.setSpring(r0)
        L90:
            com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation r8 = r5.getImmersiveAlphaAnimation()
            r8.cancel()
            com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation r8 = r5.getImmersiveAlphaAnimation()
            r8.setStartValue(r6)
            com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation r5 = r5.getImmersiveAlphaAnimation()
            r5.animateToFinalPosition(r7)
            return r3
        La6:
            java.lang.String r5 = "animateTo return invisible"
            com.oplusos.keyguard.utils.KgdLogUtil.w(r1, r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController.immersiveAnimateTo(float, float, boolean):boolean");
    }

    public final void init() {
        BuildersKt.launch$default(this.coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass1(null), 2);
        BuildersKt.launch$default(this.coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass2(null), 2);
        SmoothTransitionController.registerSmoothTransitionCallback$default(SmoothTransitionController.Companion.getInstance(this.context), this.keyguardStyleServiceCallback);
        ((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).registerCallback(this.updateMonitorCallback);
        this.configurationController.addCallback(this);
        UncaughtExceptionPreHandlerManager uncaughtExceptionPreHandlerManager = this.uncaughtExceptionPreHandlerManager;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        uncaughtExceptionPreHandlerManager.registerHandler(anonymousClass3);
        KeyguardThreadUtil.Companion companion = KeyguardThreadUtil.Companion;
        OplusKgdImmersiveController$exitWorkShopAod$1 oplusKgdImmersiveController$exitWorkShopAod$1 = new OplusKgdImmersiveController$exitWorkShopAod$1(2);
        oplusKgdImmersiveController$exitWorkShopAod$1.this$0 = this;
        VarHandle.storeStoreFence();
        companion.runOnIOThread(oplusKgdImmersiveController$exitWorkShopAod$1);
        OplusWallpaperColorManager.INSTANCE.addCallback(this.context, this.wallpaperChangeCallback);
        ((WakefulnessLifecycle) this.wakefulnessLifecycle.get()).addObserver(this.wakeUpObserver);
    }

    public final boolean isBitmapImmersiveActive() {
        Integer num;
        Integer num2 = this.currentImmersiveType;
        return (num2 != null && num2.intValue() == -1) || ((num = this.currentImmersiveType) != null && num.intValue() == 1) || this.immersiveRenderMode == ImmersiveRenderMode.BITMAP || this.surfaceControlViewHost != null;
    }

    public final boolean isBitmapLockEnterValid() {
        return this.isImmersiveWallpaper && this.immersiveRenderMode == ImmersiveRenderMode.BITMAP && this.surfaceControlViewHost != null;
    }

    public final boolean isScreenOffUiReadyForMusicEnter() {
        return isScreenOffUiReadyForMusicEnterInner(this.lastUiState) || isScreenOffUiReadyForMusicEnterInner(SmoothTransitionController.Companion.getInstance(this.context).getCurrentUiState());
    }

    public final boolean isWallpaperMatrixValid() {
        OplusWindowInfo oplusWindowInfo = (OplusWindowInfo) ((OplusWallpaperAnimControllerImpl) this.wallpaperAnimController.get()).getLockScreenWallpaperWindowInfoFlow().getValue();
        Bundle bundle = oplusWindowInfo != null ? oplusWindowInfo.extension : null;
        return (bundle != null ? bundle.getFloat("HScale", 1.0f) : 1.0f) < 1.4f && (bundle != null ? bundle.getFloat("VScale", 1.0f) : 1.0f) < 1.4f;
    }

    public final void makeSureSurfaceVisible() {
        if (((COUIDynamicAnimation) getImmersiveAlphaAnimation()).mRunning || this.surfaceAlpha == 1.0f) {
            return;
        }
        setSurfaceAlpha(1.0f);
    }

    public final void markDesktopWallpaperUnderlayIfNeeded() {
        if (!this.useDesktopWallpaperUnderlayForLockEnter && SmoothTransitionController.Companion.getInstance(this.context).isPanoramicAod() && !((KeyguardStateController) this.keyguardStateController.get()).mShowing && OplusKeyguardUnlockAnimationControllerExImpl.Companion.isUnlockToLauncher()) {
            this.useDesktopWallpaperUnderlayForLockEnter = true;
            KgdLogUtil.i(TAG, "markDesktopWallpaperUnderlayIfNeeded=true");
            clearDesktopWallpaperBackground();
            OplusKgdImmersiveController$handler$1 oplusKgdImmersiveController$handler$1 = this.handler;
            OplusKgdImmersiveController$exitWorkShopAod$1 oplusKgdImmersiveController$exitWorkShopAod$1 = new OplusKgdImmersiveController$exitWorkShopAod$1(3);
            oplusKgdImmersiveController$exitWorkShopAod$1.this$0 = this;
            VarHandle.storeStoreFence();
            oplusKgdImmersiveController$handler$1.post(oplusKgdImmersiveController$exitWorkShopAod$1);
        }
    }

    public void onConfigChanged(Configuration configuration) {
        if (configuration == null) {
            KgdLogUtil.i(TAG, "onConfigChanged, newConfig is null");
            return;
        }
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) this.keyguardStateController.get();
        if (keyguardStateControllerImpl.mOccluded && !keyguardStateControllerImpl.mOplusKeyguardStateController.isKeyguardScreenRotationAllowed()) {
            KgdLogUtil.i(TAG, "onConfigChanged skip relayout, bounds=" + configuration.windowConfiguration.getBounds());
            return;
        }
        int iWidth = configuration.windowConfiguration.getBounds().width();
        int iHeight = configuration.windowConfiguration.getBounds().height();
        SurfaceControlViewHost surfaceControlViewHost = this.surfaceControlViewHost;
        OplusKgdImmersiveController$$ExternalSyntheticLambda5 oplusKgdImmersiveController$$ExternalSyntheticLambda5 = new OplusKgdImmersiveController$$ExternalSyntheticLambda5(0);
        oplusKgdImmersiveController$$ExternalSyntheticLambda5.f$0 = this;
        oplusKgdImmersiveController$$ExternalSyntheticLambda5.f$1 = iWidth;
        oplusKgdImmersiveController$$ExternalSyntheticLambda5.f$2 = iHeight;
        VarHandle.storeStoreFence();
        relayoutImmersiveHostOnConfigChanged(surfaceControlViewHost, iWidth, iHeight, configuration, oplusKgdImmersiveController$$ExternalSyntheticLambda5);
        SurfaceControlViewHost surfaceControlViewHost2 = this.musicSurfaceControlViewHost;
        OplusKgdImmersiveController$$ExternalSyntheticLambda5 oplusKgdImmersiveController$$ExternalSyntheticLambda6 = new OplusKgdImmersiveController$$ExternalSyntheticLambda5(1);
        oplusKgdImmersiveController$$ExternalSyntheticLambda6.f$0 = this;
        oplusKgdImmersiveController$$ExternalSyntheticLambda6.f$1 = iWidth;
        oplusKgdImmersiveController$$ExternalSyntheticLambda6.f$2 = iHeight;
        VarHandle.storeStoreFence();
        relayoutImmersiveHostOnConfigChanged(surfaceControlViewHost2, iWidth, iHeight, configuration, oplusKgdImmersiveController$$ExternalSyntheticLambda6);
    }

    public final void onMusicLockEnterAnimationFinished() {
        if (this.isImmersiveWallpaperState == 3) {
            this.isLockImmersive = false;
            setImmersiveWallpaperState(4);
        }
        this.useDesktopWallpaperUnderlayForLockEnter = false;
        dismissMusicScreenshotOverlay();
    }

    public final void processImmersiveBitmap(Bitmap bitmap, Integer num) {
        if ((num != null && num.intValue() == -1) || (num != null && num.intValue() == 1)) {
            KgdLogUtil.i(TAG, "processImmersiveWindowBlurBitmap");
            this.cropBitmap = bitmap;
            sendEnterImmersiveLockScreenWallpaperMsg$default(this);
        } else {
            KgdLogUtil.w(TAG, "processImmersiveBitmap skip unsupported type=" + num);
        }
        KgdLogUtil.i(TAG, "processImmersiveBitmap done");
    }

    public final void relayoutImmersiveHostOnConfigChanged(SurfaceControlViewHost surfaceControlViewHost, int i, int i2, Configuration configuration, Function2 function2) {
        View view;
        if (surfaceControlViewHost == null || (view = surfaceControlViewHost.getView()) == null) {
            return;
        }
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("onConfigChanged, view:", view.getWidth(), view.getHeight(), " x ", ", screenSize:");
        sbM.append(i);
        sbM.append(" x ");
        sbM.append(i2);
        KgdLogUtil.i(TAG, sbM.toString());
        if (view.getWidth() == i && view.getHeight() == i2) {
            return;
        }
        surfaceControlViewHost.relayout(i, i2);
        function2.invoke(Integer.valueOf(View.MeasureSpec.makeMeasureSpec(i, 1073741824)), Integer.valueOf(View.MeasureSpec.makeMeasureSpec(i2, 1073741824)));
        view.requestLayout();
        view.invalidate();
        checkMatrix(configuration);
    }

    public final void releaseActiveImmersiveSurface() {
        int iOrdinal = this.immersiveRenderMode.ordinal();
        if (iOrdinal == 0) {
            releaseMusicSurface();
            releaseMaskViewSurface();
        } else if (iOrdinal == 1) {
            releaseMaskViewSurface();
        } else if (iOrdinal == 2) {
            releaseMusicSurface();
        } else {
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        }
    }

    public final void releaseBitmapPipelineSilently() {
        if (((COUIDynamicAnimation) getImmersiveAlphaAnimation()).mRunning && this.immersiveRenderMode == ImmersiveRenderMode.BITMAP) {
            getImmersiveAlphaAnimation().cancel();
        }
        releaseMaskViewSurface();
        clearCropBitmap(true);
    }

    public final void releaseMaskViewSurface() {
        cancelMusicContentFade();
        ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).setImmersiveContentAlpha(1.0f);
        ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).setImageBitmap((Bitmap) null);
        if (!this.useDesktopWallpaperUnderlayForLockEnter) {
            clearDesktopWallpaperBackground();
        }
        SurfaceControlViewHost surfaceControlViewHost = this.surfaceControlViewHost;
        if (surfaceControlViewHost != null) {
            SurfaceControlViewHost.SurfacePackage surfacePackage = surfaceControlViewHost.getSurfacePackage();
            SurfaceControl surfaceControl = surfacePackage != null ? surfacePackage.getSurfaceControl() : null;
            if (surfaceControl != null && surfaceControl.isValid()) {
                new SurfaceControl.Transaction().hide(surfaceControl).remove(surfaceControl).apply();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    Log.d("SystemUi--Keyguard", "OplusKgdImmersiveController-->SurfaceControl removed successfully");
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "OplusKgdImmersiveController-->SurfaceControl removed failed, not valid");
            }
            surfaceControlViewHost.release();
            saveSurfaceStateToSp(false);
        }
        this.surfaceControlViewHost = null;
        ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).resetBlackMaskRatio();
        if (this.musicSurfaceControlViewHost == null) {
            this.lastAttachedWallpaperSurfaceControl = null;
        }
    }

    public final void releaseMusicPipelineSilently() {
        this.musicExitShouldClearState = false;
        if (((COUIDynamicAnimation) getImmersiveAlphaAnimation()).mRunning && this.immersiveRenderMode == ImmersiveRenderMode.MUSIC_VIEW) {
            getImmersiveAlphaAnimation().cancel();
        }
        releaseMusicSurface();
        getMusicArtHostContainer().setVisibility(8);
        this.musicArtBackgroundView = null;
    }

    public final void releaseMusicSurface() {
        cancelMusicContentFade();
        if (!this.useDesktopWallpaperUnderlayForLockEnter) {
            clearDesktopWallpaperBackground();
        }
        SurfaceControlViewHost surfaceControlViewHost = this.musicSurfaceControlViewHost;
        if (surfaceControlViewHost != null) {
            SurfaceControlViewHost.SurfacePackage surfacePackage = surfaceControlViewHost.getSurfacePackage();
            SurfaceControl surfaceControl = surfacePackage != null ? surfacePackage.getSurfaceControl() : null;
            if (surfaceControl != null && surfaceControl.isValid()) {
                new SurfaceControl.Transaction().hide(surfaceControl).remove(surfaceControl).apply();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    Log.d("SystemUi--Keyguard", "OplusKgdImmersiveController-->music SurfaceControl removed successfully");
                }
            }
            surfaceControlViewHost.release();
            if (this.surfaceControlViewHost == null) {
                saveSurfaceStateToSp(false);
            }
        }
        this.musicSurfaceControlViewHost = null;
        if (this.surfaceControlViewHost == null) {
            this.lastAttachedWallpaperSurfaceControl = null;
        }
    }

    public final void removeMessage() {
        this.typeChangeLoadGeneration++;
        if (hasMessages(101)) {
            removeMessages(101);
        }
        this.pendingBitmapEnterMessage = hasMessages(101);
        if (hasMessages(103)) {
            removeMessages(103);
        }
    }

    public final void replaceAOD(SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState) {
        float fResolveBitmapMaskRatio;
        if (this.currentImmersiveType == null) {
            return;
        }
        Integer num = this.currentImmersiveType;
        Float fValueOf = null;
        if (num != null && num.intValue() == 2) {
            fResolveBitmapMaskRatio = resolveMusicMaskRatio(smoothTransitionUiState);
            if (fResolveBitmapMaskRatio == 0.5f) {
                fValueOf = Float.valueOf(fResolveBitmapMaskRatio);
            }
        } else {
            fResolveBitmapMaskRatio = resolveBitmapMaskRatio(smoothTransitionUiState);
            if (fResolveBitmapMaskRatio == 0.6f) {
                fValueOf = Float.valueOf(fResolveBitmapMaskRatio);
            }
        }
        OplusKgdImmersiveController$replaceAOD$applyMask$1 oplusKgdImmersiveController$replaceAOD$applyMask$1 = new OplusKgdImmersiveController$replaceAOD$applyMask$1();
        oplusKgdImmersiveController$replaceAOD$applyMask$1.this$0 = this;
        oplusKgdImmersiveController$replaceAOD$applyMask$1.$maskRatio = fResolveBitmapMaskRatio;
        oplusKgdImmersiveController$replaceAOD$applyMask$1.$lockMaskRatio = fValueOf;
        VarHandle.storeStoreFence();
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            oplusKgdImmersiveController$replaceAOD$applyMask$1.run();
        } else {
            post(oplusKgdImmersiveController$replaceAOD$applyMask$1);
        }
    }

    public final void requestDesktopWallpaperBitmap(OplusKgdImmersiveController$.ExternalSyntheticLambda10 externalSyntheticLambda10, Function1 function1) {
        Bitmap bitmap = this.musicDesktopWallpaperBitmap;
        if (bitmap != null) {
            if (bitmap.isRecycled()) {
                bitmap = null;
            }
            if (bitmap != null) {
                function1.invoke(bitmap);
                return;
            }
        }
        int i = this.musicDesktopWallpaperLoadGeneration;
        KeyguardThreadUtil.Companion companion = KeyguardThreadUtil.Companion;
        OplusKgdImmersiveController$changeBitmapForImmersiveType$1 oplusKgdImmersiveController$changeBitmapForImmersiveType$1 = new OplusKgdImmersiveController$changeBitmapForImmersiveType$1(1);
        oplusKgdImmersiveController$changeBitmapForImmersiveType$1.this$0 = this;
        oplusKgdImmersiveController$changeBitmapForImmersiveType$1.$newType = i;
        oplusKgdImmersiveController$changeBitmapForImmersiveType$1.$from = externalSyntheticLambda10;
        oplusKgdImmersiveController$changeBitmapForImmersiveType$1.$to = function1;
        VarHandle.storeStoreFence();
        companion.runOnWorkThread(oplusKgdImmersiveController$changeBitmapForImmersiveType$1);
    }

    public void requestExitImmersiveState(String str, int i) {
        KgdLogUtil.i(TAG, "requestExitImmersiveState,from=" + str + ",reason=" + i);
        if (((OplusKeyguardClockViewModel.ClockSizeState) ((OplusKeyguardClockViewModel) this.keyguardClockViewModel.get()).getClockSizeStateFlow().getValue()).getClockSize() == ClockSize.IMMERSED) {
            ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).goToExtState(false, ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getLastExtState(), FileProvider$.ExternalSyntheticOutline0.m("KgdExit-", str), i, null);
        }
    }

    public final float resolveBitmapMaskRatio(SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState) {
        SmoothTransitionController companion = SmoothTransitionController.Companion.getInstance(this.context);
        SmoothTransitionController.SmoothTransitionUiState currentUiState = companion.getCurrentUiState();
        if (((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).mGoingToSleep && companion.isPanoramicAod()) {
            return 0.6f;
        }
        if (currentUiState == SmoothTransitionController.SmoothTransitionUiState.KEYGUARD) {
            return 0.3f;
        }
        SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState2 = SmoothTransitionController.SmoothTransitionUiState.PANORAMIC_AOD;
        if (currentUiState == smoothTransitionUiState2) {
            return 0.6f;
        }
        return (smoothTransitionUiState != smoothTransitionUiState2 || ((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).mDeviceInteractive) ? 0.3f : 0.6f;
    }

    public final float resolveLockWallpaperAppearLeashAlpha(float f) {
        SurfaceControlViewHost.SurfacePackage surfacePackage;
        if (this.isImmersiveWallpaper) {
            boolean z = this.immersiveRenderMode == ImmersiveRenderMode.MUSIC_VIEW;
            boolean z2 = this.immersiveRenderMode == ImmersiveRenderMode.BITMAP;
            if ((z || z2) && (this.isLockImmersive || this.pendingMusicSurfaceAttach || this.pendingBitmapSurfaceAttach || this.pendingMusicScreenOffEnter || this.isImmersiveWallpaperState == 2 || this.isImmersiveWallpaperState == 3 || (this.isImmersiveWallpaperState == 4 && hostForRenderMode(this.immersiveRenderMode) != null))) {
                SurfaceControlViewHost surfaceControlViewHostHostForRenderMode = hostForRenderMode(this.immersiveRenderMode);
                SurfaceControl surfaceControl = (surfaceControlViewHostHostForRenderMode == null || (surfacePackage = surfaceControlViewHostHostForRenderMode.getSurfacePackage()) == null) ? null : surfacePackage.getSurfaceControl();
                return (surfaceControl == null || !surfaceControl.isValid() || this.surfaceAlpha < 1.0f) ? 0.0f : 1.0f;
            }
        }
        return f;
    }

    public final float resolveMusicMaskRatio(SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState) {
        SmoothTransitionController.SmoothTransitionUiState currentUiState = SmoothTransitionController.Companion.getInstance(this.context).getCurrentUiState();
        if (currentUiState == SmoothTransitionController.SmoothTransitionUiState.KEYGUARD) {
            return 0.0f;
        }
        SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState2 = SmoothTransitionController.SmoothTransitionUiState.PANORAMIC_AOD;
        if (currentUiState == smoothTransitionUiState2) {
            return 0.5f;
        }
        return (smoothTransitionUiState != smoothTransitionUiState2 || ((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).mDeviceInteractive) ? 0.0f : 0.5f;
    }

    public final void resolveWallpaperTransformState(int i, float f, float f2, Point point) {
        boolean zShouldSwapWallpaperExt = shouldSwapWallpaperExt(point, i);
        this.wallpaperHScale = zShouldSwapWallpaperExt ? f2 : f;
        if (!zShouldSwapWallpaperExt) {
            f = f2;
        }
        this.wallpaperVScale = f;
        if (zShouldSwapWallpaperExt) {
            point = new Point(point.y, point.x);
        }
        this.wallpaperOffset = point;
        int i2 = this.wallpaperOrientation;
        if (i2 != i || i2 == -1) {
            this.wallpaperOrientation = i;
        }
    }

    /* JADX WARN: Code duplicated, block: B:8:0x003a  */
    /* JADX WARN: Code duplicated, block: B:9:0x003c  */
    public final void restoreImmersiveForKeyguardShow() {
        boolean z;
        int i = this.isImmersiveWallpaperState;
        boolean z2 = this.isImmersiveWallpaper;
        ImmersiveRenderMode immersiveRenderMode = this.immersiveRenderMode;
        boolean z3 = this.pendingMusicSurfaceAttach;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("restoreImmersiveForKeyguardShow,isImmersiveWallpaperState=", i, ",isImmersiveWallpaper=", ",immersiveRenderMode=", z2);
        sbM.append(immersiveRenderMode);
        sbM.append(",pendingMusic=");
        sbM.append(z3);
        KgdLogUtil.i(TAG, sbM.toString());
        int iOrdinal = this.immersiveRenderMode.ordinal();
        if (iOrdinal == 0) {
            z = true;
        } else if (iOrdinal != 1) {
            if (iOrdinal != 2) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return;
            } else if (this.musicSurfaceControlViewHost == null) {
                z = true;
            } else {
                z = false;
            }
        } else if (this.surfaceControlViewHost == null) {
            z = true;
        } else {
            z = false;
        }
        if (this.isImmersiveWallpaper) {
            if (this.isImmersiveWallpaperState == 0 || this.pendingMusicSurfaceAttach || this.pendingBitmapSurfaceAttach || z) {
                ImmersiveRenderMode immersiveRenderMode2 = this.immersiveRenderMode;
                ImmersiveRenderMode immersiveRenderMode3 = ImmersiveRenderMode.MUSIC_VIEW;
                if (immersiveRenderMode2 == immersiveRenderMode3 && this.musicArtBackgroundView == null) {
                    KgdLogUtil.w(TAG, "restoreImmersiveForKeyguardShow skip, music view missing");
                    return;
                }
                if (!this.pendingMusicSurfaceAttach && !this.pendingBitmapSurfaceAttach) {
                    setImmersiveWallpaperState(3);
                    if (this.immersiveRenderMode == immersiveRenderMode3) {
                        getMusicArtHostContainer().setVisibility(0);
                        if (setMusicArtViewToSurface$default(this, true, 2)) {
                            immersiveAnimateTo(this.surfaceAlpha, 1.0f, SmoothTransitionController.Companion.getInstance(this.context).isPanoramicAod());
                            return;
                        }
                        return;
                    }
                    Bitmap bitmap = this.cropBitmap;
                    if (bitmap != null) {
                        ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).setImageBitmap(bitmap);
                    }
                    if (setMaskViewToSurface$default(this, true, 2)) {
                        immersiveAnimateTo(this.surfaceAlpha, 1.0f, SmoothTransitionController.Companion.getInstance(this.context).isPanoramicAod());
                        return;
                    }
                    return;
                }
                if (((KeyguardStateController) this.keyguardStateController.get()).mShowing) {
                    if (!this.isImmersiveWallpaper || this.isImmersiveWallpaperAod) {
                        this.pendingMusicSurfaceAttach = false;
                        this.pendingBitmapSurfaceAttach = false;
                        return;
                    }
                    if (this.pendingMusicSurfaceAttach && this.immersiveRenderMode == immersiveRenderMode3 && this.musicArtBackgroundView != null) {
                        KgdLogUtil.i(TAG, "retryPendingImmersiveSurfaceAttach music");
                        if (this.isLockImmersive) {
                            enterMusicImmersiveOnLock(true);
                            return;
                        }
                        this.pendingMusicSurfaceAttach = false;
                        if (this.isImmersiveWallpaperState == 0) {
                            setImmersiveWallpaperState(3);
                        }
                        if (setMusicArtViewToSurface$default(this, true, 2)) {
                            getImmersiveAlphaAnimation().cancel();
                            float fCoerceIn = RangesKt___RangesKt.coerceIn(this.surfaceAlpha, 0.0f, 1.0f);
                            setSurfaceAlpha(fCoerceIn);
                            immersiveAnimateTo(fCoerceIn, 1.0f, false);
                            return;
                        }
                        return;
                    }
                    if (this.pendingBitmapSurfaceAttach && this.immersiveRenderMode == ImmersiveRenderMode.BITMAP && this.cropBitmap != null) {
                        KgdLogUtil.i(TAG, "retryPendingImmersiveSurfaceAttach bitmap");
                        this.pendingBitmapSurfaceAttach = false;
                        if (this.isImmersiveWallpaperState == 0) {
                            setImmersiveWallpaperState(3);
                        }
                        if (this.isLockImmersive) {
                            Bitmap bitmap2 = this.cropBitmap;
                            bitmap2.getClass();
                            enterImmersiveLockScreenWallpaper(bitmap2, true);
                        } else {
                            ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).setImageBitmap(this.cropBitmap);
                            if (setMaskViewToSurface$default(this, true, 2)) {
                                getImmersiveAlphaAnimation().cancel();
                                immersiveAnimateTo(this.surfaceAlpha, 1.0f, false);
                            }
                        }
                    }
                }
            }
        }
    }

    public final void runBitmapExitAnimation() {
        releaseMusicPipelineSilently();
        this.immersiveRenderMode = ImmersiveRenderMode.BITMAP;
        setMaskViewToSurface$default(this, false, 3);
        runExitAnimAndReleaseResource();
    }

    public final void runExitAnimAndReleaseResource() {
        cancelPipelineCrossfade(true);
        OplusLockIconView$.ExternalSyntheticOutline0.m(this.isImmersiveWallpaperState, "runExitAnimAndReleaseResource isImmersiveWallpaperState=", TAG);
        setImmersiveWallpaperState(0);
        this.useDesktopWallpaperUnderlayForLockEnter = false;
        immersiveAnimateTo(this.surfaceAlpha, 0.0f, false);
        if (this.immersiveRenderMode == ImmersiveRenderMode.BITMAP) {
            clearCropBitmap(false);
        }
    }

    public final void runMusicExitAnimation() {
        cancelMusicContentFade();
        View view = this.musicArtBackgroundView;
        if (view != null) {
            view.setAlpha(1.0f);
        }
        if (this.immersiveRenderMode != ImmersiveRenderMode.MUSIC_VIEW) {
            finishMusicImmersiveExit();
            return;
        }
        if (this.isImmersiveWallpaperState <= 0) {
            finishMusicImmersiveExit();
            return;
        }
        setImmersiveWallpaperState(1);
        getMusicArtHostContainer().setVisibility(0);
        if (!setMusicArtViewToSurface$default(this, true, 2)) {
            KgdLogUtil.w(TAG, "runMusicExitAnimation attach failed, exit immediately");
            finishMusicImmersiveExit();
        } else {
            if (immersiveAnimateTo(RangesKt___RangesKt.coerceIn(this.surfaceAlpha, 0.0f, 1.0f), 0.0f, false)) {
                return;
            }
            KgdLogUtil.w(TAG, "runMusicExitAnimation animate skipped, exit immediately");
            finishMusicImmersiveExit();
        }
    }

    public final void runMusicExitForContext() {
        if (!this.isImmersiveWallpaperAod) {
            releaseBitmapPipelineSilently();
            ImmersiveRenderMode immersiveRenderMode = this.immersiveRenderMode;
            ImmersiveRenderMode immersiveRenderMode2 = ImmersiveRenderMode.MUSIC_VIEW;
            if (immersiveRenderMode != immersiveRenderMode2) {
                this.immersiveRenderMode = immersiveRenderMode2;
            }
            this.musicExitShouldClearState = true;
            runMusicExitAnimation();
            return;
        }
        cancelMusicContentFade();
        View view = this.musicArtBackgroundView;
        if (view != null) {
            view.setAlpha(1.0f);
        }
        releaseBitmapPipelineSilently();
        ImmersiveRenderMode immersiveRenderMode3 = this.immersiveRenderMode;
        ImmersiveRenderMode immersiveRenderMode4 = ImmersiveRenderMode.MUSIC_VIEW;
        if (immersiveRenderMode3 != immersiveRenderMode4) {
            if (this.musicArtBackgroundView == null && this.musicSurfaceControlViewHost == null) {
                return;
            } else {
                this.immersiveRenderMode = immersiveRenderMode4;
            }
        }
        this.musicExitShouldClearState = false;
        if (this.isImmersiveWallpaperState >= 3) {
            setImmersiveWallpaperState(1);
        }
        getMusicArtHostContainer().setVisibility(0);
        if (!setMusicArtViewToSurface$default(this, true, 2)) {
            KgdLogUtil.w(TAG, "runMusicFadeOutForWorkShopAod attach failed");
            setImmersiveWallpaperState(0);
            releaseMusicSurface();
            getMusicArtHostContainer().setVisibility(8);
            return;
        }
        if (immersiveAnimateTo(RangesKt___RangesKt.coerceIn(this.surfaceAlpha, 0.0f, 1.0f), 0.0f, false)) {
            return;
        }
        setImmersiveWallpaperState(0);
        getMusicArtHostContainer().setVisibility(8);
        releaseMusicSurface();
    }

    public final boolean runPipelineCrossfade(ImmersiveRenderMode immersiveRenderMode, ImmersiveRenderMode immersiveRenderMode2, Function0 function0) {
        SurfaceControlViewHost surfaceControlViewHostHostForRenderMode = hostForRenderMode(immersiveRenderMode);
        SurfaceControlViewHost surfaceControlViewHostHostForRenderMode2 = hostForRenderMode(immersiveRenderMode2);
        if (surfaceControlViewHostHostForRenderMode == null || surfaceControlViewHostHostForRenderMode2 == null) {
            KgdLogUtil.w(TAG, "runPipelineCrossfade skip, outHost=" + surfaceControlViewHostHostForRenderMode + ",inHost=" + surfaceControlViewHostHostForRenderMode2);
            return false;
        }
        if (((COUIDynamicAnimation) getImmersiveAlphaAnimation()).mRunning) {
            getImmersiveAlphaAnimation().cancel();
        }
        cancelPipelineCrossfade(false);
        this.isPipelineCrossfading = true;
        this.crossfadeOutMode = immersiveRenderMode;
        this.crossfadeInMode = immersiveRenderMode2;
        this.crossfadeOutCleanup = function0;
        this.immersiveRenderMode = immersiveRenderMode2;
        setImmersiveWallpaperState(3);
        setHostSurfaceAlpha(hostForRenderMode(ImmersiveRenderMode.BITMAP), 1.0f);
        ImmersiveRenderMode immersiveRenderMode3 = ImmersiveRenderMode.MUSIC_VIEW;
        setHostSurfaceAlpha(hostForRenderMode(immersiveRenderMode3), immersiveRenderMode2 == immersiveRenderMode3 ? 0.0f : 1.0f);
        KgdLogUtil.i(TAG, "runPipelineCrossfade " + immersiveRenderMode + " -> " + immersiveRenderMode2);
        getPipelineCrossfadeAnimation().cancel();
        getPipelineCrossfadeAnimation().setStartValue(0.0f);
        getPipelineCrossfadeAnimation().animateToFinalPosition(1.0f);
        return true;
    }

    public final void safeReleaseActiveImmersiveSurface() {
        if (Intrinsics.areEqual(Handler.getMain().getLooper().getThread(), Thread.currentThread())) {
            releaseActiveImmersiveSurface();
            return;
        }
        Handler main = Handler.getMain();
        OplusKgdImmersiveController$exitWorkShopAod$1 oplusKgdImmersiveController$exitWorkShopAod$1 = new OplusKgdImmersiveController$exitWorkShopAod$1(4);
        oplusKgdImmersiveController$exitWorkShopAod$1.this$0 = this;
        VarHandle.storeStoreFence();
        main.post(oplusKgdImmersiveController$exitWorkShopAod$1);
    }

    public final void saveSurfaceStateToSp(boolean z) {
        KeyguardThreadUtil.Companion companion = KeyguardThreadUtil.Companion;
        RunnableC01781 runnableC01781 = new RunnableC01781();
        runnableC01781.this$0 = this;
        runnableC01781.$showing = z;
        VarHandle.storeStoreFence();
        companion.runOnIOThread(runnableC01781);
    }

    public final void seedDesktopWallpaperBeforeAttach() {
        Bitmap bitmap;
        if (this.useDesktopWallpaperUnderlayForLockEnter) {
            if ((this.surfaceControlViewHost == null || this.surfaceAlpha < 1.0f) && (bitmap = this.musicDesktopWallpaperBitmap) != null) {
                if (bitmap.isRecycled()) {
                    bitmap = null;
                }
                if (bitmap == null) {
                    return;
                }
                ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).setImageBitmap(bitmap);
            }
        }
    }

    public final boolean setMaskViewToSurface(boolean z, boolean z2) {
        if (!z && !((KeyguardStateController) this.keyguardStateController.get()).mShowing) {
            this.pendingBitmapSurfaceAttach = true;
            ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).setVisibility(0);
            KgdLogUtil.i(TAG, "setMaskViewToSurface defer, keyguard not show");
            return false;
        }
        this.pendingBitmapSurfaceAttach = false;
        ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).setVisibility(0);
        KgdLogUtil.i(TAG, "setMaskViewToSurface surfaceControlViewHost:" + this.surfaceControlViewHost);
        if (this.surfaceControlViewHost == null) {
            Context context = this.context;
            SurfaceControlViewHost surfaceControlViewHost = new SurfaceControlViewHost(context, context.getDisplay(), ((NotificationShadeWindowView) this.nswv.get()).getViewRootImpl().getInputTransferToken(), TAG);
            int screenWidth = ScreenUtils.getScreenWidth(this.context);
            int screenHeight = ScreenUtils.getScreenHeight(this.context);
            surfaceControlViewHost.setView((View) this.oplusKgdImmersiveMaskView.get(), screenWidth, screenHeight);
            ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).measure(screenWidth, screenHeight);
            ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).layout(0, 0, screenWidth, screenHeight);
            ((OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get()).invalidate();
            this.surfaceControlViewHost = surfaceControlViewHost;
        }
        attachHostSurfaceToWallpaper(this.surfaceControlViewHost, isWallpaperMatrixValid(), z2);
        return true;
    }

    public final boolean setMusicArtViewToSurface(boolean z, boolean z2) {
        if (!z && !((KeyguardStateController) this.keyguardStateController.get()).mShowing) {
            this.pendingMusicSurfaceAttach = true;
            getMusicArtHostContainer().setVisibility(0);
            KgdLogUtil.i(TAG, "setMusicArtViewToSurface defer, keyguard not show");
            return false;
        }
        this.pendingMusicSurfaceAttach = false;
        getMusicArtHostContainer().setVisibility(0);
        KgdLogUtil.i(TAG, "setMusicArtViewToSurface musicSurfaceControlViewHost:" + this.musicSurfaceControlViewHost);
        SurfaceControlViewHost surfaceControlViewHost = this.musicSurfaceControlViewHost;
        if (surfaceControlViewHost == null) {
            Context context = this.context;
            SurfaceControlViewHost surfaceControlViewHost2 = new SurfaceControlViewHost(context, context.getDisplay(), ((NotificationShadeWindowView) this.nswv.get()).getViewRootImpl().getInputTransferToken(), "OplusKgdImmersiveController-music");
            int screenWidth = ScreenUtils.getScreenWidth(this.context);
            int screenHeight = ScreenUtils.getScreenHeight(this.context);
            surfaceControlViewHost2.setView(getMusicArtHostContainer(), screenWidth, screenHeight);
            getMusicArtHostContainer().measure(screenWidth, screenHeight);
            getMusicArtHostContainer().layout(0, 0, screenWidth, screenHeight);
            getMusicArtHostContainer().invalidate();
            this.musicSurfaceControlViewHost = surfaceControlViewHost2;
        } else {
            int screenWidth2 = ScreenUtils.getScreenWidth(this.context);
            int screenHeight2 = ScreenUtils.getScreenHeight(this.context);
            if (getMusicArtHostContainer().getWidth() != screenWidth2 || getMusicArtHostContainer().getHeight() != screenHeight2) {
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("relayoutMusicHostIfNeeded, view:", getMusicArtHostContainer().getWidth(), getMusicArtHostContainer().getHeight(), " x ", ", screenSize:");
                sbM.append(screenWidth2);
                sbM.append(" x ");
                sbM.append(screenHeight2);
                KgdLogUtil.i(TAG, sbM.toString());
                surfaceControlViewHost.relayout(screenWidth2, screenHeight2);
                getMusicArtHostContainer().measure(View.MeasureSpec.makeMeasureSpec(screenWidth2, 1073741824), View.MeasureSpec.makeMeasureSpec(screenHeight2, 1073741824));
                getMusicArtHostContainer().layout(0, 0, screenWidth2, screenHeight2);
                getMusicArtHostContainer().invalidate();
                checkMatrix(this.context.getResources().getConfiguration());
            }
        }
        attachHostSurfaceToWallpaper(this.musicSurfaceControlViewHost, isWallpaperMatrixValid(), z2);
        return true;
    }

    public final boolean shouldSwapWallpaperExt(Point point, int i) {
        int i2 = this.wallpaperOrientation;
        if (i2 == -1 || i2 == i) {
            return false;
        }
        if (i == 1) {
            int i3 = point.x;
            if (i3 != 0 && Math.abs(i3) >= Math.abs(point.y)) {
                return false;
            }
        } else {
            if (i != 2) {
                return false;
            }
            int i4 = point.y;
            if (i4 != 0 && Math.abs(i4) >= Math.abs(point.x)) {
                return false;
            }
        }
        return true;
    }

    public final boolean shouldUseOpaqueContentFadeForLockEnter() {
        return this.isLockImmersive && SmoothTransitionController.Companion.getInstance(this.context).isPanoramicAod();
    }

    public final void startBitmapOpaqueLockEnter(Bitmap bitmap) {
        OplusKgdImmersiveMaskView oplusKgdImmersiveMaskView = (OplusKgdImmersiveMaskView) this.oplusKgdImmersiveMaskView.get();
        cancelMusicContentFade();
        getImmersiveAlphaAnimation().cancel();
        oplusKgdImmersiveMaskView.setImmersiveContentAlpha(1.0f);
        OplusKgdImmersiveController$.ExternalSyntheticLambda9 externalSyntheticLambda9 = new OplusKgdImmersiveController$.ExternalSyntheticLambda9();
        externalSyntheticLambda9.f$0 = oplusKgdImmersiveMaskView;
        externalSyntheticLambda9.f$1 = bitmap;
        externalSyntheticLambda9.f$2 = this;
        VarHandle.storeStoreFence();
        if (!this.useDesktopWallpaperUnderlayForLockEnter || (this.surfaceControlViewHost != null && this.surfaceAlpha >= 1.0f)) {
            externalSyntheticLambda9.invoke();
            return;
        }
        setSurfaceAlpha(0.0f);
        OplusKgdImmersiveController$.ExternalSyntheticLambda10 externalSyntheticLambda10 = new OplusKgdImmersiveController$.ExternalSyntheticLambda10();
        externalSyntheticLambda10.f$0 = this;
        externalSyntheticLambda10.f$1 = externalSyntheticLambda9;
        VarHandle.storeStoreFence();
        OplusKgdImmersiveController$.ExternalSyntheticLambda11 externalSyntheticLambda11 = new OplusKgdImmersiveController$.ExternalSyntheticLambda11();
        externalSyntheticLambda11.f$0 = this;
        externalSyntheticLambda11.f$1 = oplusKgdImmersiveMaskView;
        externalSyntheticLambda11.f$2 = bitmap;
        VarHandle.storeStoreFence();
        requestDesktopWallpaperBitmap(externalSyntheticLambda10, externalSyntheticLambda11);
    }

    public final void stopMusicImmersiveWallpaper(boolean z) {
        if (this.immersiveRenderMode == ImmersiveRenderMode.MUSIC_VIEW || z) {
            if (z) {
                releaseMusicSurface();
                getMusicArtHostContainer().setVisibility(8);
                this.musicArtBackgroundView = null;
                this.immersiveRenderMode = ImmersiveRenderMode.NONE;
                return;
            }
            if (this.isImmersiveWallpaperState == 1) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    Log.d("SystemUi--Keyguard", "OplusKgdImmersiveController-->stopMusicImmersiveWallpaper skip, already exiting");
                    return;
                }
                return;
            }
            if (this.isImmersiveWallpaper || this.musicSurfaceControlViewHost != null) {
                this.musicExitShouldClearState = true;
                runMusicExitAnimation();
            } else {
                releaseMusicSurface();
                clearMusicImmersiveState();
            }
        }
    }

    public final void tryRunPendingMusicScreenOffEnter(boolean z) {
        if (this.pendingMusicScreenOffEnter && ((KeyguardStateController) this.keyguardStateController.get()).mShowing) {
            if (!this.isLockImmersive || this.immersiveRenderMode != ImmersiveRenderMode.MUSIC_VIEW || !this.isImmersiveWallpaper) {
                boolean z2 = this.pendingMusicScreenOffEnter;
                this.pendingMusicScreenOffEnter = false;
                if (z2) {
                    dismissMusicScreenshotOverlay();
                    return;
                }
                return;
            }
            if (z || isScreenOffUiReadyForMusicEnter()) {
                this.pendingMusicScreenOffEnter = false;
                KgdLogUtil.i(TAG, "run deferred music enter");
                attachMusicImmersiveOnLockSurface(true);
            }
        }
    }

    @Deprecated
    public final void updateCacheMusicArt(Bitmap bitmap) {
        KgdLogUtil.i(TAG, "updateCacheMusicArt deprecated, ignored. bitmap=" + bitmap);
    }

    public final void updateImmersiveLockScreenWallpaper(Bitmap bitmap, int i, Integer num) {
        Integer num2;
        int i2 = this.isImmersiveWallpaperState;
        boolean z = this.isImmersiveWallpaperAod;
        Integer num3 = this.currentImmersiveType;
        StringBuilder sb = new StringBuilder("updateImmersiveLockScreenWallpaper,bitmap=");
        sb.append(bitmap);
        sb.append(",isImmersiveWallpaperState=");
        sb.append(i2);
        sb.append(",isImmersiveWallpaperAod=");
        PromptIconState$.ExternalSyntheticOutline0.m(i, ",reason=", ",type=", sb, z);
        sb.append(num);
        sb.append(",currentImmersiveType=");
        sb.append(num3);
        KgdLogUtil.i(TAG, sb.toString());
        Integer num4 = null;
        if (num != null && num.intValue() == 2) {
            KgdLogUtil.i(TAG, "updateImmersiveLockScreenWallpaper skip INTERACTIVE, use updateMusicArtBg");
            if (bitmap == null) {
                updateMusicArtBg(null);
                return;
            }
            return;
        }
        if (num == null) {
            num = this.currentImmersiveType;
        }
        if (bitmap != null && ((num != null && num.intValue() == 2) || ((num2 = this.currentImmersiveType) != null && num2.intValue() == 2))) {
            KgdLogUtil.i(TAG, "updateImmersiveLockScreenWallpaper ignore legacy bitmap for music type");
            return;
        }
        if (bitmap == null || checkWallpaperImmersive(bitmap, num)) {
            if (!this.isImmersiveWallpaperAod && this.isImmersiveWallpaper && this.currentImmersiveType != null && num != null && bitmap != null && this.cropBitmap != null) {
                if (this.lastSwitchedFromMusicToBitmap || this.immersiveRenderMode != ImmersiveRenderMode.BITMAP || this.surfaceControlViewHost == null) {
                    KgdLogUtil.i(TAG, "shouldUseBitmapTypeChangeTransition skip, fromMusic=" + this.lastSwitchedFromMusicToBitmap + ",renderMode=" + this.immersiveRenderMode + ", maskHost=" + this.surfaceControlViewHost);
                } else {
                    num4 = num;
                }
            }
            int i3 = 0;
            if (num4 != null) {
                Integer num5 = this.currentImmersiveType;
                int iIntValue = num4.intValue();
                Bitmap bitmap2 = this.cropBitmap;
                if (iIntValue != 2) {
                    if ((num5 != null && num5.intValue() == 2) || bitmap2 == null || bitmap == null) {
                        return;
                    }
                    KeyguardThreadUtil.Companion companion = KeyguardThreadUtil.Companion;
                    OplusKgdImmersiveController$changeBitmapForImmersiveType$1 oplusKgdImmersiveController$changeBitmapForImmersiveType$1 = new OplusKgdImmersiveController$changeBitmapForImmersiveType$1(i3);
                    oplusKgdImmersiveController$changeBitmapForImmersiveType$1.$newType = iIntValue;
                    oplusKgdImmersiveController$changeBitmapForImmersiveType$1.this$0 = this;
                    oplusKgdImmersiveController$changeBitmapForImmersiveType$1.$from = bitmap2;
                    oplusKgdImmersiveController$changeBitmapForImmersiveType$1.$to = bitmap;
                    VarHandle.storeStoreFence();
                    companion.runOnWorkThread(oplusKgdImmersiveController$changeBitmapForImmersiveType$1);
                    return;
                }
                return;
            }
            boolean z2 = bitmap != null && (((ILockScreenExtStateController.ExtStateEvent) ((LockScreenNotificationExtStateManager) this.lockScreenNotificationExtStateManager.get()).getExtStateEvent().getValue()).getReason() == 305 || ((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).mGoingToSleep);
            this.isLockImmersive = z2;
            if (z2) {
                markDesktopWallpaperUnderlayIfNeeded();
            }
            if (bitmap == null) {
                if (((num == null || num.intValue() != -1) && (num == null || num.intValue() != 1)) || i == 3 || !this.pendingBitmapEnterMessage || this.isImmersiveWallpaperState != 2) {
                    if (this.isImmersiveWallpaper) {
                        this.isImmersiveWallpaper = false;
                        this.pendingBitmapEnterMessage = false;
                        exitImmersiveLockScreenWallpaper();
                        return;
                    }
                    return;
                }
                KgdLogUtil.i(TAG, "ignore stale null wallpaper update during bitmap enter, reason=" + i + ",type=" + num);
                clearStaleMusicImmersiveState(true);
                return;
            }
            ImmersiveRenderMode immersiveRenderMode = this.immersiveRenderMode;
            ImmersiveRenderMode immersiveRenderMode2 = ImmersiveRenderMode.MUSIC_VIEW;
            boolean z3 = immersiveRenderMode == immersiveRenderMode2 || this.lastSwitchedFromMusicToBitmap;
            if (z3) {
                if (this.immersiveRenderMode == immersiveRenderMode2 && this.musicSurfaceControlViewHost != null) {
                    this.pendingMusicToBitmapCrossfade = true;
                } else if (this.immersiveRenderMode == immersiveRenderMode2) {
                    stopMusicImmersiveWallpaper(true);
                }
                this.lastSwitchedFromMusicToBitmap = false;
                if (this.isImmersiveWallpaperState >= 3) {
                    setImmersiveWallpaperState(2);
                }
            }
            if (!this.pendingMusicToBitmapCrossfade) {
                this.immersiveRenderMode = ImmersiveRenderMode.BITMAP;
            }
            this.isImmersiveWallpaper = true;
            if (this.isImmersiveWallpaperAod) {
                KeyguardThreadUtil.Companion companion2 = KeyguardThreadUtil.Companion;
                updateImmersiveLockScreenWallpaper.1.1 r0 = new updateImmersiveLockScreenWallpaper.1.1(0);
                r0.this$0 = this;
                r0.$bitmap = bitmap;
                r0.$effectiveType = num;
                VarHandle.storeStoreFence();
                companion2.runOnWorkThread(r0);
                return;
            }
            if (this.isImmersiveWallpaperState <= 0) {
                setImmersiveWallpaperState(2);
            }
            removeMessage();
            this.pendingBitmapEnterMessage = true;
            KeyguardThreadUtil.Companion companion3 = KeyguardThreadUtil.Companion;
            OplusKgdImmersiveController$updateImmersiveLockScreenWallpaper$1$2 oplusKgdImmersiveController$updateImmersiveLockScreenWallpaper$1$2 = new OplusKgdImmersiveController$updateImmersiveLockScreenWallpaper$1$2();
            oplusKgdImmersiveController$updateImmersiveLockScreenWallpaper$1$2.this$0 = this;
            oplusKgdImmersiveController$updateImmersiveLockScreenWallpaper$1$2.$switchingFromMusicView = z3;
            oplusKgdImmersiveController$updateImmersiveLockScreenWallpaper$1$2.$bitmap = bitmap;
            oplusKgdImmersiveController$updateImmersiveLockScreenWallpaper$1$2.$effectiveType = num;
            VarHandle.storeStoreFence();
            companion3.runOnWorkThread(oplusKgdImmersiveController$updateImmersiveLockScreenWallpaper$1$2);
        }
    }

    public final void updateMusicArtBg(View view) {
        Integer num;
        Integer num2;
        KgdLogUtil.i(TAG, "updateMusicArtBg view=" + view);
        int i = 2;
        if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            OplusKgdImmersiveController$handler$1 oplusKgdImmersiveController$handler$1 = this.handler;
            RunnableC01792 runnableC01792 = new RunnableC01792(i);
            runnableC01792.this$0 = this;
            runnableC01792.$uiState = view;
            VarHandle.storeStoreFence();
            oplusKgdImmersiveController$handler$1.post(runnableC01792);
            return;
        }
        Integer num3 = this.currentImmersiveType;
        if ((num3 != null && num3.intValue() == -1) || ((num = this.currentImmersiveType) != null && num.intValue() == 1)) {
            if (view == null) {
                clearStaleMusicImmersiveState(true);
                return;
            }
            return;
        }
        if (view == null) {
            if (!isBitmapImmersiveActive() && ((this.currentImmersiveType == null || ((num2 = this.currentImmersiveType) != null && num2.intValue() == 2)) && this.immersiveRenderMode == ImmersiveRenderMode.MUSIC_VIEW)) {
                stopMusicImmersiveWallpaper(false);
                return;
            } else {
                clearStaleMusicImmersiveState(isBitmapImmersiveActive());
                return;
            }
        }
        ImmersiveRenderMode immersiveRenderMode = this.immersiveRenderMode;
        ImmersiveRenderMode immersiveRenderMode2 = ImmersiveRenderMode.MUSIC_VIEW;
        if (immersiveRenderMode == immersiveRenderMode2 && this.isImmersiveWallpaperState >= 4 && !Intrinsics.areEqual(this.musicArtBackgroundView, view)) {
            bindMusicArtView(view);
            setMusicArtViewToSurface$default(this, this.isLockImmersive, 2);
            return;
        }
        boolean z = this.immersiveRenderMode == ImmersiveRenderMode.BITMAP && this.surfaceControlViewHost != null;
        boolean z2 = this.immersiveRenderMode != immersiveRenderMode2;
        if (z) {
            this.pendingBitmapToMusicCrossfade = true;
        } else {
            releaseMaskViewSurface();
            clearCropBitmap(false);
        }
        this.immersiveRenderMode = immersiveRenderMode2;
        this.isImmersiveWallpaper = true;
        boolean z3 = ((ILockScreenExtStateController.ExtStateEvent) ((LockScreenNotificationExtStateManager) this.lockScreenNotificationExtStateManager.get()).getExtStateEvent().getValue()).getReason() == 305 || ((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).mGoingToSleep || !isScreenOffUiReadyForMusicEnter();
        this.isLockImmersive = z3;
        if (z3) {
            markDesktopWallpaperUnderlayIfNeeded();
        }
        bindMusicArtView(view);
        replaceAOD(this.lastUiState);
        if (this.isImmersiveWallpaperAod) {
            return;
        }
        if (this.isImmersiveWallpaperState <= 0 || z || z2) {
            setImmersiveWallpaperState(2);
        }
        removeMessage();
        enterMusicImmersiveLockScreenWallpaper(z2 && !this.pendingBitmapToMusicCrossfade);
    }

    public final void updateMusicBlackMaskAlpha(float f) {
        this.musicBlackMaskRatio = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        getMusicBlackMaskView().setAlpha(this.musicBlackMaskRatio);
    }

    public final void updateUiSate(SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState) {
        Runnable runnable = this.onUiStateChangedRunnable;
        if (runnable != null && hasCallbacks(runnable)) {
            removeCallbacks(runnable);
        }
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            updateUiStateInner(smoothTransitionUiState);
            return;
        }
        RunnableC01792 runnableC01792 = new RunnableC01792(0);
        runnableC01792.this$0 = this;
        runnableC01792.$uiState = smoothTransitionUiState;
        VarHandle.storeStoreFence();
        this.onUiStateChangedRunnable = runnableC01792;
        post(runnableC01792);
    }

    public final void updateUiStateInner(SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState) {
        SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState2;
        if (this.pendingMusicScreenOffEnter && isScreenOffUiReadyForMusicEnterInner(smoothTransitionUiState)) {
            KgdLogUtil.i(TAG, "screen-off ui ready: " + smoothTransitionUiState + ", run deferred music enter");
            tryRunPendingMusicScreenOffEnter(true);
        }
        SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState3 = this.lastUiState;
        SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState4 = SmoothTransitionController.SmoothTransitionUiState.WORKSHOP_AOD;
        if (smoothTransitionUiState3 == smoothTransitionUiState4 || smoothTransitionUiState != smoothTransitionUiState4) {
            if (smoothTransitionUiState3 == smoothTransitionUiState4 && smoothTransitionUiState != smoothTransitionUiState4) {
                int i = 0;
                if (smoothTransitionUiState != SmoothTransitionController.SmoothTransitionUiState.UNLOCK && AodData.getInstance(this.context).isWorkShopAod() && AodData.getInstance(this.context).shouldShowWallpaperInAOD() && this.isImmersiveWallpaper && !((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).mKeyguardGoingAway) {
                    this.isImmersiveWallpaperAod = false;
                    KgdLogUtil.i(TAG, "exitWorkShopAod");
                    if (((COUIDynamicAnimation) getImmersiveAlphaAnimation()).mRunning) {
                        getImmersiveAlphaAnimation().cancel();
                    }
                    cancelPipelineCrossfade(true);
                    KeyguardThreadUtil.Companion companion = KeyguardThreadUtil.Companion;
                    OplusKgdImmersiveController$exitWorkShopAod$1 oplusKgdImmersiveController$exitWorkShopAod$1 = new OplusKgdImmersiveController$exitWorkShopAod$1(i);
                    oplusKgdImmersiveController$exitWorkShopAod$1.this$0 = this;
                    VarHandle.storeStoreFence();
                    companion.runOnWorkThread(oplusKgdImmersiveController$exitWorkShopAod$1);
                } else {
                    this.isImmersiveWallpaperAod = false;
                }
            }
        } else if (AodData.getInstance(this.context).isWorkShopAod() && AodData.getInstance(this.context).shouldShowWallpaperInAOD()) {
            if (this.isImmersiveWallpaper) {
                this.isImmersiveWallpaperAod = true;
                Runnable runnable = this.restoreMusicAfterWorkShopRunnable;
                if (runnable != null) {
                    removeCallbacks(runnable);
                }
                this.restoreMusicAfterWorkShopRunnable = null;
                KgdLogUtil.i(TAG, "enterWorkShopAod");
                exitImmersiveLockScreenWallpaper();
            } else {
                this.isImmersiveWallpaperAod = true;
            }
        }
        replaceAOD(smoothTransitionUiState);
        Integer num = this.currentImmersiveType;
        if (num != null && num.intValue() == 1 && AodData.getInstance(this.context).isWorkShopAod() && !SmoothTransitionController.Companion.getInstance(this.context).shouldWindowBeTransparent() && (((smoothTransitionUiState2 = this.lastUiState) == smoothTransitionUiState4 && smoothTransitionUiState == SmoothTransitionController.SmoothTransitionUiState.KEYGUARD) || (smoothTransitionUiState2 == SmoothTransitionController.SmoothTransitionUiState.KEYGUARD && smoothTransitionUiState == smoothTransitionUiState4))) {
            OplusKgdImmersiveController$handler$1 oplusKgdImmersiveController$handler$1 = this.handler;
            OplusKgdImmersiveController$exitWorkShopAod$1 oplusKgdImmersiveController$exitWorkShopAod$2 = new OplusKgdImmersiveController$exitWorkShopAod$1(5);
            oplusKgdImmersiveController$exitWorkShopAod$2.this$0 = this;
            VarHandle.storeStoreFence();
            oplusKgdImmersiveController$handler$1.post(oplusKgdImmersiveController$exitWorkShopAod$2);
        }
        this.lastUiState = smoothTransitionUiState;
    }
}
