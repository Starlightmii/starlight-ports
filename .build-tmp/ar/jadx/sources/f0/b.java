package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends l {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2091f;

    public b(int i5) {
        this.f2091f = i5;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof b) && ((b) obj).f2091f == this.f2091f;
    }

    public final int hashCode() {
        return this.f2091f * 31;
    }
}
