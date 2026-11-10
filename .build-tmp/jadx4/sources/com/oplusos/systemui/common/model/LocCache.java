package com.oplusos.systemui.common.model;

import android.util.SparseArray;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LocCache {
    public static final LocCache INSTANCE = new LocCache();
    private static final Loc DEF_LOC = new Loc(0, 0, 0);
    private static final Loc INVALID_LOC = new Loc(-1, -1, -1);
    private static final SparseArray<SparseArray<SparseArray<Loc>>> constantCache = new SparseArray<>();

    public static /* synthetic */ Loc loc$default(LocCache locCache, int i, int i2, int i3) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return locCache.loc(0, i, i2);
    }

    public final Loc getINVALID_LOC() {
        return INVALID_LOC;
    }

    public final Loc loc(int i, int i2, int i3) {
        Loc loc;
        if (i == 0 && i2 == 0 && i3 == 0) {
            return DEF_LOC;
        }
        synchronized (this) {
            try {
                SparseArray<SparseArray<SparseArray<Loc>>> sparseArray = constantCache;
                SparseArray<SparseArray<Loc>> sparseArray2 = sparseArray.get(i);
                if (sparseArray2 == null) {
                    sparseArray2 = new SparseArray<>();
                    sparseArray.set(i, sparseArray2);
                }
                SparseArray<Loc> sparseArray3 = sparseArray2.get(i2);
                if (sparseArray3 == null) {
                    sparseArray3 = new SparseArray<>();
                    sparseArray2.set(i2, sparseArray3);
                }
                loc = sparseArray3.get(i3);
                if (loc == null) {
                    loc = new Loc(i, i2, i3);
                    sparseArray3.set(i3, loc);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return loc;
    }
}
