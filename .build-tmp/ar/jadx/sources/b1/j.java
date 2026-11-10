package b1;

import android.graphics.Insets;
import android.view.WindowInsetsAnimation;
import android.view.WindowMetrics;
import android.view.animation.Interpolator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class j {
    public static /* synthetic */ WindowInsetsAnimation.Bounds g(Insets insets, Insets insets2) {
        return new WindowInsetsAnimation.Bounds(insets, insets2);
    }

    public static /* synthetic */ WindowInsetsAnimation h(int i5, Interpolator interpolator, long j5) {
        return new WindowInsetsAnimation(i5, interpolator, j5);
    }

    public static /* bridge */ /* synthetic */ WindowInsetsAnimation i(Object obj) {
        return (WindowInsetsAnimation) obj;
    }

    public static /* bridge */ /* synthetic */ Class k() {
        return WindowMetrics.class;
    }

    public static /* synthetic */ void l() {
    }
}
