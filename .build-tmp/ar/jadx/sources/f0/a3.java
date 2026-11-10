package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a3 extends p0.z {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f2090c;

    public a3(long j5, Object obj) {
        super(j5);
        this.f2090c = obj;
    }

    @Override // p0.z
    public final void a(p0.z zVar) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableStateImpl.StateStateRecord<T of androidx.compose.runtime.SnapshotMutableStateImpl.StateStateRecord>", zVar);
        this.f2090c = ((a3) zVar).f2090c;
    }

    @Override // p0.z
    public final p0.z b(long j5) {
        return new a3(p0.m.j().g(), this.f2090c);
    }
}
