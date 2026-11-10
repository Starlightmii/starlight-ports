package g4;

import b4.l;
import j.b0;
import j.c0;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e implements Iterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2557d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2558e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f2559f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f2560g;

    public e(d dVar) {
        this.f2557d = 0;
        this.f2560g = dVar;
        this.f2558e = -2;
    }

    public e(c0 c0Var) {
        this.f2557d = 1;
        this.f2560g = c0Var;
        this.f2558e = -1;
        this.f2559f = h1.c.C(new b0(c0Var, this, null));
    }

    public e(Object obj, Map map) {
        this.f2557d = 2;
        this.f2559f = obj;
        this.f2560g = map;
    }

    public void a() {
        Object objC;
        d dVar = (d) this.f2560g;
        if (this.f2558e == -2) {
            objC = ((a4.a) dVar.f2555b).a();
        } else {
            a4.c cVar = dVar.f2556c;
            Object obj = this.f2559f;
            l.b(obj);
            objC = cVar.c(obj);
        }
        this.f2559f = objC;
        this.f2558e = objC == null ? 0 : 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.f2557d) {
            case 0:
                if (this.f2558e < 0) {
                    a();
                }
                return this.f2558e == 1;
            case 1:
                return ((g) this.f2559f).hasNext();
            default:
                return this.f2558e < ((Map) this.f2560g).size();
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f2557d) {
            case 0:
                if (this.f2558e < 0) {
                    a();
                }
                if (this.f2558e == 0) {
                    throw new NoSuchElementException();
                }
                Object obj = this.f2559f;
                l.c("null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence", obj);
                this.f2558e = -1;
                return obj;
            case 1:
                return ((g) this.f2559f).next();
            default:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Object obj2 = this.f2559f;
                this.f2558e++;
                Object obj3 = ((Map) this.f2560g).get(obj2);
                if (obj3 != null) {
                    this.f2559f = ((l0.a) obj3).f3494b;
                    return obj2;
                }
                throw new ConcurrentModificationException("Hash code of an element (" + obj2 + ") has changed after it was added to the persistent set.");
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f2557d) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                int i5 = this.f2558e;
                if (i5 != -1) {
                    ((c0) this.f2560g).f3090e.k(i5);
                    this.f2558e = -1;
                    return;
                }
                return;
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }
}
