package e2;

import android.graphics.Rect;
import android.view.Choreographer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import c0.g1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 implements s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f1975a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a1.b f1976b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b0 f1977c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1978d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b4.m f1979e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a4.c f1980f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public x f1981g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public n f1982h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f1983i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f1984j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Rect f1985k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final e f1986l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final h0.e f1987m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public androidx.lifecycle.a0 f1988n;

    public a0(View view, q1.u uVar) {
        a1.b bVar = new a1.b();
        bVar.f23d = view;
        bVar.f24e = h1.c.D(new b0.a(5, bVar));
        bVar.f25f = new a1.d(view);
        b0 b0Var = new b0(Choreographer.getInstance());
        this.f1975a = view;
        this.f1976b = bVar;
        this.f1977c = b0Var;
        this.f1979e = d.f1994h;
        this.f1980f = d.f1995i;
        this.f1981g = new x("", y1.g0.f6768b, 4);
        this.f1982h = n.f2038g;
        this.f1983i = new ArrayList();
        this.f1984j = h1.c.D(new b0.a(6, this));
        this.f1986l = new e(uVar, bVar);
        this.f1987m = new h0.e(new z[16]);
    }

    @Override // e2.s
    public final void a(x0.d dVar) {
        Rect rect;
        this.f1985k = new Rect(d4.a.H(dVar.f6534a), d4.a.H(dVar.f6535b), d4.a.H(dVar.f6536c), d4.a.H(dVar.f6537d));
        if (!this.f1983i.isEmpty() || (rect = this.f1985k) == null) {
            return;
        }
        this.f1975a.requestRectangleOnScreen(new Rect(rect));
    }

    @Override // e2.s
    public final void b(x xVar, r rVar, y1.e0 e0Var, g1 g1Var, x0.d dVar, x0.d dVar2) {
        e eVar = this.f1986l;
        synchronized (eVar.f2003c) {
            try {
                eVar.f2010j = xVar;
                eVar.f2012l = rVar;
                eVar.f2011k = e0Var;
                eVar.f2013m = g1Var;
                eVar.f2014n = dVar;
                eVar.f2015o = dVar2;
                if (eVar.f2005e || eVar.f2004d) {
                    eVar.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // e2.s
    public final void c() {
        i(z.f2067d);
    }

    @Override // e2.s
    public final void d() {
        i(z.f2069f);
    }

    @Override // e2.s
    public final void e(x xVar, n nVar, c.b bVar, v.q qVar) {
        this.f1978d = true;
        this.f1981g = xVar;
        this.f1982h = nVar;
        this.f1979e = bVar;
        this.f1980f = qVar;
        i(z.f2067d);
    }

    @Override // e2.s
    public final void f() {
        i(z.f2070g);
    }

    @Override // e2.s
    public final void g() {
        this.f1978d = false;
        this.f1979e = d.f1996j;
        this.f1980f = d.f1997k;
        this.f1985k = null;
        i(z.f2068e);
    }

    /* JADX WARN: Type inference failed for: r14v14, types: [java.lang.Object, p3.d] */
    /* JADX WARN: Type inference failed for: r14v22, types: [java.lang.Object, p3.d] */
    /* JADX WARN: Type inference failed for: r14v8, types: [java.lang.Object, p3.d] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object, p3.d] */
    @Override // e2.s
    public final void h(x xVar, x xVar2) {
        boolean z2 = (y1.g0.a(this.f1981g.f2063b, xVar2.f2063b) && b4.l.a(this.f1981g.f2064c, xVar2.f2064c)) ? false : true;
        this.f1981g = xVar2;
        int size = this.f1983i.size();
        for (int i5 = 0; i5 < size; i5++) {
            t tVar = (t) ((WeakReference) this.f1983i.get(i5)).get();
            if (tVar != null) {
                tVar.f2051d = xVar2;
            }
        }
        e eVar = this.f1986l;
        synchronized (eVar.f2003c) {
            eVar.f2010j = null;
            eVar.f2012l = null;
            eVar.f2011k = null;
            eVar.f2013m = d.f1992f;
            eVar.f2014n = null;
            eVar.f2015o = null;
        }
        if (b4.l.a(xVar, xVar2)) {
            if (z2) {
                a1.b bVar = this.f1976b;
                int iE = y1.g0.e(xVar2.f2063b);
                int iD = y1.g0.d(xVar2.f2063b);
                y1.g0 g0Var = this.f1981g.f2064c;
                int iE2 = g0Var != null ? y1.g0.e(g0Var.f6770a) : -1;
                y1.g0 g0Var2 = this.f1981g.f2064c;
                ((InputMethodManager) bVar.f24e.getValue()).updateSelection((View) bVar.f23d, iE, iD, iE2, g0Var2 != null ? y1.g0.d(g0Var2.f6770a) : -1);
                return;
            }
            return;
        }
        if (xVar != null && (!b4.l.a(xVar.f2062a.f6759d, xVar2.f2062a.f6759d) || (y1.g0.a(xVar.f2063b, xVar2.f2063b) && !b4.l.a(xVar.f2064c, xVar2.f2064c)))) {
            a1.b bVar2 = this.f1976b;
            ((InputMethodManager) bVar2.f24e.getValue()).restartInput((View) bVar2.f23d);
            return;
        }
        int size2 = this.f1983i.size();
        for (int i6 = 0; i6 < size2; i6++) {
            t tVar2 = (t) ((WeakReference) this.f1983i.get(i6)).get();
            if (tVar2 != null) {
                x xVar3 = this.f1981g;
                a1.b bVar3 = this.f1976b;
                if (tVar2.f2055h) {
                    tVar2.f2051d = xVar3;
                    if (tVar2.f2053f) {
                        ((InputMethodManager) bVar3.f24e.getValue()).updateExtractedText((View) bVar3.f23d, tVar2.f2052e, a.a.N(xVar3));
                    }
                    y1.g0 g0Var3 = xVar3.f2064c;
                    long j5 = xVar3.f2063b;
                    int iE3 = g0Var3 != null ? y1.g0.e(g0Var3.f6770a) : -1;
                    y1.g0 g0Var4 = xVar3.f2064c;
                    ((InputMethodManager) bVar3.f24e.getValue()).updateSelection((View) bVar3.f23d, y1.g0.e(j5), y1.g0.d(j5), iE3, g0Var4 != null ? y1.g0.d(g0Var4.f6770a) : -1);
                }
            }
        }
    }

    public final void i(z zVar) {
        this.f1987m.b(zVar);
        if (this.f1988n == null) {
            androidx.lifecycle.a0 a0Var = new androidx.lifecycle.a0(4, this);
            this.f1977c.execute(a0Var);
            this.f1988n = a0Var;
        }
    }
}
