package c0;

import android.view.KeyEvent;
import io.github.libxposed.service.IXposedService;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t2 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1318e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f1319f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f1320g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t2(Object obj, int i5, Object obj2) {
        super(0);
        this.f1318e = i5;
        this.f1319f = obj;
        this.f1320g = obj2;
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00eb  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r2v18, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v36 */
    /* JADX WARN: Type inference failed for: r3v10, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r9v16 */
    @Override // a4.a
    public final Object a() {
        f0.e0 e0Var;
        int iZ;
        n1.l0 placementScope;
        w1.n nVar;
        p1.d0 d0Var;
        long jB;
        long j5;
        v.e1 e1VarD;
        v.o0 o0Var;
        y1.f fVar;
        int i5 = this.f1318e;
        int i6 = 0;
        p3.n nVar2 = p3.n.f5026a;
        Object obj = this.f1320g;
        Object obj2 = this.f1319f;
        switch (i5) {
            case 0:
                z2 z2Var = (z2) obj2;
                k0 k0Var = (k0) obj;
                if (!b4.l.a(z2Var, k0Var.f963a)) {
                    ArrayList arrayList = k0Var.f964b;
                    b4.l.e("<this>", arrayList);
                    int iZ2 = p3.j.z(arrayList);
                    if (iZ2 >= 0) {
                        int i7 = 0;
                        while (true) {
                            Object obj3 = arrayList.get(i6);
                            if (!b4.l.a(((j0) obj3).f940a, z2Var)) {
                                if (i7 != i6) {
                                    arrayList.set(i7, obj3);
                                }
                                i7++;
                            }
                            if (i6 != iZ2) {
                                i6++;
                            } else {
                                i6 = i7;
                            }
                        }
                    }
                    if (i6 < arrayList.size() && i6 <= (iZ = p3.j.z(arrayList))) {
                        while (true) {
                            arrayList.remove(iZ);
                            if (iZ != i6) {
                                iZ--;
                            }
                        }
                    }
                    f0.b2 b2Var = k0Var.f965c;
                    if (b2Var != null && (e0Var = b2Var.f2095a) != null) {
                        e0Var.r(b2Var, null);
                    }
                }
                return nVar2;
            case 1:
                ((k4.g) obj2).o(obj);
                return nVar2;
            case 2:
                ((b4.w) obj2).f697d = p1.f.h((n.d0) obj, n1.k0.f4127a);
                return nVar2;
            case 3:
                p1.t0 t0Var = ((p1.d0) obj2).f4801y;
                b4.w wVar = (b4.w) obj;
                if ((t0Var.f4972e.f5684g & 8) != 0) {
                    for (r0.o oVar = t0Var.f4971d; oVar != null; oVar = oVar.f5685h) {
                        if ((oVar.f5683f & 8) != 0) {
                            ?? E = oVar;
                            ?? eVar = 0;
                            while (E != 0) {
                                if (E instanceof p1.l1) {
                                    p1.l1 l1Var = (p1.l1) E;
                                    if (l1Var.Y()) {
                                        w1.i iVar = new w1.i();
                                        wVar.f697d = iVar;
                                        iVar.f6310f = true;
                                    }
                                    if (l1Var.c0()) {
                                        ((w1.i) wVar.f697d).f6309e = true;
                                    }
                                    l1Var.Z((w1.i) wVar.f697d);
                                } else if ((E.f5683f & 8) != 0 && (E instanceof p1.m)) {
                                    r0.o oVar2 = ((p1.m) E).f4921r;
                                    int i8 = 0;
                                    while (oVar2 != null) {
                                        if ((oVar2.f5683f & 8) != 0) {
                                            i8++;
                                            if (i8 == 1) {
                                                E = E;
                                                eVar = eVar;
                                                eVar = eVar;
                                                E = oVar2;
                                            } else {
                                                if (eVar == 0) {
                                                    eVar = new h0.e(new r0.o[16]);
                                                }
                                                if (E != 0) {
                                                    eVar.b(E);
                                                    E = 0;
                                                }
                                                eVar.b(oVar2);
                                            }
                                        } else {
                                            E = E;
                                            eVar = eVar;
                                        }
                                        oVar2 = oVar2.f5686i;
                                        E = E;
                                        eVar = eVar;
                                    }
                                    if (i8 == 1) {
                                        E = E;
                                        eVar = eVar;
                                    } else {
                                        E = E;
                                        eVar = eVar;
                                    }
                                }
                                E = p1.f.e(eVar);
                            }
                        }
                    }
                }
                return nVar2;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                p1.j0 j0Var = (p1.j0) obj2;
                p1.y0 y0Var = j0Var.a().f5000q;
                if (y0Var == null || (placementScope = y0Var.f4925l) == null) {
                    placementScope = ((q1.u) p1.f.u(j0Var.f4893a)).getPlacementScope();
                }
                p1.i0 i0Var = (p1.i0) obj;
                a4.c cVar = i0Var.D;
                if (cVar == null) {
                    p1.y0 y0VarA = j0Var.a();
                    long j6 = i0Var.E;
                    float f5 = i0Var.F;
                    placementScope.getClass();
                    n1.l0.a(placementScope, y0VarA);
                    y0VarA.k0(k2.h.c(j6, y0VarA.f4134h), f5, null);
                } else {
                    p1.y0 y0VarA2 = j0Var.a();
                    long j7 = i0Var.E;
                    float f6 = i0Var.F;
                    placementScope.getClass();
                    n1.l0.a(placementScope, y0VarA2);
                    y0VarA2.k0(k2.h.c(j7, y0VarA2.f4134h), f6, cVar);
                }
                return nVar2;
            case 5:
                a4.c cVarO = ((p1.i1) obj2).f4890d.o();
                if (cVarO != null) {
                    cVarO.c(new p1.l0((p1.m0) obj));
                }
                return nVar2;
            case 6:
                return Boolean.valueOf(super/*android.view.ViewGroup*/.dispatchKeyEvent((KeyEvent) obj));
            case 7:
                q1.f0 f0Var = (q1.f0) obj;
                q1.h2 h2Var = (q1.h2) obj2;
                w1.g gVar = h2Var.f5222h;
                w1.g gVar2 = h2Var.f5223i;
                Float f7 = h2Var.f5220f;
                Float f8 = h2Var.f5221g;
                float fFloatValue = (gVar == null || f7 == null) ? 0.0f : ((Number) gVar.f6280a.a()).floatValue() - f7.floatValue();
                float fFloatValue2 = (gVar2 == null || f8 == null) ? 0.0f : ((Number) gVar2.f6280a.a()).floatValue() - f8.floatValue();
                if (fFloatValue != 0.0f || fFloatValue2 != 0.0f) {
                    int iX = f0Var.x(h2Var.f5218d);
                    q1.j2 j2Var = (q1.j2) f0Var.m().d(f0Var.f5188n);
                    if (j2Var != null) {
                        try {
                            x2.h hVar = f0Var.f5189o;
                            if (hVar != null) {
                                hVar.f6563a.setBoundsInScreen(f0Var.d(j2Var));
                            }
                            break;
                        } catch (IllegalStateException unused) {
                        }
                    }
                    f0Var.f5178d.invalidate();
                    q1.j2 j2Var2 = (q1.j2) f0Var.m().d(iX);
                    if (j2Var2 != null && (nVar = j2Var2.f5252a) != null && (d0Var = nVar.f6317c) != null) {
                        if (gVar != null) {
                            f0Var.f5191q.g(iX, gVar);
                        }
                        if (gVar2 != null) {
                            f0Var.f5192r.g(iX, gVar2);
                        }
                        f0Var.s(d0Var);
                    }
                }
                if (gVar != null) {
                    h2Var.f5220f = (Float) gVar.f6280a.a();
                }
                if (gVar2 != null) {
                    h2Var.f5221g = (Float) gVar2.f6280a.a();
                }
                return nVar2;
            case 8:
                x0.d dVar = (x0.d) obj2;
                if (dVar != null) {
                    return dVar;
                }
                p1.y0 y0Var2 = (p1.y0) obj;
                if (!y0Var2.L0().f5693p) {
                    y0Var2 = null;
                }
                if (y0Var2 != null) {
                    return p3.p.b(0L, a.a.O(y0Var2.f4132f));
                }
                return null;
            case 9:
                e2.x xVar = (e2.x) obj2;
                f0.i1 i1Var = (f0.i1) obj;
                if (!y1.g0.a(xVar.f2063b, ((e2.x) i1Var.getValue()).f2063b) || !b4.l.a(xVar.f2064c, ((e2.x) i1Var.getValue()).f2064c)) {
                    i1Var.setValue(xVar);
                }
                return nVar2;
            case 10:
                ((v.o0) obj2).f6026u.c(new e2.m(((e2.n) obj).f2043e));
                return Boolean.TRUE;
            case 11:
                v.o0 o0Var2 = (v.o0) obj2;
                w0.n nVar3 = (w0.n) obj;
                if (o0Var2.b()) {
                    q1.k2 k2Var = o0Var2.f6008c;
                    if (k2Var != null) {
                        ((q1.k1) k2Var).b();
                    }
                } else {
                    nVar3.a(w0.h.f6239i);
                }
                return Boolean.TRUE;
            case 12:
                ((v0.b) obj2).f6164s.c((v0.c) obj);
                return nVar2;
            case 13:
                ((b4.w) obj2).f697d = ((w0.r) obj).y0();
                return nVar2;
            default:
                z.e0 e0Var2 = (z.e0) obj2;
                long j8 = ((k2.j) ((f0.i1) obj).getValue()).f3415a;
                x0.c cVarG = e0Var2.g();
                if (cVarG != null) {
                    long j9 = cVarG.f6532a;
                    v.o0 o0Var3 = e0Var2.f6930d;
                    y1.f fVar2 = o0Var3 != null ? o0Var3.f6006a.f6098a : null;
                    if (fVar2 == null || fVar2.f6759d.length() == 0) {
                        jB = 9205357640488583168L;
                    } else {
                        v.e0 e0Var3 = (v.e0) e0Var2.f6941o.getValue();
                        int i9 = e0Var3 == null ? -1 : z.g0.f6959a[e0Var3.ordinal()];
                        if (i9 == -1) {
                            jB = 9205357640488583168L;
                        } else {
                            if (i9 == 1 || i9 == 2) {
                                long j10 = e0Var2.j().f2063b;
                                int i10 = y1.g0.f6769c;
                                j5 = j10 >> 32;
                            } else {
                                if (i9 != 3) {
                                    throw new i4.p();
                                }
                                long j11 = e0Var2.j().f2063b;
                                int i11 = y1.g0.f6769c;
                                j5 = j11 & 4294967295L;
                            }
                            int i12 = (int) j5;
                            v.o0 o0Var4 = e0Var2.f6930d;
                            if (o0Var4 == null || (e1VarD = o0Var4.d()) == null || (o0Var = e0Var2.f6930d) == null || (fVar = o0Var.f6006a.f6098a) == null) {
                                jB = 9205357640488583168L;
                            } else {
                                int iM = d4.a.m(e0Var2.f6928b.b(i12), 0, fVar.f6759d.length());
                                float fD = x0.c.d(e1VarD.d(j9));
                                y1.e0 e0Var4 = e1VarD.f5899a;
                                int iE = e0Var4.e(iM);
                                y1.m mVar = e0Var4.f6754b;
                                float f9 = e0Var4.f(iE);
                                float fG = e0Var4.g(iE);
                                float fL = d4.a.l(fD, Math.min(f9, fG), Math.max(f9, fG));
                                if (k2.j.a(j8, 0L) || Math.abs(fD - fL) <= ((int) (j8 >> 32)) / 2) {
                                    float fD2 = mVar.d(iE);
                                    jB = p3.j.b(fL, ((mVar.b(iE) - fD2) / 2) + fD2);
                                } else {
                                    jB = 9205357640488583168L;
                                }
                            }
                        }
                    }
                } else {
                    jB = 9205357640488583168L;
                }
                return new x0.c(jB);
        }
    }
}
