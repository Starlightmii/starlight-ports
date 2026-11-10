package r0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f5676a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p f5677b;

    public j(p pVar, p pVar2) {
        this.f5676a = pVar;
        this.f5677b = pVar2;
    }

    @Override // r0.p
    public final Object a(a4.e eVar, Object obj) {
        return this.f5677b.a(eVar, this.f5676a.a(eVar, obj));
    }

    @Override // r0.p
    public final boolean b(a4.c cVar) {
        return this.f5676a.b(cVar) && this.f5677b.b(cVar);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return b4.l.a(this.f5676a, jVar.f5676a) && b4.l.a(this.f5677b, jVar.f5677b);
    }

    public final int hashCode() {
        return (this.f5677b.hashCode() * 31) + this.f5676a.hashCode();
    }

    public final String toString() {
        return "[" + ((String) a(i.f5675e, "")) + ']';
    }
}
