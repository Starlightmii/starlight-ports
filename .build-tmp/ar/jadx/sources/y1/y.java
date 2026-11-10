package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y implements o0.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ b4.m f6869d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ b4.m f6870e;

    /* JADX WARN: Multi-variable type inference failed */
    public y(a4.e eVar, a4.c cVar) {
        this.f6869d = (b4.m) eVar;
        this.f6870e = (b4.m) cVar;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [a4.e, b4.m] */
    @Override // o0.f
    public final Object f(o0.b bVar, Object obj) {
        return this.f6869d.invoke(bVar, obj);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [a4.c, b4.m] */
    @Override // o0.f
    public final Object i(Object obj) {
        return this.f6870e.c(obj);
    }
}
