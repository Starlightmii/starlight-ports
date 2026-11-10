package n4;

import c0.s1;
import i4.y;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f4291d = AtomicReferenceFieldUpdater.newUpdater(i.class, Object.class, "_next$volatile");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f4292e = AtomicReferenceFieldUpdater.newUpdater(i.class, Object.class, "_prev$volatile");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f4293f = AtomicReferenceFieldUpdater.newUpdater(i.class, Object.class, "_removedRef$volatile");
    private volatile /* synthetic */ Object _next$volatile = this;
    private volatile /* synthetic */ Object _prev$volatile = this;
    private volatile /* synthetic */ Object _removedRef$volatile;

    public final boolean d(i iVar, int i5) {
        while (true) {
            i iVarE = e();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4292e;
            if (iVarE == null) {
                Object obj = atomicReferenceFieldUpdater.get(this);
                while (true) {
                    iVarE = (i) obj;
                    if (!iVarE.h()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(iVarE);
                }
            }
            if (iVarE instanceof h) {
                return (((h) iVarE).f4290g & i5) == 0 && iVarE.d(iVar, i5);
            }
            atomicReferenceFieldUpdater.set(iVar, iVarE);
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f4291d;
            atomicReferenceFieldUpdater2.set(iVar, this);
            do {
                if (atomicReferenceFieldUpdater2.compareAndSet(iVarE, this, iVar)) {
                    iVar.f(this);
                    return true;
                }
            } while (atomicReferenceFieldUpdater2.get(iVarE) == this);
        }
    }

    public final i e() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Object obj;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f4292e;
            i iVar = (i) atomicReferenceFieldUpdater2.get(this);
            i iVar2 = iVar;
            while (true) {
                i iVar3 = null;
                while (true) {
                    atomicReferenceFieldUpdater = f4291d;
                    obj = atomicReferenceFieldUpdater.get(iVar2);
                    if (obj == this) {
                        if (iVar == iVar2) {
                            return iVar2;
                        }
                        while (!atomicReferenceFieldUpdater2.compareAndSet(this, iVar, iVar2)) {
                            if (atomicReferenceFieldUpdater2.get(this) != iVar) {
                                break;
                            }
                        }
                        return iVar2;
                    }
                    if (h()) {
                        return null;
                    }
                    if (!(obj instanceof n)) {
                        b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode", obj);
                        iVar3 = iVar2;
                        iVar2 = (i) obj;
                    } else {
                        if (iVar3 != null) {
                            break;
                        }
                        iVar2 = (i) atomicReferenceFieldUpdater2.get(iVar2);
                    }
                }
                i iVar4 = ((n) obj).f4304a;
                while (!atomicReferenceFieldUpdater.compareAndSet(iVar3, iVar2, iVar4)) {
                    if (atomicReferenceFieldUpdater.get(iVar3) != iVar2) {
                        break;
                    }
                }
                iVar2 = iVar3;
            }
        }
    }

    public final void f(i iVar) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4292e;
            i iVar2 = (i) atomicReferenceFieldUpdater.get(iVar);
            if (f4291d.get(this) != iVar) {
                return;
            }
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(iVar, iVar2, this)) {
                    if (h()) {
                        iVar.e();
                        return;
                    }
                    return;
                }
            } while (atomicReferenceFieldUpdater.get(iVar) == iVar2);
        }
    }

    public final i g() {
        i iVar;
        Object obj = f4291d.get(this);
        n nVar = obj instanceof n ? (n) obj : null;
        if (nVar != null && (iVar = nVar.f4304a) != null) {
            return iVar;
        }
        b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode", obj);
        return (i) obj;
    }

    public boolean h() {
        return f4291d.get(this) instanceof n;
    }

    public String toString() {
        return new s1(this, y.class, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;", 1, 1) + '@' + y.g(this);
    }
}
