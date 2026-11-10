package p0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w extends z {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j0.c f4763c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4764d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4765e;

    public w(long j5, j0.c cVar) {
        super(j5);
        this.f4763c = cVar;
    }

    @Override // p0.z
    public final void a(z zVar) {
        synchronized (r.f4731a) {
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.StateListStateRecord>", zVar);
            this.f4763c = ((w) zVar).f4763c;
            this.f4764d = ((w) zVar).f4764d;
            this.f4765e = ((w) zVar).f4765e;
        }
    }

    @Override // p0.z
    public final z b(long j5) {
        return new w(j5, this.f4763c);
    }
}
