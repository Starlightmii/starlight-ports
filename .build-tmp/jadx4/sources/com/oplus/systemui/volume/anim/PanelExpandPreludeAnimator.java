package com.oplus.systemui.volume.anim;

import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.oplus.systemui.volume.utils.VolumePropertyAnimationUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PanelExpandPreludeAnimator {
    public static final Companion Companion = new Companion();
    public boolean cancelled;
    private final Function0 iconParentProvider;
    public boolean running;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PanelExpandPreludeAnimator(Function0 function0) {
        this.iconParentProvider = function0;
    }

    public static void applyTerminalState(ViewGroup viewGroup, boolean z) {
        if (!z) {
            viewGroup.setAlpha(0.0f);
            viewGroup.setScaleX(0.7f);
            viewGroup.setScaleY(0.7f);
            viewGroup.setVisibility(8);
            return;
        }
        viewGroup.setVisibility(0);
        viewGroup.setAlpha(1.0f);
        viewGroup.setScaleX(1.0f);
        viewGroup.setScaleY(1.0f);
        viewGroup.setTranslationY(0.0f);
    }

    public final void cancelHandoff(boolean z) {
        this.cancelled = true;
        this.running = false;
        ViewGroup viewGroup = (ViewGroup) this.iconParentProvider.invoke();
        if (viewGroup == null) {
            return;
        }
        viewGroup.animate().cancel();
        applyTerminalState(viewGroup, z);
    }

    public final void cancelRunningAnimationOnly() {
        ViewPropertyAnimator viewPropertyAnimatorAnimate;
        this.cancelled = true;
        this.running = false;
        ViewGroup viewGroup = (ViewGroup) this.iconParentProvider.invoke();
        if (viewGroup == null || (viewPropertyAnimatorAnimate = viewGroup.animate()) == null) {
            return;
        }
        viewPropertyAnimatorAnimate.cancel();
    }

    public final void collapseForPanelDismiss(long j, Interpolator interpolator, boolean z) {
        ViewGroup viewGroup;
        if (!z || (!this.running && ((viewGroup = (ViewGroup) this.iconParentProvider.invoke()) == null || viewGroup.getVisibility() != 0 || viewGroup.getAlpha() <= 0.0f))) {
            cancelHandoff(false);
            return;
        }
        this.cancelled = true;
        ViewGroup viewGroup2 = (ViewGroup) this.iconParentProvider.invoke();
        if (viewGroup2 == null) {
            this.running = false;
            return;
        }
        viewGroup2.animate().cancel();
        if (j <= 0) {
            this.running = false;
            applyTerminalState(viewGroup2, false);
            return;
        }
        this.running = true;
        viewGroup2.setVisibility(0);
        float fCoerceIn = RangesKt___RangesKt.coerceIn(viewGroup2.getAlpha(), 0.0f, 1.0f);
        float scaleX = viewGroup2.getScaleX();
        float translationX = viewGroup2.getTranslationX();
        float translationY = viewGroup2.getTranslationY();
        VolumePropertyAnimationUtil.PropertyAnimationParams propertyAnimationParams = new VolumePropertyAnimationUtil.PropertyAnimationParams(translationX, translationY, scaleX, fCoerceIn);
        VolumePropertyAnimationUtil.PropertyAnimationParams propertyAnimationParams2 = new VolumePropertyAnimationUtil.PropertyAnimationParams(translationX, translationY, 0.7f, 0.0f);
        start.1 r13 = new start.1(1);
        r13.this$0 = this;
        VarHandle.storeStoreFence();
        VolumePropertyAnimationUtil.viewPropertyAnimation(viewGroup2, (322 & 2) != 0 ? 0L : 0L, j, interpolator, propertyAnimationParams, propertyAnimationParams2, null, (322 & 128) != 0 ? null : r13, (322 & 256) != 0 ? null : null);
    }

    public final void start() {
        ViewGroup viewGroup = (ViewGroup) this.iconParentProvider.invoke();
        if (viewGroup != null && viewGroup.getVisibility() == 0) {
            cancelRunningAnimationOnly();
            this.cancelled = false;
            this.running = true;
            viewGroup.setVisibility(0);
            COUISpringInterpolator volume_panel_expand_interpolator = VolumePropertyAnimationUtil.INSTANCE.getVOLUME_PANEL_EXPAND_INTERPOLATOR();
            VolumePropertyAnimationUtil.PropertyAnimationParams propertyAnimationParams = new VolumePropertyAnimationUtil.PropertyAnimationParams(1.0f, 1.0f);
            VolumePropertyAnimationUtil.PropertyAnimationParams propertyAnimationParams2 = new VolumePropertyAnimationUtil.PropertyAnimationParams(0.7f, 0.0f);
            start.1 r9 = new start.1(0);
            r9.this$0 = this;
            VarHandle.storeStoreFence();
            VolumePropertyAnimationUtil.viewPropertyAnimation(viewGroup, (322 & 2) != 0 ? 0L : 0L, 350L, volume_panel_expand_interpolator, propertyAnimationParams, propertyAnimationParams2, null, (322 & 128) != 0 ? null : r9, (322 & 256) != 0 ? null : null);
        }
    }
}
