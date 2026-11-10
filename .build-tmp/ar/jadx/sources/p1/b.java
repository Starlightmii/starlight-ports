package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4769e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ c f4770f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(c cVar, int i5) {
        super(0);
        this.f4769e = i5;
        this.f4770f = cVar;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f4769e) {
            case 0:
                this.f4770f.A0();
                break;
            default:
                c cVar = this.f4770f;
                r0.n nVar = cVar.f4773q;
                b4.l.c("null cannot be cast to non-null type androidx.compose.ui.modifier.ModifierLocalConsumer", nVar);
                ((o1.c) nVar).f(cVar);
                break;
        }
        return p3.n.f5026a;
    }
}
