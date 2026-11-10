package b4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f691a;

    public p(Class cls) {
        this.f691a = cls;
    }

    @Override // b4.e
    public final Class a() {
        return this.f691a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof p) {
            return l.a(this.f691a, ((p) obj).f691a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f691a.hashCode();
    }

    public final String toString() {
        return this.f691a.toString() + " (Kotlin reflection is not available)";
    }
}
