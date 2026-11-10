package com.oplus.systemui.qs.base.res.drawable;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.StateSet;
import android.view.View;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import com.oplus.graphics.OplusPath;
import com.oplus.systemui.qs.base.res.model.DrawableState;
import com.oplus.systemui.qs.base.res.model.DrawableStateKt;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.res.model.QSDrawableConfig;
import com.oplus.systemui.qs.base.res.model.TileViewFlag;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplus.systemui.qs.base.res.util.ViewTagUtils;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.MixColorWithShader;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.util.GradientStrokeLineAdapter;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MixColorTileDrawable extends TileDrawableWrapper {
    private final ValueAnimator animator;
    private final AutoBlurDrawable autoBlurDrawable;
    private Triple<? extends BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate> currentConfig;
    public boolean firstTime;
    public int maskColor;
    private final OplusPath oplusPath;
    private final Paint paint;
    private LinkedHashMap<DrawableState, Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate>> stateListConfig;
    private GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder implements QSDrawableBuilder {
        private final LinkedHashMap<DrawableState, Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate>> stateListConfig;

        public Builder(LinkedHashMap<DrawableState, Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate>> linkedHashMap) {
            this.stateListConfig = linkedHashMap;
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableBuilder
        public MixColorTileDrawable build(View view, boolean z, Integer num) {
            MixColorTileDrawable mixColorDrawableTag = ViewTagUtils.getMixColorDrawableTag(view);
            if (mixColorDrawableTag != null) {
                mixColorDrawableTag.updateStateList(this.stateListConfig);
                return mixColorDrawableTag;
            }
            ViewBlurProxy viewBlurProxy = new ViewBlurProxy(view, new BlurConfig(num != null ? num.intValue() : QSBlurConfigProvider.getDefaultBlurRadius(), 0, null, null, null, null, false, (BlurMixConfig) ((Triple) this.stateListConfig.lastEntry().getValue()).getFirst(), null, 0, null, 33488894), null, 28);
            viewBlurProxy.setBlurType(ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE);
            MixColorTileDrawable mixColorTileDrawable = new MixColorTileDrawable(new AutoBlurDrawable(viewBlurProxy, null), this.stateListConfig);
            ViewTagUtils.setMixColorDrawableTag(view, mixColorTileDrawable);
            return mixColorTileDrawable;
        }

        public final Builder copy(LinkedHashMap<DrawableState, Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate>> linkedHashMap) {
            return new Builder(linkedHashMap);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Builder) && Intrinsics.areEqual(this.stateListConfig, ((Builder) obj).stateListConfig);
        }

        public final int hashCode() {
            return this.stateListConfig.hashCode();
        }

        public String toString() {
            String string;
            Iterator<Map.Entry<DrawableState, Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate>>> it = this.stateListConfig.entrySet().iterator();
            if (it.hasNext()) {
                StringBuilder sb = new StringBuilder("{");
                while (true) {
                    Map.Entry<DrawableState, Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate>> next = it.next();
                    sb.append(next.getKey());
                    sb.append('=');
                    sb.append("(" + next.getValue().getFirst() + ", " + Integer.toHexString(((Number) next.getValue().getSecond()).intValue()) + ", " + next.getValue().getThird() + ")");
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
            return Bundler$.ExternalSyntheticOutline0.m("Builder(stateListConfig=", string, ")");
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.res.drawable.MixColorTileDrawable$updateColor$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        public /* synthetic */ int $endColor;
        public /* synthetic */ BlurMixConfig.BlurMixMultiWithShader $endConfig;
        public /* synthetic */ int $startColor;
        public /* synthetic */ BlurMixConfig.BlurMixMultiWithShader $startConfig;
        public /* synthetic */ MixColorTileDrawable this$0;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            this.this$0.animUpdateMaskColor(animatedFraction, this.$startColor, this.$endColor);
            MixColorTileDrawable mixColorTileDrawable = this.this$0;
            BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShader = this.$startConfig;
            BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShader2 = this.$endConfig;
            mixColorTileDrawable.animUpdateBlurColor(blurMixMultiWithShader, blurMixMultiWithShader2, animatedFraction, blurMixMultiWithShader2);
        }
    }

    public MixColorTileDrawable(AutoBlurDrawable autoBlurDrawable, LinkedHashMap linkedHashMap) {
        super(autoBlurDrawable);
        this.autoBlurDrawable = autoBlurDrawable;
        this.stateListConfig = linkedHashMap;
        this.paint = new Paint(1);
        this.oplusPath = new OplusPath(getPath());
        this.firstTime = true;
        this.currentConfig = (Triple) this.stateListConfig.lastEntry().getValue();
        this.strokeParamsTemplate = (GradientStrokeLineAdapter.StrokeParamsTemplate) ((Triple) this.stateListConfig.lastEntry().getValue()).getThird();
        this.maskColor = ((Number) this.currentConfig.getSecond()).intValue();
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(0.0f, 1.0f);
        valueAnimator.setInterpolator(QSConstant.INSTANCE.getBG_TRANSITION_INTERPOLATOR());
        valueAnimator.setDuration(200L);
        this.animator = valueAnimator;
    }

    private final void setMaskColor(int i) {
        if (i != this.maskColor) {
            this.maskColor = i;
            invalidateSelf();
        }
    }

    private final void setStrokeParamsTemplate(GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate) {
        if (Intrinsics.areEqual(strokeParamsTemplate, this.strokeParamsTemplate)) {
            return;
        }
        this.strokeParamsTemplate = strokeParamsTemplate;
        invalidatePath();
    }

    public static void updatePlatformMixConfig(ViewBlurProxy viewBlurProxy, BlurMixConfig blurMixConfig) {
        if (Intrinsics.areEqual(viewBlurProxy.getBlurConfig().getPlatformMixConfig(), blurMixConfig)) {
            return;
        }
        blurMixConfig.setAlphaWithBlurAmount(true);
        viewBlurProxy.getBlurConfig().setPlatformMixConfig(blurMixConfig);
        viewBlurProxy.applyBlurConfig();
    }

    public final void animUpdateBlurColor(BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShader, BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShader2, float f, BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShader3) {
        if (blurMixMultiWithShader.equals(blurMixMultiWithShader2)) {
            updatePlatformMixConfig(this.autoBlurDrawable.getViewBlurProxy(), blurMixMultiWithShader2);
            return;
        }
        int iIntValue = ((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(blurMixMultiWithShader.getForegroundShaderParam().getTopLayerColor()), Integer.valueOf(blurMixMultiWithShader2.getForegroundShaderParam().getTopLayerColor()))).intValue();
        int iIntValue2 = ((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(blurMixMultiWithShader.getForegroundShaderParam().getBottomLayerColor()), Integer.valueOf(blurMixMultiWithShader2.getForegroundShaderParam().getBottomLayerColor()))).intValue();
        ViewBlurProxy viewBlurProxy = this.autoBlurDrawable.getViewBlurProxy();
        MixColorWithShader foregroundShaderParam = blurMixMultiWithShader3.getForegroundShaderParam();
        updatePlatformMixConfig(viewBlurProxy, BlurMixConfig.BlurMixMultiWithShader.copy$default(blurMixMultiWithShader3, foregroundShaderParam.copy(foregroundShaderParam.topMode, iIntValue, foregroundShaderParam.bottomMode, iIntValue2), 30));
    }

    public final void animUpdateMaskColor(float f, int i, int i2) {
        if (i != i2) {
            i2 = ((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(i), Integer.valueOf(i2))).intValue();
        }
        setMaskColor(i2);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i = this.maskColor;
        int alpha = getAlpha();
        QSConstant qSConstant = QSConstant.INSTANCE;
        int i2 = ((i << 8) >>> 8) | ((((i >>> 24) * (alpha + (alpha >> 7))) >> 8) << 24);
        boolean z = (i2 >>> 24) != 0;
        boolean zAreEqual = Intrinsics.areEqual(this.autoBlurDrawable.getViewBlurProxy().getBlurConfig().getPlatformMixConfig(), BlurMixConfig.None.INSTANCE);
        if (((!z || this.animator.isRunning()) && !zAreEqual) || isDeforming()) {
            super.draw(canvas);
        }
        if (z) {
            this.paint.setColor(i2);
            canvas.drawPath(getPath(), this.paint);
        }
    }

    @Override // com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper, com.oplusos.systemui.common.outline.PathCornerDrawable
    public final void invalidatePath() {
        super.invalidatePath();
        CornerOutlineProvider pathProvider = getPathProvider();
        pathProvider.updatePath(this, getPath(), this.oplusPath, ensureOplusPathAdapter());
        pathProvider.updateStrokeParams(this.autoBlurDrawable, this.strokeParamsTemplate);
        ViewBlurProxy viewBlurProxy = this.autoBlurDrawable.getViewBlurProxy();
        if (Intrinsics.areEqual(viewBlurProxy.getBlurConfig().getPathProvider(), pathProvider)) {
            viewBlurProxy.invalidatePath();
        } else {
            viewBlurProxy.getBlurConfig().setPathProvider(pathProvider);
        }
        this.autoBlurDrawable.getViewBlurProxy().applyBlurConfig();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        super.jumpToCurrentState();
        this.animator.removeAllUpdateListeners();
        this.animator.cancel();
        updatePlatformMixConfig(this.autoBlurDrawable.getViewBlurProxy(), (BlurMixConfig) this.currentConfig.getFirst());
        setMaskColor(((Number) this.currentConfig.getSecond()).intValue());
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate> value;
        DrawableState key;
        boolean zOnStateChange = super.onStateChange(iArr);
        Iterator<Map.Entry<DrawableState, Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate>>> it = this.stateListConfig.entrySet().iterator();
        do {
            if (!it.hasNext()) {
                value = (Triple) this.stateListConfig.lastEntry().getValue();
                break;
            }
            Map.Entry<DrawableState, Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate>> next = it.next();
            key = next.getKey();
            value = next.getValue();
        } while (!StateSet.stateSetMatches(key.getStateSpec(), iArr));
        return updateColor(value, this.firstTime ^ true) || zOnStateChange;
    }

    @Override // com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper, android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        super.setAlpha(i);
        this.autoBlurDrawable.getViewBlurProxy().getBlurConfig().getPlatformMixConfig().setAlphaWithBlurAmount(true);
        this.autoBlurDrawable.getViewBlurProxy().setBlurAmount(RangesKt___RangesKt.coerceIn(i / 255.0f, 0.0f, 1.0f));
    }

    @Override // com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper, com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setDeformFraction(Float f) {
        BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShader;
        super.setDeformFraction(f);
        if (f == null) {
            jumpToCurrentState();
            return;
        }
        Object first = this.currentConfig.getFirst();
        BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShader2 = first instanceof BlurMixConfig.BlurMixMultiWithShader ? (BlurMixConfig.BlurMixMultiWithShader) first : null;
        Object first2 = this.currentConfig.getFirst();
        BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShader3 = first2 instanceof BlurMixConfig.BlurMixMultiWithShader ? (BlurMixConfig.BlurMixMultiWithShader) first2 : null;
        int iIntValue = ((Number) this.currentConfig.getSecond()).intValue();
        int iIntValue2 = ((Number) this.currentConfig.getSecond()).intValue();
        boolean zContains = ArraysKt___ArraysKt.contains(getState(), TileViewFlag.Active.getAttr());
        LinkedHashMap<DrawableState, Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate>> linkedHashMap = this.stateListConfig;
        if (zContains) {
            Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate> triple = linkedHashMap.get(QSConstant.getDeformActiveState());
            BlurMixConfig blurMixConfig = triple != null ? (BlurMixConfig) triple.getFirst() : null;
            BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShader4 = blurMixConfig instanceof BlurMixConfig.BlurMixMultiWithShader ? (BlurMixConfig.BlurMixMultiWithShader) blurMixConfig : null;
            if (blurMixMultiWithShader4 != null) {
                blurMixMultiWithShader2 = blurMixMultiWithShader4;
            }
            Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate> triple2 = this.stateListConfig.get(QSConstant.getDeformInactiveState());
            BlurMixConfig blurMixConfig2 = triple2 != null ? (BlurMixConfig) triple2.getFirst() : null;
            blurMixMultiWithShader = blurMixConfig2 instanceof BlurMixConfig.BlurMixMultiWithShader ? (BlurMixConfig.BlurMixMultiWithShader) blurMixConfig2 : null;
            if (blurMixMultiWithShader != null) {
                blurMixMultiWithShader3 = blurMixMultiWithShader;
            }
            Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate> triple3 = this.stateListConfig.get(QSConstant.getDeformActiveState());
            if (triple3 != null) {
                iIntValue = ((Number) triple3.getSecond()).intValue();
            }
            Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate> triple4 = this.stateListConfig.get(QSConstant.getDeformInactiveState());
            if (triple4 != null) {
                iIntValue2 = ((Number) triple4.getSecond()).intValue();
            }
        } else {
            Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate> triple5 = linkedHashMap.get(QSConstant.getDeformInactiveState());
            BlurMixConfig blurMixConfig3 = triple5 != null ? (BlurMixConfig) triple5.getFirst() : null;
            blurMixMultiWithShader = blurMixConfig3 instanceof BlurMixConfig.BlurMixMultiWithShader ? (BlurMixConfig.BlurMixMultiWithShader) blurMixConfig3 : null;
            if (blurMixMultiWithShader != null) {
                blurMixMultiWithShader2 = blurMixMultiWithShader;
            }
            Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate> triple6 = this.stateListConfig.get(QSConstant.getDeformInactiveState());
            if (triple6 != null) {
                iIntValue = ((Number) triple6.getSecond()).intValue();
            }
            blurMixMultiWithShader3 = blurMixMultiWithShader2;
            iIntValue2 = iIntValue;
        }
        if (blurMixMultiWithShader2 == null || blurMixMultiWithShader3 == null) {
            return;
        }
        animUpdateMaskColor(f.floatValue(), iIntValue, iIntValue2);
        animUpdateBlurColor(blurMixMultiWithShader2, blurMixMultiWithShader3, f.floatValue(), f.floatValue() < 0.1f ? blurMixMultiWithShader2 : blurMixMultiWithShader3);
    }

    public final boolean updateColor(Triple<? extends BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate> triple, boolean z) {
        this.firstTime = false;
        if (!Intrinsics.areEqual(triple, this.currentConfig)) {
            setStrokeParamsTemplate((GradientStrokeLineAdapter.StrokeParamsTemplate) triple.getThird());
            this.currentConfig = triple;
            if (!isDeforming()) {
                BlurMixConfig platformMixConfig = this.autoBlurDrawable.getViewBlurProxy().getBlurConfig().getPlatformMixConfig();
                int i = this.maskColor;
                BlurMixConfig blurMixConfig = (BlurMixConfig) triple.getFirst();
                int iIntValue = ((Number) triple.getSecond()).intValue();
                if (!z || !isAnimationEnable()) {
                    jumpToCurrentState();
                    return true;
                }
                if (!(platformMixConfig instanceof BlurMixConfig.BlurMixMultiWithShader) || !(blurMixConfig instanceof BlurMixConfig.BlurMixMultiWithShader)) {
                    jumpToCurrentState();
                    return true;
                }
                this.animator.removeAllUpdateListeners();
                this.animator.cancel();
                ValueAnimator valueAnimator = this.animator;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = this;
                anonymousClass1.$startColor = i;
                anonymousClass1.$endColor = iIntValue;
                anonymousClass1.$startConfig = (BlurMixConfig.BlurMixMultiWithShader) platformMixConfig;
                anonymousClass1.$endConfig = (BlurMixConfig.BlurMixMultiWithShader) blurMixConfig;
                VarHandle.storeStoreFence();
                valueAnimator.addUpdateListener(anonymousClass1);
                this.animator.start();
                return true;
            }
        }
        return false;
    }

    public final void updateStateList(LinkedHashMap<DrawableState, Triple<BlurMixConfig, Integer, GradientStrokeLineAdapter.StrokeParamsTemplate>> linkedHashMap) {
        this.stateListConfig = linkedHashMap;
        this.firstTime = true;
        onStateChange(getState());
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TileTypeConfig implements QSDrawableConfig {
        private final BlurMixConfig activeConfig;
        public final int activeMaskColor;
        private final GradientStrokeLineAdapter.StrokeParamsTemplate activeStrokeParamsTemplate;
        private final Lazy defaultTypeBuilder$delegate;
        private final GradientStrokeLineAdapter.StrokeParamsTemplate inActiveStrokeParamsTemplate;
        private final BlurMixConfig inactiveConfig;
        public final int inactiveMaskColor;
        public final int inoperableMaskColor;
        private final Lazy sepHighlightIconBgBuilder$delegate;
        private final Lazy sepHighlightTypeBuilder$delegate;
        private final Lazy sepTileTypeBuilder$delegate;
        private final Lazy staticTypeBgBuilder$delegate;
        private final Lazy stdHighLightTypeBuilder$delegate;
        private final Lazy stdTileTypeBuilder$delegate;

        /* JADX INFO: renamed from: $r8$lambda$6E0mT0OOIpvEHB9d8AQsj-ufHfo, reason: not valid java name */
        public static Builder m248$r8$lambda$6E0mT0OOIpvEHB9d8AQsjufHfo(TileTypeConfig tileTypeConfig) {
            return new Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{TuplesKt.to(DrawableStateKt.toState(TileViewFlag.Active.getFlag()), new Triple(tileTypeConfig.activeConfig, Integer.valueOf(tileTypeConfig.activeMaskColor), tileTypeConfig.activeStrokeParamsTemplate)), TuplesKt.to(DrawableStateKt.toState(TileViewFlag.Inoperable.getFlag()), new Triple(tileTypeConfig.activeConfig, Integer.valueOf(tileTypeConfig.inoperableMaskColor), tileTypeConfig.activeStrokeParamsTemplate)), TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), new Triple(tileTypeConfig.inactiveConfig, Integer.valueOf(tileTypeConfig.inactiveMaskColor), tileTypeConfig.inActiveStrokeParamsTemplate))}));
        }

        public static Builder $r8$lambda$A92lPopB3bJDQUSbpmpqZ42FfMs(TileTypeConfig tileTypeConfig) {
            return new Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), new Triple(tileTypeConfig.activeConfig, 0, tileTypeConfig.inActiveStrokeParamsTemplate))}));
        }

        public static Builder $r8$lambda$CsJWBUHTKnvjz3MaI_L047c4N2c(TileTypeConfig tileTypeConfig) {
            return new Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), new Triple(tileTypeConfig.inactiveConfig, Integer.valueOf(tileTypeConfig.inactiveMaskColor), tileTypeConfig.inActiveStrokeParamsTemplate))}));
        }

        public static Builder $r8$lambda$NRkXJoXVR_be5xqMUbTRRmv_wWY(TileTypeConfig tileTypeConfig) {
            TileViewFlag tileViewFlag = TileViewFlag.Toggleable;
            Pair pair = TuplesKt.to(DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(tileViewFlag.getFlag(), TileViewFlag.Active.getFlag())), new Triple(tileTypeConfig.activeConfig, Integer.valueOf(tileTypeConfig.activeMaskColor), tileTypeConfig.activeStrokeParamsTemplate));
            Pair pair2 = TuplesKt.to(DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(tileViewFlag.getFlag(), TileViewFlag.Inoperable.getFlag())), new Triple(tileTypeConfig.activeConfig, Integer.valueOf(tileTypeConfig.inoperableMaskColor), tileTypeConfig.activeStrokeParamsTemplate));
            DrawableState state = DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(tileViewFlag.getFlag(), TileViewFlag.Unavailable.getFlag()));
            BlurMixConfig blurMixConfig = tileTypeConfig.inactiveConfig;
            int i = tileTypeConfig.inactiveMaskColor;
            return new Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{pair, pair2, TuplesKt.to(state, new Triple(blurMixConfig, Integer.valueOf(i), (Object) null)), TuplesKt.to(DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(tileViewFlag.getFlag(), TileViewFlag.Inactive.getFlag())), new Triple(tileTypeConfig.inactiveConfig, Integer.valueOf(i), (Object) null)), TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), new Triple(BlurMixConfig.None.INSTANCE, 0, (Object) null))}));
        }

        /* JADX INFO: renamed from: $r8$lambda$ZzoYJzATeuYDlTU-PTvFIXOT6NM, reason: not valid java name */
        public static Builder m249$r8$lambda$ZzoYJzATeuYDlTUPTvFIXOT6NM(TileTypeConfig tileTypeConfig) {
            DrawableState state = DrawableStateKt.toState(TileViewFlag.Active.getFlag());
            BlurMixConfig blurMixConfig = tileTypeConfig.activeConfig;
            int i = tileTypeConfig.activeMaskColor;
            Pair pair = TuplesKt.to(state, new Triple(blurMixConfig, Integer.valueOf(i), tileTypeConfig.activeStrokeParamsTemplate));
            Pair pair2 = TuplesKt.to(DrawableStateKt.toState(TileViewFlag.Inoperable.getFlag()), new Triple(tileTypeConfig.activeConfig, Integer.valueOf(tileTypeConfig.inoperableMaskColor), tileTypeConfig.activeStrokeParamsTemplate));
            DrawableState deformInactiveState = QSConstant.getDeformInactiveState();
            BlurMixConfig blurMixConfig2 = tileTypeConfig.inactiveConfig;
            int i2 = tileTypeConfig.inactiveMaskColor;
            return new Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{pair, pair2, TuplesKt.to(deformInactiveState, new Triple(blurMixConfig2, Integer.valueOf(i2), tileTypeConfig.inActiveStrokeParamsTemplate)), TuplesKt.to(QSConstant.getDeformActiveState(), new Triple(tileTypeConfig.activeConfig, Integer.valueOf(i), tileTypeConfig.activeStrokeParamsTemplate)), TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), new Triple(tileTypeConfig.inactiveConfig, Integer.valueOf(i2), tileTypeConfig.inActiveStrokeParamsTemplate))}));
        }

        /* JADX INFO: renamed from: $r8$lambda$xff9XUyGq0Jh1yLq-LrUvgehx2c, reason: not valid java name */
        public static Builder m250$r8$lambda$xff9XUyGq0Jh1yLqLrUvgehx2c(TileTypeConfig tileTypeConfig) {
            DrawableState deformInactiveState = QSConstant.getDeformInactiveState();
            BlurMixConfig blurMixConfig = tileTypeConfig.inactiveConfig;
            int i = tileTypeConfig.inactiveMaskColor;
            return new Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{TuplesKt.to(deformInactiveState, new Triple(blurMixConfig, Integer.valueOf(i), tileTypeConfig.inActiveStrokeParamsTemplate)), TuplesKt.to(QSConstant.getDeformActiveState(), new Triple(tileTypeConfig.activeConfig, Integer.valueOf(tileTypeConfig.activeMaskColor), tileTypeConfig.activeStrokeParamsTemplate)), TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), new Triple(tileTypeConfig.inactiveConfig, Integer.valueOf(i), tileTypeConfig.inActiveStrokeParamsTemplate))}));
        }

        /* JADX INFO: renamed from: $r8$lambda$xyo5Y_08HX-UYZEPLrQ9dFVo7rc, reason: not valid java name */
        public static Builder m251$r8$lambda$xyo5Y_08HXUYZEPLrQ9dFVo7rc(TileTypeConfig tileTypeConfig) {
            return new Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{TuplesKt.to(DrawableStateKt.toState(TileViewFlag.Active.getFlag()), new Triple(tileTypeConfig.activeConfig, Integer.valueOf(tileTypeConfig.activeMaskColor), tileTypeConfig.activeStrokeParamsTemplate)), TuplesKt.to(DrawableStateKt.toState(TileViewFlag.Inoperable.getFlag()), new Triple(tileTypeConfig.activeConfig, Integer.valueOf(tileTypeConfig.inoperableMaskColor), tileTypeConfig.activeStrokeParamsTemplate)), TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), new Triple(tileTypeConfig.inactiveConfig, Integer.valueOf(tileTypeConfig.inactiveMaskColor), tileTypeConfig.inActiveStrokeParamsTemplate))}));
        }

        public TileTypeConfig(BlurMixConfig blurMixConfig, BlurMixConfig blurMixConfig2, int i, int i2, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate2) {
            this.activeConfig = blurMixConfig;
            this.inactiveConfig = blurMixConfig2;
            this.activeMaskColor = i;
            this.inactiveMaskColor = i2;
            this.activeStrokeParamsTemplate = strokeParamsTemplate;
            this.inActiveStrokeParamsTemplate = strokeParamsTemplate2;
            this.inoperableMaskColor = QSConstant.setColorAlpha(i);
            MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 = new MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0(0);
            mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            this.defaultTypeBuilder$delegate = LazyKt__LazyJVMKt.lazy(mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0);
            MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda1 = new MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0(1);
            mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            this.stdTileTypeBuilder$delegate = LazyKt__LazyJVMKt.lazy(mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda1);
            MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda2 = new MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0(2);
            mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda2.f$0 = this;
            VarHandle.storeStoreFence();
            this.stdHighLightTypeBuilder$delegate = LazyKt__LazyJVMKt.lazy(mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda2);
            MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda3 = new MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0(3);
            mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda3.f$0 = this;
            VarHandle.storeStoreFence();
            this.sepTileTypeBuilder$delegate = LazyKt__LazyJVMKt.lazy(mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda3);
            MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda4 = new MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0(4);
            mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda4.f$0 = this;
            VarHandle.storeStoreFence();
            this.sepHighlightTypeBuilder$delegate = LazyKt__LazyJVMKt.lazy(mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda4);
            MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda5 = new MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0(5);
            mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda5.f$0 = this;
            VarHandle.storeStoreFence();
            this.sepHighlightIconBgBuilder$delegate = LazyKt__LazyJVMKt.lazy(mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda5);
            MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda6 = new MixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0(6);
            mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda6.f$0 = this;
            VarHandle.storeStoreFence();
            this.staticTypeBgBuilder$delegate = LazyKt__LazyJVMKt.lazy(mixColorTileDrawable$TileTypeConfig$$ExternalSyntheticLambda6);
        }

        public final TileTypeConfig copy(BlurMixConfig blurMixConfig, BlurMixConfig blurMixConfig2, int i, int i2, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate2) {
            return new TileTypeConfig(blurMixConfig, blurMixConfig2, i, i2, strokeParamsTemplate, strokeParamsTemplate2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TileTypeConfig)) {
                return false;
            }
            TileTypeConfig tileTypeConfig = (TileTypeConfig) obj;
            return Intrinsics.areEqual(this.activeConfig, tileTypeConfig.activeConfig) && Intrinsics.areEqual(this.inactiveConfig, tileTypeConfig.inactiveConfig) && this.activeMaskColor == tileTypeConfig.activeMaskColor && this.inactiveMaskColor == tileTypeConfig.inactiveMaskColor && Intrinsics.areEqual(this.activeStrokeParamsTemplate, tileTypeConfig.activeStrokeParamsTemplate) && Intrinsics.areEqual(this.inActiveStrokeParamsTemplate, tileTypeConfig.inActiveStrokeParamsTemplate);
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
        public QSDrawableBuilder getStdHighLightTypeBuilder() {
            return (QSDrawableBuilder) this.stdHighLightTypeBuilder$delegate.getValue();
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public QSDrawableBuilder getStdTileTypeBuilder() {
            return (QSDrawableBuilder) this.stdTileTypeBuilder$delegate.getValue();
        }

        public final int hashCode() {
            int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.inactiveMaskColor, MonoSpline$.ExternalSyntheticOutline0.m(this.activeMaskColor, (this.inactiveConfig.hashCode() + (this.activeConfig.hashCode() * 31)) * 31, 31), 31);
            GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate = this.activeStrokeParamsTemplate;
            int iHashCode = (iM + (strokeParamsTemplate == null ? 0 : strokeParamsTemplate.hashCode())) * 31;
            GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate2 = this.inActiveStrokeParamsTemplate;
            return iHashCode + (strokeParamsTemplate2 != null ? strokeParamsTemplate2.hashCode() : 0);
        }

        public String toString() {
            BlurMixConfig blurMixConfig = this.activeConfig;
            BlurMixConfig blurMixConfig2 = this.inactiveConfig;
            GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate = this.activeStrokeParamsTemplate;
            GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate2 = this.inActiveStrokeParamsTemplate;
            StringBuilder sb = new StringBuilder("TileTypeConfig(activeConfig=");
            sb.append(blurMixConfig);
            sb.append(", inactiveConfig=");
            sb.append(blurMixConfig2);
            sb.append(", activeMaskColor=");
            RectList$.ExternalSyntheticOutline0.m(this.activeMaskColor, this.inactiveMaskColor, ", inactiveMaskColor=", ", activeStrokeParamsTemplate=", sb);
            sb.append(strokeParamsTemplate);
            sb.append(", inActiveStrokeParamsTemplate=");
            sb.append(strokeParamsTemplate2);
            sb.append(")");
            return sb.toString();
        }

        public TileTypeConfig(BlurMixConfig blurMixConfig, BlurMixConfig blurMixConfig2) {
            this(blurMixConfig, blurMixConfig2, 0, 0, null, null, 60);
        }

        public TileTypeConfig(BlurMixConfig blurMixConfig, BlurMixConfig blurMixConfig2, int i) {
            this(blurMixConfig, blurMixConfig2, i, 0, null, null, 56);
        }

        public TileTypeConfig(BlurMixConfig blurMixConfig, BlurMixConfig blurMixConfig2, int i, int i2) {
            this(blurMixConfig, blurMixConfig2, i, i2, null, null, 48);
        }

        public TileTypeConfig(BlurMixConfig blurMixConfig, BlurMixConfig blurMixConfig2, int i, int i2, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate) {
            this(blurMixConfig, blurMixConfig2, i, i2, strokeParamsTemplate, null, 32);
        }

        public TileTypeConfig(BlurMixConfig blurMixConfig) {
            this(blurMixConfig, null, 0, 0, null, null, 62);
        }

        public /* synthetic */ TileTypeConfig(BlurMixConfig blurMixConfig, BlurMixConfig blurMixConfig2, int i, int i2, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate2, int i3) {
            this(blurMixConfig, (i3 & 2) != 0 ? blurMixConfig : blurMixConfig2, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? null : strokeParamsTemplate, (i3 & 32) != 0 ? null : strokeParamsTemplate2);
        }
    }
}
