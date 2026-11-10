package io.github.andrea_lyz.assistrestore.ui;

import android.R;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import q1.i1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class MainActivity extends b.r {
    public static final int $stable = 8;

    @Override // b.r, p2.b, android.app.Activity
    public void onCreate(Bundle bundle) {
        b.t vVar;
        int i5 = b.s.f364a;
        b.k0 k0Var = b.k0.f348f;
        b.l0 l0Var = new b.l0(0, 0, k0Var);
        b.l0 l0Var2 = new b.l0(b.s.f364a, b.s.f365b, k0Var);
        View decorView = getWindow().getDecorView();
        b4.l.d("window.decorView", decorView);
        Resources resources = decorView.getResources();
        b4.l.d("view.resources", resources);
        boolean zBooleanValue = ((Boolean) k0Var.c(resources)).booleanValue();
        Resources resources2 = decorView.getResources();
        b4.l.d("view.resources", resources2);
        boolean zBooleanValue2 = ((Boolean) k0Var.c(resources2)).booleanValue();
        int i6 = Build.VERSION.SDK_INT;
        if (i6 >= 30) {
            vVar = new b.x();
        } else if (i6 >= 29) {
            vVar = new b.w();
        } else {
            vVar = i6 >= 28 ? new b.v() : new b.t();
        }
        b.t tVar = vVar;
        Window window = getWindow();
        b4.l.d("window", window);
        tVar.b(l0Var, l0Var2, window, decorView, zBooleanValue, zBooleanValue2);
        Window window2 = getWindow();
        b4.l.d("window", window2);
        tVar.a(window2);
        super.onCreate(bundle);
        a4.e lambda$1706964085$app_release = ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1706964085$app_release();
        ViewGroup.LayoutParams layoutParams = c.e.f710a;
        View childAt = ((ViewGroup) getWindow().getDecorView().findViewById(R.id.content)).getChildAt(0);
        i1 i1Var = childAt instanceof i1 ? (i1) childAt : null;
        if (i1Var != null) {
            i1Var.setParentCompositionContext(null);
            i1Var.setContent(lambda$1706964085$app_release);
            return;
        }
        i1 i1Var2 = new i1(this);
        i1Var2.setParentCompositionContext(null);
        i1Var2.setContent(lambda$1706964085$app_release);
        View decorView2 = getWindow().getDecorView();
        if (androidx.lifecycle.l0.c(decorView2) == null) {
            decorView2.setTag(io.github.andrea_lyz.assistrestore.R.id.view_tree_lifecycle_owner, this);
        }
        if (androidx.lifecycle.l0.d(decorView2) == null) {
            decorView2.setTag(io.github.andrea_lyz.assistrestore.R.id.view_tree_view_model_store_owner, this);
        }
        if (d4.a.t(decorView2) == null) {
            decorView2.setTag(io.github.andrea_lyz.assistrestore.R.id.view_tree_saved_state_registry_owner, this);
        }
        setContentView(i1Var2, c.e.f710a);
    }
}
