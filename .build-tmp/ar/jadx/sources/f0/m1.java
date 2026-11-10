package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2273a;

    public m1(String str) {
        this.f2273a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof m1) && b4.l.a(this.f2273a, ((m1) obj).f2273a);
    }

    public final int hashCode() {
        return this.f2273a.hashCode();
    }

    public final String toString() {
        return "OpaqueKey(key=" + this.f2273a + ')';
    }
}
