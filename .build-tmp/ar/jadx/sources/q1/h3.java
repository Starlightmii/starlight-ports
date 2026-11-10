package q1;

import android.view.View;
import android.view.ViewGroup;
import io.github.andrea_lyz.assistrestore.R;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class h3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ViewGroup.LayoutParams f5224a = new ViewGroup.LayoutParams(-2, -2);

    /* JADX WARN: Code duplicated, block: B:20:0x0058  */
    /* JADX WARN: Code duplicated, block: B:23:0x008b  */
    /* JADX WARN: Code duplicated, block: B:25:0x0090  */
    /* JADX WARN: Code duplicated, block: B:28:0x00ad  */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.Object, java.util.Collection] */
    public static final f3 a(a aVar, f0.b0 b0Var, n0.d dVar) {
        u uVar;
        f0.e0 e0Var;
        f3 f3Var;
        if (q1.f5308a.compareAndSet(false, true)) {
            k4.c cVarA = k4.j.a(1, 6, null);
            i4.y.n(i4.y.a((t3.h) v0.f5424p.getValue()), null, new b0.d(cVarA, null), 3);
            c0.g1 g1Var = new c0.g1(20, cVarA);
            synchronized (p0.m.f4719c) {
                p0.m.f4725i = q3.l.d0(p0.m.f4725i, g1Var);
            }
            p0.m.a();
        }
        if (aVar.getChildCount() > 0) {
            View childAt = aVar.getChildAt(0);
            if (childAt instanceof u) {
                uVar = (u) childAt;
            }
            if (uVar == null) {
                uVar = new u(aVar.getContext(), b0Var.i());
                aVar.addView(uVar.getView(), f5224a);
            }
            e0Var = new f0.e0(b0Var, new a1.b(uVar.getRoot()));
            Object tag = uVar.getView().getTag(R.id.wrapped_composition_tag);
            f3Var = tag instanceof f3 ? (f3) tag : null;
            if (f3Var == null) {
                f3Var = new f3(uVar, e0Var);
                uVar.getView().setTag(R.id.wrapped_composition_tag, f3Var);
            }
            f3Var.f(dVar);
            if (!b4.l.a(uVar.getCoroutineContext(), b0Var.i())) {
                uVar.setCoroutineContext(b0Var.i());
            }
            return f3Var;
        }
        aVar.removeAllViews();
        uVar = null;
        if (uVar == null) {
            uVar = new u(aVar.getContext(), b0Var.i());
            aVar.addView(uVar.getView(), f5224a);
        }
        e0Var = new f0.e0(b0Var, new a1.b(uVar.getRoot()));
        Object tag2 = uVar.getView().getTag(R.id.wrapped_composition_tag);
        if (tag2 instanceof f3) {
        }
        if (f3Var == null) {
            f3Var = new f3(uVar, e0Var);
            uVar.getView().setTag(R.id.wrapped_composition_tag, f3Var);
        }
        f3Var.f(dVar);
        if (!b4.l.a(uVar.getCoroutineContext(), b0Var.i())) {
            uVar.setCoroutineContext(b0Var.i());
        }
        return f3Var;
    }
}
