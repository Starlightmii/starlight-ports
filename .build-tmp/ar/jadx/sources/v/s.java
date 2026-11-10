package v;

import f0.s1;
import q1.u2;
import q1.v2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ o0 f6066e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f6067f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ u2 f6068g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ z.e0 f6069h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ e2.x f6070i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ e2.r f6071j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(o0 o0Var, boolean z2, u2 u2Var, z.e0 e0Var, e2.x xVar, e2.r rVar) {
        super(1);
        this.f6066e = o0Var;
        this.f6067f = z2;
        this.f6068g = u2Var;
        this.f6069h = e0Var;
        this.f6070i = xVar;
        this.f6071j = rVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        e2.d0 d0Var;
        n1.p pVar;
        n1.p pVar2;
        n1.p pVar3 = (n1.p) obj;
        o0 o0Var = this.f6066e;
        s1 s1Var = o0Var.f6020o;
        o0Var.f6013h = pVar3;
        e1 e1VarD = o0Var.d();
        if (e1VarD != null) {
            e1VarD.f5900b = pVar3;
        }
        if (this.f6067f) {
            f0 f0VarA = o0Var.a();
            f0 f0Var = f0.f5918e;
            e2.x xVar = this.f6070i;
            z.e0 e0Var = this.f6069h;
            if (f0VarA == f0Var) {
                if (((Boolean) o0Var.f6017l.getValue()).booleanValue() && ((v2) this.f6068g).a()) {
                    e0Var.o();
                } else {
                    e0Var.k();
                }
                o0Var.f6018m.setValue(Boolean.valueOf(p3.p.v(e0Var, true)));
                o0Var.f6019n.setValue(Boolean.valueOf(p3.p.v(e0Var, false)));
                s1Var.setValue(Boolean.valueOf(y1.g0.b(xVar.f2063b)));
            } else if (o0Var.a() == f0.f5919f) {
                s1Var.setValue(Boolean.valueOf(p3.p.v(e0Var, true)));
            }
            e2.r rVar = this.f6071j;
            l0.q(o0Var, xVar, rVar);
            e1 e1VarD2 = o0Var.d();
            if (e1VarD2 != null && (d0Var = o0Var.f6010e) != null && o0Var.b() && (pVar = e1VarD2.f5900b) != null && pVar.N() && (pVar2 = e1VarD2.f5901c) != null) {
                y1.e0 e0Var2 = e1VarD2.f5899a;
                c0.g1 g1Var = new c0.g1(25, pVar);
                x0.d dVarW = q.f.w(pVar);
                x0.d dVarK = pVar.k(pVar2, false);
                if (b4.l.a((e2.d0) d0Var.f1999a.f2066b.get(), d0Var)) {
                    d0Var.f2000b.b(xVar, rVar, e0Var2, g1Var, dVarW, dVarK);
                }
            }
        }
        return p3.n.f5026a;
    }
}
