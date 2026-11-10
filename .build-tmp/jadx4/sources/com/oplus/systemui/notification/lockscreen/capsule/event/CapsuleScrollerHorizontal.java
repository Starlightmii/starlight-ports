package com.oplus.systemui.notification.lockscreen.capsule.event;

import android.util.Log;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerTouchHandler;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.ContainerAnimatorViewHelper;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.LockScreenAmbientState;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataChangeInterceptor;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataOrderHandler;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.olivelink.CapsuleAnimParams;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleScrollerHorizontal {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleScrollerHorizontal";
    private Function0 cancelListener;
    private final NotificationCapsuleContainer capsuleContainer;
    private final CapsuleDataChangeInterceptor dataChangeInterceptor;
    public boolean disableHScrollWhenDown = true;
    private final COUIDynamicAnimation.OnAnimationEndListener endListener;
    public float finalXAmount;
    public volatile boolean ignoreHandScrollUntilPointerUp;
    private Function0 pendingClickWhenFling;
    public float scrollXAmount;
    private final COUISpringAnimation springAnimator;
    private final NotificationCapsuleContainerTouchHandler touchHandler;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleScrollerHorizontal(NotificationCapsuleContainerTouchHandler notificationCapsuleContainerTouchHandler, NotificationCapsuleContainer notificationCapsuleContainer, CapsuleDataChangeInterceptor capsuleDataChangeInterceptor) {
        this.touchHandler = notificationCapsuleContainerTouchHandler;
        this.capsuleContainer = notificationCapsuleContainer;
        this.dataChangeInterceptor = capsuleDataChangeInterceptor;
        CapsuleScrollerHorizontal$endListener$1 capsuleScrollerHorizontal$endListener$1 = new CapsuleScrollerHorizontal$endListener$1();
        capsuleScrollerHorizontal$endListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.endListener = capsuleScrollerHorizontal$endListener$1;
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        CapsuleAnimParams.INSTANCE.loadOLiveLinkParam(cOUISpringAnimation, CapsuleAnimParams.CapsuleScrollParams.INSTANCE.getHScrollParam());
        cOUISpringAnimation.setStartValue(0.0f);
        cOUISpringAnimation.setMinimumVisibleChange(1.0f);
        CapsuleScrollerHorizontal$springAnimator$1$1 capsuleScrollerHorizontal$springAnimator$1$1 = new CapsuleScrollerHorizontal$springAnimator$1$1();
        capsuleScrollerHorizontal$springAnimator$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(capsuleScrollerHorizontal$springAnimator$1$1);
        cOUISpringAnimation.addEndListener(capsuleScrollerHorizontal$endListener$1);
        this.springAnimator = cOUISpringAnimation;
    }

    private final LockScreenAmbientState getAnimState() {
        return getHostLayoutHelper().getAmbientState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ContainerAnimatorViewHelper getHostLayoutHelper() {
        return this.capsuleContainer.getAnimatorHelper();
    }

    public static /* synthetic */ void getSpringAnimator$annotations() {
    }

    public final void autoScrollTo(CapsuleDataOrderHandler.CapsuleNextModel capsuleNextModel, float f) {
        if (((COUIDynamicAnimation) this.springAnimator).mRunning || this.scrollXAmount != 0.0f || CapsuleViewExpandKtKt.isAppearAnimating(this.capsuleContainer)) {
            return;
        }
        this.dataChangeInterceptor.intercept();
        CapsuleDataOrderHandler.INSTANCE.setNextModel(capsuleNextModel);
        scrollToFinal(f, 0.0f);
    }

    public final void cancelScrollByImmersive() {
        COUISpringAnimation cOUISpringAnimation = this.springAnimator;
        if (((COUIDynamicAnimation) cOUISpringAnimation).mRunning && cOUISpringAnimation.canSkipToEnd()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("cancelScrollByImmersive:", this.finalXAmount, "CapsuleScrollerHorizontal-->", "SystemUi--Notification");
            }
            trySwitchBaseCard();
            this.cancelListener = null;
            this.pendingClickWhenFling = null;
            this.springAnimator.skipToEnd();
        }
    }

    public final Function0 getCancelListener() {
        return this.cancelListener;
    }

    public final Function0 getPendingClickWhenFling() {
        return this.pendingClickWhenFling;
    }

    public final COUISpringAnimation getSpringAnimator() {
        return this.springAnimator;
    }

    public final void onHorizontalMove(float f) {
        if (this.ignoreHandScrollUntilPointerUp) {
            return;
        }
        updateDisableHScrollWhenDown();
        if (this.disableHScrollWhenDown) {
            return;
        }
        COUISpringAnimation cOUISpringAnimation = this.springAnimator;
        if (!((COUIDynamicAnimation) cOUISpringAnimation).mRunning) {
            scrollToDiff(f);
            return;
        }
        CapsuleScrollerHorizontal$$ExternalSyntheticLambda0 capsuleScrollerHorizontal$$ExternalSyntheticLambda0 = new CapsuleScrollerHorizontal$$ExternalSyntheticLambda0();
        capsuleScrollerHorizontal$$ExternalSyntheticLambda0.f$0 = this;
        capsuleScrollerHorizontal$$ExternalSyntheticLambda0.f$1 = f;
        VarHandle.storeStoreFence();
        this.cancelListener = capsuleScrollerHorizontal$$ExternalSyntheticLambda0;
        cOUISpringAnimation.skipToEnd();
    }

    public final void onHorizontalUp(float f) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleScrollerHorizontal-->".concat("CapsuleEndHorizontalDrag, velocity=" + f + " "));
        }
        this.ignoreHandScrollUntilPointerUp = false;
        this.cancelListener = null;
        float f2 = this.scrollXAmount;
        if (f2 == 0.0f) {
            this.dataChangeInterceptor.unIntercept();
            return;
        }
        float nextScrollX = this.capsuleContainer.getNextScrollX(Float.valueOf(f2), f);
        DraggableStateHelper.INSTANCE.onHorizontalScrollFinished(nextScrollX, f);
        scrollToFinal(nextScrollX, f);
    }

    public final void scrollToDiff(float f) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("scrollToDiff: xDiff:", f, ", scrollXAmount:", this.scrollXAmount, "CapsuleScrollerHorizontal-->"));
        }
        float f2 = this.scrollXAmount + f;
        this.scrollXAmount = f2;
        DraggableStateHelper.INSTANCE.onHorizontalScroll(f2);
        this.capsuleContainer.customOverScrollToX((int) this.scrollXAmount);
    }

    public final void scrollToFinal(float f, float f2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("scrollToFinal:finalXAmount:", f, ", velocity:", f2, "CapsuleScrollerHorizontal-->"));
        }
        getAnimState().setHorizontalFling(true);
        this.finalXAmount = f;
        COUISpringAnimation cOUISpringAnimation = this.springAnimator;
        cOUISpringAnimation.setStartValue((int) this.scrollXAmount);
        cOUISpringAnimation.setStartVelocity(f2);
        cOUISpringAnimation.animateToFinalPosition((int) f);
    }

    public final void setCancelListener(Function0 function0) {
        this.cancelListener = function0;
    }

    public final void setPendingClickWhenFling(Function0 function0) {
        this.pendingClickWhenFling = function0;
    }

    public final void trySwitchBaseCard() {
        if (this.finalXAmount == 0.0f) {
            getAnimState().setNextCard(null);
            return;
        }
        this.finalXAmount = 0.0f;
        getAnimState().setBaseCard(getAnimState().getNextCard());
        getAnimState().setNextCard(null);
    }

    public final void updateDisableHScrollWhenDown() {
        this.disableHScrollWhenDown = getHostLayoutHelper().getStateAnimRunning() || getHostLayoutHelper().readyToAnimAddORemove() || CapsuleViewExpandKtKt.showOnlyOneCard(this.capsuleContainer) || this.capsuleContainer.isBindToStackView();
    }
}
