package com.oplus.systemui.notification.lockscreen.stack;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import androidx.compose.animation.core.MonoSpline$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.data.repository.ShadeRepository;
import com.android.systemui.statusbar.OplusLockscreenNotificationDragHelperEx;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.MediaContainerView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.util.DimensionKt;
import com.oplus.systemui.keyguard.helper.OplusKeyguardBottomAreaColorHelper;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractorImpl;
import com.oplus.systemui.notification.lockscreen.capsule.event.DraggableStateHelper;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataChangeInterceptor;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateDecider;
import com.oplus.systemui.notification.lockscreen.statemanager.touch.ExtStateTouchHandlerEx;
import com.oplus.systemui.notification.lockscreen.statemanager.touch.ExtStateTouchSwipeTransition;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.util.LockscreenNotificationNsslSlideHotArea;
import com.oplusos.systemui.common.sample.DisplacementThresholdSampler;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockscreenNotificationDragHelperExImpl extends OplusLockscreenNotificationDragHelperEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusLockscreenNotificationDragHelperEx";
    private final Lazy capsuleDataScreenMonitor;
    private final Lazy context;
    private final ExtStateTouchHandlerEx continuousSwipeHandler;
    private final CapsuleDataChangeInterceptor dataChangeInterceptor;
    private final kotlin.Lazy displacementThresholdSampler$delegate;
    public float dragAmountOnStart;
    private final LockScreenNotificationExtStateDecider extStateDecider;
    private final ExtStateTouchSwipeTransition extStateTouchSwipeTransition;
    private final kotlin.Lazy hotAreaUtil$delegate;
    private final Lazy immersiveCardControl;
    public float initialTouchX;
    public float initialTouchY;
    public boolean isTrackingTouch;
    private final Lazy lockScreenExtStateController;
    private final CoroutineScope mainScope;
    private final Lazy notificationCapsuleInteractor;
    private final Lazy notificationKeyguardHelper;
    private Lazy notificationPanelViewController;
    private final Lazy nsslController;
    private final Lazy oplusKeyguardBottomAreaColorHelper;
    private final OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionController;
    private final Lazy shadeRepository;
    public float slopMultiplier;
    private final Lazy statusBarKeyguardViewManager;
    private final Lazy statusBarStateController;
    public float touchSlop;
    public final VelocityTracker velocityTracker = VelocityTracker.obtain(0);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Unit $r8$lambda$PqQ3A54dVPZ35E7MOUP1V8EtFvw(LockscreenNotificationDragHelperExImpl lockscreenNotificationDragHelperExImpl, float f) {
        lockscreenNotificationDragHelperExImpl.oplusLockscreenShadeTransitionController.setNotificationDragAmount(f);
        return Unit.INSTANCE;
    }

    public static boolean $r8$lambda$_IksZ1viw9UXBM32cgYFdHQC43c(LockscreenNotificationDragHelperExImpl lockscreenNotificationDragHelperExImpl) {
        return ((SysuiStatusBarStateController) lockscreenNotificationDragHelperExImpl.statusBarStateController.get()).getState() == 1 && !lockscreenNotificationDragHelperExImpl.oplusLockscreenShadeTransitionController.isAnimRunning();
    }

    /* JADX INFO: renamed from: $r8$lambda$wN4fW_0mA70jWqT7u4JJQE-r7BU, reason: not valid java name */
    public static LockscreenNotificationNsslSlideHotArea m91$r8$lambda$wN4fW_0mA70jWqT7u4JJQEr7BU(LockscreenNotificationDragHelperExImpl lockscreenNotificationDragHelperExImpl) {
        return new LockscreenNotificationNsslSlideHotArea((Context) lockscreenNotificationDragHelperExImpl.context.get(), (NotificationStackScrollLayoutController) lockscreenNotificationDragHelperExImpl.nsslController.get(), (ILockScreenExtStateController) lockscreenNotificationDragHelperExImpl.lockScreenExtStateController.get(), (OplusKeyguardBottomAreaColorHelper) lockscreenNotificationDragHelperExImpl.oplusKeyguardBottomAreaColorHelper.get());
    }

    public LockscreenNotificationDragHelperExImpl(OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, Lazy lazy11, Lazy lazy12, LockScreenNotificationExtStateDecider lockScreenNotificationExtStateDecider, CoroutineScope coroutineScope) {
        this.oplusLockscreenShadeTransitionController = oplusLockscreenShadeTransitionControllerEx;
        this.lockScreenExtStateController = lazy;
        this.shadeRepository = lazy2;
        this.statusBarStateController = lazy3;
        this.context = lazy4;
        this.statusBarKeyguardViewManager = lazy5;
        this.nsslController = lazy6;
        this.notificationPanelViewController = lazy7;
        this.oplusKeyguardBottomAreaColorHelper = lazy8;
        this.notificationKeyguardHelper = lazy9;
        this.notificationCapsuleInteractor = lazy10;
        this.immersiveCardControl = lazy11;
        this.capsuleDataScreenMonitor = lazy12;
        this.extStateDecider = lockScreenNotificationExtStateDecider;
        this.mainScope = coroutineScope;
        ExtStateTouchHandlerEx.ExtStateTouchHandlerType extStateTouchHandlerType = ExtStateTouchHandlerEx.ExtStateTouchHandlerType.STACK;
        ILockScreenExtStateController iLockScreenExtStateController = (ILockScreenExtStateController) lazy.get();
        ImmersiveCardControl immersiveCardControl = (ImmersiveCardControl) lazy11.get();
        LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0 lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0 = new LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0(0);
        lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        ExtStateTouchSwipeTransition extStateTouchSwipeTransition = new ExtStateTouchSwipeTransition(extStateTouchHandlerType, iLockScreenExtStateController, immersiveCardControl, lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0, lockScreenNotificationExtStateDecider);
        this.extStateTouchSwipeTransition = extStateTouchSwipeTransition;
        this.continuousSwipeHandler = new ExtStateTouchHandlerEx(extStateTouchHandlerType, (ILockScreenExtStateController) lazy.get(), extStateTouchSwipeTransition);
        CapsuleDataChangeInterceptor capsuleDataChangeInterceptor = new CapsuleDataChangeInterceptor("LockscreenNotificationDragHelperExImpl");
        this.dataChangeInterceptor = capsuleDataChangeInterceptor;
        LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0 lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda1 = new LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0(1);
        lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.displacementThresholdSampler$delegate = LazyKt__LazyJVMKt.lazy(lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda1);
        LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0 lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda2 = new LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0(2);
        lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.hotAreaUtil$delegate = LazyKt__LazyJVMKt.lazy(lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda2);
        updateResources((Context) lazy4.get());
        capsuleDataChangeInterceptor.register();
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new LockscreenNotificationDragHelperExImpl$observeCapsuleScreenState$1(this, null), 3);
    }

    private final DisplacementThresholdSampler getDisplacementThresholdSampler() {
        return (DisplacementThresholdSampler) this.displacementThresholdSampler$delegate.getValue();
    }

    private final LockscreenNotificationNsslSlideHotArea getHotAreaUtil() {
        return (LockscreenNotificationNsslSlideHotArea) this.hotAreaUtil$delegate.getValue();
    }

    public final void addMovement$1(MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        motionEvent.offsetLocation(rawX, rawY);
        this.velocityTracker.addMovement(motionEvent);
        motionEvent.offsetLocation(-rawX, -rawY);
    }

    public final void goToExtStateOnSlide(int i, int i2, String str) {
        this.oplusLockscreenShadeTransitionController.setNotificationDragAmount(0.0f);
        ILockScreenExtStateController iLockScreenExtStateController = (ILockScreenExtStateController) this.lockScreenExtStateController.get();
        ILockScreenExtStateController.Companion companion = ILockScreenExtStateController.Companion;
        iLockScreenExtStateController.goToExtState(true, i, str, i2, null);
    }

    public final boolean isTrackingTouch() {
        return this.isTrackingTouch;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean zIsInSlideDownHotArea = false;
        if (((SysuiStatusBarStateController) this.statusBarStateController.get()).getState() != 0) {
            if (((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isLockScreenNtfCenterState() && !this.oplusLockscreenShadeTransitionController.isAnimRunning()) {
                setTrackingTouch(false);
                return false;
            }
            if (((StatusBarKeyguardViewManager) this.statusBarKeyguardViewManager.get()).getQsExpansion() <= 0.0f && !((NotificationPanelViewController) this.notificationPanelViewController.get()).mSplitShadeEnabled) {
                Pair pair = TuplesKt.to(Float.valueOf(motionEvent.getRawX()), Float.valueOf(motionEvent.getRawY()));
                float fFloatValue = ((Number) pair.component1()).floatValue();
                float fFloatValue2 = ((Number) pair.component2()).floatValue();
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked == 0) {
                    setTrackingTouch(false);
                    this.initialTouchY = fFloatValue2;
                    this.initialTouchX = fFloatValue;
                    this.velocityTracker.clear();
                    addMovement$1(motionEvent);
                    this.oplusLockscreenShadeTransitionController.getAnimBreaker().onTouchEvent(motionEvent, "OplusLockscreenNotificationDragHelperEx-onInterceptTouchEvent_ACTION_DOWN");
                    this.continuousSwipeHandler.onTouchEvent(motionEvent);
                    DisplacementThresholdSampler displacementThresholdSampler = getDisplacementThresholdSampler();
                    float notificationDragAmount = this.oplusLockscreenShadeTransitionController.getNotificationDragAmount();
                    displacementThresholdSampler.lastNotifiedDisplacementInternal = notificationDragAmount;
                    displacementThresholdSampler.previousRawDisplacement = notificationDragAmount;
                    displacementThresholdSampler.sameDirection = 0.0f;
                    return false;
                }
                if (actionMasked == 2) {
                    float f = fFloatValue2 - this.initialTouchY;
                    if ((f >= 0.0f || !((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isImmersiveState() || ((ImmersiveCardControl) this.immersiveCardControl.get()).isTouchWithinFocusCard(this.initialTouchX, this.initialTouchY)) && !((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isCapsuleState()) {
                        int classification = motionEvent.getClassification();
                        float f2 = this.touchSlop;
                        if (classification == 1) {
                            f2 *= this.slopMultiplier;
                        }
                        if (Math.abs(f) > f2 && Math.abs(f) > Math.abs(fFloatValue - this.initialTouchX)) {
                            float f3 = this.initialTouchX;
                            float f4 = this.initialTouchY;
                            boolean z = f < 0.0f;
                            if (!((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isImmersiveState()) {
                                List<ExpandableView> visibleChildren = ((NotificationStackScrollLayoutController) this.nsslController.get()).getView().getVisibleChildren();
                                if (!(visibleChildren instanceof Collection) || !visibleChildren.isEmpty()) {
                                    for (ExpandableView expandableView : visibleChildren) {
                                        if ((expandableView instanceof OplusCustomRow) || (expandableView instanceof ExpandableNotificationRow) || (expandableView instanceof MediaContainerView)) {
                                            if (!z) {
                                                zIsInSlideDownHotArea = getHotAreaUtil().isInSlideDownHotArea(f3, f4);
                                                break;
                                            }
                                            zIsInSlideDownHotArea = getHotAreaUtil().isInSlideUpHotArea(f3, f4);
                                            break;
                                        }
                                    }
                                }
                            } else {
                                zIsInSlideDownHotArea = getHotAreaUtil().isTouchInNsslHotAreaForImmersiveState(f3, f4);
                            }
                            if (zIsInSlideDownHotArea) {
                                this.dataChangeInterceptor.intercept();
                                setTrackingTouch(true);
                                this.initialTouchY = fFloatValue2;
                                this.initialTouchX = fFloatValue;
                                this.dragAmountOnStart = this.oplusLockscreenShadeTransitionController.getNotificationDragAmount();
                            }
                        }
                    }
                }
            }
        }
        return zIsInSlideDownHotArea;
    }

    public final boolean onSlideToDown(float f, VelocityTracker velocityTracker, String str) {
        velocityTracker.computeCurrentVelocity(1000);
        float yVelocity = velocityTracker.getYVelocity();
        if (f <= DimensionKt.dpToPx(ExtStateAnimParams.DraggerParams.INSTANCE.getStack().getMAX_ACTION().get(), (Context) this.context.get()) && yVelocity <= 1500.0f) {
            return false;
        }
        int newState = ((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getExtStateEvent().getValue()).getNewState();
        int iOnNsslSlideToDown = this.extStateDecider.onNsslSlideToDown(newState);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(velocityTracker.getYVelocity(), newState, "onSlideToDown, yVelocity ", ", current state = ", ", from = ");
            sbM.append(str);
            sbM.append(", target state = ");
            sbM.append(iOnNsslSlideToDown);
            Log.d("SystemUi--Notification", "OplusLockscreenNotificationDragHelperEx-->".concat(sbM.toString()));
        }
        goToExtStateOnSlide(iOnNsslSlideToDown, 101, "onSlideToDown-".concat(str));
        ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType animateType = ((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getExtStateEvent().getValue()).getAnimateType();
        animateType.getClass();
        if (animateType != ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.STACK_TO_CAPSULE && animateType != ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.SINKING_TO_CAPSULE) {
            return true;
        }
        DraggableStateHelper.INSTANCE.oneShotStackToCapsule();
        return true;
    }

    public final boolean onSlideToUp(float f, VelocityTracker velocityTracker, String str) {
        velocityTracker.computeCurrentVelocity(1000);
        if ((-f) <= CapsuleConfig.INSTANCE.getStackUpDragLimit() && velocityTracker.getYVelocity() >= -1500.0f) {
            return false;
        }
        ILockScreenExtStateController iLockScreenExtStateController = (ILockScreenExtStateController) this.lockScreenExtStateController.get();
        int newState = ((ILockScreenExtStateController.ExtStateEvent) iLockScreenExtStateController.getExtStateEvent().getValue()).getNewState();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("onSlideToUp dragAmount ", f, ", yVelocity ", velocityTracker.getYVelocity(), ", current state = ");
            sbM.append(newState);
            sbM.append(", from = ");
            sbM.append(str);
            Log.d("SystemUi--Notification", "OplusLockscreenNotificationDragHelperEx-->".concat(sbM.toString()));
        }
        if (iLockScreenExtStateController.isCapsuleState()) {
            Integer numOnNsslSlideToUp = this.extStateDecider.onNsslSlideToUp(newState);
            goToExtStateOnSlide(numOnNsslSlideToUp != null ? numOnNsslSlideToUp.intValue() : 0, 111, "onSlideToUp-".concat(str));
            return true;
        }
        if (((ILockScreenExtStateController.ExtStateEvent) iLockScreenExtStateController.getExtStateEvent().getValue()).getNewState() != 3) {
            if (!iLockScreenExtStateController.isTopStackState()) {
                if (!iLockScreenExtStateController.isImmersiveState()) {
                    stopDragging$1();
                    return true;
                }
                Integer numOnNsslSlideToUp2 = this.extStateDecider.onNsslSlideToUp(newState);
                if (numOnNsslSlideToUp2 != null) {
                    goToExtStateOnSlide(numOnNsslSlideToUp2.intValue(), 111, "onSlideToUp-".concat(str));
                }
                return true;
            }
            if (this.extStateDecider.onNsslSlideToUp(newState) == null) {
                if (str.equals("move")) {
                    return false;
                }
                stopDragging$1();
                return true;
            }
            String strConcat = "onSlideToUp-".concat(str);
            float yVelocity = velocityTracker.getYVelocity();
            this.oplusLockscreenShadeTransitionController.setNotificationDragAmount(0.0f);
            ILockScreenExtStateController.tryGotoShadeLockState$default((ILockScreenExtStateController) this.lockScreenExtStateController.get(), strConcat, yVelocity, null, 24);
            stopDragging$1();
            return true;
        }
        boolean hasNotificationStacked = ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getHasNotificationStacked();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusLockscreenNotificationDragHelperEx-->".concat("[handleSlideToUpOnSinkingStackState] hasNotificationStacked = " + hasNotificationStacked + " "));
        }
        Integer numOnNsslSlideToUp3 = this.extStateDecider.onNsslSlideToUp(newState);
        if (numOnNsslSlideToUp3 == null) {
            if (str.equals("move")) {
                return false;
            }
            stopDragging$1();
            return true;
        }
        if (numOnNsslSlideToUp3.intValue() != 10) {
            goToExtStateOnSlide(numOnNsslSlideToUp3.intValue(), 111, "onSlideToUp-".concat(str));
            return true;
        }
        String strConcat2 = "onSlideToUp-".concat(str);
        float yVelocity2 = velocityTracker.getYVelocity();
        this.oplusLockscreenShadeTransitionController.setNotificationDragAmount(0.0f);
        ILockScreenExtStateController.tryGotoShadeLockState$default((ILockScreenExtStateController) this.lockScreenExtStateController.get(), strConcat2, yVelocity2, null, 24);
        stopDragging$1();
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if ((!((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isLockScreenNtfCenterState() || this.oplusLockscreenShadeTransitionController.isAnimRunning()) && this.isTrackingTouch) {
            if (this.oplusLockscreenShadeTransitionController.isAnimRunning()) {
                this.oplusLockscreenShadeTransitionController.getAnimBreaker().onTouchEvent(motionEvent, "OplusLockscreenNotificationDragHelperEx-onTouchEvent");
                return false;
            }
            float rawY = motionEvent.getRawY();
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 1) {
                addMovement$1(motionEvent);
                if (!this.continuousSwipeHandler.onTouchEvent(motionEvent)) {
                    float f = rawY - this.initialTouchY;
                    VelocityTracker velocityTracker = this.velocityTracker;
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onTouchUp dragAmount " + f + ", yVelocity " + velocityTracker.getYVelocity(), null, 8);
                    if (!onSlideToUp(f, velocityTracker, "up") && !onSlideToDown(f, velocityTracker, "up")) {
                        this.oplusLockscreenShadeTransitionController.setNotificationDragAmount(0.0f);
                    }
                }
                stopDragging$1();
                ((OplusNotificationCapsuleInteractorImpl) this.notificationCapsuleInteractor.get()).onUpEvent(ExtStateTouchHandlerEx.ExtStateTouchHandlerType.STACK.getTag());
                return true;
            }
            if (actionMasked == 2) {
                if (this.continuousSwipeHandler.onTouchEvent(motionEvent)) {
                    return true;
                }
                float f2 = (rawY - this.initialTouchY) + this.dragAmountOnStart;
                if (f2 < 0.0f) {
                    ILockScreenExtStateController iLockScreenExtStateController = (ILockScreenExtStateController) this.lockScreenExtStateController.get();
                    if (!iLockScreenExtStateController.getHasNotificationStacked() && (((ILockScreenExtStateController.ExtStateEvent) iLockScreenExtStateController.getExtStateEvent().getValue()).getNewState() == 3 || iLockScreenExtStateController.isTopStackState())) {
                        float f3 = -CapsuleConfig.INSTANCE.getStackUpDragLimit();
                        if (f2 < f3) {
                            f2 = f3;
                        }
                    }
                }
                getDisplacementThresholdSampler().sample(f2);
                VelocityTracker velocityTracker2 = this.velocityTracker;
                if (!onSlideToUp(f2, velocityTracker2, "move")) {
                    onSlideToDown(f2, velocityTracker2, "move");
                }
                addMovement$1(motionEvent);
                return true;
            }
            if (actionMasked == 3) {
                stopDragging$1();
                return false;
            }
        }
        return false;
    }

    public void setTrackingTouch(boolean z) {
        if (this.isTrackingTouch != z) {
            this.isTrackingTouch = z;
            ((ShadeRepository) this.shadeRepository.get()).setLegacyLockscreenShadeTracking(this.isTrackingTouch);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("isTrackingTouch = ", "OplusLockscreenNotificationDragHelperEx-->", "SystemUi--Notification", z);
            }
        }
    }

    public final void stopDragging$1() {
        this.dataChangeInterceptor.unIntercept();
        setTrackingTouch(false);
        this.velocityTracker.clear();
        this.initialTouchX = 0.0f;
        this.initialTouchY = 0.0f;
        this.continuousSwipeHandler.reset();
        DisplacementThresholdSampler.flush$default(getDisplacementThresholdSampler(), 0.0f, 3);
        this.oplusLockscreenShadeTransitionController.setNotificationDragAmount(0.0f);
    }

    public final void updateResources(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.touchSlop = viewConfiguration.getScaledTouchSlop();
        this.slopMultiplier = viewConfiguration.getScaledAmbiguousGestureMultiplier();
    }
}
