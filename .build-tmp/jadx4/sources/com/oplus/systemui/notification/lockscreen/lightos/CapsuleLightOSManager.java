package com.oplus.systemui.notification.lockscreen.lightos;

import android.util.Log;
import android.util.MathUtils;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.AnimatableProperty;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.config.CapsuleDebug;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplus.systemui.notification.lockscreen.effect.OplusPhysicsPropertyAnimatorExtKt;
import com.oplus.systemui.notification.lockscreen.olivelink.CapsuleAnimParams;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateManager;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleLightOSManager implements CapsuleLightOSController {
    public static final Companion Companion = new Companion();
    public static final String TAG = "CapsuleListOSManager";
    private final Lazy capsuleNSSLController;
    private final kotlin.Lazy capsuleNSSl$delegate;
    public float capsuleProgress;
    private final COUISpringAnimation flingFadeoutAnimator;
    public boolean isKeyguardState;
    private final LockScreenNotificationExtStateManager lockScreenExtStateController;
    private final AnimatableProperty stackLightOSTransYProperty;
    private final Lazy stackNSSLController;
    private final kotlin.Lazy stackNSSl$delegate;
    public float stackStartAppearProgress;
    public float stackTranslationYState;
    private final Lazy statusBarStateController;
    private final LightOSViewState viewState;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LightOSViewState extends ViewState {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSManager$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements StatusBarStateController.StateListener {
        public /* synthetic */ CapsuleLightOSManager this$0;

        public final void onStateChanged(int i) {
            CapsuleLightOSManager capsuleLightOSManager = this.this$0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "CapsuleLightOSManagerOnStateChanged:", "CapsuleListOSManager-->", "SystemUi--Notification");
            }
            if (i == 0) {
                capsuleLightOSManager.isKeyguardState = false;
                capsuleLightOSManager.resetLightOSViewState();
            } else {
                if (i != 1) {
                    return;
                }
                capsuleLightOSManager.isKeyguardState = true;
            }
        }
    }

    public CapsuleLightOSManager(Lazy lazy, LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager, Lazy lazy2, Lazy lazy3) {
        this.statusBarStateController = lazy;
        this.lockScreenExtStateController = lockScreenNotificationExtStateManager;
        this.capsuleNSSLController = lazy2;
        this.stackNSSLController = lazy3;
        CapsuleLightOSManager$$ExternalSyntheticLambda0 capsuleLightOSManager$$ExternalSyntheticLambda0 = new CapsuleLightOSManager$$ExternalSyntheticLambda0(0);
        capsuleLightOSManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.stackNSSl$delegate = LazyKt__LazyJVMKt.lazy(capsuleLightOSManager$$ExternalSyntheticLambda0);
        CapsuleLightOSManager$$ExternalSyntheticLambda0 capsuleLightOSManager$$ExternalSyntheticLambda1 = new CapsuleLightOSManager$$ExternalSyntheticLambda0(1);
        capsuleLightOSManager$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.capsuleNSSl$delegate = LazyKt__LazyJVMKt.lazy(capsuleLightOSManager$$ExternalSyntheticLambda1);
        this.viewState = new LightOSViewState(true);
        CapsuleLightOSManager$stackLightOSTransYProperty$1 capsuleLightOSManager$stackLightOSTransYProperty$1 = new CapsuleLightOSManager$stackLightOSTransYProperty$1();
        capsuleLightOSManager$stackLightOSTransYProperty$1.this$0 = this;
        VarHandle.storeStoreFence();
        CapsuleLightOSManager$stackLightOSTransYProperty$2 capsuleLightOSManager$stackLightOSTransYProperty$2 = new CapsuleLightOSManager$stackLightOSTransYProperty$2();
        capsuleLightOSManager$stackLightOSTransYProperty$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.stackLightOSTransYProperty = AnimatableProperty.from("stackLightOSTransYProperty", capsuleLightOSManager$stackLightOSTransYProperty$1, capsuleLightOSManager$stackLightOSTransYProperty$2, 2131363727, 2131363726, 2131363725);
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        Spring scrollUpFadeoutParam = ExtStateAnimParams.LightOSAnimParams.INSTANCE.getScrollUpFadeoutParam();
        cOUISpringAnimation.setSpring(new COUISpringForce().setResponse(scrollUpFadeoutParam.response()).setBounce(scrollUpFadeoutParam.bounce()));
        cOUISpringAnimation.setStartValue(0.0f);
        cOUISpringAnimation.setMinimumVisibleChange(0.01f);
        CapsuleLightOSManager$flingFadeoutAnimator$1$1 capsuleLightOSManager$flingFadeoutAnimator$1$1 = new CapsuleLightOSManager$flingFadeoutAnimator$1$1();
        capsuleLightOSManager$flingFadeoutAnimator$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(capsuleLightOSManager$flingFadeoutAnimator$1$1);
        CapsuleLightOSManager$flingFadeoutAnimator$1$2 capsuleLightOSManager$flingFadeoutAnimator$1$2 = new CapsuleLightOSManager$flingFadeoutAnimator$1$2();
        capsuleLightOSManager$flingFadeoutAnimator$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(capsuleLightOSManager$flingFadeoutAnimator$1$2);
        this.flingFadeoutAnimator = cOUISpringAnimation;
    }

    public static final void access$updateStackNSSLTranslationY(CapsuleLightOSManager capsuleLightOSManager, float f) {
        if (capsuleLightOSManager.isKeyguardState) {
            if (CapsuleDebug.INSTANCE.getLogSwitchItemViewUpdate() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("updateStackNSSLTranslationY:", f, "CapsuleListOSManager-->", "SystemUi--Notification");
            }
            capsuleLightOSManager.getStackNSSl().setTranslationYForLightOSCapsule(f);
        }
    }

    private final NotificationCapsuleContainer getCapsuleNSSl() {
        return (NotificationCapsuleContainer) this.capsuleNSSl$delegate.getValue();
    }

    private final NotificationStackScrollLayout getStackNSSl() {
        return (NotificationStackScrollLayout) this.stackNSSl$delegate.getValue();
    }

    public final void animateStackTranslationYIfChanged(boolean z) {
        float fFloatValue = z ? ExtStateAnimParams.LightOSAnimParams.INSTANCE.getSTACK_MAX_TRANSLATION_Y().get().floatValue() : 0.0f;
        ExtStateAnimParams.LightOSAnimParams lightOSAnimParams = ExtStateAnimParams.LightOSAnimParams.INSTANCE;
        OplusPhysicsPropertyAnimatorExtKt.animatePropertyIfChanged(this.viewState, getCapsuleNSSl(), fFloatValue, this.stackLightOSTransYProperty, true, ExtStateAnimParams.INSTANCE.toInterpolator(z ? lightOSAnimParams.getSTACK_TO_CAPSULE_TRANSLATION_Y_ANIM() : lightOSAnimParams.getCAPSULE_TO_STACK_TRANSLATION_Y_ANIM()), (64 & 32) != 0 ? 400L : 0L, (64 & 64) != 0 ? 0.1f : 1.0f);
    }

    @Override // com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController
    public final void changeBindDirection(boolean z) {
        animateStackTranslationYIfChanged(z);
    }

    @Override // com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController
    public final void init() {
        SysuiStatusBarStateController sysuiStatusBarStateController = (SysuiStatusBarStateController) this.statusBarStateController.get();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        sysuiStatusBarStateController.addCallback(anonymousClass1);
    }

    @Override // com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController
    public final boolean interceptRequestImmersiveState(int i) {
        if (i != 211) {
            return true;
        }
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager = this.lockScreenExtStateController;
        ILockScreenExtStateController.Companion companion = ILockScreenExtStateController.Companion;
        lockScreenNotificationExtStateManager.goToExtState(true, 0, "LightOSImmersiveCapsuleClick", 303, null);
        return true;
    }

    @Override // com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController
    public final void onAnimEnd() {
        getStackNSSl().mForceNoOverlappingRendering = false;
    }

    @Override // com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController
    public final void onAnimStart(boolean z) {
        getStackNSSl().mForceNoOverlappingRendering = true;
        animateStackTranslationYIfChanged(z);
    }

    @Override // com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController
    public final void onCapsuleContainerDrag(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        updateCapsuleNSSLAlpha(1.0f - f);
        this.stackStartAppearProgress = 0.0f;
    }

    @Override // com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController
    public final void onCapsuleContainerFling(float f) {
        updateStackNSSLAlpha(0.0f);
        if (f == 0.0f) {
            updateCapsuleNSSLAlpha(0.0f);
        }
        this.flingFadeoutAnimator.setStartValue(getCapsuleNSSl().getAlpha());
        CapsuleAnimParams.INSTANCE.loadOLiveLinkParam(this.flingFadeoutAnimator, ExtStateAnimParams.LightOSAnimParams.INSTANCE.getScrollUpFadeoutParam());
        this.flingFadeoutAnimator.animateToFinalPosition(f);
    }

    @Override // com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController
    public final void resetLightOSViewState() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleListOSManager-->resetLightOSViewState");
        }
        ((NotificationCapsuleContainerControllerImpl) this.capsuleNSSLController.get()).setLightOSAnimAlpha(1.0f);
        getStackNSSl().setTranslationYForLightOSCapsule(0.0f);
        ((NotificationCapsuleContainerControllerImpl) this.capsuleNSSLController.get()).getView().setTranslationY(0.0f);
        ((NotificationStackScrollLayoutController) this.stackNSSLController.get()).setMaxAlphaFromLightOSCapsule(1.0f);
        this.stackStartAppearProgress = 1.0f;
    }

    @Override // com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController
    public final void updateAnimProgress(float f, boolean z) {
        this.capsuleProgress = f;
        if (((COUIDynamicAnimation) this.flingFadeoutAnimator).mRunning) {
            updateStackNSSLAlpha(0.0f);
            return;
        }
        float fMax = !z ? Math.max(this.stackStartAppearProgress, ExtStateAnimParams.LightOSAnimParams.INSTANCE.getCAPSULE_TO_STACK_SPLIT_LIMIT().get().floatValue()) : ExtStateAnimParams.LightOSAnimParams.INSTANCE.getSTACK_TO_CAPSULE_SPLIT_LIMIT().get().floatValue();
        if ((f == 0.0f || f == 1.0f) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleListOSManager-->".concat("updateAnimProgress: splitProgress=" + fMax + ", isShowCapsule:" + z));
        }
        if (f <= fMax) {
            updateCapsuleNSSLAlpha(0.0f);
            Companion.getClass();
            updateStackNSSLAlpha(1.0f - ((MathUtils.constrain(f, 0.0f, fMax) - 0.0f) / (fMax - 0.0f)));
            return;
        }
        Companion.getClass();
        float fConstrain = (MathUtils.constrain(f, fMax, 1.0f) - fMax) / (1.0f - fMax);
        updateCapsuleNSSLAlpha(fConstrain);
        float capsuleDragLimit = (fConstrain - 1.0f) * CapsuleConfig.INSTANCE.getCapsuleDragLimit();
        if (this.isKeyguardState) {
            if (CapsuleDebug.INSTANCE.getLogSwitchItemViewUpdate() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("updateCapsuleNSSLTranslationY:", capsuleDragLimit, "CapsuleListOSManager-->", "SystemUi--Notification");
            }
            ((NotificationCapsuleContainerControllerImpl) this.capsuleNSSLController.get()).getView().setTranslationY(capsuleDragLimit);
        }
        updateStackNSSLAlpha(0.0f);
    }

    public final void updateCapsuleNSSLAlpha(float f) {
        if (this.isKeyguardState) {
            ((NotificationCapsuleContainerControllerImpl) this.capsuleNSSLController.get()).setLightOSAnimAlpha(f);
        }
    }

    public final void updateStackNSSLAlpha(float f) {
        if (this.isKeyguardState) {
            ((NotificationStackScrollLayoutController) this.stackNSSLController.get()).setMaxAlphaFromLightOSCapsule(f);
        }
    }
}
