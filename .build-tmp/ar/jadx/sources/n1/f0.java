package n1;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface f0 {
    default int b(m mVar, List list, int i5) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i6 = 0; i6 < size; i6++) {
            int i7 = 1;
            arrayList.add(new j((e0) list.get(i6), i7, i7, 0));
        }
        return h(new o(mVar, mVar.getLayoutDirection()), arrayList, h1.c.c(0, i5, 7)).l();
    }

    default int d(m mVar, List list, int i5) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i6 = 0; i6 < size; i6++) {
            arrayList.add(new j((e0) list.get(i6), 2, 1, 0));
        }
        return h(new o(mVar, mVar.getLayoutDirection()), arrayList, h1.c.c(0, i5, 7)).l();
    }

    g0 h(h0 h0Var, List list, long j5);

    default int i(m mVar, List list, int i5) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i6 = 0; i6 < size; i6++) {
            arrayList.add(new j((e0) list.get(i6), 1, 2, 0));
        }
        return h(new o(mVar, mVar.getLayoutDirection()), arrayList, h1.c.c(i5, 0, 13)).e();
    }

    default int j(m mVar, List list, int i5) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i6 = 0; i6 < size; i6++) {
            int i7 = 2;
            arrayList.add(new j((e0) list.get(i6), i7, i7, 0));
        }
        return h(new o(mVar, mVar.getLayoutDirection()), arrayList, h1.c.c(i5, 0, 13)).e();
    }
}
