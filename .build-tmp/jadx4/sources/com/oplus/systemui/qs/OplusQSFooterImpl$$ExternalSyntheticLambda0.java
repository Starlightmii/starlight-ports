package com.oplus.systemui.qs;

import android.view.View;
import android.view.animation.Interpolator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSFooterImpl$$ExternalSyntheticLambda0 implements View.OnLayoutChangeListener {
    public /* synthetic */ OplusQSFooterImpl f$0;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        OplusQSFooterImpl oplusQSFooterImpl = this.f$0;
        Interpolator interpolator = OplusQSFooterImpl.DATE_X_EXPAND;
        int i9 = i3 - i;
        if (i9 == i7 - i5 || i9 <= 0) {
            return;
        }
        oplusQSFooterImpl.createAndUpdateExpandAnimators(oplusQSFooterImpl.mShowUserSwitcher);
        oplusQSFooterImpl.updateExpand(oplusQSFooterImpl.mExpansionAmount);
    }
}
