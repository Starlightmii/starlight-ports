package c0;

import androidx.compose.material3.MinimumInteractiveModifier;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n3 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ r0.p f1111e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ y0.q0 f1112f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ long f1113g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ float f1114h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ q.k f1115i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ boolean f1116j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ a4.a f1117k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ float f1118l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n0.d f1119m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n3(r0.p pVar, y0.q0 q0Var, long j5, float f5, q.k kVar, boolean z2, a4.a aVar, float f6, n0.d dVar) {
        super(2);
        this.f1111e = pVar;
        this.f1112f = q0Var;
        this.f1113g = j5;
        this.f1114h = f5;
        this.f1115i = kVar;
        this.f1116j = z2;
        this.f1117k = aVar;
        this.f1118l = f6;
        this.f1119m = dVar;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x008c  */
    /* JADX WARN: Code duplicated, block: B:11:0x0090  */
    /* JADX WARN: Code duplicated, block: B:16:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:8:0x0022  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        f0.v vVar;
        int iHashCode;
        p1.n nVar;
        p1.h hVar;
        f0.p pVar = (f0.p) obj;
        if ((((Number) obj2).intValue() & 3) == 2) {
            f0.v vVar2 = (f0.v) pVar;
            if (vVar2.z()) {
                vVar2.Q();
            } else {
                f0.h3 h3Var = r0.f1218a;
                vVar = (f0.v) pVar;
                r0.p pVarD = androidx.compose.foundation.a.d(o3.b(this.f1111e.e(MinimumInteractiveModifier.f149a), this.f1112f, o3.c(this.f1113g, this.f1114h, pVar), null, ((k2.b) vVar.j(q1.j1.f5237f)).v(this.f1118l)), this.f1115i, i2.a(false, 0.0f, 0L, vVar, 0, 7), this.f1116j, null, this.f1117k, 24);
                n1.f0 f0VarE = r.o.e(r0.b.f5653d, true);
                iHashCode = Long.hashCode(vVar.S);
                f0.w1 w1VarL = vVar.l();
                r0.p pVarC = r0.a.c(vVar, pVarD);
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(nVar);
                } else {
                    vVar.i0();
                }
                f0.l.x(p1.i.f4870e, vVar, f0VarE);
                f0.l.x(p1.i.f4869d, vVar, w1VarL);
                hVar = p1.i.f4871f;
                if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                    b.b.i(iHashCode, vVar, iHashCode, hVar);
                }
                f0.l.x(p1.i.f4868c, vVar, pVarC);
                this.f1119m.invoke(vVar, 0);
                vVar.p(true);
            }
        } else {
            f0.h3 h3Var2 = r0.f1218a;
            vVar = (f0.v) pVar;
            r0.p pVarD2 = androidx.compose.foundation.a.d(o3.b(this.f1111e.e(MinimumInteractiveModifier.f149a), this.f1112f, o3.c(this.f1113g, this.f1114h, pVar), null, ((k2.b) vVar.j(q1.j1.f5237f)).v(this.f1118l)), this.f1115i, i2.a(false, 0.0f, 0L, vVar, 0, 7), this.f1116j, null, this.f1117k, 24);
            n1.f0 f0VarE2 = r.o.e(r0.b.f5653d, true);
            iHashCode = Long.hashCode(vVar.S);
            f0.w1 w1VarL2 = vVar.l();
            r0.p pVarC2 = r0.a.c(vVar, pVarD2);
            p1.j.f4892c.getClass();
            nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, f0VarE2);
            f0.l.x(p1.i.f4869d, vVar, w1VarL2);
            hVar = p1.i.f4871f;
            if (vVar.R) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            } else {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            f0.l.x(p1.i.f4868c, vVar, pVarC2);
            this.f1119m.invoke(vVar, 0);
            vVar.p(true);
        }
        return p3.n.f5026a;
    }
}
