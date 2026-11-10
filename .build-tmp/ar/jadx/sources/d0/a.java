package d0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r0.f f1507a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r0.f f1508b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1509c;

    public a(r0.f fVar, r0.f fVar2, int i5) {
        this.f1507a = fVar;
        this.f1508b = fVar2;
        this.f1509c = i5;
    }

    @Override // d0.i
    public final int a(k2.i iVar, long j5, int i5, k2.k kVar) {
        int i6 = iVar.f3413c;
        int i7 = iVar.f3411a;
        int iA = this.f1508b.a(0, i6 - i7, kVar);
        int i8 = -this.f1507a.a(0, i5, kVar);
        k2.k kVar2 = k2.k.f3416d;
        int i9 = this.f1509c;
        if (kVar != kVar2) {
            i9 = -i9;
        }
        return i7 + iA + i8 + i9;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f1507a.equals(aVar.f1507a) && this.f1508b.equals(aVar.f1508b) && this.f1509c == aVar.f1509c;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f1509c) + b.b.a(this.f1508b.f5671a, Float.hashCode(this.f1507a.f5671a) * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Horizontal(menuAlignment=");
        sb.append(this.f1507a);
        sb.append(", anchorAlignment=");
        sb.append(this.f1508b);
        sb.append(", offset=");
        return b.b.f(sb, this.f1509c, ')');
    }
}
