package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends b4.m implements a4.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a4.a f6966e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f6967f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(a4.a aVar, boolean z2) {
        super(3);
        this.f6966e = aVar;
        this.f6967f = z2;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        r0.p pVar = (r0.p) obj;
        ((Number) obj3).intValue();
        f0.v vVar = (f0.v) ((f0.p) obj2);
        vVar.V(-196777734);
        long j5 = ((k0) vVar.j(l0.f6977a)).f6975a;
        boolean zE = vVar.e(j5);
        a4.a aVar = this.f6966e;
        boolean zF = zE | vVar.f(aVar);
        boolean z2 = this.f6967f;
        boolean zG = zF | vVar.g(z2);
        Object objK = vVar.K();
        if (zG || objK == f0.o.f2281a) {
            objK = new h(j5, aVar, z2);
            vVar.f0(objK);
        }
        r0.p pVarB = androidx.compose.ui.draw.a.b(pVar, (a4.c) objK);
        vVar.p(false);
        return pVarB;
    }
}
