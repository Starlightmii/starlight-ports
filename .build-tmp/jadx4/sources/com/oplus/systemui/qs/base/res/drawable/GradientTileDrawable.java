package com.oplus.systemui.qs.base.res.drawable;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.StateSet;
import android.view.View;
import androidx.car.app.model.TimerSpan$;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import com.oplus.graphics.OplusPath;
import com.oplus.systemui.qs.base.res.model.DrawableState;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.res.model.QSDrawableConfig;
import com.oplus.systemui.qs.base.res.model.TileViewFlag;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplus.systemui.qs.base.res.util.ViewTagUtils;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GradientTileDrawable extends TileDrawableWrapper implements ValueAnimator.AnimatorUpdateListener {
    private final ValueAnimator animator;
    private final ColorDrawable colorDrawable;
    public int currentColor;
    public boolean firstTime;
    public int lastColor;
    private final OplusPath oplusPath;
    private final Paint paint;
    private LinkedHashMap<DrawableState, Integer> stateListColors;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder implements QSDrawableBuilder {
        private final LinkedHashMap<DrawableState, Integer> stateListColors;

        public Builder(LinkedHashMap<DrawableState, Integer> linkedHashMap) {
            this.stateListColors = linkedHashMap;
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableBuilder
        public GradientTileDrawable build(View view, boolean z, Integer num) {
            GradientTileDrawable gradientDrawableTag = ViewTagUtils.getGradientDrawableTag(view);
            if (gradientDrawableTag != null && z) {
                gradientDrawableTag.updateStateList(this.stateListColors);
                return gradientDrawableTag;
            }
            GradientTileDrawable gradientTileDrawable = new GradientTileDrawable(new ColorDrawable(((Number) this.stateListColors.lastEntry().getValue()).intValue()), this.stateListColors);
            if (z) {
                ViewTagUtils.setGradientDrawableTag(view, gradientTileDrawable);
            }
            return gradientTileDrawable;
        }

        public final Builder copy(LinkedHashMap<DrawableState, Integer> linkedHashMap) {
            return new Builder(linkedHashMap);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Builder) && Intrinsics.areEqual(this.stateListColors, ((Builder) obj).stateListColors);
        }

        public final int hashCode() {
            return this.stateListColors.hashCode();
        }

        public String toString() {
            String string;
            Iterator<Map.Entry<DrawableState, Integer>> it = this.stateListColors.entrySet().iterator();
            if (it.hasNext()) {
                StringBuilder sb = new StringBuilder("{");
                while (true) {
                    Map.Entry<DrawableState, Integer> next = it.next();
                    sb.append(next.getKey());
                    sb.append('=');
                    sb.append(Integer.toHexString(next.getValue().intValue()));
                    if (!it.hasNext()) {
                        break;
                    }
                    sb.append(", ");
                }
                sb.append('}');
                string = sb.toString();
            } else {
                string = "{}";
            }
            return Bundler$.ExternalSyntheticOutline0.m("Builder(stateListColors=", string, ")");
        }
    }

    public GradientTileDrawable(ColorDrawable colorDrawable, LinkedHashMap linkedHashMap) {
        super(colorDrawable);
        this.colorDrawable = colorDrawable;
        this.stateListColors = linkedHashMap;
        this.paint = new Paint(1);
        this.oplusPath = new OplusPath(getPath());
        this.firstTime = true;
        this.lastColor = colorDrawable.getColor();
        this.currentColor = colorDrawable.getColor();
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(0.0f, 1.0f);
        valueAnimator.setInterpolator(QSConstant.INSTANCE.getBG_TRANSITION_INTERPOLATOR());
        valueAnimator.setDuration(200L);
        valueAnimator.addUpdateListener(this);
        this.animator = valueAnimator;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        ColorFilter colorFilter = this.colorDrawable.getColorFilter();
        int color = this.colorDrawable.getColor();
        if ((color >>> 24) == 0 && colorFilter == null) {
            return;
        }
        this.paint.setColorFilter(colorFilter);
        this.paint.setColor(color);
        canvas.drawPath(getPath(), this.paint);
    }

    @Override // com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper, com.oplusos.systemui.common.outline.PathCornerDrawable
    public final void invalidatePath() {
        super.invalidatePath();
        getPathProvider().updatePath(this, getPath(), this.oplusPath, ensureOplusPathAdapter());
        invalidateSelf();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        super.jumpToCurrentState();
        this.animator.cancel();
        this.colorDrawable.setColor(this.currentColor);
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.lastColor;
        int i2 = this.currentColor;
        ColorDrawable colorDrawable = this.colorDrawable;
        if (i != i2) {
            colorDrawable.setColor(((Integer) ArgbEvaluator.getInstance().evaluate(valueAnimator.getAnimatedFraction(), Integer.valueOf(this.lastColor), Integer.valueOf(this.currentColor))).intValue());
        } else {
            colorDrawable.setColor(i2);
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        int iIntValue;
        DrawableState key;
        boolean zOnStateChange = super.onStateChange(iArr);
        Iterator<Map.Entry<DrawableState, Integer>> it = this.stateListColors.entrySet().iterator();
        do {
            if (!it.hasNext()) {
                iIntValue = ((Number) this.stateListColors.lastEntry().getValue()).intValue();
                break;
            }
            Map.Entry<DrawableState, Integer> next = it.next();
            key = next.getKey();
            iIntValue = next.getValue().intValue();
        } while (!StateSet.stateSetMatches(key.getStateSpec(), iArr));
        boolean z = this.firstTime;
        this.firstTime = false;
        if (this.currentColor != iIntValue) {
            this.lastColor = this.colorDrawable.getColor();
            this.currentColor = iIntValue;
            if (!isDeforming()) {
                if (z || !isAnimationEnable()) {
                    jumpToCurrentState();
                    return true;
                }
                this.animator.cancel();
                this.animator.start();
                return true;
            }
        }
        return zOnStateChange;
    }

    @Override // com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper, com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setDeformFraction(Float f) {
        super.setDeformFraction(f);
        if (f == null) {
            jumpToCurrentState();
            return;
        }
        int color = this.colorDrawable.getColor();
        int color2 = this.colorDrawable.getColor();
        boolean zContains = ArraysKt___ArraysKt.contains(getState(), TileViewFlag.Active.getAttr());
        LinkedHashMap<DrawableState, Integer> linkedHashMap = this.stateListColors;
        if (zContains) {
            Integer num = linkedHashMap.get(QSConstant.getDeformActiveState());
            if (num != null) {
                color = num.intValue();
            }
            Integer num2 = this.stateListColors.get(QSConstant.getDeformInactiveState());
            if (num2 != null) {
                color2 = num2.intValue();
            }
        } else {
            Integer num3 = linkedHashMap.get(QSConstant.getDeformInactiveState());
            if (num3 != null) {
                color = num3.intValue();
            }
            color2 = color;
        }
        ColorDrawable colorDrawable = this.colorDrawable;
        if (color == color2) {
            colorDrawable.setColor(color2);
        } else {
            colorDrawable.setColor(((Integer) ArgbEvaluator.getInstance().evaluate(f.floatValue(), Integer.valueOf(color), Integer.valueOf(color2))).intValue());
        }
    }

    public final void updateStateList(LinkedHashMap<DrawableState, Integer> linkedHashMap) {
        this.stateListColors = linkedHashMap;
        this.firstTime = true;
        onStateChange(getState());
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TileTypeConfig implements QSDrawableConfig {
        public final int activeColor;
        public final int activeDraggedColor;
        private final Lazy defaultTypeBuilder$delegate;
        public final int inactiveColor;
        public final int inactiveDraggedColor;
        public final int inoperableColor;
        public final int inoperableDraggedColor;
        private final Lazy sepHighlightIconBgBuilder$delegate;
        private final Lazy sepHighlightTypeBuilder$delegate;
        private final Lazy sepTileTypeBuilder$delegate;
        private final Lazy staticTypeBgBuilder$delegate;
        private final Lazy stdTileTypeBuilder$delegate;
        public final int unavailableColor;
        public final int unavailableDraggedColor;

        public TileTypeConfig(int i, int i2, int i3, int i4, int i5, int i6) {
            this.activeColor = i;
            this.inactiveColor = i2;
            this.unavailableColor = i3;
            this.activeDraggedColor = i4;
            this.inactiveDraggedColor = i5;
            this.unavailableDraggedColor = i6;
            this.inoperableColor = QSConstant.setColorAlpha(i);
            this.inoperableDraggedColor = QSConstant.setColorAlpha(i4);
            GradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 = new GradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0(0);
            gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            this.defaultTypeBuilder$delegate = LazyKt__LazyJVMKt.lazy(gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0);
            GradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda1 = new GradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0(1);
            gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            this.stdTileTypeBuilder$delegate = LazyKt__LazyJVMKt.lazy(gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda1);
            GradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda2 = new GradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0(2);
            gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda2.f$0 = this;
            VarHandle.storeStoreFence();
            this.sepTileTypeBuilder$delegate = LazyKt__LazyJVMKt.lazy(gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda2);
            GradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda3 = new GradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0(3);
            gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda3.f$0 = this;
            VarHandle.storeStoreFence();
            this.sepHighlightTypeBuilder$delegate = LazyKt__LazyJVMKt.lazy(gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda3);
            GradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda4 = new GradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0(4);
            gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda4.f$0 = this;
            VarHandle.storeStoreFence();
            this.sepHighlightIconBgBuilder$delegate = LazyKt__LazyJVMKt.lazy(gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda4);
            GradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda5 = new GradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0(5);
            gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda5.f$0 = this;
            VarHandle.storeStoreFence();
            this.staticTypeBgBuilder$delegate = LazyKt__LazyJVMKt.lazy(gradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda5);
        }

        public final TileTypeConfig copy(int i, int i2, int i3, int i4, int i5, int i6) {
            return new TileTypeConfig(i, i2, i3, i4, i5, i6);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TileTypeConfig)) {
                return false;
            }
            TileTypeConfig tileTypeConfig = (TileTypeConfig) obj;
            return this.activeColor == tileTypeConfig.activeColor && this.inactiveColor == tileTypeConfig.inactiveColor && this.unavailableColor == tileTypeConfig.unavailableColor && this.activeDraggedColor == tileTypeConfig.activeDraggedColor && this.inactiveDraggedColor == tileTypeConfig.inactiveDraggedColor && this.unavailableDraggedColor == tileTypeConfig.unavailableDraggedColor;
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public QSDrawableBuilder getDefaultTypeBuilder() {
            return (QSDrawableBuilder) this.defaultTypeBuilder$delegate.getValue();
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public QSDrawableBuilder getSepHighlightIconBgBuilder() {
            return (QSDrawableBuilder) this.sepHighlightIconBgBuilder$delegate.getValue();
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public QSDrawableBuilder getSepHighlightTypeBuilder() {
            return (QSDrawableBuilder) this.sepHighlightTypeBuilder$delegate.getValue();
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public QSDrawableBuilder getSepTileTypeBuilder() {
            return (QSDrawableBuilder) this.sepTileTypeBuilder$delegate.getValue();
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public QSDrawableBuilder getStaticTypeBgBuilder() {
            return (QSDrawableBuilder) this.staticTypeBgBuilder$delegate.getValue();
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public /* bridge */ /* synthetic */ QSDrawableBuilder getStdHighLightTypeBuilder() {
            return super.getStdHighLightTypeBuilder();
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public QSDrawableBuilder getStdTileTypeBuilder() {
            return (QSDrawableBuilder) this.stdTileTypeBuilder$delegate.getValue();
        }

        public final int hashCode() {
            return Integer.hashCode(this.unavailableDraggedColor) + MonoSpline$.ExternalSyntheticOutline0.m(this.inactiveDraggedColor, MonoSpline$.ExternalSyntheticOutline0.m(this.activeDraggedColor, MonoSpline$.ExternalSyntheticOutline0.m(this.unavailableColor, MonoSpline$.ExternalSyntheticOutline0.m(this.inactiveColor, Integer.hashCode(this.activeColor) * 31, 31), 31), 31), 31);
        }

        public String toString() {
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("TileTypeConfig(activeColor=", this.activeColor, this.inactiveColor, ", inactiveColor=", ", unavailableColor=");
            RectList$.ExternalSyntheticOutline0.m(this.unavailableColor, this.activeDraggedColor, ", activeDraggedColor=", ", inactiveDraggedColor=", sbM);
            return TimerSpan$.ExternalSyntheticOutline0.m(this.inactiveDraggedColor, this.unavailableDraggedColor, ", unavailableDraggedColor=", ")", sbM);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ TileTypeConfig(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
            i2 = (i6 & 2) != 0 ? i : i2;
            i3 = (i6 & 4) != 0 ? i2 : i3;
            this(i, i2, i3, (i6 & 8) != 0 ? i : i4, (i6 & 16) != 0 ? i2 : i5, i3);
        }
    }
}
