package s;

import i4.y;
import n1.p;
import p.i;
import p1.o1;
import p1.v;
import p1.y0;
import p3.j;
import p3.n;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g extends o implements a, v, o1 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final r3.c f5757s = new r3.c(1);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public i f5758q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f5759r;

    public static final x0.d y0(g gVar, y0 y0Var, a4.a aVar) {
        x0.d dVar;
        if (gVar.f5693p && gVar.f5759r) {
            y0 y0VarR = p1.f.r(gVar);
            if (!y0Var.L0().f5693p) {
                y0Var = null;
            }
            if (y0Var != null && (dVar = (x0.d) aVar.a()) != null) {
                x0.d dVarK = y0VarR.k(y0Var, false);
                return dVar.h(j.b(dVarK.f6534a, dVarK.f6535b));
            }
        }
        return null;
    }

    @Override // p1.o1
    public final Object k() {
        return f5757s;
    }

    @Override // r0.o
    public final boolean o0() {
        return false;
    }

    @Override // p1.v
    public final void r(p pVar) {
        this.f5759r = true;
    }

    @Override // s.a
    public final Object z(y0 y0Var, a4.a aVar, v3.c cVar) {
        Object objC = y.c(new f(this, y0Var, aVar, new n.i(this, y0Var, aVar), null), cVar);
        return objC == u3.a.f5838d ? objC : n.f5026a;
    }
}
