package com.oplus.systemui.shade;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.WindowManager;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.compose.ui.spatial.RectList$;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.lifecycle.UnlockViewLifecycleOwner$;
import com.android.systemui.model.SysUiState;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.power.shared.model.WakeSleepReason;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowState;
import com.android.systemui.shade.OplusNotificationShadeWindowControllerEx;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.shade.ShadeWindowLogger;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShadeDepthControllerEx;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.oplus.keyguard.KeyguardVFXController;
import com.oplus.keyguard.OplusPowerBlockController;
import com.oplus.keyguard.utils.OplusSwitchUserUtil;
import com.oplus.systemui.aod.aodclock.constant.AodData;
import com.oplus.systemui.aod.common.AodManager;
import com.oplus.systemui.aod.display.AODDisplayUtil;
import com.oplus.systemui.aod.display.BaseDisplayUtil;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.common.OplusHdrUIController;
import com.oplus.systemui.common.flavor.OplusFlavorManager;
import com.oplus.systemui.keyguard.OplusKeyguardViewMediatorExImpl;
import com.oplus.systemui.keyguard.wallpaper.OplusLockScreenWallpaperController;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppInfo;
import com.oplus.systemui.notification.extend.OplusNotificationEx;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateManager;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplus.transition.ShellContinuousTransitionController;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.AodFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNotificationShadeWindowControllerExImpl extends OplusNotificationShadeWindowControllerEx implements OplusNotificationEx {
    public static final Companion Companion = new Companion();
    public static final String SHADE_TOP_UI_TAG = "NotificationShadeWindowController";
    public static final String TAG = "OplusNotificationShadeWindowControllerExImpl";
    private final Context context;
    private final Lazy dozeParameters;
    private final Lazy flavorManager;
    private final Lazy keyguardViewMediator;
    private final Lazy lockScreenNotificationExtStateManager;
    private View mAodRootView;
    public boolean mDebugBackGestureLog;
    public int mKeepKeyguardOn;
    public volatile boolean mPowerBlock;
    private View mPowerBlockView;
    private final Lazy notificationShadeDepthControllerEx;
    private final Lazy notificationShadeWindowController;
    private final Lazy oplusHdrUIController;
    private final Lazy oplusKeyguardViewMediatorExImpl;
    private final Lazy oplusLockScreenWallpaperController;
    private final Lazy oplusPowerBlockController;
    private final PanelAnimationEx panelAnimationEx;
    private final Lazy powerInteractor;
    private final Lazy remoteInputManager;
    private final Lazy separateNotificationAndQSEx;
    private final Lazy shadeWindowLogger;
    private final Lazy switchUserUtilLazy;
    private final Lazy topUiController;
    public boolean upgradeUxFlag;
    private final Runnable uxFlagUpdateRunnable;
    private final SysUIViewDialogInteractor viewDialogInteractor;
    private final Lazy wakefulnessLifecycle;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.shade.OplusNotificationShadeWindowControllerExImpl$attach$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        final /* synthetic */ NotificationShadeWindowControllerImpl $nswc;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.shade.OplusNotificationShadeWindowControllerExImpl$attach$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01091 extends SuspendLambda implements Function2 {
            final /* synthetic */ NotificationShadeWindowControllerImpl $nswc;
            int label;
            final /* synthetic */ OplusNotificationShadeWindowControllerExImpl this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.shade.OplusNotificationShadeWindowControllerExImpl$attach$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            public final class C01101 implements FlowCollector {
                public /* synthetic */ NotificationShadeWindowControllerImpl $nswc;
                public /* synthetic */ OplusNotificationShadeWindowControllerExImpl this$0;

                public final Object emit(Object obj, Continuation continuation) {
                    ((Boolean) obj).getClass();
                    OplusNotificationShadeWindowControllerExImpl.access$tryToShowWallpaperAndRelayoutWhileWakingUp(this.this$0, this.$nswc);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01091(OplusNotificationShadeWindowControllerExImpl oplusNotificationShadeWindowControllerExImpl, NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusNotificationShadeWindowControllerExImpl;
                this.$nswc = notificationShadeWindowControllerImpl;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C01091(this.this$0, this.$nswc, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flowIsAwake = ((PowerInteractor) this.this$0.powerInteractor.get()).isAwake();
                    OplusNotificationShadeWindowControllerExImpl oplusNotificationShadeWindowControllerExImpl = this.this$0;
                    NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = this.$nswc;
                    C01101 c01101 = new C01101();
                    c01101.this$0 = oplusNotificationShadeWindowControllerExImpl;
                    c01101.$nswc = notificationShadeWindowControllerImpl;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (flowIsAwake.collect(c01101, this) == coroutine_suspended) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl, Continuation continuation) {
            super(3, continuation);
            this.$nswc = notificationShadeWindowControllerImpl;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = OplusNotificationShadeWindowControllerExImpl.this.new AnonymousClass1(this.$nswc, (Continuation) obj3);
            anonymousClass1.L$0 = (LifecycleOwner) obj;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.STARTED;
                C01091 c01091 = new C01091(OplusNotificationShadeWindowControllerExImpl.this, this.$nswc, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c01091, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.shade.OplusNotificationShadeWindowControllerExImpl$attach$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.shade.OplusNotificationShadeWindowControllerExImpl$attach$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ OplusNotificationShadeWindowControllerExImpl this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.shade.OplusNotificationShadeWindowControllerExImpl$attach$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01111 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusNotificationShadeWindowControllerExImpl this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.shade.OplusNotificationShadeWindowControllerExImpl$attach$2$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01121 implements FlowCollector {
                    public /* synthetic */ OplusNotificationShadeWindowControllerExImpl this$0;

                    public final Object emit(Object obj, Continuation continuation) {
                        ILockScreenExtStateController.ExtStateEvent extStateEvent = (ILockScreenExtStateController.ExtStateEvent) obj;
                        OplusNotificationShadeWindowControllerExImpl oplusNotificationShadeWindowControllerExImpl = this.this$0;
                        if (oplusNotificationShadeWindowControllerExImpl.mDebugBackGestureLog) {
                            OplusLockIconView$.ExternalSyntheticOutline0.m("extStateEvent: enter EXT_STATE_IMMERSIVE  ", OplusNotificationShadeWindowControllerExImpl.TAG, extStateEvent.getNewState() == 2);
                        }
                        NotificationShadeWindowState currentShadeWindowState = ((NotificationShadeWindowController) oplusNotificationShadeWindowControllerExImpl.notificationShadeWindowController.get()).getCurrentShadeWindowState();
                        currentShadeWindowState.getClass();
                        oplusNotificationShadeWindowControllerExImpl.updateBackGestureEnable(currentShadeWindowState);
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01111(OplusNotificationShadeWindowControllerExImpl oplusNotificationShadeWindowControllerExImpl, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusNotificationShadeWindowControllerExImpl;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01111(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow extStateEvent = ((LockScreenNotificationExtStateManager) this.this$0.lockScreenNotificationExtStateManager.get()).getExtStateEvent();
                        OplusNotificationShadeWindowControllerExImpl oplusNotificationShadeWindowControllerExImpl = this.this$0;
                        C01121 c01121 = new C01121();
                        c01121.this$0 = oplusNotificationShadeWindowControllerExImpl;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (extStateEvent.collect(c01121, this) == coroutine_suspended) {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(OplusNotificationShadeWindowControllerExImpl oplusNotificationShadeWindowControllerExImpl, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusNotificationShadeWindowControllerExImpl;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
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
                BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new C01111(this.this$0, null), 2);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass2 anonymousClass2 = OplusNotificationShadeWindowControllerExImpl.this.new AnonymousClass2((Continuation) obj3);
            anonymousClass2.L$0 = (LifecycleOwner) obj;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.STARTED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(OplusNotificationShadeWindowControllerExImpl.this, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
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

    public OplusNotificationShadeWindowControllerExImpl(Context context, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, Lazy lazy11, PanelAnimationEx panelAnimationEx, SysUIViewDialogInteractor sysUIViewDialogInteractor, Lazy lazy12, Lazy lazy13, Lazy lazy14, Lazy lazy15, Lazy lazy16, Lazy lazy17) {
        this.context = context;
        this.oplusPowerBlockController = lazy;
        this.wakefulnessLifecycle = lazy2;
        this.flavorManager = lazy3;
        this.oplusKeyguardViewMediatorExImpl = lazy4;
        this.switchUserUtilLazy = lazy5;
        this.notificationShadeDepthControllerEx = lazy6;
        this.shadeWindowLogger = lazy7;
        this.powerInteractor = lazy8;
        this.dozeParameters = lazy9;
        this.keyguardViewMediator = lazy10;
        this.separateNotificationAndQSEx = lazy11;
        this.panelAnimationEx = panelAnimationEx;
        this.viewDialogInteractor = sysUIViewDialogInteractor;
        this.oplusLockScreenWallpaperController = lazy12;
        this.remoteInputManager = lazy13;
        this.notificationShadeWindowController = lazy14;
        this.lockScreenNotificationExtStateManager = lazy15;
        this.oplusHdrUIController = lazy16;
        this.topUiController = lazy17;
        OplusNotificationShadeWindowControllerExImpl$uxFlagUpdateRunnable$1 oplusNotificationShadeWindowControllerExImpl$uxFlagUpdateRunnable$1 = new OplusNotificationShadeWindowControllerExImpl$uxFlagUpdateRunnable$1();
        oplusNotificationShadeWindowControllerExImpl$uxFlagUpdateRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.uxFlagUpdateRunnable = oplusNotificationShadeWindowControllerExImpl$uxFlagUpdateRunnable$1;
    }

    public static final void access$tryToShowWallpaperAndRelayoutWhileWakingUp(OplusNotificationShadeWindowControllerExImpl oplusNotificationShadeWindowControllerExImpl, NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl) {
        oplusNotificationShadeWindowControllerExImpl.getClass();
        ViewGroup windowRootView = notificationShadeWindowControllerImpl.getWindowRootView();
        ViewGroup.LayoutParams layoutParams = windowRootView != null ? windowRootView.getLayoutParams() : null;
        WindowManager.LayoutParams layoutParams2 = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        if (layoutParams2 == null) {
            return;
        }
        int i = layoutParams2.flags & ShellContinuousTransitionController.TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE;
        boolean z = false;
        boolean z2 = i == 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("tryToShowWallpaperAndRelayout: isNotShowWallpaper=", "OplusNotificationShadeWindowControllerExImpl-->", "SystemUi--Keyguard", z2);
        }
        if (z2) {
            NotificationShadeWindowState currentShadeWindowState = notificationShadeWindowControllerImpl.getCurrentShadeWindowState();
            if (currentShadeWindowState.keyguardShowing || (currentShadeWindowState.dozing && ((DozeParameters) oplusNotificationShadeWindowControllerExImpl.dozeParameters.get()).getAlwaysOn())) {
                z = true;
            }
            if (oplusNotificationShadeWindowControllerExImpl.shouldShowWallpaper(z, currentShadeWindowState, (KeyguardViewMediator) oplusNotificationShadeWindowControllerExImpl.keyguardViewMediator.get())) {
                notificationShadeWindowControllerImpl.reapplyCurrentState();
            }
        }
    }

    public void adjustShadeUxFlag(Context context, boolean z) {
        this.upgradeUxFlag = z;
        context.getMainThreadHandler().removeCallbacks(this.uxFlagUpdateRunnable);
        if (z) {
            this.uxFlagUpdateRunnable.run();
            return;
        }
        Handler mainThreadHandler = context.getMainThreadHandler();
        Runnable runnable = this.uxFlagUpdateRunnable;
        FeatureOption.INSTANCE.getClass();
        mainThreadHandler.postDelayed(runnable, FeatureOption.isLightOs() ? 500L : 3000L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void applyWindowColorMode(NotificationShadeWindowState notificationShadeWindowState, WindowManager.LayoutParams layoutParams) {
        ((OplusHdrUIController) this.oplusHdrUIController.get()).setIsAod(notificationShadeWindowState.dozing);
        boolean zIsHdrEffectEnabled = ((OplusHdrUIController) this.oplusHdrUIController.get()).isHdrEffectEnabled();
        Object[] objArr = notificationShadeWindowState.keyguardShowing && !notificationShadeWindowState.keyguardOccluded;
        if (zIsHdrEffectEnabled) {
            layoutParams.setColorMode(objArr != false ? 2 : 0);
            ((OplusHdrUIController) this.oplusHdrUIController.get()).getClass();
            layoutParams.setDesiredHdrHeadroom(objArr != false ? 1.1f : 0.0f);
        } else if (KeyguardFeatureOption.INSTANCE.getSSupportWcgColorMode()) {
            layoutParams.setColorMode(notificationShadeWindowState.keyguardShowing ? 1 : 0);
        }
        ((OplusHdrUIController) this.oplusHdrUIController.get()).notifyHdrEffectChangedCallbacks(layoutParams.getColorMode() == 2);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(layoutParams.getColorMode(), "applyWindowColorMode: ", "OplusNotificationShadeWindowControllerExImpl-->", "SystemUi--Keyguard");
        }
    }

    public void attach(NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl) {
        ViewGroup windowRootView;
        ((OplusPowerBlockController) this.oplusPowerBlockController.get()).onStart();
        ((OplusHdrUIController) this.oplusHdrUIController.get()).onStart();
        if (isEarlyShowWallpaperFeatureEnable() && (windowRootView = notificationShadeWindowControllerImpl.getWindowRootView()) != null) {
            RepeatWhenAttachedKt.repeatWhenAttached$default(windowRootView, (CoroutineContext) null, new AnonymousClass1(notificationShadeWindowControllerImpl, null), 3);
        }
        ((BaseDisplayUtil) AODDisplayUtil.getInstance(this.context)).mOplusAODRefreshRateController.registerDisplayStateListener();
        ViewGroup windowRootView2 = notificationShadeWindowControllerImpl.getWindowRootView();
        if (windowRootView2 != null) {
            RepeatWhenAttachedKt.repeatWhenAttached$default(windowRootView2, (CoroutineContext) null, new AnonymousClass2(null), 3);
        }
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        try {
            if (strArr.length == 2 && "backgesturelog".equals(strArr[0])) {
                String str = strArr[1];
                if (PictorialAppInfo.TAG_ENABLE.equals(str)) {
                    this.mDebugBackGestureLog = true;
                    printWriter.println("OplusNotificationShadeWindowControllerExImpl: Enable updateBackGestureEnable log;");
                } else if ("disable".equals(str)) {
                    this.mDebugBackGestureLog = false;
                    printWriter.println("OplusNotificationShadeWindowControllerExImpl: Disable updateBackGestureEnable log;");
                }
            }
            printWriter.println("OplusNotificationShadeWindowControllerExImpl:");
            printWriter.println("\tmDebugBackGestureLog=" + this.mDebugBackGestureLog);
        } catch (NullPointerException e) {
            Log.e(TAG, "dump: NullPointerException caught, writer or args may be null", e);
        } catch (Exception e2) {
            Log.e(TAG, "dump: Unexpected exception", e2);
        }
    }

    public int getKeepKeyguardOnDuration() {
        return 10800000;
    }

    public int getKeyguardTurnOffDurationWhenWakingByNotification() {
        return 7000;
    }

    public final void hideViewIfNeeded() {
        ((NotificationRemoteInputManager) this.remoteInputManager.get()).closeRemoteInputs();
        this.viewDialogInteractor.dismissDialog("instant");
    }

    public final boolean isEarlyShowWallpaperFeatureEnable() {
        return ScrimUtil.getAnimLevel() == 4 || ScrimUtil.getAnimLevel() == 3 || !((OplusLockScreenWallpaperController) this.oplusLockScreenWallpaperController.get()).isLiveWallpaper;
    }

    public boolean isErrorFpTouchWhenScreenOff(NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl) {
        View view;
        View viewFindViewById = this.mAodRootView;
        if (viewFindViewById == null) {
            ViewGroup windowRootView = notificationShadeWindowControllerImpl.getWindowRootView();
            viewFindViewById = windowRootView != null ? windowRootView.findViewById(2131362099) : null;
        }
        this.mAodRootView = viewFindViewById;
        return ((WakefulnessLifecycle) this.wakefulnessLifecycle.get()).getLastWakeReason() == 98 && (view = this.mAodRootView) != null && view.getVisibility() == 0;
    }

    public final boolean isKeepKeyguardOn() {
        return this.mKeepKeyguardOn == 1;
    }

    public final boolean isPowerBlock() {
        return this.mPowerBlock;
    }

    public boolean isShadeExpandedFromOtherScene(NotificationShadeWindowState notificationShadeWindowState, NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl) {
        boolean zIsErrorFpTouchWhenScreenOff = isErrorFpTouchWhenScreenOff(notificationShadeWindowControllerImpl);
        boolean zCheckMaskShow = AODDisplayUtil.getInstance(this.context).mOplusAODMaskAnimController.checkMaskShow(notificationShadeWindowControllerImpl.getWindowRootView());
        boolean z = (this.mPowerBlock && zIsErrorFpTouchWhenScreenOff) || notificationShadeWindowState.qsPanelVisible || zCheckMaskShow;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(TextFieldImplKt$.ExternalSyntheticOutline0.m("isExpanded: isPowerBlock=", " ,isErrorFpTouchWhenScreenOff=", " ,qsPanelVisible=", this.mPowerBlock, zIsErrorFpTouchWhenScreenOff), notificationShadeWindowState.qsPanelVisible, ", isAodMaskShow=", zCheckMaskShow), null, 8);
        ((ShadeWindowLogger) this.shadeWindowLogger.get()).logIsExpandedEx(z, this.mPowerBlock, zIsErrorFpTouchWhenScreenOff, notificationShadeWindowState.qsPanelVisible, zCheckMaskShow);
        return z;
    }

    public final boolean isWakingByNotification() {
        return AODDisplayUtil.getInstance(this.context).getPmWakeReason() == 999;
    }

    public void logShadeFocusable(NotificationShadeWindowState notificationShadeWindowState, int i) {
        boolean z = notificationShadeWindowState.notificationShadeFocusable;
        boolean z2 = notificationShadeWindowState.shadeOrQsExpanded;
        boolean z3 = notificationShadeWindowState.bouncerShowing;
        boolean z4 = notificationShadeWindowState.keyguardShowing;
        boolean z5 = notificationShadeWindowState.keyguardOccluded;
        boolean z6 = notificationShadeWindowState.keyguardNeedsInput;
        boolean z7 = NotificationRemoteInputManager.ENABLE_REMOTE_INPUT;
        boolean z8 = notificationShadeWindowState.remoteInputActive;
        boolean z9 = notificationShadeWindowState.glanceableHubShowing;
        boolean z10 = notificationShadeWindowState.keyguardIsAboutToOccluded;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("mNotificationShadeFocusable= ", ", mPanelExpanded= ", ", mBouncerShowing= ", z, z2);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", mKeyguardShowing= ", ", mKeyguardOccluded= ", sbM, z3, z4);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", mKeyguardNeedsInput= ", ", ENABLE_REMOTE_INPUT= ", sbM, z5, z6);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", mRemoteInputActive= ", ", glanceableHubShowing= ", sbM, z7, z8);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", keyguardIsAboutToOccluded= ", ", layoutFlags= ", sbM, z9, z10);
        sbM.append(i);
        String string = sbM.toString();
        Handler handler = OplusNotificationLogger.LOG_HANDLER;
        LogUtil.i(LogUtil.TAG_NOTIFICATION, TAG, "SHADE_FOCUSABLE-->".concat(string));
    }

    public void logShadeVisible(NotificationShadeWindowState notificationShadeWindowState, boolean z) {
        boolean z2 = notificationShadeWindowState.keyguardShowing;
        boolean z3 = notificationShadeWindowState.keyguardOccluded;
        boolean z4 = notificationShadeWindowState.panelVisible;
        boolean z5 = notificationShadeWindowState.keyguardFadingAway;
        boolean z6 = notificationShadeWindowState.keyguardGoingAway;
        boolean z7 = notificationShadeWindowState.bouncerShowing;
        int i = notificationShadeWindowState.scrimsVisibility;
        int i2 = notificationShadeWindowState.backgroundBlurRadius;
        boolean z8 = notificationShadeWindowState.forcePluginOpen;
        boolean z9 = notificationShadeWindowState.mediaBackdropShowing;
        float blurFraction = ((OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSEx.get()).getBlurFraction();
        float blurFraction2 = this.panelAnimationEx.getPanelExpandFraction().getBlurFraction();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("mKeyguardShowing= ", ", mKeyguardOccluded= ", ", mPanelVisible= ", z2, z3);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", mKeyguardFadingAway= ", ", mKeyguardGoingAway= ", sbM, z4, z5);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", mBouncerShowing= ", ", mScrimsVisibility= ", sbM, z6, z7);
        RectList$.ExternalSyntheticOutline0.m(i, i2, ", mBackgroundBlurRadius= ", ", mForcePluginOpen= ", sbM);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", mediaBackdropShowing= ", ", viewVisibility= ", sbM, z8, z9);
        sbM.append(z);
        sbM.append(", qsBlurFraction= ");
        sbM.append(blurFraction);
        sbM.append(", panelBlurFraction= ");
        sbM.append(blurFraction2);
        String string = sbM.toString();
        Handler handler = OplusNotificationLogger.LOG_HANDLER;
        LogUtil.i(LogUtil.TAG_NOTIFICATION, TAG, "SHADE_VISIBLE-->".concat(string));
    }

    public void refreshRealPowerBlock(boolean z, NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl) {
        View viewFindViewById;
        boolean z2 = this.mPowerBlock && z;
        ViewGroup windowRootView = notificationShadeWindowControllerImpl.getWindowRootView();
        if (windowRootView != null && (viewFindViewById = windowRootView.findViewById(2131364590)) != null) {
            viewFindViewById.setVisibility(z2 ? 0 : 8);
        }
        Log.i(TAG, "mPowerBlock: block=" + z + " refreshRealBlock=" + z2);
    }

    public final void restrainNavigationBarInvertColor(boolean z, boolean z2) {
        if (z2 != z) {
            ((NotificationShadeDepthControllerEx) this.notificationShadeDepthControllerEx.get()).restrainNavigationBarInvertColor(4, z2);
        }
    }

    public void setKeepKeyguardOn(int i) {
        this.mKeepKeyguardOn = i;
    }

    public void setNotificationShadeSurfaceAlpha(float f) {
        ViewRootImpl viewRootImpl;
        SurfaceControl surfaceControl;
        ViewGroup windowRootView = ((NotificationShadeWindowController) this.notificationShadeWindowController.get()).getWindowRootView();
        if (windowRootView == null || (viewRootImpl = windowRootView.getViewRootImpl()) == null || (surfaceControl = viewRootImpl.getSurfaceControl()) == null || !surfaceControl.isValid()) {
            return;
        }
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        try {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "NotificationShade SurfaceControl setAlpha=" + f, null, 8);
            transaction.setAlpha(surfaceControl, f);
            transaction.apply();
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

    public void setPowerBlock(boolean z, NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl) {
        if (this.mPowerBlock == z) {
            return;
        }
        this.mPowerBlock = z;
        notificationShadeWindowControllerImpl.reapplyCurrentState();
        boolean z2 = z && ((OplusPowerBlockController) this.oplusPowerBlockController.get()).shouldRealBlock();
        View viewFindViewById = this.mPowerBlockView;
        if (viewFindViewById == null) {
            ViewGroup windowRootView = notificationShadeWindowControllerImpl.getWindowRootView();
            viewFindViewById = windowRootView != null ? windowRootView.findViewById(2131364590) : null;
        }
        this.mPowerBlockView = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(z2 ? 0 : 8);
        }
        Log.i(TAG, "mPowerBlock: block=" + z + " shouldRealBlock=" + z2);
    }

    public void setSoftInputMode(WindowManager.LayoutParams layoutParams) {
        layoutParams.softInputMode = 16;
    }

    public void setUpNotificationShadeView(ViewGroup viewGroup) {
        ((OplusFlavorManager) this.flavorManager.get()).getMiniCapsuleManager().injectNotificationShade(viewGroup);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0085  */
    /* JADX WARN: Code duplicated, block: B:84:0x0171  */
    /* JADX WARN: Code duplicated, block: B:97:0x0196 A[DONT_INVERT] */
    public boolean shouldShowWallpaper(boolean z, NotificationShadeWindowState notificationShadeWindowState, KeyguardViewMediator keyguardViewMediator) {
        boolean z2;
        boolean z3;
        boolean leaveOpenOnKeyguardSceneAnimRunning = ((OplusKeyguardViewMediatorExImpl) this.oplusKeyguardViewMediatorExImpl.get()).getLeaveOpenOnKeyguardSceneAnimRunning();
        OplusSwitchUserUtil oplusSwitchUserUtil = (OplusSwitchUserUtil) this.switchUserUtilLazy.get();
        boolean z4 = true;
        if (oplusSwitchUserUtil.mSwitchUserStartTimeForWallpaper == -1) {
            z2 = false;
        } else if (SystemClock.elapsedRealtime() < oplusSwitchUserUtil.mSwitchUserStartTimeForWallpaper + 100) {
            z2 = true;
        } else {
            KgdLogUtil.w("SwitchUserUtil", "reset mSwitchUserStartTimeForWallpaper in checkAndFixSwitchTimeIfNeeded");
            oplusSwitchUserUtil.mSwitchUserStartTimeForWallpaper = -1L;
            z2 = false;
        }
        WakefulnessModel wakefulnessModel = (WakefulnessModel) ((PowerInteractor) this.powerInteractor.get()).getDetailedWakefulness().getValue();
        boolean z5 = this.mPowerBlock && !(isEarlyShowWallpaperFeatureEnable() && wakefulnessModel.isAwake() && (wakefulnessModel.getLastWakeReason() != WakeSleepReason.OTHER || wakefulnessModel.getLastWakeReason().getPowerManagerWakeReason() != 98));
        SuperPowerSaveSettingsObserver.Companion companion = SuperPowerSaveSettingsObserver.Companion;
        if (companion.getInstance().isSuperPowerSaveState) {
            z4 = false;
        } else if (!leaveOpenOnKeyguardSceneAnimRunning) {
            if (notificationShadeWindowState.shadeFadingIn || keyguardViewMediator.mDozing || this.mPowerBlock) {
                if (AodManager.getInstance(this.context).getPowerOffReason() != 4 || keyguardViewMediator.mDeviceInteractive || notificationShadeWindowState.keyguardShowing) {
                    boolean zIsPanoramicProcessType = AODDisplayUtil.getInstance(this.context).isPanoramicProcessType();
                    Context context = this.context;
                    if (zIsPanoramicProcessType) {
                        if (!AodManager.getInstance(context).shouldRapidUnlockWhilePanoramicAOD()) {
                            z4 = z;
                        } else if (((OplusPowerBlockController) this.oplusPowerBlockController.get()).getTemporaryCovering()) {
                            z4 = false;
                        }
                    } else if (!AODDisplayUtil.getInstance(context).isWorkshopProcessType()) {
                        boolean z6 = (AodData.getInstance(this.context).mAodOptionsMgr.isCurrentAodSwitchEnable() && AodData.getInstance(this.context).getAodType() == 0) || (AodData.getInstance(this.context).isAodEnable() && !AodData.getInstance(this.context).mAodOptionsMgr.isCurrentAodSwitchEnable());
                        AodFeatureOption.INSTANCE.getClass();
                        if (AodFeatureOption.isSupportAod()) {
                            z3 = false;
                        } else {
                            FeatureOption.INSTANCE.getClass();
                            if (FeatureOption.isTablet()) {
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                        }
                        boolean z7 = !this.mPowerBlock && notificationShadeWindowState.shadeFadingIn;
                        if (z6 || z3) {
                            KeyguardVFXController.Companion.getInstance(this.context).getClass();
                            if (KeyguardVFXController.shouldDisableSOAVFX() && z7) {
                                z4 = false;
                            } else if (z5) {
                                z4 = false;
                            }
                        } else if (z5) {
                            z4 = false;
                        }
                    } else if (SmoothTransitionController.Companion.getInstance(this.context).shouldWindowBeTransparent()) {
                        z4 = false;
                    } else {
                        z4 = false;
                    }
                } else {
                    z4 = false;
                }
            } else if (z) {
                z4 = false;
            } else {
                z4 = false;
            }
        }
        boolean z8 = notificationShadeWindowState.shadeFadingIn;
        boolean z9 = keyguardViewMediator.mDozing;
        boolean z10 = this.mPowerBlock;
        boolean temporaryCovering = ((OplusPowerBlockController) this.oplusPowerBlockController.get()).getTemporaryCovering();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("showWallpaper=", " keyguardOrAod=", " fadeIn=", z4, z);
        KeyguardLogger$.ExternalSyntheticOutline0.m(" animLeave=", " dozing=", sbM, z8, leaveOpenOnKeyguardSceneAnimRunning);
        KeyguardLogger$.ExternalSyntheticOutline0.m(" powerBlock=", " isBlockWallpaperWhenPowerBlock=", sbM, z9, z10);
        KeyguardLogger$.ExternalSyntheticOutline0.m(" temporaryCovering=", " isWallpaperHideDelay=", sbM, z5, temporaryCovering);
        sbM.append(z2);
        String string = sbM.toString();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            String string2 = string.toString();
            if (string2 == null) {
                string2 = "";
            }
            Log.d("SystemUi--Keyguard", "OplusNotificationShadeWindowControllerExImpl-->".concat(string2));
        }
        ((ShadeWindowLogger) this.shadeWindowLogger.get()).d(string);
        return z4;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0028  */
    public void updateBackGestureEnable(NotificationShadeWindowState notificationShadeWindowState) {
        boolean z;
        boolean zIsImmersiveState = ((LockScreenNotificationExtStateManager) this.lockScreenNotificationExtStateManager.get()).isImmersiveState();
        boolean z2 = notificationShadeWindowState.keyguardShowing;
        if (z2 && !notificationShadeWindowState.keyguardOccluded && !notificationShadeWindowState.bouncerShowing && !notificationShadeWindowState.qsPanelVisible) {
            z = notificationShadeWindowState.statusBarState == 1 && !notificationShadeWindowState.dozing && zIsImmersiveState;
        }
        if (this.mDebugBackGestureLog) {
            boolean z3 = notificationShadeWindowState.keyguardOccluded;
            boolean z4 = notificationShadeWindowState.bouncerShowing;
            boolean z5 = notificationShadeWindowState.qsPanelVisible;
            int i = notificationShadeWindowState.statusBarState;
            boolean z6 = notificationShadeWindowState.dozing;
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("updateBackGestureEnable: enable=", ", keyguardShowing=", ", keyguardOccluded=", z, z2);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", bouncerShowing=", ", qsPanelVisible=", sbM, z3, z4);
            PromptIconState$.ExternalSyntheticOutline0.m(i, ", statusBarState=", ", dozing=", sbM, z5);
            UnlockViewLifecycleOwner$.ExternalSyntheticOutline0.m(", isImmersive=", TAG, sbM, z6, zIsImmersiveState);
        }
        ((SysUiState) Dependency.sDependency.getDependencyInner(SysUiState.class)).setFlag(4398046511104L, z).commitUpdate();
    }

    public void updateColorMode(boolean z, WindowManager.LayoutParams layoutParams) {
    }

    public void updatePrivateFlags(WindowManager.LayoutParams layoutParams) {
    }

    public boolean useCustomScreenOffTimeout(Context context) {
        return OplusSubDisplayUtils.Companion.getInstance(context).getSupportSubDisplay() && !DeviceStateController.getInstance().isOpenOrHalfOpen();
    }
}
