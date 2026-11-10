package j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f3162a = f0.f3102a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f3163b = k.a.f3355c;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float[] f3164c = h.f3107a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3165d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3166e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3167f;

    public w() {
        d(f0.d(6));
    }

    public final void a() {
        this.f3166e = 0;
        long[] jArr = this.f3162a;
        if (jArr != f0.f3102a) {
            q3.k.F(jArr);
            long[] jArr2 = this.f3162a;
            int i5 = this.f3165d;
            int i6 = i5 >> 3;
            long j5 = 255 << ((i5 & 7) << 3);
            jArr2[i6] = (jArr2[i6] & (~j5)) | j5;
        }
        q3.k.E(this.f3163b, 0, this.f3165d);
        this.f3167f = f0.a(this.f3165d) - this.f3166e;
    }

    public final int b(int i5) {
        int i6 = this.f3165d;
        int i7 = i5 & i6;
        int i8 = 0;
        while (true) {
            long[] jArr = this.f3162a;
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

    public final int c(Object obj) {
        int i5 = 0;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i6 = iHashCode ^ (iHashCode << 16);
        int i7 = i6 & 127;
        int i8 = this.f3165d;
        int i9 = i6 >>> 7;
        while (true) {
            int i10 = i9 & i8;
            long[] jArr = this.f3162a;
            int i11 = i10 >> 3;
            int i12 = (i10 & 7) << 3;
            long j5 = ((jArr[i11 + 1] << (64 - i12)) & ((-i12) >> 63)) | (jArr[i11] >>> i12);
            long j6 = (((long) i7) * 72340172838076673L) ^ j5;
            for (long j7 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j7) >> 3) + i10) & i8;
                if (b4.l.a(this.f3163b[iNumberOfTrailingZeros], obj)) {
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
        this.f3165d = iMax;
        if (iMax == 0) {
            jArr = f0.f3102a;
        } else {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            q3.k.F(jArr);
        }
        this.f3162a = jArr;
        int i6 = iMax >> 3;
        long j5 = 255 << ((iMax & 7) << 3);
        jArr[i6] = (jArr[i6] & (~j5)) | j5;
        this.f3167f = f0.a(this.f3165d) - this.f3166e;
        this.f3163b = new Object[iMax];
        this.f3164c = new float[iMax];
    }

    public final boolean equals(Object obj) {
        boolean z2;
        boolean z3 = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof w)) {
            return false;
        }
        w wVar = (w) obj;
        if (wVar.f3166e != this.f3166e) {
            return false;
        }
        Object[] objArr = this.f3163b;
        float[] fArr = this.f3164c;
        long[] jArr = this.f3162a;
        int length = jArr.length - 2;
        if (length < 0) {
            return true;
        }
        int i5 = 0;
        while (true) {
            long j5 = jArr[i5];
            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i6 = 8 - ((~(i5 - length)) >>> 31);
                int i7 = 0;
                while (i7 < i6) {
                    if ((255 & j5) < 128) {
                        int i8 = (i5 << 3) + i7;
                        Object obj2 = objArr[i8];
                        float f5 = fArr[i8];
                        int iC = wVar.c(obj2);
                        if (iC < 0 || f5 != wVar.f3164c[iC]) {
                            return false;
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
    }

    public final int hashCode() {
        Object[] objArr = this.f3163b;
        float[] fArr = this.f3164c;
        long[] jArr = this.f3162a;
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
                        iHashCode += Float.hashCode(fArr[i8]) ^ (obj != null ? obj.hashCode() : 0);
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
        if (this.f3166e == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Object[] objArr = this.f3163b;
        float[] fArr = this.f3164c;
        long[] jArr = this.f3162a;
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
                            float f5 = fArr[i9];
                            if (obj == this) {
                                obj = "(this)";
                            }
                            sb.append(obj);
                            sb.append("=");
                            sb.append(f5);
                            i6++;
                            if (i6 < this.f3166e) {
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
