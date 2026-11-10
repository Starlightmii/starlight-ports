package androidx.compose.ui.draw;

import b4.l;
import d1.b;
import n1.i;
import p1.q0;
import r0.c;
import r0.o;
import v0.h;
import x0.f;
import y0.m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class PainterElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f162a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f163b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f164c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f165d;

    public PainterElement(b bVar, c cVar, float f5, m mVar) {
        this.f162a = bVar;
        this.f163b = cVar;
        this.f164c = f5;
        this.f165d = mVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PainterElement)) {
            return false;
        }
        PainterElement painterElement = (PainterElement) obj;
        if (!l.a(this.f162a, painterElement.f162a) || !l.a(this.f163b, painterElement.f163b)) {
            return false;
        }
        Object obj2 = i.f4118a;
        return obj2.equals(obj2) && Float.compare(this.f164c, painterElement.f164c) == 0 && l.a(this.f165d, painterElement.f165d);
    }

    @Override // p1.q0
    public final o h() {
        h hVar = new h();
        hVar.f6171q = this.f162a;
        hVar.f6172r = true;
        hVar.f6173s = this.f163b;
        hVar.f6174t = i.f4118a;
        hVar.f6175u = this.f164c;
        hVar.f6176v = this.f165d;
        return hVar;
    }

    public final int hashCode() {
        int iA = b.b.a(this.f164c, (i.f4118a.hashCode() + ((this.f163b.hashCode() + b.b.c(this.f162a.hashCode() * 31, 31, true)) * 31)) * 31, 31);
        m mVar = this.f165d;
        return iA + (mVar == null ? 0 : mVar.hashCode());
    }

    @Override // p1.q0
    public final void i(o oVar) {
        h hVar = (h) oVar;
        boolean z2 = hVar.f6172r;
        b bVar = this.f162a;
        boolean z3 = (z2 && f.a(hVar.f6171q.d(), bVar.d())) ? false : true;
        hVar.f6171q = bVar;
        hVar.f6172r = true;
        hVar.f6173s = this.f163b;
        hVar.f6174t = i.f4118a;
        hVar.f6175u = this.f164c;
        hVar.f6176v = this.f165d;
        if (z3) {
            p1.f.m(hVar);
        }
        p1.f.l(hVar);
    }

    public final String toString() {
        return "PainterElement(painter=" + this.f162a + ", sizeToIntrinsics=true, alignment=" + this.f163b + ", contentScale=" + i.f4118a + ", alpha=" + this.f164c + ", colorFilter=" + this.f165d + ')';
    }
}
