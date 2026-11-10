package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f6799a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6800b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f6801c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f6802d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f6803e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f6804f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f6805g;

    public o(a aVar, int i5, int i6, int i7, int i8, float f5, float f6) {
        this.f6799a = aVar;
        this.f6800b = i5;
        this.f6801c = i6;
        this.f6802d = i7;
        this.f6803e = i8;
        this.f6804f = f5;
        this.f6805g = f6;
    }

    public final long a(long j5, boolean z2) {
        if (z2) {
            long j6 = g0.f6768b;
            if (g0.a(j5, j6)) {
                return j6;
            }
        }
        int i5 = g0.f6769c;
        int i6 = (int) (j5 >> 32);
        int i7 = this.f6800b;
        return p3.j.g(i6 + i7, ((int) (j5 & 4294967295L)) + i7);
    }

    public final int b(int i5) {
        int i6 = this.f6801c;
        int i7 = this.f6800b;
        return d4.a.m(i5, i7, i6) - i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        return this.f6799a.equals(oVar.f6799a) && this.f6800b == oVar.f6800b && this.f6801c == oVar.f6801c && this.f6802d == oVar.f6802d && this.f6803e == oVar.f6803e && Float.compare(this.f6804f, oVar.f6804f) == 0 && Float.compare(this.f6805g, oVar.f6805g) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f6805g) + b.b.a(this.f6804f, m.h.b(this.f6803e, m.h.b(this.f6802d, m.h.b(this.f6801c, m.h.b(this.f6800b, this.f6799a.hashCode() * 31, 31), 31), 31), 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ParagraphInfo(paragraph=");
        sb.append(this.f6799a);
        sb.append(", startIndex=");
        sb.append(this.f6800b);
        sb.append(", endIndex=");
        sb.append(this.f6801c);
        sb.append(", startLineIndex=");
        sb.append(this.f6802d);
        sb.append(", endLineIndex=");
        sb.append(this.f6803e);
        sb.append(", top=");
        sb.append(this.f6804f);
        sb.append(", bottom=");
        return b.b.e(sb, this.f6805g, ')');
    }
}
