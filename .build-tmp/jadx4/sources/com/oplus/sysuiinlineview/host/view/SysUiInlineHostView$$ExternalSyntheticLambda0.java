package com.oplus.sysuiinlineview.host.view;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SysUiInlineHostView$$ExternalSyntheticLambda0 implements View.OnLayoutChangeListener {
    public /* synthetic */ SysUiInlineHostView f$0;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        SysUiInlineHostView sysUiInlineHostView = this.f$0;
        int i9 = SysUiInlineHostView.$r8$clinit;
        if (i == i5 && i2 == i6 && i3 == i7 && i4 == i8) {
            return;
        }
        sysUiInlineHostView.onHostViewLayout();
    }
}
