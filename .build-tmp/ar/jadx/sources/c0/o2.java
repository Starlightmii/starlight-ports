package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class o2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f1133a = 16;

    public static final void a(r0.p pVar, n0.d dVar, n0.d dVar2, n0.d dVar3, a4.e eVar, int i5, long j5, long j6, r.z0 z0Var, n0.d dVar4, f0.p pVar2, int i6) {
        long jB;
        int i7;
        a4.e eVar2;
        int i8;
        int i9;
        long j7;
        a4.e eVar3;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-1219521777);
        int i10 = i6 | 221184 | (vVar.e(j5) ? 1048576 : 524288) | 4194304 | (vVar.f(z0Var) ? 67108864 : 33554432);
        if ((306783379 & i10) == 306783378 && vVar.z()) {
            vVar.Q();
            eVar3 = eVar;
            i9 = i5;
            j7 = j6;
        } else {
            vVar.S();
            int i11 = 2;
            if ((i6 & 1) == 0 || vVar.x()) {
                n0.d dVar5 = x.f1412a;
                jB = u.b(j5, vVar);
                i7 = i10 & (-29360129);
                eVar2 = dVar5;
                i8 = 2;
            } else {
                vVar.Q();
                i7 = i10 & (-29360129);
                eVar2 = eVar;
                i8 = i5;
                jB = j6;
            }
            vVar.q();
            int i12 = (234881024 & i7) ^ 100663296;
            int i13 = 1;
            boolean z2 = (i12 > 67108864 && vVar.f(z0Var)) || (i7 & 100663296) == 67108864;
            Object objK = vVar.K();
            f0.h hVar = f0.o.f2281a;
            if (z2 || objK == hVar) {
                objK = new d0.k(z0Var);
                vVar.f0(objK);
            }
            d0.k kVar = (d0.k) objK;
            boolean zF = vVar.f(kVar) | ((i12 > 67108864 && vVar.f(z0Var)) || (i7 & 100663296) == 67108864);
            Object objK2 = vVar.K();
            if (zF || objK2 == hVar) {
                objK2 = new c2(kVar, i13, z0Var);
                vVar.f0(objK2);
            }
            o1.g gVar = r.c1.f5542a;
            long j8 = jB;
            o3.a(r0.a.a(pVar, new s3(i11, (a4.c) objK2)), null, j5, j8, 0.0f, 0.0f, n0.i.c(-1979205334, new k2(i8, dVar, dVar4, dVar3, eVar2, kVar, dVar2), vVar), vVar, ((i7 >> 12) & 896) | 12582912, 114);
            i9 = i8;
            j7 = j8;
            eVar3 = eVar2;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new l2(pVar, dVar, dVar2, dVar3, eVar3, i9, j5, j7, z0Var, dVar4, i6);
        }
    }

    public static final void b(int i5, n0.d dVar, n0.d dVar2, n0.d dVar3, a4.e eVar, r.z0 z0Var, n0.d dVar4, f0.p pVar, int i6) {
        int i7;
        n0.d dVar5;
        r.z0 z0Var2;
        n0.d dVar6;
        f0.v vVar = (f0.v) pVar;
        vVar.X(-975511942);
        if ((i6 & 6) == 0) {
            i7 = (vVar.d(i5) ? 4 : 2) | i6;
        } else {
            i7 = i6;
        }
        if ((i6 & 48) == 0) {
            i7 |= vVar.h(dVar) ? 32 : 16;
        }
        if ((i6 & 384) == 0) {
            dVar5 = dVar2;
            i7 |= vVar.h(dVar5) ? 256 : 128;
        } else {
            dVar5 = dVar2;
        }
        if ((i6 & 3072) == 0) {
            i7 |= vVar.h(dVar3) ? 2048 : 1024;
        }
        if ((i6 & 24576) == 0) {
            i7 |= vVar.h(eVar) ? 16384 : 8192;
        }
        if ((196608 & i6) == 0) {
            z0Var2 = z0Var;
            i7 |= vVar.f(z0Var2) ? 131072 : 65536;
        } else {
            z0Var2 = z0Var;
        }
        if ((1572864 & i6) == 0) {
            dVar6 = dVar4;
            i7 |= vVar.h(dVar6) ? 1048576 : 524288;
        } else {
            dVar6 = dVar4;
        }
        if ((i7 & 599187) == 599186 && vVar.z()) {
            vVar.Q();
        } else {
            boolean z2 = ((i7 & 896) == 256) | ((i7 & 112) == 32) | ((i7 & 7168) == 2048) | ((458752 & i7) == 131072) | ((57344 & i7) == 16384) | ((i7 & 14) == 4) | ((3670016 & i7) == 1048576);
            Object objK = vVar.K();
            if (z2 || objK == f0.o.f2281a) {
                k2 k2Var = new k2(dVar, dVar3, eVar, i5, z0Var2, dVar6, dVar5);
                vVar.f0(k2Var);
                objK = k2Var;
            }
            n1.v0.c(null, (a4.e) objK, vVar, 0, 1);
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new n2(i5, dVar, dVar2, dVar3, eVar, z0Var, dVar4, i6);
        }
    }
}
