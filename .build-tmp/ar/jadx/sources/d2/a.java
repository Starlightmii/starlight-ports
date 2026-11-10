package d2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a implements o {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1602d;

    public a(int i5) {
        this.f1602d = i5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f1602d == ((a) obj).f1602d;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f1602d);
    }

    public final String toString() {
        return b.b.f(new StringBuilder("AndroidFontResolveInterceptor(fontWeightAdjustment="), this.f1602d, ')');
    }
}
