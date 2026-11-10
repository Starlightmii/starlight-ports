package e2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2025a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2026b;

    public h(int i5, int i6) {
        this.f2025a = i5;
        this.f2026b = i6;
        if (i5 < 0 || i6 < 0) {
            throw new IllegalArgumentException(("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were " + i5 + " and " + i6 + " respectively.").toString());
        }
    }

    @Override // e2.i
    public final void a(j jVar) {
        int i5 = 0;
        for (int i6 = 0; i6 < this.f2025a; i6++) {
            int i7 = i5 + 1;
            int i8 = jVar.f2028b;
            if (i8 <= i7) {
                i5 = i8;
                break;
            }
            i5 = (Character.isHighSurrogate(jVar.b((i8 - i7) + (-1))) && Character.isLowSurrogate(jVar.b(jVar.f2028b - i7))) ? i5 + 2 : i7;
        }
        int iB = 0;
        for (int i9 = 0; i9 < this.f2026b; i9++) {
            int i10 = iB + 1;
            int i11 = jVar.f2029c;
            a2.g gVar = jVar.f2027a;
            if (i11 + i10 >= gVar.b()) {
                iB = gVar.b() - jVar.f2029c;
                break;
            }
            iB = (Character.isHighSurrogate(jVar.b((jVar.f2029c + i10) + (-1))) && Character.isLowSurrogate(jVar.b(jVar.f2029c + i10))) ? iB + 2 : i10;
        }
        int i12 = jVar.f2029c;
        jVar.a(i12, iB + i12);
        int i13 = jVar.f2028b;
        jVar.a(i13 - i5, i13);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.f2025a == hVar.f2025a && this.f2026b == hVar.f2026b;
    }

    public final int hashCode() {
        return (this.f2025a * 31) + this.f2026b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DeleteSurroundingTextInCodePointsCommand(lengthBeforeCursor=");
        sb.append(this.f2025a);
        sb.append(", lengthAfterCursor=");
        return b.b.f(sb, this.f2026b, ')');
    }
}
