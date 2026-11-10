package p0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l implements a4.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4714d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a4.c f4715e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a4.c f4716f;

    public /* synthetic */ l(a4.c cVar, a4.c cVar2, int i5) {
        this.f4714d = i5;
        this.f4715e = cVar;
        this.f4716f = cVar2;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f4714d) {
            case 0:
                this.f4715e.c(obj);
                this.f4716f.c(obj);
                break;
            default:
                this.f4715e.c(obj);
                this.f4716f.c(obj);
                break;
        }
        return p3.n.f5026a;
    }
}
