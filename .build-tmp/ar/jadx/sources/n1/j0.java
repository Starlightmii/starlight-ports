package n1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j0 extends r0.o implements p1.v {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public a4.c f4124q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f4125r;

    @Override // p1.v
    public final void m(long j5) {
        if (k2.j.a(this.f4125r, j5)) {
            return;
        }
        this.f4124q.c(new k2.j(j5));
        this.f4125r = j5;
    }

    @Override // r0.o
    public final boolean o0() {
        return true;
    }
}
