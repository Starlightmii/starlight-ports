package k0;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k extends q3.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f3376d;

    public k(c cVar) {
        this.f3376d = cVar;
    }

    @Override // q3.a
    public final int a() {
        c cVar = this.f3376d;
        cVar.getClass();
        return cVar.f3362e;
    }

    @Override // q3.a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return this.f3376d.containsValue(obj);
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        m mVar = this.f3376d.f3361d;
        n[] nVarArr = new n[8];
        for (int i5 = 0; i5 < 8; i5++) {
            nVarArr[i5] = new o(2);
        }
        return new j(mVar, nVarArr);
    }
}
