package r3;

import b4.l;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import p0.v;
import p3.p;
import q3.k;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends q3.f implements RandomAccess, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f5710d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5711e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5712f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a f5713g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final b f5714h;

    public a(Object[] objArr, int i5, int i6, a aVar, b bVar) {
        l.e("backing", objArr);
        l.e("root", bVar);
        this.f5710d = objArr;
        this.f5711e = i5;
        this.f5712f = i6;
        this.f5713g = aVar;
        this.f5714h = bVar;
        ((AbstractList) this).modCount = ((AbstractList) bVar).modCount;
    }

    @Override // q3.f
    public final int a() {
        f();
        return this.f5712f;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i5, Object obj) {
        g();
        f();
        int i6 = this.f5712f;
        if (i5 < 0 || i5 > i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        e(this.f5711e + i5, obj);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        g();
        f();
        e(this.f5711e + this.f5712f, obj);
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i5, Collection collection) {
        l.e("elements", collection);
        g();
        f();
        int i6 = this.f5712f;
        if (i5 < 0 || i5 > i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        int size = collection.size();
        d(this.f5711e + i5, collection, size);
        return size > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        l.e("elements", collection);
        g();
        f();
        int size = collection.size();
        d(this.f5711e + this.f5712f, collection, size);
        return size > 0;
    }

    @Override // q3.f
    public final Object b(int i5) {
        g();
        f();
        int i6 = this.f5712f;
        if (i5 < 0 || i5 >= i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        return h(this.f5711e + i5);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        g();
        f();
        i(this.f5711e, this.f5712f);
    }

    public final void d(int i5, Collection collection, int i6) {
        ((AbstractList) this).modCount++;
        b bVar = this.f5714h;
        a aVar = this.f5713g;
        if (aVar != null) {
            aVar.d(i5, collection, i6);
        } else {
            b bVar2 = b.f5715g;
            bVar.d(i5, collection, i6);
        }
        this.f5710d = bVar.f5716d;
        this.f5712f += i6;
    }

    public final void e(int i5, Object obj) {
        ((AbstractList) this).modCount++;
        b bVar = this.f5714h;
        a aVar = this.f5713g;
        if (aVar != null) {
            aVar.e(i5, obj);
        } else {
            b bVar2 = b.f5715g;
            bVar.e(i5, obj);
        }
        this.f5710d = bVar.f5716d;
        this.f5712f++;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        f();
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            Object[] objArr = this.f5710d;
            int i5 = this.f5712f;
            if (i5 == list.size()) {
                for (int i6 = 0; i6 < i5; i6++) {
                    if (l.a(objArr[this.f5711e + i6], list.get(i6))) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final void f() {
        if (((AbstractList) this.f5714h).modCount != ((AbstractList) this).modCount) {
            throw new ConcurrentModificationException();
        }
    }

    public final void g() {
        if (this.f5714h.f5718f) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i5) {
        f();
        int i6 = this.f5712f;
        if (i5 < 0 || i5 >= i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        return this.f5710d[this.f5711e + i5];
    }

    public final Object h(int i5) {
        Object objH;
        ((AbstractList) this).modCount++;
        a aVar = this.f5713g;
        if (aVar != null) {
            objH = aVar.h(i5);
        } else {
            b bVar = b.f5715g;
            objH = this.f5714h.h(i5);
        }
        this.f5712f--;
        return objH;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        f();
        Object[] objArr = this.f5710d;
        int i5 = this.f5712f;
        int iHashCode = 1;
        for (int i6 = 0; i6 < i5; i6++) {
            Object obj = objArr[this.f5711e + i6];
            iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    public final void i(int i5, int i6) {
        if (i6 > 0) {
            ((AbstractList) this).modCount++;
        }
        a aVar = this.f5713g;
        if (aVar != null) {
            aVar.i(i5, i6);
        } else {
            b bVar = b.f5715g;
            this.f5714h.i(i5, i6);
        }
        this.f5712f -= i6;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        f();
        for (int i5 = 0; i5 < this.f5712f; i5++) {
            if (l.a(this.f5710d[this.f5711e + i5], obj)) {
                return i5;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        f();
        return this.f5712f == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    public final int j(int i5, int i6, Collection collection, boolean z2) {
        int iJ;
        a aVar = this.f5713g;
        if (aVar != null) {
            iJ = aVar.j(i5, i6, collection, z2);
        } else {
            b bVar = b.f5715g;
            iJ = this.f5714h.j(i5, i6, collection, z2);
        }
        if (iJ > 0) {
            ((AbstractList) this).modCount++;
        }
        this.f5712f -= iJ;
        return iJ;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        f();
        for (int i5 = this.f5712f - 1; i5 >= 0; i5--) {
            if (l.a(this.f5710d[this.f5711e + i5], obj)) {
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
        f();
        int i6 = this.f5712f;
        if (i5 < 0 || i5 > i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        return new v(this, i5);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        g();
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
        g();
        f();
        return j(this.f5711e, this.f5712f, collection, false) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        l.e("elements", collection);
        g();
        f();
        return j(this.f5711e, this.f5712f, collection, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i5, Object obj) {
        g();
        f();
        int i6 = this.f5712f;
        if (i5 < 0 || i5 >= i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        Object[] objArr = this.f5710d;
        int i7 = this.f5711e;
        Object obj2 = objArr[i7 + i5];
        objArr[i7 + i5] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i5, int i6) {
        p.i(i5, i6, this.f5712f);
        return new a(this.f5710d, this.f5711e + i5, i6 - i5, this, this.f5714h);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        f();
        Object[] objArr = this.f5710d;
        int i5 = this.f5712f;
        int i6 = this.f5711e;
        return k.D(objArr, i6, i5 + i6);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        l.e("array", objArr);
        f();
        int length = objArr.length;
        int i5 = this.f5712f;
        int i6 = this.f5711e;
        if (length < i5) {
            Object[] objArrCopyOfRange = Arrays.copyOfRange(this.f5710d, i6, i5 + i6, objArr.getClass());
            l.d("copyOfRange(...)", objArrCopyOfRange);
            return objArrCopyOfRange;
        }
        k.A(this.f5710d, objArr, 0, i6, i5 + i6);
        int i7 = this.f5712f;
        if (i7 < objArr.length) {
            objArr[i7] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        f();
        return q.f.d(this.f5710d, this.f5711e, this.f5712f, this);
    }
}
