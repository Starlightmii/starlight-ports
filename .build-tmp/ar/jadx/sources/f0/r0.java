package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r0 implements j3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1 f2301a;

    public r0(s1 s1Var) {
        this.f2301a = s1Var;
    }

    @Override // f0.j3
    public final Object a(w1 w1Var) {
        return this.f2301a.getValue();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof r0) && this.f2301a.equals(((r0) obj).f2301a);
    }

    public final int hashCode() {
        return this.f2301a.hashCode();
    }

    public final String toString() {
        return "DynamicValueHolder(state=" + this.f2301a + ')';
    }
}
