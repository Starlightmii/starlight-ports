package j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f3144a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f3145b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3146c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3147d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3148e;

    public /* synthetic */ s() {
        this(6);
    }

    public s(int i5) {
        this.f3144a = f0.f3102a;
        this.f3145b = k.f3116a;
        if (i5 >= 0) {
            e(f0.d(i5));
        } else {
            k.a.c("Capacity must be a positive value.");
            throw null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v9, types: [int] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4, types: [int] */
    /* JADX WARN: Type inference failed for: r9v5 */
    public final boolean a(int i5) {
        long j5;
        boolean z2;
        long j6;
        int iNumberOfTrailingZeros;
        long[] jArr;
        int[] iArr;
        int i6;
        int i7 = this.f3147d;
        int i8 = -862048943;
        int iHashCode = Integer.hashCode(i5) * (-862048943);
        int i9 = iHashCode ^ (iHashCode << 16);
        int i10 = i9 >>> 7;
        int i11 = i9 & 127;
        int i12 = this.f3146c;
        int i13 = i10 & i12;
        int i14 = 0;
        loop0: while (true) {
            long[] jArr2 = this.f3144a;
            int i15 = i13 >> 3;
            int i16 = (i13 & 7) << 3;
            boolean z3 = true;
            int i17 = i14;
            long j7 = (((-i16) >> 63) & (jArr2[i15 + 1] << (64 - i16))) | (jArr2[i15] >>> i16);
            long j8 = i11;
            long j9 = j7 ^ (j8 * 72340172838076673L);
            long j10 = (j9 - 72340172838076673L) & (~j9) & (-9187201950435737472L);
            while (j10 != 0) {
                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j10) >> 3) + i13) & i12;
                int i18 = i8;
                if (this.f3145b[iNumberOfTrailingZeros] == i5) {
                    z2 = true;
                    break loop0;
                }
                j10 &= j10 - 1;
                i8 = i18;
            }
            int i19 = i8;
            long j11 = j7 & ((~j7) << 6) & (-9187201950435737472L);
            char c5 = '\b';
            if (j11 != 0) {
                int iD = d(i10);
                long j12 = 255;
                if (this.f3148e != 0 || ((this.f3144a[iD >> 3] >> ((iD & 7) << 3)) & 255) == 254) {
                    j5 = 255;
                    z2 = true;
                    j6 = 128;
                } else {
                    int i20 = this.f3146c;
                    if (i20 > 8) {
                        j6 = 128;
                        if (Long.compareUnsigned(((long) this.f3147d) * 32, ((long) i20) * 25) <= 0) {
                            long[] jArr3 = this.f3144a;
                            int i21 = this.f3146c;
                            int[] iArr2 = this.f3145b;
                            int i22 = (i21 + 7) >> 3;
                            int i23 = 0;
                            while (i23 < i22) {
                                char c6 = c5;
                                long j13 = jArr3[i23] & (-9187201950435737472L);
                                jArr3[i23] = (-72340172838076674L) & ((~j13) + (j13 >>> 7));
                                i23++;
                                j12 = j12;
                                c5 = c6;
                            }
                            j5 = j12;
                            int iG = q3.k.G(jArr3);
                            int i24 = iG - 1;
                            jArr3[i24] = (jArr3[i24] & 72057594037927935L) | (-72057594037927936L);
                            jArr3[iG] = jArr3[0];
                            int i25 = 0;
                            while (i25 != i21) {
                                int i26 = i25 >> 3;
                                int i27 = (i25 & 7) << 3;
                                long j14 = (jArr3[i26] >> i27) & j5;
                                if (j14 != 128 && j14 == 254) {
                                    int iHashCode2 = Integer.hashCode(iArr2[i25]) * i19;
                                    int i28 = iHashCode2 ^ (iHashCode2 << 16);
                                    int i29 = i28 >>> 7;
                                    int iD2 = d(i29);
                                    int i30 = i29 & i21;
                                    boolean z4 = z3;
                                    if (((iD2 - i30) & i21) / 8 == ((i25 - i30) & i21) / 8) {
                                        iArr = iArr2;
                                        jArr3[i26] = ((~(j5 << i27)) & jArr3[i26]) | (((long) (i28 & 127)) << i27);
                                        jArr3[jArr3.length - 1] = (jArr3[0] & 72057594037927935L) | Long.MIN_VALUE;
                                        i25++;
                                    } else {
                                        iArr = iArr2;
                                        int i31 = i25;
                                        int i32 = iD2 >> 3;
                                        long j15 = jArr3[i32];
                                        int i33 = (iD2 & 7) << 3;
                                        if (((j15 >> i33) & j5) == 128) {
                                            jArr3[i32] = (j15 & (~(j5 << i33))) | (((long) (i28 & 127)) << i33);
                                            jArr3[i26] = (jArr3[i26] & (~(j5 << i27))) | (128 << i27);
                                            iArr[iD2] = iArr[i31];
                                            iArr[i31] = 0;
                                            i6 = i31;
                                        } else {
                                            jArr3[i32] = (((long) (i28 & 127)) << i33) | (j15 & (~(j5 << i33)));
                                            int i34 = iArr[iD2];
                                            iArr[iD2] = iArr[i31];
                                            iArr[i31] = i34;
                                            i6 = i31 - 1;
                                        }
                                        jArr3[jArr3.length - 1] = (jArr3[0] & 72057594037927935L) | Long.MIN_VALUE;
                                        i25 = i6 + 1;
                                    }
                                    iArr2 = iArr;
                                    z3 = z4;
                                } else {
                                    i25++;
                                }
                            }
                            z2 = z3;
                            this.f3148e = f0.a(this.f3146c) - this.f3147d;
                        }
                        iD = d(i10);
                    } else {
                        j6 = 128;
                    }
                    j5 = 255;
                    z2 = true;
                    int iB = f0.b(this.f3146c);
                    long[] jArr4 = this.f3144a;
                    int[] iArr3 = this.f3145b;
                    int i35 = this.f3146c;
                    e(iB);
                    long[] jArr5 = this.f3144a;
                    int[] iArr4 = this.f3145b;
                    int i36 = this.f3146c;
                    int i37 = 0;
                    while (i37 < i35) {
                        if (((jArr4[i37 >> 3] >> ((i37 & 7) << 3)) & 255) < j6) {
                            int i38 = iArr3[i37];
                            int iHashCode3 = Integer.hashCode(i38) * i19;
                            int i39 = iHashCode3 ^ (iHashCode3 << 16);
                            int iD3 = d(i39 >>> 7);
                            jArr = jArr5;
                            long j16 = i39 & 127;
                            int i40 = iD3 >> 3;
                            int i41 = (iD3 & 7) << 3;
                            long j17 = (jArr[i40] & (~(255 << i41))) | (j16 << i41);
                            jArr[i40] = j17;
                            jArr[(((iD3 - 7) & i36) + (i36 & 7)) >> 3] = j17;
                            iArr4[iD3] = i38;
                        } else {
                            jArr = jArr5;
                        }
                        i37++;
                        jArr4 = jArr4;
                        jArr5 = jArr;
                    }
                    iD = d(i10);
                }
                iNumberOfTrailingZeros = iD;
                this.f3147d++;
                int i42 = this.f3148e;
                long[] jArr6 = this.f3144a;
                int i43 = iNumberOfTrailingZeros >> 3;
                long j18 = jArr6[i43];
                int i44 = (iNumberOfTrailingZeros & 7) << 3;
                this.f3148e = i42 - (((j18 >> i44) & j5) == j6 ? z2 : 0);
                int i45 = this.f3146c;
                long j19 = (j18 & (~(j5 << i44))) | (j8 << i44);
                jArr6[i43] = j19;
                jArr6[(((iNumberOfTrailingZeros - 7) & i45) + (i45 & 7)) >> 3] = j19;
                break;
            }
            i14 = i17 + 8;
            i13 = (i13 + i14) & i12;
            i8 = i19;
        }
        this.f3145b[iNumberOfTrailingZeros] = i5;
        if (this.f3147d != i7) {
            return z2;
        }
        return false;
    }

    public final void b() {
        this.f3147d = 0;
        long[] jArr = this.f3144a;
        if (jArr != f0.f3102a) {
            q3.k.F(jArr);
            long[] jArr2 = this.f3144a;
            int i5 = this.f3146c;
            int i6 = i5 >> 3;
            long j5 = 255 << ((i5 & 7) << 3);
            jArr2[i6] = (jArr2[i6] & (~j5)) | j5;
        }
        this.f3148e = f0.a(this.f3146c) - this.f3147d;
    }

    public final boolean c(int i5) {
        int iNumberOfTrailingZeros;
        int iHashCode = Integer.hashCode(i5) * (-862048943);
        int i6 = iHashCode ^ (iHashCode << 16);
        int i7 = i6 & 127;
        int i8 = this.f3146c;
        int i9 = (i6 >>> 7) & i8;
        int i10 = 0;
        loop0: while (true) {
            long[] jArr = this.f3144a;
            int i11 = i9 >> 3;
            int i12 = (i9 & 7) << 3;
            long j5 = ((jArr[i11 + 1] << (64 - i12)) & ((-i12) >> 63)) | (jArr[i11] >>> i12);
            long j6 = (((long) i7) * 72340172838076673L) ^ j5;
            for (long j7 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j7) >> 3) + i9) & i8;
                if (this.f3145b[iNumberOfTrailingZeros] == i5) {
                    break loop0;
                }
            }
            if ((j5 & ((~j5) << 6) & (-9187201950435737472L)) != 0) {
                iNumberOfTrailingZeros = -1;
                break;
            }
            i10 += 8;
            i9 = (i9 + i10) & i8;
        }
        return iNumberOfTrailingZeros >= 0;
    }

    public final int d(int i5) {
        int i6 = this.f3146c;
        int i7 = i5 & i6;
        int i8 = 0;
        while (true) {
            long[] jArr = this.f3144a;
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

    public final void e(int i5) {
        long[] jArr;
        int iMax = i5 > 0 ? Math.max(7, f0.c(i5)) : 0;
        this.f3146c = iMax;
        if (iMax == 0) {
            jArr = f0.f3102a;
        } else {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            q3.k.F(jArr);
        }
        this.f3144a = jArr;
        int i6 = iMax >> 3;
        long j5 = 255 << ((iMax & 7) << 3);
        jArr[i6] = (jArr[i6] & (~j5)) | j5;
        this.f3148e = f0.a(this.f3146c) - this.f3147d;
        this.f3145b = new int[iMax];
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0058 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:26:0x005a A[LOOP:0: B:14:0x0021->B:26:0x005a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:30:0x005d A[SYNTHETIC] */
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        if (sVar.f3147d != this.f3147d) {
            return false;
        }
        int[] iArr = this.f3145b;
        long[] jArr = this.f3144a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i5 = 0;
            while (true) {
                long j5 = jArr[i5];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i6 = 8 - ((~(i5 - length)) >>> 31);
                    for (int i7 = 0; i7 < i6; i7++) {
                        if ((255 & j5) < 128 && !sVar.c(iArr[(i5 << 3) + i7])) {
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

    public final boolean f(int i5) {
        int iNumberOfTrailingZeros;
        int iHashCode = Integer.hashCode(i5) * (-862048943);
        int i6 = iHashCode ^ (iHashCode << 16);
        int i7 = i6 & 127;
        int i8 = this.f3146c;
        int i9 = (i6 >>> 7) & i8;
        int i10 = 0;
        loop0: while (true) {
            long[] jArr = this.f3144a;
            int i11 = i9 >> 3;
            int i12 = (i9 & 7) << 3;
            long j5 = ((jArr[i11 + 1] << (64 - i12)) & ((-i12) >> 63)) | (jArr[i11] >>> i12);
            long j6 = (((long) i7) * 72340172838076673L) ^ j5;
            for (long j7 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j7) >> 3) + i9) & i8;
                if (this.f3145b[iNumberOfTrailingZeros] == i5) {
                    break loop0;
                }
            }
            if ((j5 & ((~j5) << 6) & (-9187201950435737472L)) != 0) {
                iNumberOfTrailingZeros = -1;
                break;
            }
            i10 += 8;
            i9 = (i9 + i10) & i8;
        }
        boolean z2 = iNumberOfTrailingZeros >= 0;
        if (z2) {
            g(iNumberOfTrailingZeros);
        }
        return z2;
    }

    public final void g(int i5) {
        this.f3147d--;
        long[] jArr = this.f3144a;
        int i6 = this.f3146c;
        int i7 = i5 >> 3;
        int i8 = (i5 & 7) << 3;
        long j5 = (jArr[i7] & (~(255 << i8))) | (254 << i8);
        jArr[i7] = j5;
        jArr[(((i5 - 7) & i6) + (i6 & 7)) >> 3] = j5;
    }

    public final int hashCode() {
        int[] iArr = this.f3145b;
        long[] jArr = this.f3144a;
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
                        iHashCode = Integer.hashCode(iArr[(i5 << 3) + i7]) + iHashCode;
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

    /* JADX WARN: Code duplicated, block: B:19:0x005d A[DONT_INVERT, PHI: r7
      0x005d: PHI (r7v2 int) = (r7v1 int), (r7v3 int) binds: [B:6:0x0026, B:18:0x005b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:20:0x005f A[LOOP:0: B:5:0x0018->B:20:0x005f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:26:0x0062 A[SYNTHETIC] */
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "[");
        int[] iArr = this.f3145b;
        long[] jArr = this.f3144a;
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
                        int i9 = iArr[(i5 << 3) + i8];
                        if (i6 == -1) {
                            sb.append((CharSequence) "...");
                            break loop0;
                        }
                        if (i6 != 0) {
                            sb.append((CharSequence) ", ");
                        }
                        sb.append(i9);
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
