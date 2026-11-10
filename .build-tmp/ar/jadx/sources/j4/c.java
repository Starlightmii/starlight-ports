package j4;

import a2.h;
import android.animation.ValueAnimator;
import android.view.View;
import i4.r;
import i4.y;
import n4.g;
import t3.i;
import w2.d0;
import w2.z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3345d = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f3346e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f3347f;

    public c(View view, d0 d0Var, h hVar, ValueAnimator valueAnimator) {
        this.f3346e = view;
        this.f3347f = valueAnimator;
    }

    public c(i4.h hVar, d dVar) {
        this.f3346e = hVar;
        this.f3347f = dVar;
    }

    public c(g gVar, Runnable runnable) {
        this.f3347f = gVar;
        this.f3346e = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3345d) {
            case 0:
                ((i4.h) this.f3346e).C((d) this.f3347f);
                break;
            case 1:
                g gVar = (g) this.f3347f;
                r rVar = gVar.f4286g;
                int i5 = 0;
                while (true) {
                    try {
                        ((Runnable) this.f3346e).run();
                    } catch (Throwable th) {
                        y.j(th, i.f5807d);
                    }
                    Runnable runnableN = gVar.n();
                    if (runnableN == null) {
                        break;
                    } else {
                        this.f3346e = runnableN;
                        i5++;
                        if (i5 >= 16 && rVar.h(gVar)) {
                            rVar.e(gVar, this);
                            break;
                        }
                    }
                    break;
                }
                break;
            default:
                z.g((View) this.f3346e);
                ((ValueAnimator) this.f3347f).start();
                break;
        }
    }
}
