package androidx.compose.foundation;

import b4.l;
import n.p;
import p1.q0;
import r0.o;
import y0.s0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class BorderModifierNodeElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f56a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s0 f57b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y0.q0 f58c;

    public BorderModifierNodeElement(float f5, s0 s0Var, y0.q0 q0Var) {
        this.f56a = f5;
        this.f57b = s0Var;
        this.f58c = q0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BorderModifierNodeElement)) {
            return false;
        }
        BorderModifierNodeElement borderModifierNodeElement = (BorderModifierNodeElement) obj;
        return k2.e.a(this.f56a, borderModifierNodeElement.f56a) && this.f57b.equals(borderModifierNodeElement.f57b) && l.a(this.f58c, borderModifierNodeElement.f58c);
    }

    @Override // p1.q0
    public final o h() {
        return new p(this.f56a, this.f57b, this.f58c);
    }

    public final int hashCode() {
        return this.f58c.hashCode() + ((this.f57b.hashCode() + (Float.hashCode(this.f56a) * 31)) * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        p pVar = (p) oVar;
        float f5 = pVar.f3980t;
        v0.b bVar = pVar.f3983w;
        float f6 = this.f56a;
        if (!k2.e.a(f5, f6)) {
            pVar.f3980t = f6;
            bVar.y0();
        }
        s0 s0Var = pVar.f3981u;
        s0 s0Var2 = this.f57b;
        if (!l.a(s0Var, s0Var2)) {
            pVar.f3981u = s0Var2;
            bVar.y0();
        }
        y0.q0 q0Var = pVar.f3982v;
        y0.q0 q0Var2 = this.f58c;
        if (l.a(q0Var, q0Var2)) {
            return;
        }
        pVar.f3982v = q0Var2;
        bVar.y0();
    }

    public final String toString() {
        return "BorderModifierNodeElement(width=" + ((Object) k2.e.b(this.f56a)) + ", brush=" + this.f57b + ", shape=" + this.f58c + ')';
    }
}
