package e1;

import c0.g1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a4.c f1954a;

    public abstract void a(a1.f fVar);

    public a4.c b() {
        return this.f1954a;
    }

    public final void c() {
        a4.c cVarB = b();
        if (cVarB != null) {
            cVarB.c(this);
        }
    }

    public void d(g1 g1Var) {
        this.f1954a = g1Var;
    }
}
