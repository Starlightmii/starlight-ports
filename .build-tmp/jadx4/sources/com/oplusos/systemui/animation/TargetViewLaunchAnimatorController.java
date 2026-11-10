package com.oplusos.systemui.animation;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.window.WindowAnimationState;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.wm.shell.shared.compat.AnimatedSurface;
import com.oplus.graphics.OplusOutlineAdapter;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.outline.PathCornerDrawable;
import com.oplusos.systemui.common.outline.RoundRectOutlineProvider;
import java.util.Arrays;
import java.util.LinkedList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class TargetViewLaunchAnimatorController implements ActivityTransitionAnimator.Controller {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TargetViewLaunchAnimatorController";
    private RoundRectOutlineProvider blockRadiusProvider;
    public final int containerId;
    private TransitionAnimator.State currentState;
    public final boolean isLaunching;
    private OplusOutlineAdapter oplusOutlineAdapter;
    private Outline outlineLast;
    public float radius;
    private final Function0 surfaceControlProxy;
    private View targetBlurBackgroundView;
    private final View targetView;
    private final int[] targetViewLocation = new int[2];
    private TransitionAnimator.State targetViewState;
    private final SurfaceControl.Transaction transaction;
    private ViewGroup transitionContainer;
    public float weight;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int dp2px(Context context, float f) {
            return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
        }
    }

    public TargetViewLaunchAnimatorController(View view, Function0 function0, boolean z, int i) {
        float cornerRadius;
        Float cornerWeight;
        this.targetView = view;
        this.surfaceControlProxy = function0;
        this.isLaunching = z;
        this.containerId = i;
        this.transitionContainer = (ViewGroup) view.getRootView();
        if (!(view.getBackground() instanceof AutoBlurDrawable) && !(view.getBackground() instanceof PathCornerDrawable)) {
            LinkedList linkedList = new LinkedList();
            linkedList.add(view);
            while (true) {
                if (linkedList.isEmpty()) {
                    view = null;
                    break;
                }
                view = (View) linkedList.removeFirst();
                if ((view != null ? view.getBackground() : null) instanceof AutoBlurDrawable) {
                    break;
                }
                if ((view != null ? view.getBackground() : null) instanceof PathCornerDrawable) {
                    break;
                }
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        linkedList.add(viewGroup.getChildAt(i2));
                    }
                }
            }
        }
        this.targetBlurBackgroundView = view;
        View view2 = this.targetView;
        Object objFindBackgroundDrawable = (view == null || (objFindBackgroundDrawable = view.getBackground()) == null) ? findBackgroundDrawable(view2) : objFindBackgroundDrawable;
        if (objFindBackgroundDrawable instanceof AutoBlurDrawable) {
            cornerRadius = ((AutoBlurDrawable) objFindBackgroundDrawable).getViewBlurProxy().getBlurConfig().getCornerRadius();
        } else if (objFindBackgroundDrawable instanceof PathCornerDrawable) {
            cornerRadius = ((PathCornerDrawable) objFindBackgroundDrawable).getCornerRadius();
        } else {
            cornerRadius = objFindBackgroundDrawable instanceof GradientDrawable ? ((GradientDrawable) objFindBackgroundDrawable).getCornerRadius() : Companion.dp2px(view2.getContext(), 53.0f);
        }
        this.radius = cornerRadius;
        View view3 = this.targetView;
        View view4 = this.targetBlurBackgroundView;
        Object objFindBackgroundDrawable2 = (view4 == null || (objFindBackgroundDrawable2 = view4.getBackground()) == null) ? findBackgroundDrawable(view3) : objFindBackgroundDrawable2;
        float fFloatValue = 3.0f;
        if (objFindBackgroundDrawable2 instanceof AutoBlurDrawable) {
            Float radiusWeight = ((AutoBlurDrawable) objFindBackgroundDrawable2).getViewBlurProxy().getBlurConfig().getRadiusWeight();
            if (radiusWeight != null) {
                fFloatValue = radiusWeight.floatValue();
            }
        } else if ((objFindBackgroundDrawable2 instanceof PathCornerDrawable) && (cornerWeight = ((PathCornerDrawable) objFindBackgroundDrawable2).getCornerWeight()) != null) {
            fFloatValue = cornerWeight.floatValue();
        }
        this.weight = fFloatValue;
        this.targetViewState = createAnimatorState();
        this.transaction = new SurfaceControl.Transaction();
        View view5 = this.targetBlurBackgroundView;
        Drawable background = view5 != null ? view5.getBackground() : null;
        if (background instanceof PathCornerDrawable) {
            PathCornerDrawable pathCornerDrawable = (PathCornerDrawable) background;
            this.blockRadiusProvider = new RoundRectOutlineProvider(pathCornerDrawable.getCornerRadius(), pathCornerDrawable.getCornerWeight());
        }
        Log.d(TAG, "init: background = " + background);
    }

    public static final int dp2px(Context context, float f) {
        return Companion.dp2px(context, f);
    }

    public static GradientDrawable findBackgroundDrawable(View view) {
        if (view.getBackground() instanceof GradientDrawable) {
            return (GradientDrawable) view.getBackground();
        }
        LinkedList linkedList = new LinkedList();
        linkedList.add(view);
        while (!linkedList.isEmpty()) {
            View view2 = (View) linkedList.removeFirst();
            if (view2.getBackground() instanceof GradientDrawable) {
                return (GradientDrawable) view2.getBackground();
            }
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    linkedList.add(viewGroup.getChildAt(i));
                }
            }
        }
        return null;
    }

    public TransitionAnimator.State createAnimatorState() {
        float f = this.radius;
        TransitionAnimator.State state = new TransitionAnimator.State(0, 0, 0, 0, f, f, this.weight, 15);
        fillGhostedViewState(state);
        return state;
    }

    public /* bridge */ /* synthetic */ boolean customApplyStateToWindow(AnimatedSurface animatedSurface, TransitionAnimator.State state, float f, SyncRtSurfaceTransactionApplier syncRtSurfaceTransactionApplier, Matrix matrix, View view) {
        return super.customApplyStateToWindow(animatedSurface, state, f, syncRtSurfaceTransactionApplier, matrix, view);
    }

    public final void fillGhostedViewState(TransitionAnimator.State state) {
        int translationX;
        int translationY;
        View viewFindViewById = this.targetView.findViewById(this.containerId);
        if (viewFindViewById == null) {
            viewFindViewById = this.targetView;
        }
        viewFindViewById.getLocationOnScreen(this.targetViewLocation);
        if (viewFindViewById.equals(this.targetView)) {
            translationX = (int) (this.targetView.getTranslationX() + ((this.targetView.getWidth() - (this.targetView.getScaleX() * this.targetView.getWidth())) / 2.0f));
        } else {
            translationX = (int) (this.targetView.getTranslationX() + viewFindViewById.getTranslationX() + ((viewFindViewById.getWidth() - (viewFindViewById.getScaleX() * viewFindViewById.getWidth())) / 2.0f));
        }
        if (viewFindViewById.equals(this.targetView)) {
            translationY = (int) (this.targetView.getTranslationY() + ((this.targetView.getHeight() - (this.targetView.getScaleY() * this.targetView.getHeight())) / 2.0f));
        } else {
            translationY = (int) (this.targetView.getTranslationY() + viewFindViewById.getTranslationY() + ((viewFindViewById.getHeight() - (viewFindViewById.getScaleY() * viewFindViewById.getHeight())) / 2.0f));
        }
        state.setTop(this.targetViewLocation[1] - translationY);
        state.setBottom((viewFindViewById.getHeight() + this.targetViewLocation[1]) - translationY);
        state.setLeft(this.targetViewLocation[0] - translationX);
        state.setRight((viewFindViewById.getWidth() + this.targetViewLocation[0]) - translationX);
        int left = state.getLeft();
        int top = state.getTop();
        int right = state.getRight();
        int bottom = state.getBottom();
        float topCornerRadius = state.getTopCornerRadius();
        float bottomCornerRadius = state.getBottomCornerRadius();
        String string = Arrays.toString(this.targetViewLocation);
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("fillGhostedViewState state: left = ", left, top, ", top = ", ", right = ");
        RectList$.ExternalSyntheticOutline0.m(right, bottom, ", bottom = ", ", radius = ", sbM);
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, topCornerRadius, "-", bottomCornerRadius, ", targetViewLocation = ");
        sbM.append(string);
        Log.i(TAG, sbM.toString());
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

    public final TransitionAnimator.State getCurrentState() {
        return this.currentState;
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

    public final OplusOutlineAdapter getOplusOutlineAdapter() {
        return this.oplusOutlineAdapter;
    }

    public final Outline getOutlineLast() {
        return this.outlineLast;
    }

    public final float getRadius() {
        return this.radius;
    }

    public /* bridge */ /* synthetic */ SurfaceControl getShadeSurfaceControl() {
        return super/*com.android.systemui.animation.TransitionAnimator.Controller*/.getShadeSurfaceControl();
    }

    public /* bridge */ /* synthetic */ PointF getStartVelocity() {
        return super.getStartVelocity();
    }

    public final View getTargetBlurBackgroundView() {
        return this.targetBlurBackgroundView;
    }

    public final TransitionAnimator.State getTargetViewState() {
        return this.targetViewState;
    }

    public ViewGroup getTransitionContainer() {
        return this.transitionContainer;
    }

    public /* bridge */ /* synthetic */ ActivityTransitionAnimator.TransitionCookie getTransitionCookie() {
        return super.getTransitionCookie();
    }

    public final float getWeight() {
        return this.weight;
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

    public boolean isLaunching() {
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

    /* JADX WARN: Multi-variable type inference failed */
    public void onTransitionAnimationEnd(boolean z) {
        Log.i(TAG, "onLaunchAnimationEnd targetView = " + this.targetView + ", isExpandingFullyAbove = " + z);
        this.targetView.setClipToOutline(false);
        Function0 function0 = this.surfaceControlProxy;
        SurfaceControl surfaceControl = function0 != null ? (SurfaceControl) function0.invoke() : null;
        if (isLaunching() && surfaceControl != null && surfaceControl.isValid()) {
            this.transaction.show(surfaceControl);
            this.transaction.apply();
        }
        View view = this.targetBlurBackgroundView;
        Drawable background = view != null ? view.getBackground() : 0;
        View view2 = this.targetBlurBackgroundView;
        float scaleX = view2 != null ? view2.getScaleX() : 0.0f;
        if ((background instanceof AutoBlurDrawable) && view2 != null) {
            AutoBlurDrawable autoBlurDrawable = (AutoBlurDrawable) background;
            if (Intrinsics.areEqual(autoBlurDrawable.getViewBlurProxy().getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE) && scaleX != 0.0f) {
                autoBlurDrawable.setBounds(0, 0, view2.getWidth(), view2.getHeight());
            }
        }
        if (!(background instanceof PathCornerDrawable) || view2 == null) {
            return;
        }
        background.setBounds(0, 0, view2.getWidth(), view2.getHeight());
        ((PathCornerDrawable) background).setBlockPathProvider(null);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationEndStateCustom(TransitionAnimator.State state) {
        super.onTransitionAnimationEndStateCustom(state);
    }

    public void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        this.currentState = state;
        updateTargetView(state, f, f2);
        updateContainerView(state, f, f2);
        updateTargetViewBackground(state, state.getTopCornerRadius(), state.getCornerWeight());
        updateSurfaceTransaction(state, f);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationProgressStateCustomOverlay(float f, TransitionAnimator.Interpolators interpolators, TransitionAnimator.State state, TransitionAnimator.State state2, TransitionAnimator.State state3) {
        super/*com.android.systemui.animation.TransitionAnimator.Controller*/.onTransitionAnimationProgressStateCustomOverlay(f, interpolators, state, state2, state3);
    }

    public void onTransitionAnimationStart(boolean z) {
        if (!(this.targetView.getParent() instanceof ViewGroup)) {
            Log.w(TAG, "Skipping animation as targetView is not attached to a ViewGroup");
            return;
        }
        this.targetView.setOutlineProvider(new ViewOutlineProvider() { // from class: com.oplusos.systemui.animation.TargetViewLaunchAnimatorController.onTransitionAnimationStart.1
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                TransitionAnimator.State currentState = TargetViewLaunchAnimatorController.this.getCurrentState();
                if (currentState != null) {
                    TargetViewLaunchAnimatorController targetViewLaunchAnimatorController = TargetViewLaunchAnimatorController.this;
                    if (view != null) {
                        View targetBlurBackgroundView = targetViewLaunchAnimatorController.getTargetBlurBackgroundView();
                        Drawable background = targetBlurBackgroundView != null ? targetBlurBackgroundView.getBackground() : null;
                        if ((!(background instanceof AutoBlurDrawable) || !Intrinsics.areEqual(((AutoBlurDrawable) background).getViewBlurProxy().getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) && !(background instanceof PathCornerDrawable)) {
                            if (outline != null) {
                                outline.setRoundRect((currentState.getLeft() - view.getLeft()) - ((int) view.getTranslationX()), (currentState.getTop() - view.getTop()) - ((int) view.getTranslationY()), currentState.getWidth() + ((currentState.getLeft() - view.getLeft()) - ((int) view.getTranslationX())), currentState.getHeight() + ((currentState.getTop() - view.getTop()) - ((int) view.getTranslationY())), currentState.getTopCornerRadius());
                                return;
                            }
                            return;
                        }
                        if (targetViewLaunchAnimatorController.getOplusOutlineAdapter() == null || !Intrinsics.areEqual(targetViewLaunchAnimatorController.getOutlineLast(), outline)) {
                            targetViewLaunchAnimatorController.setOutlineLast(outline);
                            targetViewLaunchAnimatorController.setOplusOutlineAdapter(new OplusOutlineAdapter(outline, 1));
                        }
                        OplusOutlineAdapter oplusOutlineAdapter = targetViewLaunchAnimatorController.getOplusOutlineAdapter();
                        if (oplusOutlineAdapter != null) {
                            oplusOutlineAdapter.setSmoothRoundRect((currentState.getLeft() - view.getLeft()) - ((int) view.getTranslationX()), (currentState.getTop() - view.getTop()) - ((int) view.getTranslationY()), currentState.getWidth() + ((currentState.getLeft() - view.getLeft()) - ((int) view.getTranslationX())), currentState.getHeight() + ((currentState.getTop() - view.getTop()) - ((int) view.getTranslationY())), currentState.getTopCornerRadius(), currentState.getCornerWeight());
                        }
                    }
                }
            }
        });
        this.targetView.setClipToOutline(true);
        View view = this.targetBlurBackgroundView;
        Object background = view != null ? view.getBackground() : null;
        if (background instanceof PathCornerDrawable) {
            ((PathCornerDrawable) background).setBlockPathProvider(this.blockRadiusProvider);
        }
        Log.i(TAG, "onLaunchAnimationStart targetView = " + this.targetView + ", isExpandingFullyAbove = " + z);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationStartQs(boolean z, AnimatedSurface animatedSurface, Function1 function1) {
        super.onTransitionAnimationStartQs(z, animatedSurface, function1);
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationStartWithWindow(boolean z, AnimatedSurface animatedSurface) {
        super.onTransitionAnimationStartWithWindow(z, animatedSurface);
    }

    public final void setCurrentState(TransitionAnimator.State state) {
        this.currentState = state;
    }

    public /* bridge */ /* synthetic */ void setFlingToZoomAnimator(Boolean bool) {
        super.setFlingToZoomAnimator(bool);
    }

    public final void setOplusOutlineAdapter(OplusOutlineAdapter oplusOutlineAdapter) {
        this.oplusOutlineAdapter = oplusOutlineAdapter;
    }

    public final void setOutlineLast(Outline outline) {
        this.outlineLast = outline;
    }

    public final void setRadius(float f) {
        this.radius = f;
    }

    public final void setTargetBlurBackgroundView(View view) {
        this.targetBlurBackgroundView = view;
    }

    public final void setTargetViewState(TransitionAnimator.State state) {
        this.targetViewState = state;
    }

    public void setTransitionContainer(ViewGroup viewGroup) {
        this.transitionContainer = viewGroup;
    }

    public final void setWeight(float f) {
        this.weight = f;
    }

    public void updateContainerView(TransitionAnimator.State state, float f, float f2) {
        View viewFindViewById = this.targetView.findViewById(this.containerId);
        float width = state.getWidth() / this.targetViewState.getWidth();
        float height = state.getHeight() / this.targetViewState.getHeight();
        if (width > Float.MAX_VALUE || height > Float.MAX_VALUE) {
            return;
        }
        if (width < 0.45f && height < 0.45f) {
            if (viewFindViewById != null) {
                viewFindViewById.setTranslationX(((this.targetViewState.getWidth() * 0.45f) - state.getWidth()) / 2.0f);
            }
            if (viewFindViewById != null) {
                viewFindViewById.setTranslationY(((this.targetViewState.getHeight() * 0.45f) - state.getHeight()) / 2.0f);
            }
            width = 0.45f;
        } else if (width > height) {
            if (viewFindViewById != null) {
                viewFindViewById.setTranslationX(0.0f);
            }
            if (viewFindViewById != null) {
                viewFindViewById.setTranslationY(((this.targetViewState.getHeight() * width) - state.getHeight()) / 2.0f);
            }
        } else {
            if (viewFindViewById != null) {
                viewFindViewById.setTranslationY(0.0f);
            }
            if (viewFindViewById != null) {
                viewFindViewById.setTranslationX(((this.targetViewState.getWidth() * height) - state.getWidth()) / 2.0f);
            }
            width = height;
        }
        if (viewFindViewById != null) {
            viewFindViewById.setScaleX(width);
        }
        if (viewFindViewById != null) {
            viewFindViewById.setScaleY(width);
        }
    }

    public final void updateSurfaceTransaction(TransitionAnimator.State state, float f) {
        Function0 function0 = this.surfaceControlProxy;
        SurfaceControl surfaceControl = function0 != null ? (SurfaceControl) function0.invoke() : null;
        if (surfaceControl == null || !surfaceControl.isValid()) {
            return;
        }
        boolean zIsLaunching = isLaunching();
        SurfaceControl.Transaction transaction = this.transaction;
        if (zIsLaunching) {
            transaction.setAlpha(surfaceControl, 0.0f);
            if (state.getAlpha() / 3.0f > 0.32f) {
                this.transaction.setAlpha(surfaceControl, state.getAlpha());
            }
        } else {
            transaction.setAlpha(surfaceControl, state.getAlpha());
            if (state.getAlpha() < 0.2f) {
                this.transaction.hide(surfaceControl);
            }
        }
        this.transaction.apply();
    }

    public void updateTargetView(TransitionAnimator.State state, float f, float f2) {
        int left = state.getLeft() - this.targetViewState.getLeft();
        int right = state.getRight() - this.targetViewState.getRight();
        int top = state.getTop() - this.targetViewState.getTop();
        int bottom = state.getBottom() - this.targetViewState.getBottom();
        this.targetView.setTranslationX((left + right) / 2.0f);
        this.targetView.setTranslationY((top + bottom) / 2.0f);
        this.targetView.setAlpha(state.getAlpha());
    }

    public void updateTargetViewBackground(TransitionAnimator.State state, float f, float f2) {
        View view = this.targetBlurBackgroundView;
        Object background = view != null ? view.getBackground() : null;
        View view2 = this.targetBlurBackgroundView;
        float scaleX = view2 != null ? view2.getScaleX() : 0.0f;
        if ((background instanceof AutoBlurDrawable) && view2 != null) {
            AutoBlurDrawable autoBlurDrawable = (AutoBlurDrawable) background;
            if (Intrinsics.areEqual(autoBlurDrawable.getViewBlurProxy().getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE) && scaleX != 0.0f) {
                Drawable background2 = view2.getBackground();
                if (background2 != null) {
                    background2.setBounds(0, 0, MathKt.roundToInt(view2.getWidth()), MathKt.roundToInt(view2.getHeight()));
                }
                autoBlurDrawable.getViewBlurProxy().getBlurConfig().setCornerRadius(f);
                autoBlurDrawable.getViewBlurProxy().getBlurConfig().setRadiusWeight(Float.valueOf(f2));
                autoBlurDrawable.getViewBlurProxy().applyBlurConfig();
            }
        }
        if ((background instanceof PathCornerDrawable) && view2 != null) {
            Drawable background3 = view2.getBackground();
            if (background3 != null) {
                background3.setBounds(0, 0, MathKt.roundToInt(view2.getWidth()), MathKt.roundToInt(view2.getHeight()));
            }
            RoundRectOutlineProvider roundRectOutlineProvider = this.blockRadiusProvider;
            if (roundRectOutlineProvider != null) {
                roundRectOutlineProvider.update(f, Float.valueOf(f2));
            }
            ((PathCornerDrawable) background).invalidatePath();
        }
        this.targetView.invalidateOutline();
    }
}
