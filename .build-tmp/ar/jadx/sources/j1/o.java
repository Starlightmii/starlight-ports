package j1;

import p1.j1;
import p1.o1;
import q1.k0;
import v.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o extends r0.o implements o1, j1, p1.k {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f3269q;

    public final void A0() {
        p3.n nVar;
        r rVar;
        if (this.f3269q) {
            this.f3269q = false;
            if (this.f5693p) {
                b4.w wVar = new b4.w();
                p1.f.w(this, new l(wVar));
                o oVar = (o) wVar.f697d;
                if (oVar != null) {
                    oVar.y0();
                    nVar = p3.n.f5026a;
                } else {
                    nVar = null;
                }
                if (nVar != null || (rVar = (r) p1.f.h(this, q1.j1.f5250s)) == null) {
                    return;
                }
                q.f3271a.getClass();
                k0.f5255a.a(((q1.q) rVar).f5304a, s.f3272a);
            }
        }
    }

    @Override // p1.j1
    public final void W() {
        A0();
    }

    @Override // p1.o1
    public final /* bridge */ /* synthetic */ Object k() {
        return "androidx.compose.ui.input.pointer.PointerHoverIcon";
    }

    @Override // r0.o
    public final void s0() {
        A0();
    }

    @Override // p1.j1
    public final void y(i iVar, j jVar, long j5) {
        if (jVar == j.f3261e) {
            int i5 = iVar.f3259c;
            if (i5 == 4) {
                this.f3269q = true;
                z0();
            } else if (i5 == 5) {
                A0();
            }
        }
    }

    public final void y0() {
        a aVar = l0.f5959b;
        b4.w wVar = new b4.w();
        p1.f.w(this, new n(0, wVar));
        r rVar = (r) p1.f.h(this, q1.j1.f5250s);
        if (rVar != null) {
            k0.f5255a.a(((q1.q) rVar).f5304a, aVar);
        }
    }

    public final void z0() {
        b4.s sVar = new b4.s();
        sVar.f693d = true;
        p1.f.x(this, new m(sVar));
        if (sVar.f693d) {
            y0();
        }
    }
}
