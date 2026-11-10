package v;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6096e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ z.e0 f6097f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(z.e0 e0Var, int i5) {
        super(0);
        this.f6096e = i5;
        this.f6097f = e0Var;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f6096e) {
            case 0:
                this.f6097f.l();
                return Boolean.TRUE;
            case 1:
                this.f6097f.f(true);
                return Boolean.TRUE;
            case 2:
                this.f6097f.b(true);
                return Boolean.TRUE;
            case 3:
                this.f6097f.d();
                return Boolean.TRUE;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                z.e0 e0Var = this.f6097f;
                e0Var.b(true);
                e0Var.k();
                return p3.n.f5026a;
            case 5:
                z.e0 e0Var2 = this.f6097f;
                e0Var2.d();
                e0Var2.k();
                return p3.n.f5026a;
            case 6:
                z.e0 e0Var3 = this.f6097f;
                e0Var3.l();
                e0Var3.k();
                return p3.n.f5026a;
            default:
                this.f6097f.m();
                return p3.n.f5026a;
        }
    }
}
