package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z2 extends p0.z {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f2442c;

    public z2(long j5, long j6) {
        super(j5);
        this.f2442c = j6;
    }

    @Override // p0.z
    public final void a(p0.z zVar) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableLongStateImpl.LongStateStateRecord", zVar);
        this.f2442c = ((z2) zVar).f2442c;
    }

    @Override // p0.z
    public final p0.z b(long j5) {
        return new z2(j5, this.f2442c);
    }
}
