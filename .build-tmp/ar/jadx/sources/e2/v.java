package e2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y1.f f2058a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2059b;

    public v(String str, int i5) {
        this.f2058a = new y1.f(str, null, 6);
        this.f2059b = i5;
    }

    @Override // e2.i
    public final void a(j jVar) {
        int i5 = jVar.f2030d;
        y1.f fVar = this.f2058a;
        if (i5 != -1) {
            int i6 = jVar.f2031e;
            String str = fVar.f6759d;
            String str2 = fVar.f6759d;
            jVar.d(i5, i6, str);
            if (str2.length() > 0) {
                jVar.e(i5, str2.length() + i5);
            }
        } else {
            int i7 = jVar.f2028b;
            int i8 = jVar.f2029c;
            String str3 = fVar.f6759d;
            String str4 = fVar.f6759d;
            jVar.d(i7, i8, str3);
            if (str4.length() > 0) {
                jVar.e(i7, str4.length() + i7);
            }
        }
        int i9 = jVar.f2028b;
        int i10 = jVar.f2029c;
        int i11 = i9 == i10 ? i10 : -1;
        int i12 = this.f2059b;
        int iM = d4.a.m(i12 > 0 ? (i11 + i12) - 1 : (i11 + i12) - fVar.f6759d.length(), 0, jVar.f2027a.b());
        jVar.f(iM, iM);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        return b4.l.a(this.f2058a.f6759d, vVar.f2058a.f6759d) && this.f2059b == vVar.f2059b;
    }

    public final int hashCode() {
        return (this.f2058a.f6759d.hashCode() * 31) + this.f2059b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SetComposingTextCommand(text='");
        sb.append(this.f2058a.f6759d);
        sb.append("', newCursorPosition=");
        return b.b.f(sb, this.f2059b, ')');
    }
}
