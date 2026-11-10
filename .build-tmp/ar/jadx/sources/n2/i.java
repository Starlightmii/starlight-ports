package n2;

import java.util.ArrayList;
import java.util.List;
import n1.e0;
import n1.f0;
import n1.g0;
import n1.h0;
import n1.m0;
import n1.p0;
import n1.q0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i implements f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f4225a = new i();

    @Override // n1.f0
    public final g0 h(h0 h0Var, List list, long j5) {
        int iMax;
        int size = list.size();
        q3.t tVar = q3.t.f5478d;
        int i5 = 0;
        if (size == 0) {
            return h0Var.F(0, 0, tVar, e.f4212i);
        }
        if (size == 1) {
            m0 m0VarC = ((e0) list.get(0)).c(j5);
            return h0Var.F(m0VarC.f4130d, m0VarC.f4131e, tVar, new p0(m0VarC, 1));
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size2 = list.size();
        for (int i6 = 0; i6 < size2; i6++) {
            arrayList.add(((e0) list.get(i6)).c(j5));
        }
        int iZ = p3.j.z(arrayList);
        if (iZ >= 0) {
            int iMax2 = 0;
            iMax = 0;
            while (true) {
                m0 m0Var = (m0) arrayList.get(i5);
                iMax2 = Math.max(iMax2, m0Var.f4130d);
                iMax = Math.max(iMax, m0Var.f4131e);
                if (i5 == iZ) {
                    break;
                }
                i5++;
            }
            i5 = iMax2;
        } else {
            iMax = 0;
        }
        return h0Var.F(i5, iMax, tVar, new q0(1, arrayList));
    }
}
