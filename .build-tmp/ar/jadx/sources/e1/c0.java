package e1;

import f0.p1;
import f0.s1;
import p1.f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 extends d1.b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s1 f1880e = f0.l.s(new x0.f(0));

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final s1 f1881f = f0.l.s(Boolean.FALSE);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final x f1882g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final p1 f1883h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f1884i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public y0.m f1885j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1886k;

    public c0(b bVar) {
        x xVar = new x(bVar);
        xVar.f1961f = new b0.a(4, this);
        this.f1882g = xVar;
        this.f1883h = new p1(0);
        this.f1884i = 1.0f;
        this.f1886k = -1;
    }

    @Override // d1.b
    public final void a(float f5) {
        this.f1884i = f5;
    }

    @Override // d1.b
    public final void b(y0.m mVar) {
        this.f1885j = mVar;
    }

    @Override // d1.b
    public final long d() {
        return ((x0.f) this.f1880e.getValue()).f6546a;
    }

    @Override // d1.b
    public final void e(f0 f0Var) {
        a1.c cVar = f0Var.f4828d;
        y0.m mVar = this.f1885j;
        x xVar = this.f1882g;
        if (mVar == null) {
            mVar = (y0.m) xVar.f1962g.getValue();
        }
        if (((Boolean) this.f1881f.getValue()).booleanValue() && f0Var.getLayoutDirection() == k2.k.f3417e) {
            long jO = cVar.O();
            a1.b bVar = cVar.f27e;
            long jT = bVar.t();
            bVar.r().p();
            try {
                ((a1.d) bVar.f23d).v(-1.0f, 1.0f, jO);
                xVar.e(f0Var, this.f1884i, mVar);
                bVar.r().m();
                bVar.D(jT);
            } catch (Throwable th) {
                bVar.r().m();
                bVar.D(jT);
                throw th;
            }
        } else {
            xVar.e(f0Var, this.f1884i, mVar);
        }
        this.f1886k = this.f1883h.g();
    }
}
