package q1;

import android.os.Handler;
import android.view.Choreographer;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v0 extends i4.r {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final p3.k f5424p = h1.c.E(n0.f5276l);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final t0 f5425q = new t0();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Choreographer f5426f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Handler f5427g;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f5432l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5433m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final f0.g f5435o;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f5428h = new Object();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final q3.j f5429i = new q3.j();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList f5430j = new ArrayList();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList f5431k = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final u0 f5434n = new u0(this);

    public v0(Choreographer choreographer, Handler handler) {
        this.f5426f = choreographer;
        this.f5427g = handler;
        this.f5435o = new f0.g(choreographer, this);
    }

    public static final void n(v0 v0Var) {
        Runnable runnable;
        boolean z2;
        do {
            synchronized (v0Var.f5428h) {
                q3.j jVar = v0Var.f5429i;
                runnable = (Runnable) (jVar.isEmpty() ? null : jVar.removeFirst());
            }
            while (runnable != null) {
                runnable.run();
                synchronized (v0Var.f5428h) {
                    q3.j jVar2 = v0Var.f5429i;
                    runnable = (Runnable) (jVar2.isEmpty() ? null : jVar2.removeFirst());
                }
            }
            synchronized (v0Var.f5428h) {
                if (v0Var.f5429i.isEmpty()) {
                    z2 = false;
                    v0Var.f5432l = false;
                } else {
                    z2 = true;
                }
            }
        } while (z2);
    }

    @Override // i4.r
    public final void e(t3.h hVar, Runnable runnable) {
        synchronized (this.f5428h) {
            this.f5429i.addLast(runnable);
            if (!this.f5432l) {
                this.f5432l = true;
                this.f5427g.post(this.f5434n);
                if (!this.f5433m) {
                    this.f5433m = true;
                    this.f5426f.postFrameCallback(this.f5434n);
                }
            }
        }
    }
}
