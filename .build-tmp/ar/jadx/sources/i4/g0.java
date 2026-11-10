package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g0 implements o0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f2731d;

    public g0(boolean z2) {
        this.f2731d = z2;
    }

    @Override // i4.o0
    public final boolean a() {
        return this.f2731d;
    }

    @Override // i4.o0
    public final c1 c() {
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Empty{");
        sb.append(this.f2731d ? "Active" : "New");
        sb.append('}');
        return sb.toString();
    }
}
