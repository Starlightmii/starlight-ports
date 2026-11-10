package d0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r0.g f1510a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r0.g f1511b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1512c;

    public b(r0.g gVar, r0.g gVar2, int i5) {
        this.f1510a = gVar;
        this.f1511b = gVar2;
        this.f1512c = i5;
    }

    @Override // d0.j
    public final int a(k2.i iVar, long j5, int i5) {
        int iA = this.f1511b.a(0, iVar.a());
        return iVar.f3412b + iA + (-this.f1510a.a(0, i5)) + this.f1512c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f1510a.equals(bVar.f1510a) && this.f1511b.equals(bVar.f1511b) && this.f1512c == bVar.f1512c;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f1512c) + b.b.a(this.f1511b.f5672a, Float.hashCode(this.f1510a.f5672a) * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Vertical(menuAlignment=");
        sb.append(this.f1510a);
        sb.append(", anchorAlignment=");
        sb.append(this.f1511b);
        sb.append(", offset=");
        return b.b.f(sb, this.f1512c, ')');
    }
}
