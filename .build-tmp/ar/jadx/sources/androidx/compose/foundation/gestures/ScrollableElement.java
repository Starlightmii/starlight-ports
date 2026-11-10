package androidx.compose.foundation.gestures;

import a2.h;
import b.b;
import b4.l;
import j1.d0;
import n.x0;
import p.a1;
import p.b1;
import p.e;
import p.i;
import p.i1;
import p.l0;
import p1.f;
import p1.q0;
import q.k;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class ScrollableElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b1 f80a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l0 f81b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final x0 f82c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f83d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f84e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final k f85f;

    public ScrollableElement(x0 x0Var, l0 l0Var, b1 b1Var, k kVar, boolean z2, boolean z3) {
        this.f80a = b1Var;
        this.f81b = l0Var;
        this.f82c = x0Var;
        this.f83d = z2;
        this.f84e = z3;
        this.f85f = kVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScrollableElement)) {
            return false;
        }
        ScrollableElement scrollableElement = (ScrollableElement) obj;
        return l.a(this.f80a, scrollableElement.f80a) && this.f81b == scrollableElement.f81b && l.a(this.f82c, scrollableElement.f82c) && this.f83d == scrollableElement.f83d && this.f84e == scrollableElement.f84e && l.a(this.f85f, scrollableElement.f85f);
    }

    @Override // p1.q0
    public final o h() {
        boolean z2 = this.f84e;
        return new a1(this.f82c, this.f81b, this.f80a, this.f85f, this.f83d, z2);
    }

    public final int hashCode() {
        int iHashCode = (this.f81b.hashCode() + (this.f80a.hashCode() * 31)) * 31;
        x0 x0Var = this.f82c;
        int iC = b.c(b.c((iHashCode + (x0Var != null ? x0Var.hashCode() : 0)) * 31, 31, this.f83d), 961, this.f84e);
        k kVar = this.f85f;
        return (iC + (kVar != null ? kVar.hashCode() : 0)) * 31;
    }

    @Override // p1.q0
    public final void i(o oVar) {
        boolean z2;
        d0 d0Var;
        a1 a1Var = (a1) oVar;
        i1 i1Var = a1Var.E;
        boolean z3 = a1Var.f4409u;
        boolean z4 = this.f83d;
        boolean z5 = true;
        boolean z6 = false;
        if (z3 != z4) {
            a1Var.F.f4626b = z4;
            a1Var.C.f4570q = z4;
            z2 = true;
        } else {
            z2 = false;
        }
        h hVar = a1Var.D;
        a1.b bVar = a1Var.B;
        b1 b1Var = i1Var.f4484a;
        b1 b1Var2 = this.f80a;
        if (!l.a(b1Var, b1Var2)) {
            i1Var.f4484a = b1Var2;
            z6 = true;
        }
        x0 x0Var = this.f82c;
        i1Var.f4485b = x0Var;
        l0 l0Var = i1Var.f4487d;
        l0 l0Var2 = this.f81b;
        if (l0Var != l0Var2) {
            i1Var.f4487d = l0Var2;
            z6 = true;
        }
        boolean z7 = i1Var.f4488e;
        boolean z8 = this.f84e;
        if (z7 != z8) {
            i1Var.f4488e = z8;
            z6 = true;
        }
        i1Var.f4486c = hVar;
        i1Var.f4489f = bVar;
        i iVar = a1Var.G;
        iVar.f4474q = l0Var2;
        iVar.f4476s = z8;
        a1Var.A = x0Var;
        e eVar = e.f4434g;
        l0 l0Var3 = i1Var.f4487d;
        l0 l0Var4 = l0.f4525d;
        if (l0Var3 != l0Var4) {
            l0Var4 = l0.f4526e;
        }
        a1Var.f4408t = eVar;
        if (a1Var.f4409u != z4) {
            a1Var.f4409u = z4;
            if (!z4) {
                a1Var.E0();
                d0 d0Var2 = a1Var.f4414z;
                if (d0Var2 != null) {
                    a1Var.z0(d0Var2);
                }
                a1Var.f4414z = null;
            }
            z6 = true;
        }
        k kVar = a1Var.f4410v;
        k kVar2 = this.f85f;
        if (!l.a(kVar, kVar2)) {
            a1Var.E0();
            a1Var.f4410v = kVar2;
        }
        if (a1Var.f4407s != l0Var4) {
            a1Var.f4407s = l0Var4;
        } else {
            z5 = z6;
        }
        if (z5 && (d0Var = a1Var.f4414z) != null) {
            d0Var.A0();
        }
        if (z2) {
            a1Var.I = null;
            a1Var.J = null;
            f.n(a1Var);
        }
    }
}
