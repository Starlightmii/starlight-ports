package p1;

import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c1 implements Comparator {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c1 f4777b = new c1(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4778a;

    public /* synthetic */ c1(int i5) {
        this.f4778a = i5;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f4778a) {
            case 0:
                d0 d0Var = (d0) obj;
                d0 d0Var2 = (d0) obj2;
                int iF = b4.l.f(d0Var2.f4789m, d0Var.f4789m);
                return iF != 0 ? iF : b4.l.f(d0Var.hashCode(), d0Var2.hashCode());
            default:
                d0 d0Var3 = (d0) obj;
                d0 d0Var4 = (d0) obj2;
                int iF2 = b4.l.f(d0Var3.f4789m, d0Var4.f4789m);
                return iF2 != 0 ? iF2 : b4.l.f(d0Var3.hashCode(), d0Var4.hashCode());
        }
    }
}
