package com.oplusos.systemui.animation;

import android.content.ComponentName;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import android.util.MathUtils;
import android.view.SurfaceControl;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.window.WindowAnimationState;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.MediaTransitionCornerRadii;
import com.android.systemui.animation.MediaViewProvider;
import com.android.systemui.animation.OplusMediaPanelAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.wm.shell.shared.compat.AnimatedSurface;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class MediaDialogLaunchAnimatorController extends TargetViewLaunchAnimatorController implements TransitionFrameCornerSync {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MediaDialogLaunchAnimatorController";
    private ViewGroup carouselLayoutView;
    private Drawable carouselSavedBackground;
    public float clipContainerCornerRadius;
    public final int containerId;
    private final OplusMediaPanelAnimator.MediaAnimValue currentAnimValue;
    public float frameContainerVisualRadius;
    public final boolean isLaunching;
    private final MediaViewProvider mediaDetail;
    private final MediaViewProvider mediaSimple;
    public final boolean needUseAlpha;
    private OplusMediaPanelAnimator oplusMediaPanelAnimator;
    private final Function0 surfaceControlProxy;
    private final View targetView;
    private ViewGroup targetViewParent;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public MediaDialogLaunchAnimatorController(View view, Function0 function0, boolean z, int i, MediaViewProvider mediaViewProvider, boolean z2, OplusMediaPanelAnimator.MediaAnimValue mediaAnimValue) {
        super(view, function0, z, i);
        this.targetView = view;
        this.surfaceControlProxy = function0;
        this.isLaunching = z;
        this.containerId = i;
        this.mediaSimple = mediaViewProvider;
        this.needUseAlpha = z2;
        this.currentAnimValue = mediaAnimValue;
        this.mediaDetail = view instanceof MediaViewProvider ? (MediaViewProvider) view : null;
    }

    private final View getPageIndicator() {
        View mediaPageIndicator;
        MediaViewProvider mediaViewProvider = this.mediaDetail;
        if (mediaViewProvider != null && (mediaPageIndicator = mediaViewProvider.getMediaPageIndicator()) != null) {
            return mediaPageIndicator;
        }
        OplusMediaPanelAnimator oplusMediaPanelAnimator = this.oplusMediaPanelAnimator;
        if (oplusMediaPanelAnimator != null) {
            return oplusMediaPanelAnimator.getMediaPageIndicator();
        }
        return null;
    }

    public final void applyFrameCornerRadii(TransitionAnimator.State state, float f) {
        float fFloatValue;
        Pair pair;
        Float coverImgBottomRadius;
        Float coverImgBottomRadius2;
        float topCornerRadius = state.getTopCornerRadius();
        ViewGroup viewGroup = this.carouselLayoutView;
        if (viewGroup != null && viewGroup.getWidth() > 0 && viewGroup.getHeight() > 0) {
            float scaleX = viewGroup.getScaleX();
            if (scaleX < 0.01f) {
                scaleX = 0.01f;
            }
            float scaleY = viewGroup.getScaleY();
            if (scaleY < 0.01f) {
                scaleY = 0.01f;
            }
            MediaTransitionCornerRadii.Companion companion = MediaTransitionCornerRadii.Companion;
            int width = viewGroup.getWidth();
            int height = viewGroup.getHeight();
            companion.getClass();
            if (scaleX < 0.01f) {
                scaleX = 0.01f;
            }
            topCornerRadius = Math.min(topCornerRadius, Math.min(width * scaleX, height * (scaleY >= 0.01f ? scaleY : 0.01f)) / 2.0f);
        }
        this.frameContainerVisualRadius = topCornerRadius;
        Float fValueOf = Float.valueOf(0.0f);
        MediaViewProvider mediaViewProvider = this.mediaSimple;
        Float coverImgRadius = mediaViewProvider != null ? mediaViewProvider.getCoverImgRadius() : null;
        MediaViewProvider mediaViewProvider2 = this.mediaDetail;
        Float coverImgRadius2 = mediaViewProvider2 != null ? mediaViewProvider2.getCoverImgRadius() : null;
        if (coverImgRadius == null && coverImgRadius2 == null) {
            pair = TuplesKt.to(fValueOf, fValueOf);
        } else {
            if (!this.isLaunching) {
                f = 1.0f - f;
            }
            if (coverImgRadius != null) {
                fFloatValue = coverImgRadius.floatValue();
            } else {
                coverImgRadius2.getClass();
                fFloatValue = coverImgRadius2.floatValue();
            }
            float fFloatValue2 = coverImgRadius2 != null ? coverImgRadius2.floatValue() : fFloatValue;
            MediaViewProvider mediaViewProvider3 = this.mediaSimple;
            float fFloatValue3 = (mediaViewProvider3 == null || (coverImgBottomRadius2 = mediaViewProvider3.getCoverImgBottomRadius()) == null) ? fFloatValue : coverImgBottomRadius2.floatValue();
            MediaViewProvider mediaViewProvider4 = this.mediaDetail;
            pair = TuplesKt.to(Float.valueOf(MathUtils.lerp(fFloatValue, fFloatValue2, f)), Float.valueOf(MathUtils.lerp(fFloatValue3, (mediaViewProvider4 == null || (coverImgBottomRadius = mediaViewProvider4.getCoverImgBottomRadius()) == null) ? fFloatValue2 : coverImgBottomRadius.floatValue(), f)));
        }
        float fFloatValue4 = ((Number) pair.component1()).floatValue();
        float fFloatValue5 = ((Number) pair.component2()).floatValue();
        OplusMediaPanelAnimator oplusMediaPanelAnimator = this.oplusMediaPanelAnimator;
        if (oplusMediaPanelAnimator != null) {
            oplusMediaPanelAnimator.setTransitionCornerRadii(new MediaTransitionCornerRadii(this.frameContainerVisualRadius, fFloatValue4, fFloatValue5, 3.0f));
        }
    }

    public final float clampContainerVisualRadius(float f) {
        Float containerCornerRadius;
        MediaViewProvider mediaViewProvider = this.mediaDetail;
        float fFloatValue = (mediaViewProvider == null || (containerCornerRadius = mediaViewProvider.getContainerCornerRadius()) == null) ? this.clipContainerCornerRadius : containerCornerRadius.floatValue();
        return fFloatValue > 0.0f ? Math.min(f, fFloatValue) : f;
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public TransitionAnimator.State createAnimatorState() {
        Float containerCornerRadius;
        TransitionAnimator.State stateCreateAnimatorState = super.createAnimatorState();
        MediaViewProvider mediaViewProvider = this.mediaDetail;
        float fFloatValue = (mediaViewProvider == null || (containerCornerRadius = mediaViewProvider.getContainerCornerRadius()) == null) ? this.clipContainerCornerRadius : containerCornerRadius.floatValue();
        if (fFloatValue > 0.0f) {
            stateCreateAnimatorState.setTopCornerRadius(fFloatValue);
            stateCreateAnimatorState.setBottomCornerRadius(fFloatValue);
        }
        return stateCreateAnimatorState;
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

    public final OplusMediaPanelAnimator getOplusMediaPanelAnimator() {
        return this.oplusMediaPanelAnimator;
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

    public final void onShowMediaOutputDialog() {
        ViewGroup viewGroup = this.targetViewParent;
        if (viewGroup != null) {
            viewGroup.setOutlineProvider(null);
            viewGroup.setClipToOutline(false);
        }
        View pageIndicator = getPageIndicator();
        if (pageIndicator != null) {
            pageIndicator.setTranslationX(0.0f);
            pageIndicator.setTranslationY(0.0f);
        }
        this.targetView.setOutlineProvider(null);
        this.targetView.setClipToOutline(false);
        ViewGroup viewGroup2 = this.carouselLayoutView;
        if (viewGroup2 != null) {
            viewGroup2.setOutlineProvider(null);
        }
        ViewGroup viewGroup3 = this.carouselLayoutView;
        if (viewGroup3 != null) {
            viewGroup3.setClipToOutline(false);
        }
        MediaViewProvider mediaViewProvider = this.mediaDetail;
        if (mediaViewProvider != null) {
            mediaViewProvider.restoreContainerMasterClip();
        }
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ void onTransitionAnimationCancelled(Boolean bool) {
        super.onTransitionAnimationCancelled(bool);
    }

    public final void onTransitionAnimationEnd(boolean z, boolean z2) {
        onTransitionAnimationEnd(z);
        if (!this.isLaunching) {
            Trace.asyncTraceEnd(8L, "appScene_MUSIC_PANEL_CLOSE", 95);
        }
        ViewGroup viewGroup = this.targetViewParent;
        if (viewGroup != null) {
            viewGroup.setOutlineProvider(null);
        }
        ViewGroup viewGroup2 = this.targetViewParent;
        if (viewGroup2 != null) {
            viewGroup2.setClipToOutline(false);
        }
        this.targetView.setClipToOutline(false);
        this.targetView.setOutlineProvider(null);
        ViewGroup viewGroup3 = this.carouselLayoutView;
        if (viewGroup3 != null) {
            this.carouselSavedBackground = null;
            viewGroup3.setBackground(null);
        }
        ViewGroup viewGroup4 = this.carouselLayoutView;
        if (viewGroup4 != null) {
            viewGroup4.setClipChildren(false);
        }
        ViewGroup viewGroup5 = this.carouselLayoutView;
        if (viewGroup5 != null) {
            viewGroup5.setOutlineProvider(null);
        }
        ViewGroup viewGroup6 = this.carouselLayoutView;
        if (viewGroup6 != null) {
            viewGroup6.setClipToOutline(false);
        }
        MediaViewProvider mediaViewProvider = this.mediaDetail;
        if (mediaViewProvider != null) {
            mediaViewProvider.restoreCarouselContainerOutline();
        }
        MediaViewProvider mediaViewProvider2 = this.mediaDetail;
        if (mediaViewProvider2 != null) {
            mediaViewProvider2.restoreContainerMasterClip();
        }
        if (!z2) {
            OplusMediaPanelAnimator oplusMediaPanelAnimator = this.oplusMediaPanelAnimator;
            if (oplusMediaPanelAnimator != null) {
                oplusMediaPanelAnimator.setCurrentAnimValue((OplusMediaPanelAnimator.MediaAnimValue) null);
            }
            OplusMediaPanelAnimator oplusMediaPanelAnimator2 = this.oplusMediaPanelAnimator;
            if (oplusMediaPanelAnimator2 != null) {
                oplusMediaPanelAnimator2.setTransitionCornerRadii((MediaTransitionCornerRadii) null);
            }
            OplusMediaPanelAnimator oplusMediaPanelAnimator3 = this.oplusMediaPanelAnimator;
            if (oplusMediaPanelAnimator3 != null) {
                oplusMediaPanelAnimator3.setCarouselScaleX(1.0f);
            }
            OplusMediaPanelAnimator oplusMediaPanelAnimator4 = this.oplusMediaPanelAnimator;
            if (oplusMediaPanelAnimator4 != null) {
                oplusMediaPanelAnimator4.setCarouselScaleY(1.0f);
            }
        }
        View viewFindViewById = this.targetView.findViewById(this.containerId);
        viewFindViewById.setScaleX(1.0f);
        viewFindViewById.setScaleY(1.0f);
        viewFindViewById.setTranslationX(0.0f);
        viewFindViewById.setTranslationY(0.0f);
        MediaViewProvider mediaViewProvider3 = this.mediaDetail;
        if (mediaViewProvider3 != null) {
            mediaViewProvider3.updateFinishExpand("end");
        }
        OplusMediaPanelAnimator oplusMediaPanelAnimator5 = this.oplusMediaPanelAnimator;
        if (oplusMediaPanelAnimator5 != null) {
            oplusMediaPanelAnimator5.finishExpand(true);
        }
        resetView();
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ void onTransitionAnimationEndStateCustom(TransitionAnimator.State state) {
        super.onTransitionAnimationEndStateCustom(state);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        state.setTopCornerRadius(clampContainerVisualRadius(state.getTopCornerRadius()));
        state.setBottomCornerRadius(clampContainerVisualRadius(state.getBottomCornerRadius()));
        applyFrameCornerRadii(state, f);
        super.onTransitionAnimationProgress(state, f, f2);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ void onTransitionAnimationProgressStateCustomOverlay(float f, TransitionAnimator.Interpolators interpolators, TransitionAnimator.State state, TransitionAnimator.State state2, TransitionAnimator.State state3) {
        super/*com.android.systemui.animation.TransitionAnimator.Controller*/.onTransitionAnimationProgressStateCustomOverlay(f, interpolators, state, state2, state3);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public final void onTransitionAnimationStart(boolean z) {
        View pageIndicator;
        Float containerCornerRadius;
        MediaViewProvider mediaViewProvider;
        super.onTransitionAnimationStart(z);
        MediaViewProvider mediaViewProvider2 = this.mediaSimple;
        boolean z2 = this.isLaunching;
        if (mediaViewProvider2 != null && (mediaViewProvider = this.mediaDetail) != null) {
            OplusMediaPanelAnimator oplusMediaPanelAnimator = new OplusMediaPanelAnimator(mediaViewProvider2, mediaViewProvider);
            oplusMediaPanelAnimator.prepareExpand(z2, this.currentAnimValue);
            if (!z2) {
                Trace.asyncTraceBegin(8L, "appScene_MUSIC_PANEL_CLOSE", 95);
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "currentAnimValue " + oplusMediaPanelAnimator.getCurrentAnimValue(), null, 8);
            this.oplusMediaPanelAnimator = oplusMediaPanelAnimator;
        }
        ViewParent parent = this.targetView.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        this.targetViewParent = viewGroup;
        if (viewGroup == null) {
            Log.w(TAG, "Skipping animation as targetView is not attached to a ViewGroup");
            return;
        }
        MediaViewProvider mediaViewProvider3 = this.mediaDetail;
        if (mediaViewProvider3 != null) {
            mediaViewProvider3.updateFinishExpand("start");
        }
        MediaViewProvider mediaViewProvider4 = this.mediaDetail;
        float fFloatValue = (mediaViewProvider4 == null || (containerCornerRadius = mediaViewProvider4.getContainerCornerRadius()) == null) ? 0.0f : containerCornerRadius.floatValue();
        this.clipContainerCornerRadius = fFloatValue;
        this.frameContainerVisualRadius = fFloatValue;
        ViewGroup viewGroup2 = (ViewGroup) this.targetView.findViewById(this.containerId);
        this.carouselLayoutView = viewGroup2;
        if (viewGroup2 != null) {
            viewGroup2.setOutlineProvider(null);
        }
        ViewGroup viewGroup3 = this.carouselLayoutView;
        if (viewGroup3 != null) {
            viewGroup3.setClipToOutline(false);
        }
        ViewGroup viewGroup4 = this.carouselLayoutView;
        if (viewGroup4 != null) {
            viewGroup4.setClipChildren(false);
        }
        ViewGroup viewGroup5 = this.carouselLayoutView;
        if (viewGroup5 != null) {
            if (this.carouselSavedBackground == null) {
                this.carouselSavedBackground = viewGroup5.getBackground();
            }
            viewGroup5.setBackground(null);
        }
        this.targetView.setClipToOutline(false);
        this.targetView.setOutlineProvider(null);
        ViewGroup viewGroup6 = this.targetViewParent;
        if (viewGroup6 != null) {
            viewGroup6.setClipToOutline(false);
        }
        ViewGroup viewGroup7 = this.targetViewParent;
        if (viewGroup7 != null) {
            viewGroup7.setOutlineProvider(null);
        }
        MediaViewProvider mediaViewProvider5 = this.mediaDetail;
        if (mediaViewProvider5 != null) {
            mediaViewProvider5.prepareContainerMasterClip();
        }
        if (z2 && (pageIndicator = getPageIndicator()) != null) {
            View view = pageIndicator.getVisibility() == 0 ? pageIndicator : null;
            if (view != null) {
                view.setAlpha(0.0f);
            }
        }
        Log.i(TAG, "onLaunchAnimationStart targetViewParent = " + this.targetViewParent + ", isExpandingFullyAbove = " + z);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ void onTransitionAnimationStartQs(boolean z, AnimatedSurface animatedSurface, Function1 function1) {
        super.onTransitionAnimationStartQs(z, animatedSurface, function1);
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ void onTransitionAnimationStartWithWindow(boolean z, AnimatedSurface animatedSurface) {
        super.onTransitionAnimationStartWithWindow(z, animatedSurface);
    }

    @Override // com.oplusos.systemui.animation.TransitionFrameCornerSync
    public void preSyncFrameCornerRadii(TransitionAnimator.State state, float f) {
        state.setTopCornerRadius(clampContainerVisualRadius(state.getTopCornerRadius()));
        state.setBottomCornerRadius(clampContainerVisualRadius(state.getBottomCornerRadius()));
        applyFrameCornerRadii(state, f);
    }

    public final void resetView() {
        if (this.isLaunching) {
            MediaViewProvider mediaViewProvider = this.mediaDetail;
            if (mediaViewProvider != null) {
                mediaViewProvider.resetView();
                return;
            }
            return;
        }
        MediaViewProvider mediaViewProvider2 = this.mediaSimple;
        if (mediaViewProvider2 != null) {
            mediaViewProvider2.resetView();
        }
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public /* bridge */ /* synthetic */ void setFlingToZoomAnimator(Boolean bool) {
        super.setFlingToZoomAnimator(bool);
    }

    public final void setOplusMediaPanelAnimator(OplusMediaPanelAnimator oplusMediaPanelAnimator) {
        this.oplusMediaPanelAnimator = oplusMediaPanelAnimator;
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public void updateContainerView(TransitionAnimator.State state, float f, float f2) {
        View viewFindViewById = this.targetView.findViewById(this.containerId);
        float width = state.getWidth() / getTargetViewState().getWidth();
        float height = state.getHeight() / getTargetViewState().getHeight();
        if (width <= Float.MAX_VALUE && height <= Float.MAX_VALUE) {
            if (viewFindViewById != null) {
                viewFindViewById.setTranslationX(((getTargetViewState().getWidth() * width) - state.getWidth()) / 2.0f);
            }
            if (viewFindViewById != null) {
                viewFindViewById.setTranslationY(((getTargetViewState().getHeight() * height) - state.getHeight()) / 2.0f);
            }
            if (viewFindViewById != null) {
                viewFindViewById.setScaleX(width);
            }
            if (viewFindViewById != null) {
                viewFindViewById.setScaleY(height);
            }
            OplusMediaPanelAnimator oplusMediaPanelAnimator = this.oplusMediaPanelAnimator;
            if (oplusMediaPanelAnimator != null) {
                oplusMediaPanelAnimator.setCarouselScaleX(width);
            }
            OplusMediaPanelAnimator oplusMediaPanelAnimator2 = this.oplusMediaPanelAnimator;
            if (oplusMediaPanelAnimator2 != null) {
                oplusMediaPanelAnimator2.setCarouselScaleY(height);
            }
        }
        applyFrameCornerRadii(state, f);
        OplusMediaPanelAnimator oplusMediaPanelAnimator3 = this.oplusMediaPanelAnimator;
        if (oplusMediaPanelAnimator3 != null) {
            oplusMediaPanelAnimator3.expand(f);
        }
        View pageIndicator = getPageIndicator();
        if (pageIndicator != null && pageIndicator.getVisibility() == 0 && viewFindViewById != null) {
            Object parent = viewFindViewById.getParent();
            View view = parent instanceof View ? (View) parent : null;
            if (view != null) {
                Object parent2 = pageIndicator.getParent();
                View view2 = parent2 instanceof View ? (View) parent2 : null;
                if (view2 != null && view.getParent() == view2) {
                    ViewGroup.LayoutParams layoutParams = pageIndicator.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
                    if (marginLayoutParams != null) {
                        MediaViewProvider mediaViewProvider = this.mediaDetail;
                        if (mediaViewProvider == null || !mediaViewProvider.isMediaPageIndicatorBesideCarousel()) {
                            pageIndicator.setTranslationY(((viewFindViewById.getTranslationY() + ((((viewFindViewById.getScaleY() + 1.0f) * viewFindViewById.getHeight()) / 2.0f) + (viewFindViewById.getTop() + view.getTop()))) + marginLayoutParams.topMargin) - pageIndicator.getTop());
                            pageIndicator.setTranslationX((viewFindViewById.getTranslationX() + ((viewFindViewById.getWidth() / 2.0f) + (viewFindViewById.getLeft() + view.getLeft()))) - ((pageIndicator.getWidth() / 2.0f) + pageIndicator.getLeft()));
                        } else {
                            pageIndicator.setTranslationX(((viewFindViewById.getTranslationX() + ((((viewFindViewById.getScaleX() + 1.0f) * viewFindViewById.getWidth()) / 2.0f) + (viewFindViewById.getLeft() + view.getLeft()))) + marginLayoutParams.getMarginStart()) - pageIndicator.getLeft());
                            pageIndicator.setTranslationY((viewFindViewById.getTranslationY() + ((viewFindViewById.getHeight() / 2.0f) + (viewFindViewById.getTop() + view.getTop()))) - ((pageIndicator.getHeight() / 2.0f) + pageIndicator.getTop()));
                        }
                    }
                }
            }
        }
        updatePageIndicatorAlpha(f2, state.getAlpha());
    }

    public final void updatePageIndicatorAlpha(float f, float f2) {
        View pageIndicator = getPageIndicator();
        if (pageIndicator != null && pageIndicator.getVisibility() == 0) {
            boolean z = this.isLaunching;
            if (z && f >= 0.28f) {
                f2 = RangesKt___RangesKt.coerceIn((f - 0.28f) / 0.72f, 0.0f, 1.0f);
            } else if (z) {
                f2 = 0.0f;
            }
            float alpha = this.targetView.getAlpha();
            if (alpha < 0.01f) {
                alpha = 0.01f;
            }
            if (this.needUseAlpha && z) {
                f2 = RangesKt___RangesKt.coerceIn(f2 / alpha, 0.0f, 1.0f);
            }
            pageIndicator.setAlpha(f2);
        }
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public void updateTargetView(TransitionAnimator.State state, float f, float f2) {
        List mediaChildViews;
        super.updateTargetView(state, f, f2);
        if (!this.needUseAlpha) {
            this.targetView.setAlpha(1.0f);
            OplusMediaPanelAnimator oplusMediaPanelAnimator = this.oplusMediaPanelAnimator;
            if (oplusMediaPanelAnimator != null && (mediaChildViews = oplusMediaPanelAnimator.getMediaChildViews()) != null) {
                Iterator it = mediaChildViews.iterator();
                while (it.hasNext()) {
                    ((View) it.next()).setAlpha(state.getAlpha());
                }
            }
        }
        updatePageIndicatorAlpha(f2, state.getAlpha());
        MediaViewProvider mediaViewProvider = this.mediaDetail;
        if (mediaViewProvider != null) {
            if (!this.isLaunching) {
                f = 1.0f - f;
            }
            mediaViewProvider.updateBlurProgress(f);
        }
    }

    @Override // com.oplusos.systemui.animation.TargetViewLaunchAnimatorController
    public void updateTargetViewBackground(TransitionAnimator.State state, float f, float f2) {
        View targetBlurBackgroundView;
        MediaViewProvider mediaViewProvider = this.mediaDetail;
        if (mediaViewProvider == null || (targetBlurBackgroundView = mediaViewProvider.getContainer()) == null) {
            targetBlurBackgroundView = getTargetBlurBackgroundView();
        }
        float f3 = this.frameContainerVisualRadius;
        float f4 = f3 > 0.0f ? f3 : f;
        Drawable background = targetBlurBackgroundView != null ? targetBlurBackgroundView.getBackground() : null;
        if (background instanceof AutoBlurDrawable) {
            AutoBlurDrawable autoBlurDrawable = (AutoBlurDrawable) background;
            if (Intrinsics.areEqual(autoBlurDrawable.getViewBlurProxy().getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) {
                autoBlurDrawable.getViewBlurProxy().getBlurConfig().setCornerRadius(f4);
                autoBlurDrawable.getViewBlurProxy().getBlurConfig().setRadiusWeight(Float.valueOf(f2));
                autoBlurDrawable.getViewBlurProxy().applyBlurConfig();
            }
        }
        MediaViewProvider mediaViewProvider2 = this.mediaDetail;
        if (mediaViewProvider2 != null) {
            mediaViewProvider2.updateContainerMasterClip(f4, state.getLeft(), state.getTop(), 3.0f, state.getRight(), state.getBottom());
        }
        this.targetView.invalidateOutline();
        ViewGroup viewGroup = this.targetViewParent;
        if (viewGroup != null) {
            viewGroup.invalidateOutline();
        }
    }
}
