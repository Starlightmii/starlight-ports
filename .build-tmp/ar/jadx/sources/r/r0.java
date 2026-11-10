package r;

import v.g1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5616e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f5617f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f5618g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f5619h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f5620i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r0(n1.h0 h0Var, n1.r rVar, n1.m0 m0Var, int i5, int i6) {
        super(1);
        this.f5616e = i6;
        this.f5618g = h0Var;
        this.f5619h = rVar;
        this.f5620i = m0Var;
        this.f5617f = i5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r0(n1.m0[] m0VarArr, s0 s0Var, int i5, int[] iArr) {
        super(1);
        this.f5616e = 0;
        this.f5618g = m0VarArr;
        this.f5619h = s0Var;
        this.f5617f = i5;
        this.f5620i = iArr;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f5616e) {
            case 0:
                n1.l0 l0Var = (n1.l0) obj;
                n1.m0[] m0VarArr = (n1.m0[]) this.f5618g;
                s0 s0Var = (s0) this.f5619h;
                int[] iArr = (int[]) this.f5620i;
                int length = m0VarArr.length;
                int i5 = 0;
                int i6 = 0;
                while (i5 < length) {
                    n1.m0 m0Var = m0VarArr[i5];
                    b4.l.b(m0Var);
                    m0Var.h();
                    n1.l0.d(l0Var, m0Var, iArr[i6], s0Var.f5622b.a(0, this.f5617f - m0Var.f4131e));
                    i5++;
                    i6++;
                }
                break;
            case 1:
                n1.l0 l0Var2 = (n1.l0) obj;
                n1.m0 m0Var2 = (n1.m0) this.f5620i;
                n1.h0 h0Var = (n1.h0) this.f5618g;
                v.h0 h0Var2 = (v.h0) this.f5619h;
                int i7 = h0Var2.f5943b;
                v.c1 c1Var = h0Var2.f5942a;
                e2.e0 e0Var = h0Var2.f5944c;
                v.e1 e1Var = (v.e1) h0Var2.f5945d.a();
                c1Var.a(p.l0.f4526e, v.l0.h(h0Var, i7, e0Var, e1Var != null ? e1Var.f5899a : null, h0Var.getLayoutDirection() == k2.k.f3417e, m0Var2.f4130d), this.f5617f, m0Var2.f4130d);
                n1.l0.f(l0Var2, m0Var2, Math.round(-c1Var.f5869a.g()), 0);
                break;
            default:
                n1.l0 l0Var3 = (n1.l0) obj;
                n1.m0 m0Var3 = (n1.m0) this.f5620i;
                n1.h0 h0Var3 = (n1.h0) this.f5618g;
                g1 g1Var = (g1) this.f5619h;
                int i8 = g1Var.f5930b;
                v.c1 c1Var2 = g1Var.f5929a;
                e2.e0 e0Var2 = g1Var.f5931c;
                v.e1 e1Var2 = (v.e1) g1Var.f5932d.a();
                c1Var2.a(p.l0.f4525d, v.l0.h(h0Var3, i8, e0Var2, e1Var2 != null ? e1Var2.f5899a : null, false, m0Var3.f4130d), this.f5617f, m0Var3.f4131e);
                n1.l0.f(l0Var3, m0Var3, 0, Math.round(-c1Var2.f5869a.g()));
                break;
        }
        return p3.n.f5026a;
    }
}
