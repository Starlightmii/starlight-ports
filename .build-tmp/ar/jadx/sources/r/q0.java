package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s0 f5613a = new s0(j.f5571a, r0.b.f5662m);

    public static final s0 a(e eVar, r0.g gVar, f0.p pVar, int i5) {
        if (b4.l.a(eVar, j.f5571a) && b4.l.a(gVar, r0.b.f5662m)) {
            f0.v vVar = (f0.v) pVar;
            vVar.V(-849160037);
            vVar.p(false);
            return f5613a;
        }
        f0.v vVar2 = (f0.v) pVar;
        vVar2.V(-849109166);
        boolean z2 = true;
        boolean z3 = (((i5 & 14) ^ 6) > 4 && vVar2.f(eVar)) || (i5 & 6) == 4;
        if ((((i5 & 112) ^ 48) <= 32 || !vVar2.f(gVar)) && (i5 & 48) != 32) {
            z2 = false;
        }
        boolean z4 = z3 | z2;
        Object objK = vVar2.K();
        if (z4 || objK == f0.o.f2281a) {
            objK = new s0(eVar, gVar);
            vVar2.f0(objK);
        }
        s0 s0Var = (s0) objK;
        vVar2.p(false);
        return s0Var;
    }
}
