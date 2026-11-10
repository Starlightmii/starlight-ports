package b;

import android.window.OnBackInvokedCallback;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b0 implements OnBackInvokedCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f311a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ a4.a f312b;

    public /* synthetic */ b0(a4.a aVar, int i5) {
        this.f311a = i5;
        this.f312b = aVar;
    }

    public final void onBackInvoked() {
        switch (this.f311a) {
            case 0:
                a4.a aVar = this.f312b;
                b4.l.e("$onBackInvoked", aVar);
                aVar.a();
                break;
            default:
                a4.a aVar2 = this.f312b;
                if (aVar2 != null) {
                    aVar2.a();
                }
                break;
        }
    }
}
