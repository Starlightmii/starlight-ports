package androidx.compose.foundation.relocation;

import b4.l;
import p1.q0;
import r0.o;
import s.c;
import s.d;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class BringIntoViewRequesterElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f119a;

    public BringIntoViewRequesterElement(c cVar) {
        this.f119a = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BringIntoViewRequesterElement) {
            return l.a(this.f119a, ((BringIntoViewRequesterElement) obj).f119a);
        }
        return false;
    }

    @Override // p1.q0
    public final o h() {
        d dVar = new d();
        dVar.f5748q = this.f119a;
        return dVar;
    }

    public final int hashCode() {
        return this.f119a.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        d dVar = (d) oVar;
        c cVar = dVar.f5748q;
        if (cVar != null) {
            cVar.f5747a.l(dVar);
        }
        c cVar2 = this.f119a;
        if (cVar2 != null) {
            cVar2.f5747a.b(dVar);
        }
        dVar.f5748q = cVar2;
    }
}
