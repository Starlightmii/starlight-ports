package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p1 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f1146e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f1147f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f1148g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1149h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f1150i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f1151j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ int f1152k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f1153l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1154m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p1(n1.m0 m0Var, n1.m0 m0Var2, int i5, int i6, n1.m0 m0Var3, int i7, int i8, int i9, int i10) {
        super(1);
        this.f1146e = m0Var;
        this.f1147f = m0Var2;
        this.f1148g = i5;
        this.f1149h = i6;
        this.f1150i = m0Var3;
        this.f1151j = i7;
        this.f1152k = i8;
        this.f1153l = i9;
        this.f1154m = i10;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        n1.l0 l0Var = (n1.l0) obj;
        n1.m0 m0Var = this.f1146e;
        if (m0Var != null) {
            n1.l0.f(l0Var, m0Var, (this.f1153l - m0Var.f4130d) / 2, (this.f1154m - m0Var.f4131e) / 2);
        }
        n1.l0.f(l0Var, this.f1147f, this.f1148g, this.f1149h);
        n1.l0.f(l0Var, this.f1150i, this.f1151j, this.f1152k);
        return p3.n.f5026a;
    }
}
