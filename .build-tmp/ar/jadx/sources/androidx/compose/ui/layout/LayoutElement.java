package androidx.compose.ui.layout;

import a4.f;
import b4.l;
import n1.s;
import p1.q0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class LayoutElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f199a;

    public LayoutElement(f fVar) {
        this.f199a = fVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LayoutElement) && l.a(this.f199a, ((LayoutElement) obj).f199a);
    }

    @Override // p1.q0
    public final o h() {
        s sVar = new s();
        sVar.f4151q = this.f199a;
        return sVar;
    }

    public final int hashCode() {
        return this.f199a.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((s) oVar).f4151q = this.f199a;
    }

    public final String toString() {
        return "LayoutElement(measure=" + this.f199a + ')';
    }
}
