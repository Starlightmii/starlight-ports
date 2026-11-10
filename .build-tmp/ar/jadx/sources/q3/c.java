package q3;

import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends d implements RandomAccess {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f5468d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5469e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5470f;

    public c(d dVar, int i5, int i6) {
        this.f5468d = dVar;
        this.f5469e = i5;
        p3.p.i(i5, i6, dVar.a());
        this.f5470f = i6 - i5;
    }

    @Override // q3.a
    public final int a() {
        return this.f5470f;
    }

    @Override // java.util.List
    public final Object get(int i5) {
        int i6 = this.f5470f;
        if (i5 < 0 || i5 >= i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        return this.f5468d.get(this.f5469e + i5);
    }

    @Override // q3.d, java.util.List
    public final List subList(int i5, int i6) {
        p3.p.i(i5, i6, this.f5470f);
        int i7 = this.f5469e;
        return new c(this.f5468d, i5 + i7, i7 + i6);
    }
}
