package o;

import y0.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f4314a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f4315b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f4316c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f4317d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f4318e;

    public a(long j5, long j6, long j7, long j8, long j9) {
        this.f4314a = j5;
        this.f4315b = j6;
        this.f4316c = j7;
        this.f4317d = j8;
        this.f4318e = j9;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return u.c(this.f4314a, aVar.f4314a) && u.c(this.f4315b, aVar.f4315b) && u.c(this.f4316c, aVar.f4316c) && u.c(this.f4317d, aVar.f4317d) && u.c(this.f4318e, aVar.f4318e);
    }

    public final int hashCode() {
        int i5 = u.f6695h;
        return Long.hashCode(this.f4318e) + b.b.b(b.b.b(b.b.b(Long.hashCode(this.f4314a) * 31, 31, this.f4315b), 31, this.f4316c), 31, this.f4317d);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ContextMenuColors(backgroundColor=");
        b.b.j(this.f4314a, sb, ", textColor=");
        b.b.j(this.f4315b, sb, ", iconColor=");
        b.b.j(this.f4316c, sb, ", disabledTextColor=");
        b.b.j(this.f4317d, sb, ", disabledIconColor=");
        sb.append((Object) u.i(this.f4318e));
        sb.append(')');
        return sb.toString();
    }
}
