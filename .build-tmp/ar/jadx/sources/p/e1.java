package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e1 implements p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ i1 f4442a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ g1 f4443b;

    public e1(i1 i1Var, g1 g1Var) {
        this.f4442a = i1Var;
        this.f4443b = g1Var;
    }

    @Override // p.p0
    public final float a(float f5) {
        i1 i1Var = this.f4442a;
        long jD = i1Var.d(i1Var.g(f5));
        i1 i1Var2 = this.f4443b.f4466a;
        i1Var2.f4490g = 2;
        n.x0 x0Var = i1Var2.f4485b;
        return i1Var.c(i1Var.f((x0Var == null || !(i1Var2.f4484a.c() || i1Var2.f4484a.a())) ? i1.a(i1Var2, i1Var2.f4491h, jD, 2) : x0Var.d(jD, i1Var2.f4490g, i1Var2.f4493j)));
    }
}
