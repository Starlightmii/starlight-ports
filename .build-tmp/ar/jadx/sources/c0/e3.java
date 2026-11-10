package c0;

import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e3 implements n1.f0 {
    /* JADX WARN: Code duplicated, block: B:64:0x0104 A[PHI: r4 r6
      0x0104: PHI (r4v11 int) = (r4v10 int), (r4v15 int), (r4v15 int) binds: [B:67:0x011e, B:60:0x00f9, B:62:0x00ff] A[DONT_GENERATE, DONT_INLINE]
      0x0104: PHI (r6v8 int) = (r6v7 int), (r6v13 int), (r6v13 int) binds: [B:67:0x011e, B:60:0x00f9, B:62:0x00ff] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // n1.f0
    public final n1.g0 h(n1.h0 h0Var, List list, long j5) {
        Object obj;
        Object obj2;
        int iM;
        int iMax;
        int i5;
        int iF0;
        List list2 = list;
        int iMin = Math.min(k2.a.i(j5), h0Var.M(j3.f947a));
        int size = list2.size();
        int i6 = 0;
        while (true) {
            if (i6 >= size) {
                obj = null;
                break;
            }
            obj = list2.get(i6);
            if (b4.l.a(androidx.compose.ui.layout.a.a((n1.e0) obj), "action")) {
                break;
            }
            i6++;
        }
        n1.e0 e0Var = (n1.e0) obj;
        n1.m0 m0VarC = e0Var != null ? e0Var.c(j5) : null;
        int size2 = list2.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size2) {
                obj2 = null;
                break;
            }
            obj2 = list2.get(i7);
            if (b4.l.a(androidx.compose.ui.layout.a.a((n1.e0) obj2), "dismissAction")) {
                break;
            }
            i7++;
        }
        n1.e0 e0Var2 = (n1.e0) obj2;
        n1.m0 m0VarC2 = e0Var2 != null ? e0Var2.c(j5) : null;
        int i8 = m0VarC != null ? m0VarC.f4130d : 0;
        int i9 = m0VarC != null ? m0VarC.f4131e : 0;
        int i10 = m0VarC2 != null ? m0VarC2.f4130d : 0;
        int i11 = m0VarC2 != null ? m0VarC2.f4131e : 0;
        int iM2 = ((iMin - i8) - i10) - (i10 == 0 ? h0Var.M(j3.f953g) : 0);
        int iK = k2.a.k(j5);
        if (iM2 >= iK) {
            iK = iM2;
        }
        int size3 = list2.size();
        int i12 = 0;
        while (i12 < size3) {
            n1.e0 e0Var3 = (n1.e0) list2.get(i12);
            if (b4.l.a(androidx.compose.ui.layout.a.a(e0Var3), "text")) {
                n1.m0 m0VarC3 = e0Var3.c(k2.a.b(j5, 0, iK, 0, 0, 9));
                n1.l lVar = n1.c.f4106a;
                int iF1 = m0VarC3.f0(lVar);
                int iF2 = m0VarC3.f0(n1.c.f4107b);
                boolean z2 = true;
                boolean z3 = (iF1 == Integer.MIN_VALUE || iF2 == Integer.MIN_VALUE) ? false : true;
                if (iF1 != iF2 && z3) {
                    z2 = false;
                }
                int i13 = iMin - i10;
                int i14 = i13 - i8;
                if (z2) {
                    iMax = Math.max(h0Var.M(e0.n.f1784f), Math.max(i9, i11));
                    iM = (iMax - m0VarC3.f4131e) / 2;
                    if (m0VarC == null || (iF0 = m0VarC.f0(lVar)) == Integer.MIN_VALUE) {
                        i5 = 0;
                    } else {
                        i5 = (iF1 + iM) - iF0;
                    }
                } else {
                    iM = h0Var.M(j3.f948b) - iF1;
                    iMax = Math.max(h0Var.M(e0.n.f1785g), m0VarC3.f4131e + iM);
                    if (m0VarC != null) {
                        i5 = (iMax - m0VarC.f4131e) / 2;
                    } else {
                        i5 = 0;
                    }
                }
                return h0Var.F(iMin, iMax, q3.t.f5478d, new d3(m0VarC3, iM, m0VarC2, i13, m0VarC2 != null ? (iMax - m0VarC2.f4131e) / 2 : 0, m0VarC, i14, i5));
            }
            i12++;
            iK = iK;
            list2 = list;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
