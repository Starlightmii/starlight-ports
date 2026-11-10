package n4;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f4296e = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "_next$volatile");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f4297f = AtomicLongFieldUpdater.newUpdater(l.class, "_state$volatile");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b3.r f4298g = new b3.r("REMOVE_FROZEN", 1);
    private volatile /* synthetic */ Object _next$volatile;
    private volatile /* synthetic */ long _state$volatile;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f4299a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f4300b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f4301c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ AtomicReferenceArray f4302d;

    public l(int i5, boolean z2) {
        this.f4299a = i5;
        this.f4300b = z2;
        int i6 = i5 - 1;
        this.f4301c = i6;
        this.f4302d = new AtomicReferenceArray(i5);
        if (i6 > 1073741823) {
            throw new IllegalStateException("Check failed.");
        }
        if ((i5 & i6) != 0) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final int a(Object obj) {
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f4297f;
            long j5 = atomicLongFieldUpdater.get(this);
            if ((3458764513820540928L & j5) != 0) {
                return (2305843009213693952L & j5) != 0 ? 2 : 1;
            }
            int i5 = (int) (1073741823 & j5);
            int i6 = (int) ((1152921503533105152L & j5) >> 30);
            int i7 = this.f4301c;
            if (((i6 + 2) & i7) == (i5 & i7)) {
                return 1;
            }
            boolean z2 = this.f4300b;
            AtomicReferenceArray atomicReferenceArray = this.f4302d;
            if (z2 || atomicReferenceArray.get(i6 & i7) == null) {
                if (f4297f.compareAndSet(this, j5, ((-1152921503533105153L) & j5) | (((long) ((i6 + 1) & 1073741823)) << 30))) {
                    atomicReferenceArray.set(i6 & i7, obj);
                    l lVarC = this;
                    while ((atomicLongFieldUpdater.get(lVarC) & 1152921504606846976L) != 0) {
                        lVarC = lVarC.c();
                        AtomicReferenceArray atomicReferenceArray2 = lVarC.f4302d;
                        int i8 = lVarC.f4301c & i6;
                        Object obj2 = atomicReferenceArray2.get(i8);
                        if ((obj2 instanceof k) && ((k) obj2).f4295a == i6) {
                            atomicReferenceArray2.set(i8, obj);
                        } else {
                            lVarC = null;
                        }
                        if (lVarC == null) {
                            return 0;
                        }
                    }
                    return 0;
                }
            } else {
                int i9 = this.f4299a;
                if (i9 < 1024 || ((i6 - i5) & 1073741823) > (i9 >> 1)) {
                    return 1;
                }
            }
        }
    }

    public final boolean b() {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j5;
        do {
            atomicLongFieldUpdater = f4297f;
            j5 = atomicLongFieldUpdater.get(this);
            if ((j5 & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j5) != 0) {
                return false;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j5, 2305843009213693952L | j5));
        return true;
    }

    public final l c() {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j5;
        l lVar;
        while (true) {
            atomicLongFieldUpdater = f4297f;
            j5 = atomicLongFieldUpdater.get(this);
            if ((j5 & 1152921504606846976L) != 0) {
                lVar = this;
                break;
            }
            long j6 = 1152921504606846976L | j5;
            lVar = this;
            if (atomicLongFieldUpdater.compareAndSet(lVar, j5, j6)) {
                j5 = j6;
                break;
            }
        }
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4296e;
            l lVar2 = (l) atomicReferenceFieldUpdater.get(this);
            if (lVar2 != null) {
                return lVar2;
            }
            l lVar3 = new l(lVar.f4299a * 2, lVar.f4300b);
            int i5 = (int) (1073741823 & j5);
            int i6 = (int) ((1152921503533105152L & j5) >> 30);
            while (true) {
                int i7 = lVar.f4301c;
                int i8 = i5 & i7;
                if (i8 == (i7 & i6)) {
                    break;
                }
                Object kVar = lVar.f4302d.get(i8);
                if (kVar == null) {
                    kVar = new k(i5);
                }
                lVar3.f4302d.set(lVar3.f4301c & i5, kVar);
                i5++;
            }
            atomicLongFieldUpdater.set(lVar3, (-1152921504606846977L) & j5);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, lVar3) && atomicReferenceFieldUpdater.get(this) == null) {
            }
        }
    }

    public final Object d() {
        l lVarC = this;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f4297f;
            long j5 = atomicLongFieldUpdater.get(lVarC);
            if ((j5 & 1152921504606846976L) != 0) {
                return f4298g;
            }
            int i5 = (int) (j5 & 1073741823);
            int i6 = lVarC.f4301c;
            int i7 = i5 & i6;
            if ((((int) ((1152921503533105152L & j5) >> 30)) & i6) != i7) {
                AtomicReferenceArray atomicReferenceArray = lVarC.f4302d;
                Object obj = atomicReferenceArray.get(i7);
                boolean z2 = lVarC.f4300b;
                if (obj == null) {
                    if (z2) {
                    }
                } else if (!(obj instanceof k)) {
                    long j6 = (i5 + 1) & 1073741823;
                    if (f4297f.compareAndSet(lVarC, j5, (j5 & (-1073741824)) | j6)) {
                        atomicReferenceArray.set(i7, null);
                        return obj;
                    }
                    lVarC = this;
                    if (z2) {
                        while (true) {
                            long j7 = atomicLongFieldUpdater.get(lVarC);
                            int i8 = (int) (j7 & 1073741823);
                            if ((j7 & 1152921504606846976L) != 0) {
                                lVarC = lVarC.c();
                            } else {
                                l lVar = lVarC;
                                if (f4297f.compareAndSet(lVar, j7, (j7 & (-1073741824)) | j6)) {
                                    lVar.f4302d.set(i8 & lVar.f4301c, null);
                                    lVarC = null;
                                } else {
                                    lVarC = lVar;
                                }
                            }
                            if (lVarC == null) {
                                return obj;
                            }
                        }
                    }
                }
            }
            return null;
        }
    }
}
