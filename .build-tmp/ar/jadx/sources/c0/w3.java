package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w3 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f1405e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f1406f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ n1.h0 f1407g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1408h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1409i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Integer f1410j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Integer f1411k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w3(n1.m0 m0Var, n1.m0 m0Var2, n1.h0 h0Var, int i5, int i6, Integer num, Integer num2) {
        super(1);
        this.f1405e = m0Var;
        this.f1406f = m0Var2;
        this.f1407g = h0Var;
        this.f1408h = i5;
        this.f1409i = i6;
        this.f1410j = num;
        this.f1411k = num2;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        n1.l0 l0Var = (n1.l0) obj;
        n1.m0 m0Var = this.f1406f;
        int i5 = this.f1409i;
        n1.m0 m0Var2 = this.f1405e;
        if (m0Var2 != null && m0Var != null) {
            Integer num = this.f1410j;
            b4.l.b(num);
            int iIntValue = num.intValue();
            Integer num2 = this.f1411k;
            b4.l.b(num2);
            int iIntValue2 = num2.intValue();
            float f5 = iIntValue == iIntValue2 ? a4.f748c : a4.f749d;
            n1.h0 h0Var = this.f1407g;
            int iM = h0Var.M(e0.l.f1769a) + h0Var.M(f5);
            int iE = (h0Var.E(a4.f750e) + m0Var.f4131e) - iIntValue;
            int i6 = m0Var2.f4130d;
            int i7 = this.f1408h;
            int i8 = (i5 - iIntValue2) - iM;
            n1.l0.f(l0Var, m0Var2, (i7 - i6) / 2, i8);
            n1.l0.f(l0Var, m0Var, (i7 - m0Var.f4130d) / 2, i8 - iE);
        } else if (m0Var2 != null) {
            float f6 = a4.f746a;
            n1.l0.f(l0Var, m0Var2, 0, (i5 - m0Var2.f4131e) / 2);
        } else if (m0Var != null) {
            float f7 = a4.f746a;
            n1.l0.f(l0Var, m0Var, 0, (i5 - m0Var.f4131e) / 2);
        }
        return p3.n.f5026a;
    }
}
