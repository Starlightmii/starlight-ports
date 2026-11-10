package androidx.compose.ui.draw;

import b.b;
import b4.l;
import c0.g1;
import k2.e;
import o.f;
import p1.q0;
import p1.y0;
import r0.o;
import y0.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class ShadowGraphicsLayerElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y0.q0 f166a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f167b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f168c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f169d;

    public ShadowGraphicsLayerElement(y0.q0 q0Var, boolean z2, long j5, long j6) {
        float f5 = f.f4325a;
        this.f166a = q0Var;
        this.f167b = z2;
        this.f168c = j5;
        this.f169d = j6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShadowGraphicsLayerElement)) {
            return false;
        }
        ShadowGraphicsLayerElement shadowGraphicsLayerElement = (ShadowGraphicsLayerElement) obj;
        float f5 = f.f4328d;
        return e.a(f5, f5) && l.a(this.f166a, shadowGraphicsLayerElement.f166a) && this.f167b == shadowGraphicsLayerElement.f167b && u.c(this.f168c, shadowGraphicsLayerElement.f168c) && u.c(this.f169d, shadowGraphicsLayerElement.f169d);
    }

    @Override // p1.q0
    public final o h() {
        return new y0.o(new g1(28, this));
    }

    public final int hashCode() {
        int iC = b.c((this.f166a.hashCode() + (Float.hashCode(f.f4328d) * 31)) * 31, 31, this.f167b);
        int i5 = u.f6695h;
        return Long.hashCode(this.f169d) + b.b(iC, 31, this.f168c);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        y0.o oVar2 = (y0.o) oVar;
        oVar2.f6665q = new g1(28, this);
        y0 y0Var = p1.f.q(oVar2, 2).f4999p;
        if (y0Var != null) {
            y0Var.g1(oVar2.f6665q, true);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ShadowGraphicsLayerElement(elevation=");
        sb.append((Object) e.b(f.f4328d));
        sb.append(", shape=");
        sb.append(this.f166a);
        sb.append(", clip=");
        sb.append(this.f167b);
        sb.append(", ambientColor=");
        b.j(this.f168c, sb, ", spotColor=");
        sb.append((Object) u.i(this.f169d));
        sb.append(')');
        return sb.toString();
    }
}
