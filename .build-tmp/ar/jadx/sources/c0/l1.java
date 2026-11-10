package c0;

import androidx.compose.ui.semantics.ClearAndSetSemanticsElement;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l1 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ e1 f1019e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f1020f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f1021g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ a4.e f1022h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f1023i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ n0.d f1024j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l1(e1 e1Var, boolean z2, boolean z3, a4.e eVar, boolean z4, n0.d dVar) {
        super(2);
        this.f1019e = e1Var;
        this.f1020f = z2;
        this.f1021g = z3;
        this.f1022h = eVar;
        this.f1023i = z4;
        this.f1024j = dVar;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0024  */
    /* JADX WARN: Code duplicated, block: B:11:0x0027 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:12:0x0029  */
    /* JADX WARN: Code duplicated, block: B:13:0x002c  */
    /* JADX WARN: Code duplicated, block: B:20:0x004c  */
    /* JADX WARN: Code duplicated, block: B:23:0x0072  */
    /* JADX WARN: Code duplicated, block: B:24:0x0076  */
    /* JADX WARN: Code duplicated, block: B:29:0x0097  */
    /* JADX WARN: Code duplicated, block: B:8:0x001c  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        boolean z2;
        boolean z3;
        e1 e1Var;
        long j5;
        r0.p clearAndSetSemanticsElement;
        int iP;
        f0.v vVar;
        p1.n nVar;
        p1.h hVar;
        f0.p pVar = (f0.p) obj;
        if ((((Number) obj2).intValue() & 3) == 2) {
            f0.v vVar2 = (f0.v) pVar;
            if (vVar2.z()) {
                vVar2.Q();
            } else {
                z2 = this.f1021g;
                z3 = this.f1020f;
                e1Var = this.f1019e;
                if (!z2) {
                    j5 = e1Var.f820f;
                } else if (z3) {
                    j5 = e1Var.f815a;
                } else {
                    j5 = e1Var.f818d;
                }
                f0.g3 g3VarA = l.g.a(j5, m.d.h(100, 6, null), pVar);
                if (this.f1022h == null && (this.f1023i || z3)) {
                    AtomicInteger atomicInteger = w1.k.f6311a;
                    clearAndSetSemanticsElement = new ClearAndSetSemanticsElement();
                } else {
                    clearAndSetSemanticsElement = r0.m.f5680a;
                }
                n1.f0 f0VarE = r.o.e(r0.b.f5653d, false);
                iP = f0.l.p(pVar);
                vVar = (f0.v) pVar;
                f0.w1 w1VarL = vVar.l();
                r0.p pVarC = r0.a.c(pVar, clearAndSetSemanticsElement);
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(nVar);
                } else {
                    vVar.i0();
                }
                f0.l.x(p1.i.f4870e, pVar, f0VarE);
                f0.l.x(p1.i.f4869d, pVar, w1VarL);
                hVar = p1.i.f4871f;
                if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iP))) {
                    b.b.i(iP, vVar, iP, hVar);
                }
                f0.l.x(p1.i.f4868c, pVar, pVarC);
                f0.l.a(b0.f762a.a(new y0.u(((y0.u) g3VarA.getValue()).f6696a)), this.f1024j, pVar, 8);
                vVar.p(true);
            }
        } else {
            z2 = this.f1021g;
            z3 = this.f1020f;
            e1Var = this.f1019e;
            if (!z2) {
                j5 = e1Var.f820f;
            } else if (z3) {
                j5 = e1Var.f815a;
            } else {
                j5 = e1Var.f818d;
            }
            f0.g3 g3VarA2 = l.g.a(j5, m.d.h(100, 6, null), pVar);
            if (this.f1022h == null) {
                clearAndSetSemanticsElement = r0.m.f5680a;
            } else {
                clearAndSetSemanticsElement = r0.m.f5680a;
            }
            n1.f0 f0VarE2 = r.o.e(r0.b.f5653d, false);
            iP = f0.l.p(pVar);
            vVar = (f0.v) pVar;
            f0.w1 w1VarL2 = vVar.l();
            r0.p pVarC2 = r0.a.c(pVar, clearAndSetSemanticsElement);
            p1.j.f4892c.getClass();
            nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, pVar, f0VarE2);
            f0.l.x(p1.i.f4869d, pVar, w1VarL2);
            hVar = p1.i.f4871f;
            if (vVar.R) {
                b.b.i(iP, vVar, iP, hVar);
            } else {
                b.b.i(iP, vVar, iP, hVar);
            }
            f0.l.x(p1.i.f4868c, pVar, pVarC2);
            f0.l.a(b0.f762a.a(new y0.u(((y0.u) g3VarA2.getValue()).f6696a)), this.f1024j, pVar, 8);
            vVar.p(true);
        }
        return p3.n.f5026a;
    }
}
