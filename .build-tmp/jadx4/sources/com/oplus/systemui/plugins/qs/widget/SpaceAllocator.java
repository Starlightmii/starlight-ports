package com.oplus.systemui.plugins.qs.widget;

import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpaceAllocator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SpaceAllocator";
    private final ArrayList<DynamicZone> zoneList = new ArrayList<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DynamicZone {
        public int count;
        public int curH;
        public boolean isFixed;
        public int maxH;
        public int minH;
        private final String name;
        public int priority;

        public DynamicZone(String str, int i, int i2, int i3, int i4) {
            this.name = str;
            this.minH = i;
            this.maxH = i2;
            this.count = i3;
            this.priority = i4;
        }

        public final String component1() {
            return this.name;
        }

        public final DynamicZone copy(String str, int i, int i2, int i3, int i4) {
            return new DynamicZone(str, i, i2, i3, i4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DynamicZone)) {
                return false;
            }
            DynamicZone dynamicZone = (DynamicZone) obj;
            return Intrinsics.areEqual(this.name, dynamicZone.name) && this.minH == dynamicZone.minH && this.maxH == dynamicZone.maxH && this.count == dynamicZone.count && this.priority == dynamicZone.priority;
        }

        public final int getCount() {
            return this.count;
        }

        public final int getCurH() {
            return this.curH;
        }

        public final int getMaxH() {
            return this.maxH;
        }

        public final int getMinH() {
            return this.minH;
        }

        public final String getName() {
            return this.name;
        }

        public final int getPriority() {
            return this.priority;
        }

        public final int hashCode() {
            return Integer.hashCode(this.priority) + MonoSpline$.ExternalSyntheticOutline0.m(this.count, MonoSpline$.ExternalSyntheticOutline0.m(this.maxH, MonoSpline$.ExternalSyntheticOutline0.m(this.minH, this.name.hashCode() * 31, 31), 31), 31);
        }

        public final void setCount(int i) {
            this.count = i;
        }

        public final void setCurH(int i) {
            this.curH = i;
        }

        public final void setFixed(boolean z) {
            this.isFixed = z;
        }

        public final void setMaxH(int i) {
            this.maxH = i;
        }

        public final void setMinH(int i) {
            this.minH = i;
        }

        public final void setPriority(int i) {
            this.priority = i;
        }

        public String toString() {
            String str = this.name;
            int i = this.curH;
            int i2 = this.minH;
            int i3 = this.maxH;
            int i4 = this.count;
            boolean z = this.isFixed;
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "name:", str, ", curH:", ", minH:");
            RectList$.ExternalSyntheticOutline0.m(i2, i3, ", maxH:", ", count:", sbM);
            sbM.append(i4);
            sbM.append(", isFixed:");
            sbM.append(z);
            return sbM.toString();
        }
    }

    public final void allocateSpace(int i) {
        ArrayList<DynamicZone> arrayList = this.zoneList;
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            DynamicZone dynamicZone = arrayList.get(i2);
            i2++;
            DynamicZone dynamicZone2 = dynamicZone;
            if (!dynamicZone2.isFixed && dynamicZone2.getCount() > 0) {
                arrayList2.add(dynamicZone);
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size2 = arrayList2.size();
        int i3 = 0;
        while (i3 < size2) {
            Object obj = arrayList2.get(i3);
            i3++;
            Integer numValueOf = Integer.valueOf(((DynamicZone) obj).getPriority());
            Object arrayList3 = linkedHashMap.get(numValueOf);
            if (arrayList3 == null) {
                arrayList3 = new ArrayList();
                linkedHashMap.put(numValueOf, arrayList3);
            }
            ((List) arrayList3).add(obj);
        }
        Iterator it = MapsKt__MapsJVMKt.toSortedMap(linkedHashMap).entrySet().iterator();
        int i4 = i;
        while (it.hasNext()) {
            List list = (List) ((Map.Entry) it.next()).getValue();
            if (i4 <= 0) {
                break;
            }
            list.getClass();
            List<DynamicZone> list2 = list;
            int count = 0;
            for (DynamicZone dynamicZone3 : list2) {
                count += dynamicZone3.getCount() * (dynamicZone3.getMaxH() - dynamicZone3.getMinH());
            }
            if (i4 <= count) {
                for (DynamicZone dynamicZone4 : list2) {
                    dynamicZone4.setCurH((((dynamicZone4.getCount() * ((dynamicZone4.getMaxH() - dynamicZone4.getMinH()) * i4)) / count) / dynamicZone4.getCount()) + dynamicZone4.getMinH());
                }
                i4 = 0;
            } else {
                for (DynamicZone dynamicZone5 : list2) {
                    dynamicZone5.setCurH(dynamicZone5.getMaxH());
                }
                i4 -= count;
            }
        }
        ArrayList<DynamicZone> arrayList4 = this.zoneList;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("allocateSpace()-> totalH:", i, i4, ", leftH:", ", zoneList:{");
        sbM.append(arrayList4);
        sbM.append("}");
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("SpaceAllocator zoneList:{" + this.zoneList + "}");
    }

    public final int minTotalHeight() {
        ArrayList<DynamicZone> arrayList = this.zoneList;
        int size = arrayList.size();
        int count = 0;
        int i = 0;
        while (i < size) {
            DynamicZone dynamicZone = arrayList.get(i);
            i++;
            DynamicZone dynamicZone2 = dynamicZone;
            count += dynamicZone2.getCount() * (dynamicZone2.isFixed ? dynamicZone2.getCurH() : dynamicZone2.getMinH());
        }
        return count;
    }

    public final void reset() {
        for (DynamicZone dynamicZone : this.zoneList) {
            dynamicZone.setFixed(false);
            dynamicZone.setCurH(dynamicZone.getMinH());
        }
    }

    public final void setZoneList(ArrayList<DynamicZone> arrayList) {
        this.zoneList.clear();
        this.zoneList.addAll(arrayList);
    }
}
