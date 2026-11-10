package g4;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReference f2548a;

    public a(f fVar) {
        this.f2548a = new AtomicReference(fVar);
    }

    @Override // g4.f
    public final Iterator iterator() {
        f fVar = (f) this.f2548a.getAndSet(null);
        if (fVar != null) {
            return fVar.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
