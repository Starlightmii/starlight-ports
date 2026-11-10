package androidx.compose.ui.semantics;

import c0.k;
import p1.q0;
import r0.o;
import w1.b;
import w1.i;
import w1.j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class ClearAndSetSemanticsElement extends q0 implements j {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClearAndSetSemanticsElement)) {
            return false;
        }
        Object obj2 = k.f959g;
        return obj2.equals(obj2);
    }

    @Override // w1.j
    public final i g() {
        i iVar = new i();
        iVar.f6309e = false;
        iVar.f6310f = true;
        return iVar;
    }

    @Override // p1.q0
    public final o h() {
        return new b(false, true, k.f959g);
    }

    public final int hashCode() {
        return k.f959g.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((b) oVar).f6276s = k.f959g;
    }

    public final String toString() {
        return "ClearAndSetSemanticsElement(properties=" + k.f959g + ')';
    }
}
