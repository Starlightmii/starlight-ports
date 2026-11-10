package r3;

import b4.l;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import p0.v;
import p3.p;
import q3.k;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends q3.f implements RandomAccess, Serializable {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b f5715g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f5716d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5717e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5718f;

    static {
        b bVar = new b(0);
        bVar.f5718f = true;
        f5715g = bVar;
    }

    public b(int i5) {
        if (i5 < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        this.f5716d = new Object[i5];
    }

    @Override // q3.f
    public final int a() {
        return this.f5717e;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i5, Object obj) {
        f();
        int i6 = this.f5717e;
        if (i5 < 0 || i5 > i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        ((AbstractList) this).modCount++;
        g(i5, 1);
        this.f5716d[i5] = obj;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        f();
        int i5 = this.f5717e;
        ((AbstractList) this).modCount++;
        g(i5, 1);
        this.f5716d[i5] = obj;
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i5, Collection collection) {
        l.e("elements", collection);
        f();
        int i6 = this.f5717e;
        if (i5 < 0 || i5 > i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        int size = collection.size();
        d(i5, collection, size);
        return size > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        l.e("elements", collection);
        f();
        int size = collection.size();
        d(this.f5717e, collection, size);
        return size > 0;
    }

    @Override // q3.f
    public final Object b(int i5) {
        f();
        int i6 = this.f5717e;
        if (i5 < 0 || i5 >= i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        return h(i5);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        f();
        i(0, this.f5717e);
    }

    public final void d(int i5, Collection collection, int i6) {
        ((AbstractList) this).modCount++;
        g(i5, i6);
        Iterator it = collection.iterator();
        for (int i7 = 0; i7 < i6; i7++) {
            this.f5716d[i5 + i7] = it.next();
        }
    }

    public final void e(int i5, Object obj) {
        ((AbstractList) this).modCount++;
        g(i5, 1);
        this.f5716d[i5] = obj;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            Object[] objArr = this.f5716d;
            int i5 = this.f5717e;
            if (i5 == list.size()) {
                for (int i6 = 0; i6 < i5; i6++) {
                    if (l.a(objArr[i6], list.get(i6))) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final void f() {
        if (this.f5718f) {
            throw new UnsupportedOperationException();
        }
    }

    public final void g(int i5, int i6) {
        int i7 = this.f5717e + i6;
        if (i7 < 0) {
            throw new OutOfMemoryError();
        }
        Object[] objArr = this.f5716d;
        if (i7 > objArr.length) {
            int length = objArr.length;
            int i8 = length + (length >> 1);
            if (i8 - i7 < 0) {
                i8 = i7;
            }
            if (i8 - 2147483639 > 0) {
                i8 = i7 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            Object[] objArrCopyOf = Arrays.copyOf(objArr, i8);
            l.d("copyOf(...)", objArrCopyOf);
            this.f5716d = objArrCopyOf;
        }
        Object[] objArr2 = this.f5716d;
        k.A(objArr2, objArr2, i5 + i6, i5, this.f5717e);
        this.f5717e += i6;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i5) {
        int i6 = this.f5717e;
        if (i5 < 0 || i5 >= i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        return this.f5716d[i5];
    }

    public final Object h(int i5) {
        ((AbstractList) this).modCount++;
        Object[] objArr = this.f5716d;
        Object obj = objArr[i5];
        k.A(objArr, objArr, i5, i5 + 1, this.f5717e);
        Object[] objArr2 = this.f5716d;
        int i6 = this.f5717e - 1;
        l.e("<this>", objArr2);
        objArr2[i6] = null;
        this.f5717e--;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        Object[] objArr = this.f5716d;
        int i5 = this.f5717e;
        int iHashCode = 1;
        for (int i6 = 0; i6 < i5; i6++) {
            Object obj = objArr[i6];
            iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    public final void i(int i5, int i6) {
        if (i6 > 0) {
            ((AbstractList) this).modCount++;
        }
        Object[] objArr = this.f5716d;
        k.A(objArr, objArr, i5, i5 + i6, this.f5717e);
        Object[] objArr2 = this.f5716d;
        int i7 = this.f5717e;
        q.f.r(objArr2, i7 - i6, i7);
        this.f5717e -= i6;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        for (int i5 = 0; i5 < this.f5717e; i5++) {
            if (l.a(this.f5716d[i5], obj)) {
                return i5;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.f5717e == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    public final int j(int i5, int i6, Collection collection, boolean z2) {
        int i7 = 0;
        int i8 = 0;
        while (i7 < i6) {
            int i9 = i5 + i7;
            if (collection.contains(this.f5716d[i9]) == z2) {
                Object[] objArr = this.f5716d;
                i7++;
                objArr[i8 + i5] = objArr[i9];
                i8++;
            } else {
                i7++;
            }
        }
        int i10 = i6 - i8;
        Object[] objArr2 = this.f5716d;
        k.A(objArr2, objArr2, i5 + i8, i6 + i5, this.f5717e);
        Object[] objArr3 = this.f5716d;
        int i11 = this.f5717e;
        q.f.r(objArr3, i11 - i10, i11);
        if (i10 > 0) {
            ((AbstractList) this).modCount++;
        }
        this.f5717e -= i10;
        return i10;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        for (int i5 = this.f5717e - 1; i5 >= 0; i5--) {
            if (l.a(this.f5716d[i5], obj)) {
                return i5;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i5) {
        int i6 = this.f5717e;
        if (i5 < 0 || i5 > i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        return new v(this, i5);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        f();
        int iIndexOf = indexOf(obj);
        if (iIndexOf >= 0) {
            b(iIndexOf);
        }
        return iIndexOf >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        l.e("elements", collection);
        f();
        return j(0, this.f5717e, collection, false) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        l.e("elements", collection);
        f();
        return j(0, this.f5717e, collection, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i5, Object obj) {
        f();
        int i6 = this.f5717e;
        if (i5 < 0 || i5 >= i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        Object[] objArr = this.f5716d;
        Object obj2 = objArr[i5];
        objArr[i5] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i5, int i6) {
        p.i(i5, i6, this.f5717e);
        return new a(this.f5716d, i5, i6 - i5, null, this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return k.D(this.f5716d, 0, this.f5717e);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        l.e("array", objArr);
        int length = objArr.length;
        int i5 = this.f5717e;
        if (length < i5) {
            Object[] objArrCopyOfRange = Arrays.copyOfRange(this.f5716d, 0, i5, objArr.getClass());
            l.d("copyOfRange(...)", objArrCopyOfRange);
            return objArrCopyOfRange;
        }
        k.A(this.f5716d, objArr, 0, 0, i5);
        int i6 = this.f5717e;
        if (i6 < objArr.length) {
            objArr[i6] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return q.f.d(this.f5716d, 0, this.f5717e, this);
    }
}
