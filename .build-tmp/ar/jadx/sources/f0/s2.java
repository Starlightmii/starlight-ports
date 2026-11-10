package f0;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t2 f2305a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f2306b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2307c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f2308d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2309e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2310f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2311g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2312h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2313i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final w0 f2314j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2315k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2316l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2317m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2318n;

    public s2(t2 t2Var) {
        this.f2305a = t2Var;
        this.f2306b = t2Var.f2333d;
        int i5 = t2Var.f2334e;
        this.f2307c = i5;
        this.f2308d = t2Var.f2335f;
        this.f2309e = t2Var.f2336g;
        this.f2312h = i5;
        this.f2313i = -1;
        this.f2314j = new w0();
    }

    public final a a(int i5) {
        ArrayList arrayList = this.f2305a.f2341l;
        int iE = v2.e(arrayList, i5, this.f2307c);
        if (iE >= 0) {
            return (a) arrayList.get(iE);
        }
        a aVar = new a(i5);
        arrayList.add(-(iE + 1), aVar);
        return aVar;
    }

    public final Object b(int[] iArr, int i5) {
        int i6 = i5 * 5;
        int i7 = iArr[i6 + 1];
        if ((268435456 & i7) != 0) {
            return this.f2308d[i6 >= iArr.length ? iArr.length : iArr[i6 + 4] + Integer.bitCount(i7 >> 29)];
        }
        return o.f2281a;
    }

    public final void c() {
        this.f2310f = true;
        t2 t2Var = this.f2305a;
        t2Var.getClass();
        if (this.f2305a != t2Var || t2Var.f2337h <= 0) {
            z.a("Unexpected reader close()");
        }
        t2Var.f2337h--;
        this.f2308d = new Object[0];
    }

    public final boolean d(int i5) {
        return (this.f2306b[(i5 * 5) + 1] & 67108864) != 0;
    }

    public final void e() {
        if (this.f2315k == 0) {
            if (!(this.f2311g == this.f2312h)) {
                z.a("endGroup() not called at the end of a group");
            }
            int i5 = (this.f2313i * 5) + 2;
            int[] iArr = this.f2306b;
            int i6 = iArr[i5];
            this.f2313i = i6;
            int i7 = this.f2307c;
            this.f2312h = i6 < 0 ? i7 : v2.a(iArr, i6) + i6;
            int iB = this.f2314j.b();
            if (iB < 0) {
                this.f2316l = 0;
                this.f2317m = 0;
            } else {
                this.f2316l = iB;
                this.f2317m = i6 >= i7 - 1 ? this.f2309e : iArr[((i6 + 1) * 5) + 4];
            }
        }
    }

    public final Object f() {
        int i5 = this.f2311g;
        if (i5 < this.f2312h) {
            return b(this.f2306b, i5);
        }
        return 0;
    }

    public final int g() {
        int i5 = this.f2311g;
        if (i5 >= this.f2312h) {
            return 0;
        }
        return this.f2306b[i5 * 5];
    }

    public final Object h(int i5, int i6) {
        int[] iArr = this.f2306b;
        int iC = v2.c(iArr, i5);
        int i7 = i5 + 1;
        int i8 = iC + i6;
        return i8 < (i7 < this.f2307c ? iArr[(i7 * 5) + 4] : this.f2309e) ? this.f2308d[i8] : o.f2281a;
    }

    public final int i(int i5) {
        return this.f2306b[i5 * 5];
    }

    public final boolean j(int i5) {
        return (this.f2306b[(i5 * 5) + 1] & 134217728) != 0;
    }

    public final boolean k(int i5) {
        return (this.f2306b[(i5 * 5) + 1] & 536870912) != 0;
    }

    public final boolean l(int i5) {
        return (this.f2306b[(i5 * 5) + 1] & 1073741824) != 0;
    }

    public final Object m() {
        int i5;
        if (this.f2315k > 0 || (i5 = this.f2316l) >= this.f2317m) {
            this.f2318n = false;
            return o.f2281a;
        }
        this.f2318n = true;
        Object[] objArr = this.f2308d;
        this.f2316l = i5 + 1;
        return objArr[i5];
    }

    public final Object n(int i5) {
        int i6 = i5 * 5;
        int[] iArr = this.f2306b;
        int i7 = iArr[i6 + 1] & 1073741824;
        if (i7 != 0) {
            return i7 != 0 ? this.f2308d[iArr[i6 + 4]] : o.f2281a;
        }
        return null;
    }

    public final int o(int i5) {
        return this.f2306b[(i5 * 5) + 1] & 67108863;
    }

    public final Object p(int[] iArr, int i5) {
        int i6 = i5 * 5;
        int i7 = iArr[i6 + 1];
        if ((536870912 & i7) == 0) {
            return null;
        }
        return this.f2308d[Integer.bitCount(i7 >> 30) + iArr[i6 + 4]];
    }

    public final int q(int i5) {
        return this.f2306b[(i5 * 5) + 2];
    }

    public final void r(int i5) {
        if (!(this.f2315k == 0)) {
            z.a("Cannot reposition while in an empty region");
        }
        this.f2311g = i5;
        int[] iArr = this.f2306b;
        int i6 = this.f2307c;
        int i7 = i5 < i6 ? iArr[(i5 * 5) + 2] : -1;
        if (i7 != this.f2313i) {
            this.f2313i = i7;
            if (i7 < 0) {
                this.f2312h = i6;
            } else {
                this.f2312h = v2.a(iArr, i7) + i7;
            }
            this.f2316l = 0;
            this.f2317m = 0;
        }
    }

    public final int s() {
        if (!(this.f2315k == 0)) {
            z.a("Cannot skip while in an empty region");
        }
        int i5 = this.f2311g;
        int[] iArr = this.f2306b;
        int i6 = (iArr[(i5 * 5) + 1] & 1073741824) == 0 ? iArr[(i5 * 5) + 1] & 67108863 : 1;
        this.f2311g = v2.a(iArr, i5) + i5;
        return i6;
    }

    public final void t() {
        if (!(this.f2315k == 0)) {
            z.a("Cannot skip the enclosing group while in an empty region");
        }
        this.f2311g = this.f2312h;
        this.f2316l = 0;
        this.f2317m = 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SlotReader(current=");
        sb.append(this.f2311g);
        sb.append(", key=");
        sb.append(g());
        sb.append(", parent=");
        sb.append(this.f2313i);
        sb.append(", end=");
        return b.b.f(sb, this.f2312h, ')');
    }

    public final void u() {
        if (this.f2315k <= 0) {
            int i5 = this.f2313i;
            int i6 = this.f2311g;
            int[] iArr = this.f2306b;
            if (!(iArr[(i6 * 5) + 2] == i5)) {
                x1.a("Invalid slot table detected");
            }
            int i7 = this.f2316l;
            int i8 = this.f2317m;
            w0 w0Var = this.f2314j;
            if (i7 == 0 && i8 == 0) {
                w0Var.c(-1);
            } else {
                w0Var.c(i7);
            }
            this.f2313i = i6;
            this.f2312h = v2.a(iArr, i6) + i6;
            int i9 = i6 + 1;
            this.f2311g = i9;
            this.f2316l = v2.c(iArr, i6);
            this.f2317m = i6 >= this.f2307c - 1 ? this.f2309e : iArr[(i9 * 5) + 4];
        }
    }
}
