package com.oplus.systemui.notification.lockscreen;

import android.content.Context;
import android.util.Log;
import androidx.compose.animation.core.MonoSpline$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.data.repository.ShadeRepository;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.keyguard.gradientmask.OplusKeyguardGradientMaskController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveViewManager;
import com.oplus.systemui.statusbar.phone.ScrimBlurStateUtilsExImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationCapsuleInteractorImpl implements OplusNotificationCapsuleInteractor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationCapsuleInteractor";
    private final Lazy _capsulePositionFlow$delegate;
    private final dagger.Lazy capsuleCardControl;
    private final dagger.Lazy capsuleContainerController;
    private final Context context;
    private final dagger.Lazy immersiveViewManager;
    private final KeyguardInteractor keyguardInteractor;
    private final dagger.Lazy keyguardStateController;
    public boolean lastAllowToShow;
    private final dagger.Lazy lockScreenExtStateController;
    private final dagger.Lazy lockscreenShadeTransitionControllerEx;
    private final CoroutineScope mainScope;
    private final PowerInteractor powerInteractor;
    private final dagger.Lazy scrimBlurStateUtils;
    private final ShadeInteractor shadeInteractor;
    private final ShadeRepository shadeRepository;
    private final SysuiStatusBarStateController statusBarStateController;
    private OplusNotificationCapsuleInteractor.UpEventListener upEventListener;
    private final MutableStateFlow forceHideNotificationCapsule = StateFlowKt.MutableStateFlow(Boolean.FALSE);
    private final MutableStateFlow preState = StateFlowKt.MutableStateFlow(0);
    public float logScale = -1.0f;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractorImpl$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractorImpl$start$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C00201 implements FlowCollector {
            public /* synthetic */ OplusNotificationCapsuleInteractorImpl this$0;

            public final Object emit(Object obj, Continuation continuation) {
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                OplusNotificationCapsuleInteractorImpl oplusNotificationCapsuleInteractorImpl = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("updateKeyguardNotificationCapsuleView: isAllowToShow=", "OplusNotificationCapsuleInteractor-->", "SystemUi--Notification", zBooleanValue);
                }
                oplusNotificationCapsuleInteractorImpl.lastAllowToShow = zBooleanValue;
                ((CapsuleCardControl) oplusNotificationCapsuleInteractorImpl.capsuleCardControl.get()).setAllowToShowNotificationCapsule(zBooleanValue);
                ((OplusImmersiveViewManager) oplusNotificationCapsuleInteractorImpl.immersiveViewManager.get()).setAllowToShowImmersiveView(zBooleanValue);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusNotificationCapsuleInteractorImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                OplusNotificationCapsuleInteractorImpl oplusNotificationCapsuleInteractorImpl = OplusNotificationCapsuleInteractorImpl.this;
                oplusNotificationCapsuleInteractorImpl.updatePosition(oplusNotificationCapsuleInteractorImpl.context.getResources().getDimensionPixelSize(2131165924));
                Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(OplusNotificationCapsuleInteractorImpl.access$keyguardNotificationCapsule(OplusNotificationCapsuleInteractorImpl.this));
                OplusNotificationCapsuleInteractorImpl oplusNotificationCapsuleInteractorImpl2 = OplusNotificationCapsuleInteractorImpl.this;
                C00201 c00201 = new C00201();
                c00201.this$0 = oplusNotificationCapsuleInteractorImpl2;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (flowDistinctUntilChanged.collect(c00201, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractorImpl$start$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements StatusBarStateController.StateListener {
        public /* synthetic */ OplusNotificationCapsuleInteractorImpl this$0;

        public final void onStatePreChange(int i, int i2) {
            this.this$0.preState.setValue(Integer.valueOf(i2));
        }
    }

    public OplusNotificationCapsuleInteractorImpl(CoroutineScope coroutineScope, dagger.Lazy lazy, dagger.Lazy lazy2, dagger.Lazy lazy3, Context context, PowerInteractor powerInteractor, KeyguardInteractor keyguardInteractor, ShadeInteractor shadeInteractor, ShadeRepository shadeRepository, dagger.Lazy lazy4, dagger.Lazy lazy5, dagger.Lazy lazy6, SysuiStatusBarStateController sysuiStatusBarStateController, dagger.Lazy lazy7) {
        this.mainScope = coroutineScope;
        this.immersiveViewManager = lazy;
        this.capsuleCardControl = lazy2;
        this.capsuleContainerController = lazy3;
        this.context = context;
        this.powerInteractor = powerInteractor;
        this.keyguardInteractor = keyguardInteractor;
        this.shadeInteractor = shadeInteractor;
        this.shadeRepository = shadeRepository;
        this.lockscreenShadeTransitionControllerEx = lazy4;
        this.lockScreenExtStateController = lazy5;
        this.scrimBlurStateUtils = lazy6;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.keyguardStateController = lazy7;
        OplusNotificationCapsuleInteractorImpl$$ExternalSyntheticLambda0 oplusNotificationCapsuleInteractorImpl$$ExternalSyntheticLambda0 = new OplusNotificationCapsuleInteractorImpl$$ExternalSyntheticLambda0();
        oplusNotificationCapsuleInteractorImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this._capsulePositionFlow$delegate = LazyKt__LazyJVMKt.lazy(oplusNotificationCapsuleInteractorImpl$$ExternalSyntheticLambda0);
    }

    public static final Flow access$keyguardNotificationCapsule(OplusNotificationCapsuleInteractorImpl oplusNotificationCapsuleInteractorImpl) {
        StateFlow detailedWakefulness = oplusNotificationCapsuleInteractorImpl.powerInteractor.getDetailedWakefulness();
        StateFlow stateFlowIsKeyguardShowing = oplusNotificationCapsuleInteractorImpl.keyguardInteractor.isKeyguardShowing();
        StateFlow anyExpansion = oplusNotificationCapsuleInteractorImpl.shadeInteractor.getAnyExpansion();
        OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$$inlined$map$1 oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$$inlined$map$1 = new OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$$inlined$map$1(0);
        oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$$inlined$map$1.$this_unsafeTransform$inlined = anyExpansion;
        VarHandle.storeStoreFence();
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$$inlined$map$1);
        StateFlow statusBarState = oplusNotificationCapsuleInteractorImpl.keyguardInteractor.getStatusBarState();
        StateFlow lockscreenAnimFraction = ((OplusLockscreenShadeTransitionControllerEx) oplusNotificationCapsuleInteractorImpl.lockscreenShadeTransitionControllerEx.get()).getLockscreenAnimFraction();
        OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$$inlined$map$1 oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$$inlined$map$2 = new OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$$inlined$map$1(1);
        oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$$inlined$map$2.$this_unsafeTransform$inlined = lockscreenAnimFraction;
        VarHandle.storeStoreFence();
        return FlowKt.combine(detailedWakefulness, stateFlowIsKeyguardShowing, flowDistinctUntilChanged, FlowKt.combine(statusBarState, FlowKt.distinctUntilChanged(oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$$inlined$map$2), new OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$3(3, null)), FlowKt.combine(oplusNotificationCapsuleInteractorImpl.forceHideNotificationCapsule, oplusNotificationCapsuleInteractorImpl.preState, new OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$4(3, null)), new OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$5(oplusNotificationCapsuleInteractorImpl, null));
    }

    private final MutableStateFlow get_capsulePositionFlow() {
        return (MutableStateFlow) this._capsulePositionFlow$delegate.getValue();
    }

    public final OplusNotificationCapsuleInteractor.CapsulePosition createInitCapsulePosition() {
        return new OplusNotificationCapsuleInteractor.CapsulePosition(((CapsuleCardControl) this.capsuleCardControl.get()).getCapsuleTop(), CapsuleConfig.INSTANCE.getItemHeight(), (int) ((CapsuleCardControl) this.capsuleCardControl.get()).getStackY());
    }

    @Override // com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractor
    public StateFlow getCapsulePositionFlow() {
        return get_capsulePositionFlow();
    }

    public final void notifyImmersiveClockBottomChanged(int i) {
        ((OplusImmersiveViewManager) this.immersiveViewManager.get()).notifyImmersiveClockBottomChanged(i);
    }

    public final void onCapsulePositionBottomChange() {
        get_capsulePositionFlow().setValue(OplusNotificationCapsuleInteractor.CapsulePosition.copy$default((OplusNotificationCapsuleInteractor.CapsulePosition) get_capsulePositionFlow().getValue(), ((CapsuleCardControl) this.capsuleCardControl.get()).getCapsuleTop(), CapsuleConfig.INSTANCE.getItemHeight(), 0, 4));
    }

    public final void onCapsulePositionChange() {
        get_capsulePositionFlow().setValue(createInitCapsulePosition());
    }

    public final void onCapsulePositionTranslationYChange(float f) {
        OplusNotificationCapsuleInteractor.CapsulePosition capsulePosition = (OplusNotificationCapsuleInteractor.CapsulePosition) get_capsulePositionFlow().getValue();
        get_capsulePositionFlow().setValue(OplusNotificationCapsuleInteractor.CapsulePosition.copy$default(capsulePosition, 0, CapsuleConfig.INSTANCE.getItemHeight(), (int) f, 1));
    }

    public final void onNsslVisibilityChanged(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onNsslVisableChanged:", "OplusNotificationCapsuleInteractor-->", "SystemUi--Notification", z);
        }
        boolean z2 = false;
        ((CapsuleCardControl) this.capsuleCardControl.get()).setAllowToShowNotificationCapsule(this.lastAllowToShow && z);
        OplusImmersiveViewManager oplusImmersiveViewManager = (OplusImmersiveViewManager) this.immersiveViewManager.get();
        if (this.lastAllowToShow && z) {
            z2 = true;
        }
        oplusImmersiveViewManager.setAllowToShowImmersiveView(z2);
    }

    public final void onUpEvent(String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("onUpEvent:: type = ", str, ",hasListener=", this.upEventListener != null, "OplusNotificationCapsuleInteractor-->"));
        }
        OplusKeyguardGradientMaskController.notificationTouchUpEventFlow.1.listener.1 r4 = this.upEventListener;
        if (r4 != null) {
            r4.onUpEvent(str);
        }
    }

    @Override // com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractor
    public void setForceHideNotificationCapsule(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("isForceHide = ", "OplusNotificationCapsuleInteractor-->", "SystemUi--Notification", z);
        }
        this.forceHideNotificationCapsule.setValue(Boolean.valueOf(z));
    }

    @Override // com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractor
    public void setUpEventListener(OplusNotificationCapsuleInteractor.UpEventListener upEventListener) {
        this.upEventListener = upEventListener;
    }

    public final void start() {
        BuildersKt.launch$default(this.mainScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        SysuiStatusBarStateController sysuiStatusBarStateController = this.statusBarStateController;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        sysuiStatusBarStateController.addCallback(anonymousClass2);
    }

    public final void updateKeyguardCapsuleViewAlpha(float f) {
        if ((f == 0.0f || f == 1.0f) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusNotificationCapsuleInteractor-->".concat("updateKeyguardCapsuleViewAlpha:: alpha = " + f + " scrimBlurStateUtils.get().currentState = " + ((ScrimBlurStateUtilsExImpl) this.scrimBlurStateUtils.get()).getCurrentState()));
        }
        ((OplusImmersiveViewManager) this.immersiveViewManager.get()).setKeyguardViewAlpha(f);
        if (!((KeyguardStateController) this.keyguardStateController.get()).mPrimaryBouncerShowing) {
            ((OplusImmersiveViewManager) this.immersiveViewManager.get()).updateKeyguardViewAlpha(f);
        }
        ((NotificationCapsuleContainerControllerImpl) this.capsuleContainerController.get()).updateKeyguardAlpha(f);
    }

    public final void updatePosition(int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "updatePosition:: bottomMargin = ", "OplusNotificationCapsuleInteractor-->", "SystemUi--Notification");
        }
        ((NotificationCapsuleContainerControllerImpl) this.capsuleContainerController.get()).updatePosition(i);
    }

    public final void updateScaleWithPivot(float f, float f2, float f3) {
        if (this.logScale != f) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m("updateScaleWithPivot:: scale = ", f, ", targetPivotX = ", f2, ", targetPivotY = "), f3, "OplusNotificationCapsuleInteractor-->", "SystemUi--Notification");
            }
            this.logScale = f;
        }
        ((OplusImmersiveViewManager) this.immersiveViewManager.get()).updateScaleWithPivot(f, f2, f3);
        ((NotificationCapsuleContainerControllerImpl) this.capsuleContainerController.get()).updateScaleWithPivot(f, f2, f3);
    }
}
