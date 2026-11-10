package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class t0 {
    static {
        new f0.e1(t.f1300k);
    }

    public static final void a(s sVar, r2 r2Var, s4 s4Var, a4.e eVar, f0.p pVar, int i5) {
        int i6;
        r2 r2Var2;
        s4 s4Var2;
        s4 s4Var3;
        r2 r2Var3;
        f0.v vVar = (f0.v) pVar;
        vVar.X(-2127166334);
        if ((i5 & 6) == 0) {
            i6 = (vVar.f(sVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar.h(eVar) ? 2048 : 1024;
        }
        if ((i6 & 1171) == 1170 && vVar.z()) {
            vVar.Q();
            r2Var3 = r2Var;
            s4Var3 = s4Var;
        } else {
            vVar.S();
            if ((i5 & 1) == 0 || vVar.x()) {
                r2Var2 = (r2) vVar.j(s2.f1277a);
                s4Var2 = (s4) vVar.j(t4.f1328a);
            } else {
                vVar.Q();
                r2Var2 = r2Var;
                s4Var2 = s4Var;
            }
            vVar.q();
            n.i0 i0VarA = i2.a(false, 0.0f, 0L, vVar, 0, 7);
            long j5 = sVar.f1244a;
            boolean zE = vVar.e(j5);
            Object objK = vVar.K();
            if (zE || objK == f0.o.f2281a) {
                objK = new z.k0(j5, y0.u.b(0.4f, j5));
                vVar.f0(objK);
            }
            f0.l.b(new f0.z1[]{u.f1329a.a(sVar), androidx.compose.foundation.d.f78a.a(i0VarA), b0.x.f464a.a(v.f1359a), s2.f1277a.a(r2Var2), z.l0.f6977a.a((z.k0) objK), t4.f1328a.a(s4Var2)}, n0.i.c(-1066563262, new l(s4Var2, 1, eVar), vVar), vVar, 56);
            s4Var3 = s4Var2;
            r2Var3 = r2Var2;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new s0(sVar, r2Var3, s4Var3, eVar, i5, 0);
        }
    }
}
