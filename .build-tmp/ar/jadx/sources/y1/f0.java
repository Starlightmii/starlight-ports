package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a0 f6763a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a0 f6764b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a0 f6765c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a0 f6766d;

    public f0(a0 a0Var, a0 a0Var2, a0 a0Var3, a0 a0Var4) {
        this.f6763a = a0Var;
        this.f6764b = a0Var2;
        this.f6765c = a0Var3;
        this.f6766d = a0Var4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof f0)) {
            return false;
        }
        f0 f0Var = (f0) obj;
        return b4.l.a(this.f6763a, f0Var.f6763a) && b4.l.a(this.f6764b, f0Var.f6764b) && b4.l.a(this.f6765c, f0Var.f6765c) && b4.l.a(this.f6766d, f0Var.f6766d);
    }

    public final int hashCode() {
        a0 a0Var = this.f6763a;
        int iHashCode = (a0Var != null ? a0Var.hashCode() : 0) * 31;
        a0 a0Var2 = this.f6764b;
        int iHashCode2 = (iHashCode + (a0Var2 != null ? a0Var2.hashCode() : 0)) * 31;
        a0 a0Var3 = this.f6765c;
        int iHashCode3 = (iHashCode2 + (a0Var3 != null ? a0Var3.hashCode() : 0)) * 31;
        a0 a0Var4 = this.f6766d;
        return iHashCode3 + (a0Var4 != null ? a0Var4.hashCode() : 0);
    }
}
