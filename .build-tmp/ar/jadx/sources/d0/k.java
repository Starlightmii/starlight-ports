package d0;

import f0.s1;
import r.z0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k implements z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1 f1540a;

    public k(z0 z0Var) {
        this.f1540a = f0.l.s(z0Var);
    }

    @Override // r.z0
    public final int a(k2.b bVar) {
        return ((z0) this.f1540a.getValue()).a(bVar);
    }

    @Override // r.z0
    public final int b(k2.b bVar) {
        return ((z0) this.f1540a.getValue()).b(bVar);
    }

    @Override // r.z0
    public final int c(k2.b bVar, k2.k kVar) {
        return ((z0) this.f1540a.getValue()).c(bVar, kVar);
    }

    @Override // r.z0
    public final int d(k2.b bVar, k2.k kVar) {
        return ((z0) this.f1540a.getValue()).d(bVar, kVar);
    }
}
