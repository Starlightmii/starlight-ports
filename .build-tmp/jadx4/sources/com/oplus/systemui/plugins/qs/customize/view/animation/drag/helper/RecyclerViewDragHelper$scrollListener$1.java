package com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RecyclerViewDragHelper$scrollListener$1 extends RecyclerView.OnScrollListener {
    public /* synthetic */ RecyclerViewDragHelper this$0;

    public final void onScrollStateChanged(RecyclerView recyclerView, int i) {
        RecyclerViewDragHelper recyclerViewDragHelper = this.this$0;
        if (i != 0) {
            if (i != 3) {
                return;
            }
            recyclerView.removeCallbacks(recyclerViewDragHelper.dragRunnable);
        } else if (recyclerViewDragHelper.outputDragData != null) {
            recyclerViewDragHelper.moveIfNecessary(false);
        }
    }
}
