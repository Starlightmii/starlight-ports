package com.oplus.systemui.qs.base.widget;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsViewOutlineProvider$QsViewBaseOutlineProvider$invalidateOutlineRunnable$1 implements Runnable {
    public /* synthetic */ View $hostView;
    public /* synthetic */ QsViewOutlineProvider.QsViewBaseOutlineProvider this$0;

    @Override // java.lang.Runnable
    public final void run() {
        this.$hostView.invalidateOutline();
        this.this$0.refreshSmoothRound();
    }
}
