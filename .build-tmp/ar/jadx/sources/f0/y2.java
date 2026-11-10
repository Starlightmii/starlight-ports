package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y2 extends p0.z {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2428c;

    public y2(long j5, int i5) {
        super(j5);
        this.f2428c = i5;
    }

    @Override // p0.z
    public final void a(p0.z zVar) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableIntStateImpl.IntStateStateRecord", zVar);
        this.f2428c = ((y2) zVar).f2428c;
    }

    @Override // p0.z
    public final p0.z b(long j5) {
        return new y2(j5, this.f2428c);
    }
}
