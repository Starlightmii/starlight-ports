package x0;

import p3.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f6538a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f6539b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f6540c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f6541d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f6542e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f6543f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f6544g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f6545h;

    static {
        long j5 = a.f6526a;
        p.a(a.b(j5), a.c(j5));
    }

    public e(float f5, float f6, float f7, float f8, long j5, long j6, long j7, long j8) {
        this.f6538a = f5;
        this.f6539b = f6;
        this.f6540c = f7;
        this.f6541d = f8;
        this.f6542e = j5;
        this.f6543f = j6;
        this.f6544g = j7;
        this.f6545h = j8;
    }

    public final float a() {
        return this.f6541d - this.f6539b;
    }

    public final float b() {
        return this.f6540c - this.f6538a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return Float.compare(this.f6538a, eVar.f6538a) == 0 && Float.compare(this.f6539b, eVar.f6539b) == 0 && Float.compare(this.f6540c, eVar.f6540c) == 0 && Float.compare(this.f6541d, eVar.f6541d) == 0 && a.a(this.f6542e, eVar.f6542e) && a.a(this.f6543f, eVar.f6543f) && a.a(this.f6544g, eVar.f6544g) && a.a(this.f6545h, eVar.f6545h);
    }

    public final int hashCode() {
        int iA = b.b.a(this.f6541d, b.b.a(this.f6540c, b.b.a(this.f6539b, Float.hashCode(this.f6538a) * 31, 31), 31), 31);
        int i5 = a.f6527b;
        return Long.hashCode(this.f6545h) + b.b.b(b.b.b(b.b.b(iA, 31, this.f6542e), 31, this.f6543f), 31, this.f6544g);
    }

    public final String toString() {
        String str = q.f.v(this.f6538a) + ", " + q.f.v(this.f6539b) + ", " + q.f.v(this.f6540c) + ", " + q.f.v(this.f6541d);
        long j5 = this.f6542e;
        long j6 = this.f6543f;
        boolean zA = a.a(j5, j6);
        long j7 = this.f6544g;
        long j8 = this.f6545h;
        if (!zA || !a.a(j6, j7) || !a.a(j7, j8)) {
            return "RoundRect(rect=" + str + ", topLeft=" + ((Object) a.d(j5)) + ", topRight=" + ((Object) a.d(j6)) + ", bottomRight=" + ((Object) a.d(j7)) + ", bottomLeft=" + ((Object) a.d(j8)) + ')';
        }
        if (a.b(j5) == a.c(j5)) {
            return "RoundRect(rect=" + str + ", radius=" + q.f.v(a.b(j5)) + ')';
        }
        return "RoundRect(rect=" + str + ", x=" + q.f.v(a.b(j5)) + ", y=" + q.f.v(a.c(j5)) + ')';
    }
}
