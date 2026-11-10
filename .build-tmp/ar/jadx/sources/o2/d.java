package o2;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends d4.a {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f4382k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f4383l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f4384m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f4385n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f4386o;

    public d(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.f4382k = atomicReferenceFieldUpdater;
        this.f4383l = atomicReferenceFieldUpdater2;
        this.f4384m = atomicReferenceFieldUpdater3;
        this.f4385n = atomicReferenceFieldUpdater4;
        this.f4386o = atomicReferenceFieldUpdater5;
    }

    @Override // d4.a
    public final void C(f fVar, f fVar2) {
        this.f4383l.lazySet(fVar, fVar2);
    }

    @Override // d4.a
    public final void D(f fVar, Thread thread) {
        this.f4382k.lazySet(fVar, thread);
    }

    @Override // d4.a
    public final boolean h(g gVar, c cVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f4385n;
            if (atomicReferenceFieldUpdater.compareAndSet(gVar, cVar, c.f4380b)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(gVar) == cVar);
        return false;
    }

    @Override // d4.a
    public final boolean i(g gVar, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f4386o;
            if (atomicReferenceFieldUpdater.compareAndSet(gVar, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(gVar) == obj);
        return false;
    }

    @Override // d4.a
    public final boolean j(g gVar, f fVar, f fVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f4384m;
            if (atomicReferenceFieldUpdater.compareAndSet(gVar, fVar, fVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(gVar) == fVar);
        return false;
    }
}
