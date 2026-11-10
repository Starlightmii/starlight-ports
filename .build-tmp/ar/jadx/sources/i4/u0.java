package i4;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class u0 extends a1 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f2777f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u0(s0 s0Var) {
        super(true);
        boolean z2 = true;
        L(s0Var);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a1.f2716e;
        k kVar = (k) atomicReferenceFieldUpdater.get(this);
        l lVar = kVar instanceof l ? (l) kVar : null;
        if (lVar == null) {
            z2 = false;
            break;
        }
        a1 a1VarI = lVar.i();
        while (!a1VarI.G()) {
            k kVar2 = (k) atomicReferenceFieldUpdater.get(a1VarI);
            l lVar2 = kVar2 instanceof l ? (l) kVar2 : null;
            if (lVar2 == null) {
                z2 = false;
                break;
            }
            a1VarI = lVar2.i();
        }
        this.f2777f = z2;
    }

    @Override // i4.a1
    public final boolean G() {
        return this.f2777f;
    }

    @Override // i4.a1
    public final boolean H() {
        return true;
    }
}
