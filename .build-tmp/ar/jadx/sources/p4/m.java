package p4;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f5073b = AtomicReferenceFieldUpdater.newUpdater(m.class, Object.class, "lastScheduledTask$volatile");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f5074c = AtomicIntegerFieldUpdater.newUpdater(m.class, "producerIndex$volatile");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f5075d = AtomicIntegerFieldUpdater.newUpdater(m.class, "consumerIndex$volatile");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f5076e = AtomicIntegerFieldUpdater.newUpdater(m.class, "blockingTasksInBuffer$volatile");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReferenceArray f5077a = new AtomicReferenceArray(128);
    private volatile /* synthetic */ int blockingTasksInBuffer$volatile;
    private volatile /* synthetic */ int consumerIndex$volatile;
    private volatile /* synthetic */ Object lastScheduledTask$volatile;
    private volatile /* synthetic */ int producerIndex$volatile;

    public final i a(i iVar) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f5074c;
        if (atomicIntegerFieldUpdater.get(this) - f5075d.get(this) == 127) {
            return iVar;
        }
        if (iVar.f5064e) {
            f5076e.incrementAndGet(this);
        }
        int i5 = atomicIntegerFieldUpdater.get(this) & 127;
        while (true) {
            AtomicReferenceArray atomicReferenceArray = this.f5077a;
            if (atomicReferenceArray.get(i5) == null) {
                atomicReferenceArray.lazySet(i5, iVar);
                atomicIntegerFieldUpdater.incrementAndGet(this);
                return null;
            }
            Thread.yield();
        }
    }

    public final i b() {
        i iVar;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f5075d;
            int i5 = atomicIntegerFieldUpdater.get(this);
            if (i5 - f5074c.get(this) == 0) {
                return null;
            }
            int i6 = i5 & 127;
            if (atomicIntegerFieldUpdater.compareAndSet(this, i5, i5 + 1) && (iVar = (i) this.f5077a.getAndSet(i6, null)) != null) {
                if (iVar.f5064e) {
                    f5076e.decrementAndGet(this);
                }
                return iVar;
            }
        }
    }

    public final i c(int i5, boolean z2) {
        int i6 = i5 & 127;
        AtomicReferenceArray atomicReferenceArray = this.f5077a;
        i iVar = (i) atomicReferenceArray.get(i6);
        if (iVar != null && iVar.f5064e == z2) {
            while (!atomicReferenceArray.compareAndSet(i6, iVar, null)) {
                if (atomicReferenceArray.get(i6) != iVar) {
                }
            }
            if (z2) {
                f5076e.decrementAndGet(this);
            }
            return iVar;
        }
        return null;
    }
}
