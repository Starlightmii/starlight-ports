package q3;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends b4.b implements ListIterator {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ d f5467g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(d dVar, int i5) {
        super(dVar);
        this.f5467g = dVar;
        int iA = dVar.a();
        if (i5 < 0 || i5 > iA) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", iA));
        }
        this.f677e = i5;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f677e > 0;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f677e;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i5 = this.f677e - 1;
        this.f677e = i5;
        return this.f5467g.get(i5);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f677e - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
