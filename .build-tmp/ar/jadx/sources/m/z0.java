package m;

import f0.l1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z0 implements x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3807a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3808b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v f3809c;

    public z0(int i5, int i6, v vVar) {
        this.f3807a = i5;
        this.f3808b = i6;
        this.f3809c = vVar;
    }

    public z0(int i5, v vVar, int i6) {
        this(i5, 0, (i6 & 4) != 0 ? w.f3797a : vVar);
    }

    @Override // m.k
    public final c1 a(a1 a1Var) {
        return new l1(this.f3807a, this.f3808b, this.f3809c);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof z0) {
            z0 z0Var = (z0) obj;
            if (z0Var.f3807a == this.f3807a && z0Var.f3808b == this.f3808b && b4.l.a(z0Var.f3809c, this.f3809c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.f3809c.hashCode() + (this.f3807a * 31)) * 31) + this.f3808b;
    }
}
