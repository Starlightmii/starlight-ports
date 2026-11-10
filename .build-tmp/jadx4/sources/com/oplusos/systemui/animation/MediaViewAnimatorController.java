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
import android.view.ViewParent;
import android.window.WindowAnimationState;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.GhostedViewTransitionAnimatorController;
import com.android.systemui.animation.TransitionAnimator;
import com.android.wm.shell.shared.compat.AnimatedSurface;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class MediaViewAnimatorController extends GhostedViewTransitionAnimatorController {
    private final Float cornerRadius;
    public final boolean isLaunching;
    private final View simpleMediaView;
    private ViewGroup transitionContainer;

    public MediaViewAnimatorController(View view, Float f, boolean z) {
        ViewGroup viewGroup;
        super(view, (Integer) null, (ActivityTransitionAnimator.TransitionCookie) null, (ComponentName) null, (Integer) null, false, (InteractionJankMonitor) null, f, true, (Float) null, 1662);
        this.simpleMediaView = view;
        this.cornerRadius = f;
        this.isLaunching = z;
        if (z) {
            ViewParent parent = view.getParent();
            viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup == null) {
                viewGroup = (ViewGroup) view.getRootView();
            }
        } else {
            viewGroup = (ViewGroup) view.getRootView();
        }
        this.transitionContainer = viewGroup;
    }

    public /* bridge */ /* synthetic */ boolean customApplyStateToWindow(AnimatedSurface animatedSurface, TransitionAnimator.State state, float f, SyncRtSurfaceTransactionApplier syncRtSurfaceTransactionApplier, Matrix matrix, View view) {
        return super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.customApplyStateToWindow(animatedSurface, state, f, syncRtSurfaceTransactionApplier, matrix, view);
    }

    public /* bridge */ /* synthetic */ TransitionAnimator.Interpolators getCouiInterpolators() {
        return super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.getCouiInterpolators();
    }

    public /* bridge */ /* synthetic */ float[] getCouiSpringConfig() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getCouiSpringConfig();
    }

    public /* bridge */ /* synthetic */ TransitionAnimator.Interpolators getCustomInterpolators() {
        return super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.getCustomInterpolators();
    }

    public /* bridge */ /* synthetic */ TransitionAnimator.SpringParams getCustomSpringParams() {
        return super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.getCustomSpringParams();
    }

    public /* bridge */ /* synthetic */ TransitionAnimator.SpringTimings getCustomSpringTimings() {
        return super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.getCustomSpringTimings();
    }

    public /* bridge */ /* synthetic */ TransitionAnimator.Timings getCustomTimings() {
        return super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.getCustomTimings();
    }

    public /* bridge */ /* synthetic */ boolean getForceDrawHole() {
        return super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.getForceDrawHole();
    }

    public /* bridge */ /* synthetic */ boolean getForceUseWindowCorner() {
        return super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.getForceUseWindowCorner();
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
        return super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.getStartVelocity();
    }

    public ViewGroup getTransitionContainer() {
        return this.transitionContainer;
    }

    public /* bridge */ /* synthetic */ WindowAnimationState getWindowAnimatorState() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getWindowAnimatorState();
    }

    public /* bridge */ /* synthetic */ boolean getWindowFadeInAfterDrawable() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getWindowFadeInAfterDrawable();
    }

    public /* bridge */ /* synthetic */ Bundle getZoomBundle() {
        return super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.getZoomBundle();
    }

    public /* bridge */ /* synthetic */ Boolean isFlingToZoomAnimator() {
        return super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.isFlingToZoomAnimator();
    }

    public final boolean isLaunching() {
        return this.isLaunching;
    }

    public /* bridge */ /* synthetic */ SurfaceControl.Transaction mergeCustomTransactionToWindowApplied(AnimatedSurface animatedSurface, TransitionAnimator.State state, float f) {
        return super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.mergeCustomTransactionToWindowApplied(animatedSurface, state, f);
    }

    public /* bridge */ /* synthetic */ boolean onCustomStateToWindowBackgroundLayer(GradientDrawable gradientDrawable, TransitionAnimator.State state, float f, View view, Function0 function0, boolean z, boolean z2, boolean z3) {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.onCustomStateToWindowBackgroundLayer(gradientDrawable, state, f, view, function0, z, z2, z3);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationCancelled(Boolean bool) {
        super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.onTransitionAnimationCancelled(bool);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationEndStateCustom(TransitionAnimator.State state) {
        super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.onTransitionAnimationEndStateCustom(state);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationProgressStateCustomOverlay(float f, TransitionAnimator.Interpolators interpolators, TransitionAnimator.State state, TransitionAnimator.State state2, TransitionAnimator.State state3) {
        super/*com.android.systemui.animation.TransitionAnimator.Controller*/.onTransitionAnimationProgressStateCustomOverlay(f, interpolators, state, state2, state3);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationStartQs(boolean z, AnimatedSurface animatedSurface, Function1 function1) {
        super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.onTransitionAnimationStartQs(z, animatedSurface, function1);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationStartWithWindow(boolean z, AnimatedSurface animatedSurface) {
        super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.onTransitionAnimationStartWithWindow(z, animatedSurface);
    }

    public /* bridge */ /* synthetic */ void setFlingToZoomAnimator(Boolean bool) {
        super/*com.android.systemui.animation.ActivityTransitionAnimator.Controller*/.setFlingToZoomAnimator(bool);
    }

    public void setTransitionContainer(ViewGroup viewGroup) {
        this.transitionContainer = viewGroup;
    }

    public MediaViewAnimatorController(View view, boolean z) {
        this(view, null, z);
    }
}
