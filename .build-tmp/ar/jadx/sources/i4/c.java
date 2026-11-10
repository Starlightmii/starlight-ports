package i4;

import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Thread f2719g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final l0 f2720h;

    public c(t3.h hVar, Thread thread, l0 l0Var) {
        super(hVar, true);
        this.f2719g = thread;
        this.f2720h = l0Var;
    }

    @Override // i4.a1
    public final void u(Object obj) {
        Thread threadCurrentThread = Thread.currentThread();
        Thread thread = this.f2719g;
        if (b4.l.a(threadCurrentThread, thread)) {
            return;
        }
        LockSupport.unpark(thread);
    }
}
