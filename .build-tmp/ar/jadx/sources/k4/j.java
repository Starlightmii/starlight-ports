package k4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f3476a = new i();

    public static c a(int i5, int i6, a aVar) {
        if ((i6 & 1) != 0) {
            i5 = 0;
        }
        int i7 = i6 & 2;
        a aVar2 = a.f3435d;
        if (i7 != 0) {
            aVar = aVar2;
        }
        if (i5 == -2) {
            if (aVar != aVar2) {
                return new n(1, aVar);
            }
            g.f3474b.getClass();
            return new c(f.f3473b);
        }
        if (i5 == -1) {
            if (aVar == aVar2) {
                return new n(1, a.f3436e);
            }
            throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
        }
        if (i5 == 0) {
            return aVar == aVar2 ? new c(0) : new n(1, aVar);
        }
        if (i5 != Integer.MAX_VALUE) {
            return aVar == aVar2 ? new c(i5) : new n(i5, aVar);
        }
        return new c(Integer.MAX_VALUE);
    }
}
