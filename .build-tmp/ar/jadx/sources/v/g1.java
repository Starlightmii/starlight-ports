package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g1 implements n1.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c1 f5929a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5930b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e2.e0 f5931c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a4.a f5932d;

    public g1(c1 c1Var, int i5, e2.e0 e0Var, a4.a aVar) {
        this.f5929a = c1Var;
        this.f5930b = i5;
        this.f5931c = e0Var;
        this.f5932d = aVar;
    }

    @Override // n1.r
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        n1.m0 m0VarC = e0Var.c(k2.a.b(j5, 0, 0, 0, Integer.MAX_VALUE, 7));
        int iMin = Math.min(m0VarC.f4131e, k2.a.h(j5));
        return h0Var.F(m0VarC.f4130d, iMin, q3.t.f5478d, new r.r0(h0Var, this, m0VarC, iMin, 2));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g1)) {
            return false;
        }
        g1 g1Var = (g1) obj;
        return b4.l.a(this.f5929a, g1Var.f5929a) && this.f5930b == g1Var.f5930b && b4.l.a(this.f5931c, g1Var.f5931c) && b4.l.a(this.f5932d, g1Var.f5932d);
    }

    public final int hashCode() {
        return this.f5932d.hashCode() + ((this.f5931c.hashCode() + m.h.b(this.f5930b, this.f5929a.hashCode() * 31, 31)) * 31);
    }

    public final String toString() {
        return "VerticalScrollLayoutModifier(scrollerPosition=" + this.f5929a + ", cursorOffset=" + this.f5930b + ", transformedText=" + this.f5931c + ", textLayoutResultProvider=" + this.f5932d + ')';
    }
}
