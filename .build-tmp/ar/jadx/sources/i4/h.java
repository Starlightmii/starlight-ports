package i4;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class h extends d0 implements f, v3.d, p1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2732i = AtomicIntegerFieldUpdater.newUpdater(h.class, "_decisionAndIndex$volatile");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2733j = AtomicReferenceFieldUpdater.newUpdater(h.class, Object.class, "_state$volatile");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2734k = AtomicReferenceFieldUpdater.newUpdater(h.class, Object.class, "_parentHandle$volatile");
    private volatile /* synthetic */ int _decisionAndIndex$volatile;
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final t3.c f2735g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final t3.h f2736h;

    public h(int i5, t3.c cVar) {
        super(i5);
        this.f2735g = cVar;
        this.f2736h = cVar.getContext();
        this._decisionAndIndex$volatile = 536870911;
        this._state$volatile = b.f2717a;
    }

    public static Object D(f1 f1Var, Object obj, int i5, a4.f fVar) {
        if (obj instanceof o) {
            return obj;
        }
        if (i5 != 1 && i5 != 2) {
            return obj;
        }
        if (fVar != null || (f1Var instanceof e)) {
            return new n(obj, f1Var instanceof e ? (e) f1Var : null, fVar, (Throwable) null, 16);
        }
        return obj;
    }

    public static void x(f1 f1Var, Object obj) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + f1Var + ", already has " + obj).toString());
    }

    public final void A(Object obj, a4.f fVar) {
        B(obj, this.f2723f, fVar);
    }

    public final void B(Object obj, int i5, a4.f fVar) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2733j;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof f1)) {
                if (obj2 instanceof i) {
                    i iVar = (i) obj2;
                    if (i.f2739c.compareAndSet(iVar, 0, 1)) {
                        if (fVar != null) {
                            j(fVar, iVar.f2763a, obj);
                            return;
                        }
                        return;
                    }
                }
                throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
            }
            Object objD = D((f1) obj2, obj, i5, fVar);
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, objD)) {
                    if (!v()) {
                        l();
                    }
                    m(i5);
                    return;
                }
            } while (atomicReferenceFieldUpdater.get(this) == obj2);
        }
    }

    public final void C(r rVar) {
        t3.c cVar = this.f2735g;
        n4.f fVar = cVar instanceof n4.f ? (n4.f) cVar : null;
        B(p3.n.f5026a, (fVar != null ? fVar.f4280g : null) == rVar ? 4 : this.f2723f, null);
    }

    @Override // i4.p1
    public final void a(n4.q qVar, int i5) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i6;
        do {
            atomicIntegerFieldUpdater = f2732i;
            i6 = atomicIntegerFieldUpdater.get(this);
            if ((i6 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once");
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i6, ((i6 >> 29) << 29) + i5));
        t(qVar);
    }

    @Override // i4.d0
    public final void b(CancellationException cancellationException) {
        CancellationException cancellationException2;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2733j;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof f1) {
                throw new IllegalStateException("Not completed");
            }
            if (obj instanceof o) {
                return;
            }
            if (!(obj instanceof n)) {
                cancellationException2 = cancellationException;
                n nVar = new n(obj, (e) null, (a4.f) null, cancellationException2, 14);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, nVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                    }
                }
                return;
            }
            n nVar2 = (n) obj;
            if (nVar2.f2759e != null) {
                throw new IllegalStateException("Must be called at most once");
            }
            n nVarA = n.a(nVar2, null, cancellationException, 15);
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj, nVarA)) {
                    e eVar = nVar2.f2756b;
                    if (eVar != null) {
                        i(eVar, cancellationException);
                    }
                    a4.f fVar = nVar2.f2757c;
                    if (fVar != null) {
                        j(fVar, cancellationException, nVar2.f2755a);
                        return;
                    }
                    return;
                }
            } while (atomicReferenceFieldUpdater.get(this) == obj);
            cancellationException2 = cancellationException;
            cancellationException = cancellationException2;
        }
    }

    @Override // i4.d0
    public final t3.c c() {
        return this.f2735g;
    }

    @Override // i4.d0
    public final Throwable d(Object obj) {
        Throwable thD = super.d(obj);
        if (thD != null) {
            return thD;
        }
        return null;
    }

    @Override // i4.f
    public final boolean e(Throwable th) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2733j;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof f1)) {
                return false;
            }
            i iVar = new i(this, th, (obj instanceof e) || (obj instanceof n4.q));
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj, iVar)) {
                    f1 f1Var = (f1) obj;
                    if (f1Var instanceof e) {
                        i((e) obj, th);
                    } else if (f1Var instanceof n4.q) {
                        k((n4.q) obj, th);
                    }
                    if (!v()) {
                        l();
                    }
                    m(this.f2723f);
                    return true;
                }
            } while (atomicReferenceFieldUpdater.get(this) == obj);
        }
    }

    @Override // i4.d0
    public final Object f(Object obj) {
        return obj instanceof n ? ((n) obj).f2755a : obj;
    }

    @Override // v3.d
    public final v3.d getCallerFrame() {
        t3.c cVar = this.f2735g;
        if (cVar instanceof v3.d) {
            return (v3.d) cVar;
        }
        return null;
    }

    @Override // t3.c
    public final t3.h getContext() {
        return this.f2736h;
    }

    @Override // i4.d0
    public final Object h() {
        return f2733j.get(this);
    }

    public final void i(e eVar, Throwable th) {
        try {
            switch (eVar.f2725a) {
                case 0:
                    ((a4.c) eVar.f2726b).c(th);
                    break;
                default:
                    ((f0) eVar.f2726b).dispose();
                    break;
            }
        } catch (Throwable th2) {
            y.j(new p("Exception in invokeOnCancellation handler for " + this, th2), this.f2736h);
        }
    }

    public final void j(a4.f fVar, Throwable th, Object obj) {
        t3.h hVar = this.f2736h;
        try {
            fVar.b(th, obj, hVar);
        } catch (Throwable th2) {
            y.j(new p("Exception in resume onCancellation handler for " + this, th2), hVar);
        }
    }

    public final void k(n4.q qVar, Throwable th) {
        t3.h hVar = this.f2736h;
        int i5 = f2732i.get(this) & 536870911;
        if (i5 == 536870911) {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken");
        }
        try {
            qVar.g(i5, hVar);
        } catch (Throwable th2) {
            y.j(new p("Exception in invokeOnCancellation handler for " + this, th2), hVar);
        }
    }

    public final void l() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2734k;
        f0 f0Var = (f0) atomicReferenceFieldUpdater.get(this);
        if (f0Var == null) {
            return;
        }
        f0Var.dispose();
        atomicReferenceFieldUpdater.set(this, e1.f2728d);
    }

    public final void m(int i5) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i6;
        do {
            atomicIntegerFieldUpdater = f2732i;
            i6 = atomicIntegerFieldUpdater.get(this);
            int i7 = i6 >> 29;
            if (i7 != 0) {
                if (i7 != 1) {
                    throw new IllegalStateException("Already resumed");
                }
                boolean z2 = i5 == 4;
                t3.c cVar = this.f2735g;
                if (!z2 && (cVar instanceof n4.f)) {
                    boolean z3 = i5 == 1 || i5 == 2;
                    int i8 = this.f2723f;
                    if (z3 == (i8 == 1 || i8 == 2)) {
                        n4.f fVar = (n4.f) cVar;
                        r rVar = fVar.f4280g;
                        t3.h context = fVar.f4281h.getContext();
                        if (rVar.h(context)) {
                            rVar.e(context, this);
                            return;
                        }
                        l0 l0VarA = k1.a();
                        if (l0VarA.f2751f >= 4294967296L) {
                            l0VarA.o(this);
                            return;
                        }
                        l0VarA.u(true);
                        try {
                            y.p(this, cVar, true);
                            do {
                            } while (l0VarA.w());
                        } catch (Throwable th) {
                            try {
                                g(th);
                            } finally {
                                l0VarA.n(true);
                            }
                        }
                        return;
                    }
                }
                y.p(this, cVar, z2);
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i6, 1073741824 + (536870911 & i6)));
    }

    public Throwable n(a1 a1Var) {
        return a1Var.m();
    }

    public final Object o() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i5;
        s0 s0Var;
        boolean zV = v();
        do {
            atomicIntegerFieldUpdater = f2732i;
            i5 = atomicIntegerFieldUpdater.get(this);
            int i6 = i5 >> 29;
            if (i6 != 0) {
                if (i6 != 2) {
                    throw new IllegalStateException("Already suspended");
                }
                if (zV) {
                    z();
                }
                Object obj = f2733j.get(this);
                if (obj instanceof o) {
                    throw ((o) obj).f2763a;
                }
                int i7 = this.f2723f;
                if ((i7 != 1 && i7 != 2) || (s0Var = (s0) this.f2736h.j(s.f2774e)) == null || s0Var.a()) {
                    return f(obj);
                }
                CancellationException cancellationExceptionM = s0Var.m();
                b(cancellationExceptionM);
                throw cancellationExceptionM;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i5, 536870912 + (536870911 & i5)));
        if (((f0) f2734k.get(this)) == null) {
            q();
        }
        if (zV) {
            z();
        }
        return u3.a.f5838d;
    }

    public final void p() {
        f0 f0VarQ = q();
        if (f0VarQ == null || (f2733j.get(this) instanceof f1)) {
            return;
        }
        f0VarQ.dispose();
        f2734k.set(this, e1.f2728d);
    }

    public final f0 q() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        s0 s0Var = (s0) this.f2736h.j(s.f2774e);
        if (s0Var == null) {
            return null;
        }
        f0 f0VarK = y.k(s0Var, true, new j(this, 0));
        do {
            atomicReferenceFieldUpdater = f2734k;
            if (atomicReferenceFieldUpdater.compareAndSet(this, null, f0VarK)) {
                break;
            }
        } while (atomicReferenceFieldUpdater.get(this) == null);
        return f0VarK;
    }

    @Override // i4.f
    public final b3.r r(Object obj, a4.f fVar) {
        b3.r rVar = y.f2786a;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2733j;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof f1)) {
                return null;
            }
            Object objD = D((f1) obj2, obj, this.f2723f, fVar);
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, objD)) {
                    if (!v()) {
                        l();
                    }
                    return rVar;
                }
            } while (atomicReferenceFieldUpdater.get(this) == obj2);
        }
    }

    @Override // t3.c
    public final void resumeWith(Object obj) {
        Throwable thA = p3.i.a(obj);
        if (thA != null) {
            obj = new o(thA, false);
        }
        B(obj, this.f2723f, null);
    }

    public final void s(a4.c cVar) {
        t(new e(0, cVar));
    }

    public final void t(f1 f1Var) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2733j;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof b) {
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, f1Var)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                    }
                }
                return;
            }
            if ((obj instanceof e) || (obj instanceof n4.q)) {
                x(f1Var, obj);
                throw null;
            }
            if (obj instanceof o) {
                o oVar = (o) obj;
                if (!o.f2762b.compareAndSet(oVar, 0, 1)) {
                    x(f1Var, obj);
                    throw null;
                }
                if (obj instanceof i) {
                    Throwable th = oVar.f2763a;
                    if (f1Var instanceof e) {
                        i((e) f1Var, th);
                        return;
                    } else {
                        k((n4.q) f1Var, th);
                        return;
                    }
                }
                return;
            }
            if (!(obj instanceof n)) {
                if (f1Var instanceof n4.q) {
                    return;
                }
                n nVar = new n(obj, (e) f1Var, (a4.f) null, (Throwable) null, 28);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, nVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                    }
                }
                return;
            }
            n nVar2 = (n) obj;
            if (nVar2.f2756b != null) {
                x(f1Var, obj);
                throw null;
            }
            if (f1Var instanceof n4.q) {
                return;
            }
            e eVar = (e) f1Var;
            Throwable th2 = nVar2.f2759e;
            if (th2 != null) {
                i(eVar, th2);
                return;
            }
            n nVarA = n.a(nVar2, eVar, null, 29);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, nVarA)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                }
            }
            return;
        }
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(y());
        sb.append('(');
        sb.append(y.q(this.f2735g));
        sb.append("){");
        Object obj = f2733j.get(this);
        if (obj instanceof f1) {
            str = "Active";
        } else {
            str = obj instanceof i ? "Cancelled" : "Completed";
        }
        sb.append(str);
        sb.append("}@");
        sb.append(y.g(this));
        return sb.toString();
    }

    public final boolean u() {
        return f2733j.get(this) instanceof f1;
    }

    public final boolean v() {
        if (this.f2723f != 2) {
            return false;
        }
        t3.c cVar = this.f2735g;
        b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>", cVar);
        return n4.f.f4279k.get((n4.f) cVar) != null;
    }

    @Override // i4.f
    public final void w(Object obj) {
        m(this.f2723f);
    }

    public String y() {
        return "CancellableContinuation";
    }

    public final void z() {
        t3.c cVar = this.f2735g;
        Throwable th = null;
        n4.f fVar = cVar instanceof n4.f ? (n4.f) cVar : null;
        if (fVar != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = n4.f.f4279k;
            loop0: while (true) {
                Object obj = atomicReferenceFieldUpdater.get(fVar);
                b3.r rVar = n4.a.f4269c;
                if (obj != rVar) {
                    if (!(obj instanceof Throwable)) {
                        throw new IllegalStateException(("Inconsistent state " + obj).toString());
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(fVar, obj, null)) {
                        if (atomicReferenceFieldUpdater.get(fVar) != obj) {
                            throw new IllegalArgumentException("Failed requirement.");
                        }
                    }
                    th = (Throwable) obj;
                    break;
                }
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(fVar, rVar, this)) {
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(fVar) == rVar);
            }
            if (th == null) {
                return;
            }
            l();
            e(th);
        }
    }
}
