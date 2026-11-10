package v;

import c0.s3;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ o0 f5970e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ y1.h0 f5971f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f5972g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f5973h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ c1 f5974i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ e2.x f5975j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ e2.f0 f5976k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r0.p f5977l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r0.p f5978m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r0.p f5979n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r0.p f5980o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ s.c f5981p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ z.e0 f5982q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f5983r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ a4.c f5984s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ e2.r f5985t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ k2.b f5986u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(o0 o0Var, y1.h0 h0Var, int i5, int i6, c1 c1Var, e2.x xVar, e2.f0 f0Var, r0.p pVar, r0.p pVar2, r0.p pVar3, r0.p pVar4, s.c cVar, z.e0 e0Var, boolean z2, a4.c cVar2, e2.r rVar, k2.b bVar) {
        super(2);
        this.f5970e = o0Var;
        this.f5971f = h0Var;
        this.f5972g = i5;
        this.f5973h = i6;
        this.f5974i = c1Var;
        this.f5975j = xVar;
        this.f5976k = f0Var;
        this.f5977l = pVar;
        this.f5978m = pVar2;
        this.f5979n = pVar3;
        this.f5980o = pVar4;
        this.f5981p = cVar;
        this.f5982q = e0Var;
        this.f5983r = z2;
        this.f5984s = cVar2;
        this.f5985t = rVar;
        this.f5986u = bVar;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0059  */
    /* JADX WARN: Code duplicated, block: B:22:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:24:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:25:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:27:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:8:0x0022  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        o0 o0Var;
        long j5;
        f0.v vVar;
        boolean zH;
        Object objK;
        a4.a aVar;
        c1 c1Var;
        int iE;
        long j6;
        e2.e0 e0VarL;
        int iOrdinal;
        r0.p g1Var;
        f0.p pVar = (f0.p) obj;
        if ((((Number) obj2).intValue() & 3) == 2) {
            f0.v vVar2 = (f0.v) pVar;
            if (vVar2.z()) {
                vVar2.Q();
            } else {
                o0Var = this.f5970e;
                r0.p pVarE = androidx.compose.foundation.layout.c.e(r0.m.f5680a, ((k2.e) o0Var.f6012g.getValue()).f3406d, Float.NaN);
                int i5 = this.f5972g;
                int i6 = this.f5973h;
                y1.h0 h0Var = this.f5971f;
                r0.p pVarA = r0.a.a(pVarE, new g0(i5, i6, h0Var));
                e2.x xVar = this.f5975j;
                j5 = xVar.f2063b;
                vVar = (f0.v) pVar;
                zH = vVar.h(o0Var);
                objK = vVar.K();
                if (zH || objK == f0.o.f2281a) {
                    objK = new b0.a(25, o0Var);
                    vVar.f0(objK);
                }
                aVar = (a4.a) objK;
                c1Var = this.f5974i;
                p.l0 l0Var = (p.l0) c1Var.f5873e.getValue();
                int i7 = y1.g0.f6769c;
                iE = (int) (j5 >> 32);
                j6 = c1Var.f5872d;
                if (iE == ((int) (j6 >> 32)) && (iE = (int) (j5 & 4294967295L)) == ((int) (j6 & 4294967295L))) {
                    iE = y1.g0.e(j5);
                }
                c1Var.f5872d = j5;
                e0VarL = l0.l(this.f5976k, xVar.f2062a);
                iOrdinal = l0Var.ordinal();
                if (iOrdinal != 0) {
                    g1Var = new g1(c1Var, iE, e0VarL, aVar);
                } else {
                    if (iOrdinal == 1) {
                        throw new i4.p();
                    }
                    g1Var = new h0(c1Var, iE, e0VarL, aVar);
                }
                p3.j.e(androidx.compose.foundation.relocation.a.a(r0.a.a(q.f.k(pVarA).e(g1Var).e(this.f5977l).e(this.f5978m), new s3(5, h0Var)).e(this.f5979n).e(this.f5980o), this.f5981p), n0.i.c(-363167407, new m(this.f5982q, o0Var, this.f5983r, this.f5984s, xVar, this.f5985t, this.f5986u, this.f5973h), vVar), vVar, 48);
            }
        } else {
            o0Var = this.f5970e;
            r0.p pVarE2 = androidx.compose.foundation.layout.c.e(r0.m.f5680a, ((k2.e) o0Var.f6012g.getValue()).f3406d, Float.NaN);
            int i8 = this.f5972g;
            int i9 = this.f5973h;
            y1.h0 h0Var2 = this.f5971f;
            r0.p pVarA2 = r0.a.a(pVarE2, new g0(i8, i9, h0Var2));
            e2.x xVar2 = this.f5975j;
            j5 = xVar2.f2063b;
            vVar = (f0.v) pVar;
            zH = vVar.h(o0Var);
            objK = vVar.K();
            if (zH) {
                objK = new b0.a(25, o0Var);
                vVar.f0(objK);
            } else {
                objK = new b0.a(25, o0Var);
                vVar.f0(objK);
            }
            aVar = (a4.a) objK;
            c1Var = this.f5974i;
            p.l0 l0Var2 = (p.l0) c1Var.f5873e.getValue();
            int i10 = y1.g0.f6769c;
            iE = (int) (j5 >> 32);
            j6 = c1Var.f5872d;
            if (iE == ((int) (j6 >> 32))) {
                iE = y1.g0.e(j5);
            }
            c1Var.f5872d = j5;
            e0VarL = l0.l(this.f5976k, xVar2.f2062a);
            iOrdinal = l0Var2.ordinal();
            if (iOrdinal != 0) {
                g1Var = new g1(c1Var, iE, e0VarL, aVar);
            } else {
                if (iOrdinal == 1) {
                    throw new i4.p();
                }
                g1Var = new h0(c1Var, iE, e0VarL, aVar);
            }
            p3.j.e(androidx.compose.foundation.relocation.a.a(r0.a.a(q.f.k(pVarA2).e(g1Var).e(this.f5977l).e(this.f5978m), new s3(5, h0Var2)).e(this.f5979n).e(this.f5980o), this.f5981p), n0.i.c(-363167407, new m(this.f5982q, o0Var, this.f5983r, this.f5984s, xVar2, this.f5985t, this.f5986u, this.f5973h), vVar), vVar, 48);
        }
        return p3.n.f5026a;
    }
}
