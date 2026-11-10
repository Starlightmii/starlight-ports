package com.oplus.systemui.notification.interruption.fullscreenbanner.view;

import android.content.ComponentName;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.MathUtils;
import android.view.SurfaceControl;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.View;
import android.view.ViewGroup;
import android.window.WindowAnimationState;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.statusbar.notification.LaunchAnimationParameters;
import com.android.wm.shell.shared.compat.AnimatedSurface;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FullScreenBannerAnimatorController implements ActivityTransitionAnimator.Controller {
    public static final Companion Companion = new Companion();
    public static final String TAG = "FullScreenBannerAnimatorController";
    private final FullScreenBannerHelper container;
    private final Runnable onFinishAnimationCallback;
    private final Runnable onStartAnimationCallback;
    private final FullScreenBanner view;

    public FullScreenBannerAnimatorController(FullScreenBannerHelper fullScreenBannerHelper, FullScreenBanner fullScreenBanner, Runnable runnable, Runnable runnable2) {
        this.container = fullScreenBannerHelper;
        this.view = fullScreenBanner;
        this.onFinishAnimationCallback = runnable;
        this.onStartAnimationCallback = runnable2;
    }

    public TransitionAnimator.State createAnimatorState() {
        int height = this.view.getHeight();
        int[] locationOnScreen = this.view.getLocationOnScreen();
        int i = locationOnScreen[1];
        int i2 = i + height;
        int i3 = locationOnScreen[0];
        return new LaunchAnimationParameters(this.view.getResources().getDimension(2131168362), i, i2, this.view.getResources().getDimension(2131168362), i3, this.view.getWidth() + i3);
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
        return this.view;
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

    public /* bridge */ /* synthetic */ Boolean isFlingToZoomAnimator() {
        return super.isFlingToZoomAnimator();
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
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onIntentStarted: willAnimate = ", "FullScreenBannerAnimatorController-->", "SystemUi--Notification", z);
        }
    }

    public void onTransitionAnimationCancelled(Boolean bool) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "FullScreenBannerAnimatorController-->".concat("onTransitionAnimationCancelled: newKeyguardOccludedState = " + bool));
        }
        this.container.setIsStartFloatWindow(false);
        Runnable runnable = this.onFinishAnimationCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    public final void onTransitionAnimationEnd(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onTransitionAnimationEnd: isExpandingFullyAbove = ", "FullScreenBannerAnimatorController-->", "SystemUi--Notification", z);
        }
        this.container.setIsStartFloatWindow(false);
        Runnable runnable = this.onFinishAnimationCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationEndStateCustom(TransitionAnimator.State state) {
        super.onTransitionAnimationEndStateCustom(state);
    }

    public void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        LaunchAnimationParameters launchAnimationParameters = (LaunchAnimationParameters) state;
        launchAnimationParameters.setProgress(f);
        launchAnimationParameters.setLinearProgress(f2);
        FullScreenBannerHelper fullScreenBannerHelper = this.container;
        fullScreenBannerHelper.getClass();
        if (launchAnimationParameters.getLinearProgress() == 0.0f || launchAnimationParameters.getProgress() == 0.0f) {
            return;
        }
        FullScreenBanner fullScreenBanner = fullScreenBannerHelper.mFullScreenBanner;
        if (fullScreenBanner != null) {
            fullScreenBanner.setAlpha(launchAnimationParameters.getContentAlpha());
            fullScreenBanner.setTranslationX(launchAnimationParameters.getCenterX() - (((fullScreenBanner.getWidth() / 2.0f) + fullScreenBanner.getLocationOnScreen()[0]) - fullScreenBanner.getTranslationX()));
            fullScreenBanner.setTranslationY(launchAnimationParameters.getTop());
            fullScreenBanner.setScaleX(launchAnimationParameters.getWidth() / MathUtils.max(fullScreenBanner.getWidth(), 1));
        }
        if (fullScreenBannerHelper.mBackgroundNormal == null) {
            return;
        }
        Bundle bundle = fullScreenBannerHelper.mZoomBunld;
        fullScreenBannerHelper.mBackgroundNormal.setCornerRadius(launchAnimationParameters.getBottomCornerRadius() * (bundle != null ? bundle.getFloat("androidx.activity.LaunchScale", 1.0f) : 1.0f));
        fullScreenBannerHelper.mBackgroundNormal.setIconAlpha(launchAnimationParameters.getIconalpha());
        fullScreenBannerHelper.mBackgroundNormal.setAlpha(launchAnimationParameters.getAlpha());
        FullScreenBackgroundView fullScreenBackgroundView = fullScreenBannerHelper.mBackgroundNormal;
        int left = launchAnimationParameters.getLeft();
        int top = launchAnimationParameters.getTop();
        fullScreenBackgroundView.mRectF.set(left, top, left + launchAnimationParameters.getWidth(), top + launchAnimationParameters.getHeight());
        fullScreenBackgroundView.updateIconRect();
        fullScreenBackgroundView.invalidate();
        fullScreenBackgroundView.setVisibility(0);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationProgressStateCustomOverlay(float f, TransitionAnimator.Interpolators interpolators, TransitionAnimator.State state, TransitionAnimator.State state2, TransitionAnimator.State state3) {
        super/*com.android.systemui.animation.TransitionAnimator.Controller*/.onTransitionAnimationProgressStateCustomOverlay(f, interpolators, state, state2, state3);
    }

    public final void onTransitionAnimationStart(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onTransitionAnimationStart: isExpandingFullyAbove = ", "FullScreenBannerAnimatorController-->", "SystemUi--Notification", z);
        }
        this.container.setIsStartFloatWindow(true);
        Runnable runnable = this.onStartAnimationCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationStartQs(boolean z, AnimatedSurface animatedSurface, Function1 function1) {
        super.onTransitionAnimationStartQs(z, animatedSurface, function1);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationStartWithWindow(boolean z, AnimatedSurface animatedSurface) {
        super.onTransitionAnimationStartWithWindow(z, animatedSurface);
    }

    public /* bridge */ /* synthetic */ void setFlingToZoomAnimator(Boolean bool) {
        super.setFlingToZoomAnimator(bool);
    }

    public void setTransitionContainer(ViewGroup viewGroup) {
    }
}
