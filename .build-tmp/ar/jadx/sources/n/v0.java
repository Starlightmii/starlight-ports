package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f4034a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r.m0 f4035b;

    public v0() {
        long jD = y0.l0.d(4284900966L);
        float f5 = 0;
        r.m0 m0Var = new r.m0(f5, f5, f5, f5);
        this.f4034a = jD;
        this.f4035b = m0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!v0.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        b4.l.c("null cannot be cast to non-null type androidx.compose.foundation.OverscrollConfiguration", obj);
        v0 v0Var = (v0) obj;
        return y0.u.c(this.f4034a, v0Var.f4034a) && b4.l.a(this.f4035b, v0Var.f4035b);
    }

    public final int hashCode() {
        int i5 = y0.u.f6695h;
        return this.f4035b.hashCode() + (Long.hashCode(this.f4034a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("OverscrollConfiguration(glowColor=");
        b.b.j(this.f4034a, sb, ", drawPadding=");
        sb.append(this.f4035b);
        sb.append(')');
        return sb.toString();
    }
}
