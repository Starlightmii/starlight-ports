package b3;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f611d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f612e;

    public h(List list, int i5, Throwable th) {
        p3.p.h("initCallbacks cannot be null", list);
        this.f611d = new ArrayList(list);
        this.f612e = i5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayList = this.f611d;
        int size = arrayList.size();
        int i5 = 0;
        if (this.f612e != 1) {
            while (i5 < size) {
                ((g2.h) arrayList.get(i5)).f2541b.f31e = g2.k.f2544a;
                i5++;
            }
            return;
        }
        while (i5 < size) {
            g2.h hVar = (g2.h) arrayList.get(i5);
            hVar.f2540a.setValue(Boolean.TRUE);
            hVar.f2541b.f31e = new g2.l(true);
            i5++;
        }
    }
}
