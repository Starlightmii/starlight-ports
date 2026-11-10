package y;

import b4.l;
import java.util.LinkedHashMap;
import java.util.Map;
import k2.k;
import n1.e0;
import n1.g0;
import n1.p0;
import p1.f0;
import p1.l1;
import p1.m0;
import p1.w;
import p3.j;
import p3.p;
import r0.o;
import v.l0;
import w1.h;
import w1.i;
import w1.q;
import w1.s;
import w1.t;
import y0.r;
import y0.u;
import y1.a0;
import y1.h0;
import z1.y;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g extends o implements w, p1.o, l1 {
    public e A;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f6605q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public h0 f6606r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public d2.d f6607s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f6608t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f6609u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f6610v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f6611w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Map f6612x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public d f6613y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public f f6614z;

    @Override // p1.l1
    public final void Z(i iVar) {
        f fVar = this.f6614z;
        if (fVar == null) {
            fVar = new f(this, 0);
            this.f6614z = fVar;
        }
        y1.f fVar2 = new y1.f(this.f6605q, null, 6);
        f4.d[] dVarArr = s.f6363a;
        iVar.c(q.f6355t, j.G(fVar2));
        e eVar = this.A;
        if (eVar != null) {
            boolean z2 = eVar.f6601c;
            t tVar = q.f6357v;
            f4.d[] dVarArr2 = s.f6363a;
            f4.d dVar = dVarArr2[15];
            tVar.a(iVar, Boolean.valueOf(z2));
            y1.f fVar3 = new y1.f(eVar.f6600b, null, 6);
            t tVar2 = q.f6356u;
            f4.d dVar2 = dVarArr2[14];
            tVar2.a(iVar, fVar3);
        }
        iVar.c(h.f6290i, new w1.a(null, new f(this, 1)));
        iVar.c(h.f6291j, new w1.a(null, new f(this, 2)));
        iVar.c(h.f6292k, new w1.a(null, new b0.a(29, this)));
        s.c(iVar, fVar);
    }

    @Override // p1.w
    public final int a0(m0 m0Var, e0 e0Var, int i5) {
        return l0.k(z0(m0Var).d(m0Var.getLayoutDirection()).c());
    }

    @Override // p1.w
    public final g0 c(n1.h0 h0Var, e0 e0Var, long j5) {
        long jB;
        boolean z2;
        y1.q qVar;
        int iJ;
        d dVarZ0 = z0(h0Var);
        k layoutDirection = h0Var.getLayoutDirection();
        if (dVarZ0.f6587g > 1) {
            b bVar = dVarZ0.f6593m;
            h0 h0Var2 = dVarZ0.f6582b;
            k2.b bVar2 = dVarZ0.f6589i;
            l.b(bVar2);
            d2.d dVar = dVarZ0.f6583c;
            if ((bVar == null || layoutDirection != bVar.f6572a || !l.a(h0Var2, bVar.f6573b) || bVar2.a() != bVar.f6574c.a() || dVar != bVar.f6575d) && ((bVar = b.f6571h) == null || layoutDirection != bVar.f6572a || !l.a(h0Var2, bVar.f6573b) || bVar2.a() != bVar.f6574c.a() || dVar != bVar.f6575d)) {
                bVar = new b(layoutDirection, p.B(h0Var2, layoutDirection), bVar2, dVar);
                b.f6571h = bVar;
            }
            dVarZ0.f6593m = bVar;
            int i5 = dVarZ0.f6587g;
            float f5 = bVar.f6578g;
            float f6 = bVar.f6577f;
            if (Float.isNaN(f5) || Float.isNaN(f6)) {
                float fB = q.f.a(c.f6579a, bVar.f6576e, h1.c.c(0, 0, 15), bVar.f6574c, bVar.f6575d, 1, 96).b();
                float fB2 = q.f.a(c.f6580b, bVar.f6576e, h1.c.c(0, 0, 15), bVar.f6574c, bVar.f6575d, 2, 96).b() - fB;
                bVar.f6578g = fB;
                bVar.f6577f = fB2;
                f6 = fB2;
                f5 = fB;
            }
            if (i5 != 1) {
                int iRound = Math.round((f6 * (i5 - 1)) + f5);
                iJ = iRound >= 0 ? iRound : 0;
                int iH = k2.a.h(j5);
                if (iJ > iH) {
                    iJ = iH;
                }
            } else {
                iJ = k2.a.j(j5);
            }
            jB = h1.c.b(k2.a.k(j5), k2.a.i(j5), iJ, k2.a.h(j5));
        } else {
            jB = j5;
        }
        y1.a aVar = dVarZ0.f6590j;
        if (aVar == null || (qVar = dVarZ0.f6594n) == null || qVar.b() || layoutDirection != dVarZ0.f6595o || (!k2.a.c(jB, dVarZ0.f6596p) && (k2.a.i(jB) != k2.a.i(dVarZ0.f6596p) || k2.a.h(jB) < aVar.b() || aVar.f6706d.f7153c))) {
            y1.a aVarB = dVarZ0.b(jB, layoutDirection);
            dVarZ0.f6596p = jB;
            long jN = h1.c.n(jB, a.a.f(l0.k(aVarB.d()), l0.k(aVarB.b())));
            dVarZ0.f6592l = jN;
            dVarZ0.f6591k = dVarZ0.f6584d != 3 && (((float) ((int) (jN >> 32))) < aVarB.d() || ((float) ((int) (jN & 4294967295L))) < aVarB.b());
            dVarZ0.f6590j = aVarB;
            z2 = true;
        } else {
            if (!k2.a.c(jB, dVarZ0.f6596p)) {
                y1.a aVar2 = dVarZ0.f6590j;
                l.b(aVar2);
                long jN2 = h1.c.n(jB, a.a.f(l0.k(Math.min(aVar2.f6703a.f2524i.b(), aVar2.d())), l0.k(aVar2.b())));
                dVarZ0.f6592l = jN2;
                dVarZ0.f6591k = dVarZ0.f6584d != 3 && (((float) ((int) (jN2 >> 32))) < aVar2.d() || ((float) ((int) (jN2 & 4294967295L))) < aVar2.b());
                dVarZ0.f6596p = jB;
            }
            z2 = false;
        }
        y1.q qVar2 = dVarZ0.f6594n;
        if (qVar2 != null) {
            qVar2.b();
        }
        y1.a aVar3 = dVarZ0.f6590j;
        l.b(aVar3);
        y yVar = aVar3.f6706d;
        long j6 = dVarZ0.f6592l;
        if (z2) {
            p1.f.q(this, 2).R0();
            Map linkedHashMap = this.f6612x;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap(2);
            }
            linkedHashMap.put(n1.c.f4106a, Integer.valueOf(Math.round(yVar.d(0))));
            linkedHashMap.put(n1.c.f4107b, Integer.valueOf(Math.round(yVar.d(yVar.f7156f - 1))));
            this.f6612x = linkedHashMap;
        }
        int i6 = (int) (j6 >> 32);
        int i7 = (int) (j6 & 4294967295L);
        int iMin = Math.min(i6, 262142);
        int iMin2 = i6 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(i6, 262142);
        int iH2 = h1.c.h(iMin2 == Integer.MAX_VALUE ? iMin : iMin2);
        n1.m0 m0VarC = e0Var.c(h1.c.b(iMin, iMin2, Math.min(iH2, i7), i7 != Integer.MAX_VALUE ? Math.min(iH2, i7) : Integer.MAX_VALUE));
        Map map = this.f6612x;
        l.b(map);
        return h0Var.F(i6, i7, map, new p0(m0VarC, 9));
    }

    @Override // p1.o
    public final void d(f0 f0Var) {
        if (this.f5693p) {
            d dVarZ0 = z0(f0Var);
            y1.a aVar = dVarZ0.f6590j;
            if (aVar == null) {
                throw new IllegalArgumentException(("no paragraph (layoutCache=" + this.f6613y + ", textSubstitution=" + this.A + ')').toString());
            }
            r rVarR = f0Var.f4828d.f27e.r();
            boolean z2 = dVarZ0.f6591k;
            if (z2) {
                long j5 = dVarZ0.f6592l;
                rVarR.p();
                rVarR.i(0.0f, 0.0f, (int) (j5 >> 32), (int) (j5 & 4294967295L), 1);
            }
            try {
                a0 a0Var = this.f6606r.f6780a;
                j2.j jVar = a0Var.f6721m;
                if (jVar == null) {
                    jVar = j2.j.f3322b;
                }
                j2.j jVar2 = jVar;
                y0.p0 p0Var = a0Var.f6722n;
                if (p0Var == null) {
                    p0Var = y0.p0.f6668d;
                }
                y0.p0 p0Var2 = p0Var;
                a1.g gVar = a0Var.f6724p;
                if (gVar == null) {
                    gVar = a1.i.f33a;
                }
                a1.g gVar2 = gVar;
                y0.p pVarC = a0Var.f6709a.c();
                if (pVarC != null) {
                    aVar.g(rVarR, pVarC, this.f6606r.f6780a.f6709a.a(), p0Var2, jVar2, gVar2);
                } else {
                    long jB = u.f6694g;
                    if (jB == 16) {
                        jB = this.f6606r.b() != 16 ? this.f6606r.b() : u.f6689b;
                    }
                    aVar.f(rVarR, jB, p0Var2, jVar2, gVar2);
                }
            } finally {
                if (z2) {
                    rVarR.m();
                }
            }
        }
    }

    @Override // p1.w
    public final int g0(m0 m0Var, e0 e0Var, int i5) {
        return z0(m0Var).a(i5, m0Var.getLayoutDirection());
    }

    @Override // p1.w
    public final int m0(m0 m0Var, e0 e0Var, int i5) {
        return l0.k(z0(m0Var).d(m0Var.getLayoutDirection()).a());
    }

    @Override // p1.w
    public final int x(m0 m0Var, e0 e0Var, int i5) {
        return z0(m0Var).a(i5, m0Var.getLayoutDirection());
    }

    public final d y0() {
        if (this.f6613y == null) {
            this.f6613y = new d(this.f6605q, this.f6606r, this.f6607s, this.f6608t, this.f6609u, this.f6610v, this.f6611w);
        }
        d dVar = this.f6613y;
        l.b(dVar);
        return dVar;
    }

    public final d z0(k2.b bVar) {
        d dVar;
        e eVar = this.A;
        if (eVar != null && eVar.f6601c && (dVar = eVar.f6602d) != null) {
            dVar.c(bVar);
            return dVar;
        }
        d dVarY0 = y0();
        dVarY0.c(bVar);
        return dVarY0;
    }
}
