package j;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m implements Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public /* synthetic */ boolean f3118d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ long[] f3119e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ Object[] f3120f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ int f3121g;

    public m(int i5) {
        if (i5 == 0) {
            this.f3119e = k.a.f3354b;
            this.f3120f = k.a.f3355c;
            return;
        }
        int i6 = i5 * 8;
        for (int i7 = 4; i7 < 32; i7++) {
            int i8 = (1 << i7) - 12;
            if (i6 <= i8) {
                i6 = i8;
                break;
            }
        }
        int i9 = i6 / 8;
        this.f3119e = new long[i9];
        this.f3120f = new Object[i9];
    }

    public final long a(int i5) {
        int i6;
        if (i5 < 0 || i5 >= (i6 = this.f3121g)) {
            k.a.c("Expected index to be within 0..size()-1, but was " + i5);
            throw null;
        }
        if (this.f3118d) {
            long[] jArr = this.f3119e;
            Object[] objArr = this.f3120f;
            int i7 = 0;
            for (int i8 = 0; i8 < i6; i8++) {
                Object obj = objArr[i8];
                if (obj != n.f3122a) {
                    if (i8 != i7) {
                        jArr[i7] = jArr[i8];
                        objArr[i7] = obj;
                        objArr[i8] = null;
                    }
                    i7++;
                }
            }
            this.f3118d = false;
            this.f3121g = i7;
        }
        return this.f3119e[i5];
    }

    public final void b(long j5, Object obj) {
        Object obj2 = n.f3122a;
        int iB = k.a.b(this.f3119e, this.f3121g, j5);
        if (iB >= 0) {
            this.f3120f[iB] = obj;
            return;
        }
        int i5 = ~iB;
        int i6 = this.f3121g;
        if (i5 < i6) {
            Object[] objArr = this.f3120f;
            if (objArr[i5] == obj2) {
                this.f3119e[i5] = j5;
                objArr[i5] = obj;
                return;
            }
        }
        if (this.f3118d) {
            long[] jArr = this.f3119e;
            if (i6 >= jArr.length) {
                Object[] objArr2 = this.f3120f;
                int i7 = 0;
                for (int i8 = 0; i8 < i6; i8++) {
                    Object obj3 = objArr2[i8];
                    if (obj3 != obj2) {
                        if (i8 != i7) {
                            jArr[i7] = jArr[i8];
                            objArr2[i7] = obj3;
                            objArr2[i8] = null;
                        }
                        i7++;
                    }
                }
                this.f3118d = false;
                this.f3121g = i7;
                i5 = ~k.a.b(this.f3119e, i7, j5);
            }
        }
        int i9 = this.f3121g;
        if (i9 >= this.f3119e.length) {
            int i10 = (i9 + 1) * 8;
            for (int i11 = 4; i11 < 32; i11++) {
                int i12 = (1 << i11) - 12;
                if (i10 <= i12) {
                    i10 = i12;
                    break;
                }
            }
            int i13 = i10 / 8;
            long[] jArrCopyOf = Arrays.copyOf(this.f3119e, i13);
            b4.l.d("copyOf(...)", jArrCopyOf);
            this.f3119e = jArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f3120f, i13);
            b4.l.d("copyOf(...)", objArrCopyOf);
            this.f3120f = objArrCopyOf;
        }
        int i14 = this.f3121g;
        if (i14 - i5 != 0) {
            long[] jArr2 = this.f3119e;
            int i15 = i5 + 1;
            q3.k.z(jArr2, jArr2, i15, i5, i14);
            Object[] objArr3 = this.f3120f;
            q3.k.A(objArr3, objArr3, i15, i5, this.f3121g);
        }
        this.f3119e[i5] = j5;
        this.f3120f[i5] = obj;
        this.f3121g++;
    }

    public final int c() {
        if (this.f3118d) {
            int i5 = this.f3121g;
            long[] jArr = this.f3119e;
            Object[] objArr = this.f3120f;
            int i6 = 0;
            for (int i7 = 0; i7 < i5; i7++) {
                Object obj = objArr[i7];
                if (obj != n.f3122a) {
                    if (i7 != i6) {
                        jArr[i6] = jArr[i7];
                        objArr[i6] = obj;
                        objArr[i7] = null;
                    }
                    i6++;
                }
            }
            this.f3118d = false;
            this.f3121g = i6;
        }
        return this.f3121g;
    }

    public final Object clone() throws CloneNotSupportedException {
        Object objClone = super.clone();
        b4.l.c("null cannot be cast to non-null type androidx.collection.LongSparseArray<E of androidx.collection.LongSparseArray>", objClone);
        m mVar = (m) objClone;
        mVar.f3119e = (long[]) this.f3119e.clone();
        mVar.f3120f = (Object[]) this.f3120f.clone();
        return mVar;
    }

    public final Object d(int i5) {
        int i6;
        if (i5 < 0 || i5 >= (i6 = this.f3121g)) {
            k.a.c("Expected index to be within 0..size()-1, but was " + i5);
            throw null;
        }
        if (this.f3118d) {
            long[] jArr = this.f3119e;
            Object[] objArr = this.f3120f;
            int i7 = 0;
            for (int i8 = 0; i8 < i6; i8++) {
                Object obj = objArr[i8];
                if (obj != n.f3122a) {
                    if (i8 != i7) {
                        jArr[i7] = jArr[i8];
                        objArr[i7] = obj;
                        objArr[i8] = null;
                    }
                    i7++;
                }
            }
            this.f3118d = false;
            this.f3121g = i7;
        }
        return this.f3120f[i5];
    }

    public final String toString() {
        if (c() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f3121g * 28);
        sb.append('{');
        int i5 = this.f3121g;
        for (int i6 = 0; i6 < i5; i6++) {
            if (i6 > 0) {
                sb.append(", ");
            }
            sb.append(a(i6));
            sb.append('=');
            Object objD = d(i6);
            if (objD != sb) {
                sb.append(objD);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        b4.l.d("toString(...)", string);
        return string;
    }
}
