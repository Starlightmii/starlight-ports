package b;

import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c0 f317a = new c0();

    public final OnBackInvokedCallback a(a4.a aVar) {
        b4.l.e("onBackInvoked", aVar);
        return new b0(aVar, 0);
    }

    public final void b(Object obj, int i5, Object obj2) {
        b4.l.e("dispatcher", obj);
        b4.l.e("callback", obj2);
        ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(i5, (OnBackInvokedCallback) obj2);
    }

    public final void c(Object obj, Object obj2) {
        b4.l.e("dispatcher", obj);
        b4.l.e("callback", obj2);
        ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
    }
}
