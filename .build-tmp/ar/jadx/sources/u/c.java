package u;

import x0.f;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5811a;

    public c(float f5) {
        this.f5811a = f5;
        if (f5 < 0.0f || f5 > 100.0f) {
            throw new IllegalArgumentException("The percent should be in the range of [0, 100]");
        }
    }

    @Override // u.a
    public final float a(long j5, k2.b bVar) {
        return (this.f5811a / 100.0f) * f.c(j5);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof c) && Float.compare(this.f5811a, ((c) obj).f5811a) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f5811a);
    }

    public final String toString() {
        return "CornerSize(size = " + this.f5811a + "%)";
    }
}
