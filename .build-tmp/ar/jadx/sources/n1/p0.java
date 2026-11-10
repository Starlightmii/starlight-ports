package n1;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4145e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ m0 f4146f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p0(m0 m0Var, int i5) {
        super(1);
        this.f4145e = i5;
        this.f4146f = m0Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f4145e) {
            case 0:
                l0.g((l0) obj, this.f4146f, 0, 0);
                break;
            case 1:
                l0.f((l0) obj, this.f4146f, 0, 0);
                break;
            case 2:
                l0.f((l0) obj, this.f4146f, 0, 0);
                break;
            case 3:
                l0.f((l0) obj, this.f4146f, 0, 0);
                break;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                l0 l0Var = (l0) obj;
                k2.k kVarB = l0Var.b();
                k2.k kVar = k2.k.f3416d;
                m0 m0Var = this.f4146f;
                if (kVarB == kVar || l0Var.c() == 0) {
                    l0.a(l0Var, m0Var);
                    m0Var.k0(k2.h.c(0L, m0Var.f4134h), 0.0f, null);
                } else {
                    int i5 = (int) 0;
                    long jD = h1.c.d((l0Var.c() - m0Var.f4130d) - i5, i5);
                    l0.a(l0Var, m0Var);
                    m0Var.k0(k2.h.c(jD, m0Var.f4134h), 0.0f, null);
                }
                break;
            case 5:
                l0.f((l0) obj, this.f4146f, 0, 0);
                break;
            case 6:
                l0.f((l0) obj, this.f4146f, 0, 0);
                break;
            case 7:
                l0.f((l0) obj, this.f4146f, 0, 0);
                break;
            case 8:
                l0.f((l0) obj, this.f4146f, 0, 0);
                break;
            default:
                l0.d((l0) obj, this.f4146f, 0, 0);
                break;
        }
        return p3.n.f5026a;
    }
}
