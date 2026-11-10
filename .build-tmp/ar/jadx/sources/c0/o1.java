package c0;

import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o1 implements n1.f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a4.a f1130a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ a4.e f1131b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean f1132c;

    public o1(a4.a aVar, a4.e eVar, boolean z2) {
        this.f1130a = aVar;
        this.f1131b = eVar;
        this.f1132c = z2;
    }

    @Override // n1.f0
    public final n1.g0 h(n1.h0 h0Var, List list, long j5) {
        float f5;
        Object obj;
        n1.m0 m0VarC;
        n1.m0 m0VarC2;
        o1 o1Var = this;
        float fFloatValue = ((Number) o1Var.f1130a.a()).floatValue();
        long jB = k2.a.b(j5, 0, 0, 0, 0, 10);
        int size = list.size();
        int i5 = 0;
        while (i5 < size) {
            n1.e0 e0Var = (n1.e0) list.get(i5);
            if (b4.l.a(androidx.compose.ui.layout.a.a(e0Var), "icon")) {
                n1.m0 m0VarC3 = e0Var.c(jB);
                char c5 = 2;
                float f6 = 2;
                int iM = h0Var.M(r1.f1222d * f6) + m0VarC3.f4130d;
                int iH = d4.a.H(iM * fFloatValue);
                int iM2 = h0Var.M(r1.f1223e * f6) + m0VarC3.f4131e;
                int size2 = list.size();
                int i6 = 0;
                while (i6 < size2) {
                    n1.e0 e0Var2 = (n1.e0) list.get(i6);
                    char c6 = c5;
                    float f7 = fFloatValue;
                    if (b4.l.a(androidx.compose.ui.layout.a.a(e0Var2), "indicatorRipple")) {
                        if (iM < 0 || iM2 < 0) {
                            d4.a.J("width(" + iM + ") and height(" + iM2 + ") must be >= 0");
                            throw null;
                        }
                        n1.m0 m0VarC4 = e0Var2.c(h1.c.r(iM, iM, iM2, iM2));
                        int size3 = list.size();
                        int i7 = 0;
                        while (true) {
                            if (i7 >= size3) {
                                f5 = f6;
                                obj = null;
                                break;
                            }
                            obj = list.get(i7);
                            int i8 = size3;
                            f5 = f6;
                            if (b4.l.a(androidx.compose.ui.layout.a.a((n1.e0) obj), "indicator")) {
                                break;
                            }
                            i7++;
                            f6 = f5;
                            size3 = i8;
                        }
                        n1.e0 e0Var3 = (n1.e0) obj;
                        if (e0Var3 == null) {
                            m0VarC = null;
                        } else {
                            if (iH < 0 || iM2 < 0) {
                                d4.a.J("width(" + iH + ") and height(" + iM2 + ") must be >= 0");
                                throw null;
                            }
                            m0VarC = e0Var3.c(h1.c.r(iH, iH, iM2, iM2));
                        }
                        a4.e eVar = o1Var.f1131b;
                        if (eVar != null) {
                            int size4 = list.size();
                            int i9 = 0;
                            while (true) {
                                if (i9 >= size4) {
                                    throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                }
                                n1.e0 e0Var4 = (n1.e0) list.get(i9);
                                if (b4.l.a(androidx.compose.ui.layout.a.a(e0Var4), "label")) {
                                    m0VarC2 = e0Var4.c(jB);
                                    break;
                                }
                                i9++;
                            }
                        } else {
                            m0VarC2 = null;
                        }
                        q3.t tVar = q3.t.f5478d;
                        if (eVar == null) {
                            int i10 = k2.a.i(j5);
                            int iP = h1.c.p(j5, h0Var.M(r1.f1219a));
                            return h0Var.F(i10, iP, tVar, new p1(m0VarC, m0VarC3, (i10 - m0VarC3.f4130d) / 2, (iP - m0VarC3.f4131e) / 2, m0VarC4, (i10 - m0VarC4.f4130d) / 2, (iP - m0VarC4.f4131e) / 2, i10, iP));
                        }
                        b4.l.b(m0VarC2);
                        float f8 = m0VarC3.f4131e;
                        float f9 = r1.f1223e;
                        float fV = h0Var.v(f9) + f8;
                        float f10 = r1.f1221c;
                        float fV2 = h0Var.v(f10) + fV + m0VarC2.f4131e;
                        float fJ = (k2.a.j(j5) - fV2) / f5;
                        float fV3 = h0Var.v(f9);
                        if (fJ < fV3) {
                            fJ = fV3;
                        }
                        float f11 = (fJ * f5) + fV2;
                        boolean z2 = o1Var.f1132c;
                        float f12 = ((z2 ? fJ : (f11 - m0VarC3.f4131e) / f5) - fJ) * (1 - f7);
                        float fV4 = h0Var.v(f10) + h0Var.v(f9) + m0VarC3.f4131e + fJ;
                        int i11 = k2.a.i(j5);
                        return h0Var.F(i11, d4.a.H(f11), tVar, new q1(m0VarC, z2, f7, m0VarC2, (i11 - m0VarC2.f4130d) / 2, fV4, f12, m0VarC3, (i11 - m0VarC3.f4130d) / 2, fJ, m0VarC4, (i11 - m0VarC4.f4130d) / 2, fJ - h0Var.v(f9), i11, h0Var));
                    }
                    i6++;
                    o1Var = this;
                    c5 = c6;
                    fFloatValue = f7;
                    f6 = f6;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            i5++;
            o1Var = this;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
