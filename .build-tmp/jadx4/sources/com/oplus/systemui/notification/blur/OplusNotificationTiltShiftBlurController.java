package com.oplus.systemui.notification.blur;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.collection.FloatList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.keyguard.OplusLockIconView;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExt;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.systemui.panelanimation.platformblur.MaterialBlurStateManager;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationTiltShiftBlurController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationTiltShiftBlurController";
    public boolean callbackRegistered;
    private Job collectJob;
    private OplusNotificationTiltShiftBlurContainer container;
    public int lastExtRegionOwnScrollY;
    public int lastQsSimpleHeaderHeight;
    private View lockIconContainer;
    private final Lazy materialBlurStateManager;
    private final Lazy notificationStackScrollLayoutControllerExt;
    private final NotificationStackScrollLayoutControllerExt.OnSizeChangedListener nsslSizeChangedListener;
    private final Lazy oplusSeparateNotificationAndQSState;
    private final OplusNotificationTiltShiftBlurController$stateListener$1 stateListener;
    private final Lazy statusBarStateController;
    private final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob((Job) null).plus(Dispatchers.getMain().getImmediate()));
    public int currentStatusBarState = -1;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.blur.OplusNotificationTiltShiftBlurController$bind$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function1 $callback;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.blur.OplusNotificationTiltShiftBlurController$bind$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function3 {
            /* synthetic */ boolean Z$0;
            /* synthetic */ boolean Z$1;
            int label;

            public final Object invoke(Object obj, Object obj2, Object obj3) {
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(3, (Continuation) obj3);
                anonymousClass1.Z$0 = zBooleanValue;
                anonymousClass1.Z$1 = zBooleanValue2;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                boolean z = this.Z$0;
                boolean z2 = this.Z$1;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    return Boxing.boxBoolean(z && z2);
                }
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        /* JADX INFO: renamed from: com.oplus.systemui.notification.blur.OplusNotificationTiltShiftBlurController$bind$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C00062 implements FlowCollector {
            public /* synthetic */ Function1 $callback;
            public /* synthetic */ OplusNotificationTiltShiftBlurController this$0;

            public final Object emit(Object obj, Continuation continuation) {
                ((Boolean) obj).getClass();
                OplusNotificationTiltShiftBlurController oplusNotificationTiltShiftBlurController = this.this$0;
                OplusNotificationTiltShiftBlurController.access$applyCurrentState(oplusNotificationTiltShiftBlurController, "tiltShiftBlurConfigChanged");
                ((NotificationStackScrollLayoutControllerExt) oplusNotificationTiltShiftBlurController.notificationStackScrollLayoutControllerExt.get()).nsslAlphaConfigChanged();
                this.$callback.invoke(Boxing.boxBoolean(oplusNotificationTiltShiftBlurController.getTiltShiftBlurEnabled()));
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Function1 function1, Continuation continuation) {
            super(2, continuation);
            this.$callback = function1;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusNotificationTiltShiftBlurController.this.new AnonymousClass2(this.$callback, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.combine(((MaterialBlurStateManager) OplusNotificationTiltShiftBlurController.this.materialBlurStateManager.get()).getEnableMaterialBlur(), ((OplusSeparateNotificationAndQSState) OplusNotificationTiltShiftBlurController.this.oplusSeparateNotificationAndQSState.get()).getEnableSeparateNotificationAndQS(), new AnonymousClass1(3, null)));
                OplusNotificationTiltShiftBlurController oplusNotificationTiltShiftBlurController = OplusNotificationTiltShiftBlurController.this;
                Function1 function1 = this.$callback;
                C00062 c00062 = new C00062();
                c00062.this$0 = oplusNotificationTiltShiftBlurController;
                c00062.$callback = function1;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (flowDistinctUntilChanged.collect(c00062, this) == coroutine_suspended) {
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

    public OplusNotificationTiltShiftBlurController(Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4) {
        this.materialBlurStateManager = lazy;
        this.oplusSeparateNotificationAndQSState = lazy2;
        this.statusBarStateController = lazy3;
        this.notificationStackScrollLayoutControllerExt = lazy4;
        OplusNotificationTiltShiftBlurController$stateListener$1 oplusNotificationTiltShiftBlurController$stateListener$1 = new OplusNotificationTiltShiftBlurController$stateListener$1();
        oplusNotificationTiltShiftBlurController$stateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.stateListener = oplusNotificationTiltShiftBlurController$stateListener$1;
        OplusNotificationTiltShiftBlurController$nsslSizeChangedListener$1 oplusNotificationTiltShiftBlurController$nsslSizeChangedListener$1 = new OplusNotificationTiltShiftBlurController$nsslSizeChangedListener$1();
        oplusNotificationTiltShiftBlurController$nsslSizeChangedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.nsslSizeChangedListener = oplusNotificationTiltShiftBlurController$nsslSizeChangedListener$1;
    }

    public static final void access$applyCurrentState(OplusNotificationTiltShiftBlurController oplusNotificationTiltShiftBlurController, String str) {
        Integer numComputeTargetHeight;
        OplusNotificationTiltShiftBlurContainer oplusNotificationTiltShiftBlurContainer = oplusNotificationTiltShiftBlurController.container;
        if (oplusNotificationTiltShiftBlurContainer == null) {
            return;
        }
        int iIntValue = 0;
        boolean z = oplusNotificationTiltShiftBlurController.currentStatusBarState != 1;
        boolean tiltShiftBlurEnabled = oplusNotificationTiltShiftBlurController.getTiltShiftBlurEnabled();
        boolean z2 = z && tiltShiftBlurEnabled;
        View view = oplusNotificationTiltShiftBlurController.lockIconContainer;
        if (view != null && (numComputeTargetHeight = oplusNotificationTiltShiftBlurController.computeTargetHeight(oplusNotificationTiltShiftBlurController.lastQsSimpleHeaderHeight, view)) != null) {
            iIntValue = numComputeTargetHeight.intValue();
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(oplusNotificationTiltShiftBlurController.currentStatusBarState, "applyCurrentState reason=", str, "; state=", ";tiltShiftBlurEnabled=");
            KeyguardLogger$.ExternalSyntheticOutline0.m("; shouldBlur=", "; tiltShiftBlurHeight: ", sbM, tiltShiftBlurEnabled, z2);
            MobileInputLogger$.ExternalSyntheticOutline0.m(iIntValue, "OplusNotificationTiltShiftBlurController-->", "SystemUi--Notification", sbM);
        }
        oplusNotificationTiltShiftBlurContainer.updateTiltShiftBlurtSize(oplusNotificationTiltShiftBlurController.getNsslWidth(), iIntValue);
        oplusNotificationTiltShiftBlurController.updateNsslAlphaAreaHeight(iIntValue);
        if (z2) {
            oplusNotificationTiltShiftBlurContainer.setMaterialBlur();
        } else {
            oplusNotificationTiltShiftBlurContainer.resetMaterialBlur();
        }
    }

    private final int getNsslWidth() {
        return ((NotificationStackScrollLayoutControllerExt) this.notificationStackScrollLayoutControllerExt.get()).getNsslWidth();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getTiltShiftBlurEnabled() {
        return ((MaterialBlurStateManager) this.materialBlurStateManager.get()).getTiltShiftBlurEnabled();
    }

    public final void bind(OplusNotificationTiltShiftBlurContainer oplusNotificationTiltShiftBlurContainer, OplusLockIconView oplusLockIconView, Function1 function1) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusNotificationTiltShiftBlurController-->".concat("bind container=" + oplusNotificationTiltShiftBlurContainer));
        }
        this.container = oplusNotificationTiltShiftBlurContainer;
        this.lockIconContainer = oplusLockIconView;
        this.currentStatusBarState = ((StatusBarStateController) this.statusBarStateController.get()).getState();
        if (!this.callbackRegistered) {
            ((StatusBarStateController) this.statusBarStateController.get()).addCallback(this.stateListener);
            ((NotificationStackScrollLayoutControllerExt) this.notificationStackScrollLayoutControllerExt.get()).addOnSizeChangedListener(this.nsslSizeChangedListener);
            this.callbackRegistered = true;
        }
        Job job = this.collectJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        this.collectJob = BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(function1, null), 3);
    }

    public final Integer computeTargetHeight(int i, View view) {
        if (this.currentStatusBarState == 0) {
            if (i <= 0) {
                return null;
            }
            return Integer.valueOf(i);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 == null) {
            return null;
        }
        if (layoutParams2.topMargin == 0 && layoutParams2.getMarginStart() == 0 && layoutParams2.width <= 0 && layoutParams2.height <= 0) {
            return null;
        }
        int measuredHeight = layoutParams2.height;
        if (measuredHeight <= 0) {
            measuredHeight = view.getMeasuredHeight() > 0 ? view.getMeasuredHeight() : view.getHeight();
        }
        return Integer.valueOf(layoutParams2.topMargin + measuredHeight);
    }

    public final boolean isShiftBlurViewVisible() {
        OplusNotificationTiltShiftBlurContainer oplusNotificationTiltShiftBlurContainer = this.container;
        return oplusNotificationTiltShiftBlurContainer != null && oplusNotificationTiltShiftBlurContainer.getVisibility() == 0;
    }

    public final void setFirstChildTranslationY(int i) {
        Integer numComputeTargetHeight;
        View view = this.lockIconContainer;
        int iIntValue = (view == null || (numComputeTargetHeight = computeTargetHeight(this.lastQsSimpleHeaderHeight, view)) == null) ? 0 : numComputeTargetHeight.intValue();
        int i2 = iIntValue - i;
        int i3 = i2 > 0 ? i2 : 0;
        if (this.lastExtRegionOwnScrollY != i3) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MobileInputLogger$.ExternalSyntheticOutline0.m(iIntValue, "OplusNotificationTiltShiftBlurController-->", "SystemUi--Notification", FloatList$.ExternalSyntheticOutline0.m("setFirstChildTranslationY newOwnScrollY:", i3, i, "; translationY: ", "; tiltShiftBlurHeight: "));
            }
            setOwnScrollY(i3);
            this.lastExtRegionOwnScrollY = i3;
        }
    }

    public final void setOwnScrollY(int i) {
        OplusNotificationTiltShiftBlurContainer oplusNotificationTiltShiftBlurContainer;
        View view;
        int i2 = this.currentStatusBarState;
        if (i2 != 1) {
            if (i2 == 2 && getTiltShiftBlurEnabled() && (view = this.lockIconContainer) != null) {
                view.setVisibility(i == 0 ? 0 : 4);
            }
            OplusNotificationTiltShiftBlurContainer oplusNotificationTiltShiftBlurContainer2 = this.container;
            if (i == 0) {
                if (oplusNotificationTiltShiftBlurContainer2 != null) {
                    oplusNotificationTiltShiftBlurContainer2.setVisibility(4);
                }
            } else if ((oplusNotificationTiltShiftBlurContainer2 == null || oplusNotificationTiltShiftBlurContainer2.getVisibility() != 0) && (oplusNotificationTiltShiftBlurContainer = this.container) != null) {
                oplusNotificationTiltShiftBlurContainer.setVisibility(0);
            }
            ((NotificationStackScrollLayoutControllerExt) this.notificationStackScrollLayoutControllerExt.get()).setOwnScrollY(i);
        }
    }

    public final void unbind() {
        OplusNotificationTiltShiftBlurContainer oplusNotificationTiltShiftBlurContainer;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusNotificationTiltShiftBlurController-->unbind");
        }
        Job job = this.collectJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        this.collectJob = null;
        if (this.callbackRegistered) {
            ((StatusBarStateController) this.statusBarStateController.get()).removeCallback(this.stateListener);
            ((NotificationStackScrollLayoutControllerExt) this.notificationStackScrollLayoutControllerExt.get()).removeOnSizeChangedListener(this.nsslSizeChangedListener);
            this.callbackRegistered = false;
        }
        if (getTiltShiftBlurEnabled() && (oplusNotificationTiltShiftBlurContainer = this.container) != null) {
            oplusNotificationTiltShiftBlurContainer.resetMaterialBlur();
        }
        this.container = null;
    }

    public final void updateNsslAlphaAreaHeight(int i) {
        ((NotificationStackScrollLayoutControllerExt) this.notificationStackScrollLayoutControllerExt.get()).updateNsslAlphaAreaHeight(i);
    }

    public final void updateQsSimpleHeaderHeight(int i) {
        OplusNotificationTiltShiftBlurContainer oplusNotificationTiltShiftBlurContainer;
        if (this.lastQsSimpleHeaderHeight == i) {
            return;
        }
        this.lastQsSimpleHeaderHeight = i;
        if (this.currentStatusBarState == 0) {
            if (this.lockIconContainer != null && (oplusNotificationTiltShiftBlurContainer = this.container) != null) {
                oplusNotificationTiltShiftBlurContainer.updateTiltShiftBlurtSize(getNsslWidth(), this.lastQsSimpleHeaderHeight);
            }
            updateNsslAlphaAreaHeight(this.lastQsSimpleHeaderHeight);
        }
    }
}
