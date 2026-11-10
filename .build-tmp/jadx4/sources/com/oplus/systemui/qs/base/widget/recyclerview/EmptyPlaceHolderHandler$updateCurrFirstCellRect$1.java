package com.oplus.systemui.qs.base.widget.recyclerview;

import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EmptyPlaceHolderHandler$updateCurrFirstCellRect$1 implements ViewTreeObserver.OnPreDrawListener {
    public /* synthetic */ EmptyPlaceHolderHandler this$0;

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        this.this$0.getHostRecyclerView().getViewTreeObserver().removeOnPreDrawListener(this);
        this.this$0.currFirstCellRect.set(this.this$0.currFirstCellRect.left + this.this$0.cellRectDiff.left, this.this$0.currFirstCellRect.top + this.this$0.cellRectDiff.top, this.this$0.currFirstCellRect.right + this.this$0.cellRectDiff.right, this.this$0.currFirstCellRect.bottom + this.this$0.cellRectDiff.bottom);
        return true;
    }
}
