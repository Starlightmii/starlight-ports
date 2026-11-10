package j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f3151a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long[] f3152b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f3153c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3154d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3155e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3156f;

    public final int a(int i5) {
        int i6 = this.f3154d;
        int i7 = i5 & i6;
        int i8 = 0;
        while (true) {
            long[] jArr = this.f3151a;
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

    public final Object b(long j5) {
        int iNumberOfTrailingZeros;
        int iHashCode = Long.hashCode(j5) * (-862048943);
        int i5 = iHashCode ^ (iHashCode << 16);
        int i6 = i5 & 127;
        int i7 = this.f3154d;
        int i8 = (i5 >>> 7) & i7;
        int i9 = 0;
        loop0: while (true) {
            long[] jArr = this.f3151a;
            int i10 = i8 >> 3;
            int i11 = (i8 & 7) << 3;
            long j6 = ((jArr[i10 + 1] << (64 - i11)) & ((-i11) >> 63)) | (jArr[i10] >>> i11);
            long j7 = (((long) i6) * 72340172838076673L) ^ j6;
            for (long j8 = (~j7) & (j7 - 72340172838076673L) & (-9187201950435737472L); j8 != 0; j8 &= j8 - 1) {
                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j8) >> 3) + i8) & i7;
                if (this.f3152b[iNumberOfTrailingZeros] == j5) {
                    break loop0;
                }
            }
            if ((j6 & ((~j6) << 6) & (-9187201950435737472L)) != 0) {
                iNumberOfTrailingZeros = -1;
                break;
            }
            i9 += 8;
            i8 = (i8 + i9) & i7;
        }
        if (iNumberOfTrailingZeros >= 0) {
            return this.f3153c[iNumberOfTrailingZeros];
        }
        return null;
    }

    public final void c(int i5) {
        long[] jArr;
        int iMax = i5 > 0 ? Math.max(7, f0.c(i5)) : 0;
        this.f3154d = iMax;
        if (iMax == 0) {
            jArr = f0.f3102a;
        } else {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            q3.k.F(jArr);
        }
        this.f3151a = jArr;
        int i6 = iMax >> 3;
        long j5 = 255 << ((iMax & 7) << 3);
        jArr[i6] = (jArr[i6] & (~j5)) | j5;
        this.f3156f = f0.a(this.f3154d) - this.f3155e;
        this.f3152b = new long[iMax];
        this.f3153c = new Object[iMax];
    }

    public final void d(long j5, y yVar) {
        long j6;
        long j7;
        int i5;
        int i6;
        long j8;
        int iNumberOfTrailingZeros;
        long[] jArr;
        Object[] objArr;
        long[] jArr2;
        int i7 = -862048943;
        int iHashCode = Long.hashCode(j5) * (-862048943);
        int i8 = iHashCode ^ (iHashCode << 16);
        int i9 = i8 >>> 7;
        int i10 = i8 & 127;
        int i11 = this.f3154d;
        int i12 = i9 & i11;
        int i13 = 0;
        loop0: while (true) {
            long[] jArr3 = this.f3151a;
            int i14 = i12 >> 3;
            int i15 = (i12 & 7) << 3;
            int i16 = 1;
            long j9 = ((jArr3[i14 + 1] << (64 - i15)) & ((-i15) >> 63)) | (jArr3[i14] >>> i15);
            long j10 = i10;
            int i17 = i13;
            int i18 = 0;
            long j11 = j9 ^ (j10 * 72340172838076673L);
            long j12 = (~j11) & (j11 - 72340172838076673L) & (-9187201950435737472L);
            while (j12 != 0) {
                iNumberOfTrailingZeros = (i12 + (Long.numberOfTrailingZeros(j12) >> 3)) & i11;
                int i19 = i7;
                if (this.f3152b[iNumberOfTrailingZeros] == j5) {
                    break loop0;
                }
                j12 &= j12 - 1;
                i7 = i19;
            }
            int i20 = i7;
            if ((((~j9) << 6) & j9 & (-9187201950435737472L)) != 0) {
                int iA = a(i9);
                if (this.f3156f != 0 || ((this.f3151a[iA >> 3] >> ((iA & 7) << 3)) & 255) == 254) {
                    j6 = 255;
                    j7 = j10;
                    i5 = 0;
                    i6 = 1;
                    j8 = 128;
                } else {
                    int i21 = this.f3154d;
                    if (i21 > 8) {
                        j8 = 128;
                        if (Long.compareUnsigned(((long) this.f3155e) * 32, ((long) i21) * 25) <= 0) {
                            long[] jArr4 = this.f3151a;
                            int i22 = this.f3154d;
                            long[] jArr5 = this.f3152b;
                            Object[] objArr2 = this.f3153c;
                            int i23 = (i22 + 7) >> 3;
                            j6 = 255;
                            int i24 = 0;
                            while (i24 < i23) {
                                long j13 = jArr4[i24] & (-9187201950435737472L);
                                jArr4[i24] = (-72340172838076674L) & ((~j13) + (j13 >>> 7));
                                i24++;
                                i16 = i16;
                                i18 = i18;
                                j10 = j10;
                            }
                            j7 = j10;
                            i5 = i18;
                            int i25 = i16;
                            char c5 = 7;
                            int iG = q3.k.G(jArr4);
                            int i26 = iG - 1;
                            long j14 = 72057594037927935L;
                            jArr4[i26] = (jArr4[i26] & 72057594037927935L) | (-72057594037927936L);
                            jArr4[iG] = jArr4[i5];
                            int i27 = i5;
                            while (i27 != i22) {
                                int i28 = i27 >> 3;
                                int i29 = (i27 & 7) << 3;
                                long j15 = (jArr4[i28] >> i29) & 255;
                                if (j15 != 128 && j15 == 254) {
                                    int iHashCode2 = Long.hashCode(jArr5[i27]) * i20;
                                    int i30 = iHashCode2 ^ (iHashCode2 << 16);
                                    int i31 = i30 >>> 7;
                                    int iA2 = a(i31);
                                    int i32 = i31 & i22;
                                    char c6 = c5;
                                    if (((iA2 - i32) & i22) / 8 == ((i27 - i32) & i22) / 8) {
                                        int i33 = i25;
                                        long j16 = j14;
                                        jArr4[i28] = (((long) (i30 & 127)) << i29) | (jArr4[i28] & (~(255 << i29)));
                                        jArr4[jArr4.length - i33] = (jArr4[i5] & j16) | Long.MIN_VALUE;
                                        i27++;
                                        i25 = i33;
                                        c5 = c6;
                                        j14 = j16;
                                    } else {
                                        int i34 = i25;
                                        long j17 = j14;
                                        int i35 = iA2 >> 3;
                                        long j18 = jArr4[i35];
                                        int i36 = (iA2 & 7) << 3;
                                        if (((j18 >> i36) & 255) == 128) {
                                            jArr2 = jArr5;
                                            objArr = objArr2;
                                            jArr4[i35] = (j18 & (~(255 << i36))) | (((long) (i30 & 127)) << i36);
                                            jArr4[i28] = (jArr4[i28] & (~(255 << i29))) | (128 << i29);
                                            jArr2[iA2] = jArr2[i27];
                                            jArr2[i27] = 0;
                                            objArr[iA2] = objArr[i27];
                                            objArr[i27] = null;
                                        } else {
                                            objArr = objArr2;
                                            jArr2 = jArr5;
                                            jArr4[i35] = (((long) (i30 & 127)) << i36) | (j18 & (~(255 << i36)));
                                            long j19 = jArr2[iA2];
                                            jArr2[iA2] = jArr2[i27];
                                            jArr2[i27] = j19;
                                            Object obj = objArr[iA2];
                                            objArr[iA2] = objArr[i27];
                                            objArr[i27] = obj;
                                            i27--;
                                        }
                                        jArr4[jArr4.length - 1] = (jArr4[i5] & j17) | Long.MIN_VALUE;
                                        i27++;
                                        jArr5 = jArr2;
                                        i25 = i34;
                                        c5 = c6;
                                        j14 = j17;
                                        objArr2 = objArr;
                                    }
                                } else {
                                    i27++;
                                }
                            }
                            i6 = i25;
                            this.f3156f = f0.a(this.f3154d) - this.f3155e;
                        }
                        iA = a(i9);
                    } else {
                        j8 = 128;
                    }
                    j6 = 255;
                    j7 = j10;
                    i5 = 0;
                    i6 = 1;
                    int iB = f0.b(this.f3154d);
                    long[] jArr6 = this.f3151a;
                    long[] jArr7 = this.f3152b;
                    Object[] objArr3 = this.f3153c;
                    int i37 = this.f3154d;
                    c(iB);
                    long[] jArr8 = this.f3151a;
                    long[] jArr9 = this.f3152b;
                    Object[] objArr4 = this.f3153c;
                    int i38 = this.f3154d;
                    int i39 = 0;
                    while (i39 < i37) {
                        if (((jArr6[i39 >> 3] >> ((i39 & 7) << 3)) & 255) < j8) {
                            long j20 = jArr7[i39];
                            int iHashCode3 = Long.hashCode(j20) * i20;
                            int i40 = iHashCode3 ^ (iHashCode3 << 16);
                            int iA3 = a(i40 >>> 7);
                            jArr = jArr8;
                            long j21 = i40 & 127;
                            int i41 = iA3 >> 3;
                            int i42 = (iA3 & 7) << 3;
                            long j22 = (jArr[i41] & (~(255 << i42))) | (j21 << i42);
                            jArr[i41] = j22;
                            jArr[(((iA3 - 7) & i38) + (i38 & 7)) >> 3] = j22;
                            jArr9[iA3] = j20;
                            objArr4[iA3] = objArr3[i39];
                        } else {
                            jArr = jArr8;
                        }
                        i39++;
                        jArr6 = jArr6;
                        jArr8 = jArr;
                    }
                    iA = a(i9);
                }
                iNumberOfTrailingZeros = iA;
                this.f3155e++;
                int i43 = this.f3156f;
                long[] jArr10 = this.f3151a;
                int i44 = iNumberOfTrailingZeros >> 3;
                long j23 = jArr10[i44];
                int i45 = (iNumberOfTrailingZeros & 7) << 3;
                if (((j23 >> i45) & j6) != j8) {
                    i6 = i5;
                }
                this.f3156f = i43 - i6;
                int i46 = this.f3154d;
                long j24 = (j23 & (~(j6 << i45))) | (j7 << i45);
                jArr10[i44] = j24;
                jArr10[(((iNumberOfTrailingZeros - 7) & i46) + (i46 & 7)) >> 3] = j24;
                break;
            }
            i13 = i17 + 8;
            i12 = (i12 + i13) & i11;
            i7 = i20;
        }
        this.f3152b[iNumberOfTrailingZeros] = j5;
        this.f3153c[iNumberOfTrailingZeros] = yVar;
    }

    public final boolean equals(Object obj) {
        long[] jArr;
        Object[] objArr;
        boolean z2;
        int i5;
        Object[] objArr2;
        long j5;
        char c5;
        long j6;
        long[] jArr2;
        int i6;
        if (obj != this) {
            if (obj instanceof u) {
                u uVar = (u) obj;
                if (uVar.f3155e == this.f3155e) {
                    long[] jArr3 = this.f3152b;
                    Object[] objArr3 = this.f3153c;
                    long[] jArr4 = this.f3151a;
                    int length = jArr4.length - 2;
                    if (length >= 0) {
                        int i7 = 0;
                        while (true) {
                            long j7 = jArr4[i7];
                            char c6 = 7;
                            long j8 = -9187201950435737472L;
                            if ((((~j7) << 7) & j7 & (-9187201950435737472L)) != -9187201950435737472L) {
                                int i8 = 8;
                                int i9 = 8 - ((~(i7 - length)) >>> 31);
                                int i10 = 0;
                                z2 = true;
                                while (i10 < i9) {
                                    if ((j7 & 255) < 128) {
                                        int i11 = (i7 << 3) + i10;
                                        objArr2 = objArr3;
                                        long j9 = jArr3[i11];
                                        c5 = c6;
                                        Object obj2 = objArr2[i11];
                                        if (obj2 != null) {
                                            i5 = i10;
                                            j5 = j7;
                                            j6 = j8;
                                            jArr2 = jArr3;
                                            if (!obj2.equals(uVar.b(j9))) {
                                                return false;
                                            }
                                        } else {
                                            if (uVar.b(j9) != null) {
                                                return false;
                                            }
                                            int iHashCode = Long.hashCode(j9) * (-862048943);
                                            int i12 = iHashCode ^ (iHashCode << 16);
                                            j6 = j8;
                                            int i13 = i12 & 127;
                                            int i14 = uVar.f3154d;
                                            int i15 = (i12 >>> 7) & i14;
                                            int i16 = 0;
                                            while (true) {
                                                long[] jArr5 = uVar.f3151a;
                                                int i17 = i15 >> 3;
                                                int i18 = (i15 & 7) << 3;
                                                long j10 = jArr5[i17] >>> i18;
                                                long j11 = jArr5[i17 + 1] << (64 - i18);
                                                i5 = i10;
                                                jArr2 = jArr3;
                                                long j12 = j10 | (j11 & ((-i18) >> 63));
                                                j5 = j7;
                                                long j13 = (((long) i13) * 72340172838076673L) ^ j12;
                                                for (long j14 = (j13 - 72340172838076673L) & (~j13) & j6; j14 != 0; j14 &= j14 - 1) {
                                                    int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j14) >> 3) + i15) & i14;
                                                    if (uVar.f3152b[iNumberOfTrailingZeros] == j9) {
                                                        i6 = iNumberOfTrailingZeros;
                                                        break;
                                                    }
                                                }
                                                if ((j12 & ((~j12) << 6) & j6) != 0) {
                                                    i6 = -1;
                                                    break;
                                                }
                                                i16 += 8;
                                                i15 = (i15 + i16) & i14;
                                                jArr3 = jArr2;
                                                i10 = i5;
                                                j7 = j5;
                                            }
                                            if (!(i6 >= 0)) {
                                                return false;
                                            }
                                        }
                                    } else {
                                        i5 = i10;
                                        objArr2 = objArr3;
                                        j5 = j7;
                                        c5 = c6;
                                        j6 = j8;
                                        jArr2 = jArr3;
                                    }
                                    j7 = j5 >> i8;
                                    i10 = i5 + 1;
                                    c6 = c5;
                                    jArr3 = jArr2;
                                    objArr3 = objArr2;
                                    j8 = j6;
                                    i8 = i8;
                                }
                                objArr = objArr3;
                                int i19 = i8;
                                jArr = jArr3;
                                if (i9 != i19) {
                                    return true;
                                }
                            } else {
                                jArr = jArr3;
                                objArr = objArr3;
                                z2 = true;
                            }
                            if (i7 == length) {
                                return z2;
                            }
                            i7++;
                            jArr3 = jArr;
                            objArr3 = objArr;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        long[] jArr = this.f3152b;
        Object[] objArr = this.f3153c;
        long[] jArr2 = this.f3151a;
        int length = jArr2.length - 2;
        if (length < 0) {
            return 0;
        }
        int i5 = 0;
        int iHashCode = 0;
        while (true) {
            long j5 = jArr2[i5];
            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i6 = 8 - ((~(i5 - length)) >>> 31);
                for (int i7 = 0; i7 < i6; i7++) {
                    if ((255 & j5) < 128) {
                        int i8 = (i5 << 3) + i7;
                        long j6 = jArr[i8];
                        Object obj = objArr[i8];
                        iHashCode += (obj != null ? obj.hashCode() : 0) ^ Long.hashCode(j6);
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

    public final String toString() {
        int i5;
        int i6;
        if (this.f3155e == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        long[] jArr = this.f3152b;
        Object[] objArr = this.f3153c;
        long[] jArr2 = this.f3151a;
        int length = jArr2.length - 2;
        if (length >= 0) {
            int i7 = 0;
            int i8 = 0;
            while (true) {
                long j5 = jArr2[i7];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i9 = 8 - ((~(i7 - length)) >>> 31);
                    int i10 = 0;
                    while (i10 < i9) {
                        if ((255 & j5) < 128) {
                            int i11 = (i7 << 3) + i10;
                            i6 = i7;
                            long j6 = jArr[i11];
                            Object obj = objArr[i11];
                            sb.append(j6);
                            sb.append("=");
                            if (obj == this) {
                                obj = "(this)";
                            }
                            sb.append(obj);
                            i8++;
                            if (i8 < this.f3155e) {
                                sb.append(", ");
                            }
                        } else {
                            i6 = i7;
                        }
                        j5 >>= 8;
                        i10++;
                        i7 = i6;
                    }
                    int i12 = i7;
                    if (i9 != 8) {
                        break;
                    }
                    i5 = i12;
                } else {
                    i5 = i7;
                }
                if (i5 == length) {
                    break;
                }
                i7 = i5 + 1;
            }
        }
        sb.append('}');
        String string = sb.toString();
        b4.l.d("toString(...)", string);
        return string;
    }
}
