package h0;

import b4.k;
import b4.l;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements List, c4.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e f2570d;

    public b(e eVar) {
        this.f2570d = eVar;
    }

    @Override // java.util.List
    public final void add(int i5, Object obj) {
        this.f2570d.a(i5, obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        this.f2570d.b(obj);
        return true;
    }

    @Override // java.util.List
    public final boolean addAll(int i5, Collection collection) {
        return this.f2570d.e(i5, collection);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        e eVar = this.f2570d;
        return eVar.e(eVar.f2578f, collection);
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        this.f2570d.g();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return this.f2570d.h(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        e eVar = this.f2570d;
        eVar.getClass();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!eVar.h(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List
    public final Object get(int i5) {
        f.a(i5, this);
        return this.f2570d.f2576d[i5];
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return this.f2570d.i(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.f2570d.f2578f == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new d(0, this);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        e eVar = this.f2570d;
        Object[] objArr = eVar.f2576d;
        for (int i5 = eVar.f2578f - 1; i5 >= 0; i5--) {
            if (l.a(obj, objArr[i5])) {
                return i5;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new d(0, this);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i5) {
        return new d(i5, this);
    }

    @Override // java.util.List
    public final Object remove(int i5) {
        f.a(i5, this);
        return this.f2570d.m(i5);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        return this.f2570d.l(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        e eVar = this.f2570d;
        eVar.getClass();
        if (collection.isEmpty()) {
            return false;
        }
        int i5 = eVar.f2578f;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            eVar.l(it.next());
        }
        return i5 != eVar.f2578f;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        e eVar = this.f2570d;
        int i5 = eVar.f2578f;
        for (int i6 = i5 - 1; -1 < i6; i6--) {
            if (!collection.contains(eVar.f2576d[i6])) {
                eVar.m(i6);
            }
        }
        return i5 != eVar.f2578f;
    }

    @Override // java.util.List
    public final Object set(int i5, Object obj) {
        f.a(i5, this);
        Object[] objArr = this.f2570d.f2576d;
        Object obj2 = objArr[i5];
        objArr[i5] = obj;
        return obj2;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.f2570d.f2578f;
    }

    @Override // java.util.List
    public final List subList(int i5, int i6) {
        f.b(this, i5, i6);
        return new c(this, i5, i6);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return k.a(this);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return k.b(this, objArr);
    }
}
