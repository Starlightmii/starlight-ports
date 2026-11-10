package m4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t implements t3.c, v3.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final t3.c f3855d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final t3.h f3856e;

    public t(t3.c cVar, t3.h hVar) {
        this.f3855d = cVar;
        this.f3856e = hVar;
    }

    @Override // v3.d
    public final v3.d getCallerFrame() {
        t3.c cVar = this.f3855d;
        if (cVar instanceof v3.d) {
            return (v3.d) cVar;
        }
        return null;
    }

    @Override // t3.c
    public final t3.h getContext() {
        return this.f3856e;
    }

    @Override // t3.c
    public final void resumeWith(Object obj) {
        this.f3855d.resumeWith(obj);
    }
}
