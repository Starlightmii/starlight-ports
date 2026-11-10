package androidx.compose.foundation;

import a4.f;
import android.content.Context;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import b4.m;
import f0.o;
import f0.p;
import f0.v;
import n.g;
import n.h1;
import n.u0;
import n.v0;
import n.w0;
import n.x0;
import p.l0;
import q.k;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends m implements f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ h1 f79e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(h1 h1Var) {
        super(3);
        this.f79e = h1Var;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        x0 x0Var;
        ((Number) obj3).intValue();
        v vVar = (v) ((p) obj2);
        vVar.V(1478351300);
        h1 h1Var = this.f79e;
        ScrollSemanticsElement scrollSemanticsElement = new ScrollSemanticsElement(h1Var);
        k kVar = h1Var.f3922c;
        Context context = (Context) vVar.j(AndroidCompositionLocals_androidKt.f204b);
        v0 v0Var = (v0) vVar.j(w0.f4040a);
        if (v0Var != null) {
            vVar.V(1586021609);
            boolean zF = vVar.f(context) | vVar.f(v0Var);
            Object objK = vVar.K();
            if (zF || objK == o.f2281a) {
                objK = new g(context, v0Var);
                vVar.f0(objK);
            }
            x0Var = (g) objK;
            vVar.p(false);
        } else {
            vVar.V(1586120933);
            vVar.p(false);
            x0Var = u0.f4030f;
        }
        r0.p pVarE = scrollSemanticsElement.e(n.v.f4033b).e(x0Var.f());
        r0.p pVarE2 = androidx.compose.foundation.gestures.a.b(pVarE, h1Var, l0.f4525d, x0Var, true, true, kVar).e(new ScrollingLayoutElement(h1Var));
        vVar.p(false);
        return pVarE2;
    }
}
