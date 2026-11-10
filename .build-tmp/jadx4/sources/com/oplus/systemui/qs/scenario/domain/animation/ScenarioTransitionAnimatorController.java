package com.oplus.systemui.qs.scenario.domain.animation;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.window.WindowAnimationState;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.animation.TransitionAnimator;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.outline.PathCornerDrawable;
import com.oplusos.systemui.common.outline.RoundRectOutlineProvider;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function0;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ScenarioTransitionAnimatorController implements TransitionAnimator.Controller {
    private final Drawable background;
    private RoundRectOutlineProvider blockRadiusProvider;
    private TransitionAnimator.State currentState;
    private final View icon;
    private final Rect originRect;
    private final View title;
    private ViewGroup transitionContainer;
    private final View transitionView;
    private final String tag = getClass().getSimpleName();
    private final int[] tmpLocation = new int[2];
    public final boolean isLaunching = true;
    private ScenarioDialogAnimationManager.State originState = new ScenarioDialogAnimationManager.State();

    public ScenarioTransitionAnimatorController(View view) {
        this.transitionView = view;
        this.transitionContainer = (ViewGroup) view.getRootView();
        this.icon = view.findViewById(2131364996);
        this.title = view.findViewById(2131365614);
        this.originRect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        this.background = view.getBackground();
    }

    private final float getCurrentTopCornerRadius() {
        Object background = getBackground();
        if (background instanceof GradientDrawable) {
            return ((GradientDrawable) background).getCornerRadius();
        }
        if (background instanceof PathCornerDrawable) {
            return ((PathCornerDrawable) background).getCornerRadius();
        }
        if (background instanceof AutoBlurDrawable) {
            return ((AutoBlurDrawable) background).getViewBlurProxy().getBlurConfig().getCornerRadius();
        }
        return 0.0f;
    }

    private final Float getCurrentTopCornerWeight() {
        Object background = getBackground();
        if (background instanceof PathCornerDrawable) {
            return ((PathCornerDrawable) background).getCornerWeight();
        }
        return background instanceof AutoBlurDrawable ? ((AutoBlurDrawable) background).getViewBlurProxy().getBlurConfig().getRadiusWeight() : Float.valueOf(0.0f);
    }

    public TransitionAnimator.State createAnimatorState() {
        float currentTopCornerRadius = getCurrentTopCornerRadius();
        ScenarioDialogAnimationManager.State state = new ScenarioDialogAnimationManager.State();
        state.setTopCornerRadius(currentTopCornerRadius);
        state.setBottomCornerRadius(currentTopCornerRadius);
        Float currentTopCornerWeight = getCurrentTopCornerWeight();
        state.setCornerWeight(currentTopCornerWeight != null ? currentTopCornerWeight.floatValue() : 0.0f);
        fillViewState(state);
        state.setHasShareElement((state.getIconRect().isEmpty() || state.getTitleRect().isEmpty()) ? false : true);
        String str = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            int left = state.getLeft();
            int top = state.getTop();
            int right = state.getRight();
            int bottom = state.getBottom();
            float topCornerRadius = state.getTopCornerRadius();
            float bottomCornerRadius = state.getBottomCornerRadius();
            float cornerWeight = state.getCornerWeight();
            Rect iconRect = state.getIconRect();
            Rect titleRect = state.getTitleRect();
            StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("createAnimatorState: left = ", left, top, ", top = ", ", right = ");
            RectList$.ExternalSyntheticOutline0.m(right, bottom, ", bottom = ", ", radius = ", sbM2);
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM2, topCornerRadius, "-", bottomCornerRadius, ", cornerWeight = ");
            sbM2.append(cornerWeight);
            sbM2.append(", iconRect = ");
            sbM2.append(iconRect);
            sbM2.append(", titleRect = ");
            sbM2.append(titleRect);
            sbM.append(sbM2.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        this.originState = state.copy();
        return state;
    }

    public void fillShareElementState(ScenarioDialogAnimationManager.State state) {
        View view = this.icon;
        if (view != null) {
            state.getIconRect().set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        View view2 = this.title;
        if (view2 != null) {
            state.getTitleRect().set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
        }
    }

    public void fillViewState(View view, ScenarioDialogAnimationManager.State state) {
        view.getLocationOnScreen(this.tmpLocation);
        state.setTop(this.tmpLocation[1]);
        state.setBottom(MathKt.roundToInt((view.getScaleY() * view.getHeight()) + this.tmpLocation[1]));
        state.setLeft(this.tmpLocation[0]);
        state.setRight(MathKt.roundToInt((view.getScaleX() * view.getWidth()) + this.tmpLocation[0]));
        fillShareElementState(state);
    }

    public Drawable getBackground() {
        return this.background;
    }

    public /* bridge */ /* synthetic */ float[] getCouiSpringConfig() {
        return super.getCouiSpringConfig();
    }

    public final TransitionAnimator.State getCurrentState() {
        return this.currentState;
    }

    public final View getIcon() {
        return this.icon;
    }

    public /* bridge */ /* synthetic */ boolean getManagesLaunchWindowLayerLocally() {
        return super.getManagesLaunchWindowLayerLocally();
    }

    public /* bridge */ /* synthetic */ View getOpeningWindowSyncView() {
        return super.getOpeningWindowSyncView();
    }

    public Rect getOriginRect() {
        return this.originRect;
    }

    public final ScenarioDialogAnimationManager.State getOriginState() {
        return this.originState;
    }

    public /* bridge */ /* synthetic */ SurfaceControl getShadeSurfaceControl() {
        return super.getShadeSurfaceControl();
    }

    public final String getTag() {
        return this.tag;
    }

    public final View getTitle() {
        return this.title;
    }

    public final int[] getTmpLocation() {
        return this.tmpLocation;
    }

    public ViewGroup getTransitionContainer() {
        return this.transitionContainer;
    }

    public final View getTransitionView() {
        return this.transitionView;
    }

    public /* bridge */ /* synthetic */ WindowAnimationState getWindowAnimatorState() {
        return super.getWindowAnimatorState();
    }

    public /* bridge */ /* synthetic */ boolean getWindowFadeInAfterDrawable() {
        return super.getWindowFadeInAfterDrawable();
    }

    public final boolean isLaunching() {
        return this.isLaunching;
    }

    public /* bridge */ /* synthetic */ boolean onCustomStateToWindowBackgroundLayer(GradientDrawable gradientDrawable, TransitionAnimator.State state, float f, View view, Function0 function0, boolean z, boolean z2, boolean z3) {
        return super.onCustomStateToWindowBackgroundLayer(gradientDrawable, state, f, view, function0, z, z2, z3);
    }

    public void onTransitionAnimationEnd(boolean z) {
        String str = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->onTransitionAnimationEnd: ", "SystemUi--QuickSettings");
        }
        Object background = getBackground();
        if (background instanceof PathCornerDrawable) {
            ((PathCornerDrawable) background).setBlockPathProvider(null);
            this.blockRadiusProvider = null;
        }
    }

    public void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        this.currentState = state;
        Object background = getBackground();
        if (background instanceof PathCornerDrawable) {
            if (this.blockRadiusProvider == null) {
                PathCornerDrawable pathCornerDrawable = (PathCornerDrawable) background;
                RoundRectOutlineProvider roundRectOutlineProvider = new RoundRectOutlineProvider(pathCornerDrawable.getCornerRadius(), pathCornerDrawable.getCornerWeight());
                this.blockRadiusProvider = roundRectOutlineProvider;
                pathCornerDrawable.setBlockPathProvider(roundRectOutlineProvider);
            }
            RoundRectOutlineProvider roundRectOutlineProvider2 = this.blockRadiusProvider;
            if (roundRectOutlineProvider2 != null) {
                roundRectOutlineProvider2.update(state.getTopCornerRadius(), Float.valueOf(state.getCornerWeight()));
            }
            ((PathCornerDrawable) background).invalidatePath();
        }
    }

    public /* bridge */ /* synthetic */ void onTransitionAnimationProgressStateCustomOverlay(float f, TransitionAnimator.Interpolators interpolators, TransitionAnimator.State state, TransitionAnimator.State state2, TransitionAnimator.State state3) {
        super.onTransitionAnimationProgressStateCustomOverlay(f, interpolators, state, state2, state3);
    }

    public void onTransitionAnimationStart(boolean z) {
        String str = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->onTransitionAnimationStart: ", "SystemUi--QuickSettings");
        }
    }

    public final void setCurrentState(TransitionAnimator.State state) {
        this.currentState = state;
    }

    public final void setOriginState(ScenarioDialogAnimationManager.State state) {
        this.originState = state;
    }

    public void setTransitionContainer(ViewGroup viewGroup) {
        this.transitionContainer = viewGroup;
    }

    public void fillViewState(ScenarioDialogAnimationManager.State state) {
        fillViewState(this.transitionView, state);
    }
}
