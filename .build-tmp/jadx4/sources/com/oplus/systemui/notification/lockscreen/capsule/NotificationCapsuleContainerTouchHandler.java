package com.oplus.systemui.notification.lockscreen.capsule;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.biometrics.OplusBiometricAuthController;
import com.oplus.systemui.biometrics.finger.FingerSwitchHelper;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.event.CapsuleDragScaleEmptyHandler;
import com.oplus.systemui.notification.lockscreen.capsule.event.CapsuleDragScaleHelper;
import com.oplus.systemui.notification.lockscreen.capsule.event.CapsuleMotionTracker;
import com.oplus.systemui.notification.lockscreen.capsule.event.CapsuleScrollerHorizontal;
import com.oplus.systemui.notification.lockscreen.capsule.event.CapsuleScrollerVertical;
import com.oplus.systemui.notification.lockscreen.capsule.event.DraggableStateHelper;
import com.oplus.systemui.notification.lockscreen.capsule.event.ICapsuleDragEventHandler;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataChangeInterceptor;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataOrderHandler;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateDecider;
import com.oplus.systemui.notification.lockscreen.statistic.LockScreenStatisticUtil;
import com.oplus.systemui.notification.material.NotificationSpotLightEffectManager;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationCapsuleContainerTouchHandler {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationCapsuleContainerScroller";
    private final NotificationCapsuleContainer capsuleContainer;
    private final NotificationCapsuleContainerControllerImpl capsuleContainerController;
    private final Context context;
    private final CapsuleDataChangeInterceptor dataChangeInterceptor;
    private final Runnable delayResetDataInterceptTask;
    private final Lazy dragScaleHelper$delegate;
    private CapsuleItemView draggerView;
    public boolean enableHorizontalScroll = true;
    private final LockScreenNotificationExtStateDecider extStateDecider;
    private final CapsuleScrollerHorizontal hScroller;
    public int mActivePointerId;
    private final Handler mainHandler;
    public boolean motionHandled;
    private final CapsuleMotionTracker motionTracker;
    private final dagger.Lazy notificationCapsuleInteractor;
    private final dagger.Lazy oplusBiometricAuthControllerLazy;
    private final dagger.Lazy stateController;
    private final CapsuleScrollerVertical vScroller;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final boolean isQuickImmerToImmer(ILockScreenExtStateController iLockScreenExtStateController) {
            ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType animateType = ((ILockScreenExtStateController.ExtStateEvent) iLockScreenExtStateController.getExtStateEvent().getValue()).getAnimateType();
            animateType.getClass();
            return animateType == ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.IMMERSIVE_TO_IMMERSIVE && SystemClock.uptimeMillis() - ((ILockScreenExtStateController.ExtStateEvent) iLockScreenExtStateController.getExtStateEvent().getValue()).getEventTime() < 200;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LongClickHelper {
        public static final LongClickHelper INSTANCE = new LongClickHelper();
        public static long lastClickTime;
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerTouchHandler$onTouchEvent$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1 {
        public final Object invoke(Object obj) {
            ((CapsuleScrollerHorizontal) ((CallableReference) this).receiver).onHorizontalMove(((Number) obj).floatValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerTouchHandler$onTouchEvent$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass4 extends FunctionReferenceImpl implements Function1 {
        public final Object invoke(Object obj) {
            ((CapsuleScrollerHorizontal) ((CallableReference) this).receiver).onHorizontalUp(((Number) obj).floatValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerTouchHandler$onTouchEvent$6, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass6 extends FunctionReferenceImpl implements Function1 {
        public final Object invoke(Object obj) {
            ((NotificationCapsuleContainerControllerImpl) ((CallableReference) this).receiver).onCapsuleClick((CapsuleItemView) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerTouchHandler$onTouchEvent$7, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass7 extends FunctionReferenceImpl implements Function1 {
        public final Object invoke(Object obj) {
            ((NotificationCapsuleContainerControllerImpl) ((CallableReference) this).receiver).onImmerToImmerClick((CapsuleItemView) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$CalprommvOoAiJs-0Wx-Qd5bFZA, reason: not valid java name */
    public static ICapsuleDragEventHandler m78$r8$lambda$CalprommvOoAiJs0WxQd5bFZA(NotificationCapsuleContainerTouchHandler notificationCapsuleContainerTouchHandler) {
        return NotificationSpotLightEffectManager.spotLightEnabled ? new CapsuleDragScaleHelper(notificationCapsuleContainerTouchHandler.context, notificationCapsuleContainerTouchHandler.capsuleContainer, notificationCapsuleContainerTouchHandler.motionTracker) : CapsuleDragScaleEmptyHandler.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$Wg1ZvjF_cIS4Pp-3sjOWNmPWNYw, reason: not valid java name */
    public static Unit m79$r8$lambda$Wg1ZvjF_cIS4Pp3sjOWNmPWNYw(NotificationCapsuleContainerTouchHandler notificationCapsuleContainerTouchHandler, MotionEvent motionEvent, float f) {
        notificationCapsuleContainerTouchHandler.vScroller.onVerticalMove(motionEvent, f);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$_8CXhe587HUpunxKqWVaQ9-YYYA, reason: not valid java name */
    public static Unit m80$r8$lambda$_8CXhe587HUpunxKqWVaQ9YYYA(NotificationCapsuleContainerTouchHandler notificationCapsuleContainerTouchHandler, Function1 function1) {
        CapsuleItemView baseCard = notificationCapsuleContainerTouchHandler.capsuleContainer.getAnimatorHelper().getAmbientState().getBaseCard();
        if (baseCard != null) {
            function1.invoke(baseCard);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$tCk2KAVSfo2ncn1iitzj-EGliTA, reason: not valid java name */
    public static Unit m81$r8$lambda$tCk2KAVSfo2ncn1iitzjEGliTA(NotificationCapsuleContainerTouchHandler notificationCapsuleContainerTouchHandler, MotionEvent motionEvent, float f) {
        notificationCapsuleContainerTouchHandler.vScroller.onVerticalUp(motionEvent, f);
        return Unit.INSTANCE;
    }

    public NotificationCapsuleContainerTouchHandler(Context context, NotificationCapsuleContainer notificationCapsuleContainer, NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl, dagger.Lazy lazy, dagger.Lazy lazy2, dagger.Lazy lazy3, LockScreenNotificationExtStateDecider lockScreenNotificationExtStateDecider) {
        this.context = context;
        this.capsuleContainer = notificationCapsuleContainer;
        this.capsuleContainerController = notificationCapsuleContainerControllerImpl;
        this.stateController = lazy;
        this.oplusBiometricAuthControllerLazy = lazy2;
        this.notificationCapsuleInteractor = lazy3;
        this.extStateDecider = lockScreenNotificationExtStateDecider;
        CapsuleDataChangeInterceptor capsuleDataChangeInterceptor = new CapsuleDataChangeInterceptor("CapsuleContainerDragInterceptor");
        this.dataChangeInterceptor = capsuleDataChangeInterceptor;
        this.mActivePointerId = -1;
        this.motionTracker = new CapsuleMotionTracker(context);
        NotificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda2 notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda2 = new NotificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda2();
        notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.dragScaleHelper$delegate = LazyKt__LazyJVMKt.lazy(notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda2);
        CapsuleScrollerHorizontal capsuleScrollerHorizontal = new CapsuleScrollerHorizontal(this, notificationCapsuleContainer, capsuleDataChangeInterceptor);
        this.hScroller = capsuleScrollerHorizontal;
        this.vScroller = new CapsuleScrollerVertical(notificationCapsuleContainer, capsuleDataChangeInterceptor, capsuleScrollerHorizontal, notificationCapsuleContainerControllerImpl, lazy3, getDragScaleHelper(), lazy, lockScreenNotificationExtStateDecider);
        capsuleDataChangeInterceptor.register();
        this.mainHandler = new Handler(Looper.getMainLooper());
        NotificationCapsuleContainerTouchHandler$delayResetDataInterceptTask$1 notificationCapsuleContainerTouchHandler$delayResetDataInterceptTask$1 = new NotificationCapsuleContainerTouchHandler$delayResetDataInterceptTask$1();
        notificationCapsuleContainerTouchHandler$delayResetDataInterceptTask$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.delayResetDataInterceptTask = notificationCapsuleContainerTouchHandler$delayResetDataInterceptTask$1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getBeingDragged() {
        return this.motionTracker.getBeingDragged();
    }

    private final ICapsuleDragEventHandler getDragScaleHelper() {
        return (ICapsuleDragEventHandler) this.dragScaleHelper$delegate.getValue();
    }

    public final void cancelCapsuleDragScale(String str) {
        getDragScaleHelper().onCancelDragScale(str);
    }

    public final void enableHorizontalScroll(boolean z, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationCapsuleContainerScroller-->".concat("enableHorizontalScrollChanged: enable=" + z + ", reason=" + str));
        }
        this.enableHorizontalScroll = z;
    }

    public final CapsuleScrollerHorizontal getHScroller() {
        return this.hScroller;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0061  */
    public final void handleMoveEvent(MotionEvent motionEvent, boolean z, Function1 function1, NotificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0 notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0) {
        boolean z2;
        if (motionEvent.findPointerIndex(this.mActivePointerId) == -1) {
            return;
        }
        float touchSlop = CapsuleConfig.INSTANCE.getTouchSlop(motionEvent);
        if (getBeingDragged()) {
            z2 = false;
        } else {
            CapsuleMotionTracker.MoveDiffInfo moveDiffInfoDiffInfo = this.motionTracker.diffInfo(motionEvent);
            if (Math.abs(moveDiffInfoDiffInfo.getXDiff()) > touchSlop || Math.abs(moveDiffInfoDiffInfo.getYDiff()) > touchSlop) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    KeyguardSettingProvider$.ExternalSyntheticOutline0.m("CapsuleStartDrag：from ", z ? "onInterceptTouchEvent" : "onTouchEvent", "NotificationCapsuleContainerScroller-->", "SystemUi--Notification");
                }
                CapsuleDataPinHandler.INSTANCE.getClass();
                CapsuleDataPinHandler.onScrollStartByUser();
                z2 = true;
            } else {
                z2 = false;
            }
        }
        if (z2 && z) {
            this.mainHandler.removeCallbacks(this.delayResetDataInterceptTask);
            this.dataChangeInterceptor.intercept();
        }
        if (z2 || getBeingDragged()) {
            getDragScaleHelper().onCapsuleDragging(motionEvent);
            this.capsuleContainer.getParent().requestDisallowInterceptTouchEvent(true);
            CapsuleMotionTracker.MoveDiffInfo moveDiffInfoTrackMovement = this.motionTracker.trackMovement(motionEvent, touchSlop, z2);
            if (!moveDiffInfoTrackMovement.getHorizontalDragging()) {
                if (notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0 != null) {
                    notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0.invoke(Float.valueOf(moveDiffInfoTrackMovement.getYDiff()));
                    return;
                }
                return;
            } else {
                if (!this.enableHorizontalScroll || function1 == null) {
                    return;
                }
                ((AnonymousClass2) function1).invoke(Float.valueOf(moveDiffInfoTrackMovement.getXDiff()));
                return;
            }
        }
        if (this.motionTracker.getVerticalDragging() && this.vScroller.scrollYAmount > 0.0f) {
            NotificationCapsuleContainer notificationCapsuleContainer = this.capsuleContainer;
            NotificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$1 notificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$1 = new NotificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$1();
            notificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$1.this$0 = this;
            notificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$1.$this_apply = notificationCapsuleContainer;
            notificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$1.$reason = "CapsuleCancel::DragDown";
            VarHandle.storeStoreFence();
            notificationCapsuleContainer.post(notificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$1);
            return;
        }
        if (getBeingDragged()) {
            return;
        }
        LongClickHelper.INSTANCE.getClass();
        if (SystemClock.uptimeMillis() - LongClickHelper.lastClickTime > 2000) {
            NotificationCapsuleContainer notificationCapsuleContainer2 = this.capsuleContainer;
            notificationCapsuleContainer2.performHapticFeedback(0);
            NotificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$1 notificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$2 = new NotificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$1();
            notificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$2.this$0 = this;
            notificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$2.$this_apply = notificationCapsuleContainer2;
            notificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$2.$reason = "LongPress";
            VarHandle.storeStoreFence();
            notificationCapsuleContainer2.post(notificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$2);
        }
    }

    public final void handleUpEvent(MotionEvent motionEvent, boolean z, Function1 function1, NotificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0 notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0, Function1 function2, Function1 function3) {
        if (!z) {
            if (getBeingDragged()) {
                CapsuleMotionTracker.MoveVelocityInfo moveVelocityInfoComputeCurrentVelocity = this.motionTracker.computeCurrentVelocity(motionEvent);
                if (moveVelocityInfoComputeCurrentVelocity.getHorizontalDragging()) {
                    if (function1 != null) {
                        ((AnonymousClass4) function1).invoke(Float.valueOf(moveVelocityInfoComputeCurrentVelocity.getXVelocity()));
                    }
                    LockScreenStatisticUtil.recordCapsuleCount(CapsuleViewExpandKtKt.childSizeExcludeImmersiveCard(this.capsuleContainer));
                } else if (notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0 != null) {
                    notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0.invoke(Float.valueOf(moveVelocityInfoComputeCurrentVelocity.getYVelocity()));
                }
            } else {
                if (function2 != null && motionEvent.getActionMasked() == 1) {
                    LongClickHelper.INSTANCE.getClass();
                    if (SystemClock.uptimeMillis() - LongClickHelper.lastClickTime <= 2000 && !this.capsuleContainer.getAnimatorHelper().getStateAnimRunning()) {
                        CapsuleItemView capsuleItemView = this.draggerView;
                        if (capsuleItemView != null) {
                            if (this.capsuleContainer.getAnimatorHelper().getAmbientState().isHorizontalFling) {
                                CapsuleScrollerHorizontal capsuleScrollerHorizontal = this.hScroller;
                                NotificationCapsuleContainerTouchHandler$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new NotificationCapsuleContainerTouchHandler$.ExternalSyntheticLambda3();
                                externalSyntheticLambda3.f$0 = this;
                                externalSyntheticLambda3.f$1 = function2;
                                VarHandle.storeStoreFence();
                                capsuleScrollerHorizontal.setPendingClickWhenFling(externalSyntheticLambda3);
                            } else {
                                if (Companion.isQuickImmerToImmer((ILockScreenExtStateController) this.stateController.get())) {
                                    CapsuleItemView baseCard = this.capsuleContainer.getAnimatorHelper().getAmbientState().getBaseCard();
                                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                        Log.d("SystemUi--Notification", "NotificationCapsuleContainerScroller-->".concat("handleItemClickEvent：immer to immer:" + baseCard));
                                    }
                                    if (baseCard != null && function3 != null) {
                                        ((AnonymousClass7) function3).invoke(baseCard);
                                    }
                                }
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                    Log.d("SystemUi--Notification", "NotificationCapsuleContainerScroller-->".concat("handleItemClickEvent：normalClick:" + capsuleItemView));
                                }
                                ((AnonymousClass6) function2).invoke(capsuleItemView);
                            }
                        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            Log.d("SystemUi--Notification", "NotificationCapsuleContainerScroller-->handleItemClickEvent：normalClick:null");
                        }
                    }
                }
                this.dataChangeInterceptor.unIntercept();
            }
        }
        getDragScaleHelper().onCapsuleDragEnd(motionEvent);
        resetEventStatus();
        this.capsuleContainer.getParent().requestDisallowInterceptTouchEvent(false);
        this.capsuleContainer.requestCancelTouchFeedback(true);
    }

    public final void horizontalScrollTo(CapsuleDataOrderHandler.CapsuleNextModel capsuleNextModel, float f) {
        this.hScroller.autoScrollTo(capsuleNextModel, f);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0049  */
    /* JADX WARN: Code duplicated, block: B:23:0x0061  */
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("CapsuleOnInterceptTouchEvent. action:", ViewExpandKtKt.getActionName(motionEvent), OplusPreventModeData.SEPARATOR, getBeingDragged(), "NotificationCapsuleContainerScroller-->"));
        }
        if (getBeingDragged() && motionEvent.getAction() == 2) {
            return true;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            DraggableStateHelper draggableStateHelper = DraggableStateHelper.INSTANCE;
            CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
            capsuleConfig.getClass();
            if (CapsuleConfig.isLightOs) {
                z = false;
            } else {
                FeatureOption.INSTANCE.getClass();
                if (FeatureOption.isTablet() || (FeatureOption.isLargeScreenFolding() && DeviceStateController.getInstance().isOpenOrHalfOpen())) {
                    z = false;
                } else {
                    KeyguardFeatureOption.INSTANCE.getClass();
                    if (KeyguardFeatureOption.isSupportUdfps() && FingerSwitchHelper.Companion.getInstance(this.context).isFingerprintUnlockEnable() && ((OplusBiometricAuthController) this.oplusBiometricAuthControllerLazy.get()).getUdfpsIconBottomPaddingStatic() < this.context.getResources().getDimensionPixelSize(2131165601)) {
                        z = false;
                    } else {
                        z = true;
                    }
                }
            }
            draggableStateHelper.setEnableKeyguardDrag(z);
            CapsuleItemView childAtPosition = ((ILockScreenExtStateController) this.stateController.get()).isStackState() ? null : this.capsuleContainer.getChildAtPosition(motionEvent.getX(), motionEvent.getY());
            if (childAtPosition == null) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (Companion.isQuickImmerToImmer((ILockScreenExtStateController) this.stateController.get())) {
                    NotificationCapsuleContainer notificationCapsuleContainer = this.capsuleContainer;
                    int measuredHeight = notificationCapsuleContainer.getMeasuredHeight() - notificationCapsuleContainer.getAnimatorHelper().getAmbientState().getMarginBottom();
                    int itemHeight = measuredHeight - capsuleConfig.getItemHeight();
                    if (y <= measuredHeight && y >= itemHeight) {
                        int measuredWidth = (notificationCapsuleContainer.getMeasuredWidth() - capsuleConfig.getItemWidth()) / 2;
                        int itemWidth = capsuleConfig.getItemWidth() + measuredWidth;
                        if (x >= measuredWidth && x <= itemWidth) {
                            childAtPosition = this.capsuleContainer.getAnimatorHelper().getAmbientState().getBaseCard();
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                Log.d("SystemUi--Notification", "NotificationCapsuleContainerScroller-->".concat("immerToImmer:" + childAtPosition));
                            }
                        }
                    }
                }
            }
            if (childAtPosition == null) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("ACTION_DOWN_OUT_SIDE_CHILD_AREA:", motionEvent.getX(), ", ", motionEvent.getY(), "NotificationCapsuleContainerScroller-->"));
                }
                this.draggerView = null;
            } else {
                resetEventStatus();
                this.draggerView = childAtPosition;
                this.mActivePointerId = motionEvent.getPointerId(0);
                if (!this.capsuleContainer.getAnimatorHelper().isCurrentlyAnimating()) {
                    this.capsuleContainer.requestTouchFeedback(childAtPosition);
                }
                this.motionTracker.initOrResetVelocityTracker(motionEvent);
                this.motionHandled = true;
                CapsuleScrollerHorizontal capsuleScrollerHorizontal = this.hScroller;
                capsuleScrollerHorizontal.ignoreHandScrollUntilPointerUp = false;
                capsuleScrollerHorizontal.updateDisableHScrollWhenDown();
                this.vScroller.onDownEvent(motionEvent);
                getDragScaleHelper().onCapsuleDragStart(motionEvent);
                this.capsuleContainer.getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (actionMasked == 1) {
            handleUpEvent(motionEvent, true, null, null, null, null);
        } else if (actionMasked == 2) {
            handleMoveEvent(motionEvent, true, null, null);
        } else if (actionMasked == 3) {
            handleUpEvent(motionEvent, true, null, null, null, null);
        }
        return getBeingDragged();
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00cf  */
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("CapsuleOnTouchEvent. action:", ViewExpandKtKt.getActionName(motionEvent), "NotificationCapsuleContainerScroller-->", "SystemUi--Notification");
        }
        boolean z = false;
        z = false;
        if (!this.motionHandled && motionEvent.getActionMasked() != 0) {
            this.motionTracker.recycleVelocityTracker();
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        int i = 1;
        if (actionMasked == 0) {
            if (this.draggerView == null) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (!((ILockScreenExtStateController) this.stateController.get()).isStackState() && this.capsuleContainer.getChildAtPosition(x, y) != null) {
                    z = true;
                }
            } else {
                z = true;
            }
            if (z) {
                this.mainHandler.removeCallbacks(this.delayResetDataInterceptTask);
                this.dataChangeInterceptor.intercept();
                this.capsuleContainerController.onTouchStart();
            }
            return z;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                this.mainHandler.removeCallbacks(this.delayResetDataInterceptTask);
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(1, this.hScroller, CapsuleScrollerHorizontal.class, "onHorizontalMove", "onHorizontalMove(F)V", 0);
                NotificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0 notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0 = new NotificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0(z ? 1 : 0);
                notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0.f$0 = this;
                notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0.f$1 = motionEvent;
                VarHandle.storeStoreFence();
                handleMoveEvent(motionEvent, false, anonymousClass2, notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0);
                return true;
            }
            if (actionMasked != 3) {
                return true;
            }
        }
        AnonymousClass4 anonymousClass4 = new AnonymousClass4(1, this.hScroller, CapsuleScrollerHorizontal.class, "onHorizontalUp", "onHorizontalUp(F)V", 0);
        NotificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0 notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda1 = new NotificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda0(i);
        notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda1.f$0 = this;
        notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda1.f$1 = motionEvent;
        VarHandle.storeStoreFence();
        handleUpEvent(motionEvent, false, anonymousClass4, notificationCapsuleContainerTouchHandler$$ExternalSyntheticLambda1, new AnonymousClass6(1, this.capsuleContainerController, NotificationCapsuleContainerControllerImpl.class, "onCapsuleClick", "onCapsuleClick(Lcom/oplus/systemui/notification/lockscreen/capsule/CapsuleItemView;)V", 0), new AnonymousClass7(1, this.capsuleContainerController, NotificationCapsuleContainerControllerImpl.class, "onImmerToImmerClick", "onImmerToImmerClick(Lcom/oplus/systemui/notification/lockscreen/capsule/CapsuleItemView;)V", 0));
        return true;
    }

    public final void publishUnInterceptTask() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationCapsuleContainerScroller-->publishUnInterceptTask");
        }
        this.mainHandler.postDelayed(this.delayResetDataInterceptTask, 100L);
    }

    public final void resetEventStatus() {
        this.mActivePointerId = -1;
        this.motionHandled = false;
        this.draggerView = null;
        LongClickHelper.INSTANCE.getClass();
        LongClickHelper.lastClickTime = SystemClock.uptimeMillis();
        DraggableStateHelper.INSTANCE.reset("resetEventStatus");
        this.motionTracker.recycleVelocityTracker();
    }
}
