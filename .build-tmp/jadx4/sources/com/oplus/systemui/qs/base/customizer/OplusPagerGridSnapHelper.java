package com.oplus.systemui.qs.base.customizer;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPagerGridSnapHelper extends SnapHelper {
    public int mFlingThreshold = 1000;
    public RecyclerView mRecyclerView;

    public final void attachToRecyclerView(RecyclerView recyclerView) {
        super.attachToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    public final int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        return layoutManager instanceof OplusPagerGridLayoutManager ? ((OplusPagerGridLayoutManager) layoutManager).getSnapOffset(layoutManager.getPosition(view)) : new int[2];
    }

    public final RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return new OplusPagerGridSmoothScroller(this.mRecyclerView);
        }
        return null;
    }

    public final View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof OplusPagerGridLayoutManager) {
            return ((OplusPagerGridLayoutManager) layoutManager).findSnapView();
        }
        return null;
    }

    public final int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i, int i2) {
        int i3;
        int i4;
        int totalPageCount;
        int i5;
        if (!(layoutManager instanceof OplusPagerGridLayoutManager)) {
            return -1;
        }
        OplusPagerGridLayoutManager oplusPagerGridLayoutManager = (OplusPagerGridLayoutManager) layoutManager;
        if (oplusPagerGridLayoutManager.mShouldReverseLayout) {
            i = -i;
        }
        if (oplusPagerGridLayoutManager.canScrollHorizontally()) {
            int i6 = this.mFlingThreshold;
            if (i > i6) {
                totalPageCount = oplusPagerGridLayoutManager.mLastPageIndex + 1;
                if (totalPageCount >= oplusPagerGridLayoutManager.getTotalPageCount()) {
                    totalPageCount = oplusPagerGridLayoutManager.getTotalPageCount() - 1;
                }
                i5 = oplusPagerGridLayoutManager.mOnePageSize;
                return totalPageCount * i5;
            }
            if (i >= (-i6)) {
                return -1;
            }
            int i7 = oplusPagerGridLayoutManager.mLastPageIndex - 1;
            i3 = i7 >= 0 ? i7 : 0;
            i4 = oplusPagerGridLayoutManager.mOnePageSize;
            return ((i3 * i4) + i4) - 1;
        }
        if (!oplusPagerGridLayoutManager.canScrollVertically()) {
            return -1;
        }
        int i8 = this.mFlingThreshold;
        if (i2 > i8) {
            totalPageCount = oplusPagerGridLayoutManager.mLastPageIndex + 1;
            if (totalPageCount >= oplusPagerGridLayoutManager.getTotalPageCount()) {
                totalPageCount = oplusPagerGridLayoutManager.getTotalPageCount() - 1;
            }
            i5 = oplusPagerGridLayoutManager.mOnePageSize;
            return totalPageCount * i5;
        }
        if (i2 >= (-i8)) {
            return -1;
        }
        int i9 = oplusPagerGridLayoutManager.mLastPageIndex - 1;
        i3 = i9 >= 0 ? i9 : 0;
        i4 = oplusPagerGridLayoutManager.mOnePageSize;
        return ((i3 * i4) + i4) - 1;
    }

    public final boolean onFling(int i, int i2) {
        boolean z;
        int iFindTargetSnapPosition;
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager == null || this.mRecyclerView.getAdapter() == null) {
            return false;
        }
        int i3 = this.mFlingThreshold;
        if ((Math.abs(i2) <= i3 && Math.abs(i) <= i3) || !((z = layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider))) {
            return false;
        }
        RecyclerView.SmoothScroller oplusPagerGridSmoothScroller = !z ? null : new OplusPagerGridSmoothScroller(this.mRecyclerView);
        if (oplusPagerGridSmoothScroller == null || (iFindTargetSnapPosition = findTargetSnapPosition(layoutManager, i, i2)) == -1) {
            return false;
        }
        oplusPagerGridSmoothScroller.setTargetPosition(iFindTargetSnapPosition);
        layoutManager.startSmoothScroll(oplusPagerGridSmoothScroller);
        return true;
    }

    public void setFlingThreshold(int i) {
        this.mFlingThreshold = i;
    }
}
