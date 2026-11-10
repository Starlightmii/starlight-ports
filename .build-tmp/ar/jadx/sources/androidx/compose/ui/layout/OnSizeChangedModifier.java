package androidx.compose.ui.layout;

import a4.c;
import n1.j0;
import p1.q0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class OnSizeChangedModifier extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f202a;

    public OnSizeChangedModifier(c cVar) {
        this.f202a = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OnSizeChangedModifier) {
            return this.f202a == ((OnSizeChangedModifier) obj).f202a;
        }
        return false;
    }

    @Override // p1.q0
    public final o h() {
        j0 j0Var = new j0();
        j0Var.f4124q = this.f202a;
        j0Var.f4125r = a.a.f(Integer.MIN_VALUE, Integer.MIN_VALUE);
        return j0Var;
    }

    public final int hashCode() {
        return this.f202a.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        j0 j0Var = (j0) oVar;
        j0Var.f4124q = this.f202a;
        j0Var.f4125r = a.a.f(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }
}
