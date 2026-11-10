package c0;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g4 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ArrayList f878e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ n1.z0 f879f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ a4.e f880g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ b4.u f881h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f882i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f883j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ a4.f f884k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ArrayList f885l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f886m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g4(ArrayList arrayList, n1.z0 z0Var, a4.e eVar, b4.u uVar, long j5, int i5, a4.f fVar, ArrayList arrayList2, int i6) {
        super(1);
        this.f878e = arrayList;
        this.f879f = z0Var;
        this.f880g = eVar;
        this.f881h = uVar;
        this.f882i = j5;
        this.f883j = i5;
        this.f884k = fVar;
        this.f885l = arrayList2;
        this.f886m = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        int i5;
        n1.l0 l0Var = (n1.l0) obj;
        ArrayList arrayList = this.f878e;
        int size = arrayList.size();
        for (int i6 = 0; i6 < size; i6++) {
            n1.l0.f(l0Var, (n1.m0) arrayList.get(i6), this.f881h.f695d * i6, 0);
        }
        j4 j4Var = j4.f955e;
        a4.e eVar = this.f880g;
        n1.z0 z0Var = this.f879f;
        List listH = z0Var.H(eVar, j4Var);
        int size2 = listH.size();
        int i7 = 0;
        while (true) {
            i5 = this.f883j;
            if (i7 >= size2) {
                break;
            }
            n1.m0 m0VarC = ((n1.e0) listH.get(i7)).c(k2.a.b(this.f882i, 0, 0, 0, 0, 11));
            n1.l0.f(l0Var, m0VarC, 0, i5 - m0VarC.f4131e);
            i7++;
        }
        List listH2 = z0Var.H(new n0.d(1621992604, true, new l(this.f884k, this.f885l)), j4.f956f);
        int size3 = listH2.size();
        for (int i8 = 0; i8 < size3; i8++) {
            n1.e0 e0Var = (n1.e0) listH2.get(i8);
            int i9 = this.f886m;
            if (i9 < 0 || i5 < 0) {
                d4.a.J("width(" + i9 + ") and height(" + i5 + ") must be >= 0");
                throw null;
            }
            n1.l0.f(l0Var, e0Var.c(h1.c.r(i9, i9, i5, i5)), 0, 0);
        }
        return p3.n.f5026a;
    }
}
