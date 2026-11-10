package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6053e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ u0 f6054f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q0(u0 u0Var, int i5) {
        super(1);
        this.f6053e = i5;
        this.f6054f = u0Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f6053e) {
            case 0:
                this.f6054f.c(((x0.c) obj).f6532a);
                break;
            default:
                j1.u uVar = (j1.u) obj;
                this.f6054f.e(j1.s.f(uVar, false));
                uVar.a();
                break;
        }
        return p3.n.f5026a;
    }
}
