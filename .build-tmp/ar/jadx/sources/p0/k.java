package p0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k implements Iterable, c4.a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final k f4709h = new k(0, 0, 0, null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f4710d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f4711e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f4712f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long[] f4713g;

    public k(long j5, long j6, long j7, long[] jArr) {
        this.f4710d = j5;
        this.f4711e = j6;
        this.f4712f = j7;
        this.f4713g = jArr;
    }

    public final k a(k kVar) {
        k kVarB;
        long[] jArr;
        k kVar2 = f4709h;
        if (kVar == kVar2) {
            return this;
        }
        if (this == kVar2) {
            return kVar2;
        }
        long j5 = kVar.f4712f;
        long j6 = kVar.f4712f;
        long[] jArr2 = kVar.f4713g;
        long j7 = kVar.f4711e;
        long j8 = kVar.f4710d;
        long j9 = this.f4712f;
        if (j5 == j9 && jArr2 == (jArr = this.f4713g)) {
            return new k(this.f4710d & (~j8), this.f4711e & (~j7), j9, jArr);
        }
        if (jArr2 != null) {
            kVarB = this;
            for (long j10 : jArr2) {
                kVarB = kVarB.b(j10);
            }
        } else {
            kVarB = this;
        }
        long j11 = 0;
        if (j7 != 0) {
            int i5 = 0;
            while (i5 < 64) {
                if (((1 << i5) & j7) != j11) {
                    kVarB = kVarB.b(((long) i5) + j6);
                }
                i5++;
                j11 = j11;
            }
        }
        long j12 = j11;
        if (j8 != j12) {
            for (int i6 = 0; i6 < 64; i6++) {
                if (((1 << i6) & j8) != j12) {
                    kVarB = kVarB.b(((long) i6) + j6 + ((long) 64));
                }
            }
        }
        return kVarB;
    }

    public final k b(long j5) {
        long[] jArr;
        int iC;
        long[] jArr2;
        long j6 = j5 - this.f4712f;
        long j7 = 0;
        if (b4.l.g(j6, j7) >= 0 && b4.l.g(j6, 64) < 0) {
            long j8 = 1 << ((int) j6);
            long j9 = this.f4711e;
            if ((j9 & j8) != 0) {
                return new k(this.f4710d, j9 & (~j8), this.f4712f, this.f4713g);
            }
        } else if (b4.l.g(j6, 64) >= 0 && b4.l.g(j6, 128) < 0) {
            long j10 = 1 << (((int) j6) - 64);
            long j11 = this.f4710d;
            if ((j11 & j10) != 0) {
                return new k(j11 & (~j10), this.f4711e, this.f4712f, this.f4713g);
            }
        } else if (b4.l.g(j6, j7) < 0 && (jArr = this.f4713g) != null && (iC = r.c(jArr, j5)) >= 0) {
            int length = jArr.length;
            int i5 = length - 1;
            if (i5 == 0) {
                jArr2 = null;
            } else {
                long[] jArr3 = new long[i5];
                if (iC > 0) {
                    q3.k.z(jArr, jArr3, 0, 0, iC);
                }
                if (iC < i5) {
                    q3.k.z(jArr, jArr3, iC, iC + 1, length);
                }
                jArr2 = jArr3;
            }
            return new k(this.f4710d, this.f4711e, this.f4712f, jArr2);
        }
        return this;
    }

    public final boolean c(long j5) {
        long[] jArr;
        long j6 = j5 - this.f4712f;
        long j7 = 0;
        if (b4.l.g(j6, j7) >= 0 && b4.l.g(j6, 64) < 0) {
            return ((1 << ((int) j6)) & this.f4711e) != 0;
        }
        if (b4.l.g(j6, 64) < 0 || b4.l.g(j6, 128) >= 0) {
            return b4.l.g(j6, j7) <= 0 && (jArr = this.f4713g) != null && r.c(jArr, j5) >= 0;
        }
        return ((1 << (((int) j6) - 64)) & this.f4710d) != 0;
    }

    public final k d(k kVar) {
        k kVarE;
        k kVarE2;
        long[] jArr;
        k kVar2 = f4709h;
        if (kVar == kVar2) {
            return this;
        }
        if (this == kVar2) {
            return kVar;
        }
        long j5 = kVar.f4712f;
        long j6 = kVar.f4712f;
        long[] jArr2 = kVar.f4713g;
        long j7 = kVar.f4711e;
        long j8 = kVar.f4710d;
        long j9 = this.f4712f;
        long j10 = this.f4711e;
        long j11 = this.f4710d;
        if (j5 == j9 && jArr2 == (jArr = this.f4713g)) {
            return new k(j11 | j8, j10 | j7, j9, jArr);
        }
        int i5 = 0;
        long[] jArr3 = this.f4713g;
        if (jArr3 != null) {
            if (jArr2 != null) {
                kVarE = this;
                for (long j12 : jArr2) {
                    kVarE = kVarE.e(j12);
                }
            } else {
                kVarE = this;
            }
            if (j7 != 0) {
                for (int i6 = 0; i6 < 64; i6++) {
                    if (((1 << i6) & j7) != 0) {
                        kVarE = kVarE.e(((long) i6) + j6);
                    }
                }
            }
            if (j8 != 0) {
                while (i5 < 64) {
                    if (((1 << i5) & j8) != 0) {
                        kVarE = kVarE.e(((long) i5) + j6 + ((long) 64));
                    }
                    i5++;
                }
            }
            return kVarE;
        }
        if (jArr3 != null) {
            kVarE2 = kVar;
            for (long j13 : jArr3) {
                kVarE2 = kVarE2.e(j13);
            }
        } else {
            kVarE2 = kVar;
        }
        long j14 = this.f4712f;
        if (j10 != 0) {
            for (int i7 = 0; i7 < 64; i7++) {
                if (((1 << i7) & j10) != 0) {
                    kVarE2 = kVarE2.e(((long) i7) + j14);
                }
            }
        }
        if (j11 != 0) {
            while (i5 < 64) {
                if (((1 << i5) & j11) != 0) {
                    kVarE2 = kVarE2.e(((long) i5) + j14 + ((long) 64));
                }
                i5++;
            }
        }
        return kVarE2;
    }

    /* JADX WARN: Code duplicated, block: B:61:0x0141  */
    public final k e(long j5) {
        long[] jArr;
        long j6;
        long j7;
        long[] jArr2;
        long[] jArr3;
        long j8 = this.f4712f;
        long j9 = j5 - j8;
        long j10 = 0;
        int iG = b4.l.g(j9, j10);
        long j11 = this.f4711e;
        int i5 = 64;
        if (iG < 0 || b4.l.g(j9, 64) >= 0) {
            long j12 = 64;
            int iG2 = b4.l.g(j9, j12);
            long j13 = this.f4710d;
            if (iG2 < 0 || b4.l.g(j9, 128) >= 0) {
                long j14 = 128;
                int iG3 = b4.l.g(j9, j14);
                long[] jArr4 = this.f4713g;
                if (iG3 < 0) {
                    if (jArr4 == null) {
                        return new k(this.f4710d, this.f4711e, this.f4712f, new long[]{j5});
                    }
                    int iC = r.c(jArr4, j5);
                    if (iC < 0) {
                        int i6 = -(iC + 1);
                        int length = jArr4.length;
                        long[] jArr5 = new long[length + 1];
                        q3.k.z(jArr4, jArr5, 0, 0, i6);
                        q3.k.z(jArr4, jArr5, i6 + 1, i6, length);
                        jArr5[i6] = j5;
                        return new k(this.f4710d, this.f4711e, this.f4712f, jArr5);
                    }
                } else if (!c(j5)) {
                    long j15 = 1;
                    long j16 = ((j5 + j15) / j12) * j12;
                    long[] jArr6 = jArr4;
                    if (b4.l.g(j16, j10) < 0) {
                        j16 = (Long.MAX_VALUE - j14) + j15;
                    }
                    long j17 = j13;
                    long j18 = j8;
                    a1.d dVar = null;
                    while (true) {
                        if (b4.l.g(j18, j16) >= 0) {
                            jArr = jArr6;
                            j6 = j11;
                            j7 = j18;
                            break;
                        }
                        if (j11 != 0) {
                            if (dVar == null) {
                                jArr = jArr6;
                                dVar = new a1.d(jArr);
                            } else {
                                jArr = jArr6;
                            }
                            int i7 = 0;
                            while (i7 < i5) {
                                long j19 = j18;
                                if ((j11 & (1 << i7)) != 0) {
                                    long j20 = j19 + ((long) i7);
                                    j.t tVar = (j.t) dVar.f31e;
                                    int i8 = tVar.f3150b + 1;
                                    long[] jArr7 = tVar.f3149a;
                                    if (jArr7.length < i8) {
                                        long[] jArrCopyOf = Arrays.copyOf(jArr7, Math.max(i8, (jArr7.length * 3) / 2));
                                        b4.l.d("copyOf(...)", jArrCopyOf);
                                        tVar.f3149a = jArrCopyOf;
                                    }
                                    long[] jArr8 = tVar.f3149a;
                                    int i9 = tVar.f3150b;
                                    jArr8[i9] = j20;
                                    tVar.f3150b = i9 + 1;
                                }
                                i7++;
                                j18 = j19;
                                i5 = 64;
                            }
                        } else {
                            jArr = jArr6;
                        }
                        long j21 = j18;
                        if (j17 == 0) {
                            j7 = j16;
                            j6 = 0;
                            break;
                        }
                        j18 = j21 + j12;
                        jArr6 = jArr;
                        j11 = j17;
                        i5 = 64;
                        j17 = 0;
                    }
                    if (dVar == null) {
                        jArr2 = jArr;
                    } else {
                        j.t tVar2 = (j.t) dVar.f31e;
                        int i10 = tVar2.f3150b;
                        if (i10 == 0) {
                            jArr3 = null;
                        } else {
                            long[] jArr9 = new long[i10];
                            long[] jArr10 = tVar2.f3149a;
                            for (int i11 = 0; i11 < i10; i11++) {
                                jArr9[i11] = jArr10[i11];
                            }
                            jArr3 = jArr9;
                        }
                        if (jArr3 == null) {
                            jArr2 = jArr;
                        } else {
                            jArr2 = jArr3;
                        }
                    }
                    return new k(j17, j6, j7, jArr2).e(j5);
                }
            } else {
                long j22 = 1 << (((int) j9) - 64);
                if ((j13 & j22) == 0) {
                    return new k(j13 | j22, this.f4711e, this.f4712f, this.f4713g);
                }
            }
        } else {
            long j23 = 1 << ((int) j9);
            if ((j11 & j23) == 0) {
                return new k(this.f4710d, j11 | j23, this.f4712f, this.f4713g);
            }
        }
        return this;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return h1.c.C(new j(this, null));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [");
        ArrayList arrayList = new ArrayList(q3.m.S(this));
        Iterator it = iterator();
        while (true) {
            g4.g gVar = (g4.g) it;
            if (!gVar.hasNext()) {
                break;
            }
            arrayList.add(String.valueOf(((Number) gVar.next()).longValue()));
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append((CharSequence) "");
        int size = arrayList.size();
        int i5 = 0;
        for (int i6 = 0; i6 < size; i6++) {
            Object obj = arrayList.get(i6);
            i5++;
            if (i5 > 1) {
                sb2.append((CharSequence) ", ");
            }
            if (obj != null ? obj instanceof CharSequence : true) {
                sb2.append((CharSequence) obj);
            } else if (obj instanceof Character) {
                sb2.append(((Character) obj).charValue());
            } else {
                sb2.append((CharSequence) obj.toString());
            }
        }
        sb2.append((CharSequence) "");
        sb.append(sb2.toString());
        sb.append(']');
        return sb.toString();
    }
}
