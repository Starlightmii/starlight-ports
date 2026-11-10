package i4;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q0 extends w0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2768i = AtomicIntegerFieldUpdater.newUpdater(q0.class, "_invoked$volatile");
    private volatile /* synthetic */ int _invoked$volatile = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final v0 f2769h;

    public q0(v0 v0Var) {
        this.f2769h = v0Var;
    }

    @Override // i4.w0
    public final boolean j() {
        return true;
    }

    @Override // i4.w0
    public final void k(Throwable th) {
        if (f2768i.compareAndSet(this, 0, 1)) {
            this.f2769h.c(th);
        }
    }
}
