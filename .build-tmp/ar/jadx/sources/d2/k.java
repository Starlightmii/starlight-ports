package d2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k implements Comparable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final k f1612e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final k f1613f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final k f1614g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1615d;

    static {
        k kVar = new k(100);
        k kVar2 = new k(200);
        k kVar3 = new k(300);
        k kVar4 = new k(400);
        k kVar5 = new k(500);
        k kVar6 = new k(600);
        f1612e = kVar6;
        k kVar7 = new k(700);
        k kVar8 = new k(800);
        k kVar9 = new k(900);
        f1613f = kVar4;
        f1614g = kVar5;
        p3.j.H(kVar, kVar2, kVar3, kVar4, kVar5, kVar6, kVar7, kVar8, kVar9);
    }

    public k(int i5) {
        this.f1615d = i5;
        if (1 > i5 || i5 >= 1001) {
            throw new IllegalArgumentException(("Font weight can be in range [1, 1000]. Current value: " + i5).toString());
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return b4.l.f(this.f1615d, ((k) obj).f1615d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof k) {
            return this.f1615d == ((k) obj).f1615d;
        }
        return false;
    }

    public final int hashCode() {
        return this.f1615d;
    }

    public final String toString() {
        return b.b.f(new StringBuilder("FontWeight(weight="), this.f1615d, ')');
    }
}
