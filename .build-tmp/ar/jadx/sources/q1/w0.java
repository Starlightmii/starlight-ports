package q1;

import android.view.Choreographer;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w0 implements Choreographer.FrameCallback {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ i4.h f5440d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a4.c f5441e;

    public w0(i4.h hVar, f0.g gVar, a4.c cVar) {
        this.f5440d = hVar;
        this.f5441e = cVar;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j5) {
        Object objR;
        try {
            objR = this.f5441e.c(Long.valueOf(j5));
        } catch (Throwable th) {
            objR = p3.j.r(th);
        }
        this.f5440d.resumeWith(objR);
    }
}
