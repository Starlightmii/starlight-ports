package g0;

import f0.w2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k0 extends d4.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2488l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2490n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f2492p;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public i0[] f2487k = new i0[16];

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int[] f2489m = new int[16];

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Object[] f2491o = new Object[16];

    public final void O() {
        this.f2488l = 0;
        this.f2490n = 0;
        q3.k.E(this.f2491o, 0, this.f2492p);
        this.f2492p = 0;
    }

    public final void P(f0.c cVar, w2 w2Var, n0.j jVar, j0 j0Var) {
        if (R()) {
            e2.l lVar = new e2.l(this);
            k0 k0Var = (k0) lVar.f2036e;
            while (true) {
                i0 i0Var = k0Var.f2487k[lVar.f2033b];
                f0.a aVarB = i0Var.b(lVar);
                f0.c cVar2 = cVar;
                w2 w2Var2 = w2Var;
                n0.j jVar2 = jVar;
                j0 j0Var2 = j0Var;
                try {
                    i0Var.a(lVar, cVar2, w2Var2, jVar2, j0Var2);
                    int i5 = lVar.f2033b;
                    int i6 = k0Var.f2488l;
                    if (i5 < i6) {
                        i0 i0Var2 = k0Var.f2487k[i5];
                        lVar.f2034c += i0Var2.f2483a;
                        lVar.f2035d += i0Var2.f2484b;
                        int i7 = i5 + 1;
                        lVar.f2033b = i7;
                        if (i7 >= i6) {
                            break;
                        }
                        cVar = cVar2;
                        w2Var = w2Var2;
                        jVar = jVar2;
                        j0Var = j0Var2;
                    } else {
                        break;
                    }
                } catch (Throwable th) {
                    if (j0Var2 == null) {
                        throw th;
                    }
                    p3.p.D(th, new f0.q(aVarB, w2Var2, j0Var2, 1));
                    throw th;
                }
            }
        }
        O();
    }

    public final boolean Q() {
        return this.f2488l == 0;
    }

    public final boolean R() {
        return this.f2488l != 0;
    }

    public final void S(i0 i0Var) {
        int i5 = this.f2488l;
        i0[] i0VarArr = this.f2487k;
        if (i5 == i0VarArr.length) {
            i0[] i0VarArr2 = new i0[(i5 > 1024 ? 1024 : i5) + i5];
            System.arraycopy(i0VarArr, 0, i0VarArr2, 0, i5);
            this.f2487k = i0VarArr2;
        }
        int i6 = this.f2490n;
        int i7 = i0Var.f2483a;
        int i8 = i0Var.f2484b;
        int i9 = i6 + i7;
        int[] iArr = this.f2489m;
        int length = iArr.length;
        if (i9 > length) {
            int i10 = (length > 1024 ? 1024 : length) + length;
            if (i10 >= i9) {
                i9 = i10;
            }
            int[] iArr2 = new int[i9];
            q3.k.y(iArr, iArr2, 0, 0, length);
            this.f2489m = iArr2;
        }
        int i11 = this.f2492p + i8;
        Object[] objArr = this.f2491o;
        int length2 = objArr.length;
        if (i11 > length2) {
            int i12 = (length2 <= 1024 ? length2 : 1024) + length2;
            if (i12 >= i11) {
                i11 = i12;
            }
            Object[] objArr2 = new Object[i11];
            System.arraycopy(objArr, 0, objArr2, 0, length2);
            this.f2491o = objArr2;
        }
        i0[] i0VarArr3 = this.f2487k;
        int i13 = this.f2488l;
        this.f2488l = i13 + 1;
        i0VarArr3[i13] = i0Var;
        this.f2490n += i0Var.f2483a;
        this.f2492p += i8;
    }
}
