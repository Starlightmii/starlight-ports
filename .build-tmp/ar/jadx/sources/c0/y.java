package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y extends b4.m implements a4.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final y f1434e = new y(3);

    /* JADX WARN: Code duplicated, block: B:15:0x0035  */
    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        z2 z2Var = (z2) obj;
        f0.p pVar = (f0.p) obj2;
        int iIntValue = ((Number) obj3).intValue();
        if ((iIntValue & 6) == 0) {
            iIntValue |= ((f0.v) pVar).f(z2Var) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18) {
            f0.v vVar = (f0.v) pVar;
            if (vVar.z()) {
                vVar.Q();
            } else {
                j3.b(z2Var, null, null, 0L, 0L, 0L, 0L, 0L, pVar, iIntValue & 14);
            }
        } else {
            j3.b(z2Var, null, null, 0L, 0L, 0L, 0L, 0L, pVar, iIntValue & 14);
        }
        return p3.n.f5026a;
    }
}
