package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class i4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m.z0 f934a;

    static {
        m.t tVar = m.w.f3797a;
        m.d.h(250, 2, tVar);
        f934a = m.d.h(250, 2, tVar);
    }

    public static final void a(int i5, r0.p pVar, long j5, long j6, a4.f fVar, a4.e eVar, n0.d dVar, f0.p pVar2, int i6) {
        a4.e eVar2;
        long j7;
        long j8;
        r0.p pVar3;
        a4.f fVar2;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-1199178586);
        if (((i6 | (vVar.d(i5) ? 4 : 2) | 222384) & 599187) == 599186 && vVar.z()) {
            vVar.Q();
            pVar3 = pVar;
            j8 = j5;
            j7 = j6;
            fVar2 = fVar;
            eVar2 = eVar;
        } else {
            vVar.S();
            if ((i6 & 1) == 0 || vVar.x()) {
                long jD = u.d(vVar, e0.l.f1770b);
                long jD2 = u.d(vVar, e0.l.f1772d);
                n0.d dVarC = n0.i.c(-2052073983, new e4(i5), vVar);
                eVar2 = a0.f723a;
                j7 = jD2;
                j8 = jD;
                pVar3 = r0.m.f5680a;
                fVar2 = dVarC;
            } else {
                vVar.Q();
                pVar3 = pVar;
                j8 = j5;
                j7 = j6;
                fVar2 = fVar;
                eVar2 = eVar;
            }
            vVar.q();
            b(pVar3, j8, j7, fVar2, eVar2, dVar, vVar, 224262);
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new f4(i5, pVar3, j8, j7, fVar2, eVar2, dVar, i6);
        }
    }

    public static final void b(r0.p pVar, long j5, long j6, a4.f fVar, a4.e eVar, n0.d dVar, f0.p pVar2, int i5) {
        int i6;
        f0.v vVar;
        f0.v vVar2 = (f0.v) pVar2;
        vVar2.X(-160898917);
        if ((i5 & 6) == 0) {
            i6 = (vVar2.f(pVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar2.e(j5) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar2.e(j6) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar2.h(fVar) ? 2048 : 1024;
        }
        if ((i5 & 24576) == 0) {
            i6 |= vVar2.h(eVar) ? 16384 : 8192;
        }
        if ((196608 & i5) == 0) {
            i6 |= vVar2.h(dVar) ? 131072 : 65536;
        }
        if ((74899 & i6) == 74898 && vVar2.z()) {
            vVar2.Q();
            vVar = vVar2;
        } else {
            int i7 = i6 << 3;
            vVar = vVar2;
            o3.a(w1.k.a(pVar, t.a.f5776e), null, j5, j6, 0.0f, 0.0f, n0.i.c(-1617702432, new h4(dVar, eVar, fVar, 1), vVar2), vVar, (i7 & 896) | 12582912 | (i7 & 7168), 114);
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new f4(pVar, j5, j6, fVar, eVar, dVar, i5);
        }
    }
}
