package k0;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class n implements Iterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f3384d = m.f3379e.f3383d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3385e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3386f;

    public final void a(Object[] objArr, int i5, int i6) {
        this.f3384d = objArr;
        this.f3385e = i5;
        this.f3386f = i6;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f3386f < this.f3385e;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
