package q1;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o0 implements ComponentCallbacks2 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Configuration f5295d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ u1.a f5296e;

    public o0(Configuration configuration, u1.a aVar) {
        this.f5295d = configuration;
        this.f5296e = aVar;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        Configuration configuration2 = this.f5295d;
        configuration2.updateFrom(configuration);
        Iterator it = this.f5296e.f5819a.entrySet().iterator();
        while (it.hasNext()) {
            if (((WeakReference) ((Map.Entry) it.next()).getValue()).get() != null) {
                throw new ClassCastException();
            }
            it.remove();
        }
        configuration2.setTo(configuration);
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        this.f5296e.f5819a.clear();
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i5) {
        this.f5296e.f5819a.clear();
    }
}
