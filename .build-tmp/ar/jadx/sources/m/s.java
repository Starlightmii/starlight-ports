package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f3749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double f3750b;

    public s(double d5, double d6) {
        this.f3749a = d5;
        this.f3750b = d6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return Double.compare(this.f3749a, sVar.f3749a) == 0 && Double.compare(this.f3750b, sVar.f3750b) == 0;
    }

    public final int hashCode() {
        return Double.hashCode(this.f3750b) + (Double.hashCode(this.f3749a) * 31);
    }

    public final String toString() {
        return "ComplexDouble(_real=" + this.f3749a + ", _imaginary=" + this.f3750b + ')';
    }
}
