package w2;

import android.os.Build;
import android.view.View;
import android.view.animation.PathInterpolator;
import io.github.andrea_lyz.assistrestore.R;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f6438a = 0;

    static {
        new WeakHashMap();
    }

    public static void a(View view, r.c0 c0Var) {
        if (Build.VERSION.SDK_INT >= 30) {
            view.setWindowInsetsAnimationCallback(c0Var != null ? new a0(c0Var) : null);
            return;
        }
        PathInterpolator pathInterpolator = z.f6451d;
        View.OnApplyWindowInsetsListener yVar = c0Var != null ? new y(view, c0Var) : null;
        view.setTag(R.id.tag_window_insets_animation_callback, yVar);
        if (view.getTag(R.id.tag_compat_insets_dispatch) == null && view.getTag(R.id.tag_on_apply_window_listener) == null) {
            view.setOnApplyWindowInsetsListener(yVar);
        }
    }
}
