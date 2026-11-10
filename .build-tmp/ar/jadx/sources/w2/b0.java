package w2;

import android.view.WindowInsetsAnimation;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b0 extends c0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WindowInsetsAnimation f6375d;

    public b0(WindowInsetsAnimation windowInsetsAnimation) {
        super(null, 0L);
        this.f6375d = windowInsetsAnimation;
    }

    @Override // w2.c0
    public final long a() {
        return this.f6375d.getDurationMillis();
    }

    @Override // w2.c0
    public final float b() {
        return this.f6375d.getInterpolatedFraction();
    }

    @Override // w2.c0
    public final void c(float f5) {
        this.f6375d.setFraction(f5);
    }
}
