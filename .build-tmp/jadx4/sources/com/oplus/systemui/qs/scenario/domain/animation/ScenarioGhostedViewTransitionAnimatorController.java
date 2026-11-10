package com.oplus.systemui.qs.scenario.domain.animation;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.GhostView;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroupOverlay;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.window.WindowAnimationState;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.animation.TransitionAnimator;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioGhostedViewTransitionAnimatorController extends ScenarioTransitionAnimatorController {
    private final Drawable background;
    public final View backgroundHost;
    private FrameLayout backgroundView;
    private final View fromView;
    private View ghostView;
    private final Matrix ghostViewMatrix;
    private final RectF ghostViewRect;
    private final ScenarioDialogAnimationManager.State ghostedViewState;
    private final List<View> privateIcons;
    private final int[] transitionContainerLocation;

    public ScenarioGhostedViewTransitionAnimatorController(View view) {
        super(view);
        this.fromView = view;
        View viewFindViewById = view.findViewById(2131364994);
        this.backgroundHost = viewFindViewById;
        this.background = viewFindViewById.getBackground();
        this.ghostViewMatrix = new Matrix();
        this.ghostViewRect = new RectF();
        this.ghostedViewState = new ScenarioDialogAnimationManager.State();
        this.transitionContainerLocation = new int[2];
        ArrayList arrayList = new ArrayList();
        this.privateIcons = arrayList;
        View viewFindViewById2 = view.findViewById(2131364640);
        if (viewFindViewById2 != null && viewFindViewById2.getVisibility() == 0 && viewFindViewById2.getAlpha() > 0.0f) {
            arrayList.add(viewFindViewById2);
        }
        View viewFindViewById3 = view.findViewById(2131364642);
        if (viewFindViewById3 != null && viewFindViewById3.getVisibility() == 0 && viewFindViewById3.getAlpha() > 0.0f) {
            arrayList.add(viewFindViewById3);
        }
        View viewFindViewById4 = view.findViewById(2131364641);
        if (viewFindViewById4 == null || viewFindViewById4.getVisibility() != 0 || viewFindViewById4.getAlpha() <= 0.0f) {
            return;
        }
        arrayList.add(viewFindViewById4);
    }

    public static void fillScaledRect(View view, float f, float f2, Rect rect) {
        rect.set(MathKt.roundToInt(view.getLeft() * f), MathKt.roundToInt(view.getTop() * f2), MathKt.roundToInt(view.getRight() * f), MathKt.roundToInt(view.getBottom() * f2));
    }

    private final ViewGroupOverlay getTransitionContainerOverlay() {
        return getTransitionContainer().getOverlay();
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public void fillShareElementState(ScenarioDialogAnimationManager.State state) {
        float scaleX = this.fromView.getScaleX();
        float scaleY = this.fromView.getScaleY();
        View icon = getIcon();
        Object parent = icon != null ? icon.getParent() : null;
        View view = parent instanceof View ? (View) parent : null;
        if (view != null) {
            fillScaledRect(view, scaleX, scaleY, state.getIconRect());
        }
        View title = getTitle();
        ViewParent parent2 = title != null ? title.getParent() : null;
        View view2 = parent2 instanceof View ? (View) parent2 : null;
        if (view2 != null) {
            fillScaledRect(view2, scaleX, scaleY, state.getTitleRect());
        }
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public void fillViewState(View view, ScenarioDialogAnimationManager.State state) {
        this.ghostViewMatrix.reset();
        view.getLocationOnScreen(getTmpLocation());
        view.transformMatrixToGlobal(this.ghostViewMatrix);
        this.ghostViewRect.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        this.ghostViewMatrix.mapRect(this.ghostViewRect);
        state.setLeft(getTmpLocation()[0]);
        state.setTop(getTmpLocation()[1]);
        state.setRight(MathKt.roundToInt(this.ghostViewRect.width()) + getTmpLocation()[0]);
        state.setBottom(MathKt.roundToInt(this.ghostViewRect.height()) + getTmpLocation()[1]);
        fillShareElementState(state);
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
        FrameLayout frameLayout = this.backgroundView;
        if (frameLayout != null) {
            getTransitionContainerOverlay().remove(frameLayout);
        }
        this.backgroundHost.setBackground(getBackground());
        for (View view : this.privateIcons) {
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }
        View icon = getIcon();
        if (icon != null) {
            icon.setTranslationX(0.0f);
            icon.setTranslationY(0.0f);
            icon.setScaleX(1.0f);
            icon.setScaleY(1.0f);
        }
        View title = getTitle();
        if (title != null) {
            title.setTranslationX(0.0f);
            title.setTranslationY(0.0f);
            title.setScaleX(1.0f);
            title.setScaleY(1.0f);
        }
        removeGhost();
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        super.onTransitionAnimationProgress(state, f, f2);
        if (f2 <= 0.0f) {
            removeGhost();
            updateBackground(state, true);
            return;
        }
        if (this.ghostView == null) {
            String tag = getTag();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(tag, "-->addGhost: ", "SystemUi--QuickSettings");
            }
            GhostView ghostViewAddGhost = GhostView.addGhost(this.fromView, getTransitionContainer());
            ghostViewAddGhost.forceHasOverlappingRendering(false);
            this.ghostView = ghostViewAddGhost;
        }
        updateBackground(state, false);
        View view = this.ghostView;
        if (view == null) {
            return;
        }
        if (view.getVisibility() == 4) {
            view.setVisibility(0);
        }
        fillViewState(this.ghostedViewState);
        if (((ScenarioDialogAnimationManager.State) state).getHasShareElement()) {
            float width = (state.getWidth() - this.ghostedViewState.getWidth()) / 2.0f;
            float scaleX = this.fromView.getScaleX();
            float scaleY = this.fromView.getScaleY();
            View icon = getIcon();
            if (icon != null) {
                Object parent = icon.getParent();
                View view2 = parent instanceof View ? (View) parent : null;
                if (view2 != null) {
                    ScenarioDialogAnimationManager.State state2 = (ScenarioDialogAnimationManager.State) state;
                    float fExactCenterX = ((state2.getIconRect().exactCenterX() - width) / scaleX) - ((view2.getRight() + view2.getLeft()) / 2.0f);
                    float fExactCenterY = (state2.getIconRect().exactCenterY() / scaleY) - ((view2.getBottom() + view2.getTop()) / 2.0f);
                    icon.setTranslationX(fExactCenterX);
                    icon.setTranslationY(fExactCenterY);
                    icon.setScaleX((state2.getIconRect().width() / scaleX) / view2.getWidth());
                    icon.setScaleY((state2.getIconRect().height() / scaleY) / view2.getHeight());
                }
            }
            View title = getTitle();
            if (title != null) {
                Object parent2 = title.getParent();
                View view3 = parent2 instanceof View ? (View) parent2 : null;
                if (view3 != null) {
                    ScenarioDialogAnimationManager.State state3 = (ScenarioDialogAnimationManager.State) state;
                    float fExactCenterX2 = ((state3.getTitleRect().exactCenterX() - width) / scaleX) - ((view3.getRight() + view3.getLeft()) / 2.0f);
                    float fExactCenterY2 = (state3.getTitleRect().exactCenterY() / scaleY) - ((view3.getBottom() + view3.getTop()) / 2.0f);
                    title.setTranslationX(fExactCenterX2);
                    title.setTranslationY(fExactCenterY2);
                    title.setScaleX((state3.getTitleRect().width() / scaleX) / view3.getWidth());
                    title.setScaleY((state3.getTitleRect().height() / scaleY) / view3.getHeight());
                    float right = ((state3.getTitleRect().right - width) / scaleX) - view3.getRight();
                    for (View view4 : this.privateIcons) {
                        view4.setTranslationX(right);
                        view4.setTranslationY(fExactCenterY2);
                    }
                }
            }
        }
        GhostView.calculateMatrix(this.fromView, getTransitionContainer(), this.ghostViewMatrix);
        ScenarioDialogAnimationManager.State state4 = (ScenarioDialogAnimationManager.State) state;
        this.ghostViewMatrix.postTranslate(state.getCenterX() - this.ghostedViewState.getCenterX(), state4.getTranslationY() + (((this.fromView.getHeight() / 2.0f) + state.getTop()) - this.ghostedViewState.getCenterY()));
        view.setAnimationMatrix(this.ghostViewMatrix);
        view.setTransitionAlpha(state4.getTransitionAlpha() * state.getAlpha());
        view.invalidate();
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public /* bridge */ /* synthetic */ void onTransitionAnimationProgressStateCustomOverlay(float f, TransitionAnimator.Interpolators interpolators, TransitionAnimator.State state, TransitionAnimator.State state2, TransitionAnimator.State state3) {
        super.onTransitionAnimationProgressStateCustomOverlay(f, interpolators, state, state2, state3);
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public final void onTransitionAnimationStart(boolean z) {
        super.onTransitionAnimationStart(z);
        FrameLayout frameLayout = new FrameLayout(getTransitionContainer().getContext());
        getTransitionContainerOverlay().add(frameLayout);
        frameLayout.forceHasOverlappingRendering(false);
        this.backgroundView = frameLayout;
    }

    public final void removeGhost() {
        if (this.ghostView != null) {
            String tag = getTag();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(tag, "-->removeGhost: ", "SystemUi--QuickSettings");
            }
            GhostView.removeGhost(this.fromView);
            this.ghostView = null;
        }
    }

    public final void updateBackground(TransitionAnimator.State state, boolean z) {
        View view = this.backgroundHost;
        if (z) {
            view.setBackground(getBackground());
            FrameLayout frameLayout = this.backgroundView;
            if (frameLayout != null) {
                frameLayout.setBackground(null);
                return;
            }
            return;
        }
        view.setBackground(null);
        getTransitionContainer().getLocationOnScreen(this.transitionContainerLocation);
        FrameLayout frameLayout2 = this.backgroundView;
        if (frameLayout2 != null) {
            frameLayout2.setBackground(getBackground());
            ScenarioDialogAnimationManager.State state2 = (ScenarioDialogAnimationManager.State) state;
            frameLayout2.setAlpha(state2.getTransitionAlpha() * state.getAlpha());
            frameLayout2.setLeftTopRightBottom(state.getLeft() - this.transitionContainerLocation[0], state.getTop() - this.transitionContainerLocation[1], state.getRight() - this.transitionContainerLocation[0], state.getBottom() - this.transitionContainerLocation[1]);
            frameLayout2.setTranslationY(state2.getTranslationY());
        }
    }

    @Override // com.oplus.systemui.qs.scenario.domain.animation.ScenarioTransitionAnimatorController
    public void fillViewState(ScenarioDialogAnimationManager.State state) {
        fillViewState(this.fromView, state);
    }
}
