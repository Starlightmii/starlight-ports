package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h2 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2192d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object f2193e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h2(int i5, t3.c cVar, int i6) {
        super(i5, cVar);
        this.f2192d = i6;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f2192d) {
            case 0:
                h2 h2Var = new h2(2, cVar, 0);
                h2Var.f2193e = obj;
                return h2Var;
            default:
                h2 h2Var2 = new h2(2, cVar, 1);
                h2Var2.f2193e = obj;
                return h2Var2;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f2192d) {
            case 0:
                return ((h2) create((g2) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            default:
                return ((h2) create((l4.w) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
        }
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f2192d) {
            case 0:
                p3.j.O(obj);
                return Boolean.valueOf(((g2) this.f2193e) == g2.f2177d);
            default:
                p3.j.O(obj);
                return Boolean.valueOf(((l4.w) this.f2193e) != l4.w.f3591d);
        }
    }
}
