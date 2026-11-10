package i4;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j extends w0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f2742h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h f2743i;

    public /* synthetic */ j(h hVar, int i5) {
        this.f2742h = i5;
        this.f2743i = hVar;
    }

    @Override // i4.w0
    public final boolean j() {
        switch (this.f2742h) {
            case 0:
                return true;
            default:
                return false;
        }
    }

    @Override // i4.w0
    public final void k(Throwable th) {
        switch (this.f2742h) {
            case 0:
                a1 a1VarI = i();
                h hVar = this.f2743i;
                Throwable thN = hVar.n(a1VarI);
                if (hVar.v()) {
                    n4.f fVar = (n4.f) hVar.f2735g;
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = n4.f.f4279k;
                    while (true) {
                        Object obj = atomicReferenceFieldUpdater.get(fVar);
                        b3.r rVar = n4.a.f4269c;
                        if (b4.l.a(obj, rVar)) {
                            while (!atomicReferenceFieldUpdater.compareAndSet(fVar, rVar, thN)) {
                                if (atomicReferenceFieldUpdater.get(fVar) != rVar) {
                                }
                            }
                            break;
                        } else if (obj instanceof Throwable) {
                            break;
                        } else {
                            while (true) {
                                if (!atomicReferenceFieldUpdater.compareAndSet(fVar, obj, null)) {
                                    if (atomicReferenceFieldUpdater.get(fVar) != obj) {
                                    }
                                }
                            }
                        }
                    }
                }
                hVar.e(thN);
                if (!hVar.v()) {
                    hVar.l();
                }
                break;
            default:
                this.f2743i.resumeWith(p3.n.f5026a);
                break;
        }
    }
}
