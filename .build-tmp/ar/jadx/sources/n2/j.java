package n2;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import c0.c2;
import f0.b2;
import f0.i1;
import f0.j0;
import f0.v;
import f0.w1;
import java.util.UUID;
import n1.f0;
import q1.j1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j0 f4226a = new j0(a.f4195f);

    /* JADX WARN: Code duplicated, block: B:102:0x0245  */
    /* JADX WARN: Code duplicated, block: B:104:0x0253  */
    /* JADX WARN: Code duplicated, block: B:108:0x0265  */
    /* JADX WARN: Code duplicated, block: B:110:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x0041  */
    /* JADX WARN: Code duplicated, block: B:25:0x0047  */
    /* JADX WARN: Code duplicated, block: B:26:0x004a  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:32:0x0057  */
    /* JADX WARN: Code duplicated, block: B:33:0x005a  */
    /* JADX WARN: Code duplicated, block: B:41:0x0072 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:42:0x0074  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:46:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:47:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:50:0x010c  */
    /* JADX WARN: Code duplicated, block: B:51:0x010e  */
    /* JADX WARN: Code duplicated, block: B:54:0x0117  */
    /* JADX WARN: Code duplicated, block: B:55:0x0119  */
    /* JADX WARN: Code duplicated, block: B:58:0x012c  */
    /* JADX WARN: Code duplicated, block: B:61:0x0132  */
    /* JADX WARN: Code duplicated, block: B:62:0x013c  */
    /* JADX WARN: Code duplicated, block: B:66:0x0165  */
    /* JADX WARN: Code duplicated, block: B:67:0x0167  */
    /* JADX WARN: Code duplicated, block: B:70:0x016e  */
    /* JADX WARN: Code duplicated, block: B:71:0x0170  */
    /* JADX WARN: Code duplicated, block: B:74:0x0183 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:75:0x0185  */
    /* JADX WARN: Code duplicated, block: B:78:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:79:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:82:0x01ae A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:83:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:86:0x01c9 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:87:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:90:0x01e4 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:91:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:94:0x0206 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:95:0x0208  */
    /* JADX WARN: Code duplicated, block: B:98:0x022e  */
    /* JADX WARN: Code duplicated, block: B:99:0x0232  */
    public static final void a(s sVar, a4.a aVar, t tVar, n0.d dVar, f0.p pVar, int i5, int i6) {
        int i7;
        a4.a aVar2;
        int i8;
        a4.a aVar3;
        View view;
        k2.b bVar;
        String str;
        k2.k kVar;
        f0.t tVarU;
        i1 i1VarV;
        UUID uuid;
        Object objK;
        f0.h hVar;
        v vVar;
        boolean z2;
        String str2;
        a4.a aVar4;
        p pVar2;
        int i9;
        boolean z3;
        int i10;
        boolean z4;
        boolean zF;
        Object objK2;
        f0.h hVar2;
        a4.a aVar5;
        p pVar3;
        Object bVar2;
        k2.k kVar2;
        v vVar2;
        t3.c cVar;
        int i11;
        boolean z5;
        boolean z6;
        boolean zF2;
        Object objK3;
        boolean z7;
        boolean z8;
        Object objK4;
        boolean zH;
        Object objK5;
        boolean zH2;
        Object objK6;
        boolean zH3;
        Object objK7;
        int iHashCode;
        p1.n nVar;
        p1.h hVar3;
        a4.a aVar6;
        b2 b2VarR;
        int i12;
        int i13;
        s sVar2 = sVar;
        v vVar3 = (v) pVar;
        vVar3.X(-830247068);
        if ((i5 & 6) == 0) {
            i7 = (vVar3.f(sVar2) ? 4 : 2) | i5;
        } else {
            i7 = i5;
        }
        int i14 = i6 & 2;
        if (i14 == 0) {
            if ((i5 & 48) == 0) {
                aVar2 = aVar;
                i7 |= vVar3.h(aVar2) ? 32 : 16;
            }
            if ((i5 & 384) == 0) {
                if (vVar3.f(tVar)) {
                    i13 = 256;
                } else {
                    i13 = 128;
                }
                i7 |= i13;
            }
            if ((i5 & 3072) == 0) {
                if (vVar3.h(dVar)) {
                    i12 = 2048;
                } else {
                    i12 = 1024;
                }
                i7 |= i12;
            }
            i8 = i7;
            if ((i8 & 1171) == 1170 || !vVar3.z()) {
                if (i14 != 0) {
                    aVar3 = null;
                } else {
                    aVar3 = aVar2;
                }
                view = (View) vVar3.j(AndroidCompositionLocals_androidKt.f208f);
                bVar = (k2.b) vVar3.j(j1.f5237f);
                str = (String) vVar3.j(f4226a);
                kVar = (k2.k) vVar3.j(j1.f5243l);
                tVarU = f0.l.u(vVar3);
                i1VarV = f0.l.v(dVar, vVar3);
                uuid = (UUID) o0.g.b(new Object[0], null, a.f4196g, vVar3, 3072, 6);
                objK = vVar3.K();
                hVar = f0.o.f2281a;
                if (objK == hVar) {
                    a4.a aVar7 = aVar3;
                    z2 = true;
                    vVar = vVar3;
                    p pVar4 = new p(aVar7, tVar, str, view, bVar, sVar, uuid);
                    str2 = str;
                    aVar4 = aVar7;
                    sVar2 = sVar;
                    pVar4.h(tVarU, new n0.d(1302892335, true, new c0.l(pVar4, 7, i1VarV)));
                    vVar.f0(pVar4);
                    objK = pVar4;
                } else {
                    vVar = vVar3;
                    z2 = true;
                    str2 = str;
                    aVar4 = aVar3;
                }
                pVar2 = (p) objK;
                boolean zH4 = vVar.h(pVar2);
                i9 = i8 & 112;
                if (i9 == 32) {
                    z3 = z2;
                } else {
                    z3 = false;
                }
                boolean z9 = zH4 | z3;
                i10 = i8 & 896;
                if (i10 == 256) {
                    z4 = z2;
                } else {
                    z4 = false;
                }
                zF = z9 | z4 | vVar.f(str2) | vVar.f(kVar);
                objK2 = vVar.K();
                if (zF) {
                    hVar2 = hVar;
                } else {
                    hVar2 = hVar;
                    if (objK2 != hVar2) {
                        pVar3 = pVar2;
                        i11 = i8;
                        v vVar4 = vVar;
                        bVar2 = objK2;
                        vVar2 = vVar4;
                        kVar2 = kVar;
                        aVar5 = aVar4;
                        cVar = null;
                    }
                    f0.l.c(pVar3, (a4.c) bVar2, vVar2);
                    boolean zH5 = vVar2.h(pVar3);
                    if (i9 == 32) {
                        z5 = z2;
                    } else {
                        z5 = false;
                    }
                    boolean z10 = z5 | zH5;
                    if (i10 == 256) {
                        z6 = z2;
                    } else {
                        z6 = false;
                    }
                    zF2 = z10 | z6 | vVar2.f(str2) | vVar2.f(kVar2);
                    objK3 = vVar2.K();
                    if (zF2 || objK3 == hVar2) {
                        c cVar2 = new c(pVar3, aVar5, tVar, str2, kVar2);
                        vVar2.f0(cVar2);
                        objK3 = cVar2;
                    }
                    f0.l.g((a4.a) objK3, vVar2);
                    boolean zH6 = vVar2.h(pVar3);
                    if ((i11 & 14) == 4) {
                        z7 = z2;
                    } else {
                        z7 = false;
                    }
                    z8 = zH6 | z7;
                    objK4 = vVar2.K();
                    if (z8 || objK4 == hVar2) {
                        objK4 = new c2(pVar3, 9, sVar2);
                        vVar2.f0(objK4);
                    }
                    f0.l.c(sVar2, (a4.c) objK4, vVar2);
                    zH = vVar2.h(pVar3);
                    objK5 = vVar2.K();
                    if (zH || objK5 == hVar2) {
                        objK5 = new b0.t(pVar3, cVar, 9);
                        vVar2.f0(objK5);
                    }
                    f0.l.e((a4.e) objK5, vVar2, pVar3);
                    zH2 = vVar2.h(pVar3);
                    objK6 = vVar2.K();
                    if (zH2 || objK6 == hVar2) {
                        objK6 = new f(pVar3, 0);
                        vVar2.f0(objK6);
                    }
                    r0.p pVarD = androidx.compose.ui.layout.a.d(r0.m.f5680a, (a4.c) objK6);
                    zH3 = vVar2.h(pVar3) | vVar2.f(kVar2);
                    objK7 = vVar2.K();
                    if (zH3 || objK7 == hVar2) {
                        objK7 = new g(pVar3, kVar2);
                        vVar2.f0(objK7);
                    }
                    f0 f0Var = (f0) objK7;
                    iHashCode = Long.hashCode(vVar2.S);
                    w1 w1VarL = vVar2.l();
                    r0.p pVarC = r0.a.c(vVar2, pVarD);
                    p1.j.f4892c.getClass();
                    nVar = p1.i.f4867b;
                    vVar2.Y();
                    if (vVar2.R) {
                        vVar2.k(nVar);
                    } else {
                        vVar2.i0();
                    }
                    f0.l.x(p1.i.f4870e, vVar2, f0Var);
                    f0.l.x(p1.i.f4869d, vVar2, w1VarL);
                    hVar3 = p1.i.f4871f;
                    if (vVar2.R || !b4.l.a(vVar2.K(), Integer.valueOf(iHashCode))) {
                        b.b.i(iHashCode, vVar2, iHashCode, hVar3);
                    }
                    f0.l.x(p1.i.f4868c, vVar2, pVarC);
                    vVar2.p(z2);
                    aVar6 = aVar5;
                }
                vVar2 = vVar;
                pVar3 = pVar2;
                i11 = i8;
                aVar5 = aVar4;
                cVar = null;
                bVar2 = new b(pVar3, aVar5, tVar, str2, kVar, 0);
                kVar2 = kVar;
                vVar2.f0(bVar2);
                f0.l.c(pVar3, (a4.c) bVar2, vVar2);
                boolean zH7 = vVar2.h(pVar3);
                if (i9 == 32) {
                    z5 = z2;
                } else {
                    z5 = false;
                }
                boolean z11 = z5 | zH7;
                if (i10 == 256) {
                    z6 = z2;
                } else {
                    z6 = false;
                }
                zF2 = z11 | z6 | vVar2.f(str2) | vVar2.f(kVar2);
                objK3 = vVar2.K();
                if (zF2) {
                    c cVar3 = new c(pVar3, aVar5, tVar, str2, kVar2);
                    vVar2.f0(cVar3);
                    objK3 = cVar3;
                } else {
                    c cVar4 = new c(pVar3, aVar5, tVar, str2, kVar2);
                    vVar2.f0(cVar4);
                    objK3 = cVar4;
                }
                f0.l.g((a4.a) objK3, vVar2);
                boolean zH8 = vVar2.h(pVar3);
                if ((i11 & 14) == 4) {
                    z7 = z2;
                } else {
                    z7 = false;
                }
                z8 = zH8 | z7;
                objK4 = vVar2.K();
                if (z8) {
                    objK4 = new c2(pVar3, 9, sVar2);
                    vVar2.f0(objK4);
                } else {
                    objK4 = new c2(pVar3, 9, sVar2);
                    vVar2.f0(objK4);
                }
                f0.l.c(sVar2, (a4.c) objK4, vVar2);
                zH = vVar2.h(pVar3);
                objK5 = vVar2.K();
                if (zH) {
                    objK5 = new b0.t(pVar3, cVar, 9);
                    vVar2.f0(objK5);
                } else {
                    objK5 = new b0.t(pVar3, cVar, 9);
                    vVar2.f0(objK5);
                }
                f0.l.e((a4.e) objK5, vVar2, pVar3);
                zH2 = vVar2.h(pVar3);
                objK6 = vVar2.K();
                if (zH2) {
                    objK6 = new f(pVar3, 0);
                    vVar2.f0(objK6);
                } else {
                    objK6 = new f(pVar3, 0);
                    vVar2.f0(objK6);
                }
                r0.p pVarD2 = androidx.compose.ui.layout.a.d(r0.m.f5680a, (a4.c) objK6);
                zH3 = vVar2.h(pVar3) | vVar2.f(kVar2);
                objK7 = vVar2.K();
                if (zH3) {
                    objK7 = new g(pVar3, kVar2);
                    vVar2.f0(objK7);
                } else {
                    objK7 = new g(pVar3, kVar2);
                    vVar2.f0(objK7);
                }
                f0 f0Var2 = (f0) objK7;
                iHashCode = Long.hashCode(vVar2.S);
                w1 w1VarL2 = vVar2.l();
                r0.p pVarC2 = r0.a.c(vVar2, pVarD2);
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar2.Y();
                if (vVar2.R) {
                    vVar2.k(nVar);
                } else {
                    vVar2.i0();
                }
                f0.l.x(p1.i.f4870e, vVar2, f0Var2);
                f0.l.x(p1.i.f4869d, vVar2, w1VarL2);
                hVar3 = p1.i.f4871f;
                if (vVar2.R) {
                    b.b.i(iHashCode, vVar2, iHashCode, hVar3);
                } else {
                    b.b.i(iHashCode, vVar2, iHashCode, hVar3);
                }
                f0.l.x(p1.i.f4868c, vVar2, pVarC2);
                vVar2.p(z2);
                aVar6 = aVar5;
            } else {
                vVar3.Q();
                aVar6 = aVar2;
                vVar2 = vVar3;
            }
            b2VarR = vVar2.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new h(sVar2, aVar6, tVar, dVar, i5, i6);
            }
        }
        i7 |= 48;
        aVar2 = aVar;
        if ((i5 & 384) == 0) {
            if (vVar3.f(tVar)) {
                i13 = 256;
            } else {
                i13 = 128;
            }
            i7 |= i13;
        }
        if ((i5 & 3072) == 0) {
            if (vVar3.h(dVar)) {
                i12 = 2048;
            } else {
                i12 = 1024;
            }
            i7 |= i12;
        }
        i8 = i7;
        if ((i8 & 1171) == 1170) {
            if (i14 != 0) {
                aVar3 = null;
            } else {
                aVar3 = aVar2;
            }
            view = (View) vVar3.j(AndroidCompositionLocals_androidKt.f208f);
            bVar = (k2.b) vVar3.j(j1.f5237f);
            str = (String) vVar3.j(f4226a);
            kVar = (k2.k) vVar3.j(j1.f5243l);
            tVarU = f0.l.u(vVar3);
            i1VarV = f0.l.v(dVar, vVar3);
            uuid = (UUID) o0.g.b(new Object[0], null, a.f4196g, vVar3, 3072, 6);
            objK = vVar3.K();
            hVar = f0.o.f2281a;
            if (objK == hVar) {
                a4.a aVar8 = aVar3;
                z2 = true;
                vVar = vVar3;
                p pVar5 = new p(aVar8, tVar, str, view, bVar, sVar, uuid);
                str2 = str;
                aVar4 = aVar8;
                sVar2 = sVar;
                pVar5.h(tVarU, new n0.d(1302892335, true, new c0.l(pVar5, 7, i1VarV)));
                vVar.f0(pVar5);
                objK = pVar5;
            } else {
                vVar = vVar3;
                z2 = true;
                str2 = str;
                aVar4 = aVar3;
            }
            pVar2 = (p) objK;
            boolean zH9 = vVar.h(pVar2);
            i9 = i8 & 112;
            if (i9 == 32) {
                z3 = z2;
            } else {
                z3 = false;
            }
            boolean z12 = zH9 | z3;
            i10 = i8 & 896;
            if (i10 == 256) {
                z4 = z2;
            } else {
                z4 = false;
            }
            zF = z12 | z4 | vVar.f(str2) | vVar.f(kVar);
            objK2 = vVar.K();
            if (zF) {
                hVar2 = hVar;
                if (objK2 != hVar2) {
                    pVar3 = pVar2;
                    i11 = i8;
                    v vVar5 = vVar;
                    bVar2 = objK2;
                    vVar2 = vVar5;
                    kVar2 = kVar;
                    aVar5 = aVar4;
                    cVar = null;
                }
                f0.l.c(pVar3, (a4.c) bVar2, vVar2);
                boolean zH10 = vVar2.h(pVar3);
                if (i9 == 32) {
                    z5 = z2;
                } else {
                    z5 = false;
                }
                boolean z13 = z5 | zH10;
                if (i10 == 256) {
                    z6 = z2;
                } else {
                    z6 = false;
                }
                zF2 = z13 | z6 | vVar2.f(str2) | vVar2.f(kVar2);
                objK3 = vVar2.K();
                if (zF2) {
                    c cVar5 = new c(pVar3, aVar5, tVar, str2, kVar2);
                    vVar2.f0(cVar5);
                    objK3 = cVar5;
                } else {
                    c cVar6 = new c(pVar3, aVar5, tVar, str2, kVar2);
                    vVar2.f0(cVar6);
                    objK3 = cVar6;
                }
                f0.l.g((a4.a) objK3, vVar2);
                boolean zH11 = vVar2.h(pVar3);
                if ((i11 & 14) == 4) {
                    z7 = z2;
                } else {
                    z7 = false;
                }
                z8 = zH11 | z7;
                objK4 = vVar2.K();
                if (z8) {
                    objK4 = new c2(pVar3, 9, sVar2);
                    vVar2.f0(objK4);
                } else {
                    objK4 = new c2(pVar3, 9, sVar2);
                    vVar2.f0(objK4);
                }
                f0.l.c(sVar2, (a4.c) objK4, vVar2);
                zH = vVar2.h(pVar3);
                objK5 = vVar2.K();
                if (zH) {
                    objK5 = new b0.t(pVar3, cVar, 9);
                    vVar2.f0(objK5);
                } else {
                    objK5 = new b0.t(pVar3, cVar, 9);
                    vVar2.f0(objK5);
                }
                f0.l.e((a4.e) objK5, vVar2, pVar3);
                zH2 = vVar2.h(pVar3);
                objK6 = vVar2.K();
                if (zH2) {
                    objK6 = new f(pVar3, 0);
                    vVar2.f0(objK6);
                } else {
                    objK6 = new f(pVar3, 0);
                    vVar2.f0(objK6);
                }
                r0.p pVarD3 = androidx.compose.ui.layout.a.d(r0.m.f5680a, (a4.c) objK6);
                zH3 = vVar2.h(pVar3) | vVar2.f(kVar2);
                objK7 = vVar2.K();
                if (zH3) {
                    objK7 = new g(pVar3, kVar2);
                    vVar2.f0(objK7);
                } else {
                    objK7 = new g(pVar3, kVar2);
                    vVar2.f0(objK7);
                }
                f0 f0Var3 = (f0) objK7;
                iHashCode = Long.hashCode(vVar2.S);
                w1 w1VarL3 = vVar2.l();
                r0.p pVarC3 = r0.a.c(vVar2, pVarD3);
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar2.Y();
                if (vVar2.R) {
                    vVar2.k(nVar);
                } else {
                    vVar2.i0();
                }
                f0.l.x(p1.i.f4870e, vVar2, f0Var3);
                f0.l.x(p1.i.f4869d, vVar2, w1VarL3);
                hVar3 = p1.i.f4871f;
                if (vVar2.R) {
                    b.b.i(iHashCode, vVar2, iHashCode, hVar3);
                } else {
                    b.b.i(iHashCode, vVar2, iHashCode, hVar3);
                }
                f0.l.x(p1.i.f4868c, vVar2, pVarC3);
                vVar2.p(z2);
                aVar6 = aVar5;
            } else {
                hVar2 = hVar;
            }
            vVar2 = vVar;
            pVar3 = pVar2;
            i11 = i8;
            aVar5 = aVar4;
            cVar = null;
            bVar2 = new b(pVar3, aVar5, tVar, str2, kVar, 0);
            kVar2 = kVar;
            vVar2.f0(bVar2);
            f0.l.c(pVar3, (a4.c) bVar2, vVar2);
            boolean zH12 = vVar2.h(pVar3);
            if (i9 == 32) {
                z5 = z2;
            } else {
                z5 = false;
            }
            boolean z14 = z5 | zH12;
            if (i10 == 256) {
                z6 = z2;
            } else {
                z6 = false;
            }
            zF2 = z14 | z6 | vVar2.f(str2) | vVar2.f(kVar2);
            objK3 = vVar2.K();
            if (zF2) {
                c cVar7 = new c(pVar3, aVar5, tVar, str2, kVar2);
                vVar2.f0(cVar7);
                objK3 = cVar7;
            } else {
                c cVar8 = new c(pVar3, aVar5, tVar, str2, kVar2);
                vVar2.f0(cVar8);
                objK3 = cVar8;
            }
            f0.l.g((a4.a) objK3, vVar2);
            boolean zH13 = vVar2.h(pVar3);
            if ((i11 & 14) == 4) {
                z7 = z2;
            } else {
                z7 = false;
            }
            z8 = zH13 | z7;
            objK4 = vVar2.K();
            if (z8) {
                objK4 = new c2(pVar3, 9, sVar2);
                vVar2.f0(objK4);
            } else {
                objK4 = new c2(pVar3, 9, sVar2);
                vVar2.f0(objK4);
            }
            f0.l.c(sVar2, (a4.c) objK4, vVar2);
            zH = vVar2.h(pVar3);
            objK5 = vVar2.K();
            if (zH) {
                objK5 = new b0.t(pVar3, cVar, 9);
                vVar2.f0(objK5);
            } else {
                objK5 = new b0.t(pVar3, cVar, 9);
                vVar2.f0(objK5);
            }
            f0.l.e((a4.e) objK5, vVar2, pVar3);
            zH2 = vVar2.h(pVar3);
            objK6 = vVar2.K();
            if (zH2) {
                objK6 = new f(pVar3, 0);
                vVar2.f0(objK6);
            } else {
                objK6 = new f(pVar3, 0);
                vVar2.f0(objK6);
            }
            r0.p pVarD4 = androidx.compose.ui.layout.a.d(r0.m.f5680a, (a4.c) objK6);
            zH3 = vVar2.h(pVar3) | vVar2.f(kVar2);
            objK7 = vVar2.K();
            if (zH3) {
                objK7 = new g(pVar3, kVar2);
                vVar2.f0(objK7);
            } else {
                objK7 = new g(pVar3, kVar2);
                vVar2.f0(objK7);
            }
            f0 f0Var4 = (f0) objK7;
            iHashCode = Long.hashCode(vVar2.S);
            w1 w1VarL4 = vVar2.l();
            r0.p pVarC4 = r0.a.c(vVar2, pVarD4);
            p1.j.f4892c.getClass();
            nVar = p1.i.f4867b;
            vVar2.Y();
            if (vVar2.R) {
                vVar2.k(nVar);
            } else {
                vVar2.i0();
            }
            f0.l.x(p1.i.f4870e, vVar2, f0Var4);
            f0.l.x(p1.i.f4869d, vVar2, w1VarL4);
            hVar3 = p1.i.f4871f;
            if (vVar2.R) {
                b.b.i(iHashCode, vVar2, iHashCode, hVar3);
            } else {
                b.b.i(iHashCode, vVar2, iHashCode, hVar3);
            }
            f0.l.x(p1.i.f4868c, vVar2, pVarC4);
            vVar2.p(z2);
            aVar6 = aVar5;
        } else {
            if (i14 != 0) {
                aVar3 = null;
            } else {
                aVar3 = aVar2;
            }
            view = (View) vVar3.j(AndroidCompositionLocals_androidKt.f208f);
            bVar = (k2.b) vVar3.j(j1.f5237f);
            str = (String) vVar3.j(f4226a);
            kVar = (k2.k) vVar3.j(j1.f5243l);
            tVarU = f0.l.u(vVar3);
            i1VarV = f0.l.v(dVar, vVar3);
            uuid = (UUID) o0.g.b(new Object[0], null, a.f4196g, vVar3, 3072, 6);
            objK = vVar3.K();
            hVar = f0.o.f2281a;
            if (objK == hVar) {
                a4.a aVar9 = aVar3;
                z2 = true;
                vVar = vVar3;
                p pVar6 = new p(aVar9, tVar, str, view, bVar, sVar, uuid);
                str2 = str;
                aVar4 = aVar9;
                sVar2 = sVar;
                pVar6.h(tVarU, new n0.d(1302892335, true, new c0.l(pVar6, 7, i1VarV)));
                vVar.f0(pVar6);
                objK = pVar6;
            } else {
                vVar = vVar3;
                z2 = true;
                str2 = str;
                aVar4 = aVar3;
            }
            pVar2 = (p) objK;
            boolean zH14 = vVar.h(pVar2);
            i9 = i8 & 112;
            if (i9 == 32) {
                z3 = z2;
            } else {
                z3 = false;
            }
            boolean z15 = zH14 | z3;
            i10 = i8 & 896;
            if (i10 == 256) {
                z4 = z2;
            } else {
                z4 = false;
            }
            zF = z15 | z4 | vVar.f(str2) | vVar.f(kVar);
            objK2 = vVar.K();
            if (zF) {
                hVar2 = hVar;
                if (objK2 != hVar2) {
                    pVar3 = pVar2;
                    i11 = i8;
                    v vVar6 = vVar;
                    bVar2 = objK2;
                    vVar2 = vVar6;
                    kVar2 = kVar;
                    aVar5 = aVar4;
                    cVar = null;
                }
                f0.l.c(pVar3, (a4.c) bVar2, vVar2);
                boolean zH15 = vVar2.h(pVar3);
                if (i9 == 32) {
                    z5 = z2;
                } else {
                    z5 = false;
                }
                boolean z16 = z5 | zH15;
                if (i10 == 256) {
                    z6 = z2;
                } else {
                    z6 = false;
                }
                zF2 = z16 | z6 | vVar2.f(str2) | vVar2.f(kVar2);
                objK3 = vVar2.K();
                if (zF2) {
                    c cVar9 = new c(pVar3, aVar5, tVar, str2, kVar2);
                    vVar2.f0(cVar9);
                    objK3 = cVar9;
                } else {
                    c cVar10 = new c(pVar3, aVar5, tVar, str2, kVar2);
                    vVar2.f0(cVar10);
                    objK3 = cVar10;
                }
                f0.l.g((a4.a) objK3, vVar2);
                boolean zH16 = vVar2.h(pVar3);
                if ((i11 & 14) == 4) {
                    z7 = z2;
                } else {
                    z7 = false;
                }
                z8 = zH16 | z7;
                objK4 = vVar2.K();
                if (z8) {
                    objK4 = new c2(pVar3, 9, sVar2);
                    vVar2.f0(objK4);
                } else {
                    objK4 = new c2(pVar3, 9, sVar2);
                    vVar2.f0(objK4);
                }
                f0.l.c(sVar2, (a4.c) objK4, vVar2);
                zH = vVar2.h(pVar3);
                objK5 = vVar2.K();
                if (zH) {
                    objK5 = new b0.t(pVar3, cVar, 9);
                    vVar2.f0(objK5);
                } else {
                    objK5 = new b0.t(pVar3, cVar, 9);
                    vVar2.f0(objK5);
                }
                f0.l.e((a4.e) objK5, vVar2, pVar3);
                zH2 = vVar2.h(pVar3);
                objK6 = vVar2.K();
                if (zH2) {
                    objK6 = new f(pVar3, 0);
                    vVar2.f0(objK6);
                } else {
                    objK6 = new f(pVar3, 0);
                    vVar2.f0(objK6);
                }
                r0.p pVarD5 = androidx.compose.ui.layout.a.d(r0.m.f5680a, (a4.c) objK6);
                zH3 = vVar2.h(pVar3) | vVar2.f(kVar2);
                objK7 = vVar2.K();
                if (zH3) {
                    objK7 = new g(pVar3, kVar2);
                    vVar2.f0(objK7);
                } else {
                    objK7 = new g(pVar3, kVar2);
                    vVar2.f0(objK7);
                }
                f0 f0Var5 = (f0) objK7;
                iHashCode = Long.hashCode(vVar2.S);
                w1 w1VarL5 = vVar2.l();
                r0.p pVarC5 = r0.a.c(vVar2, pVarD5);
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar2.Y();
                if (vVar2.R) {
                    vVar2.k(nVar);
                } else {
                    vVar2.i0();
                }
                f0.l.x(p1.i.f4870e, vVar2, f0Var5);
                f0.l.x(p1.i.f4869d, vVar2, w1VarL5);
                hVar3 = p1.i.f4871f;
                if (vVar2.R) {
                    b.b.i(iHashCode, vVar2, iHashCode, hVar3);
                } else {
                    b.b.i(iHashCode, vVar2, iHashCode, hVar3);
                }
                f0.l.x(p1.i.f4868c, vVar2, pVarC5);
                vVar2.p(z2);
                aVar6 = aVar5;
            } else {
                hVar2 = hVar;
            }
            vVar2 = vVar;
            pVar3 = pVar2;
            i11 = i8;
            aVar5 = aVar4;
            cVar = null;
            bVar2 = new b(pVar3, aVar5, tVar, str2, kVar, 0);
            kVar2 = kVar;
            vVar2.f0(bVar2);
            f0.l.c(pVar3, (a4.c) bVar2, vVar2);
            boolean zH17 = vVar2.h(pVar3);
            if (i9 == 32) {
                z5 = z2;
            } else {
                z5 = false;
            }
            boolean z17 = z5 | zH17;
            if (i10 == 256) {
                z6 = z2;
            } else {
                z6 = false;
            }
            zF2 = z17 | z6 | vVar2.f(str2) | vVar2.f(kVar2);
            objK3 = vVar2.K();
            if (zF2) {
                c cVar11 = new c(pVar3, aVar5, tVar, str2, kVar2);
                vVar2.f0(cVar11);
                objK3 = cVar11;
            } else {
                c cVar12 = new c(pVar3, aVar5, tVar, str2, kVar2);
                vVar2.f0(cVar12);
                objK3 = cVar12;
            }
            f0.l.g((a4.a) objK3, vVar2);
            boolean zH18 = vVar2.h(pVar3);
            if ((i11 & 14) == 4) {
                z7 = z2;
            } else {
                z7 = false;
            }
            z8 = zH18 | z7;
            objK4 = vVar2.K();
            if (z8) {
                objK4 = new c2(pVar3, 9, sVar2);
                vVar2.f0(objK4);
            } else {
                objK4 = new c2(pVar3, 9, sVar2);
                vVar2.f0(objK4);
            }
            f0.l.c(sVar2, (a4.c) objK4, vVar2);
            zH = vVar2.h(pVar3);
            objK5 = vVar2.K();
            if (zH) {
                objK5 = new b0.t(pVar3, cVar, 9);
                vVar2.f0(objK5);
            } else {
                objK5 = new b0.t(pVar3, cVar, 9);
                vVar2.f0(objK5);
            }
            f0.l.e((a4.e) objK5, vVar2, pVar3);
            zH2 = vVar2.h(pVar3);
            objK6 = vVar2.K();
            if (zH2) {
                objK6 = new f(pVar3, 0);
                vVar2.f0(objK6);
            } else {
                objK6 = new f(pVar3, 0);
                vVar2.f0(objK6);
            }
            r0.p pVarD6 = androidx.compose.ui.layout.a.d(r0.m.f5680a, (a4.c) objK6);
            zH3 = vVar2.h(pVar3) | vVar2.f(kVar2);
            objK7 = vVar2.K();
            if (zH3) {
                objK7 = new g(pVar3, kVar2);
                vVar2.f0(objK7);
            } else {
                objK7 = new g(pVar3, kVar2);
                vVar2.f0(objK7);
            }
            f0 f0Var6 = (f0) objK7;
            iHashCode = Long.hashCode(vVar2.S);
            w1 w1VarL6 = vVar2.l();
            r0.p pVarC6 = r0.a.c(vVar2, pVarD6);
            p1.j.f4892c.getClass();
            nVar = p1.i.f4867b;
            vVar2.Y();
            if (vVar2.R) {
                vVar2.k(nVar);
            } else {
                vVar2.i0();
            }
            f0.l.x(p1.i.f4870e, vVar2, f0Var6);
            f0.l.x(p1.i.f4869d, vVar2, w1VarL6);
            hVar3 = p1.i.f4871f;
            if (vVar2.R) {
                b.b.i(iHashCode, vVar2, iHashCode, hVar3);
            } else {
                b.b.i(iHashCode, vVar2, iHashCode, hVar3);
            }
            f0.l.x(p1.i.f4868c, vVar2, pVarC6);
            vVar2.p(z2);
            aVar6 = aVar5;
        }
        b2VarR = vVar2.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new h(sVar2, aVar6, tVar, dVar, i5, i6);
        }
    }

    public static final boolean b(View view) {
        ViewGroup.LayoutParams layoutParams = view.getRootView().getLayoutParams();
        WindowManager.LayoutParams layoutParams2 = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        return (layoutParams2 == null || (layoutParams2.flags & 8192) == 0) ? false : true;
    }
}
