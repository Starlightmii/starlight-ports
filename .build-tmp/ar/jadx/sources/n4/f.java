package n4;

import i4.d0;
import i4.k1;
import i4.l0;
import i4.y;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends d0 implements v3.d, t3.c {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f4279k = AtomicReferenceFieldUpdater.newUpdater(f.class, Object.class, "_reusableCancellableContinuation$volatile");
    private volatile /* synthetic */ Object _reusableCancellableContinuation$volatile;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final i4.r f4280g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final v3.c f4281h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f4282i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f4283j;

    public f(i4.r rVar, v3.c cVar) {
        super(-1);
        this.f4280g = rVar;
        this.f4281h = cVar;
        this.f4282i = a.f4268b;
        this.f4283j = a.k(cVar.getContext());
    }

    @Override // i4.d0
    public final t3.c c() {
        return this;
    }

    @Override // v3.d
    public final v3.d getCallerFrame() {
        return this.f4281h;
    }

    @Override // t3.c
    public final t3.h getContext() {
        return this.f4281h.getContext();
    }

    @Override // i4.d0
    public final Object h() {
        Object obj = this.f4282i;
        this.f4282i = a.f4268b;
        return obj;
    }

    @Override // t3.c
    public final void resumeWith(Object obj) {
        Throwable thA = p3.i.a(obj);
        Object oVar = thA == null ? obj : new i4.o(thA, false);
        v3.c cVar = this.f4281h;
        t3.h context = cVar.getContext();
        i4.r rVar = this.f4280g;
        if (rVar.h(context)) {
            this.f4282i = oVar;
            this.f2723f = 0;
            rVar.e(cVar.getContext(), this);
            return;
        }
        l0 l0VarA = k1.a();
        if (l0VarA.f2751f >= 4294967296L) {
            this.f4282i = oVar;
            this.f2723f = 0;
            l0VarA.o(this);
            return;
        }
        l0VarA.u(true);
        try {
            t3.h context2 = cVar.getContext();
            Object objL = a.l(context2, this.f4283j);
            try {
                cVar.resumeWith(obj);
                a.g(context2, objL);
                while (l0VarA.w()) {
                }
            } catch (Throwable th) {
                a.g(context2, objL);
                throw th;
            }
        } catch (Throwable th2) {
            try {
                g(th2);
            } finally {
                l0VarA.n(true);
            }
        }
    }

    public final String toString() {
        return "DispatchedContinuation[" + this.f4280g + ", " + y.q(this.f4281h) + ']';
    }
}
