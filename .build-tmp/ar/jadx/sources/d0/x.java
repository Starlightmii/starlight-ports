package d0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r0.g f1591a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1592b;

    public x(r0.g gVar, int i5) {
        this.f1591a = gVar;
        this.f1592b = i5;
    }

    @Override // d0.j
    public final int a(k2.i iVar, long j5, int i5) {
        int i6 = (int) (j5 & 4294967295L);
        int i7 = this.f1592b;
        if (i5 < i6 - (i7 * 2)) {
            return d4.a.m(this.f1591a.a(i5, i6), i7, (i6 - i7) - i5);
        }
        return Math.round((1 + 0.0f) * ((i6 - i5) / 2.0f));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        return this.f1591a.equals(xVar.f1591a) && this.f1592b == xVar.f1592b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f1592b) + (Float.hashCode(this.f1591a.f5672a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Vertical(alignment=");
        sb.append(this.f1591a);
        sb.append(", margin=");
        return b.b.f(sb, this.f1592b, ')');
    }
}
