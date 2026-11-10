package androidx.compose.foundation;

import n.i0;
import n.n0;
import n.r;
import q.k;
import r0.m;
import r0.p;
import w1.f;
import y0.e0;
import y0.l0;
import y0.q0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static p a(p pVar, e0 e0Var) {
        return pVar.e(new BackgroundElement(0L, e0Var, l0.f6642a, 1));
    }

    public static final p b(p pVar, long j5, q0 q0Var) {
        return pVar.e(new BackgroundElement(j5, null, q0Var, 2));
    }

    public static final p c(p pVar, k kVar, i0 i0Var, boolean z2, String str, f fVar, a4.a aVar) {
        p pVarE;
        if (i0Var instanceof n0) {
            pVarE = new ClickableElement(kVar, (n0) i0Var, z2, str, fVar, aVar);
        } else if (i0Var == null) {
            pVarE = new ClickableElement(kVar, null, z2, str, fVar, aVar);
        } else {
            m mVar = m.f5680a;
            pVarE = kVar != null ? d.a(mVar, kVar, i0Var).e(new ClickableElement(kVar, null, z2, str, fVar, aVar)) : r0.a.a(mVar, new b(i0Var, z2, str, fVar, aVar));
        }
        return pVar.e(pVarE);
    }

    public static /* synthetic */ p d(p pVar, k kVar, i0 i0Var, boolean z2, f fVar, a4.a aVar, int i5) {
        if ((i5 & 16) != 0) {
            fVar = null;
        }
        return c(pVar, kVar, i0Var, z2, null, fVar, aVar);
    }

    public static p e(p pVar, boolean z2, String str, a4.a aVar, int i5) {
        if ((i5 & 1) != 0) {
            z2 = true;
        }
        if ((i5 & 2) != 0) {
            str = null;
        }
        return r0.a.a(pVar, new r(z2, str, aVar));
    }
}
