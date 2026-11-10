package w1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e f6277b = new e(new e4.a());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e4.a f6278a;

    public e(e4.a aVar) {
        this.f6278a = aVar;
        if (Float.isNaN(0.0f)) {
            throw new IllegalArgumentException("current must not be NaN");
        }
    }

    public final e4.a a() {
        return this.f6278a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof e) && this.f6278a.equals(((e) obj).f6278a);
    }

    public final int hashCode() {
        return (this.f6278a.hashCode() + (Float.hashCode(0.0f) * 31)) * 31;
    }

    public final String toString() {
        return "ProgressBarRangeInfo(current=0.0, range=" + this.f6278a + ", steps=0)";
    }
}
