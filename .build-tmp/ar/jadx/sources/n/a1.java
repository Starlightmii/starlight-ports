package n;

import android.widget.Magnifier;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class a1 implements y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Magnifier f3863a;

    public a1(Magnifier magnifier) {
        this.f3863a = magnifier;
    }

    @Override // n.y0
    public void a(long j5, long j6) {
        this.f3863a.show(x0.c.d(j5), x0.c.e(j5));
    }

    public final void b() {
        this.f3863a.dismiss();
    }

    public final long c() {
        return a.a.f(this.f3863a.getWidth(), this.f3863a.getHeight());
    }

    public final void d() {
        this.f3863a.update();
    }
}
