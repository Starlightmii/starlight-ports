package com.oplus.systemui.qs.base.tile;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ButtonLinearLayout extends LinearLayout {
    private Function0 doOnMeasure;

    public ButtonLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "android.widget.Button";
    }

    public final Function0 getDoOnMeasure() {
        return this.doOnMeasure;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Function0 function0 = this.doOnMeasure;
        if (function0 == null || !((Boolean) function0.invoke()).booleanValue()) {
            return;
        }
        super.onMeasure(i, i2);
    }

    public final void setDoOnMeasure(Function0 function0) {
        this.doOnMeasure = function0;
    }
}
