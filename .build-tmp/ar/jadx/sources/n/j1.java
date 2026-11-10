package n;

import f0.p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j1 extends r0.o implements p1.w {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public h1 f3944q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f3945r;

    @Override // p1.w
    public final int a0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        return this.f3945r ? e0Var.c0(Integer.MAX_VALUE) : e0Var.c0(i5);
    }

    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        boolean z2 = this.f3945r;
        p.l0 l0Var = p.l0.f4525d;
        if ((z2 ? l0Var : p.l0.f4526e) == l0Var) {
            if (k2.a.h(j5) == Integer.MAX_VALUE) {
                throw new IllegalStateException("Vertically scrollable component was measured with an infinity maximum height constraints, which is disallowed. One of the common reasons is nesting layouts like LazyColumn and Column(Modifier.verticalScroll()). If you want to add a header before the list of items please add a header as a separate item() before the main items() inside the LazyColumn scope. There are could be other reasons for this to happen: your ComposeView was added into a LinearLayout with some weight, you applied Modifier.wrapContentSize(unbounded = true) or wrote a custom layout. Please try to remove the source of infinite constraints in the hierarchy above the scrolling container.");
            }
        } else if (k2.a.i(j5) == Integer.MAX_VALUE) {
            throw new IllegalStateException("Horizontally scrollable component was measured with an infinity maximum width constraints, which is disallowed. One of the common reasons is nesting layouts like LazyRow and Row(Modifier.horizontalScroll()). If you want to add a header before the list of items please add a header as a separate item() before the main items() inside the LazyRow scope. There are could be other reasons for this to happen: your ComposeView was added into a LinearLayout with some weight, you applied Modifier.wrapContentSize(unbounded = true) or wrote a custom layout. Please try to remove the source of infinite constraints in the hierarchy above the scrolling container.");
        }
        n1.m0 m0VarC = e0Var.c(k2.a.b(j5, 0, this.f3945r ? k2.a.i(j5) : Integer.MAX_VALUE, 0, this.f3945r ? Integer.MAX_VALUE : k2.a.h(j5), 5));
        int i5 = m0VarC.f4130d;
        int i6 = k2.a.i(j5);
        if (i5 > i6) {
            i5 = i6;
        }
        int i7 = m0VarC.f4131e;
        int iH = k2.a.h(j5);
        if (i7 > iH) {
            i7 = iH;
        }
        int i8 = m0VarC.f4131e - i7;
        int i9 = m0VarC.f4130d - i5;
        if (!this.f3945r) {
            i8 = i9;
        }
        h1 h1Var = this.f3944q;
        p1 p1Var = h1Var.f3923d;
        p1 p1Var2 = h1Var.f3920a;
        p1Var.h(i8);
        p0.f fVarD = p0.r.d();
        a4.c cVarE = fVarD != null ? fVarD.e() : null;
        p0.f fVarG = p0.r.g(fVarD);
        try {
            if (p1Var2.g() > i8) {
                p1Var2.h(i8);
            }
            p0.r.j(fVarD, fVarG, cVarE);
            this.f3944q.f3921b.h(this.f3945r ? i7 : i5);
            return h0Var.F(i5, i7, q3.t.f5478d, new i1(this, i8, m0VarC));
        } catch (Throwable th) {
            p0.r.j(fVarD, fVarG, cVarE);
            throw th;
        }
    }

    @Override // p1.w
    public final int g0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        return this.f3945r ? e0Var.e0(i5) : e0Var.e0(Integer.MAX_VALUE);
    }

    @Override // p1.w
    public final int m0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        return this.f3945r ? e0Var.a0(Integer.MAX_VALUE) : e0Var.a0(i5);
    }

    @Override // p1.w
    public final int x(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        return this.f3945r ? e0Var.d(i5) : e0Var.d(Integer.MAX_VALUE);
    }
}
