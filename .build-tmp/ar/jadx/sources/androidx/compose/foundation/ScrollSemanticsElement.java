package androidx.compose.foundation;

import b4.l;
import n.e1;
import n.h1;
import p1.q0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class ScrollSemanticsElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h1 f71a;

    public ScrollSemanticsElement(h1 h1Var) {
        this.f71a = h1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ScrollSemanticsElement) && l.a(this.f71a, ((ScrollSemanticsElement) obj).f71a);
    }

    @Override // p1.q0
    public final o h() {
        e1 e1Var = new e1();
        e1Var.f3896q = this.f71a;
        e1Var.f3897r = true;
        return e1Var;
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + b.b.c(b.b.c(this.f71a.hashCode() * 31, 961, false), 31, true);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        e1 e1Var = (e1) oVar;
        e1Var.f3896q = this.f71a;
        e1Var.f3897r = true;
    }

    public final String toString() {
        return "ScrollSemanticsElement(state=" + this.f71a + ", reverseScrolling=false, flingBehavior=null, isScrollable=true, isVertical=true)";
    }
}
