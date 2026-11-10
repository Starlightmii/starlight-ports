package q4;

import b3.r;
import i4.y;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import p3.j;
import p3.n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends g implements a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f5483g = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "owner$volatile");
    private volatile /* synthetic */ Object owner$volatile = d.f5484a;

    public final Object c(v3.c cVar) {
        int i5;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = g.f5491f;
            int i6 = atomicIntegerFieldUpdater.get(this);
            if (i6 > 1) {
                do {
                    i5 = atomicIntegerFieldUpdater.get(this);
                    if (i5 <= 1) {
                        break;
                    }
                } while (!atomicIntegerFieldUpdater.compareAndSet(this, i5, 1));
            } else {
                n nVar = n.f5026a;
                if (i6 <= 0) {
                    i4.h hVarI = y.i(j.B(cVar));
                    try {
                        a(new b(this, hVarI));
                        Object objO = hVarI.o();
                        u3.a aVar = u3.a.f5838d;
                        if (objO != aVar) {
                            objO = nVar;
                        }
                        return objO == aVar ? objO : nVar;
                    } catch (Throwable th) {
                        hVarI.z();
                        throw th;
                    }
                }
                if (atomicIntegerFieldUpdater.compareAndSet(this, i6, i6 - 1)) {
                    f5483g.set(this, null);
                    return nVar;
                }
            }
        }
    }

    public final void d(Object obj) {
        while (Math.max(g.f5491f.get(this), 0) == 0) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5483g;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            r rVar = d.f5484a;
            if (obj2 != rVar) {
                if (obj2 != obj && obj != null) {
                    throw new IllegalStateException(("This mutex is locked by " + obj2 + ", but " + obj + " is expected").toString());
                }
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, rVar)) {
                        b();
                        return;
                    }
                } while (atomicReferenceFieldUpdater.get(this) == obj2);
            }
        }
        throw new IllegalStateException("This mutex is not locked");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Mutex@");
        sb.append(y.g(this));
        sb.append("[isLocked=");
        sb.append(Math.max(g.f5491f.get(this), 0) == 0);
        sb.append(",owner=");
        sb.append(f5483g.get(this));
        sb.append(']');
        return sb.toString();
    }
}
