package i3;

import android.content.Context;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2701d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Context f2702e;

    public /* synthetic */ g(Context context, int i5) {
        this.f2701d = i5;
        this.f2702e = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2701d) {
            case 0:
                new ThreadPoolExecutor(0, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()).execute(new g(this.f2702e, 1));
                break;
            default:
                f.t(this.f2702e, new d(), f.f2691a, false);
                break;
        }
    }
}
