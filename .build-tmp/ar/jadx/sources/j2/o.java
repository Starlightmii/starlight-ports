package j2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o f3331c = new o(d4.a.w(0), d4.a.w(0));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3332a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f3333b;

    public o(long j5, long j6) {
        this.f3332a = j5;
        this.f3333b = j6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        return k2.m.a(this.f3332a, oVar.f3332a) && k2.m.a(this.f3333b, oVar.f3333b);
    }

    public final int hashCode() {
        k2.n[] nVarArr = k2.m.f3420b;
        return Long.hashCode(this.f3333b) + (Long.hashCode(this.f3332a) * 31);
    }

    public final String toString() {
        return "TextIndent(firstLine=" + ((Object) k2.m.d(this.f3332a)) + ", restLine=" + ((Object) k2.m.d(this.f3333b)) + ')';
    }
}
