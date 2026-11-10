package o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f4339a;

    public h(long j5) {
        this.f4339a = j5;
        if (!p3.j.E(j5)) {
            throw new IllegalStateException("ContextMenuState.Status should never be open with an unspecified offset. Use ContextMenuState.Status.Closed instead.");
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        return x0.c.b(this.f4339a, ((h) obj).f4339a);
    }

    public final int hashCode() {
        return Long.hashCode(this.f4339a);
    }

    public final String toString() {
        return "Open(offset=" + ((Object) x0.c.j(this.f4339a)) + ')';
    }
}
