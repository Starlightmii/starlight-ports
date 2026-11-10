package n0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4062a = 0;

    public final String toString() {
        StringBuilder sb = new StringBuilder("IntRef(element = ");
        sb.append(this.f4062a);
        sb.append(")@");
        int iHashCode = hashCode();
        h1.c.m(16);
        String string = Integer.toString(iHashCode, 16);
        b4.l.d("toString(...)", string);
        sb.append(string);
        return sb.toString();
    }
}
