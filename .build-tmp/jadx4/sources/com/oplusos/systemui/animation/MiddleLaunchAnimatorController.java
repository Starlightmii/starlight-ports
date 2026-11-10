package com.oplusos.systemui.animation;

import android.content.ComponentName;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.View;
import android.window.WindowAnimationState;
import androidx.compose.animation.core.MonoSpline$;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.wm.shell.shared.compat.AnimatedSurface;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class MiddleLaunchAnimatorController extends TargetViewLaunchAnimatorController {
    private static final String TAG = "MiddleLaunchAnimatorController";
    public final int containerId;
    public final boolean isLaunching;
    public float startTargetViewAlpha;
    public float startTargetViewScale;
    private final View targetView;
    public static final Companion Companion = new Companion();
    private static final COUISpringInterpolator alphaSpInterpolator = new COUISpringInterpolator(0.8d, 0.0d);
    private static final TransitionAnimator.Timings ENTER_TIMING = new TransitionAnimator.Timings(650, 0, 0, 0, 0);
    private static final TransitionAnimator.Timings EXIT_DURATION = new TransitionAnimator.Timings(400, 0, 0, 0, 0);

    public MiddleLaunchAnimatorController(View view, Function0 function0, boolean z, int i) {
        super(view, function0, z, i);
        this.targetView = view;
        this.isLaunching = z;
        this.containerId = i;
        this.startTargetViewAlpha = 1.0f;
        this.startTargetViewScale = 1.0f;
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
    public /* bridge */ /* synthetic */ void onTransitionAnimationEndStateCustom(TransitionAnimator.State state) {
        super.onTransitionAnimationEndStateCustom(state);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        float fM;
        state.setLeft(getTargetViewState().getLeft());
        state.setRight(getTargetViewState().getRight());
        state.setTop(getTargetViewState().getTop());
        state.setBottom(getTargetViewState().getBottom());
        setCurrentState(state);
        this.targetView.setAlpha(state.getAlpha());
        View viewFindViewById = this.targetView.findViewById(this.containerId);
        if (this.isLaunching) {
            fM = (alphaSpInterpolator.getInterpolation(TransitionAnimator.Companion.getProgress(ENTER_TIMING, f2, 100L, 250L)) * 0.19999999f) + 0.8f;
        } else {
            float progress = TransitionAnimator.Companion.getProgress(EXIT_DURATION, f2, 0L, 250L);
            float f3 = this.startTargetViewScale;
            fM = MonoSpline$.ExternalSyntheticOutline0.m(this.startTargetViewScale, 0.8f, alphaSpInterpolator.getInterpolation(progress), f3);
        }
        if (viewFindViewById != null) {
            viewFindViewById.setScaleX(fM);
        }
        if (viewFindViewById != null) {
            viewFindViewById.setScaleY(fM);
        }
        updateTargetViewBackground(state, getRadius(), getWeight());
        updateSurfaceTransaction(state, f);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ void onTransitionAnimationProgressStateCustomOverlay(float f, TransitionAnimator.Interpolators interpolators, TransitionAnimator.State state, TransitionAnimator.State state2, TransitionAnimator.State state3) {
        super/*com.android.systemui.animation.TransitionAnimator.Controller*/.onTransitionAnimationProgressStateCustomOverlay(f, interpolators, state, state2, state3);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public final void onTransitionAnimationStart(boolean z) {
        this.startTargetViewAlpha = this.targetView.getAlpha();
        View viewFindViewById = this.targetView.findViewById(this.containerId);
        float scaleX = viewFindViewById != null ? viewFindViewById.getScaleX() : 1.0f;
        this.startTargetViewScale = scaleX;
        StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("isLaunching ", this.isLaunching, " startTargetViewAlpha ", this.startTargetViewAlpha, " startTargetViewScale ");
        sbM.append(scaleX);
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, sbM.toString(), null, 8);
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
