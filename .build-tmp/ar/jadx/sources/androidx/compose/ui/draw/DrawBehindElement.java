package androidx.compose.ui.draw;

import a4.c;
import b4.l;
import p1.q0;
import r0.o;
import v0.d;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class DrawBehindElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f159a;

    public DrawBehindElement(c cVar) {
        this.f159a = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DrawBehindElement) && l.a(this.f159a, ((DrawBehindElement) obj).f159a);
    }

    @Override // p1.q0
    public final o h() {
        d dVar = new d();
        dVar.f6167q = this.f159a;
        return dVar;
    }

    public final int hashCode() {
        return this.f159a.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((d) oVar).f6167q = this.f159a;
    }

    public final String toString() {
        return "DrawBehindElement(onDraw=" + this.f159a + ')';
    }
}
