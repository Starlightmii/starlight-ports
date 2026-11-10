package k0;

import e1.y;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p extends n {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final y f3388g;

    public p(y yVar) {
        this.f3388g = yVar;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i5 = this.f3386f;
        this.f3386f = i5 + 2;
        Object[] objArr = this.f3384d;
        return new b(this.f3388g, objArr[i5], objArr[i5 + 1]);
    }
}
