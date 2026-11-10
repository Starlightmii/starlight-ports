package com.oplus.systemui.qs.base.widget;

import android.graphics.Rect;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import kotlin.Pair;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpannedGridLayoutManager extends RecyclerView.LayoutManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SpannedGridLayoutManager";
    public int availableDistance;
    public int cellHeight;
    public int cellWidth;
    public int currentScroll;
    public int horizontalSpace;
    public int layoutEnd;
    public int layoutStart;
    private RectHelper rectHelper;
    private RecyclerView recyclerView;
    public int spanCount;
    public int verticalSpace;
    public final int orientation = 1;
    private SpanSizeLookUp spanSizeLookUp = new SpanSizeLookUp(null);
    private final Rect displayRect = new Rect();
    private final SparseArray<Rect> itemLayoutRects = new SparseArray<>();
    private final ArrayList<FullSpanItem> fullSpanItems = new ArrayList<>();
    private final ArrayList<Integer> layoutItems = new ArrayList<>();
    public final OrientationHelper orientationHelper = OrientationHelper.createOrientationHelper(this, 1);

    public SpannedGridLayoutManager(int i, int i2) {
        setSpanCount(4);
        setCellWidth(i);
        setCellHeight(i2);
        setHorizontalSpace(0);
        setVerticalSpace(0);
    }

    public final boolean canScrollHorizontally() {
        return this.orientation == 0;
    }

    public final boolean canScrollVertically() {
        return this.orientation == 1;
    }

    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.INSTANCE.computeScrollExtent(state, this.orientationHelper, getChildAt(0), getChildAt(getChildCount() - 1), this, true);
    }

    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset$2(state);
    }

    public int computeHorizontalScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.INSTANCE.computeScrollRange(state, this.orientationHelper, getChildAt(0), getChildAt(getChildCount() - 1), this, true);
    }

    public final int computeScrollOffset$2(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.INSTANCE.computeScrollOffset(state, this.orientationHelper, getChildAt(0), getChildAt(getChildCount() - 1), this, true, this.orientation == 0 && getLayoutDirection() == 1);
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.INSTANCE.computeScrollExtent(state, this.orientationHelper, getChildAt(0), getChildAt(getChildCount() - 1), this, true);
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset$2(state);
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.INSTANCE.computeScrollRange(state, this.orientationHelper, getChildAt(0), getChildAt(getChildCount() - 1), this, true);
    }

    public final void fillItem(RecyclerView.Recycler recycler, Rect rect, int i, boolean z) {
        if (this.layoutItems.contains(Integer.valueOf(i))) {
            return;
        }
        Rect itemLayoutRect = getItemLayoutRect(i);
        if (Rect.intersects(itemLayoutRect, rect)) {
            View viewForPosition = recycler.getViewForPosition(i);
            if (viewForPosition.getParent() != null) {
                return;
            }
            if (z) {
                addView(viewForPosition);
            } else {
                addView(viewForPosition, 0);
            }
            if (viewForPosition.isLayoutRequested() || viewForPosition.getMeasuredWidth() != itemLayoutRect.width() || viewForPosition.getMeasuredHeight() != itemLayoutRect.height()) {
                viewForPosition.measure(View.MeasureSpec.makeMeasureSpec(itemLayoutRect.width(), 1073741824), View.MeasureSpec.makeMeasureSpec(itemLayoutRect.height(), 1073741824));
            }
            int i2 = itemLayoutRect.left;
            int i3 = itemLayoutRect.top;
            int i4 = this.currentScroll;
            layoutDecoratedWithMargins(viewForPosition, i2, i3 - i4, itemLayoutRect.right, itemLayoutRect.bottom - i4);
            int i5 = itemLayoutRect.top;
            if (i5 < this.layoutStart) {
                this.layoutStart = i5;
            }
            int i6 = itemLayoutRect.bottom;
            if (i6 > this.layoutEnd) {
                this.layoutEnd = i6;
            }
            this.layoutItems.add(Integer.valueOf(i));
        }
    }

    public final void fillItems(int i, RecyclerView.Recycler recycler, boolean z) {
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop() + this.currentScroll;
        this.displayRect.set(paddingLeft, paddingTop, width + paddingLeft, height + paddingTop);
        boolean z2 = (i >= 0 && !z) || (i < 0 && z);
        for (int childCount = getChildCount() - 1; -1 < childCount; childCount--) {
            Rect rect = this.displayRect;
            View childAt = getChildAt(childCount);
            if (childAt != null) {
                int position = getPosition(childAt);
                if (!Rect.intersects(getItemLayoutRect(position), rect)) {
                    this.layoutItems.remove(Integer.valueOf(position));
                    removeAndRecycleViewAt(childCount, recycler);
                }
            }
        }
        if (z2) {
            int itemCount = getItemCount();
            for (int i2 = 0; i2 < itemCount; i2++) {
                fillItem(recycler, this.displayRect, i2, true);
            }
            return;
        }
        for (int itemCount2 = getItemCount() - 1; -1 < itemCount2; itemCount2--) {
            fillItem(recycler, this.displayRect, itemCount2, false);
        }
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return this.orientation == 1 ? new RecyclerView.LayoutParams(-1, -2) : new RecyclerView.LayoutParams(-2, -1);
    }

    public final int getCellHeight() {
        return this.cellHeight;
    }

    public final int getCellWidth() {
        return this.cellWidth;
    }

    public final int getHorizontalSpace() {
        return this.horizontalSpace;
    }

    public final Rect getItemLayoutRect(int i) {
        Pair<Rect, Rect> itemRect;
        Rect rect;
        Rect rect2;
        Integer specialSizeForFullSpan;
        if (this.itemLayoutRects.get(i) != null) {
            return this.itemLayoutRects.get(i);
        }
        SpanSizeLookUp spanSizeLookUp = this.spanSizeLookUp;
        int i2 = this.spanCount;
        int i3 = this.orientation;
        SpanSize spanSize = spanSizeLookUp.getSpanSize(i, i2, i3);
        RectHelper rectHelper = this.rectHelper;
        if (rectHelper == null || (itemRect = rectHelper.getItemRect(i, spanSize)) == null || (rect = (Rect) itemRect.getFirst()) == null) {
            return new Rect();
        }
        if (canScrollVertically()) {
            boolean zIsFullSpan = SpannedGridLayoutManagerKt.isFullSpan(spanSize, this.spanCount, i3);
            int spans = 0;
            boolean z = getLayoutDirection() == 1;
            ArrayList<FullSpanItem> arrayList = this.fullSpanItems;
            ArrayList arrayList2 = new ArrayList();
            int size = arrayList.size();
            int i4 = 0;
            while (i4 < size) {
                FullSpanItem fullSpanItem = arrayList.get(i4);
                i4++;
                if (fullSpanItem.getPosition() < i) {
                    arrayList2.add(fullSpanItem);
                }
            }
            int size2 = arrayList2.size();
            int size3 = 0;
            int i5 = 0;
            while (i5 < size2) {
                Object obj = arrayList2.get(i5);
                i5++;
                size3 += ((FullSpanItem) obj).getSize();
            }
            ArrayList<FullSpanItem> arrayList3 = this.fullSpanItems;
            ArrayList arrayList4 = new ArrayList();
            int size4 = arrayList3.size();
            int i6 = 0;
            while (i6 < size4) {
                FullSpanItem fullSpanItem2 = arrayList3.get(i6);
                i6++;
                if (fullSpanItem2.getPosition() < i) {
                    arrayList4.add(fullSpanItem2);
                }
            }
            int size5 = arrayList4.size();
            int i7 = 0;
            while (i7 < size5) {
                Object obj2 = arrayList4.get(i7);
                i7++;
                spans += ((FullSpanItem) obj2).getSpans();
            }
            int paddingTop = getPaddingTop();
            int i8 = rect.top;
            int i9 = this.cellHeight;
            int i10 = this.verticalSpace;
            int i11 = i9 + i10;
            int i12 = ((i8 - spans) * i11) + paddingTop + size3;
            int iIntValue = ((rect.bottom - i8) * i11) - i10;
            if (zIsFullSpan && (specialSizeForFullSpan = this.spanSizeLookUp.getSpecialSizeForFullSpan(i)) != null) {
                iIntValue = specialSizeForFullSpan.intValue();
            }
            if (z) {
                int width = (getWidth() - getPaddingLeft()) - ((this.cellWidth + this.horizontalSpace) * rect.left);
                int i13 = rect.right - rect.left;
                int i14 = this.cellWidth;
                int i15 = this.horizontalSpace;
                rect2 = new Rect((width - ((i14 + i15) * i13)) + i15, i12, width, iIntValue + i12);
            } else {
                int paddingLeft = ((this.cellWidth + this.horizontalSpace) * rect.left) + getPaddingLeft();
                int i16 = rect.right - rect.left;
                int i17 = this.cellWidth;
                int i18 = this.horizontalSpace;
                rect2 = new Rect(paddingLeft, i12, (((i17 + i18) * i16) + paddingLeft) - i18, iIntValue + i12);
            }
        } else {
            rect2 = new Rect();
        }
        if (!rect2.isEmpty() && rect2.isValid()) {
            this.itemLayoutRects.put(i, rect2);
            if (SpannedGridLayoutManagerKt.isFullSpan(spanSize, this.spanCount, i3)) {
                this.fullSpanItems.add(new FullSpanItem(i, i3 == 1 ? spanSize.getRowSpan() : spanSize.getColSpan(), i3 == 1 ? rect2.height() : rect2.width()));
            }
        }
        return rect2;
    }

    public final int getSpanCount() {
        return this.spanCount;
    }

    public final SpanSizeLookUp getSpanSizeLookUp() {
        return this.spanSizeLookUp;
    }

    public final int getVerticalSpace() {
        return this.verticalSpace;
    }

    public void onAttachedToWindow(RecyclerView recyclerView) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SpannedGridLayoutManager-->[onAttachedToWindow]");
        }
        super.onAttachedToWindow(recyclerView);
        this.recyclerView = recyclerView;
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SpannedGridLayoutManager-->[onDetachedFromWindow]");
        }
        onDetachedFromWindow(recyclerView);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0060  */
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        boolean z;
        if (state.mInPreLayout) {
            return;
        }
        if (getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        OrientationHelper orientationHelper = this.orientationHelper;
        this.layoutStart = orientationHelper.getStartAfterPadding();
        this.layoutEnd = orientationHelper.getEndPadding();
        this.layoutItems.clear();
        detachAndScrapAttachedViews(recycler);
        int i = this.spanCount;
        int i2 = this.orientation;
        RectHelper rectHelper = new RectHelper(i2, i);
        this.rectHelper = rectHelper;
        int itemCount = state.getItemCount();
        for (int i3 = 0; i3 < itemCount; i3++) {
            Pair<Rect, Rect> itemRect = rectHelper.getItemRect(i3, this.spanSizeLookUp.getSpanSize(i3, this.spanCount, i2));
            rectHelper.pushRect(i3, (Rect) itemRect.getFirst(), (Rect) itemRect.getSecond());
        }
        if (i2 == 0) {
            z = getLayoutDirection() == 1;
        }
        fillItems(0, recycler, z);
        Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        SparseArray<Rect> sparseArray = this.itemLayoutRects;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            sparseArray.keyAt(i);
            Rect rectValueAt = sparseArray.valueAt(i);
            int i2 = this.orientation;
            int i3 = i2 == 1 ? rectValueAt.bottom : (i2 == 0 && getLayoutDirection() == 1) ? rectValueAt.left : rectValueAt.right;
            int i4 = this.availableDistance;
            if (i4 >= i3) {
                i3 = i4;
            }
            this.availableDistance = i3;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[onLayoutCompleted] " + state + ", availableDistance: " + this.availableDistance, null, 8);
    }

    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        Integer numValueOf;
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i3 = this.orientation;
        if (i3 == 1 && mode != 1073741824 && size > 0) {
            mode = 1073741824;
        }
        if (i3 == 0 && mode2 != 1073741824 && size2 > 0) {
            mode2 = 1073741824;
        }
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(size, mode), View.MeasureSpec.makeMeasureSpec(size2, mode2));
        Integer numValueOf2 = null;
        if (i3 == 1) {
            RecyclerView recyclerView = this.recyclerView;
            int measuredWidth = recyclerView != null ? recyclerView.getMeasuredWidth() : 0;
            int i4 = this.spanCount;
            numValueOf = Integer.valueOf(((measuredWidth - (this.cellWidth * i4)) - ((i4 - 1) * this.horizontalSpace)) / 2);
        } else {
            numValueOf = null;
        }
        if (i3 == 0) {
            RecyclerView recyclerView2 = this.recyclerView;
            int measuredHeight = recyclerView2 != null ? recyclerView2.getMeasuredHeight() : 0;
            int i5 = this.spanCount;
            numValueOf2 = Integer.valueOf(((measuredHeight - (this.cellHeight * i5)) - ((i5 - 1) * this.verticalSpace)) / 2);
        }
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 != null) {
            recyclerView3.setPadding(numValueOf != null ? numValueOf.intValue() : getPaddingLeft(), numValueOf2 != null ? numValueOf2.intValue() : getPaddingTop(), numValueOf != null ? numValueOf.intValue() : getPaddingRight(), numValueOf2 != null ? numValueOf2.intValue() : getPaddingBottom());
        }
        this.itemLayoutRects.clear();
        this.availableDistance = 0;
        this.fullSpanItems.clear();
    }

    public final int scrollBy(int i, RecyclerView.Recycler recycler, boolean z) {
        int iCoerceIn;
        if (getChildCount() != 0 && i != 0) {
            int i2 = this.availableDistance;
            OrientationHelper orientationHelper = this.orientationHelper;
            int endPadding = orientationHelper.getEndPadding() + i2;
            int end = orientationHelper.getEnd();
            int i3 = this.currentScroll;
            if (z) {
                int i4 = endPadding - end;
                iCoerceIn = RangesKt___RangesKt.coerceIn(i3 + i, i4, i4 < 0 ? 0 : i4);
            } else {
                int i5 = i3 + i;
                int i6 = endPadding - end;
                if (i6 < 0) {
                    i6 = 0;
                }
                iCoerceIn = RangesKt___RangesKt.coerceIn(i5, 0, i6);
            }
            int i7 = this.currentScroll;
            if (iCoerceIn != i7) {
                int i8 = iCoerceIn - i7;
                orientationHelper.offsetChildren(-i8);
                this.currentScroll = iCoerceIn;
                fillItems(i8, recycler, z);
                return i8;
            }
        }
        return 0;
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.orientation == 1) {
            return 0;
        }
        return scrollBy(i, recycler, getLayoutDirection() == 1);
    }

    public final void scrollToPosition(int i) {
        super.scrollToPosition(i);
        requestLayout();
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.orientation == 0) {
            return 0;
        }
        return scrollBy(i, recycler, false);
    }

    public final void setCellHeight(int i) {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "[setCellHeight] "), null, 8);
        if (this.cellHeight != i) {
            this.cellHeight = i;
            requestLayout();
        }
    }

    public final void setCellWidth(int i) {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "[setCellWidth] "), null, 8);
        if (this.cellWidth != i) {
            this.cellWidth = i;
            requestLayout();
        }
    }

    public final void setHorizontalSpace(int i) {
        if (this.horizontalSpace == i || i < 0) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "[setHorizontalSpace] "), null, 8);
        this.horizontalSpace = i;
        requestLayout();
    }

    public final void setSpanCount(int i) {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "[setRowCount] "), null, 8);
        if (this.spanCount != i) {
            this.spanCount = i;
            this.spanSizeLookUp.invalidateSpanSizeCache();
            requestLayout();
        }
    }

    public final void setSpanSizeLookUp(SpanSizeLookUp spanSizeLookUp) {
        this.spanSizeLookUp = spanSizeLookUp;
    }

    public final void setVerticalSpace(int i) {
        if (this.verticalSpace == i || i < 0) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "[setVerticalSpace] "), null, 8);
        this.verticalSpace = i;
        requestLayout();
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }
}
