package y;

import b4.l;
import b4.m;
import java.util.List;
import k2.k;
import q3.s;
import y0.u;
import y1.d0;
import y1.e0;
import y1.h0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6603e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ g f6604f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(g gVar, int i5) {
        super(1);
        this.f6603e = i5;
        this.f6604f = gVar;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x00cb  */
    @Override // a4.c
    public final Object c(Object obj) {
        k2.b bVar;
        e0 e0Var;
        switch (this.f6603e) {
            case 0:
                List list = (List) obj;
                g gVar = this.f6604f;
                d dVarY0 = gVar.y0();
                h0 h0VarE = h0.e(gVar.f6606r, u.f6694g, 0L, 0L, 0, 0L, 16777214);
                k kVar = dVarY0.f6595o;
                e0 e0Var2 = null;
                if (kVar == null || (bVar = dVarY0.f6589i) == null) {
                    e0Var = null;
                } else {
                    y1.f fVar = new y1.f(dVarY0.f6581a, null, 6);
                    if (dVarY0.f6590j == null || dVarY0.f6594n == null) {
                        e0Var = null;
                    } else {
                        long jB = k2.a.b(dVarY0.f6596p, 0, 0, 0, 0, 10);
                        int i5 = dVarY0.f6586f;
                        boolean z2 = dVarY0.f6585e;
                        int i6 = dVarY0.f6584d;
                        d2.d dVar = dVarY0.f6583c;
                        s sVar = s.f5477d;
                        e0Var = new e0(new d0(fVar, h0VarE, sVar, i5, z2, i6, bVar, kVar, dVar, jB), new y1.m(new f3.a(fVar, h0VarE, sVar, bVar, dVar), jB, dVarY0.f6586f, dVarY0.f6584d == 2), dVarY0.f6592l);
                    }
                }
                if (e0Var != null) {
                    list.add(e0Var);
                    e0Var2 = e0Var;
                }
                return Boolean.valueOf(e0Var2 != null);
            case 1:
                String str = ((y1.f) obj).f6759d;
                g gVar2 = this.f6604f;
                e eVar = gVar2.A;
                if (eVar == null) {
                    e eVar2 = new e(gVar2.f6605q, str);
                    d dVar2 = new d(str, gVar2.f6606r, gVar2.f6607s, gVar2.f6608t, gVar2.f6609u, gVar2.f6610v, gVar2.f6611w);
                    dVar2.c(gVar2.y0().f6589i);
                    eVar2.f6602d = dVar2;
                    gVar2.A = eVar2;
                } else if (!l.a(str, eVar.f6600b)) {
                    eVar.f6600b = str;
                    d dVar3 = eVar.f6602d;
                    if (dVar3 != null) {
                        h0 h0Var = gVar2.f6606r;
                        d2.d dVar4 = gVar2.f6607s;
                        int i7 = gVar2.f6608t;
                        boolean z3 = gVar2.f6609u;
                        int i8 = gVar2.f6610v;
                        int i9 = gVar2.f6611w;
                        dVar3.f6581a = str;
                        dVar3.f6582b = h0Var;
                        dVar3.f6583c = dVar4;
                        dVar3.f6584d = i7;
                        dVar3.f6585e = z3;
                        dVar3.f6586f = i8;
                        dVar3.f6587g = i9;
                        dVar3.f6590j = null;
                        dVar3.f6594n = null;
                        dVar3.f6595o = null;
                        dVar3.f6597q = -1;
                        dVar3.f6598r = -1;
                        dVar3.f6596p = h1.c.r(0, 0, 0, 0);
                        dVar3.f6592l = a.a.f(0, 0);
                        dVar3.f6591k = false;
                    }
                }
                p1.f.n(gVar2);
                p1.f.m(gVar2);
                p1.f.l(gVar2);
                return Boolean.TRUE;
            default:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                g gVar3 = this.f6604f;
                e eVar3 = gVar3.A;
                if (eVar3 == null) {
                    return Boolean.FALSE;
                }
                eVar3.f6601c = zBooleanValue;
                p1.f.n(gVar3);
                p1.f.m(gVar3);
                p1.f.l(gVar3);
                return Boolean.TRUE;
        }
    }
}
