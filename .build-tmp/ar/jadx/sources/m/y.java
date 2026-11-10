package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface y extends k {
    @Override // m.k
    default c1 a(a1 a1Var) {
        return new b3.w(this);
    }

    float b(long j5, float f5, float f6, float f7);

    float c(long j5, float f5, float f6, float f7);

    long d(float f5, float f6, float f7);

    default float e(float f5, float f6, float f7) {
        return c(d(f5, f6, f7), f5, f6, f7);
    }
}
