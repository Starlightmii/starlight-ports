package n1;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y extends p1.b0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ b0 f4189b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ a4.e f4190c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(b0 b0Var, a4.e eVar, String str) {
        super(str);
        this.f4189b = b0Var;
        this.f4190c = eVar;
    }

    @Override // n1.f0
    public final g0 h(h0 h0Var, List list, long j5) {
        b0 b0Var = this.f4189b;
        w wVar = b0Var.f4097j;
        wVar.f4178d = h0Var.getLayoutDirection();
        wVar.f4179e = h0Var.a();
        wVar.f4180f = h0Var.j();
        boolean zO = h0Var.o();
        a4.e eVar = this.f4190c;
        if (zO || b0Var.f4091d.f4782f == null) {
            b0Var.f4093f = 0;
            g0 g0Var = (g0) eVar.invoke(wVar, new k2.a(j5));
            return new x(g0Var, b0Var, b0Var.f4093f, g0Var, 1);
        }
        b0Var.f4094g = 0;
        g0 g0Var2 = (g0) eVar.invoke(b0Var.f4098k, new k2.a(j5));
        return new x(g0Var2, b0Var, b0Var.f4094g, g0Var2, 0);
    }
}
