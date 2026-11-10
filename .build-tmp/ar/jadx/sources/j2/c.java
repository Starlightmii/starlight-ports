package j2;

import y0.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3308a;

    public c(long j5) {
        this.f3308a = j5;
        if (j5 == 16) {
            throw new IllegalArgumentException("ColorStyle value must be specified, use TextForegroundStyle.Unspecified instead.");
        }
    }

    @Override // j2.m
    public final float a() {
        return u.d(this.f3308a);
    }

    @Override // j2.m
    public final long b() {
        return this.f3308a;
    }

    @Override // j2.m
    public final y0.p c() {
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof c) && u.c(this.f3308a, ((c) obj).f3308a);
    }

    public final int hashCode() {
        int i5 = u.f6695h;
        return Long.hashCode(this.f3308a);
    }

    public final String toString() {
        return "ColorStyle(value=" + ((Object) u.i(this.f3308a)) + ')';
    }
}
