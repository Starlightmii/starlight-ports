package com.oplus.systemui.notification.headsup.windowframe;

import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpLayout$childrenUpdater$1 implements ViewTreeObserver.OnPreDrawListener {
    public /* synthetic */ HeadsUpLayout this$0;

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        this.this$0.updateChildren();
        this.this$0.setChildrenUpdateRequested(false);
        this.this$0.getViewTreeObserver().removeOnPreDrawListener(this);
        return true;
    }
}
