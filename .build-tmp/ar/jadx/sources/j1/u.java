package j1;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3274a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f3275b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f3276c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f3277d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f3278e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f3279f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f3280g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f3281h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f3282i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f3283j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ArrayList f3284k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f3285l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public c f3286m;

    public u(long j5, long j6, long j7, boolean z2, float f5, long j8, long j9, boolean z3, int i5, ArrayList arrayList, long j10, long j11) {
        this(j5, j6, j7, z2, f5, j8, j9, z3, false, i5, j10);
        this.f3284k = arrayList;
        this.f3285l = j11;
    }

    public u(long j5, long j6, long j7, boolean z2, float f5, long j8, long j9, boolean z3, boolean z4, int i5, long j10) {
        this.f3274a = j5;
        this.f3275b = j6;
        this.f3276c = j7;
        this.f3277d = z2;
        this.f3278e = f5;
        this.f3279f = j8;
        this.f3280g = j9;
        this.f3281h = z3;
        this.f3282i = i5;
        this.f3283j = j10;
        this.f3285l = 0L;
        c cVar = new c();
        cVar.f3222a = z4;
        cVar.f3223b = z4;
        this.f3286m = cVar;
    }

    public final void a() {
        c cVar = this.f3286m;
        cVar.f3223b = true;
        cVar.f3222a = true;
    }

    public final boolean b() {
        c cVar = this.f3286m;
        return cVar.f3223b || cVar.f3222a;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("PointerInputChange(id=");
        sb.append((Object) t.b(this.f3274a));
        sb.append(", uptimeMillis=");
        sb.append(this.f3275b);
        sb.append(", position=");
        sb.append((Object) x0.c.j(this.f3276c));
        sb.append(", pressed=");
        sb.append(this.f3277d);
        sb.append(", pressure=");
        sb.append(this.f3278e);
        sb.append(", previousUptimeMillis=");
        sb.append(this.f3279f);
        sb.append(", previousPosition=");
        sb.append((Object) x0.c.j(this.f3280g));
        sb.append(", previousPressed=");
        sb.append(this.f3281h);
        sb.append(", isConsumed=");
        sb.append(b());
        sb.append(", type=");
        int i5 = this.f3282i;
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
        sb.append(", historical=");
        Object obj = this.f3284k;
        if (obj == null) {
            obj = q3.s.f5477d;
        }
        sb.append(obj);
        sb.append(",scrollDelta=");
        sb.append((Object) x0.c.j(this.f3283j));
        sb.append(')');
        return sb.toString();
    }
}
