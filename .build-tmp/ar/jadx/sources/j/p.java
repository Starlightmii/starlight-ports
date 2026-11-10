package j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f3130a = f0.f3102a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f3131b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f3132c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3133d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3134e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3135f;

    public p() {
        int[] iArr = k.f3116a;
        this.f3131b = iArr;
        this.f3132c = iArr;
        d(f0.d(6));
    }

    public final void a() {
        this.f3134e = 0;
        long[] jArr = this.f3130a;
        if (jArr != f0.f3102a) {
            q3.k.F(jArr);
            long[] jArr2 = this.f3130a;
            int i5 = this.f3133d;
            int i6 = i5 >> 3;
            long j5 = 255 << ((i5 & 7) << 3);
            jArr2[i6] = (jArr2[i6] & (~j5)) | j5;
        }
        this.f3135f = f0.a(this.f3133d) - this.f3134e;
    }

    public final int b(int i5) {
        int i6 = this.f3133d;
        int i7 = i5 & i6;
        int i8 = 0;
        while (true) {
            long[] jArr = this.f3130a;
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

    public final int c(int i5) {
        int iHashCode = Integer.hashCode(i5) * (-862048943);
        int i6 = iHashCode ^ (iHashCode << 16);
        int i7 = i6 & 127;
        int i8 = this.f3133d;
        int i9 = (i6 >>> 7) & i8;
        int i10 = 0;
        while (true) {
            long[] jArr = this.f3130a;
            int i11 = i9 >> 3;
            int i12 = (i9 & 7) << 3;
            long j5 = ((jArr[i11 + 1] << (64 - i12)) & ((-i12) >> 63)) | (jArr[i11] >>> i12);
            long j6 = (((long) i7) * 72340172838076673L) ^ j5;
            for (long j7 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j7) >> 3) + i9) & i8;
                if (this.f3131b[iNumberOfTrailingZeros] == i5) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((j5 & ((~j5) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i10 += 8;
            i9 = (i9 + i10) & i8;
        }
    }

    public final void d(int i5) {
        long[] jArr;
        int iMax = i5 > 0 ? Math.max(7, f0.c(i5)) : 0;
        this.f3133d = iMax;
        if (iMax == 0) {
            jArr = f0.f3102a;
        } else {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            q3.k.F(jArr);
        }
        this.f3130a = jArr;
        int i6 = iMax >> 3;
        long j5 = 255 << ((iMax & 7) << 3);
        jArr[i6] = (jArr[i6] & (~j5)) | j5;
        this.f3135f = f0.a(this.f3133d) - this.f3134e;
        this.f3131b = new int[iMax];
        this.f3132c = new int[iMax];
    }

    public final void e(int i5, int i6) {
        long j5;
        long j6;
        int i7;
        int i8;
        long j7;
        int iNumberOfTrailingZeros;
        long[] jArr;
        int[] iArr;
        int[] iArr2;
        int i9 = i5;
        int i10 = -862048943;
        int iHashCode = Integer.hashCode(i9) * (-862048943);
        int i11 = iHashCode ^ (iHashCode << 16);
        int i12 = i11 >>> 7;
        int i13 = i11 & 127;
        int i14 = this.f3133d;
        int i15 = i12 & i14;
        int i16 = 0;
        loop0: while (true) {
            long[] jArr2 = this.f3130a;
            int i17 = i15 >> 3;
            int i18 = (i15 & 7) << 3;
            int i19 = 1;
            int i20 = i16;
            int i21 = 0;
            long j8 = (((-i18) >> 63) & (jArr2[i17 + 1] << (64 - i18))) | (jArr2[i17] >>> i18);
            long j9 = i13;
            long j10 = j8 ^ (j9 * 72340172838076673L);
            long j11 = (j10 - 72340172838076673L) & (~j10) & (-9187201950435737472L);
            while (j11 != 0) {
                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j11) >> 3) + i15) & i14;
                int i22 = i10;
                if (this.f3131b[iNumberOfTrailingZeros] == i9) {
                    break loop0;
                }
                j11 &= j11 - 1;
                i10 = i22;
            }
            int i23 = i10;
            if ((j8 & ((~j8) << 6) & (-9187201950435737472L)) != 0) {
                int iB = b(i12);
                long j12 = 255;
                if (this.f3135f != 0 || ((this.f3130a[iB >> 3] >> ((iB & 7) << 3)) & 255) == 254) {
                    j5 = j9;
                    j6 = 255;
                    i7 = 1;
                    i8 = 0;
                    j7 = 128;
                } else {
                    int i24 = this.f3133d;
                    if (i24 > 8) {
                        j7 = 128;
                        if (Long.compareUnsigned(((long) this.f3134e) * 32, ((long) i24) * 25) <= 0) {
                            long[] jArr3 = this.f3130a;
                            int i25 = this.f3133d;
                            int[] iArr3 = this.f3131b;
                            int[] iArr4 = this.f3132c;
                            int i26 = (i25 + 7) >> 3;
                            int i27 = 0;
                            while (i27 < i26) {
                                long j13 = jArr3[i27] & (-9187201950435737472L);
                                jArr3[i27] = (-72340172838076674L) & ((~j13) + (j13 >>> 7));
                                i27++;
                                j12 = j12;
                                j9 = j9;
                            }
                            j5 = j9;
                            j6 = j12;
                            char c5 = 7;
                            int iG = q3.k.G(jArr3);
                            int i28 = iG - 1;
                            jArr3[i28] = (jArr3[i28] & 72057594037927935L) | (-72057594037927936L);
                            jArr3[iG] = jArr3[0];
                            int i29 = 0;
                            while (i29 != i25) {
                                int i30 = i29 >> 3;
                                int i31 = (i29 & 7) << 3;
                                long j14 = (jArr3[i30] >> i31) & j6;
                                if (j14 != 128 && j14 == 254) {
                                    int iHashCode2 = Integer.hashCode(iArr3[i29]) * i23;
                                    int i32 = iHashCode2 ^ (iHashCode2 << 16);
                                    int i33 = i32 >>> 7;
                                    int iB2 = b(i33);
                                    int i34 = i33 & i25;
                                    char c6 = c5;
                                    if (((iB2 - i34) & i25) / 8 == ((i29 - i34) & i25) / 8) {
                                        int i35 = i21;
                                        jArr3[i30] = (((long) (i32 & 127)) << i31) | (jArr3[i30] & (~(j6 << i31)));
                                        jArr3[jArr3.length - 1] = (jArr3[i35] & 72057594037927935L) | Long.MIN_VALUE;
                                        i29++;
                                        i19 = i19;
                                        c5 = c6;
                                        i21 = i35;
                                    } else {
                                        int i36 = i19;
                                        int i37 = i21;
                                        int i38 = iB2 >> 3;
                                        long j15 = jArr3[i38];
                                        int i39 = (iB2 & 7) << 3;
                                        if (((j15 >> i39) & j6) == 128) {
                                            iArr = iArr3;
                                            iArr2 = iArr4;
                                            jArr3[i38] = ((~(j6 << i39)) & j15) | (((long) (i32 & 127)) << i39);
                                            jArr3[i30] = (jArr3[i30] & (~(j6 << i31))) | (128 << i31);
                                            iArr[iB2] = iArr[i29];
                                            iArr[i29] = i37;
                                            iArr2[iB2] = iArr2[i29];
                                            iArr2[i29] = i37;
                                        } else {
                                            iArr = iArr3;
                                            iArr2 = iArr4;
                                            jArr3[i38] = (((long) (i32 & 127)) << i39) | ((~(j6 << i39)) & j15);
                                            int i40 = iArr[iB2];
                                            iArr[iB2] = iArr[i29];
                                            iArr[i29] = i40;
                                            int i41 = iArr2[iB2];
                                            iArr2[iB2] = iArr2[i29];
                                            iArr2[i29] = i41;
                                            i29--;
                                        }
                                        jArr3[jArr3.length - 1] = (jArr3[i37] & 72057594037927935L) | Long.MIN_VALUE;
                                        i29++;
                                        i19 = i36;
                                        c5 = c6;
                                        i21 = i37;
                                        iArr3 = iArr;
                                        iArr4 = iArr2;
                                    }
                                } else {
                                    i29++;
                                }
                            }
                            i7 = i19;
                            i8 = i21;
                            this.f3135f = f0.a(this.f3133d) - this.f3134e;
                        }
                        iB = b(i12);
                    } else {
                        j7 = 128;
                    }
                    j5 = j9;
                    j6 = 255;
                    i7 = 1;
                    i8 = 0;
                    int iB3 = f0.b(this.f3133d);
                    long[] jArr4 = this.f3130a;
                    int[] iArr5 = this.f3131b;
                    int[] iArr6 = this.f3132c;
                    int i42 = this.f3133d;
                    d(iB3);
                    long[] jArr5 = this.f3130a;
                    int[] iArr7 = this.f3131b;
                    int[] iArr8 = this.f3132c;
                    int i43 = this.f3133d;
                    int i44 = 0;
                    while (i44 < i42) {
                        if (((jArr4[i44 >> 3] >> ((i44 & 7) << 3)) & 255) < j7) {
                            int i45 = iArr5[i44];
                            int iHashCode3 = Integer.hashCode(i45) * i23;
                            int i46 = iHashCode3 ^ (iHashCode3 << 16);
                            int iB4 = b(i46 >>> 7);
                            jArr = jArr5;
                            long j16 = i46 & 127;
                            int i47 = iB4 >> 3;
                            int i48 = (iB4 & 7) << 3;
                            long j17 = (jArr[i47] & (~(255 << i48))) | (j16 << i48);
                            jArr[i47] = j17;
                            jArr[(((iB4 - 7) & i43) + (i43 & 7)) >> 3] = j17;
                            iArr7[iB4] = i45;
                            iArr8[iB4] = iArr6[i44];
                        } else {
                            jArr = jArr5;
                        }
                        i44++;
                        jArr5 = jArr;
                    }
                    iB = b(i12);
                }
                this.f3134e++;
                int i49 = this.f3135f;
                long[] jArr6 = this.f3130a;
                int i50 = iB >> 3;
                long j18 = jArr6[i50];
                int i51 = (iB & 7) << 3;
                if (((j18 >> i51) & j6) == j7) {
                    i8 = i7;
                }
                this.f3135f = i49 - i8;
                int i52 = this.f3133d;
                long j19 = (j18 & (~(j6 << i51))) | (j5 << i51);
                jArr6[i50] = j19;
                jArr6[(((iB - 7) & i52) + (i52 & 7)) >> 3] = j19;
                iNumberOfTrailingZeros = ~iB;
                break;
            }
            i16 = i20 + 8;
            i15 = (i15 + i16) & i14;
            i9 = i5;
            i10 = i23;
        }
        if (iNumberOfTrailingZeros < 0) {
            iNumberOfTrailingZeros = ~iNumberOfTrailingZeros;
        }
        this.f3131b[iNumberOfTrailingZeros] = i5;
        this.f3132c[iNumberOfTrailingZeros] = i6;
    }

    public final boolean equals(Object obj) {
        boolean z2;
        boolean z3 = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        if (pVar.f3134e != this.f3134e) {
            return false;
        }
        int[] iArr = this.f3131b;
        int[] iArr2 = this.f3132c;
        long[] jArr = this.f3130a;
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
                        int i9 = iArr[i8];
                        int i10 = iArr2[i8];
                        int iC = pVar.c(i9);
                        if (iC < 0 || i10 != pVar.f3132c[iC]) {
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
        int[] iArr = this.f3131b;
        int[] iArr2 = this.f3132c;
        long[] jArr = this.f3130a;
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
                        int i9 = iArr[i8];
                        iHashCode += Integer.hashCode(iArr2[i8]) ^ Integer.hashCode(i9);
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

    /* JADX WARN: Code duplicated, block: B:20:0x0066 A[DONT_INVERT, PHI: r8
      0x0066: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:10:0x002c, B:19:0x0064] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:21:0x0068 A[LOOP:0: B:9:0x001e->B:21:0x0068, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:25:0x006b A[EDGE_INSN: B:25:0x006b->B:22:0x006b BREAK  A[LOOP:0: B:9:0x001e->B:21:0x0068], SYNTHETIC] */
    public final String toString() {
        if (this.f3134e == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        int[] iArr = this.f3131b;
        int[] iArr2 = this.f3132c;
        long[] jArr = this.f3130a;
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
                            int i10 = iArr[i9];
                            int i11 = iArr2[i9];
                            sb.append(i10);
                            sb.append("=");
                            sb.append(i11);
                            i6++;
                            if (i6 < this.f3134e) {
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
