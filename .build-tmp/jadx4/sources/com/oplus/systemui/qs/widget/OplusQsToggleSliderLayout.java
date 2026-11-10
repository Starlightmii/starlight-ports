package com.oplus.systemui.qs.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.oplus.systemui.qs.base.res.StdQSResPool;
import com.oplus.systemui.qs.base.res.model.ShapeConfig;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.qs.base.seek.OplusQsVerticalSeekBar;
import com.oplus.systemui.statusbar.seeding.OneHandedObserver;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsToggleSliderLayout extends OplusQsBaseToggleSliderLayout {
    public /* synthetic */ OplusQsToggleSliderLayout(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public boolean getOneHandedEnable() {
        return OneHandedObserver.Companion.getInstance().getOneHandedEnable();
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public float getRadius() {
        OplusQsVerticalSeekBar slider = getSlider();
        return (slider == null || slider.isThemeApplied()) ? super.getRadius() : ((ShapeConfig) StdQSResPool.tileShapeConfig.getValue()).getRadius(getContext());
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public final boolean isSupportStroke() {
        return StdQSResPool.isSupportStrokeCorner();
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        setCornerRadius(((ShapeConfig) StdQSResPool.tileShapeConfig.getValue()).getRadius(getContext()));
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public final void updateCornerRadius() {
        setCornerRadius(getRadius());
    }

    public OplusQsToggleSliderLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public OplusQsToggleSliderLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public OplusQsToggleSliderLayout(Context context) {
        this(context, null, 0, 0, 14, null);
    }

    public OplusQsToggleSliderLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
