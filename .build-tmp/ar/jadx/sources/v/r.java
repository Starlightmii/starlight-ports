package v;

import f0.i2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ o0 f6055e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f6056f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ e2.y f6057g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ e2.x f6058h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ e2.n f6059i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ e2.r f6060j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ z.e0 f6061k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ i4.v f6062l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ s.c f6063m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(o0 o0Var, boolean z2, e2.y yVar, e2.x xVar, e2.n nVar, e2.r rVar, z.e0 e0Var, i4.v vVar, s.c cVar) {
        super(1);
        this.f6055e = o0Var;
        this.f6056f = z2;
        this.f6057g = yVar;
        this.f6058h = xVar;
        this.f6059i = nVar;
        this.f6060j = rVar;
        this.f6061k = e0Var;
        this.f6062l = vVar;
        this.f6063m = cVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        e1 e1VarD;
        w0.q qVar = (w0.q) obj;
        o0 o0Var = this.f6055e;
        if (o0Var.b() != qVar.a()) {
            o0Var.f6011f.setValue(Boolean.valueOf(qVar.a()));
            boolean zB = o0Var.b();
            e2.r rVar = this.f6060j;
            e2.x xVar = this.f6058h;
            if (zB && this.f6056f) {
                l0.j(this.f6057g, o0Var, xVar, this.f6059i, rVar);
            } else {
                l0.g(o0Var);
            }
            if (qVar.a() && (e1VarD = o0Var.d()) != null) {
                i4.y.n(this.f6062l, null, new i2(this.f6063m, xVar, o0Var, e1VarD, rVar, null, 4), 3);
            }
            if (!qVar.a()) {
                this.f6061k.e(null);
            }
        }
        return p3.n.f5026a;
    }
}
