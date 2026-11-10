package q1;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p0 implements ComponentCallbacks2 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ u1.b f5302d;

    public p0(u1.b bVar) {
        this.f5302d = bVar;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        u1.b bVar = this.f5302d;
        synchronized (bVar) {
            bVar.f5820a.a();
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        u1.b bVar = this.f5302d;
        synchronized (bVar) {
            bVar.f5820a.a();
        }
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i5) {
        u1.b bVar = this.f5302d;
        synchronized (bVar) {
            bVar.f5820a.a();
        }
    }
}
