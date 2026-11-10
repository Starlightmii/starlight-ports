package com.oplus.systemui.qs.base.customizer;

import android.graphics.PointF;
import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.recyclerview.widget.RecyclerView;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusPagerGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    public boolean mAllowContinuousScroll;
    public Callback mCallback;
    public boolean mChangeSelectInScrolling;
    public int mColumns;
    public IQSCustomizer$EditType mEditType;
    public int mHeightUsed;
    public int mHorizontalOffset;
    public int mHorizontalSidePadding;
    public SparseArray mItemFrames;
    public int mItemHeight;
    public int mItemWidth;
    public int mLastPageCount;
    public int mLastPageIndex;
    public ArrayList mLayoutItems;
    public int mLayoutModel;
    public int mMaxScrollX;
    public int mMaxScrollY;
    public int mOffsetX;
    public int mOffsetY;
    public int mOldWidth;
    public int mOnePageSize;
    public int mOrientation;
    public boolean mPageCountChanged;
    public RecyclerView mRecyclerView;
    public boolean mReverseLayout;
    public int mRows;
    public int mScrollState;
    public boolean mShouldReverseLayout;
    public int mSmallTileRows;
    public int mVerticalOffset;
    public int mWidthUsed;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder {
        public int mHorizontalOffset;
        public int mHorizontalSidePadding;
        public int mItemHeight;
        public int mVerticalOffset;
        public int mColumns = 1;
        public int mOrientation = 1;
        public boolean mReverseLayout = false;
        public IQSCustomizer$EditType mEditType = IQSCustomizer$EditType.HIGHLIGHT;

        public final OplusPagerGridLayoutManager build() {
            OplusPagerGridLayoutManager oplusPagerGridLayoutManager = new OplusPagerGridLayoutManager();
            SparseArray sparseArray = new SparseArray();
            oplusPagerGridLayoutManager.mItemFrames = sparseArray;
            oplusPagerGridLayoutManager.mOffsetX = 0;
            oplusPagerGridLayoutManager.mOffsetY = 0;
            oplusPagerGridLayoutManager.mItemWidth = 0;
            oplusPagerGridLayoutManager.mItemHeight = 0;
            oplusPagerGridLayoutManager.mWidthUsed = 0;
            oplusPagerGridLayoutManager.mHeightUsed = 0;
            oplusPagerGridLayoutManager.mScrollState = 0;
            oplusPagerGridLayoutManager.mAllowContinuousScroll = true;
            oplusPagerGridLayoutManager.mReverseLayout = false;
            oplusPagerGridLayoutManager.mChangeSelectInScrolling = true;
            oplusPagerGridLayoutManager.mLastPageCount = -1;
            oplusPagerGridLayoutManager.mLastPageIndex = -1;
            oplusPagerGridLayoutManager.mCallback = null;
            oplusPagerGridLayoutManager.mLayoutModel = 0;
            oplusPagerGridLayoutManager.mPageCountChanged = false;
            oplusPagerGridLayoutManager.mLayoutItems = new ArrayList();
            oplusPagerGridLayoutManager.mOrientation = this.mOrientation;
            oplusPagerGridLayoutManager.mReverseLayout = this.mReverseLayout;
            oplusPagerGridLayoutManager.mHorizontalOffset = this.mHorizontalOffset;
            oplusPagerGridLayoutManager.mVerticalOffset = this.mVerticalOffset;
            oplusPagerGridLayoutManager.mHorizontalSidePadding = this.mHorizontalSidePadding;
            oplusPagerGridLayoutManager.mEditType = this.mEditType;
            int i = this.mItemHeight;
            int i2 = this.mColumns;
            if (i != 0) {
                oplusPagerGridLayoutManager.updateItemHeightAndColumn(i, i2);
            } else {
                oplusPagerGridLayoutManager.mLayoutModel = 1;
                oplusPagerGridLayoutManager.mRows = 1;
                oplusPagerGridLayoutManager.mColumns = i2;
                oplusPagerGridLayoutManager.mItemHeight = 0;
                sparseArray.clear();
                oplusPagerGridLayoutManager.mOffsetX = 0;
                oplusPagerGridLayoutManager.mOffsetY = 0;
                oplusPagerGridLayoutManager.mScrollState = 0;
                RecyclerView recyclerView = oplusPagerGridLayoutManager.mRecyclerView;
                if (recyclerView == null) {
                    VarHandle.storeStoreFence();
                    return oplusPagerGridLayoutManager;
                }
                recyclerView.requestLayout();
            }
            VarHandle.storeStoreFence();
            return oplusPagerGridLayoutManager;
        }

        public Builder setEditType(IQSCustomizer$EditType iQSCustomizer$EditType) {
            this.mEditType = iQSCustomizer$EditType;
            return this;
        }

        public Builder setOrientation(int i) {
            this.mOrientation = i;
            return this;
        }

        public Builder setReverseLayout(boolean z) {
            this.mReverseLayout = z;
            return this;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
        default void onLayoutComplete(RecyclerView recyclerView, int i, int i2) {
        }

        void onPageScrollStateChanged();

        void onPageScrolled(float f, int i);

        void onPageSelect(int i, int i2);

        void onPageSizeChanged(int i);
    }

    private int getUsableHeight() {
        return this.mRecyclerView.getMeasuredHeight();
    }

    private int getUsableWidth() {
        return this.mRecyclerView.getMeasuredWidth();
    }

    private void setPageCount(int i) {
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i, "setPageCount pageCount= ", LogUtil.TAG_QS, "OplusPagerGridLayoutManager");
        if (i >= 0) {
            if (i != this.mLastPageCount) {
                this.mPageCountChanged = true;
                Callback callback = this.mCallback;
                if (callback != null) {
                    callback.onPageSizeChanged(i);
                }
            }
            this.mLastPageCount = i;
        }
    }

    public final boolean canScrollHorizontally() {
        return this.mOrientation == 1;
    }

    public final boolean canScrollVertically() {
        return this.mOrientation == 0;
    }

    public final PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        View childAt = getChildAt(0);
        if (childAt == null) {
            return null;
        }
        int i2 = (i < getPosition(childAt)) != this.mShouldReverseLayout ? -1 : 1;
        return this.mOrientation == 1 ? new PointF(i2, 0.0f) : new PointF(0.0f, i2);
    }

    public final void fillItem(RecyclerView.Recycler recycler, Rect rect, int i) {
        int usableWidth;
        int i2;
        Rect itemFrameByPosition = getItemFrameByPosition(i);
        if (!Rect.intersects(itemFrameByPosition, rect) || this.mLayoutItems.contains(Integer.valueOf(i))) {
            return;
        }
        View viewForPosition = recycler.getViewForPosition(i);
        if (viewForPosition.getParent() != null) {
            return;
        }
        addView(viewForPosition);
        measureChildWithMargins(viewForPosition, this.mWidthUsed, this.mHeightUsed);
        RecyclerView.LayoutParams layoutParams = viewForPosition.getLayoutParams();
        int paddingStart = getPaddingStart() + layoutParams.getMarginStart() + (itemFrameByPosition.left - this.mOffsetX);
        int paddingTop = getPaddingTop() + (itemFrameByPosition.top - this.mOffsetY) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        int paddingStart2 = getPaddingStart() + ((itemFrameByPosition.right - this.mOffsetX) - layoutParams.getMarginEnd());
        int paddingTop2 = getPaddingTop() + ((itemFrameByPosition.bottom - this.mOffsetY) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        if (this.mShouldReverseLayout) {
            int usableWidth2 = getUsableWidth() - paddingStart2;
            usableWidth = getUsableWidth() - paddingStart;
            i2 = usableWidth2;
        } else {
            usableWidth = paddingStart2;
            i2 = paddingStart;
        }
        layoutDecoratedWithMargins(viewForPosition, i2, paddingTop, usableWidth, paddingTop2);
        this.mLayoutItems.add(Integer.valueOf(i));
    }

    public final View findSnapView() {
        int pageIndexByOffset;
        int i;
        int pageIndexByOffset2;
        int i2;
        int i3;
        if (getChildCount() <= 0) {
            return null;
        }
        if (canScrollHorizontally()) {
            int usableWidth = getUsableWidth();
            if (this.mOffsetX % usableWidth > usableWidth / 2) {
                pageIndexByOffset2 = getPageIndexByOffset();
                i2 = this.mOnePageSize;
                i3 = pageIndexByOffset2 * i2;
            } else {
                pageIndexByOffset = getPageIndexByOffset() + 1;
                i = this.mOnePageSize;
                i3 = (pageIndexByOffset * i) - 1;
            }
        } else {
            int usableHeight = getUsableHeight();
            if (this.mOffsetY % usableHeight > usableHeight / 2) {
                pageIndexByOffset2 = getPageIndexByOffset();
                i2 = this.mOnePageSize;
                i3 = pageIndexByOffset2 * i2;
            } else {
                pageIndexByOffset = getPageIndexByOffset() + 1;
                i = this.mOnePageSize;
                i3 = (pageIndexByOffset * i) - 1;
            }
        }
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            View childAt = getChildAt(i4);
            if (childAt != null && getPosition(childAt) == i3) {
                return childAt;
            }
        }
        return getChildAt(0);
    }

    public final RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public int getColumns() {
        return this.mColumns;
    }

    public int getCurrentPageFirstPos() {
        return getPageIndexByOffset() * this.mOnePageSize;
    }

    public int getCurrentPageLastPos() {
        int pageIndexByOffset = getPageIndexByOffset();
        return (pageIndexByOffset < getTotalPageCount() + (-1) || getItemCount() % this.mOnePageSize == 0) ? ((pageIndexByOffset + 1) * this.mOnePageSize) - 1 : getItemCount() - 1;
    }

    public final Rect getItemFrameByPosition(int i) {
        int usableHeight;
        Rect rect = (Rect) this.mItemFrames.get(i);
        if (rect != null && this.mOldWidth == getUsableWidth()) {
            return rect;
        }
        Rect rect2 = new Rect();
        int i2 = i / this.mOnePageSize;
        int usableWidth = 0;
        if (canScrollHorizontally()) {
            usableWidth = (getUsableWidth() * i2) + this.mHorizontalSidePadding;
            usableHeight = 0;
        } else {
            usableHeight = getUsableHeight() * i2;
        }
        int i3 = i % this.mOnePageSize;
        int i4 = this.mColumns;
        int i5 = i3 / i4;
        int i6 = i3 - (i4 * i5);
        int i7 = this.mItemWidth;
        int i8 = ((this.mHorizontalOffset + i7) * i6) + usableWidth;
        int i9 = this.mItemHeight;
        int i10 = ((this.mVerticalOffset + i9) * i5) + usableHeight;
        rect2.left = i8;
        rect2.top = i10;
        rect2.right = i8 + i7;
        rect2.bottom = i10 + i9;
        this.mItemFrames.put(i, rect2);
        return rect2;
    }

    public int getOnePageSize() {
        return this.mOnePageSize;
    }

    public int getPageIndexByOffset() {
        if (canScrollVertically()) {
            int usableHeight = getUsableHeight();
            int i = this.mOffsetY;
            if (i <= 0 || usableHeight <= 0) {
                return 0;
            }
            int i2 = i / usableHeight;
            return i % usableHeight > usableHeight / 2 ? i2 + 1 : i2;
        }
        int usableWidth = getUsableWidth();
        int i3 = this.mOffsetX;
        if (i3 <= 0 || usableWidth <= 0) {
            return 0;
        }
        int i4 = i3 / usableWidth;
        return i3 % usableWidth > usableWidth / 2 ? i4 + 1 : i4;
    }

    public int getPreviousPageFirstPos() {
        return (getPageIndexByOffset() - 1) * this.mOnePageSize;
    }

    public int getRows() {
        return this.mRows;
    }

    public final int[] getSnapOffset(int i) {
        int[] iArr = new int[2];
        int i2 = this.mOnePageSize;
        int i3 = i2 == 0 ? 0 : i / i2;
        int pageIndexByOffset = getPageIndexByOffset();
        if (canScrollHorizontally()) {
            int usableWidth = getUsableWidth();
            int i4 = this.mOffsetX % usableWidth;
            if (i3 > pageIndexByOffset) {
                iArr[0] = usableWidth - i4;
            } else if (i3 < pageIndexByOffset) {
                if (i4 != 0) {
                    usableWidth = i4;
                }
                iArr[0] = -usableWidth;
            } else if (i4 > usableWidth / 2) {
                iArr[0] = usableWidth - i4;
            } else {
                iArr[0] = -i4;
            }
            if (this.mShouldReverseLayout) {
                iArr[0] = -iArr[0];
            }
            return iArr;
        }
        int usableHeight = getUsableHeight();
        int i5 = this.mOffsetY % usableHeight;
        if (i3 > pageIndexByOffset) {
            iArr[1] = usableHeight - i5;
            return iArr;
        }
        if (i3 < pageIndexByOffset) {
            if (i5 != 0) {
                usableHeight = i5;
            }
            iArr[1] = -usableHeight;
            return iArr;
        }
        if (i5 > usableHeight / 2) {
            iArr[1] = usableHeight - i5;
            return iArr;
        }
        iArr[1] = -i5;
        return iArr;
    }

    public int getTotalPageCount() {
        if (getItemCount() <= 0) {
            return 0;
        }
        int itemCount = getItemCount() / this.mOnePageSize;
        return getItemCount() % this.mOnePageSize != 0 ? itemCount + 1 : itemCount;
    }

    public final void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    public final void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i;
        boolean z = this.mReverseLayout;
        if (this.mOrientation == 0 || getLayoutDirection() != 1) {
            this.mShouldReverseLayout = z;
        } else {
            this.mShouldReverseLayout = !z;
        }
        if (state.mInPreLayout) {
            return;
        }
        IQSCustomizer$EditType iQSCustomizer$EditType = this.mEditType;
        int iMax = this.mRows;
        int i2 = this.mColumns;
        if (this.mLayoutModel == 0) {
            iMax = Math.max(1, (getUsableHeight() - ((Math.max(1, getUsableHeight() / this.mItemHeight) - 1) * this.mVerticalOffset)) / this.mItemHeight);
            if (iQSCustomizer$EditType == IQSCustomizer$EditType.HIGHLIGHT) {
                iMax = 1;
            } else if (iQSCustomizer$EditType == IQSCustomizer$EditType.BOTTOM) {
                iMax = Math.max(1, (getUsableHeight() - ((Math.max(1, getUsableHeight() / this.mItemHeight) - 1) * this.mVerticalOffset)) / this.mItemHeight);
                int i3 = this.mSmallTileRows;
                if (i3 != 0) {
                    iMax = i3;
                }
                FeatureOption.INSTANCE.getClass();
                if (FeatureOption.isTablet()) {
                    iMax = Math.min(Math.max(1, (getUsableHeight() - ((Math.max(1, getUsableHeight() / this.mItemHeight) - 1) * this.mVerticalOffset)) / this.mItemHeight), 4);
                }
            }
            if (iQSCustomizer$EditType == IQSCustomizer$EditType.TOP && (i = this.mSmallTileRows) != 0) {
                iMax = i;
            }
        } else {
            this.mItemHeight = getUsableHeight() / iMax;
        }
        this.mItemWidth = ((getUsableWidth() - ((i2 - 1) * this.mHorizontalOffset)) - (this.mHorizontalSidePadding * 2)) / i2;
        boolean z2 = (iMax == this.mRows && i2 == this.mColumns) ? false : true;
        if (LogUtil.getNormalDebug()) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OplusPagerGridLayoutManager", TextFieldBuffer$.ExternalSyntheticOutline0.m(iMax, i2, "updatePageSize: rows=", " columns="));
        }
        this.mRows = iMax;
        this.mColumns = i2;
        this.mOnePageSize = iMax * i2;
        if (z2) {
            this.mItemFrames.clear();
        }
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "OplusPagerGridLayoutManager", "mEditType=" + iQSCustomizer$EditType + " mItemWidth=" + this.mItemWidth + " mItemHeight=" + this.mItemHeight);
        if (getItemCount() == 0 || getUsableWidth() <= 0 || getUsableHeight() <= 0 || this.mOnePageSize <= 0) {
            removeAndRecycleAllViews(recycler);
            setPageCount(0);
            setPageIndex(0, false);
            return;
        }
        setPageCount(getTotalPageCount());
        setPageIndex(getPageIndexByOffset(), false);
        int itemCount = getItemCount() / this.mOnePageSize;
        if (getItemCount() % this.mOnePageSize != 0) {
            itemCount++;
        }
        if (canScrollHorizontally()) {
            int usableWidth = (itemCount - 1) * getUsableWidth();
            this.mMaxScrollX = usableWidth;
            this.mMaxScrollY = 0;
            if (this.mOffsetX > usableWidth) {
                this.mOffsetX = usableWidth;
            }
        } else {
            this.mMaxScrollX = 0;
            int usableHeight = (itemCount - 1) * getUsableHeight();
            this.mMaxScrollY = usableHeight;
            if (this.mOffsetY > usableHeight) {
                this.mOffsetY = usableHeight;
            }
        }
        this.mWidthUsed = getUsableWidth() - this.mItemWidth;
        this.mHeightUsed = getUsableHeight() - this.mItemHeight;
        for (int i4 = 0; i4 < getItemCount(); i4++) {
            getItemFrameByPosition(i4);
        }
        this.mLayoutItems.clear();
        detachAndScrapAttachedViews(recycler);
        recycleAndFillItems(recycler, true);
    }

    public final void onLayoutCompleted(RecyclerView.State state) {
        if (this.mOnePageSize <= 0 || state.mInPreLayout) {
            return;
        }
        setPageCount(getTotalPageCount());
        setPageIndex(getPageIndexByOffset(), false);
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onLayoutComplete(this.mRecyclerView, this.mRows, this.mItemHeight);
        }
        this.mOldWidth = getUsableWidth();
    }

    public final void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        super.onMeasure(recycler, state, i, i2);
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824 && size > 0) {
            mode = 1073741824;
        }
        if (mode2 != 1073741824 && size2 > 0) {
            mode2 = 1073741824;
        }
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(size, mode), View.MeasureSpec.makeMeasureSpec(size2, mode2));
    }

    public final void onScrollStateChanged(int i) {
        this.mScrollState = i;
        super.onScrollStateChanged(i);
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onPageScrollStateChanged();
        }
        if (i == 0) {
            setPageIndex(getPageIndexByOffset(), false);
        }
    }

    public final void recycleAndFillItems(RecyclerView.Recycler recycler, boolean z) {
        int itemCount = getItemCount() - 1;
        int i = this.mOffsetX;
        Rect rect = new Rect(i, this.mOffsetY, getUsableWidth() + i, this.mOffsetY + getUsableHeight());
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt != null) {
                int position = getPosition(childAt);
                if (!Rect.intersects(getItemFrameByPosition(position), rect)) {
                    int iIndexOf = this.mLayoutItems.indexOf(Integer.valueOf(position));
                    if (iIndexOf >= 0) {
                        this.mLayoutItems.remove(iIndexOf);
                    }
                    removeAndRecycleViewAt(childCount, recycler);
                }
            }
        }
        if (!z) {
            while (itemCount >= 0) {
                fillItem(recycler, rect, itemCount);
                itemCount--;
            }
        } else {
            for (int i2 = 0; i2 <= itemCount; i2++) {
                fillItem(recycler, rect, i2);
            }
        }
    }

    public final int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2 = this.mShouldReverseLayout ? -i : i;
        int i3 = this.mOffsetX;
        int i4 = i3 + i2;
        int i5 = this.mMaxScrollX;
        if (i4 > i5) {
            i2 = i5 - i3;
        } else if (i4 < 0) {
            i2 = 0 - i3;
        }
        this.mOffsetX = i3 + i2;
        int pageIndexByOffset = getPageIndexByOffset();
        if (this.mCallback != null) {
            float usableWidth = (this.mOffsetX % getUsableWidth()) / getUsableWidth();
            int i6 = usableWidth > 0.5f ? pageIndexByOffset - 1 : pageIndexByOffset;
            getUsableWidth();
            this.mCallback.onPageScrolled(usableWidth, i6);
        }
        if (this.mScrollState == 2) {
            setPageIndex(pageIndexByOffset, true);
        }
        if (i2 == 0) {
            return 0;
        }
        offsetChildrenHorizontal(this.mShouldReverseLayout ? i2 : -i2);
        if (i2 <= 0 || this.mShouldReverseLayout) {
            recycleAndFillItems(recycler, false);
            return i;
        }
        recycleAndFillItems(recycler, true);
        return i;
    }

    public final void scrollToPage(int i) {
        int usableWidth;
        int usableHeight;
        if (i < 0 || i >= this.mLastPageCount) {
            StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "pageIndex = ", " is out of bounds, mast in [0, ");
            sbM.append(this.mLastPageCount);
            sbM.append(")");
            LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusPagerGridLayoutManager", sbM.toString());
            return;
        }
        if (this.mRecyclerView == null) {
            LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusPagerGridLayoutManager", "RecyclerView Not Found!");
            return;
        }
        if (canScrollVertically()) {
            usableHeight = (getUsableHeight() * i) - this.mOffsetY;
            usableWidth = 0;
        } else {
            usableWidth = this.mShouldReverseLayout ? (getUsableWidth() * i) + this.mOffsetX : (getUsableWidth() * i) - this.mOffsetX;
            usableHeight = 0;
        }
        this.mRecyclerView.scrollBy(usableWidth, usableHeight);
        setPageIndex(i, false);
    }

    public final void scrollToPosition(int i) {
        int i2 = this.mOnePageSize;
        scrollToPage(i2 == 0 ? 0 : i / i2);
    }

    public final int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2 = this.mOffsetY;
        int i3 = i2 + i;
        int i4 = this.mMaxScrollY;
        if (i3 > i4) {
            i = i4 - i2;
        } else if (i3 < 0) {
            i = 0 - i2;
        }
        this.mOffsetY = i2 + i;
        int pageIndexByOffset = getPageIndexByOffset();
        if (this.mCallback != null) {
            float usableHeight = (this.mOffsetY % getUsableHeight()) / getUsableHeight();
            int i5 = usableHeight > 0.5f ? pageIndexByOffset - 1 : pageIndexByOffset;
            getUsableHeight();
            this.mCallback.onPageScrolled(usableHeight, i5);
        }
        if (this.mScrollState == 2) {
            setPageIndex(pageIndexByOffset, true);
        }
        if (i == 0) {
            return 0;
        }
        offsetChildrenHorizontal(this.mShouldReverseLayout ? i : -i);
        if (i > 0) {
            recycleAndFillItems(recycler, true);
            return i;
        }
        recycleAndFillItems(recycler, false);
        return i;
    }

    public void setAllowContinuousScroll(boolean z) {
        this.mAllowContinuousScroll = z;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void setChangeSelectInScrolling(boolean z) {
        this.mChangeSelectInScrolling = z;
    }

    public int setOrientationType(int i) {
        int i2 = this.mOrientation;
        if (i2 == i || this.mScrollState != 0) {
            return i2;
        }
        this.mOrientation = i;
        this.mItemFrames.clear();
        int i3 = this.mOffsetX;
        this.mOffsetX = (this.mOffsetY / getUsableHeight()) * getUsableWidth();
        this.mOffsetY = (i3 / getUsableWidth()) * getUsableHeight();
        int i4 = this.mMaxScrollX;
        this.mMaxScrollX = (this.mMaxScrollY / getUsableHeight()) * getUsableWidth();
        this.mMaxScrollY = (i4 / getUsableWidth()) * getUsableHeight();
        return this.mOrientation;
    }

    public final void setPageIndex(int i, boolean z) {
        Callback callback;
        LogUtil.d(LogUtil.TAG_QS, "OplusPagerGridLayoutManager", KeyguardLogger$.ExternalSyntheticOutline0.m("setPageIndex pageIndex= ", i, " isScrolling=", z));
        if (i != this.mLastPageIndex || this.mPageCountChanged) {
            this.mPageCountChanged = false;
            if (this.mAllowContinuousScroll || !z) {
                this.mLastPageIndex = i;
            }
            if ((!z || this.mChangeSelectInScrolling) && i >= 0 && (callback = this.mCallback) != null) {
                callback.onPageSelect(i, this.mScrollState);
            }
        }
    }

    public void setSmallTileRows(int i) {
        this.mSmallTileRows = i;
    }

    public final void smoothScrollToPage(int i) {
        int i2;
        if (i < 0 || i >= this.mLastPageCount) {
            LogUtil.d(LogUtil.TAG_STATUSBAR, SeparateQSStatisicUtil.VALUE_PANEL_QS, "pageIndex is outOfIndex, must in [0, " + this.mLastPageCount + ").");
            return;
        }
        if (this.mRecyclerView == null) {
            LogUtil.d(LogUtil.TAG_STATUSBAR, SeparateQSStatisicUtil.VALUE_PANEL_QS, "RecyclerView Not Found!");
            return;
        }
        int pageIndexByOffset = getPageIndexByOffset();
        boolean z = Math.abs(i - pageIndexByOffset) > 3;
        if (i < pageIndexByOffset) {
            if (z) {
                scrollToPage(i + 1);
            }
            i2 = ((i + 1) * this.mOnePageSize) - 1;
        } else {
            if (z) {
                scrollToPage(i - 1);
            }
            i2 = i * this.mOnePageSize;
        }
        OplusPagerGridSmoothScroller oplusPagerGridSmoothScroller = new OplusPagerGridSmoothScroller(this.mRecyclerView);
        oplusPagerGridSmoothScroller.setTargetPosition(i2);
        startSmoothScroll(oplusPagerGridSmoothScroller);
    }

    public final void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        int i2 = this.mOnePageSize;
        smoothScrollToPage(i2 == 0 ? 0 : i / i2);
    }

    public final void updateItemHeightAndColumn(int i, int i2) {
        this.mLayoutModel = 0;
        this.mRows = 0;
        this.mColumns = i2;
        this.mItemHeight = i;
        this.mItemFrames.clear();
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mScrollState = 0;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.requestLayout();
        }
    }
}
