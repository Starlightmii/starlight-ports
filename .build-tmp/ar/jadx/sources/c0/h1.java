package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h1 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f894e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ f0.g3 f895f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h1(f0.g3 g3Var, int i5) {
        super(0);
        this.f894e = i5;
        this.f895f = g3Var;
    }

    @Override // a4.a
    public final Object a() {
        int i5 = this.f894e;
        f0.g3 g3Var = this.f895f;
        switch (i5) {
            case 0:
                return (Float) g3Var.getValue();
            case 1:
                return Boolean.valueOf(((Number) g3Var.getValue()).floatValue() > 0.0f);
            case 2:
                return Boolean.valueOf(((Number) g3Var.getValue()).floatValue() > 0.0f);
            case 3:
                return new x0.c(((x0.c) g3Var.getValue()).f6532a);
            default:
                m.n nVar = z.x.f7022a;
                return new x0.c(((x0.c) g3Var.getValue()).f6532a);
        }
    }
}
