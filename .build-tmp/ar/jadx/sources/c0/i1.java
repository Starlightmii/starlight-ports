package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i1 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f919e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ f0.g3 f920f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i1(f0.g3 g3Var, int i5) {
        super(1);
        this.f919e = i5;
        this.f920f = g3Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f919e) {
            case 0:
                ((y0.n0) obj).c(((Number) this.f920f.getValue()).floatValue());
                return p3.n.f5026a;
            default:
                return new k2.h(h1.c.d(((k2.b) obj).M(((k2.e) this.f920f.getValue()).f3406d), 0));
        }
    }
}
