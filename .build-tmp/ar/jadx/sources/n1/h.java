package n1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4117a;

    public /* synthetic */ h(int i5) {
        this.f4117a = i5;
    }

    public long a(long j5, long j6) {
        float fMin = Math.min(x0.f.d(j6) / x0.f.d(j5), x0.f.b(j6) / x0.f.b(j5));
        return v0.a(fMin, fMin);
    }

    public String toString() {
        switch (this.f4117a) {
            case 2:
                return "ReusedSlotId";
            default:
                return super.toString();
        }
    }
}
