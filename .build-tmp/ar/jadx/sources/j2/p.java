package j2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final p f3334c = new p(2, false);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final p f3335d = new p(1, true);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3336a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f3337b;

    public p(int i5, boolean z2) {
        this.f3336a = i5;
        this.f3337b = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return this.f3336a == pVar.f3336a && this.f3337b == pVar.f3337b;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f3337b) + (Integer.hashCode(this.f3336a) * 31);
    }

    public final String toString() {
        if (equals(f3334c)) {
            return "TextMotion.Static";
        }
        return equals(f3335d) ? "TextMotion.Animated" : "Invalid";
    }
}
