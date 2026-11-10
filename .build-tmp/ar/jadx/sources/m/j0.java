package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j0 implements c1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c1 f3687d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f3688e;

    public j0(c1 c1Var, long j5) {
        this.f3687d = c1Var;
        this.f3688e = j5;
    }

    @Override // m.c1
    public final boolean a() {
        return this.f3687d.a();
    }

    @Override // m.c1
    public final long b(q qVar, q qVar2, q qVar3) {
        return this.f3687d.b(qVar, qVar2, qVar3) + this.f3688e;
    }

    @Override // m.c1
    public final q d(long j5, q qVar, q qVar2, q qVar3) {
        long j6 = this.f3688e;
        return j5 < j6 ? qVar3 : this.f3687d.d(j5 - j6, qVar, qVar2, qVar3);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof j0)) {
            return false;
        }
        j0 j0Var = (j0) obj;
        return j0Var.f3688e == this.f3688e && b4.l.a(j0Var.f3687d, this.f3687d);
    }

    @Override // m.c1
    public final q f(long j5, q qVar, q qVar2, q qVar3) {
        long j6 = this.f3688e;
        return j5 < j6 ? qVar : this.f3687d.f(j5 - j6, qVar, qVar2, qVar3);
    }

    public final int hashCode() {
        return Long.hashCode(this.f3688e) + (this.f3687d.hashCode() * 31);
    }
}
