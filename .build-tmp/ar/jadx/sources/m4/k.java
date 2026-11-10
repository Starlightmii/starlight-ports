package m4;

import i4.y;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k extends f {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final v3.i f3842h;

    /* JADX WARN: Multi-variable type inference failed */
    public k(a4.f fVar, l4.d dVar, t3.h hVar, int i5, k4.a aVar) {
        super(dVar, hVar, i5, aVar);
        this.f3842h = (v3.i) fVar;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [a4.f, v3.i] */
    @Override // m4.f
    public final f a(t3.h hVar, int i5, k4.a aVar) {
        return new k(this.f3842h, this.f3826g, hVar, i5, aVar);
    }

    @Override // m4.f
    public final Object d(l4.e eVar, t3.c cVar) {
        Object objC = y.c(new h(this, eVar, null), cVar);
        return objC == u3.a.f5838d ? objC : p3.n.f5026a;
    }
}
