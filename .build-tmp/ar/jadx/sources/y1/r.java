package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f6809a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6810b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f6811c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final j2.o f6812d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final t f6813e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final j2.g f6814f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f6815g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f6816h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final j2.p f6817i;

    public r(int i5, int i6, long j5, j2.o oVar, t tVar, j2.g gVar, int i7, int i8, j2.p pVar) {
        this.f6809a = i5;
        this.f6810b = i6;
        this.f6811c = j5;
        this.f6812d = oVar;
        this.f6813e = tVar;
        this.f6814f = gVar;
        this.f6815g = i7;
        this.f6816h = i8;
        this.f6817i = pVar;
        if (k2.m.a(j5, k2.m.f3421c) || k2.m.c(j5) >= 0.0f) {
            return;
        }
        throw new IllegalStateException(("lineHeight can't be negative (" + k2.m.c(j5) + ')').toString());
    }

    public final r a(r rVar) {
        return rVar == null ? this : s.a(this, rVar.f6809a, rVar.f6810b, rVar.f6811c, rVar.f6812d, rVar.f6813e, rVar.f6814f, rVar.f6815g, rVar.f6816h, rVar.f6817i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.f6809a == rVar.f6809a && this.f6810b == rVar.f6810b && k2.m.a(this.f6811c, rVar.f6811c) && b4.l.a(this.f6812d, rVar.f6812d) && b4.l.a(this.f6813e, rVar.f6813e) && b4.l.a(this.f6814f, rVar.f6814f) && this.f6815g == rVar.f6815g && this.f6816h == rVar.f6816h && b4.l.a(this.f6817i, rVar.f6817i);
    }

    public final int hashCode() {
        int iB = m.h.b(this.f6810b, Integer.hashCode(this.f6809a) * 31, 31);
        k2.n[] nVarArr = k2.m.f3420b;
        int iB2 = b.b.b(iB, 31, this.f6811c);
        j2.o oVar = this.f6812d;
        int iHashCode = (iB2 + (oVar != null ? oVar.hashCode() : 0)) * 31;
        t tVar = this.f6813e;
        int iHashCode2 = (iHashCode + (tVar != null ? tVar.hashCode() : 0)) * 31;
        j2.g gVar = this.f6814f;
        int iB3 = m.h.b(this.f6816h, m.h.b(this.f6815g, (iHashCode2 + (gVar != null ? gVar.hashCode() : 0)) * 31, 31), 31);
        j2.p pVar = this.f6817i;
        return iB3 + (pVar != null ? pVar.hashCode() : 0);
    }

    public final String toString() {
        return "ParagraphStyle(textAlign=" + ((Object) j2.i.a(this.f6809a)) + ", textDirection=" + ((Object) j2.k.a(this.f6810b)) + ", lineHeight=" + ((Object) k2.m.d(this.f6811c)) + ", textIndent=" + this.f6812d + ", platformStyle=" + this.f6813e + ", lineHeightStyle=" + this.f6814f + ", lineBreak=" + ((Object) j2.e.a(this.f6815g)) + ", hyphens=" + ((Object) j2.d.a(this.f6816h)) + ", textMotion=" + this.f6817i + ')';
    }
}
