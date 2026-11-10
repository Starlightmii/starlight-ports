package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b1 extends h1 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final t3.c f2718g;

    public b1(t3.h hVar, a4.e eVar) {
        super(hVar, false);
        this.f2718g = p3.j.q(eVar, this, this);
    }

    @Override // i4.a1
    public final void T() {
        try {
            n4.a.h(p3.n.f5026a, p3.j.B(this.f2718g));
        } catch (Throwable th) {
            resumeWith(p3.j.r(th));
            throw th;
        }
    }
}
