package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ n1.m0[] f5624e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ v f5625f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f5626g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ n1.h0 f5627h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int[] f5628i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(n1.m0[] m0VarArr, v vVar, int i5, n1.h0 h0Var, int[] iArr) {
        super(1);
        this.f5624e = m0VarArr;
        this.f5625f = vVar;
        this.f5626g = i5;
        this.f5627h = h0Var;
        this.f5628i = iArr;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        n1.l0 l0Var = (n1.l0) obj;
        n1.m0[] m0VarArr = this.f5624e;
        int length = m0VarArr.length;
        int i5 = 0;
        int i6 = 0;
        while (i5 < length) {
            n1.m0 m0Var = m0VarArr[i5];
            b4.l.b(m0Var);
            m0Var.h();
            n1.l0.d(l0Var, m0Var, this.f5625f.f5631b.a(0, this.f5626g - m0Var.f4130d, this.f5627h.getLayoutDirection()), this.f5628i[i6]);
            i5++;
            i6++;
        }
        return p3.n.f5026a;
    }
}
