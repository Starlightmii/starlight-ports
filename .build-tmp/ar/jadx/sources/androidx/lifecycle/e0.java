package androidx.lifecycle;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Build;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class e0 {
    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Activity activity, o oVar) {
        b4.l.e("event", oVar);
        if (activity instanceof v) {
            q lifecycle = ((v) activity).getLifecycle();
            if (lifecycle instanceof x) {
                ((x) lifecycle).e(oVar);
            }
        }
    }

    public static void b(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            g0.a.Companion.getClass();
            activity.registerActivityLifecycleCallbacks(new g0.a());
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new g0(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }
}
