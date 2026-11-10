package b;

import android.os.Build;
import android.view.View;
import android.view.Window;
import w2.a1;
import w2.b1;
import w2.c1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class w extends v {
    @Override // b.t
    public void b(l0 l0Var, l0 l0Var2, Window window, View view, boolean z2, boolean z3) {
        p3.j b1Var;
        b4.l.e("statusBarStyle", l0Var);
        b4.l.e("navigationBarStyle", l0Var2);
        b4.l.e("window", window);
        b4.l.e("view", view);
        p3.p.C(window);
        window.setStatusBarColor(0);
        window.setNavigationBarColor(0);
        window.setStatusBarContrastEnforced(false);
        window.setNavigationBarContrastEnforced(true);
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 35) {
            b1Var = new c1(window);
        } else {
            b1Var = i5 >= 30 ? new b1(window) : new a1(window);
        }
        b1Var.M(!z2);
        b1Var.L(!z3);
    }
}
