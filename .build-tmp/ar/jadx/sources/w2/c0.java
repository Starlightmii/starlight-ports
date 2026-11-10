package w2;

import android.view.animation.Interpolator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f6379a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Interpolator f6380b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f6381c;

    public c0(Interpolator interpolator, long j5) {
        this.f6380b = interpolator;
        this.f6381c = j5;
    }

    public long a() {
        return this.f6381c;
    }

    public float b() {
        Interpolator interpolator = this.f6380b;
        return interpolator != null ? interpolator.getInterpolation(this.f6379a) : this.f6379a;
    }

    public void c(float f5) {
        this.f6379a = f5;
    }
}
