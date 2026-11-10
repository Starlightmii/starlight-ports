package b4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class r extends d implements f4.d {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f692j;

    public r(int i5, Class cls, Object obj, String str, String str2) {
        super(obj, cls, str, str2, (i5 & 1) == 1);
        this.f692j = false;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof r) {
            r rVar = (r) obj;
            return e().equals(rVar.e()) && this.f683g.equals(rVar.f683g) && this.f684h.equals(rVar.f684h) && l.a(this.f681e, rVar.f681e);
        }
        if (obj instanceof f4.d) {
            return obj.equals(f());
        }
        return false;
    }

    public final f4.a f() {
        if (this.f692j) {
            return this;
        }
        f4.a aVar = this.f680d;
        if (aVar != null) {
            return aVar;
        }
        f4.a aVarD = d();
        this.f680d = aVarD;
        return aVarD;
    }

    public final int hashCode() {
        return this.f684h.hashCode() + ((this.f683g.hashCode() + (e().hashCode() * 31)) * 31);
    }

    public final String toString() {
        f4.a aVarF = f();
        if (aVarF != this) {
            return aVarF.toString();
        }
        return "property " + this.f683g + " (Kotlin reflection is not available)";
    }
}
