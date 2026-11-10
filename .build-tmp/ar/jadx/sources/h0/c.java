package h0;

import b4.k;
import b4.l;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements List, c4.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f2571d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2572e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2573f;

    public c(List list, int i5, int i6) {
        this.f2571d = list;
        this.f2572e = i5;
        this.f2573f = i6;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final void add(int i5, Object obj) {
        this.f2571d.add(i5 + this.f2572e, obj);
        this.f2573f++;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        int i5 = this.f2573f;
        this.f2573f = i5 + 1;
        this.f2571d.add(i5, obj);
        return true;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final boolean addAll(int i5, Collection collection) {
        this.f2571d.addAll(i5 + this.f2572e, collection);
        int size = collection.size();
        this.f2573f += size;
        return size > 0;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        this.f2571d.addAll(this.f2573f, collection);
        int size = collection.size();
        this.f2573f += size;
        return size > 0;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List, java.util.Collection
    public final void clear() {
        int i5 = this.f2573f - 1;
        int i6 = this.f2572e;
        if (i6 <= i5) {
            while (true) {
                this.f2571d.remove(i5);
                if (i5 == i6) {
                    break;
                } else {
                    i5--;
                }
            }
        }
        this.f2573f = i6;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        int i5 = this.f2573f;
        for (int i6 = this.f2572e; i6 < i5; i6++) {
            if (l.a(this.f2571d.get(i6), obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final Object get(int i5) {
        f.a(i5, this);
        return this.f2571d.get(i5 + this.f2572e);
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final int indexOf(Object obj) {
        int i5 = this.f2573f;
        int i6 = this.f2572e;
        for (int i7 = i6; i7 < i5; i7++) {
            if (l.a(this.f2571d.get(i7), obj)) {
                return i7 - i6;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.f2573f == this.f2572e;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new d(0, this);
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        int i5 = this.f2573f - 1;
        int i6 = this.f2572e;
        if (i6 > i5) {
            return -1;
        }
        while (!l.a(this.f2571d.get(i5), obj)) {
            if (i5 == i6) {
                return -1;
            }
            i5--;
        }
        return i5 - i6;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new d(0, this);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i5) {
        return new d(i5, this);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final Object remove(int i5) {
        f.a(i5, this);
        Object objRemove = this.f2571d.remove(i5 + this.f2572e);
        this.f2573f--;
        return objRemove;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        int i5 = this.f2573f;
        for (int i6 = this.f2572e; i6 < i5; i6++) {
            ?? r4 = this.f2571d;
            if (l.a(r4.get(i6), obj)) {
                r4.remove(i6);
                this.f2573f--;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        int i5 = this.f2573f;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
        return i5 != this.f2573f;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        int i5 = this.f2573f;
        int i6 = i5 - 1;
        int i7 = this.f2572e;
        if (i7 <= i6) {
            while (true) {
                ?? r4 = this.f2571d;
                if (!collection.contains(r4.get(i6))) {
                    r4.remove(i6);
                    this.f2573f--;
                }
                if (i6 == i7) {
                    break;
                }
                i6--;
            }
        }
        return i5 != this.f2573f;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final Object set(int i5, Object obj) {
        f.a(i5, this);
        return this.f2571d.set(i5 + this.f2572e, obj);
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.f2573f - this.f2572e;
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
