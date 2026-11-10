package androidx.compose.ui.graphics;

import b.b;
import b4.l;
import p1.f;
import p1.q0;
import p1.y0;
import r0.o;
import y0.r0;
import y0.t0;
import y0.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class GraphicsLayerElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f185a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f186b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f187c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f188d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f189e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final y0.q0 f190f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f191g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f192h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f193i;

    public GraphicsLayerElement(float f5, float f6, float f7, float f8, long j5, y0.q0 q0Var, boolean z2, long j6, long j7) {
        this.f185a = f5;
        this.f186b = f6;
        this.f187c = f7;
        this.f188d = f8;
        this.f189e = j5;
        this.f190f = q0Var;
        this.f191g = z2;
        this.f192h = j6;
        this.f193i = j7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GraphicsLayerElement) {
            GraphicsLayerElement graphicsLayerElement = (GraphicsLayerElement) obj;
            if (Float.compare(this.f185a, graphicsLayerElement.f185a) == 0 && Float.compare(this.f186b, graphicsLayerElement.f186b) == 0 && Float.compare(this.f187c, graphicsLayerElement.f187c) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(this.f188d, graphicsLayerElement.f188d) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(8.0f, 8.0f) == 0) {
                long j5 = graphicsLayerElement.f189e;
                int i5 = t0.f6687c;
                if (this.f189e == j5 && l.a(this.f190f, graphicsLayerElement.f190f) && this.f191g == graphicsLayerElement.f191g && u.c(this.f192h, graphicsLayerElement.f192h) && u.c(this.f193i, graphicsLayerElement.f193i)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // p1.q0
    public final o h() {
        r0 r0Var = new r0();
        r0Var.f6673q = this.f185a;
        r0Var.f6674r = this.f186b;
        r0Var.f6675s = this.f187c;
        r0Var.f6676t = this.f188d;
        r0Var.f6677u = 8.0f;
        r0Var.f6678v = this.f189e;
        r0Var.f6679w = this.f190f;
        r0Var.f6680x = this.f191g;
        r0Var.f6681y = this.f192h;
        r0Var.f6682z = this.f193i;
        r0Var.A = new w1.l(2, r0Var);
        return r0Var;
    }

    public final int hashCode() {
        int iA = b.a(8.0f, b.a(0.0f, b.a(0.0f, b.a(0.0f, b.a(this.f188d, b.a(0.0f, b.a(0.0f, b.a(this.f187c, b.a(this.f186b, Float.hashCode(this.f185a) * 31, 31), 31), 31), 31), 31), 31), 31), 31), 31);
        int i5 = t0.f6687c;
        int iC = b.c((this.f190f.hashCode() + b.b(iA, 31, this.f189e)) * 31, 961, this.f191g);
        int i6 = u.f6695h;
        return Integer.hashCode(0) + b.b(b.b(iC, 31, this.f192h), 31, this.f193i);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        r0 r0Var = (r0) oVar;
        r0Var.f6673q = this.f185a;
        r0Var.f6674r = this.f186b;
        r0Var.f6675s = this.f187c;
        r0Var.f6676t = this.f188d;
        r0Var.f6677u = 8.0f;
        r0Var.f6678v = this.f189e;
        r0Var.f6679w = this.f190f;
        r0Var.f6680x = this.f191g;
        r0Var.f6681y = this.f192h;
        r0Var.f6682z = this.f193i;
        y0 y0Var = f.q(r0Var, 2).f4999p;
        if (y0Var != null) {
            y0Var.g1(r0Var.A, true);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("GraphicsLayerElement(scaleX=");
        sb.append(this.f185a);
        sb.append(", scaleY=");
        sb.append(this.f186b);
        sb.append(", alpha=");
        sb.append(this.f187c);
        sb.append(", translationX=0.0, translationY=0.0, shadowElevation=");
        sb.append(this.f188d);
        sb.append(", rotationX=0.0, rotationY=0.0, rotationZ=0.0, cameraDistance=8.0, transformOrigin=");
        sb.append((Object) t0.c(this.f189e));
        sb.append(", shape=");
        sb.append(this.f190f);
        sb.append(", clip=");
        sb.append(this.f191g);
        sb.append(", renderEffect=null, ambientShadowColor=");
        b.j(this.f192h, sb, ", spotShadowColor=");
        sb.append((Object) u.i(this.f193i));
        sb.append(", compositingStrategy=CompositingStrategy(value=0))");
        return sb.toString();
    }
}
