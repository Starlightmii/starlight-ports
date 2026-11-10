package d2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f1620a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k f1621b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1622c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1623d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f1624e;

    public q(p pVar, k kVar, int i5, int i6, Object obj) {
        this.f1620a = pVar;
        this.f1621b = kVar;
        this.f1622c = i5;
        this.f1623d = i6;
        this.f1624e = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return b4.l.a(this.f1620a, qVar.f1620a) && b4.l.a(this.f1621b, qVar.f1621b) && this.f1622c == qVar.f1622c && this.f1623d == qVar.f1623d && b4.l.a(this.f1624e, qVar.f1624e);
    }

    public final int hashCode() {
        p pVar = this.f1620a;
        int iB = m.h.b(this.f1623d, m.h.b(this.f1622c, (((pVar == null ? 0 : pVar.hashCode()) * 31) + this.f1621b.f1615d) * 31, 31), 31);
        Object obj = this.f1624e;
        return iB + (obj != null ? obj.hashCode() : 0);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("TypefaceRequest(fontFamily=");
        sb.append(this.f1620a);
        sb.append(", fontWeight=");
        sb.append(this.f1621b);
        sb.append(", fontStyle=");
        String str2 = "Invalid";
        int i5 = this.f1622c;
        if (i5 == 0) {
            str = "Normal";
        } else {
            str = i5 == 1 ? "Italic" : "Invalid";
        }
        sb.append((Object) str);
        sb.append(", fontSynthesis=");
        int i6 = this.f1623d;
        if (i6 == 0) {
            str2 = "None";
        } else if (i6 == 1) {
            str2 = "All";
        } else if (i6 == 2) {
            str2 = "Weight";
        } else if (i6 == 3) {
            str2 = "Style";
        }
        sb.append((Object) str2);
        sb.append(", resourceLoaderCacheKey=");
        sb.append(this.f1624e);
        sb.append(')');
        return sb.toString();
    }
}
