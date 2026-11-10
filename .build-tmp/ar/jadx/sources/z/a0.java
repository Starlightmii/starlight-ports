package z;

import java.util.ArrayList;
import java.util.List;
import n1.m0;
import n1.q0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 implements n1.f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a0 f6894a = new a0();

    @Override // n1.f0
    public final n1.g0 h(n1.h0 h0Var, List list, long j5) {
        ArrayList arrayList = new ArrayList(list.size());
        Integer numValueOf = 0;
        int size = list.size();
        for (int i5 = 0; i5 < size; i5++) {
            arrayList.add(((n1.e0) list.get(i5)).c(j5));
        }
        int size2 = arrayList.size();
        Integer numValueOf2 = numValueOf;
        for (int i6 = 0; i6 < size2; i6++) {
            numValueOf2 = Integer.valueOf(Math.max(numValueOf2.intValue(), ((m0) arrayList.get(i6)).f4130d));
        }
        int iIntValue = numValueOf2.intValue();
        int size3 = arrayList.size();
        for (int i7 = 0; i7 < size3; i7++) {
            numValueOf = Integer.valueOf(Math.max(numValueOf.intValue(), ((m0) arrayList.get(i7)).f4131e));
        }
        return h0Var.F(iIntValue, numValueOf.intValue(), q3.t.f5478d, new q0(2, arrayList));
    }
}
