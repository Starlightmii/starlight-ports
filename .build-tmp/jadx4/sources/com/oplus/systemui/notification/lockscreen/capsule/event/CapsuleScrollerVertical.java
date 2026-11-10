package com.oplus.systemui.notification.lockscreen.capsule.event;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import androidx.compose.animation.core.MonoSpline$;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractorImpl;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.ContainerAnimatorViewHelper;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataChangeInterceptor;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl;
import com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController;
import com.oplus.systemui.notification.lockscreen.olivelink.CapsuleAnimParams;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateDecider;
import com.oplus.systemui.notification.lockscreen.statemanager.touch.ExtStateTouchHandlerEx;
import com.oplus.systemui.notification.lockscreen.statemanager.touch.ExtStateTouchSwipeTransition;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleScrollerVertical {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleScrollerVertical";
    private final NotificationCapsuleContainer capsuleContainer;
    private final NotificationCapsuleContainerControllerImpl capsuleContainerController;
    private final ExtStateTouchHandlerEx continuousSwipeHandler;
    private final CapsuleDataChangeInterceptor dataChangeInterceptor;
    public boolean disableVScrollWhenDown;
    private final ICapsuleDragEventHandler dragScaleHelper;
    private final LockScreenNotificationExtStateDecider extStateDecider;
    private final ExtStateTouchSwipeTransition extStateTouchSwipeTransition;
    private final CapsuleScrollerHorizontal hScroller;
    public boolean isDragger;
    public boolean isLightOsFling;
    public float lastVSpringVelocity;
    private final CapsuleLightOSController lightOSController;
    private final Lazy notificationCapsuleInteractor;
    public float scrollYAmount;
    private final Lazy stateController;
    private final COUISpringAnimation vSpringAnimator;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final float getSCROLL_UP_MAX_TRANSLATION() {
            return ExtStateAnimParams.StackToCapsule.INSTANCE.getData().getDRAG_MAX_ACTION().get().intValue();
        }
    }

    public CapsuleScrollerVertical(NotificationCapsuleContainer notificationCapsuleContainer, CapsuleDataChangeInterceptor capsuleDataChangeInterceptor, CapsuleScrollerHorizontal capsuleScrollerHorizontal, NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl, Lazy lazy, ICapsuleDragEventHandler iCapsuleDragEventHandler, Lazy lazy2, LockScreenNotificationExtStateDecider lockScreenNotificationExtStateDecider) {
        this.capsuleContainer = notificationCapsuleContainer;
        this.dataChangeInterceptor = capsuleDataChangeInterceptor;
        this.hScroller = capsuleScrollerHorizontal;
        this.capsuleContainerController = notificationCapsuleContainerControllerImpl;
        this.notificationCapsuleInteractor = lazy;
        this.dragScaleHelper = iCapsuleDragEventHandler;
        this.stateController = lazy2;
        this.extStateDecider = lockScreenNotificationExtStateDecider;
        this.lightOSController = notificationCapsuleContainer.getLightOSController();
        ExtStateTouchHandlerEx.ExtStateTouchHandlerType extStateTouchHandlerType = ExtStateTouchHandlerEx.ExtStateTouchHandlerType.CAPSULE;
        ExtStateTouchSwipeTransition extStateTouchSwipeTransition = new ExtStateTouchSwipeTransition(extStateTouchHandlerType, (ILockScreenExtStateController) lazy2.get(), (ImmersiveCardControl) DependencyEx.sDependency.getDependency(ImmersiveCardControl.class), new CapsuleScrollerVertical$$ExternalSyntheticLambda0(), lockScreenNotificationExtStateDecider);
        this.extStateTouchSwipeTransition = extStateTouchSwipeTransition;
        this.continuousSwipeHandler = new ExtStateTouchHandlerEx(extStateTouchHandlerType, (ILockScreenExtStateController) lazy2.get(), extStateTouchSwipeTransition);
        this.disableVScrollWhenDown = true;
        this.lastVSpringVelocity = Float.NaN;
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        Spring vScrollBackParam = CapsuleAnimParams.CapsuleScrollParams.INSTANCE.getVScrollBackParam();
        cOUISpringAnimation.setSpring(new COUISpringForce().setResponse(vScrollBackParam.response()).setBounce(vScrollBackParam.bounce()));
        cOUISpringAnimation.setStartValue(0.0f);
        cOUISpringAnimation.setMinimumVisibleChange(1.0f);
        CapsuleScrollerVertical$vSpringAnimator$1$1 capsuleScrollerVertical$vSpringAnimator$1$1 = new CapsuleScrollerVertical$vSpringAnimator$1$1();
        capsuleScrollerVertical$vSpringAnimator$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(capsuleScrollerVertical$vSpringAnimator$1$1);
        CapsuleScrollerVertical$vSpringAnimator$1$2 capsuleScrollerVertical$vSpringAnimator$1$2 = new CapsuleScrollerVertical$vSpringAnimator$1$2();
        capsuleScrollerVertical$vSpringAnimator$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(capsuleScrollerVertical$vSpringAnimator$1$2);
        this.vSpringAnimator = cOUISpringAnimation;
    }

    public static final void access$scrollToDiff(CapsuleScrollerVertical capsuleScrollerVertical, float f) {
        float f2 = f / (-CapsuleConfig.INSTANCE.getCapsuleDragLimit());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("scrollToDiff: ", f, ", overLimitProgress:", f2, "CapsuleScrollerVertical-->"));
        }
        if (f2 < 1.0f || f >= 0.0f) {
            capsuleScrollerVertical.capsuleContainerController.customOverScrollToY(f);
            DraggableStateHelper.INSTANCE.onVerticalScroll(f2);
            capsuleScrollerVertical.lightOSController.onCapsuleContainerDrag(f2);
        } else {
            capsuleScrollerVertical.vSpringAnimator.cancelComplete();
            capsuleScrollerVertical.exchangeToStack(capsuleScrollerVertical.lastVSpringVelocity);
            capsuleScrollerVertical.lightOSController.onCapsuleContainerFling(0.0f);
            DraggableStateHelper.INSTANCE.onVerticalScrollFinished(0.0f);
        }
    }

    private final Context getContext() {
        return this.capsuleContainer.getContext();
    }

    private final ContainerAnimatorViewHelper getHostLayoutHelper() {
        return this.capsuleContainer.getAnimatorHelper();
    }

    private final void setDragger(boolean z) {
        if (this.isDragger != z) {
            this.isDragger = z;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("isDragger changed to ", "CapsuleScrollerVertical-->", "SystemUi--Notification", z);
            }
            CapsuleAnimParams.INSTANCE.loadOLiveLinkParam(this.vSpringAnimator, z ? ExtStateAnimParams.DraggerParams.INSTANCE.getCapsule().getSPING() : CapsuleAnimParams.CapsuleScrollParams.INSTANCE.getVScrollBackParam());
        }
    }

    public final void exchangeToStack(float f) {
        this.dragScaleHelper.onCapsuleDragExchangeToStack();
        this.capsuleContainerController.onTopStackMax(f);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("exchangeToStack: onTopStackMax, v=", f, "CapsuleScrollerVertical-->", "SystemUi--Notification");
        }
    }

    public final void onDownEvent(MotionEvent motionEvent) {
        this.continuousSwipeHandler.onTouchEvent(motionEvent);
        this.disableVScrollWhenDown = getHostLayoutHelper().getStateAnimRunning() || getHostLayoutHelper().readyToAnimAddORemove();
    }

    public final void onVerticalFlingEnd() {
        this.dataChangeInterceptor.unIntercept();
        this.scrollYAmount = 0.0f;
    }

    public final void onVerticalMove(MotionEvent motionEvent, float f) {
        if (this.disableVScrollWhenDown || this.continuousSwipeHandler.onTouchEvent(motionEvent)) {
            return;
        }
        if (!this.isDragger) {
            setDragger(true);
            this.vSpringAnimator.cancelComplete();
            this.vSpringAnimator.setStartValue(-this.capsuleContainer.getStackY());
            this.lastVSpringVelocity = Float.NaN;
            this.scrollYAmount = 0.0f;
            this.isLightOsFling = false;
        }
        if (!((COUIDynamicAnimation) this.hScroller.getSpringAnimator()).mRunning) {
            if (this.capsuleContainer.isBindToStackView()) {
                return;
            }
            scrollTo(f);
            return;
        }
        this.hScroller.trySwitchBaseCard();
        CapsuleScrollerHorizontal capsuleScrollerHorizontal = this.hScroller;
        CapsuleScrollerVertical$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new CapsuleScrollerVertical$.ExternalSyntheticLambda1();
        externalSyntheticLambda1.f$0 = this;
        externalSyntheticLambda1.f$1 = f;
        VarHandle.storeStoreFence();
        capsuleScrollerHorizontal.setCancelListener(externalSyntheticLambda1);
        this.hScroller.getSpringAnimator().skipToEnd();
    }

    public final void onVerticalUp(MotionEvent motionEvent, float f) {
        ((OplusNotificationCapsuleInteractorImpl) this.notificationCapsuleInteractor.get()).onUpEvent(ExtStateTouchHandlerEx.ExtStateTouchHandlerType.CAPSULE.getTag());
        boolean z = false;
        setDragger(false);
        this.isLightOsFling = false;
        if (this.disableVScrollWhenDown || this.continuousSwipeHandler.onTouchEvent(motionEvent)) {
            onVerticalFlingEnd();
            this.capsuleContainerController.customOverScrollToY(0.0f);
            return;
        }
        float stackY = this.capsuleContainer.getStackY();
        float f2 = (0.03f * f) + stackY;
        if (Math.abs(f) > 1000.0f && f2 < 0.0f && Math.abs(f2) >= Companion.getSCROLL_UP_MAX_TRANSLATION()) {
            z = true;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("CapsuleScrollerVertical-->", "SystemUi--Notification", MonoSpline$.ExternalSyntheticOutline0.m("onVerticalUp: v:", f, ", stackY:", stackY, ", isFastScrollUp: "), z);
        }
        if (z) {
            CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
            capsuleConfig.getClass();
            if (CapsuleConfig.isLightOs) {
                this.isLightOsFling = true;
                CapsuleAnimParams.INSTANCE.loadOLiveLinkParam(this.vSpringAnimator, ExtStateAnimParams.DraggerParams.INSTANCE.getCapsule().getSPING());
                this.vSpringAnimator.animateToFinalPosition(-capsuleConfig.getCapsuleDragLimit());
            } else {
                this.vSpringAnimator.cancelComplete();
                onVerticalFlingEnd();
                exchangeToStack(this.lastVSpringVelocity);
                this.lightOSController.onCapsuleContainerFling(0.0f);
            }
        } else {
            this.vSpringAnimator.setStartValue(-stackY);
            CapsuleAnimParams.INSTANCE.loadOLiveLinkParam(this.vSpringAnimator, CapsuleAnimParams.CapsuleScrollParams.INSTANCE.getVScrollBackParam());
            this.vSpringAnimator.animateToFinalPosition(0.0f);
            this.lightOSController.onCapsuleContainerFling(1.0f);
        }
        DraggableStateHelper.INSTANCE.onVerticalScrollFinished(f);
    }

    public final void scrollTo(float f) {
        this.scrollYAmount += f;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("scrollTo: yDiff:", f, ", scrollYAmount:", this.scrollYAmount, "CapsuleScrollerVertical-->"));
        }
        this.vSpringAnimator.animateToFinalPosition(this.scrollYAmount);
    }
}
