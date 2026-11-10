package c0;

import android.graphics.RectF;
import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j1 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f942e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f943f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j1(int i5, int i6, Object obj) {
        super(2);
        this.f942e = i6;
        this.f943f = obj;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j1(int i5, Object obj) {
        super(2);
        this.f942e = i5;
        this.f943f = obj;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0131  */
    /* JADX WARN: Code duplicated, block: B:59:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:67:0x01e7  */
    /* JADX WARN: Type inference failed for: r3v19, types: [a4.f, b4.m, java.lang.Object] */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        int i5 = this.f942e;
        r0.m mVar = r0.m.f5680a;
        int i6 = 3;
        boolean zF = true;
        p3.n nVar = p3.n.f5026a;
        Object obj3 = this.f943f;
        switch (i5) {
            case 0:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar = (f0.v) pVar;
                    if (vVar.z()) {
                        vVar.Q();
                    } else {
                        r0.p pVarC = androidx.compose.ui.layout.a.c(mVar, "indicatorRipple");
                        float f5 = e0.j.f1736a;
                        r.o.a(androidx.compose.foundation.d.a(q.f.j(pVarC, s2.a(pVar, 5)), (d0.h) obj3, i2.a(false, 0.0f, 0L, pVar, 0, 7)), pVar, 0);
                    }
                } else {
                    r0.p pVarC2 = androidx.compose.ui.layout.a.c(mVar, "indicatorRipple");
                    float f6 = e0.j.f1736a;
                    r.o.a(androidx.compose.foundation.d.a(q.f.j(pVarC2, s2.a(pVar, 5)), (d0.h) obj3, i2.a(false, 0.0f, 0L, pVar, 0, 7)), pVar, 0);
                }
                return nVar;
            case 1:
                f0.p pVar2 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar2 = (f0.v) pVar2;
                    if (vVar2.z()) {
                        vVar2.Q();
                    } else {
                        n4.a(y1.h0.a(t4.a(pVar2, e0.l.f1773e), 0L, 0L, null, null, 0L, 0L, null, 16744447), (a4.e) obj3, pVar2, 0);
                    }
                } else {
                    n4.a(y1.h0.a(t4.a(pVar2, e0.l.f1773e), 0L, 0L, null, null, 0L, 0L, null, 16744447), (a4.e) obj3, pVar2, 0);
                }
                return nVar;
            case 2:
                ((Number) obj2).intValue();
                ((n2.p) obj3).a((f0.p) obj, f0.l.z(1));
                return nVar;
            case 3:
                long j5 = ((x0.c) obj2).f6532a;
                ((v.q0) obj3).f6054f.c(((j1.u) obj).f3276c);
                return nVar;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                y0.r rVar = (y0.r) obj;
                b1.b bVar = (b1.b) obj2;
                p1.y0 y0Var = (p1.y0) obj3;
                p1.d0 d0Var = y0Var.f4998o;
                if (d0Var.E()) {
                    p1.g1 snapshotObserver = ((q1.u) p1.f.u(d0Var)).getSnapshotObserver();
                    y0.n0 n0Var = p1.y0.H;
                    snapshotObserver.a(y0Var, p1.e.f4805h, new n.i(y0Var, rVar, bVar, i6));
                    y0Var.F = false;
                } else {
                    y0Var.F = true;
                }
                return nVar;
            case 5:
                f0.p pVar3 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar3 = (f0.v) pVar3;
                    if (vVar3.z()) {
                        vVar3.Q();
                    } else {
                        ((q1.a) obj3).a(pVar3, 0);
                    }
                } else {
                    ((q1.a) obj3).a(pVar3, 0);
                }
                return nVar;
            case 6:
                ((Number) obj2).intValue();
                ((q1.i1) obj3).a((f0.p) obj, f0.l.z(1));
                return nVar;
            case 7:
                ((Number) obj2).intValue();
                r.o.a((r0.p) obj3, (f0.p) obj, f0.l.z(1));
                return nVar;
            case 8:
                return new k2.h(h1.c.d(0, ((r0.g) obj3).a(0, (int) (((k2.j) obj).f3415a & 4294967295L))));
            case 9:
                return new k2.h(((r0.h) obj3).a(0L, ((k2.j) obj).f3415a, (k2.k) obj2));
            case 10:
                r0.p pVar4 = (r0.p) obj;
                r0.p pVarB = (r0.n) obj2;
                f0.p pVar5 = (f0.p) obj3;
                if (pVarB instanceof r0.k) {
                    ?? r4 = ((r0.k) pVarB).f5678c;
                    b4.z.c(3, r4);
                    pVarB = r0.a.b(pVar5, (r0.p) r4.b(mVar, pVar5, 0));
                }
                return pVar4.e(pVarB);
            case 11:
                ((Number) obj2).intValue();
                v.l0.e((z.e0) obj3, (f0.p) obj, f0.l.z(1));
                return nVar;
            case 12:
                ((v.u0) obj3).e(((x0.c) obj2).f6532a);
                return nVar;
            default:
                x0.d dVarY = y0.l0.y((RectF) obj);
                x0.d dVarY2 = y0.l0.y((RectF) obj2);
                switch (((e2.f0) obj3).f2021d) {
                    case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                        zF = dVarY.f(dVarY2);
                        break;
                    default:
                        long jA = dVarY.a();
                        dVarY2.getClass();
                        if (x0.c.d(jA) < dVarY2.f6534a || x0.c.d(jA) >= dVarY2.f6536c || x0.c.e(jA) < dVarY2.f6535b || x0.c.e(jA) >= dVarY2.f6537d) {
                            zF = false;
                        }
                        break;
                }
                return Boolean.valueOf(zF);
        }
    }
}
