package w2;

import android.graphics.Rect;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z0 f6404a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public r2.c[] f6405b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect[][] f6406c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect[][] f6407d;

    public l0() {
        this(new z0());
    }

    public l0(z0 z0Var) {
        this.f6406c = new Rect[10][];
        this.f6407d = new Rect[10][];
        this.f6404a = z0Var;
        c(z0Var);
    }

    public final void a() {
        r2.c[] cVarArr = this.f6405b;
        if (cVarArr != null) {
            r2.c cVarH = cVarArr[0];
            r2.c cVarH2 = cVarArr[1];
            z0 z0Var = this.f6404a;
            if (cVarH2 == null) {
                cVarH2 = z0Var.f6456a.h(2);
            }
            if (cVarH == null) {
                cVarH = z0Var.f6456a.h(1);
            }
            g(r2.c.a(cVarH, cVarH2));
            r2.c cVar = this.f6405b[q.f.p(16)];
            if (cVar != null) {
                f(cVar);
            }
            r2.c cVar2 = this.f6405b[q.f.p(32)];
            if (cVar2 != null) {
                e(cVar2);
            }
            r2.c cVar3 = this.f6405b[q.f.p(64)];
            if (cVar3 != null) {
                h(cVar3);
            }
        }
    }

    public abstract z0 b();

    public void c(z0 z0Var) {
        for (int i5 = 1; i5 <= 512; i5 <<= 1) {
            List<Rect> listE = z0Var.f6456a.e(i5);
            int iP = q.f.p(i5);
            this.f6406c[iP] = (Rect[]) listE.toArray(new Rect[listE.size()]);
            if (i5 != 8) {
                List<Rect> listF = z0Var.f6456a.f(i5);
                this.f6407d[iP] = (Rect[]) listF.toArray(new Rect[listF.size()]);
            }
        }
    }

    public void d(int i5, r2.c cVar) {
        if (this.f6405b == null) {
            this.f6405b = new r2.c[10];
        }
        for (int i6 = 1; i6 <= 512; i6 <<= 1) {
            if ((i5 & i6) != 0) {
                this.f6405b[q.f.p(i6)] = cVar;
            }
        }
    }

    public void e(r2.c cVar) {
    }

    public void f(r2.c cVar) {
    }

    public abstract void g(r2.c cVar);

    public void h(r2.c cVar) {
    }
}
