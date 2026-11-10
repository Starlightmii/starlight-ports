package d0;

import c0.b0;
import c0.d2;
import c0.g1;
import c0.h1;
import c0.k4;
import c0.s4;
import c0.t4;
import c0.x0;
import e2.e0;
import e2.f0;
import f0.b2;
import f0.c3;
import f0.i1;
import f0.n0;
import f0.s1;
import m.a1;
import m.b1;
import m.q0;
import m.s0;
import m.y0;
import m.z0;
import r.l0;
import y1.h0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f1580b;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final float f1585g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final float f1586h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final r0.p f1587i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f1579a = h1.c.b(0, 0, 0, 0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final float f1581c = 12;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float f1582d = 4;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float f1583e = 2;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final float f1584f = 24;

    static {
        float f5 = 16;
        f1580b = f5;
        f1585g = f5;
        f1586h = f5;
        float f6 = 48;
        f1587i = androidx.compose.foundation.layout.c.a(r0.m.f5680a, f6, f6);
    }

    /* JADX WARN: Code duplicated, block: B:198:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:200:0x02bc  */
    /* JADX WARN: Code duplicated, block: B:202:0x02bf  */
    /* JADX WARN: Code duplicated, block: B:204:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:206:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:209:0x030c  */
    /* JADX WARN: Code duplicated, block: B:211:0x030f  */
    /* JADX WARN: Code duplicated, block: B:215:0x0316  */
    /* JADX WARN: Code duplicated, block: B:217:0x031c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:219:0x031f  */
    /* JADX WARN: Code duplicated, block: B:222:0x033d  */
    /* JADX WARN: Code duplicated, block: B:224:0x0340  */
    /* JADX WARN: Code duplicated, block: B:228:0x0347  */
    /* JADX WARN: Code duplicated, block: B:230:0x034d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:232:0x0350  */
    /* JADX WARN: Code duplicated, block: B:235:0x0388  */
    /* JADX WARN: Code duplicated, block: B:237:0x038b  */
    /* JADX WARN: Code duplicated, block: B:239:0x038e  */
    /* JADX WARN: Code duplicated, block: B:241:0x0392  */
    /* JADX WARN: Code duplicated, block: B:243:0x0398 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:244:0x039a  */
    /* JADX WARN: Code duplicated, block: B:247:0x03b8  */
    /* JADX WARN: Code duplicated, block: B:249:0x03bb  */
    /* JADX WARN: Code duplicated, block: B:253:0x03c2  */
    /* JADX WARN: Code duplicated, block: B:255:0x03c8  */
    /* JADX WARN: Code duplicated, block: B:258:0x03cc  */
    /* JADX WARN: Code duplicated, block: B:261:0x0410  */
    /* JADX WARN: Code duplicated, block: B:263:0x0414  */
    /* JADX WARN: Code duplicated, block: B:266:0x042e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:269:0x0434  */
    /* JADX WARN: Code duplicated, block: B:272:0x045e  */
    /* JADX WARN: Code duplicated, block: B:274:0x0463  */
    /* JADX WARN: Code duplicated, block: B:278:0x0484  */
    /* JADX WARN: Code duplicated, block: B:281:0x04ca  */
    /* JADX WARN: Code duplicated, block: B:284:0x04cf  */
    /* JADX WARN: Code duplicated, block: B:288:0x053b  */
    /* JADX WARN: Code duplicated, block: B:289:0x0543  */
    /* JADX WARN: Code duplicated, block: B:292:0x0568  */
    /* JADX WARN: Code duplicated, block: B:295:0x058c  */
    /* JADX WARN: Code duplicated, block: B:296:0x05a0  */
    /* JADX WARN: Code duplicated, block: B:299:0x05b7  */
    /* JADX WARN: Code duplicated, block: B:300:0x05ba A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:301:0x05bc  */
    /* JADX WARN: Code duplicated, block: B:302:0x05bf  */
    /* JADX WARN: Code duplicated, block: B:305:0x05c9  */
    /* JADX WARN: Code duplicated, block: B:306:0x05cc  */
    /* JADX WARN: Code duplicated, block: B:309:0x05e8  */
    /* JADX WARN: Code duplicated, block: B:310:0x05eb A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:311:0x05ed  */
    /* JADX WARN: Code duplicated, block: B:312:0x05f0  */
    /* JADX WARN: Code duplicated, block: B:315:0x05fa  */
    /* JADX WARN: Code duplicated, block: B:316:0x05fe  */
    /* JADX WARN: Code duplicated, block: B:319:0x0627  */
    /* JADX WARN: Code duplicated, block: B:322:0x064f A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:323:0x0651  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r13v4 */
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
    public static final void a(String str, a4.e eVar, f0 f0Var, a4.e eVar2, a4.e eVar3, a4.e eVar4, boolean z2, boolean z3, q.j jVar, l0 l0Var, k4 k4Var, n0.d dVar, f0.p pVar, int i5, int i6) {
        int i7;
        f0 f0Var2;
        int i8;
        f fVar;
        long j5;
        s0 s0Var;
        float f5;
        int iOrdinal;
        float f6;
        int iOrdinal2;
        float f7;
        int iOrdinal3;
        float f8;
        q0 q0VarA;
        int iOrdinal4;
        float f9;
        int iOrdinal5;
        boolean z4;
        q0 q0VarA2;
        f fVar2;
        int[] iArr;
        long j6;
        z0.c cVarF;
        boolean zF;
        Object objK;
        l.c cVar;
        f fVar3;
        long j7;
        f fVar4;
        q0 q0VarA3;
        boolean zF2;
        Object objK2;
        Object obj;
        q0 q0VarA4;
        float fFloatValue;
        boolean z5;
        h0 h0Var;
        n0.d dVarC;
        Object objK3;
        Object objK4;
        int i9;
        long j8;
        n0.d dVarC2;
        long j9;
        int i10;
        n0.d dVarC3;
        Object objK5;
        i1 i1Var;
        boolean zC;
        Object objK6;
        f0.v vVar;
        f0.v vVar2 = (f0.v) pVar;
        vVar2.X(1514469103);
        if ((i5 & 6) == 0) {
            i7 = (vVar2.f(v.f1588d) ? 4 : 2) | i5;
        } else {
            i7 = i5;
        }
        if ((i5 & 48) == 0) {
            i7 |= vVar2.f(str) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i7 |= vVar2.h(eVar) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            f0Var2 = f0Var;
            i7 |= vVar2.f(f0Var2) ? 2048 : 1024;
        } else {
            f0Var2 = f0Var;
        }
        if ((i5 & 24576) == 0) {
            i7 |= vVar2.h(eVar2) ? 16384 : 8192;
        }
        if ((i5 & 196608) == 0) {
            i7 |= vVar2.h(null) ? 131072 : 65536;
        }
        if ((i5 & 1572864) == 0) {
            i7 |= vVar2.h(eVar3) ? 1048576 : 524288;
        }
        if ((i5 & 12582912) == 0) {
            i7 |= vVar2.h(null) ? 8388608 : 4194304;
        }
        if ((i5 & 100663296) == 0) {
            i7 |= vVar2.h(null) ? 67108864 : 33554432;
        }
        if ((i5 & 805306368) == 0) {
            i7 |= vVar2.h(null) ? 536870912 : 268435456;
        }
        if ((i6 & 6) == 0) {
            i8 = i6 | (vVar2.h(eVar4) ? 4 : 2);
        } else {
            i8 = i6;
        }
        if ((i6 & 48) == 0) {
            i8 |= vVar2.g(z2) ? 32 : 16;
        }
        if ((i6 & 384) == 0) {
            i8 |= vVar2.g(z3) ? 256 : 128;
        }
        if ((i6 & 3072) == 0) {
            i8 |= vVar2.g(false) ? 2048 : 1024;
        }
        if ((i6 & 24576) == 0) {
            i8 |= vVar2.f(jVar) ? 16384 : 8192;
        }
        if ((i6 & 196608) == 0) {
            i8 |= vVar2.f(l0Var) ? 131072 : 65536;
        }
        if ((i6 & 1572864) == 0) {
            i8 |= vVar2.f(k4Var) ? 1048576 : 524288;
        }
        if ((i6 & 12582912) == 0) {
            i8 |= vVar2.h(dVar) ? 8388608 : 4194304;
        }
        if ((306783379 & i7) == 306783378 && (i8 & 4793491) == 4793490 && vVar2.z()) {
            vVar2.Q();
            vVar = vVar2;
        } else {
            boolean z6 = ((i7 & 112) == 32) | ((i7 & 7168) == 2048);
            Object objK7 = vVar2.K();
            f0.h hVar = f0.o.f2281a;
            if (z6 || objK7 == hVar) {
                y1.f fVar5 = new y1.f(str, null, 6);
                f0Var2.getClass();
                objK7 = new e0(fVar5, e2.q.f2047a);
                vVar2.f0(objK7);
            }
            String str2 = ((e0) objK7).f2019a.f6759d;
            boolean zBooleanValue = ((Boolean) q.f.l(jVar, vVar2, (i8 >> 12) & 14).getValue()).booleanValue();
            if (zBooleanValue) {
                fVar = f.f1530d;
            } else {
                fVar = str2.length() == 0 ? f.f1531e : f.f1532f;
            }
            if (z3) {
                j5 = zBooleanValue ? k4Var.f1009x : k4Var.f1010y;
            } else {
                j5 = k4Var.f1011z;
            }
            s4 s4Var = (s4) vVar2.j(t4.f1328a);
            h0 h0Var2 = s4Var.f1289j;
            h0 h0Var3 = s4Var.f1291l;
            int i11 = i7;
            long jB = h0Var2.b();
            long j10 = y0.u.f6694g;
            boolean z7 = (y0.u.c(jB, j10) && !y0.u.c(h0Var3.b(), j10)) || (!y0.u.c(h0Var2.b(), j10) && y0.u.c(h0Var3.b(), j10));
            long jB2 = h0Var3.b();
            if (z7 && jB2 == 16) {
                jB2 = j5;
            }
            long jB3 = h0Var2.b();
            long j11 = (z7 && jB3 == 16) ? j5 : jB3;
            boolean z8 = eVar2 != null;
            s0 s0VarB = y0.b(fVar, vVar2, 48, 0);
            s1 s1Var = s0VarB.f3752b;
            a1 a1Var = b1.f3620a;
            f fVar6 = (f) s0VarB.c();
            vVar2.V(-2036730335);
            int iOrdinal6 = fVar6.ordinal();
            float f10 = 0.0f;
            if (iOrdinal6 != 0) {
                s0Var = s0VarB;
                if (iOrdinal6 == 1) {
                    f5 = 0.0f;
                } else if (iOrdinal6 != 2) {
                    throw new i4.p();
                }
                vVar2.p(false);
                Float fValueOf = Float.valueOf(f5);
                f fVar7 = (f) s1Var.getValue();
                vVar2.V(-2036730335);
                iOrdinal = fVar7.ordinal();
                if (iOrdinal == 0) {
                    f6 = 1.0f;
                } else if (iOrdinal != 1) {
                    if (iOrdinal != 2) {
                        throw new i4.p();
                    }
                    f6 = 1.0f;
                } else {
                    f6 = 0.0f;
                }
                vVar2.p(false);
                Float fValueOf2 = Float.valueOf(f6);
                s0Var.e();
                vVar2.V(1276209157);
                z0 z0VarH = m.d.h(150, 6, null);
                vVar2.p(false);
                q0 q0VarA5 = y0.a(s0Var, fValueOf, fValueOf2, z0VarH, a1Var, vVar2, 196608);
                f fVar8 = (f) s0Var.c();
                vVar2.V(1435837472);
                iOrdinal2 = fVar8.ordinal();
                if (iOrdinal2 == 0) {
                    f7 = 1.0f;
                } else {
                    if (iOrdinal2 != 1) {
                        if (iOrdinal2 != 2) {
                            throw new i4.p();
                        }
                    } else if (z8) {
                        f7 = 1.0f;
                    }
                    f7 = 0.0f;
                }
                vVar2.p(false);
                Float fValueOf3 = Float.valueOf(f7);
                f fVar9 = (f) s1Var.getValue();
                vVar2.V(1435837472);
                iOrdinal3 = fVar9.ordinal();
                if (iOrdinal3 == 0) {
                    f8 = 1.0f;
                } else {
                    if (iOrdinal3 != 1) {
                        if (iOrdinal3 != 2) {
                            throw new i4.p();
                        }
                    } else if (z8) {
                        f8 = 1.0f;
                    }
                    f8 = 0.0f;
                }
                vVar2.p(false);
                q0VarA = y0.a(s0Var, fValueOf3, Float.valueOf(f8), (m.x) s.f1577e.b(s0Var.e(), vVar2, 0), a1Var, vVar2, 196608);
                f fVar10 = (f) s0Var.c();
                vVar2.V(1128033978);
                iOrdinal4 = fVar10.ordinal();
                if (iOrdinal4 == 0) {
                    f9 = 1.0f;
                } else {
                    if (iOrdinal4 != 1) {
                        if (iOrdinal4 != 2) {
                            throw new i4.p();
                        }
                    } else if (z8) {
                        f9 = 0.0f;
                    }
                    f9 = 1.0f;
                }
                vVar2.p(false);
                Float fValueOf4 = Float.valueOf(f9);
                f fVar11 = (f) s1Var.getValue();
                vVar2.V(1128033978);
                iOrdinal5 = fVar11.ordinal();
                if (iOrdinal5 == 0) {
                    if (iOrdinal5 != 1) {
                        if (!z8) {
                        }
                        vVar2.p(false);
                        Float fValueOf5 = Float.valueOf(f10);
                        s0Var.e();
                        vVar2.V(-1868044898);
                        z4 = z7;
                        z0 z0VarH2 = m.d.h(150, 6, null);
                        vVar2.p(false);
                        q0VarA2 = y0.a(s0Var, fValueOf4, fValueOf5, z0VarH2, a1Var, vVar2, 196608);
                        fVar2 = (f) s1Var.getValue();
                        vVar2.V(-107432127);
                        iArr = t.f1578a;
                        if (iArr[fVar2.ordinal()] == 1) {
                            j6 = jB2;
                        } else {
                            j6 = j11;
                        }
                        vVar2.p(false);
                        cVarF = y0.u.f(j6);
                        zF = vVar2.f(cVarF);
                        objK = vVar2.K();
                        cVar = l.c.f3485e;
                        if (zF || objK == hVar) {
                            a1 a1Var2 = new a1(cVar, new g1(6, cVarF));
                            vVar2.f0(a1Var2);
                            objK = a1Var2;
                        }
                        a1 a1Var3 = (a1) objK;
                        fVar3 = (f) s0Var.c();
                        vVar2.V(-107432127);
                        if (iArr[fVar3.ordinal()] == 1) {
                            j7 = jB2;
                        } else {
                            j7 = j11;
                        }
                        vVar2.p(false);
                        y0.u uVar = new y0.u(j7);
                        fVar4 = (f) s1Var.getValue();
                        vVar2.V(-107432127);
                        if (iArr[fVar4.ordinal()] != 1) {
                            jB2 = j11;
                        }
                        vVar2.p(false);
                        y0.u uVar2 = new y0.u(jB2);
                        s0Var.e();
                        vVar2.V(1528582156);
                        z0 z0VarH3 = m.d.h(150, 6, null);
                        vVar2.p(false);
                        q0VarA3 = y0.a(s0Var, uVar, uVar2, z0VarH3, a1Var3, vVar2, 196608);
                        vVar2.V(1023351670);
                        vVar2.p(false);
                        z0.c cVarF2 = y0.u.f(j5);
                        zF2 = vVar2.f(cVarF2);
                        objK2 = vVar2.K();
                        if (zF2) {
                            obj = hVar;
                        } else {
                            if (objK2 == obj) {
                            }
                            obj = hVar;
                            a1 a1Var4 = (a1) objK2;
                            vVar2.V(1023351670);
                            vVar2.p(false);
                            y0.u uVar3 = new y0.u(j5);
                            vVar2.V(1023351670);
                            vVar2.p(false);
                            y0.u uVar4 = new y0.u(j5);
                            s0Var.e();
                            vVar2.V(-543659263);
                            z0 z0VarH4 = m.d.h(150, 6, null);
                            vVar2.p(false);
                            q0VarA4 = y0.a(s0Var, uVar3, uVar4, z0VarH4, a1Var4, vVar2, 196608);
                            fFloatValue = ((Number) q0VarA5.f3743k.getValue()).floatValue();
                            vVar2.V(-156998101);
                            if (eVar2 == null) {
                                z5 = 0;
                                h0Var = h0Var3;
                                dVarC = null;
                            } else {
                                z5 = 0;
                                h0Var = h0Var3;
                                dVarC = n0.i.c(-1236585568, new o(h0Var2, h0Var, fFloatValue, q0VarA4, eVar2, z4, q0VarA3), vVar2);
                            }
                            vVar2.p(z5);
                            objK3 = vVar2.K();
                            if (objK3 == obj) {
                                h1 h1Var = new h1(q0VarA, 1);
                                f0.h hVar2 = f0.h.f2189j;
                                a1.b bVar = c3.f2109a;
                                n0 n0Var = new n0(h1Var, hVar2);
                                vVar2.f0(n0Var);
                                objK3 = n0Var;
                            }
                            vVar2.V(-156965270);
                            vVar2.p(z5);
                            objK4 = vVar2.K();
                            if (objK4 == obj) {
                                i9 = 2;
                                h1 h1Var2 = new h1(q0VarA2, 2);
                                f0.h hVar3 = f0.h.f2189j;
                                a1.b bVar2 = c3.f2109a;
                                n0 n0Var2 = new n0(h1Var2, hVar3);
                                vVar2.f0(n0Var2);
                                objK4 = n0Var2;
                            } else {
                                i9 = 2;
                            }
                            vVar2.V(-156940524);
                            vVar2.p(z5);
                            vVar2.V(-156921964);
                            vVar2.p(z5);
                            if (!z3) {
                                j8 = k4Var.f1003r;
                            } else if (zBooleanValue) {
                                j8 = k4Var.f1001p;
                            } else {
                                j8 = k4Var.f1002q;
                            }
                            vVar2.V(-156902962);
                            if (eVar3 == null) {
                                dVarC2 = null;
                            } else {
                                dVarC2 = n0.i.c(-130107406, new p(z5, j8, eVar3), vVar2);
                            }
                            vVar2.p(z5);
                            vVar2.V(-156893937);
                            vVar2.p(z5);
                            if (!z3) {
                                j9 = k4Var.H;
                            } else if (zBooleanValue) {
                                j9 = k4Var.F;
                            } else {
                                j9 = k4Var.G;
                            }
                            vVar2.V(-156884470);
                            if (eVar4 == null) {
                                i10 = i9;
                                dVarC3 = null;
                            } else {
                                i10 = i9;
                                dVarC3 = n0.i.c(1263707005, new c0.m(j9, h0Var, eVar4, 1), vVar2);
                            }
                            vVar2.p(z5);
                            vVar2.V(-567018607);
                            objK5 = vVar2.K();
                            if (objK5 == obj) {
                                objK5 = f0.l.s(new x0.f(0L));
                                vVar2.f0(objK5);
                            }
                            i1Var = (i1) objK5;
                            n0.d dVarC4 = n0.i.c(157291737, new x0(i1Var, l0Var, dVar, i10), vVar2);
                            zC = vVar2.c(fFloatValue);
                            objK6 = vVar2.K();
                            if (zC || objK6 == obj) {
                                objK6 = new m(fFloatValue, i1Var);
                                vVar2.f0(objK6);
                            }
                            d2.b(eVar, null, dVarC, dVarC2, null, null, null, z2, fFloatValue, (a4.c) objK6, dVarC4, dVarC3, l0Var, vVar2, ((i11 >> 3) & 112) | 6 | ((i8 << 21) & 234881024), ((i8 >> 6) & 7168) | 48);
                            f0.v vVar3 = vVar2;
                            vVar3.p(z5);
                            vVar = vVar3;
                        }
                        obj = hVar;
                        a1 a1Var5 = new a1(cVar, new g1(6, cVarF2));
                        vVar2.f0(a1Var5);
                        objK2 = a1Var5;
                        obj = hVar;
                        a1 a1Var6 = (a1) objK2;
                        vVar2.V(1023351670);
                        vVar2.p(false);
                        y0.u uVar5 = new y0.u(j5);
                        vVar2.V(1023351670);
                        vVar2.p(false);
                        y0.u uVar6 = new y0.u(j5);
                        s0Var.e();
                        vVar2.V(-543659263);
                        z0 z0VarH5 = m.d.h(150, 6, null);
                        vVar2.p(false);
                        q0VarA4 = y0.a(s0Var, uVar5, uVar6, z0VarH5, a1Var6, vVar2, 196608);
                        fFloatValue = ((Number) q0VarA5.f3743k.getValue()).floatValue();
                        vVar2.V(-156998101);
                        if (eVar2 == null) {
                            z5 = 0;
                            h0Var = h0Var3;
                            dVarC = null;
                        } else {
                            z5 = 0;
                            h0Var = h0Var3;
                            dVarC = n0.i.c(-1236585568, new o(h0Var2, h0Var, fFloatValue, q0VarA4, eVar2, z4, q0VarA3), vVar2);
                        }
                        vVar2.p(z5);
                        objK3 = vVar2.K();
                        if (objK3 == obj) {
                            h1 h1Var3 = new h1(q0VarA, 1);
                            f0.h hVar4 = f0.h.f2189j;
                            a1.b bVar3 = c3.f2109a;
                            n0 n0Var3 = new n0(h1Var3, hVar4);
                            vVar2.f0(n0Var3);
                            objK3 = n0Var3;
                        }
                        vVar2.V(-156965270);
                        vVar2.p(z5);
                        objK4 = vVar2.K();
                        if (objK4 == obj) {
                            i9 = 2;
                            h1 h1Var4 = new h1(q0VarA2, 2);
                            f0.h hVar5 = f0.h.f2189j;
                            a1.b bVar4 = c3.f2109a;
                            n0 n0Var4 = new n0(h1Var4, hVar5);
                            vVar2.f0(n0Var4);
                            objK4 = n0Var4;
                        } else {
                            i9 = 2;
                        }
                        vVar2.V(-156940524);
                        vVar2.p(z5);
                        vVar2.V(-156921964);
                        vVar2.p(z5);
                        if (!z3) {
                            j8 = k4Var.f1003r;
                        } else if (zBooleanValue) {
                            j8 = k4Var.f1001p;
                        } else {
                            j8 = k4Var.f1002q;
                        }
                        vVar2.V(-156902962);
                        if (eVar3 == null) {
                            dVarC2 = null;
                        } else {
                            dVarC2 = n0.i.c(-130107406, new p(z5, j8, eVar3), vVar2);
                        }
                        vVar2.p(z5);
                        vVar2.V(-156893937);
                        vVar2.p(z5);
                        if (!z3) {
                            j9 = k4Var.H;
                        } else if (zBooleanValue) {
                            j9 = k4Var.F;
                        } else {
                            j9 = k4Var.G;
                        }
                        vVar2.V(-156884470);
                        if (eVar4 == null) {
                            i10 = i9;
                            dVarC3 = null;
                        } else {
                            i10 = i9;
                            dVarC3 = n0.i.c(1263707005, new c0.m(j9, h0Var, eVar4, 1), vVar2);
                        }
                        vVar2.p(z5);
                        vVar2.V(-567018607);
                        objK5 = vVar2.K();
                        if (objK5 == obj) {
                            objK5 = f0.l.s(new x0.f(0L));
                            vVar2.f0(objK5);
                        }
                        i1Var = (i1) objK5;
                        n0.d dVarC5 = n0.i.c(157291737, new x0(i1Var, l0Var, dVar, i10), vVar2);
                        zC = vVar2.c(fFloatValue);
                        objK6 = vVar2.K();
                        if (zC) {
                            objK6 = new m(fFloatValue, i1Var);
                            vVar2.f0(objK6);
                        } else {
                            objK6 = new m(fFloatValue, i1Var);
                            vVar2.f0(objK6);
                        }
                        d2.b(eVar, null, dVarC, dVarC2, null, null, null, z2, fFloatValue, (a4.c) objK6, dVarC5, dVarC3, l0Var, vVar2, ((i11 >> 3) & 112) | 6 | ((i8 << 21) & 234881024), ((i8 >> 6) & 7168) | 48);
                        f0.v vVar4 = vVar2;
                        vVar4.p(z5);
                        vVar = vVar4;
                    } else if (iOrdinal5 != 2) {
                        throw new i4.p();
                    }
                }
                f10 = 1.0f;
                vVar2.p(false);
                Float fValueOf6 = Float.valueOf(f10);
                s0Var.e();
                vVar2.V(-1868044898);
                z4 = z7;
                z0 z0VarH6 = m.d.h(150, 6, null);
                vVar2.p(false);
                q0VarA2 = y0.a(s0Var, fValueOf4, fValueOf6, z0VarH6, a1Var, vVar2, 196608);
                fVar2 = (f) s1Var.getValue();
                vVar2.V(-107432127);
                iArr = t.f1578a;
                if (iArr[fVar2.ordinal()] == 1) {
                    j6 = jB2;
                } else {
                    j6 = j11;
                }
                vVar2.p(false);
                cVarF = y0.u.f(j6);
                zF = vVar2.f(cVarF);
                objK = vVar2.K();
                cVar = l.c.f3485e;
                if (zF) {
                    a1 a1Var7 = new a1(cVar, new g1(6, cVarF));
                    vVar2.f0(a1Var7);
                    objK = a1Var7;
                } else {
                    a1 a1Var8 = new a1(cVar, new g1(6, cVarF));
                    vVar2.f0(a1Var8);
                    objK = a1Var8;
                }
                a1 a1Var9 = (a1) objK;
                fVar3 = (f) s0Var.c();
                vVar2.V(-107432127);
                if (iArr[fVar3.ordinal()] == 1) {
                    j7 = jB2;
                } else {
                    j7 = j11;
                }
                vVar2.p(false);
                y0.u uVar7 = new y0.u(j7);
                fVar4 = (f) s1Var.getValue();
                vVar2.V(-107432127);
                if (iArr[fVar4.ordinal()] != 1) {
                    jB2 = j11;
                }
                vVar2.p(false);
                y0.u uVar8 = new y0.u(jB2);
                s0Var.e();
                vVar2.V(1528582156);
                z0 z0VarH7 = m.d.h(150, 6, null);
                vVar2.p(false);
                q0VarA3 = y0.a(s0Var, uVar7, uVar8, z0VarH7, a1Var9, vVar2, 196608);
                vVar2.V(1023351670);
                vVar2.p(false);
                z0.c cVarF3 = y0.u.f(j5);
                zF2 = vVar2.f(cVarF3);
                objK2 = vVar2.K();
                if (zF2) {
                    if (objK2 == obj) {
                    }
                    obj = hVar;
                    a1 a1Var10 = (a1) objK2;
                    vVar2.V(1023351670);
                    vVar2.p(false);
                    y0.u uVar9 = new y0.u(j5);
                    vVar2.V(1023351670);
                    vVar2.p(false);
                    y0.u uVar10 = new y0.u(j5);
                    s0Var.e();
                    vVar2.V(-543659263);
                    z0 z0VarH8 = m.d.h(150, 6, null);
                    vVar2.p(false);
                    q0VarA4 = y0.a(s0Var, uVar9, uVar10, z0VarH8, a1Var10, vVar2, 196608);
                    fFloatValue = ((Number) q0VarA5.f3743k.getValue()).floatValue();
                    vVar2.V(-156998101);
                    if (eVar2 == null) {
                        z5 = 0;
                        h0Var = h0Var3;
                        dVarC = null;
                    } else {
                        z5 = 0;
                        h0Var = h0Var3;
                        dVarC = n0.i.c(-1236585568, new o(h0Var2, h0Var, fFloatValue, q0VarA4, eVar2, z4, q0VarA3), vVar2);
                    }
                    vVar2.p(z5);
                    objK3 = vVar2.K();
                    if (objK3 == obj) {
                        h1 h1Var5 = new h1(q0VarA, 1);
                        f0.h hVar6 = f0.h.f2189j;
                        a1.b bVar5 = c3.f2109a;
                        n0 n0Var5 = new n0(h1Var5, hVar6);
                        vVar2.f0(n0Var5);
                        objK3 = n0Var5;
                    }
                    vVar2.V(-156965270);
                    vVar2.p(z5);
                    objK4 = vVar2.K();
                    if (objK4 == obj) {
                        i9 = 2;
                        h1 h1Var6 = new h1(q0VarA2, 2);
                        f0.h hVar7 = f0.h.f2189j;
                        a1.b bVar6 = c3.f2109a;
                        n0 n0Var6 = new n0(h1Var6, hVar7);
                        vVar2.f0(n0Var6);
                        objK4 = n0Var6;
                    } else {
                        i9 = 2;
                    }
                    vVar2.V(-156940524);
                    vVar2.p(z5);
                    vVar2.V(-156921964);
                    vVar2.p(z5);
                    if (!z3) {
                        j8 = k4Var.f1003r;
                    } else if (zBooleanValue) {
                        j8 = k4Var.f1001p;
                    } else {
                        j8 = k4Var.f1002q;
                    }
                    vVar2.V(-156902962);
                    if (eVar3 == null) {
                        dVarC2 = null;
                    } else {
                        dVarC2 = n0.i.c(-130107406, new p(z5, j8, eVar3), vVar2);
                    }
                    vVar2.p(z5);
                    vVar2.V(-156893937);
                    vVar2.p(z5);
                    if (!z3) {
                        j9 = k4Var.H;
                    } else if (zBooleanValue) {
                        j9 = k4Var.F;
                    } else {
                        j9 = k4Var.G;
                    }
                    vVar2.V(-156884470);
                    if (eVar4 == null) {
                        i10 = i9;
                        dVarC3 = null;
                    } else {
                        i10 = i9;
                        dVarC3 = n0.i.c(1263707005, new c0.m(j9, h0Var, eVar4, 1), vVar2);
                    }
                    vVar2.p(z5);
                    vVar2.V(-567018607);
                    objK5 = vVar2.K();
                    if (objK5 == obj) {
                        objK5 = f0.l.s(new x0.f(0L));
                        vVar2.f0(objK5);
                    }
                    i1Var = (i1) objK5;
                    n0.d dVarC6 = n0.i.c(157291737, new x0(i1Var, l0Var, dVar, i10), vVar2);
                    zC = vVar2.c(fFloatValue);
                    objK6 = vVar2.K();
                    if (zC) {
                        objK6 = new m(fFloatValue, i1Var);
                        vVar2.f0(objK6);
                    } else {
                        objK6 = new m(fFloatValue, i1Var);
                        vVar2.f0(objK6);
                    }
                    d2.b(eVar, null, dVarC, dVarC2, null, null, null, z2, fFloatValue, (a4.c) objK6, dVarC6, dVarC3, l0Var, vVar2, ((i11 >> 3) & 112) | 6 | ((i8 << 21) & 234881024), ((i8 >> 6) & 7168) | 48);
                    f0.v vVar5 = vVar2;
                    vVar5.p(z5);
                    vVar = vVar5;
                } else {
                    obj = hVar;
                }
                obj = hVar;
                a1 a1Var11 = new a1(cVar, new g1(6, cVarF3));
                vVar2.f0(a1Var11);
                objK2 = a1Var11;
                obj = hVar;
                a1 a1Var12 = (a1) objK2;
                vVar2.V(1023351670);
                vVar2.p(false);
                y0.u uVar11 = new y0.u(j5);
                vVar2.V(1023351670);
                vVar2.p(false);
                y0.u uVar12 = new y0.u(j5);
                s0Var.e();
                vVar2.V(-543659263);
                z0 z0VarH9 = m.d.h(150, 6, null);
                vVar2.p(false);
                q0VarA4 = y0.a(s0Var, uVar11, uVar12, z0VarH9, a1Var12, vVar2, 196608);
                fFloatValue = ((Number) q0VarA5.f3743k.getValue()).floatValue();
                vVar2.V(-156998101);
                if (eVar2 == null) {
                    z5 = 0;
                    h0Var = h0Var3;
                    dVarC = null;
                } else {
                    z5 = 0;
                    h0Var = h0Var3;
                    dVarC = n0.i.c(-1236585568, new o(h0Var2, h0Var, fFloatValue, q0VarA4, eVar2, z4, q0VarA3), vVar2);
                }
                vVar2.p(z5);
                objK3 = vVar2.K();
                if (objK3 == obj) {
                    h1 h1Var7 = new h1(q0VarA, 1);
                    f0.h hVar8 = f0.h.f2189j;
                    a1.b bVar7 = c3.f2109a;
                    n0 n0Var7 = new n0(h1Var7, hVar8);
                    vVar2.f0(n0Var7);
                    objK3 = n0Var7;
                }
                vVar2.V(-156965270);
                vVar2.p(z5);
                objK4 = vVar2.K();
                if (objK4 == obj) {
                    i9 = 2;
                    h1 h1Var8 = new h1(q0VarA2, 2);
                    f0.h hVar9 = f0.h.f2189j;
                    a1.b bVar8 = c3.f2109a;
                    n0 n0Var8 = new n0(h1Var8, hVar9);
                    vVar2.f0(n0Var8);
                    objK4 = n0Var8;
                } else {
                    i9 = 2;
                }
                vVar2.V(-156940524);
                vVar2.p(z5);
                vVar2.V(-156921964);
                vVar2.p(z5);
                if (!z3) {
                    j8 = k4Var.f1003r;
                } else if (zBooleanValue) {
                    j8 = k4Var.f1001p;
                } else {
                    j8 = k4Var.f1002q;
                }
                vVar2.V(-156902962);
                if (eVar3 == null) {
                    dVarC2 = null;
                } else {
                    dVarC2 = n0.i.c(-130107406, new p(z5, j8, eVar3), vVar2);
                }
                vVar2.p(z5);
                vVar2.V(-156893937);
                vVar2.p(z5);
                if (!z3) {
                    j9 = k4Var.H;
                } else if (zBooleanValue) {
                    j9 = k4Var.F;
                } else {
                    j9 = k4Var.G;
                }
                vVar2.V(-156884470);
                if (eVar4 == null) {
                    i10 = i9;
                    dVarC3 = null;
                } else {
                    i10 = i9;
                    dVarC3 = n0.i.c(1263707005, new c0.m(j9, h0Var, eVar4, 1), vVar2);
                }
                vVar2.p(z5);
                vVar2.V(-567018607);
                objK5 = vVar2.K();
                if (objK5 == obj) {
                    objK5 = f0.l.s(new x0.f(0L));
                    vVar2.f0(objK5);
                }
                i1Var = (i1) objK5;
                n0.d dVarC7 = n0.i.c(157291737, new x0(i1Var, l0Var, dVar, i10), vVar2);
                zC = vVar2.c(fFloatValue);
                objK6 = vVar2.K();
                if (zC) {
                    objK6 = new m(fFloatValue, i1Var);
                    vVar2.f0(objK6);
                } else {
                    objK6 = new m(fFloatValue, i1Var);
                    vVar2.f0(objK6);
                }
                d2.b(eVar, null, dVarC, dVarC2, null, null, null, z2, fFloatValue, (a4.c) objK6, dVarC7, dVarC3, l0Var, vVar2, ((i11 >> 3) & 112) | 6 | ((i8 << 21) & 234881024), ((i8 >> 6) & 7168) | 48);
                f0.v vVar6 = vVar2;
                vVar6.p(z5);
                vVar = vVar6;
            } else {
                s0Var = s0VarB;
            }
            f5 = 1.0f;
            vVar2.p(false);
            Float fValueOf7 = Float.valueOf(f5);
            f fVar12 = (f) s1Var.getValue();
            vVar2.V(-2036730335);
            iOrdinal = fVar12.ordinal();
            if (iOrdinal == 0) {
                f6 = 1.0f;
            } else if (iOrdinal != 1) {
                if (iOrdinal != 2) {
                    throw new i4.p();
                }
                f6 = 1.0f;
            } else {
                f6 = 0.0f;
            }
            vVar2.p(false);
            Float fValueOf8 = Float.valueOf(f6);
            s0Var.e();
            vVar2.V(1276209157);
            z0 z0VarH10 = m.d.h(150, 6, null);
            vVar2.p(false);
            q0 q0VarA6 = y0.a(s0Var, fValueOf7, fValueOf8, z0VarH10, a1Var, vVar2, 196608);
            f fVar13 = (f) s0Var.c();
            vVar2.V(1435837472);
            iOrdinal2 = fVar13.ordinal();
            if (iOrdinal2 == 0) {
                f7 = 1.0f;
            } else {
                if (iOrdinal2 != 1) {
                    if (iOrdinal2 != 2) {
                        throw new i4.p();
                    }
                } else if (z8) {
                    f7 = 1.0f;
                }
                f7 = 0.0f;
            }
            vVar2.p(false);
            Float fValueOf9 = Float.valueOf(f7);
            f fVar14 = (f) s1Var.getValue();
            vVar2.V(1435837472);
            iOrdinal3 = fVar14.ordinal();
            if (iOrdinal3 == 0) {
                f8 = 1.0f;
            } else {
                if (iOrdinal3 != 1) {
                    if (iOrdinal3 != 2) {
                        throw new i4.p();
                    }
                } else if (z8) {
                    f8 = 1.0f;
                }
                f8 = 0.0f;
            }
            vVar2.p(false);
            q0VarA = y0.a(s0Var, fValueOf9, Float.valueOf(f8), (m.x) s.f1577e.b(s0Var.e(), vVar2, 0), a1Var, vVar2, 196608);
            f fVar15 = (f) s0Var.c();
            vVar2.V(1128033978);
            iOrdinal4 = fVar15.ordinal();
            if (iOrdinal4 == 0) {
                f9 = 1.0f;
            } else {
                if (iOrdinal4 != 1) {
                    if (iOrdinal4 != 2) {
                        throw new i4.p();
                    }
                } else if (z8) {
                    f9 = 0.0f;
                }
                f9 = 1.0f;
            }
            vVar2.p(false);
            Float fValueOf10 = Float.valueOf(f9);
            f fVar16 = (f) s1Var.getValue();
            vVar2.V(1128033978);
            iOrdinal5 = fVar16.ordinal();
            if (iOrdinal5 == 0) {
                if (iOrdinal5 != 1) {
                    if (!z8) {
                    }
                    vVar2.p(false);
                    Float fValueOf11 = Float.valueOf(f10);
                    s0Var.e();
                    vVar2.V(-1868044898);
                    z4 = z7;
                    z0 z0VarH11 = m.d.h(150, 6, null);
                    vVar2.p(false);
                    q0VarA2 = y0.a(s0Var, fValueOf10, fValueOf11, z0VarH11, a1Var, vVar2, 196608);
                    fVar2 = (f) s1Var.getValue();
                    vVar2.V(-107432127);
                    iArr = t.f1578a;
                    if (iArr[fVar2.ordinal()] == 1) {
                        j6 = jB2;
                    } else {
                        j6 = j11;
                    }
                    vVar2.p(false);
                    cVarF = y0.u.f(j6);
                    zF = vVar2.f(cVarF);
                    objK = vVar2.K();
                    cVar = l.c.f3485e;
                    if (zF) {
                        a1 a1Var13 = new a1(cVar, new g1(6, cVarF));
                        vVar2.f0(a1Var13);
                        objK = a1Var13;
                    } else {
                        a1 a1Var14 = new a1(cVar, new g1(6, cVarF));
                        vVar2.f0(a1Var14);
                        objK = a1Var14;
                    }
                    a1 a1Var15 = (a1) objK;
                    fVar3 = (f) s0Var.c();
                    vVar2.V(-107432127);
                    if (iArr[fVar3.ordinal()] == 1) {
                        j7 = jB2;
                    } else {
                        j7 = j11;
                    }
                    vVar2.p(false);
                    y0.u uVar13 = new y0.u(j7);
                    fVar4 = (f) s1Var.getValue();
                    vVar2.V(-107432127);
                    if (iArr[fVar4.ordinal()] != 1) {
                        jB2 = j11;
                    }
                    vVar2.p(false);
                    y0.u uVar14 = new y0.u(jB2);
                    s0Var.e();
                    vVar2.V(1528582156);
                    z0 z0VarH12 = m.d.h(150, 6, null);
                    vVar2.p(false);
                    q0VarA3 = y0.a(s0Var, uVar13, uVar14, z0VarH12, a1Var15, vVar2, 196608);
                    vVar2.V(1023351670);
                    vVar2.p(false);
                    z0.c cVarF4 = y0.u.f(j5);
                    zF2 = vVar2.f(cVarF4);
                    objK2 = vVar2.K();
                    if (zF2) {
                        if (objK2 == obj) {
                        }
                        obj = hVar;
                        a1 a1Var16 = (a1) objK2;
                        vVar2.V(1023351670);
                        vVar2.p(false);
                        y0.u uVar15 = new y0.u(j5);
                        vVar2.V(1023351670);
                        vVar2.p(false);
                        y0.u uVar16 = new y0.u(j5);
                        s0Var.e();
                        vVar2.V(-543659263);
                        z0 z0VarH13 = m.d.h(150, 6, null);
                        vVar2.p(false);
                        q0VarA4 = y0.a(s0Var, uVar15, uVar16, z0VarH13, a1Var16, vVar2, 196608);
                        fFloatValue = ((Number) q0VarA6.f3743k.getValue()).floatValue();
                        vVar2.V(-156998101);
                        if (eVar2 == null) {
                            z5 = 0;
                            h0Var = h0Var3;
                            dVarC = null;
                        } else {
                            z5 = 0;
                            h0Var = h0Var3;
                            dVarC = n0.i.c(-1236585568, new o(h0Var2, h0Var, fFloatValue, q0VarA4, eVar2, z4, q0VarA3), vVar2);
                        }
                        vVar2.p(z5);
                        objK3 = vVar2.K();
                        if (objK3 == obj) {
                            h1 h1Var9 = new h1(q0VarA, 1);
                            f0.h hVar10 = f0.h.f2189j;
                            a1.b bVar9 = c3.f2109a;
                            n0 n0Var9 = new n0(h1Var9, hVar10);
                            vVar2.f0(n0Var9);
                            objK3 = n0Var9;
                        }
                        vVar2.V(-156965270);
                        vVar2.p(z5);
                        objK4 = vVar2.K();
                        if (objK4 == obj) {
                            i9 = 2;
                            h1 h1Var10 = new h1(q0VarA2, 2);
                            f0.h hVar11 = f0.h.f2189j;
                            a1.b bVar10 = c3.f2109a;
                            n0 n0Var10 = new n0(h1Var10, hVar11);
                            vVar2.f0(n0Var10);
                            objK4 = n0Var10;
                        } else {
                            i9 = 2;
                        }
                        vVar2.V(-156940524);
                        vVar2.p(z5);
                        vVar2.V(-156921964);
                        vVar2.p(z5);
                        if (!z3) {
                            j8 = k4Var.f1003r;
                        } else if (zBooleanValue) {
                            j8 = k4Var.f1001p;
                        } else {
                            j8 = k4Var.f1002q;
                        }
                        vVar2.V(-156902962);
                        if (eVar3 == null) {
                            dVarC2 = null;
                        } else {
                            dVarC2 = n0.i.c(-130107406, new p(z5, j8, eVar3), vVar2);
                        }
                        vVar2.p(z5);
                        vVar2.V(-156893937);
                        vVar2.p(z5);
                        if (!z3) {
                            j9 = k4Var.H;
                        } else if (zBooleanValue) {
                            j9 = k4Var.F;
                        } else {
                            j9 = k4Var.G;
                        }
                        vVar2.V(-156884470);
                        if (eVar4 == null) {
                            i10 = i9;
                            dVarC3 = null;
                        } else {
                            i10 = i9;
                            dVarC3 = n0.i.c(1263707005, new c0.m(j9, h0Var, eVar4, 1), vVar2);
                        }
                        vVar2.p(z5);
                        vVar2.V(-567018607);
                        objK5 = vVar2.K();
                        if (objK5 == obj) {
                            objK5 = f0.l.s(new x0.f(0L));
                            vVar2.f0(objK5);
                        }
                        i1Var = (i1) objK5;
                        n0.d dVarC8 = n0.i.c(157291737, new x0(i1Var, l0Var, dVar, i10), vVar2);
                        zC = vVar2.c(fFloatValue);
                        objK6 = vVar2.K();
                        if (zC) {
                            objK6 = new m(fFloatValue, i1Var);
                            vVar2.f0(objK6);
                        } else {
                            objK6 = new m(fFloatValue, i1Var);
                            vVar2.f0(objK6);
                        }
                        d2.b(eVar, null, dVarC, dVarC2, null, null, null, z2, fFloatValue, (a4.c) objK6, dVarC8, dVarC3, l0Var, vVar2, ((i11 >> 3) & 112) | 6 | ((i8 << 21) & 234881024), ((i8 >> 6) & 7168) | 48);
                        f0.v vVar7 = vVar2;
                        vVar7.p(z5);
                        vVar = vVar7;
                    } else {
                        obj = hVar;
                    }
                    obj = hVar;
                    a1 a1Var17 = new a1(cVar, new g1(6, cVarF4));
                    vVar2.f0(a1Var17);
                    objK2 = a1Var17;
                    obj = hVar;
                    a1 a1Var18 = (a1) objK2;
                    vVar2.V(1023351670);
                    vVar2.p(false);
                    y0.u uVar17 = new y0.u(j5);
                    vVar2.V(1023351670);
                    vVar2.p(false);
                    y0.u uVar18 = new y0.u(j5);
                    s0Var.e();
                    vVar2.V(-543659263);
                    z0 z0VarH14 = m.d.h(150, 6, null);
                    vVar2.p(false);
                    q0VarA4 = y0.a(s0Var, uVar17, uVar18, z0VarH14, a1Var18, vVar2, 196608);
                    fFloatValue = ((Number) q0VarA6.f3743k.getValue()).floatValue();
                    vVar2.V(-156998101);
                    if (eVar2 == null) {
                        z5 = 0;
                        h0Var = h0Var3;
                        dVarC = null;
                    } else {
                        z5 = 0;
                        h0Var = h0Var3;
                        dVarC = n0.i.c(-1236585568, new o(h0Var2, h0Var, fFloatValue, q0VarA4, eVar2, z4, q0VarA3), vVar2);
                    }
                    vVar2.p(z5);
                    objK3 = vVar2.K();
                    if (objK3 == obj) {
                        h1 h1Var11 = new h1(q0VarA, 1);
                        f0.h hVar12 = f0.h.f2189j;
                        a1.b bVar11 = c3.f2109a;
                        n0 n0Var11 = new n0(h1Var11, hVar12);
                        vVar2.f0(n0Var11);
                        objK3 = n0Var11;
                    }
                    vVar2.V(-156965270);
                    vVar2.p(z5);
                    objK4 = vVar2.K();
                    if (objK4 == obj) {
                        i9 = 2;
                        h1 h1Var12 = new h1(q0VarA2, 2);
                        f0.h hVar13 = f0.h.f2189j;
                        a1.b bVar12 = c3.f2109a;
                        n0 n0Var12 = new n0(h1Var12, hVar13);
                        vVar2.f0(n0Var12);
                        objK4 = n0Var12;
                    } else {
                        i9 = 2;
                    }
                    vVar2.V(-156940524);
                    vVar2.p(z5);
                    vVar2.V(-156921964);
                    vVar2.p(z5);
                    if (!z3) {
                        j8 = k4Var.f1003r;
                    } else if (zBooleanValue) {
                        j8 = k4Var.f1001p;
                    } else {
                        j8 = k4Var.f1002q;
                    }
                    vVar2.V(-156902962);
                    if (eVar3 == null) {
                        dVarC2 = null;
                    } else {
                        dVarC2 = n0.i.c(-130107406, new p(z5, j8, eVar3), vVar2);
                    }
                    vVar2.p(z5);
                    vVar2.V(-156893937);
                    vVar2.p(z5);
                    if (!z3) {
                        j9 = k4Var.H;
                    } else if (zBooleanValue) {
                        j9 = k4Var.F;
                    } else {
                        j9 = k4Var.G;
                    }
                    vVar2.V(-156884470);
                    if (eVar4 == null) {
                        i10 = i9;
                        dVarC3 = null;
                    } else {
                        i10 = i9;
                        dVarC3 = n0.i.c(1263707005, new c0.m(j9, h0Var, eVar4, 1), vVar2);
                    }
                    vVar2.p(z5);
                    vVar2.V(-567018607);
                    objK5 = vVar2.K();
                    if (objK5 == obj) {
                        objK5 = f0.l.s(new x0.f(0L));
                        vVar2.f0(objK5);
                    }
                    i1Var = (i1) objK5;
                    n0.d dVarC9 = n0.i.c(157291737, new x0(i1Var, l0Var, dVar, i10), vVar2);
                    zC = vVar2.c(fFloatValue);
                    objK6 = vVar2.K();
                    if (zC) {
                        objK6 = new m(fFloatValue, i1Var);
                        vVar2.f0(objK6);
                    } else {
                        objK6 = new m(fFloatValue, i1Var);
                        vVar2.f0(objK6);
                    }
                    d2.b(eVar, null, dVarC, dVarC2, null, null, null, z2, fFloatValue, (a4.c) objK6, dVarC9, dVarC3, l0Var, vVar2, ((i11 >> 3) & 112) | 6 | ((i8 << 21) & 234881024), ((i8 >> 6) & 7168) | 48);
                    f0.v vVar8 = vVar2;
                    vVar8.p(z5);
                    vVar = vVar8;
                } else if (iOrdinal5 != 2) {
                    throw new i4.p();
                }
            }
            f10 = 1.0f;
            vVar2.p(false);
            Float fValueOf12 = Float.valueOf(f10);
            s0Var.e();
            vVar2.V(-1868044898);
            z4 = z7;
            z0 z0VarH15 = m.d.h(150, 6, null);
            vVar2.p(false);
            q0VarA2 = y0.a(s0Var, fValueOf10, fValueOf12, z0VarH15, a1Var, vVar2, 196608);
            fVar2 = (f) s1Var.getValue();
            vVar2.V(-107432127);
            iArr = t.f1578a;
            if (iArr[fVar2.ordinal()] == 1) {
                j6 = jB2;
            } else {
                j6 = j11;
            }
            vVar2.p(false);
            cVarF = y0.u.f(j6);
            zF = vVar2.f(cVarF);
            objK = vVar2.K();
            cVar = l.c.f3485e;
            if (zF) {
                a1 a1Var19 = new a1(cVar, new g1(6, cVarF));
                vVar2.f0(a1Var19);
                objK = a1Var19;
            } else {
                a1 a1Var110 = new a1(cVar, new g1(6, cVarF));
                vVar2.f0(a1Var110);
                objK = a1Var110;
            }
            a1 a1Var111 = (a1) objK;
            fVar3 = (f) s0Var.c();
            vVar2.V(-107432127);
            if (iArr[fVar3.ordinal()] == 1) {
                j7 = jB2;
            } else {
                j7 = j11;
            }
            vVar2.p(false);
            y0.u uVar19 = new y0.u(j7);
            fVar4 = (f) s1Var.getValue();
            vVar2.V(-107432127);
            if (iArr[fVar4.ordinal()] != 1) {
                jB2 = j11;
            }
            vVar2.p(false);
            y0.u uVar110 = new y0.u(jB2);
            s0Var.e();
            vVar2.V(1528582156);
            z0 z0VarH16 = m.d.h(150, 6, null);
            vVar2.p(false);
            q0VarA3 = y0.a(s0Var, uVar19, uVar110, z0VarH16, a1Var111, vVar2, 196608);
            vVar2.V(1023351670);
            vVar2.p(false);
            z0.c cVarF5 = y0.u.f(j5);
            zF2 = vVar2.f(cVarF5);
            objK2 = vVar2.K();
            if (zF2) {
                if (objK2 == obj) {
                }
                obj = hVar;
                a1 a1Var112 = (a1) objK2;
                vVar2.V(1023351670);
                vVar2.p(false);
                y0.u uVar111 = new y0.u(j5);
                vVar2.V(1023351670);
                vVar2.p(false);
                y0.u uVar112 = new y0.u(j5);
                s0Var.e();
                vVar2.V(-543659263);
                z0 z0VarH17 = m.d.h(150, 6, null);
                vVar2.p(false);
                q0VarA4 = y0.a(s0Var, uVar111, uVar112, z0VarH17, a1Var112, vVar2, 196608);
                fFloatValue = ((Number) q0VarA6.f3743k.getValue()).floatValue();
                vVar2.V(-156998101);
                if (eVar2 == null) {
                    z5 = 0;
                    h0Var = h0Var3;
                    dVarC = null;
                } else {
                    z5 = 0;
                    h0Var = h0Var3;
                    dVarC = n0.i.c(-1236585568, new o(h0Var2, h0Var, fFloatValue, q0VarA4, eVar2, z4, q0VarA3), vVar2);
                }
                vVar2.p(z5);
                objK3 = vVar2.K();
                if (objK3 == obj) {
                    h1 h1Var13 = new h1(q0VarA, 1);
                    f0.h hVar14 = f0.h.f2189j;
                    a1.b bVar13 = c3.f2109a;
                    n0 n0Var13 = new n0(h1Var13, hVar14);
                    vVar2.f0(n0Var13);
                    objK3 = n0Var13;
                }
                vVar2.V(-156965270);
                vVar2.p(z5);
                objK4 = vVar2.K();
                if (objK4 == obj) {
                    i9 = 2;
                    h1 h1Var14 = new h1(q0VarA2, 2);
                    f0.h hVar15 = f0.h.f2189j;
                    a1.b bVar14 = c3.f2109a;
                    n0 n0Var14 = new n0(h1Var14, hVar15);
                    vVar2.f0(n0Var14);
                    objK4 = n0Var14;
                } else {
                    i9 = 2;
                }
                vVar2.V(-156940524);
                vVar2.p(z5);
                vVar2.V(-156921964);
                vVar2.p(z5);
                if (!z3) {
                    j8 = k4Var.f1003r;
                } else if (zBooleanValue) {
                    j8 = k4Var.f1001p;
                } else {
                    j8 = k4Var.f1002q;
                }
                vVar2.V(-156902962);
                if (eVar3 == null) {
                    dVarC2 = null;
                } else {
                    dVarC2 = n0.i.c(-130107406, new p(z5, j8, eVar3), vVar2);
                }
                vVar2.p(z5);
                vVar2.V(-156893937);
                vVar2.p(z5);
                if (!z3) {
                    j9 = k4Var.H;
                } else if (zBooleanValue) {
                    j9 = k4Var.F;
                } else {
                    j9 = k4Var.G;
                }
                vVar2.V(-156884470);
                if (eVar4 == null) {
                    i10 = i9;
                    dVarC3 = null;
                } else {
                    i10 = i9;
                    dVarC3 = n0.i.c(1263707005, new c0.m(j9, h0Var, eVar4, 1), vVar2);
                }
                vVar2.p(z5);
                vVar2.V(-567018607);
                objK5 = vVar2.K();
                if (objK5 == obj) {
                    objK5 = f0.l.s(new x0.f(0L));
                    vVar2.f0(objK5);
                }
                i1Var = (i1) objK5;
                n0.d dVarC10 = n0.i.c(157291737, new x0(i1Var, l0Var, dVar, i10), vVar2);
                zC = vVar2.c(fFloatValue);
                objK6 = vVar2.K();
                if (zC) {
                    objK6 = new m(fFloatValue, i1Var);
                    vVar2.f0(objK6);
                } else {
                    objK6 = new m(fFloatValue, i1Var);
                    vVar2.f0(objK6);
                }
                d2.b(eVar, null, dVarC, dVarC2, null, null, null, z2, fFloatValue, (a4.c) objK6, dVarC10, dVarC3, l0Var, vVar2, ((i11 >> 3) & 112) | 6 | ((i8 << 21) & 234881024), ((i8 >> 6) & 7168) | 48);
                f0.v vVar9 = vVar2;
                vVar9.p(z5);
                vVar = vVar9;
            } else {
                obj = hVar;
            }
            obj = hVar;
            a1 a1Var113 = new a1(cVar, new g1(6, cVarF5));
            vVar2.f0(a1Var113);
            objK2 = a1Var113;
            obj = hVar;
            a1 a1Var114 = (a1) objK2;
            vVar2.V(1023351670);
            vVar2.p(false);
            y0.u uVar113 = new y0.u(j5);
            vVar2.V(1023351670);
            vVar2.p(false);
            y0.u uVar114 = new y0.u(j5);
            s0Var.e();
            vVar2.V(-543659263);
            z0 z0VarH18 = m.d.h(150, 6, null);
            vVar2.p(false);
            q0VarA4 = y0.a(s0Var, uVar113, uVar114, z0VarH18, a1Var114, vVar2, 196608);
            fFloatValue = ((Number) q0VarA6.f3743k.getValue()).floatValue();
            vVar2.V(-156998101);
            if (eVar2 == null) {
                z5 = 0;
                h0Var = h0Var3;
                dVarC = null;
            } else {
                z5 = 0;
                h0Var = h0Var3;
                dVarC = n0.i.c(-1236585568, new o(h0Var2, h0Var, fFloatValue, q0VarA4, eVar2, z4, q0VarA3), vVar2);
            }
            vVar2.p(z5);
            objK3 = vVar2.K();
            if (objK3 == obj) {
                h1 h1Var15 = new h1(q0VarA, 1);
                f0.h hVar16 = f0.h.f2189j;
                a1.b bVar15 = c3.f2109a;
                n0 n0Var15 = new n0(h1Var15, hVar16);
                vVar2.f0(n0Var15);
                objK3 = n0Var15;
            }
            vVar2.V(-156965270);
            vVar2.p(z5);
            objK4 = vVar2.K();
            if (objK4 == obj) {
                i9 = 2;
                h1 h1Var16 = new h1(q0VarA2, 2);
                f0.h hVar17 = f0.h.f2189j;
                a1.b bVar16 = c3.f2109a;
                n0 n0Var16 = new n0(h1Var16, hVar17);
                vVar2.f0(n0Var16);
                objK4 = n0Var16;
            } else {
                i9 = 2;
            }
            vVar2.V(-156940524);
            vVar2.p(z5);
            vVar2.V(-156921964);
            vVar2.p(z5);
            if (!z3) {
                j8 = k4Var.f1003r;
            } else if (zBooleanValue) {
                j8 = k4Var.f1001p;
            } else {
                j8 = k4Var.f1002q;
            }
            vVar2.V(-156902962);
            if (eVar3 == null) {
                dVarC2 = null;
            } else {
                dVarC2 = n0.i.c(-130107406, new p(z5, j8, eVar3), vVar2);
            }
            vVar2.p(z5);
            vVar2.V(-156893937);
            vVar2.p(z5);
            if (!z3) {
                j9 = k4Var.H;
            } else if (zBooleanValue) {
                j9 = k4Var.F;
            } else {
                j9 = k4Var.G;
            }
            vVar2.V(-156884470);
            if (eVar4 == null) {
                i10 = i9;
                dVarC3 = null;
            } else {
                i10 = i9;
                dVarC3 = n0.i.c(1263707005, new c0.m(j9, h0Var, eVar4, 1), vVar2);
            }
            vVar2.p(z5);
            vVar2.V(-567018607);
            objK5 = vVar2.K();
            if (objK5 == obj) {
                objK5 = f0.l.s(new x0.f(0L));
                vVar2.f0(objK5);
            }
            i1Var = (i1) objK5;
            n0.d dVarC11 = n0.i.c(157291737, new x0(i1Var, l0Var, dVar, i10), vVar2);
            zC = vVar2.c(fFloatValue);
            objK6 = vVar2.K();
            if (zC) {
                objK6 = new m(fFloatValue, i1Var);
                vVar2.f0(objK6);
            } else {
                objK6 = new m(fFloatValue, i1Var);
                vVar2.f0(objK6);
            }
            d2.b(eVar, null, dVarC, dVarC2, null, null, null, z2, fFloatValue, (a4.c) objK6, dVarC11, dVarC3, l0Var, vVar2, ((i11 >> 3) & 112) | 6 | ((i8 << 21) & 234881024), ((i8 >> 6) & 7168) | 48);
            f0.v vVar10 = vVar2;
            vVar10.p(z5);
            vVar = vVar10;
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new q(str, eVar, f0Var, eVar2, eVar3, eVar4, z2, z3, jVar, l0Var, k4Var, dVar, i5, i6);
        }
    }

    public static final void b(long j5, h0 h0Var, a4.e eVar, f0.p pVar, int i5) {
        int i6;
        f0.v vVar = (f0.v) pVar;
        vVar.X(1208685580);
        if ((i5 & 6) == 0) {
            i6 = (vVar.e(j5) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.f(h0Var) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.h(eVar) ? 256 : 128;
        }
        if ((i6 & 147) == 146 && vVar.z()) {
            vVar.Q();
        } else {
            c.a(j5, h0Var, eVar, vVar, i6 & 1022);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new l(j5, h0Var, eVar, i5, 1);
        }
    }

    public static final void c(long j5, a4.e eVar, f0.p pVar, int i5) {
        int i6;
        f0.v vVar = (f0.v) pVar;
        vVar.X(660142980);
        if ((i5 & 6) == 0) {
            i6 = (vVar.e(j5) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.h(eVar) ? 32 : 16;
        }
        if ((i6 & 19) == 18 && vVar.z()) {
            vVar.Q();
        } else {
            f0.l.a(b0.f762a.a(new y0.u(j5)), eVar, vVar, (i6 & 112) | 8);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new r(j5, eVar, i5);
        }
    }

    public static final Object d(n1.e0 e0Var) {
        Object objH = e0Var.h();
        n1.q qVar = objH instanceof n1.q ? (n1.q) objH : null;
        if (qVar != null) {
            return qVar.f4147q;
        }
        return null;
    }
}
