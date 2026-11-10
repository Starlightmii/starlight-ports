package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4494e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f4495f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f4496g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f4497h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f4498i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(b4.t tVar, e1 e1Var, b4.t tVar2, a2.h hVar) {
        super(1);
        this.f4495f = tVar;
        this.f4497h = e1Var;
        this.f4496g = tVar2;
        this.f4498i = hVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(v.o0 o0Var, e2.y yVar, e2.x xVar, e2.n nVar) {
        super(1);
        this.f4495f = o0Var;
        this.f4496g = yVar;
        this.f4497h = xVar;
        this.f4498i = nVar;
    }

    /* JADX WARN: Type inference failed for: r10v5, types: [a4.a, b4.m] */
    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f4494e) {
            case 0:
                m.j jVar = (m.j) obj;
                float fFloatValue = ((Number) jVar.f3682e.getValue()).floatValue();
                b4.t tVar = (b4.t) this.f4495f;
                float f5 = fFloatValue - tVar.f694d;
                float fA = ((e1) this.f4497h).a(f5);
                tVar.f694d = ((Number) jVar.f3682e.getValue()).floatValue();
                ((b4.t) this.f4496g).f694d = ((Number) jVar.f3678a.f3615b.c(jVar.f3683f)).floatValue();
                if (Math.abs(f5 - fA) > 0.5f) {
                    jVar.f3686i.setValue(Boolean.FALSE);
                    jVar.f3681d.a();
                }
                ((a2.h) this.f4498i).getClass();
                return p3.n.f5026a;
            default:
                v.o0 o0Var = (v.o0) this.f4495f;
                if (o0Var.b()) {
                    e2.y yVar = (e2.y) this.f4496g;
                    e2.x xVar = (e2.x) this.f4497h;
                    a2.h hVar = o0Var.f6009d;
                    e2.n nVar = (e2.n) this.f4498i;
                    v.q qVar = o0Var.f6025t;
                    v.q qVar2 = o0Var.f6026u;
                    b4.w wVar = new b4.w();
                    c.b bVar = new c.b(hVar, qVar, wVar, 10);
                    e2.s sVar = yVar.f2065a;
                    sVar.e(xVar, nVar, bVar, qVar2);
                    e2.d0 d0Var = new e2.d0(yVar, sVar);
                    yVar.f2066b.set(d0Var);
                    wVar.f697d = d0Var;
                    o0Var.f6010e = d0Var;
                }
                return new v.k();
        }
    }
}
