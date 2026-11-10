package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class x implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ n0.j f2414d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ w2 f2415e;

    public /* synthetic */ x(n0.j jVar, w2 w2Var) {
        this.f2414d = jVar;
        this.f2415e = w2Var;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        int iIntValue = ((Integer) obj).intValue();
        boolean z2 = obj2 instanceof m;
        n0.j jVar = this.f2414d;
        if (z2) {
            jVar.f4077f.b((m) obj2);
        } else if (!(obj2 instanceof r2)) {
            boolean z3 = obj2 instanceof o2;
            w2 w2Var = this.f2415e;
            if (z3) {
                l.w(w2Var, iIntValue, obj2);
                jVar.d((o2) obj2);
            } else if (obj2 instanceof b2) {
                l.w(w2Var, iIntValue, obj2);
                ((b2) obj2).d();
            }
        }
        return p3.n.f5026a;
    }
}
