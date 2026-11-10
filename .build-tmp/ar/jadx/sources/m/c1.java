package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface c1 {
    boolean a();

    long b(q qVar, q qVar2, q qVar3);

    q d(long j5, q qVar, q qVar2, q qVar3);

    q f(long j5, q qVar, q qVar2, q qVar3);

    default q o(q qVar, q qVar2, q qVar3) {
        return d(b(qVar, qVar2, qVar3), qVar, qVar2, qVar3);
    }
}
