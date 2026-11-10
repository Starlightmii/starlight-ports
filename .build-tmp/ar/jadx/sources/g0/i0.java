package g0;

import f0.w2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2483a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2484b;

    public i0(int i5, int i6) {
        this.f2483a = i5;
        this.f2484b = i6;
    }

    public /* synthetic */ i0(int i5, int i6, int i7) {
        this((i7 & 1) != 0 ? 0 : i5, (i7 & 2) != 0 ? 0 : i6);
    }

    public abstract void a(e2.l lVar, f0.c cVar, w2 w2Var, n0.j jVar, j0 j0Var);

    public f0.a b(e2.l lVar) {
        return null;
    }

    public final String toString() {
        String strB = b4.x.a(getClass()).b();
        return strB == null ? "" : strB;
    }
}
