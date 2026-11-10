package c0;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m2 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ArrayList f1050e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ ArrayList f1051f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ ArrayList f1052g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ ArrayList f1053h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ i0 f1054i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f1055j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ int f1056k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r.z0 f1057l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n1.z0 f1058m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f1059n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f1060o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Integer f1061p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ ArrayList f1062q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Integer f1063r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m2(ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, i0 i0Var, int i5, int i6, r.z0 z0Var, n1.z0 z0Var2, int i7, int i8, Integer num, ArrayList arrayList5, Integer num2) {
        super(1);
        this.f1050e = arrayList;
        this.f1051f = arrayList2;
        this.f1052g = arrayList3;
        this.f1053h = arrayList4;
        this.f1054i = i0Var;
        this.f1055j = i5;
        this.f1056k = i6;
        this.f1057l = z0Var;
        this.f1058m = z0Var2;
        this.f1059n = i7;
        this.f1060o = i8;
        this.f1061p = num;
        this.f1062q = arrayList5;
        this.f1063r = num2;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        int i5;
        n1.l0 l0Var = (n1.l0) obj;
        ArrayList arrayList = this.f1050e;
        int size = arrayList.size();
        for (int i6 = 0; i6 < size; i6++) {
            n1.l0.d(l0Var, (n1.m0) arrayList.get(i6), 0, 0);
        }
        ArrayList arrayList2 = this.f1051f;
        int size2 = arrayList2.size();
        for (int i7 = 0; i7 < size2; i7++) {
            n1.l0.d(l0Var, (n1.m0) arrayList2.get(i7), 0, 0);
        }
        ArrayList arrayList3 = this.f1052g;
        int size3 = arrayList3.size();
        int i8 = 0;
        while (true) {
            i5 = this.f1059n;
            if (i8 >= size3) {
                break;
            }
            n1.m0 m0Var = (n1.m0) arrayList3.get(i8);
            int i9 = (this.f1055j - this.f1056k) / 2;
            n1.z0 z0Var = this.f1058m;
            n1.l0.d(l0Var, m0Var, this.f1057l.d(z0Var, z0Var.getLayoutDirection()) + i9, i5 - this.f1060o);
            i8++;
        }
        ArrayList arrayList4 = this.f1053h;
        int size4 = arrayList4.size();
        for (int i10 = 0; i10 < size4; i10++) {
            n1.m0 m0Var2 = (n1.m0) arrayList4.get(i10);
            Integer num = this.f1061p;
            n1.l0.d(l0Var, m0Var2, 0, i5 - (num != null ? num.intValue() : 0));
        }
        i0 i0Var = this.f1054i;
        if (i0Var != null) {
            ArrayList arrayList5 = this.f1062q;
            int size5 = arrayList5.size();
            for (int i11 = 0; i11 < size5; i11++) {
                n1.m0 m0Var3 = (n1.m0) arrayList5.get(i11);
                int i12 = i0Var.f917a;
                Integer num2 = this.f1063r;
                b4.l.b(num2);
                n1.l0.d(l0Var, m0Var3, i12, i5 - num2.intValue());
            }
        }
        return p3.n.f5026a;
    }
}
