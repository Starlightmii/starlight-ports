package n1;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.function.Predicate;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a1 implements Collection, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashSet f4089d = new LinkedHashSet();

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        return this.f4089d.add(obj);
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final void clear() {
        this.f4089d.clear();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.f4089d.contains(obj);
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.f4089d.containsAll(collection);
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.f4089d.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.f4089d.iterator();
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        return this.f4089d.remove(obj);
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        return this.f4089d.remove(collection);
    }

    @Override // java.util.Collection
    public final boolean removeIf(Predicate predicate) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        return this.f4089d.retainAll(collection);
    }

    @Override // java.util.Collection
    public final int size() {
        return this.f4089d.size();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return b4.k.a(this);
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return b4.k.b(this, objArr);
    }
}
