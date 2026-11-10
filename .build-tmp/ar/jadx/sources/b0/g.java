package b0;

import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import f0.i1;
import n.i0;
import n.j0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g implements i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f402a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f403b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i1 f404c;

    public g(boolean z2, float f5, i1 i1Var) {
        this.f402a = z2;
        this.f403b = f5;
        this.f404c = i1Var;
    }

    @Override // n.i0
    public final j0 a(q.j jVar, f0.v vVar) {
        long jB;
        vVar.V(988743187);
        v vVar2 = (v) vVar.j(x.f464a);
        i1 i1Var = this.f404c;
        if (((y0.u) i1Var.getValue()).f6696a != y0.u.f6694g) {
            vVar.V(-303571590);
            vVar.p(false);
            jB = ((y0.u) i1Var.getValue()).f6696a;
        } else {
            vVar.V(-303521246);
            jB = vVar2.b(vVar);
            vVar.p(false);
        }
        i1 i1VarV = f0.l.v(new y0.u(jB), vVar);
        i1 i1VarV2 = f0.l.v(vVar2.a(vVar), vVar);
        vVar.V(331259447);
        ViewGroup viewGroupB = y.b((View) vVar.j(AndroidCompositionLocals_androidKt.f208f));
        boolean zF = vVar.f(jVar) | vVar.f(this) | vVar.f(viewGroupB);
        Object objK = vVar.K();
        Object obj = f0.o.f2281a;
        if (zF || objK == obj) {
            Object bVar = new b(this.f402a, this.f403b, i1VarV, i1VarV2, viewGroupB);
            vVar.f0(bVar);
            objK = bVar;
        }
        b bVar2 = (b) objK;
        vVar.p(false);
        boolean zF2 = vVar.f(jVar) | vVar.h(bVar2);
        Object objK2 = vVar.K();
        if (zF2 || objK2 == obj) {
            objK2 = new d(jVar, bVar2, (t3.c) null, 1);
            vVar.f0(objK2);
        }
        f0.l.f(bVar2, jVar, (a4.e) objK2, vVar);
        vVar.p(false);
        return bVar2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return this.f402a == gVar.f402a && k2.e.a(this.f403b, gVar.f403b) && this.f404c.equals(gVar.f404c);
    }

    public final int hashCode() {
        return this.f404c.hashCode() + b.b.a(this.f403b, Boolean.hashCode(this.f402a) * 31, 31);
    }
}
