package j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f3066a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f3067b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3068c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3069d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3070e;

    public /* synthetic */ a0() {
        this(6);
    }

    public a0(int i5) {
        this.f3066a = f0.f3102a;
        this.f3067b = k.a.f3355c;
        if (i5 >= 0) {
            f(f0.d(i5));
        } else {
            k.a.c("Capacity must be a positive value.");
            throw null;
        }
    }

    public final boolean a(Object obj) {
        int i5 = this.f3069d;
        this.f3067b[d(obj)] = obj;
        return this.f3069d != i5;
    }

    public final void b() {
        this.f3069d = 0;
        long[] jArr = this.f3066a;
        if (jArr != f0.f3102a) {
            q3.k.F(jArr);
            long[] jArr2 = this.f3066a;
            int i5 = this.f3068c;
            int i6 = i5 >> 3;
            long j5 = 255 << ((i5 & 7) << 3);
            jArr2[i6] = (jArr2[i6] & (~j5)) | j5;
        }
        q3.k.E(this.f3067b, 0, this.f3068c);
        this.f3070e = f0.a(this.f3068c) - this.f3069d;
    }

    public final boolean c(Object obj) {
        int iNumberOfTrailingZeros;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i5 = iHashCode ^ (iHashCode << 16);
        int i6 = i5 & 127;
        int i7 = this.f3068c;
        int i8 = (i5 >>> 7) & i7;
        int i9 = 0;
        loop0: while (true) {
            long[] jArr = this.f3066a;
            int i10 = i8 >> 3;
            int i11 = (i8 & 7) << 3;
            long j5 = ((jArr[i10 + 1] << (64 - i11)) & ((-i11) >> 63)) | (jArr[i10] >>> i11);
            long j6 = (((long) i6) * 72340172838076673L) ^ j5;
            for (long j7 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j7) >> 3) + i8) & i7;
                if (b4.l.a(this.f3067b[iNumberOfTrailingZeros], obj)) {
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
        int i9 = this.f3068c;
        int i10 = i7 & i9;
        int i11 = 0;
        while (true) {
            long[] jArr2 = this.f3066a;
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
                if (b4.l.a(this.f3067b[iNumberOfTrailingZeros], obj)) {
                    return iNumberOfTrailingZeros;
                }
                j11 &= j11 - 1;
                i5 = i15;
            }
            int i16 = i5;
            if ((((~j8) << 6) & j8 & (-9187201950435737472L)) != 0) {
                int iE = e(i7);
                long j12 = 255;
                if (this.f3070e != 0 || ((this.f3066a[iE >> 3] >> ((iE & 7) << 3)) & 255) == 254) {
                    j5 = 255;
                    j6 = j9;
                    j7 = 128;
                } else {
                    int i17 = this.f3068c;
                    if (i17 > 8) {
                        int i18 = 8;
                        if (Long.compareUnsigned(((long) this.f3069d) * 32, ((long) i17) * 25) <= 0) {
                            long[] jArr3 = this.f3066a;
                            int i19 = this.f3068c;
                            Object[] objArr2 = this.f3067b;
                            int i20 = (i19 + 7) >> 3;
                            int i21 = 0;
                            j7 = 128;
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
                                    int iE2 = e(i28);
                                    int i29 = i28 & i19;
                                    if (((iE2 - i29) & i19) / i22 == ((i24 - i29) & i19) / i22) {
                                        long j17 = j15;
                                        jArr3[i25] = (((long) (i27 & 127)) << i26) | ((~(j5 << i26)) & jArr3[i25]);
                                        jArr3[jArr3.length - 1] = (jArr3[0] & j17) | Long.MIN_VALUE;
                                        i24++;
                                        j15 = j17;
                                    } else {
                                        long j18 = j15;
                                        int i30 = iE2 >> 3;
                                        long j19 = jArr3[i30];
                                        int i31 = (iE2 & 7) << 3;
                                        if (((j19 >> i31) & j5) == 128) {
                                            objArr = objArr2;
                                            jArr3[i30] = ((~(j5 << i31)) & j19) | (((long) (i27 & 127)) << i31);
                                            jArr3[i25] = (jArr3[i25] & (~(j5 << i26))) | (128 << i26);
                                            objArr[iE2] = objArr[i24];
                                            objArr[i24] = null;
                                        } else {
                                            objArr = objArr2;
                                            jArr3[i30] = (((long) (i27 & 127)) << i31) | ((~(j5 << i31)) & j19);
                                            Object obj3 = objArr[iE2];
                                            objArr[iE2] = objArr[i24];
                                            objArr[i24] = obj3;
                                            i24--;
                                        }
                                        jArr3[jArr3.length - 1] = (jArr3[0] & j18) | Long.MIN_VALUE;
                                        i24++;
                                        j15 = j18;
                                        i22 = i22;
                                        i19 = i19;
                                        objArr2 = objArr;
                                    }
                                } else {
                                    i24++;
                                }
                            }
                            this.f3070e = f0.a(this.f3068c) - this.f3069d;
                        }
                        iE = e(i7);
                    }
                    j5 = 255;
                    j6 = j9;
                    j7 = 128;
                    int iB = f0.b(this.f3068c);
                    long[] jArr4 = this.f3066a;
                    Object[] objArr3 = this.f3067b;
                    int i32 = this.f3068c;
                    f(iB);
                    long[] jArr5 = this.f3066a;
                    Object[] objArr4 = this.f3067b;
                    int i33 = this.f3068c;
                    int i34 = 0;
                    while (i34 < i32) {
                        if (((jArr4[i34 >> 3] >> ((i34 & 7) << 3)) & 255) < 128) {
                            Object obj4 = objArr3[i34];
                            int iHashCode3 = (obj4 != null ? obj4.hashCode() : 0) * i16;
                            int i35 = iHashCode3 ^ (iHashCode3 << 16);
                            int iE3 = e(i35 >>> 7);
                            long j20 = i35 & 127;
                            int i36 = iE3 >> 3;
                            int i37 = (iE3 & 7) << 3;
                            jArr = jArr5;
                            long j21 = (jArr5[i36] & (~(255 << i37))) | (j20 << i37);
                            jArr[i36] = j21;
                            jArr[(((iE3 - 7) & i33) + (i33 & 7)) >> 3] = j21;
                            objArr4[iE3] = obj4;
                        } else {
                            jArr = jArr5;
                        }
                        i34++;
                        jArr4 = jArr4;
                        jArr5 = jArr;
                    }
                    iE = e(i7);
                }
                this.f3069d++;
                int i38 = this.f3070e;
                long[] jArr6 = this.f3066a;
                int i39 = iE >> 3;
                long j22 = jArr6[i39];
                int i40 = (iE & 7) << 3;
                this.f3070e = i38 - (((j22 >> i40) & j5) == j7 ? 1 : 0);
                int i41 = this.f3068c;
                long j23 = (j22 & (~(j5 << i40))) | (j6 << i40);
                jArr6[i39] = j23;
                jArr6[(((iE - 7) & i41) + (i41 & 7)) >> 3] = j23;
                return iE;
            }
            i11 += 8;
            i10 = (i10 + i11) & i9;
            i8 = i14;
            i5 = i16;
        }
    }

    public final int e(int i5) {
        int i6 = this.f3068c;
        int i7 = i5 & i6;
        int i8 = 0;
        while (true) {
            long[] jArr = this.f3066a;
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

    /* JADX WARN: Code duplicated, block: B:25:0x0058 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:26:0x005a A[LOOP:0: B:14:0x0021->B:26:0x005a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:30:0x005d A[SYNTHETIC] */
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a0)) {
            return false;
        }
        a0 a0Var = (a0) obj;
        if (a0Var.f3069d != this.f3069d) {
            return false;
        }
        Object[] objArr = this.f3067b;
        long[] jArr = this.f3066a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i5 = 0;
            while (true) {
                long j5 = jArr[i5];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i6 = 8 - ((~(i5 - length)) >>> 31);
                    for (int i7 = 0; i7 < i6; i7++) {
                        if ((255 & j5) < 128 && !a0Var.c(objArr[(i5 << 3) + i7])) {
                            return false;
                        }
                        j5 >>= 8;
                    }
                    if (i6 == 8) {
                        if (i5 != length) {
                            i5++;
                        }
                    }
                } else if (i5 != length) {
                    i5++;
                }
            }
        }
        return true;
    }

    public final void f(int i5) {
        long[] jArr;
        int iMax = i5 > 0 ? Math.max(7, f0.c(i5)) : 0;
        this.f3068c = iMax;
        if (iMax == 0) {
            jArr = f0.f3102a;
        } else {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            q3.k.F(jArr);
        }
        this.f3066a = jArr;
        int i6 = iMax >> 3;
        long j5 = 255 << ((iMax & 7) << 3);
        jArr[i6] = (jArr[i6] & (~j5)) | j5;
        this.f3070e = f0.a(this.f3068c) - this.f3069d;
        this.f3067b = iMax == 0 ? k.a.f3355c : new Object[iMax];
    }

    public final boolean g() {
        return this.f3069d == 0;
    }

    public final boolean h() {
        return this.f3069d != 0;
    }

    public final int hashCode() {
        int iHashCode = (this.f3068c * 31) + this.f3069d;
        Object[] objArr = this.f3067b;
        long[] jArr = this.f3066a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i5 = 0;
            while (true) {
                long j5 = jArr[i5];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i6 = 8 - ((~(i5 - length)) >>> 31);
                    for (int i7 = 0; i7 < i6; i7++) {
                        if ((255 & j5) < 128) {
                            Object obj = objArr[(i5 << 3) + i7];
                            if (!b4.l.a(obj, this)) {
                                iHashCode += obj != null ? obj.hashCode() : 0;
                            }
                        }
                        j5 >>= 8;
                    }
                    if (i6 != 8) {
                        return iHashCode;
                    }
                }
                if (i5 != length) {
                    i5++;
                }
            }
        }
        return iHashCode;
    }

    public final void i(a0 a0Var) {
        b4.l.e("elements", a0Var);
        Object[] objArr = a0Var.f3067b;
        long[] jArr = a0Var.f3066a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i5 = 0;
        while (true) {
            long j5 = jArr[i5];
            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i6 = 8 - ((~(i5 - length)) >>> 31);
                for (int i7 = 0; i7 < i6; i7++) {
                    if ((255 & j5) < 128) {
                        Object obj = objArr[(i5 << 3) + i7];
                        this.f3067b[d(obj)] = obj;
                    }
                    j5 >>= 8;
                }
                if (i6 != 8) {
                    return;
                }
            }
            if (i5 == length) {
                return;
            } else {
                i5++;
            }
        }
    }

    public final boolean j(Object obj) {
        int iNumberOfTrailingZeros;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i5 = iHashCode ^ (iHashCode << 16);
        int i6 = i5 & 127;
        int i7 = this.f3068c;
        int i8 = (i5 >>> 7) & i7;
        int i9 = 0;
        loop0: while (true) {
            long[] jArr = this.f3066a;
            int i10 = i8 >> 3;
            int i11 = (i8 & 7) << 3;
            long j5 = ((jArr[i10 + 1] << (64 - i11)) & ((-i11) >> 63)) | (jArr[i10] >>> i11);
            long j6 = (((long) i6) * 72340172838076673L) ^ j5;
            for (long j7 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j7) >> 3) + i8) & i7;
                if (b4.l.a(this.f3067b[iNumberOfTrailingZeros], obj)) {
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
        boolean z2 = iNumberOfTrailingZeros >= 0;
        if (z2) {
            k(iNumberOfTrailingZeros);
        }
        return z2;
    }

    public final void k(int i5) {
        this.f3069d--;
        long[] jArr = this.f3066a;
        int i6 = this.f3068c;
        int i7 = i5 >> 3;
        int i8 = (i5 & 7) << 3;
        long j5 = (jArr[i7] & (~(255 << i8))) | (254 << i8);
        jArr[i7] = j5;
        jArr[(((i5 - 7) & i6) + (i6 & 7)) >> 3] = j5;
        this.f3067b[i5] = null;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0066 A[DONT_INVERT, PHI: r7
      0x0066: PHI (r7v2 int) = (r7v1 int), (r7v3 int) binds: [B:6:0x0026, B:21:0x0064] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:23:0x0068 A[LOOP:0: B:5:0x0018->B:23:0x0068, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:29:0x006b A[SYNTHETIC] */
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "[");
        Object[] objArr = this.f3067b;
        long[] jArr = this.f3066a;
        int length = jArr.length - 2;
        if (length < 0) {
            sb.append((CharSequence) "]");
            break;
        }
        int i5 = 0;
        int i6 = 0;
        loop0: while (true) {
            long j5 = jArr[i5];
            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i7 = 8 - ((~(i5 - length)) >>> 31);
                for (int i8 = 0; i8 < i7; i8++) {
                    if ((255 & j5) < 128) {
                        Object obj = objArr[(i5 << 3) + i8];
                        if (i6 == -1) {
                            sb.append((CharSequence) "...");
                            break loop0;
                        }
                        if (i6 != 0) {
                            sb.append((CharSequence) ", ");
                        }
                        sb.append((CharSequence) (obj == this ? "(this)" : String.valueOf(obj)));
                        i6++;
                    }
                    j5 >>= 8;
                }
                if (i7 == 8) {
                    if (i5 == length) {
                        i5++;
                    }
                }
                sb.append((CharSequence) "]");
                break;
            }
            if (i5 == length) {
                sb.append((CharSequence) "]");
                break;
            }
            i5++;
        }
        String string = sb.toString();
        b4.l.d("toString(...)", string);
        return string;
    }
}
