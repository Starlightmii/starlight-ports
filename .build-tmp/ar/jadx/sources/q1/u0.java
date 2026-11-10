package q1;

import android.view.Choreographer;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u0 implements Choreographer.FrameCallback, Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ v0 f5421d;

    public u0(v0 v0Var) {
        this.f5421d = v0Var;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j5) {
        this.f5421d.f5427g.removeCallbacks(this);
        v0.n(this.f5421d);
        v0 v0Var = this.f5421d;
        synchronized (v0Var.f5428h) {
            if (v0Var.f5433m) {
                v0Var.f5433m = false;
                ArrayList arrayList = v0Var.f5430j;
                v0Var.f5430j = v0Var.f5431k;
                v0Var.f5431k = arrayList;
                int size = arrayList.size();
                for (int i5 = 0; i5 < size; i5++) {
                    ((Choreographer.FrameCallback) arrayList.get(i5)).doFrame(j5);
                }
                arrayList.clear();
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        v0.n(this.f5421d);
        v0 v0Var = this.f5421d;
        synchronized (v0Var.f5428h) {
            if (v0Var.f5430j.isEmpty()) {
                v0Var.f5426f.removeFrameCallback(this);
                v0Var.f5433m = false;
            }
        }
    }
}
