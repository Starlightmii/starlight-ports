package e2;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import java.util.Random;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c0 implements Choreographer.FrameCallback {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1990d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f1991e;

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j5) {
        switch (this.f1990d) {
            case 0:
                ((Runnable) this.f1991e).run();
                break;
            default:
                (Build.VERSION.SDK_INT >= 28 ? Handler.createAsync(Looper.getMainLooper()) : new Handler(Looper.getMainLooper())).postDelayed(new i3.g((Context) this.f1991e, 0), new Random().nextInt(Math.max(1000, 1)) + 5000);
                break;
        }
    }
}
