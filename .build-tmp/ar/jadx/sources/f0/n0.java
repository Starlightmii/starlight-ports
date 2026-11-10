package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n0 extends p0.y implements g3 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a4.a f2277e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b3 f2278f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public m0 f2279g = new m0(p0.m.j().g());

    public n0(a4.a aVar, h hVar) {
        this.f2277e = aVar;
        this.f2278f = hVar;
    }

    @Override // p0.x
    public final p0.z a() {
        return this.f2279g;
    }

    @Override // p0.x
    public final void c(p0.z zVar) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.DerivedSnapshotState.ResultRecord<T of androidx.compose.runtime.DerivedSnapshotState>", zVar);
        this.f2279g = (m0) zVar;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x00a3 A[EDGE_INSN: B:101:0x00a3->B:31:0x00a3 BREAK  A[LOOP:1: B:16:0x0049->B:30:0x009e], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x009c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x009e A[Catch: all -> 0x0038, LOOP:1: B:16:0x0049->B:30:0x009e, LOOP_END, TryCatch #2 {all -> 0x0038, blocks: (B:8:0x0023, B:10:0x002f, B:13:0x003b, B:16:0x0049, B:18:0x005c, B:20:0x0068, B:22:0x0072, B:24:0x008a, B:26:0x0090, B:30:0x009e, B:31:0x00a3), top: B:95:0x0023 }] */
    /* JADX WARN: Type inference failed for: r6v2, types: [f0.l0] */
    public final m0 g(m0 m0Var, p0.f fVar, boolean z2, a4.a aVar) {
        b3 b3Var;
        int i5;
        m0 m0Var2 = m0Var;
        if (m0Var2.c(this, fVar)) {
            if (z2) {
                h0.e eVarM = l.m();
                Object[] objArr = eVarM.f2576d;
                int i6 = eVarM.f2578f;
                for (int i7 = 0; i7 < i6; i7++) {
                    ((u) objArr[i7]).b();
                }
                try {
                    j.x xVar = m0Var2.f2270e;
                    a1.b bVar = c3.f2109a;
                    n0.e eVar = (n0.e) bVar.q();
                    if (eVar == null) {
                        eVar = new n0.e();
                        bVar.z(eVar);
                    }
                    int i8 = eVar.f4062a;
                    Object[] objArr2 = xVar.f3169b;
                    int[] iArr = xVar.f3170c;
                    long[] jArr = xVar.f3168a;
                    int length = jArr.length - 2;
                    if (length >= 0) {
                        int i9 = 0;
                        while (true) {
                            long j5 = jArr[i9];
                            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                                if (i9 != length) {
                                    break;
                                    break;
                                }
                                i9++;
                            } else {
                                int i10 = 8;
                                int i11 = 8 - ((~(i9 - length)) >>> 31);
                                int i12 = 0;
                                while (i12 < i11) {
                                    if ((j5 & 255) < 128) {
                                        int i13 = (i9 << 3) + i12;
                                        i5 = i10;
                                        p0.x xVar2 = (p0.x) objArr2[i13];
                                        eVar.f4062a = i8 + iArr[i13];
                                        a4.c cVarE = fVar.e();
                                        if (cVarE != null) {
                                            cVarE.c(xVar2);
                                        }
                                    } else {
                                        i5 = i10;
                                    }
                                    j5 >>= i5;
                                    i12++;
                                    i10 = i5;
                                }
                                if (i11 != i10) {
                                    break;
                                }
                                if (i9 != length) {
                                    break;
                                }
                                i9++;
                            }
                        }
                    }
                    eVar.f4062a = i8;
                } finally {
                    Object[] objArr3 = eVarM.f2576d;
                    int i14 = eVarM.f2578f;
                    for (int i15 = 0; i15 < i14; i15++) {
                        ((u) objArr3[i15]).a();
                    }
                }
            }
            return m0Var2;
        }
        final j.x xVar3 = new j.x();
        a1.b bVar2 = c3.f2109a;
        final n0.e eVar2 = (n0.e) bVar2.q();
        if (eVar2 == null) {
            eVar2 = new n0.e();
            bVar2.z(eVar2);
        }
        final int i16 = eVar2.f4062a;
        h0.e eVarM2 = l.m();
        Object[] objArr4 = eVarM2.f2576d;
        int i17 = eVarM2.f2578f;
        for (int i18 = 0; i18 < i17; i18++) {
            ((u) objArr4[i18]).b();
        }
        try {
            eVar2.f4062a = i16 + 1;
            Object objI = p0.r.i(new a4.c() { // from class: f0.l0
                @Override // a4.c
                public final Object c(Object obj) {
                    if (obj == this.f2234d) {
                        throw new IllegalStateException("A derived state calculation cannot read itself");
                    }
                    if (obj instanceof p0.x) {
                        int i19 = eVar2.f4062a - i16;
                        j.x xVar4 = xVar3;
                        int iC = xVar4.c(obj);
                        int iMin = Math.min(i19, iC >= 0 ? xVar4.f3170c[iC] : Integer.MAX_VALUE);
                        int iB = xVar4.b(obj);
                        if (iB < 0) {
                            iB = ~iB;
                        }
                        xVar4.f3169b[iB] = obj;
                        xVar4.f3170c[iB] = iMin;
                    }
                    return p3.n.f5026a;
                }
            }, aVar);
            eVar2.f4062a = i16;
            Object[] objArr5 = eVarM2.f2576d;
            int i19 = eVarM2.f2578f;
            for (int i20 = 0; i20 < i19; i20++) {
                ((u) objArr5[i20]).a();
            }
            Object obj = p0.m.f4719c;
            synchronized (obj) {
                try {
                    p0.f fVarJ = p0.m.j();
                    Object obj2 = m0Var2.f2271f;
                    if (obj2 == m0.f2267h || (b3Var = this.f2278f) == null || !b3Var.a(objI, obj2)) {
                        m0 m0Var3 = this.f2279g;
                        synchronized (obj) {
                            p0.z zVarM = p0.m.m(m0Var3, this);
                            zVarM.a(m0Var3);
                            zVarM.f4767a = fVarJ.g();
                            m0Var2 = (m0) zVarM;
                            m0Var2.f2270e = xVar3;
                            m0Var2.f2272g = m0Var2.d(this, fVarJ);
                            m0Var2.f2271f = objI;
                        }
                        return m0Var2;
                    }
                    m0Var2.f2270e = xVar3;
                    m0Var2.f2272g = m0Var2.d(this, fVarJ);
                } catch (Throwable th) {
                    throw th;
                }
            }
            n0.e eVar3 = (n0.e) c3.f2109a.q();
            if (eVar3 == null || eVar3.f4062a != 0) {
                return m0Var2;
            }
            p0.m.j().m();
            synchronized (obj) {
                p0.f fVarJ2 = p0.m.j();
                m0Var2.f2268c = fVarJ2.g();
                m0Var2.f2269d = fVarJ2.h();
                return m0Var2;
            }
        } catch (Throwable th2) {
            Object[] objArr6 = eVarM2.f2576d;
            int i21 = eVarM2.f2578f;
            for (int i22 = 0; i22 < i21; i22++) {
                ((u) objArr6[i22]).a();
            }
            throw th2;
        }
    }

    @Override // f0.g3
    public final Object getValue() {
        a4.c cVarE = p0.m.j().e();
        if (cVarE != null) {
            cVarE.c(this);
        }
        p0.f fVarJ = p0.m.j();
        return g((m0) p0.m.i(this.f2279g, fVarJ), fVarJ, true, this.f2277e).f2271f;
    }

    public final m0 h() {
        p0.f fVarJ = p0.m.j();
        return g((m0) p0.m.i(this.f2279g, fVarJ), fVarJ, false, this.f2277e);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DerivedState(value=");
        m0 m0Var = (m0) p0.m.h(this.f2279g);
        sb.append(m0Var.c(this, p0.m.j()) ? String.valueOf(m0Var.f2271f) : "<Not calculated>");
        sb.append(")@");
        sb.append(hashCode());
        return sb.toString();
    }
}
