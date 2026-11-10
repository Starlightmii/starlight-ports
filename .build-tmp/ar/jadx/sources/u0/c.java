package u0;

import a1.d;
import android.view.DragEvent;
import b4.w;
import p1.l;
import p1.o1;
import p3.j;
import q.f;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends o implements o1, l {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public c f5818q;

    public final void A0(d dVar) {
        c cVar = this.f5818q;
        if (cVar != null) {
            cVar.A0(dVar);
        }
        this.f5818q = null;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0025  */
    /* JADX WARN: Code duplicated, block: B:7:0x001d  */
    /* JADX WARN: Code duplicated, block: B:9:0x0023  */
    public final void B0(d dVar) {
        o1 o1Var;
        c cVar;
        c cVar2 = this.f5818q;
        if (cVar2 != null) {
            DragEvent dragEvent = (DragEvent) dVar.f31e;
            if (f.c(cVar2, j.b(dragEvent.getX(), dragEvent.getY()))) {
                cVar = cVar2;
            } else {
                if (this.f5681d.f5693p) {
                    w wVar = new w();
                    p1.f.x(this, new c.b(wVar, this, dVar, 7));
                    o1Var = (o1) wVar.f697d;
                } else {
                    o1Var = null;
                }
                cVar = (c) o1Var;
            }
        } else {
            if (this.f5681d.f5693p) {
                o1Var = null;
            } else {
                w wVar2 = new w();
                p1.f.x(this, new c.b(wVar2, this, dVar, 7));
                o1Var = (o1) wVar2.f697d;
            }
            cVar = (c) o1Var;
        }
        if (cVar != null && cVar2 == null) {
            cVar.z0(dVar);
            cVar.B0(dVar);
        } else if (cVar == null && cVar2 != null) {
            cVar2.A0(dVar);
        } else if (!b4.l.a(cVar, cVar2)) {
            if (cVar != null) {
                cVar.z0(dVar);
                cVar.B0(dVar);
            }
            if (cVar2 != null) {
                cVar2.A0(dVar);
            }
        } else if (cVar != null) {
            cVar.B0(dVar);
        }
        this.f5818q = cVar;
    }

    public final void C0(d dVar) {
        c cVar = this.f5818q;
        if (cVar != null) {
            cVar.C0(dVar);
        }
    }

    @Override // p1.o1
    public final Object k() {
        return b.f5817a;
    }

    @Override // r0.o
    public final void s0() {
        this.f5818q = null;
    }

    public final boolean y0(d dVar) {
        c cVar = this.f5818q;
        if (cVar == null) {
            return false;
        }
        return cVar.y0(dVar);
    }

    public final void z0(d dVar) {
        c cVar = this.f5818q;
        if (cVar != null) {
            cVar.z0(dVar);
        }
    }
}
