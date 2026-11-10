package s;

import android.graphics.Rect;
import android.view.View;
import p1.l;
import p1.y0;
import p3.n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ l f5760d;

    public h(l lVar) {
        this.f5760d = lVar;
    }

    @Override // s.a
    public final Object z(y0 y0Var, a4.a aVar, v3.c cVar) {
        View viewV = p1.f.v(this.f5760d);
        long jZ = y0Var.Z(0L);
        x0.d dVar = (x0.d) aVar.a();
        x0.d dVarH = dVar != null ? dVar.h(jZ) : null;
        if (dVarH != null) {
            viewV.requestRectangleOnScreen(new Rect((int) dVarH.f6534a, (int) dVarH.f6535b, (int) dVarH.f6536c, (int) dVarH.f6537d), false);
        }
        return n.f5026a;
    }
}
