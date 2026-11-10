package com.oplus.systemui.statusbar.phone.dynamic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class DynamicContentLayout extends LinearLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "DynamicContentLayout";
    public int maxWidth;
    public boolean needRelayout;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public DynamicContentLayout(Context context) {
        this(context, null);
    }

    public final int getMaxWidth() {
        return this.maxWidth;
    }

    public final boolean getNeedRelayout() {
        return this.needRelayout;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3 = this.maxWidth;
        if (i3 > 0 && View.MeasureSpec.getSize(i) > i3) {
            i = View.MeasureSpec.getMode(i) == Integer.MIN_VALUE ? View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE) : View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    public final void setMaxWidth(int i) {
        this.maxWidth = i;
        if (this.needRelayout) {
            requestLayout();
        }
    }

    public final void setNeedRelayout(boolean z) {
        this.needRelayout = z;
    }

    public DynamicContentLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DynamicContentLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
