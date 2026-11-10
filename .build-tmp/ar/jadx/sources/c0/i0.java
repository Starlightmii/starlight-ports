package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i0 implements e2.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f917a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f918b;

    public /* synthetic */ i0(int i5, int i6) {
        this.f917a = i5;
        this.f918b = i6;
    }

    @Override // e2.r
    public int a(int i5) {
        if (i5 >= 0 && i5 <= this.f918b) {
            v.l0.u(i5, this.f917a, i5);
        }
        return i5;
    }

    @Override // e2.r
    public int b(int i5) {
        if (i5 >= 0 && i5 <= this.f917a) {
            v.l0.t(i5, this.f918b, i5);
        }
        return i5;
    }
}
