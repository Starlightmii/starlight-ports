package w1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6272a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p3.c f6273b;

    public a(String str, p3.c cVar) {
        this.f6272a = str;
        this.f6273b = cVar;
    }

    public final String a() {
        return this.f6272a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return b4.l.a(this.f6272a, aVar.f6272a) && b4.l.a(this.f6273b, aVar.f6273b);
    }

    public final int hashCode() {
        String str = this.f6272a;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        p3.c cVar = this.f6273b;
        return iHashCode + (cVar != null ? cVar.hashCode() : 0);
    }

    public final String toString() {
        return "AccessibilityAction(label=" + this.f6272a + ", action=" + this.f6273b + ')';
    }
}
