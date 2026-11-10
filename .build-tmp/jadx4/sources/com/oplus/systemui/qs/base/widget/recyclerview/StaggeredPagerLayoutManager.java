package com.oplus.systemui.qs.base.widget.recyclerview;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.FileProvider$;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.car.app.hardware.common.CarZone$;
import androidx.car.app.model.TimerSpan$;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.core.view.ViewGroupKt;
import androidx.exifinterface.media.ExifInterface$;
import androidx.fragment.app.Fragment$5$;
import androidx.recyclerview.widget.RecyclerView;
import com.android.keyguard.OplusLockIconView$;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.aod.controller.BaseAodClockLayoutController$bindSceneViewHolder$2$;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.qs.base.widget.recyclerview.callback.LayoutCallbackWrapper;
import com.oplus.systemui.qs.base.widget.recyclerview.callback.PagerCallbackWrapper;
import com.oplusos.systemui.common.adapter.BaseRecyclerAdapter;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.LocCache;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import com.oplusos.systemui.common.viewholder.GridData;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.Regex$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StaggeredPagerLayoutManager extends RecyclerView.LayoutManager implements IPagerSnapCallback {
    public static final Companion Companion = new Companion();
    private static final String _TAG = "StaggeredPagerLayoutManager";
    private final String TAG;
    private final Rect cacheRect;
    private final ArrayList<View> curPageViewList;
    private final ArrayList<View> currentPageChildren;
    public int currentPageIndex;
    private final Lazy dragXSpringAnimation$delegate;
    private final Lazy dragYSpringAnimation$delegate;
    private final Lazy emptyPlaceHolderHandler$delegate;
    private final ArrayList<View> firstColViewList;
    private final ArrayList<View> lastColViewList;
    private final Lazy layoutCallbackWrapper$delegate;
    private final LayoutConfig layoutConfig;
    public int pageCount;
    private final Lazy pagerCallbackWrapper$delegate;
    public RecyclerView.Recycler recycler;
    public RecyclerView recyclerView;
    private final ArrayList<View> visibleChildrenCache;
    private final LayoutState layoutState = new LayoutState(new SparseArray(), new ArraySet(), new Rect(), 0, 0, 0);
    private Point finalValueOfAnimation = new Point();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LayoutConfig {
        private final List<Loc> _specifyLoc;
        public final boolean autoLayout;
        public int cellHeight;
        public int cellWidth;
        public int colCount;
        private Function1 createEmptyViewHolder;
        private StaggeredPagerLayoutManager hostLayoutManager;
        public boolean notSupportDragXAnima;
        public int rowCount;
        private final List<Loc> specifyLoc;

        public LayoutConfig(int i, int i2, int i3, int i4, boolean z) {
            this.autoLayout = z;
            ArrayList arrayList = new ArrayList();
            this._specifyLoc = arrayList;
            this.specifyLoc = arrayList;
            this.cellWidth = i3;
            this.cellHeight = i4;
            this.rowCount = i;
            this.colCount = i2;
            assertLayoutInfoValid$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib("<init>");
        }

        public static void setCellInfo$default(LayoutConfig layoutConfig, int i, int i2) {
            LayoutCallbackWrapper layoutCallbackWrapper;
            if (layoutConfig.cellWidth == i && layoutConfig.cellHeight == i2) {
                return;
            }
            layoutConfig.cellWidth = i;
            layoutConfig.cellHeight = i2;
            StaggeredPagerLayoutManager staggeredPagerLayoutManager = layoutConfig.hostLayoutManager;
            if (staggeredPagerLayoutManager != null) {
                staggeredPagerLayoutManager.requestLayout();
            }
            StaggeredPagerLayoutManager staggeredPagerLayoutManager2 = layoutConfig.hostLayoutManager;
            if (staggeredPagerLayoutManager2 == null || (layoutCallbackWrapper = staggeredPagerLayoutManager2.getLayoutCallbackWrapper()) == null) {
                return;
            }
            layoutCallbackWrapper.dispatchCellInfoChanged$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(i, i2);
        }

        public final boolean assertLayoutInfoValid$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(String str) {
            String str2;
            boolean z = this.colCount > 0 && this.rowCount > 0 && this.cellWidth > 0 && this.cellHeight > 0;
            if (!z) {
                StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.hostLayoutManager;
                if (staggeredPagerLayoutManager == null || (str2 = staggeredPagerLayoutManager.TAG) == null) {
                    str2 = StaggeredPagerLayoutManager._TAG;
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR);
                    int i = this.colCount;
                    int i2 = this.rowCount;
                    int i3 = this.cellWidth;
                    int i4 = this.cellHeight;
                    StringBuilder sbM2 = ConstraintSet$.ExternalSyntheticOutline0.m(i, "[assertLayoutInfoValid] from ", str, ", colCount=", ",rowCount=");
                    RectList$.ExternalSyntheticOutline0.m(i2, i3, ",cellWidth=", ",cellHeight=", sbM2);
                    sbM2.append(i4);
                    sbM.append(sbM2.toString());
                    Log.d("SystemUi--QuickSettings", sbM.toString());
                }
            }
            return z;
        }

        public final StaggeredPagerLayoutManager build(String str) {
            StaggeredPagerLayoutManager staggeredPagerLayoutManager = new StaggeredPagerLayoutManager(this, FileProvider$.ExternalSyntheticOutline0.m("_", str));
            this.hostLayoutManager = staggeredPagerLayoutManager;
            staggeredPagerLayoutManager.getLayoutCallbackWrapper().dispatchCellNumChanged$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(this.colCount, this.rowCount);
            staggeredPagerLayoutManager.getLayoutCallbackWrapper().dispatchCellInfoChanged$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(this.cellWidth, this.cellHeight);
            return staggeredPagerLayoutManager;
        }

        public final int getCellHeight() {
            return this.cellHeight;
        }

        public final int getCellWidth() {
            return this.cellWidth;
        }

        public final int getColCount() {
            return this.colCount;
        }

        public final Function1 getCreateEmptyViewHolder() {
            return this.createEmptyViewHolder;
        }

        public final int getHorizontalSpace() {
            return 0;
        }

        public final boolean getNotSupportDragXAnima() {
            return this.notSupportDragXAnima;
        }

        public final int getOrientation() {
            return 0;
        }

        public final int getRowCount() {
            return this.rowCount;
        }

        public final List<Loc> getSpecifyLoc() {
            return this.specifyLoc;
        }

        public final int getVerticalSpace() {
            return 0;
        }

        public final void setChildrenLoc$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(List<Loc> list) {
            this._specifyLoc.clear();
            this._specifyLoc.addAll(list);
        }

        public final void setCreateEmptyViewHolder(Function1 function1) {
            if (Intrinsics.areEqual(this.createEmptyViewHolder, function1)) {
                return;
            }
            this.createEmptyViewHolder = function1;
            StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.hostLayoutManager;
            if (staggeredPagerLayoutManager != null) {
                staggeredPagerLayoutManager.requestLayout();
            }
        }

        public final void setGridNum(int i, int i2) {
            LayoutCallbackWrapper layoutCallbackWrapper;
            if (this.rowCount == i && this.colCount == i2) {
                return;
            }
            this.rowCount = i;
            this.colCount = i2;
            if (this.autoLayout) {
                tryAutoGenLoc$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(this._specifyLoc.size(), true);
                StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.hostLayoutManager;
                if (staggeredPagerLayoutManager != null) {
                    StaggeredPagerLayoutManager.refreshPageCount$default(staggeredPagerLayoutManager, false, 3);
                }
            }
            StaggeredPagerLayoutManager staggeredPagerLayoutManager2 = this.hostLayoutManager;
            if (staggeredPagerLayoutManager2 != null) {
                staggeredPagerLayoutManager2.refreshAllEmptyCell();
            }
            StaggeredPagerLayoutManager staggeredPagerLayoutManager3 = this.hostLayoutManager;
            if (staggeredPagerLayoutManager3 != null) {
                staggeredPagerLayoutManager3.requestLayout();
            }
            StaggeredPagerLayoutManager staggeredPagerLayoutManager4 = this.hostLayoutManager;
            if (staggeredPagerLayoutManager4 == null || (layoutCallbackWrapper = staggeredPagerLayoutManager4.getLayoutCallbackWrapper()) == null) {
                return;
            }
            layoutCallbackWrapper.dispatchCellNumChanged$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(i2, i);
        }

        public final void setNotSupportDragXAnima(boolean z) {
            this.notSupportDragXAnima = z;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("LayoutConfig(orientation=0, rowCount=");
            sb.append(this.rowCount);
            sb.append(", colCount=");
            sb.append(this.colCount);
            sb.append(", cellWidth=");
            sb.append(this.cellWidth);
            sb.append(", cellHeight=");
            sb.append(this.cellHeight);
            sb.append(", horizontalSpace=0, verticalSpace=0, specifyLoc=");
            return CarZone$.ExternalSyntheticOutline0.m(sb, this.specifyLoc, ")");
        }

        public final void tryAutoGenLoc$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(int i, boolean z) {
            if (this.autoLayout) {
                if (this.colCount <= 0 || this.rowCount <= 0) {
                    this._specifyLoc.clear();
                    return;
                }
                if (i != this._specifyLoc.size() || z) {
                    this._specifyLoc.clear();
                    int i2 = this.rowCount * this.colCount;
                    for (int i3 = 0; i3 < i; i3++) {
                        List<Loc> list = this._specifyLoc;
                        int i4 = i3 % i2;
                        int i5 = this.colCount;
                        list.add(i3, LocCache.INSTANCE.loc(i3 / i2, i4 % i5, i4 / i5));
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LayoutState {
        private final Rect displayRect;
        private final SparseArray<Rect> itemLayoutRects;
        private final ArraySet<Integer> layoutItems;
        public int scrollState;
        public int scrollX;
        public int scrollY;

        public LayoutState(SparseArray<Rect> sparseArray, ArraySet<Integer> arraySet, Rect rect, int i, int i2, int i3) {
            this.itemLayoutRects = sparseArray;
            this.layoutItems = arraySet;
            this.displayRect = rect;
            this.scrollState = i;
            this.scrollX = i2;
            this.scrollY = i3;
        }

        public final Rect getDisplayRect() {
            return this.displayRect;
        }

        public final SparseArray<Rect> getItemLayoutRects() {
            return this.itemLayoutRects;
        }

        public final ArraySet<Integer> getLayoutItems() {
            return this.layoutItems;
        }

        public final int getMaxScrollX() {
            StaggeredPagerLayoutManager staggeredPagerLayoutManager = StaggeredPagerLayoutManager.this;
            if (staggeredPagerLayoutManager.getPageCount() == 0) {
                return 0;
            }
            if (this.scrollState == 1) {
                int measuredWidth = staggeredPagerLayoutManager.getRecyclerView().getMeasuredWidth() * (staggeredPagerLayoutManager.getPageCount() - 1);
                int iAbs = Math.abs(this.scrollX);
                return measuredWidth < iAbs ? iAbs : measuredWidth;
            }
            int pageCount = (int) (((staggeredPagerLayoutManager.getPageCount() - 1) + 0.3f) * staggeredPagerLayoutManager.getRecyclerView().getMeasuredWidth());
            int iAbs2 = Math.abs(this.scrollX);
            return pageCount < iAbs2 ? iAbs2 : pageCount;
        }

        public final int getMaxScrollY() {
            StaggeredPagerLayoutManager staggeredPagerLayoutManager = StaggeredPagerLayoutManager.this;
            if (staggeredPagerLayoutManager.getPageCount() == 0) {
                return 0;
            }
            if (this.scrollState != 1) {
                return (int) (((staggeredPagerLayoutManager.getPageCount() - 1) + 0.3f) * staggeredPagerLayoutManager.getRecyclerView().getMeasuredHeight());
            }
            return staggeredPagerLayoutManager.getRecyclerView().getMeasuredHeight() * (staggeredPagerLayoutManager.getPageCount() - 1);
        }

        public final int getMinScrollX() {
            if (this.scrollState == 1) {
                return 0;
            }
            return (int) (StaggeredPagerLayoutManager.this.getRecyclerView().getMeasuredWidth() * 0.3f);
        }

        public final int getMinScrollY() {
            if (this.scrollState == 1) {
                return 0;
            }
            return (int) (StaggeredPagerLayoutManager.this.getRecyclerView().getMeasuredHeight() * 0.3f);
        }

        public final int getScrollState() {
            return this.scrollState;
        }

        public final int getScrollX() {
            return this.scrollX;
        }

        public final int getScrollY() {
            return this.scrollY;
        }

        public final void setScrollState(int i) {
            this.scrollState = i;
        }

        public final void setScrollX(int i) {
            this.scrollX = i;
        }

        public final void setScrollY(int i) {
            this.scrollY = i;
        }

        public String toString() {
            return "LayoutState(itemLayoutRects=" + this.itemLayoutRects + ", layoutItems=" + this.layoutItems + ", displayRect=" + this.displayRect + ", scrollState=" + this.scrollState + ", scrollX=" + this.scrollX + ", scrollY=" + this.scrollY + ", maxScrollX=" + getMaxScrollX() + ", minScrollX=" + getMinScrollX() + ", maxScrollY=" + getMaxScrollY() + ", minScrollY=" + getMinScrollY() + ")";
        }
    }

    public static EmptyPlaceHolderHandler $r8$lambda$lg6CJsN1bnMnz0UXV67Wh1CaoTw(StaggeredPagerLayoutManager staggeredPagerLayoutManager) {
        return new EmptyPlaceHolderHandler(staggeredPagerLayoutManager.getRecyclerView(), staggeredPagerLayoutManager.layoutState, staggeredPagerLayoutManager.layoutConfig, new StaggeredPagerLayoutManager$emptyPlaceHolderHandler$2$1(2, staggeredPagerLayoutManager, StaggeredPagerLayoutManager.class, "getItemRect", "getItemRect(Lcom/oplusos/systemui/common/model/Loc;Lcom/oplusos/systemui/common/model/SpanSize;)Landroid/graphics/Rect;", 0), new PlaceHolder(null, null, null));
    }

    public StaggeredPagerLayoutManager(LayoutConfig layoutConfig, String str) {
        this.layoutConfig = layoutConfig;
        this.TAG = FileProvider$.ExternalSyntheticOutline0.m(_TAG, str);
        final int i = 0;
        this.pagerCallbackWrapper$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return new PagerCallbackWrapper();
                    default:
                        return new LayoutCallbackWrapper();
                }
            }
        });
        final int i2 = 1;
        this.layoutCallbackWrapper$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return new PagerCallbackWrapper();
                    default:
                        return new LayoutCallbackWrapper();
                }
            }
        });
        StaggeredPagerLayoutManager$$ExternalSyntheticLambda2 staggeredPagerLayoutManager$$ExternalSyntheticLambda2 = new StaggeredPagerLayoutManager$$ExternalSyntheticLambda2(i);
        staggeredPagerLayoutManager$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.dragXSpringAnimation$delegate = LazyKt__LazyJVMKt.lazy(staggeredPagerLayoutManager$$ExternalSyntheticLambda2);
        StaggeredPagerLayoutManager$$ExternalSyntheticLambda2 staggeredPagerLayoutManager$$ExternalSyntheticLambda3 = new StaggeredPagerLayoutManager$$ExternalSyntheticLambda2(i2);
        staggeredPagerLayoutManager$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.dragYSpringAnimation$delegate = LazyKt__LazyJVMKt.lazy(staggeredPagerLayoutManager$$ExternalSyntheticLambda3);
        StaggeredPagerLayoutManager$$ExternalSyntheticLambda2 staggeredPagerLayoutManager$$ExternalSyntheticLambda4 = new StaggeredPagerLayoutManager$$ExternalSyntheticLambda2(2);
        staggeredPagerLayoutManager$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.emptyPlaceHolderHandler$delegate = LazyKt__LazyJVMKt.lazy(staggeredPagerLayoutManager$$ExternalSyntheticLambda4);
        this.cacheRect = new Rect();
        this.visibleChildrenCache = new ArrayList<>(16);
        this.firstColViewList = new ArrayList<>(8);
        this.lastColViewList = new ArrayList<>(8);
        this.curPageViewList = new ArrayList<>(16);
        this.currentPageChildren = new ArrayList<>(16);
    }

    private final int getDefaultPageCount() {
        if (this.layoutConfig.getSpecifyLoc().isEmpty()) {
            return 0;
        }
        Iterator<T> it = this.layoutConfig.getSpecifyLoc().iterator();
        if (!it.hasNext()) {
            Regex$.ExternalSyntheticBUOutline0.m();
            return 0;
        }
        int pageIndex = ((Loc) it.next()).getPageIndex();
        while (it.hasNext()) {
            int pageIndex2 = ((Loc) it.next()).getPageIndex();
            if (pageIndex < pageIndex2) {
                pageIndex = pageIndex2;
            }
        }
        if (pageIndex >= 0) {
            return pageIndex + 1;
        }
        return 0;
    }

    private final COUISpringAnimation getDragXSpringAnimation() {
        return (COUISpringAnimation) this.dragXSpringAnimation$delegate.getValue();
    }

    private final COUISpringAnimation getDragYSpringAnimation() {
        return (COUISpringAnimation) this.dragYSpringAnimation$delegate.getValue();
    }

    private final EmptyPlaceHolderHandler getEmptyPlaceHolderHandler() {
        return (EmptyPlaceHolderHandler) this.emptyPlaceHolderHandler$delegate.getValue();
    }

    public static /* synthetic */ void refreshPageCount$default(StaggeredPagerLayoutManager staggeredPagerLayoutManager, boolean z, int i) {
        if ((i & 1) != 0) {
            z = false;
        }
        staggeredPagerLayoutManager.refreshPageCount(-1, z);
    }

    private final void setCurrentPageIndex(int i) {
        if (this.currentPageIndex == i || i < 0 || i >= this.pageCount) {
            String str = this.TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                sbM.append("[setCurrentPageIndex] " + i + " repeat, pageCount=" + this.pageCount);
                Log.d("SystemUi--QuickSettings", sbM.toString());
                return;
            }
            return;
        }
        String str2 = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM2 = Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR);
            int i2 = this.currentPageIndex;
            int i3 = this.pageCount;
            StringBuilder sbM3 = FloatList$.ExternalSyntheticOutline0.m("[setCurrentPageIndex] ", i2, i, " -> ", ", pageCount=");
            sbM3.append(i3);
            sbM2.append(sbM3.toString());
            Log.d("SystemUi--QuickSettings", sbM2.toString());
        }
        this.currentPageIndex = i;
        getPagerCallbackWrapper().onPageSelected(this.currentPageIndex);
    }

    private final void setPageCount(int i) {
        if (this.pageCount == i || i < 0) {
            String str = this.TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                sbM.append("[setPageCount] " + i + " repeat,currentPageIndex=" + this.currentPageIndex);
                Log.d("SystemUi--QuickSettings", sbM.toString());
                return;
            }
            return;
        }
        String str2 = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM2 = Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR);
            int i2 = this.pageCount;
            int i3 = this.currentPageIndex;
            StringBuilder sbM3 = FloatList$.ExternalSyntheticOutline0.m("[setPageCount]", i2, i, " ->  ", " , currentPageIndex=");
            sbM3.append(i3);
            sbM2.append(sbM3.toString());
            Log.d("SystemUi--QuickSettings", sbM2.toString());
        }
        this.pageCount = i;
        getPagerCallbackWrapper().onPageSizeChanged(this.pageCount);
    }

    public final void addChildToColumnLists(View view, int i, Integer num, Integer num2) {
        if (num != null && i == num.intValue()) {
            this.firstColViewList.add(view);
        }
        if (num2 != null && i == num2.intValue()) {
            this.lastColViewList.add(view);
        }
        this.curPageViewList.add(view);
    }

    public final void autoGenLoc(int i) {
        this.layoutConfig.tryAutoGenLoc$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(i, false);
    }

    public final Rect calculateTargetRectWhenHorizontally(int i, int i2, int i3, int i4, Rect rect) {
        boolean z = getLayoutDirection() == 1;
        LayoutConfig layoutConfig = this.layoutConfig;
        int verticalSpace = ((layoutConfig.getVerticalSpace() + layoutConfig.getCellHeight()) * i2) + getPaddingTop();
        if (z) {
            int measuredWidth = (getRecyclerView().getMeasuredWidth() - getPaddingRight()) - ((layoutConfig.getHorizontalSpace() + layoutConfig.getCellWidth()) * i);
            rect.set(measuredWidth - i3, verticalSpace, measuredWidth, i4 + verticalSpace);
            return rect;
        }
        int horizontalSpace = ((layoutConfig.getHorizontalSpace() + layoutConfig.getCellWidth()) * i) + getPaddingLeft();
        rect.set(horizontalSpace, verticalSpace, i3 + horizontalSpace, i4 + verticalSpace);
        return rect;
    }

    public final boolean canScrollHorizontally() {
        return this.layoutConfig.getOrientation() == 0;
    }

    public final boolean canScrollVertically() {
        return this.layoutConfig.getOrientation() == 1;
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.IPagerSnapCallback
    public int[] currPageOffset() {
        if (canScrollHorizontally()) {
            int layoutDirection = getLayoutDirection();
            LayoutState layoutState = this.layoutState;
            float scrollX = layoutDirection == 1 ? -layoutState.getScrollX() : layoutState.getScrollX();
            if (scrollX > (this.currentPageIndex + 0.3f) * getRecyclerView().getMeasuredWidth()) {
                return findNextPageOffset();
            }
            if ((getRecyclerView().getMeasuredWidth() * 0.3f) + scrollX < getRecyclerView().getMeasuredWidth() * this.currentPageIndex) {
                return findPrePageOffset();
            }
        } else if (canScrollVertically()) {
            if ((getRecyclerView().getMeasuredHeight() * 0.3f) + this.layoutState.getScrollY() < getRecyclerView().getMeasuredHeight() * this.currentPageIndex) {
                return findPrePageOffset();
            }
            if (this.layoutState.getScrollY() > (this.currentPageIndex + 0.3f) * getRecyclerView().getMeasuredHeight()) {
                return findNextPageOffset();
            }
        }
        return getScrollDelta(this.currentPageIndex);
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.IPagerSnapCallback
    public int[] currScrollOffset() {
        return new int[]{this.layoutState.getScrollX(), this.layoutState.getScrollY()};
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("pageCount:", this.pageCount, printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("currentPageIndex:", this.currentPageIndex, printWriter);
        printWriter.println("LayoutState:" + this.layoutState);
        printWriter.println("layoutConfig:" + this.layoutConfig);
    }

    public final void enablePlaceHolder(boolean z, boolean z2) {
        getEmptyPlaceHolderHandler().enable(z, z2);
    }

    public final void fillItem(RecyclerView.Recycler recycler, int i, Set set) {
        Rect rect;
        SpanSize size;
        List<Object> dataList;
        if (this.layoutState.getItemLayoutRects().get(i) != null) {
            rect = this.layoutState.getItemLayoutRects().get(i);
        } else {
            if (i < 0 || i >= this.layoutConfig.getSpecifyLoc().size()) {
                rect = new Rect();
            } else {
                RecyclerView.Adapter adapter = getRecyclerView().getAdapter();
                BaseRecyclerAdapter baseRecyclerAdapter = adapter instanceof BaseRecyclerAdapter ? (BaseRecyclerAdapter) adapter : null;
                Object orNull = (baseRecyclerAdapter == null || (dataList = baseRecyclerAdapter.getDataList()) == null) ? null : CollectionsKt___CollectionsKt.getOrNull(dataList, i);
                GridData gridData = orNull instanceof GridData ? (GridData) orNull : null;
                if (gridData == null || (size = gridData.getSize()) == null) {
                    rect = new Rect();
                } else {
                    rect = getItemRect(this.layoutConfig.getSpecifyLoc().get(i), size);
                }
            }
            this.layoutState.getItemLayoutRects().put(i, rect);
        }
        if (this.layoutState.getLayoutItems().contains(Integer.valueOf(i))) {
            return;
        }
        View viewForPosition = recycler.getViewForPosition(i);
        if (viewForPosition.getParent() != null) {
            return;
        }
        if (viewForPosition.getVisibility() == 0) {
            viewForPosition.setTransitionVisibility(4);
            set.add(viewForPosition);
        }
        addView(viewForPosition);
        if (viewForPosition.isLayoutRequested() || viewForPosition.getMeasuredWidth() != rect.width() || viewForPosition.getMeasuredHeight() != rect.height()) {
            viewForPosition.measure(View.MeasureSpec.makeMeasureSpec(rect.width(), 1073741824), View.MeasureSpec.makeMeasureSpec(rect.height(), 1073741824));
        }
        LayoutState layoutState = this.layoutState;
        layoutDecoratedWithMargins(viewForPosition, rect.left - layoutState.getScrollX(), rect.top - layoutState.getScrollY(), rect.right - layoutState.getScrollX(), rect.bottom - layoutState.getScrollY());
        layoutState.getLayoutItems().add(Integer.valueOf(i));
    }

    public final void fillItems(RecyclerView.Recycler recycler, boolean z) {
        LayoutState layoutState = this.layoutState;
        layoutState.getDisplayRect().set(getRecyclerView().getPaddingLeft() + layoutState.getScrollX(), getRecyclerView().getPaddingTop() + layoutState.getScrollY(), (getRecyclerView().getMeasuredWidth() + layoutState.getScrollX()) - getRecyclerView().getPaddingRight(), (getRecyclerView().getMeasuredHeight() + layoutState.getScrollY()) - getRecyclerView().getPaddingBottom());
        if (getItemCount() == 0 || !this.layoutConfig.assertLayoutInfoValid$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib("onLayoutChildren")) {
            return;
        }
        LinkedHashSet<View> linkedHashSet = new LinkedHashSet();
        int itemCount = getItemCount() - 1;
        if (!z) {
            while (-1 < itemCount) {
                this.layoutState.getDisplayRect();
                fillItem(recycler, itemCount, linkedHashSet);
                itemCount--;
            }
        } else if (itemCount >= 0) {
            int i = 0;
            while (true) {
                this.layoutState.getDisplayRect();
                fillItem(recycler, i, linkedHashSet);
                if (i == itemCount) {
                    break;
                } else {
                    i++;
                }
            }
        }
        for (View view : linkedHashSet) {
            if (view.getVisibility() == 4) {
                view.setVisibility(0);
            } else {
                String str = this.TAG;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                    sbM.append("[fillItems] " + view + " visibility=" + view.getVisibility());
                    Log.d("SystemUi--QuickSettings", sbM.toString());
                }
            }
        }
    }

    public final List<View> findCurrentPageChildren() {
        this.currentPageChildren.clear();
        int i = 0;
        for (Object obj : this.layoutConfig.getSpecifyLoc()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (((Loc) obj).getPageIndex() == this.currentPageIndex) {
                RecyclerView.LayoutManager layoutManager = getRecyclerView().getLayoutManager();
                View childAt = layoutManager != null ? layoutManager.getChildAt(i) : null;
                if (childAt != null) {
                    this.currentPageChildren.add(childAt);
                }
            }
            i = i2;
        }
        return this.currentPageChildren;
    }

    public final Triple<List<View>, List<View>, List<View>> findFirstAndLastVisibleChildren() {
        this.firstColViewList.clear();
        this.lastColViewList.clear();
        this.curPageViewList.clear();
        Pair pairResolveCurrentPageColumnRows = resolveCurrentPageColumnRows();
        Integer num = (Integer) pairResolveCurrentPageColumnRows.component1();
        Integer num2 = (Integer) pairResolveCurrentPageColumnRows.component2();
        int i = 0;
        for (Object obj : this.layoutConfig.getSpecifyLoc()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            Loc loc = (Loc) obj;
            if (loc.getPageIndex() == this.currentPageIndex) {
                RecyclerView.LayoutManager layoutManager = getRecyclerView().getLayoutManager();
                View childAt = layoutManager != null ? layoutManager.getChildAt(i) : null;
                if (childAt != null) {
                    addChildToColumnLists(childAt, loc.getStartRow(), num, num2);
                }
            }
            i = i2;
        }
        return new Triple<>(this.firstColViewList, this.lastColViewList, this.curPageViewList);
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.IPagerSnapCallback
    public int[] findNextPageOffset() {
        int i = this.currentPageIndex + 1;
        int i2 = this.pageCount;
        if (i >= i2) {
            i = i2 - 1;
        }
        return getScrollDelta(i);
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.IPagerSnapCallback
    public int[] findPrePageOffset() {
        int i = this.currentPageIndex - 1;
        if (i < 0) {
            i = 0;
        }
        return getScrollDelta(i);
    }

    public final Loc findUnderCapsule(int i, int i2) {
        int verticalSpace;
        if (this.layoutConfig.getRowCount() > 1) {
            verticalSpace = this.layoutConfig.getVerticalSpace() * (this.layoutConfig.getRowCount() - 1);
        } else {
            verticalSpace = 0;
        }
        int cellHeight = (this.layoutConfig.getCellHeight() * this.layoutConfig.getRowCount()) + verticalSpace;
        int paddingStart = getRecyclerView().getPaddingStart();
        if (i > getRecyclerView().getMeasuredWidth() - getRecyclerView().getPaddingEnd() || paddingStart > i) {
            return null;
        }
        int paddingTop = getRecyclerView().getPaddingTop();
        if (i2 > getRecyclerView().getPaddingTop() + cellHeight || paddingTop > i2) {
            return null;
        }
        boolean z = getLayoutDirection() == 1;
        int paddingStart2 = (i - getRecyclerView().getPaddingStart()) / this.layoutConfig.getCellWidth();
        int paddingTop2 = (i2 - getRecyclerView().getPaddingTop()) / this.layoutConfig.getCellHeight();
        Loc locLoc$default = !z ? LocCache.loc$default(LocCache.INSTANCE, paddingStart2, paddingTop2, 1) : LocCache.loc$default(LocCache.INSTANCE, (this.layoutConfig.getColCount() - 1) - paddingStart2, paddingTop2, 1);
        Rect placeHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib = getEmptyPlaceHolderHandler().getPlaceHolder().getPlaceHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib();
        if (placeHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib == null) {
            return null;
        }
        Rect itemRect = getItemRect(locLoc$default, SpanCache.INSTANCE.spanSize(1, 1));
        int i3 = itemRect.left;
        int i4 = placeHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib.left + i3;
        int i5 = itemRect.top;
        itemRect.set(i4, placeHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib.top + i5, i3 + placeHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib.right, i5 + placeHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib.bottom);
        if (itemRect.contains(i, i2)) {
            return locLoc$default;
        }
        return null;
    }

    public final Triple<List<View>, List<View>, List<View>> findVisibleAndCurrentPageChildren() {
        IPagerSnap onFlingListener = getRecyclerView().getOnFlingListener();
        IPagerSnap iPagerSnap = onFlingListener instanceof IPagerSnap ? onFlingListener : null;
        if (iPagerSnap == null || !((OplusPagerStaggerSnapHelper) iPagerSnap).isFlingAnimationRunning()) {
            return findFirstAndLastVisibleChildren();
        }
        this.firstColViewList.clear();
        this.lastColViewList.clear();
        this.curPageViewList.clear();
        List<View> listFindVisibleChildren = findVisibleChildren();
        Pair pairResolveCurrentPageColumnRows = resolveCurrentPageColumnRows();
        Integer num = (Integer) pairResolveCurrentPageColumnRows.component1();
        Integer num2 = (Integer) pairResolveCurrentPageColumnRows.component2();
        int i = 0;
        for (Object obj : this.layoutConfig.getSpecifyLoc()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            Loc loc = (Loc) obj;
            if (loc.getPageIndex() == this.currentPageIndex) {
                RecyclerView.LayoutManager layoutManager = getRecyclerView().getLayoutManager();
                View childAt = layoutManager != null ? layoutManager.getChildAt(i) : null;
                if (childAt != null) {
                    addChildToColumnLists(childAt, loc.getStartRow(), num, num2);
                }
            } else {
                RecyclerView.LayoutManager layoutManager2 = getRecyclerView().getLayoutManager();
                View childAt2 = layoutManager2 != null ? layoutManager2.getChildAt(i) : null;
                if (childAt2 != null && listFindVisibleChildren.contains(childAt2)) {
                    addChildToColumnLists(childAt2, loc.getStartRow(), num, num2);
                }
            }
            i = i2;
        }
        return new Triple<>(this.firstColViewList, this.lastColViewList, this.curPageViewList);
    }

    public final List<View> findVisibleChildren() {
        this.visibleChildrenCache.clear();
        for (View view : ViewGroupKt.getChildren(getRecyclerView())) {
            this.cacheRect.set(this.layoutState.getScrollX() + view.getLeft(), this.layoutState.getScrollY() + view.getTop(), this.layoutState.getScrollX() + view.getRight(), this.layoutState.getScrollY() + view.getBottom());
            if (Rect.intersects(this.cacheRect, this.layoutState.getDisplayRect()) && view.getVisibility() == 0) {
                this.visibleChildrenCache.add(view);
            }
        }
        String str = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(this.visibleChildrenCache.size(), "[findVisibleChildren] size=", "SystemUi--Common", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR));
        }
        return this.visibleChildrenCache;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public final List<Drawable> getAllEmptyDrawables() {
        return getEmptyPlaceHolderHandler().getAllDrawable();
    }

    public final List<Drawable> getAllEmptyDrawablesByLoc() {
        return getEmptyPlaceHolderHandler().getAllDrawableByLoc();
    }

    public final int getColumnsByX(float f) {
        int rawColumnByX = getRawColumnByX(f);
        int colCount = this.layoutConfig.getColCount() - 1;
        if (colCount < 0) {
            colCount = 0;
        }
        return RangesKt___RangesKt.coerceIn(rawColumnByX, 0, colCount);
    }

    public final int getCurrentPageIndex() {
        return this.currentPageIndex;
    }

    public final Loc getItemLoc(int i) {
        return (i < 0 || i >= this.layoutConfig.getSpecifyLoc().size()) ? LocCache.loc$default(LocCache.INSTANCE, 0, 0, 7) : this.layoutConfig.getSpecifyLoc().get(i);
    }

    public final Integer getItemPageIndex(int i) {
        if (i < 0 || i >= this.layoutConfig.getSpecifyLoc().size()) {
            return null;
        }
        return Integer.valueOf(this.layoutConfig.getSpecifyLoc().get(i).getPageIndex());
    }

    public final Rect getItemRect(Loc loc, SpanSize spanSize) {
        boolean z = getLayoutDirection() == 1;
        if (canScrollHorizontally()) {
            LayoutConfig layoutConfig = this.layoutConfig;
            int verticalSpace = ((layoutConfig.getVerticalSpace() + layoutConfig.getCellHeight()) * loc.getStartRow()) + getPaddingTop();
            if (z) {
                int measuredWidth = ((getRecyclerView().getMeasuredWidth() - (getRecyclerView().getMeasuredWidth() * loc.getPageIndex())) - getPaddingRight()) - ((layoutConfig.getHorizontalSpace() + layoutConfig.getCellWidth()) * loc.getStartCol());
                return new Rect(layoutConfig.getHorizontalSpace() + (measuredWidth - ((layoutConfig.getHorizontalSpace() + layoutConfig.getCellWidth()) * spanSize.getColSpan())), verticalSpace, measuredWidth, (((layoutConfig.getVerticalSpace() + layoutConfig.getCellHeight()) * spanSize.getRowSpan()) + verticalSpace) - layoutConfig.getVerticalSpace());
            }
            int horizontalSpace = ((layoutConfig.getHorizontalSpace() + layoutConfig.getCellWidth()) * loc.getStartCol()) + getPaddingLeft() + (getRecyclerView().getMeasuredWidth() * loc.getPageIndex());
            return new Rect(horizontalSpace, verticalSpace, (((layoutConfig.getHorizontalSpace() + layoutConfig.getCellWidth()) * spanSize.getColSpan()) + horizontalSpace) - layoutConfig.getHorizontalSpace(), (((layoutConfig.getVerticalSpace() + layoutConfig.getCellHeight()) * spanSize.getRowSpan()) + verticalSpace) - layoutConfig.getVerticalSpace());
        }
        if (!canScrollVertically()) {
            return new Rect();
        }
        LayoutConfig layoutConfig2 = this.layoutConfig;
        int verticalSpace2 = ((layoutConfig2.getVerticalSpace() + layoutConfig2.getCellHeight()) * loc.getStartRow()) + getPaddingTop() + (getRecyclerView().getMeasuredHeight() * loc.getPageIndex());
        if (z) {
            int measuredWidth2 = (getRecyclerView().getMeasuredWidth() - getPaddingRight()) - ((layoutConfig2.getHorizontalSpace() + layoutConfig2.getCellWidth()) * loc.getStartCol());
            return new Rect(layoutConfig2.getHorizontalSpace() + (measuredWidth2 - ((layoutConfig2.getHorizontalSpace() + layoutConfig2.getCellWidth()) * spanSize.getColSpan())), verticalSpace2, measuredWidth2, (((layoutConfig2.getVerticalSpace() + layoutConfig2.getCellHeight()) * spanSize.getRowSpan()) + verticalSpace2) - layoutConfig2.getVerticalSpace());
        }
        int horizontalSpace2 = ((layoutConfig2.getHorizontalSpace() + layoutConfig2.getCellWidth()) * loc.getStartCol()) + getPaddingLeft();
        return new Rect(horizontalSpace2, verticalSpace2, (((layoutConfig2.getHorizontalSpace() + layoutConfig2.getCellWidth()) * spanSize.getColSpan()) + horizontalSpace2) - layoutConfig2.getHorizontalSpace(), (((layoutConfig2.getVerticalSpace() + layoutConfig2.getCellHeight()) * spanSize.getRowSpan()) + verticalSpace2) - layoutConfig2.getVerticalSpace());
    }

    public final LayoutCallbackWrapper getLayoutCallbackWrapper() {
        return (LayoutCallbackWrapper) this.layoutCallbackWrapper$delegate.getValue();
    }

    public final LayoutConfig getLayoutConfig() {
        return this.layoutConfig;
    }

    public final int getPageCount() {
        return this.pageCount;
    }

    public final PagerCallbackWrapper getPagerCallbackWrapper() {
        return (PagerCallbackWrapper) this.pagerCallbackWrapper$delegate.getValue();
    }

    public final int getRawColumnByX(float f) {
        boolean z = getLayoutDirection() == 1;
        LayoutConfig layoutConfig = this.layoutConfig;
        if (z) {
            int measuredWidth = (int) (((getRecyclerView().getMeasuredWidth() - f) - getRecyclerView().getPaddingRight()) / (layoutConfig.getHorizontalSpace() + layoutConfig.getCellWidth()));
            return (((float) getRecyclerView().getMeasuredWidth()) - f) - ((float) getRecyclerView().getPaddingRight()) < 0.0f ? measuredWidth - 1 : measuredWidth;
        }
        int paddingLeft = (int) ((f - getRecyclerView().getPaddingLeft()) / (layoutConfig.getHorizontalSpace() + layoutConfig.getCellWidth()));
        return f - ((float) getRecyclerView().getPaddingLeft()) < 0.0f ? paddingLeft - 1 : paddingLeft;
    }

    public final int getRawColumnByY(float f) {
        LayoutConfig layoutConfig = this.layoutConfig;
        int paddingTop = (int) ((f - getRecyclerView().getPaddingTop()) / (layoutConfig.getVerticalSpace() + layoutConfig.getCellHeight()));
        return f - ((float) getRecyclerView().getPaddingTop()) < 0.0f ? paddingTop - 1 : paddingTop;
    }

    public final RecyclerView getRecyclerView() {
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            return recyclerView;
        }
        return null;
    }

    public final int getRowsByY(float f) {
        int rawColumnByY = getRawColumnByY(f);
        int rowCount = this.layoutConfig.getRowCount() - 1;
        if (rowCount < 0) {
            rowCount = 0;
        }
        return RangesKt___RangesKt.coerceIn(rawColumnByY, 0, rowCount);
    }

    public final int[] getScrollDelta(int i) {
        int measuredWidth;
        int[] iArr = new int[2];
        boolean z = getLayoutDirection() == 1;
        if (!canScrollHorizontally()) {
            iArr[1] = (getRecyclerView().getMeasuredHeight() * i) - this.layoutState.getScrollY();
            return iArr;
        }
        if (z) {
            i = -i;
            measuredWidth = getRecyclerView().getMeasuredWidth();
        } else {
            measuredWidth = getRecyclerView().getMeasuredWidth();
        }
        iArr[0] = (measuredWidth * i) - this.layoutState.getScrollX();
        return iArr;
    }

    public final boolean isEmptyCell(Loc loc) {
        return getEmptyPlaceHolderHandler().isEmptyCellByLoc(loc);
    }

    public void onAttachedToWindow(RecyclerView recyclerView) {
        String str = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->[onAttachedToWindow]", "SystemUi--QuickSettings");
        }
        super.onAttachedToWindow(recyclerView);
        setRecyclerView(recyclerView);
        recyclerView.addItemDecoration(getEmptyPlaceHolderHandler().getDecoration());
        refreshAllEmptyCell();
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        String str = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->[onDetachedFromWindow]", "SystemUi--QuickSettings");
        }
        onDetachedFromWindow(recyclerView);
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        String str = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("[onLayoutChildren] " + state);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        if (state.mInPreLayout) {
            return;
        }
        this.layoutState.getLayoutItems().clear();
        if (this.layoutConfig.assertLayoutInfoValid$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib("onLayoutChildren")) {
            detachAndScrapAttachedViews(recycler);
        } else {
            removeAndRecycleAllViews(recycler);
        }
        fillItems(recycler, true);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:37:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:43:0x0158  */
    public void onLayoutCompleted(RecyclerView.State state) {
        View view;
        RecyclerView.ViewHolder viewHolder;
        View view2;
        StaggeredPagerLayoutManager staggeredPagerLayoutManager;
        Rect itemRect;
        getEmptyPlaceHolderHandler().updateCellRect();
        List mutableList = SequencesKt___SequencesKt.toMutableList(SequencesKt___SequencesKt.filter(ViewGroupKt.getChildren(getRecyclerView()), new StaggeredPagerLayoutManager$$ExternalSyntheticLambda5()));
        Iterator it = mutableList.iterator();
        while (it.hasNext()) {
            detachView((View) it.next());
        }
        if (this.layoutConfig.getCreateEmptyViewHolder() == null) {
            return;
        }
        for (Loc loc : getEmptyPlaceHolderHandler().getEmptyLocs()) {
            if (mutableList.isEmpty()) {
                Function1 createEmptyViewHolder = this.layoutConfig.getCreateEmptyViewHolder();
                view = null;
                if (createEmptyViewHolder == null || (viewHolder = (RecyclerView.ViewHolder) createEmptyViewHolder.invoke(this.getRecyclerView())) == null) {
                    viewHolder = null;
                } else {
                    RecyclerView.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
                    RecyclerView.LayoutParams layoutParamsGenerateDefaultLayoutParams = layoutParams instanceof RecyclerView.LayoutParams ? layoutParams : null;
                    if (layoutParamsGenerateDefaultLayoutParams == null) {
                        layoutParamsGenerateDefaultLayoutParams = this.generateDefaultLayoutParams();
                    }
                    ReflectionUtils.setValue(RecyclerView.LayoutParams.class, layoutParamsGenerateDefaultLayoutParams, "mViewHolder", viewHolder);
                    ReflectionUtils.methodInvoke(viewHolder, RecyclerView.ViewHolder.class, "addFlags", new Class[]{Integer.TYPE}, 128);
                    viewHolder.itemView.setLayoutParams(layoutParamsGenerateDefaultLayoutParams);
                }
                if (viewHolder != null) {
                    view2 = viewHolder.itemView;
                }
                if (view != null) {
                    itemRect = this.getItemRect(loc, SpanCache.INSTANCE.spanSize(1, 1));
                    this.addView(view);
                    if (view.isLayoutRequested() || view.getMeasuredWidth() != itemRect.width() || view.getMeasuredHeight() != itemRect.height()) {
                        view.measure(View.MeasureSpec.makeMeasureSpec(itemRect.width(), 1073741824), View.MeasureSpec.makeMeasureSpec(itemRect.height(), 1073741824));
                    }
                    LayoutState layoutState = this.layoutState;
                    staggeredPagerLayoutManager = this;
                    staggeredPagerLayoutManager.layoutDecoratedWithMargins(view, itemRect.left - layoutState.getScrollX(), itemRect.top - layoutState.getScrollY(), itemRect.right - layoutState.getScrollX(), itemRect.bottom - layoutState.getScrollY());
                    view.setTag(2131363039, loc);
                    String str = staggeredPagerLayoutManager.TAG;
                    if (!LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                        sbM.append("[tryFillEmptyCellView] addEmptyView for " + loc);
                        Log.d("SystemUi--QuickSettings", sbM.toString());
                    }
                } else {
                    staggeredPagerLayoutManager = this;
                }
                this = staggeredPagerLayoutManager;
            } else {
                view2 = (View) mutableList.remove(0);
            }
            view = view2;
            if (view != null) {
                itemRect = this.getItemRect(loc, SpanCache.INSTANCE.spanSize(1, 1));
                this.addView(view);
                if (view.isLayoutRequested()) {
                    view.measure(View.MeasureSpec.makeMeasureSpec(itemRect.width(), 1073741824), View.MeasureSpec.makeMeasureSpec(itemRect.height(), 1073741824));
                } else {
                    view.measure(View.MeasureSpec.makeMeasureSpec(itemRect.width(), 1073741824), View.MeasureSpec.makeMeasureSpec(itemRect.height(), 1073741824));
                }
                LayoutState layoutState2 = this.layoutState;
                staggeredPagerLayoutManager = this;
                staggeredPagerLayoutManager.layoutDecoratedWithMargins(view, itemRect.left - layoutState2.getScrollX(), itemRect.top - layoutState2.getScrollY(), itemRect.right - layoutState2.getScrollX(), itemRect.bottom - layoutState2.getScrollY());
                view.setTag(2131363039, loc);
                String str2 = staggeredPagerLayoutManager.TAG;
                if (!LogUtil.getNormalDebug()) {
                }
            } else {
                staggeredPagerLayoutManager = this;
            }
            this = staggeredPagerLayoutManager;
        }
        StaggeredPagerLayoutManager staggeredPagerLayoutManager2 = this;
        String str3 = staggeredPagerLayoutManager2.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(staggeredPagerLayoutManager2.getEmptyPlaceHolderHandler().getEmptyLocs().size(), "[onLayoutCompleted] curr empty views=", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str3, LogUtil.SEPARATOR));
        }
    }

    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
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
        LayoutConfig layoutConfig = this.layoutConfig;
        int measuredHeight = 0;
        if (canScrollHorizontally()) {
            if (layoutConfig.getColCount() > 0 && layoutConfig.getCellWidth() > 0) {
                measuredHeight = (getRecyclerView().getMeasuredWidth() - (layoutConfig.getCellWidth() * layoutConfig.getColCount())) - (layoutConfig.getColCount() > 0 ? (layoutConfig.getColCount() - 1) * layoutConfig.getHorizontalSpace() : 0);
            }
            int i3 = measuredHeight / 2;
            getRecyclerView().setPadding(i3, getPaddingTop(), i3, getPaddingBottom());
        } else {
            if (layoutConfig.getRowCount() > 0 && layoutConfig.getCellHeight() > 0) {
                measuredHeight = (getRecyclerView().getMeasuredHeight() - (layoutConfig.getCellHeight() * layoutConfig.getRowCount())) - (layoutConfig.getRowCount() > 0 ? (layoutConfig.getRowCount() - 1) * layoutConfig.getVerticalSpace() : 0);
            }
            int i4 = measuredHeight / 2;
            getRecyclerView().setPadding(getPaddingLeft(), i4, getPaddingRight(), i4);
        }
        String str = this.TAG;
        int colCount = layoutConfig.getColCount();
        int rowCount = layoutConfig.getRowCount();
        int cellWidth = layoutConfig.getCellWidth();
        int cellHeight = layoutConfig.getCellHeight();
        int horizontalSpace = layoutConfig.getHorizontalSpace();
        int verticalSpace = layoutConfig.getVerticalSpace();
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("[updatePadding] [", colCount, rowCount, " * ", "]-[");
        RectList$.ExternalSyntheticOutline0.m(cellWidth, cellHeight, " * ", "],horizontalSpace=", sbM);
        LogUtil.i$default(LogUtil.TAG_QS, str, Fragment$5$.ExternalSyntheticOutline0.m(sbM, horizontalSpace, verticalSpace, ", verticalSpace="), null, 8);
        this.layoutState.getItemLayoutRects().clear();
        String str2 = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM2 = Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR);
            sbM2.append("[onMeasure] " + getRecyclerView().getMeasuredWidth() + ", " + getRecyclerView().getMeasuredHeight());
            Log.d("SystemUi--QuickSettings", sbM2.toString());
        }
        this.recycler = recycler;
    }

    public final void onPageScrolled() {
        int scrollY;
        float f;
        int scrollY2;
        float f2;
        int measuredHeight;
        if (getPagerCallbackWrapper().isEmpty()) {
            return;
        }
        if (!canScrollHorizontally()) {
            if (canScrollVertically()) {
                scrollY = this.layoutState.getScrollY() / getRecyclerView().getMeasuredHeight();
                scrollY2 = this.layoutState.getScrollY() - (getRecyclerView().getMeasuredHeight() * scrollY);
                f2 = scrollY2;
                measuredHeight = getRecyclerView().getMeasuredHeight();
            } else {
                scrollY = 0;
                f = 0.0f;
                scrollY2 = 0;
            }
            getPagerCallbackWrapper().onPageScrolled(scrollY, f, scrollY2);
        }
        int layoutDirection = getLayoutDirection();
        LayoutState layoutState = this.layoutState;
        int scrollX = layoutDirection == 1 ? -layoutState.getScrollX() : layoutState.getScrollX();
        scrollY = scrollX / getRecyclerView().getMeasuredWidth();
        scrollY2 = scrollX - (getRecyclerView().getMeasuredWidth() * scrollY);
        f2 = scrollY2;
        measuredHeight = getRecyclerView().getMeasuredWidth();
        f = f2 / measuredHeight;
        getPagerCallbackWrapper().onPageScrolled(scrollY, f, scrollY2);
    }

    public final void onScrollStateChanged(int i) {
        String str = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            ExifInterface$.ExternalSyntheticOutline1.m(Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR), TimerSpan$.ExternalSyntheticOutline0.m(this.layoutState.getScrollX(), this.layoutState.getScrollY(), ", ", "]", FloatList$.ExternalSyntheticOutline0.m("[onScrollStateChanged] ", i, this.currentPageIndex, ", currentPageIndex=", ", scroll[X,Y]=[")), "SystemUi--QuickSettings");
        }
        super.onScrollStateChanged(i);
        this.layoutState.setScrollState(i);
        getPagerCallbackWrapper().onPageScrollStateChanged(i);
        if (i == 0 || i == 2) {
            if (((COUIDynamicAnimation) getDragXSpringAnimation()).mRunning) {
                getDragXSpringAnimation().reset();
                getDragXSpringAnimation().setStartValue(this.layoutState.getScrollX());
            }
            if (((COUIDynamicAnimation) getDragYSpringAnimation()).mRunning) {
                getDragYSpringAnimation().reset();
                getDragYSpringAnimation().setStartValue(this.layoutState.getScrollY());
            }
        }
        if (i == 2) {
            getRecyclerView().setImportantForAccessibility(4);
        } else {
            getRecyclerView().setImportantForAccessibility(1);
        }
    }

    public final void refreshAllEmptyCell() {
        if (this.recyclerView != null) {
            getEmptyPlaceHolderHandler().refreshAllEmptyCell(this.pageCount);
        }
    }

    public final void refreshEmptyCell(int i, boolean z) {
        String str = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("[refreshEmptyCell] " + z + ", " + i);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        int i2 = this.pageCount;
        refreshPageCount(i, z);
        if (z) {
            getEmptyPlaceHolderHandler().refreshAllEmptyCell(this.pageCount);
            return;
        }
        int i3 = this.pageCount;
        for (int i4 = i2; i4 < i3; i4++) {
            getEmptyPlaceHolderHandler().addOneEmptyPage(i4);
        }
        int i5 = i2 - 1;
        int i6 = this.pageCount;
        if (i6 > i5) {
            return;
        }
        while (true) {
            getEmptyPlaceHolderHandler().removeOneEmptyPage(i5);
            if (i5 == i6) {
                return;
            } else {
                i5--;
            }
        }
    }

    public final void refreshPageCount(int i, boolean z) {
        if (this.recyclerView == null) {
            return;
        }
        int i2 = this.pageCount;
        setPageCount(-1 != i ? i : getDefaultPageCount());
        String str = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            int i3 = this.pageCount;
            int i4 = this.currentPageIndex;
            StringBuilder sbM2 = OplusLockIconView$.ExternalSyntheticOutline0.m("[refreshPageCount] (", i, OplusPreventModeData.SEPARATOR, "), PageCount: [", z);
            RectList$.ExternalSyntheticOutline0.m(i2, i3, "->", "],PageIndex:[", sbM2);
            sbM2.append(i4);
            sbM2.append("]");
            sbM.append(sbM2.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        int i5 = this.currentPageIndex;
        int i6 = this.pageCount;
        if (i5 >= i6) {
            if (z) {
                smoothScrollToPage(i6 - 1);
            } else {
                scrollToPage(i6 - 1);
            }
        }
    }

    public final Pair resolveCurrentPageColumnRows() {
        Integer numValueOf = null;
        Integer numValueOf2 = null;
        for (Loc loc : this.layoutConfig.getSpecifyLoc()) {
            if (loc.getPageIndex() == this.currentPageIndex) {
                if (numValueOf == null) {
                    numValueOf = Integer.valueOf(loc.getStartRow());
                }
                numValueOf2 = Integer.valueOf(loc.getStartRow());
            }
        }
        return TuplesKt.to(numValueOf, numValueOf2);
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.layoutConfig.getOrientation() == 1 || i == 0) {
            String str = this.TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                sbM.append("[scrollHorizontallyBy]return 0, for orientation =" + this.layoutConfig.getOrientation() + " or dx=" + i);
                Log.d("SystemUi--QuickSettings", sbM.toString());
            }
        } else {
            int scrollX = (!((COUIDynamicAnimation) getDragXSpringAnimation()).mRunning || this.layoutConfig.getNotSupportDragXAnima()) ? this.layoutState.getScrollX() : this.finalValueOfAnimation.x;
            boolean z = getLayoutDirection() == 1;
            LayoutState layoutState = this.layoutState;
            int minScrollX = z ? layoutState.getMinScrollX() : layoutState.getMaxScrollX();
            LayoutState layoutState2 = this.layoutState;
            int i2 = -(z ? layoutState2.getMaxScrollX() : layoutState2.getMinScrollX());
            int iCoerceIn = RangesKt___RangesKt.coerceIn(scrollX + i, i2, minScrollX);
            if (iCoerceIn != scrollX) {
                if (this.layoutState.getScrollState() != 1 || this.layoutConfig.getNotSupportDragXAnima()) {
                    scrollHorizontallyInternal(iCoerceIn);
                } else {
                    if (!((COUIDynamicAnimation) getDragXSpringAnimation()).mRunning) {
                        getDragXSpringAnimation().setStartValue(this.layoutState.getScrollX());
                        getDragXSpringAnimation().setStartVelocity(0.0f);
                    }
                    getDragXSpringAnimation().animateToFinalPosition(iCoerceIn);
                    this.finalValueOfAnimation.x = iCoerceIn;
                }
                int i3 = iCoerceIn - scrollX;
                if (getRecyclerView().getMeasuredWidth() == 0 || iCoerceIn % getRecyclerView().getMeasuredWidth() == 0 || scrollX % getRecyclerView().getMeasuredWidth() == 0) {
                    String str2 = this.TAG;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        StringBuilder sbM2 = Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR);
                        int scrollState = this.layoutState.getScrollState();
                        StringBuilder sbM3 = FloatList$.ExternalSyntheticOutline0.m("[scrollHorizontallyBy]return ", i3, i, ", for dx=", ",oldScrollX= ");
                        RectList$.ExternalSyntheticOutline0.m(scrollX, i2, ", in [", ", ", sbM3);
                        sbM3.append(minScrollX);
                        sbM3.append("],scrollState=");
                        sbM3.append(scrollState);
                        sbM2.append(sbM3.toString());
                        Log.d("SystemUi--QuickSettings", sbM2.toString());
                    }
                }
                return i3;
            }
            String str3 = this.TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                ExifInterface$.ExternalSyntheticOutline1.m(Bundler$.ExternalSyntheticOutline0.m(str3, LogUtil.SEPARATOR), TimerSpan$.ExternalSyntheticOutline0.m(i2, minScrollX, ", ", "]", FloatList$.ExternalSyntheticOutline0.m("[scrollHorizontallyBy]return 0,for dx=", i, scrollX, ",ScrollX old=new=", " in[")), "SystemUi--QuickSettings");
                return 0;
            }
        }
        return 0;
    }

    public final void scrollHorizontallyInternal(int i) {
        boolean z = getLayoutDirection() == 1;
        int scrollX = this.layoutState.getScrollX();
        this.layoutState.setScrollX(i);
        offsetChildrenHorizontal(scrollX - this.layoutState.getScrollX());
        if ((this.layoutState.getScrollX() <= scrollX || z) && (this.layoutState.getScrollX() >= scrollX || !z)) {
            RecyclerView.Recycler recycler = this.recycler;
            fillItems(recycler != null ? recycler : null, false);
        } else {
            RecyclerView.Recycler recycler2 = this.recycler;
            fillItems(recycler2 != null ? recycler2 : null, true);
        }
        onPageScrolled();
    }

    public final void scrollToPage(int i) {
        if (i < 0 || i >= this.pageCount) {
            String str = this.TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                sbM.append("[scrollToPage] " + i + " is invalid. pageCount=" + this.pageCount);
                Log.d("SystemUi--QuickSettings", sbM.toString());
                return;
            }
            return;
        }
        int[] scrollDelta = getScrollDelta(i);
        String str2 = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM2 = Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR);
            int i2 = this.currentPageIndex;
            int i3 = scrollDelta[0];
            int i4 = scrollDelta[1];
            int i5 = this.pageCount;
            StringBuilder sbM3 = FloatList$.ExternalSyntheticOutline0.m("[scrollToPage] ", i2, i, " -> ", ".Offset=[");
            RectList$.ExternalSyntheticOutline0.m(i3, i4, ", ", "], pageCount=", sbM3);
            sbM3.append(i5);
            sbM2.append(sbM3.toString());
            Log.d("SystemUi--QuickSettings", sbM2.toString());
        }
        if (scrollDelta[0] == 0 && scrollDelta[1] == 0) {
            return;
        }
        IPagerSnap onFlingListener = getRecyclerView().getOnFlingListener();
        IPagerSnap iPagerSnap = onFlingListener instanceof IPagerSnap ? onFlingListener : null;
        if (iPagerSnap != null) {
            ((OplusPagerStaggerSnapHelper) iPagerSnap).cancelFlingAnimation();
        }
        getRecyclerView().scrollBy(scrollDelta[0], scrollDelta[1]);
        setCurrentPageIndex(i);
    }

    public final void scrollToPosition(int i) {
        int pageIndex;
        String str = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("[scrollToPosition] " + i + ", size= " + this.layoutConfig.getSpecifyLoc().size());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        if (i >= this.layoutConfig.getSpecifyLoc().size()) {
            pageIndex = this.pageCount - 1;
        } else {
            Loc loc = (Loc) CollectionsKt___CollectionsKt.getOrNull(this.layoutConfig.getSpecifyLoc(), i);
            pageIndex = loc != null ? loc.getPageIndex() : -1;
        }
        scrollToPage(pageIndex);
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.layoutConfig.getOrientation() == 0 || i == 0) {
            String str = this.TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(i, "[scrollVerticallyBy] ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR));
            }
        } else {
            int scrollY = ((COUIDynamicAnimation) getDragYSpringAnimation()).mRunning ? this.finalValueOfAnimation.y : this.layoutState.getScrollY();
            int iCoerceIn = RangesKt___RangesKt.coerceIn(scrollY + i, -this.layoutState.getMinScrollY(), this.layoutState.getMaxScrollY());
            if (scrollY != iCoerceIn) {
                if (this.layoutState.getScrollState() == 1) {
                    if (!((COUIDynamicAnimation) getDragYSpringAnimation()).mRunning) {
                        getDragYSpringAnimation().setStartValue(this.layoutState.getScrollY());
                        getDragYSpringAnimation().setStartVelocity(0.0f);
                    }
                    getDragYSpringAnimation().animateToFinalPosition(iCoerceIn);
                    this.finalValueOfAnimation.y = iCoerceIn;
                } else {
                    scrollVerticallyInternal(iCoerceIn);
                }
                int i2 = iCoerceIn - scrollY;
                String str2 = this.TAG;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR);
                    StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("[scrollVerticallyBy] return ", i2, i, " for dy ", ", oldScrollY=");
                    sbM2.append(scrollY);
                    sbM.append(sbM2.toString());
                    Log.d("SystemUi--QuickSettings", sbM.toString());
                }
                return i2;
            }
        }
        return 0;
    }

    public final void scrollVerticallyInternal(int i) {
        int scrollY = this.layoutState.getScrollY();
        this.layoutState.setScrollY(i);
        offsetChildrenVertical(scrollY - this.layoutState.getScrollY());
        int scrollY2 = this.layoutState.getScrollY() - scrollY;
        RecyclerView.Recycler recycler = this.recycler;
        if (scrollY2 > 0) {
            if (recycler == null) {
                recycler = null;
            }
            fillItems(recycler, true);
        } else {
            if (recycler == null) {
                recycler = null;
            }
            fillItems(recycler, false);
        }
        onPageScrolled();
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.IPagerSnapCallback
    public void setCurrPageIndex(int[] iArr) {
        String str = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            ExifInterface$.ExternalSyntheticOutline1.m(Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR), TimerSpan$.ExternalSyntheticOutline0.m(iArr[0], iArr[1], OplusPreventModeData.SEPARATOR, "]", FloatList$.ExternalSyntheticOutline0.m("[setCurrPageIndex] layoutState[", this.layoutState.getScrollX(), this.layoutState.getScrollY(), OplusPreventModeData.SEPARATOR, "], targetOffset[")), "SystemUi--QuickSettings");
        }
        boolean zCanScrollHorizontally = canScrollHorizontally();
        LayoutState layoutState = this.layoutState;
        setCurrentPageIndex(zCanScrollHorizontally ? Math.abs(layoutState.getScrollX() + iArr[0]) / getRecyclerView().getMeasuredWidth() : (layoutState.getScrollY() + iArr[1]) / getRecyclerView().getMeasuredHeight());
    }

    public final void setEmptyPlaceHolder(Drawable drawable, Rect rect, Function2 function2) {
        getEmptyPlaceHolderHandler().getPlaceHolder().update(drawable, rect, function2);
    }

    public final boolean setLoc(List<Loc> list) {
        if (Intrinsics.areEqual(this.layoutConfig.getSpecifyLoc(), list)) {
            return false;
        }
        this.layoutConfig.setChildrenLoc$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(list);
        this.layoutState.getItemLayoutRects().clear();
        requestLayout();
        return true;
    }

    public final void setPlaceHolderVisible(Loc loc, boolean z, boolean z2, boolean z3) {
        getEmptyPlaceHolderHandler().setPlaceHolderVisible(loc, z, z2, z3);
    }

    public final void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public final void smoothScrollToPage(int i) {
        int i2;
        int[] scrollDelta;
        if (i < 0 || i >= this.pageCount || i == (i2 = this.currentPageIndex)) {
            LogUtil.w$default(8, LogUtil.TAG_QS, this.TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(i, this.pageCount, "[smoothScrollToPage] ", " is invalid. pageCount="));
            return;
        }
        boolean z = Math.abs(i - i2) > 3;
        if (i < this.currentPageIndex) {
            if (z) {
                scrollToPage(i + 1);
            }
            scrollDelta = getScrollDelta(i);
        } else {
            if (z) {
                scrollToPage(i - 1);
            }
            scrollDelta = getScrollDelta(i);
        }
        String str = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            int i3 = this.currentPageIndex;
            int i4 = scrollDelta[0];
            int i5 = scrollDelta[1];
            int i6 = this.pageCount;
            StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("[smoothScrollToPage] ", i3, i, " -> ", ", targetOffset=[");
            RectList$.ExternalSyntheticOutline0.m(i4, i5, ", ", "],  pageCount=", sbM2);
            sbM2.append(i6);
            sbM.append(sbM2.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        IPagerSnap onFlingListener = getRecyclerView().getOnFlingListener();
        if (onFlingListener instanceof IPagerSnap) {
            ((OplusPagerStaggerSnapHelper) onFlingListener).flingByOffset(this, DeviceUtil.dp2px(getRecyclerView().getContext(), 500.0f), 0, scrollDelta);
        }
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        int pageIndex;
        String str = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("[smoothScrollToPosition] " + i + ", size= " + this.layoutConfig.getSpecifyLoc().size());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        if (i >= this.layoutConfig.getSpecifyLoc().size()) {
            pageIndex = this.pageCount - 1;
        } else {
            Loc loc = (Loc) CollectionsKt___CollectionsKt.getOrNull(this.layoutConfig.getSpecifyLoc(), i);
            pageIndex = loc != null ? loc.getPageIndex() : -1;
        }
        smoothScrollToPage(pageIndex);
    }
}
