package e2;

import android.view.Choreographer;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b0 implements Executor {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Choreographer f1989d;

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.f1989d.postFrameCallback(new c0(runnable));
    }
}
