package com.oplus.systemui.plugins.qs.customize.component.proxy;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import androidx.core.view.ViewKt$;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplus.systemui.plugins.qs.CellAreaConfig;
import com.oplus.systemui.plugins.qs.CellAreaConfig$$ExternalSyntheticOutline0;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function1;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CellSizeChangeProxy implements CellCalculatorManager.CellCalculatorListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CellSizeChangeProxy";
    private final CellAreaProvider cellAreaProvider;
    private final Context context;
    private final RecyclerView hostRecyclerView;
    private final Function1 hostVisibleModifier;
    public final boolean isForPersonalArea;
    public final boolean isLeftBArea;
    private final Lazy layoutManagerFromCellInfo$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$xlC-ErL2pH1Fut_vzqylCv_jewE, reason: not valid java name */
    public static StaggeredPagerLayoutManager m143$r8$lambda$xlCErL2pH1Fut_vzqylCv_jewE(CellSizeChangeProxy cellSizeChangeProxy) {
        String str;
        SpanSize currGridInfo = cellSizeChangeProxy.getCurrGridInfo();
        CellCalculatorManager companion = CellCalculatorManager.Companion.getInstance(cellSizeChangeProxy.context);
        boolean z = cellSizeChangeProxy.isForPersonalArea;
        CellCalculatorManager.CellSize cellSize = companion.getCellSize(z);
        if (z) {
            str = "A";
        } else {
            str = cellSizeChangeProxy.isLeftBArea ? "LeftB" : "B";
        }
        StaggeredPagerLayoutManager staggeredPagerLayoutManagerBuild = new StaggeredPagerLayoutManager.LayoutConfig(currGridInfo.getRowSpan(), currGridInfo.getColSpan(), cellSize.getCellWidth(), cellSize.getCellHeight(), !z).build(str);
        cellSizeChangeProxy.tryAdjustHostSize(staggeredPagerLayoutManagerBuild.getLayoutConfig());
        return staggeredPagerLayoutManagerBuild;
    }

    public CellSizeChangeProxy(Context context, RecyclerView recyclerView, boolean z, Function1 function1, boolean z2, CellAreaProvider cellAreaProvider) {
        this.context = context;
        this.hostRecyclerView = recyclerView;
        this.isForPersonalArea = z;
        this.hostVisibleModifier = function1;
        this.isLeftBArea = z2;
        this.cellAreaProvider = cellAreaProvider;
        CellSizeChangeProxy$$ExternalSyntheticLambda0 cellSizeChangeProxy$$ExternalSyntheticLambda0 = new CellSizeChangeProxy$$ExternalSyntheticLambda0();
        cellSizeChangeProxy$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.layoutManagerFromCellInfo$delegate = LazyKt__LazyJVMKt.lazy(cellSizeChangeProxy$$ExternalSyntheticLambda0);
    }

    private final SpanSize getCurrGridInfo() {
        boolean zBooleanValue = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue();
        CellCalculatorManager companion = CellCalculatorManager.Companion.getInstance(this.context);
        boolean z = this.isForPersonalArea;
        List<SpanSize> gridInfo = companion.getGridInfo(zBooleanValue, z);
        boolean z2 = this.isLeftBArea;
        if (!zBooleanValue || z) {
            return z2 ? SpanCache.EMPTY_SPANSIZE : gridInfo.get(0);
        }
        return z2 ? gridInfo.get(0) : gridInfo.get(1);
    }

    public static /* synthetic */ void tryAdjustHostSize$default(CellSizeChangeProxy cellSizeChangeProxy) {
        cellSizeChangeProxy.tryAdjustHostSize(cellSizeChangeProxy.getLayoutManagerFromCellInfo().getLayoutConfig());
    }

    public final StaggeredPagerLayoutManager getLayoutManagerFromCellInfo() {
        return (StaggeredPagerLayoutManager) this.layoutManagerFromCellInfo$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.CellCalculatorManager.CellCalculatorListener
    public final void onCellSizeChange(boolean z) {
        boolean z2 = this.isForPersonalArea;
        if (z2 != z) {
            return;
        }
        CellCalculatorManager.CellSize cellSize = CellCalculatorManager.Companion.getInstance(this.context).getCellSize(z2);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "CellSizeChangeProxy-->".concat("[onCellSizeChange] " + cellSize + ", isForPersonalArea=" + z2));
        }
        StaggeredPagerLayoutManager.LayoutConfig.setCellInfo$default(getLayoutManagerFromCellInfo().getLayoutConfig(), cellSize.getCellWidth(), cellSize.getCellHeight());
        tryAdjustHostSize$default(this);
    }

    @Override // com.oplus.systemui.plugins.qs.CellCalculatorManager.CellCalculatorListener
    public final void onGridInfoChange(boolean z, boolean z2, boolean z3) {
        boolean z4 = false;
        boolean z5 = z && !z2;
        boolean zBooleanValue = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue();
        boolean z6 = this.isForPersonalArea;
        if (zBooleanValue && !z6) {
            z4 = true;
        }
        if (z6 == z2 && z5 == z4) {
            if (!z5 || this.isLeftBArea == z3) {
                refreshGridNum();
            }
        }
    }

    public final void onViewAttached() {
        boolean z = this.isForPersonalArea;
        onCellSizeChange(z);
        onGridInfoChange(false, z, false);
        CellCalculatorManager.Companion.getInstance(this.context).addCellCalculatorListener(this);
    }

    public final void onViewDetached() {
        CellCalculatorManager.Companion.getInstance(this.context).removeCellCalculatorListener(this);
    }

    public final void refreshGridNum() {
        SpanSize currGridInfo = getCurrGridInfo();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sb = new StringBuilder("[refreshGridNum] ");
            sb.append(currGridInfo);
            sb.append(", isForPersonalArea=");
            sb.append(this.isForPersonalArea);
            sb.append(", isLeftBArea=");
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("CellSizeChangeProxy-->", "SystemUi--QuickSettings", sb, this.isLeftBArea);
        }
        getLayoutManagerFromCellInfo().getLayoutConfig().setGridNum(currGridInfo.getRowSpan(), currGridInfo.getColSpan());
        tryAdjustHostSize$default(this);
        this.hostVisibleModifier.invoke(Integer.valueOf(currGridInfo.getRowSpan()));
    }

    public final void tryAdjustHostSize(StaggeredPagerLayoutManager.LayoutConfig layoutConfig) {
        CellAreaConfig cellAreaConfig;
        CellAreaConfig cellAreaConfig2;
        RecyclerView recyclerView = this.hostRecyclerView;
        ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
        if (layoutParams == null) {
            ViewKt$.ExternalSyntheticBUOutline0.m("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            return;
        }
        int i = layoutParams.width;
        int i2 = layoutParams.height;
        boolean zBooleanValue = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue();
        int i3 = 1;
        int editABottomBorderMargin = 0;
        if (zBooleanValue) {
            layoutParams.width = (layoutConfig.getColCount() * layoutConfig.getCellWidth()) + (layoutConfig.getColCount() > 0 ? layoutConfig.getHorizontalSpace() * (layoutConfig.getColCount() - 1) : 0);
        }
        if (this.isForPersonalArea) {
            if (zBooleanValue) {
                layoutParams.height = 0;
            } else {
                if (layoutConfig.getRowCount() != 0) {
                    int rowCount = (layoutConfig.getRowCount() * layoutConfig.getCellHeight()) + (layoutConfig.getRowCount() > 0 ? layoutConfig.getVerticalSpace() * (layoutConfig.getRowCount() - 1) : 0);
                    CellAreaProvider cellAreaProvider = this.cellAreaProvider;
                    int editATopBorderMargin = rowCount + ((cellAreaProvider == null || (cellAreaConfig2 = cellAreaProvider.getCellAreaConfig()) == null) ? 0 : cellAreaConfig2.getEditATopBorderMargin());
                    CellAreaProvider cellAreaProvider2 = this.cellAreaProvider;
                    if (cellAreaProvider2 != null && (cellAreaConfig = cellAreaProvider2.getCellAreaConfig()) != null) {
                        editABottomBorderMargin = cellAreaConfig.getEditABottomBorderMargin();
                    }
                    i3 = editATopBorderMargin + editABottomBorderMargin;
                }
                layoutParams.height = i3;
            }
        }
        if (i == layoutParams.width && i2 == layoutParams.height) {
            return;
        }
        recyclerView.setLayoutParams(layoutParams);
    }

    public final void updateAreaRow(int i) {
        if (this.isForPersonalArea) {
            CellCalculatorManager.Companion.getInstance(this.context).updatePersonalAreaRow(i);
        } else {
            Path$.ExternalSyntheticBUOutline0.m$1("cannot update row count for B-Area");
        }
    }

    public /* synthetic */ CellSizeChangeProxy(Context context, RecyclerView recyclerView, boolean z, Function1 function1, CellAreaProvider cellAreaProvider, int i) {
        this(context, recyclerView, z, function1, (i & 16) == 0, (i & 32) != 0 ? null : cellAreaProvider);
    }
}
