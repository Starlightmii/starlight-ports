package q1;

import android.graphics.Outline;
import android.os.Build;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f5445a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Outline f5446b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public y0.j0 f5447c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public y0.j f5448d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public y0.k0 f5449e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5450f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5451g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public y0.k0 f5452h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public x0.e f5453i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f5454j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f5455k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f5456l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5457m;

    public x1() {
        Outline outline = new Outline();
        outline.setAlpha(1.0f);
        this.f5446b = outline;
        this.f5455k = 0L;
        this.f5456l = 0L;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0066  */
    /* JADX WARN: Code duplicated, block: B:28:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:29:0x00b0  */
    public final void a(y0.r rVar) {
        d();
        y0.k0 k0Var = this.f5449e;
        if (k0Var != null) {
            rVar.a(k0Var);
            return;
        }
        float f5 = this.f5454j;
        if (f5 <= 0.0f) {
            rVar.i(x0.c.d(this.f5455k), x0.c.e(this.f5455k), x0.c.d(this.f5455k) + x0.f.d(this.f5456l), x0.c.e(this.f5455k) + x0.f.b(this.f5456l), 1);
            return;
        }
        y0.k0 k0VarH = this.f5452h;
        x0.e eVar = this.f5453i;
        if (k0VarH != null) {
            long j5 = this.f5455k;
            long j6 = this.f5456l;
            if (eVar != null && q.f.q(eVar) && eVar.f6538a == x0.c.d(j5) && eVar.f6539b == x0.c.e(j5)) {
                if (eVar.f6540c == x0.f.d(j6) + x0.c.d(j5)) {
                    if (eVar.f6541d != x0.f.b(j6) + x0.c.e(j5) || x0.a.b(eVar.f6542e) != f5) {
                        float fD = x0.c.d(this.f5455k);
                        float fE = x0.c.e(this.f5455k);
                        float fD2 = x0.f.d(this.f5456l) + x0.c.d(this.f5455k);
                        float fB = x0.f.b(this.f5456l) + x0.c.e(this.f5455k);
                        float f6 = this.f5454j;
                        long jA = p3.p.a(f6, f6);
                        long jA2 = p3.p.a(x0.a.b(jA), x0.a.c(jA));
                        x0.e eVar2 = new x0.e(fD, fE, fD2, fB, jA2, jA2, jA2, jA2);
                        if (k0VarH == null) {
                            k0VarH = y0.l0.h();
                        } else {
                            ((y0.j) k0VarH).d();
                        }
                        y0.k0.b(k0VarH, eVar2);
                        this.f5453i = eVar2;
                        this.f5452h = k0VarH;
                    }
                } else {
                    float fD3 = x0.c.d(this.f5455k);
                    float fE2 = x0.c.e(this.f5455k);
                    float fD4 = x0.f.d(this.f5456l) + x0.c.d(this.f5455k);
                    float fB2 = x0.f.b(this.f5456l) + x0.c.e(this.f5455k);
                    float f7 = this.f5454j;
                    long jA3 = p3.p.a(f7, f7);
                    long jA4 = p3.p.a(x0.a.b(jA3), x0.a.c(jA3));
                    x0.e eVar3 = new x0.e(fD3, fE2, fD4, fB2, jA4, jA4, jA4, jA4);
                    if (k0VarH == null) {
                        k0VarH = y0.l0.h();
                    } else {
                        ((y0.j) k0VarH).d();
                    }
                    y0.k0.b(k0VarH, eVar3);
                    this.f5453i = eVar3;
                    this.f5452h = k0VarH;
                }
            } else {
                float fD5 = x0.c.d(this.f5455k);
                float fE3 = x0.c.e(this.f5455k);
                float fD6 = x0.f.d(this.f5456l) + x0.c.d(this.f5455k);
                float fB3 = x0.f.b(this.f5456l) + x0.c.e(this.f5455k);
                float f8 = this.f5454j;
                long jA5 = p3.p.a(f8, f8);
                long jA6 = p3.p.a(x0.a.b(jA5), x0.a.c(jA5));
                x0.e eVar4 = new x0.e(fD5, fE3, fD6, fB3, jA6, jA6, jA6, jA6);
                if (k0VarH == null) {
                    k0VarH = y0.l0.h();
                } else {
                    ((y0.j) k0VarH).d();
                }
                y0.k0.b(k0VarH, eVar4);
                this.f5453i = eVar4;
                this.f5452h = k0VarH;
            }
        } else {
            float fD7 = x0.c.d(this.f5455k);
            float fE4 = x0.c.e(this.f5455k);
            float fD8 = x0.f.d(this.f5456l) + x0.c.d(this.f5455k);
            float fB4 = x0.f.b(this.f5456l) + x0.c.e(this.f5455k);
            float f9 = this.f5454j;
            long jA7 = p3.p.a(f9, f9);
            long jA8 = p3.p.a(x0.a.b(jA7), x0.a.c(jA7));
            x0.e eVar5 = new x0.e(fD7, fE4, fD8, fB4, jA8, jA8, jA8, jA8);
            if (k0VarH == null) {
                k0VarH = y0.l0.h();
            } else {
                ((y0.j) k0VarH).d();
            }
            y0.k0.b(k0VarH, eVar5);
            this.f5453i = eVar5;
            this.f5452h = k0VarH;
        }
        rVar.a(k0VarH);
    }

    public final Outline b() {
        d();
        if (this.f5457m && this.f5445a) {
            return this.f5446b;
        }
        return null;
    }

    public final boolean c(y0.j0 j0Var, float f5, boolean z2, float f6, long j5) {
        this.f5446b.setAlpha(f5);
        boolean zA = b4.l.a(this.f5447c, j0Var);
        boolean z3 = !zA;
        if (!zA) {
            this.f5447c = j0Var;
            this.f5450f = true;
        }
        this.f5456l = j5;
        boolean z4 = j0Var != null && (z2 || f6 > 0.0f);
        if (this.f5457m != z4) {
            this.f5457m = z4;
            this.f5450f = true;
        }
        return z3;
    }

    public final void d() {
        if (this.f5450f) {
            this.f5455k = 0L;
            this.f5454j = 0.0f;
            this.f5449e = null;
            this.f5450f = false;
            this.f5451g = false;
            y0.j0 j0Var = this.f5447c;
            Outline outline = this.f5446b;
            if (j0Var == null || !this.f5457m || x0.f.d(this.f5456l) <= 0.0f || x0.f.b(this.f5456l) <= 0.0f) {
                outline.setEmpty();
                return;
            }
            this.f5445a = true;
            if (j0Var instanceof y0.h0) {
                x0.d dVar = ((y0.h0) j0Var).f6633a;
                float f5 = dVar.f6534a;
                float f6 = dVar.f6535b;
                this.f5455k = p3.j.b(f5, f6);
                this.f5456l = p3.j.f(dVar.c(), dVar.b());
                outline.setRect(Math.round(f5), Math.round(f6), Math.round(dVar.f6536c), Math.round(dVar.f6537d));
                return;
            }
            if (!(j0Var instanceof y0.i0)) {
                if (j0Var instanceof y0.g0) {
                    e(((y0.g0) j0Var).f6628a);
                    return;
                }
                return;
            }
            x0.e eVar = ((y0.i0) j0Var).f6636a;
            float fB = x0.a.b(eVar.f6542e);
            float f7 = eVar.f6538a;
            float f8 = eVar.f6539b;
            this.f5455k = p3.j.b(f7, f8);
            this.f5456l = p3.j.f(eVar.b(), eVar.a());
            if (q.f.q(eVar)) {
                this.f5446b.setRoundRect(Math.round(f7), Math.round(f8), Math.round(eVar.f6540c), Math.round(eVar.f6541d), fB);
                this.f5454j = fB;
                return;
            }
            y0.j jVarH = this.f5448d;
            if (jVarH == null) {
                jVarH = y0.l0.h();
                this.f5448d = jVarH;
            }
            jVarH.d();
            y0.k0.b(jVarH, eVar);
            e(jVarH);
        }
    }

    public final void e(y0.k0 k0Var) {
        int i5 = Build.VERSION.SDK_INT;
        Outline outline = this.f5446b;
        if (i5 <= 28 && !((y0.j) k0Var).f6638a.isConvex()) {
            this.f5445a = false;
            outline.setEmpty();
            this.f5451g = true;
        } else {
            if (!(k0Var instanceof y0.j)) {
                throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
            }
            outline.setConvexPath(((y0.j) k0Var).f6638a);
            this.f5451g = !outline.canClip();
        }
        this.f5449e = k0Var;
    }
}
