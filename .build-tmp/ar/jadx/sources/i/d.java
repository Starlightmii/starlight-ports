package i;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends e implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f2640d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2641e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a f2642f;

    public d(a aVar) {
        this.f2642f = aVar;
    }

    @Override // i.e
    public final void a(c cVar) {
        c cVar2 = this.f2640d;
        if (cVar == cVar2) {
            c cVar3 = cVar2.f2639g;
            this.f2640d = cVar3;
            this.f2641e = cVar3 == null;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f2641e) {
            return this.f2642f.f2628d != null;
        }
        c cVar = this.f2640d;
        return (cVar == null || cVar.f2638f == null) ? false : true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f2641e) {
            this.f2641e = false;
            this.f2640d = this.f2642f.f2628d;
        } else {
            c cVar = this.f2640d;
            this.f2640d = cVar != null ? cVar.f2638f : null;
        }
        return this.f2640d;
    }
}
