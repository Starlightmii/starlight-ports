package w2;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class q {
    public static z0 a(View view) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets == null) {
            return null;
        }
        z0 z0VarB = z0.b(null, rootWindowInsets);
        v0 v0Var = z0VarB.f6456a;
        v0Var.v(z0VarB);
        View rootView = view.getRootView();
        v0Var.d(rootView);
        v0Var.o(rootView);
        v0Var.p();
        return z0VarB;
    }
}
