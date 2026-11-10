package j0;

import b4.l;
import f0.x1;
import java.util.Arrays;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object[] f3189d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object[] f3190e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f3191f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3192g;

    public f(Object[] objArr, Object[] objArr2, int i5, int i6) {
        this.f3189d = objArr;
        this.f3190e = objArr2;
        this.f3191f = i5;
        this.f3192g = i6;
        if (!(a() > 32)) {
            x1.a("Trie-based persistent vector should have at least 33 elements, got " + a());
        }
        int length = objArr2.length;
    }

    public static Object[] i(Object[] objArr, int i5, int i6, Object obj, e eVar) {
        Object[] objArrCopyOf;
        int iX = d4.a.x(i6, i5);
        if (i5 == 0) {
            if (iX == 0) {
                objArrCopyOf = new Object[32];
            } else {
                objArrCopyOf = Arrays.copyOf(objArr, 32);
                l.d("copyOf(...)", objArrCopyOf);
            }
            q3.k.A(objArr, objArrCopyOf, iX + 1, iX, 31);
            eVar.f3188a = objArr[31];
            objArrCopyOf[iX] = obj;
            return objArrCopyOf;
        }
        Object[] objArrCopyOf2 = Arrays.copyOf(objArr, 32);
        l.d("copyOf(...)", objArrCopyOf2);
        int i7 = i5 - 5;
        Object obj2 = objArr[iX];
        l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj2);
        objArrCopyOf2[iX] = i((Object[]) obj2, i7, i6, obj, eVar);
        while (true) {
            iX++;
            if (iX >= 32 || objArrCopyOf2[iX] == null) {
                break;
            }
            Object obj3 = objArr[iX];
            l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj3);
            objArrCopyOf2[iX] = i((Object[]) obj3, i7, 0, eVar.f3188a, eVar);
        }
        return objArrCopyOf2;
    }

    public static Object[] k(Object[] objArr, int i5, int i6, e eVar) {
        Object[] objArrK;
        int iX = d4.a.x(i6, i5);
        if (i5 == 5) {
            eVar.f3188a = objArr[iX];
            objArrK = null;
        } else {
            Object obj = objArr[iX];
            l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj);
            objArrK = k((Object[]) obj, i5 - 5, i6, eVar);
        }
        if (objArrK == null && iX == 0) {
            return null;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, 32);
        l.d("copyOf(...)", objArrCopyOf);
        objArrCopyOf[iX] = objArrK;
        return objArrCopyOf;
    }

    public static Object[] q(Object[] objArr, int i5, int i6, Object obj) {
        int iX = d4.a.x(i6, i5);
        Object[] objArrCopyOf = Arrays.copyOf(objArr, 32);
        l.d("copyOf(...)", objArrCopyOf);
        if (i5 == 0) {
            objArrCopyOf[iX] = obj;
            return objArrCopyOf;
        }
        Object obj2 = objArrCopyOf[iX];
        l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj2);
        objArrCopyOf[iX] = q((Object[]) obj2, i5 - 5, i6, obj);
        return objArrCopyOf;
    }

    @Override // q3.a
    public final int a() {
        return this.f3191f;
    }

    @Override // j0.c
    public final c b(int i5, Object obj) {
        int i6 = this.f3191f;
        a.a.n(i5, i6);
        if (i5 == i6) {
            return c(obj);
        }
        int iP = p();
        Object[] objArr = this.f3189d;
        if (i5 >= iP) {
            return j(objArr, i5 - iP, obj);
        }
        e eVar = new e(null);
        return j(i(objArr, this.f3192g, i5, obj, eVar), 0, eVar.f3188a);
    }

    @Override // j0.c
    public final c c(Object obj) {
        int iP = p();
        int i5 = this.f3191f;
        int i6 = i5 - iP;
        Object[] objArr = this.f3189d;
        Object[] objArr2 = this.f3190e;
        if (i6 >= 32) {
            Object[] objArr3 = new Object[32];
            objArr3[0] = obj;
            return l(objArr, objArr2, objArr3);
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr2, 32);
        l.d("copyOf(...)", objArrCopyOf);
        objArrCopyOf[i6] = obj;
        return new f(objArr, objArrCopyOf, i5 + 1, this.f3192g);
    }

    @Override // j0.c
    public final g e() {
        return new g(this, this.f3189d, this.f3190e, this.f3192g);
    }

    @Override // j0.c
    public final c f(b bVar) {
        g gVar = new g(this, this.f3189d, this.f3190e, this.f3192g);
        gVar.y(bVar);
        return gVar.c();
    }

    @Override // j0.c
    public final c g(int i5) {
        a.a.m(i5, this.f3191f);
        int iP = p();
        Object[] objArr = this.f3189d;
        int i6 = this.f3192g;
        return i5 >= iP ? o(objArr, iP, i6, i5 - iP) : o(n(objArr, i6, i5, new e(this.f3190e[0])), iP, i6, 0);
    }

    @Override // java.util.List
    public final Object get(int i5) {
        Object[] objArr;
        a.a.m(i5, a());
        if (p() <= i5) {
            objArr = this.f3190e;
        } else {
            objArr = this.f3189d;
            for (int i6 = this.f3192g; i6 > 0; i6 -= 5) {
                Object obj = objArr[d4.a.x(i5, i6)];
                l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj);
                objArr = (Object[]) obj;
            }
        }
        return objArr[i5 & 31];
    }

    @Override // j0.c
    public final c h(int i5, Object obj) {
        int i6 = this.f3191f;
        a.a.m(i5, i6);
        int iP = p();
        Object[] objArr = this.f3189d;
        Object[] objArr2 = this.f3190e;
        int i7 = this.f3192g;
        if (iP > i5) {
            return new f(q(objArr, i7, i5, obj), objArr2, i6, i7);
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr2, 32);
        l.d("copyOf(...)", objArrCopyOf);
        objArrCopyOf[i5 & 31] = obj;
        return new f(objArr, objArrCopyOf, i6, i7);
    }

    public final f j(Object[] objArr, int i5, Object obj) {
        int iP = p();
        int i6 = this.f3191f;
        int i7 = i6 - iP;
        Object[] objArr2 = this.f3190e;
        Object[] objArrCopyOf = Arrays.copyOf(objArr2, 32);
        l.d("copyOf(...)", objArrCopyOf);
        if (i7 < 32) {
            q3.k.A(objArr2, objArrCopyOf, i5 + 1, i5, i7);
            objArrCopyOf[i5] = obj;
            return new f(objArr, objArrCopyOf, i6 + 1, this.f3192g);
        }
        Object obj2 = objArr2[31];
        q3.k.A(objArr2, objArrCopyOf, i5 + 1, i5, i7 - 1);
        objArrCopyOf[i5] = obj;
        Object[] objArr3 = new Object[32];
        objArr3[0] = obj2;
        return l(objArr, objArrCopyOf, objArr3);
    }

    public final f l(Object[] objArr, Object[] objArr2, Object[] objArr3) {
        int i5 = this.f3191f;
        int i6 = i5 >> 5;
        int i7 = this.f3192g;
        if (i6 <= (1 << i7)) {
            return new f(m(i7, objArr, objArr2), objArr3, i5 + 1, i7);
        }
        Object[] objArr4 = new Object[32];
        objArr4[0] = objArr;
        int i8 = i7 + 5;
        return new f(m(i8, objArr4, objArr2), objArr3, i5 + 1, i8);
    }

    @Override // q3.d, java.util.List
    public final ListIterator listIterator(int i5) {
        a.a.n(i5, this.f3191f);
        return new h(this.f3189d, this.f3190e, i5, this.f3191f, (this.f3192g / 5) + 1);
    }

    public final Object[] m(int i5, Object[] objArr, Object[] objArr2) {
        Object[] objArrCopyOf;
        int iX = d4.a.x(a() - 1, i5);
        if (objArr != null) {
            objArrCopyOf = Arrays.copyOf(objArr, 32);
            l.d("copyOf(...)", objArrCopyOf);
        } else {
            objArrCopyOf = new Object[32];
        }
        if (i5 == 5) {
            objArrCopyOf[iX] = objArr2;
            return objArrCopyOf;
        }
        objArrCopyOf[iX] = m(i5 - 5, (Object[]) objArrCopyOf[iX], objArr2);
        return objArrCopyOf;
    }

    public final Object[] n(Object[] objArr, int i5, int i6, e eVar) {
        Object[] objArrCopyOf;
        int iX = d4.a.x(i6, i5);
        if (i5 == 0) {
            if (iX == 0) {
                objArrCopyOf = new Object[32];
            } else {
                objArrCopyOf = Arrays.copyOf(objArr, 32);
                l.d("copyOf(...)", objArrCopyOf);
            }
            q3.k.A(objArr, objArrCopyOf, iX, iX + 1, 32);
            objArrCopyOf[31] = eVar.f3188a;
            eVar.f3188a = objArr[iX];
            return objArrCopyOf;
        }
        int iX2 = objArr[31] == null ? d4.a.x(p() - 1, i5) : 31;
        Object[] objArrCopyOf2 = Arrays.copyOf(objArr, 32);
        l.d("copyOf(...)", objArrCopyOf2);
        int i7 = i5 - 5;
        int i8 = iX + 1;
        if (i8 <= iX2) {
            while (true) {
                Object obj = objArrCopyOf2[iX2];
                l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj);
                objArrCopyOf2[iX2] = n((Object[]) obj, i7, 0, eVar);
                if (iX2 == i8) {
                    break;
                }
                iX2--;
            }
        }
        Object obj2 = objArrCopyOf2[iX];
        l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj2);
        objArrCopyOf2[iX] = n((Object[]) obj2, i7, i6, eVar);
        return objArrCopyOf2;
    }

    public final c o(Object[] objArr, int i5, int i6, int i7) {
        int i8 = this.f3191f - i5;
        if (i8 != 1) {
            Object[] objArr2 = this.f3190e;
            Object[] objArrCopyOf = Arrays.copyOf(objArr2, 32);
            l.d("copyOf(...)", objArrCopyOf);
            int i9 = i8 - 1;
            if (i7 < i9) {
                q3.k.A(objArr2, objArrCopyOf, i7, i7 + 1, i8);
            }
            objArrCopyOf[i9] = null;
            return new f(objArr, objArrCopyOf, (i5 + i8) - 1, i6);
        }
        if (i6 == 0) {
            if (objArr.length == 33) {
                objArr = Arrays.copyOf(objArr, 32);
                l.d("copyOf(...)", objArr);
            }
            return new j(objArr);
        }
        e eVar = new e(null);
        Object[] objArrK = k(objArr, i6, i5 - 1, eVar);
        l.b(objArrK);
        Object obj = eVar.f3188a;
        l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj);
        Object[] objArr3 = (Object[]) obj;
        if (objArrK[1] != null) {
            return new f(objArrK, objArr3, i5, i6);
        }
        Object obj2 = objArrK[0];
        l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj2);
        return new f((Object[]) obj2, objArr3, i5, i6 - 5);
    }

    public final int p() {
        return (this.f3191f - 1) & (-32);
    }
}
