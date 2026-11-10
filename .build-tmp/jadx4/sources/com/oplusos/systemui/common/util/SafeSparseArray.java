package com.oplusos.systemui.common.util;

import android.util.SparseArray;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SafeSparseArray extends SparseArray {
    private final Object lock = new Object();

    @Override // android.util.SparseArray
    public final void clear() {
        synchronized (this.lock) {
            super.clear();
        }
    }

    @Override // android.util.SparseArray
    public final Object get(int i) {
        Object obj;
        synchronized (this.lock) {
            obj = super.get(i);
        }
        return obj;
    }

    @Override // android.util.SparseArray
    public final void put(int i, Object obj) {
        synchronized (this.lock) {
            super.put(i, obj);
        }
    }
}
