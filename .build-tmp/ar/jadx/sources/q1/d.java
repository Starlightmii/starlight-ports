package q1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static d f5148e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public y1.e0 f5149c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public w1.n f5150d;

    @Override // q1.b
    public final int[] a(int i5) {
        int iC;
        if (c().length() <= 0 || i5 >= c().length()) {
            return null;
        }
        try {
            w1.n nVar = this.f5150d;
            if (nVar == null) {
                b4.l.j("node");
                throw null;
            }
            int iRound = Math.round(nVar.e().b());
            if (i5 <= 0) {
                i5 = 0;
            }
            y1.e0 e0Var = this.f5149c;
            if (e0Var == null) {
                b4.l.j("layoutResult");
                throw null;
            }
            int iE = e0Var.e(i5);
            y1.e0 e0Var2 = this.f5149c;
            if (e0Var2 == null) {
                b4.l.j("layoutResult");
                throw null;
            }
            float fD = e0Var2.f6754b.d(iE) + iRound;
            y1.e0 e0Var3 = this.f5149c;
            if (e0Var3 == null) {
                b4.l.j("layoutResult");
                throw null;
            }
            if (e0Var3 == null) {
                b4.l.j("layoutResult");
                throw null;
            }
            y1.m mVar = e0Var3.f6754b;
            if (fD < mVar.d(mVar.f6794f - 1)) {
                y1.e0 e0Var4 = this.f5149c;
                if (e0Var4 == null) {
                    b4.l.j("layoutResult");
                    throw null;
                }
                iC = e0Var4.f6754b.c(fD);
            } else {
                y1.e0 e0Var5 = this.f5149c;
                if (e0Var5 == null) {
                    b4.l.j("layoutResult");
                    throw null;
                }
                iC = e0Var5.f6754b.f6794f;
            }
            return b(i5, e(iC - 1, j2.h.f3318d) + 1);
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    @Override // q1.b
    public final int[] d(int i5) {
        int iC;
        if (c().length() <= 0 || i5 <= 0) {
            return null;
        }
        try {
            w1.n nVar = this.f5150d;
            if (nVar == null) {
                b4.l.j("node");
                throw null;
            }
            int iRound = Math.round(nVar.e().b());
            int length = c().length();
            if (length <= i5) {
                i5 = length;
            }
            y1.e0 e0Var = this.f5149c;
            if (e0Var == null) {
                b4.l.j("layoutResult");
                throw null;
            }
            int iE = e0Var.e(i5);
            y1.e0 e0Var2 = this.f5149c;
            if (e0Var2 == null) {
                b4.l.j("layoutResult");
                throw null;
            }
            float fD = e0Var2.f6754b.d(iE) - iRound;
            if (fD > 0.0f) {
                y1.e0 e0Var3 = this.f5149c;
                if (e0Var3 == null) {
                    b4.l.j("layoutResult");
                    throw null;
                }
                iC = e0Var3.f6754b.c(fD);
            } else {
                iC = 0;
            }
            if (i5 == c().length() && iC < iE) {
                iC++;
            }
            return b(e(iC, j2.h.f3319e), i5);
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    public final int e(int i5, j2.h hVar) {
        y1.e0 e0Var = this.f5149c;
        if (e0Var == null) {
            b4.l.j("layoutResult");
            throw null;
        }
        int iH = e0Var.h(i5);
        y1.e0 e0Var2 = this.f5149c;
        if (e0Var2 == null) {
            b4.l.j("layoutResult");
            throw null;
        }
        if (hVar != e0Var2.i(iH)) {
            y1.e0 e0Var3 = this.f5149c;
            if (e0Var3 != null) {
                return e0Var3.h(i5);
            }
            b4.l.j("layoutResult");
            throw null;
        }
        y1.e0 e0Var4 = this.f5149c;
        if (e0Var4 != null) {
            return e0Var4.d(i5, false) - 1;
        }
        b4.l.j("layoutResult");
        throw null;
    }
}
