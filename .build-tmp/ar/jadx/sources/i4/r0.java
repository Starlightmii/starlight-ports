package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r0 extends w0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f2771h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f2772i;

    public /* synthetic */ r0(int i5, Object obj) {
        this.f2771h = i5;
        this.f2772i = obj;
    }

    @Override // i4.w0
    public final boolean j() {
        switch (this.f2771h) {
        }
        return false;
    }

    @Override // i4.w0
    public final void k(Throwable th) {
        switch (this.f2771h) {
            case 0:
                ((a4.c) this.f2772i).c(th);
                break;
            default:
                x0 x0Var = (x0) this.f2772i;
                Object obj = a1.f2715d.get(i());
                if (!(obj instanceof o)) {
                    x0Var.resumeWith(y.r(obj));
                } else {
                    x0Var.resumeWith(p3.j.r(((o) obj).f2763a));
                }
                break;
        }
    }
}
