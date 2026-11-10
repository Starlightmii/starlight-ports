package q1;

import android.os.Build;
import android.view.ViewConfiguration;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y0 implements o2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewConfiguration f5459a;

    public y0(ViewConfiguration viewConfiguration) {
        this.f5459a = viewConfiguration;
    }

    @Override // q1.o2
    public final float a() {
        return this.f5459a.getScaledMaximumFlingVelocity();
    }

    @Override // q1.o2
    public final long b() {
        return ViewConfiguration.getDoubleTapTimeout();
    }

    @Override // q1.o2
    public final long c() {
        return ViewConfiguration.getLongPressTimeout();
    }

    @Override // q1.o2
    public final float d() {
        return this.f5459a.getScaledTouchSlop();
    }

    @Override // q1.o2
    public final float e() {
        if (Build.VERSION.SDK_INT >= 34) {
            return a1.f5109a.b(this.f5459a);
        }
        return 2.0f;
    }

    @Override // q1.o2
    public final float f() {
        if (Build.VERSION.SDK_INT >= 34) {
            return a1.f5109a.a(this.f5459a);
        }
        return 16.0f;
    }
}
