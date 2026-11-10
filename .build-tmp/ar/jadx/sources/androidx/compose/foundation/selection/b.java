package androidx.compose.foundation.selection;

import a4.c;
import androidx.compose.foundation.d;
import n.i0;
import n.n0;
import q.k;
import r0.m;
import r0.p;
import w1.f;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static final p a(p pVar, boolean z2, k kVar, i0 i0Var, boolean z3, f fVar, a4.a aVar) {
        p pVarE;
        if (i0Var instanceof n0) {
            pVarE = new SelectableElement(z2, kVar, (n0) i0Var, z3, fVar, aVar);
        } else if (i0Var == null) {
            pVarE = new SelectableElement(z2, kVar, null, z3, fVar, aVar);
        } else {
            m mVar = m.f5680a;
            pVarE = kVar != null ? d.a(mVar, kVar, i0Var).e(new SelectableElement(z2, kVar, null, z3, fVar, aVar)) : r0.a.a(mVar, new a(i0Var, z2, z3, fVar, aVar));
        }
        return pVar.e(pVarE);
    }

    public static final p b(p pVar, boolean z2, k kVar, boolean z3, f fVar, c cVar) {
        return pVar.e(new ToggleableElement(z2, kVar, z3, fVar, cVar));
    }
}
