package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m0 extends p0.z {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Object f2267h = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f2268c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2269d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public j.x f2270e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f2271f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2272g;

    public m0(long j5) {
        super(j5);
        j.x xVar = j.d0.f3095a;
        b4.l.c("null cannot be cast to non-null type androidx.collection.ObjectIntMap<K of androidx.collection.ObjectIntMapKt.emptyObjectIntMap>", xVar);
        this.f2270e = xVar;
        this.f2271f = f2267h;
    }

    @Override // p0.z
    public final void a(p0.z zVar) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.DerivedSnapshotState.ResultRecord<T of androidx.compose.runtime.DerivedSnapshotState.ResultRecord>", zVar);
        m0 m0Var = (m0) zVar;
        this.f2270e = m0Var.f2270e;
        this.f2271f = m0Var.f2271f;
        this.f2272g = m0Var.f2272g;
    }

    @Override // p0.z
    public final p0.z b(long j5) {
        return new m0(j5);
    }

    public final boolean c(n0 n0Var, p0.f fVar) {
        boolean z2;
        boolean z3;
        Object obj = p0.m.f4719c;
        synchronized (obj) {
            z2 = true;
            z3 = (this.f2268c == fVar.g() && this.f2269d == fVar.h()) ? false : true;
        }
        if (this.f2271f == f2267h || (z3 && this.f2272g != d(n0Var, fVar))) {
            z2 = false;
        }
        if (!z2 || !z3) {
            return z2;
        }
        synchronized (obj) {
            this.f2268c = fVar.g();
            this.f2269d = fVar.h();
        }
        return z2;
    }

    /* JADX WARN: Code duplicated, block: B:49:0x00d4 A[LOOP:4: B:48:0x00d2->B:49:0x00d4, LOOP_END] */
    public final int d(n0 n0Var, p0.f fVar) throws Throwable {
        j.x xVar;
        int iIdentityHashCode;
        Object[] objArr;
        int i5;
        int i6;
        long[] jArr;
        int i7;
        int i8;
        int i9;
        p0.z zVarG;
        synchronized (p0.m.f4719c) {
            xVar = this.f2270e;
        }
        int i10 = 7;
        if (xVar.f3172e == 0) {
            return 7;
        }
        h0.e eVarM = l.m();
        Object[] objArr2 = eVarM.f2576d;
        int i11 = eVarM.f2578f;
        for (int i12 = 0; i12 < i11; i12++) {
            ((u) objArr2[i12]).b();
        }
        try {
            Object[] objArr3 = xVar.f3169b;
            int[] iArr = xVar.f3170c;
            long[] jArr2 = xVar.f3168a;
            int length = jArr2.length - 2;
            if (length >= 0) {
                iIdentityHashCode = 7;
                int i13 = 0;
                while (true) {
                    long j5 = jArr2[i13];
                    if ((((~j5) << i10) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i14 = 8;
                        int i15 = 8 - ((~(i13 - length)) >>> 31);
                        int i16 = 0;
                        while (i16 < i15) {
                            if ((j5 & 255) < 128) {
                                int i17 = (i13 << 3) + i16;
                                Object obj = objArr3[i17];
                                i8 = i10;
                                int i18 = iArr[i17];
                                i9 = i14;
                                p0.x xVar2 = (p0.x) obj;
                                if (i18 == 1) {
                                    if (xVar2 instanceof n0) {
                                        try {
                                            n0 n0Var2 = (n0) xVar2;
                                            zVarG = n0Var2.g((m0) p0.m.i(n0Var2.f2279g, fVar), fVar, false, n0Var2.f2277e);
                                        } catch (Throwable th) {
                                            th = th;
                                            Object[] objArr4 = eVarM.f2576d;
                                            int i19 = eVarM.f2578f;
                                            for (int i20 = 0; i20 < i19; i20++) {
                                                ((u) objArr4[i20]).a();
                                            }
                                            throw th;
                                        }
                                    } else {
                                        zVarG = p0.m.i(xVar2.a(), fVar);
                                    }
                                    iIdentityHashCode = (((iIdentityHashCode * 31) + System.identityHashCode(zVarG)) * 31) + Long.hashCode(zVarG.f4767a);
                                }
                            } else {
                                i8 = i10;
                                i9 = i14;
                            }
                            j5 >>= i9;
                            i16++;
                            i10 = i8;
                            jArr2 = jArr2;
                            i14 = i9;
                        }
                        jArr = jArr2;
                        i7 = i10;
                        if (i15 != i14) {
                            break;
                        }
                    } else {
                        jArr = jArr2;
                        i7 = i10;
                    }
                    if (i13 != length) {
                        i13++;
                        i10 = i7;
                        jArr2 = jArr;
                    } else {
                        i10 = iIdentityHashCode;
                    }
                }
                objArr = eVarM.f2576d;
                i5 = eVarM.f2578f;
                for (i6 = 0; i6 < i5; i6++) {
                    ((u) objArr[i6]).a();
                }
                return iIdentityHashCode;
            }
            iIdentityHashCode = i10;
            objArr = eVarM.f2576d;
            i5 = eVarM.f2578f;
            while (i6 < i5) {
                ((u) objArr[i6]).a();
            }
            return iIdentityHashCode;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
