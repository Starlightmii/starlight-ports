package w2;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final z0 f6455b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v0 f6456a;

    static {
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 34) {
            f6455b = s0.f6433w;
        } else if (i5 >= 30) {
            f6455b = q0.f6432v;
        } else {
            f6455b = v0.f6440b;
        }
    }

    public z0() {
        this.f6456a = new v0(this);
    }

    public z0(WindowInsets windowInsets) {
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 35) {
            this.f6456a = new u0(this, windowInsets);
            return;
        }
        if (i5 >= 34) {
            this.f6456a = new s0(this, windowInsets);
            return;
        }
        if (i5 >= 31) {
            this.f6456a = new r0(this, windowInsets);
            return;
        }
        if (i5 >= 30) {
            this.f6456a = new q0(this, windowInsets);
            return;
        }
        if (i5 >= 29) {
            this.f6456a = new p0(this, windowInsets);
        } else if (i5 >= 28) {
            this.f6456a = new o0(this, windowInsets);
        } else {
            this.f6456a = new n0(this, windowInsets);
        }
    }

    public static z0 b(View view, WindowInsets windowInsets) {
        windowInsets.getClass();
        z0 z0Var = new z0(windowInsets);
        if (view != null && view.isAttachedToWindow()) {
            int i5 = u.f6438a;
            z0 z0VarA = q.a(view);
            v0 v0Var = z0Var.f6456a;
            v0Var.v(z0VarA);
            View rootView = view.getRootView();
            v0Var.d(rootView);
            v0Var.o(rootView);
            v0Var.p();
            v0Var.x(view.getWindowSystemUiVisibility());
        }
        return z0Var;
    }

    public final WindowInsets a() {
        v0 v0Var = this.f6456a;
        if (v0Var instanceof m0) {
            return ((m0) v0Var).f6414c;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof z0) {
            return Objects.equals(this.f6456a, ((z0) obj).f6456a);
        }
        return false;
    }

    public final int hashCode() {
        v0 v0Var = this.f6456a;
        if (v0Var == null) {
            return 0;
        }
        return v0Var.hashCode();
    }
}
