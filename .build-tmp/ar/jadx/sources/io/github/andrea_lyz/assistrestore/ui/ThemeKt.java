package io.github.andrea_lyz.assistrestore.ui;

import android.content.res.Configuration;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import c0.t0;
import f0.b2;
import f0.h3;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class ThemeKt {
    /* JADX WARN: Code duplicated, block: B:43:0x0086  */
    /* JADX WARN: Code duplicated, block: B:45:0x00d7  */
    public static final void AssistRestoreTheme(boolean z2, final a4.e eVar, f0.p pVar, final int i5, final int i6) {
        final boolean z3;
        int i7;
        boolean z4;
        c0.s sVarE;
        b4.l.e("content", eVar);
        f0.v vVar = (f0.v) pVar;
        vVar.X(1756292831);
        if ((i5 & 6) == 0) {
            if ((i6 & 1) == 0) {
                z3 = z2;
                int i8 = vVar.g(z3) ? 4 : 2;
                i7 = i8 | i5;
            } else {
                z3 = z2;
            }
            i7 = i8 | i5;
        } else {
            z3 = z2;
            i7 = i5;
        }
        if ((i5 & 48) == 0) {
            i7 |= vVar.h(eVar) ? 32 : 16;
        }
        if (vVar.N(i7 & 1, (i7 & 19) != 18)) {
            vVar.S();
            if ((i5 & 1) == 0 || vVar.x()) {
                if ((i6 & 1) != 0) {
                    z4 = (((Configuration) vVar.j(AndroidCompositionLocals_androidKt.f203a)).uiMode & 48) == 32;
                    i7 &= -15;
                }
                vVar.q();
                if (z4) {
                    h3 h3Var = c0.u.f1329a;
                    long j5 = e0.a.f1668t;
                    sVarE = new c0.s(j5, e0.a.f1658j, e0.a.f1669u, e0.a.f1659k, e0.a.f1653e, e0.a.f1671w, e0.a.f1660l, e0.a.f1672x, e0.a.f1661m, e0.a.H, e0.a.f1664p, e0.a.I, e0.a.f1665q, e0.a.f1649a, e0.a.f1655g, e0.a.f1673y, e0.a.f1662n, e0.a.G, e0.a.f1663o, j5, e0.a.f1654f, e0.a.f1652d, e0.a.f1650b, e0.a.f1656h, e0.a.f1651c, e0.a.f1657i, e0.a.f1666r, e0.a.f1667s, e0.a.f1670v, e0.a.f1674z, e0.a.F, e0.a.A, e0.a.B, e0.a.C, e0.a.D, e0.a.E);
                } else {
                    sVarE = c0.u.e();
                }
                t0.a(sVarE, null, null, eVar, vVar, (i7 << 6) & 7168);
                z3 = z4;
            } else {
                vVar.Q();
                if ((i6 & 1) != 0) {
                    i7 &= -15;
                }
            }
            z4 = z3;
            vVar.q();
            if (z4) {
                h3 h3Var2 = c0.u.f1329a;
                long j6 = e0.a.f1668t;
                sVarE = new c0.s(j6, e0.a.f1658j, e0.a.f1669u, e0.a.f1659k, e0.a.f1653e, e0.a.f1671w, e0.a.f1660l, e0.a.f1672x, e0.a.f1661m, e0.a.H, e0.a.f1664p, e0.a.I, e0.a.f1665q, e0.a.f1649a, e0.a.f1655g, e0.a.f1673y, e0.a.f1662n, e0.a.G, e0.a.f1663o, j6, e0.a.f1654f, e0.a.f1652d, e0.a.f1650b, e0.a.f1656h, e0.a.f1651c, e0.a.f1657i, e0.a.f1666r, e0.a.f1667s, e0.a.f1670v, e0.a.f1674z, e0.a.F, e0.a.A, e0.a.B, e0.a.C, e0.a.D, e0.a.E);
            } else {
                sVarE = c0.u.e();
            }
            t0.a(sVarE, null, null, eVar, vVar, (i7 << 6) & 7168);
            z3 = z4;
        } else {
            vVar.Q();
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.s0
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return ThemeKt.AssistRestoreTheme$lambda$0(z3, eVar, i5, i6, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreTheme$lambda$0(boolean z2, a4.e eVar, int i5, int i6, f0.p pVar, int i7) {
        AssistRestoreTheme(z2, eVar, pVar, f0.l.z(i5 | 1), i6);
        return p3.n.f5026a;
    }
}
