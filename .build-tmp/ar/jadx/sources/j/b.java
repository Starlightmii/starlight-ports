package j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements Iterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3071d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3072e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3073f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f3074g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f3075h;

    public b(int i5) {
        this.f3071d = i5;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public b(f fVar, int i5) {
        this(fVar.f3110f);
        this.f3074g = i5;
        switch (i5) {
            case 1:
                this.f3075h = fVar;
                this(fVar.f3110f);
                break;
            default:
                this.f3075h = fVar;
                break;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public b(g gVar) {
        this(gVar.f3105f);
        this.f3074g = 2;
        this.f3075h = gVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f3072e < this.f3071d;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object objE;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i5 = this.f3072e;
        switch (this.f3074g) {
            case 0:
                objE = ((f) this.f3075h).e(i5);
                break;
            case 1:
                objE = ((f) this.f3075h).h(i5);
                break;
            default:
                objE = ((g) this.f3075h).f3104e[i5];
                break;
        }
        this.f3072e++;
        this.f3073f = true;
        return objE;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f3073f) {
            throw new IllegalStateException("Call next() before removing an element.");
        }
        int i5 = this.f3072e - 1;
        this.f3072e = i5;
        switch (this.f3074g) {
            case 0:
                ((f) this.f3075h).f(i5);
                break;
            case 1:
                ((f) this.f3075h).f(i5);
                break;
            default:
                ((g) this.f3075h).a(i5);
                break;
        }
        this.f3071d--;
        this.f3073f = false;
    }
}
