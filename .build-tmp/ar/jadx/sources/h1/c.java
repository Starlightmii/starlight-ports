package h1;

import a2.h;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Path;
import android.view.KeyEvent;
import android.view.View;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import b.i0;
import b3.w;
import b4.l;
import b4.m;
import b4.z;
import e1.a0;
import e1.f;
import e1.i;
import e1.n;
import e1.q;
import e1.r;
import e1.s;
import e1.t;
import f0.b2;
import f0.d2;
import f0.i1;
import f0.j0;
import f0.k1;
import f0.p;
import f0.v;
import f0.w2;
import g4.g;
import i4.y;
import io.github.andrea_lyz.assistrestore.R;
import java.util.List;
import n.h1;
import n.k0;
import p3.j;
import p3.k;
import p3.o;
import y0.s0;
import y0.u;
import y1.g0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static e1.e f2605a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static e1.e f2606b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static e1.e f2607c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static e1.e f2608d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static e1.e f2609e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static e1.e f2610f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static e1.e f2611g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static e1.e f2612h;

    public static final boolean A(long j5, long j6) {
        int iK = k2.a.k(j5);
        int i5 = k2.a.i(j5);
        int i6 = (int) (j6 >> 32);
        if (iK > i6 || i6 > i5) {
            return false;
        }
        int i7 = (int) (j6 & 4294967295L);
        return k2.a.j(j5) <= i7 && i7 <= k2.a.h(j5);
    }

    public static boolean B(char c5) {
        return Character.isWhitespace(c5) || Character.isSpaceChar(c5);
    }

    public static g C(a4.e eVar) {
        g gVar = new g();
        gVar.f2563f = j.q(eVar, gVar, gVar);
        return gVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static p3.d D(a4.a aVar) {
        o oVar = new o();
        oVar.f5027d = (m) aVar;
        oVar.f5028e = p3.m.f5025a;
        return oVar;
    }

    public static k E(a4.a aVar) {
        l.e("initializer", aVar);
        return new k(aVar);
    }

    public static final long F(float f5, long j5) {
        return (Float.isNaN(f5) || f5 >= 1.0f) ? j5 : u.b(u.d(j5) * f5, j5);
    }

    public static final long G(int i5, int i6, long j5) {
        int iK = k2.a.k(j5) + i5;
        if (iK < 0) {
            iK = 0;
        }
        int i7 = k2.a.i(j5);
        if (i7 != Integer.MAX_VALUE && (i7 = i7 + i5) < 0) {
            i7 = 0;
        }
        int iJ = k2.a.j(j5) + i6;
        if (iJ < 0) {
            iJ = 0;
        }
        int iH = k2.a.h(j5);
        return b(iK, i7, iJ, (iH == Integer.MAX_VALUE || (iH = iH + i6) >= 0) ? iH : 0);
    }

    public static /* synthetic */ long H(long j5, int i5, int i6, int i7) {
        if ((i7 & 1) != 0) {
            i5 = 0;
        }
        if ((i7 & 2) != 0) {
            i6 = 0;
        }
        return G(i5, i6, j5);
    }

    public static final void K(w2 w2Var, f0.c cVar, int i5) {
        while (true) {
            int i6 = w2Var.f2411v;
            if (i5 > i6 && i5 < w2Var.f2410u) {
                return;
            }
            if (i6 == 0 && i5 == 0) {
                return;
            }
            w2Var.M();
            if (w2Var.y(w2Var.f2411v)) {
                cVar.p();
            }
            w2Var.j();
        }
    }

    public static final h1 L(p pVar) {
        int i5 = 0;
        Object[] objArr = new Object[0];
        h hVar = h1.f3919i;
        boolean zD = ((v) pVar).d(0);
        v vVar = (v) pVar;
        Object objK = vVar.K();
        if (zD || objK == f0.o.f2281a) {
            objK = new k0(i5, 2);
            vVar.f0(objK);
        }
        return (h1) o0.g.b(objArr, hVar, (a4.a) objK, vVar, 0, 4);
    }

    public static final long M(long j5) {
        int iRound = Math.round(x0.c.d(j5));
        return (((long) Math.round(x0.c.e(j5))) & 4294967295L) | (((long) iRound) << 32);
    }

    public static final long N(float f5, long j5) {
        return p3.p.a(Math.max(0.0f, x0.a.b(j5) - f5), Math.max(0.0f, x0.a.c(j5) - f5));
    }

    public static final Object O(n4.p pVar, n4.p pVar2, a4.e eVar) throws Throwable {
        Object oVar;
        Object objP;
        try {
            z.c(2, eVar);
            oVar = eVar.invoke(pVar2, pVar);
        } catch (Throwable th) {
            oVar = new i4.o(th, false);
        }
        u3.a aVar = u3.a.f5838d;
        if (oVar == aVar || (objP = pVar.P(oVar)) == y.f2790e) {
            return aVar;
        }
        if (objP instanceof i4.o) {
            throw ((i4.o) objP).f2763a;
        }
        return y.r(objP);
    }

    public static final void P(List list, y0.k0 k0Var) {
        e1.u uVar;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        y0.j jVar = (y0.j) k0Var;
        Path path = jVar.f6638a;
        Path path2 = jVar.f6638a;
        Path.FillType fillType = path.getFillType();
        Path.FillType fillType2 = Path.FillType.EVEN_ODD;
        boolean z2 = fillType == fillType2;
        path2.rewind();
        if (!z2) {
            fillType2 = Path.FillType.WINDING;
        }
        path2.setFillType(fillType2);
        e1.u uVar2 = list.isEmpty() ? i.f1922b : (e1.u) list.get(0);
        int size = list.size();
        float f10 = 0.0f;
        int i5 = 0;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        float f14 = 0.0f;
        float f15 = 0.0f;
        float f16 = 0.0f;
        while (i5 < size) {
            e1.u uVar3 = (e1.u) list.get(i5);
            if (uVar3 instanceof i) {
                path2.close();
                uVar = uVar3;
                f11 = f13;
                f15 = f11;
                f12 = f14;
                f16 = f12;
            } else if (uVar3 instanceof e1.m) {
                e1.m mVar = (e1.m) uVar3;
                f13 = mVar.f1932b;
                f14 = mVar.f1933c;
                path2.moveTo(f13, f14);
                uVar = uVar3;
                f15 = f13;
                f16 = f14;
            } else {
                if (uVar3 instanceof q) {
                    q qVar = (q) uVar3;
                    float f17 = qVar.f1946c;
                    float f18 = qVar.f1945b;
                    path2.rLineTo(f18, f17);
                    f15 += f18;
                    f16 += f17;
                } else if (uVar3 instanceof e1.l) {
                    e1.l lVar = (e1.l) uVar3;
                    float f19 = lVar.f1931c;
                    float f20 = lVar.f1930b;
                    path2.lineTo(f20, f19);
                    f15 = f20;
                    uVar = uVar3;
                    f16 = f19;
                } else if (uVar3 instanceof e1.p) {
                    float f21 = ((e1.p) uVar3).f1944b;
                    path2.rLineTo(f21, f10);
                    f15 += f21;
                } else if (uVar3 instanceof e1.k) {
                    float f22 = ((e1.k) uVar3).f1929b;
                    path2.lineTo(f22, f16);
                    f15 = f22;
                } else if (uVar3 instanceof s) {
                    float f23 = ((s) uVar3).f1951b;
                    path2.rLineTo(f10, f23);
                    f16 += f23;
                } else if (uVar3 instanceof t) {
                    float f24 = ((t) uVar3).f1952b;
                    path2.lineTo(f15, f24);
                    f16 = f24;
                } else {
                    if (uVar3 instanceof e1.o) {
                        e1.o oVar = (e1.o) uVar3;
                        uVar = uVar3;
                        path2.rCubicTo(oVar.f1938b, oVar.f1939c, oVar.f1940d, oVar.f1941e, oVar.f1942f, oVar.f1943g);
                        f11 = oVar.f1940d + f15;
                        f12 = oVar.f1941e + f16;
                        f15 += oVar.f1942f;
                        f9 = oVar.f1943g;
                    } else {
                        uVar = uVar3;
                        if (uVar instanceof e1.j) {
                            e1.j jVar2 = (e1.j) uVar;
                            path2.cubicTo(jVar2.f1923b, jVar2.f1924c, jVar2.f1925d, jVar2.f1926e, jVar2.f1927f, jVar2.f1928g);
                            f11 = jVar2.f1925d;
                            f12 = jVar2.f1926e;
                            f5 = jVar2.f1927f;
                            f6 = jVar2.f1928g;
                        } else if (uVar instanceof r) {
                            if (uVar2.f1953a) {
                                f8 = f16 - f12;
                                f7 = f15 - f11;
                            } else {
                                f7 = 0.0f;
                                f8 = 0.0f;
                            }
                            r rVar = (r) uVar;
                            path2.rCubicTo(f7, f8, rVar.f1947b, rVar.f1948c, rVar.f1949d, rVar.f1950e);
                            f11 = rVar.f1947b + f15;
                            f12 = rVar.f1948c + f16;
                            f15 += rVar.f1949d;
                            f9 = rVar.f1950e;
                        } else if (uVar instanceof n) {
                            if (uVar2.f1953a) {
                                float f25 = 2;
                                f15 = (f15 * f25) - f11;
                                f16 = (f25 * f16) - f12;
                            }
                            n nVar = (n) uVar;
                            path2.cubicTo(f15, f16, nVar.f1934b, nVar.f1935c, nVar.f1936d, nVar.f1937e);
                            f11 = nVar.f1934b;
                            f12 = nVar.f1935c;
                            f5 = nVar.f1936d;
                            f6 = nVar.f1937e;
                        }
                        f16 = f6;
                        f15 = f5;
                    }
                    f16 += f9;
                }
                uVar = uVar3;
            }
            i5++;
            uVar2 = uVar;
            f10 = 0.0f;
        }
    }

    public static String Q(int i5) {
        if (i5 == 0) {
            return "Unspecified";
        }
        if (i5 == 1) {
            return "Text";
        }
        if (i5 == 2) {
            return "Ascii";
        }
        if (i5 == 3) {
            return "Number";
        }
        if (i5 == 4) {
            return "Phone";
        }
        if (i5 == 5) {
            return "Uri";
        }
        if (i5 == 6) {
            return "Email";
        }
        if (i5 == 7) {
            return "Password";
        }
        if (i5 == 8) {
            return "NumberPassword";
        }
        return i5 == 9 ? "Decimal" : "Invalid";
    }

    public static final long R(long j5, long j6) {
        int iC;
        int iE = g0.e(j5);
        int iD = g0.d(j5);
        if (g0.e(j6) >= g0.d(j5) || g0.e(j5) >= g0.d(j6)) {
            if (iD > g0.e(j6)) {
                iE -= g0.c(j6);
                iC = g0.c(j6);
                iD -= iC;
            }
        } else if (g0.e(j6) > g0.e(j5) || g0.d(j5) > g0.d(j6)) {
            if (g0.e(j5) > g0.e(j6) || g0.d(j6) > g0.d(j5)) {
                int iE2 = g0.e(j6);
                if (iE >= g0.d(j6) || iE2 > iE) {
                    iD = g0.e(j6);
                } else {
                    iE = g0.e(j6);
                    iC = g0.c(j6);
                }
            } else {
                iC = g0.c(j6);
            }
            iD -= iC;
        } else {
            iE = g0.e(j6);
            iD = iE;
        }
        return j.g(iE, iD);
    }

    public static r0.p S(r0.p pVar, h1 h1Var) {
        return r0.a.a(pVar, new androidx.compose.foundation.e(h1Var));
    }

    public static final void a(boolean z2, a4.a aVar, p pVar, int i5) {
        v vVar = (v) pVar;
        vVar.X(-361453782);
        if (((i5 | 6) & 19) == 18 && vVar.z()) {
            vVar.Q();
        } else {
            i1 i1VarV = f0.l.v(aVar, vVar);
            vVar.W(-971159753);
            Object objK = vVar.K();
            f0.h hVar = f0.o.f2281a;
            if (objK == hVar) {
                objK = new c.d(i1VarV);
                vVar.f0(objK);
            }
            c.d dVar = (c.d) objK;
            int i6 = 0;
            vVar.p(false);
            vVar.W(-971159481);
            int i7 = 1;
            boolean zF = vVar.f(dVar) | vVar.g(true);
            Object objK2 = vVar.K();
            if (zF || objK2 == hVar) {
                objK2 = new b0.a(2, dVar);
                vVar.f0(objK2);
            }
            vVar.p(false);
            f0.l.g((a4.a) objK2, vVar);
            j0 j0Var = c.g.f712a;
            vVar.W(-2068013981);
            b.j0 j0Var2 = (b.j0) vVar.j(c.g.f712a);
            vVar.W(1680121597);
            Context context = null;
            if (j0Var2 == null) {
                View view = (View) vVar.j(AndroidCompositionLocals_androidKt.f208f);
                l.e("<this>", view);
                g4.c cVar = new g4.c(new g4.d(new g4.j(i7, new g4.d(new d2(i7, view), b.k0.f349g, i7)), new k1(i7), i6));
                j0Var2 = (b.j0) (!cVar.hasNext() ? null : cVar.next());
            }
            vVar.p(false);
            if (j0Var2 == null) {
                for (Context baseContext = (Context) vVar.j(AndroidCompositionLocals_androidKt.f204b); baseContext instanceof ContextWrapper; baseContext = ((ContextWrapper) baseContext).getBaseContext()) {
                    if (baseContext instanceof b.j0) {
                        context = baseContext;
                        break;
                    }
                }
                j0Var2 = (b.j0) context;
            }
            vVar.p(false);
            if (j0Var2 == null) {
                throw new IllegalStateException("No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner");
            }
            i0 onBackPressedDispatcher = j0Var2.getOnBackPressedDispatcher();
            androidx.lifecycle.v vVar2 = (androidx.lifecycle.v) vVar.j(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            vVar.W(-971159120);
            boolean zF2 = vVar.f(onBackPressedDispatcher) | vVar.f(vVar2) | vVar.f(dVar);
            Object objK3 = vVar.K();
            if (zF2 || objK3 == hVar) {
                objK3 = new c.b(onBackPressedDispatcher, vVar2, dVar, i6);
                vVar.f0(objK3);
            }
            vVar.p(false);
            f0.l.d(vVar2, onBackPressedDispatcher, (a4.c) objK3, vVar);
            z2 = true;
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c.c(z2, aVar, i5);
        }
    }

    public static final long b(int i5, int i6, int i7, int i8) {
        boolean z2 = false;
        if (!(i6 >= i5)) {
            d4.a.J("maxWidth(" + i6 + ") must be >= than minWidth(" + i5 + ')');
            throw null;
        }
        if (!(i8 >= i7)) {
            d4.a.J("maxHeight(" + i8 + ") must be >= than minHeight(" + i7 + ')');
            throw null;
        }
        if (i5 >= 0 && i7 >= 0) {
            z2 = true;
        }
        if (z2) {
            return r(i5, i6, i7, i8);
        }
        d4.a.J("minWidth(" + i5 + ") and minHeight(" + i7 + ") must be >= 0");
        throw null;
    }

    public static /* synthetic */ long c(int i5, int i6, int i7) {
        if ((i7 & 2) != 0) {
            i5 = Integer.MAX_VALUE;
        }
        if ((i7 & 8) != 0) {
            i6 = Integer.MAX_VALUE;
        }
        return b(0, i5, 0, i6);
    }

    public static final long d(int i5, int i6) {
        return (((long) i6) & 4294967295L) | (((long) i5) << 32);
    }

    public static final long e(int i5) {
        long j5 = (((long) i5) << 32) | (((long) 0) & 4294967295L);
        int i6 = a.f2602n;
        return j5;
    }

    public static final long f(float f5, float f6) {
        return (((long) Float.floatToRawIntBits(f6)) & 4294967295L) | (Float.floatToRawIntBits(f5) << 32);
    }

    public static final Object[] g(Object[] objArr, int i5, Object obj, Object obj2) {
        Object[] objArr2 = new Object[objArr.length + 2];
        q3.k.C(objArr, objArr2, 0, i5, 6);
        q3.k.A(objArr, objArr2, i5 + 2, i5, objArr.length);
        objArr2[i5] = obj;
        objArr2[i5 + 1] = obj2;
        return objArr2;
    }

    public static final int h(int i5) {
        if (i5 < 8191) {
            return 262142;
        }
        if (i5 < 32767) {
            return 65534;
        }
        if (i5 < 65535) {
            return 32766;
        }
        if (i5 < 262143) {
            return 8190;
        }
        throw new IllegalArgumentException("Can't represent a size of " + i5 + " in Constraints");
    }

    public static final Object[] i(int i5, Object[] objArr) {
        Object[] objArr2 = new Object[objArr.length - 2];
        q3.k.C(objArr, objArr2, 0, i5, 6);
        q3.k.A(objArr, objArr2, i5, i5 + 2, objArr.length);
        return objArr2;
    }

    public static final Object[] j(int i5, Object[] objArr) {
        Object[] objArr2 = new Object[objArr.length - 1];
        q3.k.C(objArr, objArr2, 0, i5, 6);
        q3.k.A(objArr, objArr2, i5, i5 + 1, objArr.length);
        return objArr2;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0026 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x0027  */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0011, code lost:
    
        if (r5 == false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0015, code lost:
    
        return r2 - r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int k(int r2, int r3, int r4, boolean r5) {
        /*
            r0 = 0
            if (r3 < r4) goto L8
            if (r5 == 0) goto L6
            return r0
        L6:
            int r4 = r4 - r3
            return r4
        L8:
            if (r5 != 0) goto Ld
            if (r3 > r2) goto L16
            goto L11
        Ld:
            int r1 = r4 - r3
            if (r1 <= r2) goto L16
        L11:
            if (r5 == 0) goto L14
            goto L21
        L14:
            int r2 = r2 - r3
            return r2
        L16:
            if (r5 == 0) goto L1b
            if (r3 > r2) goto L24
            goto L1f
        L1b:
            int r1 = r4 - r3
            if (r1 <= r2) goto L24
        L1f:
            if (r5 != 0) goto L22
        L21:
            return r2
        L22:
            int r2 = r2 - r3
            return r2
        L24:
            if (r5 != 0) goto L27
            return r0
        L27:
            int r4 = r4 - r3
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: h1.c.k(int, int, int, boolean):int");
    }

    public static final int l(int i5) {
        if (i5 < 8191) {
            return 13;
        }
        if (i5 < 32767) {
            return 15;
        }
        if (i5 < 65535) {
            return 16;
        }
        return i5 < 262143 ? 18 : 255;
    }

    public static void m(int i5) {
        if (2 > i5 || i5 >= 37) {
            StringBuilder sbG = b.b.g("radix ", i5, " was not in valid range ");
            sbG.append(new e4.d(2, 36, 1));
            throw new IllegalArgumentException(sbG.toString());
        }
    }

    public static final long n(long j5, long j6) {
        return a.a.f(d4.a.m((int) (j6 >> 32), k2.a.k(j5), k2.a.i(j5)), d4.a.m((int) (j6 & 4294967295L), k2.a.j(j5), k2.a.h(j5)));
    }

    public static final long o(long j5, long j6) {
        return b(d4.a.m(k2.a.k(j6), k2.a.k(j5), k2.a.i(j5)), d4.a.m(k2.a.i(j6), k2.a.k(j5), k2.a.i(j5)), d4.a.m(k2.a.j(j6), k2.a.j(j5), k2.a.h(j5)), d4.a.m(k2.a.h(j6), k2.a.j(j5), k2.a.h(j5)));
    }

    public static final int p(long j5, int i5) {
        return d4.a.m(i5, k2.a.j(j5), k2.a.h(j5));
    }

    public static final int q(long j5, int i5) {
        return d4.a.m(i5, k2.a.k(j5), k2.a.i(j5));
    }

    public static final long r(int i5, int i6, int i7, int i8) {
        int i9 = i8 == Integer.MAX_VALUE ? i7 : i8;
        int iL = l(i9);
        int i10 = i6 == Integer.MAX_VALUE ? i5 : i6;
        int iL2 = l(i10);
        if (iL + iL2 > 31) {
            throw new IllegalArgumentException("Can't represent a width of " + i10 + " and height of " + i9 + " in Constraints");
        }
        int i11 = i6 + 1;
        int i12 = i11 & (~(i11 >> 31));
        int i13 = i8 + 1;
        int i14 = i13 & (~(i13 >> 31));
        int i15 = 0;
        if (iL2 != 13) {
            if (iL2 == 18) {
                i15 = 3;
            } else if (iL2 == 15) {
                i15 = 1;
            } else if (iL2 == 16) {
                i15 = 2;
            }
        }
        int i16 = (((i15 & 2) >> 1) * 3) + ((i15 & 1) << 1);
        return (((long) i12) << 33) | ((long) i15) | (((long) i5) << 2) | (((long) i7) << (i16 + 15)) | (((long) i14) << (i16 + 46));
    }

    public static String s(List list, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        int size = list.size();
        int i5 = 0;
        for (int i6 = 0; i6 < size; i6++) {
            Object obj = list.get(i6);
            i5++;
            if (i5 > 1) {
                sb.append((CharSequence) str);
            }
            if (obj != null ? obj instanceof CharSequence : true) {
                sb.append((CharSequence) obj);
            } else if (obj instanceof Character) {
                sb.append(((Character) obj).charValue());
            } else {
                sb.append((CharSequence) String.valueOf(obj));
            }
        }
        sb.append((CharSequence) "");
        return sb.toString();
    }

    public static final e1.e t() {
        e1.e eVar = f2607c;
        if (eVar != null) {
            return eVar;
        }
        e1.d dVar = new e1.d("Rounded.Check");
        int i5 = a0.f1864a;
        s0 s0Var = new s0(u.f6689b);
        f fVar = new f();
        fVar.h(9.0f, 16.17f);
        fVar.f(5.53f, 12.7f);
        fVar.c(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        fVar.c(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        fVar.g(4.18f, 4.18f);
        fVar.c(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        fVar.f(20.29f, 7.71f);
        fVar.c(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        fVar.c(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        fVar.f(9.0f, 16.17f);
        fVar.a();
        e1.d.a(dVar, fVar.f1910d, s0Var);
        e1.e eVarB = dVar.b();
        f2607c = eVarB;
        return eVarB;
    }

    public static final long u(KeyEvent keyEvent) {
        return e(keyEvent.getKeyCode());
    }

    public static final a3.a v(View view) {
        a3.a aVar = (a3.a) view.getTag(R.id.pooling_container_listener_holder_tag);
        if (aVar != null) {
            return aVar;
        }
        a3.a aVar2 = new a3.a();
        view.setTag(R.id.pooling_container_listener_holder_tag, aVar2);
        return aVar2;
    }

    public static final e1.e w() {
        e1.e eVar = f2610f;
        if (eVar != null) {
            return eVar;
        }
        e1.d dVar = new e1.d("Rounded.SettingsVoice");
        int i5 = a0.f1864a;
        s0 s0Var = new s0(u.f6689b);
        f fVar = new f();
        fVar.h(8.0f, 24.0f);
        fVar.f(8.0f, 24.0f);
        fVar.c(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        fVar.l(0.0f);
        fVar.c(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        fVar.e(0.0f);
        fVar.c(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        fVar.l(0.0f);
        fVar.b(7.0f, 23.55f, 7.45f, 24.0f, 8.0f, 24.0f);
        fVar.a();
        fVar.h(12.0f, 24.0f);
        fVar.f(12.0f, 24.0f);
        fVar.c(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        fVar.l(0.0f);
        fVar.c(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        fVar.e(0.0f);
        fVar.c(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        fVar.l(0.0f);
        fVar.b(11.0f, 23.55f, 11.45f, 24.0f, 12.0f, 24.0f);
        fVar.a();
        fVar.h(16.0f, 24.0f);
        fVar.f(16.0f, 24.0f);
        fVar.c(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        fVar.l(0.0f);
        fVar.c(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        fVar.e(0.0f);
        fVar.c(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        fVar.l(0.0f);
        fVar.b(15.0f, 23.55f, 15.45f, 24.0f, 16.0f, 24.0f);
        fVar.a();
        fVar.h(9.0f, 10.0f);
        fVar.k(4.0f);
        fVar.c(0.0f, -1.66f, 1.34f, -3.0f, 3.0f, -3.0f);
        fVar.j(3.0f, 1.34f, 3.0f, 3.0f);
        fVar.l(6.0f);
        fVar.c(0.0f, 1.66f, -1.34f, 3.0f, -3.0f, 3.0f);
        fVar.i(9.0f, 11.66f, 9.0f, 10.0f);
        fVar.a();
        fVar.h(17.91f, 10.0f);
        fVar.c(0.61f, 0.0f, 1.09f, 0.54f, 1.0f, 1.14f);
        fVar.c(-0.49f, 3.0f, -2.89f, 5.34f, -5.91f, 5.78f);
        fVar.k(19.0f);
        fVar.c(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        fVar.e(0.0f);
        fVar.c(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        fVar.l(-2.08f);
        fVar.c(-3.02f, -0.44f, -5.42f, -2.78f, -5.91f, -5.78f);
        fVar.c(-0.1f, -0.6f, 0.39f, -1.14f, 1.0f, -1.14f);
        fVar.e(0.0f);
        fVar.c(0.49f, 0.0f, 0.9f, 0.36f, 0.98f, 0.85f);
        fVar.b(7.48f, 13.21f, 9.53f, 15.0f, 12.0f, 15.0f);
        fVar.j(4.52f, -1.79f, 4.93f, -4.15f);
        fVar.b(17.01f, 10.36f, 17.42f, 10.0f, 17.91f, 10.0f);
        fVar.f(17.91f, 10.0f);
        fVar.a();
        e1.d.a(dVar, fVar.f1910d, s0Var);
        e1.e eVarB = dVar.b();
        f2610f = eVarB;
        return eVarB;
    }

    public static final e1.e x() {
        e1.e eVar = f2611g;
        if (eVar != null) {
            return eVar;
        }
        e1.d dVar = new e1.d("Rounded.TouchApp");
        int i5 = a0.f1864a;
        s0 s0Var = new s0(u.f6689b);
        f fVar = new f();
        fVar.h(8.79f, 9.24f);
        fVar.k(5.5f);
        fVar.c(0.0f, -1.38f, 1.12f, -2.5f, 2.5f, -2.5f);
        fVar.j(2.5f, 1.12f, 2.5f, 2.5f);
        fVar.l(3.74f);
        fVar.c(1.21f, -0.81f, 2.0f, -2.18f, 2.0f, -3.74f);
        fVar.c(0.0f, -2.49f, -2.01f, -4.5f, -4.5f, -4.5f);
        fVar.j(-4.5f, 2.01f, -4.5f, 4.5f);
        fVar.b(6.79f, 7.06f, 7.58f, 8.43f, 8.79f, 9.24f);
        fVar.a();
        fVar.h(14.29f, 11.71f);
        fVar.c(-0.28f, -0.14f, -0.58f, -0.21f, -0.89f, -0.21f);
        fVar.e(-0.61f);
        fVar.l(-6.0f);
        fVar.c(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        fVar.j(-1.5f, 0.67f, -1.5f, 1.5f);
        fVar.l(10.74f);
        fVar.g(-3.44f, -0.72f);
        fVar.c(-0.37f, -0.08f, -0.76f, 0.04f, -1.03f, 0.31f);
        fVar.c(-0.43f, 0.44f, -0.43f, 1.14f, 0.0f, 1.58f);
        fVar.g(4.01f, 4.01f);
        fVar.b(9.71f, 21.79f, 10.22f, 22.0f, 10.75f, 22.0f);
        fVar.e(6.1f);
        fVar.c(1.0f, 0.0f, 1.84f, -0.73f, 1.98f, -1.72f);
        fVar.g(0.63f, -4.47f);
        fVar.c(0.12f, -0.85f, -0.32f, -1.69f, -1.09f, -2.07f);
        fVar.f(14.29f, 11.71f);
        fVar.a();
        e1.d.a(dVar, fVar.f1910d, s0Var);
        e1.e eVarB = dVar.b();
        f2611g = eVarB;
        return eVarB;
    }

    public static final int y(KeyEvent keyEvent) {
        int action = keyEvent.getAction();
        if (action != 0) {
            return action != 1 ? 0 : 1;
        }
        return 2;
    }

    public static final int z(int i5, int i6) {
        return (i5 >> i6) & 31;
    }

    public abstract void I(Throwable th);

    public abstract void J(w wVar);
}
