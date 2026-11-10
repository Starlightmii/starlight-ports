package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v extends b4.m implements a4.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ e2.r f6112e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f6113f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ e2.x f6114g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ z.e0 f6115h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ o0 f6116i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(e2.r rVar, boolean z2, e2.x xVar, z.e0 e0Var, o0 o0Var) {
        super(3);
        this.f6112e = rVar;
        this.f6113f = z2;
        this.f6114g = xVar;
        this.f6115h = e0Var;
        this.f6116i = o0Var;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        int iIntValue = ((Number) obj).intValue();
        int iIntValue2 = ((Number) obj2).intValue();
        boolean zBooleanValue = ((Boolean) obj3).booleanValue();
        e2.x xVar = this.f6114g;
        y1.f fVar = xVar.f2062a;
        e2.r rVar = this.f6112e;
        if (!zBooleanValue) {
            iIntValue = rVar.a(iIntValue);
        }
        if (!zBooleanValue) {
            iIntValue2 = rVar.a(iIntValue2);
        }
        boolean z2 = false;
        if (this.f6113f) {
            long j5 = xVar.f2063b;
            int i5 = y1.g0.f6769c;
            if (iIntValue != ((int) (j5 >> 32)) || iIntValue2 != ((int) (j5 & 4294967295L))) {
                int iMin = Math.min(iIntValue, iIntValue2);
                f0 f0Var = f0.f5917d;
                z.e0 e0Var = this.f6115h;
                if (iMin < 0 || Math.max(iIntValue, iIntValue2) > fVar.f6759d.length()) {
                    e0Var.p(false);
                    e0Var.n(f0Var);
                } else {
                    if (zBooleanValue || iIntValue == iIntValue2) {
                        e0Var.p(false);
                        e0Var.n(f0Var);
                    } else {
                        e0Var.f(true);
                    }
                    this.f6116i.f6025t.c(new e2.x(fVar, p3.j.g(iIntValue, iIntValue2), (y1.g0) null));
                    z2 = true;
                }
            }
        }
        return Boolean.valueOf(z2);
    }
}
