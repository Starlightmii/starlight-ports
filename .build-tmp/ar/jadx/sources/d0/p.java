package d0;

import f0.w1;
import n1.f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1557e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f1558f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f1559g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(int i5, long j5, Object obj) {
        super(2);
        this.f1557e = i5;
        this.f1558f = j5;
        this.f1559g = obj;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0021  */
    /* JADX WARN: Code duplicated, block: B:12:0x002d  */
    /* JADX WARN: Code duplicated, block: B:14:0x0079  */
    /* JADX WARN: Code duplicated, block: B:15:0x007d  */
    /* JADX WARN: Code duplicated, block: B:20:0x009e  */
    /* JADX WARN: Code duplicated, block: B:22:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:31:0x00e2  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        long j5;
        f0.v vVar;
        int iHashCode;
        p1.n nVar;
        p1.h hVar;
        switch (this.f1557e) {
            case 0:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar2 = (f0.v) pVar;
                    if (vVar2.z()) {
                        vVar2.Q();
                    } else {
                        u.c(this.f1558f, (a4.e) this.f1559g, pVar, 0);
                    }
                } else {
                    u.c(this.f1558f, (a4.e) this.f1559g, pVar, 0);
                }
                break;
            default:
                f0.p pVar2 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar3 = (f0.v) pVar2;
                    if (vVar3.z()) {
                        vVar3.Q();
                    } else {
                        j5 = this.f1558f;
                        if (j5 != 9205357640488583168L) {
                            vVar = (f0.v) pVar2;
                            vVar.V(1828881000);
                            r0.p pVarG = androidx.compose.foundation.layout.c.g((r0.p) this.f1559g, Float.intBitsToFloat((int) (j5 >> 32)), Float.intBitsToFloat((int) (j5 & 4294967295L)), 0.0f, 0.0f, 12);
                            f0 f0VarE = r.o.e(r0.b.f5654e, false);
                            iHashCode = Long.hashCode(vVar.S);
                            w1 w1VarL = vVar.l();
                            r0.p pVarC = r0.a.c(vVar, pVarG);
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
                            v.d.b(null, vVar, 0, 1);
                            vVar.p(true);
                            vVar.p(false);
                        } else {
                            f0.v vVar4 = (f0.v) pVar2;
                            vVar4.V(1829217412);
                            v.d.b((r0.p) this.f1559g, vVar4, 0, 0);
                            vVar4.p(false);
                        }
                    }
                } else {
                    j5 = this.f1558f;
                    if (j5 != 9205357640488583168L) {
                        vVar = (f0.v) pVar2;
                        vVar.V(1828881000);
                        r0.p pVarG2 = androidx.compose.foundation.layout.c.g((r0.p) this.f1559g, Float.intBitsToFloat((int) (j5 >> 32)), Float.intBitsToFloat((int) (j5 & 4294967295L)), 0.0f, 0.0f, 12);
                        f0 f0VarE2 = r.o.e(r0.b.f5654e, false);
                        iHashCode = Long.hashCode(vVar.S);
                        w1 w1VarL2 = vVar.l();
                        r0.p pVarC2 = r0.a.c(vVar, pVarG2);
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
                        v.d.b(null, vVar, 0, 1);
                        vVar.p(true);
                        vVar.p(false);
                    } else {
                        f0.v vVar5 = (f0.v) pVar2;
                        vVar5.V(1829217412);
                        v.d.b((r0.p) this.f1559g, vVar5, 0, 0);
                        vVar5.p(false);
                    }
                }
                break;
        }
        return p3.n.f5026a;
    }
}
