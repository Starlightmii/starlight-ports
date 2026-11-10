package i4;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z0 implements o0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2802e = AtomicIntegerFieldUpdater.newUpdater(z0.class, "_isCompleting$volatile");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2803f = AtomicReferenceFieldUpdater.newUpdater(z0.class, Object.class, "_rootCause$volatile");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2804g = AtomicReferenceFieldUpdater.newUpdater(z0.class, Object.class, "_exceptionsHolder$volatile");
    private volatile /* synthetic */ Object _exceptionsHolder$volatile;
    private volatile /* synthetic */ int _isCompleting$volatile = 0;
    private volatile /* synthetic */ Object _rootCause$volatile;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c1 f2805d;

    public z0(c1 c1Var, Throwable th) {
        this.f2805d = c1Var;
        this._rootCause$volatile = th;
    }

    @Override // i4.o0
    public final boolean a() {
        return d() == null;
    }

    public final void b(Throwable th) {
        Throwable thD = d();
        if (thD == null) {
            f2803f.set(this, th);
            return;
        }
        if (th == thD) {
            return;
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2804g;
        Object obj = atomicReferenceFieldUpdater.get(this);
        if (obj == null) {
            atomicReferenceFieldUpdater.set(this, th);
            return;
        }
        if (!(obj instanceof Throwable)) {
            if (obj instanceof ArrayList) {
                ((ArrayList) obj).add(th);
                return;
            } else {
                throw new IllegalStateException(("State is " + obj).toString());
            }
        }
        if (th == obj) {
            return;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(th);
        atomicReferenceFieldUpdater.set(this, arrayList);
    }

    @Override // i4.o0
    public final c1 c() {
        return this.f2805d;
    }

    public final Throwable d() {
        return (Throwable) f2803f.get(this);
    }

    public final boolean e() {
        return d() != null;
    }

    public final ArrayList f(Throwable th) {
        ArrayList arrayList;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2804g;
        Object obj = atomicReferenceFieldUpdater.get(this);
        if (obj == null) {
            arrayList = new ArrayList(4);
        } else if (obj instanceof Throwable) {
            ArrayList arrayList2 = new ArrayList(4);
            arrayList2.add(obj);
            arrayList = arrayList2;
        } else {
            if (!(obj instanceof ArrayList)) {
                throw new IllegalStateException(("State is " + obj).toString());
            }
            arrayList = (ArrayList) obj;
        }
        Throwable thD = d();
        if (thD != null) {
            arrayList.add(0, thD);
        }
        if (th != null && !th.equals(thD)) {
            arrayList.add(th);
        }
        atomicReferenceFieldUpdater.set(this, y.f2793h);
        return arrayList;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Finishing[cancelling=");
        sb.append(e());
        sb.append(", completing=");
        sb.append(f2802e.get(this) != 0);
        sb.append(", rootCause=");
        sb.append(d());
        sb.append(", exceptions=");
        sb.append(f2804g.get(this));
        sb.append(", list=");
        sb.append(this.f2805d);
        sb.append(']');
        return sb.toString();
    }
}
