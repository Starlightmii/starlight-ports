package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y0 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4651d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object f4652e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f4653f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ y0(long j5, t3.c cVar, int i5) {
        super(2, cVar);
        this.f4651d = i5;
        this.f4653f = j5;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f4651d) {
            case 0:
                y0 y0Var = new y0(this.f4653f, cVar, 0);
                y0Var.f4652e = obj;
                return y0Var;
            default:
                y0 y0Var2 = new y0(this.f4653f, cVar, 1);
                y0Var2.f4652e = obj;
                return y0Var2;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        g1 g1Var = (g1) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f4651d) {
            case 0:
                y0 y0Var = (y0) create(g1Var, cVar);
                p3.n nVar = p3.n.f5026a;
                y0Var.invokeSuspend(nVar);
                return nVar;
            default:
                y0 y0Var2 = (y0) create(g1Var, cVar);
                p3.n nVar2 = p3.n.f5026a;
                y0Var2.invokeSuspend(nVar2);
                return nVar2;
        }
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f4651d) {
            case 0:
                p3.j.O(obj);
                i1 i1Var = ((g1) this.f4652e).f4466a;
                i1.a(i1Var, i1Var.f4491h, this.f4653f, 1);
                break;
            default:
                p3.j.O(obj);
                i1 i1Var2 = ((g1) this.f4652e).f4466a;
                i1.a(i1Var2, i1Var2.f4491h, this.f4653f, 1);
                break;
        }
        return p3.n.f5026a;
    }
}
