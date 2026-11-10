package c0;

import androidx.compose.material3.MinimumInteractiveModifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class h2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b0.i f896a = new b0.i(0.16f, 0.1f, 0.08f, 0.1f);

    /* JADX WARN: Code duplicated, block: B:105:0x0123  */
    /* JADX WARN: Code duplicated, block: B:107:0x0132  */
    /* JADX WARN: Code duplicated, block: B:120:0x0156 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:121:0x0158  */
    /* JADX WARN: Code duplicated, block: B:124:0x015e  */
    /* JADX WARN: Code duplicated, block: B:127:0x016d  */
    /* JADX WARN: Code duplicated, block: B:129:0x017b  */
    /* JADX WARN: Code duplicated, block: B:130:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:132:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:135:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:136:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:138:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:142:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:145:0x0200  */
    /* JADX WARN: Code duplicated, block: B:146:0x0203  */
    /* JADX WARN: Code duplicated, block: B:149:0x0209  */
    /* JADX WARN: Code duplicated, block: B:150:0x020c  */
    /* JADX WARN: Code duplicated, block: B:153:0x0216  */
    /* JADX WARN: Code duplicated, block: B:155:0x0224  */
    /* JADX WARN: Code duplicated, block: B:157:0x0233  */
    /* JADX WARN: Code duplicated, block: B:160:0x0249 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:163:0x024f  */
    /* JADX WARN: Code duplicated, block: B:166:0x0268  */
    /* JADX WARN: Code duplicated, block: B:167:0x026b  */
    /* JADX WARN: Code duplicated, block: B:169:0x026f  */
    /* JADX WARN: Code duplicated, block: B:170:0x0272  */
    /* JADX WARN: Code duplicated, block: B:172:0x0276  */
    /* JADX WARN: Code duplicated, block: B:173:0x0279  */
    /* JADX WARN: Code duplicated, block: B:175:0x027d  */
    /* JADX WARN: Code duplicated, block: B:176:0x0280  */
    /* JADX WARN: Code duplicated, block: B:179:0x0288  */
    /* JADX WARN: Code duplicated, block: B:180:0x029d  */
    /* JADX WARN: Code duplicated, block: B:183:0x02b6  */
    /* JADX WARN: Code duplicated, block: B:185:0x02bc  */
    /* JADX WARN: Code duplicated, block: B:191:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:193:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:199:0x02e5 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:202:0x02ed  */
    /* JADX WARN: Code duplicated, block: B:206:0x0311  */
    /* JADX WARN: Code duplicated, block: B:207:0x031c  */
    /* JADX WARN: Code duplicated, block: B:211:0x0387  */
    /* JADX WARN: Code duplicated, block: B:213:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x0053  */
    /* JADX WARN: Code duplicated, block: B:32:0x0057  */
    /* JADX WARN: Code duplicated, block: B:34:0x005f  */
    /* JADX WARN: Code duplicated, block: B:35:0x0062  */
    /* JADX WARN: Code duplicated, block: B:38:0x0068  */
    /* JADX WARN: Code duplicated, block: B:41:0x006e  */
    /* JADX WARN: Code duplicated, block: B:43:0x0072  */
    /* JADX WARN: Code duplicated, block: B:45:0x007a  */
    /* JADX WARN: Code duplicated, block: B:46:0x007d  */
    /* JADX WARN: Code duplicated, block: B:49:0x0083  */
    /* JADX WARN: Code duplicated, block: B:52:0x008a  */
    /* JADX WARN: Code duplicated, block: B:54:0x008e  */
    /* JADX WARN: Code duplicated, block: B:56:0x0096  */
    /* JADX WARN: Code duplicated, block: B:57:0x0099  */
    /* JADX WARN: Code duplicated, block: B:60:0x009f  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:66:0x00af  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:76:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:83:0x00df  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:86:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:88:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:89:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:93:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:95:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:96:0x0102  */
    /* JADX WARN: Code duplicated, block: B:98:0x0107  */
    public static final void a(a4.a aVar, r0.p pVar, boolean z2, y0.q0 q0Var, f fVar, j jVar, r.l0 l0Var, a4.f fVar2, f0.p pVar2, int i5, int i6) {
        int i7;
        boolean z3;
        y0.q0 q0VarA;
        f fVar3;
        j jVar2;
        int i8;
        int i9;
        r.l0 l0Var2;
        int i10;
        int i11;
        f fVar4;
        j jVar3;
        y0.q0 q0Var2;
        j jVar4;
        r.l0 l0Var3;
        s sVar;
        Object objK;
        f0.h hVar;
        q.k kVar;
        long j5;
        int i12;
        long j6;
        Object objK2;
        p0.q qVar;
        boolean zF;
        Object objK3;
        q.i iVar;
        float f5;
        Object objK4;
        m.c cVar;
        boolean zH;
        Object objK5;
        j jVar5;
        boolean z4;
        m.l lVar;
        float f6;
        y0.q0 q0Var3;
        boolean z5;
        j jVar6;
        f fVar5;
        f0.b2 b2VarR;
        int i13;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(650121315);
        if ((i5 & 6) == 0) {
            i7 = (vVar.h(aVar) ? 4 : 2) | i5;
        } else {
            i7 = i5;
        }
        if ((i5 & 48) == 0) {
            i7 |= vVar.f(pVar) ? 32 : 16;
        }
        int i14 = i6 & 4;
        if (i14 == 0) {
            if ((i5 & 384) == 0) {
                z3 = z2;
                i7 |= vVar.g(z3) ? 256 : 128;
            }
            if ((i5 & 3072) == 0) {
                if ((i6 & 8) == 0) {
                    q0VarA = q0Var;
                    int i15 = vVar.f(q0VarA) ? 2048 : 1024;
                    i7 |= i15;
                } else {
                    q0VarA = q0Var;
                }
                i7 |= i15;
            } else {
                q0VarA = q0Var;
            }
            if ((i5 & 24576) == 0) {
                if ((i6 & 16) == 0) {
                    fVar3 = fVar;
                    int i16 = vVar.f(fVar3) ? 16384 : 8192;
                    i7 |= i16;
                } else {
                    fVar3 = fVar;
                }
                i7 |= i16;
            } else {
                fVar3 = fVar;
            }
            if ((196608 & i5) == 0) {
                if ((i6 & 32) == 0) {
                    jVar2 = jVar;
                    int i17 = vVar.f(jVar2) ? 131072 : 65536;
                    i7 |= i17;
                } else {
                    jVar2 = jVar;
                }
                i7 |= i17;
            } else {
                jVar2 = jVar;
            }
            if ((i6 & 64) != 0) {
                i7 |= 1572864;
            } else if ((i5 & 1572864) == 0) {
                if (vVar.f(null)) {
                    i8 = 1048576;
                } else {
                    i8 = 524288;
                }
                i7 |= i8;
            }
            i9 = i6 & 128;
            if (i9 != 0) {
                i7 |= 12582912;
                l0Var2 = l0Var;
            } else {
                l0Var2 = l0Var;
                if ((i5 & 12582912) == 0) {
                    if (vVar.f(l0Var2)) {
                        i10 = 8388608;
                    } else {
                        i10 = 4194304;
                    }
                    i7 |= i10;
                }
            }
            if ((i6 & 256) != 0) {
                i7 |= 100663296;
            } else if ((i5 & 100663296) == 0) {
                if (vVar.f(null)) {
                    i11 = 67108864;
                } else {
                    i11 = 33554432;
                }
                i7 |= i11;
            }
            if ((805306368 & i5) != 0) {
                if (vVar.h(fVar2)) {
                    i13 = 536870912;
                } else {
                    i13 = 268435456;
                }
                i7 |= i13;
            }
            if ((i7 & 306783379) == 306783378 || !vVar.z()) {
                vVar.S();
                if ((i5 & 1) != 0 || vVar.x()) {
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i6 & 8) != 0) {
                        r.m0 m0Var = g.f865a;
                        i7 &= -7169;
                        q0VarA = s2.a(vVar, e0.e.f1707b);
                    }
                    if ((i6 & 16) != 0) {
                        r.m0 m0Var2 = g.f865a;
                        sVar = (s) vVar.j(u.f1329a);
                        fVar4 = sVar.K;
                        if (fVar4 == null) {
                            float f7 = e0.e.f1706a;
                            f fVar6 = new f(u.c(sVar, 26), u.c(sVar, e0.e.f1713h), y0.u.b(0.12f, u.c(sVar, e0.e.f1708c)), y0.u.b(0.38f, u.c(sVar, e0.e.f1710e)));
                            sVar.K = fVar6;
                            fVar4 = fVar6;
                        }
                        i7 &= -57345;
                    } else {
                        fVar4 = fVar3;
                    }
                    if ((i6 & 32) != 0) {
                        r.m0 m0Var3 = g.f865a;
                        jVar3 = new j(e0.e.f1706a, e0.e.f1714i, e0.e.f1711f, e0.e.f1712g, e0.e.f1709d);
                        i7 &= -458753;
                    } else {
                        jVar3 = jVar2;
                    }
                    if (i9 != 0) {
                        l0Var2 = g.f865a;
                    }
                    q0Var2 = q0VarA;
                    jVar4 = jVar3;
                    l0Var3 = l0Var2;
                } else {
                    vVar.Q();
                    if ((i6 & 8) != 0) {
                        i7 &= -7169;
                    }
                    if ((i6 & 16) != 0) {
                        i7 &= -57345;
                    }
                    if ((i6 & 32) != 0) {
                        i7 &= -458753;
                    }
                    fVar4 = fVar3;
                    jVar4 = jVar2;
                    l0Var3 = l0Var2;
                    q0Var2 = q0VarA;
                }
                vVar.q();
                vVar.V(-239156623);
                objK = vVar.K();
                hVar = f0.o.f2281a;
                if (objK == hVar) {
                    objK = new q.k();
                    vVar.f0(objK);
                }
                kVar = (q.k) objK;
                vVar.p(false);
                if (z3) {
                    j5 = fVar4.f836a;
                } else {
                    j5 = fVar4.f838c;
                }
                i12 = i7;
                if (z3) {
                    j6 = fVar4.f837b;
                } else {
                    j6 = fVar4.f839d;
                }
                vVar.V(-239150048);
                if (jVar4 == null) {
                    jVar5 = jVar4;
                    fVar4 = fVar4;
                    kVar = kVar;
                    z4 = z3;
                    lVar = null;
                } else {
                    int i18 = ((i12 >> 9) & 896) | ((i12 >> 6) & 14);
                    objK2 = vVar.K();
                    if (objK2 == hVar) {
                        objK2 = new p0.q();
                        vVar.f0(objK2);
                    }
                    qVar = (p0.q) objK2;
                    zF = vVar.f(kVar);
                    objK3 = vVar.K();
                    if (zF || objK3 == hVar) {
                        objK3 = new b0.t(kVar, qVar, null, 2);
                        vVar.f0(objK3);
                    }
                    f0.l.e((a4.e) objK3, vVar, kVar);
                    iVar = (q.i) q3.l.c0(qVar);
                    if (!z3) {
                        f5 = jVar4.f939e;
                    } else if (iVar instanceof q.m) {
                        f5 = jVar4.f936b;
                    } else if (iVar instanceof q.g) {
                        f5 = jVar4.f938d;
                    } else if (iVar instanceof q.d) {
                        f5 = jVar4.f937c;
                    } else {
                        f5 = jVar4.f935a;
                    }
                    objK4 = vVar.K();
                    if (objK4 == hVar) {
                        objK4 = new m.c(new k2.e(f5), m.b1.f3622c, null, 12);
                        vVar.f0(objK4);
                    }
                    cVar = (m.c) objK4;
                    k2.e eVar = new k2.e(f5);
                    zH = ((((i18 & 896) ^ 384) <= 256 && vVar.f(jVar4)) || (i18 & 384) == 256) | vVar.h(cVar) | vVar.c(f5) | ((((i18 & 14) ^ 6) <= 4 && vVar.g(z3)) || (i18 & 6) == 4) | vVar.h(iVar);
                    objK5 = vVar.K();
                    if (!zH || objK5 == hVar) {
                        jVar5 = jVar4;
                        z4 = z3;
                        objK5 = new i(cVar, f5, z4, jVar5, iVar, null);
                        vVar.f0(objK5);
                    } else {
                        jVar5 = jVar4;
                        z4 = z3;
                    }
                    f0.l.e((a4.e) objK5, vVar, eVar);
                    lVar = cVar.f3631c;
                }
                vVar.p(false);
                if (lVar != null) {
                    f6 = ((k2.e) lVar.f3696e.getValue()).f3406d;
                } else {
                    f6 = 0;
                }
                r0.p pVarA = w1.k.a(pVar, k.f958f);
                long j7 = j6;
                l0Var2 = l0Var3;
                n0.d dVarC = n0.i.c(956488494, new m(j7, l0Var3, fVar2, 0), vVar);
                f0.j0 j0Var = o3.f1134a;
                float f8 = ((k2.e) vVar.j(j0Var)).f3406d + 0;
                f0.l.b(new f0.z1[]{b0.f762a.a(new y0.u(j7)), j0Var.a(new k2.e(f8))}, n0.i.c(1279702876, new n3(pVarA, q0Var2, j5, f8, kVar, z4, aVar, f6, dVarC), vVar), vVar, 56);
                q0Var3 = q0Var2;
                z5 = z4;
                jVar6 = jVar5;
                fVar5 = fVar4;
            } else {
                vVar.Q();
                z5 = z3;
                q0Var3 = q0VarA;
                fVar5 = fVar3;
                jVar6 = jVar2;
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new n(aVar, pVar, z5, q0Var3, fVar5, jVar6, l0Var2, fVar2, i5, i6);
            }
        }
        i7 |= 384;
        z3 = z2;
        if ((i5 & 3072) == 0) {
            if ((i6 & 8) == 0) {
                q0VarA = q0Var;
                if (vVar.f(q0VarA)) {
                }
                i7 |= i15;
            } else {
                q0VarA = q0Var;
            }
            i7 |= i15;
        } else {
            q0VarA = q0Var;
        }
        if ((i5 & 24576) == 0) {
            if ((i6 & 16) == 0) {
                fVar3 = fVar;
                if (vVar.f(fVar3)) {
                }
                i7 |= i16;
            } else {
                fVar3 = fVar;
            }
            i7 |= i16;
        } else {
            fVar3 = fVar;
        }
        if ((196608 & i5) == 0) {
            if ((i6 & 32) == 0) {
                jVar2 = jVar;
                if (vVar.f(jVar2)) {
                }
                i7 |= i17;
            } else {
                jVar2 = jVar;
            }
            i7 |= i17;
        } else {
            jVar2 = jVar;
        }
        if ((i6 & 64) != 0) {
            i7 |= 1572864;
        } else if ((i5 & 1572864) == 0) {
            if (vVar.f(null)) {
                i8 = 1048576;
            } else {
                i8 = 524288;
            }
            i7 |= i8;
        }
        i9 = i6 & 128;
        if (i9 != 0) {
            i7 |= 12582912;
            l0Var2 = l0Var;
        } else {
            l0Var2 = l0Var;
            if ((i5 & 12582912) == 0) {
                if (vVar.f(l0Var2)) {
                    i10 = 8388608;
                } else {
                    i10 = 4194304;
                }
                i7 |= i10;
            }
        }
        if ((i6 & 256) != 0) {
            i7 |= 100663296;
        } else if ((i5 & 100663296) == 0) {
            if (vVar.f(null)) {
                i11 = 67108864;
            } else {
                i11 = 33554432;
            }
            i7 |= i11;
        }
        if ((805306368 & i5) != 0) {
            if (vVar.h(fVar2)) {
                i13 = 536870912;
            } else {
                i13 = 268435456;
            }
            i7 |= i13;
        }
        if ((i7 & 306783379) == 306783378) {
            vVar.S();
            if ((i5 & 1) != 0) {
                if (i14 != 0) {
                    z3 = true;
                }
                if ((i6 & 8) != 0) {
                    r.m0 m0Var4 = g.f865a;
                    i7 &= -7169;
                    q0VarA = s2.a(vVar, e0.e.f1707b);
                }
                if ((i6 & 16) != 0) {
                    r.m0 m0Var5 = g.f865a;
                    sVar = (s) vVar.j(u.f1329a);
                    fVar4 = sVar.K;
                    if (fVar4 == null) {
                        float f9 = e0.e.f1706a;
                        f fVar7 = new f(u.c(sVar, 26), u.c(sVar, e0.e.f1713h), y0.u.b(0.12f, u.c(sVar, e0.e.f1708c)), y0.u.b(0.38f, u.c(sVar, e0.e.f1710e)));
                        sVar.K = fVar7;
                        fVar4 = fVar7;
                    }
                    i7 &= -57345;
                } else {
                    fVar4 = fVar3;
                }
                if ((i6 & 32) != 0) {
                    r.m0 m0Var6 = g.f865a;
                    jVar3 = new j(e0.e.f1706a, e0.e.f1714i, e0.e.f1711f, e0.e.f1712g, e0.e.f1709d);
                    i7 &= -458753;
                } else {
                    jVar3 = jVar2;
                }
                if (i9 != 0) {
                    l0Var2 = g.f865a;
                }
                q0Var2 = q0VarA;
                jVar4 = jVar3;
                l0Var3 = l0Var2;
            } else {
                if (i14 != 0) {
                    z3 = true;
                }
                if ((i6 & 8) != 0) {
                    r.m0 m0Var7 = g.f865a;
                    i7 &= -7169;
                    q0VarA = s2.a(vVar, e0.e.f1707b);
                }
                if ((i6 & 16) != 0) {
                    r.m0 m0Var8 = g.f865a;
                    sVar = (s) vVar.j(u.f1329a);
                    fVar4 = sVar.K;
                    if (fVar4 == null) {
                        float f10 = e0.e.f1706a;
                        f fVar8 = new f(u.c(sVar, 26), u.c(sVar, e0.e.f1713h), y0.u.b(0.12f, u.c(sVar, e0.e.f1708c)), y0.u.b(0.38f, u.c(sVar, e0.e.f1710e)));
                        sVar.K = fVar8;
                        fVar4 = fVar8;
                    }
                    i7 &= -57345;
                } else {
                    fVar4 = fVar3;
                }
                if ((i6 & 32) != 0) {
                    r.m0 m0Var9 = g.f865a;
                    jVar3 = new j(e0.e.f1706a, e0.e.f1714i, e0.e.f1711f, e0.e.f1712g, e0.e.f1709d);
                    i7 &= -458753;
                } else {
                    jVar3 = jVar2;
                }
                if (i9 != 0) {
                    l0Var2 = g.f865a;
                }
                q0Var2 = q0VarA;
                jVar4 = jVar3;
                l0Var3 = l0Var2;
            }
            vVar.q();
            vVar.V(-239156623);
            objK = vVar.K();
            hVar = f0.o.f2281a;
            if (objK == hVar) {
                objK = new q.k();
                vVar.f0(objK);
            }
            kVar = (q.k) objK;
            vVar.p(false);
            if (z3) {
                j5 = fVar4.f836a;
            } else {
                j5 = fVar4.f838c;
            }
            i12 = i7;
            if (z3) {
                j6 = fVar4.f837b;
            } else {
                j6 = fVar4.f839d;
            }
            vVar.V(-239150048);
            if (jVar4 == null) {
                jVar5 = jVar4;
                fVar4 = fVar4;
                kVar = kVar;
                z4 = z3;
                lVar = null;
            } else {
                int i19 = ((i12 >> 9) & 896) | ((i12 >> 6) & 14);
                objK2 = vVar.K();
                if (objK2 == hVar) {
                    objK2 = new p0.q();
                    vVar.f0(objK2);
                }
                qVar = (p0.q) objK2;
                zF = vVar.f(kVar);
                objK3 = vVar.K();
                if (zF) {
                    objK3 = new b0.t(kVar, qVar, null, 2);
                    vVar.f0(objK3);
                } else {
                    objK3 = new b0.t(kVar, qVar, null, 2);
                    vVar.f0(objK3);
                }
                f0.l.e((a4.e) objK3, vVar, kVar);
                iVar = (q.i) q3.l.c0(qVar);
                if (!z3) {
                    f5 = jVar4.f939e;
                } else if (iVar instanceof q.m) {
                    f5 = jVar4.f936b;
                } else if (iVar instanceof q.g) {
                    f5 = jVar4.f938d;
                } else if (iVar instanceof q.d) {
                    f5 = jVar4.f937c;
                } else {
                    f5 = jVar4.f935a;
                }
                objK4 = vVar.K();
                if (objK4 == hVar) {
                    objK4 = new m.c(new k2.e(f5), m.b1.f3622c, null, 12);
                    vVar.f0(objK4);
                }
                cVar = (m.c) objK4;
                k2.e eVar2 = new k2.e(f5);
                zH = ((((i19 & 896) ^ 384) <= 256 && vVar.f(jVar4)) || (i19 & 384) == 256) | vVar.h(cVar) | vVar.c(f5) | ((((i19 & 14) ^ 6) <= 4 && vVar.g(z3)) || (i19 & 6) == 4) | vVar.h(iVar);
                objK5 = vVar.K();
                if (zH) {
                    jVar5 = jVar4;
                    z4 = z3;
                    objK5 = new i(cVar, f5, z4, jVar5, iVar, null);
                    vVar.f0(objK5);
                } else {
                    jVar5 = jVar4;
                    z4 = z3;
                    objK5 = new i(cVar, f5, z4, jVar5, iVar, null);
                    vVar.f0(objK5);
                }
                f0.l.e((a4.e) objK5, vVar, eVar2);
                lVar = cVar.f3631c;
            }
            vVar.p(false);
            if (lVar != null) {
                f6 = ((k2.e) lVar.f3696e.getValue()).f3406d;
            } else {
                f6 = 0;
            }
            r0.p pVarA2 = w1.k.a(pVar, k.f958f);
            long j8 = j6;
            l0Var2 = l0Var3;
            n0.d dVarC2 = n0.i.c(956488494, new m(j8, l0Var3, fVar2, 0), vVar);
            f0.j0 j0Var2 = o3.f1134a;
            float f11 = ((k2.e) vVar.j(j0Var2)).f3406d + 0;
            f0.l.b(new f0.z1[]{b0.f762a.a(new y0.u(j8)), j0Var2.a(new k2.e(f11))}, n0.i.c(1279702876, new n3(pVarA2, q0Var2, j5, f11, kVar, z4, aVar, f6, dVarC2), vVar), vVar, 56);
            q0Var3 = q0Var2;
            z5 = z4;
            jVar6 = jVar5;
            fVar5 = fVar4;
        } else {
            vVar.S();
            if ((i5 & 1) != 0) {
                if (i14 != 0) {
                    z3 = true;
                }
                if ((i6 & 8) != 0) {
                    r.m0 m0Var10 = g.f865a;
                    i7 &= -7169;
                    q0VarA = s2.a(vVar, e0.e.f1707b);
                }
                if ((i6 & 16) != 0) {
                    r.m0 m0Var11 = g.f865a;
                    sVar = (s) vVar.j(u.f1329a);
                    fVar4 = sVar.K;
                    if (fVar4 == null) {
                        float f12 = e0.e.f1706a;
                        f fVar9 = new f(u.c(sVar, 26), u.c(sVar, e0.e.f1713h), y0.u.b(0.12f, u.c(sVar, e0.e.f1708c)), y0.u.b(0.38f, u.c(sVar, e0.e.f1710e)));
                        sVar.K = fVar9;
                        fVar4 = fVar9;
                    }
                    i7 &= -57345;
                } else {
                    fVar4 = fVar3;
                }
                if ((i6 & 32) != 0) {
                    r.m0 m0Var12 = g.f865a;
                    jVar3 = new j(e0.e.f1706a, e0.e.f1714i, e0.e.f1711f, e0.e.f1712g, e0.e.f1709d);
                    i7 &= -458753;
                } else {
                    jVar3 = jVar2;
                }
                if (i9 != 0) {
                    l0Var2 = g.f865a;
                }
                q0Var2 = q0VarA;
                jVar4 = jVar3;
                l0Var3 = l0Var2;
            } else {
                if (i14 != 0) {
                    z3 = true;
                }
                if ((i6 & 8) != 0) {
                    r.m0 m0Var13 = g.f865a;
                    i7 &= -7169;
                    q0VarA = s2.a(vVar, e0.e.f1707b);
                }
                if ((i6 & 16) != 0) {
                    r.m0 m0Var14 = g.f865a;
                    sVar = (s) vVar.j(u.f1329a);
                    fVar4 = sVar.K;
                    if (fVar4 == null) {
                        float f13 = e0.e.f1706a;
                        f fVar10 = new f(u.c(sVar, 26), u.c(sVar, e0.e.f1713h), y0.u.b(0.12f, u.c(sVar, e0.e.f1708c)), y0.u.b(0.38f, u.c(sVar, e0.e.f1710e)));
                        sVar.K = fVar10;
                        fVar4 = fVar10;
                    }
                    i7 &= -57345;
                } else {
                    fVar4 = fVar3;
                }
                if ((i6 & 32) != 0) {
                    r.m0 m0Var15 = g.f865a;
                    jVar3 = new j(e0.e.f1706a, e0.e.f1714i, e0.e.f1711f, e0.e.f1712g, e0.e.f1709d);
                    i7 &= -458753;
                } else {
                    jVar3 = jVar2;
                }
                if (i9 != 0) {
                    l0Var2 = g.f865a;
                }
                q0Var2 = q0VarA;
                jVar4 = jVar3;
                l0Var3 = l0Var2;
            }
            vVar.q();
            vVar.V(-239156623);
            objK = vVar.K();
            hVar = f0.o.f2281a;
            if (objK == hVar) {
                objK = new q.k();
                vVar.f0(objK);
            }
            kVar = (q.k) objK;
            vVar.p(false);
            if (z3) {
                j5 = fVar4.f836a;
            } else {
                j5 = fVar4.f838c;
            }
            i12 = i7;
            if (z3) {
                j6 = fVar4.f837b;
            } else {
                j6 = fVar4.f839d;
            }
            vVar.V(-239150048);
            if (jVar4 == null) {
                jVar5 = jVar4;
                fVar4 = fVar4;
                kVar = kVar;
                z4 = z3;
                lVar = null;
            } else {
                int i110 = ((i12 >> 9) & 896) | ((i12 >> 6) & 14);
                objK2 = vVar.K();
                if (objK2 == hVar) {
                    objK2 = new p0.q();
                    vVar.f0(objK2);
                }
                qVar = (p0.q) objK2;
                zF = vVar.f(kVar);
                objK3 = vVar.K();
                if (zF) {
                    objK3 = new b0.t(kVar, qVar, null, 2);
                    vVar.f0(objK3);
                } else {
                    objK3 = new b0.t(kVar, qVar, null, 2);
                    vVar.f0(objK3);
                }
                f0.l.e((a4.e) objK3, vVar, kVar);
                iVar = (q.i) q3.l.c0(qVar);
                if (!z3) {
                    f5 = jVar4.f939e;
                } else if (iVar instanceof q.m) {
                    f5 = jVar4.f936b;
                } else if (iVar instanceof q.g) {
                    f5 = jVar4.f938d;
                } else if (iVar instanceof q.d) {
                    f5 = jVar4.f937c;
                } else {
                    f5 = jVar4.f935a;
                }
                objK4 = vVar.K();
                if (objK4 == hVar) {
                    objK4 = new m.c(new k2.e(f5), m.b1.f3622c, null, 12);
                    vVar.f0(objK4);
                }
                cVar = (m.c) objK4;
                k2.e eVar3 = new k2.e(f5);
                zH = ((((i110 & 896) ^ 384) <= 256 && vVar.f(jVar4)) || (i110 & 384) == 256) | vVar.h(cVar) | vVar.c(f5) | ((((i110 & 14) ^ 6) <= 4 && vVar.g(z3)) || (i110 & 6) == 4) | vVar.h(iVar);
                objK5 = vVar.K();
                if (zH) {
                    jVar5 = jVar4;
                    z4 = z3;
                    objK5 = new i(cVar, f5, z4, jVar5, iVar, null);
                    vVar.f0(objK5);
                } else {
                    jVar5 = jVar4;
                    z4 = z3;
                    objK5 = new i(cVar, f5, z4, jVar5, iVar, null);
                    vVar.f0(objK5);
                }
                f0.l.e((a4.e) objK5, vVar, eVar3);
                lVar = cVar.f3631c;
            }
            vVar.p(false);
            if (lVar != null) {
                f6 = ((k2.e) lVar.f3696e.getValue()).f3406d;
            } else {
                f6 = 0;
            }
            r0.p pVarA3 = w1.k.a(pVar, k.f958f);
            long j9 = j6;
            l0Var2 = l0Var3;
            n0.d dVarC3 = n0.i.c(956488494, new m(j9, l0Var3, fVar2, 0), vVar);
            f0.j0 j0Var3 = o3.f1134a;
            float f14 = ((k2.e) vVar.j(j0Var3)).f3406d + 0;
            f0.l.b(new f0.z1[]{b0.f762a.a(new y0.u(j9)), j0Var3.a(new k2.e(f14))}, n0.i.c(1279702876, new n3(pVarA3, q0Var2, j5, f14, kVar, z4, aVar, f6, dVarC3), vVar), vVar, 56);
            q0Var3 = q0Var2;
            z5 = z4;
            jVar6 = jVar5;
            fVar5 = fVar4;
        }
        b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new n(aVar, pVar, z5, q0Var3, fVar5, jVar6, l0Var2, fVar2, i5, i6);
        }
    }

    public static final void b(r0.p pVar, y0.q0 q0Var, o oVar, p pVar2, n0.d dVar, f0.p pVar3, int i5) {
        int i6;
        p pVar4;
        p pVar5;
        f0.v vVar = (f0.v) pVar3;
        vVar.X(1179621553);
        int i7 = i5 | (vVar.f(pVar) ? 4 : 2) | (vVar.f(q0Var) ? 32 : 16) | (vVar.f(oVar) ? 256 : 128) | 25600;
        if ((74899 & i7) == 74898 && vVar.z()) {
            vVar.Q();
            pVar5 = pVar2;
        } else {
            vVar.S();
            if ((i5 & 1) == 0 || vVar.x()) {
                i6 = i7 & (-7169);
                pVar4 = new p(e0.f.f1715a, e0.f.f1722h, e0.f.f1720f, e0.f.f1721g, e0.f.f1719e, e0.f.f1717c);
            } else {
                vVar.Q();
                i6 = i7 & (-7169);
                pVar4 = pVar2;
            }
            vVar.q();
            long j5 = oVar.f1121a;
            long j6 = oVar.f1122b;
            float f5 = pVar4.f1139a;
            vVar.V(-1763481333);
            vVar.V(-734838460);
            Object objK = vVar.K();
            if (objK == f0.o.f2281a) {
                objK = f0.l.s(new k2.e(f5));
                vVar.f0(objK);
            }
            vVar.p(false);
            vVar.p(false);
            o3.a(pVar, q0Var, j5, j6, 0.0f, ((k2.e) ((f0.i1) objK).getValue()).f3406d, n0.i.c(664103990, new q(dVar, 0), vVar), vVar, (i6 & 112) | (i6 & 14) | 12582912 | 1572864, 16);
            pVar5 = pVar4;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new r(pVar, q0Var, oVar, pVar5, dVar, i5);
        }
    }

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
    public static final void c(z2 z2Var, r0.p pVar, f0.p pVar2, int i5) {
        z2 z2Var2;
        n0.d dVar = z.f1462a;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-1316639904);
        int i6 = (i5 & 6) == 0 ? (vVar.f(z2Var) ? 4 : 2) | i5 : i5;
        if ((i5 & 48) == 0) {
            i6 |= vVar.f(pVar) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.h(dVar) ? 256 : 128;
        }
        if ((i6 & 147) == 146 && vVar.z()) {
            vVar.Q();
            z2Var2 = z2Var;
        } else {
            Object objK = vVar.K();
            Object obj = objK;
            if (objK == f0.o.f2281a) {
                k0 k0Var = new k0();
                k0Var.f963a = new Object();
                k0Var.f964b = new ArrayList();
                vVar.f0(k0Var);
                obj = k0Var;
            }
            k0 k0Var2 = (k0) obj;
            vVar.V(-1256811491);
            Object obj2 = k0Var2.f963a;
            ArrayList arrayList = k0Var2.f964b;
            if (!b4.l.a(z2Var, obj2)) {
                k0Var2.f963a = z2Var;
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                int size = arrayList.size();
                for (int i7 = 0; i7 < size; i7++) {
                    arrayList2.add((z2) ((j0) arrayList.get(i7)).f940a);
                }
                ArrayList arrayListG0 = q3.l.g0(arrayList2);
                if (!arrayListG0.contains(z2Var)) {
                    arrayListG0.add(z2Var);
                }
                arrayList.clear();
                ArrayList arrayList3 = new ArrayList(arrayListG0.size());
                int size2 = arrayListG0.size();
                for (int i8 = 0; i8 < size2; i8++) {
                    Object obj3 = arrayListG0.get(i8);
                    if (obj3 != null) {
                        arrayList3.add(obj3);
                    }
                }
                int size3 = arrayList3.size();
                int i9 = 0;
                while (i9 < size3) {
                    z2 z2Var3 = (z2) arrayList3.get(i9);
                    z2 z2Var4 = z2Var;
                    arrayList.add(new j0(z2Var3, n0.i.c(-1654683077, new u2(z2Var3, z2Var4, arrayListG0, k0Var2, 0), vVar)));
                    i9++;
                    z2Var = z2Var4;
                }
            }
            z2Var2 = z2Var;
            vVar.p(false);
            n1.f0 f0VarE = r.o.e(r0.b.f5653d, false);
            int iHashCode = Long.hashCode(vVar.S);
            f0.w1 w1VarL = vVar.l();
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
            f0.b2 b2VarW = vVar.w();
            if (b2VarW == null) {
                throw new IllegalStateException("no recompose scope found");
            }
            b2VarW.f2096b |= 1;
            k0Var2.f965c = b2VarW;
            vVar.V(1748085441);
            int size4 = arrayList.size();
            for (int i10 = 0; i10 < size4; i10++) {
                j0 j0Var = (j0) arrayList.get(i10);
                z2 z2Var5 = (z2) j0Var.f940a;
                n0.d dVar2 = j0Var.f941b;
                vVar.R(1201076541, 0, z2Var5, null);
                dVar2.b(n0.i.c(-1135367807, new v2(z2Var5, 0), vVar), vVar, 6);
                vVar.p(false);
            }
            vVar.p(false);
            vVar.p(true);
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new w2(z2Var2, pVar, i5);
        }
    }

    public static final void d(r0.p pVar, float f5, long j5, f0.p pVar2, int i5, int i6) {
        r0.p pVar3;
        int i7;
        r0.p pVar4;
        float f6;
        r0.p pVar5;
        float f7;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(75144485);
        int i8 = i6 & 1;
        if (i8 != 0) {
            i7 = i5 | 6;
            pVar3 = pVar;
        } else if ((i5 & 6) == 0) {
            pVar3 = pVar;
            i7 = i5 | (vVar.f(pVar3) ? 4 : 2);
        } else {
            pVar3 = pVar;
            i7 = i5;
        }
        long jD = j5;
        int i9 = i7 | 48 | (((i6 & 4) == 0 && vVar.e(jD)) ? 256 : 128);
        if ((i9 & 147) == 146 && vVar.z()) {
            vVar.Q();
            f7 = f5;
            pVar5 = pVar3;
        } else {
            vVar.S();
            if ((i5 & 1) == 0 || vVar.x()) {
                pVar4 = i8 != 0 ? r0.m.f5680a : pVar3;
                f6 = f0.f840a;
                if ((i6 & 4) != 0) {
                    float f8 = e0.c.f1701a;
                    jD = u.d(vVar, 25);
                    i9 &= -897;
                }
            } else {
                vVar.Q();
                if ((i6 & 4) != 0) {
                    i9 &= -897;
                }
                pVar4 = pVar3;
                f6 = f5;
            }
            vVar.q();
            r0.p pVarD = androidx.compose.foundation.layout.c.d(pVar4.e(androidx.compose.foundation.layout.c.f113a), f6);
            boolean z2 = (((i9 & 896) ^ 384) > 256 && vVar.e(jD)) || (i9 & 384) == 256;
            Object objK = vVar.K();
            if (z2 || objK == f0.o.f2281a) {
                objK = new g0(f6, jD);
                vVar.f0(objK);
            }
            a.a.a(pVarD, (a4.c) objK, vVar, 0);
            pVar5 = pVar4;
            f7 = f6;
        }
        long j6 = jD;
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new h0(pVar5, f7, j6, i5, i6);
        }
    }

    public static final void e(a4.a aVar, r0.p pVar, boolean z2, l0 l0Var, a4.e eVar, f0.p pVar2, int i5) {
        l0 l0Var2;
        r0.p pVar3;
        l0 l0Var3;
        boolean z3;
        a4.e eVar2;
        r0.p pVar4;
        l0 l0Var4;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-1142896114);
        if (((i5 | (vVar.h(aVar) ? 4 : 2) | 26032) & 74899) == 74898 && vVar.z()) {
            vVar.Q();
            pVar4 = pVar;
            z3 = z2;
            l0Var4 = l0Var;
            eVar2 = eVar;
        } else {
            vVar.S();
            if ((i5 & 1) == 0 || vVar.x()) {
                vVar.V(-1519621781);
                long j5 = ((y0.u) vVar.j(b0.f762a)).f6696a;
                s sVar = (s) vVar.j(u.f1329a);
                l0 l0Var5 = sVar.M;
                if (l0Var5 == null) {
                    long j6 = y0.u.f6693f;
                    l0 l0Var6 = new l0(j6, j5, j6, y0.u.b(0.38f, j5));
                    sVar.M = l0Var6;
                    l0Var5 = l0Var6;
                }
                long j7 = l0Var5.f1016b;
                if (y0.u.c(j7, j5)) {
                    vVar.p(false);
                    l0Var2 = l0Var5;
                } else {
                    long jB = y0.u.b(0.38f, j5);
                    l0Var2 = new l0(l0Var5.f1015a, j5 != 16 ? j5 : j7, l0Var5.f1017c, jB != 16 ? jB : l0Var5.f1018d);
                    vVar.p(false);
                }
                pVar3 = r0.m.f5680a;
                l0Var3 = l0Var2;
                z3 = true;
            } else {
                vVar.Q();
                pVar3 = pVar;
                z3 = z2;
                l0Var3 = l0Var;
            }
            vVar.q();
            f0.h3 h3Var = r0.f1218a;
            r0.p pVarE = pVar3.e(MinimumInteractiveModifier.f149a);
            float f5 = e0.g.f1724b;
            r0.p pVarD = androidx.compose.foundation.a.d(androidx.compose.foundation.a.b(q.f.j(androidx.compose.foundation.layout.c.h(pVarE, f5), s2.a(vVar, 5)), z3 ? l0Var3.f1015a : l0Var3.f1017c, y0.l0.f6642a), null, i2.a(false, f5 / 2, 0L, vVar, 54, 4), z3, new w1.f(0), aVar, 8);
            n1.f0 f0VarE = r.o.e(r0.b.f5657h, false);
            int iHashCode = Long.hashCode(vVar.S);
            f0.w1 w1VarL = vVar.l();
            r0.p pVarC = r0.a.c(vVar, pVarD);
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
            eVar2 = eVar;
            f0.l.a(b0.f762a.a(new y0.u(z3 ? l0Var3.f1016b : l0Var3.f1018d)), eVar2, vVar, 56);
            vVar.p(true);
            pVar4 = pVar3;
            l0Var4 = l0Var3;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new m0(aVar, pVar4, z3, l0Var4, eVar2, i5);
        }
    }

    public static final void f(c3 c3Var, r0.p pVar, a4.f fVar, f0.p pVar2, int i5) {
        f0.s1 s1Var = c3Var.f784b;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(464178177);
        if ((((vVar.f(c3Var) ? 4 : 2) | i5 | 432) & 147) == 146 && vVar.z()) {
            vVar.Q();
        } else {
            fVar = z.f1462a;
            z2 z2Var = (z2) s1Var.getValue();
            q1.f fVar2 = (q1.f) vVar.j(q1.j1.f5232a);
            boolean zF = vVar.f(z2Var) | vVar.h(fVar2);
            Object objK = vVar.K();
            if (zF || objK == f0.o.f2281a) {
                objK = new b0.t(z2Var, fVar2, null, 3);
                vVar.f0(objK);
            }
            f0.l.e((a4.e) objK, vVar, z2Var);
            z2 z2Var2 = (z2) s1Var.getValue();
            r0.m mVar = r0.m.f5680a;
            c(z2Var2, mVar, vVar, 432);
            pVar = mVar;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new x0(c3Var, pVar, fVar, i5);
        }
    }
}
