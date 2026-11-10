package k4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f3475a;

    public h(Throwable th) {
        this.f3475a = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof h) {
            return b4.l.a(this.f3475a, ((h) obj).f3475a);
        }
        return false;
    }

    public final int hashCode() {
        Throwable th = this.f3475a;
        if (th != null) {
            return th.hashCode();
        }
        return 0;
    }

    @Override // k4.i
    public final String toString() {
        return "Closed(" + this.f3475a + ')';
    }
}
