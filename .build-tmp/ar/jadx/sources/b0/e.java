package b0;

import c0.d0;
import p1.f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends u {
    public final j.z A;

    public e(q.j jVar, boolean z2, float f5, c0.c0 c0Var, d0 d0Var) {
        super(jVar, z2, f5, c0Var, d0Var);
        this.A = new j.z();
    }

    @Override // b0.u
    public final void B0(q.m mVar) {
        m mVar2 = (m) this.A.e(mVar);
        if (mVar2 != null) {
            mVar2.f432k.setValue(Boolean.TRUE);
            mVar2.f430i.O(p3.n.f5026a);
        }
    }

    @Override // r0.o
    public final void s0() {
        this.A.a();
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0056 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:15:0x0058 A[LOOP:0: B:5:0x0012->B:15:0x0058, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:23:0x005b A[EDGE_INSN: B:23:0x005b->B:16:0x005b BREAK  A[LOOP:0: B:5:0x0012->B:15:0x0058], SYNTHETIC] */
    @Override // b0.u
    public final void y0(q.m mVar, long j5, float f5) {
        j.z zVar = this.A;
        Object[] objArr = zVar.f3177b;
        Object[] objArr2 = zVar.f3178c;
        long[] jArr = zVar.f3176a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i5 = 0;
            while (true) {
                long j6 = jArr[i5];
                if ((((~j6) << 7) & j6 & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i5 != length) {
                        break;
                        break;
                    }
                    i5++;
                } else {
                    int i6 = 8 - ((~(i5 - length)) >>> 31);
                    for (int i7 = 0; i7 < i6; i7++) {
                        if ((255 & j6) < 128) {
                            int i8 = (i5 << 3) + i7;
                            Object obj = objArr[i8];
                            m mVar2 = (m) objArr2[i8];
                            mVar2.f432k.setValue(Boolean.TRUE);
                            mVar2.f430i.O(p3.n.f5026a);
                        }
                        j6 >>= 8;
                    }
                    if (i6 != 8) {
                        break;
                    } else if (i5 != length) {
                        break;
                    } else {
                        i5++;
                    }
                }
            }
        }
        boolean z2 = this.f454r;
        m mVar3 = new m(z2 ? new x0.c(mVar.f5084a) : null, f5, z2);
        zVar.i(mVar, mVar3);
        i4.y.n(n0(), null, new d(mVar3, this, mVar, null, 0), 3);
        p1.f.l(this);
    }

    @Override // b0.u
    public final void z0(f0 f0Var) {
        a1.c cVar;
        Object[] objArr;
        int i5;
        int i6;
        int i7;
        e eVar = this;
        a1.c cVar2 = f0Var.f4828d;
        eVar.f457u.a();
        float f5 = 0.1f;
        if (0.1f == 0.0f) {
            return;
        }
        j.z zVar = eVar.A;
        Object[] objArr2 = zVar.f3177b;
        Object[] objArr3 = zVar.f3178c;
        long[] jArr = zVar.f3176a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i8 = 0;
        while (true) {
            long j5 = jArr[i8];
            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i9 = 8;
                int i10 = 8 - ((~(i8 - length)) >>> 31);
                long j6 = j5;
                int i11 = 0;
                while (i11 < i10) {
                    if ((j6 & 255) < 128) {
                        int i12 = (i8 << 3) + i11;
                        Object obj = objArr2[i12];
                        m mVar = (m) objArr3[i12];
                        long jB = y0.u.b(f5, eVar.f456t.a());
                        m.c cVar3 = mVar.f429h;
                        if (mVar.f425d == null) {
                            long jB2 = cVar2.b();
                            float f6 = n.f433a;
                            mVar.f425d = Float.valueOf(Math.max(x0.f.d(jB2), x0.f.b(jB2)) * 0.3f);
                        }
                        if (mVar.f422a == null) {
                            mVar.f422a = new x0.c(cVar2.O());
                        }
                        if (mVar.f426e == null) {
                            mVar.f426e = new x0.c(p3.j.b(x0.f.d(cVar2.b()) / 2.0f, x0.f.b(cVar2.b()) / 2.0f));
                        }
                        float fFloatValue = (!((Boolean) mVar.f432k.getValue()).booleanValue() || ((Boolean) mVar.f431j.getValue()).booleanValue()) ? ((Number) mVar.f427f.c()).floatValue() : 1.0f;
                        Float f7 = mVar.f425d;
                        b4.l.b(f7);
                        float fD = a.a.D(f7.floatValue(), mVar.f423b, ((Number) mVar.f428g.c()).floatValue());
                        x0.c cVar4 = mVar.f422a;
                        b4.l.b(cVar4);
                        float f8 = fFloatValue;
                        float fD2 = x0.c.d(cVar4.f6532a);
                        x0.c cVar5 = mVar.f426e;
                        b4.l.b(cVar5);
                        int i13 = i11;
                        float fD3 = a.a.D(fD2, x0.c.d(cVar5.f6532a), ((Number) cVar3.c()).floatValue());
                        x0.c cVar6 = mVar.f422a;
                        b4.l.b(cVar6);
                        float fE = x0.c.e(cVar6.f6532a);
                        x0.c cVar7 = mVar.f426e;
                        b4.l.b(cVar7);
                        int i14 = i10;
                        long jB3 = p3.j.b(fD3, a.a.D(fE, x0.c.e(cVar7.f6532a), ((Number) cVar3.c()).floatValue()));
                        long jB4 = y0.u.b(y0.u.d(jB) * f8, jB);
                        if (mVar.f424c) {
                            float fD4 = x0.f.d(cVar2.b());
                            float fB = x0.f.b(cVar2.b());
                            a1.b bVar = cVar2.f27e;
                            long jT = bVar.t();
                            bVar.r().p();
                            ((a1.b) ((a1.d) bVar.f23d).f31e).r().i(0.0f, 0.0f, fD4, fB, 1);
                            i6 = i14;
                            i7 = 8;
                            i5 = i13;
                            a1.f.C(f0Var, jB4, fD, jB3, 120);
                            bVar.r().m();
                            bVar.D(jT);
                        } else {
                            i5 = i13;
                            i6 = i14;
                            i7 = 8;
                            a1.f.C(f0Var, jB4, fD, jB3, 120);
                        }
                    } else {
                        i5 = i11;
                        i6 = i10;
                        i7 = i9;
                    }
                    j6 >>= i7;
                    i11 = i5 + 1;
                    i10 = i6;
                    i9 = i7;
                    cVar2 = cVar2;
                    objArr2 = objArr2;
                    f5 = 0.1f;
                    eVar = this;
                }
                cVar = cVar2;
                objArr = objArr2;
                if (i10 != i9) {
                    return;
                }
            } else {
                cVar = cVar2;
                objArr = objArr2;
            }
            if (i8 == length) {
                return;
            }
            i8++;
            eVar = this;
            cVar2 = cVar;
            objArr2 = objArr;
            f5 = 0.1f;
        }
    }
}
