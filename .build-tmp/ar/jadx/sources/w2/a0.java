package w2;

import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowInsetsAnimation$Callback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 extends WindowInsetsAnimation$Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r.c0 f6368a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList f6369b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f6370c;

    public a0(r.c0 c0Var) {
        super(c0Var.f5537e);
        this.f6370c = new HashMap();
        this.f6368a = c0Var;
    }

    public final d0 a(WindowInsetsAnimation windowInsetsAnimation) {
        d0 d0Var = (d0) this.f6370c.get(windowInsetsAnimation);
        if (d0Var != null) {
            return d0Var;
        }
        d0 d0Var2 = new d0(0, null, 0L);
        d0Var2.f6385a = new b0(windowInsetsAnimation);
        this.f6370c.put(windowInsetsAnimation, d0Var2);
        return d0Var2;
    }

    public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
        this.f6368a.b(a(windowInsetsAnimation));
        this.f6370c.remove(windowInsetsAnimation);
    }

    public final void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
        a(windowInsetsAnimation);
        r.c0 c0Var = this.f6368a;
        c0Var.f5539g = true;
        c0Var.f5540h = true;
    }

    public final WindowInsets onProgress(WindowInsets windowInsets, List list) {
        ArrayList arrayList = this.f6369b;
        if (arrayList == null) {
            ArrayList arrayList2 = new ArrayList(list.size());
            this.f6369b = arrayList2;
            Collections.unmodifiableList(arrayList2);
        } else {
            arrayList.clear();
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            WindowInsetsAnimation windowInsetsAnimationI = b1.j.i(list.get(size));
            d0 d0VarA = a(windowInsetsAnimationI);
            d0VarA.f6385a.c(windowInsetsAnimationI.getFraction());
            this.f6369b.add(d0VarA);
        }
        z0 z0VarB = z0.b(null, windowInsets);
        r.a1 a1Var = this.f6368a.f5538f;
        r.a1.a(a1Var, z0VarB);
        if (a1Var.f5524r) {
            z0VarB = z0.f6455b;
        }
        return z0VarB.a();
    }

    public final WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
        a(windowInsetsAnimation);
        r2.c cVarC = r2.c.c(bounds.getLowerBound());
        r2.c cVarC2 = r2.c.c(bounds.getUpperBound());
        this.f6368a.f5539g = false;
        b1.j.l();
        return b1.j.g(cVarC.d(), cVarC2.d());
    }
}
