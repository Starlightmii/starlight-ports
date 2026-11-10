package androidx.compose.ui.draw;

import a4.c;
import b4.l;
import p1.q0;
import r0.o;
import v0.f;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class DrawWithContentElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f161a;

    public DrawWithContentElement(c cVar) {
        this.f161a = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DrawWithContentElement) && l.a(this.f161a, ((DrawWithContentElement) obj).f161a);
    }

    @Override // p1.q0
    public final o h() {
        f fVar = new f();
        fVar.f6168q = this.f161a;
        return fVar;
    }

    public final int hashCode() {
        return this.f161a.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((f) oVar).f6168q = this.f161a;
    }

    public final String toString() {
        return "DrawWithContentElement(onDraw=" + this.f161a + ')';
    }
}
