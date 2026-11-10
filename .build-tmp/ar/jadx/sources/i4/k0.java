package i4;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class k0 extends l0 implements b0 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2745j = AtomicReferenceFieldUpdater.newUpdater(k0.class, Object.class, "_queue$volatile");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2746k = AtomicReferenceFieldUpdater.newUpdater(k0.class, Object.class, "_delayed$volatile");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2747l = AtomicIntegerFieldUpdater.newUpdater(k0.class, "_isCompleted$volatile");
    private volatile /* synthetic */ Object _delayed$volatile;
    private volatile /* synthetic */ int _isCompleted$volatile = 0;
    private volatile /* synthetic */ Object _queue$volatile;

    public final boolean A(Runnable runnable) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2745j;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (f2747l.get(this) != 0) {
                return false;
            }
            if (obj == null) {
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, runnable)) {
                    if (atomicReferenceFieldUpdater.get(this) != null) {
                    }
                }
                return true;
            }
            if (!(obj instanceof n4.l)) {
                if (obj == y.f2788c) {
                    return false;
                }
                n4.l lVar = new n4.l(8, true);
                lVar.a((Runnable) obj);
                lVar.a(runnable);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, lVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                    }
                }
                return true;
            }
            n4.l lVar2 = (n4.l) obj;
            int iA = lVar2.a(runnable);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                n4.l lVarC = lVar2.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, lVarC) && atomicReferenceFieldUpdater.get(this) == obj) {
                }
            } else if (iA == 2) {
                return false;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0027  */
    /* JADX WARN: Code duplicated, block: B:20:0x0030  */
    /* JADX WARN: Code duplicated, block: B:22:0x0034  */
    /* JADX WARN: Code duplicated, block: B:24:0x004d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x004e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:26:0x004f  */
    public final boolean B() {
        Object obj;
        long j5;
        q3.j jVar = this.f2753h;
        if (jVar != null ? jVar.isEmpty() : true) {
            j0 j0Var = (j0) f2746k.get(this);
            if (j0Var == null) {
                obj = f2745j.get(this);
                if (obj != null) {
                    if (obj instanceof n4.l) {
                        j5 = n4.l.f4297f.get((n4.l) obj);
                        if (((int) (1073741823 & j5)) == ((int) ((j5 & 1152921503533105152L) >> 30))) {
                            return true;
                        }
                        return false;
                    }
                    if (obj == y.f2788c) {
                    }
                }
                return true;
            }
            if (n4.t.f4310b.get(j0Var) == 0) {
                obj = f2745j.get(this);
                if (obj != null) {
                    if (obj instanceof n4.l) {
                        j5 = n4.l.f4297f.get((n4.l) obj);
                        if (((int) (1073741823 & j5)) == ((int) ((j5 & 1152921503533105152L) >> 30))) {
                            return true;
                        }
                        return false;
                    }
                    if (obj == y.f2788c) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final void C(long j5, i0 i0Var) {
        int iA;
        Thread threadR;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2746k;
        i0 i0Var2 = null;
        if (f2747l.get(this) != 0) {
            iA = 1;
        } else {
            j0 j0Var = (j0) atomicReferenceFieldUpdater.get(this);
            if (j0Var == null) {
                j0 j0Var2 = new j0();
                j0Var2.f2744c = j5;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, j0Var2) && atomicReferenceFieldUpdater.get(this) == null) {
                }
                Object obj = atomicReferenceFieldUpdater.get(this);
                b4.l.b(obj);
                j0Var = (j0) obj;
            }
            iA = i0Var.a(j5, j0Var, this);
        }
        if (iA != 0) {
            if (iA == 1) {
                x(j5, i0Var);
                return;
            } else {
                if (iA != 2) {
                    throw new IllegalStateException("unexpected result");
                }
                return;
            }
        }
        j0 j0Var3 = (j0) atomicReferenceFieldUpdater.get(this);
        if (j0Var3 != null) {
            synchronized (j0Var3) {
                i0[] i0VarArr = j0Var3.f4311a;
                i0Var2 = i0VarArr != null ? i0VarArr[0] : null;
            }
        }
        if (i0Var2 != i0Var || Thread.currentThread() == (threadR = r())) {
            return;
        }
        LockSupport.unpark(threadR);
    }

    @Override // i4.b0
    public final void d(long j5, h hVar) {
        long j6 = 0;
        if (j5 > 0) {
            j6 = j5 >= 9223372036854L ? Long.MAX_VALUE : 1000000 * j5;
        }
        if (j6 < 4611686018427387903L) {
            long jNanoTime = System.nanoTime();
            h0 h0Var = new h0(this, j6 + jNanoTime, hVar);
            C(jNanoTime, h0Var);
            hVar.t(new e(1, h0Var));
        }
    }

    @Override // i4.r
    public final void e(t3.h hVar, Runnable runnable) {
        y(runnable);
    }

    @Override // i4.l0
    public void shutdown() {
        i0 i0VarB;
        k1.f2748a.set(null);
        f2747l.set(this, 1);
        b3.r rVar = y.f2788c;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2745j;
        loop0: while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, null, rVar)) {
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(this) == null);
            } else if (obj instanceof n4.l) {
                ((n4.l) obj).b();
                break;
            } else {
                if (obj == rVar) {
                    break;
                }
                n4.l lVar = new n4.l(8, true);
                lVar.a((Runnable) obj);
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, obj, lVar)) {
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(this) == obj);
            }
        }
        while (v() <= 0) {
        }
        long jNanoTime = System.nanoTime();
        while (true) {
            j0 j0Var = (j0) f2746k.get(this);
            if (j0Var == null) {
                return;
            }
            synchronized (j0Var) {
                i0VarB = n4.t.f4310b.get(j0Var) > 0 ? j0Var.b(0) : null;
            }
            if (i0VarB == null) {
                return;
            } else {
                x(jNanoTime, i0VarB);
            }
        }
    }

    @Override // i4.l0
    public final long v() {
        Runnable runnable;
        i0 i0Var;
        b3.r rVar = y.f2788c;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2745j;
        if (!w()) {
            z();
            loop0: while (true) {
                Object obj = atomicReferenceFieldUpdater.get(this);
                if (obj != null) {
                    if (obj instanceof n4.l) {
                        n4.l lVar = (n4.l) obj;
                        Object objD = lVar.d();
                        if (objD != n4.l.f4298g) {
                            runnable = (Runnable) objD;
                            break;
                        }
                        n4.l lVarC = lVar.c();
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, lVarC) && atomicReferenceFieldUpdater.get(this) == obj) {
                        }
                    } else if (obj != rVar) {
                        do {
                            if (atomicReferenceFieldUpdater.compareAndSet(this, obj, null)) {
                                runnable = (Runnable) obj;
                                break loop0;
                            }
                        } while (atomicReferenceFieldUpdater.get(this) == obj);
                    }
                }
                runnable = null;
                break;
            }
            if (runnable != null) {
                runnable.run();
                return 0L;
            }
            q3.j jVar = this.f2753h;
            if (((jVar == null || jVar.isEmpty()) ? Long.MAX_VALUE : 0L) != 0) {
                Object obj2 = atomicReferenceFieldUpdater.get(this);
                if (obj2 != null) {
                    if (obj2 instanceof n4.l) {
                        long j5 = n4.l.f4297f.get((n4.l) obj2);
                        if (((int) (1073741823 & j5)) != ((int) ((j5 & 1152921503533105152L) >> 30))) {
                            return 0L;
                        }
                    } else if (obj2 == rVar) {
                        return Long.MAX_VALUE;
                    }
                }
                j0 j0Var = (j0) f2746k.get(this);
                if (j0Var != null) {
                    synchronized (j0Var) {
                        i0[] i0VarArr = j0Var.f4311a;
                        i0Var = i0VarArr != null ? i0VarArr[0] : null;
                    }
                    if (i0Var != null) {
                        long jNanoTime = i0Var.f2740d - System.nanoTime();
                        if (jNanoTime >= 0) {
                            return jNanoTime;
                        }
                    }
                }
                return Long.MAX_VALUE;
            }
        }
        return 0L;
    }

    public void y(Runnable runnable) {
        z();
        if (!A(runnable)) {
            z.f2800m.y(runnable);
            return;
        }
        Thread threadR = r();
        if (Thread.currentThread() != threadR) {
            LockSupport.unpark(threadR);
        }
    }

    public final void z() {
        i0 i0VarB;
        j0 j0Var = (j0) f2746k.get(this);
        if (j0Var == null || n4.t.f4310b.get(j0Var) == 0) {
            return;
        }
        long jNanoTime = System.nanoTime();
        do {
            synchronized (j0Var) {
                try {
                    i0[] i0VarArr = j0Var.f4311a;
                    i0VarB = null;
                    i0 i0Var = i0VarArr != null ? i0VarArr[0] : null;
                    if (i0Var != null) {
                        i0VarB = ((jNanoTime - i0Var.f2740d) > 0L ? 1 : ((jNanoTime - i0Var.f2740d) == 0L ? 0 : -1)) >= 0 ? A(i0Var) : false ? j0Var.b(0) : null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } while (i0VarB != null);
    }
}
