package com.oplus.systemui.scenario.host.impl.domain.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.outline.PathCornerDrawable;
import com.oplusos.systemui.common.shader.MetaballBottomOffsetCenterProvider;
import com.oplusos.systemui.common.shader.MetaballLightConfig;
import com.oplusos.systemui.common.shader.MetaballLightRenderer;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Deprecated;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioLightBackgroundDrawable extends Drawable implements PathCornerDrawable, Drawable.Callback {
    private static final Companion Companion = new Companion();

    @Deprecated
    public static final String TAG = "ScenarioLightBackgroundDrawable";
    private final ScenarioLightBackgroundDrawable$attachStateListener$1 attachStateListener;
    private final TileDrawableWrapper baseDrawable;
    public boolean disposed;
    private final View hostView;
    public float lastCornerRadius;
    private final MetaballLightRenderer light;
    private final Rect lightBounds = new Rect();
    private final float[] lightCornerRadii = new float[8];
    public boolean lightVisible;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final MetaballLightConfig createLightConfig() {
            return new MetaballLightConfig(2131236459, 2131886220, 3000L, 0.5f, 0.8f, 500.0f, 0.5f, 360.0f, MetaballBottomOffsetCenterProvider.INSTANCE);
        }
    }

    public ScenarioLightBackgroundDrawable(View view, TileDrawableWrapper tileDrawableWrapper) {
        this.hostView = view;
        this.baseDrawable = tileDrawableWrapper;
        this.light = new MetaballLightRenderer(view.getContext(), view, Companion.createLightConfig());
        ScenarioLightBackgroundDrawable$attachStateListener$1 scenarioLightBackgroundDrawable$attachStateListener$1 = new ScenarioLightBackgroundDrawable$attachStateListener$1();
        scenarioLightBackgroundDrawable$attachStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.attachStateListener = scenarioLightBackgroundDrawable$attachStateListener$1;
        tileDrawableWrapper.setCallback(this);
        view.addOnAttachStateChangeListener(scenarioLightBackgroundDrawable$attachStateListener$1);
    }

    public final void dispose() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        this.hostView.removeOnAttachStateChangeListener(this.attachStateListener);
        setLightVisible(false);
        this.baseDrawable.setCallback(null);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.baseDrawable.setBounds(getBounds());
        this.baseDrawable.draw(canvas);
        if (!this.lightVisible || getBounds().isEmpty()) {
            return;
        }
        this.lightBounds.set(getBounds());
        float cornerRadius = getCornerRadius();
        if (cornerRadius < 0.0f) {
            cornerRadius = 0.0f;
        }
        if (cornerRadius != this.lastCornerRadius) {
            ArraysKt___ArraysJvmKt.fill$default(cornerRadius, this.lightCornerRadii);
            this.lastCornerRadius = cornerRadius;
        }
        this.light.draw(canvas, this.lightBounds, this.lightCornerRadii);
    }

    public final void fadeInLightFirstFrame() {
        this.lightVisible = true;
        this.light.fadeInFirstFrame();
        invalidateSelf();
    }

    public final void fadeInThenRestartLight() {
        this.lightVisible = true;
        this.light.fadeInThenPlay();
        invalidateSelf();
    }

    public final void fadeOutThenHideLight(Function0 function0) {
        if (!this.lightVisible) {
            function0.invoke();
            return;
        }
        this.lightVisible = true;
        MetaballLightRenderer metaballLightRenderer = this.light;
        ScenarioLightBackgroundDrawable$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ScenarioLightBackgroundDrawable$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        externalSyntheticLambda0.f$1 = function0;
        VarHandle.storeStoreFence();
        metaballLightRenderer.fadeOutThenHide(50L, externalSyntheticLambda0);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.baseDrawable.getAlpha();
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public float getCornerRadius() {
        return this.baseDrawable.getCornerRadius();
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public Float getCornerWeight() {
        return this.baseDrawable.getCornerWeight();
    }

    @Override // android.graphics.drawable.Drawable
    @Deprecated
    public int getOpacity() {
        return -3;
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public CornerOutlineProvider getPathProvider() {
        return this.baseDrawable.getPathProvider();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public final void invalidatePath() {
        this.baseDrawable.invalidatePath();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.baseDrawable.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean state = this.baseDrawable.setState(iArr);
        if (state) {
            invalidateSelf();
        }
        return state;
    }

    public final void restartLight() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "restartLight", null, 8);
        this.lightVisible = true;
        this.light.startPlay();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.baseDrawable.setAlpha(i);
        invalidateSelf();
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setAnimationEnable(boolean z) {
        this.baseDrawable.setAnimationEnable(z);
    }

    public final void setBaseDrawable(Drawable drawable) {
        this.baseDrawable.setDrawable(drawable);
        invalidateSelf();
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setBlockPathProvider(CornerOutlineProvider cornerOutlineProvider) {
        this.baseDrawable.setBlockPathProvider(cornerOutlineProvider);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.baseDrawable.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setDeformFraction(Float f) {
        this.baseDrawable.setDeformFraction(f);
        invalidateSelf();
    }

    public final void setLightVisible(boolean z) {
        if (!z) {
            this.lightVisible = false;
            this.light.cancelAllAnimations();
        } else {
            if (this.lightVisible) {
                return;
            }
            this.lightVisible = true;
            this.light.startPlay();
        }
        invalidateSelf();
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setPathProvider(CornerOutlineProvider cornerOutlineProvider) {
        this.baseDrawable.setPathProvider(cornerOutlineProvider);
        invalidateSelf();
    }

    public final void showLightFinalFrame() {
        this.lightVisible = true;
        this.light.showFinalFrame();
        invalidateSelf();
    }

    public final void showLightFirstFrame() {
        this.lightVisible = true;
        this.light.showFirstFrame();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
