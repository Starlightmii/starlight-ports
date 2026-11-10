package r;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n implements n1.f0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n f5592b = new n(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final n f5593c = new n(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5594a;

    public /* synthetic */ n(int i5) {
        this.f5594a = i5;
    }

    @Override // n1.f0
    public final n1.g0 h(n1.h0 h0Var, List list, long j5) {
        switch (this.f5594a) {
            case 0:
                return h0Var.F(k2.a.k(j5), k2.a.j(j5), q3.t.f5478d, m.f5584f);
            default:
                return h0Var.F(k2.a.g(j5) ? k2.a.i(j5) : 0, k2.a.f(j5) ? k2.a.h(j5) : 0, q3.t.f5478d, m.f5586h);
        }
    }
}
