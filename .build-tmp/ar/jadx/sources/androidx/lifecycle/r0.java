package androidx.lifecycle;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class r0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h3.a f287a = new h3.a();

    public final void a() {
        h3.a aVar = this.f287a;
        if (aVar == null || aVar.f2622d) {
            return;
        }
        aVar.f2622d = true;
        synchronized (aVar.f2619a) {
            try {
                Iterator it = aVar.f2620b.values().iterator();
                while (it.hasNext()) {
                    h3.a.a((AutoCloseable) it.next());
                }
                Iterator it2 = aVar.f2621c.iterator();
                while (it2.hasNext()) {
                    h3.a.a((AutoCloseable) it2.next());
                }
                aVar.f2621c.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
