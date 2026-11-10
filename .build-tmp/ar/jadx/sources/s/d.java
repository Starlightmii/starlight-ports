package s;

import b4.l;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends o {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public c f5748q;

    @Override // r0.o
    public final boolean o0() {
        return false;
    }

    @Override // r0.o
    public final void r0() {
        c cVar = this.f5748q;
        if (cVar != null) {
            cVar.f5747a.l(this);
        }
        if (cVar != null) {
            cVar.f5747a.b(this);
        }
        this.f5748q = cVar;
    }

    @Override // r0.o
    public final void s0() {
        c cVar = this.f5748q;
        if (cVar != null) {
            l.c("null cannot be cast to non-null type androidx.compose.foundation.relocation.BringIntoViewRequesterImpl", cVar);
            cVar.f5747a.l(this);
        }
    }
}
