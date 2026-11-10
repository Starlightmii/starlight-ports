package e2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y1.f f2062a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f2063b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y1.g0 f2064c;

    public x(String str, long j5, int i5) {
        this(new y1.f((i5 & 1) != 0 ? "" : str, null, 6), (i5 & 2) != 0 ? y1.g0.f6768b : j5, (y1.g0) null);
    }

    public x(y1.f fVar, long j5, y1.g0 g0Var) {
        this.f2062a = fVar;
        this.f2063b = p3.j.o(j5, fVar.f6759d.length());
        this.f2064c = g0Var != null ? new y1.g0(p3.j.o(g0Var.f6770a, fVar.f6759d.length())) : null;
    }

    public static x a(x xVar, y1.f fVar, long j5, int i5) {
        if ((i5 & 1) != 0) {
            fVar = xVar.f2062a;
        }
        if ((i5 & 2) != 0) {
            j5 = xVar.f2063b;
        }
        y1.g0 g0Var = (i5 & 4) != 0 ? xVar.f2064c : null;
        xVar.getClass();
        return new x(fVar, j5, g0Var);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        return y1.g0.a(this.f2063b, xVar.f2063b) && b4.l.a(this.f2064c, xVar.f2064c) && b4.l.a(this.f2062a, xVar.f2062a);
    }

    public final int hashCode() {
        int iHashCode = this.f2062a.hashCode() * 31;
        int i5 = y1.g0.f6769c;
        int iB = b.b.b(iHashCode, 31, this.f2063b);
        y1.g0 g0Var = this.f2064c;
        return iB + (g0Var != null ? Long.hashCode(g0Var.f6770a) : 0);
    }

    public final String toString() {
        return "TextFieldValue(text='" + ((Object) this.f2062a) + "', selection=" + ((Object) y1.g0.g(this.f2063b)) + ", composition=" + this.f2064c + ')';
    }
}
