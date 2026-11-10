package androidx.compose.foundation;

import b4.l;
import n.h1;
import n.j1;
import p1.q0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class ScrollingLayoutElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h1 f72a;

    public ScrollingLayoutElement(h1 h1Var) {
        this.f72a = h1Var;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ScrollingLayoutElement) {
            return l.a(this.f72a, ((ScrollingLayoutElement) obj).f72a);
        }
        return false;
    }

    @Override // p1.q0
    public final o h() {
        j1 j1Var = new j1();
        j1Var.f3944q = this.f72a;
        j1Var.f3945r = true;
        return j1Var;
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + b.b.c(this.f72a.hashCode() * 31, 31, false);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        j1 j1Var = (j1) oVar;
        j1Var.f3944q = this.f72a;
        j1Var.f3945r = true;
    }
}
