package d1;

import a1.c;
import a1.d;
import b4.l;
import k2.k;
import p1.f0;
import x0.f;
import y0.h;
import y0.m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public h f1598a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public m f1599b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f1600c = 1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public k f1601d = k.f3416d;

    public abstract void a(float f5);

    public abstract void b(m mVar);

    public final void c(f0 f0Var, long j5, float f5, m mVar) {
        c cVar = f0Var.f4828d;
        if (this.f1600c != f5) {
            a(f5);
            this.f1600c = f5;
        }
        if (!l.a(this.f1599b, mVar)) {
            b(mVar);
            this.f1599b = mVar;
        }
        k layoutDirection = f0Var.getLayoutDirection();
        if (this.f1601d != layoutDirection) {
            this.f1601d = layoutDirection;
        }
        float fD = f.d(cVar.b()) - f.d(j5);
        float fB = f.b(cVar.b()) - f.b(j5);
        ((d) cVar.f27e.f23d).s(0.0f, 0.0f, fD, fB);
        if (f5 > 0.0f) {
            try {
                if (f.d(j5) > 0.0f && f.b(j5) > 0.0f) {
                    e(f0Var);
                }
            } finally {
                ((d) cVar.f27e.f23d).s(-0.0f, -0.0f, -fD, -fB);
            }
        }
    }

    public abstract long d();

    public abstract void e(f0 f0Var);
}
