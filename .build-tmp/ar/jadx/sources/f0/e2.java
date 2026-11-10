package f0;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e2 implements a4.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2152d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2153e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f2154f;

    public /* synthetic */ e2(Object obj, int i5, Object obj2) {
        this.f2152d = i5;
        this.f2153e = obj;
        this.f2154f = obj2;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f2152d) {
            case 0:
                e0 e0Var = (e0) this.f2153e;
                j.a0 a0Var = (j.a0) this.f2154f;
                e0Var.z(obj);
                if (a0Var != null) {
                    a0Var.a(obj);
                }
                return p3.n.f5026a;
            case 1:
                l2 l2Var = (l2) this.f2153e;
                Throwable th = (Throwable) this.f2154f;
                Throwable th2 = (Throwable) obj;
                synchronized (l2Var.f2245c) {
                    if (th == null) {
                        th = null;
                    } else if (th2 != null) {
                        try {
                            if (th2 instanceof CancellationException) {
                                th2 = null;
                            }
                            if (th2 != null) {
                                d4.a.f(th, th2);
                            }
                        } catch (Throwable th3) {
                            throw th3;
                        }
                    }
                    l2Var.f2247e = th;
                    l4.d0 d0Var = l2Var.f2263u;
                    g2 g2Var = g2.f2177d;
                    d0Var.getClass();
                    d0Var.i(null, g2Var);
                    break;
                }
                break;
            default:
                ((j4.d) this.f2153e).f3348f.removeCallbacks((j4.c) this.f2154f);
                break;
        }
        return p3.n.f5026a;
    }
}
