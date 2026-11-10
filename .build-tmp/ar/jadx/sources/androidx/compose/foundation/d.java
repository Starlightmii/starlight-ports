package androidx.compose.foundation;

import f0.h3;
import n.i0;
import n.k0;
import n.n0;
import q.j;
import r0.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h3 f78a = new h3(k0.f3950f);

    public static final p a(p pVar, j jVar, i0 i0Var) {
        if (i0Var == null) {
            return pVar;
        }
        return i0Var instanceof n0 ? pVar.e(new IndicationModifierElement(jVar, (n0) i0Var)) : r0.a.a(pVar, new h2.b(i0Var, 1, jVar));
    }
}
