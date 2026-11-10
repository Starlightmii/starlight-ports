package w2;

import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6382a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6383b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f6384c;

    public d(int i5, int i6, String str) {
        this.f6382a = str;
        this.f6383b = i5;
        this.f6384c = i6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return Objects.equals(this.f6382a, dVar.f6382a) && this.f6383b == dVar.f6383b && this.f6384c == dVar.f6384c;
    }

    public final int hashCode() {
        Integer numValueOf = Integer.valueOf(this.f6383b);
        Integer numValueOf2 = Integer.valueOf(this.f6384c);
        Float fValueOf = Float.valueOf(1.0f);
        return Objects.hash(this.f6382a, numValueOf, numValueOf2, fValueOf, 0, 0, 0, fValueOf);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DisplayShapeCompat{ spec=");
        String str = this.f6382a;
        sb.append(str != null ? Integer.valueOf(str.hashCode()) : "null");
        sb.append(" displayWidth=");
        sb.append(this.f6383b);
        sb.append(" displayHeight=");
        sb.append(this.f6384c);
        sb.append(" physicalPixelDisplaySizeRatio=1.0 rotation=0 offsetX=0 offsetY=0 scale=1.0}");
        return sb.toString();
    }
}
