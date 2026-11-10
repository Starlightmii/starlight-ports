package e2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2022a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2023b;

    public g(int i5, int i6) {
        this.f2022a = i5;
        this.f2023b = i6;
        if (i5 < 0 || i6 < 0) {
            throw new IllegalArgumentException(("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were " + i5 + " and " + i6 + " respectively.").toString());
        }
    }

    @Override // e2.i
    public final void a(j jVar) {
        int i5 = jVar.f2029c;
        a2.g gVar = jVar.f2027a;
        int i6 = this.f2023b;
        int iB = i5 + i6;
        if (((i5 ^ iB) & (i6 ^ iB)) < 0) {
            iB = gVar.b();
        }
        jVar.a(jVar.f2029c, Math.min(iB, gVar.b()));
        int i7 = jVar.f2028b;
        int i8 = this.f2022a;
        int i9 = i7 - i8;
        if (((i7 ^ i9) & (i8 ^ i7)) < 0) {
            i9 = 0;
        }
        jVar.a(Math.max(0, i9), jVar.f2028b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return this.f2022a == gVar.f2022a && this.f2023b == gVar.f2023b;
    }

    public final int hashCode() {
        return (this.f2022a * 31) + this.f2023b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DeleteSurroundingTextCommand(lengthBeforeCursor=");
        sb.append(this.f2022a);
        sb.append(", lengthAfterCursor=");
        return b.b.f(sb, this.f2023b, ')');
    }
}
