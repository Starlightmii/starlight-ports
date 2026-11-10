package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class g0 extends Fragment {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ int f251e = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a1.d f252d;

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class a implements Application.ActivityLifecycleCallbacks {
        public static final f0 Companion = new f0();

        public static final void registerIn(Activity activity) {
            Companion.getClass();
            b4.l.e("activity", activity);
            activity.registerActivityLifecycleCallbacks(new a());
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            b4.l.e("activity", activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            b4.l.e("activity", activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            b4.l.e("activity", activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostCreated(Activity activity, Bundle bundle) {
            b4.l.e("activity", activity);
            int i5 = g0.f251e;
            e0.a(activity, o.ON_CREATE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            b4.l.e("activity", activity);
            int i5 = g0.f251e;
            e0.a(activity, o.ON_RESUME);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            b4.l.e("activity", activity);
            int i5 = g0.f251e;
            e0.a(activity, o.ON_START);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreDestroyed(Activity activity) {
            b4.l.e("activity", activity);
            int i5 = g0.f251e;
            e0.a(activity, o.ON_DESTROY);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPrePaused(Activity activity) {
            b4.l.e("activity", activity);
            int i5 = g0.f251e;
            e0.a(activity, o.ON_PAUSE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreStopped(Activity activity) {
            b4.l.e("activity", activity);
            int i5 = g0.f251e;
            e0.a(activity, o.ON_STOP);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            b4.l.e("activity", activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            b4.l.e("activity", activity);
            b4.l.e("bundle", bundle);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            b4.l.e("activity", activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            b4.l.e("activity", activity);
        }
    }

    public final void a(o oVar) {
        if (Build.VERSION.SDK_INT < 29) {
            Activity activity = getActivity();
            b4.l.d("getActivity(...)", activity);
            e0.a(activity, oVar);
        }
    }

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(o.ON_CREATE);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        a(o.ON_DESTROY);
        this.f252d = null;
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        a(o.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        a1.d dVar = this.f252d;
        if (dVar != null) {
            ((d0) dVar.f31e).a();
        }
        a(o.ON_RESUME);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        a1.d dVar = this.f252d;
        if (dVar != null) {
            d0 d0Var = (d0) dVar.f31e;
            int i5 = d0Var.f240d + 1;
            d0Var.f240d = i5;
            if (i5 == 1 && d0Var.f243g) {
                d0Var.f245i.e(o.ON_START);
                d0Var.f243g = false;
            }
        }
        a(o.ON_START);
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        a(o.ON_STOP);
    }
}
