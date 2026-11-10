package n4;

import i4.f1;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class q extends b implements f1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f4306d = AtomicIntegerFieldUpdater.newUpdater(q.class, "cleanedAndPointers$volatile");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f4307c;
    private volatile /* synthetic */ int cleanedAndPointers$volatile;

    public q(long j5, q qVar, int i5) {
        super(qVar);
        this.f4307c = j5;
        this.cleanedAndPointers$volatile = i5 << 16;
    }

    @Override // n4.b
    public final boolean c() {
        return f4306d.get(this) == f() && b() != null;
    }

    public final boolean e() {
        return f4306d.addAndGet(this, -65536) == f() && b() != null;
    }

    public abstract int f();

    public abstract void g(int i5, t3.h hVar);

    public final void h() {
        if (f4306d.incrementAndGet(this) == f()) {
            d();
        }
    }

    public final boolean i() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i5;
        do {
            atomicIntegerFieldUpdater = f4306d;
            i5 = atomicIntegerFieldUpdater.get(this);
            if (i5 == f() && b() != null) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i5, 65536 + i5));
        return true;
    }
}
