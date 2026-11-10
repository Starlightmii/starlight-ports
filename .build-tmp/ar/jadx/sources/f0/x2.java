package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x2 extends p0.z {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f2419c;

    public x2(float f5, long j5) {
        super(j5);
        this.f2419c = f5;
    }

    @Override // p0.z
    public final void a(p0.z zVar) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableFloatStateImpl.FloatStateStateRecord", zVar);
        this.f2419c = ((x2) zVar).f2419c;
    }

    @Override // p0.z
    public final p0.z b(long j5) {
        return new x2(this.f2419c, j5);
    }
}
