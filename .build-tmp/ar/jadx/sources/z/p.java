package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ e2.l f6992e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f6993f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f6994g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ w0.s f6995h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f6996i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(e2.l lVar, int i5, int i6, w0.s sVar, p3.d dVar) {
        super(0);
        this.f6992e = lVar;
        this.f6993f = i5;
        this.f6994g = i6;
        this.f6995h = sVar;
        this.f6996i = dVar;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, p3.d] */
    @Override // a4.a
    public final Object a() {
        e2.l lVar = this.f6992e;
        y1.e0 e0Var = (y1.e0) lVar.f2036e;
        int iIntValue = ((Number) this.f6996i.getValue()).intValue();
        w0.s sVar = this.f6995h;
        boolean z2 = sVar.f6266b;
        boolean z3 = sVar.c() == 1;
        int i5 = this.f6993f;
        long jK = e0Var.k(i5);
        int i6 = y1.g0.f6769c;
        int iH = (int) (jK >> 32);
        int iE = e0Var.e(iH);
        int i7 = e0Var.f6754b.f6794f;
        if (iE != iIntValue) {
            iH = iIntValue >= i7 ? e0Var.h(i7 - 1) : e0Var.h(iIntValue);
        }
        int iD = (int) (jK & 4294967295L);
        if (e0Var.e(iD) != iIntValue) {
            iD = iIntValue >= i7 ? e0Var.d(i7 - 1, false) : e0Var.d(iIntValue, false);
        }
        int i8 = this.f6994g;
        if (iH == i8) {
            return lVar.a(iD);
        }
        if (iD == i8) {
            return lVar.a(iH);
        }
        if (!(z2 ^ z3) ? i5 >= iH : i5 > iD) {
            iH = iD;
        }
        return lVar.a(iH);
    }
}
