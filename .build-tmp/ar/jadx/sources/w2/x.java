package w2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d0 f6447a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f6448b;

    public x(View view, d0 d0Var) {
        this.f6447a = d0Var;
        this.f6448b = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        d0 d0Var = this.f6447a;
        d0Var.f6385a.c(1.0f);
        z.d(this.f6448b, d0Var);
    }
}
