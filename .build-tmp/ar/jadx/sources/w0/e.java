package w0;

import b.h0;
import i4.v0;
import j.a0;
import j.g0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v0 f6227a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h0 f6228b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a0 f6229c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a0 f6230d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a0 f6231e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a0 f6232f;

    public e(v0 v0Var, h0 h0Var) {
        this.f6227a = v0Var;
        this.f6228b = h0Var;
        int i5 = g0.f3106a;
        this.f6229c = new a0();
        this.f6230d = new a0();
        this.f6231e = new a0();
        this.f6232f = new a0();
    }

    public final boolean a() {
        return this.f6229c.h() || this.f6231e.h() || this.f6230d.h();
    }

    public final void b(a0 a0Var, Object obj) {
        if (a0Var.a(obj) && this.f6229c.f3069d + this.f6230d.f3069d + this.f6231e.f3069d == 1) {
            this.f6227a.c(new h0(0, this, e.class, "invalidateNodes", "invalidateNodes()V", 0, 0, 5));
        }
    }
}
