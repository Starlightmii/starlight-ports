package b3;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f630d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f631e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f632f = -1;

    public q(int i5) {
        this.f630d = i5;
    }

    @Override // b3.p
    public final Object a() {
        return this;
    }

    @Override // b3.p
    public final boolean j(CharSequence charSequence, int i5, int i6, x xVar) {
        int i7 = this.f630d;
        if (i5 > i7 || i7 >= i6) {
            return i6 <= i7;
        }
        this.f631e = i5;
        this.f632f = i6;
        return false;
    }
}
