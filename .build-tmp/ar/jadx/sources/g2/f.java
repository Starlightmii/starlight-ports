package g2;

import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextPaint;
import f0.n0;
import y0.l0;
import y0.o0;
import y0.p;
import y0.p0;
import y0.s0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends TextPaint {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public y0.h f2531a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public j2.j f2532b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2533c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public p0 f2534d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public p f2535e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public n0 f2536f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public x0.f f2537g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a1.g f2538h;

    public final y0.h a() {
        y0.h hVar = this.f2531a;
        if (hVar != null) {
            return hVar;
        }
        y0.h hVar2 = new y0.h(this);
        this.f2531a = hVar2;
        return hVar2;
    }

    public final void b(int i5) {
        if (i5 == this.f2533c) {
            return;
        }
        a().d(i5);
        this.f2533c = i5;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0038  */
    /* JADX WARN: Code duplicated, block: B:21:0x0041  */
    /* JADX WARN: Code duplicated, block: B:23:0x0044  */
    public final void c(p pVar, long j5, float f5) {
        if (pVar == null) {
            this.f2536f = null;
            this.f2535e = null;
            this.f2537g = null;
            setShader(null);
            return;
        }
        if (pVar instanceof s0) {
            d(h1.c.F(f5, ((s0) pVar).f6684a));
            return;
        }
        if (pVar instanceof o0) {
            if (b4.l.a(this.f2535e, pVar)) {
                x0.f fVar = this.f2537g;
                if (!(fVar == null ? false : x0.f.a(fVar.f6546a, j5))) {
                    if (j5 != 9205357640488583168L) {
                        this.f2535e = pVar;
                        this.f2537g = new x0.f(j5);
                        this.f2536f = f0.l.n(new e(0, j5, pVar));
                    }
                }
            } else {
                if (j5 != 9205357640488583168L) {
                    this.f2535e = pVar;
                    this.f2537g = new x0.f(j5);
                    this.f2536f = f0.l.n(new e(0, j5, pVar));
                }
            }
            y0.h hVarA = a();
            n0 n0Var = this.f2536f;
            hVarA.h(n0Var != null ? (Shader) n0Var.getValue() : null);
            k.b(this, f5);
        }
    }

    public final void d(long j5) {
        if (j5 != 16) {
            setColor(l0.w(j5));
            this.f2536f = null;
            this.f2535e = null;
            this.f2537g = null;
            setShader(null);
        }
    }

    public final void e(a1.g gVar) {
        if (gVar == null || b4.l.a(this.f2538h, gVar)) {
            return;
        }
        this.f2538h = gVar;
        if (gVar.equals(a1.i.f33a)) {
            setStyle(Paint.Style.FILL);
            return;
        }
        if (gVar instanceof a1.j) {
            a().l(1);
            a1.j jVar = (a1.j) gVar;
            a().k(jVar.f34a);
            y0.h hVarA = a();
            hVarA.f6629a.setStrokeMiter(jVar.f35b);
            a().j(jVar.f37d);
            a().i(jVar.f36c);
            a().f6629a.setPathEffect(null);
        }
    }

    public final void f(p0 p0Var) {
        if (p0Var == null || b4.l.a(this.f2534d, p0Var)) {
            return;
        }
        this.f2534d = p0Var;
        if (p0Var.equals(p0.f6668d)) {
            clearShadowLayer();
            return;
        }
        p0 p0Var2 = this.f2534d;
        float f5 = p0Var2.f6671c;
        if (f5 == 0.0f) {
            f5 = Float.MIN_VALUE;
        }
        setShadowLayer(f5, x0.c.d(p0Var2.f6670b), x0.c.e(this.f2534d.f6670b), l0.w(this.f2534d.f6669a));
    }

    public final void g(j2.j jVar) {
        if (jVar == null || b4.l.a(this.f2532b, jVar)) {
            return;
        }
        this.f2532b = jVar;
        int i5 = jVar.f3325a;
        setUnderlineText((i5 | 1) == i5);
        int i6 = this.f2532b.f3325a;
        setStrikeThruText((i6 | 2) == i6);
    }
}
