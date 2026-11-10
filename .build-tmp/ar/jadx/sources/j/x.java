package j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f3168a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f3169b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f3170c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3171d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3172e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3173f;

    public /* synthetic */ x() {
        this(6);
    }

    public x(int i5) {
        this.f3168a = f0.f3102a;
        this.f3169b = k.a.f3355c;
        this.f3170c = k.f3116a;
        if (i5 >= 0) {
            d(f0.d(i5));
        } else {
            k.a.c("Capacity must be a positive value.");
            throw null;
        }
    }

    public final int a(int i5) {
        int i6 = this.f3171d;
        int i7 = i5 & i6;
        int i8 = 0;
        while (true) {
            long[] jArr = this.f3168a;
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

    public final int b(Object obj) {
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
        int i9 = this.f3171d;
        int i10 = i7 & i9;
        int i11 = 0;
        while (true) {
            long[] jArr2 = this.f3168a;
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
                if (b4.l.a(this.f3169b[iNumberOfTrailingZeros], obj)) {
                    return iNumberOfTrailingZeros;
                }
                j11 &= j11 - 1;
                i5 = i15;
            }
            int i16 = i5;
            if ((((~j8) << 6) & j8 & (-9187201950435737472L)) != 0) {
                int iA = a(i7);
                long j12 = 255;
                if (this.f3173f != 0 || ((this.f3168a[iA >> 3] >> ((iA & 7) << 3)) & 255) == 254) {
                    j5 = 255;
                    j6 = j9;
                    j7 = 128;
                } else {
                    int i17 = this.f3171d;
                    if (i17 > 8) {
                        int i18 = 8;
                        if (Long.compareUnsigned(((long) this.f3172e) * 32, ((long) i17) * 25) <= 0) {
                            long[] jArr3 = this.f3168a;
                            int i19 = this.f3171d;
                            Object[] objArr2 = this.f3169b;
                            int[] iArr = this.f3170c;
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
                            long j15 = 72057594037927935L;
                            jArr3[i23] = (jArr3[i23] & 72057594037927935L) | (-72057594037927936L);
                            jArr3[iG] = jArr3[0];
                            int i24 = 0;
                            while (i24 != i19) {
                                int i25 = i24 >> 3;
                                int i26 = (i24 & 7) << 3;
                                long j16 = (jArr3[i25] >> i26) & j5;
                                if (j16 != 128 && j16 == 254) {
                                    Object obj2 = objArr2[i24];
                                    int iHashCode2 = (obj2 != null ? obj2.hashCode() : 0) * i16;
                                    int i27 = iHashCode2 ^ (iHashCode2 << 16);
                                    int i28 = i27 >>> 7;
                                    int iA2 = a(i28);
                                    int i29 = i28 & i19;
                                    long j17 = j15;
                                    if (((iA2 - i29) & i19) / 8 == ((i24 - i29) & i19) / i22) {
                                        jArr3[i25] = (((long) (i27 & 127)) << i26) | (jArr3[i25] & (~(j5 << i26)));
                                        jArr3[jArr3.length - 1] = (jArr3[0] & j17) | Long.MIN_VALUE;
                                        i24++;
                                        i22 = i22;
                                        j15 = j17;
                                    } else {
                                        int i30 = i22;
                                        int i31 = iA2 >> 3;
                                        long j18 = jArr3[i31];
                                        int i32 = (iA2 & 7) << 3;
                                        if (((j18 >> i32) & j5) == 128) {
                                            objArr = objArr2;
                                            jArr3[i31] = ((~(j5 << i32)) & j18) | (((long) (i27 & 127)) << i32);
                                            jArr3[i25] = (jArr3[i25] & (~(j5 << i26))) | (128 << i26);
                                            objArr[iA2] = objArr[i24];
                                            objArr[i24] = null;
                                            iArr[iA2] = iArr[i24];
                                            iArr[i24] = 0;
                                        } else {
                                            objArr = objArr2;
                                            jArr3[i31] = (((long) (i27 & 127)) << i32) | ((~(j5 << i32)) & j18);
                                            Object obj3 = objArr[iA2];
                                            objArr[iA2] = objArr[i24];
                                            objArr[i24] = obj3;
                                            int i33 = iArr[iA2];
                                            iArr[iA2] = iArr[i24];
                                            iArr[i24] = i33;
                                            i24--;
                                        }
                                        jArr3[jArr3.length - 1] = (jArr3[0] & j17) | Long.MIN_VALUE;
                                        i24++;
                                        i19 = i19;
                                        i22 = i30;
                                        j15 = j17;
                                        objArr2 = objArr;
                                    }
                                } else {
                                    i24++;
                                }
                            }
                            this.f3173f = f0.a(this.f3171d) - this.f3172e;
                        }
                        iA = a(i7);
                    }
                    j5 = 255;
                    j6 = j9;
                    j7 = 128;
                    int iB = f0.b(this.f3171d);
                    long[] jArr4 = this.f3168a;
                    Object[] objArr3 = this.f3169b;
                    int[] iArr2 = this.f3170c;
                    int i34 = this.f3171d;
                    d(iB);
                    long[] jArr5 = this.f3168a;
                    Object[] objArr4 = this.f3169b;
                    int[] iArr3 = this.f3170c;
                    int i35 = this.f3171d;
                    int i36 = 0;
                    while (i36 < i34) {
                        if (((jArr4[i36 >> 3] >> ((i36 & 7) << 3)) & 255) < 128) {
                            Object obj4 = objArr3[i36];
                            int iHashCode3 = (obj4 != null ? obj4.hashCode() : 0) * i16;
                            int i37 = iHashCode3 ^ (iHashCode3 << 16);
                            int iA3 = a(i37 >>> 7);
                            jArr = jArr5;
                            long j19 = i37 & 127;
                            int i38 = iA3 >> 3;
                            int i39 = (iA3 & 7) << 3;
                            long j20 = (jArr[i38] & (~(255 << i39))) | (j19 << i39);
                            jArr[i38] = j20;
                            jArr[(((iA3 - 7) & i35) + (i35 & 7)) >> 3] = j20;
                            objArr4[iA3] = obj4;
                            iArr3[iA3] = iArr2[i36];
                        } else {
                            jArr = jArr5;
                        }
                        i36++;
                        jArr4 = jArr4;
                        jArr5 = jArr;
                    }
                    iA = a(i7);
                }
                this.f3172e++;
                int i40 = this.f3173f;
                long[] jArr6 = this.f3168a;
                int i41 = iA >> 3;
                long j21 = jArr6[i41];
                int i42 = (iA & 7) << 3;
                this.f3173f = i40 - (((j21 >> i42) & j5) == j7 ? 1 : 0);
                int i43 = this.f3171d;
                long j22 = (j21 & (~(j5 << i42))) | (j6 << i42);
                jArr6[i41] = j22;
                jArr6[(((iA - 7) & i43) + (i43 & 7)) >> 3] = j22;
                return ~iA;
            }
            i11 += 8;
            i10 = (i10 + i11) & i9;
            i8 = i14;
            i5 = i16;
        }
    }

    public final int c(Object obj) {
        int i5 = 0;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i6 = iHashCode ^ (iHashCode << 16);
        int i7 = i6 & 127;
        int i8 = this.f3171d;
        int i9 = i6 >>> 7;
        while (true) {
            int i10 = i9 & i8;
            long[] jArr = this.f3168a;
            int i11 = i10 >> 3;
            int i12 = (i10 & 7) << 3;
            long j5 = ((jArr[i11 + 1] << (64 - i12)) & ((-i12) >> 63)) | (jArr[i11] >>> i12);
            long j6 = (((long) i7) * 72340172838076673L) ^ j5;
            for (long j7 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j7) >> 3) + i10) & i8;
                if (b4.l.a(this.f3169b[iNumberOfTrailingZeros], obj)) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((j5 & ((~j5) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i5 += 8;
            i9 = i10 + i5;
        }
    }

    public final void d(int i5) {
        long[] jArr;
        int iMax = i5 > 0 ? Math.max(7, f0.c(i5)) : 0;
        this.f3171d = iMax;
        if (iMax == 0) {
            jArr = f0.f3102a;
        } else {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            q3.k.F(jArr);
        }
        this.f3168a = jArr;
        int i6 = iMax >> 3;
        long j5 = 255 << ((iMax & 7) << 3);
        jArr[i6] = (jArr[i6] & (~j5)) | j5;
        this.f3173f = f0.a(this.f3171d) - this.f3172e;
        this.f3169b = new Object[iMax];
        this.f3170c = new int[iMax];
    }

    public final void e(int i5) {
        this.f3172e--;
        long[] jArr = this.f3168a;
        int i6 = this.f3171d;
        int i7 = i5 >> 3;
        int i8 = (i5 & 7) << 3;
        long j5 = (jArr[i7] & (~(255 << i8))) | (254 << i8);
        jArr[i7] = j5;
        jArr[(((i5 - 7) & i6) + (i6 & 7)) >> 3] = j5;
        this.f3169b[i5] = null;
    }

    public final boolean equals(Object obj) {
        boolean z2;
        boolean z3 = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        if (xVar.f3172e != this.f3172e) {
            return false;
        }
        Object[] objArr = this.f3169b;
        int[] iArr = this.f3170c;
        long[] jArr = this.f3168a;
        int length = jArr.length - 2;
        if (length < 0) {
            return true;
        }
        int i5 = 0;
        loop0: while (true) {
            long j5 = jArr[i5];
            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i6 = 8 - ((~(i5 - length)) >>> 31);
                int i7 = 0;
                while (i7 < i6) {
                    if ((255 & j5) < 128) {
                        int i8 = (i5 << 3) + i7;
                        Object obj2 = objArr[i8];
                        int i9 = iArr[i8];
                        int iC = xVar.c(obj2);
                        if (iC < 0 || i9 != xVar.f3170c[iC]) {
                            break loop0;
                        }
                    }
                    j5 >>= 8;
                    i7++;
                    z3 = z3;
                }
                z2 = z3;
                if (i6 != 8) {
                    return z2;
                }
            } else {
                z2 = z3;
            }
            if (i5 == length) {
                return z2;
            }
            i5++;
            z3 = z2;
        }
        return false;
    }

    public final int hashCode() {
        Object[] objArr = this.f3169b;
        int[] iArr = this.f3170c;
        long[] jArr = this.f3168a;
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
                        iHashCode += Integer.hashCode(iArr[i8]) ^ (obj != null ? obj.hashCode() : 0);
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

    /* JADX WARN: Code duplicated, block: B:23:0x006a A[DONT_INVERT, PHI: r8
      0x006a: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:10:0x002c, B:22:0x0068] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:24:0x006c A[LOOP:0: B:9:0x001e->B:24:0x006c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:28:0x006f A[EDGE_INSN: B:28:0x006f->B:25:0x006f BREAK  A[LOOP:0: B:9:0x001e->B:24:0x006c], SYNTHETIC] */
    public final String toString() {
        if (this.f3172e == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Object[] objArr = this.f3169b;
        int[] iArr = this.f3170c;
        long[] jArr = this.f3168a;
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
                            int i10 = iArr[i9];
                            if (obj == this) {
                                obj = "(this)";
                            }
                            sb.append(obj);
                            sb.append("=");
                            sb.append(i10);
                            i6++;
                            if (i6 < this.f3172e) {
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
