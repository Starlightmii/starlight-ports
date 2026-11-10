package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final v f5623a = new v(j.f5572b, r0.b.f5665p);

    public static final v a(h hVar, r0.f fVar, f0.p pVar, int i5) {
        if (b4.l.a(hVar, j.f5572b) && fVar.equals(r0.b.f5665p)) {
            f0.v vVar = (f0.v) pVar;
            vVar.V(345884104);
            vVar.p(false);
            return f5623a;
        }
        f0.v vVar2 = (f0.v) pVar;
        vVar2.V(345937951);
        boolean z2 = true;
        boolean z3 = (((i5 & 14) ^ 6) > 4 && vVar2.f(hVar)) || (i5 & 6) == 4;
        if ((((i5 & 112) ^ 48) <= 32 || !vVar2.f(fVar)) && (i5 & 48) != 32) {
            z2 = false;
        }
        boolean z4 = z3 | z2;
        Object objK = vVar2.K();
        if (z4 || objK == f0.o.f2281a) {
            objK = new v(hVar, fVar);
            vVar2.f0(objK);
        }
        v vVar3 = (v) objK;
        vVar2.p(false);
        return vVar3;
    }
}
