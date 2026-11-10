package w2;

import android.os.Build;
import android.view.animation.Interpolator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c0 f6385a;

    public d0(int i5, Interpolator interpolator, long j5) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.f6385a = new b0(b1.j.h(i5, interpolator, j5));
        } else {
            this.f6385a = new z(interpolator, j5);
        }
    }
}
