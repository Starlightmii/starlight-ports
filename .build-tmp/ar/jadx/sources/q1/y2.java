package q1;

import android.view.View;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y2 implements View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ View f5462d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ f0.l2 f5463e;

    public y2(View view, f0.l2 l2Var) {
        this.f5462d = view;
        this.f5463e = l2Var;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.f5462d.removeOnAttachStateChangeListener(this);
        this.f5463e.v();
    }
}
