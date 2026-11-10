package androidx.compose.ui.focus;

import b4.l;
import p1.q0;
import r0.o;
import w0.n;
import w0.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class FocusRequesterElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n f173a;

    public FocusRequesterElement(n nVar) {
        this.f173a = nVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FocusRequesterElement) && l.a(this.f173a, ((FocusRequesterElement) obj).f173a);
    }

    @Override // p1.q0
    public final o h() {
        p pVar = new p();
        pVar.f6257q = this.f173a;
        return pVar;
    }

    public final int hashCode() {
        return this.f173a.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        p pVar = (p) oVar;
        pVar.f6257q.f6256a.l(pVar);
        n nVar = this.f173a;
        pVar.f6257q = nVar;
        nVar.f6256a.b(pVar);
    }

    public final String toString() {
        return "FocusRequesterElement(focusRequester=" + this.f173a + ')';
    }
}
