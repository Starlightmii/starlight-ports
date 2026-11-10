package androidx.compose.foundation.layout;

import b4.l;
import p1.q0;
import r.l0;
import r.n0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class PaddingValuesElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l0 f101a;

    public PaddingValuesElement(l0 l0Var) {
        this.f101a = l0Var;
    }

    public final boolean equals(Object obj) {
        PaddingValuesElement paddingValuesElement = obj instanceof PaddingValuesElement ? (PaddingValuesElement) obj : null;
        if (paddingValuesElement == null) {
            return false;
        }
        return l.a(this.f101a, paddingValuesElement.f101a);
    }

    @Override // p1.q0
    public final o h() {
        n0 n0Var = new n0();
        n0Var.f5595q = this.f101a;
        return n0Var;
    }

    public final int hashCode() {
        return this.f101a.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((n0) oVar).f5595q = this.f101a;
    }
}
