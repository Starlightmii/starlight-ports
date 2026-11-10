package androidx.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 extends i {
    final /* synthetic */ d0 this$0;

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class a extends i {
        final /* synthetic */ d0 this$0;

        public a(d0 d0Var) {
            this.this$0 = d0Var;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            b4.l.e("activity", activity);
            this.this$0.a();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            b4.l.e("activity", activity);
            d0 d0Var = this.this$0;
            int i5 = d0Var.f240d + 1;
            d0Var.f240d = i5;
            if (i5 == 1 && d0Var.f243g) {
                d0Var.f245i.e(o.ON_START);
                d0Var.f243g = false;
            }
        }
    }

    public c0(d0 d0Var) {
        this.this$0 = d0Var;
    }

    @Override // androidx.lifecycle.i, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        b4.l.e("activity", activity);
        if (Build.VERSION.SDK_INT < 29) {
            int i5 = g0.f251e;
            Fragment fragmentFindFragmentByTag = activity.getFragmentManager().findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag");
            b4.l.c("null cannot be cast to non-null type androidx.lifecycle.ReportFragment", fragmentFindFragmentByTag);
            ((g0) fragmentFindFragmentByTag).f252d = this.this$0.f247k;
        }
    }

    @Override // androidx.lifecycle.i, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        b4.l.e("activity", activity);
        d0 d0Var = this.this$0;
        int i5 = d0Var.f241e - 1;
        d0Var.f241e = i5;
        if (i5 == 0) {
            Handler handler = d0Var.f244h;
            b4.l.b(handler);
            handler.postDelayed(d0Var.f246j, 700L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        b4.l.e("activity", activity);
        b0.a(activity, new a(this.this$0));
    }

    @Override // androidx.lifecycle.i, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        b4.l.e("activity", activity);
        d0 d0Var = this.this$0;
        int i5 = d0Var.f240d - 1;
        d0Var.f240d = i5;
        if (i5 == 0 && d0Var.f242f) {
            d0Var.f245i.e(o.ON_STOP);
            d0Var.f243g = true;
        }
    }
}
