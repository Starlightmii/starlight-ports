package e1;

import androidx.lifecycle.k0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final k0 f1899j = new k0(16);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static int f1900k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1901a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1902b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f1903c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f1904d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f1905e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final z f1906f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f1907g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f1908h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f1909i;

    public e(String str, float f5, float f6, float f7, float f8, z zVar, long j5, int i5) {
        int i6;
        synchronized (f1899j) {
            i6 = f1900k;
            f1900k = i6 + 1;
        }
        this.f1901a = str;
        this.f1902b = f5;
        this.f1903c = f6;
        this.f1904d = f7;
        this.f1905e = f8;
        this.f1906f = zVar;
        this.f1907g = j5;
        this.f1908h = i5;
        this.f1909i = i6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return b4.l.a(this.f1901a, eVar.f1901a) && k2.e.a(this.f1902b, eVar.f1902b) && k2.e.a(this.f1903c, eVar.f1903c) && this.f1904d == eVar.f1904d && this.f1905e == eVar.f1905e && this.f1906f.equals(eVar.f1906f) && y0.u.c(this.f1907g, eVar.f1907g) && this.f1908h == eVar.f1908h;
    }

    public final int hashCode() {
        int iHashCode = (this.f1906f.hashCode() + b.b.a(this.f1905e, b.b.a(this.f1904d, b.b.a(this.f1903c, b.b.a(this.f1902b, this.f1901a.hashCode() * 31, 31), 31), 31), 31)) * 31;
        int i5 = y0.u.f6695h;
        return Boolean.hashCode(false) + m.h.b(this.f1908h, b.b.b(iHashCode, 31, this.f1907g), 31);
    }
}
