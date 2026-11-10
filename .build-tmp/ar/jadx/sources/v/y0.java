package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y0 extends b4.m implements a4.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ o0 f6146e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ z.e0 f6147f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ e2.x f6148g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ boolean f6149h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f6150i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ e2.r f6151j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ f1 f6152k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ a4.c f6153l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6154m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y0(o0 o0Var, z.e0 e0Var, e2.x xVar, boolean z2, boolean z3, e2.r rVar, f1 f1Var, a4.c cVar, int i5) {
        super(3);
        this.f6146e = o0Var;
        this.f6147f = e0Var;
        this.f6148g = xVar;
        this.f6149h = z2;
        this.f6150i = z3;
        this.f6151j = rVar;
        this.f6152k = f1Var;
        this.f6153l = cVar;
        this.f6154m = i5;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        ((Number) obj3).intValue();
        f0.v vVar = (f0.v) ((f0.p) obj2);
        vVar.V(851809892);
        Object objK = vVar.K();
        f0.h hVar = f0.o.f2281a;
        if (objK == hVar) {
            objK = new z.j0();
            vVar.f0(objK);
        }
        z.j0 j0Var = (z.j0) objK;
        Object objK2 = vVar.K();
        if (objK2 == hVar) {
            objK2 = new c0();
            vVar.f0(objK2);
        }
        a4.c cVar = this.f6153l;
        int i5 = this.f6154m;
        x0 x0Var = new x0(this.f6146e, this.f6147f, this.f6148g, this.f6149h, this.f6150i, j0Var, this.f6151j, this.f6152k, (c0) objK2, cVar, i5);
        boolean zH = vVar.h(x0Var);
        Object objK3 = vVar.K();
        if (zH || objK3 == hVar) {
            i4.v0 v0Var = new i4.v0(1, x0Var, x0.class, "process", "process-ZmokQxo(Landroid/view/KeyEvent;)Z", 0, 0, 3);
            vVar.f0(v0Var);
            objK3 = v0Var;
        }
        r0.p pVarA = androidx.compose.ui.input.key.a.a((a4.c) ((b4.j) objK3));
        vVar.p(false);
        return pVarA;
    }
}
