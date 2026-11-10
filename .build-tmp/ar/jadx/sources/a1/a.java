package a1;

import b4.l;
import k2.k;
import y0.r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k2.b f18a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public k f19b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public r f20c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f21d;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return l.a(this.f18a, aVar.f18a) && this.f19b == aVar.f19b && l.a(this.f20c, aVar.f20c) && x0.f.a(this.f21d, aVar.f21d);
    }

    public final int hashCode() {
        return Long.hashCode(this.f21d) + ((this.f20c.hashCode() + ((this.f19b.hashCode() + (this.f18a.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "DrawParams(density=" + this.f18a + ", layoutDirection=" + this.f19b + ", canvas=" + this.f20c + ", size=" + ((Object) x0.f.f(this.f21d)) + ')';
    }
}
