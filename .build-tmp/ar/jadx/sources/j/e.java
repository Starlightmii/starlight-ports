package j;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e implements Collection {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ f f3096d;

    public e(f fVar) {
        this.f3096d = fVar;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final void clear() {
        this.f3096d.clear();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.f3096d.a(obj) >= 0;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.f3096d.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new b(this.f3096d, 1);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        f fVar = this.f3096d;
        int iA = fVar.a(obj);
        if (iA < 0) {
            return false;
        }
        fVar.f(iA);
        return true;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        f fVar = this.f3096d;
        int i5 = fVar.f3110f;
        int i6 = 0;
        boolean z2 = false;
        while (i6 < i5) {
            if (collection.contains(fVar.h(i6))) {
                fVar.f(i6);
                i6--;
                i5--;
                z2 = true;
            }
            i6++;
        }
        return z2;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        f fVar = this.f3096d;
        int i5 = fVar.f3110f;
        int i6 = 0;
        boolean z2 = false;
        while (i6 < i5) {
            if (!collection.contains(fVar.h(i6))) {
                fVar.f(i6);
                i6--;
                i5--;
                z2 = true;
            }
            i6++;
        }
        return z2;
    }

    @Override // java.util.Collection
    public final int size() {
        return this.f3096d.f3110f;
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        f fVar = this.f3096d;
        int i5 = fVar.f3110f;
        Object[] objArr = new Object[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            objArr[i6] = fVar.h(i6);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        f fVar = this.f3096d;
        int i5 = fVar.f3110f;
        if (objArr.length < i5) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i5);
        }
        for (int i6 = 0; i6 < i5; i6++) {
            objArr[i6] = fVar.h(i6);
        }
        if (objArr.length > i5) {
            objArr[i5] = null;
        }
        return objArr;
    }
}
