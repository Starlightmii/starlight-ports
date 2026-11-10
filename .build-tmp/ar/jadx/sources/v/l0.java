package v;

import android.text.Spanned;
import android.view.KeyEvent;
import androidx.compose.foundation.text.modifiers.TextStringSimpleElement;
import androidx.compose.ui.input.pointer.PointerHoverIconModifierElement;
import androidx.compose.ui.input.pointer.SuspendPointerInputElement;
import androidx.compose.ui.semantics.AppendedSemanticsElement;
import c0.c2;
import c0.s3;
import c0.w2;
import f0.b2;
import f0.i1;
import f0.i2;
import f0.s1;
import f0.w1;
import java.text.BreakIterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import q1.j1;
import q1.k2;
import q1.l2;
import q1.u2;
import q1.v2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i0 f5958a = new i0(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j1.a f5959b = new j1.a(1008);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c0.i0 f5960c = new c0.i0(0, 0);

    /* JADX WARN: Code duplicated, block: B:100:0x0178  */
    /* JADX WARN: Code duplicated, block: B:104:0x0184  */
    /* JADX WARN: Code duplicated, block: B:106:0x0194  */
    /* JADX WARN: Code duplicated, block: B:108:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:0x0082  */
    /* JADX WARN: Code duplicated, block: B:50:0x0086  */
    /* JADX WARN: Code duplicated, block: B:52:0x0089  */
    /* JADX WARN: Code duplicated, block: B:54:0x0091  */
    /* JADX WARN: Code duplicated, block: B:55:0x0094  */
    /* JADX WARN: Code duplicated, block: B:59:0x009c  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:77:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:81:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:86:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:88:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:89:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:92:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:94:0x014c  */
    /* JADX WARN: Code duplicated, block: B:95:0x0150  */
    /* JADX WARN: Code duplicated, block: B:98:0x016a  */
    public static final void a(String str, r0.p pVar, y1.h0 h0Var, int i5, boolean z2, int i6, int i7, f0.p pVar2, int i8, int i9) {
        int i10;
        y1.h0 h0Var2;
        int i11;
        int i12;
        boolean z3;
        int i13;
        int i14;
        int i15;
        int i16;
        boolean z4;
        int i17;
        int iHashCode;
        p1.n nVar;
        p1.h hVar;
        b2 b2VarR;
        int i18;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-1186827822);
        if ((i8 & 6) == 0) {
            i10 = (vVar.f(str) ? 4 : 2) | i8;
        } else {
            i10 = i8;
        }
        if ((i8 & 48) == 0) {
            i10 |= vVar.f(pVar) ? 32 : 16;
        }
        if ((i8 & 384) == 0) {
            h0Var2 = h0Var;
            i10 |= vVar.f(h0Var2) ? 256 : 128;
        } else {
            h0Var2 = h0Var;
        }
        if ((i9 & 8) != 0) {
            i10 |= 3072;
        } else if ((i8 & 3072) == 0) {
            i10 |= vVar.h(null) ? 2048 : 1024;
        }
        int i19 = i9 & 16;
        if (i19 == 0) {
            if ((i8 & 24576) == 0) {
                i11 = i5;
                i10 |= vVar.d(i11) ? 16384 : 8192;
            }
            i12 = i9 & 32;
            if (i12 != 0) {
                if ((196608 & i8) == 0) {
                    z3 = z2;
                    if (vVar.g(z3)) {
                        i13 = 131072;
                    } else {
                        i13 = 65536;
                    }
                    i10 |= i13;
                }
                if ((1572864 & i8) == 0) {
                    if (vVar.d(i6)) {
                        i18 = 1048576;
                    } else {
                        i18 = 524288;
                    }
                    i10 |= i18;
                }
                i14 = i9 & 128;
                if (i14 != 0) {
                    if ((12582912 & i8) == 0) {
                        i15 = i7;
                        if (vVar.d(i15)) {
                            i16 = 8388608;
                        } else {
                            i16 = 4194304;
                        }
                        i10 |= i16;
                    }
                    if (((i10 | 100663296) & 38347923) == 38347922 || !vVar.z()) {
                        if (i19 != 0) {
                            i11 = 1;
                        }
                        if (i12 != 0) {
                            z4 = true;
                        } else {
                            z4 = z3;
                        }
                        if (i14 != 0) {
                            i17 = 1;
                        } else {
                            i17 = i15;
                        }
                        s(i17, i6);
                        if (vVar.j(z.z.f7029a) != null) {
                            throw new ClassCastException();
                        }
                        vVar.V(-1588686502);
                        vVar.p(false);
                        vVar.V(-1587866335);
                        r0.p pVarE = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
                        vVar.p(false);
                        iHashCode = Long.hashCode(vVar.S);
                        r0.p pVarC = r0.a.c(vVar, pVarE);
                        w1 w1VarL = vVar.l();
                        p1.j.f4892c.getClass();
                        nVar = p1.i.f4867b;
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(nVar);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
                        f0.l.x(p1.i.f4869d, vVar, w1VarL);
                        f0.l.x(p1.i.f4868c, vVar, pVarC);
                        hVar = p1.i.f4871f;
                        if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                            b.b.i(iHashCode, vVar, iHashCode, hVar);
                        }
                        vVar.p(true);
                    } else {
                        vVar.Q();
                        z4 = z3;
                        i17 = i15;
                    }
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new h(str, pVar, h0Var, i11, z4, i6, i17, i8, i9);
                    }
                }
                i10 |= 12582912;
                i15 = i7;
                if (((i10 | 100663296) & 38347923) == 38347922) {
                    if (i19 != 0) {
                        i11 = 1;
                    }
                    if (i12 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i14 != 0) {
                        i17 = 1;
                    } else {
                        i17 = i15;
                    }
                    s(i17, i6);
                    if (vVar.j(z.z.f7029a) != null) {
                        throw new ClassCastException();
                    }
                    vVar.V(-1588686502);
                    vVar.p(false);
                    vVar.V(-1587866335);
                    r0.p pVarE2 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
                    vVar.p(false);
                    iHashCode = Long.hashCode(vVar.S);
                    r0.p pVarC2 = r0.a.c(vVar, pVarE2);
                    w1 w1VarL2 = vVar.l();
                    p1.j.f4892c.getClass();
                    nVar = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(nVar);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
                    f0.l.x(p1.i.f4869d, vVar, w1VarL2);
                    f0.l.x(p1.i.f4868c, vVar, pVarC2);
                    hVar = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar);
                    }
                    vVar.p(true);
                } else {
                    if (i19 != 0) {
                        i11 = 1;
                    }
                    if (i12 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i14 != 0) {
                        i17 = 1;
                    } else {
                        i17 = i15;
                    }
                    s(i17, i6);
                    if (vVar.j(z.z.f7029a) != null) {
                        throw new ClassCastException();
                    }
                    vVar.V(-1588686502);
                    vVar.p(false);
                    vVar.V(-1587866335);
                    r0.p pVarE3 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
                    vVar.p(false);
                    iHashCode = Long.hashCode(vVar.S);
                    r0.p pVarC3 = r0.a.c(vVar, pVarE3);
                    w1 w1VarL3 = vVar.l();
                    p1.j.f4892c.getClass();
                    nVar = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(nVar);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
                    f0.l.x(p1.i.f4869d, vVar, w1VarL3);
                    f0.l.x(p1.i.f4868c, vVar, pVarC3);
                    hVar = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar);
                    }
                    vVar.p(true);
                }
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new h(str, pVar, h0Var, i11, z4, i6, i17, i8, i9);
                }
            }
            i10 |= 196608;
            z3 = z2;
            if ((1572864 & i8) == 0) {
                if (vVar.d(i6)) {
                    i18 = 1048576;
                } else {
                    i18 = 524288;
                }
                i10 |= i18;
            }
            i14 = i9 & 128;
            if (i14 != 0) {
                if ((12582912 & i8) == 0) {
                    i15 = i7;
                    if (vVar.d(i15)) {
                        i16 = 8388608;
                    } else {
                        i16 = 4194304;
                    }
                    i10 |= i16;
                }
                if (((i10 | 100663296) & 38347923) == 38347922) {
                    if (i19 != 0) {
                        i11 = 1;
                    }
                    if (i12 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i14 != 0) {
                        i17 = 1;
                    } else {
                        i17 = i15;
                    }
                    s(i17, i6);
                    if (vVar.j(z.z.f7029a) != null) {
                        throw new ClassCastException();
                    }
                    vVar.V(-1588686502);
                    vVar.p(false);
                    vVar.V(-1587866335);
                    r0.p pVarE4 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
                    vVar.p(false);
                    iHashCode = Long.hashCode(vVar.S);
                    r0.p pVarC4 = r0.a.c(vVar, pVarE4);
                    w1 w1VarL4 = vVar.l();
                    p1.j.f4892c.getClass();
                    nVar = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(nVar);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
                    f0.l.x(p1.i.f4869d, vVar, w1VarL4);
                    f0.l.x(p1.i.f4868c, vVar, pVarC4);
                    hVar = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar);
                    }
                    vVar.p(true);
                } else {
                    if (i19 != 0) {
                        i11 = 1;
                    }
                    if (i12 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i14 != 0) {
                        i17 = 1;
                    } else {
                        i17 = i15;
                    }
                    s(i17, i6);
                    if (vVar.j(z.z.f7029a) != null) {
                        throw new ClassCastException();
                    }
                    vVar.V(-1588686502);
                    vVar.p(false);
                    vVar.V(-1587866335);
                    r0.p pVarE5 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
                    vVar.p(false);
                    iHashCode = Long.hashCode(vVar.S);
                    r0.p pVarC5 = r0.a.c(vVar, pVarE5);
                    w1 w1VarL5 = vVar.l();
                    p1.j.f4892c.getClass();
                    nVar = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(nVar);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
                    f0.l.x(p1.i.f4869d, vVar, w1VarL5);
                    f0.l.x(p1.i.f4868c, vVar, pVarC5);
                    hVar = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar);
                    }
                    vVar.p(true);
                }
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new h(str, pVar, h0Var, i11, z4, i6, i17, i8, i9);
                }
            }
            i10 |= 12582912;
            i15 = i7;
            if (((i10 | 100663296) & 38347923) == 38347922) {
                if (i19 != 0) {
                    i11 = 1;
                }
                if (i12 != 0) {
                    z4 = true;
                } else {
                    z4 = z3;
                }
                if (i14 != 0) {
                    i17 = 1;
                } else {
                    i17 = i15;
                }
                s(i17, i6);
                if (vVar.j(z.z.f7029a) != null) {
                    throw new ClassCastException();
                }
                vVar.V(-1588686502);
                vVar.p(false);
                vVar.V(-1587866335);
                r0.p pVarE6 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
                vVar.p(false);
                iHashCode = Long.hashCode(vVar.S);
                r0.p pVarC6 = r0.a.c(vVar, pVarE6);
                w1 w1VarL6 = vVar.l();
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(nVar);
                } else {
                    vVar.i0();
                }
                f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
                f0.l.x(p1.i.f4869d, vVar, w1VarL6);
                f0.l.x(p1.i.f4868c, vVar, pVarC6);
                hVar = p1.i.f4871f;
                if (vVar.R) {
                    b.b.i(iHashCode, vVar, iHashCode, hVar);
                } else {
                    b.b.i(iHashCode, vVar, iHashCode, hVar);
                }
                vVar.p(true);
            } else {
                if (i19 != 0) {
                    i11 = 1;
                }
                if (i12 != 0) {
                    z4 = true;
                } else {
                    z4 = z3;
                }
                if (i14 != 0) {
                    i17 = 1;
                } else {
                    i17 = i15;
                }
                s(i17, i6);
                if (vVar.j(z.z.f7029a) != null) {
                    throw new ClassCastException();
                }
                vVar.V(-1588686502);
                vVar.p(false);
                vVar.V(-1587866335);
                r0.p pVarE7 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
                vVar.p(false);
                iHashCode = Long.hashCode(vVar.S);
                r0.p pVarC7 = r0.a.c(vVar, pVarE7);
                w1 w1VarL7 = vVar.l();
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(nVar);
                } else {
                    vVar.i0();
                }
                f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
                f0.l.x(p1.i.f4869d, vVar, w1VarL7);
                f0.l.x(p1.i.f4868c, vVar, pVarC7);
                hVar = p1.i.f4871f;
                if (vVar.R) {
                    b.b.i(iHashCode, vVar, iHashCode, hVar);
                } else {
                    b.b.i(iHashCode, vVar, iHashCode, hVar);
                }
                vVar.p(true);
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new h(str, pVar, h0Var, i11, z4, i6, i17, i8, i9);
            }
        }
        i10 |= 24576;
        i11 = i5;
        i12 = i9 & 32;
        if (i12 != 0) {
            if ((196608 & i8) == 0) {
                z3 = z2;
                if (vVar.g(z3)) {
                    i13 = 131072;
                } else {
                    i13 = 65536;
                }
                i10 |= i13;
            }
            if ((1572864 & i8) == 0) {
                if (vVar.d(i6)) {
                    i18 = 1048576;
                } else {
                    i18 = 524288;
                }
                i10 |= i18;
            }
            i14 = i9 & 128;
            if (i14 != 0) {
                if ((12582912 & i8) == 0) {
                    i15 = i7;
                    if (vVar.d(i15)) {
                        i16 = 8388608;
                    } else {
                        i16 = 4194304;
                    }
                    i10 |= i16;
                }
                if (((i10 | 100663296) & 38347923) == 38347922) {
                    if (i19 != 0) {
                        i11 = 1;
                    }
                    if (i12 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i14 != 0) {
                        i17 = 1;
                    } else {
                        i17 = i15;
                    }
                    s(i17, i6);
                    if (vVar.j(z.z.f7029a) != null) {
                        throw new ClassCastException();
                    }
                    vVar.V(-1588686502);
                    vVar.p(false);
                    vVar.V(-1587866335);
                    r0.p pVarE8 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
                    vVar.p(false);
                    iHashCode = Long.hashCode(vVar.S);
                    r0.p pVarC8 = r0.a.c(vVar, pVarE8);
                    w1 w1VarL8 = vVar.l();
                    p1.j.f4892c.getClass();
                    nVar = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(nVar);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
                    f0.l.x(p1.i.f4869d, vVar, w1VarL8);
                    f0.l.x(p1.i.f4868c, vVar, pVarC8);
                    hVar = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar);
                    }
                    vVar.p(true);
                } else {
                    if (i19 != 0) {
                        i11 = 1;
                    }
                    if (i12 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i14 != 0) {
                        i17 = 1;
                    } else {
                        i17 = i15;
                    }
                    s(i17, i6);
                    if (vVar.j(z.z.f7029a) != null) {
                        throw new ClassCastException();
                    }
                    vVar.V(-1588686502);
                    vVar.p(false);
                    vVar.V(-1587866335);
                    r0.p pVarE9 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
                    vVar.p(false);
                    iHashCode = Long.hashCode(vVar.S);
                    r0.p pVarC9 = r0.a.c(vVar, pVarE9);
                    w1 w1VarL9 = vVar.l();
                    p1.j.f4892c.getClass();
                    nVar = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(nVar);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
                    f0.l.x(p1.i.f4869d, vVar, w1VarL9);
                    f0.l.x(p1.i.f4868c, vVar, pVarC9);
                    hVar = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar);
                    }
                    vVar.p(true);
                }
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new h(str, pVar, h0Var, i11, z4, i6, i17, i8, i9);
                }
            }
            i10 |= 12582912;
            i15 = i7;
            if (((i10 | 100663296) & 38347923) == 38347922) {
                if (i19 != 0) {
                    i11 = 1;
                }
                if (i12 != 0) {
                    z4 = true;
                } else {
                    z4 = z3;
                }
                if (i14 != 0) {
                    i17 = 1;
                } else {
                    i17 = i15;
                }
                s(i17, i6);
                if (vVar.j(z.z.f7029a) != null) {
                    throw new ClassCastException();
                }
                vVar.V(-1588686502);
                vVar.p(false);
                vVar.V(-1587866335);
                r0.p pVarE10 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
                vVar.p(false);
                iHashCode = Long.hashCode(vVar.S);
                r0.p pVarC10 = r0.a.c(vVar, pVarE10);
                w1 w1VarL10 = vVar.l();
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(nVar);
                } else {
                    vVar.i0();
                }
                f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
                f0.l.x(p1.i.f4869d, vVar, w1VarL10);
                f0.l.x(p1.i.f4868c, vVar, pVarC10);
                hVar = p1.i.f4871f;
                if (vVar.R) {
                    b.b.i(iHashCode, vVar, iHashCode, hVar);
                } else {
                    b.b.i(iHashCode, vVar, iHashCode, hVar);
                }
                vVar.p(true);
            } else {
                if (i19 != 0) {
                    i11 = 1;
                }
                if (i12 != 0) {
                    z4 = true;
                } else {
                    z4 = z3;
                }
                if (i14 != 0) {
                    i17 = 1;
                } else {
                    i17 = i15;
                }
                s(i17, i6);
                if (vVar.j(z.z.f7029a) != null) {
                    throw new ClassCastException();
                }
                vVar.V(-1588686502);
                vVar.p(false);
                vVar.V(-1587866335);
                r0.p pVarE11 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
                vVar.p(false);
                iHashCode = Long.hashCode(vVar.S);
                r0.p pVarC11 = r0.a.c(vVar, pVarE11);
                w1 w1VarL11 = vVar.l();
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(nVar);
                } else {
                    vVar.i0();
                }
                f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
                f0.l.x(p1.i.f4869d, vVar, w1VarL11);
                f0.l.x(p1.i.f4868c, vVar, pVarC11);
                hVar = p1.i.f4871f;
                if (vVar.R) {
                    b.b.i(iHashCode, vVar, iHashCode, hVar);
                } else {
                    b.b.i(iHashCode, vVar, iHashCode, hVar);
                }
                vVar.p(true);
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new h(str, pVar, h0Var, i11, z4, i6, i17, i8, i9);
            }
        }
        i10 |= 196608;
        z3 = z2;
        if ((1572864 & i8) == 0) {
            if (vVar.d(i6)) {
                i18 = 1048576;
            } else {
                i18 = 524288;
            }
            i10 |= i18;
        }
        i14 = i9 & 128;
        if (i14 != 0) {
            if ((12582912 & i8) == 0) {
                i15 = i7;
                if (vVar.d(i15)) {
                    i16 = 8388608;
                } else {
                    i16 = 4194304;
                }
                i10 |= i16;
            }
            if (((i10 | 100663296) & 38347923) == 38347922) {
                if (i19 != 0) {
                    i11 = 1;
                }
                if (i12 != 0) {
                    z4 = true;
                } else {
                    z4 = z3;
                }
                if (i14 != 0) {
                    i17 = 1;
                } else {
                    i17 = i15;
                }
                s(i17, i6);
                if (vVar.j(z.z.f7029a) != null) {
                    throw new ClassCastException();
                }
                vVar.V(-1588686502);
                vVar.p(false);
                vVar.V(-1587866335);
                r0.p pVarE12 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
                vVar.p(false);
                iHashCode = Long.hashCode(vVar.S);
                r0.p pVarC12 = r0.a.c(vVar, pVarE12);
                w1 w1VarL12 = vVar.l();
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(nVar);
                } else {
                    vVar.i0();
                }
                f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
                f0.l.x(p1.i.f4869d, vVar, w1VarL12);
                f0.l.x(p1.i.f4868c, vVar, pVarC12);
                hVar = p1.i.f4871f;
                if (vVar.R) {
                    b.b.i(iHashCode, vVar, iHashCode, hVar);
                } else {
                    b.b.i(iHashCode, vVar, iHashCode, hVar);
                }
                vVar.p(true);
            } else {
                if (i19 != 0) {
                    i11 = 1;
                }
                if (i12 != 0) {
                    z4 = true;
                } else {
                    z4 = z3;
                }
                if (i14 != 0) {
                    i17 = 1;
                } else {
                    i17 = i15;
                }
                s(i17, i6);
                if (vVar.j(z.z.f7029a) != null) {
                    throw new ClassCastException();
                }
                vVar.V(-1588686502);
                vVar.p(false);
                vVar.V(-1587866335);
                r0.p pVarE13 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
                vVar.p(false);
                iHashCode = Long.hashCode(vVar.S);
                r0.p pVarC13 = r0.a.c(vVar, pVarE13);
                w1 w1VarL13 = vVar.l();
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(nVar);
                } else {
                    vVar.i0();
                }
                f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
                f0.l.x(p1.i.f4869d, vVar, w1VarL13);
                f0.l.x(p1.i.f4868c, vVar, pVarC13);
                hVar = p1.i.f4871f;
                if (vVar.R) {
                    b.b.i(iHashCode, vVar, iHashCode, hVar);
                } else {
                    b.b.i(iHashCode, vVar, iHashCode, hVar);
                }
                vVar.p(true);
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new h(str, pVar, h0Var, i11, z4, i6, i17, i8, i9);
            }
        }
        i10 |= 12582912;
        i15 = i7;
        if (((i10 | 100663296) & 38347923) == 38347922) {
            if (i19 != 0) {
                i11 = 1;
            }
            if (i12 != 0) {
                z4 = true;
            } else {
                z4 = z3;
            }
            if (i14 != 0) {
                i17 = 1;
            } else {
                i17 = i15;
            }
            s(i17, i6);
            if (vVar.j(z.z.f7029a) != null) {
                throw new ClassCastException();
            }
            vVar.V(-1588686502);
            vVar.p(false);
            vVar.V(-1587866335);
            r0.p pVarE14 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
            vVar.p(false);
            iHashCode = Long.hashCode(vVar.S);
            r0.p pVarC14 = r0.a.c(vVar, pVarE14);
            w1 w1VarL14 = vVar.l();
            p1.j.f4892c.getClass();
            nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
            f0.l.x(p1.i.f4869d, vVar, w1VarL14);
            f0.l.x(p1.i.f4868c, vVar, pVarC14);
            hVar = p1.i.f4871f;
            if (vVar.R) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            } else {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            vVar.p(true);
        } else {
            if (i19 != 0) {
                i11 = 1;
            }
            if (i12 != 0) {
                z4 = true;
            } else {
                z4 = z3;
            }
            if (i14 != 0) {
                i17 = 1;
            } else {
                i17 = i15;
            }
            s(i17, i6);
            if (vVar.j(z.z.f7029a) != null) {
                throw new ClassCastException();
            }
            vVar.V(-1588686502);
            vVar.p(false);
            vVar.V(-1587866335);
            r0.p pVarE15 = androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 131071).e(new TextStringSimpleElement(str, h0Var2, (d2.d) vVar.j(j1.f5240i), i11, z4, i6, i17));
            vVar.p(false);
            iHashCode = Long.hashCode(vVar.S);
            r0.p pVarC15 = r0.a.c(vVar, pVarE15);
            w1 w1VarL15 = vVar.l();
            p1.j.f4892c.getClass();
            nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, d0.f5876a);
            f0.l.x(p1.i.f4869d, vVar, w1VarL15);
            f0.l.x(p1.i.f4868c, vVar, pVarC15);
            hVar = p1.i.f4871f;
            if (vVar.R) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            } else {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            vVar.p(true);
        }
        b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new h(str, pVar, h0Var, i11, z4, i6, i17, i8, i9);
        }
    }

    public static final void b(z.e0 e0Var, n0.d dVar, f0.p pVar, int i5) {
        int i6;
        n0.d dVar2;
        f0.v vVar = (f0.v) pVar;
        vVar.X(-1985516685);
        if ((i5 & 6) == 0) {
            i6 = (vVar.h(e0Var) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.h(dVar) ? 32 : 16;
        }
        if ((i6 & 19) == 18 && vVar.z()) {
            vVar.Q();
            dVar2 = dVar;
        } else {
            Object objK = vVar.K();
            f0.h hVar = f0.o.f2281a;
            if (objK == hVar) {
                objK = new o.j();
                vVar.f0(objK);
            }
            o.j jVar = (o.j) objK;
            Object objK2 = vVar.K();
            if (objK2 == hVar) {
                objK2 = new b0.a(24, jVar);
                vVar.f0(objK2);
            }
            dVar2 = dVar;
            a.a.c(jVar, (a4.a) objK2, new c2(e0Var, 28, jVar), null, e0Var.h(), dVar2, vVar, ((i6 << 12) & 458752) | 54);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new w2(i5, 4, e0Var, dVar2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:146:0x0229  */
    /* JADX WARN: Code duplicated, block: B:149:0x024a  */
    /* JADX WARN: Code duplicated, block: B:150:0x024d  */
    /* JADX WARN: Code duplicated, block: B:153:0x025b  */
    /* JADX WARN: Code duplicated, block: B:154:0x025e  */
    /* JADX WARN: Code duplicated, block: B:160:0x026e  */
    /* JADX WARN: Code duplicated, block: B:162:0x0274  */
    /* JADX WARN: Code duplicated, block: B:164:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:168:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:173:0x0315  */
    /* JADX WARN: Code duplicated, block: B:192:0x038e  */
    /* JADX WARN: Code duplicated, block: B:197:0x03a3  */
    /* JADX WARN: Code duplicated, block: B:200:0x03cd  */
    /* JADX WARN: Code duplicated, block: B:202:0x03d7  */
    /* JADX WARN: Code duplicated, block: B:204:0x03e3  */
    /* JADX WARN: Code duplicated, block: B:205:0x03f5  */
    /* JADX WARN: Code duplicated, block: B:208:0x03fa  */
    /* JADX WARN: Code duplicated, block: B:209:0x0403  */
    /* JADX WARN: Code duplicated, block: B:211:0x040b  */
    /* JADX WARN: Code duplicated, block: B:214:0x0420 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:218:0x0427  */
    /* JADX WARN: Code duplicated, block: B:221:0x043b  */
    /* JADX WARN: Code duplicated, block: B:224:0x0446  */
    /* JADX WARN: Code duplicated, block: B:227:0x0458  */
    /* JADX WARN: Code duplicated, block: B:229:0x045c  */
    /* JADX WARN: Code duplicated, block: B:232:0x0468  */
    /* JADX WARN: Code duplicated, block: B:235:0x0477  */
    /* JADX WARN: Code duplicated, block: B:238:0x04cc  */
    /* JADX WARN: Code duplicated, block: B:241:0x04e3  */
    /* JADX WARN: Code duplicated, block: B:244:0x04f9  */
    /* JADX WARN: Code duplicated, block: B:245:0x04fb  */
    /* JADX WARN: Code duplicated, block: B:248:0x0506  */
    /* JADX WARN: Code duplicated, block: B:249:0x0508  */
    /* JADX WARN: Code duplicated, block: B:252:0x051b  */
    /* JADX WARN: Code duplicated, block: B:253:0x051e  */
    /* JADX WARN: Code duplicated, block: B:256:0x052c  */
    /* JADX WARN: Code duplicated, block: B:260:0x053a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:263:0x0544 A[PHI: r28
      0x0544: PHI (r28v11 v.o0) = (r28v5 v.o0), (r28v13 v.o0) binds: [B:262:0x0542, B:259:0x0535] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:264:0x0546  */
    /* JADX WARN: Code duplicated, block: B:267:0x0567 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:270:0x058b  */
    /* JADX WARN: Code duplicated, block: B:273:0x05e6  */
    /* JADX WARN: Code duplicated, block: B:277:0x05f0  */
    /* JADX WARN: Code duplicated, block: B:279:0x05f6 A[PHI: r23
      0x05f6: PHI (r23v9 r0.p) = (r23v4 r0.p), (r23v11 r0.p) binds: [B:278:0x05f4, B:276:0x05ed] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:280:0x05f8  */
    /* JADX WARN: Code duplicated, block: B:283:0x0600  */
    /* JADX WARN: Code duplicated, block: B:285:0x0604  */
    /* JADX WARN: Code duplicated, block: B:286:0x0607  */
    /* JADX WARN: Code duplicated, block: B:287:0x0611  */
    /* JADX WARN: Code duplicated, block: B:291:0x063b A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:292:0x063d  */
    /* JADX WARN: Code duplicated, block: B:295:0x0665  */
    /* JADX WARN: Code duplicated, block: B:296:0x0667  */
    /* JADX WARN: Code duplicated, block: B:299:0x066d  */
    /* JADX WARN: Code duplicated, block: B:300:0x066f  */
    /* JADX WARN: Code duplicated, block: B:303:0x0681 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:304:0x0683  */
    /* JADX WARN: Code duplicated, block: B:309:0x06a0  */
    /* JADX WARN: Code duplicated, block: B:310:0x06ab  */
    /* JADX WARN: Code duplicated, block: B:313:0x06d3  */
    /* JADX WARN: Code duplicated, block: B:314:0x06d5  */
    /* JADX WARN: Code duplicated, block: B:317:0x06e2 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:318:0x06e4  */
    /* JADX WARN: Code duplicated, block: B:321:0x06fc  */
    /* JADX WARN: Code duplicated, block: B:322:0x06fe  */
    /* JADX WARN: Code duplicated, block: B:325:0x070f  */
    /* JADX WARN: Code duplicated, block: B:326:0x0711  */
    /* JADX WARN: Code duplicated, block: B:329:0x071e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:332:0x072c  */
    /* JADX WARN: Code duplicated, block: B:335:0x0751  */
    /* JADX WARN: Code duplicated, block: B:336:0x0753  */
    /* JADX WARN: Code duplicated, block: B:339:0x0759  */
    /* JADX WARN: Code duplicated, block: B:340:0x075b  */
    /* JADX WARN: Code duplicated, block: B:343:0x0767  */
    /* JADX WARN: Code duplicated, block: B:344:0x0769  */
    /* JADX WARN: Code duplicated, block: B:347:0x0780  */
    /* JADX WARN: Code duplicated, block: B:349:0x0786  */
    /* JADX WARN: Code duplicated, block: B:355:0x0794 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:356:0x0796  */
    /* JADX WARN: Code duplicated, block: B:361:0x07cb  */
    /* JADX WARN: Code duplicated, block: B:369:0x07f8  */
    /* JADX WARN: Code duplicated, block: B:372:0x07fd  */
    /* JADX WARN: Code duplicated, block: B:373:0x0813  */
    /* JADX WARN: Code duplicated, block: B:376:0x0823 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:377:0x0825  */
    /* JADX WARN: Code duplicated, block: B:380:0x083f  */
    /* JADX WARN: Code duplicated, block: B:381:0x0841  */
    /* JADX WARN: Code duplicated, block: B:384:0x0847  */
    /* JADX WARN: Code duplicated, block: B:386:0x084d  */
    /* JADX WARN: Code duplicated, block: B:392:0x085b A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:393:0x085d  */
    /* JADX WARN: Code duplicated, block: B:396:0x0872  */
    /* JADX WARN: Code duplicated, block: B:398:0x0876  */
    /* JADX WARN: Code duplicated, block: B:401:0x08a1  */
    /* JADX WARN: Code duplicated, block: B:403:0x08a7  */
    /* JADX WARN: Code duplicated, block: B:409:0x08bc A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:410:0x08be  */
    /* JADX WARN: Code duplicated, block: B:413:0x0921  */
    /* JADX WARN: Code duplicated, block: B:420:0x0941  */
    /* JADX WARN: Code duplicated, block: B:422:0x0944  */
    /* JADX WARN: Code duplicated, block: B:424:0x094a  */
    /* JADX WARN: Code duplicated, block: B:425:0x094c  */
    /* JADX WARN: Code duplicated, block: B:428:0x0959  */
    /* JADX WARN: Code duplicated, block: B:434:0x09c4  */
    public static final void c(e2.x xVar, a4.c cVar, r0.p pVar, y1.h0 h0Var, e2.f0 f0Var, a4.c cVar2, q.k kVar, y0.s0 s0Var, boolean z2, int i5, int i6, e2.n nVar, m0 m0Var, boolean z3, n0.d dVar, f0.p pVar2, int i7, int i8) {
        int i9;
        int i10;
        int i11;
        p.l0 l0Var;
        boolean zF;
        Object objK;
        c1 c1Var;
        int i12;
        int i13;
        int i14;
        int i15;
        Object objK2;
        e2.e0 e0VarL;
        e2.e0 e0Var;
        y1.f fVar;
        e2.r rVar;
        b2 b2VarW;
        boolean zF2;
        Object objK3;
        y1.h0 h0Var2;
        boolean z4;
        y1.g0 g0Var;
        k2.b bVar;
        d2.d dVar2;
        o0 o0Var;
        t0 t0Var;
        k2.b bVar2;
        a2.h hVar;
        e2.d0 d0Var;
        long j5;
        y1.g0 g0Var2;
        boolean z5;
        boolean z6;
        long j6;
        e2.x xVarA;
        e2.x xVar2;
        Object objK4;
        f1 f1Var;
        long jCurrentTimeMillis;
        Object objK5;
        z.e0 e0Var2;
        w0.n nVar2;
        Object objK6;
        i4.v vVar;
        Object objK7;
        s.c cVar3;
        int i16;
        boolean z7;
        int i17;
        boolean z8;
        boolean z9;
        int i18;
        o0 o0Var2;
        boolean z10;
        boolean zH;
        Object objK8;
        o0 o0Var3;
        e2.y yVar;
        int i19;
        e2.n nVar3;
        e2.x xVar3;
        s.c cVar4;
        boolean z11;
        r0.p pVarA;
        r0.p pVar3;
        boolean z12;
        boolean z13;
        Object objK9;
        f0.h hVar2;
        e2.r rVar2;
        f0.h hVar3;
        Object i2Var;
        boolean zH2;
        Object objK10;
        r0.m mVar;
        r0.p pVarA2;
        boolean z14;
        boolean z15;
        boolean zH3;
        Object objK11;
        r0.p pVar4;
        e2.r rVar3;
        a4.c cVar5;
        r0.p pVarA3;
        boolean z16;
        boolean zH4;
        Object objK12;
        boolean z17;
        boolean z18;
        boolean zH5;
        Object sVar;
        e2.r rVar4;
        z.e0 e0Var3;
        u2 u2Var;
        boolean z19;
        boolean z20;
        boolean z21;
        boolean zH6;
        Object objK13;
        w0.n nVar4;
        z.e0 e0Var4;
        e2.n nVar5;
        e2.r rVar5;
        AppendedSemanticsElement appendedSemanticsElement;
        boolean z22;
        e2.x xVar4;
        AppendedSemanticsElement appendedSemanticsElement2;
        r0.p pVarA4;
        boolean zH7;
        Object objK14;
        boolean z23;
        boolean z24;
        Object objK15;
        w0.n nVar6;
        boolean z25;
        e2.n nVar7;
        z.e0 e0Var5;
        boolean zH8;
        Object objK16;
        boolean z26;
        r0.p pVar5;
        f0.v vVar2;
        r0.p pVarA5;
        Long l5;
        int i20;
        y1.g0 g0Var3 = xVar.f2064c;
        y1.f fVar2 = xVar.f2062a;
        f0.v vVar3 = (f0.v) pVar2;
        vVar3.X(-958708118);
        if ((i7 & 6) == 0) {
            i9 = i7 | (vVar3.f(xVar) ? 4 : 2);
        } else {
            i9 = i7;
        }
        if ((i7 & 48) == 0) {
            i9 |= vVar3.h(cVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i9 |= vVar3.f(pVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i9 |= vVar3.f(h0Var) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i9 |= vVar3.f(f0Var) ? 16384 : 8192;
        }
        if ((i7 & 196608) == 0) {
            i9 |= vVar3.h(cVar2) ? 131072 : 65536;
        }
        if ((i7 & 1572864) == 0) {
            i9 |= vVar3.f(kVar) ? 1048576 : 524288;
        }
        if ((i7 & 12582912) == 0) {
            i9 |= vVar3.f(s0Var) ? 8388608 : 4194304;
        }
        if ((i7 & 100663296) == 0) {
            i9 |= vVar3.g(z2) ? 67108864 : 33554432;
        }
        if ((i7 & 805306368) == 0) {
            i9 |= vVar3.d(i5) ? 536870912 : 268435456;
        }
        int i21 = i9;
        if ((i8 & 6) == 0) {
            i10 = i8 | (vVar3.d(i6) ? 4 : 2);
        } else {
            i10 = i8;
        }
        if ((i8 & 48) == 0) {
            i10 |= vVar3.f(nVar) ? 32 : 16;
        }
        if ((i8 & 384) == 0) {
            i10 |= vVar3.f(m0Var) ? 256 : 128;
        }
        if ((i8 & 3072) == 0) {
            i10 |= vVar3.g(z3) ? 2048 : 1024;
        }
        if ((i8 & 24576) == 0) {
            i10 |= vVar3.g(false) ? 16384 : 8192;
        }
        if ((i8 & 196608) == 0) {
            i10 |= vVar3.h(dVar) ? 131072 : 65536;
        }
        int i22 = i10;
        if ((i21 & 306783379) == 306783378 && (74899 & i22) == 74898 && vVar3.z()) {
            vVar3.Q();
            vVar2 = vVar3;
        } else {
            vVar3.S();
            if ((i7 & 1) != 0 && !vVar3.x()) {
                vVar3.Q();
            }
            vVar3.q();
            Object objK17 = vVar3.K();
            f0.h hVar4 = f0.o.f2281a;
            if (objK17 == hVar4) {
                objK17 = new w0.n();
                vVar3.f0(objK17);
            }
            w0.n nVar8 = (w0.n) objK17;
            Object objK18 = vVar3.K();
            if (objK18 == hVar4) {
                x.s sVar2 = x.t.f6500a;
                objK18 = new x.c();
                vVar3.f0(objK18);
            }
            x.c cVar6 = (x.c) objK18;
            Object objK19 = vVar3.K();
            if (objK19 == hVar4) {
                objK19 = new e2.y(cVar6);
                vVar3.f0(objK19);
            }
            e2.y yVar2 = (e2.y) objK19;
            k2.b bVar3 = (k2.b) vVar3.j(j1.f5237f);
            d2.d dVar3 = (d2.d) vVar3.j(j1.f5240i);
            long j7 = ((z.k0) vVar3.j(z.l0.f6977a)).f6976b;
            w0.g gVar = (w0.g) vVar3.j(j1.f5238g);
            u2 u2Var2 = (u2) vVar3.j(j1.f5249r);
            k2 k2Var = (k2) vVar3.j(j1.f5245n);
            if (i5 != 1 || z2) {
                i11 = 1;
            } else {
                i11 = 1;
                if (nVar.f2039a) {
                    l0Var = p.l0.f4526e;
                }
                Object[] objArr = {l0Var};
                a2.h hVar5 = c1.f5868f;
                zF = vVar3.f(l0Var);
                objK = vVar3.K();
                if (zF || objK == hVar4) {
                    objK = new b0.a(26, l0Var);
                    vVar3.f0(objK);
                }
                c1Var = (c1) o0.g.b(objArr, hVar5, (a4.a) objK, vVar3, 0, 4);
                i12 = i21 & 14;
                if (i12 == 4) {
                    i13 = i11;
                } else {
                    i13 = 0;
                }
                if ((i21 & 57344) == 16384) {
                    i14 = i11;
                } else {
                    i14 = 0;
                }
                i15 = i14 | i13;
                objK2 = vVar3.K();
                if (i15 == 0 || objK2 == hVar4) {
                    e0VarL = l(f0Var, fVar2);
                    if (g0Var3 != null) {
                        long j8 = g0Var3.f6770a;
                        e2.r rVar6 = e0VarL.f2020b;
                        int i23 = y1.g0.f6769c;
                        int iB = rVar6.b((int) (j8 >> 32));
                        int iB2 = rVar6.b((int) (j8 & 4294967295L));
                        int iMin = Math.min(iB, iB2);
                        int iMax = Math.max(iB, iB2);
                        y1.c cVar7 = new y1.c(e0VarL.f2019a);
                        cVar7.a(new y1.a0(0L, 0L, (d2.k) null, (d2.i) null, (d2.j) null, (d2.p) null, (String) null, 0L, (j2.a) null, (j2.n) null, (f2.b) null, 0L, j2.j.f3323c, (y0.p0) null, 61439), iMin, iMax);
                        e0VarL = new e2.e0(cVar7.c(), rVar6);
                    }
                    objK2 = e0VarL;
                    vVar3.f0(objK2);
                } else {
                    nVar8 = nVar8;
                    fVar2 = fVar2;
                }
                e0Var = (e2.e0) objK2;
                fVar = e0Var.f2019a;
                rVar = e0Var.f2020b;
                b2VarW = vVar3.w();
                if (b2VarW != null) {
                    throw new IllegalStateException("no recompose scope found");
                }
                b2VarW.f2096b |= 1;
                zF2 = vVar3.f(k2Var);
                objK3 = vVar3.K();
                if (!zF2 || objK3 == hVar4) {
                    h0Var2 = h0Var;
                    z4 = z2;
                    t0 t0Var2 = new t0(fVar, h0Var2, z4, bVar3, dVar3, 0);
                    g0Var = g0Var3;
                    bVar = bVar3;
                    dVar2 = dVar3;
                    objK3 = new o0(t0Var2, b2VarW, k2Var);
                    vVar3.f0(objK3);
                } else {
                    h0Var2 = h0Var;
                    z4 = z2;
                    bVar = bVar3;
                    dVar2 = dVar3;
                    g0Var = g0Var3;
                }
                o0Var = (o0) objK3;
                o0Var.f6024s = cVar;
                o0Var.f6028w = j7;
                a1.b bVar4 = o0Var.f6023r;
                bVar4.f24e = m0Var;
                bVar4.f25f = gVar;
                o0Var.f6015j = fVar2;
                t0Var = o0Var.f6006a;
                if (!b4.l.a(t0Var.f6098a, fVar) && b4.l.a(t0Var.f6099b, h0Var2) && t0Var.f6102e == z4 && t0Var.f6103f == (i20 = i11) && t0Var.f6100c == Integer.MAX_VALUE && t0Var.f6101d == i20 && b4.l.a(t0Var.f6104g, bVar) && b4.l.a(t0Var.f6106i, q3.s.f5477d) && t0Var.f6105h == dVar2) {
                    bVar2 = bVar;
                } else {
                    bVar2 = bVar;
                    t0Var = new t0(fVar, h0Var2, z4, bVar2, dVar2, 0);
                }
                if (o0Var.f6006a != t0Var) {
                    o0Var.f6021p = true;
                }
                o0Var.f6006a = t0Var;
                hVar = o0Var.f6009d;
                d0Var = o0Var.f6010e;
                hVar.getClass();
                j5 = xVar.f2063b;
                g0Var2 = g0Var;
                boolean zA = b4.l.a(g0Var2, ((e2.j) hVar.f50f).c());
                if (b4.l.a(((e2.x) hVar.f49e).f2062a, fVar2)) {
                    if (y1.g0.a(((e2.x) hVar.f49e).f2063b, j5)) {
                        z5 = false;
                    } else {
                        ((e2.j) hVar.f50f).f(y1.g0.e(j5), y1.g0.d(j5));
                        z5 = false;
                        z6 = true;
                    }
                    if (g0Var2 == null) {
                        e2.j jVar = (e2.j) hVar.f50f;
                        jVar.f2030d = -1;
                        jVar.f2031e = -1;
                    } else {
                        j6 = g0Var2.f6770a;
                        if (!y1.g0.b(j6)) {
                            ((e2.j) hVar.f50f).e(y1.g0.e(j6), y1.g0.d(j6));
                        }
                    }
                    if (z5 && (z6 || zA)) {
                        xVarA = xVar;
                    } else {
                        e2.j jVar2 = (e2.j) hVar.f50f;
                        jVar2.f2030d = -1;
                        jVar2.f2031e = -1;
                        xVarA = e2.x.a(xVar, null, 0L, 3);
                    }
                    xVar2 = (e2.x) hVar.f49e;
                    hVar.f49e = xVarA;
                    if (d0Var != null) {
                        d0Var.a(xVar2, xVarA);
                    }
                    objK4 = vVar3.K();
                    if (objK4 == hVar4) {
                        objK4 = new f1();
                        vVar3.f0(objK4);
                    }
                    f1Var = (f1) objK4;
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (f1Var.f5925e) {
                        f1Var.f5924d = Long.valueOf(jCurrentTimeMillis);
                        f1Var.a(xVar);
                    } else {
                        l5 = f1Var.f5924d;
                        if (jCurrentTimeMillis > (l5 != null ? l5.longValue() : 0L) + ((long) 5000)) {
                            f1Var.f5924d = Long.valueOf(jCurrentTimeMillis);
                            f1Var.a(xVar);
                        }
                    }
                    objK5 = vVar3.K();
                    if (objK5 == hVar4) {
                        objK5 = new z.e0(f1Var);
                        vVar3.f0(objK5);
                    }
                    e0Var2 = (z.e0) objK5;
                    e0Var2.f6928b = rVar;
                    e0Var2.f6929c = o0Var.f6025t;
                    e0Var2.f6930d = o0Var;
                    e0Var2.f6931e.setValue(xVar);
                    e0Var2.f6932f = (q1.f1) vVar3.j(j1.f5235d);
                    e0Var2.f6933g = (l2) vVar3.j(j1.f5246o);
                    e0Var2.f6934h = (f1.a) vVar3.j(j1.f5241j);
                    nVar2 = nVar8;
                    e0Var2.f6935i = nVar2;
                    e0Var2.f6936j.setValue(true);
                    e0Var2.f6937k.setValue(Boolean.valueOf(z3));
                    objK6 = vVar3.K();
                    if (objK6 == hVar4) {
                        f0.i0 i0Var = new f0.i0(f0.l.l(vVar3));
                        vVar3.f0(i0Var);
                        objK6 = i0Var;
                    }
                    vVar = ((f0.i0) objK6).f2194d;
                    objK7 = vVar3.K();
                    if (objK7 == hVar4) {
                        objK7 = new s.c();
                        vVar3.f0(objK7);
                    }
                    cVar3 = (s.c) objK7;
                    boolean zH9 = vVar3.h(o0Var);
                    i16 = i22 & 7168;
                    if (i16 == 2048) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    boolean z27 = zH9 | z7;
                    i17 = i22 & 57344;
                    if (i17 == 16384) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    boolean zH10 = z8 | z27 | vVar3.h(yVar2);
                    if (i12 == 4) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    boolean z28 = zH10 | z9;
                    i18 = (i22 & 112) ^ 48;
                    if (i18 > 32 || !vVar3.f(nVar)) {
                        o0Var2 = o0Var;
                        if ((i22 & 48) != 32) {
                            z10 = false;
                        }
                        zH = z28 | z10 | vVar3.h(rVar) | vVar3.h(vVar) | vVar3.h(cVar3) | vVar3.h(e0Var2);
                        objK8 = vVar3.K();
                        if (!zH || objK8 == hVar4) {
                            o0Var3 = o0Var2;
                            yVar = yVar2;
                            i19 = 32;
                            nVar3 = nVar;
                            r rVar7 = new r(o0Var3, z3, yVar, xVar, nVar3, rVar, e0Var2, vVar, cVar3);
                            xVar3 = xVar;
                            cVar4 = cVar3;
                            z11 = z3;
                            vVar3.f0(rVar7);
                            objK8 = rVar7;
                        } else {
                            nVar3 = nVar;
                            o0Var3 = o0Var2;
                            i19 = 32;
                            z11 = z3;
                            cVar4 = cVar3;
                            xVar3 = xVar;
                            yVar = yVar2;
                        }
                        pVarA = androidx.compose.foundation.c.a(androidx.compose.ui.focus.a.b(androidx.compose.ui.focus.a.a(nVar2), (a4.c) objK8), z11, kVar);
                        i1 i1VarV = f0.l.v(Boolean.valueOf(z11), vVar3);
                        boolean zF3 = vVar3.f(i1VarV) | vVar3.h(o0Var3) | vVar3.h(yVar) | vVar3.h(e0Var2);
                        if (i18 > i19 || !vVar3.f(nVar3)) {
                            pVar3 = pVarA;
                            if ((i22 & 48) != i19) {
                                z12 = false;
                            }
                            z13 = z12 | zF3;
                            objK9 = vVar3.K();
                            if (z13) {
                                hVar2 = r1;
                            } else {
                                if (objK9 == hVar4) {
                                    hVar2 = hVar4;
                                } else {
                                    hVar3 = hVar4;
                                    rVar2 = rVar;
                                    i2Var = objK9;
                                }
                                f0.l.e((a4.e) i2Var, vVar3, p3.n.f5026a);
                                zH2 = vVar3.h(o0Var3);
                                objK10 = vVar3.K();
                                if (zH2 || objK10 == hVar3) {
                                    objK10 = new q(o0Var3, 1);
                                    vVar3.f0(objK10);
                                }
                                b0.t tVar = new b0.t((a4.c) objK10, null, 23);
                                mVar = r0.m.f5680a;
                                pVarA2 = j1.z.a(mVar, 8675309, tVar);
                                boolean zH11 = vVar3.h(o0Var3);
                                if (i17 == 16384) {
                                    z14 = true;
                                } else {
                                    z14 = false;
                                }
                                boolean z29 = zH11 | z14;
                                if (i16 == 2048) {
                                    z15 = true;
                                } else {
                                    z15 = false;
                                }
                                zH3 = z29 | z15 | vVar3.h(rVar2) | vVar3.h(e0Var2);
                                objK11 = vVar3.K();
                                if (!zH3 || objK11 == hVar3) {
                                    e2.r rVar8 = rVar2;
                                    pVar4 = pVarA2;
                                    c0.w0 w0Var = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar8);
                                    rVar3 = rVar8;
                                    vVar3.f0(w0Var);
                                    objK11 = w0Var;
                                } else {
                                    rVar3 = rVar2;
                                    pVar4 = pVarA2;
                                }
                                cVar5 = (a4.c) objK11;
                                if (z3) {
                                    pVarA3 = r0.a.a(pVar4, new h2.b(cVar5, 3, kVar));
                                } else {
                                    pVarA3 = pVar4;
                                }
                                a1.d dVar4 = e0Var2.f6947u;
                                z.c0 c0Var = e0Var2.f6946t;
                                r0.p pVarE = pVarA3.e(new SuspendPointerInputElement(dVar4, c0Var, new b0.d(dVar4, c0Var, (t3.c) null, 12), 4)).e(new PointerHoverIconModifierElement());
                                boolean zH12 = vVar3.h(o0Var3);
                                if (i12 == 4) {
                                    z16 = true;
                                } else {
                                    z16 = false;
                                }
                                zH4 = zH12 | z16 | vVar3.h(rVar3);
                                objK12 = vVar3.K();
                                if (zH4 || objK12 == hVar3) {
                                    objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                                    vVar3.f0(objK12);
                                }
                                r0.p pVarA6 = androidx.compose.ui.draw.a.a(mVar, (a4.c) objK12);
                                boolean zH13 = vVar3.h(o0Var3);
                                if (i16 == 2048) {
                                    z17 = true;
                                } else {
                                    z17 = false;
                                }
                                boolean zF4 = zH13 | z17 | vVar3.f(u2Var2) | vVar3.h(e0Var2);
                                if (i12 == 4) {
                                    z18 = true;
                                } else {
                                    z18 = false;
                                }
                                zH5 = zF4 | z18 | vVar3.h(rVar3);
                                Object objK20 = vVar3.K();
                                if (!zH5 || objK20 == hVar3) {
                                    rVar4 = rVar3;
                                    e0Var3 = e0Var2;
                                    sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                                    u2Var = u2Var2;
                                    vVar3.f0(sVar);
                                } else {
                                    u2Var = u2Var2;
                                    sVar = objK20;
                                    rVar4 = rVar3;
                                    e0Var3 = e0Var2;
                                }
                                r0.p pVarD = androidx.compose.ui.layout.a.d(mVar, (a4.c) sVar);
                                boolean zH14 = vVar3.h(e0Var);
                                if (r3 == 4) {
                                    z19 = true;
                                } else {
                                    z19 = false;
                                }
                                boolean z30 = zH14 | z19;
                                if (i16 == 2048) {
                                    z20 = true;
                                } else {
                                    z20 = false;
                                }
                                boolean zG = z30 | z20 | vVar3.g(false);
                                if (i17 == 16384) {
                                    z21 = true;
                                } else {
                                    z21 = false;
                                }
                                zH6 = zG | z21 | vVar3.h(o0Var3) | vVar3.h(rVar4) | vVar3.h(e0Var3) | ((i18 <= 32 && vVar3.f(nVar)) || (i22 & 48) == 32);
                                objK13 = vVar3.K();
                                if (!zH6 || objK13 == hVar3) {
                                    nVar4 = nVar2;
                                    e0Var4 = e0Var3;
                                    o0 o0Var4 = o0Var3;
                                    w wVar = new w(e0Var, xVar, z3, nVar, o0Var4, rVar4, e0Var4, nVar4);
                                    e2.r rVar9 = rVar4;
                                    nVar5 = nVar;
                                    rVar5 = rVar9;
                                    o0Var3 = o0Var4;
                                    vVar3.f0(wVar);
                                    objK13 = wVar;
                                } else {
                                    nVar4 = nVar2;
                                    e0Var4 = e0Var3;
                                    rVar5 = rVar4;
                                    nVar5 = nVar;
                                }
                                AtomicInteger atomicInteger = w1.k.f6311a;
                                appendedSemanticsElement = new AppendedSemanticsElement((a4.c) objK13, true);
                                if (!z3 && ((v2) u2Var).a() && y1.g0.b(((y1.g0) o0Var3.f6029x.getValue()).f6770a) && y1.g0.b(((y1.g0) o0Var3.f6030y.getValue()).f6770a)) {
                                    z22 = true;
                                } else {
                                    z22 = false;
                                }
                                float f5 = v0.f6117a;
                                if (z22) {
                                    xVar4 = xVar;
                                    appendedSemanticsElement2 = appendedSemanticsElement;
                                    o0 o0Var5 = o0Var3;
                                    o0Var3 = o0Var5;
                                    pVarA4 = r0.a.a(mVar, new c0.u2(s0Var, o0Var5, xVar4, rVar5, 1));
                                } else {
                                    xVar4 = xVar;
                                    appendedSemanticsElement2 = appendedSemanticsElement;
                                    pVarA4 = mVar;
                                }
                                zH7 = vVar3.h(e0Var4);
                                objK14 = vVar3.K();
                                if (zH7 || objK14 == hVar3) {
                                    objK14 = new j(e0Var4, 0);
                                    vVar3.f0(objK14);
                                }
                                f0.l.c(e0Var4, (a4.c) objK14, vVar3);
                                boolean zH15 = vVar3.h(o0Var3) | vVar3.h(yVar);
                                if (i12 == 4) {
                                    z23 = true;
                                } else {
                                    z23 = false;
                                }
                                z24 = zH15 | z23 | ((i18 <= 32 && vVar3.f(nVar5)) || (i22 & 48) == 32);
                                objK15 = vVar3.K();
                                if (z24 || objK15 == hVar3) {
                                    objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                                    vVar3.f0(objK15);
                                }
                                f0.l.c(nVar5, (a4.c) objK15, vVar3);
                                nVar6 = nVar4;
                                q qVar = o0Var3.f6025t;
                                if (i5 == 1) {
                                    z25 = true;
                                } else {
                                    z25 = false;
                                }
                                f0.h hVar6 = hVar3;
                                int i24 = nVar5.f2043e;
                                nVar7 = nVar5;
                                e2.r rVar10 = rVar5;
                                e0Var5 = e0Var4;
                                r0.p pVarA7 = r0.a.a(mVar, new y0(o0Var3, e0Var5, xVar4, true, z25, rVar10, f1Var, qVar, i24));
                                boolean zBooleanValue = ((Boolean) i1VarV.getValue()).booleanValue();
                                zH8 = vVar3.h(o0Var3) | ((i18 <= 32 && vVar3.f(nVar7)) || (i22 & 48) == 32) | vVar3.h(cVar6);
                                objK16 = vVar3.K();
                                if (zH8 || objK16 == hVar6) {
                                    objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                                    vVar3.f0(objK16);
                                }
                                r0.p pVarD2 = androidx.compose.ui.layout.a.d(r0.a.a(androidx.compose.ui.input.key.a.b(androidx.compose.ui.input.key.a.b(androidx.compose.foundation.text.input.internal.a.a(pVar, cVar6, o0Var3, e0Var5).e(androidx.compose.foundation.text.handwriting.a.a((a4.a) objK16, zBooleanValue)).e(pVar3), new c2(gVar, 23, o0Var3)), new c2(o0Var3, 22, e0Var5)).e(pVarA7), new n.r(c1Var, z3, kVar)).e(pVarE).e(appendedSemanticsElement2), new q(o0Var3, 0));
                                if (!z3 && o0Var3.b() && ((Boolean) o0Var3.f6022q.getValue()).booleanValue() && ((v2) u2Var).a()) {
                                    z26 = true;
                                } else {
                                    z26 = false;
                                }
                                if (z26) {
                                    if (n.q0.a()) {
                                        pVarA5 = r0.a.a(mVar, new s3(6, e0Var5));
                                    } else {
                                        pVarA5 = mVar;
                                    }
                                    pVar5 = pVarA5;
                                } else {
                                    pVar5 = mVar;
                                }
                                vVar2 = vVar3;
                                d(pVarD2, e0Var5, n0.i.c(-374338080, new o(dVar, o0Var3, h0Var, i6, i5, c1Var, xVar, f0Var, pVarA4, pVarA6, pVarD, pVar5, cVar4, e0Var5, z26, cVar2, rVar10, bVar2), vVar2), vVar2, 384);
                            }
                            rVar2 = rVar;
                            hVar3 = hVar2;
                            i2Var = new i2(o0Var3, i1VarV, yVar, e0Var2, nVar3, null, 3);
                            vVar3.f0(i2Var);
                            f0.l.e((a4.e) i2Var, vVar3, p3.n.f5026a);
                            zH2 = vVar3.h(o0Var3);
                            objK10 = vVar3.K();
                            if (zH2) {
                                objK10 = new q(o0Var3, 1);
                                vVar3.f0(objK10);
                            } else {
                                objK10 = new q(o0Var3, 1);
                                vVar3.f0(objK10);
                            }
                            b0.t tVar2 = new b0.t((a4.c) objK10, null, 23);
                            mVar = r0.m.f5680a;
                            pVarA2 = j1.z.a(mVar, 8675309, tVar2);
                            boolean zH16 = vVar3.h(o0Var3);
                            if (i17 == 16384) {
                                z14 = true;
                            } else {
                                z14 = false;
                            }
                            boolean z210 = zH16 | z14;
                            if (i16 == 2048) {
                                z15 = true;
                            } else {
                                z15 = false;
                            }
                            zH3 = z210 | z15 | vVar3.h(rVar2) | vVar3.h(e0Var2);
                            objK11 = vVar3.K();
                            if (zH3) {
                                e2.r rVar11 = rVar2;
                                pVar4 = pVarA2;
                                c0.w0 w0Var2 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar11);
                                rVar3 = rVar11;
                                vVar3.f0(w0Var2);
                                objK11 = w0Var2;
                            } else {
                                e2.r rVar12 = rVar2;
                                pVar4 = pVarA2;
                                c0.w0 w0Var3 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar12);
                                rVar3 = rVar12;
                                vVar3.f0(w0Var3);
                                objK11 = w0Var3;
                            }
                            cVar5 = (a4.c) objK11;
                            if (z3) {
                                pVarA3 = r0.a.a(pVar4, new h2.b(cVar5, 3, kVar));
                            } else {
                                pVarA3 = pVar4;
                            }
                            a1.d dVar5 = e0Var2.f6947u;
                            z.c0 c0Var2 = e0Var2.f6946t;
                            r0.p pVarE2 = pVarA3.e(new SuspendPointerInputElement(dVar5, c0Var2, new b0.d(dVar5, c0Var2, (t3.c) null, 12), 4)).e(new PointerHoverIconModifierElement());
                            boolean zH17 = vVar3.h(o0Var3);
                            if (i12 == 4) {
                                z16 = true;
                            } else {
                                z16 = false;
                            }
                            zH4 = zH17 | z16 | vVar3.h(rVar3);
                            objK12 = vVar3.K();
                            if (zH4) {
                                objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                                vVar3.f0(objK12);
                            } else {
                                objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                                vVar3.f0(objK12);
                            }
                            r0.p pVarA8 = androidx.compose.ui.draw.a.a(mVar, (a4.c) objK12);
                            boolean zH18 = vVar3.h(o0Var3);
                            if (i16 == 2048) {
                                z17 = true;
                            } else {
                                z17 = false;
                            }
                            boolean zF5 = zH18 | z17 | vVar3.f(u2Var2) | vVar3.h(e0Var2);
                            if (i12 == 4) {
                                z18 = true;
                            } else {
                                z18 = false;
                            }
                            zH5 = zF5 | z18 | vVar3.h(rVar3);
                            Object objK21 = vVar3.K();
                            if (zH5) {
                                rVar4 = rVar3;
                                e0Var3 = e0Var2;
                                sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                                u2Var = u2Var2;
                                vVar3.f0(sVar);
                            } else {
                                rVar4 = rVar3;
                                e0Var3 = e0Var2;
                                sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                                u2Var = u2Var2;
                                vVar3.f0(sVar);
                            }
                            r0.p pVarD3 = androidx.compose.ui.layout.a.d(mVar, (a4.c) sVar);
                            boolean zH19 = vVar3.h(e0Var);
                            if (r3 == 4) {
                                z19 = true;
                            } else {
                                z19 = false;
                            }
                            boolean z31 = zH19 | z19;
                            if (i16 == 2048) {
                                z20 = true;
                            } else {
                                z20 = false;
                            }
                            boolean zG2 = z31 | z20 | vVar3.g(false);
                            if (i17 == 16384) {
                                z21 = true;
                            } else {
                                z21 = false;
                            }
                            zH6 = zG2 | z21 | vVar3.h(o0Var3) | vVar3.h(rVar4) | vVar3.h(e0Var3) | ((i18 <= 32 && vVar3.f(nVar)) || (i22 & 48) == 32);
                            objK13 = vVar3.K();
                            if (zH6) {
                                nVar4 = nVar2;
                                e0Var4 = e0Var3;
                                o0 o0Var6 = o0Var3;
                                w wVar2 = new w(e0Var, xVar, z3, nVar, o0Var6, rVar4, e0Var4, nVar4);
                                e2.r rVar13 = rVar4;
                                nVar5 = nVar;
                                rVar5 = rVar13;
                                o0Var3 = o0Var6;
                                vVar3.f0(wVar2);
                                objK13 = wVar2;
                            } else {
                                nVar4 = nVar2;
                                e0Var4 = e0Var3;
                                o0 o0Var7 = o0Var3;
                                w wVar3 = new w(e0Var, xVar, z3, nVar, o0Var7, rVar4, e0Var4, nVar4);
                                e2.r rVar14 = rVar4;
                                nVar5 = nVar;
                                rVar5 = rVar14;
                                o0Var3 = o0Var7;
                                vVar3.f0(wVar3);
                                objK13 = wVar3;
                            }
                            AtomicInteger atomicInteger2 = w1.k.f6311a;
                            appendedSemanticsElement = new AppendedSemanticsElement((a4.c) objK13, true);
                            if (!z3) {
                                z22 = false;
                            } else {
                                z22 = false;
                            }
                            float f6 = v0.f6117a;
                            if (z22) {
                                xVar4 = xVar;
                                appendedSemanticsElement2 = appendedSemanticsElement;
                                o0 o0Var8 = o0Var3;
                                o0Var3 = o0Var8;
                                pVarA4 = r0.a.a(mVar, new c0.u2(s0Var, o0Var8, xVar4, rVar5, 1));
                            } else {
                                xVar4 = xVar;
                                appendedSemanticsElement2 = appendedSemanticsElement;
                                pVarA4 = mVar;
                            }
                            zH7 = vVar3.h(e0Var4);
                            objK14 = vVar3.K();
                            if (zH7) {
                                objK14 = new j(e0Var4, 0);
                                vVar3.f0(objK14);
                            } else {
                                objK14 = new j(e0Var4, 0);
                                vVar3.f0(objK14);
                            }
                            f0.l.c(e0Var4, (a4.c) objK14, vVar3);
                            boolean zH110 = vVar3.h(o0Var3) | vVar3.h(yVar);
                            if (i12 == 4) {
                                z23 = true;
                            } else {
                                z23 = false;
                            }
                            z24 = zH110 | z23 | ((i18 <= 32 && vVar3.f(nVar5)) || (i22 & 48) == 32);
                            objK15 = vVar3.K();
                            if (z24) {
                                objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                                vVar3.f0(objK15);
                            } else {
                                objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                                vVar3.f0(objK15);
                            }
                            f0.l.c(nVar5, (a4.c) objK15, vVar3);
                            nVar6 = nVar4;
                            q qVar2 = o0Var3.f6025t;
                            if (i5 == 1) {
                                z25 = true;
                            } else {
                                z25 = false;
                            }
                            f0.h hVar7 = hVar3;
                            int i25 = nVar5.f2043e;
                            nVar7 = nVar5;
                            e2.r rVar15 = rVar5;
                            e0Var5 = e0Var4;
                            r0.p pVarA9 = r0.a.a(mVar, new y0(o0Var3, e0Var5, xVar4, true, z25, rVar15, f1Var, qVar2, i25));
                            boolean zBooleanValue2 = ((Boolean) i1VarV.getValue()).booleanValue();
                            zH8 = vVar3.h(o0Var3) | ((i18 <= 32 && vVar3.f(nVar7)) || (i22 & 48) == 32) | vVar3.h(cVar6);
                            objK16 = vVar3.K();
                            if (zH8) {
                                objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                                vVar3.f0(objK16);
                            } else {
                                objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                                vVar3.f0(objK16);
                            }
                            r0.p pVarD4 = androidx.compose.ui.layout.a.d(r0.a.a(androidx.compose.ui.input.key.a.b(androidx.compose.ui.input.key.a.b(androidx.compose.foundation.text.input.internal.a.a(pVar, cVar6, o0Var3, e0Var5).e(androidx.compose.foundation.text.handwriting.a.a((a4.a) objK16, zBooleanValue2)).e(pVar3), new c2(gVar, 23, o0Var3)), new c2(o0Var3, 22, e0Var5)).e(pVarA9), new n.r(c1Var, z3, kVar)).e(pVarE2).e(appendedSemanticsElement2), new q(o0Var3, 0));
                            if (!z3) {
                                z26 = false;
                            } else {
                                z26 = false;
                            }
                            if (z26) {
                                if (n.q0.a()) {
                                    pVarA5 = mVar;
                                } else {
                                    pVarA5 = r0.a.a(mVar, new s3(6, e0Var5));
                                }
                                pVar5 = pVarA5;
                            } else {
                                pVar5 = mVar;
                            }
                            vVar2 = vVar3;
                            d(pVarD4, e0Var5, n0.i.c(-374338080, new o(dVar, o0Var3, h0Var, i6, i5, c1Var, xVar, f0Var, pVarA4, pVarA8, pVarD3, pVar5, cVar4, e0Var5, z26, cVar2, rVar15, bVar2), vVar2), vVar2, 384);
                        } else {
                            pVar3 = pVarA;
                        }
                        z12 = true;
                        z13 = z12 | zF3;
                        objK9 = vVar3.K();
                        if (z13) {
                            if (objK9 == hVar4) {
                                hVar2 = hVar4;
                            } else {
                                hVar3 = hVar4;
                                rVar2 = rVar;
                                i2Var = objK9;
                            }
                            f0.l.e((a4.e) i2Var, vVar3, p3.n.f5026a);
                            zH2 = vVar3.h(o0Var3);
                            objK10 = vVar3.K();
                            if (zH2) {
                                objK10 = new q(o0Var3, 1);
                                vVar3.f0(objK10);
                            } else {
                                objK10 = new q(o0Var3, 1);
                                vVar3.f0(objK10);
                            }
                            b0.t tVar3 = new b0.t((a4.c) objK10, null, 23);
                            mVar = r0.m.f5680a;
                            pVarA2 = j1.z.a(mVar, 8675309, tVar3);
                            boolean zH111 = vVar3.h(o0Var3);
                            if (i17 == 16384) {
                                z14 = true;
                            } else {
                                z14 = false;
                            }
                            boolean z211 = zH111 | z14;
                            if (i16 == 2048) {
                                z15 = true;
                            } else {
                                z15 = false;
                            }
                            zH3 = z211 | z15 | vVar3.h(rVar2) | vVar3.h(e0Var2);
                            objK11 = vVar3.K();
                            if (zH3) {
                                e2.r rVar16 = rVar2;
                                pVar4 = pVarA2;
                                c0.w0 w0Var4 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar16);
                                rVar3 = rVar16;
                                vVar3.f0(w0Var4);
                                objK11 = w0Var4;
                            } else {
                                e2.r rVar17 = rVar2;
                                pVar4 = pVarA2;
                                c0.w0 w0Var5 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar17);
                                rVar3 = rVar17;
                                vVar3.f0(w0Var5);
                                objK11 = w0Var5;
                            }
                            cVar5 = (a4.c) objK11;
                            if (z3) {
                                pVarA3 = r0.a.a(pVar4, new h2.b(cVar5, 3, kVar));
                            } else {
                                pVarA3 = pVar4;
                            }
                            a1.d dVar6 = e0Var2.f6947u;
                            z.c0 c0Var3 = e0Var2.f6946t;
                            r0.p pVarE3 = pVarA3.e(new SuspendPointerInputElement(dVar6, c0Var3, new b0.d(dVar6, c0Var3, (t3.c) null, 12), 4)).e(new PointerHoverIconModifierElement());
                            boolean zH112 = vVar3.h(o0Var3);
                            if (i12 == 4) {
                                z16 = true;
                            } else {
                                z16 = false;
                            }
                            zH4 = zH112 | z16 | vVar3.h(rVar3);
                            objK12 = vVar3.K();
                            if (zH4) {
                                objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                                vVar3.f0(objK12);
                            } else {
                                objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                                vVar3.f0(objK12);
                            }
                            r0.p pVarA10 = androidx.compose.ui.draw.a.a(mVar, (a4.c) objK12);
                            boolean zH113 = vVar3.h(o0Var3);
                            if (i16 == 2048) {
                                z17 = true;
                            } else {
                                z17 = false;
                            }
                            boolean zF6 = zH113 | z17 | vVar3.f(u2Var2) | vVar3.h(e0Var2);
                            if (i12 == 4) {
                                z18 = true;
                            } else {
                                z18 = false;
                            }
                            zH5 = zF6 | z18 | vVar3.h(rVar3);
                            Object objK22 = vVar3.K();
                            if (zH5) {
                                rVar4 = rVar3;
                                e0Var3 = e0Var2;
                                sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                                u2Var = u2Var2;
                                vVar3.f0(sVar);
                            } else {
                                rVar4 = rVar3;
                                e0Var3 = e0Var2;
                                sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                                u2Var = u2Var2;
                                vVar3.f0(sVar);
                            }
                            r0.p pVarD5 = androidx.compose.ui.layout.a.d(mVar, (a4.c) sVar);
                            boolean zH114 = vVar3.h(e0Var);
                            if (r3 == 4) {
                                z19 = true;
                            } else {
                                z19 = false;
                            }
                            boolean z32 = zH114 | z19;
                            if (i16 == 2048) {
                                z20 = true;
                            } else {
                                z20 = false;
                            }
                            boolean zG3 = z32 | z20 | vVar3.g(false);
                            if (i17 == 16384) {
                                z21 = true;
                            } else {
                                z21 = false;
                            }
                            zH6 = zG3 | z21 | vVar3.h(o0Var3) | vVar3.h(rVar4) | vVar3.h(e0Var3) | ((i18 <= 32 && vVar3.f(nVar)) || (i22 & 48) == 32);
                            objK13 = vVar3.K();
                            if (zH6) {
                                nVar4 = nVar2;
                                e0Var4 = e0Var3;
                                o0 o0Var9 = o0Var3;
                                w wVar4 = new w(e0Var, xVar, z3, nVar, o0Var9, rVar4, e0Var4, nVar4);
                                e2.r rVar18 = rVar4;
                                nVar5 = nVar;
                                rVar5 = rVar18;
                                o0Var3 = o0Var9;
                                vVar3.f0(wVar4);
                                objK13 = wVar4;
                            } else {
                                nVar4 = nVar2;
                                e0Var4 = e0Var3;
                                o0 o0Var10 = o0Var3;
                                w wVar5 = new w(e0Var, xVar, z3, nVar, o0Var10, rVar4, e0Var4, nVar4);
                                e2.r rVar19 = rVar4;
                                nVar5 = nVar;
                                rVar5 = rVar19;
                                o0Var3 = o0Var10;
                                vVar3.f0(wVar5);
                                objK13 = wVar5;
                            }
                            AtomicInteger atomicInteger3 = w1.k.f6311a;
                            appendedSemanticsElement = new AppendedSemanticsElement((a4.c) objK13, true);
                            if (!z3) {
                                z22 = false;
                            } else {
                                z22 = false;
                            }
                            float f7 = v0.f6117a;
                            if (z22) {
                                xVar4 = xVar;
                                appendedSemanticsElement2 = appendedSemanticsElement;
                                o0 o0Var11 = o0Var3;
                                o0Var3 = o0Var11;
                                pVarA4 = r0.a.a(mVar, new c0.u2(s0Var, o0Var11, xVar4, rVar5, 1));
                            } else {
                                xVar4 = xVar;
                                appendedSemanticsElement2 = appendedSemanticsElement;
                                pVarA4 = mVar;
                            }
                            zH7 = vVar3.h(e0Var4);
                            objK14 = vVar3.K();
                            if (zH7) {
                                objK14 = new j(e0Var4, 0);
                                vVar3.f0(objK14);
                            } else {
                                objK14 = new j(e0Var4, 0);
                                vVar3.f0(objK14);
                            }
                            f0.l.c(e0Var4, (a4.c) objK14, vVar3);
                            boolean zH115 = vVar3.h(o0Var3) | vVar3.h(yVar);
                            if (i12 == 4) {
                                z23 = true;
                            } else {
                                z23 = false;
                            }
                            z24 = zH115 | z23 | ((i18 <= 32 && vVar3.f(nVar5)) || (i22 & 48) == 32);
                            objK15 = vVar3.K();
                            if (z24) {
                                objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                                vVar3.f0(objK15);
                            } else {
                                objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                                vVar3.f0(objK15);
                            }
                            f0.l.c(nVar5, (a4.c) objK15, vVar3);
                            nVar6 = nVar4;
                            q qVar3 = o0Var3.f6025t;
                            if (i5 == 1) {
                                z25 = true;
                            } else {
                                z25 = false;
                            }
                            f0.h hVar8 = hVar3;
                            int i26 = nVar5.f2043e;
                            nVar7 = nVar5;
                            e2.r rVar110 = rVar5;
                            e0Var5 = e0Var4;
                            r0.p pVarA11 = r0.a.a(mVar, new y0(o0Var3, e0Var5, xVar4, true, z25, rVar110, f1Var, qVar3, i26));
                            boolean zBooleanValue3 = ((Boolean) i1VarV.getValue()).booleanValue();
                            zH8 = vVar3.h(o0Var3) | ((i18 <= 32 && vVar3.f(nVar7)) || (i22 & 48) == 32) | vVar3.h(cVar6);
                            objK16 = vVar3.K();
                            if (zH8) {
                                objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                                vVar3.f0(objK16);
                            } else {
                                objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                                vVar3.f0(objK16);
                            }
                            r0.p pVarD6 = androidx.compose.ui.layout.a.d(r0.a.a(androidx.compose.ui.input.key.a.b(androidx.compose.ui.input.key.a.b(androidx.compose.foundation.text.input.internal.a.a(pVar, cVar6, o0Var3, e0Var5).e(androidx.compose.foundation.text.handwriting.a.a((a4.a) objK16, zBooleanValue3)).e(pVar3), new c2(gVar, 23, o0Var3)), new c2(o0Var3, 22, e0Var5)).e(pVarA11), new n.r(c1Var, z3, kVar)).e(pVarE3).e(appendedSemanticsElement2), new q(o0Var3, 0));
                            if (!z3) {
                                z26 = false;
                            } else {
                                z26 = false;
                            }
                            if (z26) {
                                if (n.q0.a()) {
                                    pVarA5 = mVar;
                                } else {
                                    pVarA5 = r0.a.a(mVar, new s3(6, e0Var5));
                                }
                                pVar5 = pVarA5;
                            } else {
                                pVar5 = mVar;
                            }
                            vVar2 = vVar3;
                            d(pVarD6, e0Var5, n0.i.c(-374338080, new o(dVar, o0Var3, h0Var, i6, i5, c1Var, xVar, f0Var, pVarA4, pVarA10, pVarD5, pVar5, cVar4, e0Var5, z26, cVar2, rVar110, bVar2), vVar2), vVar2, 384);
                        } else {
                            hVar2 = r1;
                        }
                        rVar2 = rVar;
                        hVar3 = hVar2;
                        i2Var = new i2(o0Var3, i1VarV, yVar, e0Var2, nVar3, null, 3);
                        vVar3.f0(i2Var);
                        f0.l.e((a4.e) i2Var, vVar3, p3.n.f5026a);
                        zH2 = vVar3.h(o0Var3);
                        objK10 = vVar3.K();
                        if (zH2) {
                            objK10 = new q(o0Var3, 1);
                            vVar3.f0(objK10);
                        } else {
                            objK10 = new q(o0Var3, 1);
                            vVar3.f0(objK10);
                        }
                        b0.t tVar4 = new b0.t((a4.c) objK10, null, 23);
                        mVar = r0.m.f5680a;
                        pVarA2 = j1.z.a(mVar, 8675309, tVar4);
                        boolean zH116 = vVar3.h(o0Var3);
                        if (i17 == 16384) {
                            z14 = true;
                        } else {
                            z14 = false;
                        }
                        boolean z212 = zH116 | z14;
                        if (i16 == 2048) {
                            z15 = true;
                        } else {
                            z15 = false;
                        }
                        zH3 = z212 | z15 | vVar3.h(rVar2) | vVar3.h(e0Var2);
                        objK11 = vVar3.K();
                        if (zH3) {
                            e2.r rVar111 = rVar2;
                            pVar4 = pVarA2;
                            c0.w0 w0Var6 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar111);
                            rVar3 = rVar111;
                            vVar3.f0(w0Var6);
                            objK11 = w0Var6;
                        } else {
                            e2.r rVar112 = rVar2;
                            pVar4 = pVarA2;
                            c0.w0 w0Var7 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar112);
                            rVar3 = rVar112;
                            vVar3.f0(w0Var7);
                            objK11 = w0Var7;
                        }
                        cVar5 = (a4.c) objK11;
                        if (z3) {
                            pVarA3 = r0.a.a(pVar4, new h2.b(cVar5, 3, kVar));
                        } else {
                            pVarA3 = pVar4;
                        }
                        a1.d dVar7 = e0Var2.f6947u;
                        z.c0 c0Var4 = e0Var2.f6946t;
                        r0.p pVarE4 = pVarA3.e(new SuspendPointerInputElement(dVar7, c0Var4, new b0.d(dVar7, c0Var4, (t3.c) null, 12), 4)).e(new PointerHoverIconModifierElement());
                        boolean zH117 = vVar3.h(o0Var3);
                        if (i12 == 4) {
                            z16 = true;
                        } else {
                            z16 = false;
                        }
                        zH4 = zH117 | z16 | vVar3.h(rVar3);
                        objK12 = vVar3.K();
                        if (zH4) {
                            objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                            vVar3.f0(objK12);
                        } else {
                            objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                            vVar3.f0(objK12);
                        }
                        r0.p pVarA12 = androidx.compose.ui.draw.a.a(mVar, (a4.c) objK12);
                        boolean zH118 = vVar3.h(o0Var3);
                        if (i16 == 2048) {
                            z17 = true;
                        } else {
                            z17 = false;
                        }
                        boolean zF7 = zH118 | z17 | vVar3.f(u2Var2) | vVar3.h(e0Var2);
                        if (i12 == 4) {
                            z18 = true;
                        } else {
                            z18 = false;
                        }
                        zH5 = zF7 | z18 | vVar3.h(rVar3);
                        Object objK23 = vVar3.K();
                        if (zH5) {
                            rVar4 = rVar3;
                            e0Var3 = e0Var2;
                            sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                            u2Var = u2Var2;
                            vVar3.f0(sVar);
                        } else {
                            rVar4 = rVar3;
                            e0Var3 = e0Var2;
                            sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                            u2Var = u2Var2;
                            vVar3.f0(sVar);
                        }
                        r0.p pVarD7 = androidx.compose.ui.layout.a.d(mVar, (a4.c) sVar);
                        boolean zH119 = vVar3.h(e0Var);
                        if (r3 == 4) {
                            z19 = true;
                        } else {
                            z19 = false;
                        }
                        boolean z33 = zH119 | z19;
                        if (i16 == 2048) {
                            z20 = true;
                        } else {
                            z20 = false;
                        }
                        boolean zG4 = z33 | z20 | vVar3.g(false);
                        if (i17 == 16384) {
                            z21 = true;
                        } else {
                            z21 = false;
                        }
                        zH6 = zG4 | z21 | vVar3.h(o0Var3) | vVar3.h(rVar4) | vVar3.h(e0Var3) | ((i18 <= 32 && vVar3.f(nVar)) || (i22 & 48) == 32);
                        objK13 = vVar3.K();
                        if (zH6) {
                            nVar4 = nVar2;
                            e0Var4 = e0Var3;
                            o0 o0Var12 = o0Var3;
                            w wVar6 = new w(e0Var, xVar, z3, nVar, o0Var12, rVar4, e0Var4, nVar4);
                            e2.r rVar113 = rVar4;
                            nVar5 = nVar;
                            rVar5 = rVar113;
                            o0Var3 = o0Var12;
                            vVar3.f0(wVar6);
                            objK13 = wVar6;
                        } else {
                            nVar4 = nVar2;
                            e0Var4 = e0Var3;
                            o0 o0Var13 = o0Var3;
                            w wVar7 = new w(e0Var, xVar, z3, nVar, o0Var13, rVar4, e0Var4, nVar4);
                            e2.r rVar114 = rVar4;
                            nVar5 = nVar;
                            rVar5 = rVar114;
                            o0Var3 = o0Var13;
                            vVar3.f0(wVar7);
                            objK13 = wVar7;
                        }
                        AtomicInteger atomicInteger4 = w1.k.f6311a;
                        appendedSemanticsElement = new AppendedSemanticsElement((a4.c) objK13, true);
                        if (!z3) {
                            z22 = false;
                        } else {
                            z22 = false;
                        }
                        float f8 = v0.f6117a;
                        if (z22) {
                            xVar4 = xVar;
                            appendedSemanticsElement2 = appendedSemanticsElement;
                            o0 o0Var14 = o0Var3;
                            o0Var3 = o0Var14;
                            pVarA4 = r0.a.a(mVar, new c0.u2(s0Var, o0Var14, xVar4, rVar5, 1));
                        } else {
                            xVar4 = xVar;
                            appendedSemanticsElement2 = appendedSemanticsElement;
                            pVarA4 = mVar;
                        }
                        zH7 = vVar3.h(e0Var4);
                        objK14 = vVar3.K();
                        if (zH7) {
                            objK14 = new j(e0Var4, 0);
                            vVar3.f0(objK14);
                        } else {
                            objK14 = new j(e0Var4, 0);
                            vVar3.f0(objK14);
                        }
                        f0.l.c(e0Var4, (a4.c) objK14, vVar3);
                        boolean zH1110 = vVar3.h(o0Var3) | vVar3.h(yVar);
                        if (i12 == 4) {
                            z23 = true;
                        } else {
                            z23 = false;
                        }
                        z24 = zH1110 | z23 | ((i18 <= 32 && vVar3.f(nVar5)) || (i22 & 48) == 32);
                        objK15 = vVar3.K();
                        if (z24) {
                            objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                            vVar3.f0(objK15);
                        } else {
                            objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                            vVar3.f0(objK15);
                        }
                        f0.l.c(nVar5, (a4.c) objK15, vVar3);
                        nVar6 = nVar4;
                        q qVar4 = o0Var3.f6025t;
                        if (i5 == 1) {
                            z25 = true;
                        } else {
                            z25 = false;
                        }
                        f0.h hVar9 = hVar3;
                        int i27 = nVar5.f2043e;
                        nVar7 = nVar5;
                        e2.r rVar115 = rVar5;
                        e0Var5 = e0Var4;
                        r0.p pVarA13 = r0.a.a(mVar, new y0(o0Var3, e0Var5, xVar4, true, z25, rVar115, f1Var, qVar4, i27));
                        boolean zBooleanValue4 = ((Boolean) i1VarV.getValue()).booleanValue();
                        zH8 = vVar3.h(o0Var3) | ((i18 <= 32 && vVar3.f(nVar7)) || (i22 & 48) == 32) | vVar3.h(cVar6);
                        objK16 = vVar3.K();
                        if (zH8) {
                            objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                            vVar3.f0(objK16);
                        } else {
                            objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                            vVar3.f0(objK16);
                        }
                        r0.p pVarD8 = androidx.compose.ui.layout.a.d(r0.a.a(androidx.compose.ui.input.key.a.b(androidx.compose.ui.input.key.a.b(androidx.compose.foundation.text.input.internal.a.a(pVar, cVar6, o0Var3, e0Var5).e(androidx.compose.foundation.text.handwriting.a.a((a4.a) objK16, zBooleanValue4)).e(pVar3), new c2(gVar, 23, o0Var3)), new c2(o0Var3, 22, e0Var5)).e(pVarA13), new n.r(c1Var, z3, kVar)).e(pVarE4).e(appendedSemanticsElement2), new q(o0Var3, 0));
                        if (!z3) {
                            z26 = false;
                        } else {
                            z26 = false;
                        }
                        if (z26) {
                            if (n.q0.a()) {
                                pVarA5 = mVar;
                            } else {
                                pVarA5 = r0.a.a(mVar, new s3(6, e0Var5));
                            }
                            pVar5 = pVarA5;
                        } else {
                            pVar5 = mVar;
                        }
                        vVar2 = vVar3;
                        d(pVarD8, e0Var5, n0.i.c(-374338080, new o(dVar, o0Var3, h0Var, i6, i5, c1Var, xVar, f0Var, pVarA4, pVarA12, pVarD7, pVar5, cVar4, e0Var5, z26, cVar2, rVar115, bVar2), vVar2), vVar2, 384);
                    } else {
                        o0Var2 = o0Var;
                    }
                    z10 = true;
                    zH = z28 | z10 | vVar3.h(rVar) | vVar3.h(vVar) | vVar3.h(cVar3) | vVar3.h(e0Var2);
                    objK8 = vVar3.K();
                    if (zH) {
                        o0Var3 = o0Var2;
                        yVar = yVar2;
                        i19 = 32;
                        nVar3 = nVar;
                        r rVar20 = new r(o0Var3, z3, yVar, xVar, nVar3, rVar, e0Var2, vVar, cVar3);
                        xVar3 = xVar;
                        cVar4 = cVar3;
                        z11 = z3;
                        vVar3.f0(rVar20);
                        objK8 = rVar20;
                    } else {
                        o0Var3 = o0Var2;
                        yVar = yVar2;
                        i19 = 32;
                        nVar3 = nVar;
                        r rVar21 = new r(o0Var3, z3, yVar, xVar, nVar3, rVar, e0Var2, vVar, cVar3);
                        xVar3 = xVar;
                        cVar4 = cVar3;
                        z11 = z3;
                        vVar3.f0(rVar21);
                        objK8 = rVar21;
                    }
                    pVarA = androidx.compose.foundation.c.a(androidx.compose.ui.focus.a.b(androidx.compose.ui.focus.a.a(nVar2), (a4.c) objK8), z11, kVar);
                    i1 i1VarV2 = f0.l.v(Boolean.valueOf(z11), vVar3);
                    boolean zF8 = vVar3.f(i1VarV2) | vVar3.h(o0Var3) | vVar3.h(yVar) | vVar3.h(e0Var2);
                    if (i18 > i19) {
                        pVar3 = pVarA;
                        if ((i22 & 48) != i19) {
                            z12 = true;
                        } else {
                            z12 = false;
                        }
                    } else {
                        pVar3 = pVarA;
                        if ((i22 & 48) != i19) {
                            z12 = true;
                        } else {
                            z12 = false;
                        }
                    }
                    z13 = z12 | zF8;
                    objK9 = vVar3.K();
                    if (z13) {
                        if (objK9 == hVar4) {
                            hVar2 = hVar4;
                        } else {
                            hVar3 = hVar4;
                            rVar2 = rVar;
                            i2Var = objK9;
                        }
                        f0.l.e((a4.e) i2Var, vVar3, p3.n.f5026a);
                        zH2 = vVar3.h(o0Var3);
                        objK10 = vVar3.K();
                        if (zH2) {
                            objK10 = new q(o0Var3, 1);
                            vVar3.f0(objK10);
                        } else {
                            objK10 = new q(o0Var3, 1);
                            vVar3.f0(objK10);
                        }
                        b0.t tVar5 = new b0.t((a4.c) objK10, null, 23);
                        mVar = r0.m.f5680a;
                        pVarA2 = j1.z.a(mVar, 8675309, tVar5);
                        boolean zH1111 = vVar3.h(o0Var3);
                        if (i17 == 16384) {
                            z14 = true;
                        } else {
                            z14 = false;
                        }
                        boolean z213 = zH1111 | z14;
                        if (i16 == 2048) {
                            z15 = true;
                        } else {
                            z15 = false;
                        }
                        zH3 = z213 | z15 | vVar3.h(rVar2) | vVar3.h(e0Var2);
                        objK11 = vVar3.K();
                        if (zH3) {
                            e2.r rVar116 = rVar2;
                            pVar4 = pVarA2;
                            c0.w0 w0Var8 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar116);
                            rVar3 = rVar116;
                            vVar3.f0(w0Var8);
                            objK11 = w0Var8;
                        } else {
                            e2.r rVar117 = rVar2;
                            pVar4 = pVarA2;
                            c0.w0 w0Var9 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar117);
                            rVar3 = rVar117;
                            vVar3.f0(w0Var9);
                            objK11 = w0Var9;
                        }
                        cVar5 = (a4.c) objK11;
                        if (z3) {
                            pVarA3 = r0.a.a(pVar4, new h2.b(cVar5, 3, kVar));
                        } else {
                            pVarA3 = pVar4;
                        }
                        a1.d dVar8 = e0Var2.f6947u;
                        z.c0 c0Var5 = e0Var2.f6946t;
                        r0.p pVarE5 = pVarA3.e(new SuspendPointerInputElement(dVar8, c0Var5, new b0.d(dVar8, c0Var5, (t3.c) null, 12), 4)).e(new PointerHoverIconModifierElement());
                        boolean zH1112 = vVar3.h(o0Var3);
                        if (i12 == 4) {
                            z16 = true;
                        } else {
                            z16 = false;
                        }
                        zH4 = zH1112 | z16 | vVar3.h(rVar3);
                        objK12 = vVar3.K();
                        if (zH4) {
                            objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                            vVar3.f0(objK12);
                        } else {
                            objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                            vVar3.f0(objK12);
                        }
                        r0.p pVarA14 = androidx.compose.ui.draw.a.a(mVar, (a4.c) objK12);
                        boolean zH1113 = vVar3.h(o0Var3);
                        if (i16 == 2048) {
                            z17 = true;
                        } else {
                            z17 = false;
                        }
                        boolean zF9 = zH1113 | z17 | vVar3.f(u2Var2) | vVar3.h(e0Var2);
                        if (i12 == 4) {
                            z18 = true;
                        } else {
                            z18 = false;
                        }
                        zH5 = zF9 | z18 | vVar3.h(rVar3);
                        Object objK24 = vVar3.K();
                        if (zH5) {
                            rVar4 = rVar3;
                            e0Var3 = e0Var2;
                            sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                            u2Var = u2Var2;
                            vVar3.f0(sVar);
                        } else {
                            rVar4 = rVar3;
                            e0Var3 = e0Var2;
                            sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                            u2Var = u2Var2;
                            vVar3.f0(sVar);
                        }
                        r0.p pVarD9 = androidx.compose.ui.layout.a.d(mVar, (a4.c) sVar);
                        boolean zH1114 = vVar3.h(e0Var);
                        if (r3 == 4) {
                            z19 = true;
                        } else {
                            z19 = false;
                        }
                        boolean z34 = zH1114 | z19;
                        if (i16 == 2048) {
                            z20 = true;
                        } else {
                            z20 = false;
                        }
                        boolean zG5 = z34 | z20 | vVar3.g(false);
                        if (i17 == 16384) {
                            z21 = true;
                        } else {
                            z21 = false;
                        }
                        zH6 = zG5 | z21 | vVar3.h(o0Var3) | vVar3.h(rVar4) | vVar3.h(e0Var3) | ((i18 <= 32 && vVar3.f(nVar)) || (i22 & 48) == 32);
                        objK13 = vVar3.K();
                        if (zH6) {
                            nVar4 = nVar2;
                            e0Var4 = e0Var3;
                            o0 o0Var15 = o0Var3;
                            w wVar8 = new w(e0Var, xVar, z3, nVar, o0Var15, rVar4, e0Var4, nVar4);
                            e2.r rVar118 = rVar4;
                            nVar5 = nVar;
                            rVar5 = rVar118;
                            o0Var3 = o0Var15;
                            vVar3.f0(wVar8);
                            objK13 = wVar8;
                        } else {
                            nVar4 = nVar2;
                            e0Var4 = e0Var3;
                            o0 o0Var16 = o0Var3;
                            w wVar9 = new w(e0Var, xVar, z3, nVar, o0Var16, rVar4, e0Var4, nVar4);
                            e2.r rVar119 = rVar4;
                            nVar5 = nVar;
                            rVar5 = rVar119;
                            o0Var3 = o0Var16;
                            vVar3.f0(wVar9);
                            objK13 = wVar9;
                        }
                        AtomicInteger atomicInteger5 = w1.k.f6311a;
                        appendedSemanticsElement = new AppendedSemanticsElement((a4.c) objK13, true);
                        if (!z3) {
                            z22 = false;
                        } else {
                            z22 = false;
                        }
                        float f9 = v0.f6117a;
                        if (z22) {
                            xVar4 = xVar;
                            appendedSemanticsElement2 = appendedSemanticsElement;
                            o0 o0Var17 = o0Var3;
                            o0Var3 = o0Var17;
                            pVarA4 = r0.a.a(mVar, new c0.u2(s0Var, o0Var17, xVar4, rVar5, 1));
                        } else {
                            xVar4 = xVar;
                            appendedSemanticsElement2 = appendedSemanticsElement;
                            pVarA4 = mVar;
                        }
                        zH7 = vVar3.h(e0Var4);
                        objK14 = vVar3.K();
                        if (zH7) {
                            objK14 = new j(e0Var4, 0);
                            vVar3.f0(objK14);
                        } else {
                            objK14 = new j(e0Var4, 0);
                            vVar3.f0(objK14);
                        }
                        f0.l.c(e0Var4, (a4.c) objK14, vVar3);
                        boolean zH1115 = vVar3.h(o0Var3) | vVar3.h(yVar);
                        if (i12 == 4) {
                            z23 = true;
                        } else {
                            z23 = false;
                        }
                        z24 = zH1115 | z23 | ((i18 <= 32 && vVar3.f(nVar5)) || (i22 & 48) == 32);
                        objK15 = vVar3.K();
                        if (z24) {
                            objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                            vVar3.f0(objK15);
                        } else {
                            objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                            vVar3.f0(objK15);
                        }
                        f0.l.c(nVar5, (a4.c) objK15, vVar3);
                        nVar6 = nVar4;
                        q qVar5 = o0Var3.f6025t;
                        if (i5 == 1) {
                            z25 = true;
                        } else {
                            z25 = false;
                        }
                        f0.h hVar10 = hVar3;
                        int i28 = nVar5.f2043e;
                        nVar7 = nVar5;
                        e2.r rVar1110 = rVar5;
                        e0Var5 = e0Var4;
                        r0.p pVarA15 = r0.a.a(mVar, new y0(o0Var3, e0Var5, xVar4, true, z25, rVar1110, f1Var, qVar5, i28));
                        boolean zBooleanValue5 = ((Boolean) i1VarV2.getValue()).booleanValue();
                        zH8 = vVar3.h(o0Var3) | ((i18 <= 32 && vVar3.f(nVar7)) || (i22 & 48) == 32) | vVar3.h(cVar6);
                        objK16 = vVar3.K();
                        if (zH8) {
                            objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                            vVar3.f0(objK16);
                        } else {
                            objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                            vVar3.f0(objK16);
                        }
                        r0.p pVarD10 = androidx.compose.ui.layout.a.d(r0.a.a(androidx.compose.ui.input.key.a.b(androidx.compose.ui.input.key.a.b(androidx.compose.foundation.text.input.internal.a.a(pVar, cVar6, o0Var3, e0Var5).e(androidx.compose.foundation.text.handwriting.a.a((a4.a) objK16, zBooleanValue5)).e(pVar3), new c2(gVar, 23, o0Var3)), new c2(o0Var3, 22, e0Var5)).e(pVarA15), new n.r(c1Var, z3, kVar)).e(pVarE5).e(appendedSemanticsElement2), new q(o0Var3, 0));
                        if (!z3) {
                            z26 = false;
                        } else {
                            z26 = false;
                        }
                        if (z26) {
                            if (n.q0.a()) {
                                pVarA5 = mVar;
                            } else {
                                pVarA5 = r0.a.a(mVar, new s3(6, e0Var5));
                            }
                            pVar5 = pVarA5;
                        } else {
                            pVar5 = mVar;
                        }
                        vVar2 = vVar3;
                        d(pVarD10, e0Var5, n0.i.c(-374338080, new o(dVar, o0Var3, h0Var, i6, i5, c1Var, xVar, f0Var, pVarA4, pVarA14, pVarD9, pVar5, cVar4, e0Var5, z26, cVar2, rVar1110, bVar2), vVar2), vVar2, 384);
                    } else {
                        hVar2 = r1;
                    }
                    rVar2 = rVar;
                    hVar3 = hVar2;
                    i2Var = new i2(o0Var3, i1VarV2, yVar, e0Var2, nVar3, null, 3);
                    vVar3.f0(i2Var);
                    f0.l.e((a4.e) i2Var, vVar3, p3.n.f5026a);
                    zH2 = vVar3.h(o0Var3);
                    objK10 = vVar3.K();
                    if (zH2) {
                        objK10 = new q(o0Var3, 1);
                        vVar3.f0(objK10);
                    } else {
                        objK10 = new q(o0Var3, 1);
                        vVar3.f0(objK10);
                    }
                    b0.t tVar6 = new b0.t((a4.c) objK10, null, 23);
                    mVar = r0.m.f5680a;
                    pVarA2 = j1.z.a(mVar, 8675309, tVar6);
                    boolean zH1116 = vVar3.h(o0Var3);
                    if (i17 == 16384) {
                        z14 = true;
                    } else {
                        z14 = false;
                    }
                    boolean z214 = zH1116 | z14;
                    if (i16 == 2048) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    zH3 = z214 | z15 | vVar3.h(rVar2) | vVar3.h(e0Var2);
                    objK11 = vVar3.K();
                    if (zH3) {
                        e2.r rVar1111 = rVar2;
                        pVar4 = pVarA2;
                        c0.w0 w0Var10 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar1111);
                        rVar3 = rVar1111;
                        vVar3.f0(w0Var10);
                        objK11 = w0Var10;
                    } else {
                        e2.r rVar1112 = rVar2;
                        pVar4 = pVarA2;
                        c0.w0 w0Var11 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar1112);
                        rVar3 = rVar1112;
                        vVar3.f0(w0Var11);
                        objK11 = w0Var11;
                    }
                    cVar5 = (a4.c) objK11;
                    if (z3) {
                        pVarA3 = r0.a.a(pVar4, new h2.b(cVar5, 3, kVar));
                    } else {
                        pVarA3 = pVar4;
                    }
                    a1.d dVar9 = e0Var2.f6947u;
                    z.c0 c0Var6 = e0Var2.f6946t;
                    r0.p pVarE6 = pVarA3.e(new SuspendPointerInputElement(dVar9, c0Var6, new b0.d(dVar9, c0Var6, (t3.c) null, 12), 4)).e(new PointerHoverIconModifierElement());
                    boolean zH1117 = vVar3.h(o0Var3);
                    if (i12 == 4) {
                        z16 = true;
                    } else {
                        z16 = false;
                    }
                    zH4 = zH1117 | z16 | vVar3.h(rVar3);
                    objK12 = vVar3.K();
                    if (zH4) {
                        objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                        vVar3.f0(objK12);
                    } else {
                        objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                        vVar3.f0(objK12);
                    }
                    r0.p pVarA16 = androidx.compose.ui.draw.a.a(mVar, (a4.c) objK12);
                    boolean zH1118 = vVar3.h(o0Var3);
                    if (i16 == 2048) {
                        z17 = true;
                    } else {
                        z17 = false;
                    }
                    boolean zF10 = zH1118 | z17 | vVar3.f(u2Var2) | vVar3.h(e0Var2);
                    if (i12 == 4) {
                        z18 = true;
                    } else {
                        z18 = false;
                    }
                    zH5 = zF10 | z18 | vVar3.h(rVar3);
                    Object objK25 = vVar3.K();
                    if (zH5) {
                        rVar4 = rVar3;
                        e0Var3 = e0Var2;
                        sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                        u2Var = u2Var2;
                        vVar3.f0(sVar);
                    } else {
                        rVar4 = rVar3;
                        e0Var3 = e0Var2;
                        sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                        u2Var = u2Var2;
                        vVar3.f0(sVar);
                    }
                    r0.p pVarD11 = androidx.compose.ui.layout.a.d(mVar, (a4.c) sVar);
                    boolean zH1119 = vVar3.h(e0Var);
                    if (r3 == 4) {
                        z19 = true;
                    } else {
                        z19 = false;
                    }
                    boolean z35 = zH1119 | z19;
                    if (i16 == 2048) {
                        z20 = true;
                    } else {
                        z20 = false;
                    }
                    boolean zG6 = z35 | z20 | vVar3.g(false);
                    if (i17 == 16384) {
                        z21 = true;
                    } else {
                        z21 = false;
                    }
                    zH6 = zG6 | z21 | vVar3.h(o0Var3) | vVar3.h(rVar4) | vVar3.h(e0Var3) | ((i18 <= 32 && vVar3.f(nVar)) || (i22 & 48) == 32);
                    objK13 = vVar3.K();
                    if (zH6) {
                        nVar4 = nVar2;
                        e0Var4 = e0Var3;
                        o0 o0Var18 = o0Var3;
                        w wVar10 = new w(e0Var, xVar, z3, nVar, o0Var18, rVar4, e0Var4, nVar4);
                        e2.r rVar1113 = rVar4;
                        nVar5 = nVar;
                        rVar5 = rVar1113;
                        o0Var3 = o0Var18;
                        vVar3.f0(wVar10);
                        objK13 = wVar10;
                    } else {
                        nVar4 = nVar2;
                        e0Var4 = e0Var3;
                        o0 o0Var19 = o0Var3;
                        w wVar11 = new w(e0Var, xVar, z3, nVar, o0Var19, rVar4, e0Var4, nVar4);
                        e2.r rVar1114 = rVar4;
                        nVar5 = nVar;
                        rVar5 = rVar1114;
                        o0Var3 = o0Var19;
                        vVar3.f0(wVar11);
                        objK13 = wVar11;
                    }
                    AtomicInteger atomicInteger6 = w1.k.f6311a;
                    appendedSemanticsElement = new AppendedSemanticsElement((a4.c) objK13, true);
                    if (!z3) {
                        z22 = false;
                    } else {
                        z22 = false;
                    }
                    float f10 = v0.f6117a;
                    if (z22) {
                        xVar4 = xVar;
                        appendedSemanticsElement2 = appendedSemanticsElement;
                        o0 o0Var110 = o0Var3;
                        o0Var3 = o0Var110;
                        pVarA4 = r0.a.a(mVar, new c0.u2(s0Var, o0Var110, xVar4, rVar5, 1));
                    } else {
                        xVar4 = xVar;
                        appendedSemanticsElement2 = appendedSemanticsElement;
                        pVarA4 = mVar;
                    }
                    zH7 = vVar3.h(e0Var4);
                    objK14 = vVar3.K();
                    if (zH7) {
                        objK14 = new j(e0Var4, 0);
                        vVar3.f0(objK14);
                    } else {
                        objK14 = new j(e0Var4, 0);
                        vVar3.f0(objK14);
                    }
                    f0.l.c(e0Var4, (a4.c) objK14, vVar3);
                    boolean zH11110 = vVar3.h(o0Var3) | vVar3.h(yVar);
                    if (i12 == 4) {
                        z23 = true;
                    } else {
                        z23 = false;
                    }
                    z24 = zH11110 | z23 | ((i18 <= 32 && vVar3.f(nVar5)) || (i22 & 48) == 32);
                    objK15 = vVar3.K();
                    if (z24) {
                        objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                        vVar3.f0(objK15);
                    } else {
                        objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                        vVar3.f0(objK15);
                    }
                    f0.l.c(nVar5, (a4.c) objK15, vVar3);
                    nVar6 = nVar4;
                    q qVar6 = o0Var3.f6025t;
                    if (i5 == 1) {
                        z25 = true;
                    } else {
                        z25 = false;
                    }
                    f0.h hVar11 = hVar3;
                    int i29 = nVar5.f2043e;
                    nVar7 = nVar5;
                    e2.r rVar1115 = rVar5;
                    e0Var5 = e0Var4;
                    r0.p pVarA17 = r0.a.a(mVar, new y0(o0Var3, e0Var5, xVar4, true, z25, rVar1115, f1Var, qVar6, i29));
                    boolean zBooleanValue6 = ((Boolean) i1VarV2.getValue()).booleanValue();
                    zH8 = vVar3.h(o0Var3) | ((i18 <= 32 && vVar3.f(nVar7)) || (i22 & 48) == 32) | vVar3.h(cVar6);
                    objK16 = vVar3.K();
                    if (zH8) {
                        objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                        vVar3.f0(objK16);
                    } else {
                        objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                        vVar3.f0(objK16);
                    }
                    r0.p pVarD12 = androidx.compose.ui.layout.a.d(r0.a.a(androidx.compose.ui.input.key.a.b(androidx.compose.ui.input.key.a.b(androidx.compose.foundation.text.input.internal.a.a(pVar, cVar6, o0Var3, e0Var5).e(androidx.compose.foundation.text.handwriting.a.a((a4.a) objK16, zBooleanValue6)).e(pVar3), new c2(gVar, 23, o0Var3)), new c2(o0Var3, 22, e0Var5)).e(pVarA17), new n.r(c1Var, z3, kVar)).e(pVarE6).e(appendedSemanticsElement2), new q(o0Var3, 0));
                    if (!z3) {
                        z26 = false;
                    } else {
                        z26 = false;
                    }
                    if (z26) {
                        if (n.q0.a()) {
                            pVarA5 = mVar;
                        } else {
                            pVarA5 = r0.a.a(mVar, new s3(6, e0Var5));
                        }
                        pVar5 = pVarA5;
                    } else {
                        pVar5 = mVar;
                    }
                    vVar2 = vVar3;
                    d(pVarD12, e0Var5, n0.i.c(-374338080, new o(dVar, o0Var3, h0Var, i6, i5, c1Var, xVar, f0Var, pVarA4, pVarA16, pVarD11, pVar5, cVar4, e0Var5, z26, cVar2, rVar1115, bVar2), vVar2), vVar2, 384);
                } else {
                    hVar.f50f = new e2.j(fVar2, j5);
                    z5 = true;
                }
                z6 = false;
                if (g0Var2 == null) {
                    e2.j jVar3 = (e2.j) hVar.f50f;
                    jVar3.f2030d = -1;
                    jVar3.f2031e = -1;
                } else {
                    j6 = g0Var2.f6770a;
                    if (!y1.g0.b(j6)) {
                        ((e2.j) hVar.f50f).e(y1.g0.e(j6), y1.g0.d(j6));
                    }
                }
                if (z5) {
                    e2.j jVar4 = (e2.j) hVar.f50f;
                    jVar4.f2030d = -1;
                    jVar4.f2031e = -1;
                    xVarA = e2.x.a(xVar, null, 0L, 3);
                } else {
                    e2.j jVar5 = (e2.j) hVar.f50f;
                    jVar5.f2030d = -1;
                    jVar5.f2031e = -1;
                    xVarA = e2.x.a(xVar, null, 0L, 3);
                }
                xVar2 = (e2.x) hVar.f49e;
                hVar.f49e = xVarA;
                if (d0Var != null) {
                    d0Var.a(xVar2, xVarA);
                }
                objK4 = vVar3.K();
                if (objK4 == hVar4) {
                    objK4 = new f1();
                    vVar3.f0(objK4);
                }
                f1Var = (f1) objK4;
                jCurrentTimeMillis = System.currentTimeMillis();
                if (f1Var.f5925e) {
                    f1Var.f5924d = Long.valueOf(jCurrentTimeMillis);
                    f1Var.a(xVar);
                } else {
                    l5 = f1Var.f5924d;
                    if (jCurrentTimeMillis > (l5 != null ? l5.longValue() : 0L) + ((long) 5000)) {
                        f1Var.f5924d = Long.valueOf(jCurrentTimeMillis);
                        f1Var.a(xVar);
                    }
                }
                objK5 = vVar3.K();
                if (objK5 == hVar4) {
                    objK5 = new z.e0(f1Var);
                    vVar3.f0(objK5);
                }
                e0Var2 = (z.e0) objK5;
                e0Var2.f6928b = rVar;
                e0Var2.f6929c = o0Var.f6025t;
                e0Var2.f6930d = o0Var;
                e0Var2.f6931e.setValue(xVar);
                e0Var2.f6932f = (q1.f1) vVar3.j(j1.f5235d);
                e0Var2.f6933g = (l2) vVar3.j(j1.f5246o);
                e0Var2.f6934h = (f1.a) vVar3.j(j1.f5241j);
                nVar2 = nVar8;
                e0Var2.f6935i = nVar2;
                e0Var2.f6936j.setValue(true);
                e0Var2.f6937k.setValue(Boolean.valueOf(z3));
                objK6 = vVar3.K();
                if (objK6 == hVar4) {
                    f0.i0 i0Var2 = new f0.i0(f0.l.l(vVar3));
                    vVar3.f0(i0Var2);
                    objK6 = i0Var2;
                }
                vVar = ((f0.i0) objK6).f2194d;
                objK7 = vVar3.K();
                if (objK7 == hVar4) {
                    objK7 = new s.c();
                    vVar3.f0(objK7);
                }
                cVar3 = (s.c) objK7;
                boolean zH20 = vVar3.h(o0Var);
                i16 = i22 & 7168;
                if (i16 == 2048) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z215 = zH20 | z7;
                i17 = i22 & 57344;
                if (i17 == 16384) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean zH120 = z8 | z215 | vVar3.h(yVar2);
                if (i12 == 4) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                boolean z216 = zH120 | z9;
                i18 = (i22 & 112) ^ 48;
                if (i18 > 32) {
                }
                o0Var2 = o0Var;
                if ((i22 & 48) != 32) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                zH = z216 | z10 | vVar3.h(rVar) | vVar3.h(vVar) | vVar3.h(cVar3) | vVar3.h(e0Var2);
                objK8 = vVar3.K();
                if (zH) {
                    o0Var3 = o0Var2;
                    yVar = yVar2;
                    i19 = 32;
                    nVar3 = nVar;
                    r rVar22 = new r(o0Var3, z3, yVar, xVar, nVar3, rVar, e0Var2, vVar, cVar3);
                    xVar3 = xVar;
                    cVar4 = cVar3;
                    z11 = z3;
                    vVar3.f0(rVar22);
                    objK8 = rVar22;
                } else {
                    o0Var3 = o0Var2;
                    yVar = yVar2;
                    i19 = 32;
                    nVar3 = nVar;
                    r rVar23 = new r(o0Var3, z3, yVar, xVar, nVar3, rVar, e0Var2, vVar, cVar3);
                    xVar3 = xVar;
                    cVar4 = cVar3;
                    z11 = z3;
                    vVar3.f0(rVar23);
                    objK8 = rVar23;
                }
                pVarA = androidx.compose.foundation.c.a(androidx.compose.ui.focus.a.b(androidx.compose.ui.focus.a.a(nVar2), (a4.c) objK8), z11, kVar);
                i1 i1VarV3 = f0.l.v(Boolean.valueOf(z11), vVar3);
                boolean zF11 = vVar3.f(i1VarV3) | vVar3.h(o0Var3) | vVar3.h(yVar) | vVar3.h(e0Var2);
                if (i18 > i19) {
                    pVar3 = pVarA;
                    if ((i22 & 48) != i19) {
                        z12 = true;
                    } else {
                        z12 = false;
                    }
                } else {
                    pVar3 = pVarA;
                    if ((i22 & 48) != i19) {
                        z12 = true;
                    } else {
                        z12 = false;
                    }
                }
                z13 = z12 | zF11;
                objK9 = vVar3.K();
                if (z13) {
                    if (objK9 == hVar4) {
                        hVar2 = hVar4;
                    } else {
                        hVar3 = hVar4;
                        rVar2 = rVar;
                        i2Var = objK9;
                    }
                    f0.l.e((a4.e) i2Var, vVar3, p3.n.f5026a);
                    zH2 = vVar3.h(o0Var3);
                    objK10 = vVar3.K();
                    if (zH2) {
                        objK10 = new q(o0Var3, 1);
                        vVar3.f0(objK10);
                    } else {
                        objK10 = new q(o0Var3, 1);
                        vVar3.f0(objK10);
                    }
                    b0.t tVar7 = new b0.t((a4.c) objK10, null, 23);
                    mVar = r0.m.f5680a;
                    pVarA2 = j1.z.a(mVar, 8675309, tVar7);
                    boolean zH11111 = vVar3.h(o0Var3);
                    if (i17 == 16384) {
                        z14 = true;
                    } else {
                        z14 = false;
                    }
                    boolean z217 = zH11111 | z14;
                    if (i16 == 2048) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    zH3 = z217 | z15 | vVar3.h(rVar2) | vVar3.h(e0Var2);
                    objK11 = vVar3.K();
                    if (zH3) {
                        e2.r rVar1116 = rVar2;
                        pVar4 = pVarA2;
                        c0.w0 w0Var12 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar1116);
                        rVar3 = rVar1116;
                        vVar3.f0(w0Var12);
                        objK11 = w0Var12;
                    } else {
                        e2.r rVar1117 = rVar2;
                        pVar4 = pVarA2;
                        c0.w0 w0Var13 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar1117);
                        rVar3 = rVar1117;
                        vVar3.f0(w0Var13);
                        objK11 = w0Var13;
                    }
                    cVar5 = (a4.c) objK11;
                    if (z3) {
                        pVarA3 = r0.a.a(pVar4, new h2.b(cVar5, 3, kVar));
                    } else {
                        pVarA3 = pVar4;
                    }
                    a1.d dVar10 = e0Var2.f6947u;
                    z.c0 c0Var7 = e0Var2.f6946t;
                    r0.p pVarE7 = pVarA3.e(new SuspendPointerInputElement(dVar10, c0Var7, new b0.d(dVar10, c0Var7, (t3.c) null, 12), 4)).e(new PointerHoverIconModifierElement());
                    boolean zH11112 = vVar3.h(o0Var3);
                    if (i12 == 4) {
                        z16 = true;
                    } else {
                        z16 = false;
                    }
                    zH4 = zH11112 | z16 | vVar3.h(rVar3);
                    objK12 = vVar3.K();
                    if (zH4) {
                        objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                        vVar3.f0(objK12);
                    } else {
                        objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                        vVar3.f0(objK12);
                    }
                    r0.p pVarA18 = androidx.compose.ui.draw.a.a(mVar, (a4.c) objK12);
                    boolean zH11113 = vVar3.h(o0Var3);
                    if (i16 == 2048) {
                        z17 = true;
                    } else {
                        z17 = false;
                    }
                    boolean zF12 = zH11113 | z17 | vVar3.f(u2Var2) | vVar3.h(e0Var2);
                    if (i12 == 4) {
                        z18 = true;
                    } else {
                        z18 = false;
                    }
                    zH5 = zF12 | z18 | vVar3.h(rVar3);
                    Object objK26 = vVar3.K();
                    if (zH5) {
                        rVar4 = rVar3;
                        e0Var3 = e0Var2;
                        sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                        u2Var = u2Var2;
                        vVar3.f0(sVar);
                    } else {
                        rVar4 = rVar3;
                        e0Var3 = e0Var2;
                        sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                        u2Var = u2Var2;
                        vVar3.f0(sVar);
                    }
                    r0.p pVarD13 = androidx.compose.ui.layout.a.d(mVar, (a4.c) sVar);
                    boolean zH11114 = vVar3.h(e0Var);
                    if (r3 == 4) {
                        z19 = true;
                    } else {
                        z19 = false;
                    }
                    boolean z36 = zH11114 | z19;
                    if (i16 == 2048) {
                        z20 = true;
                    } else {
                        z20 = false;
                    }
                    boolean zG7 = z36 | z20 | vVar3.g(false);
                    if (i17 == 16384) {
                        z21 = true;
                    } else {
                        z21 = false;
                    }
                    zH6 = zG7 | z21 | vVar3.h(o0Var3) | vVar3.h(rVar4) | vVar3.h(e0Var3) | ((i18 <= 32 && vVar3.f(nVar)) || (i22 & 48) == 32);
                    objK13 = vVar3.K();
                    if (zH6) {
                        nVar4 = nVar2;
                        e0Var4 = e0Var3;
                        o0 o0Var111 = o0Var3;
                        w wVar12 = new w(e0Var, xVar, z3, nVar, o0Var111, rVar4, e0Var4, nVar4);
                        e2.r rVar1118 = rVar4;
                        nVar5 = nVar;
                        rVar5 = rVar1118;
                        o0Var3 = o0Var111;
                        vVar3.f0(wVar12);
                        objK13 = wVar12;
                    } else {
                        nVar4 = nVar2;
                        e0Var4 = e0Var3;
                        o0 o0Var112 = o0Var3;
                        w wVar13 = new w(e0Var, xVar, z3, nVar, o0Var112, rVar4, e0Var4, nVar4);
                        e2.r rVar1119 = rVar4;
                        nVar5 = nVar;
                        rVar5 = rVar1119;
                        o0Var3 = o0Var112;
                        vVar3.f0(wVar13);
                        objK13 = wVar13;
                    }
                    AtomicInteger atomicInteger7 = w1.k.f6311a;
                    appendedSemanticsElement = new AppendedSemanticsElement((a4.c) objK13, true);
                    if (!z3) {
                        z22 = false;
                    } else {
                        z22 = false;
                    }
                    float f11 = v0.f6117a;
                    if (z22) {
                        xVar4 = xVar;
                        appendedSemanticsElement2 = appendedSemanticsElement;
                        o0 o0Var113 = o0Var3;
                        o0Var3 = o0Var113;
                        pVarA4 = r0.a.a(mVar, new c0.u2(s0Var, o0Var113, xVar4, rVar5, 1));
                    } else {
                        xVar4 = xVar;
                        appendedSemanticsElement2 = appendedSemanticsElement;
                        pVarA4 = mVar;
                    }
                    zH7 = vVar3.h(e0Var4);
                    objK14 = vVar3.K();
                    if (zH7) {
                        objK14 = new j(e0Var4, 0);
                        vVar3.f0(objK14);
                    } else {
                        objK14 = new j(e0Var4, 0);
                        vVar3.f0(objK14);
                    }
                    f0.l.c(e0Var4, (a4.c) objK14, vVar3);
                    boolean zH11115 = vVar3.h(o0Var3) | vVar3.h(yVar);
                    if (i12 == 4) {
                        z23 = true;
                    } else {
                        z23 = false;
                    }
                    z24 = zH11115 | z23 | ((i18 <= 32 && vVar3.f(nVar5)) || (i22 & 48) == 32);
                    objK15 = vVar3.K();
                    if (z24) {
                        objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                        vVar3.f0(objK15);
                    } else {
                        objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                        vVar3.f0(objK15);
                    }
                    f0.l.c(nVar5, (a4.c) objK15, vVar3);
                    nVar6 = nVar4;
                    q qVar7 = o0Var3.f6025t;
                    if (i5 == 1) {
                        z25 = true;
                    } else {
                        z25 = false;
                    }
                    f0.h hVar12 = hVar3;
                    int i210 = nVar5.f2043e;
                    nVar7 = nVar5;
                    e2.r rVar11110 = rVar5;
                    e0Var5 = e0Var4;
                    r0.p pVarA19 = r0.a.a(mVar, new y0(o0Var3, e0Var5, xVar4, true, z25, rVar11110, f1Var, qVar7, i210));
                    boolean zBooleanValue7 = ((Boolean) i1VarV3.getValue()).booleanValue();
                    zH8 = vVar3.h(o0Var3) | ((i18 <= 32 && vVar3.f(nVar7)) || (i22 & 48) == 32) | vVar3.h(cVar6);
                    objK16 = vVar3.K();
                    if (zH8) {
                        objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                        vVar3.f0(objK16);
                    } else {
                        objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                        vVar3.f0(objK16);
                    }
                    r0.p pVarD14 = androidx.compose.ui.layout.a.d(r0.a.a(androidx.compose.ui.input.key.a.b(androidx.compose.ui.input.key.a.b(androidx.compose.foundation.text.input.internal.a.a(pVar, cVar6, o0Var3, e0Var5).e(androidx.compose.foundation.text.handwriting.a.a((a4.a) objK16, zBooleanValue7)).e(pVar3), new c2(gVar, 23, o0Var3)), new c2(o0Var3, 22, e0Var5)).e(pVarA19), new n.r(c1Var, z3, kVar)).e(pVarE7).e(appendedSemanticsElement2), new q(o0Var3, 0));
                    if (!z3) {
                        z26 = false;
                    } else {
                        z26 = false;
                    }
                    if (z26) {
                        if (n.q0.a()) {
                            pVarA5 = mVar;
                        } else {
                            pVarA5 = r0.a.a(mVar, new s3(6, e0Var5));
                        }
                        pVar5 = pVarA5;
                    } else {
                        pVar5 = mVar;
                    }
                    vVar2 = vVar3;
                    d(pVarD14, e0Var5, n0.i.c(-374338080, new o(dVar, o0Var3, h0Var, i6, i5, c1Var, xVar, f0Var, pVarA4, pVarA18, pVarD13, pVar5, cVar4, e0Var5, z26, cVar2, rVar11110, bVar2), vVar2), vVar2, 384);
                } else {
                    hVar2 = r1;
                }
                rVar2 = rVar;
                hVar3 = hVar2;
                i2Var = new i2(o0Var3, i1VarV3, yVar, e0Var2, nVar3, null, 3);
                vVar3.f0(i2Var);
                f0.l.e((a4.e) i2Var, vVar3, p3.n.f5026a);
                zH2 = vVar3.h(o0Var3);
                objK10 = vVar3.K();
                if (zH2) {
                    objK10 = new q(o0Var3, 1);
                    vVar3.f0(objK10);
                } else {
                    objK10 = new q(o0Var3, 1);
                    vVar3.f0(objK10);
                }
                b0.t tVar8 = new b0.t((a4.c) objK10, null, 23);
                mVar = r0.m.f5680a;
                pVarA2 = j1.z.a(mVar, 8675309, tVar8);
                boolean zH11116 = vVar3.h(o0Var3);
                if (i17 == 16384) {
                    z14 = true;
                } else {
                    z14 = false;
                }
                boolean z218 = zH11116 | z14;
                if (i16 == 2048) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                zH3 = z218 | z15 | vVar3.h(rVar2) | vVar3.h(e0Var2);
                objK11 = vVar3.K();
                if (zH3) {
                    e2.r rVar11111 = rVar2;
                    pVar4 = pVarA2;
                    c0.w0 w0Var14 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar11111);
                    rVar3 = rVar11111;
                    vVar3.f0(w0Var14);
                    objK11 = w0Var14;
                } else {
                    e2.r rVar11112 = rVar2;
                    pVar4 = pVarA2;
                    c0.w0 w0Var15 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar11112);
                    rVar3 = rVar11112;
                    vVar3.f0(w0Var15);
                    objK11 = w0Var15;
                }
                cVar5 = (a4.c) objK11;
                if (z3) {
                    pVarA3 = r0.a.a(pVar4, new h2.b(cVar5, 3, kVar));
                } else {
                    pVarA3 = pVar4;
                }
                a1.d dVar11 = e0Var2.f6947u;
                z.c0 c0Var8 = e0Var2.f6946t;
                r0.p pVarE8 = pVarA3.e(new SuspendPointerInputElement(dVar11, c0Var8, new b0.d(dVar11, c0Var8, (t3.c) null, 12), 4)).e(new PointerHoverIconModifierElement());
                boolean zH11117 = vVar3.h(o0Var3);
                if (i12 == 4) {
                    z16 = true;
                } else {
                    z16 = false;
                }
                zH4 = zH11117 | z16 | vVar3.h(rVar3);
                objK12 = vVar3.K();
                if (zH4) {
                    objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                    vVar3.f0(objK12);
                } else {
                    objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                    vVar3.f0(objK12);
                }
                r0.p pVarA110 = androidx.compose.ui.draw.a.a(mVar, (a4.c) objK12);
                boolean zH11118 = vVar3.h(o0Var3);
                if (i16 == 2048) {
                    z17 = true;
                } else {
                    z17 = false;
                }
                boolean zF13 = zH11118 | z17 | vVar3.f(u2Var2) | vVar3.h(e0Var2);
                if (i12 == 4) {
                    z18 = true;
                } else {
                    z18 = false;
                }
                zH5 = zF13 | z18 | vVar3.h(rVar3);
                Object objK27 = vVar3.K();
                if (zH5) {
                    rVar4 = rVar3;
                    e0Var3 = e0Var2;
                    sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                    u2Var = u2Var2;
                    vVar3.f0(sVar);
                } else {
                    rVar4 = rVar3;
                    e0Var3 = e0Var2;
                    sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                    u2Var = u2Var2;
                    vVar3.f0(sVar);
                }
                r0.p pVarD15 = androidx.compose.ui.layout.a.d(mVar, (a4.c) sVar);
                boolean zH11119 = vVar3.h(e0Var);
                if (r3 == 4) {
                    z19 = true;
                } else {
                    z19 = false;
                }
                boolean z37 = zH11119 | z19;
                if (i16 == 2048) {
                    z20 = true;
                } else {
                    z20 = false;
                }
                boolean zG8 = z37 | z20 | vVar3.g(false);
                if (i17 == 16384) {
                    z21 = true;
                } else {
                    z21 = false;
                }
                zH6 = zG8 | z21 | vVar3.h(o0Var3) | vVar3.h(rVar4) | vVar3.h(e0Var3) | ((i18 <= 32 && vVar3.f(nVar)) || (i22 & 48) == 32);
                objK13 = vVar3.K();
                if (zH6) {
                    nVar4 = nVar2;
                    e0Var4 = e0Var3;
                    o0 o0Var114 = o0Var3;
                    w wVar14 = new w(e0Var, xVar, z3, nVar, o0Var114, rVar4, e0Var4, nVar4);
                    e2.r rVar11113 = rVar4;
                    nVar5 = nVar;
                    rVar5 = rVar11113;
                    o0Var3 = o0Var114;
                    vVar3.f0(wVar14);
                    objK13 = wVar14;
                } else {
                    nVar4 = nVar2;
                    e0Var4 = e0Var3;
                    o0 o0Var115 = o0Var3;
                    w wVar15 = new w(e0Var, xVar, z3, nVar, o0Var115, rVar4, e0Var4, nVar4);
                    e2.r rVar11114 = rVar4;
                    nVar5 = nVar;
                    rVar5 = rVar11114;
                    o0Var3 = o0Var115;
                    vVar3.f0(wVar15);
                    objK13 = wVar15;
                }
                AtomicInteger atomicInteger8 = w1.k.f6311a;
                appendedSemanticsElement = new AppendedSemanticsElement((a4.c) objK13, true);
                if (!z3) {
                    z22 = false;
                } else {
                    z22 = false;
                }
                float f12 = v0.f6117a;
                if (z22) {
                    xVar4 = xVar;
                    appendedSemanticsElement2 = appendedSemanticsElement;
                    o0 o0Var116 = o0Var3;
                    o0Var3 = o0Var116;
                    pVarA4 = r0.a.a(mVar, new c0.u2(s0Var, o0Var116, xVar4, rVar5, 1));
                } else {
                    xVar4 = xVar;
                    appendedSemanticsElement2 = appendedSemanticsElement;
                    pVarA4 = mVar;
                }
                zH7 = vVar3.h(e0Var4);
                objK14 = vVar3.K();
                if (zH7) {
                    objK14 = new j(e0Var4, 0);
                    vVar3.f0(objK14);
                } else {
                    objK14 = new j(e0Var4, 0);
                    vVar3.f0(objK14);
                }
                f0.l.c(e0Var4, (a4.c) objK14, vVar3);
                boolean zH111110 = vVar3.h(o0Var3) | vVar3.h(yVar);
                if (i12 == 4) {
                    z23 = true;
                } else {
                    z23 = false;
                }
                z24 = zH111110 | z23 | ((i18 <= 32 && vVar3.f(nVar5)) || (i22 & 48) == 32);
                objK15 = vVar3.K();
                if (z24) {
                    objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                    vVar3.f0(objK15);
                } else {
                    objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                    vVar3.f0(objK15);
                }
                f0.l.c(nVar5, (a4.c) objK15, vVar3);
                nVar6 = nVar4;
                q qVar8 = o0Var3.f6025t;
                if (i5 == 1) {
                    z25 = true;
                } else {
                    z25 = false;
                }
                f0.h hVar13 = hVar3;
                int i211 = nVar5.f2043e;
                nVar7 = nVar5;
                e2.r rVar11115 = rVar5;
                e0Var5 = e0Var4;
                r0.p pVarA111 = r0.a.a(mVar, new y0(o0Var3, e0Var5, xVar4, true, z25, rVar11115, f1Var, qVar8, i211));
                boolean zBooleanValue8 = ((Boolean) i1VarV3.getValue()).booleanValue();
                zH8 = vVar3.h(o0Var3) | ((i18 <= 32 && vVar3.f(nVar7)) || (i22 & 48) == 32) | vVar3.h(cVar6);
                objK16 = vVar3.K();
                if (zH8) {
                    objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                    vVar3.f0(objK16);
                } else {
                    objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                    vVar3.f0(objK16);
                }
                r0.p pVarD16 = androidx.compose.ui.layout.a.d(r0.a.a(androidx.compose.ui.input.key.a.b(androidx.compose.ui.input.key.a.b(androidx.compose.foundation.text.input.internal.a.a(pVar, cVar6, o0Var3, e0Var5).e(androidx.compose.foundation.text.handwriting.a.a((a4.a) objK16, zBooleanValue8)).e(pVar3), new c2(gVar, 23, o0Var3)), new c2(o0Var3, 22, e0Var5)).e(pVarA111), new n.r(c1Var, z3, kVar)).e(pVarE8).e(appendedSemanticsElement2), new q(o0Var3, 0));
                if (!z3) {
                    z26 = false;
                } else {
                    z26 = false;
                }
                if (z26) {
                    if (n.q0.a()) {
                        pVarA5 = mVar;
                    } else {
                        pVarA5 = r0.a.a(mVar, new s3(6, e0Var5));
                    }
                    pVar5 = pVarA5;
                } else {
                    pVar5 = mVar;
                }
                vVar2 = vVar3;
                d(pVarD16, e0Var5, n0.i.c(-374338080, new o(dVar, o0Var3, h0Var, i6, i5, c1Var, xVar, f0Var, pVarA4, pVarA110, pVarD15, pVar5, cVar4, e0Var5, z26, cVar2, rVar11115, bVar2), vVar2), vVar2, 384);
            }
            l0Var = p.l0.f4525d;
            Object[] objArr2 = {l0Var};
            a2.h hVar14 = c1.f5868f;
            zF = vVar3.f(l0Var);
            objK = vVar3.K();
            if (zF) {
                objK = new b0.a(26, l0Var);
                vVar3.f0(objK);
            } else {
                objK = new b0.a(26, l0Var);
                vVar3.f0(objK);
            }
            c1Var = (c1) o0.g.b(objArr2, hVar14, (a4.a) objK, vVar3, 0, 4);
            i12 = i21 & 14;
            if (i12 == 4) {
                i13 = i11;
            } else {
                i13 = 0;
            }
            if ((i21 & 57344) == 16384) {
                i14 = i11;
            } else {
                i14 = 0;
            }
            i15 = i14 | i13;
            objK2 = vVar3.K();
            if (i15 == 0) {
                e0VarL = l(f0Var, fVar2);
                if (g0Var3 != null) {
                    long j9 = g0Var3.f6770a;
                    e2.r rVar24 = e0VarL.f2020b;
                    int i212 = y1.g0.f6769c;
                    int iB3 = rVar24.b((int) (j9 >> 32));
                    int iB4 = rVar24.b((int) (j9 & 4294967295L));
                    int iMin2 = Math.min(iB3, iB4);
                    int iMax2 = Math.max(iB3, iB4);
                    y1.c cVar8 = new y1.c(e0VarL.f2019a);
                    cVar8.a(new y1.a0(0L, 0L, (d2.k) null, (d2.i) null, (d2.j) null, (d2.p) null, (String) null, 0L, (j2.a) null, (j2.n) null, (f2.b) null, 0L, j2.j.f3323c, (y0.p0) null, 61439), iMin2, iMax2);
                    e0VarL = new e2.e0(cVar8.c(), rVar24);
                }
                objK2 = e0VarL;
                vVar3.f0(objK2);
            } else {
                e0VarL = l(f0Var, fVar2);
                if (g0Var3 != null) {
                    long j10 = g0Var3.f6770a;
                    e2.r rVar25 = e0VarL.f2020b;
                    int i213 = y1.g0.f6769c;
                    int iB5 = rVar25.b((int) (j10 >> 32));
                    int iB6 = rVar25.b((int) (j10 & 4294967295L));
                    int iMin3 = Math.min(iB5, iB6);
                    int iMax3 = Math.max(iB5, iB6);
                    y1.c cVar9 = new y1.c(e0VarL.f2019a);
                    cVar9.a(new y1.a0(0L, 0L, (d2.k) null, (d2.i) null, (d2.j) null, (d2.p) null, (String) null, 0L, (j2.a) null, (j2.n) null, (f2.b) null, 0L, j2.j.f3323c, (y0.p0) null, 61439), iMin3, iMax3);
                    e0VarL = new e2.e0(cVar9.c(), rVar25);
                }
                objK2 = e0VarL;
                vVar3.f0(objK2);
            }
            e0Var = (e2.e0) objK2;
            fVar = e0Var.f2019a;
            rVar = e0Var.f2020b;
            b2VarW = vVar3.w();
            if (b2VarW != null) {
                throw new IllegalStateException("no recompose scope found");
            }
            b2VarW.f2096b |= 1;
            zF2 = vVar3.f(k2Var);
            objK3 = vVar3.K();
            if (zF2) {
                h0Var2 = h0Var;
                z4 = z2;
                t0 t0Var3 = new t0(fVar, h0Var2, z4, bVar3, dVar3, 0);
                g0Var = g0Var3;
                bVar = bVar3;
                dVar2 = dVar3;
                objK3 = new o0(t0Var3, b2VarW, k2Var);
                vVar3.f0(objK3);
            } else {
                h0Var2 = h0Var;
                z4 = z2;
                t0 t0Var4 = new t0(fVar, h0Var2, z4, bVar3, dVar3, 0);
                g0Var = g0Var3;
                bVar = bVar3;
                dVar2 = dVar3;
                objK3 = new o0(t0Var4, b2VarW, k2Var);
                vVar3.f0(objK3);
            }
            o0Var = (o0) objK3;
            o0Var.f6024s = cVar;
            o0Var.f6028w = j7;
            a1.b bVar5 = o0Var.f6023r;
            bVar5.f24e = m0Var;
            bVar5.f25f = gVar;
            o0Var.f6015j = fVar2;
            t0Var = o0Var.f6006a;
            if (!b4.l.a(t0Var.f6098a, fVar)) {
                bVar2 = bVar;
                t0Var = new t0(fVar, h0Var2, z4, bVar2, dVar2, 0);
            } else {
                bVar2 = bVar;
                t0Var = new t0(fVar, h0Var2, z4, bVar2, dVar2, 0);
            }
            if (o0Var.f6006a != t0Var) {
                o0Var.f6021p = true;
            }
            o0Var.f6006a = t0Var;
            hVar = o0Var.f6009d;
            d0Var = o0Var.f6010e;
            hVar.getClass();
            j5 = xVar.f2063b;
            g0Var2 = g0Var;
            boolean zA2 = b4.l.a(g0Var2, ((e2.j) hVar.f50f).c());
            if (b4.l.a(((e2.x) hVar.f49e).f2062a, fVar2)) {
                hVar.f50f = new e2.j(fVar2, j5);
                z5 = true;
            } else {
                if (y1.g0.a(((e2.x) hVar.f49e).f2063b, j5)) {
                    ((e2.j) hVar.f50f).f(y1.g0.e(j5), y1.g0.d(j5));
                    z5 = false;
                    z6 = true;
                } else {
                    z5 = false;
                }
                if (g0Var2 == null) {
                    e2.j jVar6 = (e2.j) hVar.f50f;
                    jVar6.f2030d = -1;
                    jVar6.f2031e = -1;
                } else {
                    j6 = g0Var2.f6770a;
                    if (!y1.g0.b(j6)) {
                        ((e2.j) hVar.f50f).e(y1.g0.e(j6), y1.g0.d(j6));
                    }
                }
                if (z5) {
                    e2.j jVar7 = (e2.j) hVar.f50f;
                    jVar7.f2030d = -1;
                    jVar7.f2031e = -1;
                    xVarA = e2.x.a(xVar, null, 0L, 3);
                } else {
                    e2.j jVar8 = (e2.j) hVar.f50f;
                    jVar8.f2030d = -1;
                    jVar8.f2031e = -1;
                    xVarA = e2.x.a(xVar, null, 0L, 3);
                }
                xVar2 = (e2.x) hVar.f49e;
                hVar.f49e = xVarA;
                if (d0Var != null) {
                    d0Var.a(xVar2, xVarA);
                }
                objK4 = vVar3.K();
                if (objK4 == hVar4) {
                    objK4 = new f1();
                    vVar3.f0(objK4);
                }
                f1Var = (f1) objK4;
                jCurrentTimeMillis = System.currentTimeMillis();
                if (f1Var.f5925e) {
                    f1Var.f5924d = Long.valueOf(jCurrentTimeMillis);
                    f1Var.a(xVar);
                } else {
                    l5 = f1Var.f5924d;
                    if (jCurrentTimeMillis > (l5 != null ? l5.longValue() : 0L) + ((long) 5000)) {
                        f1Var.f5924d = Long.valueOf(jCurrentTimeMillis);
                        f1Var.a(xVar);
                    }
                }
                objK5 = vVar3.K();
                if (objK5 == hVar4) {
                    objK5 = new z.e0(f1Var);
                    vVar3.f0(objK5);
                }
                e0Var2 = (z.e0) objK5;
                e0Var2.f6928b = rVar;
                e0Var2.f6929c = o0Var.f6025t;
                e0Var2.f6930d = o0Var;
                e0Var2.f6931e.setValue(xVar);
                e0Var2.f6932f = (q1.f1) vVar3.j(j1.f5235d);
                e0Var2.f6933g = (l2) vVar3.j(j1.f5246o);
                e0Var2.f6934h = (f1.a) vVar3.j(j1.f5241j);
                nVar2 = nVar8;
                e0Var2.f6935i = nVar2;
                e0Var2.f6936j.setValue(true);
                e0Var2.f6937k.setValue(Boolean.valueOf(z3));
                objK6 = vVar3.K();
                if (objK6 == hVar4) {
                    f0.i0 i0Var3 = new f0.i0(f0.l.l(vVar3));
                    vVar3.f0(i0Var3);
                    objK6 = i0Var3;
                }
                vVar = ((f0.i0) objK6).f2194d;
                objK7 = vVar3.K();
                if (objK7 == hVar4) {
                    objK7 = new s.c();
                    vVar3.f0(objK7);
                }
                cVar3 = (s.c) objK7;
                boolean zH21 = vVar3.h(o0Var);
                i16 = i22 & 7168;
                if (i16 == 2048) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z219 = zH21 | z7;
                i17 = i22 & 57344;
                if (i17 == 16384) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean zH121 = z8 | z219 | vVar3.h(yVar2);
                if (i12 == 4) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                boolean z2110 = zH121 | z9;
                i18 = (i22 & 112) ^ 48;
                if (i18 > 32) {
                }
                o0Var2 = o0Var;
                if ((i22 & 48) != 32) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                zH = z2110 | z10 | vVar3.h(rVar) | vVar3.h(vVar) | vVar3.h(cVar3) | vVar3.h(e0Var2);
                objK8 = vVar3.K();
                if (zH) {
                    o0Var3 = o0Var2;
                    yVar = yVar2;
                    i19 = 32;
                    nVar3 = nVar;
                    r rVar26 = new r(o0Var3, z3, yVar, xVar, nVar3, rVar, e0Var2, vVar, cVar3);
                    xVar3 = xVar;
                    cVar4 = cVar3;
                    z11 = z3;
                    vVar3.f0(rVar26);
                    objK8 = rVar26;
                } else {
                    o0Var3 = o0Var2;
                    yVar = yVar2;
                    i19 = 32;
                    nVar3 = nVar;
                    r rVar27 = new r(o0Var3, z3, yVar, xVar, nVar3, rVar, e0Var2, vVar, cVar3);
                    xVar3 = xVar;
                    cVar4 = cVar3;
                    z11 = z3;
                    vVar3.f0(rVar27);
                    objK8 = rVar27;
                }
                pVarA = androidx.compose.foundation.c.a(androidx.compose.ui.focus.a.b(androidx.compose.ui.focus.a.a(nVar2), (a4.c) objK8), z11, kVar);
                i1 i1VarV4 = f0.l.v(Boolean.valueOf(z11), vVar3);
                boolean zF14 = vVar3.f(i1VarV4) | vVar3.h(o0Var3) | vVar3.h(yVar) | vVar3.h(e0Var2);
                if (i18 > i19) {
                    pVar3 = pVarA;
                    if ((i22 & 48) != i19) {
                        z12 = true;
                    } else {
                        z12 = false;
                    }
                } else {
                    pVar3 = pVarA;
                    if ((i22 & 48) != i19) {
                        z12 = true;
                    } else {
                        z12 = false;
                    }
                }
                z13 = z12 | zF14;
                objK9 = vVar3.K();
                if (z13) {
                    if (objK9 == hVar4) {
                        hVar2 = hVar4;
                    } else {
                        hVar3 = hVar4;
                        rVar2 = rVar;
                        i2Var = objK9;
                    }
                    f0.l.e((a4.e) i2Var, vVar3, p3.n.f5026a);
                    zH2 = vVar3.h(o0Var3);
                    objK10 = vVar3.K();
                    if (zH2) {
                        objK10 = new q(o0Var3, 1);
                        vVar3.f0(objK10);
                    } else {
                        objK10 = new q(o0Var3, 1);
                        vVar3.f0(objK10);
                    }
                    b0.t tVar9 = new b0.t((a4.c) objK10, null, 23);
                    mVar = r0.m.f5680a;
                    pVarA2 = j1.z.a(mVar, 8675309, tVar9);
                    boolean zH111111 = vVar3.h(o0Var3);
                    if (i17 == 16384) {
                        z14 = true;
                    } else {
                        z14 = false;
                    }
                    boolean z2111 = zH111111 | z14;
                    if (i16 == 2048) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    zH3 = z2111 | z15 | vVar3.h(rVar2) | vVar3.h(e0Var2);
                    objK11 = vVar3.K();
                    if (zH3) {
                        e2.r rVar11116 = rVar2;
                        pVar4 = pVarA2;
                        c0.w0 w0Var16 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar11116);
                        rVar3 = rVar11116;
                        vVar3.f0(w0Var16);
                        objK11 = w0Var16;
                    } else {
                        e2.r rVar11117 = rVar2;
                        pVar4 = pVarA2;
                        c0.w0 w0Var17 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar11117);
                        rVar3 = rVar11117;
                        vVar3.f0(w0Var17);
                        objK11 = w0Var17;
                    }
                    cVar5 = (a4.c) objK11;
                    if (z3) {
                        pVarA3 = r0.a.a(pVar4, new h2.b(cVar5, 3, kVar));
                    } else {
                        pVarA3 = pVar4;
                    }
                    a1.d dVar12 = e0Var2.f6947u;
                    z.c0 c0Var9 = e0Var2.f6946t;
                    r0.p pVarE9 = pVarA3.e(new SuspendPointerInputElement(dVar12, c0Var9, new b0.d(dVar12, c0Var9, (t3.c) null, 12), 4)).e(new PointerHoverIconModifierElement());
                    boolean zH111112 = vVar3.h(o0Var3);
                    if (i12 == 4) {
                        z16 = true;
                    } else {
                        z16 = false;
                    }
                    zH4 = zH111112 | z16 | vVar3.h(rVar3);
                    objK12 = vVar3.K();
                    if (zH4) {
                        objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                        vVar3.f0(objK12);
                    } else {
                        objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                        vVar3.f0(objK12);
                    }
                    r0.p pVarA112 = androidx.compose.ui.draw.a.a(mVar, (a4.c) objK12);
                    boolean zH111113 = vVar3.h(o0Var3);
                    if (i16 == 2048) {
                        z17 = true;
                    } else {
                        z17 = false;
                    }
                    boolean zF15 = zH111113 | z17 | vVar3.f(u2Var2) | vVar3.h(e0Var2);
                    if (i12 == 4) {
                        z18 = true;
                    } else {
                        z18 = false;
                    }
                    zH5 = zF15 | z18 | vVar3.h(rVar3);
                    Object objK28 = vVar3.K();
                    if (zH5) {
                        rVar4 = rVar3;
                        e0Var3 = e0Var2;
                        sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                        u2Var = u2Var2;
                        vVar3.f0(sVar);
                    } else {
                        rVar4 = rVar3;
                        e0Var3 = e0Var2;
                        sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                        u2Var = u2Var2;
                        vVar3.f0(sVar);
                    }
                    r0.p pVarD17 = androidx.compose.ui.layout.a.d(mVar, (a4.c) sVar);
                    boolean zH111114 = vVar3.h(e0Var);
                    if (r3 == 4) {
                        z19 = true;
                    } else {
                        z19 = false;
                    }
                    boolean z38 = zH111114 | z19;
                    if (i16 == 2048) {
                        z20 = true;
                    } else {
                        z20 = false;
                    }
                    boolean zG9 = z38 | z20 | vVar3.g(false);
                    if (i17 == 16384) {
                        z21 = true;
                    } else {
                        z21 = false;
                    }
                    zH6 = zG9 | z21 | vVar3.h(o0Var3) | vVar3.h(rVar4) | vVar3.h(e0Var3) | ((i18 <= 32 && vVar3.f(nVar)) || (i22 & 48) == 32);
                    objK13 = vVar3.K();
                    if (zH6) {
                        nVar4 = nVar2;
                        e0Var4 = e0Var3;
                        o0 o0Var117 = o0Var3;
                        w wVar16 = new w(e0Var, xVar, z3, nVar, o0Var117, rVar4, e0Var4, nVar4);
                        e2.r rVar11118 = rVar4;
                        nVar5 = nVar;
                        rVar5 = rVar11118;
                        o0Var3 = o0Var117;
                        vVar3.f0(wVar16);
                        objK13 = wVar16;
                    } else {
                        nVar4 = nVar2;
                        e0Var4 = e0Var3;
                        o0 o0Var118 = o0Var3;
                        w wVar17 = new w(e0Var, xVar, z3, nVar, o0Var118, rVar4, e0Var4, nVar4);
                        e2.r rVar11119 = rVar4;
                        nVar5 = nVar;
                        rVar5 = rVar11119;
                        o0Var3 = o0Var118;
                        vVar3.f0(wVar17);
                        objK13 = wVar17;
                    }
                    AtomicInteger atomicInteger9 = w1.k.f6311a;
                    appendedSemanticsElement = new AppendedSemanticsElement((a4.c) objK13, true);
                    if (!z3) {
                        z22 = false;
                    } else {
                        z22 = false;
                    }
                    float f13 = v0.f6117a;
                    if (z22) {
                        xVar4 = xVar;
                        appendedSemanticsElement2 = appendedSemanticsElement;
                        o0 o0Var119 = o0Var3;
                        o0Var3 = o0Var119;
                        pVarA4 = r0.a.a(mVar, new c0.u2(s0Var, o0Var119, xVar4, rVar5, 1));
                    } else {
                        xVar4 = xVar;
                        appendedSemanticsElement2 = appendedSemanticsElement;
                        pVarA4 = mVar;
                    }
                    zH7 = vVar3.h(e0Var4);
                    objK14 = vVar3.K();
                    if (zH7) {
                        objK14 = new j(e0Var4, 0);
                        vVar3.f0(objK14);
                    } else {
                        objK14 = new j(e0Var4, 0);
                        vVar3.f0(objK14);
                    }
                    f0.l.c(e0Var4, (a4.c) objK14, vVar3);
                    boolean zH111115 = vVar3.h(o0Var3) | vVar3.h(yVar);
                    if (i12 == 4) {
                        z23 = true;
                    } else {
                        z23 = false;
                    }
                    z24 = zH111115 | z23 | ((i18 <= 32 && vVar3.f(nVar5)) || (i22 & 48) == 32);
                    objK15 = vVar3.K();
                    if (z24) {
                        objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                        vVar3.f0(objK15);
                    } else {
                        objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                        vVar3.f0(objK15);
                    }
                    f0.l.c(nVar5, (a4.c) objK15, vVar3);
                    nVar6 = nVar4;
                    q qVar9 = o0Var3.f6025t;
                    if (i5 == 1) {
                        z25 = true;
                    } else {
                        z25 = false;
                    }
                    f0.h hVar15 = hVar3;
                    int i214 = nVar5.f2043e;
                    nVar7 = nVar5;
                    e2.r rVar111110 = rVar5;
                    e0Var5 = e0Var4;
                    r0.p pVarA113 = r0.a.a(mVar, new y0(o0Var3, e0Var5, xVar4, true, z25, rVar111110, f1Var, qVar9, i214));
                    boolean zBooleanValue9 = ((Boolean) i1VarV4.getValue()).booleanValue();
                    zH8 = vVar3.h(o0Var3) | ((i18 <= 32 && vVar3.f(nVar7)) || (i22 & 48) == 32) | vVar3.h(cVar6);
                    objK16 = vVar3.K();
                    if (zH8) {
                        objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                        vVar3.f0(objK16);
                    } else {
                        objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                        vVar3.f0(objK16);
                    }
                    r0.p pVarD18 = androidx.compose.ui.layout.a.d(r0.a.a(androidx.compose.ui.input.key.a.b(androidx.compose.ui.input.key.a.b(androidx.compose.foundation.text.input.internal.a.a(pVar, cVar6, o0Var3, e0Var5).e(androidx.compose.foundation.text.handwriting.a.a((a4.a) objK16, zBooleanValue9)).e(pVar3), new c2(gVar, 23, o0Var3)), new c2(o0Var3, 22, e0Var5)).e(pVarA113), new n.r(c1Var, z3, kVar)).e(pVarE9).e(appendedSemanticsElement2), new q(o0Var3, 0));
                    if (!z3) {
                        z26 = false;
                    } else {
                        z26 = false;
                    }
                    if (z26) {
                        if (n.q0.a()) {
                            pVarA5 = mVar;
                        } else {
                            pVarA5 = r0.a.a(mVar, new s3(6, e0Var5));
                        }
                        pVar5 = pVarA5;
                    } else {
                        pVar5 = mVar;
                    }
                    vVar2 = vVar3;
                    d(pVarD18, e0Var5, n0.i.c(-374338080, new o(dVar, o0Var3, h0Var, i6, i5, c1Var, xVar, f0Var, pVarA4, pVarA112, pVarD17, pVar5, cVar4, e0Var5, z26, cVar2, rVar111110, bVar2), vVar2), vVar2, 384);
                } else {
                    hVar2 = r1;
                }
                rVar2 = rVar;
                hVar3 = hVar2;
                i2Var = new i2(o0Var3, i1VarV4, yVar, e0Var2, nVar3, null, 3);
                vVar3.f0(i2Var);
                f0.l.e((a4.e) i2Var, vVar3, p3.n.f5026a);
                zH2 = vVar3.h(o0Var3);
                objK10 = vVar3.K();
                if (zH2) {
                    objK10 = new q(o0Var3, 1);
                    vVar3.f0(objK10);
                } else {
                    objK10 = new q(o0Var3, 1);
                    vVar3.f0(objK10);
                }
                b0.t tVar10 = new b0.t((a4.c) objK10, null, 23);
                mVar = r0.m.f5680a;
                pVarA2 = j1.z.a(mVar, 8675309, tVar10);
                boolean zH111116 = vVar3.h(o0Var3);
                if (i17 == 16384) {
                    z14 = true;
                } else {
                    z14 = false;
                }
                boolean z2112 = zH111116 | z14;
                if (i16 == 2048) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                zH3 = z2112 | z15 | vVar3.h(rVar2) | vVar3.h(e0Var2);
                objK11 = vVar3.K();
                if (zH3) {
                    e2.r rVar111111 = rVar2;
                    pVar4 = pVarA2;
                    c0.w0 w0Var18 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar111111);
                    rVar3 = rVar111111;
                    vVar3.f0(w0Var18);
                    objK11 = w0Var18;
                } else {
                    e2.r rVar111112 = rVar2;
                    pVar4 = pVarA2;
                    c0.w0 w0Var19 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar111112);
                    rVar3 = rVar111112;
                    vVar3.f0(w0Var19);
                    objK11 = w0Var19;
                }
                cVar5 = (a4.c) objK11;
                if (z3) {
                    pVarA3 = r0.a.a(pVar4, new h2.b(cVar5, 3, kVar));
                } else {
                    pVarA3 = pVar4;
                }
                a1.d dVar13 = e0Var2.f6947u;
                z.c0 c0Var10 = e0Var2.f6946t;
                r0.p pVarE10 = pVarA3.e(new SuspendPointerInputElement(dVar13, c0Var10, new b0.d(dVar13, c0Var10, (t3.c) null, 12), 4)).e(new PointerHoverIconModifierElement());
                boolean zH111117 = vVar3.h(o0Var3);
                if (i12 == 4) {
                    z16 = true;
                } else {
                    z16 = false;
                }
                zH4 = zH111117 | z16 | vVar3.h(rVar3);
                objK12 = vVar3.K();
                if (zH4) {
                    objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                    vVar3.f0(objK12);
                } else {
                    objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                    vVar3.f0(objK12);
                }
                r0.p pVarA114 = androidx.compose.ui.draw.a.a(mVar, (a4.c) objK12);
                boolean zH111118 = vVar3.h(o0Var3);
                if (i16 == 2048) {
                    z17 = true;
                } else {
                    z17 = false;
                }
                boolean zF16 = zH111118 | z17 | vVar3.f(u2Var2) | vVar3.h(e0Var2);
                if (i12 == 4) {
                    z18 = true;
                } else {
                    z18 = false;
                }
                zH5 = zF16 | z18 | vVar3.h(rVar3);
                Object objK29 = vVar3.K();
                if (zH5) {
                    rVar4 = rVar3;
                    e0Var3 = e0Var2;
                    sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                    u2Var = u2Var2;
                    vVar3.f0(sVar);
                } else {
                    rVar4 = rVar3;
                    e0Var3 = e0Var2;
                    sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                    u2Var = u2Var2;
                    vVar3.f0(sVar);
                }
                r0.p pVarD19 = androidx.compose.ui.layout.a.d(mVar, (a4.c) sVar);
                boolean zH111119 = vVar3.h(e0Var);
                if (r3 == 4) {
                    z19 = true;
                } else {
                    z19 = false;
                }
                boolean z39 = zH111119 | z19;
                if (i16 == 2048) {
                    z20 = true;
                } else {
                    z20 = false;
                }
                boolean zG10 = z39 | z20 | vVar3.g(false);
                if (i17 == 16384) {
                    z21 = true;
                } else {
                    z21 = false;
                }
                zH6 = zG10 | z21 | vVar3.h(o0Var3) | vVar3.h(rVar4) | vVar3.h(e0Var3) | ((i18 <= 32 && vVar3.f(nVar)) || (i22 & 48) == 32);
                objK13 = vVar3.K();
                if (zH6) {
                    nVar4 = nVar2;
                    e0Var4 = e0Var3;
                    o0 o0Var1110 = o0Var3;
                    w wVar18 = new w(e0Var, xVar, z3, nVar, o0Var1110, rVar4, e0Var4, nVar4);
                    e2.r rVar111113 = rVar4;
                    nVar5 = nVar;
                    rVar5 = rVar111113;
                    o0Var3 = o0Var1110;
                    vVar3.f0(wVar18);
                    objK13 = wVar18;
                } else {
                    nVar4 = nVar2;
                    e0Var4 = e0Var3;
                    o0 o0Var1111 = o0Var3;
                    w wVar19 = new w(e0Var, xVar, z3, nVar, o0Var1111, rVar4, e0Var4, nVar4);
                    e2.r rVar111114 = rVar4;
                    nVar5 = nVar;
                    rVar5 = rVar111114;
                    o0Var3 = o0Var1111;
                    vVar3.f0(wVar19);
                    objK13 = wVar19;
                }
                AtomicInteger atomicInteger10 = w1.k.f6311a;
                appendedSemanticsElement = new AppendedSemanticsElement((a4.c) objK13, true);
                if (!z3) {
                    z22 = false;
                } else {
                    z22 = false;
                }
                float f14 = v0.f6117a;
                if (z22) {
                    xVar4 = xVar;
                    appendedSemanticsElement2 = appendedSemanticsElement;
                    o0 o0Var1112 = o0Var3;
                    o0Var3 = o0Var1112;
                    pVarA4 = r0.a.a(mVar, new c0.u2(s0Var, o0Var1112, xVar4, rVar5, 1));
                } else {
                    xVar4 = xVar;
                    appendedSemanticsElement2 = appendedSemanticsElement;
                    pVarA4 = mVar;
                }
                zH7 = vVar3.h(e0Var4);
                objK14 = vVar3.K();
                if (zH7) {
                    objK14 = new j(e0Var4, 0);
                    vVar3.f0(objK14);
                } else {
                    objK14 = new j(e0Var4, 0);
                    vVar3.f0(objK14);
                }
                f0.l.c(e0Var4, (a4.c) objK14, vVar3);
                boolean zH1111110 = vVar3.h(o0Var3) | vVar3.h(yVar);
                if (i12 == 4) {
                    z23 = true;
                } else {
                    z23 = false;
                }
                z24 = zH1111110 | z23 | ((i18 <= 32 && vVar3.f(nVar5)) || (i22 & 48) == 32);
                objK15 = vVar3.K();
                if (z24) {
                    objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                    vVar3.f0(objK15);
                } else {
                    objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                    vVar3.f0(objK15);
                }
                f0.l.c(nVar5, (a4.c) objK15, vVar3);
                nVar6 = nVar4;
                q qVar10 = o0Var3.f6025t;
                if (i5 == 1) {
                    z25 = true;
                } else {
                    z25 = false;
                }
                f0.h hVar16 = hVar3;
                int i215 = nVar5.f2043e;
                nVar7 = nVar5;
                e2.r rVar111115 = rVar5;
                e0Var5 = e0Var4;
                r0.p pVarA115 = r0.a.a(mVar, new y0(o0Var3, e0Var5, xVar4, true, z25, rVar111115, f1Var, qVar10, i215));
                boolean zBooleanValue10 = ((Boolean) i1VarV4.getValue()).booleanValue();
                zH8 = vVar3.h(o0Var3) | ((i18 <= 32 && vVar3.f(nVar7)) || (i22 & 48) == 32) | vVar3.h(cVar6);
                objK16 = vVar3.K();
                if (zH8) {
                    objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                    vVar3.f0(objK16);
                } else {
                    objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                    vVar3.f0(objK16);
                }
                r0.p pVarD110 = androidx.compose.ui.layout.a.d(r0.a.a(androidx.compose.ui.input.key.a.b(androidx.compose.ui.input.key.a.b(androidx.compose.foundation.text.input.internal.a.a(pVar, cVar6, o0Var3, e0Var5).e(androidx.compose.foundation.text.handwriting.a.a((a4.a) objK16, zBooleanValue10)).e(pVar3), new c2(gVar, 23, o0Var3)), new c2(o0Var3, 22, e0Var5)).e(pVarA115), new n.r(c1Var, z3, kVar)).e(pVarE10).e(appendedSemanticsElement2), new q(o0Var3, 0));
                if (!z3) {
                    z26 = false;
                } else {
                    z26 = false;
                }
                if (z26) {
                    if (n.q0.a()) {
                        pVarA5 = mVar;
                    } else {
                        pVarA5 = r0.a.a(mVar, new s3(6, e0Var5));
                    }
                    pVar5 = pVarA5;
                } else {
                    pVar5 = mVar;
                }
                vVar2 = vVar3;
                d(pVarD110, e0Var5, n0.i.c(-374338080, new o(dVar, o0Var3, h0Var, i6, i5, c1Var, xVar, f0Var, pVarA4, pVarA114, pVarD19, pVar5, cVar4, e0Var5, z26, cVar2, rVar111115, bVar2), vVar2), vVar2, 384);
            }
            z6 = false;
            if (g0Var2 == null) {
                e2.j jVar9 = (e2.j) hVar.f50f;
                jVar9.f2030d = -1;
                jVar9.f2031e = -1;
            } else {
                j6 = g0Var2.f6770a;
                if (!y1.g0.b(j6)) {
                    ((e2.j) hVar.f50f).e(y1.g0.e(j6), y1.g0.d(j6));
                }
            }
            if (z5) {
                e2.j jVar10 = (e2.j) hVar.f50f;
                jVar10.f2030d = -1;
                jVar10.f2031e = -1;
                xVarA = e2.x.a(xVar, null, 0L, 3);
            } else {
                e2.j jVar11 = (e2.j) hVar.f50f;
                jVar11.f2030d = -1;
                jVar11.f2031e = -1;
                xVarA = e2.x.a(xVar, null, 0L, 3);
            }
            xVar2 = (e2.x) hVar.f49e;
            hVar.f49e = xVarA;
            if (d0Var != null) {
                d0Var.a(xVar2, xVarA);
            }
            objK4 = vVar3.K();
            if (objK4 == hVar4) {
                objK4 = new f1();
                vVar3.f0(objK4);
            }
            f1Var = (f1) objK4;
            jCurrentTimeMillis = System.currentTimeMillis();
            if (f1Var.f5925e) {
                f1Var.f5924d = Long.valueOf(jCurrentTimeMillis);
                f1Var.a(xVar);
            } else {
                l5 = f1Var.f5924d;
                if (jCurrentTimeMillis > (l5 != null ? l5.longValue() : 0L) + ((long) 5000)) {
                    f1Var.f5924d = Long.valueOf(jCurrentTimeMillis);
                    f1Var.a(xVar);
                }
            }
            objK5 = vVar3.K();
            if (objK5 == hVar4) {
                objK5 = new z.e0(f1Var);
                vVar3.f0(objK5);
            }
            e0Var2 = (z.e0) objK5;
            e0Var2.f6928b = rVar;
            e0Var2.f6929c = o0Var.f6025t;
            e0Var2.f6930d = o0Var;
            e0Var2.f6931e.setValue(xVar);
            e0Var2.f6932f = (q1.f1) vVar3.j(j1.f5235d);
            e0Var2.f6933g = (l2) vVar3.j(j1.f5246o);
            e0Var2.f6934h = (f1.a) vVar3.j(j1.f5241j);
            nVar2 = nVar8;
            e0Var2.f6935i = nVar2;
            e0Var2.f6936j.setValue(true);
            e0Var2.f6937k.setValue(Boolean.valueOf(z3));
            objK6 = vVar3.K();
            if (objK6 == hVar4) {
                f0.i0 i0Var4 = new f0.i0(f0.l.l(vVar3));
                vVar3.f0(i0Var4);
                objK6 = i0Var4;
            }
            vVar = ((f0.i0) objK6).f2194d;
            objK7 = vVar3.K();
            if (objK7 == hVar4) {
                objK7 = new s.c();
                vVar3.f0(objK7);
            }
            cVar3 = (s.c) objK7;
            boolean zH22 = vVar3.h(o0Var);
            i16 = i22 & 7168;
            if (i16 == 2048) {
                z7 = true;
            } else {
                z7 = false;
            }
            boolean z2113 = zH22 | z7;
            i17 = i22 & 57344;
            if (i17 == 16384) {
                z8 = true;
            } else {
                z8 = false;
            }
            boolean zH122 = z8 | z2113 | vVar3.h(yVar2);
            if (i12 == 4) {
                z9 = true;
            } else {
                z9 = false;
            }
            boolean z2114 = zH122 | z9;
            i18 = (i22 & 112) ^ 48;
            if (i18 > 32) {
            }
            o0Var2 = o0Var;
            if ((i22 & 48) != 32) {
                z10 = true;
            } else {
                z10 = false;
            }
            zH = z2114 | z10 | vVar3.h(rVar) | vVar3.h(vVar) | vVar3.h(cVar3) | vVar3.h(e0Var2);
            objK8 = vVar3.K();
            if (zH) {
                o0Var3 = o0Var2;
                yVar = yVar2;
                i19 = 32;
                nVar3 = nVar;
                r rVar28 = new r(o0Var3, z3, yVar, xVar, nVar3, rVar, e0Var2, vVar, cVar3);
                xVar3 = xVar;
                cVar4 = cVar3;
                z11 = z3;
                vVar3.f0(rVar28);
                objK8 = rVar28;
            } else {
                o0Var3 = o0Var2;
                yVar = yVar2;
                i19 = 32;
                nVar3 = nVar;
                r rVar29 = new r(o0Var3, z3, yVar, xVar, nVar3, rVar, e0Var2, vVar, cVar3);
                xVar3 = xVar;
                cVar4 = cVar3;
                z11 = z3;
                vVar3.f0(rVar29);
                objK8 = rVar29;
            }
            pVarA = androidx.compose.foundation.c.a(androidx.compose.ui.focus.a.b(androidx.compose.ui.focus.a.a(nVar2), (a4.c) objK8), z11, kVar);
            i1 i1VarV5 = f0.l.v(Boolean.valueOf(z11), vVar3);
            boolean zF17 = vVar3.f(i1VarV5) | vVar3.h(o0Var3) | vVar3.h(yVar) | vVar3.h(e0Var2);
            if (i18 > i19) {
                pVar3 = pVarA;
                if ((i22 & 48) != i19) {
                    z12 = true;
                } else {
                    z12 = false;
                }
            } else {
                pVar3 = pVarA;
                if ((i22 & 48) != i19) {
                    z12 = true;
                } else {
                    z12 = false;
                }
            }
            z13 = z12 | zF17;
            objK9 = vVar3.K();
            if (z13) {
                if (objK9 == hVar4) {
                    hVar2 = hVar4;
                } else {
                    hVar3 = hVar4;
                    rVar2 = rVar;
                    i2Var = objK9;
                }
                f0.l.e((a4.e) i2Var, vVar3, p3.n.f5026a);
                zH2 = vVar3.h(o0Var3);
                objK10 = vVar3.K();
                if (zH2) {
                    objK10 = new q(o0Var3, 1);
                    vVar3.f0(objK10);
                } else {
                    objK10 = new q(o0Var3, 1);
                    vVar3.f0(objK10);
                }
                b0.t tVar11 = new b0.t((a4.c) objK10, null, 23);
                mVar = r0.m.f5680a;
                pVarA2 = j1.z.a(mVar, 8675309, tVar11);
                boolean zH1111111 = vVar3.h(o0Var3);
                if (i17 == 16384) {
                    z14 = true;
                } else {
                    z14 = false;
                }
                boolean z2115 = zH1111111 | z14;
                if (i16 == 2048) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                zH3 = z2115 | z15 | vVar3.h(rVar2) | vVar3.h(e0Var2);
                objK11 = vVar3.K();
                if (zH3) {
                    e2.r rVar111116 = rVar2;
                    pVar4 = pVarA2;
                    c0.w0 w0Var110 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar111116);
                    rVar3 = rVar111116;
                    vVar3.f0(w0Var110);
                    objK11 = w0Var110;
                } else {
                    e2.r rVar111117 = rVar2;
                    pVar4 = pVarA2;
                    c0.w0 w0Var111 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar111117);
                    rVar3 = rVar111117;
                    vVar3.f0(w0Var111);
                    objK11 = w0Var111;
                }
                cVar5 = (a4.c) objK11;
                if (z3) {
                    pVarA3 = r0.a.a(pVar4, new h2.b(cVar5, 3, kVar));
                } else {
                    pVarA3 = pVar4;
                }
                a1.d dVar14 = e0Var2.f6947u;
                z.c0 c0Var11 = e0Var2.f6946t;
                r0.p pVarE11 = pVarA3.e(new SuspendPointerInputElement(dVar14, c0Var11, new b0.d(dVar14, c0Var11, (t3.c) null, 12), 4)).e(new PointerHoverIconModifierElement());
                boolean zH1111112 = vVar3.h(o0Var3);
                if (i12 == 4) {
                    z16 = true;
                } else {
                    z16 = false;
                }
                zH4 = zH1111112 | z16 | vVar3.h(rVar3);
                objK12 = vVar3.K();
                if (zH4) {
                    objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                    vVar3.f0(objK12);
                } else {
                    objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                    vVar3.f0(objK12);
                }
                r0.p pVarA116 = androidx.compose.ui.draw.a.a(mVar, (a4.c) objK12);
                boolean zH1111113 = vVar3.h(o0Var3);
                if (i16 == 2048) {
                    z17 = true;
                } else {
                    z17 = false;
                }
                boolean zF18 = zH1111113 | z17 | vVar3.f(u2Var2) | vVar3.h(e0Var2);
                if (i12 == 4) {
                    z18 = true;
                } else {
                    z18 = false;
                }
                zH5 = zF18 | z18 | vVar3.h(rVar3);
                Object objK210 = vVar3.K();
                if (zH5) {
                    rVar4 = rVar3;
                    e0Var3 = e0Var2;
                    sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                    u2Var = u2Var2;
                    vVar3.f0(sVar);
                } else {
                    rVar4 = rVar3;
                    e0Var3 = e0Var2;
                    sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                    u2Var = u2Var2;
                    vVar3.f0(sVar);
                }
                r0.p pVarD111 = androidx.compose.ui.layout.a.d(mVar, (a4.c) sVar);
                boolean zH1111114 = vVar3.h(e0Var);
                if (r3 == 4) {
                    z19 = true;
                } else {
                    z19 = false;
                }
                boolean z310 = zH1111114 | z19;
                if (i16 == 2048) {
                    z20 = true;
                } else {
                    z20 = false;
                }
                boolean zG11 = z310 | z20 | vVar3.g(false);
                if (i17 == 16384) {
                    z21 = true;
                } else {
                    z21 = false;
                }
                zH6 = zG11 | z21 | vVar3.h(o0Var3) | vVar3.h(rVar4) | vVar3.h(e0Var3) | ((i18 <= 32 && vVar3.f(nVar)) || (i22 & 48) == 32);
                objK13 = vVar3.K();
                if (zH6) {
                    nVar4 = nVar2;
                    e0Var4 = e0Var3;
                    o0 o0Var1113 = o0Var3;
                    w wVar110 = new w(e0Var, xVar, z3, nVar, o0Var1113, rVar4, e0Var4, nVar4);
                    e2.r rVar111118 = rVar4;
                    nVar5 = nVar;
                    rVar5 = rVar111118;
                    o0Var3 = o0Var1113;
                    vVar3.f0(wVar110);
                    objK13 = wVar110;
                } else {
                    nVar4 = nVar2;
                    e0Var4 = e0Var3;
                    o0 o0Var1114 = o0Var3;
                    w wVar111 = new w(e0Var, xVar, z3, nVar, o0Var1114, rVar4, e0Var4, nVar4);
                    e2.r rVar111119 = rVar4;
                    nVar5 = nVar;
                    rVar5 = rVar111119;
                    o0Var3 = o0Var1114;
                    vVar3.f0(wVar111);
                    objK13 = wVar111;
                }
                AtomicInteger atomicInteger11 = w1.k.f6311a;
                appendedSemanticsElement = new AppendedSemanticsElement((a4.c) objK13, true);
                if (!z3) {
                    z22 = false;
                } else {
                    z22 = false;
                }
                float f15 = v0.f6117a;
                if (z22) {
                    xVar4 = xVar;
                    appendedSemanticsElement2 = appendedSemanticsElement;
                    o0 o0Var1115 = o0Var3;
                    o0Var3 = o0Var1115;
                    pVarA4 = r0.a.a(mVar, new c0.u2(s0Var, o0Var1115, xVar4, rVar5, 1));
                } else {
                    xVar4 = xVar;
                    appendedSemanticsElement2 = appendedSemanticsElement;
                    pVarA4 = mVar;
                }
                zH7 = vVar3.h(e0Var4);
                objK14 = vVar3.K();
                if (zH7) {
                    objK14 = new j(e0Var4, 0);
                    vVar3.f0(objK14);
                } else {
                    objK14 = new j(e0Var4, 0);
                    vVar3.f0(objK14);
                }
                f0.l.c(e0Var4, (a4.c) objK14, vVar3);
                boolean zH1111115 = vVar3.h(o0Var3) | vVar3.h(yVar);
                if (i12 == 4) {
                    z23 = true;
                } else {
                    z23 = false;
                }
                z24 = zH1111115 | z23 | ((i18 <= 32 && vVar3.f(nVar5)) || (i22 & 48) == 32);
                objK15 = vVar3.K();
                if (z24) {
                    objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                    vVar3.f0(objK15);
                } else {
                    objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                    vVar3.f0(objK15);
                }
                f0.l.c(nVar5, (a4.c) objK15, vVar3);
                nVar6 = nVar4;
                q qVar11 = o0Var3.f6025t;
                if (i5 == 1) {
                    z25 = true;
                } else {
                    z25 = false;
                }
                f0.h hVar17 = hVar3;
                int i216 = nVar5.f2043e;
                nVar7 = nVar5;
                e2.r rVar1111110 = rVar5;
                e0Var5 = e0Var4;
                r0.p pVarA117 = r0.a.a(mVar, new y0(o0Var3, e0Var5, xVar4, true, z25, rVar1111110, f1Var, qVar11, i216));
                boolean zBooleanValue11 = ((Boolean) i1VarV5.getValue()).booleanValue();
                zH8 = vVar3.h(o0Var3) | ((i18 <= 32 && vVar3.f(nVar7)) || (i22 & 48) == 32) | vVar3.h(cVar6);
                objK16 = vVar3.K();
                if (zH8) {
                    objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                    vVar3.f0(objK16);
                } else {
                    objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                    vVar3.f0(objK16);
                }
                r0.p pVarD112 = androidx.compose.ui.layout.a.d(r0.a.a(androidx.compose.ui.input.key.a.b(androidx.compose.ui.input.key.a.b(androidx.compose.foundation.text.input.internal.a.a(pVar, cVar6, o0Var3, e0Var5).e(androidx.compose.foundation.text.handwriting.a.a((a4.a) objK16, zBooleanValue11)).e(pVar3), new c2(gVar, 23, o0Var3)), new c2(o0Var3, 22, e0Var5)).e(pVarA117), new n.r(c1Var, z3, kVar)).e(pVarE11).e(appendedSemanticsElement2), new q(o0Var3, 0));
                if (!z3) {
                    z26 = false;
                } else {
                    z26 = false;
                }
                if (z26) {
                    if (n.q0.a()) {
                        pVarA5 = mVar;
                    } else {
                        pVarA5 = r0.a.a(mVar, new s3(6, e0Var5));
                    }
                    pVar5 = pVarA5;
                } else {
                    pVar5 = mVar;
                }
                vVar2 = vVar3;
                d(pVarD112, e0Var5, n0.i.c(-374338080, new o(dVar, o0Var3, h0Var, i6, i5, c1Var, xVar, f0Var, pVarA4, pVarA116, pVarD111, pVar5, cVar4, e0Var5, z26, cVar2, rVar1111110, bVar2), vVar2), vVar2, 384);
            } else {
                hVar2 = r1;
            }
            rVar2 = rVar;
            hVar3 = hVar2;
            i2Var = new i2(o0Var3, i1VarV5, yVar, e0Var2, nVar3, null, 3);
            vVar3.f0(i2Var);
            f0.l.e((a4.e) i2Var, vVar3, p3.n.f5026a);
            zH2 = vVar3.h(o0Var3);
            objK10 = vVar3.K();
            if (zH2) {
                objK10 = new q(o0Var3, 1);
                vVar3.f0(objK10);
            } else {
                objK10 = new q(o0Var3, 1);
                vVar3.f0(objK10);
            }
            b0.t tVar12 = new b0.t((a4.c) objK10, null, 23);
            mVar = r0.m.f5680a;
            pVarA2 = j1.z.a(mVar, 8675309, tVar12);
            boolean zH1111116 = vVar3.h(o0Var3);
            if (i17 == 16384) {
                z14 = true;
            } else {
                z14 = false;
            }
            boolean z2116 = zH1111116 | z14;
            if (i16 == 2048) {
                z15 = true;
            } else {
                z15 = false;
            }
            zH3 = z2116 | z15 | vVar3.h(rVar2) | vVar3.h(e0Var2);
            objK11 = vVar3.K();
            if (zH3) {
                e2.r rVar1111111 = rVar2;
                pVar4 = pVarA2;
                c0.w0 w0Var112 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar1111111);
                rVar3 = rVar1111111;
                vVar3.f0(w0Var112);
                objK11 = w0Var112;
            } else {
                e2.r rVar1111112 = rVar2;
                pVar4 = pVarA2;
                c0.w0 w0Var113 = new c0.w0(o0Var3, nVar2, z3, e0Var2, rVar1111112);
                rVar3 = rVar1111112;
                vVar3.f0(w0Var113);
                objK11 = w0Var113;
            }
            cVar5 = (a4.c) objK11;
            if (z3) {
                pVarA3 = r0.a.a(pVar4, new h2.b(cVar5, 3, kVar));
            } else {
                pVarA3 = pVar4;
            }
            a1.d dVar15 = e0Var2.f6947u;
            z.c0 c0Var12 = e0Var2.f6946t;
            r0.p pVarE12 = pVarA3.e(new SuspendPointerInputElement(dVar15, c0Var12, new b0.d(dVar15, c0Var12, (t3.c) null, 12), 4)).e(new PointerHoverIconModifierElement());
            boolean zH1111117 = vVar3.h(o0Var3);
            if (i12 == 4) {
                z16 = true;
            } else {
                z16 = false;
            }
            zH4 = zH1111117 | z16 | vVar3.h(rVar3);
            objK12 = vVar3.K();
            if (zH4) {
                objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                vVar3.f0(objK12);
            } else {
                objK12 = new c.b(o0Var3, xVar3, rVar3, 9);
                vVar3.f0(objK12);
            }
            r0.p pVarA118 = androidx.compose.ui.draw.a.a(mVar, (a4.c) objK12);
            boolean zH1111118 = vVar3.h(o0Var3);
            if (i16 == 2048) {
                z17 = true;
            } else {
                z17 = false;
            }
            boolean zF19 = zH1111118 | z17 | vVar3.f(u2Var2) | vVar3.h(e0Var2);
            if (i12 == 4) {
                z18 = true;
            } else {
                z18 = false;
            }
            zH5 = zF19 | z18 | vVar3.h(rVar3);
            Object objK211 = vVar3.K();
            if (zH5) {
                rVar4 = rVar3;
                e0Var3 = e0Var2;
                sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                u2Var = u2Var2;
                vVar3.f0(sVar);
            } else {
                rVar4 = rVar3;
                e0Var3 = e0Var2;
                sVar = new s(o0Var3, z3, u2Var2, e0Var3, xVar3, rVar4);
                u2Var = u2Var2;
                vVar3.f0(sVar);
            }
            r0.p pVarD113 = androidx.compose.ui.layout.a.d(mVar, (a4.c) sVar);
            boolean zH1111119 = vVar3.h(e0Var);
            if (r3 == 4) {
                z19 = true;
            } else {
                z19 = false;
            }
            boolean z311 = zH1111119 | z19;
            if (i16 == 2048) {
                z20 = true;
            } else {
                z20 = false;
            }
            boolean zG12 = z311 | z20 | vVar3.g(false);
            if (i17 == 16384) {
                z21 = true;
            } else {
                z21 = false;
            }
            zH6 = zG12 | z21 | vVar3.h(o0Var3) | vVar3.h(rVar4) | vVar3.h(e0Var3) | ((i18 <= 32 && vVar3.f(nVar)) || (i22 & 48) == 32);
            objK13 = vVar3.K();
            if (zH6) {
                nVar4 = nVar2;
                e0Var4 = e0Var3;
                o0 o0Var1116 = o0Var3;
                w wVar112 = new w(e0Var, xVar, z3, nVar, o0Var1116, rVar4, e0Var4, nVar4);
                e2.r rVar1111113 = rVar4;
                nVar5 = nVar;
                rVar5 = rVar1111113;
                o0Var3 = o0Var1116;
                vVar3.f0(wVar112);
                objK13 = wVar112;
            } else {
                nVar4 = nVar2;
                e0Var4 = e0Var3;
                o0 o0Var1117 = o0Var3;
                w wVar113 = new w(e0Var, xVar, z3, nVar, o0Var1117, rVar4, e0Var4, nVar4);
                e2.r rVar1111114 = rVar4;
                nVar5 = nVar;
                rVar5 = rVar1111114;
                o0Var3 = o0Var1117;
                vVar3.f0(wVar113);
                objK13 = wVar113;
            }
            AtomicInteger atomicInteger12 = w1.k.f6311a;
            appendedSemanticsElement = new AppendedSemanticsElement((a4.c) objK13, true);
            if (!z3) {
                z22 = false;
            } else {
                z22 = false;
            }
            float f16 = v0.f6117a;
            if (z22) {
                xVar4 = xVar;
                appendedSemanticsElement2 = appendedSemanticsElement;
                o0 o0Var1118 = o0Var3;
                o0Var3 = o0Var1118;
                pVarA4 = r0.a.a(mVar, new c0.u2(s0Var, o0Var1118, xVar4, rVar5, 1));
            } else {
                xVar4 = xVar;
                appendedSemanticsElement2 = appendedSemanticsElement;
                pVarA4 = mVar;
            }
            zH7 = vVar3.h(e0Var4);
            objK14 = vVar3.K();
            if (zH7) {
                objK14 = new j(e0Var4, 0);
                vVar3.f0(objK14);
            } else {
                objK14 = new j(e0Var4, 0);
                vVar3.f0(objK14);
            }
            f0.l.c(e0Var4, (a4.c) objK14, vVar3);
            boolean zH11111110 = vVar3.h(o0Var3) | vVar3.h(yVar);
            if (i12 == 4) {
                z23 = true;
            } else {
                z23 = false;
            }
            z24 = zH11111110 | z23 | ((i18 <= 32 && vVar3.f(nVar5)) || (i22 & 48) == 32);
            objK15 = vVar3.K();
            if (z24) {
                objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                vVar3.f0(objK15);
            } else {
                objK15 = new p.j(o0Var3, yVar, xVar4, nVar5);
                vVar3.f0(objK15);
            }
            f0.l.c(nVar5, (a4.c) objK15, vVar3);
            nVar6 = nVar4;
            q qVar12 = o0Var3.f6025t;
            if (i5 == 1) {
                z25 = true;
            } else {
                z25 = false;
            }
            f0.h hVar18 = hVar3;
            int i217 = nVar5.f2043e;
            nVar7 = nVar5;
            e2.r rVar1111115 = rVar5;
            e0Var5 = e0Var4;
            r0.p pVarA119 = r0.a.a(mVar, new y0(o0Var3, e0Var5, xVar4, true, z25, rVar1111115, f1Var, qVar12, i217));
            boolean zBooleanValue12 = ((Boolean) i1VarV5.getValue()).booleanValue();
            zH8 = vVar3.h(o0Var3) | ((i18 <= 32 && vVar3.f(nVar7)) || (i22 & 48) == 32) | vVar3.h(cVar6);
            objK16 = vVar3.K();
            if (zH8) {
                objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                vVar3.f0(objK16);
            } else {
                objK16 = new x(o0Var3, nVar6, nVar7, cVar6);
                vVar3.f0(objK16);
            }
            r0.p pVarD114 = androidx.compose.ui.layout.a.d(r0.a.a(androidx.compose.ui.input.key.a.b(androidx.compose.ui.input.key.a.b(androidx.compose.foundation.text.input.internal.a.a(pVar, cVar6, o0Var3, e0Var5).e(androidx.compose.foundation.text.handwriting.a.a((a4.a) objK16, zBooleanValue12)).e(pVar3), new c2(gVar, 23, o0Var3)), new c2(o0Var3, 22, e0Var5)).e(pVarA119), new n.r(c1Var, z3, kVar)).e(pVarE12).e(appendedSemanticsElement2), new q(o0Var3, 0));
            if (!z3) {
                z26 = false;
            } else {
                z26 = false;
            }
            if (z26) {
                if (n.q0.a()) {
                    pVarA5 = mVar;
                } else {
                    pVarA5 = r0.a.a(mVar, new s3(6, e0Var5));
                }
                pVar5 = pVarA5;
            } else {
                pVar5 = mVar;
            }
            vVar2 = vVar3;
            d(pVarD114, e0Var5, n0.i.c(-374338080, new o(dVar, o0Var3, h0Var, i6, i5, c1Var, xVar, f0Var, pVarA4, pVarA118, pVarD113, pVar5, cVar4, e0Var5, z26, cVar2, rVar1111115, bVar2), vVar2), vVar2, 384);
        }
        b2 b2VarR = vVar2.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new p(xVar, cVar, pVar, h0Var, f0Var, cVar2, kVar, s0Var, z2, i5, i6, nVar, m0Var, z3, dVar, i7, i8);
        }
    }

    public static final void d(r0.p pVar, z.e0 e0Var, n0.d dVar, f0.p pVar2, int i5) {
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-20551815);
        int i6 = (vVar.f(pVar) ? 4 : 2) | i5 | (vVar.h(e0Var) ? 32 : 16);
        if ((i6 & 147) == 146 && vVar.z()) {
            vVar.Q();
        } else {
            n1.f0 f0VarE = r.o.e(r0.b.f5653d, true);
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            r0.p pVarC = r0.a.c(vVar, pVar);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, f0VarE);
            f0.l.x(p1.i.f4869d, vVar, w1VarL);
            p1.h hVar = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            f0.l.x(p1.i.f4868c, vVar, pVarC);
            b(e0Var, dVar, vVar, (i6 >> 3) & 126);
            vVar.p(true);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c0.x0(pVar, e0Var, dVar, i5, 6);
        }
    }

    /* JADX WARN: Code duplicated, block: B:44:0x0114  */
    public static final void e(z.e0 e0Var, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        vVar.X(-1436003720);
        int i6 = 11;
        if ((((vVar.h(e0Var) ? 4 : 2) | i5) & 3) == 2 && vVar.z()) {
            vVar.Q();
        } else {
            o0 o0Var = e0Var.f6930d;
            int i7 = 0;
            if (o0Var != null) {
                int i8 = 1;
                if (((Boolean) o0Var.f6020o.getValue()).booleanValue()) {
                    o0 o0Var2 = e0Var.f6930d;
                    t3.c cVar = null;
                    y1.f fVar = o0Var2 != null ? o0Var2.f6006a.f6098a : null;
                    if (fVar == null || fVar.f6759d.length() <= 0) {
                        vVar.V(-284257090);
                        vVar.p(false);
                    } else {
                        vVar.V(-285446808);
                        boolean zF = vVar.f(e0Var);
                        Object objK = vVar.K();
                        f0.h hVar = f0.o.f2281a;
                        if (zF || objK == hVar) {
                            objK = new z.c0(e0Var, i7);
                            vVar.f0(objK);
                        }
                        u0 u0Var = (u0) objK;
                        k2.b bVar = (k2.b) vVar.j(j1.f5237f);
                        e2.r rVar = e0Var.f6928b;
                        long j5 = e0Var.j().f2063b;
                        int i9 = y1.g0.f6769c;
                        int iB = rVar.b((int) (j5 >> 32));
                        o0 o0Var3 = e0Var.f6930d;
                        e1 e1VarD = o0Var3 != null ? o0Var3.d() : null;
                        b4.l.b(e1VarD);
                        y1.e0 e0Var2 = e1VarD.f5899a;
                        x0.d dVarC = e0Var2.c(d4.a.m(iB, 0, e0Var2.f6753a.f6743a.f6759d.length()));
                        long jB = p3.j.b((bVar.v(v0.f6117a) / 2) + dVarC.f6534a, dVarC.f6537d);
                        boolean zE = vVar.e(jB);
                        Object objK2 = vVar.K();
                        if (zE || objK2 == hVar) {
                            objK2 = new z(jB);
                            vVar.f0(objK2);
                        }
                        z.l lVar = (z.l) objK2;
                        boolean zH = vVar.h(u0Var) | vVar.h(e0Var);
                        Object objK3 = vVar.K();
                        if (zH || objK3 == hVar) {
                            objK3 = new b0.d(u0Var, e0Var, cVar, i6);
                            vVar.f0(objK3);
                        }
                        r0.p pVarA = j1.z.a(r0.m.f5680a, u0Var, (a4.e) objK3);
                        boolean zE2 = vVar.e(jB);
                        Object objK4 = vVar.K();
                        if (zE2 || objK4 == hVar) {
                            objK4 = new b(jB, i8);
                            vVar.f0(objK4);
                        }
                        d.a(lVar, w1.k.a(pVarA, (a4.c) objK4), 0L, vVar, 0);
                        vVar.p(false);
                    }
                } else {
                    vVar.V(-284257090);
                    vVar.p(false);
                }
            } else {
                vVar.V(-284257090);
                vVar.p(false);
            }
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c0.j1(i5, i6, e0Var);
        }
    }

    public static final void f(z.e0 e0Var, boolean z2, f0.p pVar, int i5) {
        int i6;
        e1 e1VarD;
        f0.v vVar = (f0.v) pVar;
        vVar.X(626339208);
        if ((i5 & 6) == 0) {
            i6 = (vVar.h(e0Var) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.g(z2) ? 32 : 16;
        }
        if ((i6 & 19) == 18 && vVar.z()) {
            vVar.Q();
        } else if (z2) {
            vVar.V(-1286242594);
            o0 o0Var = e0Var.f6930d;
            y1.e0 e0Var2 = null;
            if (o0Var != null && (e1VarD = o0Var.d()) != null) {
                y1.e0 e0Var3 = e1VarD.f5899a;
                o0 o0Var2 = e0Var.f6930d;
                if (!(o0Var2 != null ? o0Var2.f6021p : true)) {
                    e0Var2 = e0Var3;
                }
            }
            if (e0Var2 == null) {
                vVar.V(-1285984396);
            } else {
                vVar.V(-1285984395);
                if (y1.g0.b(e0Var.j().f2063b)) {
                    vVar.V(-1679637798);
                    vVar.p(false);
                } else {
                    vVar.V(-1680616096);
                    int iB = e0Var.f6928b.b((int) (e0Var.j().f2063b >> 32));
                    int iB2 = e0Var.f6928b.b((int) (e0Var.j().f2063b & 4294967295L));
                    j2.h hVarA = e0Var2.a(iB);
                    j2.h hVarA2 = e0Var2.a(Math.max(iB2 - 1, 0));
                    o0 o0Var3 = e0Var.f6930d;
                    if (o0Var3 == null || !((Boolean) o0Var3.f6018m.getValue()).booleanValue()) {
                        vVar.V(-1679975078);
                        vVar.p(false);
                    } else {
                        vVar.V(-1680216289);
                        p3.p.d(true, hVarA, e0Var, vVar, ((i6 << 6) & 896) | 6);
                        vVar.p(false);
                    }
                    o0 o0Var4 = e0Var.f6930d;
                    if (o0Var4 == null || !((Boolean) o0Var4.f6019n.getValue()).booleanValue()) {
                        vVar.V(-1679655654);
                        vVar.p(false);
                    } else {
                        vVar.V(-1679895904);
                        p3.p.d(false, hVarA2, e0Var, vVar, ((i6 << 6) & 896) | 6);
                        vVar.p(false);
                    }
                    vVar.p(false);
                }
                o0 o0Var5 = e0Var.f6930d;
                if (o0Var5 != null) {
                    s1 s1Var = o0Var5.f6017l;
                    if (!b4.l.a(e0Var.f6944r.f2062a.f6759d, e0Var.j().f2062a.f6759d)) {
                        s1Var.setValue(Boolean.FALSE);
                    }
                    if (o0Var5.b()) {
                        if (((Boolean) s1Var.getValue()).booleanValue()) {
                            e0Var.o();
                        } else {
                            e0Var.k();
                        }
                    }
                }
            }
            vVar.p(false);
            vVar.p(false);
        } else {
            vVar.V(651305535);
            vVar.p(false);
            e0Var.k();
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new y(e0Var, z2, i5);
        }
    }

    public static final void g(o0 o0Var) {
        e2.d0 d0Var = o0Var.f6010e;
        if (d0Var != null) {
            o0Var.f6025t.c(e2.x.a((e2.x) o0Var.f6009d.f49e, null, 0L, 3));
            e2.y yVar = d0Var.f1999a;
            AtomicReference atomicReference = yVar.f2066b;
            while (!atomicReference.compareAndSet(d0Var, null)) {
                if (atomicReference.get() != d0Var) {
                }
            }
            yVar.f2065a.g();
        }
        o0Var.f6010e = null;
    }

    public static final x0.d h(k2.b bVar, int i5, e2.e0 e0Var, y1.e0 e0Var2, boolean z2, int i6) {
        x0.d dVarC = e0Var2 != null ? e0Var2.c(e0Var.f2020b.b(i5)) : x0.d.f6533e;
        float f5 = dVarC.f6534a;
        int iM = bVar.M(v0.f6117a);
        return new x0.d(z2 ? (i6 - f5) - iM : f5, dVarC.f6535b, z2 ? i6 - f5 : iM + f5, dVarC.f6537d);
    }

    public static final boolean i(KeyEvent keyEvent, int i5) {
        return ((int) (h1.c.u(keyEvent) >> 32)) == i5;
    }

    public static final void j(e2.y yVar, o0 o0Var, e2.x xVar, e2.n nVar, e2.r rVar) {
        a2.h hVar = o0Var.f6009d;
        q qVar = o0Var.f6025t;
        q qVar2 = o0Var.f6026u;
        b4.w wVar = new b4.w();
        c.b bVar = new c.b(hVar, qVar, wVar, 10);
        e2.s sVar = yVar.f2065a;
        sVar.e(xVar, nVar, bVar, qVar2);
        e2.d0 d0Var = new e2.d0(yVar, sVar);
        yVar.f2066b.set(d0Var);
        wVar.f697d = d0Var;
        o0Var.f6010e = d0Var;
        q(o0Var, xVar, rVar);
    }

    public static final int k(float f5) {
        return Math.round((float) Math.ceil(f5));
    }

    public static final e2.e0 l(e2.f0 f0Var, y1.f fVar) {
        f0Var.getClass();
        int length = fVar.f6759d.length();
        int length2 = fVar.f6759d.length();
        int iMin = Math.min(length, 100);
        for (int i5 = 0; i5 < iMin; i5++) {
            t(i5, length2, i5);
        }
        t(length, length2, length);
        int iMin2 = Math.min(length2, 100);
        for (int i6 = 0; i6 < iMin2; i6++) {
            u(i6, length, i6);
        }
        u(length2, length, length2);
        return new e2.e0(fVar, new c0.i0(fVar.f6759d.length(), fVar.f6759d.length()));
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0045  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.text.BreakIterator] */
    /* JADX WARN: Type inference failed for: r2v0, types: [a1.b, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final int m(int i5, String str) {
        b3.j jVarA;
        ?? r4;
        ?? r5;
        int spanEnd;
        Integer num = null;
        if (b3.j.c()) {
            jVarA = b3.j.a();
            if (jVarA.b() != 1) {
                jVarA = null;
            }
        } else {
            jVarA = null;
        }
        if (jVarA != null) {
            ?? r6 = jVarA.f619e.f609b;
            r6.getClass();
            if (i5 < 0 || i5 >= str.length()) {
                r5 = str;
                spanEnd = -1;
            } else if (str instanceof Spanned) {
                Spanned spanned = (Spanned) str;
                b3.y[] yVarArr = (b3.y[]) spanned.getSpans(i5, i5 + 1, b3.y.class);
                if (yVarArr.length > 0) {
                    spanEnd = spanned.getSpanEnd(yVarArr[0]);
                    r5 = str;
                } else {
                    ?? r7 = str;
                    spanEnd = ((b3.q) r6.y(r7, Math.max(0, i5 - 16), Math.min(str.length(), i5 + 16), Integer.MAX_VALUE, true, new b3.q(i5))).f632f;
                    r5 = r7;
                }
            } else {
                ?? r8 = str;
                spanEnd = ((b3.q) r6.y(r8, Math.max(0, i5 - 16), Math.min(str.length(), i5 + 16), Integer.MAX_VALUE, true, new b3.q(i5))).f632f;
                r5 = r8;
            }
            Integer numValueOf = Integer.valueOf(spanEnd);
            r4 = r5;
            if (spanEnd != -1) {
                num = numValueOf;
            }
        } else {
            r4 = str;
        }
        if (num != null) {
            r4 = r5;
            return num.intValue();
        }
        r4 = r5;
        ?? characterInstance = BreakIterator.getCharacterInstance();
        characterInstance.setText(r4);
        return characterInstance.following(i5);
    }

    public static final int n(CharSequence charSequence, int i5) {
        int length = charSequence.length();
        while (i5 < length) {
            if (charSequence.charAt(i5) == '\n') {
                return i5;
            }
            i5++;
        }
        return charSequence.length();
    }

    public static final int o(CharSequence charSequence, int i5) {
        while (i5 > 0) {
            if (charSequence.charAt(i5 - 1) == '\n') {
                return i5;
            }
            i5--;
        }
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x004b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.text.BreakIterator] */
    /* JADX WARN: Type inference failed for: r4v0, types: [a1.b, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final int p(int i5, String str) {
        b3.j jVarA;
        ?? r5;
        ?? r6;
        int spanStart;
        Integer num = null;
        if (b3.j.c()) {
            jVarA = b3.j.a();
            if (jVarA.b() != 1) {
                jVarA = null;
            }
        } else {
            jVarA = null;
        }
        if (jVarA != null) {
            int iMax = Math.max(0, i5 - 1);
            ?? r4 = jVarA.f619e.f609b;
            r4.getClass();
            if (iMax < 0 || iMax >= str.length()) {
                r6 = str;
                spanStart = -1;
            } else if (str instanceof Spanned) {
                Spanned spanned = (Spanned) str;
                b3.y[] yVarArr = (b3.y[]) spanned.getSpans(iMax, iMax + 1, b3.y.class);
                if (yVarArr.length > 0) {
                    spanStart = spanned.getSpanStart(yVarArr[0]);
                    r6 = str;
                } else {
                    ?? r7 = str;
                    spanStart = ((b3.q) r4.y(r7, Math.max(0, iMax - 16), Math.min(str.length(), iMax + 16), Integer.MAX_VALUE, true, new b3.q(iMax))).f631e;
                    r6 = r7;
                }
            } else {
                ?? r8 = str;
                spanStart = ((b3.q) r4.y(r8, Math.max(0, iMax - 16), Math.min(str.length(), iMax + 16), Integer.MAX_VALUE, true, new b3.q(iMax))).f631e;
                r6 = r8;
            }
            Integer numValueOf = Integer.valueOf(spanStart);
            r5 = r6;
            if (spanStart != -1) {
                num = numValueOf;
            }
        } else {
            r5 = str;
        }
        if (num != null) {
            r5 = r6;
            return num.intValue();
        }
        r5 = r6;
        ?? characterInstance = BreakIterator.getCharacterInstance();
        characterInstance.setText(r5);
        return characterInstance.preceding(i5);
    }

    public static final void q(o0 o0Var, e2.x xVar, e2.r rVar) {
        p0.f fVarD = p0.r.d();
        a4.c cVarE = fVarD != null ? fVarD.e() : null;
        p0.f fVarG = p0.r.g(fVarD);
        try {
            e1 e1VarD = o0Var.d();
            if (e1VarD == null) {
                return;
            }
            e2.d0 d0Var = o0Var.f6010e;
            if (d0Var == null) {
                return;
            }
            n1.p pVarC = o0Var.c();
            if (pVarC == null) {
                return;
            }
            r(xVar, o0Var.f6006a, e1VarD.f5899a, pVarC, d0Var, o0Var.b(), rVar);
        } finally {
            p0.r.j(fVarD, fVarG, cVarE);
        }
    }

    public static void r(e2.x xVar, t0 t0Var, y1.e0 e0Var, n1.p pVar, e2.d0 d0Var, boolean z2, e2.r rVar) {
        x0.d dVarB;
        if (z2) {
            int iB = rVar.b(y1.g0.d(xVar.f2063b));
            if (iB < e0Var.f6753a.f6743a.f6759d.length()) {
                dVarB = e0Var.b(iB);
            } else {
                dVarB = iB != 0 ? e0Var.b(iB - 1) : new x0.d(0.0f, 0.0f, 1.0f, (int) (w0.a(t0Var.f6099b, t0Var.f6104g, t0Var.f6105h, w0.f6126a, 1) & 4294967295L));
            }
            long jZ = pVar.Z(p3.j.b(dVarB.f6534a, dVarB.f6535b));
            x0.d dVarB2 = p3.p.b(p3.j.b(x0.c.d(jZ), x0.c.e(jZ)), p3.j.f(dVarB.c(), dVarB.b()));
            if (b4.l.a((e2.d0) d0Var.f1999a.f2066b.get(), d0Var)) {
                d0Var.f2000b.a(dVarB2);
            }
        }
    }

    public static final void s(int i5, int i6) {
        if (i5 > 0 && i6 > 0) {
            if (i5 > i6) {
                throw new IllegalArgumentException(b.b.d("minLines ", i5, " must be less than or equal to maxLines ", i6).toString());
            }
            return;
        }
        throw new IllegalArgumentException(("both minLines " + i5 + " and maxLines " + i6 + " must be greater than zero").toString());
    }

    public static final void t(int i5, int i6, int i7) {
        if (i5 < 0 || i5 > i6) {
            StringBuilder sb = new StringBuilder("OffsetMapping.originalToTransformed returned invalid mapping: ");
            sb.append(i7);
            sb.append(" -> ");
            sb.append(i5);
            sb.append(" is not in range of transformed text [0, ");
            throw new IllegalStateException(b.b.f(sb, i6, ']').toString());
        }
    }

    public static final void u(int i5, int i6, int i7) {
        if (i5 < 0 || i5 > i6) {
            StringBuilder sb = new StringBuilder("OffsetMapping.transformedToOriginal returned invalid mapping: ");
            sb.append(i7);
            sb.append(" -> ");
            sb.append(i5);
            sb.append(" is not in range of original text [0, ");
            throw new IllegalStateException(b.b.f(sb, i6, ']').toString());
        }
    }
}
