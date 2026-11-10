package l4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q implements b0, d, m4.m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ d0 f3566d;

    public q(d0 d0Var) {
        this.f3566d = d0Var;
    }

    @Override // m4.m
    public final d c(t3.h hVar, int i5, k4.a aVar) {
        return ((((i5 < 0 || i5 >= 2) && i5 != -2) || aVar != k4.a.f3436e) && !((i5 == 0 || i5 == -3) && aVar == k4.a.f3435d)) ? new m4.g(this, hVar, i5, aVar) : this;
    }

    @Override // l4.d
    public final Object g(e eVar, t3.c cVar) {
        this.f3566d.g(eVar, cVar);
        return u3.a.f5838d;
    }

    @Override // l4.b0
    public final Object getValue() {
        return this.f3566d.getValue();
    }
}
