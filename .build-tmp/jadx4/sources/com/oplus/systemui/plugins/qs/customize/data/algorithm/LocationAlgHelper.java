package com.oplus.systemui.plugins.qs.customize.data.algorithm;

import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfigKt;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.LocCache;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.model.SpanSizeKt;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.text.Regex$;
import kotlinx.coroutines.internal.Symbol;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LocationAlgHelper {
    private static final String TAG = "LocationAlgHelper";
    public static GridInfoEntity gridInfo;
    public static final LocationAlgHelper INSTANCE = new LocationAlgHelper();
    private static final TileConfig[][][] pageTileInfos = new TileConfig[1][][];
    private static final HashMap<String, Integer> tileIndexMap = new HashMap<>();

    public static final void addPageUseInfo(int i) {
        if (i >= 1 || pageTileInfos[i] != null) {
            return;
        }
        GridInfoEntity gridInfoEntity = gridInfo;
        if (gridInfoEntity == null) {
            gridInfoEntity = null;
        }
        int gridRow = gridInfoEntity.getGridRow();
        GridInfoEntity gridInfoEntity2 = gridInfo;
        if (gridInfoEntity2 == null) {
            gridInfoEntity2 = null;
        }
        int gridCol = gridInfoEntity2.getGridCol();
        TileConfig[][] tileConfigArr = new TileConfig[gridRow][];
        for (int i2 = 0; i2 < gridRow; i2++) {
            TileConfig[] tileConfigArr2 = new TileConfig[gridCol];
            for (int i3 = 0; i3 < gridCol; i3++) {
                tileConfigArr2[i3] = null;
            }
            tileConfigArr[i2] = tileConfigArr2;
        }
        pageTileInfos[i] = tileConfigArr;
    }

    public static boolean canDraggingAreaIncludeTarget(TargetArea targetArea, TileConfig tileConfig) {
        Loc cornerPos = targetArea.getCornerPos();
        Loc involvedLoc = targetArea.getInvolvedLoc();
        if (targetArea.getInvolvedTiles().size() != 1 || !Intrinsics.areEqual(tileConfig, CollectionsKt___CollectionsKt.first(targetArea.getInvolvedTiles()))) {
            if (cornerPos.getStartRow() > involvedLoc.getStartRow()) {
                return false;
            }
            if (targetArea.getSize().getRowSpan() + cornerPos.getStartRow() < targetArea.getInvolvedSpanSize().getRowSpan() + involvedLoc.getStartRow() || cornerPos.getStartCol() > involvedLoc.getStartCol()) {
                return false;
            }
            if (targetArea.getSize().getColSpan() + cornerPos.getStartCol() < targetArea.getInvolvedSpanSize().getColSpan() + involvedLoc.getStartCol()) {
                return false;
            }
        }
        return true;
    }

    public static boolean canMatchColAndRow(Rect rect, GridInfoEntity gridInfoEntity) {
        return rect.getLeft() >= 0 && rect.getRight() <= gridInfoEntity.getGridCol() && rect.getTop() >= 0 && rect.getBottom() <= gridInfoEntity.getGridRow();
    }

    public static boolean canUpdateInvolvedTiles(TargetArea targetArea, int i) {
        int startRow = targetArea.getInvolvedLoc().getStartRow();
        int startCol = targetArea.getInvolvedLoc().getStartCol();
        int colSpan = targetArea.getInvolvedSpanSize().getColSpan();
        int rowSpan = targetArea.getInvolvedSpanSize().getRowSpan() + startRow;
        for (int i2 = startRow; i2 < rowSpan; i2++) {
            int i3 = startCol + colSpan;
            for (int i4 = startCol; i4 < i3; i4++) {
                TileConfig locTile = getLocTile(LocCache.INSTANCE.loc(targetArea.getCornerPos().getPageIndex(), i4, i2));
                if (locTile != null) {
                    if (i == 1 || i == 2) {
                        if (locTile.getLocation().getStartRow() < startRow) {
                            return false;
                        }
                        if (locTile.getSize().getRowSpan() + locTile.getLocation().getStartRow() > rowSpan) {
                            return false;
                        }
                    } else if (i == 3 || i == 4) {
                        if (locTile.getLocation().getStartCol() < startCol) {
                            return false;
                        }
                        if (locTile.getSize().getColSpan() + locTile.getLocation().getStartCol() > i3) {
                            return false;
                        }
                    }
                    if (!targetArea.getInvolvedTiles().contains(locTile)) {
                        targetArea.getInvolvedTiles().add(locTile);
                    }
                }
            }
        }
        return true;
    }

    public static final void clearPageUsesInfos() {
        ArraysKt___ArraysJvmKt.fill$default(pageTileInfos, (Symbol) null);
        tileIndexMap.clear();
    }

    public static final Loc findEmptyPositionLoc(Loc loc, GridInfoEntity gridInfoEntity, boolean z) {
        int pageIndex = loc.getPageIndex();
        int startCol = loc.getStartCol();
        int startRow = loc.getStartRow();
        if (!z) {
            for (int i = startRow; -1 < i; i--) {
                for (int gridCol = gridInfoEntity.getGridCol() - 1; -1 < gridCol; gridCol--) {
                    if (i != startRow || gridCol < startCol) {
                        Loc loc2 = LocCache.INSTANCE.loc(pageIndex, gridCol, i);
                        if (!INSTANCE.isLocationUsed(loc2)) {
                            return loc2;
                        }
                    }
                }
            }
            return null;
        }
        int gridRow = gridInfoEntity.getGridRow();
        for (int i2 = startRow; i2 < gridRow; i2++) {
            int gridCol2 = gridInfoEntity.getGridCol();
            for (int i3 = 0; i3 < gridCol2; i3++) {
                if (i2 != startRow || i3 > startCol) {
                    Loc loc3 = LocCache.INSTANCE.loc(pageIndex, i3, i2);
                    if (!INSTANCE.isLocationUsed(loc3)) {
                        return loc3;
                    }
                }
            }
        }
        return null;
    }

    public static int getContiguousDirections(Rect rect, Rect rect2) {
        if (rect.getRight() == rect2.getLeft()) {
            return 1;
        }
        if (rect.getLeft() == rect2.getRight()) {
            return 2;
        }
        if (rect.getBottom() == rect2.getTop()) {
            return 3;
        }
        return rect.getTop() == rect2.getBottom() ? 4 : 0;
    }

    public static final int getLocIndex(Loc loc) {
        int startRow = loc.getStartRow();
        GridInfoEntity gridInfoEntity = gridInfo;
        if (gridInfoEntity == null) {
            gridInfoEntity = null;
        }
        return loc.getStartCol() + (gridInfoEntity.getGridCol() * startRow);
    }

    public static final TileConfig getLocTile(Loc loc) {
        TileConfig[] tileConfigArr;
        TileConfig[][] tileConfigArr2 = pageTileInfos[loc.getPageIndex()];
        if (tileConfigArr2 == null || (tileConfigArr = tileConfigArr2[loc.getStartRow()]) == null) {
            return null;
        }
        return tileConfigArr[loc.getStartCol()];
    }

    public static final int getTileIndex(TileConfig tileConfig) {
        Integer num = tileIndexMap.get(tileConfig.getTileSpec());
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public static TargetArea involvedTargetArea(List list, TileConfig tileConfig, Loc loc) {
        Iterator it = list.iterator();
        int iMin = Integer.MAX_VALUE;
        int iMax = Integer.MIN_VALUE;
        int iMax2 = Integer.MIN_VALUE;
        int iMin2 = Integer.MAX_VALUE;
        while (it.hasNext()) {
            TileConfig tileConfig2 = (TileConfig) it.next();
            int colSpan = tileConfig2.getSize().getColSpan();
            int rowSpan = tileConfig2.getSize().getRowSpan();
            int startCol = tileConfig2.getLocation().getStartCol();
            int startRow = tileConfig2.getLocation().getStartRow();
            iMin = Math.min(iMin, startCol);
            iMax = Math.max(iMax, startCol + colSpan);
            iMin2 = Math.min(iMin2, startRow);
            iMax2 = Math.max(iMax2, startRow + rowSpan);
        }
        SpanSize spanSize = SpanCache.INSTANCE.spanSize(iMax - iMin, iMax2 - iMin2);
        Loc loc2 = LocCache.INSTANCE.loc(loc.getPageIndex(), iMin, iMin2);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "calculateTargetArea: involvedLoc= " + loc2, null, 8);
        return new TargetArea(loc, tileConfig.getSize(), list, loc2, spanSize);
    }

    public static boolean isEqualHeightOrWidth(Rect rect, Rect rect2) {
        return (rect.getRight() >= rect2.getRight() && rect.getLeft() <= rect2.getLeft()) || (rect.getBottom() >= rect2.getBottom() && rect.getTop() <= rect2.getTop());
    }

    public static final boolean isLocationUsed(Loc loc, SpanSize spanSize) {
        GridInfoEntity gridInfoEntity = gridInfo;
        if (gridInfoEntity == null) {
            gridInfoEntity = null;
        }
        int gridCol = gridInfoEntity.getGridCol();
        GridInfoEntity gridInfoEntity2 = gridInfo;
        int gridRow = (gridInfoEntity2 != null ? gridInfoEntity2 : null).getGridRow();
        int startCol = loc.getStartCol();
        int startRow = loc.getStartRow();
        int colSpan = spanSize.getColSpan() + startCol;
        int i = colSpan - 1;
        int rowSpan = spanSize.getRowSpan() + startRow;
        int i2 = rowSpan - 1;
        if (startCol < 0 || startRow < 0 || i >= gridCol || i2 >= gridRow) {
            return true;
        }
        while (startCol < colSpan) {
            for (int i3 = startRow; i3 < rowSpan; i3++) {
                if (INSTANCE.isLocationUsed(LocCache.INSTANCE.loc(loc.getPageIndex(), startCol, i3))) {
                    return true;
                }
            }
            startCol++;
        }
        return false;
    }

    public static final void refreshPageUseInfos(GridInfoEntity gridInfoEntity, List<TileConfig> list) {
        gridInfo = gridInfoEntity;
        tileIndexMap.clear();
        int pageCount = INSTANCE.getPageCount(CollectionsKt___CollectionsKt.toMutableList(list));
        int i = 0;
        for (int i2 = 0; i2 < pageCount; i2++) {
            addPageUseInfo(i2);
        }
        for (TileConfig[][] tileConfigArr : pageTileInfos) {
            if (tileConfigArr != null) {
                Iterator it = ArrayIteratorKt.iterator(tileConfigArr);
                while (it.hasNext()) {
                    ArraysKt___ArraysJvmKt.fill$default((TileConfig[]) it.next(), (Symbol) null);
                }
            }
        }
        for (TileConfig tileConfig : list) {
            int i3 = i + 1;
            int pageIndex = tileConfig.getLocation().getPageIndex();
            int startCol = tileConfig.getLocation().getStartCol();
            int startRow = tileConfig.getLocation().getStartRow();
            SpanSize size = tileConfig.getSize();
            TileConfig[][] tileConfigArr2 = pageTileInfos[pageIndex];
            if (tileConfigArr2 != null) {
                int rowSpan = size.getRowSpan() + startRow;
                while (startRow < rowSpan) {
                    TileConfig[] tileConfigArr3 = tileConfigArr2[startRow];
                    int colSpan = size.getColSpan() + startCol;
                    for (int i4 = startCol; i4 < colSpan; i4++) {
                        tileConfigArr3[i4] = tileConfig;
                    }
                    startRow++;
                }
                tileIndexMap.put(tileConfig.getTileSpec(), Integer.valueOf(i));
            }
            i = i3;
        }
    }

    public static final void removeTile(TileConfig tileConfig, List<TileConfig> list) {
        int tileIndex = getTileIndex(tileConfig);
        if (tileIndex < 0) {
            list.remove(tileConfig);
        } else {
            list.remove(tileIndex);
            tileIndexMap.remove(tileConfig.getTileSpec());
        }
    }

    public static void translateDraggingAndTargetCol(List list, TileConfig tileConfig, List list2, int i) {
        Loc location = tileConfig.getLocation();
        int startCol = location.getStartCol();
        int iIndexOf = list.indexOf(tileConfig);
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            TileConfig tileConfig2 = (TileConfig) it.next();
            list.set(list.indexOf(tileConfig2), TileConfigKt.replaceLoc(tileConfig2, LocCache.INSTANCE.loc(location.getPageIndex(), tileConfig2.getLocation().getStartCol() + i, tileConfig2.getLocation().getStartRow())));
        }
        list.set(iIndexOf, TileConfigKt.replaceLoc(tileConfig, LocCache.INSTANCE.loc(location.getPageIndex(), startCol + i, location.getStartRow())));
    }

    public static void translateDraggingAndTargetRow(List list, TileConfig tileConfig, List list2, int i) {
        Loc location = tileConfig.getLocation();
        int startRow = location.getStartRow();
        int iIndexOf = list.indexOf(tileConfig);
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            TileConfig tileConfig2 = (TileConfig) it.next();
            list.set(list.indexOf(tileConfig2), TileConfigKt.replaceLoc(tileConfig2, LocCache.INSTANCE.loc(location.getPageIndex(), tileConfig2.getLocation().getStartCol(), tileConfig2.getLocation().getStartRow() + i)));
        }
        list.set(iIndexOf, TileConfigKt.replaceLoc(tileConfig, LocCache.INSTANCE.loc(location.getPageIndex(), location.getStartCol(), startRow + i)));
    }

    public static final void updateLocUseInfo(Loc loc, SpanSize spanSize, TileConfig tileConfig) {
        if (SpanSizeKt.isOneXOne(spanSize)) {
            updateLocUseInfo(tileConfig, loc);
            return;
        }
        int pageIndex = loc.getPageIndex();
        int colSpan = spanSize.getColSpan() + loc.getStartCol();
        for (int startCol = loc.getStartCol(); startCol < colSpan; startCol++) {
            int rowSpan = spanSize.getRowSpan() + loc.getStartRow();
            for (int startRow = loc.getStartRow(); startRow < rowSpan; startRow++) {
                updateLocUseInfo(tileConfig, LocCache.INSTANCE.loc(pageIndex, startCol, startRow));
            }
        }
    }

    public final TargetArea calculateTargetArea(TileConfig tileConfig, Loc loc, GridInfoEntity gridInfoEntity) {
        ArrayList arrayList = new ArrayList();
        int iMin = Math.min(tileConfig.getSize().getColSpan() + loc.getStartCol(), gridInfoEntity.getGridCol());
        int iMin2 = Math.min(tileConfig.getSize().getRowSpan() + loc.getStartRow(), gridInfoEntity.getGridRow());
        for (int startCol = loc.getStartCol(); startCol < iMin; startCol++) {
            for (int startRow = loc.getStartRow(); startRow < iMin2; startRow++) {
                TileConfig locTile = getLocTile(LocCache.INSTANCE.loc(loc.getPageIndex(), startCol, startRow));
                if (locTile != null && !arrayList.contains(locTile)) {
                    arrayList.add(locTile);
                }
            }
        }
        return involvedTargetArea(arrayList, tileConfig, loc);
    }

    public final boolean canMatchBasicInsertScene(List<TileConfig> list, int i, Loc loc, GridInfoEntity gridInfoEntity) {
        boolean zIsOneXOne;
        SpanSize size;
        TileConfig tileConfig = list.get(i);
        Rect rect = new Rect(loc, tileConfig.getSize());
        if (SpanSizeKt.isOneXOne(tileConfig.getSize())) {
            TileConfig locTile = getLocTile(loc);
            if (locTile == null || (size = locTile.getSize()) == null) {
                zIsOneXOne = !isLocationUsed(loc);
            } else {
                zIsOneXOne = SpanSizeKt.isOneXOne(size);
            }
            if (zIsOneXOne && canMatchColAndRow(rect, gridInfoEntity)) {
                return true;
            }
        }
        return false;
    }

    public final TargetArea excludeDraggingTargetAre(List<TileConfig> list, TileConfig tileConfig, Loc loc) {
        list.remove(tileConfig);
        return involvedTargetArea(list, tileConfig, loc);
    }

    public final int getPageCount(List<TileConfig> list) {
        if (!list.isEmpty()) {
            Iterator<T> it = list.iterator();
            if (it.hasNext()) {
                int pageIndex = ((TileConfig) it.next()).getLocation().getPageIndex();
                while (it.hasNext()) {
                    int pageIndex2 = ((TileConfig) it.next()).getLocation().getPageIndex();
                    if (pageIndex < pageIndex2) {
                        pageIndex = pageIndex2;
                    }
                }
                return pageIndex + 1;
            }
            Regex$.ExternalSyntheticBUOutline0.m();
        }
        return 0;
    }

    public final int isTilesContiguous(TargetArea targetArea, TileConfig tileConfig) {
        if (tileConfig.getLocation().getPageIndex() == targetArea.getCornerPos().getPageIndex() && !targetArea.getInvolvedTiles().isEmpty()) {
            Rect rect = new Rect(tileConfig.getLocation(), tileConfig.getSize());
            if (!canDraggingAreaIncludeTarget(targetArea, tileConfig)) {
                TileConfig tileConfig2 = (TileConfig) CollectionsKt___CollectionsKt.first(targetArea.getInvolvedTiles());
                Rect rect2 = new Rect(tileConfig2.getLocation(), tileConfig2.getSize());
                if (isEqualHeightOrWidth(rect, rect2)) {
                    return getContiguousDirections(rect, rect2);
                }
            } else if (isEqualHeightOrWidth(rect, new Rect(targetArea.getCornerPos(), tileConfig.getSize()))) {
                int contiguousDirections = 0;
                for (TileConfig tileConfig3 : targetArea.getInvolvedTiles()) {
                    Rect rect3 = new Rect(tileConfig3.getLocation(), tileConfig3.getSize());
                    INSTANCE.getClass();
                    if (isEqualHeightOrWidth(rect, rect3) && (contiguousDirections = getContiguousDirections(rect, rect3)) != 0) {
                    }
                }
                return contiguousDirections;
            }
        }
        return 0;
    }

    public final boolean matchBasicSwapScene(TargetArea targetArea, TileConfig tileConfig, GridInfoEntity gridInfoEntity) {
        if (canDraggingAreaIncludeTarget(targetArea, tileConfig)) {
            return targetArea.getInvolvedSpanSize().getColSpan() <= tileConfig.getSize().getColSpan() && targetArea.getInvolvedSpanSize().getRowSpan() <= tileConfig.getSize().getRowSpan() && canMatchColAndRow(new Rect(targetArea.getCornerPos(), tileConfig.getSize()), gridInfoEntity);
        }
        return false;
    }

    public final boolean matchBigAccordionSwapScene(TargetArea targetArea, TileConfig tileConfig, GridInfoEntity gridInfoEntity, int i) {
        return canDraggingAreaIncludeTarget(targetArea, tileConfig) && (i == 3 || i == 4 ? targetArea.getInvolvedSpanSize().getRowSpan() == 1 : targetArea.getInvolvedSpanSize().getColSpan() == 1) && canMatchColAndRow(new Rect(targetArea.getCornerPos(), tileConfig.getSize()), gridInfoEntity);
    }

    public final boolean matchPushSwapScene(TargetArea targetArea, TileConfig tileConfig, int i) {
        boolean zIsLocationUsed;
        int pageIndex = targetArea.getCornerPos().getPageIndex();
        if (pageIndex != tileConfig.getLocation().getPageIndex()) {
            return false;
        }
        if (i == 1 || i == 2) {
            if (targetArea.getCornerPos().getStartRow() != targetArea.getInvolvedLoc().getStartRow() || tileConfig.getSize().getRowSpan() != targetArea.getInvolvedSpanSize().getRowSpan()) {
                return false;
            }
        } else if ((i != 3 && i != 4) || targetArea.getCornerPos().getStartCol() != targetArea.getInvolvedLoc().getStartCol() || tileConfig.getSize().getColSpan() != targetArea.getInvolvedSpanSize().getColSpan()) {
            return false;
        }
        if (!canUpdateInvolvedTiles(targetArea, i)) {
            return false;
        }
        int startCol = targetArea.getInvolvedLoc().getStartCol();
        int startRow = targetArea.getInvolvedLoc().getStartRow();
        int colSpan = targetArea.getInvolvedSpanSize().getColSpan();
        int rowSpan = targetArea.getInvolvedSpanSize().getRowSpan();
        if (i == 1) {
            zIsLocationUsed = isLocationUsed(LocCache.INSTANCE.loc(pageIndex, startCol + colSpan, startRow), SpanCache.INSTANCE.spanSize(1, rowSpan));
        } else if (i == 2) {
            zIsLocationUsed = isLocationUsed(LocCache.INSTANCE.loc(pageIndex, startCol - 1, startRow), SpanCache.INSTANCE.spanSize(1, rowSpan));
        } else if (i == 3) {
            zIsLocationUsed = isLocationUsed(LocCache.INSTANCE.loc(pageIndex, startCol, startRow + rowSpan), SpanCache.INSTANCE.spanSize(colSpan, 1));
        } else {
            if (i != 4) {
                return false;
            }
            zIsLocationUsed = isLocationUsed(LocCache.INSTANCE.loc(pageIndex, startCol, startRow - 1), SpanCache.INSTANCE.spanSize(colSpan, 1));
        }
        return !zIsLocationUsed;
    }

    public final boolean matchSmallAccordionSwapScene(TargetArea targetArea, TileConfig tileConfig, int i) {
        if (!canDraggingAreaIncludeTarget(targetArea, tileConfig)) {
            int startCol = targetArea.getCornerPos().getStartCol();
            int startRow = targetArea.getCornerPos().getStartRow();
            int startCol2 = targetArea.getInvolvedLoc().getStartCol();
            int startRow2 = targetArea.getInvolvedLoc().getStartRow();
            int colSpan = targetArea.getInvolvedSpanSize().getColSpan();
            int rowSpan = targetArea.getInvolvedSpanSize().getRowSpan();
            boolean z = startCol == startCol2 && tileConfig.getSize().getColSpan() == colSpan;
            boolean z2 = startRow == startRow2 && tileConfig.getSize().getRowSpan() == rowSpan;
            if (i == 1 ? !(!z2 || targetArea.getSize().getColSpan() + startCol != startCol2 + colSpan) : !(i == 2 ? !z2 || startCol != startCol2 : i == 3 ? !z || targetArea.getSize().getRowSpan() + startRow != startRow2 + rowSpan : i != 4 || !z || startRow != startRow2)) {
                if (canUpdateInvolvedTiles(targetArea, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void performBasicSwap(List<TileConfig> list, TileConfig tileConfig, TargetArea targetArea) {
        Loc location = tileConfig.getLocation();
        int iIndexOf = list.indexOf(tileConfig);
        if (iIndexOf == -1) {
            return;
        }
        if (targetArea.getInvolvedTiles().size() == 1 && Intrinsics.areEqual(CollectionsKt___CollectionsKt.first(targetArea.getInvolvedTiles()), tileConfig)) {
            list.set(iIndexOf, TileConfigKt.replaceLoc(tileConfig, targetArea.getCornerPos()));
            return;
        }
        int startCol = targetArea.getCornerPos().getStartCol() - location.getStartCol();
        int startRow = targetArea.getCornerPos().getStartRow() - location.getStartRow();
        for (TileConfig tileConfig2 : targetArea.getInvolvedTiles()) {
            int iIndexOf2 = list.indexOf(tileConfig2);
            Loc location2 = tileConfig2.getLocation();
            list.set(iIndexOf2, TileConfigKt.replaceLoc(tileConfig2, LocCache.INSTANCE.loc(location.getPageIndex(), location2.getStartCol() - startCol, location2.getStartRow() - startRow)));
        }
        list.set(iIndexOf, TileConfigKt.replaceLoc(tileConfig, targetArea.getCornerPos()));
    }

    public final void processBigAccordionSwap(List<TileConfig> list, TileConfig tileConfig, TargetArea targetArea, int i) {
        Loc loc;
        Loc location = tileConfig.getLocation();
        int iIndexOf = list.indexOf(tileConfig);
        for (TileConfig tileConfig2 : targetArea.getInvolvedTiles()) {
            int iIndexOf2 = list.indexOf(tileConfig2);
            Loc location2 = tileConfig2.getLocation();
            if (i == 1) {
                loc = LocCache.INSTANCE.loc(location2.getPageIndex(), location.getStartCol(), location2.getStartRow());
            } else if (i == 2) {
                loc = LocCache.INSTANCE.loc(location2.getPageIndex(), tileConfig.getSize().getColSpan() + location2.getStartCol(), location2.getStartRow());
            } else if (i == 3) {
                loc = LocCache.INSTANCE.loc(location2.getPageIndex(), location2.getStartCol(), location.getStartRow());
            } else if (i != 4) {
                loc = location2;
            } else {
                loc = LocCache.INSTANCE.loc(location2.getPageIndex(), location2.getStartCol(), tileConfig.getSize().getRowSpan() + location2.getStartRow());
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "processBigAccordionSwap: targetLoc=" + location2 + " newLoc=" + loc + " targetSize=" + tileConfig2.getSize(), null, 8);
            list.set(iIndexOf2, TileConfigKt.replaceLoc(tileConfig2, loc));
        }
        list.set(iIndexOf, TileConfigKt.replaceLoc(tileConfig, targetArea.getCornerPos()));
    }

    public final void processPageFullyOccupied(List<TileConfig> list, int i, int i2, TileConfig tileConfig, TileConfig tileConfig2, GridInfoEntity gridInfoEntity) {
        List<TileConfig> list2 = list;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list2) {
            if (((TileConfig) obj).getLocation().getPageIndex() == tileConfig2.getLocation().getPageIndex()) {
                arrayList.add(obj);
            }
        }
        boolean z = arrayList.size() == 1 && tileConfig2.getLocation().getPageIndex() > i;
        if (getPageCount(list) < 1 || z) {
            int i3 = i + 1;
            for (TileConfig tileConfig3 : list2) {
                if (tileConfig3.getLocation().getPageIndex() >= i3) {
                    list.set(getTileIndex(tileConfig3), TileConfigKt.replaceLoc(tileConfig3, LocCache.INSTANCE.loc(tileConfig3.getLocation().getPageIndex() + 1, tileConfig3.getLocation().getStartCol(), tileConfig3.getLocation().getStartRow())));
                }
            }
            list.set(i2, TileConfigKt.replaceLoc(tileConfig, LocCache.INSTANCE.loc(i3, 0, 0)));
            return;
        }
        for (int i4 = i - 1; -1 < i4; i4--) {
            int gridRow = gridInfoEntity.getGridRow();
            for (int i5 = 0; i5 < gridRow; i5++) {
                int gridCol = gridInfoEntity.getGridCol();
                for (int i6 = 0; i6 < gridCol; i6++) {
                    Loc loc = LocCache.INSTANCE.loc(i4, i6, i5);
                    if (!isLocationUsed(loc)) {
                        list.set(i2, TileConfigKt.replaceLoc(tileConfig, loc));
                        return;
                    }
                }
            }
        }
    }

    public final void processPushSwap(List<TileConfig> list, TileConfig tileConfig, TargetArea targetArea, int i) {
        List involvedTiles = targetArea.getInvolvedTiles();
        if (i == 1) {
            translateDraggingAndTargetCol(list, tileConfig, involvedTiles, 1);
            return;
        }
        if (i == 2) {
            translateDraggingAndTargetCol(list, tileConfig, involvedTiles, -1);
        } else if (i == 3) {
            translateDraggingAndTargetRow(list, tileConfig, involvedTiles, 1);
        } else {
            if (i != 4) {
                return;
            }
            translateDraggingAndTargetRow(list, tileConfig, involvedTiles, -1);
        }
    }

    public final void processSmallAccordionSwap(List<TileConfig> list, TileConfig tileConfig, TargetArea targetArea, int i) {
        Loc location = tileConfig.getLocation();
        list.set(list.indexOf(tileConfig), TileConfigKt.replaceLoc(tileConfig, targetArea.getCornerPos()));
        if (i == 1) {
            for (TileConfig tileConfig2 : targetArea.getInvolvedTiles()) {
                list.set(list.indexOf(tileConfig2), TileConfigKt.replaceLoc(tileConfig2, LocCache.INSTANCE.loc(location.getPageIndex(), tileConfig2.getLocation().getStartCol() - targetArea.getSize().getColSpan(), tileConfig2.getLocation().getStartRow())));
            }
            return;
        }
        if (i == 2) {
            for (TileConfig tileConfig3 : targetArea.getInvolvedTiles()) {
                list.set(list.indexOf(tileConfig3), TileConfigKt.replaceLoc(tileConfig3, LocCache.INSTANCE.loc(location.getPageIndex(), targetArea.getSize().getColSpan() + tileConfig3.getLocation().getStartCol(), tileConfig3.getLocation().getStartRow())));
            }
            return;
        }
        if (i == 3) {
            for (TileConfig tileConfig4 : targetArea.getInvolvedTiles()) {
                list.set(list.indexOf(tileConfig4), TileConfigKt.replaceLoc(tileConfig4, LocCache.INSTANCE.loc(location.getPageIndex(), tileConfig4.getLocation().getStartCol(), tileConfig4.getLocation().getStartRow() - targetArea.getSize().getRowSpan())));
            }
            return;
        }
        if (i != 4) {
            return;
        }
        for (TileConfig tileConfig5 : targetArea.getInvolvedTiles()) {
            list.set(list.indexOf(tileConfig5), TileConfigKt.replaceLoc(tileConfig5, LocCache.INSTANCE.loc(location.getPageIndex(), tileConfig5.getLocation().getStartCol(), targetArea.getSize().getRowSpan() + tileConfig5.getLocation().getStartRow())));
        }
    }

    public final void shiftTiles(List<TileConfig> list, TileConfig tileConfig, Loc loc, int i, boolean z) {
        int i2;
        SpanSize size;
        Loc location = tileConfig.getLocation();
        int locIndex = getLocIndex(location);
        int startRow = location.getStartRow();
        int locIndex2 = getLocIndex(loc);
        int startRow2 = loc.getStartRow();
        IntRange intRange = z ? new IntRange(startRow, startRow2, 1) : new IntProgression(startRow2, startRow, 1);
        IntRange intRange2 = z ? new IntRange(locIndex, locIndex2, 1) : new IntProgression(locIndex2, locIndex, 1);
        ArrayList<TileConfig> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int first = intRange.getFirst();
        int last = intRange.getLast();
        if (first <= last) {
            while (true) {
                GridInfoEntity gridInfoEntity = gridInfo;
                if (gridInfoEntity == null) {
                    gridInfoEntity = null;
                }
                int gridCol = gridInfoEntity.getGridCol();
                int i3 = 0;
                while (i3 < gridCol) {
                    Loc loc2 = LocCache.INSTANCE.loc(i, i3, first);
                    TileConfig locTile = getLocTile(loc2);
                    int locIndex3 = getLocIndex(loc2);
                    int first2 = intRange2.getFirst();
                    IntRange intRange3 = intRange2;
                    if (locIndex3 <= intRange3.getLast() && first2 <= locIndex3 && locTile != null && (size = locTile.getSize()) != null && SpanSizeKt.isOneXOne(size)) {
                        arrayList.add(locTile);
                        arrayList2.add(loc2);
                    }
                    i3++;
                    intRange2 = intRange3;
                }
                IntRange intRange4 = intRange2;
                if (first == last) {
                    break;
                }
                first++;
                intRange2 = intRange4;
            }
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "shiftTiles: targetLoc: " + location + " emptyPositionLoc: " + loc + " isBehind: " + z + " oneXOneTilesToMove:" + arrayList, null, 8);
        if (z) {
            arrayList2.add(loc);
            i2 = 0;
        } else {
            i2 = 0;
            arrayList2.add(0, loc);
        }
        if (z) {
            int i4 = i2;
            for (TileConfig tileConfig2 : arrayList) {
                i4++;
                list.set(getTileIndex(tileConfig2), TileConfigKt.replaceLoc(tileConfig2, (Loc) arrayList2.get(i4)));
            }
            return;
        }
        int i5 = i2;
        for (TileConfig tileConfig3 : arrayList) {
            list.set(getTileIndex(tileConfig3), TileConfigKt.replaceLoc(tileConfig3, (Loc) arrayList2.get(i5)));
            i5++;
        }
    }

    public final List<TileConfig> sortTilesByLocation(List<TileConfig> list) {
        final int i = 0;
        final int i2 = 1;
        final int i3 = 2;
        return CollectionsKt___CollectionsKt.sortedWith(list, ComparisonsKt__ComparisonsKt.compareBy(new Function1[]{new Function1() { // from class: com.oplus.systemui.plugins.qs.customize.data.algorithm.LocationAlgHelper$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                TileConfig tileConfig = (TileConfig) obj;
                switch (i) {
                    case 0:
                        return Integer.valueOf(tileConfig.getLocation().getPageIndex());
                    case 1:
                        return Integer.valueOf(tileConfig.getLocation().getStartRow());
                    default:
                        return Integer.valueOf(tileConfig.getLocation().getStartCol());
                }
            }
        }, new Function1() { // from class: com.oplus.systemui.plugins.qs.customize.data.algorithm.LocationAlgHelper$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                TileConfig tileConfig = (TileConfig) obj;
                switch (i2) {
                    case 0:
                        return Integer.valueOf(tileConfig.getLocation().getPageIndex());
                    case 1:
                        return Integer.valueOf(tileConfig.getLocation().getStartRow());
                    default:
                        return Integer.valueOf(tileConfig.getLocation().getStartCol());
                }
            }
        }, new Function1() { // from class: com.oplus.systemui.plugins.qs.customize.data.algorithm.LocationAlgHelper$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                TileConfig tileConfig = (TileConfig) obj;
                switch (i3) {
                    case 0:
                        return Integer.valueOf(tileConfig.getLocation().getPageIndex());
                    case 1:
                        return Integer.valueOf(tileConfig.getLocation().getStartRow());
                    default:
                        return Integer.valueOf(tileConfig.getLocation().getStartCol());
                }
            }
        }}));
    }

    public static final void updateLocUseInfo(TileConfig tileConfig, Loc loc) {
        TileConfig[] tileConfigArr;
        TileConfig locTile = getLocTile(loc);
        if (tileConfig != null && locTile == null) {
            addPageUseInfo(loc.getPageIndex());
        }
        TileConfig[][] tileConfigArr2 = pageTileInfos[loc.getPageIndex()];
        if (tileConfigArr2 == null || (tileConfigArr = tileConfigArr2[loc.getStartRow()]) == null) {
            return;
        }
        tileConfigArr[loc.getStartCol()] = tileConfig;
    }

    public final boolean isLocationUsed(Loc loc) {
        return loc.getPageIndex() < pageTileInfos.length && getLocTile(loc) != null;
    }
}
