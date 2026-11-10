package p1;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q implements List, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4954d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4955e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ r f4956f;

    public q(r rVar, int i5, int i6) {
        this.f4956f = rVar;
        this.f4954d = i5;
        this.f4955e = i6;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ void add(int i5, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final boolean addAll(int i5, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return (obj instanceof r0.o) && indexOf((r0.o) obj) != -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains((r0.o) it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List
    public final Object get(int i5) {
        Object obj = this.f4956f.f4957d[i5 + this.f4954d];
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.Modifier.Node", obj);
        return (r0.o) obj;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof r0.o)) {
            return -1;
        }
        r0.o oVar = (r0.o) obj;
        int i5 = this.f4954d;
        int i6 = this.f4955e;
        if (i5 <= i6) {
            int i7 = i5;
            while (!b4.l.a(this.f4956f.f4957d[i7], oVar)) {
                if (i7 != i6) {
                    i7++;
                }
            }
            return i7 - i5;
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        int i5 = this.f4954d;
        return new p0.v(this.f4956f, i5, i5, this.f4955e);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof r0.o)) {
            return -1;
        }
        r0.o oVar = (r0.o) obj;
        int i5 = this.f4955e;
        int i6 = this.f4954d;
        if (i6 <= i5) {
            while (!b4.l.a(this.f4956f.f4957d[i5], oVar)) {
                if (i5 != i6) {
                    i5--;
                }
            }
            return i5 - i6;
        }
        return -1;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        int i5 = this.f4954d;
        return new p0.v(this.f4956f, i5, i5, this.f4955e);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i5) {
        int i6 = this.f4954d;
        int i7 = this.f4955e;
        return new p0.v(this.f4956f, i5 + i6, i6, i7);
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i5) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final void replaceAll(UnaryOperator unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i5, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.f4955e - this.f4954d;
    }

    @Override // java.util.List
    public final void sort(Comparator comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final List subList(int i5, int i6) {
        int i7 = this.f4954d;
        return new q(this.f4956f, i5 + i7, i7 + i6);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return b4.k.a(this);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return b4.k.b(this, objArr);
    }
}
