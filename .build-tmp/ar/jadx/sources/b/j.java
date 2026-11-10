package b;

import android.app.Activity;
import android.window.OnBackInvokedDispatcher;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f347a = new j();

    public final OnBackInvokedDispatcher a(Activity activity) {
        b4.l.e("activity", activity);
        OnBackInvokedDispatcher onBackInvokedDispatcher = activity.getOnBackInvokedDispatcher();
        b4.l.d("activity.getOnBackInvokedDispatcher()", onBackInvokedDispatcher);
        return onBackInvokedDispatcher;
    }
}
