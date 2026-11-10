package q4;

import b3.r;
import f0.c2;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import n4.q;
import p3.n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f5487b = AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, "head$volatile");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f5488c = AtomicLongFieldUpdater.newUpdater(g.class, "deqIdx$volatile");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f5489d = AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, "tail$volatile");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f5490e = AtomicLongFieldUpdater.newUpdater(g.class, "enqIdx$volatile");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f5491f = AtomicIntegerFieldUpdater.newUpdater(g.class, "_availablePermits$volatile");
    private volatile /* synthetic */ int _availablePermits$volatile;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i4.g f5492a;
    private volatile /* synthetic */ long deqIdx$volatile;
    private volatile /* synthetic */ long enqIdx$volatile;
    private volatile /* synthetic */ Object head$volatile;
    private volatile /* synthetic */ Object tail$volatile;

    public g() {
        i iVar = new i(0L, null, 2);
        this.head$volatile = iVar;
        this.tail$volatile = iVar;
        this._availablePermits$volatile = 1;
        this.f5492a = new i4.g(2, this);
    }

    public final void a(b bVar) {
        Object objB;
        i iVar;
        i4.h hVar = bVar.f5481d;
        c cVar = bVar.f5482e;
        while (true) {
            int andDecrement = f5491f.getAndDecrement(this);
            if (andDecrement <= 1) {
                n nVar = n.f5026a;
                if (andDecrement > 0) {
                    c.f5483g.set(cVar, null);
                    hVar.B(nVar, hVar.f2723f, new i4.g(0, new c2(cVar, bVar)));
                    return;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5489d;
                i iVar2 = (i) atomicReferenceFieldUpdater.get(this);
                long andIncrement = f5490e.getAndIncrement(this);
                e eVar = e.f5485k;
                long j5 = andIncrement / ((long) h.f5498f);
                while (true) {
                    objB = n4.a.b(iVar2, j5, eVar);
                    if (n4.a.e(objB)) {
                        break;
                    }
                    q qVarC = n4.a.c(objB);
                    while (true) {
                        q qVar = (q) atomicReferenceFieldUpdater.get(this);
                        iVar = iVar2;
                        if (qVar.f4307c >= qVarC.f4307c) {
                            break;
                        }
                        if (!qVarC.i()) {
                            break;
                        }
                        do {
                            if (atomicReferenceFieldUpdater.compareAndSet(this, qVar, qVarC)) {
                                if (!qVar.e()) {
                                    break;
                                }
                                qVar.d();
                                break;
                            }
                        } while (atomicReferenceFieldUpdater.get(this) == qVar);
                        if (qVarC.e()) {
                            qVarC.d();
                        }
                        iVar2 = iVar;
                    }
                    iVar2 = iVar;
                }
                i iVar3 = (i) n4.a.c(objB);
                AtomicReferenceArray atomicReferenceArray = iVar3.f5499e;
                int i5 = (int) (andIncrement % ((long) h.f5498f));
                do {
                    if (atomicReferenceArray.compareAndSet(i5, null, bVar)) {
                        bVar.a(iVar3, i5);
                        return;
                    }
                } while (atomicReferenceArray.get(i5) == null);
                r rVar = h.f5494b;
                r rVar2 = h.f5495c;
                do {
                    if (atomicReferenceArray.compareAndSet(i5, rVar, rVar2)) {
                        c.f5483g.set(cVar, null);
                        hVar.B(nVar, hVar.f2723f, new i4.g(0, new c2(cVar, bVar)));
                        return;
                    }
                } while (atomicReferenceArray.get(i5) == rVar);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0078  */
    public final void b() {
        boolean z2;
        int i5;
        Object objB;
        do {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f5491f;
            int andIncrement = atomicIntegerFieldUpdater.getAndIncrement(this);
            z2 = true;
            if (andIncrement >= 1) {
                do {
                    i5 = atomicIntegerFieldUpdater.get(this);
                    if (i5 <= 1) {
                        break;
                    }
                } while (!atomicIntegerFieldUpdater.compareAndSet(this, i5, 1));
                throw new IllegalStateException("The number of released permits cannot be greater than 1".toString());
            }
            if (andIncrement >= 0) {
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5487b;
            i iVar = (i) atomicReferenceFieldUpdater.get(this);
            long andIncrement2 = f5488c.getAndIncrement(this);
            long j5 = andIncrement2 / ((long) h.f5498f);
            f fVar = f.f5486k;
            while (true) {
                objB = n4.a.b(iVar, j5, fVar);
                if (!n4.a.e(objB)) {
                    q qVarC = n4.a.c(objB);
                    while (true) {
                        q qVar = (q) atomicReferenceFieldUpdater.get(this);
                        if (qVar.f4307c >= qVarC.f4307c) {
                            break;
                        }
                        if (!qVarC.i()) {
                            break;
                        }
                        do {
                            if (atomicReferenceFieldUpdater.compareAndSet(this, qVar, qVarC)) {
                                if (!qVar.e()) {
                                    break;
                                }
                                qVar.d();
                                break;
                            }
                        } while (atomicReferenceFieldUpdater.get(this) == qVar);
                        if (qVarC.e()) {
                            qVarC.d();
                        }
                    }
                } else {
                    break;
                }
            }
            i iVar2 = (i) n4.a.c(objB);
            AtomicReferenceArray atomicReferenceArray = iVar2.f5499e;
            iVar2.a();
            boolean z3 = false;
            if (iVar2.f4307c <= j5) {
                int i6 = (int) (andIncrement2 % ((long) h.f5498f));
                Object andSet = atomicReferenceArray.getAndSet(i6, h.f5494b);
                if (andSet == null) {
                    int i7 = h.f5493a;
                    int i8 = 0;
                    while (true) {
                        if (i8 >= i7) {
                            r rVar = h.f5494b;
                            r rVar2 = h.f5496d;
                            do {
                                if (atomicReferenceArray.compareAndSet(i6, rVar, rVar2)) {
                                    z3 = true;
                                    break;
                                }
                            } while (atomicReferenceArray.get(i6) == rVar);
                            z2 = true ^ z3;
                            break;
                        }
                        if (atomicReferenceArray.get(i6) == h.f5495c) {
                            break;
                        } else {
                            i8++;
                        }
                    }
                } else if (andSet == h.f5497e) {
                    z2 = false;
                } else {
                    if (!(andSet instanceof i4.f)) {
                        throw new IllegalStateException(("unexpected: " + andSet).toString());
                    }
                    i4.f fVar2 = (i4.f) andSet;
                    r rVarR = fVar2.r(n.f5026a, this.f5492a);
                    if (rVarR != null) {
                        fVar2.w(rVarR);
                    } else {
                        z2 = false;
                    }
                }
            } else {
                z2 = false;
            }
        } while (!z2);
    }
}
