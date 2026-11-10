package i4;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 extends n4.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2721h = AtomicIntegerFieldUpdater.newUpdater(c0.class, "_decision$volatile");
    private volatile /* synthetic */ int _decision$volatile;

    @Override // n4.p, i4.a1
    public final void u(Object obj) {
        v(obj);
    }

    @Override // n4.p, i4.a1
    public final void v(Object obj) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        do {
            atomicIntegerFieldUpdater = f2721h;
            int i5 = atomicIntegerFieldUpdater.get(this);
            if (i5 != 0) {
                if (i5 != 1) {
                    throw new IllegalStateException("Already resumed");
                }
                n4.a.h(y.o(obj), p3.j.B(this.f4305g));
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, 0, 2));
    }
}
