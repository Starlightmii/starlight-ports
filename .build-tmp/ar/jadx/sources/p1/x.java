package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x extends n0 {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ y f4988u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(y yVar) {
        super(yVar);
        this.f4988u = yVar;
    }

    @Override // n1.e0
    public final int a0(int i5) {
        y yVar = this.f4988u;
        w wVar = yVar.M;
        y0 y0Var = yVar.f4999p;
        b4.l.b(y0Var);
        n0 n0VarJ0 = y0Var.J0();
        b4.l.b(n0VarJ0);
        return wVar.m0(this, n0VarJ0, i5);
    }

    @Override // n1.e0
    public final n1.m0 c(long j5) {
        n0(j5);
        new k2.a(j5);
        y yVar = this.f4988u;
        w wVar = yVar.M;
        y0 y0Var = yVar.f4999p;
        b4.l.b(y0Var);
        n0 n0VarJ0 = y0Var.J0();
        b4.l.b(n0VarJ0);
        n0.z0(this, wVar.c(this, n0VarJ0, j5));
        return this;
    }

    @Override // n1.e0
    public final int c0(int i5) {
        y yVar = this.f4988u;
        w wVar = yVar.M;
        y0 y0Var = yVar.f4999p;
        b4.l.b(y0Var);
        n0 n0VarJ0 = y0Var.J0();
        b4.l.b(n0VarJ0);
        return wVar.a0(this, n0VarJ0, i5);
    }

    @Override // n1.e0
    public final int d(int i5) {
        y yVar = this.f4988u;
        w wVar = yVar.M;
        y0 y0Var = yVar.f4999p;
        b4.l.b(y0Var);
        n0 n0VarJ0 = y0Var.J0();
        b4.l.b(n0VarJ0);
        return wVar.x(this, n0VarJ0, i5);
    }

    @Override // n1.e0
    public final int e0(int i5) {
        y yVar = this.f4988u;
        w wVar = yVar.M;
        y0 y0Var = yVar.f4999p;
        b4.l.b(y0Var);
        n0 n0VarJ0 = y0Var.J0();
        b4.l.b(n0VarJ0);
        return wVar.g0(this, n0VarJ0, i5);
    }

    @Override // p1.m0
    public final int o0(n1.l lVar) {
        int iC = f.c(this, lVar);
        this.f4937t.put(lVar, Integer.valueOf(iC));
        return iC;
    }
}
