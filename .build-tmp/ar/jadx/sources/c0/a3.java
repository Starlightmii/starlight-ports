package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f745a;

    public a3(String str) {
        this.f745a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && a3.class == obj.getClass() && b4.l.a(this.f745a, ((a3) obj).f745a);
    }

    public final int hashCode() {
        return m.h.c(1) + b.b.c(this.f745a.hashCode() * 961, 31, false);
    }
}
