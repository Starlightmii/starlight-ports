package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.Context;
import android.view.View;
import com.android.systemui.plugins.qs.QSIconView;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.base.springtransition.DrawableTransitionAlphaTarget;
import com.oplus.systemui.qs.base.springtransition.IIntTarget;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSThreeStageIconView extends QSIconView {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean disableTheme;
    private final DrawableTransitionAlphaTarget iconBgTransitionTarget;
    public float iconScaleRatio;
    private final IIntTarget iconViewTransitionTarget;
    private final Lazy segmentLayout$delegate;
    private Integer tileColorfulConfig;

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSThreeStageIconView(Context context) {
        super(context);
        OplusQSThreeStageIconView$$ExternalSyntheticLambda0 oplusQSThreeStageIconView$$ExternalSyntheticLambda0 = new OplusQSThreeStageIconView$$ExternalSyntheticLambda0();
        oplusQSThreeStageIconView$$ExternalSyntheticLambda0.f$0 = context;
        VarHandle.storeStoreFence();
        this.segmentLayout$delegate = LazyKt__LazyJVMKt.lazy(oplusQSThreeStageIconView$$ExternalSyntheticLambda0);
        this.iconViewTransitionTarget = getSegmentLayout().getIconViewTransitionTarget();
        this.iconBgTransitionTarget = getSegmentLayout().getIconBgTransitionTarget();
        this.iconScaleRatio = 1.0f;
        setDuplicateParentStateEnabled(true);
        addView(getSegmentLayout(), -1, -1);
    }

    private final OplusQSThreeStageLayout getSegmentLayout() {
        return (OplusQSThreeStageLayout) this.segmentLayout$delegate.getValue();
    }

    private final void setDisableTheme(boolean z) {
        if (this.disableTheme == z) {
            return;
        }
        this.disableTheme = z;
        getSegmentLayout().disableTheme = z;
        onIconTintUpdate();
        getSegmentLayout().updateIconBgDrawable();
    }

    private final void setTileColorfulConfig(Integer num) {
        if (Intrinsics.areEqual(this.tileColorfulConfig, num)) {
            return;
        }
        this.tileColorfulConfig = num;
        onIconTintUpdate();
    }

    public final void disableAnimation() {
    }

    public final void disableTheme(boolean z) {
        setDisableTheme(z);
    }

    public final DrawableTransitionAlphaTarget getIconBgTransitionTarget() {
        return this.iconBgTransitionTarget;
    }

    public final float getIconScaleRatio() {
        return this.iconScaleRatio;
    }

    public View getIconView() {
        return getSegmentLayout();
    }

    public final IIntTarget getIconViewTransitionTarget() {
        return this.iconViewTransitionTarget;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.view.ViewGroup*/.onAttachedToWindow();
        getSegmentLayout().updateIconBgDrawable();
        onIconDrawableUpdate(false);
        onIconDrawableUpdate(true);
        onIconTintUpdate();
    }

    public final void onIconDrawableUpdate(boolean z) {
        getSegmentLayout().onIconDrawableUpdate(z);
    }

    public final void onIconTintUpdate() {
        getSegmentLayout().tintColor(this.tileColorfulConfig);
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (getSegmentLayout().getVisibility() != 8) {
            getSegmentLayout().layout(0, 0, i3 - i, i4 - i2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasure(int i, int i2) {
        super/*android.view.ViewGroup*/.onMeasure(i, i2);
        measureChildren(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    public final void onThemeApplied(boolean z) {
        getSegmentLayout().updateIconBgDrawable();
    }

    public void setIcon(QSTile.State state, boolean z) {
        setTileColorfulConfig(Integer.valueOf(state.colorfulConfig));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setIconScaleRatio(float f) {
        if (f != this.iconScaleRatio) {
            getSegmentLayout().updateIconViewSize(MathKt.roundToInt(getResources().getDimensionPixelSize(2131173138) * f));
        }
        this.iconScaleRatio = f;
    }
}
