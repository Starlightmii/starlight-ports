package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q1 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f1184e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f1185f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ float f1186g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f1187h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1188i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ float f1189j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ float f1190k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f1191l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1192m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f1193n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f1194o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f1195p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ float f1196q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f1197r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ n1.h0 f1198s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q1(n1.m0 m0Var, boolean z2, float f5, n1.m0 m0Var2, int i5, float f6, float f7, n1.m0 m0Var3, int i6, float f8, n1.m0 m0Var4, int i7, float f9, int i8, n1.h0 h0Var) {
        super(1);
        this.f1184e = m0Var;
        this.f1185f = z2;
        this.f1186g = f5;
        this.f1187h = m0Var2;
        this.f1188i = i5;
        this.f1189j = f6;
        this.f1190k = f7;
        this.f1191l = m0Var3;
        this.f1192m = i6;
        this.f1193n = f8;
        this.f1194o = m0Var4;
        this.f1195p = i7;
        this.f1196q = f9;
        this.f1197r = i8;
        this.f1198s = h0Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        n1.l0 l0Var = (n1.l0) obj;
        float f5 = this.f1193n;
        float f6 = this.f1190k;
        n1.m0 m0Var = this.f1184e;
        if (m0Var != null) {
            n1.l0.f(l0Var, m0Var, (this.f1197r - m0Var.f4130d) / 2, d4.a.H((f5 - this.f1198s.M(r1.f1223e)) + f6));
        }
        if (this.f1185f || this.f1186g != 0.0f) {
            n1.l0.f(l0Var, this.f1187h, this.f1188i, d4.a.H(this.f1189j + f6));
        }
        n1.l0.f(l0Var, this.f1191l, this.f1192m, d4.a.H(f5 + f6));
        n1.l0.f(l0Var, this.f1194o, this.f1195p, d4.a.H(this.f1196q + f6));
        return p3.n.f5026a;
    }
}
