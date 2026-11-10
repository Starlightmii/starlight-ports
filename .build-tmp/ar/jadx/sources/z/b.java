package z;

import f0.w1;
import r.q0;
import r.s0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ long f6895e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f6896f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ r0.p f6897g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ l f6898h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(long j5, boolean z2, r0.p pVar, l lVar) {
        super(2);
        this.f6895e = j5;
        this.f6896f = z2;
        this.f6897g = pVar;
        this.f6898h = lVar;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x002e  */
    /* JADX WARN: Code duplicated, block: B:12:0x0038  */
    /* JADX WARN: Code duplicated, block: B:13:0x003b  */
    /* JADX WARN: Code duplicated, block: B:16:0x007d  */
    /* JADX WARN: Code duplicated, block: B:17:0x0081  */
    /* JADX WARN: Code duplicated, block: B:22:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:26:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:28:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:31:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:8:0x001c  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        long j5;
        l lVar;
        boolean z2;
        f0.v vVar;
        boolean zH;
        Object objK;
        f0.v vVar2;
        r.b bVar;
        int iHashCode;
        p1.n nVar;
        p1.h hVar;
        boolean zH2;
        Object objK2;
        f0.p pVar = (f0.p) obj;
        if ((((Number) obj2).intValue() & 3) == 2) {
            f0.v vVar3 = (f0.v) pVar;
            if (vVar3.z()) {
                vVar3.Q();
            } else {
                j5 = this.f6895e;
                f0.h hVar2 = f0.o.f2281a;
                lVar = this.f6898h;
                z2 = this.f6896f;
                if (j5 != 9205357640488583168L) {
                    vVar2 = (f0.v) pVar;
                    vVar2.V(-837727128);
                    if (z2) {
                        bVar = r.c.f5530b;
                    } else {
                        bVar = r.c.f5529a;
                    }
                    r0.p pVarG = androidx.compose.foundation.layout.c.g(this.f6897g, Float.intBitsToFloat((int) (j5 >> 32)), Float.intBitsToFloat((int) (j5 & 4294967295L)), 0.0f, 0.0f, 12);
                    s0 s0VarA = q0.a(bVar, r0.b.f5662m, vVar2, 0);
                    iHashCode = Long.hashCode(vVar2.S);
                    w1 w1VarL = vVar2.l();
                    r0.p pVarC = r0.a.c(vVar2, pVarG);
                    p1.j.f4892c.getClass();
                    nVar = p1.i.f4867b;
                    vVar2.Y();
                    if (vVar2.R) {
                        vVar2.k(nVar);
                    } else {
                        vVar2.i0();
                    }
                    f0.l.x(p1.i.f4870e, vVar2, s0VarA);
                    f0.l.x(p1.i.f4869d, vVar2, w1VarL);
                    hVar = p1.i.f4871f;
                    if (vVar2.R || !b4.l.a(vVar2.K(), Integer.valueOf(iHashCode))) {
                        b.b.i(iHashCode, vVar2, iHashCode, hVar);
                    }
                    f0.l.x(p1.i.f4868c, vVar2, pVarC);
                    zH2 = vVar2.h(lVar);
                    objK2 = vVar2.K();
                    if (zH2 || objK2 == hVar2) {
                        objK2 = new a(lVar, 0);
                        vVar2.f0(objK2);
                    }
                    p3.j.d(r0.m.f5680a, (a4.a) objK2, z2, vVar2, 6);
                    vVar2.p(true);
                    vVar2.p(false);
                } else {
                    vVar = (f0.v) pVar;
                    vVar.V(-836867312);
                    zH = vVar.h(lVar);
                    objK = vVar.K();
                    if (zH || objK == hVar2) {
                        objK = new a(lVar, 1);
                        vVar.f0(objK);
                    }
                    p3.j.d(this.f6897g, (a4.a) objK, z2, vVar, 0);
                    vVar.p(false);
                }
            }
        } else {
            j5 = this.f6895e;
            f0.h hVar3 = f0.o.f2281a;
            lVar = this.f6898h;
            z2 = this.f6896f;
            if (j5 != 9205357640488583168L) {
                vVar2 = (f0.v) pVar;
                vVar2.V(-837727128);
                if (z2) {
                    bVar = r.c.f5530b;
                } else {
                    bVar = r.c.f5529a;
                }
                r0.p pVarG2 = androidx.compose.foundation.layout.c.g(this.f6897g, Float.intBitsToFloat((int) (j5 >> 32)), Float.intBitsToFloat((int) (j5 & 4294967295L)), 0.0f, 0.0f, 12);
                s0 s0VarA2 = q0.a(bVar, r0.b.f5662m, vVar2, 0);
                iHashCode = Long.hashCode(vVar2.S);
                w1 w1VarL2 = vVar2.l();
                r0.p pVarC2 = r0.a.c(vVar2, pVarG2);
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar2.Y();
                if (vVar2.R) {
                    vVar2.k(nVar);
                } else {
                    vVar2.i0();
                }
                f0.l.x(p1.i.f4870e, vVar2, s0VarA2);
                f0.l.x(p1.i.f4869d, vVar2, w1VarL2);
                hVar = p1.i.f4871f;
                if (vVar2.R) {
                    b.b.i(iHashCode, vVar2, iHashCode, hVar);
                } else {
                    b.b.i(iHashCode, vVar2, iHashCode, hVar);
                }
                f0.l.x(p1.i.f4868c, vVar2, pVarC2);
                zH2 = vVar2.h(lVar);
                objK2 = vVar2.K();
                if (zH2) {
                    objK2 = new a(lVar, 0);
                    vVar2.f0(objK2);
                } else {
                    objK2 = new a(lVar, 0);
                    vVar2.f0(objK2);
                }
                p3.j.d(r0.m.f5680a, (a4.a) objK2, z2, vVar2, 6);
                vVar2.p(true);
                vVar2.p(false);
            } else {
                vVar = (f0.v) pVar;
                vVar.V(-836867312);
                zH = vVar.h(lVar);
                objK = vVar.K();
                if (zH) {
                    objK = new a(lVar, 1);
                    vVar.f0(objK);
                } else {
                    objK = new a(lVar, 1);
                    vVar.f0(objK);
                }
                p3.j.d(this.f6897g, (a4.a) objK, z2, vVar, 0);
                vVar.p(false);
            }
        }
        return p3.n.f5026a;
    }
}
