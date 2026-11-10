package n4;

import i4.y;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class p extends i4.a implements v3.d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final t3.c f4305g;

    public p(t3.c cVar, t3.h hVar) {
        super(hVar, true);
        this.f4305g = cVar;
    }

    @Override // i4.a1
    public final boolean N() {
        return true;
    }

    @Override // v3.d
    public final v3.d getCallerFrame() {
        t3.c cVar = this.f4305g;
        if (cVar instanceof v3.d) {
            return (v3.d) cVar;
        }
        return null;
    }

    @Override // i4.a1
    public void u(Object obj) {
        a.h(y.o(obj), p3.j.B(this.f4305g));
    }

    @Override // i4.a1
    public void v(Object obj) {
        this.f4305g.resumeWith(y.o(obj));
    }
}
