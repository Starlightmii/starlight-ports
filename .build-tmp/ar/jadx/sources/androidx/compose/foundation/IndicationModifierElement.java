package androidx.compose.foundation;

import b4.l;
import n.m0;
import n.n0;
import p1.q0;
import q.j;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class IndicationModifierElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j f66a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n0 f67b;

    public IndicationModifierElement(j jVar, n0 n0Var) {
        this.f66a = jVar;
        this.f67b = n0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IndicationModifierElement)) {
            return false;
        }
        IndicationModifierElement indicationModifierElement = (IndicationModifierElement) obj;
        return l.a(this.f66a, indicationModifierElement.f66a) && l.a(this.f67b, indicationModifierElement.f67b);
    }

    @Override // p1.q0
    public final o h() {
        p1.l lVarB = this.f67b.b(this.f66a);
        m0 m0Var = new m0();
        m0Var.f3963s = lVarB;
        m0Var.y0(lVarB);
        return m0Var;
    }

    public final int hashCode() {
        return this.f67b.hashCode() + (this.f66a.hashCode() * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        m0 m0Var = (m0) oVar;
        p1.l lVarB = this.f67b.b(this.f66a);
        m0Var.z0(m0Var.f3963s);
        m0Var.f3963s = lVarB;
        m0Var.y0(lVarB);
    }
}
