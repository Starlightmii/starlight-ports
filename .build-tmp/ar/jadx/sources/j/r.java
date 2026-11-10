package j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f3138a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f3139b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f3140c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3141d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3142e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3143f;

    public /* synthetic */ r() {
        this(6);
    }

    public r(int i5) {
        this.f3138a = f0.f3102a;
        this.f3139b = k.f3116a;
        this.f3140c = k.a.f3355c;
        if (i5 >= 0) {
            e(f0.d(i5));
        } else {
            k.a.c("Capacity must be a positive value.");
            throw null;
        }
    }

    public final void a() {
        this.f3142e = 0;
        long[] jArr = this.f3138a;
        if (jArr != f0.f3102a) {
            q3.k.F(jArr);
            long[] jArr2 = this.f3138a;
            int i5 = this.f3141d;
            int i6 = i5 >> 3;
            long j5 = 255 << ((i5 & 7) << 3);
            jArr2[i6] = (jArr2[i6] & (~j5)) | j5;
        }
        q3.k.E(this.f3140c, 0, this.f3141d);
        this.f3143f = f0.a(this.f3141d) - this.f3142e;
    }

    public final boolean b(int i5) {
        int iNumberOfTrailingZeros;
        int iHashCode = Integer.hashCode(i5) * (-862048943);
        int i6 = iHashCode ^ (iHashCode << 16);
        int i7 = i6 & 127;
        int i8 = this.f3141d;
        int i9 = (i6 >>> 7) & i8;
        int i10 = 0;
        loop0: while (true) {
            long[] jArr = this.f3138a;
            int i11 = i9 >> 3;
            int i12 = (i9 & 7) << 3;
            long j5 = ((jArr[i11 + 1] << (64 - i12)) & ((-i12) >> 63)) | (jArr[i11] >>> i12);
            long j6 = (((long) i7) * 72340172838076673L) ^ j5;
            for (long j7 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j7) >> 3) + i9) & i8;
                if (this.f3139b[iNumberOfTrailingZeros] == i5) {
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

    public final int c(int i5) {
        int i6 = this.f3141d;
        int i7 = i5 & i6;
        int i8 = 0;
        while (true) {
            long[] jArr = this.f3138a;
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

    public final Object d(int i5) {
        int iNumberOfTrailingZeros;
        int iHashCode = Integer.hashCode(i5) * (-862048943);
        int i6 = iHashCode ^ (iHashCode << 16);
        int i7 = i6 & 127;
        int i8 = this.f3141d;
        int i9 = (i6 >>> 7) & i8;
        int i10 = 0;
        loop0: while (true) {
            long[] jArr = this.f3138a;
            int i11 = i9 >> 3;
            int i12 = (i9 & 7) << 3;
            long j5 = ((jArr[i11 + 1] << (64 - i12)) & ((-i12) >> 63)) | (jArr[i11] >>> i12);
            long j6 = (((long) i7) * 72340172838076673L) ^ j5;
            for (long j7 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j7) >> 3) + i9) & i8;
                if (this.f3139b[iNumberOfTrailingZeros] == i5) {
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
        if (iNumberOfTrailingZeros >= 0) {
            return this.f3140c[iNumberOfTrailingZeros];
        }
        return null;
    }

    public final void e(int i5) {
        long[] jArr;
        int iMax = i5 > 0 ? Math.max(7, f0.c(i5)) : 0;
        this.f3141d = iMax;
        if (iMax == 0) {
            jArr = f0.f3102a;
        } else {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            q3.k.F(jArr);
        }
        this.f3138a = jArr;
        int i6 = iMax >> 3;
        long j5 = 255 << ((iMax & 7) << 3);
        jArr[i6] = (jArr[i6] & (~j5)) | j5;
        this.f3143f = f0.a(this.f3141d) - this.f3142e;
        this.f3139b = new int[iMax];
        this.f3140c = new Object[iMax];
    }

    /* JADX WARN: Code duplicated, block: B:32:0x006f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:33:0x0071 A[LOOP:0: B:14:0x0023->B:33:0x0071, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:35:0x0074 A[EDGE_INSN: B:35:0x0074->B:34:0x0074 BREAK  A[LOOP:0: B:14:0x0023->B:33:0x0071], SYNTHETIC] */
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        if (rVar.f3142e != this.f3142e) {
            return false;
        }
        int[] iArr = this.f3139b;
        Object[] objArr = this.f3140c;
        long[] jArr = this.f3138a;
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
                            int i9 = iArr[i8];
                            Object obj2 = objArr[i8];
                            if (obj2 == null) {
                                if (rVar.d(i9) != null || !rVar.b(i9)) {
                                    return false;
                                }
                            } else if (!obj2.equals(rVar.d(i9))) {
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

    public final Object f(int i5) {
        int iNumberOfTrailingZeros;
        int iHashCode = Integer.hashCode(i5) * (-862048943);
        int i6 = iHashCode ^ (iHashCode << 16);
        int i7 = i6 & 127;
        int i8 = this.f3141d;
        int i9 = (i6 >>> 7) & i8;
        int i10 = 0;
        loop0: while (true) {
            long[] jArr = this.f3138a;
            int i11 = i9 >> 3;
            int i12 = (i9 & 7) << 3;
            long j5 = ((jArr[i11 + 1] << (64 - i12)) & ((-i12) >> 63)) | (jArr[i11] >>> i12);
            long j6 = (((long) i7) * 72340172838076673L) ^ j5;
            for (long j7 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j7) >> 3) + i9) & i8;
                if (this.f3139b[iNumberOfTrailingZeros] == i5) {
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
        if (iNumberOfTrailingZeros < 0) {
            return null;
        }
        this.f3142e--;
        long[] jArr2 = this.f3138a;
        int i13 = this.f3141d;
        int i14 = iNumberOfTrailingZeros >> 3;
        int i15 = (iNumberOfTrailingZeros & 7) << 3;
        long j8 = (jArr2[i14] & (~(255 << i15))) | (254 << i15);
        jArr2[i14] = j8;
        jArr2[(((iNumberOfTrailingZeros - 7) & i13) + (i13 & 7)) >> 3] = j8;
        Object[] objArr = this.f3140c;
        Object obj = objArr[iNumberOfTrailingZeros];
        objArr[iNumberOfTrailingZeros] = null;
        return obj;
    }

    public final void g(int i5, Object obj) {
        long j5;
        long j6;
        int i6;
        int i7;
        long j7;
        int iC;
        long[] jArr;
        int[] iArr;
        Object[] objArr;
        int i8 = i5;
        int i9 = -862048943;
        int iHashCode = Integer.hashCode(i8) * (-862048943);
        int i10 = iHashCode ^ (iHashCode << 16);
        int i11 = i10 >>> 7;
        int i12 = i10 & 127;
        int i13 = this.f3141d;
        int i14 = i11 & i13;
        int i15 = 0;
        loop0: while (true) {
            long[] jArr2 = this.f3138a;
            int i16 = i14 >> 3;
            int i17 = (i14 & 7) << 3;
            int i18 = 1;
            int i19 = i15;
            int i20 = 0;
            long j8 = (((-i17) >> 63) & (jArr2[i16 + 1] << (64 - i17))) | (jArr2[i16] >>> i17);
            long j9 = i12;
            long j10 = j8 ^ (j9 * 72340172838076673L);
            long j11 = (j10 - 72340172838076673L) & (~j10) & (-9187201950435737472L);
            while (j11 != 0) {
                iC = ((Long.numberOfTrailingZeros(j11) >> 3) + i14) & i13;
                int i21 = i9;
                if (this.f3139b[iC] == i8) {
                    break loop0;
                }
                j11 &= j11 - 1;
                i9 = i21;
            }
            int i22 = i9;
            if ((j8 & ((~j8) << 6) & (-9187201950435737472L)) != 0) {
                int iC2 = c(i11);
                long j12 = 255;
                if (this.f3143f != 0 || ((this.f3138a[iC2 >> 3] >> ((iC2 & 7) << 3)) & 255) == 254) {
                    j5 = j9;
                    j6 = 255;
                    i6 = 1;
                    i7 = 0;
                    j7 = 128;
                    iC = iC2;
                } else {
                    int i23 = this.f3141d;
                    if (i23 > 8) {
                        j7 = 128;
                        if (Long.compareUnsigned(((long) this.f3142e) * 32, ((long) i23) * 25) <= 0) {
                            long[] jArr3 = this.f3138a;
                            int i24 = this.f3141d;
                            int[] iArr2 = this.f3139b;
                            Object[] objArr2 = this.f3140c;
                            int i25 = (i24 + 7) >> 3;
                            int i26 = 0;
                            while (i26 < i25) {
                                long j13 = jArr3[i26] & (-9187201950435737472L);
                                jArr3[i26] = (-72340172838076674L) & ((~j13) + (j13 >>> 7));
                                i26++;
                                j12 = j12;
                                j9 = j9;
                            }
                            j5 = j9;
                            j6 = j12;
                            char c5 = 7;
                            int iG = q3.k.G(jArr3);
                            int i27 = iG - 1;
                            jArr3[i27] = (jArr3[i27] & 72057594037927935L) | (-72057594037927936L);
                            jArr3[iG] = jArr3[0];
                            int i28 = 0;
                            while (i28 != i24) {
                                int i29 = i28 >> 3;
                                int i30 = (i28 & 7) << 3;
                                long j14 = (jArr3[i29] >> i30) & j6;
                                if (j14 != 128 && j14 == 254) {
                                    int iHashCode2 = Integer.hashCode(iArr2[i28]) * i22;
                                    int i31 = iHashCode2 ^ (iHashCode2 << 16);
                                    int i32 = i31 >>> 7;
                                    int iC3 = c(i32);
                                    int i33 = i32 & i24;
                                    char c6 = c5;
                                    if (((iC3 - i33) & i24) / 8 == ((i28 - i33) & i24) / 8) {
                                        int i34 = i20;
                                        jArr3[i29] = (((long) (i31 & 127)) << i30) | (jArr3[i29] & (~(j6 << i30)));
                                        jArr3[jArr3.length - 1] = (jArr3[i34] & 72057594037927935L) | Long.MIN_VALUE;
                                        i28++;
                                        i18 = i18;
                                        c5 = c6;
                                        i20 = i34;
                                    } else {
                                        int i35 = i18;
                                        int i36 = i20;
                                        int i37 = iC3 >> 3;
                                        long j15 = jArr3[i37];
                                        int i38 = (iC3 & 7) << 3;
                                        if (((j15 >> i38) & j6) == 128) {
                                            iArr = iArr2;
                                            objArr = objArr2;
                                            jArr3[i37] = ((~(j6 << i38)) & j15) | (((long) (i31 & 127)) << i38);
                                            jArr3[i29] = (jArr3[i29] & (~(j6 << i30))) | (128 << i30);
                                            iArr[iC3] = iArr[i28];
                                            iArr[i28] = i36;
                                            objArr[iC3] = objArr[i28];
                                            objArr[i28] = null;
                                        } else {
                                            iArr = iArr2;
                                            objArr = objArr2;
                                            jArr3[i37] = (((long) (i31 & 127)) << i38) | ((~(j6 << i38)) & j15);
                                            int i39 = iArr[iC3];
                                            iArr[iC3] = iArr[i28];
                                            iArr[i28] = i39;
                                            Object obj2 = objArr[iC3];
                                            objArr[iC3] = objArr[i28];
                                            objArr[i28] = obj2;
                                            i28--;
                                        }
                                        jArr3[jArr3.length - 1] = (jArr3[i36] & 72057594037927935L) | Long.MIN_VALUE;
                                        i28++;
                                        i18 = i35;
                                        c5 = c6;
                                        i20 = i36;
                                        iArr2 = iArr;
                                        objArr2 = objArr;
                                    }
                                } else {
                                    i28++;
                                }
                            }
                            i6 = i18;
                            i7 = i20;
                            this.f3143f = f0.a(this.f3141d) - this.f3142e;
                        }
                        iC = c(i11);
                    } else {
                        j7 = 128;
                    }
                    j5 = j9;
                    j6 = 255;
                    i6 = 1;
                    i7 = 0;
                    int iB = f0.b(this.f3141d);
                    long[] jArr4 = this.f3138a;
                    int[] iArr3 = this.f3139b;
                    Object[] objArr3 = this.f3140c;
                    int i40 = this.f3141d;
                    e(iB);
                    long[] jArr5 = this.f3138a;
                    int[] iArr4 = this.f3139b;
                    Object[] objArr4 = this.f3140c;
                    int i41 = this.f3141d;
                    int i42 = 0;
                    while (i42 < i40) {
                        if (((jArr4[i42 >> 3] >> ((i42 & 7) << 3)) & 255) < j7) {
                            int i43 = iArr3[i42];
                            int iHashCode3 = Integer.hashCode(i43) * i22;
                            int i44 = iHashCode3 ^ (iHashCode3 << 16);
                            int iC4 = c(i44 >>> 7);
                            jArr = jArr5;
                            long j16 = i44 & 127;
                            int i45 = iC4 >> 3;
                            int i46 = (iC4 & 7) << 3;
                            long j17 = (jArr[i45] & (~(255 << i46))) | (j16 << i46);
                            jArr[i45] = j17;
                            jArr[(((iC4 - 7) & i41) + (i41 & 7)) >> 3] = j17;
                            iArr4[iC4] = i43;
                            objArr4[iC4] = objArr3[i42];
                        } else {
                            jArr = jArr5;
                        }
                        i42++;
                        jArr5 = jArr;
                    }
                    iC = c(i11);
                }
                this.f3142e++;
                int i47 = this.f3143f;
                long[] jArr6 = this.f3138a;
                int i48 = iC >> 3;
                long j18 = jArr6[i48];
                int i49 = (iC & 7) << 3;
                this.f3143f = i47 - (((j18 >> i49) & j6) == j7 ? i6 : i7);
                int i50 = this.f3141d;
                long j19 = (j18 & (~(j6 << i49))) | (j5 << i49);
                jArr6[i48] = j19;
                jArr6[(((iC - 7) & i50) + (i50 & 7)) >> 3] = j19;
                break;
            }
            i15 = i19 + 8;
            i14 = (i14 + i15) & i13;
            i8 = i5;
            i9 = i22;
        }
        this.f3139b[iC] = i5;
        this.f3140c[iC] = obj;
    }

    public final int hashCode() {
        int[] iArr = this.f3139b;
        Object[] objArr = this.f3140c;
        long[] jArr = this.f3138a;
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
                        Object obj = objArr[i8];
                        iHashCode += (obj != null ? obj.hashCode() : 0) ^ Integer.hashCode(i9);
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
        if (this.f3142e == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        int[] iArr = this.f3139b;
        Object[] objArr = this.f3140c;
        long[] jArr = this.f3138a;
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
                            Object obj = objArr[i9];
                            sb.append(i10);
                            sb.append("=");
                            if (obj == this) {
                                obj = "(this)";
                            }
                            sb.append(obj);
                            i6++;
                            if (i6 < this.f3142e) {
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
