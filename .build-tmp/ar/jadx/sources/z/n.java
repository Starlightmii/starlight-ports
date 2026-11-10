package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f6982a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f6983b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f6984c;

    public n(m mVar, m mVar2, boolean z2) {
        this.f6982a = mVar;
        this.f6983b = mVar2;
        this.f6984c = z2;
    }

    public static n a(n nVar, m mVar, m mVar2, boolean z2, int i5) {
        if ((i5 & 1) != 0) {
            mVar = nVar.f6982a;
        }
        if ((i5 & 2) != 0) {
            mVar2 = nVar.f6983b;
        }
        nVar.getClass();
        return new n(mVar, mVar2, z2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return b4.l.a(this.f6982a, nVar.f6982a) && b4.l.a(this.f6983b, nVar.f6983b) && this.f6984c == nVar.f6984c;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f6984c) + ((this.f6983b.hashCode() + (this.f6982a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "Selection(start=" + this.f6982a + ", end=" + this.f6983b + ", handlesCrossed=" + this.f6984c + ')';
    }
}
