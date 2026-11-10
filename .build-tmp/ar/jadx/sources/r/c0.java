package r;

import android.os.Build;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 implements Runnable, w2.j, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public w2.z0 f5536d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5537e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a1 f5538f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5539g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f5540h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public w2.z0 f5541i;

    public c0(a1 a1Var) {
        this.f5537e = !a1Var.f5524r ? 1 : 0;
        this.f5538f = a1Var;
    }

    public final w2.z0 a(View view, w2.z0 z0Var) {
        this.f5541i = z0Var;
        a1 a1Var = this.f5538f;
        y0 y0Var = a1Var.f5522p;
        w2.v0 v0Var = z0Var.f6456a;
        y0Var.f(c.f(v0Var.h(8)));
        if (this.f5539g) {
            if (Build.VERSION.SDK_INT == 30) {
                view.post(this);
            }
        } else if (!this.f5540h) {
            a1Var.f5523q.f(c.f(v0Var.h(8)));
            a1.a(a1Var, z0Var);
        }
        return a1Var.f5524r ? w2.z0.f6455b : z0Var;
    }

    public final void b(w2.d0 d0Var) {
        this.f5539g = false;
        this.f5540h = false;
        w2.z0 z0Var = this.f5541i;
        if (d0Var.f6385a.a() != 0 && z0Var != null) {
            w2.v0 v0Var = z0Var.f6456a;
            a1 a1Var = this.f5538f;
            a1Var.f5523q.f(c.f(v0Var.h(8)));
            a1Var.f5522p.f(c.f(v0Var.h(8)));
            a1.a(a1Var, z0Var);
        }
        this.f5541i = null;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        view.requestApplyInsets();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.f5539g) {
            this.f5539g = false;
            this.f5540h = false;
            w2.z0 z0Var = this.f5541i;
            if (z0Var != null) {
                a1 a1Var = this.f5538f;
                a1Var.f5523q.f(c.f(z0Var.f6456a.h(8)));
                a1.a(a1Var, z0Var);
                this.f5541i = null;
            }
        }
    }
}
