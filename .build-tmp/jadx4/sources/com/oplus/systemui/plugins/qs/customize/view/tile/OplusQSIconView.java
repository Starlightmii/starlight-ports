package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.collection.FloatList$;
import com.android.systemui.plugins.qs.QSIconView;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper;
import com.oplus.systemui.qs.base.res.drawable.TileTransitionDrawable;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.res.model.TileIconColorState;
import com.oplus.systemui.qs.base.res.widget.QSIconViewProxy;
import com.oplus.systemui.qs.base.springtransition.DrawableTransitionAlphaTarget;
import com.oplus.systemui.qs.base.springtransition.ImageViewTintTarget;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.model.SpanSizeKt;
import com.oplusos.systemui.common.outline.OvalOutlineProvider;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSIconView extends QSIconView {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final Lazy bgDrawable$delegate;
    public boolean disableTheme;
    private final Lazy iconBgDrawable$delegate;
    private final Lazy iconBgTransitionTarget$delegate;
    private final ImageView iconBgView;
    public float iconScaleRatio;
    private final QSIconViewProxy iconViewProxy;
    private final ImageViewTintTarget iconViewTransitionTarget;
    public final boolean showIconBg;
    private final SpanSize spanSize;
    private final Lazy themeDrawable$delegate;
    private Integer tileColorfulConfig;
    public boolean tileToggleable;

    public static TileTransitionDrawable $r8$lambda$Gfgu2Zxa6TLr4WmeKSxLe3oh19c(OplusQSIconView oplusQSIconView) {
        TileTransitionDrawable tileTransitionDrawable = new TileTransitionDrawable(oplusQSIconView.getBgDrawable(), oplusQSIconView.getThemeDrawable());
        tileTransitionDrawable.setPathProvider(OvalOutlineProvider.INSTANCE);
        return tileTransitionDrawable;
    }

    public static DrawableTransitionAlphaTarget $r8$lambda$rk8VM8JV8ZxJJMuNU2XBslaE0IE(OplusQSIconView oplusQSIconView) {
        return new DrawableTransitionAlphaTarget(oplusQSIconView.getIconBgDrawable());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSIconView(Context context, SpanSize spanSize, boolean z) {
        super(context);
        this.spanSize = spanSize;
        this.showIconBg = z;
        this.iconViewProxy = new QSIconViewProxy(this);
        this.iconViewTransitionTarget = new ImageViewTintTarget(getIconView());
        final int i = 0;
        this.bgDrawable$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSIconView$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        int i2 = OplusQSIconView.$r8$clinit;
                        break;
                    default:
                        int i3 = OplusQSIconView.$r8$clinit;
                        break;
                }
                return new TileDrawableWrapper(null);
            }
        });
        final int i2 = 1;
        this.themeDrawable$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSIconView$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        int i3 = OplusQSIconView.$r8$clinit;
                        break;
                    default:
                        int i4 = OplusQSIconView.$r8$clinit;
                        break;
                }
                return new TileDrawableWrapper(null);
            }
        });
        OplusQSIconView$$ExternalSyntheticLambda2 oplusQSIconView$$ExternalSyntheticLambda2 = new OplusQSIconView$$ExternalSyntheticLambda2(i);
        oplusQSIconView$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.iconBgDrawable$delegate = LazyKt__LazyJVMKt.lazy(oplusQSIconView$$ExternalSyntheticLambda2);
        OplusQSIconView$$ExternalSyntheticLambda2 oplusQSIconView$$ExternalSyntheticLambda3 = new OplusQSIconView$$ExternalSyntheticLambda2(i2);
        oplusQSIconView$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.iconBgTransitionTarget$delegate = LazyKt__LazyJVMKt.lazy(oplusQSIconView$$ExternalSyntheticLambda3);
        ImageView imageView = new ImageView(context);
        imageView.setId(2131364406);
        imageView.setDuplicateParentStateEnabled(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.iconBgView = imageView;
        this.iconScaleRatio = 1.0f;
        setDuplicateParentStateEnabled(true);
        addView(imageView);
        addView(getIconView());
    }

    private final TileDrawableWrapper getBgDrawable() {
        return (TileDrawableWrapper) this.bgDrawable$delegate.getValue();
    }

    private final TileTransitionDrawable getIconBgDrawable() {
        return (TileTransitionDrawable) this.iconBgDrawable$delegate.getValue();
    }

    private final TileDrawableWrapper getThemeDrawable() {
        return (TileDrawableWrapper) this.themeDrawable$delegate.getValue();
    }

    private final void setDisableTheme(boolean z) {
        if (this.disableTheme == z) {
            return;
        }
        this.disableTheme = z;
        onIconTintUpdate();
        updateIconBgDrawable();
    }

    private final void setTileColorfulConfig(Integer num) {
        if (Intrinsics.areEqual(this.tileColorfulConfig, num)) {
            return;
        }
        this.tileColorfulConfig = num;
        onIconTintUpdate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setTileToggleable(boolean z) {
        if (this.tileToggleable == z) {
            return;
        }
        this.tileToggleable = z;
        requestLayout();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int dimen(int i) {
        return getResources().getDimensionPixelSize(i);
    }

    public final void disableAnimation() {
        this.iconViewProxy.setAnimationEnable(false);
    }

    public final void disableTheme(boolean z) {
        setDisableTheme(z);
    }

    public final void enableAnimation() {
        this.iconViewProxy.setAnimationEnable(true);
    }

    public final DrawableTransitionAlphaTarget getIconBgTransitionTarget() {
        return (DrawableTransitionAlphaTarget) this.iconBgTransitionTarget$delegate.getValue();
    }

    public final float getIconScaleRatio() {
        return this.iconScaleRatio;
    }

    public ImageView getIconView() {
        return this.iconViewProxy.getIconView();
    }

    public final ImageViewTintTarget getIconViewTransitionTarget() {
        return this.iconViewTransitionTarget;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void layoutViewToCenter(View view) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int measuredWidth2 = (getMeasuredWidth() - measuredWidth) / 2;
        int measuredHeight2 = (getMeasuredHeight() - measuredHeight) / 2;
        view.layout(measuredWidth2, measuredHeight2, measuredWidth + measuredWidth2, measuredHeight + measuredHeight2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.view.ViewGroup*/.onAttachedToWindow();
        if (this.showIconBg) {
            this.iconBgView.setBackground(getIconBgDrawable());
            onIconDrawableUpdate(false);
            onIconDrawableUpdate(true);
        }
        BaseQSResPool.globalThemeApplied();
        updateIconBgDrawable();
        onIconTintUpdate();
    }

    public final void onIconDrawableUpdate(boolean z) {
        if (this.showIconBg) {
            if (z) {
                getThemeDrawable().setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) SepQSResPool.INSTANCE.getHighLightIconThemeBg().getValue(), this.iconBgView, false, 4));
            } else {
                getBgDrawable().setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) SepQSResPool.INSTANCE.getHighLightIconBg().getValue(), this.iconBgView, false, 6));
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x000f  */
    public final void onIconTintUpdate() {
        TileIconColorState tileIconThemeColorState = SepQSResPool.getTileIconThemeColorState();
        if (tileIconThemeColorState == null) {
            tileIconThemeColorState = SepQSResPool.getTileIconColorState();
        } else {
            if (this.disableTheme) {
                tileIconThemeColorState = null;
            }
            if (tileIconThemeColorState == null) {
                tileIconThemeColorState = SepQSResPool.getTileIconColorState();
            }
        }
        this.iconViewProxy.setTintList(tileIconThemeColorState != null ? tileIconThemeColorState.getColorStateList(this.tileColorfulConfig) : null);
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        layoutViewToCenter(this.iconBgView);
        layoutViewToCenter(getIconView());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasure(int i, int i2) {
        Integer numValueOf;
        super/*android.view.ViewGroup*/.onMeasure(i, i2);
        this.iconBgView.measure(i, i2);
        SpanSize spanSize = this.spanSize;
        boolean z = this.tileToggleable;
        if (SpanSizeKt.isOneXOne(spanSize)) {
            numValueOf = Integer.valueOf(dimen(z ? 2131173120 : 2131173118));
        } else if (SpanSizeKt.isTwoXOne(spanSize)) {
            numValueOf = Integer.valueOf(dimen(z ? 2131173125 : 2131173122));
        } else if (SpanSizeKt.isTwoXTwo(spanSize)) {
            numValueOf = Integer.valueOf(dimen(z ? 2131173133 : 2131173129));
        } else {
            numValueOf = null;
        }
        Integer numValueOf2 = numValueOf != null ? Integer.valueOf(MathKt.roundToInt(numValueOf.intValue() * this.iconScaleRatio)) : null;
        if (numValueOf2 != null) {
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(numValueOf2.intValue(), 1073741824);
            getIconView().measure(iMakeMeasureSpec, iMakeMeasureSpec);
        }
    }

    public final void onRecycle() {
        this.iconViewProxy.onRecycle();
    }

    public final void onThemeApplied(boolean z) {
        updateIconBgDrawable();
    }

    public void setIcon(QSTile.State state, boolean z) {
        setTileToggleable(state.toggleable);
        setTileColorfulConfig(Integer.valueOf(state.colorfulConfig));
        this.iconViewProxy.setIcon(state, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setIconScaleRatio(float f) {
        if (this.iconScaleRatio == f) {
            return;
        }
        this.iconScaleRatio = f;
        requestLayout();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String toString() {
        StringBuilder sb = new StringBuilder(super/*android.view.ViewGroup*/.toString());
        int left = getIconView().getLeft();
        int top = getIconView().getTop();
        int right = getIconView().getRight();
        int bottom = getIconView().getBottom();
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m(", iconView rect: left=", left, top, ", top=", ", right=");
        sbM.append(right);
        sbM.append(", bottom=");
        sbM.append(bottom);
        sb.append(sbM.toString());
        sb.append(", iconView measuredWidth=" + getIconView().getMeasuredWidth() + ", measuredHeight=" + getIconView().getMeasuredHeight());
        int paddingLeft = getIconView().getPaddingLeft();
        int paddingTop = getIconView().getPaddingTop();
        int paddingRight = getIconView().getPaddingRight();
        int paddingBottom = getIconView().getPaddingBottom();
        StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m(", iconView padding=", paddingLeft, paddingTop, ", ", ", ");
        sbM2.append(paddingRight);
        sbM2.append(", ");
        sbM2.append(paddingBottom);
        sb.append(sbM2.toString());
        sb.append("  ---------------------------------");
        sb.append(", OplusQSIconView measuredWidth=" + getMeasuredWidth());
        sb.append(", OplusQSIconView measuredHeight=" + getMeasuredHeight());
        return sb.toString();
    }

    public final void updateIconBgDrawable() {
        if (this.showIconBg) {
            if (!BaseQSResPool.globalThemeApplied() || this.disableTheme) {
                getIconBgDrawable().setTransitionAlpha(255);
            } else {
                getIconBgDrawable().setTransitionAlpha(0);
            }
        }
    }
}
