package q3;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class p extends o {
    public static void T(List list, Comparator comparator) {
        b4.l.e("<this>", list);
        if (list.size() > 1) {
            Collections.sort(list, comparator);
        }
    }
}
