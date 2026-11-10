package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends r0.o implements p1.v, p1.k {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public l0 f4474q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final i1 f4475r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f4476s;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public n1.p f4478u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public x0.d f4479v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f4480w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f4482y;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final j1.h f4477t = new j1.h(1);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f4481x = 0;

    public i(l0 l0Var, i1 i1Var, boolean z2) {
        this.f4474q = l0Var;
        this.f4475r = i1Var;
        this.f4476s = z2;
    }

    public static final float y0(i iVar, d dVar) {
        x0.d dVar2;
        int iCompare;
        if (k2.j.a(iVar.f4481x, 0L)) {
            return 0.0f;
        }
        h0.e eVar = iVar.f4477t.f3256a;
        int i5 = eVar.f2578f;
        if (i5 > 0) {
            int i6 = i5 - 1;
            Object[] objArr = eVar.f2576d;
            dVar2 = null;
            do {
                x0.d dVar3 = (x0.d) ((h) objArr[i6]).f4467a.a();
                if (dVar3 != null) {
                    long jF = p3.j.f(dVar3.c(), dVar3.b());
                    long jO = a.a.O(iVar.f4481x);
                    int iOrdinal = iVar.f4474q.ordinal();
                    if (iOrdinal == 0) {
                        iCompare = Float.compare(x0.f.b(jF), x0.f.b(jO));
                    } else {
                        if (iOrdinal != 1) {
                            throw new i4.p();
                        }
                        iCompare = Float.compare(x0.f.d(jF), x0.f.d(jO));
                    }
                    if (iCompare > 0) {
                        if (dVar2 != null) {
                            break;
                        }
                        dVar2 = dVar3;
                        break;
                    }
                    dVar2 = dVar3;
                    i6--;
                } else {
                    i6--;
                }
            } while (i6 >= 0);
        } else {
            dVar2 = null;
        }
        if (dVar2 == null) {
            x0.d dVarZ0 = iVar.f4480w ? iVar.z0() : null;
            if (dVarZ0 == null) {
                return 0.0f;
            }
            dVar2 = dVarZ0;
        }
        long jO2 = a.a.O(iVar.f4481x);
        int iOrdinal2 = iVar.f4474q.ordinal();
        if (iOrdinal2 == 0) {
            float f5 = dVar2.f6535b;
            return dVar.a(f5, dVar2.f6537d - f5, x0.f.b(jO2));
        }
        if (iOrdinal2 != 1) {
            throw new i4.p();
        }
        float f6 = dVar2.f6534a;
        return dVar.a(f6, dVar2.f6536c - f6, x0.f.d(jO2));
    }

    public final boolean A0(x0.d dVar, long j5) {
        long jC0 = C0(dVar, j5);
        return Math.abs(x0.c.d(jC0)) <= 0.5f && Math.abs(x0.c.e(jC0)) <= 0.5f;
    }

    public final void B0() {
        d dVar = (d) p1.f.h(this, g.f4458a);
        if (this.f4482y) {
            throw new IllegalStateException("launchAnimation called when previous animation was running");
        }
        i4.y.n(n0(), null, new l4.n(this, new v1(dVar.b()), dVar, null, 3), 1);
    }

    public final long C0(x0.d dVar, long j5) {
        long jO = a.a.O(j5);
        int iOrdinal = this.f4474q.ordinal();
        if (iOrdinal == 0) {
            d dVar2 = (d) p1.f.h(this, g.f4458a);
            float f5 = dVar.f6535b;
            return p3.j.b(0.0f, dVar2.a(f5, dVar.f6537d - f5, x0.f.b(jO)));
        }
        if (iOrdinal != 1) {
            throw new i4.p();
        }
        d dVar3 = (d) p1.f.h(this, g.f4458a);
        float f6 = dVar.f6534a;
        return p3.j.b(dVar3.a(f6, dVar.f6536c - f6, x0.f.d(jO)), 0.0f);
    }

    @Override // p1.v
    public final void m(long j5) {
        int iF;
        x0.d dVarZ0;
        long j6 = this.f4481x;
        this.f4481x = j5;
        int iOrdinal = this.f4474q.ordinal();
        if (iOrdinal == 0) {
            iF = b4.l.f((int) (j5 & 4294967295L), (int) (4294967295L & j6));
        } else {
            if (iOrdinal != 1) {
                throw new i4.p();
            }
            iF = b4.l.f((int) (j5 >> 32), (int) (j6 >> 32));
        }
        if (iF < 0 && (dVarZ0 = z0()) != null) {
            x0.d dVar = this.f4479v;
            if (dVar == null) {
                dVar = dVarZ0;
            }
            if (!this.f4482y && !this.f4480w && A0(dVar, j6) && !A0(dVarZ0, j5)) {
                this.f4480w = true;
                B0();
            }
            this.f4479v = dVarZ0;
        }
    }

    @Override // r0.o
    public final boolean o0() {
        return false;
    }

    public final x0.d z0() {
        if (this.f5693p) {
            p1.y0 y0VarR = p1.f.r(this);
            n1.p pVar = this.f4478u;
            if (pVar != null) {
                if (!pVar.N()) {
                    pVar = null;
                }
                if (pVar != null) {
                    return y0VarR.k(pVar, false);
                }
            }
        }
        return null;
    }
}
