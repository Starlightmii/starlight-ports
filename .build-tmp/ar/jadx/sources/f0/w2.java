package f0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t2 f2390a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f2391b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f2392c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList f2393d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public HashMap f2394e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public j.r f2395f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2396g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2397h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2398i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2399j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2400k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2401l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2402m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2403n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f2404o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final w0 f2405p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final w0 f2406q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final w0 f2407r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public j.r f2408s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f2409t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f2410u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f2411v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f2412w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public j.q f2413x;

    public w2(t2 t2Var) {
        this.f2390a = t2Var;
        int[] iArr = t2Var.f2333d;
        this.f2391b = iArr;
        Object[] objArr = t2Var.f2335f;
        this.f2392c = objArr;
        this.f2393d = t2Var.f2341l;
        this.f2394e = t2Var.f2342m;
        this.f2395f = t2Var.f2343n;
        int i5 = t2Var.f2334e;
        this.f2396g = i5;
        this.f2397h = (iArr.length / 5) - i5;
        int i6 = t2Var.f2336g;
        this.f2400k = i6;
        this.f2401l = objArr.length - i6;
        this.f2402m = i5;
        this.f2405p = new w0();
        this.f2406q = new w0();
        this.f2407r = new w0();
        this.f2410u = i5;
        this.f2411v = -1;
    }

    public static int i(int i5, int i6, int i7, int i8) {
        return i5 > i6 ? -(((i8 - i7) - i5) + 1) : i5;
    }

    public static void z(w2 w2Var) {
        int i5 = w2Var.f2411v;
        int iR = w2Var.r(i5);
        int[] iArr = w2Var.f2391b;
        int i6 = (iR * 5) + 1;
        int i7 = iArr[i6];
        if ((i7 & 134217728) != 0) {
            return;
        }
        int i8 = (i7 & (-134217729)) | 134217728;
        iArr[i6] = i8;
        if ((67108864 & i8) != 0) {
            return;
        }
        w2Var.T(w2Var.E(iArr, i5));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void A(t2 t2Var, int i5) {
        if (this.f2403n <= 0) {
            z.a("Check failed");
        }
        boolean z2 = false;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        if (i5 == 0 && this.f2409t == 0 && this.f2390a.f2334e == 0) {
            int[] iArr = t2Var.f2333d;
            int i6 = iArr[(i5 * 5) + 3];
            int i7 = t2Var.f2334e;
            if (i6 == i7) {
                int[] iArr2 = this.f2391b;
                Object[] objArr3 = this.f2392c;
                ArrayList arrayList = this.f2393d;
                HashMap map = this.f2394e;
                j.r rVar = this.f2395f;
                Object[] objArr4 = t2Var.f2335f;
                int i8 = t2Var.f2336g;
                HashMap map2 = t2Var.f2342m;
                j.r rVar2 = t2Var.f2343n;
                this.f2391b = iArr;
                this.f2392c = objArr4;
                this.f2393d = t2Var.f2341l;
                this.f2396g = i7;
                this.f2397h = (iArr.length / 5) - i7;
                this.f2400k = i8;
                this.f2401l = objArr4.length - i8;
                this.f2402m = i7;
                this.f2394e = map2;
                this.f2395f = rVar2;
                t2Var.f2333d = iArr2;
                t2Var.f2334e = objArr2 == true ? 1 : 0;
                t2Var.f2335f = objArr3;
                t2Var.f2336g = objArr == true ? 1 : 0;
                t2Var.f2341l = arrayList;
                t2Var.f2342m = map;
                t2Var.f2343n = rVar;
                return;
            }
        }
        w2 w2VarD = t2Var.d();
        try {
            l.r(w2VarD, i5, this, true, true, false);
            boolean z3 = true;
        } finally {
            w2VarD.e(z2);
        }
    }

    public final void B(int i5) {
        a aVar;
        int i6;
        a aVar2;
        int i7;
        int i8;
        int i9 = this.f2397h;
        int i10 = this.f2396g;
        if (i10 != i5) {
            if (!this.f2393d.isEmpty()) {
                int iO = o() - this.f2397h;
                if (i10 < i5) {
                    for (int iB = v2.b(this.f2393d, i10, iO); iB < this.f2393d.size() && (i7 = (aVar2 = (a) this.f2393d.get(iB)).f2082a) < 0 && (i8 = i7 + iO) < i5; iB++) {
                        aVar2.f2082a = i8;
                    }
                } else {
                    for (int iB2 = v2.b(this.f2393d, i5, iO); iB2 < this.f2393d.size() && (i6 = (aVar = (a) this.f2393d.get(iB2)).f2082a) >= 0; iB2++) {
                        aVar.f2082a = -(iO - i6);
                    }
                }
            }
            if (i9 > 0) {
                int[] iArr = this.f2391b;
                int i11 = i5 * 5;
                int i12 = i9 * 5;
                int i13 = i10 * 5;
                if (i5 < i10) {
                    q3.k.y(iArr, iArr, i12 + i11, i11, i13);
                } else {
                    q3.k.y(iArr, iArr, i13, i13 + i12, i11 + i12);
                }
            }
            if (i5 < i10) {
                i10 = i5 + i9;
            }
            int iO2 = o();
            if (i10 >= iO2) {
                z.a("Check failed");
            }
            while (i10 < iO2) {
                int i14 = (i10 * 5) + 2;
                int i15 = this.f2391b[i14];
                int iP = i15 > -2 ? i15 : (p() + i15) - (-2);
                if (iP >= i5) {
                    iP = -((p() - iP) - (-2));
                }
                if (iP != i15) {
                    this.f2391b[i14] = iP;
                }
                i10++;
                if (i10 == i5) {
                    i10 += i9;
                }
            }
        }
        this.f2396g = i5;
    }

    public final void C(int i5, int i6) {
        int i7 = this.f2401l;
        int i8 = this.f2400k;
        int i9 = this.f2402m;
        if (i8 != i5) {
            Object[] objArr = this.f2392c;
            if (i5 < i8) {
                System.arraycopy(objArr, i5, objArr, i5 + i7, i8 - i5);
            } else {
                int i10 = i8 + i7;
                System.arraycopy(objArr, i10, objArr, i8, (i5 + i7) - i10);
            }
        }
        int iMin = Math.min(i6 + 1, p());
        if (i9 != iMin) {
            int length = this.f2392c.length - i7;
            if (iMin < i9) {
                int iR = r(iMin);
                int iR2 = r(i9);
                int i11 = this.f2396g;
                while (iR < iR2) {
                    int i12 = (iR * 5) + 4;
                    int i13 = this.f2391b[i12];
                    if (!(i13 >= 0)) {
                        z.a("Unexpected anchor value, expected a positive anchor");
                    }
                    this.f2391b[i12] = -((length - i13) + 1);
                    iR++;
                    if (iR == i11) {
                        iR += this.f2397h;
                    }
                }
            } else {
                int iR3 = r(i9);
                int iR4 = r(iMin);
                while (iR3 < iR4) {
                    int i14 = (iR3 * 5) + 4;
                    int i15 = this.f2391b[i14];
                    if (!(i15 < 0)) {
                        z.a("Unexpected anchor value, expected a negative anchor");
                    }
                    this.f2391b[i14] = i15 + length + 1;
                    iR3++;
                    if (iR3 == this.f2396g) {
                        iR3 += this.f2397h;
                    }
                }
            }
            this.f2402m = iMin;
        }
        this.f2400k = i5;
    }

    public final Object D(int i5) {
        int iR = r(i5);
        int[] iArr = this.f2391b;
        if ((iArr[(iR * 5) + 1] & 1073741824) != 0) {
            return this.f2392c[h(g(iArr, iR))];
        }
        return null;
    }

    public final int E(int[] iArr, int i5) {
        int i6 = iArr[(r(i5) * 5) + 2];
        return i6 > -2 ? i6 : (p() + i6) - (-2);
    }

    public final Object F(Object obj) {
        if (this.f2403n > 0) {
            x(1, this.f2411v);
        }
        Object[] objArr = this.f2392c;
        int i5 = this.f2398i;
        this.f2398i = i5 + 1;
        Object obj2 = objArr[h(i5)];
        if (this.f2398i > this.f2399j) {
            z.a("Writing to an invalid slot");
        }
        this.f2392c[h(this.f2398i - 1)] = obj;
        return obj2;
    }

    public final void G() {
        int i5;
        j.q qVar = this.f2413x;
        if (qVar != null) {
            while (qVar.f3137b != 0) {
                int iY = l.y(qVar);
                int iR = r(iY);
                int iU = iY + 1;
                int iU2 = u(iY) + iY;
                while (true) {
                    if (iU >= iU2) {
                        i5 = 0;
                        break;
                    } else {
                        if ((this.f2391b[(r(iU) * 5) + 1] & 201326592) != 0) {
                            i5 = 1;
                            break;
                        }
                        iU += u(iU);
                    }
                }
                int[] iArr = this.f2391b;
                int i6 = (iR * 5) + 1;
                int i7 = iArr[i6];
                if (((67108864 & i7) != 0 ? 1 : 0) != i5) {
                    iArr[i6] = (i5 << 26) | ((-67108865) & i7);
                    int iE = E(iArr, iY);
                    if (iE >= 0) {
                        l.i(qVar, iE);
                    }
                }
            }
        }
    }

    public final boolean H() {
        if (!(this.f2403n == 0)) {
            z.a("Cannot remove group while inserting");
        }
        int i5 = this.f2409t;
        int i6 = this.f2398i;
        int iG = g(this.f2391b, r(i5));
        int iL = L();
        O(this.f2411v);
        j.q qVar = this.f2413x;
        if (qVar != null) {
            while (true) {
                int i7 = qVar.f3137b;
                if (i7 == 0) {
                    break;
                }
                if (i7 == 0) {
                    k.a.e("IntList is empty.");
                    throw null;
                }
                if (qVar.f3136a[0] < i5) {
                    break;
                }
                l.y(qVar);
            }
        }
        boolean zI = I(i5, this.f2409t - i5);
        J(iG, this.f2398i - iG, i5 - 1);
        this.f2409t = i5;
        this.f2398i = i6;
        this.f2404o -= iL;
        return zI;
    }

    public final boolean I(int i5, int i6) {
        boolean z2 = false;
        if (i6 > 0) {
            ArrayList arrayList = this.f2393d;
            B(i5);
            if (!arrayList.isEmpty()) {
                HashMap map = this.f2394e;
                int i7 = i5 + i6;
                int iB = v2.b(this.f2393d, i7, o() - this.f2397h);
                if (iB >= this.f2393d.size()) {
                    iB--;
                }
                int i8 = iB + 1;
                int i9 = 0;
                while (iB >= 0) {
                    a aVar = (a) this.f2393d.get(iB);
                    int iC = c(aVar);
                    if (iC < i5) {
                        break;
                    }
                    if (iC < i7) {
                        aVar.f2082a = Integer.MIN_VALUE;
                        if (map != null) {
                        }
                        if (i9 == 0) {
                            i9 = iB + 1;
                        }
                        i8 = iB;
                    }
                    iB--;
                }
                z2 = i8 < i9;
                if (z2) {
                    this.f2393d.subList(i8, i9).clear();
                }
            }
            this.f2396g = i5;
            this.f2397h += i6;
            int i10 = this.f2402m;
            if (i10 > i5) {
                this.f2402m = Math.max(i5, i10 - i6);
            }
            int i11 = this.f2410u;
            if (i11 >= this.f2396g) {
                this.f2410u = i11 - i6;
            }
            int i12 = this.f2411v;
            if (i12 >= 0 && (this.f2391b[(r(i12) * 5) + 1] & 67108864) != 0) {
                T(i12);
            }
        }
        return z2;
    }

    public final void J(int i5, int i6, int i7) {
        if (i6 > 0) {
            int i8 = this.f2401l;
            int i9 = i5 + i6;
            C(i9, i7);
            this.f2400k = i5;
            this.f2401l = i8 + i6;
            q3.k.E(this.f2392c, i5, i9);
            int i10 = this.f2399j;
            if (i10 >= i5) {
                this.f2399j = i10 - i6;
            }
        }
    }

    public final Object K(int i5, int i6, Object obj) {
        int iN = N(this.f2391b, r(i5));
        int iG = g(this.f2391b, r(i5 + 1));
        int i7 = iN + i6;
        if (i7 < iN || i7 >= iG) {
            z.a("Write to an invalid slot index " + i6 + " for group " + i5);
        }
        int iH = h(i7);
        Object[] objArr = this.f2392c;
        Object obj2 = objArr[iH];
        objArr[iH] = obj;
        return obj2;
    }

    public final int L() {
        int iR = r(this.f2409t);
        int iA = v2.a(this.f2391b, iR) + this.f2409t;
        this.f2409t = iA;
        this.f2398i = g(this.f2391b, r(iA));
        int i5 = this.f2391b[(iR * 5) + 1];
        if ((1073741824 & i5) != 0) {
            return 1;
        }
        return i5 & 67108863;
    }

    public final void M() {
        int i5 = this.f2410u;
        this.f2409t = i5;
        this.f2398i = g(this.f2391b, r(i5));
    }

    public final int N(int[] iArr, int i5) {
        if (i5 >= o()) {
            return this.f2392c.length - this.f2401l;
        }
        int iC = v2.c(iArr, i5);
        return iC < 0 ? (this.f2392c.length - this.f2401l) + iC + 1 : iC;
    }

    public final v0 O(int i5) {
        a aVarR;
        HashMap map = this.f2394e;
        if (map == null || (aVarR = R(i5)) == null) {
            return null;
        }
        return (v0) map.get(aVarR);
    }

    public final void P() {
        if (this.f2403n != 0) {
            z.a("Key must be supplied when inserting");
        }
        h hVar = o.f2281a;
        Q(0, hVar, false, hVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Q(int i5, Object obj, boolean z2, Object obj2) {
        int i6;
        int i7 = this.f2411v;
        Object[] objArr = this.f2403n > 0;
        this.f2407r.c(this.f2404o);
        h hVar = o.f2281a;
        if (objArr == true) {
            int i8 = this.f2409t;
            int iG = g(this.f2391b, r(i8));
            w(1);
            this.f2398i = iG;
            this.f2399j = iG;
            int iR = r(i8);
            int i9 = obj != hVar ? 1 : 0;
            int i10 = (z2 || obj2 == hVar) ? 0 : 1;
            int i11 = i(iG, this.f2400k, this.f2401l, this.f2392c.length);
            if (i11 >= 0 && this.f2402m < i8) {
                i11 = -(((this.f2392c.length - this.f2401l) - i11) + 1);
            }
            int[] iArr = this.f2391b;
            int i12 = this.f2411v;
            int i13 = iR * 5;
            iArr[i13] = i5;
            iArr[i13 + 1] = ((z2 ? 1 : 0) << 30) | (i9 << 29) | (i10 << 28);
            iArr[i13 + 2] = i12;
            iArr[i13 + 3] = 0;
            iArr[i13 + 4] = i11;
            int i14 = (z2 ? 1 : 0) + i9 + i10;
            if (i14 > 0) {
                x(i14, i8);
                Object[] objArr2 = this.f2392c;
                int i15 = this.f2398i;
                if (z2) {
                    objArr2[i15] = obj2;
                    i15++;
                }
                if (i9 != 0) {
                    objArr2[i15] = obj;
                    i15++;
                }
                if (i10 != 0) {
                    objArr2[i15] = obj2;
                    i15++;
                }
                this.f2398i = i15;
            }
            this.f2404o = 0;
            i6 = i8 + 1;
            this.f2411v = i8;
            this.f2409t = i6;
            if (i7 >= 0) {
                O(i7);
            }
        } else {
            this.f2405p.c(i7);
            this.f2406q.c((o() - this.f2397h) - this.f2410u);
            int i16 = this.f2409t;
            int iR2 = r(i16);
            if (!b4.l.a(obj2, hVar)) {
                if (z2) {
                    U(this.f2409t, obj2);
                } else {
                    S(obj2);
                }
            }
            this.f2398i = N(this.f2391b, iR2);
            this.f2399j = g(this.f2391b, r(this.f2409t + 1));
            int[] iArr2 = this.f2391b;
            int i17 = iR2 * 5;
            this.f2404o = iArr2[i17 + 1] & 67108863;
            this.f2411v = i16;
            this.f2409t = i16 + 1;
            i6 = i16 + iArr2[i17 + 3];
        }
        this.f2410u = i6;
    }

    public final a R(int i5) {
        ArrayList arrayList;
        int iE;
        if (i5 < 0 || i5 >= p() || (iE = v2.e((arrayList = this.f2393d), i5, p())) < 0) {
            return null;
        }
        return (a) arrayList.get(iE);
    }

    public final void S(Object obj) {
        int iR = r(this.f2409t);
        int i5 = (iR * 5) + 1;
        if ((this.f2391b[i5] & 268435456) == 0) {
            z.a("Updating the data of a group that was not created with a data slot");
        }
        Object[] objArr = this.f2392c;
        int[] iArr = this.f2391b;
        objArr[h(Integer.bitCount(iArr[i5] >> 29) + g(iArr, iR))] = obj;
    }

    public final void T(int i5) {
        if (i5 >= 0) {
            j.q qVar = this.f2413x;
            if (qVar == null) {
                qVar = new j.q();
                this.f2413x = qVar;
            }
            l.i(qVar, i5);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    public final void U(int i5, Object obj) {
        boolean z2;
        int iR = r(i5);
        int[] iArr = this.f2391b;
        if (iR < iArr.length) {
            z2 = (iArr[(iR * 5) + 1] & 1073741824) != 0;
        }
        if (!z2) {
            z.a("Updating the node of a group at " + i5 + " that was not created with as a node group");
        }
        this.f2392c[h(g(this.f2391b, iR))] = obj;
    }

    public final void a(int i5) {
        boolean z2 = false;
        if (!(i5 >= 0)) {
            z.a("Cannot seek backwards");
        }
        if (!(this.f2403n <= 0)) {
            x1.b("Cannot call seek() while inserting");
        }
        if (i5 == 0) {
            return;
        }
        int i6 = this.f2409t + i5;
        if (i6 >= this.f2411v && i6 <= this.f2410u) {
            z2 = true;
        }
        if (!z2) {
            z.a("Cannot seek outside the current group (" + this.f2411v + '-' + this.f2410u + ')');
        }
        this.f2409t = i6;
        int iG = g(this.f2391b, r(i6));
        this.f2398i = iG;
        this.f2399j = iG;
    }

    public final a b(int i5) {
        ArrayList arrayList = this.f2393d;
        int iE = v2.e(arrayList, i5, p());
        if (iE >= 0) {
            return (a) arrayList.get(iE);
        }
        if (i5 > this.f2396g) {
            i5 = -(p() - i5);
        }
        a aVar = new a(i5);
        arrayList.add(-(iE + 1), aVar);
        return aVar;
    }

    public final int c(a aVar) {
        int i5 = aVar.f2082a;
        return i5 < 0 ? p() + i5 : i5;
    }

    public final void d() {
        int i5 = this.f2403n;
        this.f2403n = i5 + 1;
        if (i5 == 0) {
            this.f2406q.c((o() - this.f2397h) - this.f2410u);
        }
    }

    public final void e(boolean z2) {
        this.f2412w = true;
        if (z2 && this.f2405p.f2389b == 0) {
            B(p());
            C(this.f2392c.length - this.f2401l, this.f2396g);
            int i5 = this.f2400k;
            q3.k.E(this.f2392c, i5, this.f2401l + i5);
            G();
        }
        int[] iArr = this.f2391b;
        int i6 = this.f2396g;
        Object[] objArr = this.f2392c;
        int i7 = this.f2400k;
        ArrayList arrayList = this.f2393d;
        HashMap map = this.f2394e;
        j.r rVar = this.f2395f;
        t2 t2Var = this.f2390a;
        if (!t2Var.f2339j) {
            x1.a("Unexpected writer close()");
        }
        t2Var.f2339j = false;
        t2Var.f2333d = iArr;
        t2Var.f2334e = i6;
        t2Var.f2335f = objArr;
        t2Var.f2336g = i7;
        t2Var.f2341l = arrayList;
        t2Var.f2342m = map;
        t2Var.f2343n = rVar;
    }

    public final int f(int i5) {
        return g(this.f2391b, r(i5));
    }

    public final int g(int[] iArr, int i5) {
        if (i5 >= o()) {
            return this.f2392c.length - this.f2401l;
        }
        int i6 = iArr[(i5 * 5) + 4];
        return i6 < 0 ? (this.f2392c.length - this.f2401l) + i6 + 1 : i6;
    }

    public final int h(int i5) {
        return (this.f2401l * (i5 < this.f2400k ? 0 : 1)) + i5;
    }

    public final void j() {
        j.y yVar;
        boolean z2 = this.f2403n > 0;
        int i5 = this.f2409t;
        int i6 = this.f2410u;
        int i7 = this.f2411v;
        int iR = r(i7);
        int i8 = this.f2404o;
        int i9 = i5 - i7;
        int i10 = iR * 5;
        int i11 = i10 + 1;
        boolean z3 = (this.f2391b[i11] & 1073741824) != 0;
        w0 w0Var = this.f2407r;
        if (z2) {
            j.r rVar = this.f2408s;
            if (rVar != null && (yVar = (j.y) rVar.d(i7)) != null) {
                Object[] objArr = yVar.f3174a;
                int i12 = yVar.f3175b;
                for (int i13 = 0; i13 < i12; i13++) {
                    F(objArr[i13]);
                }
            }
            int[] iArr = this.f2391b;
            iArr[i10 + 3] = i9;
            v2.d(iArr, iR, i8);
            int iB = w0Var.b();
            if (z3) {
                i8 = 1;
            }
            this.f2404o = iB + i8;
            int iE = E(this.f2391b, i7);
            this.f2411v = iE;
            int iP = iE < 0 ? p() : r(iE + 1);
            int iG = iP >= 0 ? g(this.f2391b, iP) : 0;
            this.f2398i = iG;
            this.f2399j = iG;
            return;
        }
        if (i5 != i6) {
            z.a("Expected to be at the end of a group");
        }
        int[] iArr2 = this.f2391b;
        int i14 = i10 + 3;
        int i15 = iArr2[i14];
        int i16 = iArr2[i11] & 67108863;
        iArr2[i14] = i9;
        v2.d(iArr2, iR, i8);
        int iB2 = this.f2405p.b();
        this.f2410u = (o() - this.f2397h) - this.f2406q.b();
        this.f2411v = iB2;
        int iE2 = E(this.f2391b, i7);
        int iB3 = w0Var.b();
        this.f2404o = iB3;
        if (iE2 == iB2) {
            this.f2404o = iB3 + (z3 ? 0 : i8 - i16);
            return;
        }
        int i17 = i9 - i15;
        int i18 = z3 ? 0 : i8 - i16;
        if (i17 != 0 || i18 != 0) {
            while (iE2 != 0 && iE2 != iB2 && (i18 != 0 || i17 != 0)) {
                int iR2 = r(iE2);
                if (i17 != 0) {
                    int[] iArr3 = this.f2391b;
                    int i19 = (iR2 * 5) + 3;
                    iArr3[i19] = iArr3[i19] + i17;
                }
                if (i18 != 0) {
                    int[] iArr4 = this.f2391b;
                    v2.d(iArr4, iR2, (iArr4[(iR2 * 5) + 1] & 67108863) + i18);
                }
                int[] iArr5 = this.f2391b;
                if ((iArr5[(iR2 * 5) + 1] & 1073741824) != 0) {
                    i18 = 0;
                }
                iE2 = E(iArr5, iE2);
            }
        }
        this.f2404o += i18;
    }

    public final void k() {
        if (this.f2403n <= 0) {
            x1.b("Unbalanced begin/end insert");
        }
        int i5 = this.f2403n - 1;
        this.f2403n = i5;
        if (i5 == 0) {
            if (this.f2407r.f2389b != this.f2405p.f2389b) {
                z.a("startGroup/endGroup mismatch while inserting");
            }
            this.f2410u = (o() - this.f2397h) - this.f2406q.b();
        }
    }

    public final void l(int i5) {
        boolean z2 = false;
        if (!(this.f2403n <= 0)) {
            z.a("Cannot call ensureStarted() while inserting");
        }
        int i6 = this.f2411v;
        if (i6 != i5) {
            if (i5 >= i6 && i5 < this.f2410u) {
                z2 = true;
            }
            if (!z2) {
                z.a("Started group at " + i5 + " must be a subgroup of the group at " + i6);
            }
            int i7 = this.f2409t;
            int i8 = this.f2398i;
            int i9 = this.f2399j;
            this.f2409t = i5;
            P();
            this.f2409t = i7;
            this.f2398i = i8;
            this.f2399j = i9;
        }
    }

    public final void m(int i5, int i6, int i7) {
        if (i5 >= this.f2396g) {
            i5 = -((p() - i5) + 2);
        }
        while (i7 < i6) {
            this.f2391b[(r(i7) * 5) + 2] = i5;
            int i8 = this.f2391b[(r(i7) * 5) + 3] + i7;
            m(i7, i8, i7 + 1);
            i7 = i8;
        }
    }

    public final void n(int i5, a4.e eVar) {
        int i6;
        int i7;
        int i8;
        int i9;
        int iE = E(this.f2391b, i5);
        int iP = p();
        int iU = u(i5) + i5;
        int i10 = i5;
        j.s sVar = null;
        j.q qVar = null;
        while (i10 < iU) {
            int iF = f(i10);
            int i11 = i10 + 1;
            int iF2 = f(i11);
            while (iF < iF2) {
                Object obj = this.f2392c[h(iF)];
                if (!(obj instanceof o2) || (i9 = ((o2) obj).f2286b) < 0) {
                    i8 = iE;
                    eVar.invoke(Integer.valueOf(iF), obj);
                } else {
                    int iU2 = u(i10) + i10;
                    int i12 = i11;
                    int i13 = 0;
                    while (i12 < iU2 && i13 < i9) {
                        int iR = r(i12);
                        int i14 = iE;
                        int[] iArr = this.f2391b;
                        int i15 = iR * 5;
                        i12 = iArr[i15 + 3] + i12;
                        if (i12 < iU2 && (iArr[i15 + 1] & 536870912) == 0) {
                            i13++;
                        }
                        iE = i14;
                    }
                    i8 = iE;
                    if (sVar == null) {
                        int[] iArr2 = j.k.f3116a;
                        sVar = new j.s();
                    }
                    if (qVar == null) {
                        qVar = new j.q();
                    }
                    sVar.a(i12);
                    qVar.a(i12);
                    qVar.a(iF);
                }
                iF++;
                iE = i8;
            }
            int i16 = iE;
            iE = i11 < iP ? E(this.f2391b, i11) : -1;
            if (iE != i10) {
                int iE2 = i16;
                while (true) {
                    if (qVar == null || sVar == null || !sVar.f(i10)) {
                        i6 = iP;
                    } else {
                        int i17 = qVar.f3137b;
                        int i18 = i17 / 2;
                        int i19 = 0;
                        int i20 = 0;
                        while (i19 < i18) {
                            int i21 = i19 * 2;
                            int i22 = iP;
                            int iC = qVar.c(i21);
                            if (iC == i10) {
                                int iC2 = qVar.c(i21 + 1);
                                eVar.invoke(Integer.valueOf(iC2), this.f2392c[h(iC2)]);
                            } else if (i21 != i20) {
                                int i23 = i20 + 1;
                                qVar.d(i20, iC);
                                i20 += 2;
                                qVar.d(i23, qVar.c(i21 + 1));
                            } else {
                                i20 += 2;
                            }
                            i19++;
                            eVar = eVar;
                            iP = i22;
                        }
                        i6 = iP;
                        if (i20 != i17) {
                            if (i20 < 0 || i20 > (i7 = qVar.f3137b) || i17 < 0 || i17 > i7) {
                                k.a.d("Index must be between 0 and size");
                                throw null;
                            }
                            if (i17 < i20) {
                                k.a.c("The end index must be < start index");
                                throw null;
                            }
                            if (i17 != i20) {
                                if (i17 < i7) {
                                    int[] iArr3 = qVar.f3136a;
                                    q3.k.y(iArr3, iArr3, i20, i17, i7);
                                }
                                qVar.f3137b -= i17 - i20;
                            }
                        }
                    }
                    if (i10 == i5 || iE2 == iE) {
                        break;
                    }
                    i10 = iE2;
                    iP = i6;
                    iE2 = E(this.f2391b, iE2);
                    eVar = eVar;
                }
            } else {
                i6 = iP;
            }
            i10 = i11;
            iP = i6;
        }
    }

    public final int o() {
        return this.f2391b.length / 5;
    }

    public final int p() {
        return o() - this.f2397h;
    }

    public final Object q(int i5) {
        int iR = r(i5);
        int[] iArr = this.f2391b;
        int i6 = (iR * 5) + 1;
        if ((iArr[i6] & 268435456) == 0) {
            return o.f2281a;
        }
        return this.f2392c[Integer.bitCount(iArr[i6] >> 29) + g(iArr, iR)];
    }

    public final int r(int i5) {
        return (this.f2397h * (i5 < this.f2396g ? 0 : 1)) + i5;
    }

    public final int s(int i5) {
        return this.f2391b[r(i5) * 5];
    }

    public final Object t(int i5) {
        int iR = r(i5);
        int[] iArr = this.f2391b;
        int i6 = iR * 5;
        int i7 = iArr[i6 + 1];
        if ((536870912 & i7) == 0) {
            return null;
        }
        return this.f2392c[Integer.bitCount(i7 >> 30) + iArr[i6 + 4]];
    }

    public final String toString() {
        return "SlotWriter(current = " + this.f2409t + " end=" + this.f2410u + " size = " + p() + " gap=" + this.f2396g + '-' + (this.f2396g + this.f2397h) + ')';
    }

    public final int u(int i5) {
        return v2.a(this.f2391b, r(i5));
    }

    public final boolean v(int i5, int i6) {
        int iO;
        int iU;
        if (i6 == this.f2411v) {
            iO = this.f2410u;
        } else {
            w0 w0Var = this.f2405p;
            if (i6 > w0Var.a(0)) {
                iU = u(i6);
            } else {
                int[] iArr = w0Var.f2388a;
                int iMin = Math.min(iArr.length, w0Var.f2389b);
                int i7 = 0;
                while (true) {
                    if (i7 >= iMin) {
                        i7 = -1;
                        break;
                    }
                    if (iArr[i7] == i6) {
                        break;
                    }
                    i7++;
                }
                if (i7 < 0) {
                    iU = u(i6);
                } else {
                    iO = (o() - this.f2397h) - this.f2406q.f2388a[i7];
                }
            }
            iO = iU + i6;
        }
        return i5 > i6 && i5 < iO;
    }

    public final void w(int i5) {
        if (i5 > 0) {
            int i6 = this.f2409t;
            B(i6);
            int i7 = this.f2396g;
            int i8 = this.f2397h;
            int[] iArr = this.f2391b;
            int length = iArr.length / 5;
            int i9 = length - i8;
            if (i8 < i5) {
                int iMax = Math.max(Math.max(length * 2, i9 + i5), 32);
                int[] iArr2 = new int[iMax * 5];
                int i10 = iMax - i9;
                q3.k.y(iArr, iArr2, 0, 0, i7 * 5);
                q3.k.y(iArr, iArr2, (i7 + i10) * 5, (i8 + i7) * 5, length * 5);
                this.f2391b = iArr2;
                i8 = i10;
            }
            int i11 = this.f2410u;
            if (i11 >= i7) {
                this.f2410u = i11 + i5;
            }
            int i12 = i7 + i5;
            this.f2396g = i12;
            this.f2397h = i8 - i5;
            int i13 = i(i9 > 0 ? f(i6 + i5) : 0, this.f2402m >= i7 ? this.f2400k : 0, this.f2401l, this.f2392c.length);
            for (int i14 = i7; i14 < i12; i14++) {
                this.f2391b[(i14 * 5) + 4] = i13;
            }
            int i15 = this.f2402m;
            if (i15 >= i7) {
                this.f2402m = i15 + i5;
            }
        }
    }

    public final void x(int i5, int i6) {
        if (i5 > 0) {
            C(this.f2398i, i6);
            int i7 = this.f2400k;
            int i8 = this.f2401l;
            if (i8 < i5) {
                Object[] objArr = this.f2392c;
                int length = objArr.length;
                int i9 = length - i8;
                int iMax = Math.max(Math.max(length * 2, i9 + i5), 32);
                Object[] objArr2 = new Object[iMax];
                for (int i10 = 0; i10 < iMax; i10++) {
                    objArr2[i10] = null;
                }
                int i11 = iMax - i9;
                int i12 = i8 + i7;
                System.arraycopy(objArr, 0, objArr2, 0, i7);
                System.arraycopy(objArr, i12, objArr2, i7 + i11, length - i12);
                this.f2392c = objArr2;
                i8 = i11;
            }
            int i13 = this.f2399j;
            if (i13 >= i7) {
                this.f2399j = i13 + i5;
            }
            this.f2400k = i7 + i5;
            this.f2401l = i8 - i5;
        }
    }

    public final boolean y(int i5) {
        return (this.f2391b[(r(i5) * 5) + 1] & 1073741824) != 0;
    }
}
