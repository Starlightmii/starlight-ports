package com.oplus.systemui.qs.scenario.domain.animation;

import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.window.WindowAnimationState;
import com.android.systemui.animation.TransitionAnimator;
import com.oplus.graphics.OplusOutlineAdapter;
import kotlin.jvm.functions.Function0;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioTargetViewTransitionAnimatorController extends ScenarioTransitionAnimatorController {
    private final Drawable background;
    private final View container;
    private OplusOutlineAdapter oplusOutlineAdapter;
    private final Rect originRect;
    private Outline outlineLast;
    private final View targetView;
    private final int[] targetViewLoc;

    public ScenarioTargetViewTransitionAnimatorController(View view, int i) {
        super(view);
        this.targetView = view;
        View viewFindViewById = view.findViewById(i);
        view = viewFindViewById != null ? viewFindViewById : view;
        this.container = view;
        this.originRect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        this.targetViewLoc = new int[2];
        this.background = view.getBackground();
        view.setClipToOutline(true);
        view.setOutlineProvider(new 1(this));
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public void fillViewState(ScenarioDialogAnimationManager.State state) {
        super.fillViewState(this.container, state);
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public Drawable getBackground() {
        return this.background;
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public /* bridge */ /* synthetic */ float[] getCouiSpringConfig() {
        return super.getCouiSpringConfig();
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public /* bridge */ /* synthetic */ boolean getManagesLaunchWindowLayerLocally() {
        return super.getManagesLaunchWindowLayerLocally();
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public /* bridge */ /* synthetic */ View getOpeningWindowSyncView() {
        return super.getOpeningWindowSyncView();
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public Rect getOriginRect() {
        return this.originRect;
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public /* bridge */ /* synthetic */ SurfaceControl getShadeSurfaceControl() {
        return super.getShadeSurfaceControl();
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public /* bridge */ /* synthetic */ WindowAnimationState getWindowAnimatorState() {
        return super.getWindowAnimatorState();
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public /* bridge */ /* synthetic */ boolean getWindowFadeInAfterDrawable() {
        return super.getWindowFadeInAfterDrawable();
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public /* bridge */ /* synthetic */ boolean onCustomStateToWindowBackgroundLayer(GradientDrawable gradientDrawable, TransitionAnimator.State state, float f, View view, Function0 function0, boolean z, boolean z2, boolean z3) {
        return super.onCustomStateToWindowBackgroundLayer(gradientDrawable, state, f, view, function0, z, z2, z3);
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public final void onTransitionAnimationEnd(boolean z) {
        super.onTransitionAnimationEnd(z);
        ((ViewGroup) this.targetView).suppressLayout(false);
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        super.onTransitionAnimationProgress(state, f, f2);
        ScenarioDialogAnimationManager.State state2 = (ScenarioDialogAnimationManager.State) state;
        this.targetView.setAlpha(state2.getTransitionAlpha() * state.getAlpha());
        this.targetView.setTranslationY(state2.getTranslationY());
        this.container.setLeftTopRightBottom(state.getLeft() - this.targetViewLoc[0], state.getTop() - this.targetViewLoc[1], state.getRight() - this.targetViewLoc[0], state.getBottom() - this.targetViewLoc[1]);
        int iRoundToInt = MathKt.roundToInt((state.getWidth() - getOriginRect().width()) / 2.0f);
        this.container.scrollTo(-iRoundToInt, 0);
        this.container.invalidateOutline();
        if (state2.getHasShareElement()) {
            View icon = getIcon();
            if (icon != null) {
                float fCenterX = state2.getIconRect().centerX() - ((icon.getRight() + icon.getLeft()) / 2.0f);
                float fCenterY = state2.getIconRect().centerY() - ((icon.getBottom() + icon.getTop()) / 2.0f);
                icon.setTranslationX(fCenterX - iRoundToInt);
                icon.setTranslationY(fCenterY);
                icon.setScaleX(state2.getIconRect().width() / icon.getWidth());
                icon.setScaleY(state2.getIconRect().height() / icon.getHeight());
            }
            View title = getTitle();
            if (title != null) {
                float fCenterX2 = state2.getTitleRect().centerX() - ((title.getRight() + title.getLeft()) / 2.0f);
                float fCenterY2 = state2.getTitleRect().centerY() - ((title.getBottom() + title.getTop()) / 2.0f);
                title.setTranslationX(fCenterX2 - iRoundToInt);
                title.setTranslationY(fCenterY2);
                title.setScaleX(state2.getTitleRect().width() / title.getWidth());
                title.setScaleY(state2.getTitleRect().height() / title.getHeight());
            }
        }
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public /* bridge */ /* synthetic */ void onTransitionAnimationProgressStateCustomOverlay(float f, TransitionAnimator.Interpolators interpolators, TransitionAnimator.State state, TransitionAnimator.State state2, TransitionAnimator.State state3) {
        super.onTransitionAnimationProgressStateCustomOverlay(f, interpolators, state, state2, state3);
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public final void onTransitionAnimationStart(boolean z) {
        super.onTransitionAnimationStart(z);
        this.targetView.setAlpha(0.0f);
        this.targetView.getLocationOnScreen(this.targetViewLoc);
        ((ViewGroup) this.targetView).suppressLayout(true);
    }
}
