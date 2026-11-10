package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x0 extends h {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final a1 f2785l;

    public x0(t3.c cVar, m mVar) {
        super(1, cVar);
        this.f2785l = mVar;
    }

    @Override // i4.h
    public final Throwable n(a1 a1Var) {
        Throwable thD;
        a1 a1Var2 = this.f2785l;
        a1Var2.getClass();
        Object obj = a1.f2715d.get(a1Var2);
        if (!(obj instanceof z0) || (thD = ((z0) obj).d()) == null) {
            return obj instanceof o ? ((o) obj).f2763a : a1Var.m();
        }
        return thD;
    }

    @Override // i4.h
    public final String y() {
        return "AwaitContinuation";
    }
}
