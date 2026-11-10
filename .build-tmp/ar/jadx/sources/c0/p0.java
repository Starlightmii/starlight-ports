package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1144e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ String f1145f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p0(String str, int i5) {
        super(1);
        this.f1144e = i5;
        this.f1145f = str;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        int i5 = this.f1144e;
        p3.n nVar = p3.n.f5026a;
        String str = this.f1145f;
        switch (i5) {
            case 0:
                w1.i iVar = (w1.i) obj;
                f4.d[] dVarArr = w1.s.f6363a;
                iVar.c(w1.q.f6336a, p3.j.G(str));
                w1.s.d(iVar, 5);
                break;
            default:
                f4.d[] dVarArr2 = w1.s.f6363a;
                ((w1.i) obj).c(w1.q.f6336a, p3.j.G(str));
                break;
        }
        return nVar;
    }
}
