package w2;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;
import io.github.andrea_lyz.assistrestore.R;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z extends c0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final PathInterpolator f6451d = new PathInterpolator(0.0f, 1.1f, 0.0f, 1.0f);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d3.a f6452e = new d3.a(d3.a.f1627c);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final DecelerateInterpolator f6453f = new DecelerateInterpolator(1.5f);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final AccelerateInterpolator f6454g = new AccelerateInterpolator(1.5f);

    public static void d(View view, d0 d0Var) {
        r.c0 c0VarI = i(view);
        if (c0VarI != null) {
            c0VarI.b(d0Var);
            if (c0VarI.f5537e == 0) {
                return;
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
                d(viewGroup.getChildAt(i5), d0Var);
            }
        }
    }

    public static void e(View view, z0 z0Var, boolean z2) {
        r.c0 c0VarI = i(view);
        if (c0VarI != null) {
            c0VarI.f5536d = z0Var;
            if (!z2) {
                z2 = true;
                c0VarI.f5539g = true;
                c0VarI.f5540h = true;
                if (c0VarI.f5537e != 0) {
                    z2 = false;
                }
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
                e(viewGroup.getChildAt(i5), z0Var, z2);
            }
        }
    }

    public static void f(View view, z0 z0Var) {
        r.c0 c0VarI = i(view);
        if (c0VarI != null) {
            r.a1 a1Var = c0VarI.f5538f;
            r.a1.a(a1Var, z0Var);
            if (a1Var.f5524r) {
                z0Var = z0.f6455b;
            }
            if (c0VarI.f5537e == 0) {
                return;
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
                f(viewGroup.getChildAt(i5), z0Var);
            }
        }
    }

    public static void g(View view) {
        r.c0 c0VarI = i(view);
        if (c0VarI != null) {
            c0VarI.f5539g = false;
            if (c0VarI.f5537e == 0) {
                return;
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
                g(viewGroup.getChildAt(i5));
            }
        }
    }

    public static WindowInsets h(View view, WindowInsets windowInsets) {
        return view.getTag(R.id.tag_on_apply_window_listener) != null ? windowInsets : view.onApplyWindowInsets(windowInsets);
    }

    public static r.c0 i(View view) {
        Object tag = view.getTag(R.id.tag_window_insets_animation_callback);
        if (tag instanceof y) {
            return ((y) tag).f6449a;
        }
        return null;
    }
}
