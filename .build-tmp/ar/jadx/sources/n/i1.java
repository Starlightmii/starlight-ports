package n;

import io.github.libxposed.service.IXposedService;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i1 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3932e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f3933f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f3934g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f3935h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i1(int i5, v.x0 x0Var, b4.s sVar) {
        super(1);
        this.f3933f = i5;
        this.f3934g = x0Var;
        this.f3935h = sVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i1(j1 j1Var, int i5, n1.m0 m0Var) {
        super(1);
        this.f3934g = j1Var;
        this.f3933f = i5;
        this.f3935h = m0Var;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // a4.c
    public final Object c(Object obj) {
        Integer numD;
        Integer numC;
        Integer numC2;
        Integer numD2;
        y1.e0 e0Var;
        y1.e0 e0Var2;
        v.e1 e1Var;
        v.e1 e1Var2;
        y1.e0 e0Var3;
        y1.e0 e0Var4;
        v.e1 e1Var3;
        v.e1 e1Var4;
        Integer numC3;
        Integer numD3;
        Integer numD4;
        Integer numC4;
        a2.h hVar;
        int i5 = this.f3932e;
        p3.n nVar = p3.n.f5026a;
        Object obj2 = this.f3935h;
        int i6 = this.f3933f;
        Object obj3 = this.f3934g;
        switch (i5) {
            case 0:
                n1.l0 l0Var = (n1.l0) obj;
                j1 j1Var = (j1) obj3;
                int i7 = -d4.a.m(j1Var.f3944q.f3920a.g(), 0, i6);
                boolean z2 = j1Var.f3945r;
                int i8 = z2 ? 0 : i7;
                if (!z2) {
                    i7 = 0;
                }
                l0Var.f4129a = true;
                n1.l0.g(l0Var, (n1.m0) obj2, i8, i7);
                l0Var.f4129a = false;
                break;
            default:
                z.b0 b0Var = (z.b0) obj;
                v.x0 x0Var = (v.x0) obj3;
                int iC = m.h.c(i6);
                int i9 = 25;
                e2.x xVar = null;
                switch (iC) {
                    case 0:
                        b0Var.f6903e.f6971a = null;
                        if (b0Var.f6905g.f6759d.length() > 0) {
                            if (y1.g0.b(b0Var.f6904f)) {
                                b0Var.h();
                            } else if (!b0Var.e()) {
                                int iD = y1.g0.d(b0Var.f6904f);
                                b0Var.o(iD, iD);
                            } else {
                                int iE = y1.g0.e(b0Var.f6904f);
                                b0Var.o(iE, iE);
                            }
                        }
                        break;
                    case 1:
                        b0Var.f6903e.f6971a = null;
                        if (b0Var.f6905g.f6759d.length() > 0) {
                            if (y1.g0.b(b0Var.f6904f)) {
                                b0Var.k();
                            } else if (!b0Var.e()) {
                                int iE2 = y1.g0.e(b0Var.f6904f);
                                b0Var.o(iE2, iE2);
                            } else {
                                int iD2 = y1.g0.d(b0Var.f6904f);
                                b0Var.o(iD2, iD2);
                            }
                        }
                        break;
                    case 2:
                        z.j0 j0Var = b0Var.f6903e;
                        j0Var.f6971a = null;
                        y1.f fVar = b0Var.f6905g;
                        String str = fVar.f6759d;
                        String str2 = fVar.f6759d;
                        if (str.length() > 0) {
                            if (!b0Var.e()) {
                                j0Var.f6971a = null;
                                if (str2.length() > 0 && (numD = b0Var.d()) != null) {
                                    int iIntValue = numD.intValue();
                                    b0Var.o(iIntValue, iIntValue);
                                }
                            } else {
                                j0Var.f6971a = null;
                                if (str2.length() > 0 && (numC = b0Var.c()) != null) {
                                    int iIntValue2 = numC.intValue();
                                    b0Var.o(iIntValue2, iIntValue2);
                                }
                            }
                        }
                        break;
                    case 3:
                        z.j0 j0Var2 = b0Var.f6903e;
                        j0Var2.f6971a = null;
                        y1.f fVar2 = b0Var.f6905g;
                        String str3 = fVar2.f6759d;
                        String str4 = fVar2.f6759d;
                        if (str3.length() > 0) {
                            if (!b0Var.e()) {
                                j0Var2.f6971a = null;
                                if (str4.length() > 0 && (numC2 = b0Var.c()) != null) {
                                    int iIntValue3 = numC2.intValue();
                                    b0Var.o(iIntValue3, iIntValue3);
                                }
                            } else {
                                j0Var2.f6971a = null;
                                if (str4.length() > 0 && (numD2 = b0Var.d()) != null) {
                                    int iIntValue4 = numD2.intValue();
                                    b0Var.o(iIntValue4, iIntValue4);
                                }
                            }
                        }
                        break;
                    case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                        b0Var.i();
                        break;
                    case 5:
                        b0Var.j();
                        break;
                    case 6:
                        b0Var.m();
                        break;
                    case 7:
                        b0Var.l();
                        break;
                    case 8:
                        b0Var.f6903e.f6971a = null;
                        if (b0Var.f6905g.f6759d.length() > 0) {
                            if (!b0Var.e()) {
                                b0Var.l();
                            } else {
                                b0Var.m();
                            }
                        }
                        break;
                    case 9:
                        b0Var.f6903e.f6971a = null;
                        if (b0Var.f6905g.f6759d.length() > 0) {
                            if (!b0Var.e()) {
                                b0Var.m();
                            } else {
                                b0Var.l();
                            }
                        }
                        break;
                    case 10:
                        if (b0Var.f6905g.f6759d.length() > 0 && (e0Var = b0Var.f6901c) != null) {
                            int iF = b0Var.f(e0Var, -1);
                            b0Var.o(iF, iF);
                        }
                        break;
                    case 11:
                        if (b0Var.f6905g.f6759d.length() > 0 && (e0Var2 = b0Var.f6901c) != null) {
                            int iF2 = b0Var.f(e0Var2, 1);
                            b0Var.o(iF2, iF2);
                        }
                        break;
                    case 12:
                        if (b0Var.f6905g.f6759d.length() > 0 && (e1Var = b0Var.f6907i) != null) {
                            int iG = b0Var.g(e1Var, -1);
                            b0Var.o(iG, iG);
                        }
                        break;
                    case 13:
                        if (b0Var.f6905g.f6759d.length() > 0 && (e1Var2 = b0Var.f6907i) != null) {
                            int iG2 = b0Var.g(e1Var2, 1);
                            b0Var.o(iG2, iG2);
                        }
                        break;
                    case 14:
                        b0Var.f6903e.f6971a = null;
                        if (b0Var.f6905g.f6759d.length() > 0) {
                            b0Var.o(0, 0);
                        }
                        break;
                    case 15:
                        b0Var.f6903e.f6971a = null;
                        y1.f fVar3 = b0Var.f6905g;
                        if (fVar3.f6759d.length() > 0) {
                            int length = fVar3.f6759d.length();
                            b0Var.o(length, length);
                        }
                        break;
                    case 16:
                        x0Var.f6132b.b(false);
                        break;
                    case 17:
                        x0Var.f6132b.l();
                        break;
                    case 18:
                        x0Var.f6132b.d();
                        break;
                    case 19:
                        List listA = b0Var.a(v.e.f5887j);
                        if (listA != null) {
                            x0Var.a(listA);
                        }
                        break;
                    case 20:
                        List listA2 = b0Var.a(v.e.f5888k);
                        if (listA2 != null) {
                            x0Var.a(listA2);
                        }
                        break;
                    case 21:
                        List listA3 = b0Var.a(v.e.f5889l);
                        if (listA3 != null) {
                            x0Var.a(listA3);
                        }
                        break;
                    case 22:
                        List listA4 = b0Var.a(v.e.f5890m);
                        if (listA4 != null) {
                            x0Var.a(listA4);
                        }
                        break;
                    case 23:
                        List listA5 = b0Var.a(v.e.f5891n);
                        if (listA5 != null) {
                            x0Var.a(listA5);
                        }
                        break;
                    case 24:
                        List listA6 = b0Var.a(v.e.f5892o);
                        if (listA6 != null) {
                            x0Var.a(listA6);
                        }
                        break;
                    case 25:
                        b0Var.f6903e.f6971a = null;
                        y1.f fVar4 = b0Var.f6905g;
                        if (fVar4.f6759d.length() > 0) {
                            b0Var.o(0, fVar4.f6759d.length());
                        }
                        break;
                    case 26:
                        b0Var.h();
                        b0Var.n();
                        break;
                    case 27:
                        b0Var.k();
                        b0Var.n();
                        break;
                    case 28:
                        if (b0Var.f6905g.f6759d.length() > 0 && (e0Var3 = b0Var.f6901c) != null) {
                            int iF3 = b0Var.f(e0Var3, -1);
                            b0Var.o(iF3, iF3);
                        }
                        b0Var.n();
                        break;
                    case 29:
                        if (b0Var.f6905g.f6759d.length() > 0 && (e0Var4 = b0Var.f6901c) != null) {
                            int iF4 = b0Var.f(e0Var4, 1);
                            b0Var.o(iF4, iF4);
                        }
                        b0Var.n();
                        break;
                    case 30:
                        if (b0Var.f6905g.f6759d.length() > 0 && (e1Var3 = b0Var.f6907i) != null) {
                            int iG3 = b0Var.g(e1Var3, -1);
                            b0Var.o(iG3, iG3);
                        }
                        b0Var.n();
                        break;
                    case 31:
                        if (b0Var.f6905g.f6759d.length() > 0 && (e1Var4 = b0Var.f6907i) != null) {
                            int iG4 = b0Var.g(e1Var4, 1);
                            b0Var.o(iG4, iG4);
                        }
                        b0Var.n();
                        break;
                    case 32:
                        b0Var.f6903e.f6971a = null;
                        if (b0Var.f6905g.f6759d.length() > 0) {
                            b0Var.o(0, 0);
                        }
                        b0Var.n();
                        break;
                    case 33:
                        b0Var.f6903e.f6971a = null;
                        y1.f fVar5 = b0Var.f6905g;
                        if (fVar5.f6759d.length() > 0) {
                            int length2 = fVar5.f6759d.length();
                            b0Var.o(length2, length2);
                        }
                        b0Var.n();
                        break;
                    case 34:
                        z.j0 j0Var3 = b0Var.f6903e;
                        j0Var3.f6971a = null;
                        y1.f fVar6 = b0Var.f6905g;
                        String str5 = fVar6.f6759d;
                        String str6 = fVar6.f6759d;
                        if (str5.length() > 0) {
                            if (b0Var.e()) {
                                j0Var3.f6971a = null;
                                if (str6.length() > 0 && (numD3 = b0Var.d()) != null) {
                                    int iIntValue5 = numD3.intValue();
                                    b0Var.o(iIntValue5, iIntValue5);
                                }
                            } else {
                                j0Var3.f6971a = null;
                                if (str6.length() > 0 && (numC3 = b0Var.c()) != null) {
                                    int iIntValue6 = numC3.intValue();
                                    b0Var.o(iIntValue6, iIntValue6);
                                }
                            }
                        }
                        b0Var.n();
                        break;
                    case 35:
                        z.j0 j0Var4 = b0Var.f6903e;
                        j0Var4.f6971a = null;
                        y1.f fVar7 = b0Var.f6905g;
                        String str7 = fVar7.f6759d;
                        String str8 = fVar7.f6759d;
                        if (str7.length() > 0) {
                            if (b0Var.e()) {
                                j0Var4.f6971a = null;
                                if (str8.length() > 0 && (numC4 = b0Var.c()) != null) {
                                    int iIntValue7 = numC4.intValue();
                                    b0Var.o(iIntValue7, iIntValue7);
                                }
                            } else {
                                j0Var4.f6971a = null;
                                if (str8.length() > 0 && (numD4 = b0Var.d()) != null) {
                                    int iIntValue8 = numD4.intValue();
                                    b0Var.o(iIntValue8, iIntValue8);
                                }
                            }
                        }
                        b0Var.n();
                        break;
                    case 36:
                        b0Var.i();
                        b0Var.n();
                        break;
                    case 37:
                        b0Var.j();
                        b0Var.n();
                        break;
                    case 38:
                        b0Var.m();
                        b0Var.n();
                        break;
                    case 39:
                        b0Var.l();
                        b0Var.n();
                        break;
                    case 40:
                        b0Var.f6903e.f6971a = null;
                        if (b0Var.f6905g.f6759d.length() > 0) {
                            if (b0Var.e()) {
                                b0Var.m();
                            } else {
                                b0Var.l();
                            }
                        }
                        b0Var.n();
                        break;
                    case 41:
                        b0Var.f6903e.f6971a = null;
                        if (b0Var.f6905g.f6759d.length() > 0) {
                            if (b0Var.e()) {
                                b0Var.l();
                            } else {
                                b0Var.m();
                            }
                        }
                        b0Var.n();
                        break;
                    case 42:
                        b0Var.f6903e.f6971a = null;
                        if (b0Var.f6905g.f6759d.length() > 0) {
                            long j5 = b0Var.f6904f;
                            int i10 = y1.g0.f6769c;
                            int i11 = (int) (j5 & 4294967295L);
                            b0Var.o(i11, i11);
                        }
                        break;
                    case 43:
                        if (!x0Var.f6135e) {
                            x0Var.a(p3.j.G(new e2.a("\n", 1)));
                        } else {
                            x0Var.f6131a.f6026u.c(new e2.m(x0Var.f6142l));
                        }
                        break;
                    case 44:
                        if (!x0Var.f6135e) {
                            x0Var.a(p3.j.G(new e2.a("\t", 1)));
                        } else {
                            ((b4.s) obj2).f693d = false;
                        }
                        break;
                    case 45:
                        v.f1 f1Var = x0Var.f6138h;
                        if (f1Var != null) {
                            f1Var.a(e2.x.a(b0Var.f6906h, b0Var.f6905g, b0Var.f6904f, 4));
                        }
                        v.f1 f1Var2 = x0Var.f6138h;
                        if (f1Var2 != null) {
                            a2.h hVar2 = f1Var2.f5921a;
                            if (hVar2 != null && (hVar = (a2.h) hVar2.f49e) != null) {
                                f1Var2.f5921a = hVar;
                                f1Var2.f5923c -= ((e2.x) hVar2.f50f).f2062a.f6759d.length();
                                f1Var2.f5922b = new a2.h(f1Var2.f5922b, i9, (e2.x) hVar2.f50f);
                                xVar = (e2.x) hVar.f50f;
                            }
                            if (xVar != null) {
                                x0Var.f6141k.c(xVar);
                            }
                        }
                        break;
                    case 46:
                        v.f1 f1Var3 = x0Var.f6138h;
                        if (f1Var3 != null) {
                            a2.h hVar3 = f1Var3.f5922b;
                            if (hVar3 != null) {
                                f1Var3.f5922b = (a2.h) hVar3.f49e;
                                e2.x xVar2 = (e2.x) hVar3.f50f;
                                f1Var3.f5921a = new a2.h(f1Var3.f5921a, i9, xVar2);
                                f1Var3.f5923c = xVar2.f2062a.f6759d.length() + f1Var3.f5923c;
                                xVar = (e2.x) hVar3.f50f;
                            }
                            if (xVar != null) {
                                x0Var.f6141k.c(xVar);
                            }
                        }
                        break;
                }
                break;
        }
        return nVar;
    }
}
