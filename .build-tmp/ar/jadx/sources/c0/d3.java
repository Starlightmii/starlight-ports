package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d3 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f800e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f801f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f802g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f803h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f804i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f805j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ int f806k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f807l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d3(n1.m0 m0Var, int i5, n1.m0 m0Var2, int i6, int i7, n1.m0 m0Var3, int i8, int i9) {
        super(1);
        this.f800e = m0Var;
        this.f801f = i5;
        this.f802g = m0Var2;
        this.f803h = i6;
        this.f804i = i7;
        this.f805j = m0Var3;
        this.f806k = i8;
        this.f807l = i9;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        n1.l0 l0Var = (n1.l0) obj;
        n1.l0.f(l0Var, this.f800e, 0, this.f801f);
        n1.m0 m0Var = this.f802g;
        if (m0Var != null) {
            n1.l0.f(l0Var, m0Var, this.f803h, this.f804i);
        }
        n1.m0 m0Var2 = this.f805j;
        if (m0Var2 != null) {
            n1.l0.f(l0Var, m0Var2, this.f806k, this.f807l);
        }
        return p3.n.f5026a;
    }
}
