package androidx.compose.foundation.layout;

import p1.q0;
import r.j0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class OffsetPxElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a4.c f96a;

    public OffsetPxElement(a4.c cVar) {
        this.f96a = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        OffsetPxElement offsetPxElement = obj instanceof OffsetPxElement ? (OffsetPxElement) obj : null;
        return offsetPxElement != null && this.f96a == offsetPxElement.f96a;
    }

    @Override // p1.q0
    public final o h() {
        j0 j0Var = new j0();
        j0Var.f5574q = this.f96a;
        j0Var.f5575r = true;
        return j0Var;
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + (this.f96a.hashCode() * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        j0 j0Var = (j0) oVar;
        j0Var.f5574q = this.f96a;
        j0Var.f5575r = true;
    }

    public final String toString() {
        return "OffsetPxModifier(offset=" + this.f96a + ", rtlAware=true)";
    }
}
