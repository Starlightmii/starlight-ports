package p0;

import f0.k1;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k1 f4717a = new k1(5);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a1.b f4718b = new a1.b(8);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f4719c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static k f4720d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static long f4721e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final i f4722f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final u f4723g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static Object f4724h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static Object f4725i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final a f4726j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final n0.a f4727k;

    static {
        k kVar = k.f4709h;
        f4720d = kVar;
        long j5 = 1;
        f4721e = j5 + j5;
        i iVar = new i();
        iVar.f4699b = new long[16];
        iVar.f4700c = new int[16];
        int[] iArr = new int[16];
        int i5 = 0;
        while (i5 < 16) {
            int i6 = i5 + 1;
            iArr[i5] = i6;
            i5 = i6;
        }
        iVar.f4701d = iArr;
        f4722f = iVar;
        u uVar = new u();
        uVar.f4756b = new int[16];
        uVar.f4757c = new n0.m[16];
        f4723g = uVar;
        q3.s sVar = q3.s.f5477d;
        f4724h = sVar;
        f4725i = sVar;
        long j6 = f4721e;
        f4721e = j5 + j6;
        a aVar = new a(j6, kVar, null, new k1(4));
        f4720d = f4720d.e(aVar.f4694b);
        f4726j = aVar;
        f4727k = new n0.a(0);
    }

    public static final void a() {
        e(f4717a);
    }

    public static final HashMap b(long j5, b bVar, k kVar) {
        long[] jArr;
        k kVar2;
        long[] jArr2;
        int i5;
        int i6;
        z zVarS;
        j.a0 a0VarX = bVar.x();
        if (a0VarX != null) {
            long jG = bVar.g();
            k kVarD = bVar.d().e(jG).d(bVar.f4668j);
            Object[] objArr = a0VarX.f3067b;
            long[] jArr3 = a0VarX.f3066a;
            int length = jArr3.length - 2;
            if (length >= 0) {
                int i7 = 0;
                HashMap map = null;
                while (true) {
                    long j6 = jArr3[i7];
                    if ((((~j6) << 7) & j6 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i8 = 8;
                        int i9 = 8 - ((~(i7 - length)) >>> 31);
                        int i10 = 0;
                        while (i10 < i9) {
                            if ((j6 & 255) < 128) {
                                x xVar = (x) objArr[(i7 << 3) + i10];
                                z zVarA = xVar.a();
                                jArr2 = jArr3;
                                i5 = i8;
                                i6 = i10;
                                z zVarS2 = s(zVarA, j5, kVar);
                                if (zVarS2 != null && (zVarS = s(zVarA, jG, kVarD)) != null && !zVarS2.equals(zVarS)) {
                                    z zVarS3 = s(zVarA, jG, bVar.d());
                                    if (zVarS3 == null) {
                                        r();
                                        throw null;
                                    }
                                    z zVarB = xVar.b(zVarS, zVarS2, zVarS3);
                                    if (zVarB == null) {
                                        return null;
                                    }
                                    if (map == null) {
                                        map = new HashMap();
                                    }
                                    map.put(zVarS2, zVarB);
                                    map = map;
                                }
                            } else {
                                jArr2 = jArr3;
                                i5 = i8;
                                i6 = i10;
                            }
                            j6 >>= i5;
                            i10 = i6 + 1;
                            i8 = i5;
                            jArr3 = jArr2;
                            kVarD = kVarD;
                        }
                        jArr = jArr3;
                        kVar2 = kVarD;
                        if (i9 != i8) {
                            return map;
                        }
                    } else {
                        jArr = jArr3;
                        kVar2 = kVarD;
                    }
                    if (i7 == length) {
                        return map;
                    }
                    i7++;
                    jArr3 = jArr;
                    kVarD = kVar2;
                }
            }
        }
        return null;
    }

    public static final void c(f fVar) {
        long j5;
        if (f4720d.c(fVar.g())) {
            return;
        }
        StringBuilder sb = new StringBuilder("Snapshot is not open: snapshotId=");
        sb.append(fVar.g());
        sb.append(", disposed=");
        sb.append(fVar.f4695c);
        sb.append(", applied=");
        b bVar = fVar instanceof b ? (b) fVar : null;
        sb.append(bVar != null ? Boolean.valueOf(bVar.f4671m) : "read-only");
        sb.append(", lowestPin=");
        synchronized (f4719c) {
            i iVar = f4722f;
            j5 = iVar.f4698a > 0 ? iVar.f4699b[0] : -1L;
        }
        sb.append(j5);
        throw new IllegalStateException(sb.toString().toString());
    }

    public static final k d(k kVar, long j5, long j6) {
        while (b4.l.g(j5, j6) < 0) {
            kVar = kVar.e(j5);
            j5 += (long) 1;
        }
        return kVar;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x008e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:43:0x0090 A[LOOP:1: B:30:0x0056->B:43:0x0090, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:58:0x0093 A[EDGE_INSN: B:58:0x0093->B:44:0x0093 BREAK  A[LOOP:1: B:30:0x0056->B:43:0x0090], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Object, java.util.Collection, java.util.List] */
    public static final Object e(a4.c cVar) {
        j.a0 a0Var;
        Object objV;
        a aVar = f4726j;
        synchronized (f4719c) {
            try {
                a0Var = aVar.f4666h;
                if (a0Var != null) {
                    f4727k.addAndGet(1);
                }
                objV = v(aVar, cVar);
            } catch (Throwable th) {
                throw th;
            }
        }
        if (a0Var != null) {
            try {
                ?? r4 = f4724h;
                int size = r4.size();
                for (int i5 = 0; i5 < size; i5++) {
                    ((a4.e) r4.get(i5)).invoke(new h0.h(a0Var), aVar);
                }
                f4727k.addAndGet(-1);
            } catch (Throwable th2) {
                f4727k.addAndGet(-1);
                throw th2;
            }
        }
        synchronized (f4719c) {
            f();
            if (a0Var != null) {
                Object[] objArr = a0Var.f3067b;
                long[] jArr = a0Var.f3066a;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i6 = 0;
                    while (true) {
                        long j5 = jArr[i6];
                        if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                            if (i6 != length) {
                                break;
                                break;
                            }
                            i6++;
                        } else {
                            int i7 = 8 - ((~(i6 - length)) >>> 31);
                            for (int i8 = 0; i8 < i7; i8++) {
                                if ((255 & j5) < 128) {
                                    q((x) objArr[(i6 << 3) + i8]);
                                }
                                j5 >>= 8;
                            }
                            if (i7 != 8) {
                                break;
                            }
                            if (i6 != length) {
                                break;
                            }
                            i6++;
                        }
                    }
                }
            }
        }
        return objV;
    }

    public static final void f() {
        u uVar = f4723g;
        int i5 = uVar.f4755a;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            if (i6 >= i5) {
                break;
            }
            n0.m mVar = ((n0.m[]) uVar.f4757c)[i6];
            Object obj = mVar != null ? mVar.get() : null;
            if (obj != null && p((x) obj)) {
                if (i7 != i6) {
                    ((n0.m[]) uVar.f4757c)[i7] = mVar;
                    int[] iArr = (int[]) uVar.f4756b;
                    iArr[i7] = iArr[i6];
                }
                i7++;
            }
            i6++;
        }
        for (int i8 = i7; i8 < i5; i8++) {
            ((n0.m[]) uVar.f4757c)[i8] = null;
            ((int[]) uVar.f4756b)[i8] = 0;
        }
        if (i7 != i5) {
            uVar.f4755a = i7;
        }
    }

    public static final f g(f fVar, a4.c cVar, boolean z2) {
        boolean z3 = fVar instanceof b;
        if (z3 || fVar == null) {
            return new c0(z3 ? (b) fVar : null, cVar, null, false, z2);
        }
        return new d0(fVar, cVar, false, z2);
    }

    public static final z h(z zVar) {
        z zVarS;
        f fVarJ = j();
        z zVarS2 = s(zVar, fVarJ.g(), fVarJ.d());
        if (zVarS2 != null) {
            return zVarS2;
        }
        synchronized (f4719c) {
            f fVarJ2 = j();
            zVarS = s(zVar, fVarJ2.g(), fVarJ2.d());
        }
        if (zVarS != null) {
            return zVarS;
        }
        r();
        throw null;
    }

    public static final z i(z zVar, f fVar) {
        z zVarS;
        z zVarS2 = s(zVar, fVar.g(), fVar.d());
        if (zVarS2 != null) {
            return zVarS2;
        }
        synchronized (f4719c) {
            zVarS = s(zVar, fVar.g(), fVar.d());
        }
        if (zVarS != null) {
            return zVarS;
        }
        r();
        throw null;
    }

    public static final f j() {
        f fVar = (f) f4718b.q();
        return fVar == null ? f4726j : fVar;
    }

    public static final a4.c k(a4.c cVar, a4.c cVar2, boolean z2) {
        if (!z2) {
            cVar2 = null;
        }
        if (cVar == null || cVar2 == null || cVar == cVar2) {
            return cVar == null ? cVar2 : cVar;
        }
        return new l(cVar, cVar2, 0);
    }

    public static final a4.c l(a4.c cVar, a4.c cVar2) {
        if (cVar == null || cVar2 == null || cVar == cVar2) {
            return cVar == null ? cVar2 : cVar;
        }
        return new l(cVar, cVar2, 1);
    }

    public static final z m(z zVar, x xVar) {
        long j5 = f4721e;
        i iVar = f4722f;
        if (iVar.f4698a > 0) {
            j5 = iVar.f4699b[0];
        }
        long j6 = j5 - ((long) 1);
        z zVar2 = null;
        z zVar3 = null;
        for (z zVarA = xVar.a(); zVarA != null; zVarA = zVarA.f4768b) {
            long j7 = zVarA.f4767a;
            if (j7 != 0) {
                if (j7 != 0 && b4.l.g(j7, j6) <= 0 && !k.f4709h.c(j7)) {
                    if (zVar3 != null) {
                        if (b4.l.g(zVarA.f4767a, zVar3.f4767a) >= 0) {
                            zVar2 = zVar3;
                            break;
                        }
                        break;
                    }
                    zVar3 = zVarA;
                }
            }
            zVar2 = zVarA;
            break;
        }
        if (zVar2 != null) {
            zVar2.f4767a = Long.MAX_VALUE;
            return zVar2;
        }
        z zVarB = zVar.b(Long.MAX_VALUE);
        zVarB.f4768b = xVar.a();
        xVar.c(zVarB);
        return zVarB;
    }

    public static final void n(f fVar, x xVar) {
        fVar.t(fVar.h() + 1);
        a4.c cVarI = fVar.i();
        if (cVarI != null) {
            cVarI.c(xVar);
        }
    }

    public static final z o(z zVar, y yVar, f fVar, z zVar2) {
        z zVarM;
        if (fVar.f()) {
            fVar.n(yVar);
        }
        long jG = fVar.g();
        if (zVar2.f4767a == jG) {
            return zVar2;
        }
        synchronized (f4719c) {
            zVarM = m(zVar, yVar);
        }
        zVarM.f4767a = jG;
        if (zVar2.f4767a != 1) {
            fVar.n(yVar);
        }
        return zVarM;
    }

    public static final boolean p(x xVar) {
        z zVar;
        long j5 = f4721e;
        i iVar = f4722f;
        if (iVar.f4698a > 0) {
            j5 = iVar.f4699b[0];
        }
        z zVar2 = null;
        z zVarA = null;
        int i5 = 0;
        for (z zVarA2 = xVar.a(); zVarA2 != null; zVarA2 = zVarA2.f4768b) {
            long j6 = zVarA2.f4767a;
            if (j6 != 0) {
                if (b4.l.g(j6, j5) >= 0) {
                    i5++;
                } else if (zVar2 == null) {
                    i5++;
                    zVar2 = zVarA2;
                } else {
                    if (b4.l.g(zVarA2.f4767a, zVar2.f4767a) < 0) {
                        zVar = zVar2;
                        zVar2 = zVarA2;
                    } else {
                        zVar = zVarA2;
                    }
                    if (zVarA == null) {
                        zVarA = xVar.a();
                        z zVar3 = zVarA;
                        while (true) {
                            if (zVarA == null) {
                                zVarA = zVar3;
                                break;
                            }
                            if (b4.l.g(zVarA.f4767a, j5) >= 0) {
                                break;
                            }
                            if (b4.l.g(zVar3.f4767a, zVarA.f4767a) < 0) {
                                zVar3 = zVarA;
                            }
                            zVarA = zVarA.f4768b;
                        }
                    }
                    zVar2.f4767a = 0L;
                    zVar2.a(zVarA);
                    zVar2 = zVar;
                }
            }
        }
        return i5 > 1;
    }

    public static final void q(x xVar) {
        if (p(xVar)) {
            u uVar = f4723g;
            int i5 = uVar.f4755a;
            int iIdentityHashCode = System.identityHashCode(xVar);
            int i6 = -1;
            if (i5 > 0) {
                int i7 = uVar.f4755a - 1;
                int i8 = 0;
                while (true) {
                    if (i8 > i7) {
                        i6 = -(i8 + 1);
                        break;
                    }
                    int i9 = (i8 + i7) >>> 1;
                    int i10 = ((int[]) uVar.f4756b)[i9];
                    if (i10 < iIdentityHashCode) {
                        i8 = i9 + 1;
                    } else if (i10 > iIdentityHashCode) {
                        i7 = i9 - 1;
                    } else {
                        n0.m mVar = ((n0.m[]) uVar.f4757c)[i9];
                        if (xVar == (mVar != null ? mVar.get() : null)) {
                            i6 = i9;
                            break;
                        }
                        int i11 = i9 - 1;
                        while (true) {
                            if (-1 >= i11 || ((int[]) uVar.f4756b)[i11] != iIdentityHashCode) {
                                i9++;
                                int i12 = uVar.f4755a;
                                while (true) {
                                    if (i9 >= i12) {
                                        i6 = -(uVar.f4755a + 1);
                                        break;
                                    }
                                    if (((int[]) uVar.f4756b)[i9] != iIdentityHashCode) {
                                        i6 = -(i9 + 1);
                                        break;
                                    }
                                    n0.m mVar2 = ((n0.m[]) uVar.f4757c)[i9];
                                    if ((mVar2 != null ? mVar2.get() : null) == xVar) {
                                        i6 = i9;
                                        break;
                                    }
                                    i9++;
                                }
                            } else {
                                n0.m mVar3 = ((n0.m[]) uVar.f4757c)[i11];
                                if ((mVar3 != null ? mVar3.get() : null) == xVar) {
                                    i6 = i11;
                                    break;
                                }
                                i11--;
                            }
                        }
                    }
                }
                if (i6 >= 0) {
                    return;
                }
            }
            int i13 = -(i6 + 1);
            n0.m[] mVarArr = (n0.m[]) uVar.f4757c;
            int length = mVarArr.length;
            if (i5 == length) {
                int i14 = length * 2;
                n0.m[] mVarArr2 = new n0.m[i14];
                int[] iArr = new int[i14];
                int i15 = i13 + 1;
                System.arraycopy(mVarArr, i13, mVarArr2, i15, i5 - i13);
                System.arraycopy((n0.m[]) uVar.f4757c, 0, mVarArr2, 0, i13);
                q3.k.y((int[]) uVar.f4756b, iArr, i15, i13, i5);
                q3.k.B((int[]) uVar.f4756b, iArr, 0, i13, 6);
                uVar.f4757c = mVarArr2;
                uVar.f4756b = iArr;
            } else {
                int i16 = i13 + 1;
                System.arraycopy(mVarArr, i13, mVarArr, i16, i5 - i13);
                int[] iArr2 = (int[]) uVar.f4756b;
                q3.k.y(iArr2, iArr2, i16, i13, i5);
            }
            ((n0.m[]) uVar.f4757c)[i13] = new n0.m(xVar);
            ((int[]) uVar.f4756b)[i13] = iIdentityHashCode;
            uVar.f4755a++;
        }
    }

    public static final void r() {
        throw new IllegalStateException("Reading a state that was created after the snapshot was taken or in a snapshot that has not yet been applied");
    }

    public static final z s(z zVar, long j5, k kVar) {
        z zVar2 = null;
        while (zVar != null) {
            long j6 = zVar.f4767a;
            if (j6 != 0 && b4.l.g(j6, j5) <= 0 && !kVar.c(j6) && (zVar2 == null || b4.l.g(zVar2.f4767a, zVar.f4767a) < 0)) {
                zVar2 = zVar;
            }
            zVar = zVar.f4768b;
        }
        if (zVar2 != null) {
            return zVar2;
        }
        return null;
    }

    public static final z t(z zVar, x xVar) {
        z zVarS;
        f fVarJ = j();
        a4.c cVarE = fVarJ.e();
        if (cVarE != null) {
            cVarE.c(xVar);
        }
        z zVarS2 = s(zVar, fVarJ.g(), fVarJ.d());
        if (zVarS2 != null) {
            return zVarS2;
        }
        synchronized (f4719c) {
            f fVarJ2 = j();
            z zVarA = xVar.a();
            b4.l.c("null cannot be cast to non-null type T of androidx.compose.runtime.snapshots.SnapshotKt.readable", zVarA);
            zVarS = s(zVarA, fVarJ2.g(), fVarJ2.d());
            if (zVarS == null) {
                r();
                throw null;
            }
        }
        return zVarS;
    }

    public static final void u(int i5) {
        i iVar = f4722f;
        int i6 = iVar.f4701d[i5];
        iVar.b(i6, iVar.f4698a - 1);
        iVar.f4698a--;
        long[] jArr = iVar.f4699b;
        long j5 = jArr[i6];
        int i7 = i6;
        while (i7 > 0) {
            int i8 = ((i7 + 1) >> 1) - 1;
            if (b4.l.g(jArr[i8], j5) <= 0) {
                break;
            }
            iVar.b(i8, i7);
            i7 = i8;
        }
        long[] jArr2 = iVar.f4699b;
        int i9 = iVar.f4698a >> 1;
        while (i6 < i9) {
            int i10 = (i6 + 1) << 1;
            int i11 = i10 - 1;
            if (i10 < iVar.f4698a && b4.l.g(jArr2[i10], jArr2[i11]) < 0) {
                if (b4.l.g(jArr2[i10], jArr2[i6]) >= 0) {
                    break;
                }
                iVar.b(i10, i6);
                i6 = i10;
            } else {
                if (b4.l.g(jArr2[i11], jArr2[i6]) >= 0) {
                    break;
                }
                iVar.b(i11, i6);
                i6 = i11;
            }
        }
        iVar.f4701d[i5] = iVar.f4702e;
        iVar.f4702e = i5;
    }

    public static final Object v(a aVar, a4.c cVar) {
        long j5 = aVar.f4694b;
        Object objC = cVar.c(f4720d.b(j5));
        long j6 = f4721e;
        f4721e = ((long) 1) + j6;
        k kVarB = f4720d.b(j5);
        f4720d = kVarB;
        aVar.f4694b = j6;
        aVar.f4693a = kVarB;
        aVar.f4665g = 0;
        aVar.f4666h = null;
        aVar.o();
        f4720d = f4720d.e(j6);
        return objC;
    }

    public static final z w(z zVar, x xVar, f fVar) {
        z zVarS;
        if (fVar.f()) {
            fVar.n(xVar);
        }
        long jG = fVar.g();
        z zVarS2 = s(zVar, jG, fVar.d());
        if (zVarS2 == null) {
            r();
            throw null;
        }
        if (zVarS2.f4767a == fVar.g()) {
            return zVarS2;
        }
        synchronized (f4719c) {
            zVarS = s(xVar.a(), jG, fVar.d());
            if (zVarS == null) {
                r();
                throw null;
            }
            if (zVarS.f4767a != jG) {
                z zVarM = m(zVarS, xVar);
                zVarM.a(zVarS);
                zVarM.f4767a = fVar.g();
                zVarS = zVarM;
            }
        }
        if (zVarS2.f4767a != 1) {
            fVar.n(xVar);
        }
        return zVarS;
    }
}
