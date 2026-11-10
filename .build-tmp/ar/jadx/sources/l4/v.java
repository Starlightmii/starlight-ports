package l4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v extends m4.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3589a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i4.h f3590b;

    @Override // m4.d
    public final boolean a(m4.b bVar) {
        t tVar = (t) bVar;
        if (this.f3589a >= 0) {
            return false;
        }
        long j5 = tVar.f3582l;
        if (j5 < tVar.f3583m) {
            tVar.f3583m = j5;
        }
        this.f3589a = j5;
        return true;
    }

    @Override // m4.d
    public final t3.c[] b(m4.b bVar) {
        long j5 = this.f3589a;
        this.f3589a = -1L;
        this.f3590b = null;
        return ((t) bVar).v(j5);
    }
}
