package l4;

import i4.f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r implements f0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final t f3567d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f3568e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f3569f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final i4.h f3570g;

    public r(t tVar, long j5, Object obj, i4.h hVar) {
        this.f3567d = tVar;
        this.f3568e = j5;
        this.f3569f = obj;
        this.f3570g = hVar;
    }

    @Override // i4.f0
    public final void dispose() {
        t tVar = this.f3567d;
        synchronized (tVar) {
            if (this.f3568e < tVar.o()) {
                return;
            }
            Object[] objArr = tVar.f3581k;
            b4.l.b(objArr);
            long j5 = this.f3568e;
            if (objArr[((int) j5) & (objArr.length - 1)] != this) {
                return;
            }
            u.b(objArr, j5, u.f3586a);
            tVar.j();
        }
    }
}
