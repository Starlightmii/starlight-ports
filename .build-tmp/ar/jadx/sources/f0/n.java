package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n extends IllegalStateException {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f2276d;

    public n(String str) {
        this.f2276d = str;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        return this.f2276d;
    }
}
