package androidx.compose.ui.focus;

import a4.c;
import b4.l;
import p1.q0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class FocusChangedElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f170a;

    public FocusChangedElement(c cVar) {
        this.f170a = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FocusChangedElement) && l.a(this.f170a, ((FocusChangedElement) obj).f170a);
    }

    @Override // p1.q0
    public final o h() {
        w0.a aVar = new w0.a();
        aVar.f6223q = this.f170a;
        return aVar;
    }

    public final int hashCode() {
        return this.f170a.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((w0.a) oVar).f6223q = this.f170a;
    }

    public final String toString() {
        return "FocusChangedElement(onFocusChanged=" + this.f170a + ')';
    }
}
