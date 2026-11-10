package q1;

import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e0 implements Comparator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5158a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Comparator f5159b;

    public e0(Comparator comparator) {
        this.f5159b = comparator;
    }

    public e0(e0 e0Var) {
        this.f5159b = e0Var;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f5158a) {
            case 0:
                int iCompare = this.f5159b.compare(obj, obj2);
                if (iCompare != 0) {
                    return iCompare;
                }
                return p1.d0.L.compare(((w1.n) obj).f6317c, ((w1.n) obj2).f6317c);
            default:
                int iCompare2 = ((e0) this.f5159b).compare(obj, obj2);
                return iCompare2 != 0 ? iCompare2 : p3.p.j(Integer.valueOf(((w1.n) obj).f6321g), Integer.valueOf(((w1.n) obj2).f6321g));
        }
    }
}
