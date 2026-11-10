package c0;

import androidx.compose.ui.semantics.AppendedSemanticsElement;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m3 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ r0.p f1064e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ y0.q0 f1065f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ long f1066g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ float f1067h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ n.q f1068i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ float f1069j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ n0.d f1070k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m3(r0.p pVar, y0.q0 q0Var, long j5, float f5, n.q qVar, float f6, n0.d dVar) {
        super(2);
        this.f1064e = pVar;
        this.f1065f = q0Var;
        this.f1066g = j5;
        this.f1067h = f5;
        this.f1068i = qVar;
        this.f1069j = f6;
        this.f1070k = dVar;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        f0.p pVar = (f0.p) obj;
        int iIntValue = ((Number) obj2).intValue() & 3;
        p3.n nVar = p3.n.f5026a;
        if (iIntValue == 2) {
            f0.v vVar = (f0.v) pVar;
            if (vVar.z()) {
                vVar.Q();
                return nVar;
            }
        }
        long jC = o3.c(this.f1066g, this.f1067h, pVar);
        f0.v vVar2 = (f0.v) pVar;
        r0.p pVarB = o3.b(this.f1064e, this.f1065f, jC, this.f1068i, ((k2.b) vVar2.j(q1.j1.f5237f)).v(this.f1069j));
        k kVar = k.f961i;
        AtomicInteger atomicInteger = w1.k.f6311a;
        r0.p pVarA = j1.z.a(pVarB.e(new AppendedSemanticsElement(kVar, false)), nVar, new l3(2, null, 0));
        n1.f0 f0VarE = r.o.e(r0.b.f5653d, true);
        int iHashCode = Long.hashCode(vVar2.S);
        f0.w1 w1VarL = vVar2.l();
        r0.p pVarC = r0.a.c(vVar2, pVarA);
        p1.j.f4892c.getClass();
        p1.n nVar2 = p1.i.f4867b;
        vVar2.Y();
        if (vVar2.R) {
            vVar2.k(nVar2);
        } else {
            vVar2.i0();
        }
        f0.l.x(p1.i.f4870e, vVar2, f0VarE);
        f0.l.x(p1.i.f4869d, vVar2, w1VarL);
        p1.h hVar = p1.i.f4871f;
        if (vVar2.R || !b4.l.a(vVar2.K(), Integer.valueOf(iHashCode))) {
            b.b.i(iHashCode, vVar2, iHashCode, hVar);
        }
        f0.l.x(p1.i.f4868c, vVar2, pVarC);
        this.f1070k.invoke(vVar2, 0);
        vVar2.p(true);
        return nVar;
    }
}
