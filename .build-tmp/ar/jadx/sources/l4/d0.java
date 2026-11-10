package l4;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 extends m4.b implements p, d, m4.m {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f3526i = AtomicReferenceFieldUpdater.newUpdater(d0.class, Object.class, "_state$volatile");
    private volatile /* synthetic */ Object _state$volatile;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3527h;

    public d0(Object obj) {
        this._state$volatile = obj;
    }

    @Override // m4.b
    public final m4.d b() {
        return new e0();
    }

    @Override // m4.m
    public final d c(t3.h hVar, int i5, k4.a aVar) {
        return ((((i5 < 0 || i5 >= 2) && i5 != -2) || aVar != k4.a.f3436e) && !((i5 == 0 || i5 == -3) && aVar == k4.a.f3435d)) ? new m4.g(this, hVar, i5, aVar) : this;
    }

    @Override // l4.e
    public final Object d(Object obj, t3.c cVar) {
        if (obj == null) {
            obj = m4.c.f3818b;
        }
        i(null, obj);
        return p3.n.f5026a;
    }

    @Override // m4.b
    public final m4.d[] e() {
        return new e0[2];
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00e7 A[Catch: all -> 0x003e, TryCatch #0 {all -> 0x003e, blocks: (B:14:0x0039, B:28:0x007d, B:30:0x0085, B:33:0x008c, B:34:0x0090, B:36:0x0093, B:46:0x00b4, B:49:0x00c4, B:50:0x00e0, B:56:0x00f0, B:53:0x00e7, B:55:0x00ed, B:38:0x0099, B:42:0x00a0, B:21:0x0053, B:24:0x005d, B:27:0x006e), top: B:63:0x0027 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x00ed A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:? A[LOOP:0: B:50:0x00e0->B:68:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x00c3 -> B:28:0x007d). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    @Override // l4.d
    public final java.lang.Object g(l4.e r18, t3.c r19) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: l4.d0.g(l4.e, t3.c):java.lang.Object");
    }

    @Override // l4.b0
    public final Object getValue() {
        b3.r rVar = m4.c.f3818b;
        Object obj = f3526i.get(this);
        if (obj == rVar) {
            return null;
        }
        return obj;
    }

    public final boolean i(Object obj, Object obj2) {
        int i5;
        m4.d[] dVarArr;
        b3.r rVar;
        synchronized (this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3526i;
            Object obj3 = atomicReferenceFieldUpdater.get(this);
            if (obj != null && !b4.l.a(obj3, obj)) {
                return false;
            }
            if (b4.l.a(obj3, obj2)) {
                return true;
            }
            atomicReferenceFieldUpdater.set(this, obj2);
            int i6 = this.f3527h;
            if ((i6 & 1) != 0) {
                this.f3527h = i6 + 2;
                return true;
            }
            int i7 = i6 + 1;
            this.f3527h = i7;
            m4.d[] dVarArr2 = this.f3813d;
            while (true) {
                e0[] e0VarArr = (e0[]) dVarArr2;
                if (e0VarArr != null) {
                    for (e0 e0Var : e0VarArr) {
                        if (e0Var != null) {
                            AtomicReference atomicReference = e0Var.f3528a;
                            while (true) {
                                Object obj4 = atomicReference.get();
                                if (obj4 == null || obj4 == (rVar = u.f3588c)) {
                                    break;
                                }
                                b3.r rVar2 = u.f3587b;
                                if (obj4 != rVar2) {
                                    do {
                                        if (atomicReference.compareAndSet(obj4, rVar2)) {
                                            ((i4.h) obj4).resumeWith(p3.n.f5026a);
                                            break;
                                        }
                                    } while (atomicReference.get() == obj4);
                                } else {
                                    do {
                                        if (atomicReference.compareAndSet(obj4, rVar)) {
                                            break;
                                        }
                                    } while (atomicReference.get() == obj4);
                                }
                            }
                        }
                    }
                }
                synchronized (this) {
                    i5 = this.f3527h;
                    if (i5 == i7) {
                        this.f3527h = i7 + 1;
                        return true;
                    }
                    dVarArr = this.f3813d;
                }
                dVarArr2 = dVarArr;
                i7 = i5;
            }
        }
    }
}
