package b3;

import android.util.SparseArray;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SparseArray f654a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public x f655b;

    public v(int i5) {
        this.f654a = new SparseArray(i5);
    }

    public final void a(x xVar, int i5, int i6) {
        int iA = xVar.a(i5);
        SparseArray sparseArray = this.f654a;
        v vVar = sparseArray == null ? null : (v) sparseArray.get(iA);
        if (vVar == null) {
            vVar = new v(1);
            sparseArray.put(xVar.a(i5), vVar);
        }
        if (i6 > i5) {
            vVar.a(xVar, i5 + 1, i6);
        } else {
            vVar.f655b = xVar;
        }
    }
}
