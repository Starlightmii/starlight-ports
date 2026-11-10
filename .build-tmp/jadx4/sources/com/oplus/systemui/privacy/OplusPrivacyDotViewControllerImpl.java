package com.oplus.systemui.privacy;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Process;
import android.util.Log;
import android.view.Display;
import android.view.DisplayInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.app.animation.Interpolators;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.bluetooth.BluetoothUtils$;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.people.PeopleBackupFollowUpJob$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.OplusPrivacyDotViewController;
import com.android.systemui.statusbar.data.repository.HomeStatusBarComponentsRepository;
import com.android.systemui.statusbar.events.SystemStatusAnimationCallback;
import com.android.systemui.statusbar.events.SystemStatusAnimationScheduler;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.concurrent.Executor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPrivacyDotViewControllerImpl extends OplusPrivacyDotViewController {
    public static final Companion Companion = new Companion();
    private final SystemStatusAnimationScheduler animationScheduler;
    private Boolean cachedIsMiddleOrHighAnimLevel;
    private Runnable cancelRunnable;
    private final ConfigurationController configurationController;
    private final Context context;
    private ViewState currentViewState;
    public boolean displayConfigPending;
    private final HomeStatusBarComponentsRepository homeStatusBarComponentsRepository;
    private PrivacyDotLayoutState lastAppliedPrivacyDotLayoutState;
    private String lastDisplayUniqueId;
    private final Object lock;
    public int mDotSize;
    private final WakefulnessLifecycle.Observer mWakefulnessObserver;
    private final Executor mainExecutor;
    private ViewState nextViewState;
    private Runnable pendingTimeoutCancel;
    private final PowerInteractor powerInteractor;
    private final CoroutineScope scope;
    private final ShadeInteractor shadeInteractor;
    private final StatusBarStateController stateController;
    private final SystemStatusAnimationCallback systemStatusAnimationCallback;
    public ViewGroup tl;
    private ImageView tlDotView;
    public ViewGroup tr;
    private ImageView trDotView;
    private final DelayableExecutor uiExecutor;
    private final WakefulnessLifecycle wakefulnessLifecycle;
    public boolean wakefulnessObserverAdded;
    private final WindowManager windowManager;
    public int lastDisplayRotation = -1;
    public int lastDisplayWidth = -1;
    public int lastDisplayHeight = -1;
    public int pendingTargetRotation = -1;
    public int pendingTargetWidth = -1;
    public int pendingTargetHeight = -1;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.privacy.OplusPrivacyDotViewControllerImpl$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ConfigurationController.ConfigurationListener {
        public /* synthetic */ OplusPrivacyDotViewControllerImpl this$0;

        public final void onConfigChanged(Configuration configuration) {
            OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = this.this$0;
            OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 = new OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1(0);
            oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1.f$0 = oplusPrivacyDotViewControllerImpl;
            VarHandle.storeStoreFence();
            oplusPrivacyDotViewControllerImpl.runOnSdThread(oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1);
        }

        public final void onDensityOrFontScaleChanged() {
            OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = this.this$0;
            OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 = new OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1(1);
            oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1.f$0 = oplusPrivacyDotViewControllerImpl;
            VarHandle.storeStoreFence();
            oplusPrivacyDotViewControllerImpl.runOnSdThread(oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1);
        }

        public final void onLayoutDirectionChanged(boolean z) {
            OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = this.this$0;
            OplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0 oplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0 = new OplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0(1);
            oplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0.f$0 = oplusPrivacyDotViewControllerImpl;
            oplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0.f$1 = z;
            VarHandle.storeStoreFence();
            oplusPrivacyDotViewControllerImpl.runOnSdThread(oplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.privacy.OplusPrivacyDotViewControllerImpl$init$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements StatusBarStateController.StateListener {
        public /* synthetic */ OplusPrivacyDotViewControllerImpl this$0;

        public final void onExpandedChanged(boolean z) {
            OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = this.this$0;
            OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 = new OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1(3);
            oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1.f$0 = oplusPrivacyDotViewControllerImpl;
            VarHandle.storeStoreFence();
            oplusPrivacyDotViewControllerImpl.runOnSdThread(oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1);
        }

        public final void onStateChanged(int i) {
            OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = this.this$0;
            OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 = new OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1(2);
            oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1.f$0 = oplusPrivacyDotViewControllerImpl;
            VarHandle.storeStoreFence();
            oplusPrivacyDotViewControllerImpl.runOnSdThread(oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.privacy.OplusPrivacyDotViewControllerImpl$init$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        int I$0;
        Object L$0;
        int label;

        public AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusPrivacyDotViewControllerImpl.this.new AnonymousClass3(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            StateFlow stateFlowIsQsExpanded;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            int i2 = 1;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ShadeInteractor shadeInteractor = OplusPrivacyDotViewControllerImpl.this.shadeInteractor;
                if (shadeInteractor != null && (stateFlowIsQsExpanded = shadeInteractor.isQsExpanded()) != null) {
                    OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = OplusPrivacyDotViewControllerImpl.this;
                    Flow flowCombine = FlowKt.combine(stateFlowIsQsExpanded, oplusPrivacyDotViewControllerImpl.shadeInteractor.isSeparateQsExpanded(), oplusPrivacyDotViewControllerImpl.powerInteractor.isInteractive(), new OplusPrivacyDotViewControllerImpl$init$3$1$1(4, null));
                    AnonymousClass4.AnonymousClass1 anonymousClass1 = new AnonymousClass4.AnonymousClass1(i2);
                    anonymousClass1.this$0 = oplusPrivacyDotViewControllerImpl;
                    VarHandle.storeStoreFence();
                    this.L$0 = SpillingKt.nullOutSpilledVariable(stateFlowIsQsExpanded);
                    this.I$0 = 0;
                    this.label = 1;
                    if (flowCombine.collect(anonymousClass1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
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

    /* JADX INFO: renamed from: com.oplus.systemui.privacy.OplusPrivacyDotViewControllerImpl$init$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass4 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.privacy.OplusPrivacyDotViewControllerImpl$init$4$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public final /* synthetic */ int $r8$classId;
            public /* synthetic */ OplusPrivacyDotViewControllerImpl this$0;

            public /* synthetic */ AnonymousClass1(int i) {
                this.$r8$classId = i;
            }

            public final Object emit(Object obj, Continuation continuation) {
                switch (this.$r8$classId) {
                    case 0:
                        OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = this.this$0;
                        OplusPrivacyDotViewControllerImpl$init$4$1$$ExternalSyntheticLambda0 oplusPrivacyDotViewControllerImpl$init$4$1$$ExternalSyntheticLambda0 = new OplusPrivacyDotViewControllerImpl$init$4$1$$ExternalSyntheticLambda0();
                        oplusPrivacyDotViewControllerImpl$init$4$1$$ExternalSyntheticLambda0.f$0 = oplusPrivacyDotViewControllerImpl;
                        oplusPrivacyDotViewControllerImpl$init$4$1$$ExternalSyntheticLambda0.f$1 = (PrivacyDotLayoutState) obj;
                        VarHandle.storeStoreFence();
                        oplusPrivacyDotViewControllerImpl.runOnSdThread(oplusPrivacyDotViewControllerImpl$init$4$1$$ExternalSyntheticLambda0);
                        break;
                    default:
                        boolean zBooleanValue = ((Boolean) obj).booleanValue();
                        OplusPrivacyDotViewControllerImplKt.access$dlog("needShow " + zBooleanValue);
                        OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl2 = this.this$0;
                        OplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0 oplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0 = new OplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0(2);
                        oplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0.f$0 = oplusPrivacyDotViewControllerImpl2;
                        oplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0.f$1 = zBooleanValue;
                        VarHandle.storeStoreFence();
                        oplusPrivacyDotViewControllerImpl2.runOnSdThread(oplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0);
                        break;
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass4(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusPrivacyDotViewControllerImpl.this.new AnonymousClass4(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow defaultPrivacyDotLayoutStateFlow = OplusPrivacyDotViewControllerImpl.this.getDefaultPrivacyDotLayoutStateFlow();
                OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = OplusPrivacyDotViewControllerImpl.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
                anonymousClass1.this$0 = oplusPrivacyDotViewControllerImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (defaultPrivacyDotLayoutStateFlow.collect(anonymousClass1, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: $r8$lambda$Og-EJzNvyYSUSld4hlX7x0cmgHM, reason: not valid java name */
    public static Unit m239$r8$lambda$OgEJzNvyYSUSld4hlX7x0cmgHM(OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl, boolean z) throws Throwable {
        Object obj;
        Unit unit;
        Object obj2 = oplusPrivacyDotViewControllerImpl.lock;
        synchronized (obj2) {
            try {
                OplusPrivacyDotViewControllerImplKt.access$dlog("setChipAnimation: " + oplusPrivacyDotViewControllerImpl.nextViewState.getChipAnimationStart() + " " + z);
                if (z != oplusPrivacyDotViewControllerImpl.nextViewState.getChipAnimationStart()) {
                    ViewState viewState = oplusPrivacyDotViewControllerImpl.nextViewState;
                    obj = obj2;
                    try {
                        oplusPrivacyDotViewControllerImpl.setNextViewState(viewState.copy((24575 & 1) != 0 ? viewState.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState.persistentDotFillColorRes : 0, (24575 & 4) != 0 ? viewState.shadeExpanded : false, (24575 & 8) != 0 ? viewState.needShow : false, (24575 & 16) != 0 ? viewState.wakeAllowsPrivacyDot : false, (24575 & 32) != 0 ? viewState.rtl : false, (24575 & 64) != 0 ? viewState.rotation : 0, (24575 & 128) != 0 ? viewState.height : 0, (24575 & 256) != 0 ? viewState.x : 0, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState.y : 0, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState.cornerIndex : 0, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState.designatedCorner : null, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState.chipAnimationStart : z, (24575 & 16384) != 0 ? viewState.preDraw : false));
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
                unit = Unit.INSTANCE;
            } catch (Throwable th2) {
                th = th2;
                obj = obj2;
            }
        }
        return unit;
    }

    public OplusPrivacyDotViewControllerImpl(CoroutineScope coroutineScope, Executor executor, Context context, WindowManager windowManager, StatusBarStateController statusBarStateController, ConfigurationController configurationController, SystemStatusAnimationScheduler systemStatusAnimationScheduler, PowerInteractor powerInteractor, WakefulnessLifecycle wakefulnessLifecycle, ShadeInteractor shadeInteractor, HomeStatusBarComponentsRepository homeStatusBarComponentsRepository, DelayableExecutor delayableExecutor) {
        this.scope = coroutineScope;
        this.mainExecutor = executor;
        this.context = context;
        this.windowManager = windowManager;
        this.stateController = statusBarStateController;
        this.configurationController = configurationController;
        this.animationScheduler = systemStatusAnimationScheduler;
        this.powerInteractor = powerInteractor;
        this.wakefulnessLifecycle = wakefulnessLifecycle;
        this.shadeInteractor = shadeInteractor;
        this.homeStatusBarComponentsRepository = homeStatusBarComponentsRepository;
        this.uiExecutor = delayableExecutor;
        ViewState viewState = new ViewState(false, 2131103174, false, true, true, false, 0, 0, 0, 0, -1, null, null, false, false);
        this.currentViewState = viewState;
        this.nextViewState = viewState.copy((24575 & 1) != 0 ? viewState.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState.persistentDotFillColorRes : 0, (24575 & 4) != 0 ? viewState.shadeExpanded : false, (24575 & 8) != 0 ? viewState.needShow : false, (24575 & 16) != 0 ? viewState.wakeAllowsPrivacyDot : false, (24575 & 32) != 0 ? viewState.rtl : false, (24575 & 64) != 0 ? viewState.rotation : 0, (24575 & 128) != 0 ? viewState.height : 0, (24575 & 256) != 0 ? viewState.x : 0, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState.y : 0, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState.cornerIndex : 0, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState.designatedCorner : null, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState.preDraw : false);
        this.lock = new Object();
        OplusPrivacyDotViewControllerImpl$mWakefulnessObserver$1 oplusPrivacyDotViewControllerImpl$mWakefulnessObserver$1 = new OplusPrivacyDotViewControllerImpl$mWakefulnessObserver$1();
        oplusPrivacyDotViewControllerImpl$mWakefulnessObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mWakefulnessObserver = oplusPrivacyDotViewControllerImpl$mWakefulnessObserver$1;
        OplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1 oplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1 = new OplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1();
        oplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.systemStatusAnimationCallback = oplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1;
    }

    public static final void access$addWakefulnessObserverIfNeeded(OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl) {
        if (oplusPrivacyDotViewControllerImpl.wakefulnessObserverAdded) {
            return;
        }
        oplusPrivacyDotViewControllerImpl.wakefulnessLifecycle.addObserver(oplusPrivacyDotViewControllerImpl.mWakefulnessObserver);
        oplusPrivacyDotViewControllerImpl.wakefulnessObserverAdded = true;
        boolean z = oplusPrivacyDotViewControllerImpl.wakefulnessLifecycle.getWakefulness() == 2;
        synchronized (oplusPrivacyDotViewControllerImpl.lock) {
            ViewState viewState = oplusPrivacyDotViewControllerImpl.nextViewState;
            oplusPrivacyDotViewControllerImpl.setNextViewState(viewState.copy((24575 & 1) != 0 ? viewState.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState.persistentDotFillColorRes : 0, (24575 & 4) != 0 ? viewState.shadeExpanded : false, (24575 & 8) != 0 ? viewState.needShow : false, (24575 & 16) != 0 ? viewState.wakeAllowsPrivacyDot : z, (24575 & 32) != 0 ? viewState.rtl : false, (24575 & 64) != 0 ? viewState.rotation : 0, (24575 & 128) != 0 ? viewState.height : 0, (24575 & 256) != 0 ? viewState.x : 0, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState.y : 0, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState.cornerIndex : 0, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState.designatedCorner : null, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState.preDraw : false));
        }
    }

    public static final void access$handleDensityOrFontScaleChanged(OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl) {
        oplusPrivacyDotViewControllerImpl.refreshDotMetricsFromResources();
        int privacyPaddingStart = oplusPrivacyDotViewControllerImpl.getPrivacyPaddingStart(oplusPrivacyDotViewControllerImpl.nextViewState.getRtl());
        int privacyPaddingTop = oplusPrivacyDotViewControllerImpl.getPrivacyPaddingTop();
        synchronized (oplusPrivacyDotViewControllerImpl.lock) {
            ViewState viewState = oplusPrivacyDotViewControllerImpl.nextViewState;
            oplusPrivacyDotViewControllerImpl.setNextViewState(viewState.copy((24575 & 1) != 0 ? viewState.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState.persistentDotFillColorRes : 0, (24575 & 4) != 0 ? viewState.shadeExpanded : false, (24575 & 8) != 0 ? viewState.needShow : false, (24575 & 16) != 0 ? viewState.wakeAllowsPrivacyDot : false, (24575 & 32) != 0 ? viewState.rtl : false, (24575 & 64) != 0 ? viewState.rotation : 0, (24575 & 128) != 0 ? viewState.height : oplusPrivacyDotViewControllerImpl.mDotSize, (24575 & 256) != 0 ? viewState.x : privacyPaddingStart, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState.y : privacyPaddingTop, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState.cornerIndex : 0, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState.designatedCorner : null, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState.preDraw : false));
        }
    }

    public static final void access$processNextViewState(OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl) {
        ViewState viewStateCopy;
        View designatedCorner;
        View viewFindViewById;
        OplusPrivacyDotViewControllerImplKt.access$dlog("processNextViewState: ");
        synchronized (oplusPrivacyDotViewControllerImpl.lock) {
            ViewState viewState = oplusPrivacyDotViewControllerImpl.nextViewState;
            viewStateCopy = viewState.copy((24575 & 1) != 0 ? viewState.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState.persistentDotFillColorRes : 0, (24575 & 4) != 0 ? viewState.shadeExpanded : false, (24575 & 8) != 0 ? viewState.needShow : false, (24575 & 16) != 0 ? viewState.wakeAllowsPrivacyDot : false, (24575 & 32) != 0 ? viewState.rtl : false, (24575 & 64) != 0 ? viewState.rotation : 0, (24575 & 128) != 0 ? viewState.height : 0, (24575 & 256) != 0 ? viewState.x : 0, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState.y : 0, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState.cornerIndex : 0, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState.designatedCorner : null, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState.preDraw : false);
        }
        OplusPrivacyDotViewControllerImplKt.access$dlog("resolveState " + viewStateCopy + " shouldShowDot " + viewStateCopy.shouldShowDot());
        OplusSysUiSchedMgr companion = OplusSysUiSchedMgr.Companion.getInstance();
        boolean zShouldShowDot = viewStateCopy.shouldShowDot();
        boolean z = companion.isShowingDot;
        int i = 0;
        if (zShouldShowDot) {
            if (z) {
                LogUtil.w$default(8, LogUtil.TAG_COMMON, "OplusSysUiSchedMgr", "schedForPrivacyIconShowing already showing, no need request");
            } else {
                Process.setThreadPriority(-10);
                companion.requestSysResource(OplusSysUiSchedMgr.UafSceneEvent.PRIVACY_UI_SHOWING);
                companion.isShowingDot = true;
            }
        } else if (z) {
            Process.setThreadPriority(0);
            companion.releaseSysResource(OplusSysUiSchedMgr.UafSceneEvent.PRIVACY_UI_SHOWING);
            companion.isShowingDot = false;
        } else {
            LogUtil.w$default(8, LogUtil.TAG_COMMON, "OplusSysUiSchedMgr", "schedForPrivacyIconShowing not showing, no need release");
        }
        if (viewStateCopy.equals(oplusPrivacyDotViewControllerImpl.currentViewState)) {
            OplusPrivacyDotViewControllerImplKt.access$dlog("resolveState: skipping");
            return;
        }
        if (viewStateCopy.getHeight() != oplusPrivacyDotViewControllerImpl.currentViewState.getHeight()) {
            OplusPrivacyDotViewControllerImplKt.access$dlog("resolveState: currentHeight=" + oplusPrivacyDotViewControllerImpl.currentViewState.getHeight() + " newHeight=" + viewStateCopy.getHeight());
            ImageView imageView = oplusPrivacyDotViewControllerImpl.tlDotView;
            if (imageView != null) {
                imageView.setImageResource(2131237387);
            }
            ImageView imageView2 = oplusPrivacyDotViewControllerImpl.trDotView;
            if (imageView2 != null) {
                imageView2.setImageResource(2131237387);
            }
        }
        if ((viewStateCopy.getX() != oplusPrivacyDotViewControllerImpl.currentViewState.getX() || viewStateCopy.getY() != oplusPrivacyDotViewControllerImpl.currentViewState.getY()) && viewStateCopy.shouldShowDot()) {
            oplusPrivacyDotViewControllerImpl.updatePosition(viewStateCopy.getX(), viewStateCopy.getY());
        }
        if (!Intrinsics.areEqual(viewStateCopy.getDesignatedCorner(), oplusPrivacyDotViewControllerImpl.currentViewState.getDesignatedCorner())) {
            View designatedCorner2 = oplusPrivacyDotViewControllerImpl.currentViewState.getDesignatedCorner();
            if (designatedCorner2 != null) {
                designatedCorner2.setContentDescription(null);
            }
            View designatedCorner3 = viewStateCopy.getDesignatedCorner();
            if (designatedCorner3 != null) {
                designatedCorner3.setContentDescription(viewStateCopy.getContentDescription());
            }
            View designatedCorner4 = viewStateCopy.getDesignatedCorner();
            boolean zShouldShowDot2 = viewStateCopy.shouldShowDot();
            OplusPrivacyDotViewControllerImplKt.access$dlog("updateDesignatedCorner: " + designatedCorner4 + " shouldShowDot:" + zShouldShowDot2);
            if (designatedCorner4 != null && zShouldShowDot2 && (viewFindViewById = designatedCorner4.findViewById(2131363412)) != null) {
                viewFindViewById.clearAnimation();
                viewFindViewById.setVisibility(0);
                viewFindViewById.setAlpha(0.0f);
                viewFindViewById.animate().alpha(1.0f).setDuration(300L).start();
            }
        } else if (!Intrinsics.areEqual(viewStateCopy.getContentDescription(), oplusPrivacyDotViewControllerImpl.currentViewState.getContentDescription()) && (designatedCorner = viewStateCopy.getDesignatedCorner()) != null) {
            designatedCorner.setContentDescription(viewStateCopy.getContentDescription());
        }
        if (viewStateCopy.needsLayout(oplusPrivacyDotViewControllerImpl.currentViewState) && viewStateCopy.shouldShowDot()) {
            Iterator it = oplusPrivacyDotViewControllerImpl.getViews().iterator();
            while (it.hasNext()) {
                ((View) it.next()).requestLayout();
            }
        }
        boolean zShouldShowDot3 = viewStateCopy.shouldShowDot();
        if (viewStateCopy.shouldShowDot() && viewStateCopy.getDesignatedCorner() != null && oplusPrivacyDotViewControllerImpl.tl != null) {
            View designatedCorner5 = viewStateCopy.getDesignatedCorner();
            ImageView imageView3 = (ImageView) designatedCorner5.findViewById(2131364661);
            if (imageView3 != null) {
                Drawable drawable = imageView3.getDrawable();
                Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
                GradientDrawable gradientDrawable = drawableMutate instanceof GradientDrawable ? (GradientDrawable) drawableMutate : null;
                if (gradientDrawable != null) {
                    int color = designatedCorner5.getContext().getColor(viewStateCopy.getPersistentDotFillColorRes());
                    ColorStateList color2 = gradientDrawable.getColor();
                    if (color2 == null || color2.getDefaultColor() != color) {
                        gradientDrawable.setColor(color);
                    }
                }
            }
        }
        if (zShouldShowDot3 == oplusPrivacyDotViewControllerImpl.currentViewState.shouldShowDot() && viewStateCopy.getRotation() == oplusPrivacyDotViewControllerImpl.currentViewState.getRotation()) {
            if (!viewStateCopy.getSystemPrivacyEventIsActive() && viewStateCopy.getDesignatedCorner() != null) {
                viewStateCopy.getDesignatedCorner().requireViewById(2131363412).setVisibility(8);
            }
        } else if (zShouldShowDot3 && viewStateCopy.getDesignatedCorner() != null) {
            Context context = viewStateCopy.getDesignatedCorner().getContext();
            if (oplusPrivacyDotViewControllerImpl.cachedIsMiddleOrHighAnimLevel == null) {
                oplusPrivacyDotViewControllerImpl.cachedIsMiddleOrHighAnimLevel = Boolean.valueOf(ScrimUtil.isMiddleOrHighAnimLevel(context));
            }
            boolean zAreEqual = Intrinsics.areEqual(oplusPrivacyDotViewControllerImpl.cachedIsMiddleOrHighAnimLevel, Boolean.TRUE);
            oplusPrivacyDotViewControllerImpl.updatePosition(viewStateCopy.getX(), viewStateCopy.getY());
            View viewRequireViewById = viewStateCopy.getDesignatedCorner().requireViewById(2131363412);
            OplusPrivacyDotViewControllerImplKt.access$dlog("showDotView:" + zAreEqual);
            viewRequireViewById.clearAnimation();
            if (zAreEqual) {
                viewRequireViewById.setVisibility(0);
                View viewFindViewById2 = viewRequireViewById.findViewById(2131364661);
                if (viewFindViewById2 != null) {
                    viewFindViewById2.setVisibility(0);
                }
                viewRequireViewById.setAlpha(0.0f);
                viewRequireViewById.animate().alpha(1.0f).setDuration(160L).setInterpolator(Interpolators.ALPHA_IN).start();
            } else {
                viewRequireViewById.setVisibility(0);
                View viewFindViewById3 = viewRequireViewById.findViewById(2131364661);
                if (viewFindViewById3 != null) {
                    viewFindViewById3.setVisibility(0);
                }
                viewRequireViewById.setAlpha(1.0f);
            }
        } else if (!zShouldShowDot3 && viewStateCopy.getDesignatedCorner() != null) {
            Context context2 = viewStateCopy.getDesignatedCorner().getContext();
            if (oplusPrivacyDotViewControllerImpl.cachedIsMiddleOrHighAnimLevel == null) {
                oplusPrivacyDotViewControllerImpl.cachedIsMiddleOrHighAnimLevel = Boolean.valueOf(ScrimUtil.isMiddleOrHighAnimLevel(context2));
            }
            boolean zAreEqual2 = Intrinsics.areEqual(oplusPrivacyDotViewControllerImpl.cachedIsMiddleOrHighAnimLevel, Boolean.TRUE);
            View viewRequireViewById2 = viewStateCopy.getDesignatedCorner().requireViewById(2131363412);
            boolean z2 = (viewStateCopy.preDraw || viewStateCopy.getShadeExpanded() || !zAreEqual2) ? false : true;
            OplusPrivacyDotViewControllerImplKt.access$dlog("hideDotView:" + z2);
            viewRequireViewById2.clearAnimation();
            if (z2) {
                ViewPropertyAnimator viewPropertyAnimatorAlpha = viewRequireViewById2.animate().setDuration(160L).setInterpolator(Interpolators.ALPHA_OUT).alpha(0.0f);
                OplusPrivacyDotViewControllerImpl$hideDotView$1 oplusPrivacyDotViewControllerImpl$hideDotView$1 = new OplusPrivacyDotViewControllerImpl$hideDotView$1(i);
                oplusPrivacyDotViewControllerImpl$hideDotView$1.$dot = viewRequireViewById2;
                VarHandle.storeStoreFence();
                viewPropertyAnimatorAlpha.withEndAction(oplusPrivacyDotViewControllerImpl$hideDotView$1).start();
            } else {
                View viewFindViewById4 = viewRequireViewById2.findViewById(2131364661);
                if (viewFindViewById4 != null) {
                    viewFindViewById4.setVisibility(4);
                }
            }
        }
        oplusPrivacyDotViewControllerImpl.currentViewState = viewStateCopy;
    }

    public static final void access$removeWakefulnessObserverIfNeeded(OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl) {
        if (oplusPrivacyDotViewControllerImpl.wakefulnessObserverAdded) {
            oplusPrivacyDotViewControllerImpl.wakefulnessLifecycle.removeObserver(oplusPrivacyDotViewControllerImpl.mWakefulnessObserver);
            oplusPrivacyDotViewControllerImpl.wakefulnessObserverAdded = false;
        }
    }

    public static final void access$tryApplyDisplayConfigIfStable(OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl) {
        if (oplusPrivacyDotViewControllerImpl.displayConfigPending) {
            if (oplusPrivacyDotViewControllerImpl.pendingTargetRotation >= 0 && oplusPrivacyDotViewControllerImpl.pendingTargetWidth >= 0 && oplusPrivacyDotViewControllerImpl.pendingTargetHeight >= 0) {
                DisplayInfo displayInfo = new DisplayInfo();
                Display display = oplusPrivacyDotViewControllerImpl.context.getDisplay();
                if (display != null) {
                    display.getDisplayInfo(displayInfo);
                    if (displayInfo.rotation == oplusPrivacyDotViewControllerImpl.pendingTargetRotation && displayInfo.getNaturalWidth() == oplusPrivacyDotViewControllerImpl.pendingTargetWidth && displayInfo.getNaturalHeight() == oplusPrivacyDotViewControllerImpl.pendingTargetHeight) {
                        oplusPrivacyDotViewControllerImpl.applyDisplayConfig("config_changed");
                        return;
                    }
                }
            }
            int i = oplusPrivacyDotViewControllerImpl.pendingTargetRotation;
            int i2 = oplusPrivacyDotViewControllerImpl.pendingTargetWidth;
            int i3 = oplusPrivacyDotViewControllerImpl.pendingTargetHeight;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("tryApplyDisplayConfigIfStable skip reason=config_changed targetRot=", i, i2, " targetSize=", "x");
            sbM.append(i3);
            OplusPrivacyDotViewControllerImplKt.access$dlog(sbM.toString());
        }
    }

    public static final void access$updatePrivacyDotLayoutState(OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl, PrivacyDotLayoutState privacyDotLayoutState) {
        if (Intrinsics.areEqual(privacyDotLayoutState, oplusPrivacyDotViewControllerImpl.lastAppliedPrivacyDotLayoutState)) {
            return;
        }
        PrivacyDotLayoutState privacyDotLayoutState2 = oplusPrivacyDotViewControllerImpl.lastAppliedPrivacyDotLayoutState;
        PrivacyDotLayoutState.Companion companion = PrivacyDotLayoutState.Companion;
        if (Intrinsics.areEqual(privacyDotLayoutState, companion.getEMPTY()) && privacyDotLayoutState2 != null && !privacyDotLayoutState2.equals(companion.getEMPTY())) {
            OplusPrivacyDotViewControllerImplKt.access$dlog("skip EMPTY PrivacyDotLayoutState during transition");
            return;
        }
        oplusPrivacyDotViewControllerImpl.lastAppliedPrivacyDotLayoutState = privacyDotLayoutState;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusPrivacyDotViewController-->".concat("applyPrivacyDotLayoutState: " + privacyDotLayoutState));
        }
        int privacyPaddingStart = oplusPrivacyDotViewControllerImpl.getPrivacyPaddingStart(oplusPrivacyDotViewControllerImpl.nextViewState.getRtl());
        int privacyPaddingTop = oplusPrivacyDotViewControllerImpl.getPrivacyPaddingTop();
        synchronized (oplusPrivacyDotViewControllerImpl.lock) {
            ViewState viewState = oplusPrivacyDotViewControllerImpl.nextViewState;
            oplusPrivacyDotViewControllerImpl.setNextViewState(viewState.copy((24575 & 1) != 0 ? viewState.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState.persistentDotFillColorRes : 0, (24575 & 4) != 0 ? viewState.shadeExpanded : false, (24575 & 8) != 0 ? viewState.needShow : false, (24575 & 16) != 0 ? viewState.wakeAllowsPrivacyDot : false, (24575 & 32) != 0 ? viewState.rtl : false, (24575 & 64) != 0 ? viewState.rotation : 0, (24575 & 128) != 0 ? viewState.height : 0, (24575 & 256) != 0 ? viewState.x : privacyPaddingStart, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState.y : privacyPaddingTop, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState.cornerIndex : 0, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState.designatedCorner : null, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState.preDraw : false));
        }
    }

    public static final void access$updateStatusBarState(OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl) {
        synchronized (oplusPrivacyDotViewControllerImpl.lock) {
            ViewState viewState = oplusPrivacyDotViewControllerImpl.nextViewState;
            oplusPrivacyDotViewControllerImpl.setNextViewState(viewState.copy((24575 & 1) != 0 ? viewState.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState.persistentDotFillColorRes : 0, (24575 & 4) != 0 ? viewState.shadeExpanded : (oplusPrivacyDotViewControllerImpl.stateController.isExpanded() && oplusPrivacyDotViewControllerImpl.stateController.getState() == 0) || oplusPrivacyDotViewControllerImpl.stateController.getState() == 2, (24575 & 8) != 0 ? viewState.needShow : false, (24575 & 16) != 0 ? viewState.wakeAllowsPrivacyDot : false, (24575 & 32) != 0 ? viewState.rtl : false, (24575 & 64) != 0 ? viewState.rotation : 0, (24575 & 128) != 0 ? viewState.height : 0, (24575 & 256) != 0 ? viewState.x : 0, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState.y : 0, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState.cornerIndex : 0, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState.designatedCorner : null, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState.preDraw : false));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Flow getDefaultPrivacyDotLayoutStateFlow() {
        StateFlow componentsByDisplayId = this.homeStatusBarComponentsRepository.getComponentsByDisplayId();
        OplusPrivacyDotViewControllerImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$map$1 oplusPrivacyDotViewControllerImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$map$1 = new OplusPrivacyDotViewControllerImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$map$1();
        oplusPrivacyDotViewControllerImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$map$1.$this_unsafeTransform$inlined = componentsByDisplayId;
        VarHandle.storeStoreFence();
        return FlowKt.transformLatest(FlowKt.distinctUntilChanged(oplusPrivacyDotViewControllerImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$map$1), new OplusPrivacyDotViewControllerImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$flatMapLatest$1(3, null));
    }

    private final int getPrivacyPaddingTop() {
        PrivacyDotLayoutState privacyDotLayoutState = this.lastAppliedPrivacyDotLayoutState;
        if (privacyDotLayoutState == null) {
            return 0;
        }
        return ListPopupWindow$.ExternalSyntheticOutline0.m(privacyDotLayoutState.getStatusBarContentsHeight(), this.mDotSize, 2, privacyDotLayoutState.getStatusBarPaddingTop());
    }

    private final Sequence getViews() {
        ViewGroup viewGroup = this.tl;
        if (viewGroup == null) {
            return SequencesKt__SequencesKt.emptySequence();
        }
        ViewGroup[] viewGroupArr = new ViewGroup[2];
        if (viewGroup == null) {
            viewGroup = null;
        }
        viewGroupArr[0] = viewGroup;
        ViewGroup viewGroup2 = this.tr;
        viewGroupArr[1] = viewGroup2 != null ? viewGroup2 : null;
        return SequencesKt__SequencesKt.sequenceOf(viewGroupArr);
    }

    public static WindowManager.LayoutParams getWindowLayoutParams(int i) {
        String str;
        int i2;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2026, 545259816, -3);
        layoutParams.privateFlags |= 80;
        Log.i("OplusPrivacyDotViewController", "windowLayoutParams without rounded corner");
        if (i == 0) {
            str = "OplusPrivacyIconTopLeft";
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m(ListPopupWindow$.ExternalSyntheticOutline0.m(i, "unknown bound position: "));
                return null;
            }
            str = "OplusPrivacyIconTopRight";
        }
        layoutParams.setTitle(str);
        if (i == 0) {
            i2 = 51;
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m(ListPopupWindow$.ExternalSyntheticOutline0.m(i, "unknown bound position: "));
                return null;
            }
            i2 = 53;
        }
        layoutParams.gravity = i2;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.setFitInsetsTypes(0);
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCornerVisibilities(int i) {
        Iterator it = getViews().iterator();
        while (it.hasNext()) {
            View viewFindViewById = ((View) it.next()).findViewById(2131363412);
            if (viewFindViewById == null) {
                viewFindViewById = null;
            }
            if (viewFindViewById != null) {
                viewFindViewById.setVisibility(i);
            }
        }
    }

    private final void setNewRotation(int i) {
        synchronized (this.lock) {
            if (i == this.nextViewState.getRotation()) {
                return;
            }
            boolean rtl = this.nextViewState.getRtl();
            setCornerVisibilities(8);
            View viewSelectDesignatedCorner = selectDesignatedCorner(i, rtl);
            int iCornerIndex = cornerIndex(viewSelectDesignatedCorner);
            int i2 = this.mDotSize;
            int privacyPaddingStart = getPrivacyPaddingStart(rtl);
            synchronized (this.lock) {
                ViewState viewState = this.nextViewState;
                setNextViewState(viewState.copy((24575 & 1) != 0 ? viewState.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState.persistentDotFillColorRes : 0, (24575 & 4) != 0 ? viewState.shadeExpanded : false, (24575 & 8) != 0 ? viewState.needShow : false, (24575 & 16) != 0 ? viewState.wakeAllowsPrivacyDot : false, (24575 & 32) != 0 ? viewState.rtl : rtl, (24575 & 64) != 0 ? viewState.rotation : i, (24575 & 128) != 0 ? viewState.height : i2, (24575 & 256) != 0 ? viewState.x : privacyPaddingStart, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState.y : 0, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState.cornerIndex : iCornerIndex, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState.designatedCorner : viewSelectDesignatedCorner, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState.preDraw : false));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setNextViewState(ViewState viewState) {
        this.nextViewState = viewState;
        OplusPrivacyDotViewControllerImplKt.access$dlog("scheduleUpdate: ");
        if (this.tl == null || this.tr == null) {
            return;
        }
        Runnable runnable = this.cancelRunnable;
        if (runnable != null) {
            runnable.run();
        }
        DelayableExecutor delayableExecutor = this.uiExecutor;
        OplusPrivacyDotViewControllerImpl$hideDotView$1 oplusPrivacyDotViewControllerImpl$hideDotView$1 = new OplusPrivacyDotViewControllerImpl$hideDotView$1(3);
        oplusPrivacyDotViewControllerImpl$hideDotView$1.$dot = this;
        VarHandle.storeStoreFence();
        this.cancelRunnable = delayableExecutor.executeDelayed(oplusPrivacyDotViewControllerImpl$hideDotView$1, 0L);
    }

    private final void setPendingConfigChange(boolean z) {
        OplusPrivacyDotViewControllerImplKt.access$dlog("setPendingConfigChange: " + z);
        synchronized (this.lock) {
            ViewState viewState = this.nextViewState;
            setNextViewState(viewState.copy((24575 & 1) != 0 ? viewState.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState.persistentDotFillColorRes : 0, (24575 & 4) != 0 ? viewState.shadeExpanded : false, (24575 & 8) != 0 ? viewState.needShow : false, (24575 & 16) != 0 ? viewState.wakeAllowsPrivacyDot : false, (24575 & 32) != 0 ? viewState.rtl : false, (24575 & 64) != 0 ? viewState.rotation : 0, (24575 & 128) != 0 ? viewState.height : 0, (24575 & 256) != 0 ? viewState.x : 0, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState.y : 0, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState.cornerIndex : 0, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState.designatedCorner : null, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState.preDraw : z));
        }
    }

    public final void applyDisplayConfig(String str) {
        boolean rtl;
        if (this.displayConfigPending) {
            this.displayConfigPending = false;
            Runnable runnable = this.pendingTimeoutCancel;
            if (runnable != null) {
                runnable.run();
            }
            this.pendingTimeoutCancel = null;
            int i = this.pendingTargetRotation;
            int i2 = this.pendingTargetWidth;
            int i3 = this.pendingTargetHeight;
            String str2 = this.lastDisplayUniqueId;
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "applyDisplayConfig reason=", str, " rot=", " size=");
            RectList$.ExternalSyntheticOutline0.m(i2, i3, "x", " uniqueId=", sbM);
            sbM.append(str2);
            OplusPrivacyDotViewControllerImplKt.access$dlog(sbM.toString());
            this.pendingTargetRotation = -1;
            this.pendingTargetWidth = -1;
            this.pendingTargetHeight = -1;
            refreshDotMetricsFromResources();
            synchronized (this.lock) {
                rtl = this.nextViewState.getRtl();
            }
            int privacyPaddingStart = getPrivacyPaddingStart(rtl);
            int privacyPaddingTop = getPrivacyPaddingTop();
            synchronized (this.lock) {
                ViewState viewState = this.nextViewState;
                setNextViewState(viewState.copy((24575 & 1) != 0 ? viewState.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState.persistentDotFillColorRes : 0, (24575 & 4) != 0 ? viewState.shadeExpanded : false, (24575 & 8) != 0 ? viewState.needShow : false, (24575 & 16) != 0 ? viewState.wakeAllowsPrivacyDot : false, (24575 & 32) != 0 ? viewState.rtl : false, (24575 & 64) != 0 ? viewState.rotation : 0, (24575 & 128) != 0 ? viewState.height : this.mDotSize, (24575 & 256) != 0 ? viewState.x : privacyPaddingStart, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState.y : privacyPaddingTop, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState.cornerIndex : 0, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState.designatedCorner : null, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState.preDraw : false));
            }
        }
    }

    public final int cornerIndex(View view) {
        if (view == null) {
            return -1;
        }
        ViewGroup viewGroup = this.tl;
        if (viewGroup == null) {
            viewGroup = null;
        }
        if (Intrinsics.areEqual(view, viewGroup)) {
            return 0;
        }
        ViewGroup viewGroup2 = this.tr;
        if (Intrinsics.areEqual(view, viewGroup2 != null ? viewGroup2 : null)) {
            return 1;
        }
        Path$.ExternalSyntheticBUOutline0.m("not a corner view");
        return 0;
    }

    public final ViewGroup createDotView(int i, Context context) {
        Log.i("OplusPrivacyDotViewController", "createDotView:" + i);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(2131559495, (ViewGroup) null);
        viewGroup.setSystemUiVisibility(256);
        viewGroup.setForceDarkAllowed(false);
        try {
            this.windowManager.addView(viewGroup, getWindowLayoutParams(i));
            return viewGroup;
        } catch (RuntimeException e) {
            Log.e("OplusPrivacyDotViewController", "createDotView e :" + e);
            return viewGroup;
        }
    }

    public final int getPrivacyPaddingStart(boolean z) {
        PrivacyDotLayoutState privacyDotLayoutState = this.lastAppliedPrivacyDotLayoutState;
        if (privacyDotLayoutState == null) {
            return 0;
        }
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(2131171476);
        if (z) {
            return (((privacyDotLayoutState.getStatusBarPaddingLeft() + privacyDotLayoutState.getLeftInset()) - dimensionPixelSize) - this.mDotSize) + ((int) privacyDotLayoutState.getBurnInTranslationX());
        }
        return (((privacyDotLayoutState.getStatusBarPaddingRight() + privacyDotLayoutState.getRightInset()) - dimensionPixelSize) - this.mDotSize) - ((int) privacyDotLayoutState.getBurnInTranslationX());
    }

    public final void init() {
        ConfigurationController configurationController = this.configurationController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        configurationController.addCallback(anonymousClass1);
        StatusBarStateController statusBarStateController = this.stateController;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        statusBarStateController.addCallback(anonymousClass2);
        BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(null), 3);
        BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(null), 3);
        if (this.tl == null || this.tr == null) {
            Log.i("OplusPrivacyDotViewController", "initialize");
            int i = 1;
            OplusPrivacyDotViewControllerImplKt.DEBUG = true;
            this.tl = createDotView(0, this.context);
            this.tr = createDotView(1, this.context);
            ViewGroup viewGroup = this.tl;
            if (viewGroup == null) {
                viewGroup = null;
            }
            this.tlDotView = (ImageView) viewGroup.findViewById(2131364661);
            ViewGroup viewGroup2 = this.tr;
            this.trDotView = (ImageView) (viewGroup2 != null ? viewGroup2 : null).findViewById(2131364661);
            refreshDotMetricsFromResources();
            DisplayInfo displayInfo = new DisplayInfo();
            Display display = this.context.getDisplay();
            if (display != null) {
                display.getDisplayInfo(displayInfo);
                this.lastDisplayRotation = displayInfo.rotation;
                this.lastDisplayWidth = displayInfo.getNaturalWidth();
                this.lastDisplayHeight = displayInfo.getNaturalHeight();
                this.lastDisplayUniqueId = displayInfo.uniqueId;
            }
            boolean zIsLayoutRtl = this.configurationController.isLayoutRtl();
            int i2 = this.lastDisplayRotation;
            int i3 = i2 >= 0 ? i2 : 0;
            View viewSelectDesignatedCorner = selectDesignatedCorner(i3, zIsLayoutRtl);
            int iCornerIndex = cornerIndex(viewSelectDesignatedCorner);
            Executor executor = this.mainExecutor;
            OplusPrivacyDotViewControllerImpl$hideDotView$1 oplusPrivacyDotViewControllerImpl$hideDotView$1 = new OplusPrivacyDotViewControllerImpl$hideDotView$1(i);
            oplusPrivacyDotViewControllerImpl$hideDotView$1.$dot = this;
            VarHandle.storeStoreFence();
            executor.execute(oplusPrivacyDotViewControllerImpl$hideDotView$1);
            setCornerVisibilities(8);
            synchronized (this.lock) {
                ViewState viewState = this.nextViewState;
                setNextViewState(viewState.copy((24575 & 1) != 0 ? viewState.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState.persistentDotFillColorRes : 0, (24575 & 4) != 0 ? viewState.shadeExpanded : false, (24575 & 8) != 0 ? viewState.needShow : false, (24575 & 16) != 0 ? viewState.wakeAllowsPrivacyDot : false, (24575 & 32) != 0 ? viewState.rtl : zIsLayoutRtl, (24575 & 64) != 0 ? viewState.rotation : i3, (24575 & 128) != 0 ? viewState.height : this.mDotSize, (24575 & 256) != 0 ? viewState.x : 0, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState.y : 0, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState.cornerIndex : iCornerIndex, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState.designatedCorner : viewSelectDesignatedCorner, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState.preDraw : false));
            }
        }
    }

    public void onDisplayInfoChanged(int i, int i2, int i3, String str) {
        if (this.tl == null || this.tr == null) {
            return;
        }
        boolean z = i != this.lastDisplayRotation;
        boolean z2 = (i2 == this.lastDisplayWidth && i3 == this.lastDisplayHeight) ? false : true;
        boolean zAreEqual = Intrinsics.areEqual(str, this.lastDisplayUniqueId);
        boolean z3 = !zAreEqual;
        if (z || z2 || !zAreEqual) {
            int i4 = this.lastDisplayRotation;
            int i5 = this.lastDisplayWidth;
            int i6 = this.lastDisplayHeight;
            String str2 = this.lastDisplayUniqueId;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("onDisplayInfoChanged pending rot=", i, i2, " size=", "x");
            BluetoothUtils$.ExternalSyntheticOutline0.m(i3, " uniqueId=", str, " lastRot=", sbM);
            RectList$.ExternalSyntheticOutline0.m(i4, i5, " lastSize=", "x", sbM);
            BluetoothUtils$.ExternalSyntheticOutline0.m(i6, " lastUniqueId=", str2, " rotationChanged=", sbM);
            KeyguardLogger$.ExternalSyntheticOutline0.m(" sizeChanged=", " uniqueIdChanged=", sbM, z, z2);
            sbM.append(z3);
            OplusPrivacyDotViewControllerImplKt.access$dlog(sbM.toString());
            this.pendingTargetRotation = i;
            this.pendingTargetWidth = i2;
            this.pendingTargetHeight = i3;
            this.displayConfigPending = true;
            setPendingConfigChange(true);
            Runnable runnable = this.pendingTimeoutCancel;
            if (runnable != null) {
                runnable.run();
            }
            DelayableExecutor delayableExecutor = this.uiExecutor;
            OplusPrivacyDotViewControllerImpl$hideDotView$1 oplusPrivacyDotViewControllerImpl$hideDotView$1 = new OplusPrivacyDotViewControllerImpl$hideDotView$1(2);
            oplusPrivacyDotViewControllerImpl$hideDotView$1.$dot = this;
            VarHandle.storeStoreFence();
            this.pendingTimeoutCancel = delayableExecutor.executeDelayed(oplusPrivacyDotViewControllerImpl$hideDotView$1, 800L);
            if (z) {
                setNewRotation(i);
            }
            this.lastDisplayRotation = i;
            this.lastDisplayWidth = i2;
            this.lastDisplayHeight = i3;
            if (zAreEqual) {
                return;
            }
            this.lastDisplayUniqueId = str;
        }
    }

    public final void refreshDotMetricsFromResources() {
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(2131171477);
        int i = this.mDotSize;
        if (dimensionPixelSize != i) {
            OplusPrivacyDotViewControllerImplKt.access$dlog("refreshDotMetricsFromResources mDotSize " + i + " -> " + dimensionPixelSize);
            this.mDotSize = dimensionPixelSize;
        }
    }

    public final void runOnSdThread(Function0 function0) {
        ExecutorImpl executorImpl = this.uiExecutor;
        OplusPrivacyDotViewControllerImplKt$sam$java_lang_Runnable$0 oplusPrivacyDotViewControllerImplKt$sam$java_lang_Runnable$0 = new OplusPrivacyDotViewControllerImplKt$sam$java_lang_Runnable$0();
        oplusPrivacyDotViewControllerImplKt$sam$java_lang_Runnable$0.function = function0;
        VarHandle.storeStoreFence();
        executorImpl.execute(oplusPrivacyDotViewControllerImplKt$sam$java_lang_Runnable$0);
    }

    public final View selectDesignatedCorner(int i, boolean z) {
        if (this.tl == null) {
            return null;
        }
        OplusPrivacyDotViewControllerImplKt.access$dlog("selectDesignatedCorner: " + i + " isRtl:" + z);
        if (z) {
            ViewGroup viewGroup = this.tl;
            if (viewGroup == null) {
                return null;
            }
            return viewGroup;
        }
        ViewGroup viewGroup2 = this.tr;
        if (viewGroup2 == null) {
            return null;
        }
        return viewGroup2;
    }

    public final void setChipAnimation(boolean z) {
        OplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0 oplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0 = new OplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0(0);
        oplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0.f$0 = this;
        oplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0.f$1 = z;
        VarHandle.storeStoreFence();
        runOnSdThread(oplusPrivacyDotViewControllerImpl$$ExternalSyntheticLambda0);
    }

    public final void updatePosition(int i, int i2) {
        for (View view : getViews()) {
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.x = i;
            }
            if (layoutParams != null) {
                layoutParams.y = i2;
            }
            OplusPrivacyDotViewControllerImplKt.access$dlog("updatePosition:" + i + ":" + i2);
            try {
                this.windowManager.updateViewLayout(view, layoutParams);
            } catch (Exception e) {
                PeopleBackupFollowUpJob$.ExternalSyntheticOutline0.m("updatePosition e :", "OplusPrivacyDotViewController", e);
            }
        }
    }
}
