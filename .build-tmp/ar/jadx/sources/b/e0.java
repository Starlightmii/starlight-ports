package b;

import android.window.OnBackInvokedCallback;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e0 f324a = new e0();

    public final OnBackInvokedCallback a(a4.c cVar, a4.c cVar2, a4.a aVar, a4.a aVar2) {
        b4.l.e("onBackStarted", cVar);
        b4.l.e("onBackProgressed", cVar2);
        b4.l.e("onBackInvoked", aVar);
        b4.l.e("onBackCancelled", aVar2);
        return new d0(cVar, cVar2, aVar, aVar2);
    }
}
