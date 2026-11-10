package b;

import android.window.BackEvent;
import android.window.OnBackAnimationCallback;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 implements OnBackAnimationCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a4.c f318a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ a4.c f319b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ a4.a f320c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ a4.a f321d;

    public d0(a4.c cVar, a4.c cVar2, a4.a aVar, a4.a aVar2) {
        this.f318a = cVar;
        this.f319b = cVar2;
        this.f320c = aVar;
        this.f321d = aVar2;
    }

    public final void onBackCancelled() {
        this.f321d.a();
    }

    public final void onBackInvoked() {
        this.f320c.a();
    }

    public final void onBackProgressed(BackEvent backEvent) {
        b4.l.e("backEvent", backEvent);
        this.f319b.c(new c(backEvent));
    }

    public final void onBackStarted(BackEvent backEvent) {
        b4.l.e("backEvent", backEvent);
        this.f318a.c(new c(backEvent));
    }
}
