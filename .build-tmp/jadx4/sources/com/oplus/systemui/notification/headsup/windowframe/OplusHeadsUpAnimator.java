package com.oplus.systemui.notification.headsup.windowframe;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.app.displaylib.PerDisplayRepository;
import com.android.internal.policy.SystemBarUtils;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.display.dagger.ReferenceSysUIDisplaySubcomponent;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.shade.data.repository.ShadeDisplaysRepository;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.android.systemui.statusbar.window.data.repository.StatusBarWindowStatePerDisplayRepository;
import com.android.systemui.statusbar.window.shared.model.StatusBarWindowState;
import com.android.systemui.util.JankUtils;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.notification.headsup.zoom.HeadsUpZoomParams;
import com.oplus.systemui.notification.headsup.zoom.HeadsUpZoomParamsKt;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplus.systemui.statusbar.notification.collection.inflation.NotifInflateDelayStrategyImpl;
import com.oplus.systemui.statusbar.notification.helper.HeadsUpHelper;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.PerformanceUtils;
import com.oplusos.systemui.common.utils.DeviceUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CancellationException;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpAnimator implements HeadsUpAnimator, HeadsUpViewControl, ConfigurationController.ConfigurationListener, OnImeHeightChangedListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "HeadsUpAnimator";
    private final AmbientState ambientState;
    private Job collectJob;
    private final Context context;
    private final HeadsUpContainerWindow control;
    public float diffHoldY;
    private final PerDisplayRepository displaySubComponentRepository;
    private final HeadsUpHelper headsUpHelper;
    private final Lazy headsUpNotificationInteractor;
    private final HeadsUpZoomParams headsUpZoom;
    public float holdY;
    private COUIDynamicAnimation.OnAnimationEndListener lastDismissedDelayEndListener;
    private COUIDynamicAnimation.OnAnimationEndListener lastEndListenser;
    public float lastHoldY;
    public float lastY;
    private final Handler mainHandler;
    private final HeadsUpAnimator.NormalHeadsUpParams normalParams;
    private final NotifInflateDelayStrategyImpl notifInflateDelayStrategy;
    private HeadsUpAnimator.NormalHeadsUpParams params;
    private final CoroutineScope scope;
    private final ShadeDisplaysRepository shadeDisplaysRepository;
    private final kotlin.Lazy springAnimation$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpAnimator$animateTo$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements COUIDynamicAnimation.OnAnimationEndListener {
        public /* synthetic */ Object $endCallback;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusHeadsUpAnimator this$0;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
            switch (this.$r8$classId) {
                case 0:
                    OplusHeadsUpAnimator oplusHeadsUpAnimator = this.this$0;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        OplusLockIconView$.ExternalSyntheticOutline0.m("onAnimationEnd: isCanceled=", "HeadsUpAnimator-->", "SystemUi--Notification", z);
                    }
                    oplusHeadsUpAnimator.setPerformanceState(false);
                    oplusHeadsUpAnimator.getSpringAnimation().removeEndListener(this);
                    oplusHeadsUpAnimator.control.notifySizeOrLocationChanged(null);
                    ((Function0) this.$endCallback).invoke();
                    break;
                default:
                    OplusHeadsUpAnimator$dismiss$animateDismiss$1 oplusHeadsUpAnimator$dismiss$animateDismiss$1 = (OplusHeadsUpAnimator$dismiss$animateDismiss$1) this.$endCallback;
                    OplusHeadsUpAnimator oplusHeadsUpAnimator2 = this.this$0;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        OplusLockIconView$.ExternalSyntheticOutline0.m("dismiss: wait show end isCanceled=", "HeadsUpAnimator-->", "SystemUi--Notification", z);
                    }
                    oplusHeadsUpAnimator2.getSpringAnimation().removeEndListener(this);
                    if (!z) {
                        Handler handler = oplusHeadsUpAnimator2.mainHandler;
                        dismiss.tempLastEndListener.1.onAnimationEnd.2 r5 = new dismiss.tempLastEndListener.1.onAnimationEnd.2();
                        r5.$animateDismiss = oplusHeadsUpAnimator$dismiss$animateDismiss$1;
                        VarHandle.storeStoreFence();
                        handler.post(r5);
                    } else {
                        oplusHeadsUpAnimator$dismiss$animateDismiss$1.run();
                    }
                    break;
            }
        }
    }

    public static Unit $r8$lambda$h3m0W74D5ET7qneYQkpmfROKkkQ(OplusHeadsUpAnimator oplusHeadsUpAnimator, View view) {
        oplusHeadsUpAnimator.notifInflateDelayStrategy.stopWaitAction(1);
        if (view instanceof ExpandableNotificationRow) {
            ((OplusNotificationEntryExImpl) ((ExpandableNotificationRow) view).getEntry().getEx()).setHeadsUpEnd();
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "HeadsUpAnimator-->show: end");
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$qS9eTgJm2eWstbo2E9hlQc3oOPc(OplusHeadsUpAnimator oplusHeadsUpAnimator, View view) {
        oplusHeadsUpAnimator.notifInflateDelayStrategy.stopWaitAction(1);
        if (view instanceof ExpandableNotificationRow) {
            ((OplusNotificationEntryExImpl) ((ExpandableNotificationRow) view).getEntry().getEx()).setHeadsUpEnd();
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "HeadsUpAnimator-->show: end");
        }
        JankUtils.gfxSceneEnd(oplusHeadsUpAnimator.context, new int[]{40});
        JankUtils.latencySceneEnd(oplusHeadsUpAnimator.context, 412);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$tjRxtVfihYB2VUmhA6elwmskMKQ(Function1 function1, OplusHeadsUpAnimator oplusHeadsUpAnimator) {
        if (function1 != null) {
            function1.invoke(oplusHeadsUpAnimator.control.getView());
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "HeadsUpAnimator-->onEndDrag: animation end");
        }
        return Unit.INSTANCE;
    }

    public OplusHeadsUpAnimator(Context context, AmbientState ambientState, HeadsUpZoomParams headsUpZoomParams, HeadsUpContainerWindow headsUpContainerWindow, NotifInflateDelayStrategyImpl notifInflateDelayStrategyImpl, Lazy lazy, Handler handler, HeadsUpHelper headsUpHelper, CoroutineScope coroutineScope, ShadeDisplaysRepository shadeDisplaysRepository, PerDisplayRepository perDisplayRepository) {
        this.context = context;
        this.ambientState = ambientState;
        this.headsUpZoom = headsUpZoomParams;
        this.control = headsUpContainerWindow;
        this.notifInflateDelayStrategy = notifInflateDelayStrategyImpl;
        this.headsUpNotificationInteractor = lazy;
        this.mainHandler = handler;
        this.headsUpHelper = headsUpHelper;
        this.scope = coroutineScope;
        this.shadeDisplaysRepository = shadeDisplaysRepository;
        this.displaySubComponentRepository = perDisplayRepository;
        headsUpContainerWindow.providerHeadsUpLayout().setImeHeightChangedListener(this);
        OplusHeadsUpAnimator$$ExternalSyntheticLambda0 oplusHeadsUpAnimator$$ExternalSyntheticLambda0 = new OplusHeadsUpAnimator$$ExternalSyntheticLambda0();
        oplusHeadsUpAnimator$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.springAnimation$delegate = LazyKt__LazyJVMKt.lazy(oplusHeadsUpAnimator$$ExternalSyntheticLambda0);
        HeadsUpAnimator.NormalHeadsUpParams normalHeadsUpParams = new HeadsUpAnimator.NormalHeadsUpParams(ambientState);
        this.normalParams = normalHeadsUpParams;
        this.params = normalHeadsUpParams;
        float f = this.holdY;
        this.diffHoldY = f;
        this.lastHoldY = f;
    }

    public static final void access$handleStatusBarStateChanged(OplusHeadsUpAnimator oplusHeadsUpAnimator, int i) {
        if (oplusHeadsUpAnimator.control.getView() == null) {
            return;
        }
        oplusHeadsUpAnimator.refreshHoldY(i);
        if (oplusHeadsUpAnimator.lastHoldY == oplusHeadsUpAnimator.holdY) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "HeadsUpAnimator-->no need move");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "HeadsUpAnimator-->move: start");
        }
        oplusHeadsUpAnimator.animateTo(oplusHeadsUpAnimator.holdY, new OplusHeadsUpAnimator$$ExternalSyntheticLambda4(1));
        oplusHeadsUpAnimator.diffHoldY = oplusHeadsUpAnimator.holdY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final COUISpringAnimation getSpringAnimation() {
        return (COUISpringAnimation) this.springAnimation$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPerformanceState(boolean z) {
        ExpandableNotificationRow view = this.control.getView();
        if (view == null || !(view instanceof ExpandableNotificationRow)) {
            return;
        }
        view.mRowEx.setBoostFrameRate(z);
    }

    public final void animateTo(float f, Function0 function0) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("animateTo: ", this.control.getTranslationY(), " to ", f, "HeadsUpAnimator-->"));
        }
        this.control.notifySizeOrLocationChanged(Float.valueOf(f));
        if (((COUIDynamicAnimation) getSpringAnimation()).mRunning) {
            getSpringAnimation().cancel();
        }
        if (this.lastEndListenser != null) {
            getSpringAnimation().removeEndListener(this.lastEndListenser);
        }
        if (this.control.getView() == null) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "animateTo: control.view == null");
            function0.invoke();
            return;
        }
        setPerformanceState(true);
        getSpringAnimation().setStartValue(this.control.getBaseTranslationY());
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
        anonymousClass2.this$0 = this;
        anonymousClass2.$endCallback = function0;
        VarHandle.storeStoreFence();
        this.lastEndListenser = anonymousClass2;
        getSpringAnimation().addEndListener(this.lastEndListenser);
        getSpringAnimation().animateToFinalPosition(f);
    }

    public final void cleanupAndInvokeOnEnd(Function1 function1) {
        View view = this.control.getView();
        this.control.setView(null);
        if (this.params instanceof HeadsUpZoomParams) {
            this.headsUpZoom.setView(null);
            this.params = this.normalParams;
        }
        if (function1 != null) {
            function1.invoke(view);
        }
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator
    public void dismiss(boolean z, String str, Function1 function1) {
        Job job = this.collectJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        this.collectJob = null;
        if (this.control.getView() == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "HeadsUpAnimator-->dismiss: has dismiss");
            }
            if (function1 != null) {
                function1.invoke((Object) null);
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(str, "HeadsUpAnimator-->", "SystemUi--Notification", TextFieldImplKt$.ExternalSyntheticOutline0.m("dismiss: ", ",springIsRunning:", ",reason: ", z, ((COUIDynamicAnimation) getSpringAnimation()).mRunning));
        }
        if (!z) {
            tryClearDismissedDelayEndListener();
            if (((COUIDynamicAnimation) getSpringAnimation()).mRunning) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "HeadsUpAnimator-->dismiss: cancel");
                }
                getSpringAnimation().cancel();
            }
            if (this.lastEndListenser != null) {
                getSpringAnimation().removeEndListener(this.lastEndListenser);
                this.lastEndListenser = null;
            }
            cleanupAndInvokeOnEnd(function1);
            return;
        }
        OplusHeadsUpAnimator$dismiss$animateDismiss$1 oplusHeadsUpAnimator$dismiss$animateDismiss$1 = new OplusHeadsUpAnimator$dismiss$animateDismiss$1();
        oplusHeadsUpAnimator$dismiss$animateDismiss$1.this$0 = this;
        oplusHeadsUpAnimator$dismiss$animateDismiss$1.$onEnd = function1;
        VarHandle.storeStoreFence();
        if (!((COUIDynamicAnimation) getSpringAnimation()).mRunning) {
            oplusHeadsUpAnimator$dismiss$animateDismiss$1.run();
            return;
        }
        tryClearDismissedDelayEndListener();
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(1);
        anonymousClass2.this$0 = this;
        anonymousClass2.$endCallback = oplusHeadsUpAnimator$dismiss$animateDismiss$1;
        VarHandle.storeStoreFence();
        this.lastDismissedDelayEndListener = anonymousClass2;
        getSpringAnimation().addEndListener(anonymousClass2);
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator
    public boolean getIsNeedInterceptZoom() {
        return false;
    }

    public final ShadeDisplaysRepository getShadeDisplaysRepository() {
        return this.shadeDisplaysRepository;
    }

    public final boolean isHeadsUpAnimatorRunning() {
        return ((COUIDynamicAnimation) getSpringAnimation()).mRunning;
    }

    public void onConfigChanged(Configuration configuration) {
        if (this.headsUpHelper.getTopIsFullscreen()) {
            refreshHoldY(2);
        } else {
            refreshHoldY(0);
        }
        this.diffHoldY = this.holdY;
        this.control.onConfigChanged(configuration);
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpViewControl
    public boolean onEndDrag(float f, Function1 function1) {
        int i = 0;
        if (this.params.endHeadsUpTrackingEvent()) {
            setPerformanceState(false);
            if (function1 != null) {
                function1.invoke((Object) null);
            }
            return false;
        }
        if (this.control.getTranslationY() <= this.holdY || f <= -1.0f) {
            ((OplusHeadsUpNotificationInteractor) this.headsUpNotificationInteractor.get()).dismiss(true, "onEndDrag", function1);
            return true;
        }
        if (this.control.getTranslationY() >= this.holdY + this.ambientState.getExt().getMaxHeadsUpPullHeight() || this.control.getTouchMotion() != null) {
            getSpringAnimation().setStartVelocity(0.0f);
        } else {
            getSpringAnimation().setStartVelocity(f);
        }
        float f2 = this.diffHoldY;
        OplusHeadsUpAnimator$$ExternalSyntheticLambda3 oplusHeadsUpAnimator$$ExternalSyntheticLambda3 = new OplusHeadsUpAnimator$$ExternalSyntheticLambda3(i);
        oplusHeadsUpAnimator$$ExternalSyntheticLambda3.f$0 = function1;
        oplusHeadsUpAnimator$$ExternalSyntheticLambda3.f$1 = this;
        VarHandle.storeStoreFence();
        animateTo(f2, oplusHeadsUpAnimator$$ExternalSyntheticLambda3);
        return false;
    }

    public final void onImeHeightChanged(int i, int i2, int i3) {
        float f = i2 + i3;
        float f2 = this.holdY;
        float f3 = f + f2;
        float f4 = i;
        if (f3 > f4) {
            f2 -= f3 - f4;
        }
        this.diffHoldY = f2;
        boolean z = (this.control.getView() instanceof ExpandableNotificationRow) && this.control.getView().mRowEx.getIsZoomAnimating();
        if (this.diffHoldY == this.control.getTranslationY() || z) {
            return;
        }
        animateTo(this.diffHoldY, new OplusHeadsUpAnimator$$ExternalSyntheticLambda4(0));
    }

    public final void onStartDrag() {
        setPerformanceState(true);
        this.lastY = this.control.getTranslationY();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("onStartDrag: ", this.lastY, "HeadsUpAnimator-->", "SystemUi--Notification");
        }
        if (((COUIDynamicAnimation) getSpringAnimation()).mRunning) {
            getSpringAnimation().cancel();
        }
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpViewControl
    public void onY(float f, Function0 function0) {
        Float fOnTouchMove = this.params.onTouchMove(new HeadsUpAnimator.MoveData(this.holdY, this.control.getTranslationY(), this.lastY, f, function0));
        if (fOnTouchMove != null) {
            this.control.setBaseTranslationY(fOnTouchMove.floatValue());
        }
    }

    public final void refreshHoldY(int i) {
        this.lastHoldY = this.holdY;
        float dimensionPixelSize = this.context.getResources().getDimensionPixelSize(2131168426);
        if (DeviceUtil.isLandscape(this.context) && (i == 2 || i == 1)) {
            this.holdY = dimensionPixelSize;
        } else {
            this.holdY = dimensionPixelSize + SystemBarUtils.getStatusBarHeight(this.context);
        }
    }

    public final void resetDrag() {
        if (this.headsUpZoom.endHeadsUpTrackingEvent()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "HeadsUpAnimator-->resetDrag");
            }
            setPerformanceState(false);
        }
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator
    public void reuseSwipeHelper(NotificationSwipeHelper notificationSwipeHelper) {
        this.control.reuseSwipeHelper(notificationSwipeHelper);
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator
    public void setHeadsUpHelper(OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper) {
        oplusHeadsUpTouchHelper.setControl(this);
        this.control.setHeadsUpHelper(oplusHeadsUpTouchHelper);
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator
    public void show(View view) {
        int i = 0;
        if (this.headsUpHelper.getTopIsFullscreen()) {
            refreshHoldY(2);
        } else {
            refreshHoldY(0);
        }
        this.collectJob = BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        this.params = this.normalParams;
        if (view instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            if (HeadsUpZoomParamsKt.isSupportZoom(expandableNotificationRow)) {
                resetDrag();
                this.headsUpZoom.setView(expandableNotificationRow);
                this.params = this.headsUpZoom;
            }
        }
        int i2 = 1;
        this.notifInflateDelayStrategy.startWaitAction(1);
        if (view.getParent() == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "HeadsUpAnimator-->show: ");
            }
            this.control.setView(view);
            view.setVisibility(0);
            this.control.setTranslationY(view instanceof ExpandableNotificationRow ? Math.min(-((ExpandableNotificationRow) view).getActualHeight(), -430.0f) : -430.0f);
            JankUtils.gfxSceneBegin(this.context, 40, "BANNER_NF_APPEAR", 0L, 4L, true);
            PerformanceUtils.notifyAnimationNotification();
            JankUtils.latencySceneEnd(this.context, 411);
            float f = this.holdY;
            OplusHeadsUpAnimator$$ExternalSyntheticLambda1 oplusHeadsUpAnimator$$ExternalSyntheticLambda1 = new OplusHeadsUpAnimator$$ExternalSyntheticLambda1(i);
            oplusHeadsUpAnimator$$ExternalSyntheticLambda1.f$0 = this;
            oplusHeadsUpAnimator$$ExternalSyntheticLambda1.f$1 = view;
            VarHandle.storeStoreFence();
            animateTo(f, oplusHeadsUpAnimator$$ExternalSyntheticLambda1);
        } else {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "HeadsUpAnimator-->show: again");
            }
            float f2 = this.holdY;
            OplusHeadsUpAnimator$$ExternalSyntheticLambda1 oplusHeadsUpAnimator$$ExternalSyntheticLambda2 = new OplusHeadsUpAnimator$$ExternalSyntheticLambda1(i2);
            oplusHeadsUpAnimator$$ExternalSyntheticLambda2.f$0 = this;
            oplusHeadsUpAnimator$$ExternalSyntheticLambda2.f$1 = view;
            VarHandle.storeStoreFence();
            animateTo(f2, oplusHeadsUpAnimator$$ExternalSyntheticLambda2);
        }
        this.diffHoldY = this.holdY;
    }

    public final void tryClearDismissedDelayEndListener() {
        COUIDynamicAnimation.OnAnimationEndListener onAnimationEndListener = this.lastDismissedDelayEndListener;
        if (onAnimationEndListener != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "HeadsUpAnimator-->tryClearDismissedDelayEndListener::but it has value");
            }
            getSpringAnimation().removeEndListener(onAnimationEndListener);
            this.lastDismissedDelayEndListener = null;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpAnimator$show$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusHeadsUpAnimator.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow displayId = OplusHeadsUpAnimator.this.getShadeDisplaysRepository().getDisplayId();
                OplusHeadsUpAnimator oplusHeadsUpAnimator = OplusHeadsUpAnimator.this;
                C00191 c00191 = new C00191();
                c00191.this$0 = oplusHeadsUpAnimator;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (displayId.collect(c00191, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpAnimator$show$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C00191 implements FlowCollector {
            public /* synthetic */ OplusHeadsUpAnimator this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpAnimator$show$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            public final class AnonymousClass2 implements FlowCollector {
                public /* synthetic */ OplusHeadsUpAnimator this$0;

                /* JADX WARN: Code duplicated, block: B:14:0x0038 A[PHI: r0
                  0x0038: PHI (r0v1 int) = (r0v0 int), (r0v2 int) binds: [B:10:0x0032, B:12:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
                public final Object emit(Object obj, Continuation continuation) {
                    StatusBarWindowState statusBarWindowState = (StatusBarWindowState) obj;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "HeadsUpAnimator-->".concat("onStatusBarWindowStateChanged:" + statusBarWindowState));
                    }
                    int iOrdinal = statusBarWindowState.ordinal();
                    int i = 0;
                    if (iOrdinal != 0) {
                        int i2 = 1;
                        if (iOrdinal != 1) {
                            i2 = 2;
                            if (iOrdinal == 2) {
                                i = i2;
                            }
                        } else {
                            i = i2;
                        }
                    }
                    OplusHeadsUpAnimator.access$handleStatusBarStateChanged(this.this$0, i);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: Code duplicated, block: B:7:0x0015  */
            public final Object emit(int i, Continuation continuation) {
                OplusHeadsUpAnimator$show$1$1$emit$1 oplusHeadsUpAnimator$show$1$1$emit$1;
                OplusHeadsUpAnimator oplusHeadsUpAnimator = this.this$0;
                if (continuation instanceof OplusHeadsUpAnimator$show$1$1$emit$1) {
                    oplusHeadsUpAnimator$show$1$1$emit$1 = (OplusHeadsUpAnimator$show$1$1$emit$1) continuation;
                    int i2 = oplusHeadsUpAnimator$show$1$1$emit$1.label;
                    if ((i2 & Integer.MIN_VALUE) != 0) {
                        oplusHeadsUpAnimator$show$1$1$emit$1.label = i2 - Integer.MIN_VALUE;
                    } else {
                        oplusHeadsUpAnimator$show$1$1$emit$1 = new OplusHeadsUpAnimator$show$1$1$emit$1(this, continuation);
                    }
                } else {
                    oplusHeadsUpAnimator$show$1$1$emit$1 = new OplusHeadsUpAnimator$show$1$1$emit$1(this, continuation);
                }
                Object obj = oplusHeadsUpAnimator$show$1$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i3 = oplusHeadsUpAnimator$show$1$1$emit$1.label;
                if (i3 == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "displayId changed:", "HeadsUpAnimator-->", "SystemUi--Notification");
                    }
                    StatusBarWindowStatePerDisplayRepository statusBarWindowStateRepository = ((ReferenceSysUIDisplaySubcomponent) oplusHeadsUpAnimator.displaySubComponentRepository.getOrDefault(i)).getStatusBarWindowStateRepository();
                    StateFlow windowState = statusBarWindowStateRepository.getWindowState();
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2();
                    anonymousClass2.this$0 = oplusHeadsUpAnimator;
                    VarHandle.storeStoreFence();
                    oplusHeadsUpAnimator$show$1$1$emit$1.L$0 = SpillingKt.nullOutSpilledVariable(statusBarWindowStateRepository);
                    oplusHeadsUpAnimator$show$1$1$emit$1.I$0 = i;
                    oplusHeadsUpAnimator$show$1$1$emit$1.label = 1;
                    if (windowState.collect(anonymousClass2, oplusHeadsUpAnimator$show$1$1$emit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i3 != 1) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                Regex$.ExternalSyntheticBUOutline0.m$1();
                return null;
            }

            public final /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit(((Number) obj).intValue(), continuation);
            }
        }
    }
}
