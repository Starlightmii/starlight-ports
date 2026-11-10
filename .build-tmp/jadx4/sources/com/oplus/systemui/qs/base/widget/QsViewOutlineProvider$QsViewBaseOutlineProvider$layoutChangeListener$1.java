package com.oplus.systemui.qs.base.widget;

import android.view.View;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsViewOutlineProvider$QsViewBaseOutlineProvider$layoutChangeListener$1 implements View.OnLayoutChangeListener {
    public /* synthetic */ QsViewOutlineProvider.QsViewBaseOutlineProvider this$0;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int width = view.getWidth();
        int height = view.getHeight();
        int i9 = i7 - i5;
        int i10 = i8 - i6;
        if (width <= 0 || height <= 0 || width == i9 || height == i10) {
            return;
        }
        if (Intrinsics.areEqual(view.getOutlineProvider(), this.this$0)) {
            this.this$0.refreshSmoothRound();
        } else {
            view.removeOnLayoutChangeListener(this);
        }
    }
}
