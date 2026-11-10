package j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f3176a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f3177b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f3178c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3179d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3180e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3181f;

    public /* synthetic */ z() {
        this(6);
    }

    public z(int i5) {
        this.f3176a = f0.f3102a;
        Object[] objArr = k.a.f3355c;
        this.f3177b = objArr;
        this.f3178c = objArr;
        if (i5 >= 0) {
            f(f0.d(i5));
        } else {
            k.a.c("Capacity must be a positive value.");
            throw null;
        }
    }

    public final void a() {
        this.f3180e = 0;
        long[] jArr = this.f3176a;
        if (jArr != f0.f3102a) {
            q3.k.F(jArr);
            long[] jArr2 = this.f3176a;
            int i5 = this.f3179d;
            int i6 = i5 >> 3;
            long j5 = 255 << ((i5 & 7) << 3);
            jArr2[i6] = (jArr2[i6] & (~j5)) | j5;
        }
        q3.k.E(this.f3178c, 0, this.f3179d);
        q3.k.E(this.f3177b, 0, this.f3179d);
        this.f3181f = f0.a(this.f3179d) - this.f3180e;
    }

    public final boolean b(Object obj) {
        int iNumberOfTrailingZeros;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i5 = iHashCode ^ (iHashCode << 16);
        int i6 = i5 & 127;
        int i7 = this.f3179d;
        int i8 = (i5 >>> 7) & i7;
        int i9 = 0;
        loop0: while (true) {
            long[] jArr = this.f3176a;
            int i10 = i8 >> 3;
            int i11 = (i8 & 7) << 3;
            long j5 = ((jArr[i10 + 1] << (64 - i11)) & ((-i11) >> 63)) | (jArr[i10] >>> i11);
            long j6 = (((long) i6) * 72340172838076673L) ^ j5;
            for (long j7 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j7) >> 3) + i8) & i7;
                if (b4.l.a(this.f3177b[iNumberOfTrailingZeros], obj)) {
                    break loop0;
                }
            }
            if ((j5 & ((~j5) << 6) & (-9187201950435737472L)) != 0) {
                iNumberOfTrailingZeros = -1;
                break;
            }
            i9 += 8;
            i8 = (i8 + i9) & i7;
        }
        return iNumberOfTrailingZeros >= 0;
    }

    public final int c(int i5) {
        int i6 = this.f3179d;
        int i7 = i5 & i6;
        int i8 = 0;
        while (true) {
            long[] jArr = this.f3176a;
            int i9 = i7 >> 3;
            int i10 = (i7 & 7) << 3;
            long j5 = ((jArr[i9 + 1] << (64 - i10)) & ((-i10) >> 63)) | (jArr[i9] >>> i10);
            long j6 = j5 & ((~j5) << 7) & (-9187201950435737472L);
            if (j6 != 0) {
                return (i7 + (Long.numberOfTrailingZeros(j6) >> 3)) & i6;
            }
            i8 += 8;
            i7 = (i7 + i8) & i6;
        }
    }

    public final int d(Object obj) {
        long j5;
        long j6;
        long j7;
        long[] jArr;
        Object[] objArr;
        int i5 = -862048943;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i6 = iHashCode ^ (iHashCode << 16);
        int i7 = i6 >>> 7;
        int i8 = i6 & 127;
        int i9 = this.f3179d;
        int i10 = i7 & i9;
        int i11 = 0;
        while (true) {
            long[] jArr2 = this.f3176a;
            int i12 = i10 >> 3;
            int i13 = (i10 & 7) << 3;
            long j8 = ((jArr2[i12 + 1] << (64 - i13)) & ((-i13) >> 63)) | (jArr2[i12] >>> i13);
            long j9 = i8;
            int i14 = i8;
            long j10 = j8 ^ (j9 * 72340172838076673L);
            long j11 = (~j10) & (j10 - 72340172838076673L) & (-9187201950435737472L);
            while (j11 != 0) {
                int iNumberOfTrailingZeros = (i10 + (Long.numberOfTrailingZeros(j11) >> 3)) & i9;
                int i15 = i5;
                if (b4.l.a(this.f3177b[iNumberOfTrailingZeros], obj)) {
                    return iNumberOfTrailingZeros;
                }
                j11 &= j11 - 1;
                i5 = i15;
            }
            int i16 = i5;
            if ((((~j8) << 6) & j8 & (-9187201950435737472L)) != 0) {
                int iC = c(i7);
                long j12 = 255;
                if (this.f3181f != 0 || ((this.f3176a[iC >> 3] >> ((iC & 7) << 3)) & 255) == 254) {
                    j5 = 255;
                    j6 = j9;
                    j7 = 128;
                } else {
                    int i17 = this.f3179d;
                    if (i17 > 8) {
                        int i18 = 8;
                        if (Long.compareUnsigned(((long) this.f3180e) * 32, ((long) i17) * 25) <= 0) {
                            long[] jArr3 = this.f3176a;
                            int i19 = this.f3179d;
                            Object[] objArr2 = this.f3177b;
                            Object[] objArr3 = this.f3178c;
                            j7 = 128;
                            int i20 = (i19 + 7) >> 3;
                            int i21 = 0;
                            while (i21 < i20) {
                                long j13 = j12;
                                long j14 = jArr3[i21] & (-9187201950435737472L);
                                jArr3[i21] = (-72340172838076674L) & ((~j14) + (j14 >>> 7));
                                i21++;
                                i18 = i18;
                                j9 = j9;
                                j12 = j13;
                            }
                            j5 = j12;
                            j6 = j9;
                            int i22 = i18;
                            int iG = q3.k.G(jArr3);
                            int i23 = iG - 1;
                            jArr3[i23] = (jArr3[i23] & 72057594037927935L) | (-72057594037927936L);
                            jArr3[iG] = jArr3[0];
                            int i24 = 0;
                            while (i24 != i19) {
                                int i25 = i24 >> 3;
                                int i26 = (i24 & 7) << 3;
                                long j15 = (jArr3[i25] >> i26) & j5;
                                if (j15 != 128 && j15 == 254) {
                                    Object obj2 = objArr2[i24];
                                    int iHashCode2 = (obj2 != null ? obj2.hashCode() : 0) * i16;
                                    int i27 = iHashCode2 ^ (iHashCode2 << 16);
                                    int i28 = i27 >>> 7;
                                    int iC2 = c(i28);
                                    int i29 = i28 & i19;
                                    if (((iC2 - i29) & i19) / i22 == ((i24 - i29) & i19) / i22) {
                                        jArr3[i25] = (((long) (i27 & 127)) << i26) | (jArr3[i25] & (~(j5 << i26)));
                                        jArr3[jArr3.length - 1] = jArr3[0];
                                        i24++;
                                        i22 = i22;
                                    } else {
                                        int i30 = i22;
                                        int i31 = iC2 >> 3;
                                        long j16 = jArr3[i31];
                                        int i32 = (iC2 & 7) << 3;
                                        if (((j16 >> i32) & j5) == 128) {
                                            objArr = objArr2;
                                            jArr3[i31] = ((~(j5 << i32)) & j16) | (((long) (i27 & 127)) << i32);
                                            jArr3[i25] = (jArr3[i25] & (~(j5 << i26))) | (128 << i26);
                                            objArr[iC2] = objArr[i24];
                                            objArr[i24] = null;
                                            objArr3[iC2] = objArr3[i24];
                                            objArr3[i24] = null;
                                        } else {
                                            objArr = objArr2;
                                            jArr3[i31] = (((long) (i27 & 127)) << i32) | ((~(j5 << i32)) & j16);
                                            Object obj3 = objArr[iC2];
                                            objArr[iC2] = objArr[i24];
                                            objArr[i24] = obj3;
                                            Object obj4 = objArr3[iC2];
                                            objArr3[iC2] = objArr3[i24];
                                            objArr3[i24] = obj4;
                                            i24--;
                                        }
                                        jArr3[jArr3.length - 1] = jArr3[0];
                                        i24++;
                                        i22 = i30;
                                        i19 = i19;
                                        objArr2 = objArr;
                                    }
                                } else {
                                    i24++;
                                }
                            }
                            this.f3181f = f0.a(this.f3179d) - this.f3180e;
                        }
                        iC = c(i7);
                    }
                    j5 = 255;
                    j6 = j9;
                    j7 = 128;
                    int iB = f0.b(this.f3179d);
                    long[] jArr4 = this.f3176a;
                    Object[] objArr4 = this.f3177b;
                    Object[] objArr5 = this.f3178c;
                    int i33 = this.f3179d;
                    f(iB);
                    long[] jArr5 = this.f3176a;
                    Object[] objArr6 = this.f3177b;
                    Object[] objArr7 = this.f3178c;
                    int i34 = this.f3179d;
                    int i35 = 0;
                    while (i35 < i33) {
                        if (((jArr4[i35 >> 3] >> ((i35 & 7) << 3)) & 255) < 128) {
                            Object obj5 = objArr4[i35];
                            int iHashCode3 = (obj5 != null ? obj5.hashCode() : 0) * i16;
                            int i36 = iHashCode3 ^ (iHashCode3 << 16);
                            int iC3 = c(i36 >>> 7);
                            jArr = jArr5;
                            long j17 = i36 & 127;
                            int i37 = iC3 >> 3;
                            int i38 = (iC3 & 7) << 3;
                            long j18 = (jArr[i37] & (~(255 << i38))) | (j17 << i38);
                            jArr[i37] = j18;
                            jArr[(((iC3 - 7) & i34) + (i34 & 7)) >> 3] = j18;
                            objArr6[iC3] = obj5;
                            objArr7[iC3] = objArr5[i35];
                        } else {
                            jArr = jArr5;
                        }
                        i35++;
                        jArr4 = jArr4;
                        jArr5 = jArr;
                    }
                    iC = c(i7);
                }
                this.f3180e++;
                int i39 = this.f3181f;
                long[] jArr6 = this.f3176a;
                int i40 = iC >> 3;
                long j19 = jArr6[i40];
                int i41 = (iC & 7) << 3;
                this.f3181f = i39 - (((j19 >> i41) & j5) == j7 ? 1 : 0);
                int i42 = this.f3179d;
                long j20 = (j19 & (~(j5 << i41))) | (j6 << i41);
                jArr6[i40] = j20;
                jArr6[(((iC - 7) & i42) + (i42 & 7)) >> 3] = j20;
                return ~iC;
            }
            i11 += 8;
            i10 = (i10 + i11) & i9;
            i8 = i14;
            i5 = i16;
        }
    }

    public final Object e(Object obj) {
        int iNumberOfTrailingZeros;
        int i5 = 0;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i6 = iHashCode ^ (iHashCode << 16);
        int i7 = i6 & 127;
        int i8 = this.f3179d;
        int i9 = i6 >>> 7;
        loop0: while (true) {
            int i10 = i9 & i8;
            long[] jArr = this.f3176a;
            int i11 = i10 >> 3;
            int i12 = (i10 & 7) << 3;
            long j5 = ((jArr[i11 + 1] << (64 - i12)) & ((-i12) >> 63)) | (jArr[i11] >>> i12);
            long j6 = (((long) i7) * 72340172838076673L) ^ j5;
            for (long j7 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j7) >> 3) + i10) & i8;
                if (b4.l.a(this.f3177b[iNumberOfTrailingZeros], obj)) {
                    break loop0;
                }
            }
            if ((j5 & ((~j5) << 6) & (-9187201950435737472L)) != 0) {
                iNumberOfTrailingZeros = -1;
                break;
            }
            i5 += 8;
            i9 = i10 + i5;
        }
        if (iNumberOfTrailingZeros >= 0) {
            return this.f3178c[iNumberOfTrailingZeros];
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x006f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:33:0x0071 A[LOOP:0: B:14:0x0023->B:33:0x0071, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:35:0x0074 A[EDGE_INSN: B:35:0x0074->B:34:0x0074 BREAK  A[LOOP:0: B:14:0x0023->B:33:0x0071], SYNTHETIC] */
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        if (zVar.f3180e != this.f3180e) {
            return false;
        }
        Object[] objArr = this.f3177b;
        Object[] objArr2 = this.f3178c;
        long[] jArr = this.f3176a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i5 = 0;
            while (true) {
                long j5 = jArr[i5];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i5 != length) {
                        break;
                        break;
                    }
                    i5++;
                } else {
                    int i6 = 8 - ((~(i5 - length)) >>> 31);
                    for (int i7 = 0; i7 < i6; i7++) {
                        if ((255 & j5) < 128) {
                            int i8 = (i5 << 3) + i7;
                            Object obj2 = objArr[i8];
                            Object obj3 = objArr2[i8];
                            if (obj3 == null) {
                                if (zVar.e(obj2) != null || !zVar.b(obj2)) {
                                    return false;
                                }
                            } else if (!obj3.equals(zVar.e(obj2))) {
                                return false;
                            }
                        }
                        j5 >>= 8;
                    }
                    if (i6 != 8) {
                        break;
                    }
                    if (i5 != length) {
                        break;
                    }
                    i5++;
                }
            }
        }
        return true;
    }

    public final void f(int i5) {
        long[] jArr;
        int iMax = i5 > 0 ? Math.max(7, f0.c(i5)) : 0;
        this.f3179d = iMax;
        if (iMax == 0) {
            jArr = f0.f3102a;
        } else {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            q3.k.F(jArr);
            int i6 = iMax >> 3;
            long j5 = 255 << ((iMax & 7) << 3);
            jArr[i6] = (jArr[i6] & (~j5)) | j5;
        }
        this.f3176a = jArr;
        this.f3181f = f0.a(this.f3179d) - this.f3180e;
        Object[] objArr = k.a.f3355c;
        this.f3177b = iMax == 0 ? objArr : new Object[iMax];
        if (iMax != 0) {
            objArr = new Object[iMax];
        }
        this.f3178c = objArr;
    }

    public final Object g(Object obj) {
        int iNumberOfTrailingZeros;
        int i5 = 0;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i6 = iHashCode ^ (iHashCode << 16);
        int i7 = i6 & 127;
        int i8 = this.f3179d;
        int i9 = i6 >>> 7;
        loop0: while (true) {
            int i10 = i9 & i8;
            long[] jArr = this.f3176a;
            int i11 = i10 >> 3;
            int i12 = (i10 & 7) << 3;
            long j5 = ((jArr[i11 + 1] << (64 - i12)) & ((-i12) >> 63)) | (jArr[i11] >>> i12);
            long j6 = (((long) i7) * 72340172838076673L) ^ j5;
            for (long j7 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j7) >> 3) + i10) & i8;
                if (b4.l.a(this.f3177b[iNumberOfTrailingZeros], obj)) {
                    break loop0;
                }
            }
            if ((j5 & ((~j5) << 6) & (-9187201950435737472L)) != 0) {
                iNumberOfTrailingZeros = -1;
                break;
            }
            i5 += 8;
            i9 = i10 + i5;
        }
        if (iNumberOfTrailingZeros >= 0) {
            return h(iNumberOfTrailingZeros);
        }
        return null;
    }

    public final Object h(int i5) {
        this.f3180e--;
        long[] jArr = this.f3176a;
        int i6 = this.f3179d;
        int i7 = i5 >> 3;
        int i8 = (i5 & 7) << 3;
        long j5 = (jArr[i7] & (~(255 << i8))) | (254 << i8);
        jArr[i7] = j5;
        jArr[(((i5 - 7) & i6) + (i6 & 7)) >> 3] = j5;
        this.f3177b[i5] = null;
        Object[] objArr = this.f3178c;
        Object obj = objArr[i5];
        objArr[i5] = null;
        return obj;
    }

    public final int hashCode() {
        Object[] objArr = this.f3177b;
        Object[] objArr2 = this.f3178c;
        long[] jArr = this.f3176a;
        int length = jArr.length - 2;
        if (length < 0) {
            return 0;
        }
        int i5 = 0;
        int iHashCode = 0;
        while (true) {
            long j5 = jArr[i5];
            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i6 = 8 - ((~(i5 - length)) >>> 31);
                for (int i7 = 0; i7 < i6; i7++) {
                    if ((255 & j5) < 128) {
                        int i8 = (i5 << 3) + i7;
                        Object obj = objArr[i8];
                        Object obj2 = objArr2[i8];
                        iHashCode += (obj2 != null ? obj2.hashCode() : 0) ^ (obj != null ? obj.hashCode() : 0);
                    }
                    j5 >>= 8;
                }
                if (i6 != 8) {
                    return iHashCode;
                }
            }
            if (i5 == length) {
                return iHashCode;
            }
            i5++;
        }
    }

    public final void i(Object obj, Object obj2) {
        int iD = d(obj);
        if (iD < 0) {
            iD = ~iD;
        }
        this.f3177b[iD] = obj;
        this.f3178c[iD] = obj2;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0070 A[DONT_INVERT, PHI: r8
      0x0070: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:10:0x002c, B:25:0x006e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:27:0x0072 A[LOOP:0: B:9:0x001e->B:27:0x0072, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:31:0x0075 A[EDGE_INSN: B:31:0x0075->B:28:0x0075 BREAK  A[LOOP:0: B:9:0x001e->B:27:0x0072], SYNTHETIC] */
    public final String toString() {
        if (this.f3180e == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Object[] objArr = this.f3177b;
        Object[] objArr2 = this.f3178c;
        long[] jArr = this.f3176a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i5 = 0;
            int i6 = 0;
            while (true) {
                long j5 = jArr[i5];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i5 != length) {
                        break;
                        break;
                    }
                    i5++;
                } else {
                    int i7 = 8 - ((~(i5 - length)) >>> 31);
                    for (int i8 = 0; i8 < i7; i8++) {
                        if ((255 & j5) < 128) {
                            int i9 = (i5 << 3) + i8;
                            Object obj = objArr[i9];
                            Object obj2 = objArr2[i9];
                            if (obj == this) {
                                obj = "(this)";
                            }
                            sb.append(obj);
                            sb.append("=");
                            if (obj2 == this) {
                                obj2 = "(this)";
                            }
                            sb.append(obj2);
                            i6++;
                            if (i6 < this.f3180e) {
                                sb.append(", ");
                            }
                        }
                        j5 >>= 8;
                    }
                    if (i7 != 8) {
                        break;
                    }
                    if (i5 != length) {
                        break;
                    }
                    i5++;
                }
            }
        }
        sb.append('}');
        String string = sb.toString();
        b4.l.d("toString(...)", string);
        return string;
    }
}
