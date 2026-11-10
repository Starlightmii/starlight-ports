package i4;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b3.r f2786a = new b3.r("RESUME_TOKEN", 1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b3.r f2787b = new b3.r("REMOVED_TASK", 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b3.r f2788c = new b3.r("CLOSED_EMPTY", 1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b3.r f2789d = new b3.r("COMPLETING_ALREADY", 1);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b3.r f2790e = new b3.r("COMPLETING_WAITING_CHILDREN", 1);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b3.r f2791f = new b3.r("COMPLETING_RETRY", 1);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b3.r f2792g = new b3.r("TOO_LATE_TO_CANCEL", 1);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final b3.r f2793h = new b3.r("SEALED", 1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final g0 f2794i = new g0(false);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final g0 f2795j = new g0(true);

    public static final n4.c a(t3.h hVar) {
        if (hVar.j(s.f2774e) == null) {
            hVar = hVar.k(new u0(null));
        }
        return new n4.c(hVar);
    }

    public static final void b(v vVar, CancellationException cancellationException) {
        s0 s0Var = (s0) vVar.h().j(s.f2774e);
        if (s0Var != null) {
            s0Var.b(cancellationException);
        } else {
            throw new IllegalStateException(("Scope cannot be cancelled because it does not have a job: " + vVar).toString());
        }
    }

    public static final Object c(a4.e eVar, t3.c cVar) {
        n4.p pVar = new n4.p(cVar, cVar.getContext());
        return h1.c.O(pVar, pVar, eVar);
    }

    public static final Object d(long j5, v3.c cVar) {
        if (j5 > 0) {
            h hVar = new h(1, p3.j.B(cVar));
            hVar.p();
            if (j5 < Long.MAX_VALUE) {
                t3.f fVarJ = hVar.f2736h.j(t3.d.f5806d);
                b0 b0Var = fVarJ instanceof b0 ? (b0) fVarJ : null;
                if (b0Var == null) {
                    b0Var = a0.f2714a;
                }
                b0Var.d(j5, hVar);
            }
            Object objO = hVar.o();
            if (objO == u3.a.f5838d) {
                return objO;
            }
        }
        return p3.n.f5026a;
    }

    public static final void e(t3.h hVar) {
        s0 s0Var = (s0) hVar.j(s.f2774e);
        if (s0Var != null && !s0Var.a()) {
            throw s0Var.m();
        }
    }

    public static final t3.h f(t3.h hVar, t3.h hVar2, boolean z2) {
        Boolean bool = Boolean.FALSE;
        boolean zBooleanValue = ((Boolean) hVar.t(new f0.k(4), bool)).booleanValue();
        boolean zBooleanValue2 = ((Boolean) hVar2.t(new f0.k(4), bool)).booleanValue();
        if (!zBooleanValue && !zBooleanValue2) {
            return hVar.k(hVar2);
        }
        f0.k kVar = new f0.k(2);
        t3.i iVar = t3.i.f5807d;
        t3.h hVar3 = (t3.h) hVar.t(kVar, iVar);
        Object objT = hVar2;
        if (zBooleanValue2) {
            objT = hVar2.t(new f0.k(3), iVar);
        }
        return hVar3.k((t3.h) objT);
    }

    public static final String g(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final s0 h(t3.h hVar) {
        s0 s0Var = (s0) hVar.j(s.f2774e);
        if (s0Var != null) {
            return s0Var;
        }
        throw new IllegalStateException(("Current context doesn't contain Job in it: " + hVar).toString());
    }

    public static final h i(t3.c cVar) {
        h hVar;
        h hVar2;
        if (!(cVar instanceof n4.f)) {
            return new h(1, cVar);
        }
        n4.f fVar = (n4.f) cVar;
        b3.r rVar = n4.a.f4269c;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = n4.f.f4279k;
        loop0: while (true) {
            Object obj = atomicReferenceFieldUpdater.get(fVar);
            hVar = null;
            if (obj == null) {
                atomicReferenceFieldUpdater.set(fVar, rVar);
                hVar2 = null;
                break;
            }
            if (obj instanceof h) {
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(fVar, obj, rVar)) {
                        hVar2 = (h) obj;
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(fVar) == obj);
            } else if (obj != rVar && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
        if (hVar2 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = h.f2733j;
            Object obj2 = atomicReferenceFieldUpdater2.get(hVar2);
            if (!(obj2 instanceof n) || ((n) obj2).f2758d == null) {
                h.f2732i.set(hVar2, 536870911);
                atomicReferenceFieldUpdater2.set(hVar2, b.f2717a);
                hVar = hVar2;
            } else {
                hVar2.l();
            }
            if (hVar != null) {
                return hVar;
            }
        }
        return new h(2, cVar);
    }

    public static final void j(Throwable th, t3.h hVar) {
        try {
            t tVar = (t) hVar.j(s.f2773d);
            if (tVar != null) {
                tVar.s(th, hVar);
            } else {
                n4.a.d(th, hVar);
            }
        } catch (Throwable th2) {
            if (th != th2) {
                RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                d4.a.f(runtimeException, th);
                th = runtimeException;
            }
            n4.a.d(th, hVar);
        }
    }

    public static final f0 k(s0 s0Var, boolean z2, w0 w0Var) {
        if (s0Var instanceof a1) {
            return ((a1) s0Var).M(z2, w0Var);
        }
        return s0Var.c(w0Var.j(), z2, new v0(1, w0Var, w0.class, "invoke", "invoke(Ljava/lang/Throwable;)V", 0, 0, 0));
    }

    public static final boolean l(v vVar) {
        s0 s0Var = (s0) vVar.h().j(s.f2774e);
        if (s0Var != null) {
            return s0Var.a();
        }
        return true;
    }

    public static final boolean m(t3.h hVar) {
        s0 s0Var = (s0) hVar.j(s.f2774e);
        if (s0Var != null) {
            return s0Var.a();
        }
        return true;
    }

    public static h1 n(v vVar, t3.h hVar, a4.e eVar, int i5) {
        if ((i5 & 1) != 0) {
            hVar = t3.i.f5807d;
        }
        w wVar = (i5 & 2) != 0 ? w.f2779d : w.f2782g;
        t3.h hVarF = f(vVar.h(), hVar, true);
        p4.e eVar2 = e0.f2727a;
        if (hVarF != eVar2 && hVarF.j(t3.d.f5806d) == null) {
            hVarF = hVarF.k(eVar2);
        }
        h1 b1Var = wVar == w.f2780e ? new b1(hVarF, eVar) : new h1(hVarF, true);
        b1Var.c0(wVar, b1Var, eVar);
        return b1Var;
    }

    public static final Object o(Object obj) {
        return obj instanceof o ? p3.j.r(((o) obj).f2763a) : obj;
    }

    public static final void p(h hVar, t3.c cVar, boolean z2) {
        Object obj = h.f2733j.get(hVar);
        Throwable thD = hVar.d(obj);
        Object objR = thD != null ? p3.j.r(thD) : hVar.f(obj);
        if (!z2) {
            cVar.resumeWith(objR);
            return;
        }
        b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTaskKt.resume>", cVar);
        n4.f fVar = (n4.f) cVar;
        v3.c cVar2 = fVar.f4281h;
        Object obj2 = fVar.f4283j;
        t3.h context = cVar2.getContext();
        Object objL = n4.a.l(context, obj2);
        n1 n1VarS = objL != n4.a.f4270d ? s(cVar2, context, objL) : null;
        try {
            cVar2.resumeWith(objR);
        } finally {
            if (n1VarS == null || n1VarS.d0()) {
                n4.a.g(context, objL);
            }
        }
    }

    public static final String q(t3.c cVar) {
        Object objR;
        if (cVar instanceof n4.f) {
            return ((n4.f) cVar).toString();
        }
        try {
            objR = cVar + '@' + g(cVar);
        } catch (Throwable th) {
            objR = p3.j.r(th);
        }
        if (p3.i.a(objR) != null) {
            objR = cVar.getClass().getName() + '@' + g(cVar);
        }
        return (String) objR;
    }

    public static final Object r(Object obj) {
        o0 o0Var;
        p0 p0Var = obj instanceof p0 ? (p0) obj : null;
        return (p0Var == null || (o0Var = p0Var.f2765a) == null) ? obj : o0Var;
    }

    public static final n1 s(t3.c cVar, t3.h hVar, Object obj) {
        n1 n1Var = null;
        if ((cVar instanceof v3.d) && hVar.j(o1.f2764d) != null) {
            v3.d callerFrame = (v3.d) cVar;
            while (!(callerFrame instanceof c0) && (callerFrame = callerFrame.getCallerFrame()) != null) {
                if (callerFrame instanceof n1) {
                    n1Var = (n1) callerFrame;
                    break;
                }
            }
            if (n1Var != null) {
                n1Var.e0(hVar, obj);
            }
        }
        return n1Var;
    }

    public static final Object t(t3.h hVar, a4.e eVar, v3.i iVar) throws Throwable {
        t3.h context = iVar.getContext();
        t3.h hVarK = !((Boolean) hVar.t(new f0.k(4), Boolean.FALSE)).booleanValue() ? context.k(hVar) : f(context, hVar, false);
        e(hVarK);
        if (hVarK == context) {
            n4.p pVar = new n4.p(iVar, hVarK);
            return h1.c.O(pVar, pVar, eVar);
        }
        t3.d dVar = t3.d.f5806d;
        if (b4.l.a(hVarK.j(dVar), context.j(dVar))) {
            n1 n1Var = new n1(hVarK, iVar);
            t3.h hVar2 = n1Var.f2713f;
            Object objL = n4.a.l(hVar2, null);
            try {
                return h1.c.O(n1Var, n1Var, eVar);
            } finally {
                n4.a.g(hVar2, objL);
            }
        }
        c0 c0Var = new c0(iVar, hVarK);
        try {
            n4.a.h(p3.n.f5026a, p3.j.B(p3.j.q(eVar, c0Var, c0Var)));
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = c0.f2721h;
            do {
                int i5 = atomicIntegerFieldUpdater.get(c0Var);
                if (i5 != 0) {
                    if (i5 != 2) {
                        throw new IllegalStateException("Already suspended");
                    }
                    Object objR = r(a1.f2715d.get(c0Var));
                    if (objR instanceof o) {
                        throw ((o) objR).f2763a;
                    }
                    return objR;
                }
            } while (!atomicIntegerFieldUpdater.compareAndSet(c0Var, 0, 1));
            return u3.a.f5838d;
        } catch (Throwable th) {
            c0Var.resumeWith(p3.j.r(th));
            throw th;
        }
    }
}
