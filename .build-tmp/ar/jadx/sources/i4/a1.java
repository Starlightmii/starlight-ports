package i4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class a1 implements s0, g1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2715d = AtomicReferenceFieldUpdater.newUpdater(a1.class, Object.class, "_state$volatile");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2716e = AtomicReferenceFieldUpdater.newUpdater(a1.class, Object.class, "_parentHandle$volatile");
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile;

    public a1(boolean z2) {
        this._state$volatile = z2 ? y.f2795j : y.f2794i;
    }

    public static l Q(n4.i iVar) {
        while (iVar.h()) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = n4.i.f4292e;
            n4.i iVarE = iVar.e();
            if (iVarE == null) {
                Object obj = atomicReferenceFieldUpdater.get(iVar);
                while (true) {
                    iVar = (n4.i) obj;
                    if (!iVar.h()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(iVar);
                }
            } else {
                iVar = iVarE;
            }
        }
        while (true) {
            iVar = iVar.g();
            if (!iVar.h()) {
                if (iVar instanceof l) {
                    return (l) iVar;
                }
                if (iVar instanceof c1) {
                    return null;
                }
            }
        }
    }

    public static String X(Object obj) {
        if (!(obj instanceof z0)) {
            if (obj instanceof o0) {
                return ((o0) obj).a() ? "Active" : "New";
            }
            return obj instanceof o ? "Cancelled" : "Completed";
        }
        z0 z0Var = (z0) obj;
        if (z0Var.e()) {
            return "Cancelling";
        }
        return z0.f2802e.get(z0Var) != 0 ? "Completing" : "Active";
    }

    public String A() {
        return "Job was cancelled";
    }

    public boolean B(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        return x(th) && G();
    }

    public final void C(o0 o0Var, Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2716e;
        k kVar = (k) atomicReferenceFieldUpdater.get(this);
        if (kVar != null) {
            kVar.dispose();
            atomicReferenceFieldUpdater.set(this, e1.f2728d);
        }
        p pVar = null;
        o oVar = obj instanceof o ? (o) obj : null;
        Throwable th = oVar != null ? oVar.f2763a : null;
        if (o0Var instanceof w0) {
            try {
                ((w0) o0Var).k(th);
                return;
            } catch (Throwable th2) {
                K(new p("Exception in completion handler " + o0Var + " for " + this, th2));
                return;
            }
        }
        c1 c1VarC = o0Var.c();
        if (c1VarC != null) {
            c1VarC.d(new n4.h(1), 1);
            Object obj2 = n4.i.f4291d.get(c1VarC);
            b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode", obj2);
            for (n4.i iVarG = (n4.i) obj2; !iVarG.equals(c1VarC); iVarG = iVarG.g()) {
                if (iVarG instanceof w0) {
                    try {
                        ((w0) iVarG).k(th);
                    } catch (Throwable th3) {
                        if (pVar != null) {
                            d4.a.f(pVar, th3);
                        } else {
                            pVar = new p("Exception in completion handler " + iVarG + " for " + this, th3);
                        }
                    }
                }
            }
            if (pVar != null) {
                K(pVar);
            }
        }
    }

    public final Throwable D(Object obj) {
        Throwable thD;
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        a1 a1Var = (a1) ((g1) obj);
        Object obj2 = f2715d.get(a1Var);
        if (obj2 instanceof z0) {
            thD = ((z0) obj2).d();
        } else if (obj2 instanceof o) {
            thD = ((o) obj2).f2763a;
        } else {
            if (obj2 instanceof o0) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + obj2).toString());
            }
            thD = null;
        }
        CancellationException cancellationException = thD instanceof CancellationException ? (CancellationException) thD : null;
        return cancellationException == null ? new t0("Parent job is ".concat(X(obj2)), thD, a1Var) : cancellationException;
    }

    public final Object E(z0 z0Var, Object obj) {
        Throwable thF;
        o oVar = obj instanceof o ? (o) obj : null;
        Throwable th = oVar != null ? oVar.f2763a : null;
        synchronized (z0Var) {
            z0Var.e();
            ArrayList arrayListF = z0Var.f(th);
            thF = F(z0Var, arrayListF);
            if (thF != null && arrayListF.size() > 1) {
                Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(arrayListF.size()));
                int size = arrayListF.size();
                int i5 = 0;
                while (i5 < size) {
                    Object obj2 = arrayListF.get(i5);
                    i5++;
                    Throwable th2 = (Throwable) obj2;
                    if (th2 != thF && th2 != thF && !(th2 instanceof CancellationException) && setNewSetFromMap.add(th2)) {
                        d4.a.f(thF, th2);
                    }
                }
            }
        }
        if (thF != null && thF != th) {
            obj = new o(thF, false);
        }
        if (thF != null && (z(thF) || J(thF))) {
            b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally", obj);
            o.f2762b.compareAndSet((o) obj, 0, 1);
        }
        S(obj);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2715d;
        Object p0Var = obj instanceof o0 ? new p0((o0) obj) : obj;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, z0Var, p0Var) && atomicReferenceFieldUpdater.get(this) == z0Var) {
        }
        C(z0Var, obj);
        return obj;
    }

    public final Throwable F(z0 z0Var, ArrayList arrayList) {
        Object obj;
        Object obj2 = null;
        if (arrayList.isEmpty()) {
            if (z0Var.e()) {
                return new t0(A(), null, this);
            }
            return null;
        }
        int size = arrayList.size();
        int i5 = 0;
        int i6 = 0;
        do {
            if (i6 >= size) {
                obj = null;
                break;
            }
            obj = arrayList.get(i6);
            i6++;
        } while (((Throwable) obj) instanceof CancellationException);
        Throwable th = (Throwable) obj;
        if (th != null) {
            return th;
        }
        Throwable th2 = (Throwable) arrayList.get(0);
        if (th2 instanceof l1) {
            int size2 = arrayList.size();
            while (i5 < size2) {
                Object obj3 = arrayList.get(i5);
                i5++;
                Throwable th3 = (Throwable) obj3;
                if (th3 != th2 && (th3 instanceof l1)) {
                    obj2 = obj3;
                    break;
                }
            }
            Throwable th4 = (Throwable) obj2;
            if (th4 != null) {
                return th4;
            }
        }
        return th2;
    }

    public boolean G() {
        return true;
    }

    public boolean H() {
        return this instanceof m;
    }

    public final c1 I(o0 o0Var) {
        c1 c1VarC = o0Var.c();
        if (c1VarC != null) {
            return c1VarC;
        }
        if (o0Var instanceof g0) {
            return new c1();
        }
        if (o0Var instanceof w0) {
            V((w0) o0Var);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + o0Var).toString());
    }

    public boolean J(Throwable th) {
        return false;
    }

    public void K(p pVar) {
        throw pVar;
    }

    public final void L(s0 s0Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2716e;
        e1 e1Var = e1.f2728d;
        if (s0Var == null) {
            atomicReferenceFieldUpdater.set(this, e1Var);
            return;
        }
        s0Var.start();
        k kVarI = s0Var.i(this);
        atomicReferenceFieldUpdater.set(this, kVarI);
        if (f2715d.get(this) instanceof o0) {
            return;
        }
        kVarI.dispose();
        atomicReferenceFieldUpdater.set(this, e1Var);
    }

    public final f0 M(boolean z2, w0 w0Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        boolean z3;
        boolean zD;
        w0Var.f2784g = this;
        loop0: while (true) {
            atomicReferenceFieldUpdater = f2715d;
            Object obj = atomicReferenceFieldUpdater.get(this);
            boolean z4 = obj instanceof g0;
            e1 e1Var = e1.f2728d;
            z3 = true;
            if (!z4) {
                if (!(obj instanceof o0)) {
                    z3 = false;
                    break;
                }
                o0 o0Var = (o0) obj;
                c1 c1VarC = o0Var.c();
                if (c1VarC == null) {
                    V((w0) obj);
                } else {
                    if (w0Var.j()) {
                        z0 z0Var = o0Var instanceof z0 ? (z0) o0Var : null;
                        Throwable thD = z0Var != null ? z0Var.d() : null;
                        if (thD == null) {
                            zD = c1VarC.d(w0Var, 5);
                        } else if (z2) {
                            w0Var.k(thD);
                            return e1Var;
                        }
                    } else {
                        zD = c1VarC.d(w0Var, 1);
                    }
                    if (zD) {
                        break;
                    }
                }
            } else {
                g0 g0Var = (g0) obj;
                if (g0Var.f2731d) {
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(this, obj, w0Var)) {
                            break loop0;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == obj);
                } else {
                    U(g0Var);
                }
            }
            return e1Var;
        }
        if (z3) {
            return w0Var;
        }
        if (z2) {
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            o oVar = obj2 instanceof o ? (o) obj2 : null;
            w0Var.k(oVar != null ? oVar.f2763a : null);
        }
        return e1Var;
    }

    public boolean N() {
        return this instanceof c;
    }

    public final boolean O(Object obj) {
        Object objY;
        do {
            objY = Y(f2715d.get(this), obj);
            if (objY == y.f2789d) {
                return false;
            }
            if (objY == y.f2790e) {
                return true;
            }
        } while (objY == y.f2791f);
        u(objY);
        return true;
    }

    public final Object P(Object obj) {
        Object objY;
        do {
            objY = Y(f2715d.get(this), obj);
            if (objY == y.f2789d) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                o oVar = obj instanceof o ? (o) obj : null;
                throw new IllegalStateException(str, oVar != null ? oVar.f2763a : null);
            }
        } while (objY == y.f2791f);
        return objY;
    }

    public final void R(c1 c1Var, Throwable th) {
        c1Var.d(new n4.h(4), 4);
        Object obj = n4.i.f4291d.get(c1Var);
        b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode", obj);
        p pVar = null;
        for (n4.i iVarG = (n4.i) obj; !iVarG.equals(c1Var); iVarG = iVarG.g()) {
            if ((iVarG instanceof w0) && ((w0) iVarG).j()) {
                try {
                    ((w0) iVarG).k(th);
                } catch (Throwable th2) {
                    if (pVar != null) {
                        d4.a.f(pVar, th2);
                    } else {
                        pVar = new p("Exception in completion handler " + iVarG + " for " + this, th2);
                    }
                }
            }
        }
        if (pVar != null) {
            K(pVar);
        }
        z(th);
    }

    public void S(Object obj) {
    }

    public void T() {
    }

    public final void U(g0 g0Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        c1 c1Var = new c1();
        Object n0Var = c1Var;
        if (!g0Var.f2731d) {
            n0Var = new n0(c1Var);
        }
        do {
            atomicReferenceFieldUpdater = f2715d;
            if (atomicReferenceFieldUpdater.compareAndSet(this, g0Var, n0Var)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == g0Var);
    }

    public final void V(w0 w0Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        c1 c1Var = new c1();
        w0Var.getClass();
        n4.i.f4292e.set(c1Var, w0Var);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = n4.i.f4291d;
        atomicReferenceFieldUpdater2.set(c1Var, w0Var);
        loop0: while (atomicReferenceFieldUpdater2.get(w0Var) == w0Var) {
            do {
                if (atomicReferenceFieldUpdater2.compareAndSet(w0Var, w0Var, c1Var)) {
                    c1Var.f(w0Var);
                    break loop0;
                }
            } while (atomicReferenceFieldUpdater2.get(w0Var) == w0Var);
        }
        n4.i iVarG = w0Var.g();
        do {
            atomicReferenceFieldUpdater = f2715d;
            if (atomicReferenceFieldUpdater.compareAndSet(this, w0Var, iVarG)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == w0Var);
    }

    public final int W(Object obj) {
        boolean z2 = obj instanceof g0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2715d;
        if (z2) {
            if (((g0) obj).f2731d) {
                return 0;
            }
            g0 g0Var = y.f2795j;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, g0Var)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    return -1;
                }
            }
            T();
            return 1;
        }
        if (!(obj instanceof n0)) {
            return 0;
        }
        c1 c1Var = ((n0) obj).f2760d;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, c1Var)) {
            if (atomicReferenceFieldUpdater.get(this) != obj) {
                return -1;
            }
        }
        T();
        return 1;
    }

    public final Object Y(Object obj, Object obj2) {
        if (!(obj instanceof o0)) {
            return y.f2789d;
        }
        if (((obj instanceof g0) || (obj instanceof w0)) && !(obj instanceof l) && !(obj2 instanceof o)) {
            o0 o0Var = (o0) obj;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2715d;
            Object p0Var = obj2 instanceof o0 ? new p0((o0) obj2) : obj2;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, o0Var, p0Var)) {
                if (atomicReferenceFieldUpdater.get(this) != o0Var) {
                    return y.f2791f;
                }
            }
            S(obj2);
            C(o0Var, obj2);
            return obj2;
        }
        o0 o0Var2 = (o0) obj;
        c1 c1VarI = I(o0Var2);
        if (c1VarI == null) {
            return y.f2791f;
        }
        z0 z0Var = o0Var2 instanceof z0 ? (z0) o0Var2 : null;
        if (z0Var == null) {
            z0Var = new z0(c1VarI, null);
        }
        synchronized (z0Var) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = z0.f2802e;
            if (atomicIntegerFieldUpdater.get(z0Var) != 0) {
                return y.f2789d;
            }
            atomicIntegerFieldUpdater.set(z0Var, 1);
            if (z0Var != o0Var2) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f2715d;
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, o0Var2, z0Var)) {
                    if (atomicReferenceFieldUpdater2.get(this) != o0Var2) {
                        return y.f2791f;
                    }
                }
            }
            boolean zE = z0Var.e();
            o oVar = obj2 instanceof o ? (o) obj2 : null;
            if (oVar != null) {
                z0Var.b(oVar.f2763a);
            }
            Throwable thD = zE ? null : z0Var.d();
            if (thD != null) {
                R(c1VarI, thD);
            }
            l lVarQ = Q(c1VarI);
            if (lVarQ != null && Z(z0Var, lVarQ, obj2)) {
                return y.f2790e;
            }
            c1VarI.d(new n4.h(2), 2);
            l lVarQ2 = Q(c1VarI);
            return (lVarQ2 == null || !Z(z0Var, lVarQ2, obj2)) ? E(z0Var, obj2) : y.f2790e;
        }
    }

    public final boolean Z(z0 z0Var, l lVar, Object obj) {
        while (y.k(lVar.f2749h, false, new y0(this, z0Var, lVar, obj)) == e1.f2728d) {
            lVar = Q(lVar);
            if (lVar == null) {
                return false;
            }
        }
        return true;
    }

    @Override // i4.s0
    public boolean a() {
        Object obj = f2715d.get(this);
        return (obj instanceof o0) && ((o0) obj).a();
    }

    @Override // i4.s0
    public void b(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new t0(A(), null, this);
        }
        y(cancellationException);
    }

    @Override // i4.s0
    public final f0 c(boolean z2, boolean z3, v0 v0Var) {
        return M(z3, z2 ? new q0(v0Var) : new r0(0, v0Var));
    }

    @Override // t3.h
    public final t3.h f(t3.g gVar) {
        return p3.p.y(this, gVar);
    }

    @Override // i4.s0
    public final f0 g(a4.c cVar) {
        return M(true, new r0(0, cVar));
    }

    @Override // t3.f
    public final t3.g getKey() {
        return s.f2774e;
    }

    @Override // i4.s0
    public final k i(a1 a1Var) {
        l lVar = new l(a1Var);
        lVar.f2784g = this;
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2715d;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof g0) {
                g0 g0Var = (g0) obj;
                if (g0Var.f2731d) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, lVar)) {
                        if (atomicReferenceFieldUpdater.get(this) != obj) {
                        }
                    }
                    break loop0;
                }
                U(g0Var);
            } else {
                boolean z2 = obj instanceof o0;
                e1 e1Var = e1.f2728d;
                Throwable thD = null;
                if (!z2) {
                    Object obj2 = atomicReferenceFieldUpdater.get(this);
                    o oVar = obj2 instanceof o ? (o) obj2 : null;
                    lVar.k(oVar != null ? oVar.f2763a : null);
                    return e1Var;
                }
                c1 c1VarC = ((o0) obj).c();
                if (c1VarC != null) {
                    if (c1VarC.d(lVar, 7)) {
                        break;
                    }
                    boolean zD = c1VarC.d(lVar, 3);
                    Object obj3 = atomicReferenceFieldUpdater.get(this);
                    if (obj3 instanceof z0) {
                        thD = ((z0) obj3).d();
                    } else {
                        o oVar2 = obj3 instanceof o ? (o) obj3 : null;
                        if (oVar2 != null) {
                            thD = oVar2.f2763a;
                        }
                    }
                    lVar.k(thD);
                    if (zD) {
                        break;
                    }
                    return e1Var;
                }
                V((w0) obj);
            }
        }
        return lVar;
    }

    @Override // t3.h
    public final t3.f j(t3.g gVar) {
        return p3.p.p(this, gVar);
    }

    @Override // t3.h
    public final t3.h k(t3.h hVar) {
        return p3.p.A(this, hVar);
    }

    @Override // i4.s0
    public final CancellationException m() {
        CancellationException cancellationException;
        Object obj = f2715d.get(this);
        if (!(obj instanceof z0)) {
            if (obj instanceof o0) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            if (!(obj instanceof o)) {
                return new t0(getClass().getSimpleName().concat(" has completed normally"), null, this);
            }
            Throwable th = ((o) obj).f2763a;
            cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
            return cancellationException == null ? new t0(A(), th, this) : cancellationException;
        }
        Throwable thD = ((z0) obj).d();
        if (thD == null) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        String strConcat = getClass().getSimpleName().concat(" is cancelling");
        cancellationException = thD instanceof CancellationException ? (CancellationException) thD : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        if (strConcat == null) {
            strConcat = A();
        }
        return new t0(strConcat, thD, this);
    }

    @Override // i4.s0
    public final Object q(v3.c cVar) {
        Object obj;
        p3.n nVar;
        do {
            obj = f2715d.get(this);
            boolean z2 = obj instanceof o0;
            nVar = p3.n.f5026a;
            if (!z2) {
                y.e(cVar.getContext());
                return nVar;
            }
        } while (W(obj) < 0);
        h hVar = new h(1, p3.j.B(cVar));
        hVar.p();
        int i5 = 1;
        hVar.t(new e(i5, y.k(this, true, new j(hVar, i5))));
        Object objO = hVar.o();
        u3.a aVar = u3.a.f5838d;
        if (objO != aVar) {
            objO = nVar;
        }
        return objO == aVar ? objO : nVar;
    }

    @Override // i4.s0
    public final boolean start() {
        int iW;
        do {
            iW = W(f2715d.get(this));
            if (iW == 0) {
                return false;
            }
        } while (iW != 1);
        return true;
    }

    @Override // t3.h
    public final Object t(a4.e eVar, Object obj) {
        return eVar.invoke(obj, this);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName() + '{' + X(f2715d.get(this)) + '}');
        sb.append('@');
        sb.append(y.g(this));
        return sb.toString();
    }

    public void u(Object obj) {
    }

    public void v(Object obj) {
        u(obj);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x003e A[PHI: r0
      0x003e: PHI (r0v1 java.lang.Object) = (r0v0 java.lang.Object), (r0v13 java.lang.Object) binds: [B:3:0x0008, B:16:0x003a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:20:0x0042  */
    /* JADX WARN: Code duplicated, block: B:26:0x005c  */
    /* JADX WARN: Code duplicated, block: B:27:0x005e  */
    /* JADX WARN: Code duplicated, block: B:29:0x0061 A[Catch: all -> 0x0067, TRY_LEAVE, TryCatch #0 {, blocks: (B:24:0x004f, B:29:0x0061, B:34:0x0069, B:36:0x0072, B:37:0x0076), top: B:81:0x004f }] */
    /* JADX WARN: Code duplicated, block: B:34:0x0069 A[Catch: all -> 0x0067, TRY_ENTER, TryCatch #0 {, blocks: (B:24:0x004f, B:29:0x0061, B:34:0x0069, B:36:0x0072, B:37:0x0076), top: B:81:0x004f }] */
    /* JADX WARN: Code duplicated, block: B:36:0x0072 A[Catch: all -> 0x0067, TryCatch #0 {, blocks: (B:24:0x004f, B:29:0x0061, B:34:0x0069, B:36:0x0072, B:37:0x0076), top: B:81:0x004f }] */
    /* JADX WARN: Code duplicated, block: B:39:0x0085  */
    /* JADX WARN: Code duplicated, block: B:42:0x0089  */
    /* JADX WARN: Code duplicated, block: B:46:0x0095  */
    /* JADX WARN: Code duplicated, block: B:48:0x0099 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:49:0x009b  */
    /* JADX WARN: Code duplicated, block: B:59:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:64:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:78:0x0105 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:79:0x0106  */
    /* JADX WARN: Code duplicated, block: B:81:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x00c8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:87:0x00af A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:88:0x004e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x00f3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x00d9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x00a8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x00db A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:95:0x0044 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:96:0x0044 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:97:0x0044 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:98:? A[LOOP:2: B:56:0x00b4->B:98:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:20:0x0042, please report this as an issue */
    public final boolean x(Object obj) {
        Throwable thD;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Object obj2;
        boolean z2;
        Throwable thD2;
        b3.r rVar;
        o0 o0Var;
        c1 c1VarI;
        z0 z0Var;
        Object objY;
        Object objY2 = y.f2789d;
        if (H()) {
            do {
                Object obj3 = f2715d.get(this);
                if (obj3 instanceof o0) {
                    if (obj3 instanceof z0) {
                        if (z0.f2802e.get((z0) obj3) != 0) {
                        }
                    }
                    objY2 = Y(obj3, new o(D(obj), false));
                }
                objY2 = y.f2789d;
                break;
            } while (objY2 == y.f2791f);
            if (objY2 != y.f2790e) {
                if (objY2 == y.f2789d) {
                    thD = null;
                    loop1: while (true) {
                        atomicReferenceFieldUpdater = f2715d;
                        obj2 = atomicReferenceFieldUpdater.get(this);
                        if (obj2 instanceof z0) {
                            synchronized (obj2) {
                                if (z0.f2804g.get((z0) obj2) == y.f2793h) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                if (z2) {
                                    rVar = y.f2792g;
                                } else {
                                    boolean zE = ((z0) obj2).e();
                                    if (thD == null) {
                                        thD = D(obj);
                                    }
                                    ((z0) obj2).b(thD);
                                    thD2 = zE ? null : ((z0) obj2).d();
                                    if (thD2 != null) {
                                        R(((z0) obj2).f2805d, thD2);
                                    }
                                    rVar = y.f2789d;
                                }
                            }
                        } else if (obj2 instanceof o0) {
                            if (thD == null) {
                                thD = D(obj);
                            }
                            o0Var = (o0) obj2;
                            if (o0Var.a()) {
                                c1VarI = I(o0Var);
                                if (c1VarI == null) {
                                    continue;
                                } else {
                                    z0Var = new z0(c1VarI, thD);
                                    while (true) {
                                        if (atomicReferenceFieldUpdater.compareAndSet(this, o0Var, z0Var)) {
                                            R(c1VarI, thD);
                                            rVar = y.f2789d;
                                        } else if (atomicReferenceFieldUpdater.get(this) != o0Var) {
                                        }
                                    }
                                }
                            } else {
                                objY = Y(obj2, new o(thD, false));
                                if (objY != y.f2789d) {
                                    throw new IllegalStateException(("Cannot happen in " + obj2).toString());
                                }
                                if (objY != y.f2791f) {
                                    objY2 = objY;
                                    break;
                                }
                            }
                        } else {
                            rVar = y.f2792g;
                        }
                        objY2 = rVar;
                        break;
                    }
                }
                if (objY2 != y.f2789d && objY2 != y.f2790e) {
                    if (objY2 == y.f2792g) {
                        return false;
                    }
                    u(objY2);
                    return true;
                }
            }
        } else {
            if (objY2 == y.f2789d) {
                thD = null;
                loop1: while (true) {
                    atomicReferenceFieldUpdater = f2715d;
                    obj2 = atomicReferenceFieldUpdater.get(this);
                    if (obj2 instanceof z0) {
                        synchronized (obj2) {
                            if (z0.f2804g.get((z0) obj2) == y.f2793h) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (z2) {
                                rVar = y.f2792g;
                            } else {
                                boolean zE2 = ((z0) obj2).e();
                                if (thD == null) {
                                    thD = D(obj);
                                }
                                ((z0) obj2).b(thD);
                                if (zE2) {
                                }
                                if (thD2 != null) {
                                    R(((z0) obj2).f2805d, thD2);
                                }
                                rVar = y.f2789d;
                            }
                        }
                    } else if (obj2 instanceof o0) {
                        if (thD == null) {
                            thD = D(obj);
                        }
                        o0Var = (o0) obj2;
                        if (o0Var.a()) {
                            c1VarI = I(o0Var);
                            if (c1VarI == null) {
                                continue;
                            } else {
                                z0Var = new z0(c1VarI, thD);
                                while (true) {
                                    if (atomicReferenceFieldUpdater.compareAndSet(this, o0Var, z0Var)) {
                                        R(c1VarI, thD);
                                        rVar = y.f2789d;
                                    } else if (atomicReferenceFieldUpdater.get(this) != o0Var) {
                                    }
                                }
                            }
                        } else {
                            objY = Y(obj2, new o(thD, false));
                            if (objY != y.f2789d) {
                                throw new IllegalStateException(("Cannot happen in " + obj2).toString());
                            }
                            if (objY != y.f2791f) {
                                objY2 = objY;
                                break;
                            }
                        }
                    } else {
                        rVar = y.f2792g;
                    }
                    objY2 = rVar;
                    break;
                }
            }
            if (objY2 != y.f2789d) {
                if (objY2 == y.f2792g) {
                    return false;
                }
                u(objY2);
                return true;
            }
        }
        return true;
    }

    public void y(CancellationException cancellationException) {
        x(cancellationException);
    }

    public final boolean z(Throwable th) {
        if (N()) {
            return true;
        }
        boolean z2 = th instanceof CancellationException;
        k kVar = (k) f2716e.get(this);
        if (kVar == null || kVar == e1.f2728d) {
            return z2;
        }
        return kVar.b(th) || z2;
    }
}
