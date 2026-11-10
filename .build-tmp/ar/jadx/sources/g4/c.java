package g4;

import e1.y;
import f0.k1;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements Iterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Iterator f2550d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2551e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f2552f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ d f2553g;

    public c(d dVar) {
        this.f2553g = dVar;
        this.f2550d = new y((j) dVar.f2555b);
    }

    public final void a() {
        Object next;
        do {
            Iterator it = this.f2550d;
            if (!it.hasNext()) {
                this.f2551e = 0;
                return;
            }
            next = it.next();
        } while (((Boolean) ((k1) this.f2553g.f2556c).c(next)).booleanValue());
        this.f2552f = next;
        this.f2551e = 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f2551e == -1) {
            a();
        }
        return this.f2551e == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f2551e == -1) {
            a();
        }
        if (this.f2551e == 0) {
            throw new NoSuchElementException();
        }
        Object obj = this.f2552f;
        this.f2552f = null;
        this.f2551e = -1;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
