package o;

import n2.s;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f4323a;

    public d(long j5) {
        this.f4323a = j5;
    }

    @Override // n2.s
    public final long a(k2.i iVar, long j5, k2.k kVar, long j6) {
        int i5 = iVar.f3411a;
        long j7 = this.f4323a;
        return h1.c.d(h1.c.k(i5 + ((int) (j7 >> 32)), (int) (j6 >> 32), (int) (j5 >> 32), kVar == k2.k.f3416d), h1.c.k(iVar.f3412b + ((int) (j7 & 4294967295L)), (int) (j6 & 4294967295L), (int) (j5 & 4294967295L), true));
    }
}
