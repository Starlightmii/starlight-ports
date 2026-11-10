package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class i0 implements Runnable, Comparable, f0 {
    private volatile Object _heap;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f2740d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2741e;

    public final int a(long j5, j0 j0Var, k0 k0Var) {
        synchronized (this) {
            if (this._heap == y.f2787b) {
                return 2;
            }
            synchronized (j0Var) {
                try {
                    i0[] i0VarArr = j0Var.f4311a;
                    i0 i0Var = i0VarArr != null ? i0VarArr[0] : null;
                    if (k0.f2747l.get(k0Var) != 0) {
                        return 1;
                    }
                    if (i0Var == null) {
                        j0Var.f2744c = j5;
                    } else {
                        long j6 = i0Var.f2740d;
                        if (j6 - j5 < 0) {
                            j5 = j6;
                        }
                        if (j5 - j0Var.f2744c > 0) {
                            j0Var.f2744c = j5;
                        }
                    }
                    long j7 = this.f2740d;
                    long j8 = j0Var.f2744c;
                    if (j7 - j8 < 0) {
                        this.f2740d = j8;
                    }
                    j0Var.a(this);
                    return 0;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public final void c(j0 j0Var) {
        if (this._heap == y.f2787b) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        this._heap = j0Var;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        long j5 = this.f2740d - ((i0) obj).f2740d;
        if (j5 > 0) {
            return 1;
        }
        return j5 < 0 ? -1 : 0;
    }

    @Override // i4.f0
    public final void dispose() {
        synchronized (this) {
            try {
                Object obj = this._heap;
                b3.r rVar = y.f2787b;
                if (obj == rVar) {
                    return;
                }
                j0 j0Var = obj instanceof j0 ? (j0) obj : null;
                if (j0Var != null) {
                    synchronized (j0Var) {
                        Object obj2 = this._heap;
                        if ((obj2 instanceof n4.t ? (n4.t) obj2 : null) != null) {
                            j0Var.b(this.f2741e);
                        }
                    }
                }
                this._heap = rVar;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String toString() {
        return "Delayed[nanos=" + this.f2740d + ']';
    }
}
