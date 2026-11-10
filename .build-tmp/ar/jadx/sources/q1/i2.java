package q1;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w1.i f5228a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j.s f5229b;

    public i2(w1.n nVar, j.r rVar) {
        this.f5228a = nVar.f6318d;
        int[] iArr = j.k.f3116a;
        this.f5229b = new j.s();
        List listH = w1.n.h(nVar, 4);
        int size = listH.size();
        for (int i5 = 0; i5 < size; i5++) {
            w1.n nVar2 = (w1.n) listH.get(i5);
            if (rVar.b(nVar2.f6321g)) {
                this.f5229b.a(nVar2.f6321g);
            }
        }
    }
}
