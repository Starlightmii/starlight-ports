package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y1.e0 f5899a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public n1.p f5900b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public n1.p f5901c;

    public e1(y1.e0 e0Var, n1.p pVar) {
        this.f5899a = e0Var;
        this.f5901c = pVar;
    }

    public final long a(long j5) {
        x0.d dVarK;
        n1.p pVar = this.f5900b;
        x0.d dVar = x0.d.f6533e;
        if (pVar != null) {
            if (pVar.N()) {
                n1.p pVar2 = this.f5901c;
                dVarK = pVar2 != null ? pVar2.k(pVar, true) : null;
            } else {
                dVarK = dVar;
            }
            if (dVarK != null) {
                dVar = dVarK;
            }
        }
        float fD = x0.c.d(j5);
        float fD2 = dVar.f6534a;
        if (fD >= fD2) {
            float fD3 = x0.c.d(j5);
            fD2 = dVar.f6536c;
            if (fD3 <= fD2) {
                fD2 = x0.c.d(j5);
            }
        }
        float fE = x0.c.e(j5);
        float fE2 = dVar.f6535b;
        if (fE >= fE2) {
            float fE3 = x0.c.e(j5);
            fE2 = dVar.f6537d;
            if (fE3 <= fE2) {
                fE2 = x0.c.e(j5);
            }
        }
        return p3.j.b(fD2, fE2);
    }

    public final int b(long j5, boolean z2) {
        if (z2) {
            j5 = a(j5);
        }
        return this.f5899a.f6754b.e(d(j5));
    }

    public final boolean c(long j5) {
        long jD = d(a(j5));
        float fE = x0.c.e(jD);
        y1.e0 e0Var = this.f5899a;
        int iC = e0Var.f6754b.c(fE);
        return x0.c.d(jD) >= e0Var.f(iC) && x0.c.d(jD) <= e0Var.g(iC);
    }

    public final long d(long j5) {
        n1.p pVar;
        n1.p pVar2 = this.f5900b;
        if (pVar2 == null) {
            return j5;
        }
        if (!pVar2.N()) {
            pVar2 = null;
        }
        if (pVar2 == null || (pVar = this.f5901c) == null) {
            return j5;
        }
        n1.p pVar3 = pVar.N() ? pVar : null;
        return pVar3 == null ? j5 : pVar2.y(pVar3, j5);
    }

    public final long e(long j5) {
        n1.p pVar;
        n1.p pVar2 = this.f5900b;
        if (pVar2 == null) {
            return j5;
        }
        if (!pVar2.N()) {
            pVar2 = null;
        }
        if (pVar2 == null || (pVar = this.f5901c) == null) {
            return j5;
        }
        n1.p pVar3 = pVar.N() ? pVar : null;
        return pVar3 == null ? j5 : pVar3.y(pVar2, j5);
    }
}
