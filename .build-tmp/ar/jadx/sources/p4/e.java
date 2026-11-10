package p4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends h {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e f5060g;

    static {
        int i5 = k.f5068c;
        int i6 = k.f5069d;
        long j5 = k.f5070e;
        String str = k.f5066a;
        e eVar = new e();
        eVar.f5062f = new c(i5, i6, j5, str);
        f5060g = eVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // i4.r
    public final String toString() {
        return "Dispatchers.Default";
    }
}
