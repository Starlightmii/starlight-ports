package com.oplus.systemui.qs.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import com.oplus.systemui.qs.base.R$styleable;
import com.oplus.systemui.qs.base.spotlight.SpotLightLinearLayout;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MaxHeightAndWidthLinearLayout extends SpotLightLinearLayout {
    public int maxHeight;
    public int maxWidth;
    public final boolean supportSpotLight;

    public MaxHeightAndWidthLinearLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.maxHeight = Integer.MAX_VALUE;
        this.maxWidth = Integer.MAX_VALUE;
        int[] iArr = R$styleable.MaxHeightAndWidthLinearLayout;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, i2);
        try {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, i, i2);
            setMaxHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(0, Integer.MAX_VALUE));
            setMaxWidth(typedArrayObtainStyledAttributes.getDimensionPixelSize(1, Integer.MAX_VALUE));
            this.supportSpotLight = typedArrayObtainStyledAttributes.getBoolean(2, false);
            AutoCloseableKt.closeFinally(typedArrayObtainStyledAttributes, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(typedArrayObtainStyledAttributes, th);
                throw th2;
            }
        }
    }

    public final int getMaxHeight() {
        return this.maxHeight;
    }

    public final int getMaxWidth() {
        return this.maxWidth;
    }

    @Override // com.oplus.systemui.qs.base.spotlight.SpotLightLinearLayout
    public OplusRoundParams getSpotLightRoundParams() {
        return OplusQsSmoothRoundUtil.getRoundParams(getContext(), getContext().getResources().getDimensionPixelSize(2131171633));
    }

    @Override // com.oplus.systemui.qs.base.spotlight.SpotLightLinearLayout
    public final boolean isViewSupportSpotLight() {
        return this.supportSpotLight;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getSize(i) > this.maxWidth) {
            i = View.MeasureSpec.makeMeasureSpec(this.maxWidth, View.MeasureSpec.getMode(i));
        }
        if (View.MeasureSpec.getMode(i2) != 1073741824 && View.MeasureSpec.getSize(i2) > this.maxHeight) {
            i2 = View.MeasureSpec.makeMeasureSpec(this.maxHeight, View.MeasureSpec.getMode(i2));
        }
        super.onMeasure(i, i2);
    }

    public final void setMaxHeight(int i) {
        this.maxHeight = i;
    }

    public final void setMaxWidth(int i) {
        this.maxWidth = i;
    }

    public MaxHeightAndWidthLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public MaxHeightAndWidthLinearLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public /* synthetic */ MaxHeightAndWidthLinearLayout(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    public MaxHeightAndWidthLinearLayout(Context context) {
        this(context, null, 0, 0, 14, null);
    }
}
