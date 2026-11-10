package com.oplus.systemui.separate;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSimpleHeader$simpleQsLayoutChangeListener$1 implements View.OnLayoutChangeListener {
    public /* synthetic */ OplusQSSimpleHeader this$0;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i2 == i6 && i4 == i8) {
            return;
        }
        OplusQSSimpleHeader.access$updateTiltShiftBlurHeight(this.this$0);
    }
}
