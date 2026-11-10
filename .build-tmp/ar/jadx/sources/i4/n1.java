package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n1 extends n4.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ThreadLocal f2761h;
    private volatile boolean threadLocalIsSet;

    /* JADX WARN: Illegal instructions before constructor call */
    public n1(t3.h hVar, v3.i iVar) {
        o1 o1Var = o1.f2764d;
        super(iVar, hVar.j(o1Var) == null ? hVar.k(o1Var) : hVar);
        this.f2761h = new ThreadLocal();
        if (iVar.getContext().j(t3.d.f5806d) instanceof r) {
            return;
        }
        Object objL = n4.a.l(hVar, null);
        n4.a.g(hVar, objL);
        e0(hVar, objL);
    }

    public final boolean d0() {
        boolean z2 = this.threadLocalIsSet && this.f2761h.get() == null;
        this.f2761h.remove();
        return !z2;
    }

    public final void e0(t3.h hVar, Object obj) {
        this.threadLocalIsSet = true;
        this.f2761h.set(new p3.f(hVar, obj));
    }

    @Override // n4.p, i4.a1
    public final void v(Object obj) {
        if (this.threadLocalIsSet) {
            p3.f fVar = (p3.f) this.f2761h.get();
            if (fVar != null) {
                n4.a.g((t3.h) fVar.f5014d, fVar.f5015e);
            }
            this.f2761h.remove();
        }
        Object objO = y.o(obj);
        t3.c cVar = this.f4305g;
        t3.h context = cVar.getContext();
        Object objL = n4.a.l(context, null);
        n1 n1VarS = objL != n4.a.f4270d ? y.s(cVar, context, objL) : null;
        try {
            this.f4305g.resumeWith(objO);
        } finally {
            if (n1VarS == null || n1VarS.d0()) {
                n4.a.g(context, objL);
            }
        }
    }
}
