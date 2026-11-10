package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h0 extends i0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final h f2737f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ k0 f2738g;

    public h0(k0 k0Var, long j5, h hVar) {
        this.f2738g = k0Var;
        this.f2740d = j5;
        this.f2741e = -1;
        this.f2737f = hVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f2737f.C(this.f2738g);
    }

    @Override // i4.i0
    public final String toString() {
        return super.toString() + this.f2737f;
    }
}
