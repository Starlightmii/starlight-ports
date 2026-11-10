package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k implements n2.s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r0.c f6972a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l f6973b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f6974c = 0;

    public k(r0.c cVar, l lVar) {
        this.f6972a = cVar;
        this.f6973b = lVar;
    }

    @Override // n2.s
    public final long a(k2.i iVar, long j5, k2.k kVar, long j6) {
        long jA = this.f6973b.a();
        if (!p3.j.E(jA)) {
            jA = this.f6974c;
        }
        this.f6974c = jA;
        return k2.h.c(k2.h.c(h1.c.d(iVar.f3411a, iVar.f3412b), h1.c.M(jA)), this.f6972a.a(j6, 0L, kVar));
    }
}
