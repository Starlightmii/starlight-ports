package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b1 implements p.b1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ p.b1 f5863a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f0.n0 f5864b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f0.n0 f5865c;

    public b1(p.b1 b1Var, c1 c1Var) {
        this.f5863a = b1Var;
        this.f5864b = f0.l.n(new a1(c1Var, 1));
        this.f5865c = f0.l.n(new a1(c1Var, 0));
    }

    @Override // p.b1
    public final boolean a() {
        return ((Boolean) this.f5865c.getValue()).booleanValue();
    }

    @Override // p.b1
    public final boolean b() {
        return this.f5863a.b();
    }

    @Override // p.b1
    public final boolean c() {
        return ((Boolean) this.f5864b.getValue()).booleanValue();
    }

    @Override // p.b1
    public final Object d(int i5, b0.d dVar, v3.c cVar) {
        return this.f5863a.d(i5, dVar, cVar);
    }

    @Override // p.b1
    public final float e(float f5) {
        return this.f5863a.e(f5);
    }
}
