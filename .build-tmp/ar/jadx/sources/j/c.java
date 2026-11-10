package j;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements Set {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ f f3088d;

    public c(f fVar) {
        this.f3088d = fVar;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        this.f3088d.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        return this.f3088d.containsKey(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.f3088d.i(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean equals(Object obj) {
        f fVar = this.f3088d;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        try {
            return fVar.f3110f == set.size() && fVar.i(set);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final int hashCode() {
        f fVar = this.f3088d;
        int iHashCode = 0;
        for (int i5 = fVar.f3110f - 1; i5 >= 0; i5--) {
            Object objE = fVar.e(i5);
            iHashCode += objE == null ? 0 : objE.hashCode();
        }
        return iHashCode;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        return this.f3088d.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new b(this.f3088d, 0);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        f fVar = this.f3088d;
        int iC = fVar.c(obj);
        if (iC < 0) {
            return false;
        }
        fVar.f(iC);
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        return this.f3088d.j(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        f fVar = this.f3088d;
        int i5 = fVar.f3110f;
        for (int i6 = i5 - 1; i6 >= 0; i6--) {
            if (!collection.contains(fVar.e(i6))) {
                fVar.f(i6);
            }
        }
        return i5 != fVar.f3110f;
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        return this.f3088d.f3110f;
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        f fVar = this.f3088d;
        int i5 = fVar.f3110f;
        Object[] objArr = new Object[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            objArr[i6] = fVar.e(i6);
        }
        return objArr;
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        f fVar = this.f3088d;
        int i5 = fVar.f3110f;
        if (objArr.length < i5) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i5);
        }
        for (int i6 = 0; i6 < i5; i6++) {
            objArr[i6] = fVar.e(i6);
        }
        if (objArr.length > i5) {
            objArr[i5] = null;
        }
        return objArr;
    }
}
