package p0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class y implements x {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final n0.a f4766d = new n0.a(0);

    public final boolean e(int i5) {
        return (i5 & this.f4766d.get()) != 0;
    }

    public final void f(int i5) {
        n0.a aVar;
        int i6;
        do {
            aVar = this.f4766d;
            i6 = aVar.get();
            if ((i6 & i5) != 0) {
                return;
            }
        } while (!aVar.compareAndSet(i6, i6 | i5));
    }
}
