package com.oplus.systemui.plugins.qs.customize.data.algorithm;

import android.util.Log;
import androidx.car.app.SurfaceContainer$;
import androidx.collection.FloatList$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.ui.spatial.RectList$;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.EarDrawableShaderStringKt;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.LocCache;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.Regex$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LandscapeMappingAlg implements ILocationAlg {
    public static final Companion Companion = new Companion();
    private static final String TAG = "LandscapeMappingAlg";
    public int firstBestSplitRow;
    public int landscapeAEndCol;
    public int landscapeBEndCol;
    public int landscapeRowSize;
    public int secondBestSplitRow;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static boolean isCrossRow(int i, int i2, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            TileConfig tileConfig = (TileConfig) it.next();
            if (tileConfig.getLocation().getStartRow() > i) {
                return false;
            }
            if (tileConfig.getSize().getRowSpan() + tileConfig.getLocation().getStartRow() > i + i2) {
                return true;
            }
        }
        return false;
    }

    public final List directSplitPage(int i, int i2, int i3, List list) {
        int i4;
        int i5;
        List<TileConfig> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        for (TileConfig tileConfig : list2) {
            Loc location = tileConfig.getLocation();
            if (tileConfig.getSize().getRowSpan() + location.getStartRow() > i2 + 1) {
                i4 = this.landscapeAEndCol;
                i5 = -(i3 + 1);
            } else {
                i4 = 0;
                i5 = 0;
            }
            int iCoerceIn = RangesKt___RangesKt.coerceIn(location.getStartRow() + i5, 0, this.landscapeRowSize - 1);
            arrayList.add(TileConfig.copy$default(tileConfig, null, null, LocCache.INSTANCE.loc(location.getPageIndex() + i, RangesKt___RangesKt.coerceIn(location.getStartCol() + i4, 0, this.landscapeBEndCol - 1), iCoerceIn), null, 11));
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:50:0x01fb A[PHI: r6
      0x01fb: PHI (r6v7 int) = (r6v6 int), (r6v8 int) binds: [B:53:0x0201, B:49:0x01f9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:64:0x028a  */
    /* JADX WARN: Code duplicated, block: B:65:0x028d  */
    /* JADX WARN: Code duplicated, block: B:68:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:72:0x02c1  */
    /* JADX WARN: Code duplicated, block: B:73:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:87:0x02bd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x029f A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v0, types: [java.util.List<com.oplus.systemui.plugins.qs.customize.data.model.TileConfig>] */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16, types: [java.lang.Number] */
    /* JADX WARN: Type inference failed for: r2v18 */
    @Override // com.oplus.systemui.plugins.qs.customize.data.algorithm.ILocationAlg
    public List<TileConfig> process(LandscapeInputData landscapeInputData) {
        LogUtil.IDebug iDebug;
        String str;
        String str2;
        int i;
        int i2;
        List listDirectSplitPage;
        int i3;
        int i4;
        Iterator it;
        ?? ValueOf;
        Integer numValueOf;
        int iIntValue;
        String str3 = LogUtil.TAG_QS;
        String str4 = TAG;
        LogUtil.IDebug iDebug2 = null;
        int i5 = 8;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, EarDrawableShaderStringKt.EAR_DRAWABLE_SHADER_UNIFORM_PROCESS, null, 8);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "initGridInfo: gridInfo = " + landscapeInputData.getGridInfo() + " , dstGridInfo = " + landscapeInputData.getDstGridInfo(), null, 8);
        this.landscapeRowSize = landscapeInputData.getDstGridInfo().getGridRow();
        this.landscapeAEndCol = landscapeInputData.getGridInfo().getGridCol();
        this.landscapeBEndCol = landscapeInputData.getDstGridInfo().getGridCol();
        int i6 = 1;
        this.firstBestSplitRow = landscapeInputData.getDstGridInfo().getGridRow() - 1;
        int gridRow = (landscapeInputData.getGridInfo().getGridRow() - landscapeInputData.getDstGridInfo().getGridRow()) - 1;
        this.secondBestSplitRow = gridRow;
        int i7 = this.landscapeRowSize;
        int i8 = this.landscapeAEndCol;
        int i9 = this.landscapeBEndCol;
        int i10 = this.firstBestSplitRow;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("initGridInfo : [", i7, i8, ", ", ", ");
        RectList$.ExternalSyntheticOutline0.m(i9, i10, "],[", ", ", sbM);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, SurfaceContainer$.ExternalSyntheticOutline0.m(gridRow, " ]", sbM), null, 8);
        ArrayList arrayList = new ArrayList();
        List<TileConfig> tiles = landscapeInputData.getTiles();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : tiles) {
            Integer numValueOf2 = Integer.valueOf(((TileConfig) obj).getLocation().getPageIndex());
            Object arrayList2 = linkedHashMap.get(numValueOf2);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                linkedHashMap.put(numValueOf2, arrayList2);
            }
            ((List) arrayList2).add(obj);
        }
        int i11 = 0;
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            int iIntValue2 = ((Number) entry.getKey()).intValue();
            List list = (List) entry.getValue();
            LogUtil.i$default(str3, str4, TextFieldBuffer$.ExternalSyntheticOutline0.m(iIntValue2, i11, "start reorder for Page: pageIndex = ", ", newPage= "), iDebug2, i5);
            if (!isCrossRow(this.firstBestSplitRow, i6, list)) {
                LogUtil.i$default(str3, str4, " not cross fourth row, directSplitPage A4*B3 ", iDebug2, i5);
                int i12 = this.firstBestSplitRow;
                listDirectSplitPage = directSplitPage(i11, i12, i12, list);
            } else if (isCrossRow(this.secondBestSplitRow, i6, list)) {
                if (isCrossRow(this.secondBestSplitRow, 2, list)) {
                    LogUtil.i$default(str3, str4, "have cross multiple row ", iDebug2, i5);
                    LogUtil.i$default(str3, str4, "reorderWithLargeTile", iDebug2, i5);
                    List<TileConfig> list2 = list;
                    Iterator it2 = list2.iterator();
                    if (!it2.hasNext()) {
                        ?? r18 = iDebug2;
                        Regex$.ExternalSyntheticBUOutline0.m();
                        return r18;
                    }
                    Object next = it2.next();
                    if (it2.hasNext()) {
                        int rowSpan = ((TileConfig) next).getSize().getRowSpan();
                        while (true) {
                            Object next2 = it2.next();
                            iDebug = iDebug2;
                            int rowSpan2 = ((TileConfig) next2).getSize().getRowSpan();
                            if (rowSpan < rowSpan2) {
                                rowSpan = rowSpan2;
                                next = next2;
                            }
                            if (!it2.hasNext()) {
                                break;
                            }
                            iDebug2 = iDebug;
                        }
                    } else {
                        iDebug = iDebug2;
                    }
                    TileConfig tileConfig = (TileConfig) next;
                    int startRow = tileConfig.getLocation().getStartRow();
                    int rowSpan3 = tileConfig.getSize().getRowSpan();
                    int rowSpan4 = tileConfig.getSize().getRowSpan() + tileConfig.getLocation().getStartRow();
                    int i13 = rowSpan3 <= ((this.firstBestSplitRow + i6) - startRow) * 2 ? i6 : 0;
                    ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                    for (TileConfig tileConfig2 : list2) {
                        Loc location = tileConfig2.getLocation();
                        int i14 = i6;
                        if (location.getStartRow() < startRow) {
                            i4 = i13 != 0 ? this.landscapeAEndCol : 0;
                            i3 = 0;
                        } else if (location.getStartRow() < rowSpan4) {
                            i3 = -startRow;
                            if (i13 != 0) {
                                i4 = 0;
                            } else {
                                i4 = this.landscapeAEndCol;
                            }
                        } else {
                            i3 = -rowSpan3;
                            if (i13 != 0) {
                                i4 = this.landscapeAEndCol;
                            } else {
                                i4 = 0;
                            }
                        }
                        arrayList3.add(TileConfig.copy$default(tileConfig2, null, null, LocCache.INSTANCE.loc(location.getPageIndex() + i11, RangesKt___RangesKt.coerceIn(location.getStartCol() + i4, 0, this.landscapeBEndCol - 1), RangesKt___RangesKt.coerceIn(location.getStartRow() + i3, 0, this.landscapeRowSize - 1)), null, 11));
                        i6 = i14;
                        str3 = str3;
                        str4 = str4;
                        startRow = startRow;
                    }
                    str = str3;
                    str2 = str4;
                    i = i6;
                    i2 = 0;
                    listDirectSplitPage = arrayList3;
                } else {
                    LogUtil.i$default(str3, str4, " cross third row less than two, directSplitPage A4*B4 ", iDebug2, i5);
                    listDirectSplitPage = directSplitPage(i11, this.firstBestSplitRow, this.secondBestSplitRow, list);
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                    Log.d("SystemUi--QuickSettings", "LandscapeMappingAlg-->".concat("end reorder for Page: pageIndex = " + iIntValue2 + ", result = " + listDirectSplitPage));
                }
                it = listDirectSplitPage.iterator();
                if (it.hasNext()) {
                    ValueOf = Integer.valueOf(((TileConfig) it.next()).getLocation().getPageIndex());
                    while (it.hasNext()) {
                        numValueOf = Integer.valueOf(((TileConfig) it.next()).getLocation().getPageIndex());
                        if (ValueOf.compareTo(numValueOf) < 0) {
                            ValueOf = numValueOf;
                        }
                    }
                } else {
                    ValueOf = iDebug;
                }
                if (ValueOf != 0) {
                    iIntValue = ValueOf.intValue() - iIntValue2;
                } else {
                    iIntValue = i2;
                }
                i11 += iIntValue;
                arrayList.addAll(listDirectSplitPage);
                i6 = i;
                iDebug2 = iDebug;
                str3 = str;
                str4 = str2;
                i5 = 8;
            } else {
                LogUtil.i$default(str3, str4, " cross third row less than one, directSplitPage A3*B4 ", iDebug2, i5);
                int i15 = this.secondBestSplitRow;
                listDirectSplitPage = directSplitPage(i11, i15, i15, list);
            }
            str = str3;
            str2 = str4;
            iDebug = iDebug2;
            i = i6;
            i2 = 0;
            if (LogUtil.getNormalDebug()) {
                Log.d("SystemUi--QuickSettings", "LandscapeMappingAlg-->".concat("end reorder for Page: pageIndex = " + iIntValue2 + ", result = " + listDirectSplitPage));
            }
            it = listDirectSplitPage.iterator();
            if (it.hasNext()) {
                ValueOf = iDebug;
            } else {
                ValueOf = Integer.valueOf(((TileConfig) it.next()).getLocation().getPageIndex());
                while (it.hasNext()) {
                    numValueOf = Integer.valueOf(((TileConfig) it.next()).getLocation().getPageIndex());
                    if (ValueOf.compareTo(numValueOf) < 0) {
                        ValueOf = numValueOf;
                    }
                }
            }
            if (ValueOf != 0) {
                iIntValue = ValueOf.intValue() - iIntValue2;
            } else {
                iIntValue = i2;
            }
            i11 += iIntValue;
            arrayList.addAll(listDirectSplitPage);
            i6 = i;
            iDebug2 = iDebug;
            str3 = str;
            str4 = str2;
            i5 = 8;
        }
        return arrayList;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.algorithm.ILocationAlg
    public boolean match(LandscapeInputData landscapeInputData) {
        return true;
    }
}
