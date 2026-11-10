package y0;

import c0.c2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r0 extends r0.o implements p1.w {
    public w1.l A;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f6673q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f6674r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f6675s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f6676t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f6677u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f6678v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public q0 f6679w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f6680x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f6681y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public long f6682z;

    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        n1.m0 m0VarC = e0Var.c(j5);
        return h0Var.F(m0VarC.f4130d, m0VarC.f4131e, q3.t.f5478d, new c2(m0VarC, 26, this));
    }

    @Override // r0.o
    public final boolean o0() {
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SimpleGraphicsLayerModifier(scaleX=");
        sb.append(this.f6673q);
        sb.append(", scaleY=");
        sb.append(this.f6674r);
        sb.append(", alpha = ");
        sb.append(this.f6675s);
        sb.append(", translationX=0.0, translationY=0.0, shadowElevation=");
        sb.append(this.f6676t);
        sb.append(", rotationX=0.0, rotationY=0.0, rotationZ=0.0, cameraDistance=");
        sb.append(this.f6677u);
        sb.append(", transformOrigin=");
        sb.append((Object) t0.c(this.f6678v));
        sb.append(", shape=");
        sb.append(this.f6679w);
        sb.append(", clip=");
        sb.append(this.f6680x);
        sb.append(", renderEffect=null, ambientShadowColor=");
        b.b.j(this.f6681y, sb, ", spotShadowColor=");
        sb.append((Object) u.i(this.f6682z));
        sb.append(", compositingStrategy=CompositingStrategy(value=0))");
        return sb.toString();
    }
}
