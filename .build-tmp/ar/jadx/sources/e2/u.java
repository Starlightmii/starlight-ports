package e2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2056a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2057b;

    public u(int i5, int i6) {
        this.f2056a = i5;
        this.f2057b = i6;
    }

    @Override // e2.i
    public final void a(j jVar) {
        boolean z2 = jVar.f2030d != -1;
        a2.g gVar = jVar.f2027a;
        if (z2) {
            jVar.f2030d = -1;
            jVar.f2031e = -1;
        }
        int iM = d4.a.m(this.f2056a, 0, gVar.b());
        int iM2 = d4.a.m(this.f2057b, 0, gVar.b());
        if (iM != iM2) {
            if (iM < iM2) {
                jVar.e(iM, iM2);
            } else {
                jVar.e(iM2, iM);
            }
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        return this.f2056a == uVar.f2056a && this.f2057b == uVar.f2057b;
    }

    public final int hashCode() {
        return (this.f2056a * 31) + this.f2057b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SetComposingRegionCommand(start=");
        sb.append(this.f2056a);
        sb.append(", end=");
        return b.b.f(sb, this.f2057b, ')');
    }
}
