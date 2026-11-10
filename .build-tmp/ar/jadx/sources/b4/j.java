package b4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class j extends d implements i, f4.a, p3.c {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f688j;

    public j(int i5, Class cls, String str, String str2, int i6) {
        this(i5, c.f679d, cls, str, str2, i6, 0);
    }

    public j(int i5, Object obj, Class cls, String str, String str2, int i6, int i7) {
        super(obj, cls, str, str2, (i6 & 1) == 1);
        this.f688j = i5;
    }

    @Override // b4.d
    public final f4.a d() {
        x.f698a.getClass();
        return this;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof j) {
            j jVar = (j) obj;
            return this.f683g.equals(jVar.f683g) && this.f684h.equals(jVar.f684h) && l.a(this.f681e, jVar.f681e) && e().equals(jVar.e());
        }
        if (!(obj instanceof j)) {
            return false;
        }
        f4.a aVar = this.f680d;
        if (aVar == null) {
            d();
            this.f680d = this;
            aVar = this;
        }
        return obj.equals(aVar);
    }

    @Override // b4.i
    public final int getArity() {
        return this.f688j;
    }

    public final int hashCode() {
        e();
        return this.f684h.hashCode() + ((this.f683g.hashCode() + (e().hashCode() * 31)) * 31);
    }

    public final String toString() {
        f4.a aVar = this.f680d;
        if (aVar == null) {
            d();
            this.f680d = this;
            aVar = this;
        }
        if (aVar != this) {
            return aVar.toString();
        }
        String str = this.f683g;
        if ("<init>".equals(str)) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + str + " (Kotlin reflection is not available)";
    }
}
