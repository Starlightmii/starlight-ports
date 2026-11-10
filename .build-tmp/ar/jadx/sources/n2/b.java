package n2;

import android.graphics.Paint;
import e2.x;
import p1.f0;
import q1.j1;
import q1.o2;
import v.e1;
import v.o0;
import v.v0;
import x.u;
import y0.l0;
import y0.s0;
import y1.g0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4198e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f4199f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f4200g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f4201h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f4202i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Object f4203j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i5) {
        super(1);
        this.f4198e = i5;
        this.f4199f = obj;
        this.f4200g = obj2;
        this.f4201h = obj3;
        this.f4202i = obj4;
        this.f4203j = obj5;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        int i5 = this.f4198e;
        p3.n nVar = p3.n.f5026a;
        Object obj2 = this.f4203j;
        Object obj3 = this.f4202i;
        Object obj4 = this.f4201h;
        Object obj5 = this.f4200g;
        Object obj6 = this.f4199f;
        switch (i5) {
            case 0:
                p pVar = (p) obj6;
                pVar.f4241q.addView(pVar, pVar.f4242r);
                pVar.i((a4.a) obj5, (t) obj4, (String) obj3, (k2.k) obj2);
                return new c.a(1, pVar);
            case 1:
                f0 f0Var = (f0) obj;
                f0Var.c();
                a1.c cVar = f0Var.f4828d;
                float fG = ((x.j) obj6).f6472b.g();
                if (fG == 0.0f) {
                    return nVar;
                }
                long j5 = ((x) obj4).f2063b;
                int i6 = g0.f6769c;
                int iB = ((e2.r) obj5).b((int) (j5 >> 32));
                e1 e1VarD = ((o0) obj3).d();
                x0.d dVarC = e1VarD != null ? e1VarD.f5899a.c(iB) : new x0.d(0.0f, 0.0f, 0.0f, 0.0f);
                float fV = f0Var.v(v0.f6117a);
                float f5 = fV / 2;
                float f6 = dVarC.f6534a + f5;
                float fD = x0.f.d(cVar.b()) - f5;
                if (f6 > fD) {
                    f6 = fD;
                }
                if (f6 >= f5) {
                    f5 = f6;
                }
                long jB = p3.j.b(f5, dVarC.f6535b);
                long jB2 = p3.j.b(f5, dVarC.f6537d);
                s0 s0Var = (s0) obj2;
                y0.r rVar = cVar.f26d.f20c;
                y0.h hVarG = cVar.f29g;
                if (hVarG == null) {
                    hVarG = l0.g();
                    hVarG.l(1);
                    cVar.f29g = hVarG;
                }
                Paint paint = hVarG.f6629a;
                s0Var.a(fG, cVar.b(), hVarG);
                if (!b4.l.a(hVarG.f6632d, null)) {
                    hVarG.f(null);
                }
                if (hVarG.f6630b != 3) {
                    hVarG.d(3);
                }
                if (paint.getStrokeWidth() != fV) {
                    hVarG.k(fV);
                }
                if (paint.getStrokeMiter() != 4.0f) {
                    paint.setStrokeMiter(4.0f);
                }
                if (hVarG.a() != 0) {
                    hVarG.i(0);
                }
                if (hVarG.b() != 0) {
                    hVarG.j(0);
                }
                if (!paint.isFilterBitmap()) {
                    hVarG.g(1);
                }
                rVar.o(jB, jB2, hVarG);
                return nVar;
            default:
                u uVar = (u) obj;
                x.q qVar = ((x.c) obj5).f6463a;
                uVar.f6508h = (x) obj6;
                uVar.f6509i = (e2.n) obj4;
                uVar.f6503c = (c.b) obj3;
                uVar.f6504d = (a4.c) obj2;
                uVar.f6505e = qVar != null ? qVar.f6479r : null;
                uVar.f6506f = qVar != null ? qVar.f6480s : null;
                uVar.f6507g = qVar != null ? (o2) p1.f.h(qVar, j1.f5248q) : null;
                return nVar;
        }
    }
}
