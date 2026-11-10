package r2;

import android.graphics.Insets;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f5696e = new c(0, 0, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5697a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5698b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5699c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5700d;

    public c(int i5, int i6, int i7, int i8) {
        this.f5697a = i5;
        this.f5698b = i6;
        this.f5699c = i7;
        this.f5700d = i8;
    }

    public static c a(c cVar, c cVar2) {
        return b(Math.max(cVar.f5697a, cVar2.f5697a), Math.max(cVar.f5698b, cVar2.f5698b), Math.max(cVar.f5699c, cVar2.f5699c), Math.max(cVar.f5700d, cVar2.f5700d));
    }

    public static c b(int i5, int i6, int i7, int i8) {
        return (i5 == 0 && i6 == 0 && i7 == 0 && i8 == 0) ? f5696e : new c(i5, i6, i7, i8);
    }

    public static c c(Insets insets) {
        return b(insets.left, insets.top, insets.right, insets.bottom);
    }

    public final Insets d() {
        return b.a(this.f5697a, this.f5698b, this.f5699c, this.f5700d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c.class != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        return this.f5700d == cVar.f5700d && this.f5697a == cVar.f5697a && this.f5699c == cVar.f5699c && this.f5698b == cVar.f5698b;
    }

    public final int hashCode() {
        return (((((this.f5697a * 31) + this.f5698b) * 31) + this.f5699c) * 31) + this.f5700d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Insets{left=");
        sb.append(this.f5697a);
        sb.append(", top=");
        sb.append(this.f5698b);
        sb.append(", right=");
        sb.append(this.f5699c);
        sb.append(", bottom=");
        return b.b.f(sb, this.f5700d, '}');
    }
}
