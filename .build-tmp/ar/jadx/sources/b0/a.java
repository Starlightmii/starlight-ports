package b0;

import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.InputMethodManager;
import c0.k3;
import c0.z2;
import f0.p1;
import f0.s1;
import io.github.libxposed.service.IXposedService;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import m.s0;
import n1.y0;
import p.l0;
import p1.d0;
import p1.e0;
import p1.h0;
import p1.i0;
import p1.j0;
import q1.r0;
import v.c1;
import v.o0;
import y0.n0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f371e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f372f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i5, Object obj) {
        super(0);
        this.f371e = i5;
        this.f372f = obj;
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [a4.a, b4.j] */
    @Override // a4.a
    public final Object a() {
        boolean zI;
        Boolean boolB;
        switch (this.f371e) {
            case 0:
                s1 s1Var = ((b) this.f372f).f387m;
                s1Var.setValue(Boolean.valueOf(!((Boolean) s1Var.getValue()).booleanValue()));
                return p3.n.f5026a;
            case 1:
                p1.f.l((c) this.f372f);
                return p3.n.f5026a;
            case 2:
                c.d dVar = (c.d) this.f372f;
                dVar.getClass();
                ?? r1 = dVar.f708b;
                if (r1 != 0) {
                    r1.a();
                }
                return p3.n.f5026a;
            case 3:
                i4.h hVar = ((z2) this.f372f).f1490b;
                if (hVar.u()) {
                    hVar.resumeWith(k3.f984d);
                }
                return Boolean.TRUE;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                e1.c0 c0Var = (e1.c0) this.f372f;
                int i5 = c0Var.f1886k;
                p1 p1Var = c0Var.f1883h;
                if (i5 == p1Var.g()) {
                    p1Var.h(p1Var.g() + 1);
                }
                return p3.n.f5026a;
            case 5:
                Object systemService = ((View) ((a1.b) this.f372f).f23d).getContext().getSystemService("input_method");
                b4.l.c("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager", systemService);
                return (InputMethodManager) systemService;
            case 6:
                return new BaseInputConnection(((e2.a0) this.f372f).f1975a, false);
            case 7:
                return (i4.v) ((a1.b) this.f372f).f25f;
            case 8:
                return ((i1.e) this.f372f).y0();
            case 9:
                i2.b bVar = (i2.b) this.f372f;
                s1 s1Var2 = bVar.f2669f;
                if (((x0.f) s1Var2.getValue()).f6546a == 9205357640488583168L || x0.f.e(((x0.f) s1Var2.getValue()).f6546a)) {
                    return null;
                }
                return bVar.f2667d.b(((x0.f) s1Var2.getValue()).f6546a);
            case 10:
                return Long.valueOf(((s0) this.f372f).b());
            case 11:
                ((n.t) this.f372f).f4022x.a();
                return Boolean.TRUE;
            case 12:
                n.c0 c0Var2 = (n.c0) this.f372f;
                w0.h hVar2 = w0.h.f6240j;
                r0.o oVarE = c0Var2.f5681d;
                h0.e eVar = null;
                while (true) {
                    zI = false;
                    int i6 = 0;
                    zI = false;
                    zI = false;
                    if (oVarE == null) {
                        r0.o oVar = c0Var2.f5681d;
                        if (!oVar.f5693p) {
                            throw new IllegalStateException("visitChildren called on an unattached node");
                        }
                        h0.e eVar2 = new h0.e(new r0.o[16]);
                        r0.o oVar2 = oVar.f5686i;
                        if (oVar2 == null) {
                            p1.f.b(eVar2, oVar);
                        } else {
                            eVar2.b(oVar2);
                        }
                        while (eVar2.k()) {
                            r0.o oVarE2 = (r0.o) eVar2.m(eVar2.f2578f - 1);
                            if ((oVarE2.f5684g & 1024) == 0) {
                                p1.f.b(eVar2, oVarE2);
                            } else {
                                while (true) {
                                    if (oVarE2 == null) {
                                        continue;
                                    } else if ((oVarE2.f5683f & 1024) != 0) {
                                        h0.e eVar3 = null;
                                        while (true) {
                                            if (oVarE2 == null) {
                                                continue;
                                            } else if (oVarE2 instanceof w0.r) {
                                                w0.r rVar = (w0.r) oVarE2;
                                                if (rVar.y0().f6242a) {
                                                    boolB = w0.d.B(rVar);
                                                    if (boolB != null) {
                                                    }
                                                } else {
                                                    zI = w0.d.i(rVar, 7, hVar2);
                                                }
                                            } else {
                                                if ((oVarE2.f5683f & 1024) != 0 && (oVarE2 instanceof p1.m)) {
                                                    int i7 = 0;
                                                    for (r0.o oVar3 = ((p1.m) oVarE2).f4921r; oVar3 != null; oVar3 = oVar3.f5686i) {
                                                        if ((oVar3.f5683f & 1024) != 0) {
                                                            i7++;
                                                            if (i7 == 1) {
                                                                oVarE2 = oVar3;
                                                            } else {
                                                                if (eVar3 == null) {
                                                                    eVar3 = new h0.e(new r0.o[16]);
                                                                }
                                                                if (oVarE2 != null) {
                                                                    eVar3.b(oVarE2);
                                                                    oVarE2 = null;
                                                                }
                                                                eVar3.b(oVar3);
                                                            }
                                                        }
                                                    }
                                                    if (i7 == 1) {
                                                    }
                                                }
                                                oVarE2 = p1.f.e(eVar3);
                                            }
                                        }
                                    } else {
                                        oVarE2 = oVarE2.f5686i;
                                    }
                                }
                            }
                        }
                    } else if (oVarE instanceof w0.r) {
                        w0.r rVar2 = (w0.r) oVarE;
                        if (rVar2.y0().f6242a) {
                            boolB = w0.d.B(rVar2);
                            if (boolB != null) {
                            }
                        } else {
                            zI = w0.d.i(rVar2, 7, hVar2);
                        }
                    } else {
                        if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                            for (r0.o oVar4 = ((p1.m) oVarE).f4921r; oVar4 != null; oVar4 = oVar4.f5686i) {
                                if ((oVar4.f5683f & 1024) != 0) {
                                    i6++;
                                    if (i6 == 1) {
                                        oVarE = oVar4;
                                    } else {
                                        if (eVar == null) {
                                            eVar = new h0.e(new r0.o[16]);
                                        }
                                        if (oVarE != null) {
                                            eVar.b(oVarE);
                                            oVarE = null;
                                        }
                                        eVar.b(oVar4);
                                    }
                                }
                            }
                            if (i6 == 1) {
                            }
                        }
                        oVarE = p1.f.e(eVar);
                    }
                    return Boolean.valueOf(zI);
                }
                zI = boolB.booleanValue();
                return Boolean.valueOf(zI);
            case 13:
                n1.b0 b0VarA = ((y0) this.f372f).a();
                d0 d0Var = b0VarA.f4091d;
                if (b0VarA.f4103p != ((h0.b) d0Var.o()).f2570d.f2578f) {
                    Iterator it = b0VarA.f4095h.entrySet().iterator();
                    while (it.hasNext()) {
                        ((n1.t) ((Map.Entry) it.next()).getValue()).f4157d = true;
                    }
                    if (!d0Var.f4802z.f4896d) {
                        d0.Q(d0Var, false, 7);
                    }
                }
                return p3.n.f5026a;
            case 14:
                n2.p pVar = (n2.p) this.f372f;
                n1.p parentLayoutCoordinates = pVar.getParentLayoutCoordinates();
                if (parentLayoutCoordinates == null || !parentLayoutCoordinates.N()) {
                    parentLayoutCoordinates = null;
                }
                return Boolean.valueOf((parentLayoutCoordinates == null || pVar.m11getPopupContentSizebOM6tXw() == null) ? false : true);
            case 15:
                o1.d dVar2 = (o1.d) this.f372f;
                h0.e eVar4 = dVar2.f4372c;
                h0.e eVar5 = dVar2.f4371b;
                h0.e eVar6 = dVar2.f4374e;
                int i8 = 0;
                dVar2.f4375f = false;
                HashSet hashSet = new HashSet();
                h0.e eVar7 = dVar2.f4373d;
                int i9 = eVar7.f2578f;
                if (i9 > 0) {
                    Object[] objArr = eVar7.f2576d;
                    int i10 = 0;
                    do {
                        d0 d0Var2 = (d0) objArr[i10];
                        o1.g gVar = (o1.g) eVar6.f2576d[i10];
                        r0.o oVar5 = d0Var2.f4801y.f4972e;
                        if (oVar5.f5693p) {
                            o1.d.b(oVar5, gVar, hashSet);
                        }
                        i10++;
                    } while (i10 < i9);
                }
                eVar7.g();
                eVar6.g();
                int i11 = eVar5.f2578f;
                if (i11 > 0) {
                    Object[] objArr2 = eVar5.f2576d;
                    do {
                        p1.c cVar = (p1.c) objArr2[i8];
                        o1.g gVar2 = (o1.g) eVar4.f2576d[i8];
                        if (cVar.f5693p) {
                            o1.d.b(cVar, gVar2, hashSet);
                        }
                        i8++;
                    } while (i8 < i11);
                }
                eVar5.g();
                eVar4.g();
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    ((p1.c) it2.next()).A0();
                }
                return p3.n.f5026a;
            case 16:
                j0 j0Var = ((d0) this.f372f).f4802z;
                j0Var.f4910r.f4888y = true;
                h0 h0Var = j0Var.f4911s;
                if (h0Var != null) {
                    h0Var.f4861v = true;
                }
                return p3.n.f5026a;
            case 17:
                i0 i0Var = (i0) this.f372f;
                j0 j0Var2 = i0Var.I;
                d0 d0Var3 = j0Var2.f4893a;
                int i12 = 0;
                j0Var2.f4903k = 0;
                h0.e eVarU = j0Var2.f4893a.u();
                int i13 = eVarU.f2578f;
                if (i13 > 0) {
                    Object[] objArr3 = eVarU.f2576d;
                    int i14 = 0;
                    do {
                        i0 i0Var2 = ((d0) objArr3[i14]).f4802z.f4910r;
                        i0Var2.f4873j = i0Var2.f4874k;
                        i0Var2.f4874k = Integer.MAX_VALUE;
                        i0Var2.f4885v = false;
                        if (i0Var2.f4877n == 2) {
                            i0Var2.f4877n = 3;
                        }
                        i14++;
                    } while (i14 < i13);
                }
                h0.e eVarU2 = d0Var3.u();
                int i15 = eVarU2.f2578f;
                if (i15 > 0) {
                    Object[] objArr4 = eVarU2.f2576d;
                    int i16 = 0;
                    do {
                        ((d0) objArr4[i16]).f4802z.f4910r.f4886w.f4819d = false;
                        i16++;
                    } while (i16 < i15);
                }
                i0Var.x().u0().n();
                h0.e eVarU3 = d0Var3.u();
                int i17 = eVarU3.f2578f;
                if (i17 > 0) {
                    Object[] objArr5 = eVarU3.f2576d;
                    int i18 = 0;
                    do {
                        d0 d0Var4 = (d0) objArr5[i18];
                        if (d0Var4.f4802z.f4910r.f4873j != d0Var4.s()) {
                            d0Var3.I();
                            d0Var3.y();
                            if (d0Var4.s() == Integer.MAX_VALUE) {
                                d0Var4.f4802z.f4910r.q0();
                            }
                        }
                        i18++;
                    } while (i18 < i17);
                }
                h0.e eVarU4 = d0Var3.u();
                int i19 = eVarU4.f2578f;
                if (i19 > 0) {
                    Object[] objArr6 = eVarU4.f2576d;
                    do {
                        e0 e0Var = ((d0) objArr6[i12]).f4802z.f4910r.f4886w;
                        e0Var.f4820e = e0Var.f4819d;
                        i12++;
                    } while (i12 < i19);
                }
                return p3.n.f5026a;
            case 18:
                j0 j0Var3 = (j0) this.f372f;
                j0Var3.a().c(j0Var3.f4912t);
                return p3.n.f5026a;
            case 19:
                p1.y0 y0Var = ((p1.y0) this.f372f).f5000q;
                if (y0Var != null) {
                    y0Var.R0();
                }
                return p3.n.f5026a;
            case 20:
                a4.c cVar2 = (a4.c) this.f372f;
                n0 n0Var = p1.y0.H;
                cVar2.c(n0Var);
                n0Var.f6664r = n0Var.f6659m.c(n0Var.f6661o, n0Var.f6663q, n0Var.f6662p);
                return p3.n.f5026a;
            case 21:
                i4.y.b(((r0) this.f372f).f5334f, null);
                return p3.n.f5026a;
            case 22:
                ((q1.s0) this.f372f).f5358b = null;
                return p3.n.f5026a;
            case 23:
                t.c cVar3 = (t.c) this.f372f;
                cVar3.L.c(Boolean.valueOf(!cVar3.K));
                return p3.n.f5026a;
            case 24:
                ((o.j) this.f372f).f4340a.setValue(o.g.f4338a);
                return p3.n.f5026a;
            case 25:
                return ((o0) this.f372f).d();
            case 26:
                return new c1((l0) this.f372f, 0.0f);
            case 27:
                Object systemService2 = ((x.p) this.f372f).f6477b.getContext().getSystemService("input_method");
                b4.l.c("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager", systemService2);
                return (InputMethodManager) systemService2;
            case 28:
                return new BaseInputConnection(((x.u) this.f372f).f6501a, false);
            default:
                y.g gVar3 = (y.g) this.f372f;
                gVar3.A = null;
                p1.f.n(gVar3);
                p1.f.m(gVar3);
                p1.f.l(gVar3);
                return Boolean.TRUE;
        }
    }
}
