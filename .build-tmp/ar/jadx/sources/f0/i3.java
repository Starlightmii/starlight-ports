package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i3 implements j3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2202a;

    public i3(Object obj) {
        this.f2202a = obj;
    }

    @Override // f0.j3
    public final Object a(w1 w1Var) {
        return this.f2202a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof i3) && b4.l.a(this.f2202a, ((i3) obj).f2202a);
    }

    public final int hashCode() {
        Object obj = this.f2202a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        return "StaticValueHolder(value=" + this.f2202a + ')';
    }
}
