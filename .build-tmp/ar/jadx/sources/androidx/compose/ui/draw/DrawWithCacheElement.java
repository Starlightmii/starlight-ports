package androidx.compose.ui.draw;

import a4.c;
import b4.l;
import p1.q0;
import r0.o;
import v0.b;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class DrawWithCacheElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f160a;

    public DrawWithCacheElement(c cVar) {
        this.f160a = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DrawWithCacheElement) && l.a(this.f160a, ((DrawWithCacheElement) obj).f160a);
    }

    @Override // p1.q0
    public final o h() {
        return new b(new v0.c(), this.f160a);
    }

    public final int hashCode() {
        return this.f160a.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        b bVar = (b) oVar;
        bVar.f6164s = this.f160a;
        bVar.y0();
    }

    public final String toString() {
        return "DrawWithCacheElement(onBuildDrawCache=" + this.f160a + ')';
    }
}
