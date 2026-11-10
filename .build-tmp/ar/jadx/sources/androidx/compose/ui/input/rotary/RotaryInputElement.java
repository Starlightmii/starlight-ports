package androidx.compose.ui.input.rotary;

import p1.q0;
import q1.n;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class RotaryInputElement extends q0 {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RotaryInputElement)) {
            return false;
        }
        Object obj2 = n.f5268h;
        return obj2.equals(obj2);
    }

    @Override // p1.q0
    public final o h() {
        n nVar = n.f5268h;
        l1.a aVar = new l1.a();
        aVar.f3499q = nVar;
        return aVar;
    }

    public final int hashCode() {
        return n.f5268h.hashCode() * 31;
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((l1.a) oVar).f3499q = n.f5268h;
    }

    public final String toString() {
        return "RotaryInputElement(onRotaryScrollEvent=" + n.f5268h + ", onPreRotaryScrollEvent=null)";
    }
}
