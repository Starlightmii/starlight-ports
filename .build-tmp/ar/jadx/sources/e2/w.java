package e2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2060a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2061b;

    public w(int i5, int i6) {
        this.f2060a = i5;
        this.f2061b = i6;
    }

    @Override // e2.i
    public final void a(j jVar) {
        int iM = d4.a.m(this.f2060a, 0, jVar.f2027a.b());
        int iM2 = d4.a.m(this.f2061b, 0, jVar.f2027a.b());
        if (iM < iM2) {
            jVar.f(iM, iM2);
        } else {
            jVar.f(iM2, iM);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w)) {
            return false;
        }
        w wVar = (w) obj;
        return this.f2060a == wVar.f2060a && this.f2061b == wVar.f2061b;
    }

    public final int hashCode() {
        return (this.f2060a * 31) + this.f2061b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SetSelectionCommand(start=");
        sb.append(this.f2060a);
        sb.append(", end=");
        return b.b.f(sb, this.f2061b, ')');
    }
}
