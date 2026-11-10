package f0;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u0 implements Iterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final t2 f2346d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2347e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2348f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2349g;

    public u0(t2 t2Var, int i5, int i6) {
        this.f2346d = t2Var;
        this.f2347e = i6;
        this.f2348f = i5;
        this.f2349g = t2Var.f2340k;
        if (t2Var.f2339j) {
            v2.f();
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f2348f < this.f2347e;
    }

    @Override // java.util.Iterator
    public final Object next() {
        t2 t2Var = this.f2346d;
        int i5 = t2Var.f2340k;
        int i6 = this.f2349g;
        if (i5 != i6) {
            v2.f();
        }
        int i7 = this.f2348f;
        this.f2348f = v2.a(t2Var.f2333d, i7) + i7;
        return new u2(t2Var, i7, i6);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
