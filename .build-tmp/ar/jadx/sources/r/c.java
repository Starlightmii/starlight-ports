package r;

import f0.w1;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f5529a = new b(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f5530b = new b(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f5531c = 9;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f5532d = 6;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f5533e = 10;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f5534f = 5;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int f5535g = 15;

    public static final void a(f0.p pVar, r0.p pVar2) {
        n nVar = n.f5593c;
        int iP = f0.l.p(pVar);
        r0.p pVarC = r0.a.c(pVar, pVar2);
        f0.v vVar = (f0.v) pVar;
        w1 w1VarL = vVar.l();
        p1.j.f4892c.getClass();
        p1.n nVar2 = p1.i.f4867b;
        vVar.Y();
        if (vVar.R) {
            vVar.k(nVar2);
        } else {
            vVar.i0();
        }
        f0.l.x(p1.i.f4870e, pVar, nVar);
        f0.l.x(p1.i.f4869d, pVar, w1VarL);
        f0.l.x(p1.i.f4868c, pVar, pVarC);
        p1.h hVar = p1.i.f4871f;
        if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iP))) {
            b.b.i(iP, vVar, iP, hVar);
        }
        vVar.p(true);
    }

    public static final p0 c(n1.e0 e0Var) {
        Object objH = e0Var.h();
        if (objH instanceof p0) {
            return (p0) objH;
        }
        return null;
    }

    public static final float d(p0 p0Var) {
        if (p0Var != null) {
            return p0Var.f5605a;
        }
        return 0.0f;
    }

    public static n1.g0 e(o0 o0Var, int i5, int i6, int i7, int i8, int i9, n1.h0 h0Var, List list, n1.m0[] m0VarArr, int i10) throws Throwable {
        int[] iArr;
        float f5;
        String str;
        long j5;
        int i11;
        int i12;
        int iM;
        int i13;
        String str2;
        int i14;
        int i15;
        int i16;
        boolean z2;
        List list2 = list;
        int i17 = i10;
        long j6 = i9;
        int[] iArr2 = new int[i17];
        float f6 = 0.0f;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int iMin = 0;
        int iMax = 0;
        while (i18 < i17) {
            n1.e0 e0Var = (n1.e0) list2.get(i18);
            float fD = d(c(e0Var));
            if (fD > 0.0f) {
                f6 += fD;
                i19++;
                i18 = i18;
            } else {
                int i21 = i7 - i20;
                n1.m0 m0VarC = m0VarArr[i18];
                if (m0VarC == null) {
                    m0VarC = e0Var.c(o0Var.g(0, i7 == Integer.MAX_VALUE ? Integer.MAX_VALUE : i21 < 0 ? 0 : i21, i8, false));
                }
                n1.m0 m0Var = m0VarC;
                int iF = o0Var.f(m0Var);
                int iC = o0Var.c(m0Var);
                iArr2[i18] = iF;
                int i22 = i21 - iF;
                if (i22 < 0) {
                    i22 = 0;
                }
                iMin = Math.min(i9, i22);
                i20 += iF + iMin;
                iMax = Math.max(iMax, iC);
                m0VarArr[i18] = m0Var;
                f6 = f6;
            }
            i18++;
        }
        float f7 = f6;
        int i23 = iMax;
        if (i19 != 0) {
            int i24 = i7 != Integer.MAX_VALUE ? i7 : i5;
            long j7 = ((long) (i19 - 1)) * j6;
            iArr = iArr2;
            long j8 = ((long) (i24 - i20)) - j7;
            if (j8 < 0) {
                j8 = 0;
            }
            float f8 = j8 / f7;
            long jRound = j8;
            int i25 = 0;
            while (true) {
                f5 = f8;
                str = "fixedSpace ";
                j5 = j8;
                if (i25 >= i17) {
                    break;
                }
                int i26 = i25;
                float fD2 = d(c((n1.e0) list2.get(i25)));
                float f9 = f5 * fD2;
                try {
                    jRound -= (long) Math.round(f9);
                    i25 = i26 + 1;
                    list2 = list;
                    f8 = f5;
                    j8 = j5;
                } catch (IllegalArgumentException e5) {
                    throw new IllegalArgumentException("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/297974033 mainAxisMax " + i7 + "mainAxisMin " + i5 + "targetSpace " + i24 + "arrangementSpacingPx " + j6 + "weightChildrenCount " + i19 + "fixedSpace " + i20 + "arrangementSpacingTotal " + j7 + "remainingToTarget " + j5 + "totalWeight " + f7 + "weightUnitSpace " + f5 + "itemWeight " + fD2 + "weightedSize " + f9).initCause(e5);
                }
            }
            int i27 = i23;
            int i28 = 0;
            int i29 = 0;
            while (true) {
                long j9 = j5;
                if (i29 >= i17) {
                    i11 = i20;
                    i12 = 0;
                    i23 = i27;
                    iM = d4.a.m((int) (((long) i28) + j7), 0, i7 - i11);
                    break;
                }
                if (m0VarArr[i29] == null) {
                    i13 = i29;
                    n1.e0 e0Var2 = (n1.e0) list.get(i29);
                    p0 p0VarC = c(e0Var2);
                    i14 = i20;
                    float fD3 = d(p0VarC);
                    if (fD3 <= 0.0f) {
                        throw new IllegalStateException("All weights <= 0 should have placeables");
                    }
                    int iSignum = Long.signum(jRound);
                    str2 = str;
                    int i30 = i19;
                    jRound -= (long) iSignum;
                    float f10 = f5 * fD3;
                    int iMax2 = Math.max(0, Math.round(f10) + iSignum);
                    if (p0VarC != null) {
                        try {
                            z2 = p0VarC.f5606b;
                        } catch (IllegalArgumentException e6) {
                            e = e6;
                            throw new IllegalArgumentException("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/300280216 mainAxisMax " + i7 + "mainAxisMin " + i5 + "targetSpace " + i24 + "arrangementSpacingPx " + j6 + "weightChildrenCount " + i30 + str2 + i14 + "arrangementSpacingTotal " + j7 + "remainingToTarget " + j9 + "totalWeight " + f7 + "weightUnitSpace " + f5 + "weight " + fD3 + "weightedSize " + f10 + "crossAxisDesiredSize nullremainderUnit " + iSignum + "childMainAxisSize " + iMax2).initCause(e);
                        }
                    } else {
                        z2 = true;
                    }
                    try {
                        n1.m0 m0VarC2 = e0Var2.c(o0Var.g((!z2 || iMax2 == Integer.MAX_VALUE) ? 0 : iMax2, iMax2, i8, true));
                        int iF2 = o0Var.f(m0VarC2);
                        int iC2 = o0Var.c(m0VarC2);
                        iArr[i13] = iF2;
                        i16 = i28 + iF2;
                        int iMax3 = Math.max(i27, iC2);
                        m0VarArr[i13] = m0VarC2;
                        i27 = iMax3;
                        i15 = i30;
                    } catch (IllegalArgumentException e7) {
                        e = e7;
                        throw new IllegalArgumentException("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/300280216 mainAxisMax " + i7 + "mainAxisMin " + i5 + "targetSpace " + i24 + "arrangementSpacingPx " + j6 + "weightChildrenCount " + i30 + str2 + i14 + "arrangementSpacingTotal " + j7 + "remainingToTarget " + j9 + "totalWeight " + f7 + "weightUnitSpace " + f5 + "weight " + fD3 + "weightedSize " + f10 + "crossAxisDesiredSize nullremainderUnit " + iSignum + "childMainAxisSize " + iMax2).initCause(e);
                    }
                } else {
                    i13 = i29;
                    str2 = str;
                    i14 = i20;
                    i15 = i19;
                    i16 = i28;
                }
                j5 = j9;
                i29 = i13 + 1;
                i28 = i16;
                i19 = i15;
                i20 = i14;
                str = str2;
                i17 = i10;
            }
        } else {
            iArr = iArr2;
            i11 = i20 - iMin;
            i12 = 0;
            iM = 0;
        }
        int i31 = i11 + iM;
        if (i31 < 0) {
            i31 = i12;
        }
        int iMax4 = Math.max(i31, i5);
        int iMax5 = Math.max(i23, Math.max(i6, i12));
        int[] iArr3 = new int[i10];
        for (int i32 = i12; i32 < i10; i32++) {
            iArr3[i32] = i12;
        }
        o0Var.e(iMax4, h0Var, iArr, iArr3);
        return o0Var.a(m0VarArr, h0Var, iArr3, iMax4, iMax5);
    }

    public static final e0 f(r2.c cVar) {
        return new e0(cVar.f5697a, cVar.f5698b, cVar.f5699c, cVar.f5700d);
    }

    public static final void g(StringBuilder sb, String str) {
        if (sb.length() > 0) {
            sb.append('+');
        }
        sb.append(str);
    }

    public abstract int b(int i5, k2.k kVar);
}
