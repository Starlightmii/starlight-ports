package x0;

import p3.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f6526a = p.a(0.0f, 0.0f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f6527b = 0;

    public static final boolean a(long j5, long j6) {
        return j5 == j6;
    }

    public static final float b(long j5) {
        return Float.intBitsToFloat((int) (j5 >> 32));
    }

    public static final float c(long j5) {
        return Float.intBitsToFloat((int) (j5 & 4294967295L));
    }

    public static String d(long j5) {
        if (b(j5) == c(j5)) {
            return "CornerRadius.circular(" + q.f.v(b(j5)) + ')';
        }
        return "CornerRadius.elliptical(" + q.f.v(b(j5)) + ", " + q.f.v(c(j5)) + ')';
    }
}
