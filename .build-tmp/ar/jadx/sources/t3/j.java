package t3;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j implements c, v3.d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f5808e = AtomicReferenceFieldUpdater.newUpdater(j.class, Object.class, "result");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f5809d;
    private volatile Object result;

    public j(c cVar) {
        u3.a aVar = u3.a.f5838d;
        this.f5809d = cVar;
        this.result = aVar;
    }

    @Override // v3.d
    public final v3.d getCallerFrame() {
        c cVar = this.f5809d;
        if (cVar instanceof v3.d) {
            return (v3.d) cVar;
        }
        return null;
    }

    @Override // t3.c
    public final h getContext() {
        return this.f5809d.getContext();
    }

    @Override // t3.c
    public final void resumeWith(Object obj) {
        while (true) {
            Object obj2 = this.result;
            u3.a aVar = u3.a.f5839e;
            if (obj2 == aVar) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5808e;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, aVar, obj)) {
                    if (atomicReferenceFieldUpdater.get(this) != aVar) {
                    }
                }
                return;
            }
            u3.a aVar2 = u3.a.f5838d;
            if (obj2 != aVar2) {
                throw new IllegalStateException("Already resumed");
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f5808e;
            u3.a aVar3 = u3.a.f5840f;
            do {
                if (atomicReferenceFieldUpdater2.compareAndSet(this, aVar2, aVar3)) {
                    this.f5809d.resumeWith(obj);
                    return;
                }
            } while (atomicReferenceFieldUpdater2.get(this) == aVar2);
        }
    }

    public final String toString() {
        return "SafeContinuation for " + this.f5809d;
    }
}
