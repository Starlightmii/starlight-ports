package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s.e f4467a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i4.h f4468b;

    public h(s.e eVar, i4.h hVar) {
        this.f4467a = eVar;
        this.f4468b = hVar;
    }

    public final String toString() {
        i4.h hVar = this.f4468b;
        if (hVar.f2736h.j(i4.u.f2776e) != null) {
            throw new ClassCastException();
        }
        StringBuilder sb = new StringBuilder("Request@");
        int iHashCode = hashCode();
        h1.c.m(16);
        String string = Integer.toString(iHashCode, 16);
        b4.l.d("toString(this, checkRadix(radix))", string);
        sb.append(string);
        sb.append("(currentBounds()=");
        sb.append(this.f4467a.a());
        sb.append(", continuation=");
        sb.append(hVar);
        sb.append(')');
        return sb.toString();
    }
}
