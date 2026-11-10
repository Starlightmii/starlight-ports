package b;

import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import io.github.andrea_lyz.assistrestore.ui.MainActivity;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n implements m, ViewTreeObserver.OnDrawListener, Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f355d = SystemClock.uptimeMillis() + ((long) 10000);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Runnable f356e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f357f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ MainActivity f358g;

    public n(MainActivity mainActivity) {
        this.f358g = mainActivity;
    }

    public final void a(View view) {
        if (this.f357f) {
            return;
        }
        this.f357f = true;
        view.getViewTreeObserver().addOnDrawListener(this);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        b4.l.e("runnable", runnable);
        this.f356e = runnable;
        View decorView = this.f358g.getWindow().getDecorView();
        b4.l.d("window.decorView", decorView);
        if (!this.f357f) {
            decorView.postOnAnimation(new androidx.lifecycle.a0(1, this));
        } else if (b4.l.a(Looper.myLooper(), Looper.getMainLooper())) {
            decorView.invalidate();
        } else {
            decorView.postInvalidate();
        }
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        boolean z2;
        Runnable runnable = this.f356e;
        if (runnable == null) {
            if (SystemClock.uptimeMillis() > this.f355d) {
                this.f357f = false;
                this.f358g.getWindow().getDecorView().post(this);
                return;
            }
            return;
        }
        runnable.run();
        this.f356e = null;
        y fullyDrawnReporter = this.f358g.getFullyDrawnReporter();
        synchronized (fullyDrawnReporter.f366a) {
            z2 = fullyDrawnReporter.f367b;
        }
        if (z2) {
            this.f357f = false;
            this.f358g.getWindow().getDecorView().post(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f358g.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
    }
}
