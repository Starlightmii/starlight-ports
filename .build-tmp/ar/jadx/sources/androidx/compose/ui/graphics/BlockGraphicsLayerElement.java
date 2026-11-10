package androidx.compose.ui.graphics;

import a4.c;
import b4.l;
import p1.f;
import p1.q0;
import p1.y0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class BlockGraphicsLayerElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f184a;

    public BlockGraphicsLayerElement(c cVar) {
        this.f184a = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BlockGraphicsLayerElement) && l.a(this.f184a, ((BlockGraphicsLayerElement) obj).f184a);
    }

    @Override // p1.q0
    public final o h() {
        return new y0.o(this.f184a);
    }

    public final int hashCode() {
        return this.f184a.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        y0.o oVar2 = (y0.o) oVar;
        oVar2.f6665q = this.f184a;
        y0 y0Var = f.q(oVar2, 2).f4999p;
        if (y0Var != null) {
            y0Var.g1(oVar2.f6665q, true);
        }
    }

    public final String toString() {
        return "BlockGraphicsLayerElement(block=" + this.f184a + ')';
    }
}
