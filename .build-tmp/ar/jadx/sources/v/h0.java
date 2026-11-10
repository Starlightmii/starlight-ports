package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h0 implements n1.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c1 f5942a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5943b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e2.e0 f5944c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a4.a f5945d;

    public h0(c1 c1Var, int i5, e2.e0 e0Var, a4.a aVar) {
        this.f5942a = c1Var;
        this.f5943b = i5;
        this.f5944c = e0Var;
        this.f5945d = aVar;
    }

    @Override // n1.r
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        long j6;
        if (e0Var.c0(k2.a.h(j5)) < k2.a.i(j5)) {
            j6 = j5;
        } else {
            j6 = j5;
            j5 = k2.a.b(j6, 0, Integer.MAX_VALUE, 0, 0, 13);
        }
        n1.m0 m0VarC = e0Var.c(j5);
        int iMin = Math.min(m0VarC.f4130d, k2.a.i(j6));
        return h0Var.F(iMin, m0VarC.f4131e, q3.t.f5478d, new r.r0(h0Var, this, m0VarC, iMin, 1));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h0)) {
            return false;
        }
        h0 h0Var = (h0) obj;
        return b4.l.a(this.f5942a, h0Var.f5942a) && this.f5943b == h0Var.f5943b && b4.l.a(this.f5944c, h0Var.f5944c) && b4.l.a(this.f5945d, h0Var.f5945d);
    }

    public final int hashCode() {
        return this.f5945d.hashCode() + ((this.f5944c.hashCode() + m.h.b(this.f5943b, this.f5942a.hashCode() * 31, 31)) * 31);
    }

    public final String toString() {
        return "HorizontalScrollLayoutModifier(scrollerPosition=" + this.f5942a + ", cursorOffset=" + this.f5943b + ", transformedText=" + this.f5944c + ", textLayoutResultProvider=" + this.f5945d + ')';
    }
}
