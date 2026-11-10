package com.oplus.systemui.notification.lockscreen.launch;

import android.content.ComponentName;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.View;
import android.view.ViewGroup;
import android.window.WindowAnimationState;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.statusbar.notification.LaunchAnimationParameters;
import com.android.systemui.statusbar.notification.domain.interactor.NotificationLaunchAnimationInteractor;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.util.NotificationJankUtils;
import com.android.wm.shell.shared.compat.AnimatedSurface;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveViewManager;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingTransitionAnimatorController implements ActivityTransitionAnimator.Controller {
    public static final Companion Companion = new Companion();
    private final OplusImmersiveViewManager immersiveViewManager;
    private Boolean isFlingToZoomAnimator = Boolean.FALSE;
    public boolean isSurfaceImmersiveState;
    private final InteractionJankMonitor jankMonitor;
    private final ILockScreenExtStateController lockScreenExtStateController;
    private final NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor;
    private final NotificationListContainer notificationListContainer;
    private final Runnable onFinishAnimationCallback;
    private final OplusCustomRow row;

    public SeedlingTransitionAnimatorController(NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor, NotificationListContainer notificationListContainer, OplusCustomRow oplusCustomRow, InteractionJankMonitor interactionJankMonitor, Runnable runnable, OplusImmersiveViewManager oplusImmersiveViewManager, ILockScreenExtStateController iLockScreenExtStateController) {
        this.notificationLaunchAnimationInteractor = notificationLaunchAnimationInteractor;
        this.notificationListContainer = notificationListContainer;
        this.row = oplusCustomRow;
        this.jankMonitor = interactionJankMonitor;
        this.onFinishAnimationCallback = runnable;
        this.immersiveViewManager = oplusImmersiveViewManager;
        this.lockScreenExtStateController = iLockScreenExtStateController;
    }

    public final void applyParams$3(LaunchAnimationParameters launchAnimationParameters) {
        if (this.isSurfaceImmersiveState) {
            float f = 1.0f;
            if (launchAnimationParameters != null) {
                float progress = launchAnimationParameters.getProgress();
                if (progress > 0.7f) {
                    progress = 0.7f;
                }
                f = 1.0f - (progress / 0.7f);
            }
            this.immersiveViewManager.updateLaunchViewAlpha(f);
        }
        this.row.applyLaunchAnimationParams(launchAnimationParameters);
        this.notificationListContainer.applyLaunchAnimationParams(launchAnimationParameters);
    }

    public TransitionAnimator.State createAnimatorState() {
        int iMax = Math.max(0, this.row.getActualHeight() - this.row.getClipBottomAmount());
        ((ExpandableView) this.row).mExpandableViewEx.resetTouchScaleImmediately("remote anim");
        int[] locationOnScreen = this.row.getLocationOnScreen();
        int topClippingStartLocation = this.notificationListContainer.getTopClippingStartLocation();
        int i = locationOnScreen[1];
        int i2 = topClippingStartLocation - i;
        if (i2 < 0) {
            i2 = 0;
        }
        int i3 = i + i2;
        float topCornerRadius = i2 > 0 ? 0.0f : this.row.getTopCornerRadius();
        int i4 = locationOnScreen[1] + iMax;
        int i5 = locationOnScreen[0];
        LaunchAnimationParameters launchAnimationParameters = new LaunchAnimationParameters(topCornerRadius, i3, i4, this.row.getBottomCornerRadius(), i5, this.row.getWidth() + i5);
        Log.d("SeedlingLaunchAnimatorController", "createAnimatorState params=" + launchAnimationParameters + ",row width=" + this.row.getWidth() + ",scale=" + this.row.getScaleX() + ",location[0]=" + locationOnScreen[0]);
        launchAnimationParameters.setStartTranslationZ(this.row.getTranslationZ());
        launchAnimationParameters.setStartNotificationTop(locationOnScreen[1]);
        launchAnimationParameters.setNotificationParentTop(this.notificationListContainer.getViewParentForNotification().getLocationOnScreen()[1]);
        launchAnimationParameters.setStartRoundedTopClipping(i2);
        launchAnimationParameters.setStartClipTopAmount(this.row.getClipTopAmount());
        return launchAnimationParameters;
    }

    public /* bridge */ /* synthetic */ boolean customApplyStateToWindow(AnimatedSurface animatedSurface, TransitionAnimator.State state, float f, SyncRtSurfaceTransactionApplier syncRtSurfaceTransactionApplier, Matrix matrix, View view) {
        return super.customApplyStateToWindow(animatedSurface, state, f, syncRtSurfaceTransactionApplier, matrix, view);
    }

    public /* bridge */ /* synthetic */ ComponentName getComponent() {
        return super.getComponent();
    }

    public /* bridge */ /* synthetic */ TransitionAnimator.Interpolators getCouiInterpolators() {
        return super.getCouiInterpolators();
    }

    public /* bridge */ /* synthetic */ float[] getCouiSpringConfig() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getCouiSpringConfig();
    }

    public /* bridge */ /* synthetic */ TransitionAnimator.Interpolators getCustomInterpolators() {
        return super.getCustomInterpolators();
    }

    public /* bridge */ /* synthetic */ TransitionAnimator.SpringParams getCustomSpringParams() {
        return super.getCustomSpringParams();
    }

    public /* bridge */ /* synthetic */ TransitionAnimator.SpringTimings getCustomSpringTimings() {
        return super.getCustomSpringTimings();
    }

    public /* bridge */ /* synthetic */ TransitionAnimator.Timings getCustomTimings() {
        return super.getCustomTimings();
    }

    public /* bridge */ /* synthetic */ boolean getForceDrawHole() {
        return super.getForceDrawHole();
    }

    public /* bridge */ /* synthetic */ boolean getForceUseWindowCorner() {
        return super.getForceUseWindowCorner();
    }

    public /* bridge */ /* synthetic */ boolean getManagesLaunchWindowLayerLocally() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getManagesLaunchWindowLayerLocally();
    }

    public /* bridge */ /* synthetic */ View getOpeningWindowSyncView() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getOpeningWindowSyncView();
    }

    public /* bridge */ /* synthetic */ SurfaceControl getShadeSurfaceControl() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getShadeSurfaceControl();
    }

    public /* bridge */ /* synthetic */ PointF getStartVelocity() {
        return super.getStartVelocity();
    }

    public ViewGroup getTransitionContainer() {
        return (ViewGroup) this.row.getRootView();
    }

    public /* bridge */ /* synthetic */ ActivityTransitionAnimator.TransitionCookie getTransitionCookie() {
        return super.getTransitionCookie();
    }

    public /* bridge */ /* synthetic */ WindowAnimationState getWindowAnimatorState() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getWindowAnimatorState();
    }

    public /* bridge */ /* synthetic */ boolean getWindowFadeInAfterDrawable() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getWindowFadeInAfterDrawable();
    }

    public /* bridge */ /* synthetic */ Bundle getZoomBundle() {
        return super.getZoomBundle();
    }

    public Boolean isFlingToZoomAnimator() {
        return this.isFlingToZoomAnimator;
    }

    public final boolean isLaunching() {
        return true;
    }

    public /* bridge */ /* synthetic */ SurfaceControl.Transaction mergeCustomTransactionToWindowApplied(AnimatedSurface animatedSurface, TransitionAnimator.State state, float f) {
        return super.mergeCustomTransactionToWindowApplied(animatedSurface, state, f);
    }

    public /* bridge */ /* synthetic */ boolean onCustomStateToWindowBackgroundLayer(GradientDrawable gradientDrawable, TransitionAnimator.State state, float f, View view, Function0 function0, boolean z, boolean z2, boolean z3) {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.onCustomStateToWindowBackgroundLayer(gradientDrawable, state, f, view, function0, z, z2, z3);
    }

    public final void onIntentStarted(boolean z) {
        Runnable runnable;
        String strM = OplusLockIconView$.ExternalSyntheticOutline0.m("onIntentStarted(willAnimate=", ")", z);
        if (ActivityTransitionAnimator.DEBUG_TRANSITION_ANIMATION) {
            Log.d("SeedlingLaunchAnimatorController", strM);
        }
        this.notificationLaunchAnimationInteractor.setIsLaunchAnimationRunning(z);
        this.row.setLaunchAnimationRunning(z);
        if (z || (runnable = this.onFinishAnimationCallback) == null) {
            return;
        }
        runnable.run();
    }

    public void onTransitionAnimationCancelled(Boolean bool) {
        if (ActivityTransitionAnimator.DEBUG_TRANSITION_ANIMATION) {
            Log.d("SeedlingLaunchAnimatorController", "onLaunchAnimationCancelled()");
        }
        NotificationJankUtils.gfxClickSceneJudgeEnd(this.row.getContext());
        this.notificationLaunchAnimationInteractor.setIsLaunchAnimationRunning(false);
        this.row.setLaunchAnimationRunning(false);
        Runnable runnable = this.onFinishAnimationCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    public final void onTransitionAnimationEnd(boolean z) {
        if (ActivityTransitionAnimator.DEBUG_TRANSITION_ANIMATION) {
            Log.d("SeedlingLaunchAnimatorController", "onLaunchAnimationEnd()");
        }
        NotificationJankUtils.gfxClickSceneJudgeEnd(this.row.getContext());
        this.jankMonitor.end(16);
        this.row.setExpandAnimationRunning(false);
        this.notificationLaunchAnimationInteractor.setIsLaunchAnimationRunning(false);
        this.row.setLaunchAnimationRunning(false);
        applyParams$3(null);
        Runnable runnable = this.onFinishAnimationCallback;
        if (runnable != null) {
            runnable.run();
        }
        OplusSysUiSchedMgr.Companion.getInstance().releaseSysResource(OplusSysUiSchedMgr.UafSceneEvent.SEEDLING_ANIMATION);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationEndStateCustom(TransitionAnimator.State state) {
        super.onTransitionAnimationEndStateCustom(state);
    }

    public void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        LaunchAnimationParameters launchAnimationParameters = (LaunchAnimationParameters) state;
        launchAnimationParameters.setProgress(f);
        launchAnimationParameters.setLinearProgress(f2);
        applyParams$3(launchAnimationParameters);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationProgressStateCustomOverlay(float f, TransitionAnimator.Interpolators interpolators, TransitionAnimator.State state, TransitionAnimator.State state2, TransitionAnimator.State state3) {
        super/*com.android.systemui.animation.TransitionAnimator.Controller*/.onTransitionAnimationProgressStateCustomOverlay(f, interpolators, state, state2, state3);
    }

    public final void onTransitionAnimationStart(boolean z) {
        OplusSysUiSchedMgr.Companion.getInstance().requestSysResource(OplusSysUiSchedMgr.UafSceneEvent.SEEDLING_ANIMATION);
        this.row.setExpandAnimationRunning(true);
        this.jankMonitor.begin(this.row, 16);
        this.isSurfaceImmersiveState = this.lockScreenExtStateController.isImmersiveState() && this.immersiveViewManager.isSurfaceBg();
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationStartQs(boolean z, AnimatedSurface animatedSurface, Function1 function1) {
        super.onTransitionAnimationStartQs(z, animatedSurface, function1);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationStartWithWindow(boolean z, AnimatedSurface animatedSurface) {
        super.onTransitionAnimationStartWithWindow(z, animatedSurface);
    }

    public void setFlingToZoomAnimator(Boolean bool) {
        this.isFlingToZoomAnimator = bool;
    }

    public void setTransitionContainer(ViewGroup viewGroup) {
    }
}
