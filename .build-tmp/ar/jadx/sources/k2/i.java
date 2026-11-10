package k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final i f3410e = new i(0, 0, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3411a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3412b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3413c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f3414d;

    public i(int i5, int i6, int i7, int i8) {
        this.f3411a = i5;
        this.f3412b = i6;
        this.f3413c = i7;
        this.f3414d = i8;
    }

    public final int a() {
        return this.f3414d - this.f3412b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return this.f3411a == iVar.f3411a && this.f3412b == iVar.f3412b && this.f3413c == iVar.f3413c && this.f3414d == iVar.f3414d;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f3414d) + m.h.b(this.f3413c, m.h.b(this.f3412b, Integer.hashCode(this.f3411a) * 31, 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("IntRect.fromLTRB(");
        sb.append(this.f3411a);
        sb.append(", ");
        sb.append(this.f3412b);
        sb.append(", ");
        sb.append(this.f3413c);
        sb.append(", ");
        return b.b.f(sb, this.f3414d, ')');
    }
}
