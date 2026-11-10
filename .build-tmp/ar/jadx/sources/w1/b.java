package w1;

import p1.l1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends r0.o implements l1 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f6274q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f6275r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public a4.c f6276s;

    public b(boolean z2, boolean z3, a4.c cVar) {
        this.f6274q = z2;
        this.f6275r = z3;
        this.f6276s = cVar;
    }

    @Override // p1.l1
    public final boolean Y() {
        return this.f6275r;
    }

    @Override // p1.l1
    public final void Z(i iVar) {
        this.f6276s.c(iVar);
    }

    @Override // p1.l1
    public final boolean c0() {
        return this.f6274q;
    }
}
