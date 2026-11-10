package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h0 extends v3.i implements a4.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4469d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h0(int i5, t3.c cVar, int i6) {
        super(i5, cVar);
        this.f4469d = i6;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        switch (this.f4469d) {
            case 0:
                long j5 = ((x0.c) obj2).f6532a;
                h0 h0Var = new h0(3, (t3.c) obj3, 0);
                p3.n nVar = p3.n.f5026a;
                h0Var.invokeSuspend(nVar);
                return nVar;
            case 1:
                ((Number) obj2).floatValue();
                h0 h0Var2 = new h0(3, (t3.c) obj3, 1);
                p3.n nVar2 = p3.n.f5026a;
                h0Var2.invokeSuspend(nVar2);
                return nVar2;
            default:
                long j6 = ((x0.c) obj2).f6532a;
                h0 h0Var3 = new h0(3, (t3.c) obj3, 2);
                p3.n nVar3 = p3.n.f5026a;
                h0Var3.invokeSuspend(nVar3);
                return nVar3;
        }
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        int i5 = this.f4469d;
        p3.n nVar = p3.n.f5026a;
        switch (i5) {
            case 0:
                p3.j.O(obj);
                break;
            case 1:
                p3.j.O(obj);
                break;
            default:
                p3.j.O(obj);
                break;
        }
        return nVar;
    }
}
