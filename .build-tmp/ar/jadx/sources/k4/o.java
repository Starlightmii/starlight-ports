package k4;

import i4.a1;
import i4.t0;
import i4.y;
import i4.z0;
import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o extends i4.a implements p, g {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c f3480g;

    public o(t3.h hVar, c cVar) {
        super(hVar, true);
        this.f3480g = cVar;
    }

    @Override // i4.a
    public final void a0(Throwable th, boolean z2) {
        if (this.f3480g.g(th, false) || z2) {
            return;
        }
        y.j(th, this.f2713f);
    }

    @Override // i4.a1, i4.s0
    public final void b(CancellationException cancellationException) {
        Object obj = a1.f2715d.get(this);
        if (obj instanceof i4.o) {
            return;
        }
        if ((obj instanceof z0) && ((z0) obj).e()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new t0(A(), null, this);
        }
        y(cancellationException);
    }

    @Override // i4.a
    public final void b0(Object obj) {
        this.f3480g.g(null, false);
    }

    @Override // k4.q
    public final Object d(v3.i iVar) {
        return this.f3480g.d(iVar);
    }

    @Override // k4.q
    public final b iterator() {
        c cVar = this.f3480g;
        cVar.getClass();
        return new b(cVar);
    }

    @Override // k4.r
    public final Object l(Object obj, t3.c cVar) {
        return this.f3480g.l(obj, cVar);
    }

    @Override // k4.q
    public final Object n() {
        return this.f3480g.n();
    }

    @Override // k4.r
    public final Object o(Object obj) {
        return this.f3480g.o(obj);
    }

    @Override // i4.a1
    public final void y(CancellationException cancellationException) {
        this.f3480g.g(cancellationException, true);
        x(cancellationException);
    }
}
