package e4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements Iterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2077d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2078e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2079f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2080g;

    public c(int i5, int i6, int i7) {
        this.f2077d = i7;
        this.f2078e = i6;
        boolean z2 = false;
        if (i7 <= 0 ? i5 >= i6 : i5 <= i6) {
            z2 = true;
        }
        this.f2079f = z2;
        this.f2080g = z2 ? i5 : i6;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f2079f;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return Integer.valueOf(nextInt());
    }

    public final int nextInt() {
        int i5 = this.f2080g;
        if (i5 != this.f2078e) {
            this.f2080g = this.f2077d + i5;
            return i5;
        }
        if (!this.f2079f) {
            throw new NoSuchElementException();
        }
        this.f2079f = false;
        return i5;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
