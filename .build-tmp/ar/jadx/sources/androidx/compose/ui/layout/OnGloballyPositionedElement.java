package androidx.compose.ui.layout;

import a4.c;
import n1.i0;
import p1.q0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class OnGloballyPositionedElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f201a;

    public OnGloballyPositionedElement(c cVar) {
        this.f201a = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OnGloballyPositionedElement) {
            return this.f201a == ((OnGloballyPositionedElement) obj).f201a;
        }
        return false;
    }

    @Override // p1.q0
    public final o h() {
        i0 i0Var = new i0();
        i0Var.f4119q = this.f201a;
        return i0Var;
    }

    public final int hashCode() {
        return this.f201a.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((i0) oVar).f4119q = this.f201a;
    }
}
