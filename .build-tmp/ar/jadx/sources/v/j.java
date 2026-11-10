package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5948e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ z.e0 f5949f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(z.e0 e0Var, int i5) {
        super(1);
        this.f5948e = i5;
        this.f5949f = e0Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f5948e) {
            case 0:
                return new c.a(3, this.f5949f);
            default:
                long j5 = ((x0.c) obj).f6532a;
                this.f5949f.o();
                return p3.n.f5026a;
        }
    }
}
