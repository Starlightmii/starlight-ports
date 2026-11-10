package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends b4.m implements a4.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f5866e = new c(3);

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        r0.p pVar = (r0.p) obj;
        ((Number) obj3).intValue();
        f0.v vVar = (f0.v) ((f0.p) obj2);
        vVar.V(-2126899193);
        long j5 = ((z.k0) vVar.j(z.l0.f6977a)).f6975a;
        boolean zE = vVar.e(j5);
        Object objK = vVar.K();
        if (zE || objK == f0.o.f2281a) {
            objK = new b(j5, 0);
            vVar.f0(objK);
        }
        r0.p pVarE = pVar.e(androidx.compose.ui.draw.a.b(r0.m.f5680a, (a4.c) objK));
        vVar.p(false);
        return pVarE;
    }
}
