package w2;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final View f6398d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ViewTreeObserver f6399e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final j4.c f6400f;

    public k(View view, j4.c cVar) {
        this.f6398d = view;
        this.f6399e = view.getViewTreeObserver();
        this.f6400f = cVar;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        boolean zIsAlive = this.f6399e.isAlive();
        View view = this.f6398d;
        if (zIsAlive) {
            this.f6399e.removeOnPreDrawListener(this);
        } else {
            view.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view.removeOnAttachStateChangeListener(this);
        this.f6400f.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.f6399e = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        boolean zIsAlive = this.f6399e.isAlive();
        View view2 = this.f6398d;
        if (zIsAlive) {
            this.f6399e.removeOnPreDrawListener(this);
        } else {
            view2.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view2.removeOnAttachStateChangeListener(this);
    }
}
