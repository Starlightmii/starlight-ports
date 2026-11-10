package androidx.compose.ui.layout;

import a4.c;
import a4.f;
import n1.e0;
import n1.q;
import r0.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final Object a(e0 e0Var) {
        Object objH = e0Var.h();
        q qVar = objH instanceof q ? (q) objH : null;
        if (qVar != null) {
            return qVar.f4147q;
        }
        return null;
    }

    public static final p b(f fVar) {
        return new LayoutElement(fVar);
    }

    public static final p c(p pVar, String str) {
        return pVar.e(new LayoutIdElement(str));
    }

    public static final p d(p pVar, c cVar) {
        return pVar.e(new OnGloballyPositionedElement(cVar));
    }

    public static final p e(p pVar, c cVar) {
        return pVar.e(new OnSizeChangedModifier(cVar));
    }
}
