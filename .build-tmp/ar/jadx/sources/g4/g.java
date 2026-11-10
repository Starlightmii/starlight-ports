package g4;

import b4.l;
import java.util.Iterator;
import java.util.NoSuchElementException;
import p3.n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g implements Iterator, t3.c, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2561d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f2562e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public t3.c f2563f;

    public final RuntimeException a() {
        int i5 = this.f2561d;
        if (i5 == 4) {
            return new NoSuchElementException();
        }
        if (i5 == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.f2561d);
    }

    public final void b(Object obj, v3.h hVar) {
        this.f2562e = obj;
        this.f2561d = 3;
        this.f2563f = hVar;
    }

    @Override // t3.c
    public final t3.h getContext() {
        return t3.i.f5807d;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i5;
        while (true) {
            i5 = this.f2561d;
            if (i5 != 0) {
                break;
            }
            this.f2561d = 5;
            t3.c cVar = this.f2563f;
            l.b(cVar);
            this.f2563f = null;
            cVar.resumeWith(n.f5026a);
        }
        if (i5 == 1) {
            l.b(null);
            throw null;
        }
        if (i5 == 2 || i5 == 3) {
            return true;
        }
        if (i5 == 4) {
            return false;
        }
        throw a();
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i5 = this.f2561d;
        if (i5 == 0 || i5 == 1) {
            if (hasNext()) {
                return next();
            }
            throw new NoSuchElementException();
        }
        if (i5 == 2) {
            this.f2561d = 1;
            l.b(null);
            throw null;
        }
        if (i5 != 3) {
            throw a();
        }
        this.f2561d = 0;
        Object obj = this.f2562e;
        this.f2562e = null;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // t3.c
    public final void resumeWith(Object obj) {
        p3.j.O(obj);
        this.f2561d = 4;
    }
}
