package i4;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class w0 extends n4.i implements f0, o0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a1 f2784g;

    @Override // i4.o0
    public final boolean a() {
        return true;
    }

    @Override // i4.o0
    public final c1 c() {
        return null;
    }

    @Override // i4.f0
    public final void dispose() {
        a1 a1VarI = i();
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a1.f2715d;
            Object obj = atomicReferenceFieldUpdater.get(a1VarI);
            if (obj instanceof w0) {
                if (obj != this) {
                    return;
                }
                g0 g0Var = y.f2795j;
                while (!atomicReferenceFieldUpdater.compareAndSet(a1VarI, obj, g0Var)) {
                    if (atomicReferenceFieldUpdater.get(a1VarI) != obj) {
                    }
                }
                return;
            }
            if (!(obj instanceof o0) || ((o0) obj).c() == null) {
                return;
            }
            while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = n4.i.f4291d;
                Object obj2 = atomicReferenceFieldUpdater2.get(this);
                if (obj2 instanceof n4.n) {
                    return;
                }
                if (obj2 == this) {
                    return;
                }
                b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode", obj2);
                n4.i iVar = (n4.i) obj2;
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3 = n4.i.f4293f;
                n4.n nVar = (n4.n) atomicReferenceFieldUpdater3.get(iVar);
                if (nVar == null) {
                    nVar = new n4.n(iVar);
                    atomicReferenceFieldUpdater3.set(iVar, nVar);
                }
                do {
                    if (atomicReferenceFieldUpdater2.compareAndSet(this, obj2, nVar)) {
                        iVar.e();
                        return;
                    }
                } while (atomicReferenceFieldUpdater2.get(this) == obj2);
            }
        }
    }

    public s0 getParent() {
        return i();
    }

    public final a1 i() {
        a1 a1Var = this.f2784g;
        if (a1Var != null) {
            return a1Var;
        }
        b4.l.j("job");
        throw null;
    }

    public abstract boolean j();

    public abstract void k(Throwable th);

    @Override // n4.i
    public final String toString() {
        return getClass().getSimpleName() + '@' + y.g(this) + "[job@" + y.g(i()) + ']';
    }
}
