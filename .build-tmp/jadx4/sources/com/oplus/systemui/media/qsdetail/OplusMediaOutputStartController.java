package com.oplus.systemui.media.qsdetail;

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
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.oplusos.systemui.animation.TargetViewLaunchAnimatorController;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaOutputStartController extends TargetViewLaunchAnimatorController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusMediaOutputStartController";
    private final View fromView;
    private final View indicatorView;
    public final boolean isLaunching;
    private final COUISpringInterpolator springInterpolator;
    private final int[] targetViewLocation;

    public OplusMediaOutputStartController(View view, View view2, Function0 function0, boolean z, int i) {
        super(view, function0, z, i);
        this.fromView = view;
        this.indicatorView = view2;
        this.isLaunching = z;
        this.springInterpolator = new COUISpringInterpolator(0.2d, 0.0d);
        int[] iArr = new int[2];
        Object parent = view.getParent();
        View view3 = parent instanceof View ? (View) parent : null;
        if (view3 != null) {
            view3.getLocationOnScreen(iArr);
        }
        this.targetViewLocation = iArr;
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ boolean customApplyStateToWindow(AnimatedSurface animatedSurface, TransitionAnimator.State state, float f, SyncRtSurfaceTransactionApplier syncRtSurfaceTransactionApplier, Matrix matrix, View view) {
        return super.customApplyStateToWindow(animatedSurface, state, f, syncRtSurfaceTransactionApplier, matrix, view);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ ComponentName getComponent() {
        return super.getComponent();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ TransitionAnimator.Interpolators getCouiInterpolators() {
        return super.getCouiInterpolators();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ float[] getCouiSpringConfig() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getCouiSpringConfig();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ TransitionAnimator.Interpolators getCustomInterpolators() {
        return super.getCustomInterpolators();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ TransitionAnimator.SpringParams getCustomSpringParams() {
        return super.getCustomSpringParams();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ TransitionAnimator.SpringTimings getCustomSpringTimings() {
        return super.getCustomSpringTimings();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ TransitionAnimator.Timings getCustomTimings() {
        return super.getCustomTimings();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ boolean getForceDrawHole() {
        return super.getForceDrawHole();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ boolean getForceUseWindowCorner() {
        return super.getForceUseWindowCorner();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ boolean getManagesLaunchWindowLayerLocally() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getManagesLaunchWindowLayerLocally();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ View getOpeningWindowSyncView() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getOpeningWindowSyncView();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ SurfaceControl getShadeSurfaceControl() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getShadeSurfaceControl();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ PointF getStartVelocity() {
        return super.getStartVelocity();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ ActivityTransitionAnimator.TransitionCookie getTransitionCookie() {
        return super.getTransitionCookie();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ WindowAnimationState getWindowAnimatorState() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getWindowAnimatorState();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ boolean getWindowFadeInAfterDrawable() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getWindowFadeInAfterDrawable();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ Bundle getZoomBundle() {
        return super.getZoomBundle();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ Boolean isFlingToZoomAnimator() {
        return super.isFlingToZoomAnimator();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public final boolean isLaunching() {
        return this.isLaunching;
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ SurfaceControl.Transaction mergeCustomTransactionToWindowApplied(AnimatedSurface animatedSurface, TransitionAnimator.State state, float f) {
        return super.mergeCustomTransactionToWindowApplied(animatedSurface, state, f);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ boolean onCustomStateToWindowBackgroundLayer(GradientDrawable gradientDrawable, TransitionAnimator.State state, float f, View view, Function0 function0, boolean z, boolean z2, boolean z3) {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.onCustomStateToWindowBackgroundLayer(gradientDrawable, state, f, view, function0, z, z2, z3);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ void onTransitionAnimationCancelled(Boolean bool) {
        super.onTransitionAnimationCancelled(bool);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public final void onTransitionAnimationEnd(boolean z) {
        this.fromView.setVisibility(8);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ void onTransitionAnimationEndStateCustom(TransitionAnimator.State state) {
        super.onTransitionAnimationEndStateCustom(state);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        View childAt;
        float f3 = f2 < 0.3f ? 1.0f - (f2 / 0.3f) : 0.0f;
        this.indicatorView.setAlpha(f3);
        View view = this.fromView;
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        int childCount = viewGroup != null ? viewGroup.getChildCount() : 0;
        if (childCount >= 0) {
            int i = 0;
            while (true) {
                View view2 = this.fromView;
                ViewGroup viewGroup2 = view2 instanceof ViewGroup ? (ViewGroup) view2 : null;
                if (viewGroup2 != null && (childAt = viewGroup2.getChildAt(i)) != null) {
                    childAt.setAlpha(f3);
                }
                if (i == childCount) {
                    break;
                } else {
                    i++;
                }
            }
        }
        this.fromView.setLeftTopRightBottom(state.getLeft() - this.targetViewLocation[0], state.getTop() - this.targetViewLocation[1], state.getRight() - this.targetViewLocation[0], state.getBottom() - this.targetViewLocation[1]);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ void onTransitionAnimationProgressStateCustomOverlay(float f, TransitionAnimator.Interpolators interpolators, TransitionAnimator.State state, TransitionAnimator.State state2, TransitionAnimator.State state3) {
        super/*com.android.systemui.animation.TransitionAnimator.Controller*/.onTransitionAnimationProgressStateCustomOverlay(f, interpolators, state, state2, state3);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public final void onTransitionAnimationStart(boolean z) {
        this.fromView.setAlpha(1.0f);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ void onTransitionAnimationStartQs(boolean z, AnimatedSurface animatedSurface, Function1 function1) {
        super.onTransitionAnimationStartQs(z, animatedSurface, function1);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ void onTransitionAnimationStartWithWindow(boolean z, AnimatedSurface animatedSurface) {
        super.onTransitionAnimationStartWithWindow(z, animatedSurface);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ void setFlingToZoomAnimator(Boolean bool) {
        super.setFlingToZoomAnimator(bool);
    }
}
