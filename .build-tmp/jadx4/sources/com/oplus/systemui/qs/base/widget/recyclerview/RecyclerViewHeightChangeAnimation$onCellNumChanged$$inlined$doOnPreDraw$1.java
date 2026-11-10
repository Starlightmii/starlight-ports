package com.oplus.systemui.qs.base.widget.recyclerview;

import android.view.View;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RecyclerViewHeightChangeAnimation$onCellNumChanged$$inlined$doOnPreDraw$1 implements Runnable {
    public /* synthetic */ RecyclerViewHeightChangeAnimation this$0;

    @Override // java.lang.Runnable
    public final void run() {
        RecyclerViewHeightChangeAnimation recyclerViewHeightChangeAnimation = this.this$0;
        if (recyclerViewHeightChangeAnimation.curEnable) {
            int rowCount = recyclerViewHeightChangeAnimation.layoutManager.getLayoutConfig().getRowCount();
            int colCount = this.this$0.layoutManager.getLayoutConfig().getColCount();
            SpanSize spanSize = this.this$0.currGridInfo;
            if (spanSize == null) {
                spanSize = null;
            }
            if (rowCount == spanSize.getRowSpan()) {
                SpanSize spanSize2 = this.this$0.currGridInfo;
                if (spanSize2 == null) {
                    spanSize2 = null;
                }
                if (colCount == spanSize2.getColSpan()) {
                    return;
                }
            }
            RecyclerViewHeightChangeAnimation recyclerViewHeightChangeAnimation2 = this.this$0;
            SpanSize spanSize3 = recyclerViewHeightChangeAnimation2.currGridInfo;
            if (spanSize3 == null) {
                spanSize3 = null;
            }
            recyclerViewHeightChangeAnimation2.lastGridInfo = spanSize3;
            recyclerViewHeightChangeAnimation2.currGridInfo = SpanCache.INSTANCE.spanSize(colCount, rowCount);
            boolean zAccess$isNeedAnimator = RecyclerViewHeightChangeAnimation.access$isNeedAnimator(this.this$0);
            RecyclerViewHeightChangeAnimation recyclerViewHeightChangeAnimation3 = this.this$0;
            if (zAccess$isNeedAnimator) {
                RecyclerViewHeightChangeAnimation.access$prepareAnimator(recyclerViewHeightChangeAnimation3);
            } else {
                int childCount = recyclerViewHeightChangeAnimation3.layoutManager.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = this.this$0.layoutManager.getChildAt(i);
                    if (childAt != null) {
                        RecyclerViewHeightChangeAnimation.resetItemAnimator(childAt);
                    }
                }
            }
            if (this.this$0.layoutManager.getCurrentPageIndex() != 0) {
                RecyclerViewHeightChangeAnimation.access$startRecyclerviewAlphaAnimator(this.this$0);
            }
            RecyclerViewHeightChangeAnimation recyclerViewHeightChangeAnimation4 = this.this$0;
            SpanSize spanSize4 = recyclerViewHeightChangeAnimation4.lastGridInfo;
            if (spanSize4 == null) {
                spanSize4 = null;
            }
            SpanSize spanSize5 = recyclerViewHeightChangeAnimation4.currGridInfo;
            if (spanSize5 == null) {
                spanSize5 = null;
            }
            LogUtil.i$default(LogUtil.TAG_QS, "OplusItemShowHideAnimator", "onCellNumChanged predraw, lastGridInfo=" + spanSize4 + ", currGridInfo=" + spanSize5, null, 8);
            this.this$0.layoutManager.scrollToPage(0);
        }
    }
}
