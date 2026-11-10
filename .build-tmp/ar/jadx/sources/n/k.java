package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public y0.g f3946a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public y0.d f3947b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a1.c f3948c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public y0.j f3949d = null;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return b4.l.a(this.f3946a, kVar.f3946a) && b4.l.a(this.f3947b, kVar.f3947b) && b4.l.a(this.f3948c, kVar.f3948c) && b4.l.a(this.f3949d, kVar.f3949d);
    }

    public final int hashCode() {
        y0.g gVar = this.f3946a;
        int iHashCode = (gVar == null ? 0 : gVar.hashCode()) * 31;
        y0.d dVar = this.f3947b;
        int iHashCode2 = (iHashCode + (dVar == null ? 0 : dVar.hashCode())) * 31;
        a1.c cVar = this.f3948c;
        int iHashCode3 = (iHashCode2 + (cVar == null ? 0 : cVar.hashCode())) * 31;
        y0.j jVar = this.f3949d;
        return iHashCode3 + (jVar != null ? jVar.hashCode() : 0);
    }

    public final String toString() {
        return "BorderCache(imageBitmap=" + this.f3946a + ", canvas=" + this.f3947b + ", canvasDrawScope=" + this.f3948c + ", borderPath=" + this.f3949d + ')';
    }
}
