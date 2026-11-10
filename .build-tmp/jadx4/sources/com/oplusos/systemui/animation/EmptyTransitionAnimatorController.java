package com.oplusos.systemui.animation;

import android.content.ComponentName;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.View;
import android.view.ViewGroup;
import android.window.WindowAnimationState;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.wm.shell.shared.compat.AnimatedSurface;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class EmptyTransitionAnimatorController implements ActivityTransitionAnimator.Controller {
    public final boolean isLaunching;
    private ViewGroup transitionContainer;

    public EmptyTransitionAnimatorController(View view, boolean z) {
        this.isLaunching = z;
        this.transitionContainer = (ViewGroup) view.getRootView();
    }

    public TransitionAnimator.State createAnimatorState() {
        return new TransitionAnimator.State(0, 0, 0, 0, 0.0f, 0.0f, 0.0f, 127);
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
        return this.transitionContainer;
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
        return this.isLaunching;
    }

    public /* bridge */ /* synthetic */ SurfaceControl.Transaction mergeCustomTransactionToWindowApplied(AnimatedSurface animatedSurface, TransitionAnimator.State state, float f) {
        return super.mergeCustomTransactionToWindowApplied(animatedSurface, state, f);
    }

    public /* bridge */ /* synthetic */ boolean onCustomStateToWindowBackgroundLayer(GradientDrawable gradientDrawable, TransitionAnimator.State state, float f, View view, Function0 function0, boolean z, boolean z2, boolean z3) {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.onCustomStateToWindowBackgroundLayer(gradientDrawable, state, f, view, function0, z, z2, z3);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationCancelled(Boolean bool) {
        super.onTransitionAnimationCancelled(bool);
    }

    public final void onTransitionAnimationEnd(boolean z) {
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationEndStateCustom(TransitionAnimator.State state) {
        super.onTransitionAnimationEndStateCustom(state);
    }

    public void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationProgressStateCustomOverlay(float f, TransitionAnimator.Interpolators interpolators, TransitionAnimator.State state, TransitionAnimator.State state2, TransitionAnimator.State state3) {
        super/*com.android.systemui.animation.TransitionAnimator.Controller*/.onTransitionAnimationProgressStateCustomOverlay(f, interpolators, state, state2, state3);
    }

    public final void onTransitionAnimationStart(boolean z) {
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
        this.transitionContainer = viewGroup;
    }

    public EmptyTransitionAnimatorController(View view) {
        this(view, false);
    }
}
