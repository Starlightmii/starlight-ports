package j0;

import b4.l;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j extends c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final j f3207e = new j(new Object[0]);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object[] f3208d;

    public j(Object[] objArr) {
        this.f3208d = objArr;
    }

    @Override // q3.a
    public final int a() {
        return this.f3208d.length;
    }

    @Override // j0.c
    public final c b(int i5, Object obj) {
        Object[] objArr = this.f3208d;
        a.a.n(i5, objArr.length);
        if (i5 == objArr.length) {
            return c(obj);
        }
        if (objArr.length < 32) {
            Object[] objArr2 = new Object[objArr.length + 1];
            q3.k.C(objArr, objArr2, 0, i5, 6);
            q3.k.A(objArr, objArr2, i5 + 1, i5, objArr.length);
            objArr2[i5] = obj;
            return new j(objArr2);
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        l.d("copyOf(...)", objArrCopyOf);
        q3.k.A(objArr, objArrCopyOf, i5 + 1, i5, objArr.length - 1);
        objArrCopyOf[i5] = obj;
        Object[] objArr3 = new Object[32];
        objArr3[0] = objArr[31];
        return new f(objArrCopyOf, objArr3, objArr.length + 1, 0);
    }

    @Override // j0.c
    public final c c(Object obj) {
        Object[] objArr = this.f3208d;
        if (objArr.length >= 32) {
            Object[] objArr2 = new Object[32];
            objArr2[0] = obj;
            return new f(objArr, objArr2, objArr.length + 1, 0);
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length + 1);
        l.d("copyOf(...)", objArrCopyOf);
        objArrCopyOf[objArr.length] = obj;
        return new j(objArrCopyOf);
    }

    @Override // j0.c
    public final c d(Collection collection) {
        Object[] objArr = this.f3208d;
        if (collection.size() + objArr.length > 32) {
            g gVarE = e();
            gVarE.addAll(collection);
            return gVarE.c();
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, collection.size() + objArr.length);
        l.d("copyOf(...)", objArrCopyOf);
        int length = objArr.length;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            objArrCopyOf[length] = it.next();
            length++;
        }
        return new j(objArrCopyOf);
    }

    @Override // j0.c
    public final g e() {
        return new g(this, null, this.f3208d, 0);
    }

    @Override // j0.c
    public final c f(b bVar) {
        Object[] objArr = this.f3208d;
        int length = objArr.length;
        int length2 = objArr.length;
        Object[] objArrCopyOf = objArr;
        boolean z2 = false;
        for (int i5 = 0; i5 < length2; i5++) {
            Object obj = objArr[i5];
            if (((Boolean) bVar.c(obj)).booleanValue()) {
                if (!z2) {
                    objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
                    l.d("copyOf(...)", objArrCopyOf);
                    z2 = true;
                    length = i5;
                }
            } else if (z2) {
                objArrCopyOf[length] = obj;
                length++;
            }
        }
        if (length == objArr.length) {
            return this;
        }
        return length == 0 ? f3207e : new j(q3.k.D(objArrCopyOf, 0, length));
    }

    @Override // j0.c
    public final c g(int i5) {
        Object[] objArr = this.f3208d;
        a.a.m(i5, objArr.length);
        if (objArr.length == 1) {
            return f3207e;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length - 1);
        l.d("copyOf(...)", objArrCopyOf);
        q3.k.A(objArr, objArrCopyOf, i5, i5 + 1, objArr.length);
        return new j(objArrCopyOf);
    }

    @Override // java.util.List
    public final Object get(int i5) {
        a.a.m(i5, a());
        return this.f3208d[i5];
    }

    @Override // j0.c
    public final c h(int i5, Object obj) {
        Object[] objArr = this.f3208d;
        a.a.m(i5, objArr.length);
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        l.d("copyOf(...)", objArrCopyOf);
        objArrCopyOf[i5] = obj;
        return new j(objArrCopyOf);
    }

    @Override // q3.d, java.util.List
    public final int indexOf(Object obj) {
        return q3.k.H(this.f3208d, obj);
    }

    @Override // q3.d, java.util.List
    public final int lastIndexOf(Object obj) {
        Object[] objArr = this.f3208d;
        l.e("<this>", objArr);
        if (obj == null) {
            int length = objArr.length - 1;
            if (length >= 0) {
                while (true) {
                    int i5 = length - 1;
                    if (objArr[length] == null) {
                        return length;
                    }
                    if (i5 >= 0) {
                        length = i5;
                    }
                }
            }
        } else {
            int length2 = objArr.length - 1;
            if (length2 >= 0) {
                while (true) {
                    int i6 = length2 - 1;
                    if (obj.equals(objArr[length2])) {
                        return length2;
                    }
                    if (i6 < 0) {
                        break;
                    }
                    length2 = i6;
                }
            }
        }
        return -1;
    }

    @Override // q3.d, java.util.List
    public final ListIterator listIterator(int i5) {
        Object[] objArr = this.f3208d;
        a.a.n(i5, objArr.length);
        return new d(objArr, i5, objArr.length);
    }
}
