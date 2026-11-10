package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i0 implements n2 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i4.v f2194d;

    public i0(i4.v vVar) {
        this.f2194d = vVar;
    }

    @Override // f0.n2
    public final void d() {
    }

    @Override // f0.n2
    public final void e() {
        i4.v vVar = this.f2194d;
        if (vVar instanceof q2) {
            ((q2) vVar).a();
        } else {
            i4.y.b(vVar, new s0(1));
        }
    }

    @Override // f0.n2
    public final void l() {
        i4.v vVar = this.f2194d;
        if (vVar instanceof q2) {
            ((q2) vVar).a();
        } else {
            i4.y.b(vVar, new s0(1));
        }
    }
}
