package com.oplus.systemui.shutdown;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OpAssistNavigationDialog$$ExternalSyntheticLambda1 implements View.OnTouchListener {
    public /* synthetic */ OpAssistNavigationDialog f$0;

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        OpAssistNavigationDialog opAssistNavigationDialog = this.f$0;
        return opAssistNavigationDialog.mCardBottom.getBottom() < opAssistNavigationDialog.mScrollView.getHeight();
    }
}
