package j1;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3290a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f3291b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f3292c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f3293d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f3294e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f3295f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3296g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f3297h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f3298i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f3299j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final long f3300k;

    public w(long j5, long j6, long j7, long j8, boolean z2, float f5, int i5, boolean z3, ArrayList arrayList, long j9, long j10) {
        this.f3290a = j5;
        this.f3291b = j6;
        this.f3292c = j7;
        this.f3293d = j8;
        this.f3294e = z2;
        this.f3295f = f5;
        this.f3296g = i5;
        this.f3297h = z3;
        this.f3298i = arrayList;
        this.f3299j = j9;
        this.f3300k = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w)) {
            return false;
        }
        w wVar = (w) obj;
        return t.a(this.f3290a, wVar.f3290a) && this.f3291b == wVar.f3291b && x0.c.b(this.f3292c, wVar.f3292c) && x0.c.b(this.f3293d, wVar.f3293d) && this.f3294e == wVar.f3294e && Float.compare(this.f3295f, wVar.f3295f) == 0 && this.f3296g == wVar.f3296g && this.f3297h == wVar.f3297h && this.f3298i.equals(wVar.f3298i) && x0.c.b(this.f3299j, wVar.f3299j) && x0.c.b(this.f3300k, wVar.f3300k);
    }

    public final int hashCode() {
        return Long.hashCode(this.f3300k) + b.b.b((this.f3298i.hashCode() + b.b.c(m.h.b(this.f3296g, b.b.a(this.f3295f, b.b.c(b.b.b(b.b.b(b.b.b(Long.hashCode(this.f3290a) * 31, 31, this.f3291b), 31, this.f3292c), 31, this.f3293d), 31, this.f3294e), 31), 31), 31, this.f3297h)) * 31, 31, this.f3299j);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("PointerInputEventData(id=");
        sb.append((Object) t.b(this.f3290a));
        sb.append(", uptime=");
        sb.append(this.f3291b);
        sb.append(", positionOnScreen=");
        sb.append((Object) x0.c.j(this.f3292c));
        sb.append(", position=");
        sb.append((Object) x0.c.j(this.f3293d));
        sb.append(", down=");
        sb.append(this.f3294e);
        sb.append(", pressure=");
        sb.append(this.f3295f);
        sb.append(", type=");
        int i5 = this.f3296g;
        if (i5 == 1) {
            str = "Touch";
        } else if (i5 == 2) {
            str = "Mouse";
        } else if (i5 != 3) {
            str = i5 != 4 ? "Unknown" : "Eraser";
        } else {
            str = "Stylus";
        }
        sb.append((Object) str);
        sb.append(", activeHover=");
        sb.append(this.f3297h);
        sb.append(", historical=");
        sb.append(this.f3298i);
        sb.append(", scrollDelta=");
        sb.append((Object) x0.c.j(this.f3299j));
        sb.append(", originalEventPosition=");
        sb.append((Object) x0.c.j(this.f3300k));
        sb.append(')');
        return sb.toString();
    }
}
