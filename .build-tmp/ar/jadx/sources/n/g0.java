package n;

import android.content.Context;
import android.widget.EdgeEffect;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g0 extends EdgeEffect {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3913a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f3914b;

    public g0(Context context) {
        super(context);
        this.f3913a = d4.a.a(context).f3403d * 1;
    }

    @Override // android.widget.EdgeEffect
    public final void onAbsorb(int i5) {
        this.f3914b = 0.0f;
        super.onAbsorb(i5);
    }

    @Override // android.widget.EdgeEffect
    public final void onPull(float f5) {
        this.f3914b = 0.0f;
        super.onPull(f5);
    }

    @Override // android.widget.EdgeEffect
    public final void onPull(float f5, float f6) {
        this.f3914b = 0.0f;
        super.onPull(f5, f6);
    }

    @Override // android.widget.EdgeEffect
    public final void onRelease() {
        this.f3914b = 0.0f;
        super.onRelease();
    }
}
