package d0;

import f0.g3;
import m.q0;
import y0.l0;
import y0.o0;
import y0.p0;
import y0.s0;
import y1.a0;
import y1.b0;
import y1.h0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ h0 f1550e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ h0 f1551f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ float f1552g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ g3 f1553h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ a4.e f1554i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ boolean f1555j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ g3 f1556k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(h0 h0Var, h0 h0Var2, float f5, q0 q0Var, a4.e eVar, boolean z2, q0 q0Var2) {
        super(2);
        this.f1550e = h0Var;
        this.f1551f = h0Var2;
        this.f1552g = f5;
        this.f1553h = q0Var;
        this.f1554i = eVar;
        this.f1555j = z2;
        this.f1556k = q0Var2;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0057 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:17:0x0059  */
    /* JADX WARN: Code duplicated, block: B:32:0x009e  */
    /* JADX WARN: Code duplicated, block: B:35:0x00be  */
    /* JADX WARN: Code duplicated, block: B:38:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:41:0x010d  */
    /* JADX WARN: Code duplicated, block: B:42:0x0110  */
    /* JADX WARN: Code duplicated, block: B:45:0x0115  */
    /* JADX WARN: Code duplicated, block: B:48:0x0121  */
    /* JADX WARN: Code duplicated, block: B:52:0x0127  */
    /* JADX WARN: Code duplicated, block: B:55:0x0165  */
    /* JADX WARN: Code duplicated, block: B:58:0x016e  */
    /* JADX WARN: Code duplicated, block: B:63:0x01d8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:64:0x01da  */
    /* JADX WARN: Code duplicated, block: B:68:0x0238  */
    /* JADX WARN: Code duplicated, block: B:71:0x023e  */
    /* JADX WARN: Code duplicated, block: B:76:0x0260  */
    /* JADX WARN: Code duplicated, block: B:78:0x0264  */
    /* JADX WARN: Code duplicated, block: B:79:0x0266  */
    /* JADX WARN: Code duplicated, block: B:82:0x026b  */
    /* JADX WARN: Code duplicated, block: B:86:0x0273  */
    /* JADX WARN: Code duplicated, block: B:89:0x02f2  */
    /* JADX WARN: Code duplicated, block: B:8:0x0022  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        h0 h0Var;
        j2.m mVar;
        j2.m mVar2;
        boolean z2;
        j2.m bVar;
        float f5;
        d2.k kVar;
        d2.k kVar2;
        j2.a aVar;
        float f6;
        j2.n nVar;
        j2.n nVar2;
        j2.n nVar3;
        p0 p0Var;
        p0 p0Var2;
        y1.u uVar;
        y1.u uVar2;
        j2.o oVar;
        j2.o oVar2;
        y1.t tVar;
        y1.t tVar2;
        y1.t tVar3;
        y1.t tVar4;
        boolean z3;
        boolean z4;
        y1.t tVar5;
        f0.p pVar = (f0.p) obj;
        if ((((Number) obj2).intValue() & 3) == 2) {
            f0.v vVar = (f0.v) pVar;
            if (vVar.z()) {
                vVar.Q();
            } else {
                h0 h0Var2 = this.f1550e;
                a0 a0Var = h0Var2.f6780a;
                h0 h0Var3 = this.f1551f;
                a0 a0Var2 = h0Var3.f6780a;
                j2.m mVar3 = b0.f6732d;
                mVar = a0Var.f6709a;
                mVar2 = a0Var2.f6709a;
                z2 = mVar instanceof j2.b;
                bVar = j2.l.f3327a;
                f5 = this.f1552g;
                if (z2 && !(mVar2 instanceof j2.b)) {
                    long jO = l0.o(f5, mVar.b(), mVar2.b());
                    if (jO != 16) {
                        bVar = new j2.c(jO);
                    }
                } else if (z2 || !(mVar2 instanceof j2.b)) {
                    bVar = (j2.m) b0.b(mVar, mVar2, f5);
                } else {
                    j2.b bVar2 = (j2.b) mVar;
                    j2.b bVar3 = (j2.b) mVar2;
                    y0.p pVar2 = (y0.p) b0.b(bVar2.f3306a, bVar3.f3306a, f5);
                    float fD = a.a.D(bVar2.f3307b, bVar3.f3307b, f5);
                    if (pVar2 != null) {
                        if (pVar2 instanceof s0) {
                            long jF = h1.c.F(fD, ((s0) pVar2).f6684a);
                            if (jF != 16) {
                                bVar = new j2.c(jF);
                            }
                        } else {
                            if (!(pVar2 instanceof o0)) {
                                throw new i4.p();
                            }
                            bVar = new j2.b((o0) pVar2, fD);
                        }
                    }
                }
                j2.m mVar4 = bVar;
                d2.p pVar3 = (d2.p) b0.b(a0Var.f6714f, a0Var2.f6714f, f5);
                long jC = b0.c(f5, a0Var.f6710b, a0Var2.f6710b);
                kVar = a0Var.f6711c;
                if (kVar == null) {
                    kVar = d2.k.f1613f;
                }
                kVar2 = a0Var2.f6711c;
                if (kVar2 == null) {
                    kVar2 = d2.k.f1613f;
                }
                d2.k kVar3 = new d2.k(d4.a.m(a.a.E(f5, kVar.f1615d, kVar2.f1615d), 1, 1000));
                d2.i iVar = (d2.i) b0.b(a0Var.f6712d, a0Var2.f6712d, f5);
                d2.j jVar = (d2.j) b0.b(a0Var.f6713e, a0Var2.f6713e, f5);
                String str = (String) b0.b(a0Var.f6715g, a0Var2.f6715g, f5);
                long jC2 = b0.c(f5, a0Var.f6716h, a0Var2.f6716h);
                aVar = a0Var.f6717i;
                if (aVar != null) {
                    f6 = aVar.f3305a;
                } else {
                    f6 = 0.0f;
                }
                j2.a aVar2 = a0Var2.f6717i;
                float fD2 = a.a.D(f6, aVar2 != null ? aVar2.f3305a : 0.0f, f5);
                nVar = a0Var.f6718j;
                nVar2 = j2.n.f3328c;
                if (nVar == null) {
                    nVar = nVar2;
                }
                nVar3 = a0Var2.f6718j;
                if (nVar3 != null) {
                    nVar2 = nVar3;
                }
                j2.n nVar4 = new j2.n(a.a.D(nVar.f3329a, nVar2.f3329a, f5), a.a.D(nVar.f3330b, nVar2.f3330b, f5));
                f2.b bVar4 = (f2.b) b0.b(a0Var.f6719k, a0Var2.f6719k, f5);
                long jO2 = l0.o(f5, a0Var.f6720l, a0Var2.f6720l);
                j2.j jVar2 = (j2.j) b0.b(a0Var.f6721m, a0Var2.f6721m, f5);
                p0Var = a0Var.f6722n;
                if (p0Var == null) {
                    p0Var = new p0();
                }
                p0Var2 = a0Var2.f6722n;
                if (p0Var2 == null) {
                    p0Var2 = new p0();
                }
                long jO3 = l0.o(f5, p0Var.f6669a, p0Var2.f6669a);
                long j5 = p0Var.f6670b;
                long j6 = p0Var2.f6670b;
                p0 p0Var3 = new p0(a.a.D(p0Var.f6671c, p0Var2.f6671c, f5), jO3, (((long) Float.floatToRawIntBits(a.a.D(Float.intBitsToFloat((int) (j5 >> 32)), Float.intBitsToFloat((int) (j6 >> 32)), f5))) << 32) | (((long) Float.floatToRawIntBits(a.a.D(Float.intBitsToFloat((int) (j5 & 4294967295L)), Float.intBitsToFloat((int) (j6 & 4294967295L)), f5))) & 4294967295L));
                uVar = a0Var.f6723o;
                y1.u uVar3 = a0Var2.f6723o;
                if (uVar == null || uVar3 != null) {
                    if (uVar == null) {
                        uVar = y1.u.f6822a;
                    }
                    uVar2 = uVar;
                } else {
                    uVar2 = null;
                }
                a0 a0Var3 = new a0(mVar4, jC, kVar3, iVar, jVar, pVar3, str, jC2, new j2.a(fD2), nVar4, bVar4, jO2, jVar2, p0Var3, uVar2, (a1.g) b0.b(a0Var.f6724p, a0Var2.f6724p, f5));
                y1.r rVar = h0Var2.f6781b;
                y1.r rVar2 = h0Var3.f6781b;
                int i5 = y1.s.f6819b;
                int i6 = ((j2.i) b0.b(new j2.i(rVar.f6809a), new j2.i(rVar2.f6809a), f5)).f3321a;
                int i7 = ((j2.k) b0.b(new j2.k(rVar.f6810b), new j2.k(rVar2.f6810b), f5)).f3326a;
                long jC3 = b0.c(f5, rVar.f6811c, rVar2.f6811c);
                oVar = rVar.f6812d;
                if (oVar == null) {
                    oVar = j2.o.f3331c;
                }
                oVar2 = rVar2.f6812d;
                if (oVar2 == null) {
                    oVar2 = j2.o.f3331c;
                }
                j2.o oVar3 = new j2.o(b0.c(f5, oVar.f3332a, oVar2.f3332a), b0.c(f5, oVar.f3333b, oVar2.f3333b));
                tVar = rVar.f6813e;
                tVar2 = rVar2.f6813e;
                if (tVar == null || tVar2 != null) {
                    tVar3 = y1.t.f6820b;
                    if (tVar == null) {
                        tVar4 = tVar3;
                    } else {
                        tVar4 = tVar;
                    }
                    z3 = tVar4.f6821a;
                    if (tVar2 == null) {
                        tVar2 = tVar3;
                    }
                    z4 = tVar2.f6821a;
                    if (z3 != z4) {
                        ((y1.i) b0.b(new y1.i(), new y1.i(), f5)).getClass();
                        tVar4 = new y1.t(((Boolean) b0.b(Boolean.valueOf(z3), Boolean.valueOf(z4), f5)).booleanValue());
                    }
                    tVar5 = tVar4;
                } else {
                    tVar5 = null;
                }
                h0Var = new h0(a0Var3, new y1.r(i6, i7, jC3, oVar3, tVar5, (j2.g) b0.b(rVar.f6814f, rVar2.f6814f, f5), ((j2.e) b0.b(new j2.e(rVar.f6815g), new j2.e(rVar2.f6815g), f5)).f3311a, ((j2.d) b0.b(new j2.d(rVar.f6816h), new j2.d(rVar2.f6816h), f5)).f3309a, (j2.p) b0.b(rVar.f6817i, rVar2.f6817i, f5)));
                if (this.f1555j) {
                    h0Var = h0.a(h0Var, ((y0.u) this.f1556k.getValue()).f6696a, 0L, null, null, 0L, 0L, null, 16777214);
                }
                u.b(((y0.u) this.f1553h.getValue()).f6696a, h0Var, this.f1554i, pVar, 0);
            }
        } else {
            h0 h0Var4 = this.f1550e;
            a0 a0Var4 = h0Var4.f6780a;
            h0 h0Var5 = this.f1551f;
            a0 a0Var5 = h0Var5.f6780a;
            j2.m mVar5 = b0.f6732d;
            mVar = a0Var4.f6709a;
            mVar2 = a0Var5.f6709a;
            z2 = mVar instanceof j2.b;
            bVar = j2.l.f3327a;
            f5 = this.f1552g;
            if (z2) {
                if (z2) {
                    bVar = (j2.m) b0.b(mVar, mVar2, f5);
                } else {
                    bVar = (j2.m) b0.b(mVar, mVar2, f5);
                }
            } else if (z2) {
                bVar = (j2.m) b0.b(mVar, mVar2, f5);
            } else {
                bVar = (j2.m) b0.b(mVar, mVar2, f5);
            }
            j2.m mVar6 = bVar;
            d2.p pVar4 = (d2.p) b0.b(a0Var4.f6714f, a0Var5.f6714f, f5);
            long jC4 = b0.c(f5, a0Var4.f6710b, a0Var5.f6710b);
            kVar = a0Var4.f6711c;
            if (kVar == null) {
                kVar = d2.k.f1613f;
            }
            kVar2 = a0Var5.f6711c;
            if (kVar2 == null) {
                kVar2 = d2.k.f1613f;
            }
            d2.k kVar4 = new d2.k(d4.a.m(a.a.E(f5, kVar.f1615d, kVar2.f1615d), 1, 1000));
            d2.i iVar2 = (d2.i) b0.b(a0Var4.f6712d, a0Var5.f6712d, f5);
            d2.j jVar3 = (d2.j) b0.b(a0Var4.f6713e, a0Var5.f6713e, f5);
            String str2 = (String) b0.b(a0Var4.f6715g, a0Var5.f6715g, f5);
            long jC5 = b0.c(f5, a0Var4.f6716h, a0Var5.f6716h);
            aVar = a0Var4.f6717i;
            if (aVar != null) {
                f6 = aVar.f3305a;
            } else {
                f6 = 0.0f;
            }
            j2.a aVar3 = a0Var5.f6717i;
            float fD3 = a.a.D(f6, aVar3 != null ? aVar3.f3305a : 0.0f, f5);
            nVar = a0Var4.f6718j;
            nVar2 = j2.n.f3328c;
            if (nVar == null) {
                nVar = nVar2;
            }
            nVar3 = a0Var5.f6718j;
            if (nVar3 != null) {
                nVar2 = nVar3;
            }
            j2.n nVar5 = new j2.n(a.a.D(nVar.f3329a, nVar2.f3329a, f5), a.a.D(nVar.f3330b, nVar2.f3330b, f5));
            f2.b bVar5 = (f2.b) b0.b(a0Var4.f6719k, a0Var5.f6719k, f5);
            long jO4 = l0.o(f5, a0Var4.f6720l, a0Var5.f6720l);
            j2.j jVar4 = (j2.j) b0.b(a0Var4.f6721m, a0Var5.f6721m, f5);
            p0Var = a0Var4.f6722n;
            if (p0Var == null) {
                p0Var = new p0();
            }
            p0Var2 = a0Var5.f6722n;
            if (p0Var2 == null) {
                p0Var2 = new p0();
            }
            long jO5 = l0.o(f5, p0Var.f6669a, p0Var2.f6669a);
            long j7 = p0Var.f6670b;
            long j8 = p0Var2.f6670b;
            p0 p0Var4 = new p0(a.a.D(p0Var.f6671c, p0Var2.f6671c, f5), jO5, (((long) Float.floatToRawIntBits(a.a.D(Float.intBitsToFloat((int) (j7 >> 32)), Float.intBitsToFloat((int) (j8 >> 32)), f5))) << 32) | (((long) Float.floatToRawIntBits(a.a.D(Float.intBitsToFloat((int) (j7 & 4294967295L)), Float.intBitsToFloat((int) (j8 & 4294967295L)), f5))) & 4294967295L));
            uVar = a0Var4.f6723o;
            y1.u uVar4 = a0Var5.f6723o;
            if (uVar == null) {
                if (uVar == null) {
                    uVar = y1.u.f6822a;
                }
                uVar2 = uVar;
            } else {
                if (uVar == null) {
                    uVar = y1.u.f6822a;
                }
                uVar2 = uVar;
            }
            a0 a0Var6 = new a0(mVar6, jC4, kVar4, iVar2, jVar3, pVar4, str2, jC5, new j2.a(fD3), nVar5, bVar5, jO4, jVar4, p0Var4, uVar2, (a1.g) b0.b(a0Var4.f6724p, a0Var5.f6724p, f5));
            y1.r rVar3 = h0Var4.f6781b;
            y1.r rVar4 = h0Var5.f6781b;
            int i8 = y1.s.f6819b;
            int i9 = ((j2.i) b0.b(new j2.i(rVar3.f6809a), new j2.i(rVar4.f6809a), f5)).f3321a;
            int i10 = ((j2.k) b0.b(new j2.k(rVar3.f6810b), new j2.k(rVar4.f6810b), f5)).f3326a;
            long jC6 = b0.c(f5, rVar3.f6811c, rVar4.f6811c);
            oVar = rVar3.f6812d;
            if (oVar == null) {
                oVar = j2.o.f3331c;
            }
            oVar2 = rVar4.f6812d;
            if (oVar2 == null) {
                oVar2 = j2.o.f3331c;
            }
            j2.o oVar4 = new j2.o(b0.c(f5, oVar.f3332a, oVar2.f3332a), b0.c(f5, oVar.f3333b, oVar2.f3333b));
            tVar = rVar3.f6813e;
            tVar2 = rVar4.f6813e;
            if (tVar == null) {
                tVar3 = y1.t.f6820b;
                if (tVar == null) {
                    tVar4 = tVar3;
                } else {
                    tVar4 = tVar;
                }
                z3 = tVar4.f6821a;
                if (tVar2 == null) {
                    tVar2 = tVar3;
                }
                z4 = tVar2.f6821a;
                if (z3 != z4) {
                    ((y1.i) b0.b(new y1.i(), new y1.i(), f5)).getClass();
                    tVar4 = new y1.t(((Boolean) b0.b(Boolean.valueOf(z3), Boolean.valueOf(z4), f5)).booleanValue());
                }
                tVar5 = tVar4;
            } else {
                tVar3 = y1.t.f6820b;
                if (tVar == null) {
                    tVar4 = tVar3;
                } else {
                    tVar4 = tVar;
                }
                z3 = tVar4.f6821a;
                if (tVar2 == null) {
                    tVar2 = tVar3;
                }
                z4 = tVar2.f6821a;
                if (z3 != z4) {
                    ((y1.i) b0.b(new y1.i(), new y1.i(), f5)).getClass();
                    tVar4 = new y1.t(((Boolean) b0.b(Boolean.valueOf(z3), Boolean.valueOf(z4), f5)).booleanValue());
                }
                tVar5 = tVar4;
            }
            h0Var = new h0(a0Var6, new y1.r(i9, i10, jC6, oVar4, tVar5, (j2.g) b0.b(rVar3.f6814f, rVar4.f6814f, f5), ((j2.e) b0.b(new j2.e(rVar3.f6815g), new j2.e(rVar4.f6815g), f5)).f3311a, ((j2.d) b0.b(new j2.d(rVar3.f6816h), new j2.d(rVar4.f6816h), f5)).f3309a, (j2.p) b0.b(rVar3.f6817i, rVar4.f6817i, f5)));
            if (this.f1555j) {
                h0Var = h0.a(h0Var, ((y0.u) this.f1556k.getValue()).f6696a, 0L, null, null, 0L, 0L, null, 16777214);
            }
            u.b(((y0.u) this.f1553h.getValue()).f6696a, h0Var, this.f1554i, pVar, 0);
        }
        return p3.n.f5026a;
    }
}
