package j0;

import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements ListIterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3182d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3183e;

    public a(int i5, int i6) {
        this.f3182d = i5;
        this.f3183e = i6;
    }

    @Override // java.util.ListIterator
    public void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.f3182d < this.f3183e;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f3182d > 0;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f3182d;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f3182d - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator
    public void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
