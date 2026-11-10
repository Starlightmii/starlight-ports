package c0;

import android.graphics.ColorFilter;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r0.p f1183a = androidx.compose.foundation.layout.c.h(r0.m.f5680a, e0.g.f1723a);

    /* JADX WARN: Code duplicated, block: B:82:0x011f  */
    public static final void a(d1.b bVar, String str, r0.p pVar, long j5, f0.p pVar2, int i5) {
        int i6;
        y0.m mVar;
        r0.p pVarA;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-2142239481);
        if ((i5 & 6) == 0) {
            i6 = (vVar.h(bVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.f(str) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.f(pVar) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar.e(j5) ? 2048 : 1024;
        }
        if ((i6 & 1171) == 1170 && vVar.z()) {
            vVar.Q();
        } else {
            vVar.S();
            if ((i5 & 1) != 0 && !vVar.x()) {
                vVar.Q();
            }
            vVar.q();
            boolean z2 = (((i6 & 7168) ^ 3072) > 2048 && vVar.e(j5)) || (i6 & 3072) == 2048;
            Object objK = vVar.K();
            f0.h hVar = f0.o.f2281a;
            if (z2 || objK == hVar) {
                if (y0.u.c(j5, y0.u.f6694g)) {
                    mVar = null;
                } else {
                    mVar = new y0.m(j5, 5, Build.VERSION.SDK_INT >= 29 ? y0.n.f6649a.a(j5, 5) : new PorterDuffColorFilter(y0.l0.w(j5), y0.l0.z(5)));
                }
                objK = mVar;
                vVar.f0(objK);
            }
            y0.m mVar2 = (y0.m) objK;
            vVar.V(-2144891392);
            r0.p pVar3 = r0.m.f5680a;
            if (str != null) {
                boolean z3 = (i6 & 112) == 32;
                Object objK2 = vVar.K();
                if (z3 || objK2 == hVar) {
                    objK2 = new p0(str, 0);
                    vVar.f0(objK2);
                }
                pVarA = w1.k.a(pVar3, (a4.c) objK2);
            } else {
                pVarA = pVar3;
            }
            vVar.p(false);
            if (x0.f.a(bVar.d(), 9205357640488583168L)) {
                pVar3 = f1183a;
            } else {
                long jD = bVar.d();
                if (Float.isInfinite(x0.f.d(jD)) && Float.isInfinite(x0.f.b(jD))) {
                    pVar3 = f1183a;
                }
            }
            r.o.a(androidx.compose.ui.draw.a.d(pVar.e(pVar3), bVar, mVar2, 22).e(pVarA), vVar, 0);
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new o0(bVar, str, pVar, j5, i5);
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0050  */
    /* JADX WARN: Code duplicated, block: B:35:0x005f  */
    /* JADX WARN: Code duplicated, block: B:37:0x0063  */
    /* JADX WARN: Code duplicated, block: B:44:0x0079  */
    /* JADX WARN: Code duplicated, block: B:46:0x0080  */
    /* JADX WARN: Code duplicated, block: B:53:0x0093 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:54:0x0095  */
    /* JADX WARN: Code duplicated, block: B:55:0x0098  */
    /* JADX WARN: Code duplicated, block: B:58:0x009d  */
    /* JADX WARN: Code duplicated, block: B:59:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:62:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:66:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:68:0x010c  */
    /* JADX WARN: Code duplicated, block: B:71:0x0116  */
    /* JADX WARN: Code duplicated, block: B:74:0x012f  */
    /* JADX WARN: Code duplicated, block: B:76:0x0139  */
    /* JADX WARN: Code duplicated, block: B:77:0x0142  */
    /* JADX WARN: Code duplicated, block: B:79:0x0155  */
    /* JADX WARN: Code duplicated, block: B:84:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:86:? A[RETURN, SYNTHETIC] */
    public static final void b(e1.e eVar, String str, r0.p pVar, long j5, f0.p pVar2, int i5, int i6) {
        int i7;
        r0.p pVar3;
        long j6;
        r0.p pVar4;
        long j7;
        r0.p pVar5;
        k2.b bVar;
        boolean zE;
        Object objK;
        long jF;
        float fD;
        float fB;
        long j8;
        int i8;
        y0.m mVar;
        ColorFilter porterDuffColorFilter;
        r0.p pVar6;
        long j9;
        f0.b2 b2VarR;
        int i9;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-126890956);
        if ((i5 & 6) == 0) {
            i7 = (vVar.f(eVar) ? 4 : 2) | i5;
        } else {
            i7 = i5;
        }
        if ((i5 & 48) == 0) {
            i7 |= vVar.f(str) ? 32 : 16;
        }
        int i10 = i6 & 4;
        if (i10 == 0) {
            if ((i5 & 384) == 0) {
                pVar3 = pVar;
                i7 |= vVar.f(pVar3) ? 256 : 128;
            }
            if ((i5 & 3072) == 0) {
                j6 = j5;
                if ((i6 & 8) == 0 || !vVar.e(j6)) {
                    i9 = 1024;
                } else {
                    i9 = 2048;
                }
                i7 |= i9;
            } else {
                j6 = j5;
            }
            if ((i7 & 1171) == 1170 || !vVar.z()) {
                vVar.S();
                if ((i5 & 1) != 0 || vVar.x()) {
                    if (i10 != 0) {
                        pVar4 = r0.m.f5680a;
                    } else {
                        pVar4 = pVar3;
                    }
                    if ((i6 & 8) != 0) {
                        i7 &= -7169;
                        pVar5 = pVar4;
                        j7 = ((y0.u) vVar.j(b0.f762a)).f6696a;
                    } else {
                        j7 = j6;
                        pVar5 = pVar4;
                    }
                } else {
                    vVar.Q();
                    if ((i6 & 8) != 0) {
                        i7 &= -7169;
                    }
                    j7 = j6;
                    pVar5 = pVar3;
                }
                vVar.q();
                bVar = (k2.b) vVar.j(q1.j1.f5237f);
                zE = vVar.e((((long) Float.floatToRawIntBits(bVar.a())) & 4294967295L) | (((long) Float.floatToRawIntBits(eVar.f1909i)) << 32));
                objK = vVar.K();
                if (!zE || objK == f0.o.f2281a) {
                    e1.b bVar2 = new e1.b();
                    a.a.q(bVar2, eVar.f1906f);
                    jF = p3.j.f(bVar.v(eVar.f1902b), bVar.v(eVar.f1903c));
                    fD = eVar.f1904d;
                    fB = eVar.f1905e;
                    if (Float.isNaN(fD)) {
                        fD = x0.f.d(jF);
                    }
                    if (Float.isNaN(fB)) {
                        fB = x0.f.b(jF);
                    }
                    long jF2 = p3.j.f(fD, fB);
                    e1.c0 c0Var = new e1.c0(bVar2);
                    String str2 = eVar.f1901a;
                    j8 = eVar.f1907g;
                    i8 = eVar.f1908h;
                    if (j8 != 16) {
                        if (Build.VERSION.SDK_INT >= 29) {
                            porterDuffColorFilter = y0.n.f6649a.a(j8, i8);
                        } else {
                            porterDuffColorFilter = new PorterDuffColorFilter(y0.l0.w(j8), y0.l0.z(i8));
                        }
                        mVar = new y0.m(j8, i8, porterDuffColorFilter);
                    } else {
                        j7 = j7;
                        mVar = null;
                    }
                    c0Var.f1880e.setValue(new x0.f(jF));
                    c0Var.f1881f.setValue(Boolean.FALSE);
                    e1.x xVar = c0Var.f1882g;
                    xVar.f1962g.setValue(mVar);
                    xVar.f1964i.setValue(new x0.f(jF2));
                    xVar.f1958c = str2;
                    vVar.f0(c0Var);
                    objK = c0Var;
                } else {
                    pVar5 = pVar5;
                    j7 = j7;
                }
                r0.p pVar7 = pVar5;
                long j10 = j7;
                a((e1.c0) objK, str, pVar7, j10, vVar, (i7 & 112) | 8 | (i7 & 896) | (i7 & 7168));
                pVar6 = pVar7;
                j9 = j10;
            } else {
                vVar.Q();
                pVar6 = pVar3;
                j9 = j6;
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new n0(eVar, str, pVar6, j9, i5, i6);
            }
        }
        i7 |= 384;
        pVar3 = pVar;
        if ((i5 & 3072) == 0) {
            j6 = j5;
            if ((i6 & 8) == 0) {
                i9 = 1024;
            } else {
                i9 = 1024;
            }
            i7 |= i9;
        } else {
            j6 = j5;
        }
        if ((i7 & 1171) == 1170) {
            vVar.S();
            if ((i5 & 1) != 0) {
                if (i10 != 0) {
                    pVar4 = r0.m.f5680a;
                } else {
                    pVar4 = pVar3;
                }
                if ((i6 & 8) != 0) {
                    i7 &= -7169;
                    pVar5 = pVar4;
                    j7 = ((y0.u) vVar.j(b0.f762a)).f6696a;
                } else {
                    j7 = j6;
                    pVar5 = pVar4;
                }
            } else {
                if (i10 != 0) {
                    pVar4 = r0.m.f5680a;
                } else {
                    pVar4 = pVar3;
                }
                if ((i6 & 8) != 0) {
                    i7 &= -7169;
                    pVar5 = pVar4;
                    j7 = ((y0.u) vVar.j(b0.f762a)).f6696a;
                } else {
                    j7 = j6;
                    pVar5 = pVar4;
                }
            }
            vVar.q();
            bVar = (k2.b) vVar.j(q1.j1.f5237f);
            zE = vVar.e((((long) Float.floatToRawIntBits(bVar.a())) & 4294967295L) | (((long) Float.floatToRawIntBits(eVar.f1909i)) << 32));
            objK = vVar.K();
            if (zE) {
                e1.b bVar3 = new e1.b();
                a.a.q(bVar3, eVar.f1906f);
                jF = p3.j.f(bVar.v(eVar.f1902b), bVar.v(eVar.f1903c));
                fD = eVar.f1904d;
                fB = eVar.f1905e;
                if (Float.isNaN(fD)) {
                    fD = x0.f.d(jF);
                }
                if (Float.isNaN(fB)) {
                    fB = x0.f.b(jF);
                }
                long jF3 = p3.j.f(fD, fB);
                e1.c0 c0Var2 = new e1.c0(bVar3);
                String str3 = eVar.f1901a;
                j8 = eVar.f1907g;
                i8 = eVar.f1908h;
                if (j8 != 16) {
                    if (Build.VERSION.SDK_INT >= 29) {
                        porterDuffColorFilter = y0.n.f6649a.a(j8, i8);
                    } else {
                        porterDuffColorFilter = new PorterDuffColorFilter(y0.l0.w(j8), y0.l0.z(i8));
                    }
                    mVar = new y0.m(j8, i8, porterDuffColorFilter);
                } else {
                    j7 = j7;
                    mVar = null;
                }
                c0Var2.f1880e.setValue(new x0.f(jF));
                c0Var2.f1881f.setValue(Boolean.FALSE);
                e1.x xVar2 = c0Var2.f1882g;
                xVar2.f1962g.setValue(mVar);
                xVar2.f1964i.setValue(new x0.f(jF3));
                xVar2.f1958c = str3;
                vVar.f0(c0Var2);
                objK = c0Var2;
            } else {
                e1.b bVar4 = new e1.b();
                a.a.q(bVar4, eVar.f1906f);
                jF = p3.j.f(bVar.v(eVar.f1902b), bVar.v(eVar.f1903c));
                fD = eVar.f1904d;
                fB = eVar.f1905e;
                if (Float.isNaN(fD)) {
                    fD = x0.f.d(jF);
                }
                if (Float.isNaN(fB)) {
                    fB = x0.f.b(jF);
                }
                long jF4 = p3.j.f(fD, fB);
                e1.c0 c0Var3 = new e1.c0(bVar4);
                String str4 = eVar.f1901a;
                j8 = eVar.f1907g;
                i8 = eVar.f1908h;
                if (j8 != 16) {
                    if (Build.VERSION.SDK_INT >= 29) {
                        porterDuffColorFilter = y0.n.f6649a.a(j8, i8);
                    } else {
                        porterDuffColorFilter = new PorterDuffColorFilter(y0.l0.w(j8), y0.l0.z(i8));
                    }
                    mVar = new y0.m(j8, i8, porterDuffColorFilter);
                } else {
                    j7 = j7;
                    mVar = null;
                }
                c0Var3.f1880e.setValue(new x0.f(jF));
                c0Var3.f1881f.setValue(Boolean.FALSE);
                e1.x xVar3 = c0Var3.f1882g;
                xVar3.f1962g.setValue(mVar);
                xVar3.f1964i.setValue(new x0.f(jF4));
                xVar3.f1958c = str4;
                vVar.f0(c0Var3);
                objK = c0Var3;
            }
            r0.p pVar8 = pVar5;
            long j11 = j7;
            a((e1.c0) objK, str, pVar8, j11, vVar, (i7 & 112) | 8 | (i7 & 896) | (i7 & 7168));
            pVar6 = pVar8;
            j9 = j11;
        } else {
            vVar.S();
            if ((i5 & 1) != 0) {
                if (i10 != 0) {
                    pVar4 = r0.m.f5680a;
                } else {
                    pVar4 = pVar3;
                }
                if ((i6 & 8) != 0) {
                    i7 &= -7169;
                    pVar5 = pVar4;
                    j7 = ((y0.u) vVar.j(b0.f762a)).f6696a;
                } else {
                    j7 = j6;
                    pVar5 = pVar4;
                }
            } else {
                if (i10 != 0) {
                    pVar4 = r0.m.f5680a;
                } else {
                    pVar4 = pVar3;
                }
                if ((i6 & 8) != 0) {
                    i7 &= -7169;
                    pVar5 = pVar4;
                    j7 = ((y0.u) vVar.j(b0.f762a)).f6696a;
                } else {
                    j7 = j6;
                    pVar5 = pVar4;
                }
            }
            vVar.q();
            bVar = (k2.b) vVar.j(q1.j1.f5237f);
            zE = vVar.e((((long) Float.floatToRawIntBits(bVar.a())) & 4294967295L) | (((long) Float.floatToRawIntBits(eVar.f1909i)) << 32));
            objK = vVar.K();
            if (zE) {
                e1.b bVar5 = new e1.b();
                a.a.q(bVar5, eVar.f1906f);
                jF = p3.j.f(bVar.v(eVar.f1902b), bVar.v(eVar.f1903c));
                fD = eVar.f1904d;
                fB = eVar.f1905e;
                if (Float.isNaN(fD)) {
                    fD = x0.f.d(jF);
                }
                if (Float.isNaN(fB)) {
                    fB = x0.f.b(jF);
                }
                long jF5 = p3.j.f(fD, fB);
                e1.c0 c0Var4 = new e1.c0(bVar5);
                String str5 = eVar.f1901a;
                j8 = eVar.f1907g;
                i8 = eVar.f1908h;
                if (j8 != 16) {
                    if (Build.VERSION.SDK_INT >= 29) {
                        porterDuffColorFilter = y0.n.f6649a.a(j8, i8);
                    } else {
                        porterDuffColorFilter = new PorterDuffColorFilter(y0.l0.w(j8), y0.l0.z(i8));
                    }
                    mVar = new y0.m(j8, i8, porterDuffColorFilter);
                } else {
                    j7 = j7;
                    mVar = null;
                }
                c0Var4.f1880e.setValue(new x0.f(jF));
                c0Var4.f1881f.setValue(Boolean.FALSE);
                e1.x xVar4 = c0Var4.f1882g;
                xVar4.f1962g.setValue(mVar);
                xVar4.f1964i.setValue(new x0.f(jF5));
                xVar4.f1958c = str5;
                vVar.f0(c0Var4);
                objK = c0Var4;
            } else {
                e1.b bVar6 = new e1.b();
                a.a.q(bVar6, eVar.f1906f);
                jF = p3.j.f(bVar.v(eVar.f1902b), bVar.v(eVar.f1903c));
                fD = eVar.f1904d;
                fB = eVar.f1905e;
                if (Float.isNaN(fD)) {
                    fD = x0.f.d(jF);
                }
                if (Float.isNaN(fB)) {
                    fB = x0.f.b(jF);
                }
                long jF6 = p3.j.f(fD, fB);
                e1.c0 c0Var5 = new e1.c0(bVar6);
                String str6 = eVar.f1901a;
                j8 = eVar.f1907g;
                i8 = eVar.f1908h;
                if (j8 != 16) {
                    if (Build.VERSION.SDK_INT >= 29) {
                        porterDuffColorFilter = y0.n.f6649a.a(j8, i8);
                    } else {
                        porterDuffColorFilter = new PorterDuffColorFilter(y0.l0.w(j8), y0.l0.z(i8));
                    }
                    mVar = new y0.m(j8, i8, porterDuffColorFilter);
                } else {
                    j7 = j7;
                    mVar = null;
                }
                c0Var5.f1880e.setValue(new x0.f(jF));
                c0Var5.f1881f.setValue(Boolean.FALSE);
                e1.x xVar5 = c0Var5.f1882g;
                xVar5.f1962g.setValue(mVar);
                xVar5.f1964i.setValue(new x0.f(jF6));
                xVar5.f1958c = str6;
                vVar.f0(c0Var5);
                objK = c0Var5;
            }
            r0.p pVar9 = pVar5;
            long j12 = j7;
            a((e1.c0) objK, str, pVar9, j12, vVar, (i7 & 112) | 8 | (i7 & 896) | (i7 & 7168));
            pVar6 = pVar9;
            j9 = j12;
        }
        b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new n0(eVar, str, pVar6, j9, i5, i6);
        }
    }
}
