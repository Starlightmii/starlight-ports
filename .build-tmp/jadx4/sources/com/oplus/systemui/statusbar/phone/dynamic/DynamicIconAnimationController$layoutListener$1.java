package com.oplus.systemui.statusbar.phone.dynamic;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicIconAnimationController$layoutListener$1 implements View.OnLayoutChangeListener {
    public /* synthetic */ DynamicIconAnimationController this$0;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        DynamicContentLayout dynamicContentLayout;
        int width = view.getWidth() - this.this$0.getContext().getResources().getDimensionPixelSize(2131170848);
        DynamicContentLayout dynamicContentLayout2 = this.this$0.dynamicIconContent;
        if ((dynamicContentLayout2 == null || width != dynamicContentLayout2.getMaxWidth()) && (dynamicContentLayout = this.this$0.dynamicIconContent) != null) {
            dynamicContentLayout.setMaxWidth(width);
        }
    }
}
