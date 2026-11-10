package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f6768b = p3.j.g(0, 0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f6769c = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6770a;

    public /* synthetic */ g0(long j5) {
        this.f6770a = j5;
    }

    public static final boolean a(long j5, long j6) {
        return j5 == j6;
    }

    public static final boolean b(long j5) {
        return ((int) (j5 >> 32)) == ((int) (j5 & 4294967295L));
    }

    public static final int c(long j5) {
        return d(j5) - e(j5);
    }

    public static final int d(long j5) {
        int i5 = (int) (j5 >> 32);
        int i6 = (int) (j5 & 4294967295L);
        return i5 > i6 ? i5 : i6;
    }

    public static final int e(long j5) {
        int i5 = (int) (j5 >> 32);
        int i6 = (int) (j5 & 4294967295L);
        return i5 > i6 ? i6 : i5;
    }

    public static final boolean f(long j5) {
        return ((int) (j5 >> 32)) > ((int) (j5 & 4294967295L));
    }

    public static String g(long j5) {
        StringBuilder sb = new StringBuilder("TextRange(");
        sb.append((int) (j5 >> 32));
        sb.append(", ");
        return b.b.f(sb, (int) (j5 & 4294967295L), ')');
    }

    public final boolean equals(Object obj) {
        if (obj instanceof g0) {
            return this.f6770a == ((g0) obj).f6770a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f6770a);
    }

    public final String toString() {
        return g(this.f6770a);
    }
}
