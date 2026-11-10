package p3;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Throwable f5016d;

    public h(Throwable th) {
        b4.l.e("exception", th);
        this.f5016d = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof h) {
            return b4.l.a(this.f5016d, ((h) obj).f5016d);
        }
        return false;
    }

    public final int hashCode() {
        return this.f5016d.hashCode();
    }

    public final String toString() {
        return "Failure(" + this.f5016d + ')';
    }
}
