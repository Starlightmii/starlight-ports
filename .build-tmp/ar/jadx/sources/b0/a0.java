package b0;

import java.util.ArrayList;
import m.z0;
import p1.d0;
import p1.f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f373a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f374b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f375c = m.d.a(0.0f);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f376d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f377e;

    /* JADX WARN: Multi-variable type inference failed */
    public a0(a4.a aVar, boolean z2) {
        this.f373a = z2;
        this.f374b = (b4.m) aVar;
    }

    public void a(f0 f0Var, float f5, long j5) {
        a1.c cVar = f0Var.f4828d;
        float fFloatValue = ((Number) ((m.c) this.f375c).c()).floatValue();
        if (fFloatValue > 0.0f) {
            long jB = y0.u.b(fFloatValue, j5);
            if (!this.f373a) {
                a1.f.C(f0Var, jB, f5, 0L, 124);
                return;
            }
            float fD = x0.f.d(cVar.b());
            float fB = x0.f.b(cVar.b());
            a1.b bVar = cVar.f27e;
            long jT = bVar.t();
            bVar.r().p();
            ((a1.b) ((a1.d) bVar.f23d).f31e).r().i(0.0f, 0.0f, fD, fB, 1);
            a1.f.C(f0Var, jB, f5, 0L, 124);
            bVar.r().m();
            bVar.D(jT);
        }
    }

    /* JADX WARN: Type inference failed for: r5v4, types: [a4.a, b4.m] */
    public void b(q.i iVar, i4.v vVar) {
        float f5;
        ArrayList arrayList = (ArrayList) this.f376d;
        boolean z2 = iVar instanceof q.g;
        if (z2) {
            arrayList.add(iVar);
        } else if (iVar instanceof q.h) {
            arrayList.remove(((q.h) iVar).f5081a);
        } else if (iVar instanceof q.d) {
            arrayList.add(iVar);
        } else if (iVar instanceof q.e) {
            arrayList.remove(((q.e) iVar).f5080a);
        } else if (iVar instanceof q.b) {
            arrayList.add(iVar);
        } else if (iVar instanceof q.c) {
            arrayList.remove(((q.c) iVar).f5079a);
        } else if (!(iVar instanceof q.a)) {
            return;
        } else {
            arrayList.remove(((q.a) iVar).f5078a);
        }
        q.i iVar2 = (q.i) q3.l.c0(arrayList);
        if (b4.l.a((q.i) this.f377e, iVar2)) {
            return;
        }
        t3.c cVar = null;
        if (iVar2 != null) {
            i iVar3 = (i) ((b4.m) this.f374b).a();
            if (z2) {
                f5 = iVar3.f410c;
            } else if (iVar instanceof q.d) {
                f5 = iVar3.f409b;
            } else {
                f5 = iVar instanceof q.b ? iVar3.f408a : 0.0f;
            }
            z0 z0Var = s.f448a;
            if (!(iVar2 instanceof q.g) && ((iVar2 instanceof q.d) || (iVar2 instanceof q.b))) {
                z0Var = new z0(45, m.w.f3799c, 2);
            }
            i4.y.n(vVar, null, new z(this, f5, z0Var, null), 3);
        } else {
            q.i iVar4 = (q.i) this.f377e;
            z0 z0Var2 = s.f448a;
            if (!(iVar4 instanceof q.g) && !(iVar4 instanceof q.d) && (iVar4 instanceof q.b)) {
                z0Var2 = new z0(150, m.w.f3799c, 2);
            }
            i4.y.n(vVar, null, new t(this, z0Var2, cVar, 1), 3);
        }
        this.f377e = iVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int c(a2.h hVar, q1.u uVar, boolean z2) {
        int i5;
        Object[] objArr;
        int i6;
        a1.b bVar = (a1.b) this.f375c;
        p1.r rVar = (p1.r) this.f377e;
        if (this.f373a) {
            return 0;
        }
        try {
            this.f373a = true;
            a2.h hVarU = ((a1.d) this.f376d).u(hVar, uVar);
            j.m mVar = (j.m) hVarU.f49e;
            int iC = mVar.c();
            while (true) {
                if (i5 >= iC) {
                    objArr = true;
                    break;
                }
                j1.u uVar2 = (j1.u) mVar.d(i5);
                i5 = (uVar2.f3277d || uVar2.f3281h) ? 0 : i5 + 1;
                objArr = false;
                break;
            }
            int iC2 = mVar.c();
            for (int i7 = 0; i7 < iC2; i7++) {
                j1.u uVar3 = (j1.u) mVar.d(i7);
                if (objArr != false || j1.s.a(uVar3)) {
                    ((d0) this.f374b).v(uVar3.f3276c, (p1.r) this.f377e, uVar3.f3282i == 1, true);
                    if (!rVar.isEmpty()) {
                        bVar.d(uVar3.f3274a, rVar, j1.s.a(uVar3));
                        rVar.clear();
                    }
                }
            }
            ((j1.h) bVar.f24e).d();
            boolean zL = bVar.l(hVarU, z2);
            int iC3 = mVar.c();
            for (int i8 = 0; i8 < iC3; i8++) {
                j1.u uVar4 = (j1.u) mVar.d(i8);
                if (!x0.c.b(j1.s.f(uVar4, true), 0L) && uVar4.b()) {
                    i6 = 2;
                    return (zL ? 1 : 0) | i6;
                }
            }
            i6 = 0;
            return (zL ? 1 : 0) | i6;
        } finally {
            this.f373a = false;
        }
    }

    public void d() {
        if (this.f373a) {
            return;
        }
        j.m mVar = (j.m) ((a1.d) this.f376d).f31e;
        int i5 = mVar.f3121g;
        Object[] objArr = mVar.f3120f;
        int i6 = 0;
        for (int i7 = 0; i7 < i5; i7++) {
            objArr[i7] = null;
        }
        mVar.f3121g = 0;
        mVar.f3118d = false;
        a1.b bVar = (a1.b) this.f375c;
        h0.e eVar = ((j1.h) bVar.f24e).f3256a;
        int i8 = eVar.f2578f;
        if (i8 > 0) {
            Object[] objArr2 = eVar.f2576d;
            do {
                ((j1.g) objArr2[i6]).f();
                i6++;
            } while (i6 < i8);
        }
        ((j1.h) bVar.f24e).f3256a.g();
    }
}
