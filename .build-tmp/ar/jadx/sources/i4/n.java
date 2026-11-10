package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2755a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f2756b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a4.f f2757c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f2758d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Throwable f2759e;

    public n(Object obj, e eVar, a4.f fVar, Object obj2, Throwable th) {
        this.f2755a = obj;
        this.f2756b = eVar;
        this.f2757c = fVar;
        this.f2758d = obj2;
        this.f2759e = th;
    }

    public /* synthetic */ n(Object obj, e eVar, a4.f fVar, Throwable th, int i5) {
        this(obj, (i5 & 2) != 0 ? null : eVar, (i5 & 4) != 0 ? null : fVar, (Object) null, (i5 & 16) != 0 ? null : th);
    }

    public static n a(n nVar, e eVar, Throwable th, int i5) {
        Object obj = nVar.f2755a;
        if ((i5 & 2) != 0) {
            eVar = nVar.f2756b;
        }
        e eVar2 = eVar;
        a4.f fVar = nVar.f2757c;
        Object obj2 = nVar.f2758d;
        if ((i5 & 16) != 0) {
            th = nVar.f2759e;
        }
        return new n(obj, eVar2, fVar, obj2, th);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return b4.l.a(this.f2755a, nVar.f2755a) && b4.l.a(this.f2756b, nVar.f2756b) && b4.l.a(this.f2757c, nVar.f2757c) && b4.l.a(this.f2758d, nVar.f2758d) && b4.l.a(this.f2759e, nVar.f2759e);
    }

    public final int hashCode() {
        Object obj = this.f2755a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        e eVar = this.f2756b;
        int iHashCode2 = (iHashCode + (eVar == null ? 0 : eVar.hashCode())) * 31;
        a4.f fVar = this.f2757c;
        int iHashCode3 = (iHashCode2 + (fVar == null ? 0 : fVar.hashCode())) * 31;
        Object obj2 = this.f2758d;
        int iHashCode4 = (iHashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.f2759e;
        return iHashCode4 + (th != null ? th.hashCode() : 0);
    }

    public final String toString() {
        return "CompletedContinuation(result=" + this.f2755a + ", cancelHandler=" + this.f2756b + ", onCancellation=" + this.f2757c + ", idempotentResume=" + this.f2758d + ", cancelCause=" + this.f2759e + ')';
    }
}
