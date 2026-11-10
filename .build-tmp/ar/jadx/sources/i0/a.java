package i0;

import j0.c;
import java.util.List;
import q3.d;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f2643d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2644e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2645f;

    public a(c cVar, int i5, int i6) {
        this.f2643d = cVar;
        this.f2644e = i5;
        a.a.o(i5, i6, cVar.a());
        this.f2645f = i6 - i5;
    }

    @Override // q3.a
    public final int a() {
        return this.f2645f;
    }

    @Override // java.util.List
    public final Object get(int i5) {
        a.a.m(i5, this.f2645f);
        return this.f2643d.get(this.f2644e + i5);
    }

    @Override // q3.d, java.util.List
    public final List subList(int i5, int i6) {
        a.a.o(i5, i6, this.f2645f);
        int i7 = this.f2644e;
        return new a(this.f2643d, i5 + i7, i7 + i6);
    }
}
