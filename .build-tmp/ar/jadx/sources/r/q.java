package r;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ n1.m0[] f5607e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ List f5608f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ n1.h0 f5609g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ b4.u f5610h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ b4.u f5611i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ r f5612j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(n1.m0[] m0VarArr, List list, n1.h0 h0Var, b4.u uVar, b4.u uVar2, r rVar) {
        super(1);
        this.f5607e = m0VarArr;
        this.f5608f = list;
        this.f5609g = h0Var;
        this.f5610h = uVar;
        this.f5611i = uVar2;
        this.f5612j = rVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        n1.l0 l0Var = (n1.l0) obj;
        n1.m0[] m0VarArr = this.f5607e;
        int length = m0VarArr.length;
        int i5 = 0;
        int i6 = 0;
        while (i6 < length) {
            int i7 = i5;
            n1.m0 m0Var = m0VarArr[i6];
            b4.l.c("null cannot be cast to non-null type androidx.compose.ui.layout.Placeable", m0Var);
            o.b(l0Var, m0Var, (n1.e0) this.f5608f.get(i7), this.f5609g.getLayoutDirection(), this.f5610h.f695d, this.f5611i.f695d, this.f5612j.f5614a);
            i6++;
            i5 = i7 + 1;
        }
        return p3.n.f5026a;
    }
}
