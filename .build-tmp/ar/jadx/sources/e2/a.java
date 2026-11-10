package e2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y1.f f1973a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1974b;

    public a(String str, int i5) {
        this(new y1.f(str, null, 6), i5);
    }

    public a(y1.f fVar, int i5) {
        this.f1973a = fVar;
        this.f1974b = i5;
    }

    @Override // e2.i
    public final void a(j jVar) {
        int i5 = jVar.f2030d;
        y1.f fVar = this.f1973a;
        if (i5 != -1) {
            jVar.d(i5, jVar.f2031e, fVar.f6759d);
        } else {
            jVar.d(jVar.f2028b, jVar.f2029c, fVar.f6759d);
        }
        int i6 = jVar.f2028b;
        int i7 = jVar.f2029c;
        int i8 = i6 == i7 ? i7 : -1;
        int i9 = this.f1974b;
        int iM = d4.a.m(i9 > 0 ? (i8 + i9) - 1 : (i8 + i9) - fVar.f6759d.length(), 0, jVar.f2027a.b());
        jVar.f(iM, iM);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return b4.l.a(this.f1973a.f6759d, aVar.f1973a.f6759d) && this.f1974b == aVar.f1974b;
    }

    public final int hashCode() {
        return (this.f1973a.f6759d.hashCode() * 31) + this.f1974b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CommitTextCommand(text='");
        sb.append(this.f1973a.f6759d);
        sb.append("', newCursorPosition=");
        return b.b.f(sb, this.f1974b, ')');
    }
}
