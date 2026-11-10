package androidx.lifecycle;

import android.os.Handler;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 implements v {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final d0 f239l = new d0();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f240d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f241e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Handler f244h;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f242f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f243g = true;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final x f245i = new x(this);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final a0 f246j = new a0(0, this);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final a1.d f247k = new a1.d(1, this);

    public final void a() {
        int i5 = this.f241e + 1;
        this.f241e = i5;
        if (i5 == 1) {
            if (this.f242f) {
                this.f245i.e(o.ON_RESUME);
                this.f242f = false;
            } else {
                Handler handler = this.f244h;
                b4.l.b(handler);
                handler.removeCallbacks(this.f246j);
            }
        }
    }

    @Override // androidx.lifecycle.v
    public final q getLifecycle() {
        return this.f245i;
    }
}
