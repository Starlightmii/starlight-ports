package q4;

import b3.r;
import i4.p1;
import n4.q;
import p3.n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements i4.f, p1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i4.h f5481d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ c f5482e;

    public b(c cVar, i4.h hVar) {
        this.f5482e = cVar;
        this.f5481d = hVar;
    }

    @Override // i4.p1
    public final void a(q qVar, int i5) {
        this.f5481d.a(qVar, i5);
    }

    @Override // i4.f
    public final boolean e(Throwable th) {
        return this.f5481d.e(th);
    }

    @Override // t3.c
    public final t3.h getContext() {
        return this.f5481d.f2736h;
    }

    @Override // i4.f
    public final r r(Object obj, a4.f fVar) {
        c cVar = this.f5482e;
        i4.g gVar = new i4.g(cVar, this);
        r rVarR = this.f5481d.r((n) obj, gVar);
        if (rVarR != null) {
            c.f5483g.set(cVar, null);
        }
        return rVarR;
    }

    @Override // t3.c
    public final void resumeWith(Object obj) {
        this.f5481d.resumeWith(obj);
    }

    @Override // i4.f
    public final void w(Object obj) {
        this.f5481d.w(obj);
    }
}
