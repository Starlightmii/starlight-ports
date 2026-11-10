package z0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f7109a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f7110b;

    public s(float f5, float f6) {
        this.f7109a = f5;
        this.f7110b = f6;
    }

    public final float[] a() {
        float f5 = this.f7109a;
        float f6 = this.f7110b;
        return new float[]{f5 / f6, 1.0f, ((1.0f - f5) - f6) / f6};
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return Float.compare(this.f7109a, sVar.f7109a) == 0 && Float.compare(this.f7110b, sVar.f7110b) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f7110b) + (Float.hashCode(this.f7109a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("WhitePoint(x=");
        sb.append(this.f7109a);
        sb.append(", y=");
        return b.b.e(sb, this.f7110b, ')');
    }
}
