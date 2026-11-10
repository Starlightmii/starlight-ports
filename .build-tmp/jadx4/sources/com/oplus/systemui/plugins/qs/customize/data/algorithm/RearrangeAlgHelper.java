package com.oplus.systemui.plugins.qs.customize.data.algorithm;

import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.runtime.collection.MutableVectorKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfigKt;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.LocCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.model.SpanSizeKt;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RearrangeAlgHelper {
    private static final String TAG = "RearrangeAlgHelper";
    public static final RearrangeAlgHelper INSTANCE = new RearrangeAlgHelper();
    private static final LinkedList<Loc> emptyPosition = new LinkedList<>();

    public static void addTilesToEnd(List list, GridInfoEntity gridInfoEntity, List list2, List list3) {
        int i;
        TileConfig tileConfig;
        int i2;
        int i3;
        TileConfig tileConfig2;
        int i4;
        int i5;
        TileConfig tileConfig3 = (TileConfig) CollectionsKt___CollectionsKt.last(list);
        int pageIndex = tileConfig3.getLocation().getPageIndex();
        int startRow = tileConfig3.getLocation().getStartRow();
        int startCol = tileConfig3.getLocation().getStartCol();
        ArrayList arrayList = new ArrayList();
        Iterator it = list2.iterator();
        boolean z = false;
        while (it.hasNext()) {
            TileConfig tileConfig4 = (TileConfig) it.next();
            ArrayList arrayList2 = (ArrayList) list3;
            int size = arrayList2.size();
            int i6 = pageIndex;
            while (i6 < size) {
                boolean[][] zArr = (boolean[][]) arrayList2.get(i6);
                int gridRow = gridInfoEntity.getGridRow();
                boolean z2 = z;
                int i7 = startRow;
                while (true) {
                    if (startRow >= gridRow) {
                        i3 = size;
                        tileConfig2 = tileConfig4;
                        startRow = i7;
                        break;
                    }
                    int gridCol = gridInfoEntity.getGridCol();
                    int i8 = startCol;
                    while (true) {
                        if (startCol >= gridCol) {
                            i3 = size;
                            i4 = gridRow;
                            tileConfig2 = tileConfig4;
                            i5 = startRow;
                            break;
                        }
                        int i9 = startRow;
                        int i10 = startCol == gridInfoEntity.getGridCol() + (-1) ? 0 : startCol;
                        INSTANCE.getClass();
                        i4 = gridRow;
                        int i11 = startCol;
                        i3 = size;
                        boolean zCanPlaceCell = canPlaceCell(tileConfig4, gridInfoEntity, zArr, i6, i11, i9);
                        tileConfig2 = tileConfig4;
                        i5 = i9;
                        if (zCanPlaceCell) {
                            placeCell(tileConfig2, zArr, i11, i5);
                            Loc loc = LocCache.INSTANCE.loc(i6, i11, i5);
                            list.add(TileConfigKt.replaceLoc(tileConfig2, loc));
                            i8 = i10;
                            LogUtil.i$default(LogUtil.TAG_QS, TAG, "add tile: " + tileConfig2.getTileSpec() + ", loc: " + loc + ", tile: " + tileConfig2, null, 8);
                            z2 = true;
                            break;
                        }
                        i8 = i10;
                        startCol = i11 + 1;
                        startRow = i5;
                        tileConfig4 = tileConfig2;
                        size = i3;
                        gridRow = i4;
                    }
                    startCol = i8;
                    if (z2) {
                        startRow = i5;
                        break;
                    }
                    startRow = i5 + 1;
                    i7 = i5;
                    tileConfig4 = tileConfig2;
                    size = i3;
                    gridRow = i4;
                }
                z = z2;
                if (z) {
                    break;
                }
                if (pageIndex < 0) {
                    int gridCol2 = gridInfoEntity.getGridCol();
                    boolean[][] zArr2 = new boolean[gridCol2][];
                    for (int i12 = 0; i12 < gridCol2; i12++) {
                        int gridRow2 = gridInfoEntity.getGridRow();
                        boolean[] zArr3 = new boolean[gridRow2];
                        for (int i13 = 0; i13 < gridRow2; i13++) {
                            zArr3[i13] = false;
                        }
                        zArr2[i12] = zArr3;
                    }
                    list3.add(zArr2);
                    pageIndex++;
                    list.add(TileConfigKt.replaceLoc(tileConfig2, LocCache.INSTANCE.loc(pageIndex, 0, 0)));
                    RearrangeAlgHelper rearrangeAlgHelper = INSTANCE;
                    boolean[][] zArr4 = (boolean[][]) arrayList2.get(pageIndex);
                    rearrangeAlgHelper.getClass();
                    placeCell(tileConfig2, zArr4, 0, 0);
                    startCol = 0;
                    startRow = 0;
                } else {
                    LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "unplaced tile " + tileConfig2 + " ,currentPageIndex " + pageIndex);
                    arrayList.add(tileConfig2);
                }
                i6++;
                tileConfig4 = tileConfig2;
                size = i3;
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        int size2 = arrayList.size();
        boolean z3 = false;
        int i14 = 0;
        while (i14 < size2) {
            Object obj = arrayList.get(i14);
            i14++;
            TileConfig tileConfig5 = (TileConfig) obj;
            int i15 = 0;
            while (-1 < i15) {
                int gridRow3 = gridInfoEntity.getGridRow();
                int i16 = 0;
                while (true) {
                    if (i16 >= gridRow3) {
                        i = i15;
                        tileConfig = tileConfig5;
                        break;
                    }
                    int gridCol3 = gridInfoEntity.getGridCol();
                    int i17 = 0;
                    while (true) {
                        if (i17 >= gridCol3) {
                            i = i15;
                            i2 = i16;
                            tileConfig = tileConfig5;
                            break;
                        }
                        RearrangeAlgHelper rearrangeAlgHelper2 = INSTANCE;
                        boolean[][] zArr5 = (boolean[][]) ((ArrayList) list3).get(i15);
                        rearrangeAlgHelper2.getClass();
                        int i18 = i15;
                        int i19 = i16;
                        int i20 = i17;
                        tileConfig = tileConfig5;
                        i = i18;
                        i2 = i19;
                        if (canPlaceCell(tileConfig5, gridInfoEntity, zArr5, i18, i20, i19)) {
                            list.add(TileConfigKt.replaceLoc(tileConfig, LocCache.INSTANCE.loc(i, i20, i2)));
                            z3 = true;
                            break;
                        } else {
                            i17 = i20 + 1;
                            i15 = i;
                            tileConfig5 = tileConfig;
                            i16 = i2;
                        }
                    }
                    if (z3) {
                        break;
                    }
                    tileConfig5 = tileConfig;
                    i16 = i2 + 1;
                    i15 = i;
                }
                if (z3) {
                    break;
                }
                i15 = i - 1;
                tileConfig5 = tileConfig;
            }
        }
    }

    public static boolean canPlaceCell(TileConfig tileConfig, GridInfoEntity gridInfoEntity, boolean[][] zArr, int i, int i2, int i3) {
        int i4;
        int rowSpan = tileConfig.getSize().getRowSpan();
        int colSpan = tileConfig.getSize().getColSpan() + i2;
        if (colSpan <= gridInfoEntity.getGridCol() && (i4 = rowSpan + i3) <= gridInfoEntity.getGridRow()) {
            while (i3 < i4) {
                for (int i5 = i2; i5 < colSpan; i5++) {
                    if (zArr[i5][i3]) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", "RearrangeAlgHelper-->".concat(MutableVectorKt$.ExternalSyntheticOutline0.m("placeCell occupied col,row: (", i5, i3, OplusPreventModeData.SEPARATOR, ")")));
                            return false;
                        }
                    }
                }
                i3++;
            }
            return true;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("placeCell start col,row: (", i2, i3, OplusPreventModeData.SEPARATOR, "), out of grid: ");
            sbM.append(tileConfig);
            Log.d("SystemUi--QuickSettings", "RearrangeAlgHelper-->".concat(sbM.toString()));
        }
        if (!zArr[i2][i3]) {
            emptyPosition.add(LocCache.INSTANCE.loc(i, i2, i3));
        }
        return false;
    }

    public static void placeCell(TileConfig tileConfig, boolean[][] zArr, int i, int i2) {
        int rowSpan = tileConfig.getSize().getRowSpan() + i2;
        while (i2 < rowSpan) {
            int colSpan = tileConfig.getSize().getColSpan() + i;
            for (int i3 = i; i3 < colSpan; i3++) {
                zArr[i3][i2] = true;
            }
            i2++;
        }
    }

    /* JADX WARN: Code duplicated, block: B:49:0x0162  */
    /* JADX WARN: Code duplicated, block: B:51:0x0177  */
    /* JADX WARN: Code duplicated, block: B:53:0x018a  */
    /* JADX WARN: Code duplicated, block: B:55:0x0199  */
    /* JADX WARN: Code duplicated, block: B:56:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:59:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:62:0x020c A[LOOP:10: B:57:0x01df->B:62:0x020c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:65:0x0218  */
    /* JADX WARN: Code duplicated, block: B:68:0x0222 A[LOOP:9: B:48:0x0160->B:68:0x0222, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:71:0x0234  */
    /* JADX WARN: Code duplicated, block: B:88:0x0237 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x021e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x022c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x0210 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x0200 A[SYNTHETIC] */
    public final List<TileConfig> addTilesByIndex$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(List<TileConfig> list, GridInfoEntity gridInfoEntity, List<TileConfig> list2, int i) {
        int size;
        int i2;
        boolean z;
        Iterator it;
        TileConfig tileConfig;
        GridInfoEntity gridInfoEntity2;
        TileConfig tileConfig2;
        int iIndexOf;
        int pageIndex;
        boolean[][] zArr;
        int startCol;
        int startRow;
        Iterator<SpanSize> it2;
        TileConfig tileConfigReplaceLoc;
        TileConfig tileConfigReplaceSize;
        GridInfoEntity gridInfoEntity3 = gridInfoEntity;
        int pageIndex2 = ((TileConfig) CollectionsKt___CollectionsKt.last(list)).getLocation().getPageIndex() + 1;
        ArrayList arrayList = new ArrayList(pageIndex2);
        int i3 = 0;
        for (int i4 = 0; i4 < pageIndex2; i4++) {
            int gridCol = gridInfoEntity3.getGridCol();
            boolean[][] zArr2 = new boolean[gridCol][];
            for (int i5 = 0; i5 < gridCol; i5++) {
                int gridRow = gridInfoEntity3.getGridRow();
                boolean[] zArr3 = new boolean[gridRow];
                for (int i6 = 0; i6 < gridRow; i6++) {
                    zArr3[i6] = false;
                }
                zArr2[i5] = zArr3;
            }
            arrayList.add(zArr2);
        }
        for (TileConfig tileConfig3 : list) {
            RearrangeAlgHelper rearrangeAlgHelper = INSTANCE;
            boolean[][] zArr4 = (boolean[][]) arrayList.get(tileConfig3.getLocation().getPageIndex());
            int startCol2 = tileConfig3.getLocation().getStartCol();
            int startRow2 = tileConfig3.getLocation().getStartRow();
            rearrangeAlgHelper.getClass();
            placeCell(tileConfig3, zArr4, startCol2, startRow2);
        }
        if (CollectionsKt__CollectionsKt.getLastIndex(list) == i) {
            addTilesToEnd(list, gridInfoEntity3, list2, arrayList);
            return list;
        }
        ArrayList arrayList2 = new ArrayList();
        Loc location = list.get(i).getLocation();
        int pageIndex3 = location.getPageIndex();
        ArrayList arrayList3 = new ArrayList();
        if (pageIndex3 >= 0) {
            int i7 = 0;
            loop4: while (true) {
                boolean[][] zArr5 = (boolean[][]) arrayList.get(i7);
                int gridRow2 = gridInfoEntity3.getGridRow();
                int i8 = i3;
                while (i8 < gridRow2) {
                    int gridCol2 = gridInfoEntity3.getGridCol();
                    for (int i9 = i3; i9 < gridCol2; i9++) {
                        LocCache locCache = LocCache.INSTANCE;
                        if (Intrinsics.areEqual(locCache.loc(i7, i9, i8), location)) {
                            break loop4;
                        }
                        if (!zArr5[i9][i8]) {
                            arrayList3.add(locCache.loc(i7, i9, i8));
                        }
                    }
                    i8++;
                    i3 = 0;
                }
                if (i7 == pageIndex3) {
                    break;
                }
                i7++;
                i3 = 0;
            }
        }
        Iterator it3 = list2.iterator();
        int i10 = i;
        int i11 = 0;
        while (it3.hasNext()) {
            TileConfig tileConfig4 = (TileConfig) it3.next();
            INSTANCE.getClass();
            if (arrayList3.isEmpty()) {
                size = list.size();
                i2 = i10;
                z = false;
                while (true) {
                    if (i10 < size) {
                        it = it3;
                        tileConfig = tileConfig4;
                        gridInfoEntity2 = gridInfoEntity;
                        i10 = i2;
                        break;
                    }
                    tileConfig2 = list.get(i10);
                    if (Intrinsics.areEqual(tileConfig4.getSize(), tileConfig2.getSize())) {
                        list.set(i10, TileConfigKt.replaceLoc(tileConfig4, tileConfig2.getLocation()));
                    } else {
                        iIndexOf = tileConfig4.getSupportSizes().indexOf(tileConfig2.getSize());
                        if (iIndexOf != -1) {
                            list.set(i10, TileConfigKt.replaceLoc(TileConfigKt.replaceSize(tileConfig4, tileConfig4.getSupportSizes().get(iIndexOf)), tileConfig2.getLocation()));
                        } else {
                            INSTANCE.getClass();
                            pageIndex = tileConfig2.getLocation().getPageIndex();
                            zArr = (boolean[][]) arrayList.get(pageIndex);
                            startCol = tileConfig2.getLocation().getStartCol();
                            startRow = tileConfig2.getLocation().getStartRow();
                            it2 = tileConfig4.getSupportSizes().iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    it = it3;
                                    tileConfig = tileConfig4;
                                    gridInfoEntity2 = gridInfoEntity;
                                    tileConfigReplaceLoc = null;
                                    break;
                                }
                                tileConfigReplaceSize = TileConfigKt.replaceSize(tileConfig4, it2.next());
                                it = it3;
                                tileConfig = tileConfig4;
                                gridInfoEntity2 = gridInfoEntity;
                                if (canPlaceCell(tileConfigReplaceSize, gridInfoEntity, zArr, pageIndex, startCol, startRow)) {
                                    tileConfigReplaceLoc = TileConfigKt.replaceLoc(tileConfigReplaceSize, tileConfig2.getLocation());
                                    placeCell(tileConfigReplaceLoc, zArr, startCol, startRow);
                                    break;
                                }
                                tileConfig4 = tileConfig;
                                it3 = it;
                            }
                            if (tileConfigReplaceLoc != null) {
                                list.set(i10, tileConfigReplaceLoc);
                                z = true;
                            }
                        }
                        if (z) {
                            arrayList2.add(tileConfig2);
                            break;
                        }
                        int i12 = i10;
                        i10++;
                        i2 = i12;
                        tileConfig4 = tileConfig;
                        it3 = it;
                    }
                    it = it3;
                    z = true;
                    tileConfig = tileConfig4;
                    gridInfoEntity2 = gridInfoEntity;
                    if (z) {
                        arrayList2.add(tileConfig2);
                        break;
                    }
                    int i13 = i10;
                    i10++;
                    i2 = i13;
                    tileConfig4 = tileConfig;
                    it3 = it;
                }
                if (!z) {
                    arrayList2.add(tileConfig);
                }
            } else {
                int size2 = arrayList3.size();
                int i14 = i11;
                while (true) {
                    if (i14 < size2) {
                        Loc loc = (Loc) arrayList3.get(i14);
                        if (canPlaceCell(tileConfig4, gridInfoEntity3, (boolean[][]) arrayList.get(loc.getPageIndex()), loc.getPageIndex(), loc.getStartCol(), loc.getStartRow())) {
                            TileConfig tileConfigReplaceLoc2 = TileConfigKt.replaceLoc(tileConfig4, loc);
                            list.add(tileConfigReplaceLoc2);
                            placeCell(tileConfigReplaceLoc2, (boolean[][]) arrayList.get(loc.getPageIndex()), loc.getStartCol(), loc.getStartRow());
                            i11++;
                            gridInfoEntity2 = gridInfoEntity;
                            it = it3;
                        } else {
                            i14++;
                            gridInfoEntity3 = gridInfoEntity;
                        }
                    } else {
                        size = list.size();
                        i2 = i10;
                        z = false;
                        while (true) {
                            if (i10 < size) {
                                it = it3;
                                tileConfig = tileConfig4;
                                gridInfoEntity2 = gridInfoEntity;
                                i10 = i2;
                                break;
                            }
                            tileConfig2 = list.get(i10);
                            if (Intrinsics.areEqual(tileConfig4.getSize(), tileConfig2.getSize())) {
                                list.set(i10, TileConfigKt.replaceLoc(tileConfig4, tileConfig2.getLocation()));
                            } else {
                                iIndexOf = tileConfig4.getSupportSizes().indexOf(tileConfig2.getSize());
                                if (iIndexOf != -1) {
                                    list.set(i10, TileConfigKt.replaceLoc(TileConfigKt.replaceSize(tileConfig4, tileConfig4.getSupportSizes().get(iIndexOf)), tileConfig2.getLocation()));
                                } else {
                                    INSTANCE.getClass();
                                    pageIndex = tileConfig2.getLocation().getPageIndex();
                                    zArr = (boolean[][]) arrayList.get(pageIndex);
                                    startCol = tileConfig2.getLocation().getStartCol();
                                    startRow = tileConfig2.getLocation().getStartRow();
                                    it2 = tileConfig4.getSupportSizes().iterator();
                                    while (true) {
                                        if (it2.hasNext()) {
                                            it = it3;
                                            tileConfig = tileConfig4;
                                            gridInfoEntity2 = gridInfoEntity;
                                            tileConfigReplaceLoc = null;
                                            break;
                                        }
                                        tileConfigReplaceSize = TileConfigKt.replaceSize(tileConfig4, it2.next());
                                        it = it3;
                                        tileConfig = tileConfig4;
                                        gridInfoEntity2 = gridInfoEntity;
                                        if (canPlaceCell(tileConfigReplaceSize, gridInfoEntity, zArr, pageIndex, startCol, startRow)) {
                                            tileConfigReplaceLoc = TileConfigKt.replaceLoc(tileConfigReplaceSize, tileConfig2.getLocation());
                                            placeCell(tileConfigReplaceLoc, zArr, startCol, startRow);
                                            break;
                                        }
                                        tileConfig4 = tileConfig;
                                        it3 = it;
                                    }
                                    if (tileConfigReplaceLoc != null) {
                                        list.set(i10, tileConfigReplaceLoc);
                                        z = true;
                                    }
                                }
                                if (z) {
                                    arrayList2.add(tileConfig2);
                                    break;
                                }
                                int i15 = i10;
                                i10++;
                                i2 = i15;
                                tileConfig4 = tileConfig;
                                it3 = it;
                            }
                            it = it3;
                            z = true;
                            tileConfig = tileConfig4;
                            gridInfoEntity2 = gridInfoEntity;
                            if (z) {
                                arrayList2.add(tileConfig2);
                                break;
                            }
                            int i16 = i10;
                            i10++;
                            i2 = i16;
                            tileConfig4 = tileConfig;
                            it3 = it;
                        }
                        if (!z) {
                            arrayList2.add(tileConfig);
                        }
                    }
                }
            }
            it3 = it;
            gridInfoEntity3 = gridInfoEntity2;
        }
        addTilesToEnd(list, gridInfoEntity3, arrayList2, arrayList);
        return list;
    }

    public final List<TileConfig> adjustPosition$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(List<TileConfig> list, GridInfoEntity gridInfoEntity) {
        int i;
        boolean[][] zArr;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(list.size(), "adjustPosition start, allocate tiles size "), null, 8);
        ArrayList arrayList = new ArrayList();
        List<TileConfig> list2 = list;
        Iterator<T> it = list2.iterator();
        if (!it.hasNext()) {
            Regex$.ExternalSyntheticBUOutline0.m();
            return null;
        }
        TileConfig tileConfig = (TileConfig) it.next();
        int rowSpan = tileConfig.getSize().getRowSpan() + tileConfig.getLocation().getStartRow();
        while (it.hasNext()) {
            TileConfig tileConfig2 = (TileConfig) it.next();
            int rowSpan2 = tileConfig2.getSize().getRowSpan() + tileConfig2.getLocation().getStartRow();
            if (rowSpan < rowSpan2) {
                rowSpan = rowSpan2;
            }
        }
        ArrayList arrayList2 = new ArrayList(1);
        int gridCol = gridInfoEntity.getGridCol();
        boolean[][] zArr2 = new boolean[gridCol][];
        for (int i2 = 0; i2 < gridCol; i2++) {
            boolean[] zArr3 = new boolean[rowSpan];
            for (int i3 = 0; i3 < rowSpan; i3++) {
                zArr3[i3] = false;
            }
            zArr2[i2] = zArr3;
        }
        arrayList2.add(zArr2);
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (TileConfig tileConfig3 : list2) {
            if (SpanSizeKt.isOneXOne(tileConfig3.getSize())) {
                int i7 = i4;
                boolean z = false;
                while (i4 < 1) {
                    boolean[][] zArr4 = (boolean[][]) arrayList2.get(i4);
                    int i8 = i6;
                    boolean z2 = z;
                    int i9 = i5;
                    while (true) {
                        if (i5 >= rowSpan) {
                            i = i4;
                            i5 = i9;
                            break;
                        }
                        int gridCol2 = gridInfoEntity.getGridCol();
                        int i10 = i8;
                        while (true) {
                            if (i8 >= gridCol2) {
                                i = i4;
                                zArr = zArr4;
                                break;
                            }
                            int i11 = i8 == gridInfoEntity.getGridCol() - 1 ? 0 : i8;
                            INSTANCE.getClass();
                            i = i4;
                            int i12 = i8;
                            zArr = zArr4;
                            if (canPlaceCell(tileConfig3, gridInfoEntity, zArr4, i, i12, i5)) {
                                placeCell(tileConfig3, zArr, i12, i5);
                                arrayList.add(TileConfigKt.replaceLoc(tileConfig3, LocCache.INSTANCE.loc(i7, i12, i5)));
                                z2 = true;
                                i10 = i11;
                                break;
                            }
                            zArr4 = zArr;
                            i10 = i11;
                            i8 = i12 + 1;
                            i4 = i;
                        }
                        if (z2) {
                            i8 = i10;
                            break;
                        }
                        i9 = i5;
                        zArr4 = zArr;
                        i8 = i10;
                        i5++;
                        i4 = i;
                    }
                    z = z2;
                    if (z) {
                        i6 = i8;
                        break;
                    }
                    if (i7 < 1) {
                        i7++;
                        i5 = 0;
                        i6 = 0;
                    } else {
                        i6 = i8;
                    }
                    i4 = i + 1;
                }
                i4 = i7;
            } else {
                arrayList.add(tileConfig3);
                RearrangeAlgHelper rearrangeAlgHelper = INSTANCE;
                boolean[][] zArr5 = (boolean[][]) arrayList2.get(tileConfig3.getLocation().getPageIndex());
                int startCol = tileConfig3.getLocation().getStartCol();
                int startRow = tileConfig3.getLocation().getStartRow();
                rearrangeAlgHelper.getClass();
                placeCell(tileConfig3, zArr5, startCol, startRow);
            }
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(arrayList.size(), i4, "adjustPosition end, allocate tiles size ", " ,currentPage "), null, 8);
        return arrayList;
    }

    public final List<TileConfig> allocatePosition$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(List<TileConfig> list, GridInfoEntity gridInfoEntity) {
        int i;
        int i2;
        int i3;
        List<TileConfig> list2 = list;
        LogUtil.IDebug iDebug = null;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(list2.size(), "allocatePosition start, allocate tiles size "), null, 8);
        ArrayList arrayList = new ArrayList();
        int i4 = 1;
        ArrayList arrayList2 = new ArrayList(1);
        int gridCol = gridInfoEntity.getGridCol();
        boolean[][] zArr = new boolean[gridCol][];
        int i5 = 0;
        for (int i6 = 0; i6 < gridCol; i6++) {
            int gridRow = gridInfoEntity.getGridRow();
            boolean[] zArr2 = new boolean[gridRow];
            for (int i7 = 0; i7 < gridRow; i7++) {
                zArr2[i7] = false;
            }
            zArr[i6] = zArr2;
        }
        arrayList2.add(zArr);
        emptyPosition.clear();
        Iterator it = list2.iterator();
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            int i12 = i9 + 1;
            if (i9 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            TileConfig tileConfig = (TileConfig) next;
            int i13 = i5;
            int i14 = i11;
            int i15 = i10;
            int i16 = i8;
            while (true) {
                if (i8 < i4) {
                    boolean[][] zArr3 = (boolean[][]) arrayList2.get(i8);
                    INSTANCE.getClass();
                    int i17 = i4;
                    if (SpanSizeKt.isOneXOne(tileConfig.getSize())) {
                        LinkedList<Loc> linkedList = emptyPosition;
                        if (!linkedList.isEmpty()) {
                            TileConfig tileConfigReplaceLoc = TileConfigKt.replaceLoc(tileConfig, linkedList.removeFirst());
                            String tileSpec = tileConfigReplaceLoc.getTileSpec();
                            Loc location = tileConfigReplaceLoc.getLocation();
                            SpanSize size = tileConfigReplaceLoc.getSize();
                            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(i9, "allocatePosition allocate Loc tile[", "]-- ", tileSpec, ", ");
                            sbM.append(location);
                            sbM.append(OplusPreventModeData.SEPARATOR);
                            sbM.append(size);
                            sbM.append(" ");
                            LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
                            arrayList.add(tileConfigReplaceLoc);
                            i = i17;
                        }
                    }
                    arrayList2 = arrayList2;
                    it = it;
                    i15 = i15;
                    int gridRow2 = gridInfoEntity.getGridRow();
                    int i18 = i13;
                    int i19 = i15;
                    while (true) {
                        if (i19 >= gridRow2) {
                            arrayList = arrayList;
                            i2 = i8;
                            break;
                        }
                        int i20 = gridRow2;
                        int gridCol2 = gridInfoEntity.getGridCol();
                        int i21 = i18;
                        int i22 = i14;
                        while (true) {
                            if (i22 >= gridCol2) {
                                int i23 = i19;
                                i2 = i8;
                                i3 = i23;
                                i18 = i21;
                                break;
                            }
                            int i24 = gridCol2;
                            int i25 = i22 == gridInfoEntity.getGridCol() + (-1) ? 0 : i22;
                            INSTANCE.getClass();
                            int i26 = i22;
                            int i27 = i8;
                            int i28 = i19;
                            i2 = i27;
                            i3 = i28;
                            i14 = i25;
                            boolean[][] zArr4 = zArr3;
                            if (canPlaceCell(tileConfig, gridInfoEntity, zArr3, i27, i26, i28)) {
                                placeCell(tileConfig, zArr4, i26, i3);
                                zArr3 = zArr4;
                                Loc loc = LocCache.INSTANCE.loc(i16, i26, i3);
                                arrayList.add(TileConfigKt.replaceLoc(tileConfig, loc));
                                String tileSpec2 = tileConfig.getTileSpec();
                                SpanSize size2 = list2.get(i9).getSize();
                                StringBuilder sbM2 = KeyguardLogger$.ExternalSyntheticOutline0.m(i9, "allocatePosition allocate Loc tile[", "]: ", tileSpec2, ", ");
                                sbM2.append(loc);
                                sbM2.append(OplusPreventModeData.SEPARATOR);
                                sbM2.append(size2);
                                sbM2.append(" ");
                                LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM2.toString(), null, 8);
                                i18 = i17;
                                break;
                            }
                            zArr3 = zArr4;
                            i22 = i26 + 1;
                            i19 = i3;
                            i8 = i2;
                            list2 = list;
                            gridCol2 = i24;
                        }
                        if (i18 != 0) {
                            i15 = i3;
                            break;
                        }
                        int i29 = i3 + 1;
                        i15 = i3;
                        i8 = i2;
                        arrayList = arrayList;
                        gridRow2 = i20;
                        i19 = i29;
                        list2 = list;
                    }
                    if (i18 != 0) {
                        i = i17;
                        break;
                    }
                    if (i16 < i17) {
                        i16++;
                        i14 = 0;
                        i15 = 0;
                    }
                    i8 = i2 + 1;
                    i4 = i17;
                    arrayList = arrayList;
                    i15 = i15;
                    arrayList2 = arrayList2;
                    list2 = list;
                    i13 = i18;
                    it = it;
                } else {
                    i = i4;
                }
                arrayList = arrayList;
                break;
            }
            i11 = i14;
            i4 = i;
            i8 = i16;
            i9 = i12;
            arrayList = arrayList;
            i10 = i15;
            arrayList2 = arrayList2;
            it = it;
            iDebug = null;
            i5 = 0;
            list2 = list;
        }
        ArrayList arrayList3 = arrayList;
        emptyPosition.clear();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(arrayList3.size(), i8, "allocatePosition end, allocate tiles size ", " ,currentPage "), iDebug, 8);
        return arrayList3;
    }
}
