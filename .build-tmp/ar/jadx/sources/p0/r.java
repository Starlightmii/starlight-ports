package p0;

import f0.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f4731a = new Object();

    public static final void a(int i5, int i6) {
        if (i5 < 0 || i5 >= i6) {
            throw new IndexOutOfBoundsException("index (" + i5 + ") is out of bound of [0, " + i6 + ')');
        }
    }

    public static final boolean b(w wVar, int i5, j0.c cVar, boolean z2) {
        boolean z3;
        synchronized (f4731a) {
            try {
                int i6 = wVar.f4764d;
                if (i6 == i5) {
                    wVar.f4763c = cVar;
                    z3 = true;
                    if (z2) {
                        wVar.f4765e++;
                    }
                    wVar.f4764d = i6 + 1;
                } else {
                    z3 = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z3;
    }

    public static final int c(long[] jArr, long j5) {
        int length = jArr.length - 1;
        int i5 = 0;
        while (i5 <= length) {
            int i6 = (i5 + length) >>> 1;
            long j6 = jArr[i6];
            if (j5 > j6) {
                i5 = i6 + 1;
            } else {
                if (j5 >= j6) {
                    return i6;
                }
                length = i6 - 1;
            }
        }
        return -(i5 + 1);
    }

    public static f d() {
        return (f) m.f4718b.q();
    }

    public static final w e(q qVar) {
        w wVar = qVar.f4730d;
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.<get-readable>>", wVar);
        return (w) m.t(wVar, qVar);
    }

    public static final int f(q qVar) {
        w wVar = qVar.f4730d;
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>", wVar);
        return ((w) m.h(wVar)).f4765e;
    }

    public static f g(f fVar) {
        if (fVar instanceof c0) {
            c0 c0Var = (c0) fVar;
            if (c0Var.f4683t == n0.i.b()) {
                c0Var.f4681r = null;
                return fVar;
            }
        }
        if (fVar instanceof d0) {
            d0 d0Var = (d0) fVar;
            if (d0Var.f4690i == n0.i.b()) {
                d0Var.f4689h = null;
                return fVar;
            }
        }
        f fVarG = m.g(fVar, null, false);
        fVarG.j();
        return fVarG;
    }

    public static final boolean h(q qVar, a4.c cVar) {
        int i5;
        j0.c cVar2;
        Object objC;
        f fVarJ;
        boolean zB;
        do {
            synchronized (f4731a) {
                w wVar = qVar.f4730d;
                b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>", wVar);
                w wVar2 = (w) m.h(wVar);
                i5 = wVar2.f4764d;
                cVar2 = wVar2.f4763c;
            }
            b4.l.b(cVar2);
            j0.g gVarE = cVar2.e();
            objC = cVar.c(gVarE);
            j0.c cVarC = gVarE.c();
            if (b4.l.a(cVarC, cVar2)) {
                break;
            }
            w wVar3 = qVar.f4730d;
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>", wVar3);
            synchronized (m.f4719c) {
                fVarJ = m.j();
                zB = b((w) m.w(wVar3, qVar, fVarJ), i5, cVarC, true);
            }
            m.n(fVarJ, qVar);
        } while (!zB);
        return ((Boolean) objC).booleanValue();
    }

    public static Object i(l0 l0Var, a4.a aVar) {
        f c0Var;
        f fVar = (f) m.f4718b.q();
        if (fVar instanceof c0) {
            c0 c0Var2 = (c0) fVar;
            if (c0Var2.f4683t == n0.i.b()) {
                a4.c cVar = c0Var2.f4681r;
                a4.c cVar2 = c0Var2.f4682s;
                try {
                    ((c0) fVar).f4681r = m.k(l0Var, cVar, true);
                    ((c0) fVar).f4682s = cVar2;
                    return aVar.a();
                } finally {
                    c0Var2.f4681r = cVar;
                    c0Var2.f4682s = cVar2;
                }
            }
        }
        if (fVar == null || (fVar instanceof b)) {
            c0Var = new c0(fVar instanceof b ? (b) fVar : null, l0Var, null, true, false);
        } else {
            c0Var = fVar.u(l0Var);
        }
        try {
            f fVarJ = c0Var.j();
            try {
                Object objA = aVar.a();
                f.q(fVarJ);
                c0Var.c();
                return objA;
            } catch (Throwable th) {
                f.q(fVarJ);
                throw th;
            }
        } catch (Throwable th2) {
            c0Var.c();
            throw th2;
        }
    }

    public static void j(f fVar, f fVar2, a4.c cVar) {
        if (fVar != fVar2) {
            fVar2.getClass();
            f.q(fVar);
            fVar2.c();
        } else if (fVar instanceof c0) {
            ((c0) fVar).f4681r = cVar;
        } else if (fVar instanceof d0) {
            ((d0) fVar).f4689h = cVar;
        } else {
            throw new IllegalStateException(("Non-transparent snapshot was reused: " + fVar).toString());
        }
    }

    public static final void k() {
        throw new UnsupportedOperationException();
    }
}
