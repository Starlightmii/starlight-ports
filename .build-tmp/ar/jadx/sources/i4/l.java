package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l extends w0 implements k {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a1 f2749h;

    public l(a1 a1Var) {
        this.f2749h = a1Var;
    }

    @Override // i4.k
    public final boolean b(Throwable th) {
        return i().B(th);
    }

    @Override // i4.w0
    public final boolean j() {
        return true;
    }

    @Override // i4.w0
    public final void k(Throwable th) {
        this.f2749h.x(i());
    }
}
