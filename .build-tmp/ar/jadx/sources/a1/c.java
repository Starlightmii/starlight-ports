package a1;

import android.graphics.Paint;
import b4.l;
import k2.k;
import y0.c0;
import y0.k0;
import y0.l0;
import y0.m;
import y0.p;
import y0.r;
import y0.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f26d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f27e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public y0.h f28f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public y0.h f29g;

    public c() {
        h hVar = new h();
        a aVar = new a();
        aVar.f18a = e.f32a;
        aVar.f19b = k.f3416d;
        aVar.f20c = hVar;
        aVar.f21d = 0L;
        this.f26d = aVar;
        b bVar = new b();
        bVar.f25f = this;
        bVar.f23d = new d(0, bVar);
        this.f27e = bVar;
    }

    public static y0.h c(c cVar, long j5, g gVar, int i5) {
        y0.h hVarG = cVar.g(gVar);
        Paint paint = hVarG.f6629a;
        if (!u.c(l0.c(paint.getColor()), j5)) {
            hVarG.e(j5);
        }
        if (hVarG.f6631c != null) {
            hVarG.h(null);
        }
        if (!l.a(hVarG.f6632d, null)) {
            hVarG.f(null);
        }
        if (hVarG.f6630b != i5) {
            hVarG.d(i5);
        }
        if (paint.isFilterBitmap()) {
            return hVarG;
        }
        hVarG.g(1);
        return hVarG;
    }

    @Override // a1.f
    public final b A() {
        return this.f27e;
    }

    @Override // a1.f
    public final void G(c0 c0Var, long j5, long j6, long j7, float f5, m mVar, int i5) {
        this.f26d.f20c.g(c0Var, j5, j6, j7, d(null, i.f33a, f5, mVar, 3, i5));
    }

    @Override // a1.f
    public final void L(long j5, long j6, long j7, int i5) {
        this.f26d.f20c.f(x0.c.d(j6), x0.c.e(j6), x0.f.d(j7) + x0.c.d(j6), x0.f.b(j7) + x0.c.e(j6), c(this, j5, i.f33a, i5));
    }

    @Override // a1.f
    public final void V(k0 k0Var, p pVar, float f5, g gVar, int i5) {
        this.f26d.f20c.b(k0Var, d(pVar, gVar, f5, null, i5, 1));
    }

    @Override // a1.f
    public final void X(long j5, long j6, long j7, float f5) {
        r rVar = this.f26d.f20c;
        y0.h hVarG = this.f29g;
        if (hVarG == null) {
            hVarG = l0.g();
            hVarG.l(1);
            this.f29g = hVarG;
        }
        Paint paint = hVarG.f6629a;
        if (!u.c(l0.c(paint.getColor()), j5)) {
            hVarG.e(j5);
        }
        if (hVarG.f6631c != null) {
            hVarG.h(null);
        }
        if (!l.a(hVarG.f6632d, null)) {
            hVarG.f(null);
        }
        if (hVarG.f6630b != 3) {
            hVarG.d(3);
        }
        if (paint.getStrokeWidth() != f5) {
            hVarG.k(f5);
        }
        if (paint.getStrokeMiter() != 4.0f) {
            paint.setStrokeMiter(4.0f);
        }
        if (hVarG.a() != 0) {
            hVarG.i(0);
        }
        if (hVarG.b() != 0) {
            hVarG.j(0);
        }
        if (!paint.isFilterBitmap()) {
            hVarG.g(1);
        }
        rVar.o(j6, j7, hVarG);
    }

    @Override // k2.b
    public final float a() {
        return this.f26d.f18a.a();
    }

    public final y0.h d(p pVar, g gVar, float f5, m mVar, int i5, int i6) {
        y0.h hVarG = g(gVar);
        Paint paint = hVarG.f6629a;
        if (pVar != null) {
            pVar.a(f5, b(), hVarG);
        } else {
            if (hVarG.f6631c != null) {
                hVarG.h(null);
            }
            long jC = l0.c(paint.getColor());
            long j5 = u.f6689b;
            if (!u.c(jC, j5)) {
                hVarG.e(j5);
            }
            if (paint.getAlpha() / 255.0f != f5) {
                hVarG.c(f5);
            }
        }
        if (!l.a(hVarG.f6632d, mVar)) {
            hVarG.f(mVar);
        }
        if (hVarG.f6630b != i5) {
            hVarG.d(i5);
        }
        if (paint.isFilterBitmap() == i6) {
            return hVarG;
        }
        hVarG.g(i6);
        return hVarG;
    }

    public final void e(c0 c0Var, m mVar) {
        this.f26d.f20c.j(c0Var, d(null, i.f33a, 1.0f, mVar, 3, 1));
    }

    public final y0.h g(g gVar) {
        if (l.a(gVar, i.f33a)) {
            y0.h hVar = this.f28f;
            if (hVar != null) {
                return hVar;
            }
            y0.h hVarG = l0.g();
            hVarG.l(0);
            this.f28f = hVarG;
            return hVarG;
        }
        if (!(gVar instanceof j)) {
            throw new i4.p();
        }
        y0.h hVarG2 = this.f29g;
        if (hVarG2 == null) {
            hVarG2 = l0.g();
            hVarG2.l(1);
            this.f29g = hVarG2;
        }
        Paint paint = hVarG2.f6629a;
        float strokeWidth = paint.getStrokeWidth();
        j jVar = (j) gVar;
        float f5 = jVar.f34a;
        if (strokeWidth != f5) {
            hVarG2.k(f5);
        }
        int iA = hVarG2.a();
        int i5 = jVar.f36c;
        if (iA != i5) {
            hVarG2.i(i5);
        }
        float strokeMiter = paint.getStrokeMiter();
        float f6 = jVar.f35b;
        if (strokeMiter != f6) {
            paint.setStrokeMiter(f6);
        }
        int iB = hVarG2.b();
        int i6 = jVar.f37d;
        if (iB == i6) {
            return hVarG2;
        }
        hVarG2.j(i6);
        return hVarG2;
    }

    @Override // a1.f
    public final k getLayoutDirection() {
        return this.f26d.f19b;
    }

    @Override // k2.b
    public final float j() {
        return this.f26d.f18a.j();
    }

    @Override // a1.f
    public final void l(float f5, long j5, long j6) {
        this.f26d.f20c.c(f5, j6, c(this, j5, i.f33a, 3));
    }

    @Override // a1.f
    public final void q(long j5, long j6, long j7, long j8) {
        this.f26d.f20c.n(x0.c.d(j6), x0.c.e(j6), x0.f.d(j7) + x0.c.d(j6), x0.f.b(j7) + x0.c.e(j6), x0.a.b(j8), x0.a.c(j8), c(this, j5, i.f33a, 3));
    }

    @Override // a1.f
    public final void t(k0 k0Var, long j5, g gVar) {
        this.f26d.f20c.b(k0Var, c(this, j5, gVar, 3));
    }
}
