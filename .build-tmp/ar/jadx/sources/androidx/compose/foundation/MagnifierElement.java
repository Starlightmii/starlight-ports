package androidx.compose.foundation;

import android.view.View;
import n.p0;
import n.z0;
import p1.f;
import p1.q0;
import r0.o;
import w1.l;
import w1.t;
import z.i0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class MagnifierElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f68a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i0 f69b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final z0 f70c;

    public MagnifierElement(l lVar, i0 i0Var, z0 z0Var) {
        this.f68a = lVar;
        this.f69b = i0Var;
        this.f70c = z0Var;
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    @Override // p1.q0
    public final o h() {
        return new p0(this.f68a, this.f69b, this.f70c);
    }

    public final int hashCode() {
        return this.f70c.hashCode() + ((this.f69b.hashCode() + b.b.c(b.b.a(Float.NaN, b.b.a(Float.NaN, b.b.b(b.b.c(b.b.a(Float.NaN, this.f68a.hashCode() * 961, 31), 31, true), 31, 9205357640488583168L), 31), 31), 31, true)) * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        p0 p0Var = (p0) oVar;
        p0Var.getClass();
        z0 z0Var = p0Var.f3986s;
        View view = p0Var.f3987t;
        k2.b bVar = p0Var.f3988u;
        p0Var.f3984q = this.f68a;
        p0Var.f3985r = this.f69b;
        z0 z0Var2 = this.f70c;
        p0Var.f3986s = z0Var2;
        View viewV = f.v(p0Var);
        k2.b bVar2 = f.s(p0Var).f4796t;
        if (p0Var.f3989v != null) {
            t tVar = n.q0.f3996a;
            if (((!Float.isNaN(Float.NaN) || !Float.isNaN(Float.NaN)) && !z0Var2.a()) || !k2.e.a(Float.NaN, Float.NaN) || !k2.e.a(Float.NaN, Float.NaN) || !z0Var2.equals(z0Var) || !viewV.equals(view) || !b4.l.a(bVar2, bVar)) {
                p0Var.z0();
            }
        }
        p0Var.A0();
    }
}
