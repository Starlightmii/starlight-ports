package p1;

import c0.t2;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class m0 extends n1.m0 implements n1.h0, r0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4922i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4923j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f4924k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final n1.c0 f4925l = new n1.c0(0, this);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public j.w f4926m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public j.w f4927n;

    public static void x0(y0 y0Var) {
        e0 e0Var;
        y0 y0Var2 = y0Var.f4999p;
        d0 d0Var = y0Var.f4998o;
        if (!b4.l.a(y0Var2 != null ? y0Var2.f4998o : null, d0Var)) {
            d0Var.f4802z.f4910r.f4886w.f();
            return;
        }
        a aVarZ = d0Var.f4802z.f4910r.z();
        if (aVarZ == null || (e0Var = ((i0) aVarZ).f4886w) == null) {
            return;
        }
        e0Var.f();
    }

    @Override // n1.h0
    public final n1.g0 S(int i5, int i6, Map map, a4.c cVar) {
        if ((i5 & (-16777216)) == 0 && ((-16777216) & i6) == 0) {
            return new k0(i5, i6, map, cVar, this);
        }
        d4.a.L("Size(" + i5 + " x " + i6 + ") is out of range. Each dimension must be between 0 and 16777215.");
        throw null;
    }

    @Override // p1.r0
    public final void W(boolean z2) {
        this.f4922i = z2;
    }

    @Override // n1.m0
    public final int f0(n1.l lVar) {
        int iO0;
        if (s0() && (iO0 = o0(lVar)) != Integer.MIN_VALUE) {
            return iO0 + ((int) (this.f4134h & 4294967295L));
        }
        return Integer.MIN_VALUE;
    }

    @Override // n1.m
    public boolean o() {
        return false;
    }

    public abstract int o0(n1.l lVar);

    public final void p0(i1 i1Var) {
        long j5;
        long j6;
        long j7;
        Object obj;
        Object obj2;
        Object obj3;
        m0 m0VarV0;
        g1 snapshotObserver;
        Object[] objArr;
        float[] fArr;
        long[] jArr;
        long[] jArr2;
        long j8;
        int i5;
        int i6;
        float f5;
        int i7;
        int i8;
        int i9;
        if (this.f4924k || i1Var.f4890d.o() == null) {
            return;
        }
        j.w wVar = this.f4927n;
        if (wVar == null) {
            wVar = new j.w();
            this.f4927n = wVar;
        }
        j.w wVar2 = this.f4926m;
        if (wVar2 == null) {
            wVar2 = new j.w();
            this.f4926m = wVar2;
        }
        Object[] objArr2 = wVar2.f3163b;
        float[] fArr2 = wVar2.f3164c;
        long[] jArr3 = wVar2.f3162a;
        int length = jArr3.length - 2;
        int i10 = 8;
        if (length >= 0) {
            int i11 = 0;
            j5 = 255;
            j6 = 128;
            while (true) {
                long j9 = jArr3[i11];
                j7 = -9187201950435737472L;
                if ((((~j9) << 7) & j9 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i12 = 8 - ((~(i11 - length)) >>> 31);
                    int i13 = 0;
                    while (i13 < i12) {
                        if ((j9 & 255) < 128) {
                            int i14 = (i11 << 3) + i13;
                            Object obj4 = objArr2[i14];
                            float f6 = fArr2[i14];
                            int iHashCode = (obj4 != null ? obj4.hashCode() : 0) * (-862048943);
                            int i15 = iHashCode ^ (iHashCode << 16);
                            int i16 = i15 >>> 7;
                            int i17 = i15 & 127;
                            int i18 = wVar.f3165d;
                            int i19 = i16 & i18;
                            int i20 = 0;
                            while (true) {
                                long[] jArr4 = wVar.f3162a;
                                int i21 = i19 >> 3;
                                int i22 = (i19 & 7) << 3;
                                long j10 = jArr4[i21] >>> i22;
                                long j11 = jArr4[i21 + 1] << (64 - i22);
                                jArr2 = jArr3;
                                long j12 = j10 | (j11 & ((-i22) >> 63));
                                j8 = j9;
                                long j13 = i17;
                                long j14 = j12 ^ (j13 * 72340172838076673L);
                                long j15 = (j14 - 72340172838076673L) & (~j14) & (-9187201950435737472L);
                                while (j15 != 0) {
                                    int iNumberOfTrailingZeros = (i19 + (Long.numberOfTrailingZeros(j15) >> 3)) & i18;
                                    int i23 = i17;
                                    if (b4.l.a(wVar.f3163b[iNumberOfTrailingZeros], obj4)) {
                                        f5 = f6;
                                        i7 = iNumberOfTrailingZeros;
                                        i5 = i13;
                                        break;
                                    }
                                    j15 &= j15 - 1;
                                    i17 = i23;
                                }
                                int i24 = i17;
                                if ((j12 & ((~j12) << 6) & (-9187201950435737472L)) != 0) {
                                    int iB = wVar.b(i16);
                                    if (wVar.f3167f != 0 || ((wVar.f3162a[iB >> 3] >> ((iB & 7) << 3)) & 255) == 254) {
                                        i5 = i13;
                                        f5 = f6;
                                    } else {
                                        int i25 = wVar.f3165d;
                                        if (i25 <= 8 || Long.compareUnsigned(((long) wVar.f3166e) * 32, ((long) i25) * 25) > 0) {
                                            i5 = i13;
                                            f5 = f6;
                                            int iB2 = j.f0.b(wVar.f3165d);
                                            long[] jArr5 = wVar.f3162a;
                                            Object[] objArr3 = wVar.f3163b;
                                            float[] fArr3 = wVar.f3164c;
                                            int i26 = wVar.f3165d;
                                            wVar.d(iB2);
                                            long[] jArr6 = wVar.f3162a;
                                            Object[] objArr4 = wVar.f3163b;
                                            float[] fArr4 = wVar.f3164c;
                                            int i27 = wVar.f3165d;
                                            int i28 = 0;
                                            while (i28 < i26) {
                                                if (((jArr5[i28 >> 3] >> ((i28 & 7) << 3)) & 255) < 128) {
                                                    Object obj5 = objArr3[i28];
                                                    int iHashCode2 = (obj5 != null ? obj5.hashCode() : 0) * (-862048943);
                                                    int i29 = iHashCode2 ^ (iHashCode2 << 16);
                                                    i8 = i28;
                                                    int iB3 = wVar.b(i29 >>> 7);
                                                    int i30 = iB3 >> 3;
                                                    int i31 = (iB3 & 7) << 3;
                                                    long j16 = (jArr6[i30] & (~(255 << i31))) | (((long) (i29 & 127)) << i31);
                                                    jArr6[i30] = j16;
                                                    jArr6[(((iB3 - 7) & i27) + (i27 & 7)) >> 3] = j16;
                                                    objArr4[iB3] = obj5;
                                                    fArr4[iB3] = fArr3[i8];
                                                } else {
                                                    i8 = i28;
                                                }
                                                i28 = i8 + 1;
                                                jArr5 = jArr5;
                                            }
                                        } else {
                                            long[] jArr7 = wVar.f3162a;
                                            int i32 = wVar.f3165d;
                                            Object[] objArr5 = wVar.f3163b;
                                            float[] fArr5 = wVar.f3164c;
                                            int i33 = 0;
                                            for (int i34 = (i32 + 7) >> 3; i33 < i34; i34 = i34) {
                                                float[] fArr6 = fArr5;
                                                long j17 = jArr7[i33] & (-9187201950435737472L);
                                                jArr7[i33] = (-72340172838076674L) & ((~j17) + (j17 >>> 7));
                                                i33++;
                                                i13 = i13;
                                                f6 = f6;
                                                fArr5 = fArr6;
                                            }
                                            float[] fArr7 = fArr5;
                                            i5 = i13;
                                            f5 = f6;
                                            int iG = q3.k.G(jArr7);
                                            int i35 = iG - 1;
                                            jArr7[i35] = (jArr7[i35] & 72057594037927935L) | (-72057594037927936L);
                                            jArr7[iG] = jArr7[0];
                                            int i36 = 0;
                                            while (i36 != i32) {
                                                int i37 = i36 >> 3;
                                                int i38 = (i36 & 7) << 3;
                                                long j18 = (jArr7[i37] >> i38) & 255;
                                                if (j18 != 128 && j18 == 254) {
                                                    Object obj6 = objArr5[i36];
                                                    int iHashCode3 = (obj6 != null ? obj6.hashCode() : 0) * (-862048943);
                                                    int i39 = iHashCode3 ^ (iHashCode3 << 16);
                                                    int i40 = i39 >>> 7;
                                                    int iB4 = wVar.b(i40);
                                                    int i41 = i40 & i32;
                                                    int i42 = i32;
                                                    if (((iB4 - i41) & i32) / 8 == ((i36 - i41) & i42) / 8) {
                                                        jArr7[i37] = (jArr7[i37] & (~(255 << i38))) | (((long) (i39 & 127)) << i38);
                                                        jArr7[jArr7.length - 1] = (jArr7[0] & 72057594037927935L) | Long.MIN_VALUE;
                                                        i36++;
                                                    } else {
                                                        int i43 = i36;
                                                        int i44 = iB4 >> 3;
                                                        long j19 = jArr7[i44];
                                                        int i45 = (iB4 & 7) << 3;
                                                        if (((j19 >> i45) & 255) == 128) {
                                                            jArr7[i44] = (j19 & (~(255 << i45))) | (((long) (i39 & 127)) << i45);
                                                            jArr7[i37] = (jArr7[i37] & (~(255 << i38))) | (128 << i38);
                                                            objArr5[iB4] = objArr5[i43];
                                                            objArr5[i43] = null;
                                                            fArr7[iB4] = fArr7[i43];
                                                            fArr7[i43] = 0.0f;
                                                            i9 = i43;
                                                        } else {
                                                            jArr7[i44] = (((long) (i39 & 127)) << i45) | (j19 & (~(255 << i45)));
                                                            Object obj7 = objArr5[iB4];
                                                            objArr5[iB4] = objArr5[i43];
                                                            objArr5[i43] = obj7;
                                                            float f7 = fArr7[iB4];
                                                            fArr7[iB4] = fArr7[i43];
                                                            fArr7[i43] = f7;
                                                            i9 = i43 - 1;
                                                        }
                                                        jArr7[jArr7.length - 1] = (jArr7[0] & 72057594037927935L) | Long.MIN_VALUE;
                                                        i36 = i9 + 1;
                                                    }
                                                    i32 = i42;
                                                } else {
                                                    i36++;
                                                }
                                            }
                                            wVar.f3167f = j.f0.a(wVar.f3165d) - wVar.f3166e;
                                        }
                                        iB = wVar.b(i16);
                                    }
                                    wVar.f3166e++;
                                    int i46 = wVar.f3167f;
                                    long[] jArr8 = wVar.f3162a;
                                    int i47 = iB >> 3;
                                    long j20 = jArr8[i47];
                                    int i48 = (iB & 7) << 3;
                                    wVar.f3167f = i46 - (((j20 >> i48) & 255) != 128 ? 0 : 1);
                                    int i49 = wVar.f3165d;
                                    long j21 = ((~(255 << i48)) & j20) | (j13 << i48);
                                    jArr8[i47] = j21;
                                    jArr8[(((iB - 7) & i49) + (i49 & 7)) >> 3] = j21;
                                    i7 = ~iB;
                                    break;
                                }
                                i20 += 8;
                                i19 = (i19 + i20) & i18;
                                jArr3 = jArr2;
                                j9 = j8;
                                i17 = i24;
                            }
                            if (i7 < 0) {
                                i7 = ~i7;
                            }
                            wVar.f3163b[i7] = obj4;
                            wVar.f3164c[i7] = f5;
                            i6 = 8;
                        } else {
                            jArr2 = jArr3;
                            j8 = j9;
                            i5 = i13;
                            i6 = i10;
                        }
                        j9 = j8 >> i6;
                        i13 = i5 + 1;
                        i10 = i6;
                        fArr2 = fArr2;
                        objArr2 = objArr2;
                        jArr3 = jArr2;
                    }
                    objArr = objArr2;
                    fArr = fArr2;
                    jArr = jArr3;
                    obj = null;
                    if (i12 != i10) {
                        break;
                    }
                } else {
                    objArr = objArr2;
                    fArr = fArr2;
                    jArr = jArr3;
                    obj = null;
                }
                if (i11 == length) {
                    break;
                }
                i11++;
                fArr2 = fArr;
                objArr2 = objArr;
                jArr3 = jArr;
                i10 = 8;
            }
        } else {
            j5 = 255;
            j6 = 128;
            j7 = -9187201950435737472L;
            obj = null;
        }
        wVar2.a();
        e1 e1Var = t0().f4788l;
        if (e1Var != null && (snapshotObserver = ((q1.u) e1Var).getSnapshotObserver()) != null) {
            snapshotObserver.a(i1Var, e.f4804g, new t2(i1Var, 5, this));
        }
        Object[] objArr6 = wVar2.f3163b;
        long[] jArr9 = wVar2.f3162a;
        int length2 = jArr9.length - 2;
        if (length2 >= 0) {
            int i50 = 0;
            while (true) {
                long j22 = jArr9[i50];
                if ((((~j22) << 7) & j22 & j7) != j7) {
                    int i51 = 8 - ((~(i50 - length2)) >>> 31);
                    int i52 = 0;
                    while (i52 < i51) {
                        if ((j22 & j5) >= j6) {
                            obj3 = obj;
                        } else {
                            if (objArr6[(i50 << 3) + i52] != null) {
                                throw new ClassCastException();
                            }
                            obj3 = obj;
                            if (wVar.c(obj3) < 0 && (m0VarV0 = v0()) != null) {
                                do {
                                    j.w wVar3 = m0VarV0.f4926m;
                                    if (wVar3 != null && wVar3.c(obj3) >= 0) {
                                        break;
                                    } else {
                                        m0VarV0 = m0VarV0.v0();
                                    }
                                } while (m0VarV0 != null);
                            }
                        }
                        j22 >>= 8;
                        i52++;
                        obj = obj3;
                    }
                    obj2 = obj;
                    if (i51 != 8) {
                        break;
                    }
                } else {
                    obj2 = obj;
                }
                if (i50 == length2) {
                    break;
                }
                i50++;
                obj = obj2;
            }
        }
        wVar.a();
    }

    public abstract m0 q0();

    public abstract n1.p r0();

    public abstract boolean s0();

    public abstract d0 t0();

    public abstract n1.g0 u0();

    public abstract m0 v0();

    public abstract long w0();

    public abstract void y0();
}
