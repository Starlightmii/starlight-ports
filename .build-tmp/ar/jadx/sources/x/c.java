package x;

import android.graphics.Rect;
import c0.g1;
import i4.h1;
import java.lang.ref.WeakReference;
import q1.j1;
import q1.k1;
import q1.k2;
import y1.e0;
import y1.g0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements e2.s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public q f6463a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public h1 f6464b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public u f6465c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public l4.t f6466d;

    @Override // e2.s
    public final void a(x0.d dVar) {
        Rect rect;
        u uVar = this.f6465c;
        if (uVar != null) {
            uVar.f6512l = new Rect(d4.a.H(dVar.f6534a), d4.a.H(dVar.f6535b), d4.a.H(dVar.f6536c), d4.a.H(dVar.f6537d));
            if (!uVar.f6510j.isEmpty() || (rect = uVar.f6512l) == null) {
                return;
            }
            uVar.f6501a.requestRectangleOnScreen(new Rect(rect));
        }
    }

    @Override // e2.s
    public final void b(e2.x xVar, e2.r rVar, e0 e0Var, g1 g1Var, x0.d dVar, x0.d dVar2) {
        u uVar = this.f6465c;
        if (uVar != null) {
            r rVar2 = uVar.f6513m;
            synchronized (rVar2.f6484c) {
                try {
                    rVar2.f6491j = xVar;
                    rVar2.f6493l = rVar;
                    rVar2.f6492k = e0Var;
                    rVar2.f6494m = dVar;
                    rVar2.f6495n = dVar2;
                    if (rVar2.f6486e || rVar2.f6485d) {
                        rVar2.a();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // e2.s
    public final void c() {
        j(null);
    }

    @Override // e2.s
    public final void d() {
        k2 k2Var;
        q qVar = this.f6463a;
        if (qVar == null || (k2Var = (k2) p1.f.h(qVar, j1.f5245n)) == null) {
            return;
        }
        ((k1) k2Var).b();
    }

    @Override // e2.s
    public final void e(e2.x xVar, e2.n nVar, c.b bVar, v.q qVar) {
        j(new n2.b(xVar, this, nVar, bVar, qVar, 2));
    }

    @Override // e2.s
    public final void f() {
        k2 k2Var;
        q qVar = this.f6463a;
        if (qVar == null || (k2Var = (k2) p1.f.h(qVar, j1.f5245n)) == null) {
            return;
        }
        ((k1) k2Var).a();
    }

    @Override // e2.s
    public final void g() {
        h1 h1Var = this.f6464b;
        if (h1Var != null) {
            h1Var.b(null);
        }
        this.f6464b = null;
        l4.o oVarI = i();
        if (oVarI != null) {
            l4.t tVar = (l4.t) oVarI;
            synchronized (tVar) {
                tVar.u(tVar.o() + ((long) tVar.f3584n), tVar.f3583m, tVar.o() + ((long) tVar.f3584n), tVar.o() + ((long) tVar.f3584n) + ((long) tVar.f3585o));
            }
        }
    }

    @Override // e2.s
    public final void h(e2.x xVar, e2.x xVar2) {
        u uVar = this.f6465c;
        if (uVar != null) {
            boolean z2 = (g0.a(uVar.f6508h.f2063b, xVar2.f2063b) && b4.l.a(uVar.f6508h.f2064c, xVar2.f2064c)) ? false : true;
            uVar.f6508h = xVar2;
            int size = uVar.f6510j.size();
            for (int i5 = 0; i5 < size; i5++) {
                w wVar = (w) ((WeakReference) uVar.f6510j.get(i5)).get();
                if (wVar != null) {
                    wVar.f6521g = xVar2;
                }
            }
            r rVar = uVar.f6513m;
            synchronized (rVar.f6484c) {
                rVar.f6491j = null;
                rVar.f6493l = null;
                rVar.f6492k = null;
                rVar.f6494m = null;
                rVar.f6495n = null;
            }
            if (b4.l.a(xVar, xVar2)) {
                if (z2) {
                    p pVar = uVar.f6502b;
                    int iE = g0.e(xVar2.f2063b);
                    int iD = g0.d(xVar2.f2063b);
                    g0 g0Var = uVar.f6508h.f2064c;
                    int iE2 = g0Var != null ? g0.e(g0Var.f6770a) : -1;
                    g0 g0Var2 = uVar.f6508h.f2064c;
                    pVar.a().updateSelection(pVar.f6477b, iE, iD, iE2, g0Var2 != null ? g0.d(g0Var2.f6770a) : -1);
                    return;
                }
                return;
            }
            if (xVar != null && (!b4.l.a(xVar.f2062a.f6759d, xVar2.f2062a.f6759d) || (g0.a(xVar.f2063b, xVar2.f2063b) && !b4.l.a(xVar.f2064c, xVar2.f2064c)))) {
                p pVar2 = uVar.f6502b;
                pVar2.a().restartInput(pVar2.f6477b);
                return;
            }
            int size2 = uVar.f6510j.size();
            for (int i6 = 0; i6 < size2; i6++) {
                w wVar2 = (w) ((WeakReference) uVar.f6510j.get(i6)).get();
                if (wVar2 != null) {
                    e2.x xVar3 = uVar.f6508h;
                    p pVar3 = uVar.f6502b;
                    if (wVar2.f6525k) {
                        wVar2.f6521g = xVar3;
                        if (wVar2.f6523i) {
                            pVar3.a().updateExtractedText(pVar3.f6477b, wVar2.f6522h, p3.j.j(xVar3));
                        }
                        g0 g0Var3 = xVar3.f2064c;
                        long j5 = xVar3.f2063b;
                        int iE3 = g0Var3 != null ? g0.e(g0Var3.f6770a) : -1;
                        g0 g0Var4 = xVar3.f2064c;
                        pVar3.a().updateSelection(pVar3.f6477b, g0.e(j5), g0.d(j5), iE3, g0Var4 != null ? g0.d(g0Var4.f6770a) : -1);
                    }
                }
            }
        }
    }

    public final l4.o i() {
        l4.t tVar = this.f6466d;
        if (tVar != null) {
            return tVar;
        }
        if (!w.c.f6222a) {
            return null;
        }
        l4.t tVarA = l4.u.a(2, k4.a.f3437f);
        this.f6466d = tVarA;
        return tVarA;
    }

    public final void j(n2.b bVar) {
        q qVar = this.f6463a;
        if (qVar == null) {
            return;
        }
        h1 h1Var = null;
        this.f6464b = qVar.f5693p ? i4.y.n(qVar.n0(), null, new b0.t(qVar, new l4.n(bVar, this, qVar, h1Var, 7), h1Var, 22), 1) : null;
    }

    public final void k(q qVar) {
        if (this.f6463a == qVar) {
            this.f6463a = null;
            return;
        }
        throw new IllegalStateException(("Expected textInputModifierNode to be " + qVar + " but was " + this.f6463a).toString());
    }
}
