package c0;

import androidx.compose.foundation.BorderModifierNodeElement;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final v1 f1366a = new v1();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f1367b = 56;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final float f1368c = 280;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float f1369d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float f1370e = 2;

    /* JADX WARN: Code duplicated, block: B:101:0x0169  */
    /* JADX WARN: Code duplicated, block: B:104:0x019c  */
    /* JADX WARN: Code duplicated, block: B:106:0x01a1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:107:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:108:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:112:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:27:0x005a  */
    /* JADX WARN: Code duplicated, block: B:28:0x005d  */
    /* JADX WARN: Code duplicated, block: B:31:0x0066  */
    /* JADX WARN: Code duplicated, block: B:32:0x0069  */
    /* JADX WARN: Code duplicated, block: B:35:0x0071  */
    /* JADX WARN: Code duplicated, block: B:37:0x0075  */
    /* JADX WARN: Code duplicated, block: B:39:0x007d  */
    /* JADX WARN: Code duplicated, block: B:40:0x0080  */
    /* JADX WARN: Code duplicated, block: B:43:0x0086  */
    /* JADX WARN: Code duplicated, block: B:46:0x008d  */
    /* JADX WARN: Code duplicated, block: B:48:0x0091  */
    /* JADX WARN: Code duplicated, block: B:50:0x0099  */
    /* JADX WARN: Code duplicated, block: B:51:0x009c  */
    /* JADX WARN: Code duplicated, block: B:54:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:62:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:64:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:74:0x00e0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:75:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:76:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:79:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:80:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:83:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:86:0x0110  */
    /* JADX WARN: Code duplicated, block: B:87:0x0113 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x0115  */
    /* JADX WARN: Code duplicated, block: B:89:0x0118  */
    /* JADX WARN: Code duplicated, block: B:92:0x011f  */
    /* JADX WARN: Code duplicated, block: B:93:0x0136  */
    /* JADX WARN: Code duplicated, block: B:95:0x014d  */
    /* JADX WARN: Code duplicated, block: B:97:0x0155  */
    /* JADX WARN: Code duplicated, block: B:99:0x0158  */
    public final void a(boolean z2, q.j jVar, r0.p pVar, k4 k4Var, y0.q0 q0Var, float f5, float f6, f0.p pVar2, int i5, int i6) {
        r0.p pVar3;
        int i7;
        int i8;
        int i9;
        float f7;
        float f8;
        r0.p pVar4;
        float f9;
        boolean zBooleanValue;
        long j5;
        boolean z3;
        f0.g3 g3VarV;
        f0.g3 g3VarV2;
        long j6;
        r0.p pVar5;
        float f10;
        float f11;
        float f12;
        f0.b2 b2VarR;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(1035477640);
        int i10 = (vVar.g(z2) ? 4 : 2) | i5 | (vVar.g(false) ? 32 : 16) | (vVar.f(jVar) ? 256 : 128);
        int i11 = i6 & 8;
        if (i11 == 0) {
            if ((i5 & 3072) == 0) {
                pVar3 = pVar;
                i10 |= vVar.f(pVar3) ? 2048 : 1024;
            }
            if (vVar.f(k4Var)) {
                i7 = 16384;
            } else {
                i7 = 8192;
            }
            int i12 = i10 | i7;
            if (vVar.f(q0Var)) {
                i8 = 131072;
            } else {
                i8 = 65536;
            }
            i9 = i12 | i8;
            if ((1572864 & i5) == 0) {
                if ((i6 & 64) == 0) {
                    f7 = f5;
                    int i13 = vVar.c(f7) ? 1048576 : 524288;
                    i9 |= i13;
                } else {
                    f7 = f5;
                }
                i9 |= i13;
            } else {
                f7 = f5;
            }
            if ((12582912 & i5) == 0) {
                if ((i6 & 128) == 0) {
                    f8 = f6;
                    int i14 = vVar.c(f8) ? 8388608 : 4194304;
                    i9 |= i14;
                } else {
                    f8 = f6;
                }
                i9 |= i14;
            } else {
                f8 = f6;
            }
            if ((38347923 & i9) == 38347922 || !vVar.z()) {
                vVar.S();
                if ((i5 & 1) != 0 || vVar.x()) {
                    if (i11 != 0) {
                        pVar4 = r0.m.f5680a;
                    } else {
                        pVar4 = pVar3;
                    }
                    if ((i6 & 64) != 0) {
                        i9 &= -3670017;
                        f9 = f1370e;
                    } else {
                        f9 = f7;
                    }
                    if ((i6 & 128) != 0) {
                        i9 &= -29360129;
                        f8 = f1369d;
                    }
                } else {
                    vVar.Q();
                    if ((i6 & 64) != 0) {
                        i9 &= -3670017;
                    }
                    if ((i6 & 128) != 0) {
                        i9 &= -29360129;
                    }
                    pVar4 = pVar3;
                    f9 = f7;
                }
                vVar.q();
                zBooleanValue = ((Boolean) q.f.l(jVar, vVar, (i9 >> 6) & 14).getValue()).booleanValue();
                float f13 = d0.u.f1580b;
                if (!z2) {
                    j5 = k4Var.f999n;
                } else if (zBooleanValue) {
                    j5 = k4Var.f997l;
                } else {
                    j5 = k4Var.f998m;
                }
                if (z2) {
                    vVar.V(1023053998);
                    f0.g3 g3VarA = l.g.a(j5, m.d.h(150, 6, null), vVar);
                    vVar.p(false);
                    g3VarV = g3VarA;
                    z3 = false;
                } else {
                    z3 = false;
                    vVar.V(1023165505);
                    g3VarV = f0.l.v(new y0.u(j5), vVar);
                    vVar.p(false);
                }
                if (z2) {
                    vVar.V(1023269417);
                    if (zBooleanValue) {
                        f11 = f9;
                    } else {
                        f11 = f8;
                    }
                    g3VarV2 = m.f.a(f11, m.d.h(150, 6, null), vVar, 48);
                    vVar.p(false);
                } else {
                    boolean z4 = z3;
                    vVar.V(1023478388);
                    g3VarV2 = f0.l.v(new k2.e(f8), vVar);
                    vVar.p(z4);
                }
                f0.i1 i1VarV = f0.l.v(new n.q(((k2.e) g3VarV2.getValue()).f3406d, new y0.s0(((y0.u) g3VarV.getValue()).f6696a)), vVar);
                if (!z2) {
                    j6 = k4Var.f992g;
                } else if (zBooleanValue != 0) {
                    j6 = k4Var.f990e;
                } else {
                    j6 = k4Var.f991f;
                }
                f0.g3 g3VarA2 = l.g.a(j6, m.d.h(150, 6, null), vVar);
                n.q qVar = (n.q) i1VarV.getValue();
                r.o.a(androidx.compose.ui.draw.a.b(pVar4.e(new BorderModifierNodeElement(qVar.f3994a, qVar.f3995b, q0Var)), new c2(q0Var, 3, new l4(new s1(g3VarA2, f0.g3.class, "value", "getValue()Ljava/lang/Object;", 0, 0)))), vVar, 0);
                pVar5 = pVar4;
                f10 = f9;
            } else {
                vVar.Q();
                pVar5 = pVar3;
                f10 = f7;
            }
            f12 = f8;
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new t1(this, z2, jVar, pVar5, k4Var, q0Var, f10, f12, i5, i6);
            }
        }
        i10 |= 3072;
        pVar3 = pVar;
        if (vVar.f(k4Var)) {
            i7 = 16384;
        } else {
            i7 = 8192;
        }
        int i15 = i10 | i7;
        if (vVar.f(q0Var)) {
            i8 = 131072;
        } else {
            i8 = 65536;
        }
        i9 = i15 | i8;
        if ((1572864 & i5) == 0) {
            if ((i6 & 64) == 0) {
                f7 = f5;
                if (vVar.c(f7)) {
                }
                i9 |= i13;
            } else {
                f7 = f5;
            }
            i9 |= i13;
        } else {
            f7 = f5;
        }
        if ((12582912 & i5) == 0) {
            if ((i6 & 128) == 0) {
                f8 = f6;
                if (vVar.c(f8)) {
                }
                i9 |= i14;
            } else {
                f8 = f6;
            }
            i9 |= i14;
        } else {
            f8 = f6;
        }
        if ((38347923 & i9) == 38347922) {
            vVar.S();
            if ((i5 & 1) != 0) {
                if (i11 != 0) {
                    pVar4 = r0.m.f5680a;
                } else {
                    pVar4 = pVar3;
                }
                if ((i6 & 64) != 0) {
                    i9 &= -3670017;
                    f9 = f1370e;
                } else {
                    f9 = f7;
                }
                if ((i6 & 128) != 0) {
                    i9 &= -29360129;
                    f8 = f1369d;
                }
            } else {
                if (i11 != 0) {
                    pVar4 = r0.m.f5680a;
                } else {
                    pVar4 = pVar3;
                }
                if ((i6 & 64) != 0) {
                    i9 &= -3670017;
                    f9 = f1370e;
                } else {
                    f9 = f7;
                }
                if ((i6 & 128) != 0) {
                    i9 &= -29360129;
                    f8 = f1369d;
                }
            }
            vVar.q();
            zBooleanValue = ((Boolean) q.f.l(jVar, vVar, (i9 >> 6) & 14).getValue()).booleanValue();
            float f14 = d0.u.f1580b;
            if (!z2) {
                j5 = k4Var.f999n;
            } else if (zBooleanValue) {
                j5 = k4Var.f997l;
            } else {
                j5 = k4Var.f998m;
            }
            if (z2) {
                vVar.V(1023053998);
                f0.g3 g3VarA3 = l.g.a(j5, m.d.h(150, 6, null), vVar);
                vVar.p(false);
                g3VarV = g3VarA3;
                z3 = false;
            } else {
                z3 = false;
                vVar.V(1023165505);
                g3VarV = f0.l.v(new y0.u(j5), vVar);
                vVar.p(false);
            }
            if (z2) {
                vVar.V(1023269417);
                if (zBooleanValue) {
                    f11 = f9;
                } else {
                    f11 = f8;
                }
                g3VarV2 = m.f.a(f11, m.d.h(150, 6, null), vVar, 48);
                vVar.p(false);
            } else {
                boolean z5 = z3;
                vVar.V(1023478388);
                g3VarV2 = f0.l.v(new k2.e(f8), vVar);
                vVar.p(z5);
            }
            f0.i1 i1VarV2 = f0.l.v(new n.q(((k2.e) g3VarV2.getValue()).f3406d, new y0.s0(((y0.u) g3VarV.getValue()).f6696a)), vVar);
            if (!z2) {
                j6 = k4Var.f992g;
            } else if (zBooleanValue != 0) {
                j6 = k4Var.f990e;
            } else {
                j6 = k4Var.f991f;
            }
            f0.g3 g3VarA4 = l.g.a(j6, m.d.h(150, 6, null), vVar);
            n.q qVar2 = (n.q) i1VarV2.getValue();
            r.o.a(androidx.compose.ui.draw.a.b(pVar4.e(new BorderModifierNodeElement(qVar2.f3994a, qVar2.f3995b, q0Var)), new c2(q0Var, 3, new l4(new s1(g3VarA4, f0.g3.class, "value", "getValue()Ljava/lang/Object;", 0, 0)))), vVar, 0);
            pVar5 = pVar4;
            f10 = f9;
        } else {
            vVar.S();
            if ((i5 & 1) != 0) {
                if (i11 != 0) {
                    pVar4 = r0.m.f5680a;
                } else {
                    pVar4 = pVar3;
                }
                if ((i6 & 64) != 0) {
                    i9 &= -3670017;
                    f9 = f1370e;
                } else {
                    f9 = f7;
                }
                if ((i6 & 128) != 0) {
                    i9 &= -29360129;
                    f8 = f1369d;
                }
            } else {
                if (i11 != 0) {
                    pVar4 = r0.m.f5680a;
                } else {
                    pVar4 = pVar3;
                }
                if ((i6 & 64) != 0) {
                    i9 &= -3670017;
                    f9 = f1370e;
                } else {
                    f9 = f7;
                }
                if ((i6 & 128) != 0) {
                    i9 &= -29360129;
                    f8 = f1369d;
                }
            }
            vVar.q();
            zBooleanValue = ((Boolean) q.f.l(jVar, vVar, (i9 >> 6) & 14).getValue()).booleanValue();
            float f15 = d0.u.f1580b;
            if (!z2) {
                j5 = k4Var.f999n;
            } else if (zBooleanValue) {
                j5 = k4Var.f997l;
            } else {
                j5 = k4Var.f998m;
            }
            if (z2) {
                vVar.V(1023053998);
                f0.g3 g3VarA5 = l.g.a(j5, m.d.h(150, 6, null), vVar);
                vVar.p(false);
                g3VarV = g3VarA5;
                z3 = false;
            } else {
                z3 = false;
                vVar.V(1023165505);
                g3VarV = f0.l.v(new y0.u(j5), vVar);
                vVar.p(false);
            }
            if (z2) {
                vVar.V(1023269417);
                if (zBooleanValue) {
                    f11 = f9;
                } else {
                    f11 = f8;
                }
                g3VarV2 = m.f.a(f11, m.d.h(150, 6, null), vVar, 48);
                vVar.p(false);
            } else {
                boolean z6 = z3;
                vVar.V(1023478388);
                g3VarV2 = f0.l.v(new k2.e(f8), vVar);
                vVar.p(z6);
            }
            f0.i1 i1VarV3 = f0.l.v(new n.q(((k2.e) g3VarV2.getValue()).f3406d, new y0.s0(((y0.u) g3VarV.getValue()).f6696a)), vVar);
            if (!z2) {
                j6 = k4Var.f992g;
            } else if (zBooleanValue != 0) {
                j6 = k4Var.f990e;
            } else {
                j6 = k4Var.f991f;
            }
            f0.g3 g3VarA6 = l.g.a(j6, m.d.h(150, 6, null), vVar);
            n.q qVar3 = (n.q) i1VarV3.getValue();
            r.o.a(androidx.compose.ui.draw.a.b(pVar4.e(new BorderModifierNodeElement(qVar3.f3994a, qVar3.f3995b, q0Var)), new c2(q0Var, 3, new l4(new s1(g3VarA6, f0.g3.class, "value", "getValue()Ljava/lang/Object;", 0, 0)))), vVar, 0);
            pVar5 = pVar4;
            f10 = f9;
        }
        f12 = f8;
        b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new t1(this, z2, jVar, pVar5, k4Var, q0Var, f10, f12, i5, i6);
        }
    }

    public final void b(String str, a4.e eVar, boolean z2, boolean z3, e2.f0 f0Var, q.j jVar, a4.e eVar2, a4.e eVar3, a4.e eVar4, k4 k4Var, r.l0 l0Var, n0.d dVar, f0.p pVar, int i5) {
        int i6;
        a4.e eVar5;
        boolean z4;
        boolean z5;
        e2.f0 f0Var2;
        a4.e eVar6;
        int i7;
        r.l0 m0Var;
        f0.v vVar;
        r.l0 l0Var2;
        f0.v vVar2 = (f0.v) pVar;
        vVar2.X(-350442135);
        if ((i5 & 6) == 0) {
            i6 = (vVar2.f(str) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            eVar5 = eVar;
            i6 |= vVar2.h(eVar5) ? 32 : 16;
        } else {
            eVar5 = eVar;
        }
        if ((i5 & 384) == 0) {
            z4 = z2;
            i6 |= vVar2.g(z4) ? 256 : 128;
        } else {
            z4 = z2;
        }
        if ((i5 & 3072) == 0) {
            z5 = z3;
            i6 |= vVar2.g(z5) ? 2048 : 1024;
        } else {
            z5 = z3;
        }
        if ((i5 & 24576) == 0) {
            f0Var2 = f0Var;
            i6 |= vVar2.f(f0Var2) ? 16384 : 8192;
        } else {
            f0Var2 = f0Var;
        }
        if ((i5 & 196608) == 0) {
            i6 |= vVar2.f(jVar) ? 131072 : 65536;
        }
        if ((i5 & 1572864) == 0) {
            i6 |= vVar2.g(false) ? 1048576 : 524288;
        }
        if ((i5 & 12582912) == 0) {
            eVar6 = eVar2;
            i6 |= vVar2.h(eVar6) ? 8388608 : 4194304;
        } else {
            eVar6 = eVar2;
        }
        if ((i5 & 100663296) == 0) {
            i6 |= vVar2.h(null) ? 67108864 : 33554432;
        }
        if ((i5 & 805306368) == 0) {
            i6 |= vVar2.h(eVar3) ? 536870912 : 268435456;
        }
        int i8 = 14155776 | (vVar2.h(null) ? 4 : 2) | (vVar2.h(null) ? 32 : 16) | (vVar2.h(null) ? 256 : 128) | (vVar2.h(eVar4) ? 2048 : 1024) | (vVar2.f(k4Var) ? 16384 : 8192) | 65536;
        if ((306783379 & i6) == 306783378 && (4793491 & i8) == 4793490 && vVar2.z()) {
            vVar2.Q();
            l0Var2 = l0Var;
            vVar = vVar2;
        } else {
            vVar2.S();
            if ((i5 & 1) == 0 || vVar2.x()) {
                float f5 = d0.u.f1580b;
                i7 = i8 & (-458753);
                m0Var = new r.m0(f5, f5, f5, f5);
            } else {
                vVar2.Q();
                i7 = i8 & (-458753);
                m0Var = l0Var;
            }
            vVar2.q();
            int i9 = i6 << 3;
            int i10 = i6 >> 3;
            vVar = vVar2;
            int i11 = (i10 & 7168) | (i9 & 896) | (i9 & 112) | 6;
            int i12 = i6 >> 9;
            int i13 = i7 << 21;
            d0.u.a(str, eVar5, f0Var2, eVar6, eVar3, eVar4, z5, z4, jVar, m0Var, k4Var, dVar, vVar, i11 | (i12 & 57344) | (i12 & 458752) | (i12 & 3670016) | (i13 & 29360128) | (i13 & 234881024) | (i13 & 1879048192), ((i7 >> 9) & 14) | ((i6 >> 6) & 112) | (i6 & 896) | (i12 & 7168) | (i10 & 57344) | ((i7 << 6) & 3670016) | 12582912);
            l0Var2 = m0Var;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new u1(this, str, eVar, z2, z3, f0Var, jVar, eVar2, eVar3, eVar4, k4Var, l0Var2, dVar, i5);
        }
    }
}
