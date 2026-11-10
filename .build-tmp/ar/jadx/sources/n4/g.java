package n4;

import i4.a0;
import i4.b0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g extends i4.r implements b0 {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f4284k = AtomicIntegerFieldUpdater.newUpdater(g.class, "runningWorkers$volatile");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ b0 f4285f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final i4.r f4286g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f4287h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final j f4288i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f4289j;
    private volatile /* synthetic */ int runningWorkers$volatile;

    /* JADX WARN: Multi-variable type inference failed */
    public g(i4.r rVar, int i5) {
        b0 b0Var = rVar instanceof b0 ? (b0) rVar : null;
        this.f4285f = b0Var == null ? a0.f2714a : b0Var;
        this.f4286g = rVar;
        this.f4287h = i5;
        this.f4288i = new j();
        this.f4289j = new Object();
    }

    @Override // i4.b0
    public final void d(long j5, i4.h hVar) {
        this.f4285f.d(j5, hVar);
    }

    @Override // i4.r
    public final void e(t3.h hVar, Runnable runnable) {
        boolean z2;
        Runnable runnableN;
        this.f4288i.a(runnable);
        if (f4284k.get(this) < this.f4287h) {
            synchronized (this.f4289j) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f4284k;
                if (atomicIntegerFieldUpdater.get(this) >= this.f4287h) {
                    z2 = false;
                } else {
                    atomicIntegerFieldUpdater.incrementAndGet(this);
                    z2 = true;
                }
            }
            if (!z2 || (runnableN = n()) == null) {
                return;
            }
            this.f4286g.e(this, new j4.c(this, runnableN));
        }
    }

    public final Runnable n() {
        while (true) {
            Runnable runnable = (Runnable) this.f4288i.d();
            if (runnable != null) {
                return runnable;
            }
            synchronized (this.f4289j) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f4284k;
                atomicIntegerFieldUpdater.decrementAndGet(this);
                if (this.f4288i.c() == 0) {
                    return null;
                }
                atomicIntegerFieldUpdater.incrementAndGet(this);
            }
        }
    }

    @Override // i4.r
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f4286g);
        sb.append(".limitedParallelism(");
        return b.b.f(sb, this.f4287h, ')');
    }
}
