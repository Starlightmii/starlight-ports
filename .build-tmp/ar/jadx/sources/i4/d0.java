package i4;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class d0 extends p4.i {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2723f;

    public d0(int i5) {
        super(0L, false);
        this.f2723f = i5;
    }

    public void b(CancellationException cancellationException) {
    }

    public abstract t3.c c();

    public Throwable d(Object obj) {
        o oVar = obj instanceof o ? (o) obj : null;
        if (oVar != null) {
            return oVar.f2763a;
        }
        return null;
    }

    public Object f(Object obj) {
        return obj;
    }

    public final void g(Throwable th) {
        y.j(new x("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th), c().getContext());
    }

    public abstract Object h();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            t3.c cVarC = c();
            b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>", cVarC);
            n4.f fVar = (n4.f) cVarC;
            v3.c cVar = fVar.f4281h;
            Object obj = fVar.f4283j;
            t3.h context = cVar.getContext();
            Object objL = n4.a.l(context, obj);
            s0 s0Var = null;
            n1 n1VarS = objL != n4.a.f4270d ? y.s(cVar, context, objL) : null;
            try {
                t3.h context2 = cVar.getContext();
                Object objH = h();
                Throwable thD = d(objH);
                if (thD == null) {
                    int i5 = this.f2723f;
                    boolean z2 = true;
                    if (i5 != 1 && i5 != 2) {
                        z2 = false;
                    }
                    if (z2) {
                        s0Var = (s0) context2.j(s.f2774e);
                    }
                }
                if (s0Var != null && !s0Var.a()) {
                    CancellationException cancellationExceptionM = s0Var.m();
                    b(cancellationExceptionM);
                    cVar.resumeWith(p3.j.r(cancellationExceptionM));
                } else if (thD != null) {
                    cVar.resumeWith(p3.j.r(thD));
                } else {
                    cVar.resumeWith(f(objH));
                }
            } finally {
                if (n1VarS == null || n1VarS.d0()) {
                    n4.a.g(context, objL);
                }
            }
        } catch (Throwable th) {
            g(th);
        }
    }
}
