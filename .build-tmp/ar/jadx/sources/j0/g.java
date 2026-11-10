package j0;

import b4.l;
import f0.x1;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g extends q3.f implements Collection, c4.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f3193d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f3194e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object[] f3195f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3196g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public m0.b f3197h = new m0.b();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object[] f3198i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Object[] f3199j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3200k;

    public g(c cVar, Object[] objArr, Object[] objArr2, int i5) {
        this.f3193d = cVar;
        this.f3194e = objArr;
        this.f3195f = objArr2;
        this.f3196g = i5;
        this.f3198i = objArr;
        this.f3199j = objArr2;
        this.f3200k = cVar.a();
    }

    public static void d(Object[] objArr, int i5, Iterator it) {
        while (i5 < 32 && it.hasNext()) {
            objArr[i5] = it.next();
            i5++;
        }
    }

    public final Object A(Object[] objArr, int i5, int i6, int i7) {
        int i8 = this.f3200k - i5;
        if (i8 == 1) {
            Object obj = this.f3199j[0];
            q(objArr, i5, i6);
            return obj;
        }
        Object[] objArr2 = this.f3199j;
        Object obj2 = objArr2[i7];
        Object[] objArrK = k(objArr2);
        q3.k.A(objArr2, objArrK, i7, i7 + 1, i8);
        objArrK[i8 - 1] = null;
        this.f3198i = objArr;
        this.f3199j = objArrK;
        this.f3200k = (i5 + i8) - 1;
        this.f3196g = i6;
        return obj2;
    }

    public final int B() {
        int i5 = this.f3200k;
        if (i5 <= 32) {
            return 0;
        }
        return (i5 - 1) & (-32);
    }

    public final Object[] C(Object[] objArr, int i5, int i6, Object obj, e eVar) {
        int iX = d4.a.x(i6, i5);
        Object[] objArrK = k(objArr);
        if (i5 != 0) {
            Object obj2 = objArrK[iX];
            l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj2);
            objArrK[iX] = C((Object[]) obj2, i5 - 5, i6, obj, eVar);
            return objArrK;
        }
        if (objArrK != objArr) {
            ((AbstractList) this).modCount++;
        }
        eVar.f3188a = objArrK[iX];
        objArrK[iX] = obj;
        return objArrK;
    }

    public final void D(Collection collection, int i5, Object[] objArr, int i6, Object[][] objArr2, int i7, Object[] objArr3) {
        Object[] objArrM;
        if (i7 < 1) {
            x1.a("requires at least one nullBuffer");
        }
        Object[] objArrK = k(objArr);
        objArr2[0] = objArrK;
        int i8 = i5 & 31;
        int size = ((collection.size() + i5) - 1) & 31;
        int i9 = (i6 - i8) + size;
        if (i9 < 32) {
            q3.k.A(objArrK, objArr3, size + 1, i8, i6);
        } else {
            int i10 = i9 - 31;
            if (i7 == 1) {
                objArrM = objArrK;
            } else {
                objArrM = m();
                i7--;
                objArr2[i7] = objArrM;
            }
            int i11 = i6 - i10;
            q3.k.A(objArrK, objArr3, 0, i11, i6);
            q3.k.A(objArrK, objArrM, size + 1, i8, i11);
            objArr3 = objArrM;
        }
        Iterator it = collection.iterator();
        d(objArrK, i8, it);
        for (int i12 = 1; i12 < i7; i12++) {
            Object[] objArrM2 = m();
            d(objArrM2, 0, it);
            objArr2[i12] = objArrM2;
        }
        d(objArr3, 0, it);
    }

    public final int E() {
        int i5 = this.f3200k;
        return i5 <= 32 ? i5 : i5 - ((i5 - 1) & (-32));
    }

    @Override // q3.f
    public final int a() {
        return this.f3200k;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i5, Object obj) {
        a.a.n(i5, a());
        if (i5 == a()) {
            add(obj);
            return;
        }
        ((AbstractList) this).modCount++;
        int iB = B();
        if (i5 >= iB) {
            h(this.f3198i, i5 - iB, obj);
            return;
        }
        e eVar = new e(null);
        Object[] objArr = this.f3198i;
        l.b(objArr);
        h(g(objArr, this.f3196g, i5, obj, eVar), 0, eVar.f3188a);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        ((AbstractList) this).modCount++;
        int iE = E();
        if (iE < 32) {
            Object[] objArrK = k(this.f3199j);
            objArrK[iE] = obj;
            this.f3199j = objArrK;
            this.f3200k = a() + 1;
        } else {
            t(this.f3198i, this.f3199j, n(obj));
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i5, Collection collection) {
        Collection collection2;
        g gVar;
        Object[] objArrM;
        a.a.n(i5, this.f3200k);
        if (i5 == this.f3200k) {
            return addAll(collection);
        }
        if (collection.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int i6 = (i5 >> 5) << 5;
        int size = ((collection.size() + (this.f3200k - i6)) - 1) / 32;
        if (size == 0) {
            int i7 = i5 & 31;
            int size2 = ((collection.size() + i5) - 1) & 31;
            Object[] objArr = this.f3199j;
            Object[] objArrK = k(objArr);
            q3.k.A(objArr, objArrK, size2 + 1, i7, E());
            d(objArrK, i7, collection.iterator());
            this.f3199j = objArrK;
            this.f3200k = collection.size() + this.f3200k;
            return true;
        }
        Object[][] objArr2 = new Object[size][];
        int iE = E();
        int size3 = collection.size() + this.f3200k;
        if (size3 > 32) {
            size3 -= (size3 - 1) & (-32);
        }
        if (i5 >= B()) {
            objArrM = m();
            collection2 = collection;
            D(collection2, i5, this.f3199j, iE, objArr2, size, objArrM);
            gVar = this;
            objArr2 = objArr2;
        } else {
            collection2 = collection;
            gVar = this;
            if (size3 > iE) {
                int i8 = size3 - iE;
                Object[] objArrL = l(i8, gVar.f3199j);
                gVar.f(collection2, i5, i8, objArr2, size, objArrL);
                objArr2 = objArr2;
                objArrM = objArrL;
            } else {
                Object[] objArr3 = gVar.f3199j;
                objArrM = m();
                int i9 = iE - size3;
                q3.k.A(objArr3, objArrM, 0, i9, iE);
                int i10 = 32 - i9;
                Object[] objArrL2 = l(i10, gVar.f3199j);
                int i11 = size - 1;
                objArr2[i11] = objArrL2;
                gVar.f(collection2, i5, i10, objArr2, i11, objArrL2);
                collection2 = collection2;
            }
        }
        gVar.f3198i = s(gVar.f3198i, i6, objArr2);
        gVar.f3199j = objArrM;
        gVar.f3200k = collection2.size() + gVar.f3200k;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int iE = E();
        Iterator it = collection.iterator();
        if (32 - iE >= collection.size()) {
            Object[] objArrK = k(this.f3199j);
            d(objArrK, iE, it);
            this.f3199j = objArrK;
            this.f3200k = collection.size() + this.f3200k;
            return true;
        }
        int size = ((collection.size() + iE) - 1) / 32;
        Object[][] objArr = new Object[size][];
        Object[] objArrK2 = k(this.f3199j);
        d(objArrK2, iE, it);
        objArr[0] = objArrK2;
        for (int i5 = 1; i5 < size; i5++) {
            Object[] objArrM = m();
            d(objArrM, 0, it);
            objArr[i5] = objArrM;
        }
        this.f3198i = s(this.f3198i, B(), objArr);
        Object[] objArrM2 = m();
        d(objArrM2, 0, it);
        this.f3199j = objArrM2;
        this.f3200k = collection.size() + this.f3200k;
        return true;
    }

    @Override // q3.f
    public final Object b(int i5) {
        a.a.m(i5, a());
        ((AbstractList) this).modCount++;
        int iB = B();
        if (i5 >= iB) {
            return A(this.f3198i, iB, this.f3196g, i5 - iB);
        }
        e eVar = new e(this.f3199j[0]);
        Object[] objArr = this.f3198i;
        l.b(objArr);
        A(z(objArr, this.f3196g, i5, eVar), iB, this.f3196g, 0);
        return eVar.f3188a;
    }

    public final c c() {
        c fVar;
        Object[] objArr = this.f3198i;
        if (objArr == this.f3194e && this.f3199j == this.f3195f) {
            fVar = this.f3193d;
        } else {
            this.f3197h = new m0.b();
            this.f3194e = objArr;
            Object[] objArr2 = this.f3199j;
            this.f3195f = objArr2;
            if (objArr != null) {
                fVar = new f(objArr, objArr2, this.f3200k, this.f3196g);
            } else if (objArr2.length == 0) {
                fVar = j.f3207e;
            } else {
                Object[] objArrCopyOf = Arrays.copyOf(objArr2, this.f3200k);
                l.d("copyOf(...)", objArrCopyOf);
                fVar = new j(objArrCopyOf);
            }
        }
        this.f3193d = fVar;
        return fVar;
    }

    public final int e() {
        return ((AbstractList) this).modCount;
    }

    public final void f(Collection collection, int i5, int i6, Object[][] objArr, int i7, Object[] objArr2) {
        if (this.f3198i == null) {
            throw new IllegalStateException("root is null");
        }
        int i8 = i5 >> 5;
        a aVarJ = j(B() >> 5);
        int i9 = i7;
        Object[] objArrL = objArr2;
        while (aVarJ.f3182d - 1 != i8) {
            Object[] objArr3 = (Object[]) aVarJ.previous();
            q3.k.A(objArr3, objArrL, 0, 32 - i6, 32);
            objArrL = l(i6, objArr3);
            i9--;
            objArr[i9] = objArrL;
        }
        Object[] objArr4 = (Object[]) aVarJ.previous();
        int iB = i7 - (((B() >> 5) - 1) - i8);
        if (iB < i7) {
            objArr2 = objArr[iB];
            l.b(objArr2);
        }
        D(collection, i5, objArr4, 32, objArr, iB, objArr2);
    }

    public final Object[] g(Object[] objArr, int i5, int i6, Object obj, e eVar) {
        Object obj2;
        int iX = d4.a.x(i6, i5);
        if (i5 == 0) {
            eVar.f3188a = objArr[31];
            Object[] objArrK = k(objArr);
            q3.k.A(objArr, objArrK, iX + 1, iX, 31);
            objArrK[iX] = obj;
            return objArrK;
        }
        Object[] objArrK2 = k(objArr);
        int i7 = i5 - 5;
        Object obj3 = objArrK2[iX];
        l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj3);
        objArrK2[iX] = g((Object[]) obj3, i7, i6, obj, eVar);
        while (true) {
            iX++;
            if (iX >= 32 || (obj2 = objArrK2[iX]) == null) {
                break;
            }
            objArrK2[iX] = g((Object[]) obj2, i7, 0, eVar.f3188a, eVar);
        }
        return objArrK2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i5) {
        Object[] objArr;
        a.a.m(i5, a());
        if (B() <= i5) {
            objArr = this.f3199j;
        } else {
            objArr = this.f3198i;
            l.b(objArr);
            for (int i6 = this.f3196g; i6 > 0; i6 -= 5) {
                Object obj = objArr[d4.a.x(i5, i6)];
                l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj);
                objArr = (Object[]) obj;
            }
        }
        return objArr[i5 & 31];
    }

    public final void h(Object[] objArr, int i5, Object obj) {
        int iE = E();
        Object[] objArrK = k(this.f3199j);
        if (iE < 32) {
            q3.k.A(this.f3199j, objArrK, i5 + 1, i5, iE);
            objArrK[i5] = obj;
            this.f3198i = objArr;
            this.f3199j = objArrK;
            this.f3200k++;
            return;
        }
        Object[] objArr2 = this.f3199j;
        Object obj2 = objArr2[31];
        q3.k.A(objArr2, objArrK, i5 + 1, i5, 31);
        objArrK[i5] = obj;
        t(objArr, objArrK, n(obj2));
    }

    public final boolean i(Object[] objArr) {
        return objArr.length == 33 && objArr[32] == this.f3197h;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    public final a j(int i5) {
        Object[] objArr = this.f3198i;
        if (objArr == null) {
            throw new IllegalStateException("Invalid root");
        }
        int iB = B() >> 5;
        a.a.n(i5, iB);
        int i6 = this.f3196g;
        return i6 == 0 ? new d(i5, objArr) : new k(objArr, i5, iB, i6 / 5);
    }

    public final Object[] k(Object[] objArr) {
        if (objArr == null) {
            return m();
        }
        if (i(objArr)) {
            return objArr;
        }
        Object[] objArrM = m();
        int length = objArr.length;
        if (length > 32) {
            length = 32;
        }
        q3.k.C(objArr, objArrM, 0, length, 6);
        return objArrM;
    }

    public final Object[] l(int i5, Object[] objArr) {
        if (i(objArr)) {
            q3.k.A(objArr, objArr, i5, 0, 32 - i5);
            return objArr;
        }
        Object[] objArrM = m();
        q3.k.A(objArr, objArrM, i5, 0, 32 - i5);
        return objArrM;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i5) {
        a.a.n(i5, this.f3200k);
        return new i(this, i5);
    }

    public final Object[] m() {
        Object[] objArr = new Object[33];
        objArr[32] = this.f3197h;
        return objArr;
    }

    public final Object[] n(Object obj) {
        Object[] objArr = new Object[33];
        objArr[0] = obj;
        objArr[32] = this.f3197h;
        return objArr;
    }

    public final Object[] o(Object[] objArr, int i5, int i6) {
        if (i6 < 0) {
            x1.a("shift should be positive");
        }
        if (i6 == 0) {
            return objArr;
        }
        int iX = d4.a.x(i5, i6);
        Object obj = objArr[iX];
        l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj);
        Object objO = o((Object[]) obj, i5, i6 - 5);
        if (iX < 31) {
            int i7 = iX + 1;
            if (objArr[i7] != null) {
                if (i(objArr)) {
                    Arrays.fill(objArr, i7, 32, (Object) null);
                }
                Object[] objArrM = m();
                q3.k.A(objArr, objArrM, 0, 0, i7);
                objArr = objArrM;
            }
        }
        if (objO == objArr[iX]) {
            return objArr;
        }
        Object[] objArrK = k(objArr);
        objArrK[iX] = objO;
        return objArrK;
    }

    public final Object[] p(Object[] objArr, int i5, int i6, e eVar) {
        Object[] objArrP;
        int iX = d4.a.x(i6 - 1, i5);
        if (i5 == 5) {
            eVar.f3188a = objArr[iX];
            objArrP = null;
        } else {
            Object obj = objArr[iX];
            l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj);
            objArrP = p((Object[]) obj, i5 - 5, i6, eVar);
        }
        if (objArrP == null && iX == 0) {
            return null;
        }
        Object[] objArrK = k(objArr);
        objArrK[iX] = objArrP;
        return objArrK;
    }

    public final void q(Object[] objArr, int i5, int i6) {
        if (i6 == 0) {
            this.f3198i = null;
            if (objArr == null) {
                objArr = new Object[0];
            }
            this.f3199j = objArr;
            this.f3200k = i5;
            this.f3196g = i6;
            return;
        }
        e eVar = new e(null);
        l.b(objArr);
        Object[] objArrP = p(objArr, i6, i5, eVar);
        l.b(objArrP);
        Object obj = eVar.f3188a;
        l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj);
        this.f3199j = (Object[]) obj;
        this.f3200k = i5;
        if (objArrP[1] == null) {
            this.f3198i = (Object[]) objArrP[0];
            this.f3196g = i6 - 5;
        } else {
            this.f3198i = objArrP;
            this.f3196g = i6;
        }
    }

    public final Object[] r(Object[] objArr, int i5, int i6, Iterator it) {
        if (!it.hasNext()) {
            x1.a("invalid buffersIterator");
        }
        if (!(i6 >= 0)) {
            x1.a("negative shift");
        }
        if (i6 == 0) {
            return (Object[]) it.next();
        }
        Object[] objArrK = k(objArr);
        int iX = d4.a.x(i5, i6);
        int i7 = i6 - 5;
        objArrK[iX] = r((Object[]) objArrK[iX], i5, i7, it);
        while (true) {
            iX++;
            if (iX >= 32 || !it.hasNext()) {
                break;
            }
            objArrK[iX] = r((Object[]) objArrK[iX], 0, i7, it);
        }
        return objArrK;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        return y(new b(1, collection));
    }

    public final Object[] s(Object[] objArr, int i5, Object[][] objArr2) {
        b4.b bVarH = l.h(objArr2);
        int i6 = i5 >> 5;
        int i7 = this.f3196g;
        Object[] objArrR = i6 < (1 << i7) ? r(objArr, i5, i7, bVarH) : k(objArr);
        while (bVarH.hasNext()) {
            this.f3196g += 5;
            objArrR = n(objArrR);
            int i8 = this.f3196g;
            r(objArrR, 1 << i8, i8, bVarH);
        }
        return objArrR;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i5, Object obj) {
        a.a.m(i5, a());
        if (B() > i5) {
            e eVar = new e(null);
            Object[] objArr = this.f3198i;
            l.b(objArr);
            this.f3198i = C(objArr, this.f3196g, i5, obj, eVar);
            return eVar.f3188a;
        }
        Object[] objArrK = k(this.f3199j);
        if (objArrK != this.f3199j) {
            ((AbstractList) this).modCount++;
        }
        int i6 = i5 & 31;
        Object obj2 = objArrK[i6];
        objArrK[i6] = obj;
        this.f3199j = objArrK;
        return obj2;
    }

    public final void t(Object[] objArr, Object[] objArr2, Object[] objArr3) {
        int i5 = this.f3200k;
        int i6 = i5 >> 5;
        int i7 = this.f3196g;
        if (i6 > (1 << i7)) {
            this.f3198i = u(this.f3196g + 5, n(objArr), objArr2);
            this.f3199j = objArr3;
            this.f3196g += 5;
            this.f3200k++;
            return;
        }
        if (objArr == null) {
            this.f3198i = objArr2;
            this.f3199j = objArr3;
            this.f3200k = i5 + 1;
        } else {
            this.f3198i = u(i7, objArr, objArr2);
            this.f3199j = objArr3;
            this.f3200k++;
        }
    }

    public final Object[] u(int i5, Object[] objArr, Object[] objArr2) {
        int iX = d4.a.x(a() - 1, i5);
        Object[] objArrK = k(objArr);
        if (i5 == 5) {
            objArrK[iX] = objArr2;
            return objArrK;
        }
        objArrK[iX] = u(i5 - 5, (Object[]) objArrK[iX], objArr2);
        return objArrK;
    }

    public final int v(a4.c cVar, Object[] objArr, int i5, int i6, e eVar, ArrayList arrayList, ArrayList arrayList2) {
        if (i(objArr)) {
            arrayList.add(objArr);
        }
        Object obj = eVar.f3188a;
        l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj);
        Object[] objArr2 = (Object[]) obj;
        Object[] objArrM = objArr2;
        for (int i7 = 0; i7 < i5; i7++) {
            Object obj2 = objArr[i7];
            if (!((Boolean) cVar.c(obj2)).booleanValue()) {
                if (i6 == 32) {
                    objArrM = !arrayList.isEmpty() ? (Object[]) arrayList.remove(arrayList.size() - 1) : m();
                    i6 = 0;
                }
                objArrM[i6] = obj2;
                i6++;
            }
        }
        eVar.f3188a = objArrM;
        if (objArr2 != objArrM) {
            arrayList2.add(objArr2);
        }
        return i6;
    }

    public final int w(a4.c cVar, Object[] objArr, int i5, e eVar) {
        Object[] objArrK = objArr;
        int i6 = i5;
        boolean z2 = false;
        for (int i7 = 0; i7 < i5; i7++) {
            Object obj = objArr[i7];
            if (((Boolean) cVar.c(obj)).booleanValue()) {
                if (!z2) {
                    objArrK = k(objArr);
                    z2 = true;
                    i6 = i7;
                }
            } else if (z2) {
                objArrK[i6] = obj;
                i6++;
            }
        }
        eVar.f3188a = objArrK;
        return i6;
    }

    public final int x(a4.c cVar, int i5, e eVar) {
        int iW = w(cVar, this.f3199j, i5, eVar);
        if (iW == i5) {
            return i5;
        }
        Object obj = eVar.f3188a;
        l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj);
        Object[] objArr = (Object[]) obj;
        Arrays.fill(objArr, iW, i5, (Object) null);
        this.f3199j = objArr;
        this.f3200k -= i5 - iW;
        return iW;
    }

    public final boolean y(a4.c cVar) {
        Object[] objArrR;
        int i5;
        a4.c cVar2 = cVar;
        int iE = E();
        Object[] objArrO = null;
        e eVar = new e(null);
        boolean z2 = false;
        if (this.f3198i != null) {
            a aVarJ = j(0);
            int iW = 32;
            while (iW == 32 && aVarJ.hasNext()) {
                iW = w(cVar2, (Object[]) aVarJ.next(), 32, eVar);
            }
            if (iW == 32) {
                int iX = x(cVar2, iE, eVar);
                if (iX == 0) {
                    q(this.f3198i, this.f3200k, this.f3196g);
                }
                if (iX != iE) {
                }
            } else {
                int i6 = (aVarJ.f3182d - 1) << 5;
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                int iV = iW;
                while (aVarJ.hasNext()) {
                    iV = v(cVar2, (Object[]) aVarJ.next(), 32, iV, eVar, arrayList2, arrayList);
                    cVar2 = cVar;
                }
                int iV2 = v(cVar, this.f3199j, iE, iV, eVar, arrayList2, arrayList);
                Object obj = eVar.f3188a;
                l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj);
                Object[] objArr = (Object[]) obj;
                Arrays.fill(objArr, iV2, 32, (Object) null);
                if (arrayList.isEmpty()) {
                    objArrR = this.f3198i;
                    l.b(objArrR);
                } else {
                    objArrR = r(this.f3198i, i6, this.f3196g, arrayList.iterator());
                }
                int size = i6 + (arrayList.size() << 5);
                if ((size & 31) != 0) {
                    x1.a("invalid size");
                }
                if (size == 0) {
                    this.f3196g = 0;
                } else {
                    int i7 = size - 1;
                    while (true) {
                        i5 = this.f3196g;
                        if ((i7 >> i5) != 0) {
                            break;
                        }
                        this.f3196g = i5 - 5;
                        Object[] objArr2 = objArrR[0];
                        l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", objArr2);
                        objArrR = objArr2;
                    }
                    objArrO = o(objArrR, i7, i5);
                }
                this.f3198i = objArrO;
                this.f3199j = objArr;
                this.f3200k = size + iV2;
            }
            z2 = true;
        } else if (x(cVar2, iE, eVar) != iE) {
            z2 = true;
        }
        if (z2) {
            ((AbstractList) this).modCount++;
        }
        return z2;
    }

    public final Object[] z(Object[] objArr, int i5, int i6, e eVar) {
        int iX = d4.a.x(i6, i5);
        if (i5 == 0) {
            Object obj = objArr[iX];
            Object[] objArrK = k(objArr);
            q3.k.A(objArr, objArrK, iX, iX + 1, 32);
            objArrK[31] = eVar.f3188a;
            eVar.f3188a = obj;
            return objArrK;
        }
        int iX2 = objArr[31] == null ? d4.a.x(B() - 1, i5) : 31;
        Object[] objArrK2 = k(objArr);
        int i7 = i5 - 5;
        int i8 = iX + 1;
        if (i8 <= iX2) {
            while (true) {
                Object obj2 = objArrK2[iX2];
                l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj2);
                objArrK2[iX2] = z((Object[]) obj2, i7, 0, eVar);
                if (iX2 == i8) {
                    break;
                }
                iX2--;
            }
        }
        Object obj3 = objArrK2[iX];
        l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj3);
        objArrK2[iX] = z((Object[]) obj3, i7, i6, eVar);
        return objArrK2;
    }
}
