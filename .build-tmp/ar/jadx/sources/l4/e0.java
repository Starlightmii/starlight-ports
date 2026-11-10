package l4;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e0 extends m4.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReference f3528a = new AtomicReference(null);

    @Override // m4.d
    public final boolean a(m4.b bVar) {
        AtomicReference atomicReference = this.f3528a;
        if (atomicReference.get() != null) {
            return false;
        }
        atomicReference.set(u.f3587b);
        return true;
    }

    @Override // m4.d
    public final t3.c[] b(m4.b bVar) {
        this.f3528a.set(null);
        return m4.c.f3817a;
    }
}
