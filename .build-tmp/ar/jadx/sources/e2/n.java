package e2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final n f2038g = new n(false, 0, true, 1, 1, f2.b.f2445f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f2039a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2040b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f2041c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2042d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2043e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f2.b f2044f;

    public n(boolean z2, int i5, boolean z3, int i6, int i7, f2.b bVar) {
        this.f2039a = z2;
        this.f2040b = i5;
        this.f2041c = z3;
        this.f2042d = i6;
        this.f2043e = i7;
        this.f2044f = bVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return this.f2039a == nVar.f2039a && this.f2040b == nVar.f2040b && this.f2041c == nVar.f2041c && this.f2042d == nVar.f2042d && this.f2043e == nVar.f2043e && b4.l.a(this.f2044f, nVar.f2044f);
    }

    public final int hashCode() {
        return this.f2044f.f2446d.hashCode() + m.h.b(this.f2043e, m.h.b(this.f2042d, b.b.c(m.h.b(this.f2040b, Boolean.hashCode(this.f2039a) * 31, 31), 31, this.f2041c), 31), 961);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("ImeOptions(singleLine=");
        sb.append(this.f2039a);
        sb.append(", capitalization=");
        int i5 = this.f2040b;
        if (i5 == -1) {
            str = "Unspecified";
        } else if (i5 == 0) {
            str = "None";
        } else if (i5 == 1) {
            str = "Characters";
        } else if (i5 == 2) {
            str = "Words";
        } else {
            str = i5 == 3 ? "Sentences" : "Invalid";
        }
        sb.append((Object) str);
        sb.append(", autoCorrect=");
        sb.append(this.f2041c);
        sb.append(", keyboardType=");
        sb.append((Object) h1.c.Q(this.f2042d));
        sb.append(", imeAction=");
        sb.append((Object) m.a(this.f2043e));
        sb.append(", platformImeOptions=null, hintLocales=");
        sb.append(this.f2044f);
        sb.append(')');
        return sb.toString();
    }
}
