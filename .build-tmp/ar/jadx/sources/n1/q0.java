package n1;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4148e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ ArrayList f4149f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q0(int i5, ArrayList arrayList) {
        super(1);
        this.f4148e = i5;
        this.f4149f = arrayList;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f4148e) {
            case 0:
                l0 l0Var = (l0) obj;
                ArrayList arrayList = this.f4149f;
                int size = arrayList.size();
                for (int i5 = 0; i5 < size; i5++) {
                    l0.g(l0Var, (m0) arrayList.get(i5), 0, 0);
                }
                break;
            case 1:
                l0 l0Var2 = (l0) obj;
                ArrayList arrayList2 = this.f4149f;
                int iZ = p3.j.z(arrayList2);
                if (iZ >= 0) {
                    int i6 = 0;
                    while (true) {
                        l0.f(l0Var2, (m0) arrayList2.get(i6), 0, 0);
                        if (i6 != iZ) {
                            i6++;
                        }
                    }
                }
                break;
            default:
                l0 l0Var3 = (l0) obj;
                ArrayList arrayList3 = this.f4149f;
                int size2 = arrayList3.size();
                for (int i7 = 0; i7 < size2; i7++) {
                    l0.d(l0Var3, (m0) arrayList3.get(i7), 0, 0);
                }
                break;
        }
        return p3.n.f5026a;
    }
}
