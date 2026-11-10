package v1;

import a4.c;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ScrollCaptureTarget;
import android.view.View;
import f0.l;
import f0.s1;
import i4.y;
import java.util.Comparator;
import java.util.function.Consumer;
import n1.v0;
import p1.y0;
import p3.p;
import w1.o;
import y0.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1 f6204a = l.s(Boolean.FALSE);

    public final void a(View view, o oVar, t3.h hVar, Consumer<ScrollCaptureTarget> consumer) {
        h0.e eVar = new h0.e(new j[16]);
        p3.j.Q(oVar.a(), 0, new h(1, h0.e.class, eVar, "add", "add(Ljava/lang/Object;)Z"));
        final a4.c[] cVarArr = {b.f6186g, b.f6187h};
        eVar.p(new Comparator() { // from class: s3.a
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                for (c cVar : cVarArr) {
                    int iJ = p.j((Comparable) cVar.c(obj), (Comparable) cVar.c(obj2));
                    if (iJ != 0) {
                        return iJ;
                    }
                }
                return 0;
            }
        });
        j jVar = (j) (eVar.j() ? null : eVar.f2576d[eVar.f2578f - 1]);
        if (jVar == null) {
            return;
        }
        k2.i iVar = jVar.f6207c;
        c cVar = new c(jVar.f6205a, iVar, y.a(hVar), this);
        y0 y0Var = jVar.f6208d;
        x0.d dVarK = v0.f(y0Var).k(y0Var, true);
        long jD = h1.c.d(iVar.f3411a, iVar.f3412b);
        ScrollCaptureTarget scrollCaptureTargetI = q1.i.i(view, new Rect(Math.round(dVarK.f6534a), Math.round(dVarK.f6535b), Math.round(dVarK.f6536c), Math.round(dVarK.f6537d)), new Point((int) (jD >> 32), (int) (jD & 4294967295L)), cVar);
        scrollCaptureTargetI.setScrollBounds(l0.t(iVar));
        consumer.accept(scrollCaptureTargetI);
    }
}
