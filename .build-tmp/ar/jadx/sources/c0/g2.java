package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f872a = y0.u.f6694g;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof g2) {
            return y0.u.c(this.f872a, ((g2) obj).f872a);
        }
        return false;
    }

    public final int hashCode() {
        int i5 = y0.u.f6695h;
        return Long.hashCode(this.f872a) * 31;
    }

    public final String toString() {
        return "RippleConfiguration(color=" + ((Object) y0.u.i(this.f872a)) + ", rippleAlpha=null)";
    }
}
