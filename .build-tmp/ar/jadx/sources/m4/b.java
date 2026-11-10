package m4;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d[] f3813d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3814e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3815f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public u f3816g;

    public final d a() {
        d dVarB;
        u uVar;
        synchronized (this) {
            try {
                d[] dVarArrE = this.f3813d;
                if (dVarArrE == null) {
                    dVarArrE = e();
                    this.f3813d = dVarArrE;
                } else if (this.f3814e >= dVarArrE.length) {
                    Object[] objArrCopyOf = Arrays.copyOf(dVarArrE, dVarArrE.length * 2);
                    b4.l.d("copyOf(...)", objArrCopyOf);
                    this.f3813d = (d[]) objArrCopyOf;
                    dVarArrE = (d[]) objArrCopyOf;
                }
                int i5 = this.f3815f;
                do {
                    dVarB = dVarArrE[i5];
                    if (dVarB == null) {
                        dVarB = b();
                        dVarArrE[i5] = dVarB;
                    }
                    i5++;
                    if (i5 >= dVarArrE.length) {
                        i5 = 0;
                    }
                } while (!dVarB.a(this));
                this.f3815f = i5;
                this.f3814e++;
                uVar = this.f3816g;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (uVar != null) {
            uVar.w(1);
        }
        return dVarB;
    }

    public abstract d b();

    public abstract d[] e();

    public final void f(d dVar) {
        u uVar;
        int i5;
        t3.c[] cVarArrB;
        synchronized (this) {
            try {
                int i6 = this.f3814e - 1;
                this.f3814e = i6;
                uVar = this.f3816g;
                if (i6 == 0) {
                    this.f3815f = 0;
                }
                b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>", dVar);
                cVarArrB = dVar.b(this);
            } catch (Throwable th) {
                throw th;
            }
        }
        for (t3.c cVar : cVarArrB) {
            if (cVar != null) {
                cVar.resumeWith(p3.n.f5026a);
            }
        }
        if (uVar != null) {
            uVar.w(-1);
        }
    }

    public final u h() {
        u uVar;
        synchronized (this) {
            uVar = this.f3816g;
            if (uVar == null) {
                int i5 = this.f3814e;
                uVar = new u(1, Integer.MAX_VALUE, k4.a.f3436e);
                uVar.q(Integer.valueOf(i5));
                this.f3816g = uVar;
            }
        }
        return uVar;
    }
}
