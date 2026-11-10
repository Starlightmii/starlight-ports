package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f940a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n0.d f941b;

    public j0(z2 z2Var, n0.d dVar) {
        this.f940a = z2Var;
        this.f941b = dVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j0)) {
            return false;
        }
        j0 j0Var = (j0) obj;
        return b4.l.a(this.f940a, j0Var.f940a) && this.f941b.equals(j0Var.f941b);
    }

    public final int hashCode() {
        Object obj = this.f940a;
        return this.f941b.hashCode() + ((obj == null ? 0 : obj.hashCode()) * 31);
    }

    public final String toString() {
        return "FadeInFadeOutAnimationItem(key=" + this.f940a + ", transition=" + this.f941b + ')';
    }
}
