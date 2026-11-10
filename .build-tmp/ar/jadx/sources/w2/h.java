package w2;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.e f6395a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArrayList f6396b = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f6397c = new HashMap();

    public h(b.e eVar) {
        this.f6395a = eVar;
    }

    public final void a() {
        this.f6396b.remove((Object) null);
        g gVar = (g) this.f6397c.remove(null);
        if (gVar != null) {
            gVar.f6393a.b(gVar.f6394b);
            gVar.f6394b = null;
        }
        this.f6395a.run();
    }
}
